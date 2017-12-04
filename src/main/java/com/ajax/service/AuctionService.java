package com.ajax.service;

import com.ajax.model.Auction;
import java.util.List;

/**
 *
 * @author majiasheng
 */
public interface AuctionService {

    /**
     * Handles users bid on the flight, records the bidding to auction history
     * (and reservations if bid succeeded)
     *
     * @param auction
     * @param hiddenFare
     * @return SUCCESS on success, FAILURE on failure (lower bid than hidden
     * fare), or ERROR on error while bidding
     */
    public int reserveFlightFromAuction(Auction auction);
    
    public boolean saveAuction(Auction auction);

    public List<Auction> getAllAuctionHistory(int customerAccNo);

    public List<Auction> getAuctionHistoryByFlight(int customerAccNo, String airline, int flightNo, String flightClass);

}
