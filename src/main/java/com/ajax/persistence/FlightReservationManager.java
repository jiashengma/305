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
public class FlightReservationManager {
    public List<Flight> searchFlight(FlightSearchForm flightSearchForm) {
        try {
            Connection conn = MySQLConnection.connect();
	        // TODO: add leg info
	        PreparedStatement stmt =
		        conn.prepareStatement("SELECT F." + Constants.AIRLINEID_FIELD +
			        ", F." + Constants.FLIGHTNO_FIELD + " FROM " +
			        Constants.FLIGHT_TABLE +" F;");
	        ResultSet rs = stmt.executeQuery();

            //TODO: query data base for result
            //TODO: if user is not logged in when trying to book, pop up login
        } catch (SQLException ex) {
            Logger.getLogger(FlightReservationManager.class.getName()).log(Level.SEVERE, "SQL Error", ex);
        }

        return null;
    }

    public List<Airport> getAirports() {
    	List<Airport> airports = new ArrayList<>();
	    try {
		    Connection conn = MySQLConnection.connect();
		    PreparedStatement stmt =
			    conn.prepareStatement("SELECT * FROM " + Constants.AIRPORT_TABLE + ";");
		    ResultSet rs = stmt.executeQuery();
		    while(rs.next())
		    	airports.add(new Airport(rs.getString(Constants.AIRPORT_ID), rs.getString(Constants.AIRPORT_NAME),
					    rs.getString(Constants.AIRPORT_CITY), rs.getString(Constants.AIRPORT_COUNTRY)));

//		    airports.forEach(System.out::println);
		    conn.close();
	    } catch (SQLException ex) {
		    Logger.getLogger(FlightReservationManager.class.getName()).log(Level.SEVERE, "SQL Error", ex);
	    }
    	return airports;
    }

    public boolean bookFlight(Flight flight) {

        //TODO: do book flight
        return false;
    }

    public void saveAuction(Auction auction) {
        //TODO:
    }

}
