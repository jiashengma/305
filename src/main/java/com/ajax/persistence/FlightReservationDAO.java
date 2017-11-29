package com.ajax.persistence;

import com.ajax.model.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.stereotype.Repository;

@Repository
public class FlightReservationDAO {
    public List<Flight> searchFlight(FlightSearchForm flightSearchForm) {
	    Logger.getLogger(FlightReservationDAO.class.getName()).log(Level.FINE, flightSearchForm.toString());
	    List<Flight> flights = new ArrayList<>();
        Connection conn = MySQLConnection.connect();
        try {
        	if (flightSearchForm.getFlyingFrom() == null && flightSearchForm.getFlyingTo() == null) {
		        Logger.getLogger(FlightReservationDAO.class.getName()).log(Level.FINE, "Requires one+ search entry");
		        return null;
	        }

	        boolean hasFlightFrom = flightSearchForm.getFlyingFrom() != null;
	        boolean hasFlightTo = flightSearchForm.getFlyingTo() != null;

	        /*  Example query:
	            select L1.AirlineID, L1.FlightNo, L1.LegNo from leg L1, leg L2, airport AP1, airport AP2 where AP1.Name="LaGuardia"
			//	        and AP1.Id=L1.DepAirportId and AP2.Name="Los Angeles International" and AP2.Id=L2.ArrAirportId;
	            -> obtains AirlineID, FlightNo of all possible routes
	        */
	        StringBuilder query = new StringBuilder("SELECT L1.").append(Constants.AIRLINEID_FIELD)
			        .append(", L1.").append(Constants.FLIGHTNO_FIELD).append(", L1.").append(Constants.LEGNO)
			        .append(" FROM ").append(Constants.LEG_TABLE).append(" L1, ");

	        if (hasFlightFrom && hasFlightTo)
		        query.append(Constants.LEG_TABLE).append(" L2, ");
	        query.append(Constants.AIRPORT_TABLE).append(" AP1");
	        if (hasFlightFrom && hasFlightTo)
		        query.append(", ").append(Constants.AIRPORT_TABLE).append(" AP2 ");
	        query.append("WHERE AP1.").append(Constants.NAME_FIELD).append("=\"%s\" and AP1.")
			        .append(Constants.ID_FIELD).append("=L1.");
	        if (hasFlightFrom ^ hasFlightTo)
	        	query.append(hasFlightFrom ? Constants.DEPATURE_AIRPORT_ID : Constants.ARRIVAL_AIRPORT_ID);
	        else
	        	query.append(Constants.DEPATURE_AIRPORT_ID).append(" and AP2.").append(Constants.NAME_FIELD)
				        .append("=\"%s\" and AP2.").append(Constants.ID_FIELD).append("=L2.")
				        .append(Constants.ARRIVAL_AIRPORT_ID).append(" and L1.");

	        PreparedStatement stmt = conn.prepareStatement(hasFlightFrom ^ hasFlightTo ?
			        String.format(query.append(";").toString(),
					        hasFlightFrom ? flightSearchForm.getFlyingFrom() : flightSearchForm.getFlyingTo()) :
			        String.format(query.append(";").toString(),
					        flightSearchForm.getFlyingFrom(), flightSearchForm.getFlyingFrom()));
	        ResultSet rs = stmt.executeQuery();

	        ArrayList<String> airlineIDs = new ArrayList<>();
	        ArrayList<Integer> flightNums = new ArrayList<>();
	        ArrayList<Integer> legNums = new ArrayList<>();
	        while (rs.next()) {   // obtaining L1.AirlineID, L1.FlightNo, L1.LegNo
				airlineIDs.add(rs.getString(1));
				flightNums.add(rs.getInt(2));
				legNums.add(rs.getInt(3));
	        }   // hard coded numbers because I chose order in select statement above

	        for (int i = 0; i < airlineIDs.size(); i++) {
	        	query.setLength(0);
	        	// for this (airline, flight num) get fares
	        	// select F.FareType, F.Fare from Fare F where F.AirlineID="AA" and F.FlightNo=111;
		        query.append("SELECT F.").append(Constants.FARE_TYPE_FIELD)
				        .append(", F.").append(Constants.FARE_FIELD)
		                .append(" FROM ").append(Constants.FARE_TABLE)
				        .append(" WHERE F.").append(Constants.AIRLINEID_FIELD)
				        .append("=\"%s\" and F.").append(Constants.FLIGHTNO_FIELD).append("=%d;");
		        stmt = conn.prepareStatement(String.format(query.toString(), airlineIDs.get(i), flightNums.get(i)));
		        rs = stmt.executeQuery();

		        double fare = -1;
		        double hiddenFare = -1;
				while (rs.next())
					switch (rs.getString(1)) {
						case Constants.HIDDEN_FARE_FIELD:
							hiddenFare = rs.getInt(2);
							break;
						case Constants.REGULAR_FARE_FIELD:
							fare = rs.getInt(2);
							break;
						default:
							Logger.getLogger(FlightReservationDAO.class.getName()).log(Level.WARNING, "Weird Result" + rs.getString(2));
					}

		        query.setLength(0);
				// select L.LegNo, L.DepAirportId, L.ArrTime, L.DepTime, L.ArrAirportId from leg L where L.AirlineID="AA" and L.FlightNo=111 and L.LegNo>=1;
				query.append("SELECT L.").append(Constants.LEGNO)
						.append(", L.").append(Constants.DEPATURE_AIRPORT_ID)
						.append(", L.").append(Constants.ARRIVAL_TIME)
						.append(", L.").append(Constants.DEPATURE_TIME)
						.append(", L.").append(Constants.ARRIVAL_AIRPORT_ID)
						.append(" FROM ").append(Constants.LEG_TABLE)
						.append(" L WHERE L.").append(Constants.AIRLINEID_FIELD)
						.append("=\"%s\" and L.").append(Constants.FLIGHTNO_FIELD)
						.append("=%d").append(" and L.").append(Constants.LEGNO).append(">=%d;");

		        stmt = conn.prepareStatement(String.format(query.toString(), airlineIDs.get(i), flightNums.get(i), legNums.get(i)));
		        rs = stmt.executeQuery();
	        }

            conn.commit();
        } catch (SQLException ex) {
            Logger.getLogger(FlightReservationDAO.class.getName()).log(Level.SEVERE, "SQL query Error", ex);
        } finally {
            try {
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(FlightReservationDAO.class.getName()).log(Level.SEVERE, "SQL closing error", ex);
            }
        }
        return flights;
    }

    public List<Airport> getAirports() {
    	List<Airport> airports = new ArrayList<>();
	    try {
		    Connection conn = MySQLConnection.connect();
		    PreparedStatement stmt =
			    conn.prepareStatement("SELECT * FROM " + Constants.AIRPORT_TABLE + " ORDER BY "
					    + Constants.AIRPORT_NAME_FIELD + " ASC;");
		    ResultSet rs = stmt.executeQuery();
		    while(rs.next())
		    	airports.add(new Airport(rs.getString(Constants.AIRPORT_ID), rs.getString(Constants.AIRPORT_NAME),
					    rs.getString(Constants.AIRPORT_CITY), rs.getString(Constants.AIRPORT_COUNTRY)));

//		    airports.forEach(System.out::println);
		    conn.close();
	    } catch (SQLException ex) {
		    Logger.getLogger(FlightReservationDAO.class.getName()).log(Level.SEVERE, "SQL Error", ex);
	    }
    	return airports;
    }

    public boolean reserveFlight(Flight flight) {

        //TODO: do reserve flight
        return false;
    }

    /**
     * Reserves a flight from a successfully auction
     * @param auction 
     */
    public void reserveFlightFromAuction(Auction auction) {
        /*TODO: use the info in the auction to do reservation
         this method may call the reserveFlight() method above */
        
        throw new UnsupportedOperationException("Not supported yet.");
    }

}