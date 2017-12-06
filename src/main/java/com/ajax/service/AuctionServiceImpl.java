package com.ajax.service;

import com.ajax.model.Auction;
import com.ajax.model.Status;
import com.ajax.persistence.AuctionDAO;
import com.ajax.persistence.FlightReservationDAO;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author majiasheng
 */
@Service
public class AuctionServiceImpl implements AuctionService {

    @Autowired
    AuctionDAO auctionDAO;
    @Autowired
    FlightReservationDAO flightReservationDAO;

    /**
     * Handles users bid on the flight, records the bidding to auction history
     * (and reservations if bid succeeded)
     *
     * @param auction
     * @param hiddenFare
     * @return AUCTION_SUCCESS on success, AUCTION_LOW_BID on failure (lower bid
     * than hidden fare), or AUCTION_ERROR on error while bidding
     */
    public boolean reserveFlightFromAuction(Auction auction) {
        return flightReservationDAO.reserveFlightFromAuction(auction);
    }

    @Override
    public boolean saveAuction(Auction auction) {
        return auctionDAO.saveAuction(auction);
    }

    @Override
    public List<Auction> getAllAuctionHistory(int customerAccNo) {
        return auctionDAO.getAllAuctionHistory(customerAccNo);
    }

    @Override
    public List<Auction> getAuctionHistoryByFlight(int customerAccNo, String airline, int flightNo, String flightClass) {
        return auctionDAO.getAuctionHistoryByFlight(customerAccNo, airline, flightNo, flightClass);
    }

}
