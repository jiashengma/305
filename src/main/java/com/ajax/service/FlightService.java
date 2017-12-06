/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ajax.service;

import com.ajax.model.Flight;
import com.ajax.persistence.FlightDAO;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author majiasheng
 */
@Service
public class FlightService {
    @Autowired
    FlightDAO flightDAO;

    public List<Flight> getBestSeller() {
        return flightDAO.getBestSellers();
    }

    public Object getFlightSuggestion(int accNum) {
        return flightDAO.getFlightSuggestion(accNum);
    }
    
    
    
}
