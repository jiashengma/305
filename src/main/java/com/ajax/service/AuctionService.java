package com.ajax.service;

import com.ajax.model.Auction;
import com.ajax.model.Status;
import com.ajax.persistence.AuctionDAO;
import com.ajax.persistence.FlightReservationDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author majiasheng
 */
@Service
public class AuctionService {

    @Autowired
    AuctionDAO auctionDAO;

    /**
     * Handles users bid on the flight, records the bidding to auction history
     * (and reservations if bid succeeded)
     *
     * @param bidderId
     * @param bid
     * @param hiddenFare
     * @param airline
     * @param flightNo
     * @return SUCCESS on success, FAILURE on failure (lower bid than hidden
     * fare), or ERROR on error while bidding
     */
    public int handleBid(int bidderId, double bid, double hiddenFare, String airline, int flightNo) {
        // record this bid to bid history
        auctionDAO.saveAuction(new Auction(bidderId, bid, airline, flightNo));
        /*TODO: maybe add bid accepted to the auction table as well 
         since we have the straight answer? */

        if (bid >= hiddenFare) {
            // TODO: do reservation/book, should show the winner bid price 

            return Status.SUCCESS;
        }
        return Status.FAILURE;

    }
}
