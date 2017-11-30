/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ajax.service;

import com.ajax.model.Airport;
import com.ajax.model.BookingType;
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

    /**
     *
     * @return number of available seats for a given flight operating on a
     * particular date
     */
    public int getAvailableSeats() {
        throw new UnsupportedOperationException("not supported");
    }

    public boolean bookFlight(Flight flight, BookingType type) {
        if (type == BookingType.AUCTION) {
            // reserve flight from auction
            return bookFlightFromAuction(flight);
        } else if (type == BookingType.DISCOUNT) {
            // reserve flight with discount
            return bookDiscountedFlight(flight);
        }
        return bookFlight(flight);
    }

    private boolean bookFlightFromAuction(Flight flight) {
        throw new UnsupportedOperationException();
    }

    private boolean bookDiscountedFlight(Flight flight) {
        throw new UnsupportedOperationException();
    }

    private boolean bookFlight(Flight flight) {
        throw new UnsupportedOperationException();
    }

    public List<Airport> getAirports() {
        return flightReservationManager.getAirports();
    }

}
