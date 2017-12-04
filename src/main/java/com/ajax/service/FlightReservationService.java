package com.ajax.service;

import com.ajax.model.Airport;
import com.ajax.model.Auction;
import com.ajax.model.BookingType;
import com.ajax.model.Flight;
import com.ajax.model.FlightSearchForm;
import com.ajax.persistence.FlightReservationDAO;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FlightReservationService {

    @Autowired
    FlightReservationDAO flightReservationManager;
    @Autowired
    FlightReservationDAO flightReservationDAO;

    public List<Flight> searchFlight(FlightSearchForm flightSearchForm) {
        //TODO: sanitize inputs/args ?
        return flightReservationManager.searchFlight(flightSearchForm);
    }

    /**
     *
     * @return number of available seats for a given flight operating on a
     * particular date
     */
    public int getAvailableSeats() {
        throw new UnsupportedOperationException("not supported");
    }

    private int bookFlight(Auction auction) {
        return flightReservationDAO.reserveFlightFromAuction(auction);
    }

    private int bookFlight(Flight flight) {
        throw new UnsupportedOperationException();
    }

    public List<Airport> getAirports() {
        return flightReservationManager.getAirports();
    }

}
