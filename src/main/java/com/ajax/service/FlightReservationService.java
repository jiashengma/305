/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ajax.service;

import com.ajax.model.Airport;
import com.ajax.model.Flight;
import com.ajax.model.FlightSearchForm;
import com.ajax.model.Status;
import com.ajax.persistence.FlightReservationDAO;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FlightReservationService {

    @Autowired
    FlightReservationDAO flightReservationManager;

    public List<Flight> searchFlight(FlightSearchForm flightSearchForm) {
        System.out.println("TODO: query database for results");

        //TODO: sanitize inputs/args ?
        flightReservationManager.searchFlight(flightSearchForm);
        return null;
    }

    public boolean bookFlight(Flight flight) {
        return flightReservationManager.bookFlight(flight);
    }

    public List<Airport> getAirports() {
        return flightReservationManager.getAirports();
    }

}
