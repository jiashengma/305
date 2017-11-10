/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ajax.persistence;

import com.ajax.model.Flight;
import java.sql.Connection;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * CRUD operations on flight reservation
 * @author majiasheng
 */
@Repository
public class FlightReservationManager {
    
    
            
    public List<Flight> searchFlight(String src, String dst, Date dep, Date ret) {
        Connection conn = MySQLConnection.connect();
        
        //TODO: query data base for result
        
        return null;
    }
    
}
