package com.ajax.persistence;

import com.ajax.model.Constants;
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
        List<Flight> flights = new ArrayList<>();
        Connection conn = MySQLConnection.connect();
        try {
            // TODO: add leg info
            PreparedStatement stmt
                    = conn.prepareStatement("SELECT F." + Constants.AIRLINEID_FIELD
                            + ", F." + Constants.FLIGHTNO_FIELD + " FROM "
                            + Constants.FLIGHT_TABLE + " F;");
            ResultSet rs = stmt.executeQuery();

            // am I potentially skipping one?
            while (rs.next()) {
                flights.add(new Flight());
            }

            conn.commit();
            //TODO: query data base for result
            //TODO: do not show flights that are full in the search result (Andrew)

        } catch (SQLException ex) {
            Logger.getLogger(FlightReservationDAO.class.getName()).log(Level.SEVERE, "SQL Error", ex);
        } finally {
            try {
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(FlightReservationDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return flights;
    }

    public List<Airport> getAirports() {
        List<Airport> airports = new ArrayList<>();
        try {
            Connection conn = MySQLConnection.connect();
            String query = "SELECT * FROM "
                    + Constants.AIRPORT_TABLE
                    + " ORDER BY "
                    + Constants.NAME_FIELD + " ASC;";

            PreparedStatement stmt = conn.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                airports.add(
                        new Airport(
                                rs.getString(Constants.AIRPORT_ID),
                                rs.getString(Constants.AIRPORT_NAME),
                                rs.getString(Constants.AIRPORT_CITY),
                                rs.getString(Constants.AIRPORT_COUNTRY)
                        )
                );
            }

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
     *
     * @param auction
     */
    public int reserveFlightFromAuction(Auction auction) {
        /*TODO: use the info in the auction to do reservation
         this method may call the reserveFlight() method above */

        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
