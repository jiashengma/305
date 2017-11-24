package com.ajax.persistence;

import com.ajax.model.Auction;
import java.util.List;

/**
 *
 * @author majiasheng
 */
public interface AuctionDAO {

    public int saveAuction(Auction auction);

    public List<Auction> getAllAuctionHistory(int customerAccNo);

    public List<Auction> getAuctionHistoryByFlight(int customerAccNo, String airline, int flightNo, String flightClass);

}
