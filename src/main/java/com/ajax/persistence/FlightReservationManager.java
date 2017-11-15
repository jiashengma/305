/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ajax.persistence;

import com.ajax.model.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.stereotype.Repository;

/**
 * CRUD operations on flight reservation
 *
 * @author majiasheng
 */
@Repository
public class FlightReservationManager {

    public List<Flight> searchFlight(FlightSearchForm flightSearchForm) {

        String query = "SELECT * FROM person;"
                + "F." + Constants.AIRLINEID_FIELD + ", "
                + "F." + Constants.FLIGHTNO_FIELD + ", "
                // TODO: add leg info
                + " FROM " + Constants.FLIGHT_TABLE + " F, ";

        try {
            Connection conn = MySQLConnection.connect();
            PreparedStatement stmt = conn.prepareStatement(query);
	        stmt.executeQuery();

            //TODO: query data base for result
            //TODO: if user is not logged in when trying to book, pop up login
        } catch (SQLException ex) {
            Logger.getLogger(FlightReservationManager.class.getName()).log(Level.SEVERE, "SQL Error", ex);
        }

        return null;
    }

    public List<Airport> getAirports() {
	    try {
		    Connection conn = MySQLConnection.connect();
		    PreparedStatement stmt =
			    conn.prepareStatement("SELECT * FROM " + Constants.AIRPORT_TABLE + ";");
		    stmt.executeQuery();
		    conn.close();
	    } catch (SQLException ex) {
		    Logger.getLogger(FlightReservationManager.class.getName()).log(Level.SEVERE, "SQL Error", ex);
	    }

    	return null;
    }

    public boolean bookFlight(Flight flight) {

        //TODO: do book flight
        return false;
    }

}
