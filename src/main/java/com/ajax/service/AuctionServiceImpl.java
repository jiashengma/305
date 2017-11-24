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
     * @param bidderAccNo
     * @param bid
     * @param hiddenFare
     * @param airline
     * @param flightNo
     * @return SUCCESS on success, FAILURE on failure (lower bid than hidden
     * fare), or ERROR on error while bidding
     */
    public int handleBid(int bidderAccNo, double bid, double hiddenFare, String airline, int flightNo) {
        // record this bid to bid history
//        Auction auction = new Auction(bidderAccNo, bid, airline, flightNo);
//        auctionDAO.saveAuction(auction);
//        /*TODO: maybe add bid accepted to the auction table as well 
//         since we have the straight answer? */
//
//        if (bid >= hiddenFare) {
//            // TODO: do reservation/book, should use the winner bid price 
//            flightReservationDAO.reserveFlightFromAuction(auction);
//            return Status.SUCCESS;
//        }
        return Status.FAILURE;

    }
    
    @Override
    public int saveAuction(Auction auction) {
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
