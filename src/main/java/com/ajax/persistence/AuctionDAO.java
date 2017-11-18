package com.ajax.persistence;

import com.ajax.model.Auction;
import org.springframework.stereotype.Repository;

/**
 *
 * @author majiasheng
 */
@Repository
public class AuctionDAO {
    
    public void saveAuction(Auction auction) {
        //TODO:
        String query = "INSERT INTO " 
                + Constants.AUCTIONS_TABLE;
        
    }
}
