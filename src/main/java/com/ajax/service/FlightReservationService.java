/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ajax.service;

import com.ajax.model.Flight;
import com.ajax.persistence.FlightReservationManager;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author majiasheng
 */
@Service
public class FlightReservationService {
    
    @Autowired
    FlightReservationManager flightReservationManager;
    
    public List<Flight> searchFlight(String src, String dst, Date dep, Date ret) {
        
        System.out.println("TODO: query database for results");
        
        //TODO: sanitize inputs/args ?
        
        flightReservationManager.searchFlight(src, dst, dep, ret);
        
        return null;
    }
    
    public boolean bookFlight(Flight flight) {
        return flightReservationManager.bookFlight(flight);
    }
    
}
