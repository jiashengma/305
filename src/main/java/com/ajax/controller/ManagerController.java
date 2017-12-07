/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ajax.controller;

import com.ajax.model.Airport;
import com.ajax.model.Constants;
import com.ajax.model.Customer;
import com.ajax.model.Employee;
import com.ajax.model.Flight;
import com.ajax.model.Leg;
import com.ajax.model.Reservation;
import com.ajax.persistence.MySQLConnection;
import com.ajax.persistence.PersonEntitiesManager;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author antonio
 */
@Controller
@ControllerAdvice
public class ManagerController {

    @Autowired
    PersonEntitiesManager personEntitiesManager;

    @RequestMapping(value = "/sales", method = RequestMethod.GET)
    public ModelAndView salesReport(@ModelAttribute("sales") ArrayList<Reservation> results, @ModelAttribute("errors") ArrayList<String> errors, @RequestParam Map<String, String> requestParams) {
        ModelAndView mv;
        String month = requestParams.get("month");
        String year = requestParams.get("year");
        if (month == null && year == null) {
            return new ModelAndView("salesform");
        }
        if (month == null || !month.matches("0*[1-9][0-2]?")) {
            errors.add("Please enter a valid month.");
        }
        if (year == null || !year.matches("[1-9][0-9]{3}")) {
            errors.add("Please enter a valid year.");
        }
        if (!errors.isEmpty()) {
            mv = new ModelAndView("salesform");
            return mv;
        }
        mv = new ModelAndView("salesreport");
        Connection conn = MySQLConnection.connect();
        try {
            PreparedStatement stmt = conn.prepareStatement("SELECT ResrDate, ResrNo, TotalFare FROM reservation "
                    + "WHERE MONTH(ResrDate) = ? AND YEAR(ResrDate) = ?;");
            stmt.setInt(1, Integer.parseInt(requestParams.get("month")));
            stmt.setInt(2, Integer.parseInt(requestParams.get("year")));
            ResultSet rs = stmt.executeQuery();
            conn.commit();
            while (rs.next()) {
                Reservation reservation = new Reservation();
                reservation.setReservationNo(rs.getInt("ResrNo"));
                reservation.setTotalFare(rs.getDouble("TotalFare"));
                results.add(reservation);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ManagerController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return mv;
    }

    @RequestMapping(value = "/flight-listing", method = RequestMethod.GET)
    public ModelAndView flightListing(@ModelAttribute("flights") ArrayList<Flight> results) {
        ModelAndView mv = new ModelAndView("flight-listing");
        Connection conn = MySQLConnection.connect();
        try {
            PreparedStatement stmt = conn.prepareStatement("SELECT F.FlightNo, F.AirlineID, F.NoOfSeats,\n"
                    + "L.LegNo, L.DepAirportId, L.ArrTime, L.ArrAirportId, L.DepTime FROM flight F, leg L WHERE F.AirlineID = L.AirlineID AND F.FlightNo = L.FlightNo");
            ResultSet rs = stmt.executeQuery();
            conn.commit();
            Flight lastFlight = null;
            while (rs.next()) {
                Flight f = new Flight();
                f.setFlightNo(rs.getInt(1));
                f.setAirline(rs.getString(2));
                f.setNumberOfSeats(rs.getInt(3));
                // The same flight shouldn't be added to the list twice.
                if (lastFlight == null || !lastFlight.equals(f)) {
                    List<Leg> legs = new ArrayList<Leg>();
                    Leg l = new Leg(rs.getInt(4), Airport.getAirportByID(rs.getString(5)), rs.getTimestamp(6), rs.getTimestamp(8), Airport.getAirportByID(rs.getString(7)));
                    legs.add(l);
                    results.add(f);
                    f.setLegs(legs);
                    lastFlight = f;
                } else {
                    Leg l = new Leg(rs.getInt(4), Airport.getAirportByID(rs.getString(5)), rs.getTimestamp(6), rs.getTimestamp(8), Airport.getAirportByID(rs.getString(7)));
                    lastFlight.getLegs().add(l);
                }

            }
        } catch (SQLException ex) {
            Logger.getLogger(ManagerController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return mv;
    }

    /* SELECT AccountNo, MAX(Revenue) FROM (
		SELECT AccountNo, SUM(TotalFare) AS Revenue
		FROM Reservation
		GROUP BY AccountNo
	) ; */
    @RequestMapping(value = "/max-revenue", method = RequestMethod.GET)
    public ModelAndView maxRevenue() {
        ModelAndView mv = new ModelAndView("max-revenue");
        Connection conn = MySQLConnection.connect();
        try {
            PreparedStatement stmt = conn.prepareStatement("SELECT C.Id, C.AccountNo, Revenue FROM (SELECT  MAX(Revenue) as Revenue FROM ( SELECT AccountNo, SUM(TotalFare) AS Revenue FROM reservation GROUP BY AccountNo) _, customer C WHERE C.AccountNo = _.AccountNo) RES, reservation R, customer C WHERE R.TotalFare = RES.Revenue AND R.AccountNo = C.AccountNo;");

            ResultSet rs = stmt.executeQuery();
            conn.commit();
            while (rs.next()) {
                Customer customer = personEntitiesManager.getCustomerById(rs.getInt(1));
                mv.addObject("customer", customer);
                mv.addObject("maxCustomerRevenue", rs.getString(3));
            }

            stmt = conn.prepareStatement("SELECT E.Id, P.FirstName, P.LastName, MAX(R.Revenue) "
                    + "FROM ( "
                    + "SELECT RepSSN, SUM(TotalFare) AS Revenue "
                    + "FROM reservation GROUP BY RepSSN) R, employee E, person P "
                    + "WHERE E.ssn = R.RepSSN and E.Id = P.Id GROUP BY E.Id;");
            rs = stmt.executeQuery();
            conn.commit();
            while (rs.next()) {
                Employee representative = new Employee();
                representative.setId(rs.getInt(1));
                representative.setFirstName(rs.getString(2));
                representative.setLastName(rs.getString(3));

                mv.addObject("representative", representative);
                mv.addObject("maxRepresentativeRevenue", rs.getString(4));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ManagerController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return mv;
    }

    @RequestMapping(value = "/delays", method = RequestMethod.GET)
    public ModelAndView delayListing(@ModelAttribute("delays") ArrayList<Flight> delays,
            @ModelAttribute("ontime") ArrayList<Flight> ontime) {
        ModelAndView mv = new ModelAndView("delay-listing");
        Connection conn = MySQLConnection.connect();
        try {
            PreparedStatement stmt = conn.prepareStatement("SELECT DISTINCT L.AirlineID, L.FlightNo\n"
                    + "FROM leg L, stopsat S\n"
                    + "WHERE L.DepAirportId = S.AirportId\n"
                    + "AND L.AirlineID = S.AirlineID\n"
                    + "AND L.FlightNo = S.FlightNo\n"
                    + "AND (S.DepTime > L.DepTime\n"
                    + "OR S.ArrTime > L.ArrTime);");

            ResultSet rs = stmt.executeQuery();
            conn.commit();
            while (rs.next()) {
                Flight f = new Flight();
                f.setAirline(rs.getString(1));
                f.setFlightNo(rs.getInt(2));
                delays.add(f);
            }

            stmt = conn.prepareStatement("SELECT DISTINCT L.AirlineID, L.FlightNo\n"
                    + "FROM leg L, stopsat S\n"
                    + "WHERE L.DepAirportId = S.AirportId\n"
                    + "AND L.AirlineID = S.AirlineID\n"
                    + "AND L.FlightNo = S.FlightNo\n"
                    + "AND S.DepTime = L.DepTime\n"
                    + "AND S.ArrTime = L.ArrTime;");
            rs = stmt.executeQuery();
            conn.commit();
            while (rs.next()) {
                Flight f = new Flight();
                f.setAirline(rs.getString(1));
                f.setFlightNo(rs.getInt(2));
                ontime.add(f);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ManagerController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return mv;
    }

    @RequestMapping(value = "/reservation-search", method = RequestMethod.GET)
    public ModelAndView reservationSearch() {
        ModelAndView mv = new ModelAndView("reservation-search-form");

        return mv;
    }

    @RequestMapping(value = "/reservation-search", method = RequestMethod.POST)
    public ModelAndView reservationResults(@RequestParam Map<String, String> requestParams,
            @ModelAttribute("results") ArrayList<Reservation> results) {
        ModelAndView mv = new ModelAndView("reservation-search-form");
        if (!requestParams.containsKey("mode")) {
            mv.addObject("error", "You must specify a valid mode!");
            return mv;
        }
        Connection conn = MySQLConnection.connect();
        try {
            PreparedStatement stmt;
            if (requestParams.get("mode").equals("flightNo")) {
                String airlineId = requestParams.getOrDefault("airlineId", "");
                String flightNo = requestParams.getOrDefault("flightNo", "");
                if (airlineId.isEmpty()
                        || !flightNo.matches("[0-9]+")) {
                    mv.addObject("error", "You must enter a valid airline ID and flight number!");
                    return mv;
                }
                stmt = conn.prepareStatement("SELECT DISTINCT R.ResrNo, R.ResrDate, R.TotalFare\n"
                        + "FROM includes I, reservation R\n"
                        + "WHERE I.ResrNo = R.ResrNo\n"
                        + "AND AirlineID = ?\n"
                        + "AND FlightNo = ?;");
                stmt.setString(1, airlineId);
                stmt.setInt(2, Integer.parseInt(flightNo));
                ResultSet rs = stmt.executeQuery();
                conn.commit();
                while (rs.next()) {
                    Reservation r = new Reservation();
                    r.setReservationNo(rs.getInt(1));
                    r.setDate(rs.getTimestamp(2));
                    r.setTotalFare(rs.getDouble(3));
                    results.add(r);
                }

            } else if (requestParams.get("mode").equals("customerName")) {
                String customerName = requestParams.getOrDefault("customerName", "");
                if (customerName.isEmpty()) {
                    mv.addObject("error", "You must enter a valid customer name!");
                    return mv;
                }

                stmt = conn.prepareStatement("SELECT DISTINCT R.ResrNo, R.ResrDate, R.TotalFare\n"
                        + "FROM reservation R, customer C, person P\n"
                        + "WHERE C.AccountNo = R.AccountNo\n"
                        + "AND C.Id = P.Id\n"
                        + "AND concat(P.FirstName, ' ', P.LastName) = ?;");
                stmt.setString(1, customerName);
                ResultSet rs = stmt.executeQuery();
                conn.commit();
                while (rs.next()) {
                    Reservation r = new Reservation();
                    r.setReservationNo(rs.getInt(1));
                    r.setDate(rs.getTimestamp(2));
                    r.setTotalFare(rs.getDouble(3));
                    results.add(r);
                }

            } else {
                mv.addObject("error", "Invalid parameters.");
                return mv;
            }
        } catch (SQLException ex) {
            Logger.getLogger(ManagerController.class.getName()).log(Level.SEVERE, null, ex);
        }

        return mv;
    }

    @RequestMapping(value = "/airport-search", method = RequestMethod.GET)
    public ModelAndView airportResults(@RequestParam Map<String, String> requestParams,
            @ModelAttribute("results") ArrayList<Flight> results) {
        ModelAndView mv = new ModelAndView("airport-search-form");
        Connection conn = MySQLConnection.connect();
        try {
            PreparedStatement stmt;
            String airportId = requestParams.getOrDefault("airportId", "");
            if (airportId.isEmpty()) {
                return mv;
            }
            if (!airportId.matches("[A-Za-z]{3}")) {
                mv.addObject("error", "You must specify a valid 3-character airport ID.");
                return mv;
            } else {
                stmt = conn.prepareStatement(String.format("SELECT DISTINCT %s, %s \n"
                        + "FROM %s\n"
                        + "WHERE %s = ?\n"
                        + "OR %s = ? ", Constants.AIRLINEID_FIELD, Constants.FLIGHTNO_FIELD,
                        Constants.LEG_TABLE, Constants.DEPATURE_AIRPORT_ID, Constants.ARRIVAL_AIRPORT_ID));
                stmt.setString(1, airportId);
                stmt.setString(2, airportId);
                ResultSet rs = stmt.executeQuery();
                conn.commit();
                while (rs.next()) {
                    Flight f = new Flight();
                    f.setAirline(rs.getString(1));
                    f.setFlightNo(rs.getInt(2));
                    results.add(f);
                }

            }
        } catch (SQLException ex) {
            Logger.getLogger(ManagerController.class.getName()).log(Level.SEVERE, null, ex);
        }

        return mv;
    }

    @RequestMapping(value = "/most-active-flights", method = RequestMethod.GET)
    public ModelAndView airportResults(@ModelAttribute("results") ArrayList<Map<String, String>> results) {
        ModelAndView mv = new ModelAndView("most-active-flights");
        Connection conn = MySQLConnection.connect();

        try {
            PreparedStatement stmt = conn.prepareStatement(String.format("SELECT %s, %s, COUNT(ResrNo) "
                    + "FROM includes "
                    + "GROUP BY %s, %s "
                    + "ORDER BY COUNT(ResrNo) DESC "
                    + "LIMIT 10;", Constants.AIRLINEID_FIELD, Constants.FLIGHTNO_FIELD,
                    Constants.AIRLINEID_FIELD, Constants.FLIGHTNO_FIELD));
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Map<String, String> map = new HashMap<>();
                map.put("airline", rs.getString(1));
                map.put("flightNo", rs.getString(2));
                map.put("numRes", rs.getString(3));
                results.add(map);
            }

        } catch (SQLException ex) {
            Logger.getLogger(ManagerController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return mv;
    }

    @RequestMapping(value = "/customer-flight-search", method = RequestMethod.GET)
    public ModelAndView customerFlightResults(@RequestParam Map<String, String> requestParams,
            @ModelAttribute("results") ArrayList<Customer> results) {
        ModelAndView mv = new ModelAndView("customer-flight-search");
        String airline = requestParams.getOrDefault("airline", "");
        String flightNo = requestParams.getOrDefault("flightNo", "");
        if (airline.isEmpty() && flightNo.isEmpty()) {
            return mv;
        }
        if (!airline.matches("[A-Za-z]{2}") || !flightNo.matches("[0-9]+")) {
            mv.addObject("error", "Please enter a valid flight number and airline ID.");
            return mv;
        }

        Connection conn = MySQLConnection.connect();
        try {
            PreparedStatement stmt = conn.prepareStatement(String.format("SELECT DISTINCT P.%s, P.%s, C.%s, C.%s\n"
                    + "FROM %s RP, %s I, %s P, %s C\n"
                    + "WHERE I.%s = ?\n"
                    + "AND I.%s = ?\n"
                    + "AND I.%s = RP.%s\n"
                    + "AND P.%s = C.%s\n"
                    + "AND RP.%s = P.%s;", Constants.FIRSTNAME_FILED, Constants.LASTNAME_FILED, Constants.ACCOUNTNO_FIELD, Constants.EMAIL_FIELD,
                    Constants.RESERVATION_PASSENGER_TABLE, Constants.INCLUDES_TABLE, Constants.PERSON_TABLE, Constants.CUSTOMER_TABLE,
                    Constants.FLIGHTNO_FIELD, Constants.AIRLINEID_FIELD, Constants.RESERVATION_NO_FIELD, Constants.RESERVATION_NO_FIELD,
                    Constants.ID_FIELD, Constants.ID_FIELD, Constants.ID_FIELD, Constants.ID_FIELD));
            stmt.setString(1, flightNo);
            stmt.setString(2, airline);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Customer c = new Customer();
                c.setFirstName(rs.getString(1));
                c.setLastName(rs.getString(2));
                c.setAccNum(rs.getInt(3));
                c.setEmail(rs.getString(4));
                results.add(c);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ManagerController.class.getName()).log(Level.SEVERE, null, ex);
        }

        return mv;
    }

    @RequestMapping(value = "/revenue-summary", method = RequestMethod.GET)
    public ModelAndView revenueSummary(@RequestParam Map<String, String> requestParams,
            @ModelAttribute("results") ArrayList results) {
        ModelAndView mv = new ModelAndView("revenue-summary");
        Connection conn = MySQLConnection.connect();
        PreparedStatement stmt;
        try {
            String mode = requestParams.get("mode");
            if (mode == null) {
                return mv;
            }
            if (mode.equals("city")) {
                String city = requestParams.get("city");
                if (city == null) {
                    mv.addObject("error", "Please enter a valid city.");
                    return mv;
                }
                stmt = conn.prepareStatement("SELECT City, SUM(TotalFare) FROM (\n"
                        + "SELECT I.ResrNo, MAX(LegNo) AS FinalLeg, TotalFare, AirlineId, FlightNo\n"
                        + "FROM includes I, reservation R\n"
                        + "WHERE I.ResrNo = R.ResrNo\n"
                        + "GROUP BY ResrNo, AirlineId, FlightNo\n"
                        + ") Sub, leg L, airport A\n"
                        + "WHERE Sub.AirlineId = L.AirlineId\n"
                        + "AND Sub.FinalLeg = L.LegNo\n"
                        + "AND A.Id = L.ArrAirportId\n"
                        + "AND Sub.FlightNo = L.FlightNo\n"
                        + "AND City = ?\n"
                        + "GROUP BY (A.City);");
                stmt.setString(1, city);
                ResultSet rs = stmt.executeQuery();
                conn.commit();
                if (rs.next()) {
                    mv.addObject("city", rs.getString(1));
                    mv.addObject("result", rs.getString(2));
                    return mv;
                }
            } 
            else if (mode.equals("flightNo")) {
                String flightNo = requestParams.getOrDefault("flightNo", "");
                String airline = requestParams.getOrDefault("airline", "");
                if (!flightNo.matches("[0-9]+") || !airline.matches("[A-Za-z]{2}")) {
                    mv.addObject("error", "Please enter a valid airline ID and flight number.");
                    return mv;
                }
                stmt = conn.prepareStatement("SELECT AirlineID, FlightNo, TotalFare FROM (\n"
                        + "SELECT DISTINCT I.ResrNo, I.AirlineID, I.FlightNo, R.TotalFare\n"
                        + "FROM includes I, reservation R\n"
                        + "WHERE I.ResrNo = R.ResrNo\n"
                        + ") _\n"
                        + "WHERE FlightNo = ?\n"
                        + "AND AirlineID = ?;");
                stmt.setInt(1, Integer.parseInt(flightNo));
                stmt.setString(2, airline);
                ResultSet rs = stmt.executeQuery();
                conn.commit();
                while (rs.next()){
                    Flight f = new Flight();
                    f.setAirline(rs.getString(1));
                    f.setFlightNo(rs.getInt(2));
                    f.setFare(rs.getDouble(3));
                    results.add(f);
                }
            }
            else if (mode.equals("customer")) {
                String customer = requestParams.getOrDefault("customer", "");
                if (!customer.matches("[0-9]+")) {
                    mv.addObject("error", "Please enter a valid account number.");
                    return mv;
                }
                stmt = conn.prepareStatement("SELECT P.FirstName, P.LastName, R.ResrNo, TotalFare\n" +
                    "FROM reservation R, customer C, person P\n" +
                    "WHERE R.AccountNo = C.AccountNo\n" +
                    "AND C.Id = P.Id\n" +
                    "AND C.AccountNo = ?;");
                stmt.setString(1, customer);
                ResultSet rs = stmt.executeQuery();
                conn.commit();
                while (rs.next()){
                    Reservation r = new Reservation();
                    Customer c = new Customer();
                    c.setAccNum(Integer.parseInt(customer));
                    c.setFirstName(rs.getString(1));
                    c.setLastName(rs.getString(2));
                    r.setReservationNo(rs.getInt(3));
                    r.setTotalFare(rs.getDouble(4));
                    r.setCustomer(c);
                    results.add(r);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(ManagerController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return mv;
    }
    
    @RequestMapping(value="/backup.sql", method=RequestMethod.GET)
    public ModelAndView backupDatabase(HttpServletResponse resp){
        ModelAndView mv = new ModelAndView("backup");
        try {
            Process p = Runtime.getRuntime().exec(String.format("mysqldump %s -u%s -p%s", Constants.DBNAME, 
                    Constants.USERNAME, Constants.PASSWORD));
            StringBuilder output;
            try (InputStream stdout = p.getInputStream()) {
                output = new StringBuilder();
                BufferedReader reader = new BufferedReader(new InputStreamReader(stdout));
                String line;
                while ((line = reader.readLine()) != null){
                    output.append(line);
                    output.append(System.lineSeparator());
                }
            }
            int exitCode = p.waitFor();
            if (exitCode == 0){
                mv.addObject("dump", output);
            } else {
                resp.setHeader("Content-Disposition", "inline");
                mv.addObject("dump", "There was an error creating the dump.");
                return mv;
            }
        } catch (IOException | InterruptedException ex) {
            Logger.getLogger(ManagerController.class.getName()).log(Level.SEVERE, null, ex);
            resp.setHeader("Content-Disposition", "inline");
            mv.addObject("dump", "There was an error creating the dump.");
        }
        return mv;
    }
}
