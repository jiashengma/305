package com.ajax.persistence;

import com.ajax.model.Auction;
import com.ajax.service.ReturnValue;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.stereotype.Repository;

/**
 *
 * @author majiasheng
 */
@Repository
public class AuctionDAO {

    public int saveAuction(Auction auction) {
        //TODO:add class field to auction and flight
        int ret = ReturnValue.ERROR;
        Connection conn = MySQLConnection.connect();
        try {
            String query = "INSERT INTO "
                + Constants.AUCTIONS_TABLE
                + " ("
                + Constants.ACCOUNTNO_FIELD + ", "
                + Constants.AIRLINEID_FIELD + ", "
                + Constants.FLIGHTNO_FIELD + ", "
                + Constants.CLASS_FIELD + ", "
                + Constants.NYOP_FIELD
                + ") VALUES (?,?,?,?)";
            PreparedStatement stmt = conn.prepareStatement(query);

            stmt.setInt(1, auction.getPersonAccNo());
            stmt.setString(2, auction.getAirline());
            stmt.setInt(3, auction.getFlightNo());
            //TODO:
            //stmt.setString(4, auction.getFlightClass);
            stmt.setDouble(5, auction.getNYOP());
            
            ret = stmt.executeUpdate();
            conn.commit();
        } catch (SQLException ex) {
            System.err.println("Error occurred while trying to insert record into auctions table.");
        } finally {
            try {
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(AuctionDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return ret;

    }
}
