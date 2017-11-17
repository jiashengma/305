/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ajax.service;

import com.ajax.model.Flight;
import com.ajax.model.FlightSearchForm;
import com.ajax.model.Status;
import com.ajax.persistence.FlightReservationManager;
import java.util.Date;
import java.util.List;
import java.util.Map;
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
    
    public List<Flight> searchFlight(FlightSearchForm flightSearchForm) {
        
        System.out.println("TODO: query database for results");
        
        //TODO: sanitize inputs/args ?
        
        flightReservationManager.searchFlight(flightSearchForm);
        
        return null;
    }
    
    public boolean bookFlight(Flight flight) {
        return flightReservationManager.bookFlight(flight);
    }

    /**
     * Handles users bid on the flight, records the bidding to auction history 
     * (and reservations if bid succeeded)
     * @param bidderId
     * @param bid
     * @param hiddenFare
     * @return SUCCESS on success, 
     *      FAILURE on failure (lower bid than hidden fare), or
     *      ERROR on error while bidding
     */
    public int handleBid(int bidderId, double bid, double hiddenFare) {
        //TODO: record this bid to bid history
        
        if (bid >= hiddenFare) {
            // TODO: do reservation/book, should have a unique 
            
            return Status.SUCCESS;
        }
        return Status.FAILURE;
        
    }
    
}
