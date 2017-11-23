package com.ajax.persistence;

import com.ajax.model.Auction;
import com.ajax.model.FlightClass;
import com.ajax.service.ReturnValue;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.stereotype.Repository;

/**
 *
 * @author majiasheng
 */
@Repository
public class AuctionDAOImpl implements AuctionDAO {

    @Override
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
                    + ") VALUES (?,?,?,?,?)";
            PreparedStatement stmt = conn.prepareStatement(query);

            stmt.setInt(1, auction.getPersonAccNo());
            stmt.setString(2, auction.getAirline());
            stmt.setInt(3, auction.getFlightNo());
            stmt.setString(4, auction.getFlightClass().name());
            stmt.setDouble(5, auction.getNYOP());

            ret = stmt.executeUpdate();
            conn.commit();

        } catch (SQLException ex) {
            System.err.println("Error occurred while trying to insert record into auctions table.");
        } finally {
            try {
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(AuctionDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return ret;

    }

    @Override
    public List<Auction> getAllAuctionHistory(int customerAccNo) {
        List<Auction> auctions = null;

        //TODO: get auction history
        // List<Auction> auctions = null;
        Connection conn = MySQLConnection.connect();
        try {
            String query = "SELECT * "
                    + "FROM " + Constants.AUCTIONS_TABLE
                    + " WHERE " + Constants.ACCOUNTNO_FIELD + " = ? ";

            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, customerAccNo);

            ResultSet rs = stmt.executeQuery();
            conn.commit();

            auctions = new ArrayList<>();
            while (rs.next()) {
                String airlineId = rs.getString(Constants.AIRLINEID_FIELD);
                int flightNo = rs.getInt(Constants.FLIGHTNO_FIELD);
                String flightClass = rs.getString(Constants.CLASS_FIELD);
                //TODO: Date date
                Timestamp timestamp = rs.getTimestamp(Constants.DATE_FIELD);
                double NYOP = rs.getDouble(Constants.NYOP_FIELD);

                Auction auction = new Auction(customerAccNo, NYOP, airlineId, flightNo, flightClass, timestamp);
                auctions.add(auction);

            }

        } catch (SQLException ex) {
            Logger.getLogger(AuctionDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(AuctionDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return auctions;
    }

    public List<Auction> getAuctionHistoryByFlight(int customerAccNo, String airline, int flightNo) {
        List<Auction> auctions = null;

        Connection conn = MySQLConnection.connect();
        try {
            String query = "SELECT A.NYOP,"
                    + "IF(A.NYOP >= F.Fare, 'Yes', 'No') AS Accepted?,"
                    + " A.Date"
                    + "FROM " + Constants.AUCTIONS_TABLE + " A, " + Constants.FARE_TABLE + " F "
                    + "WHERE A." + Constants.ACCOUNTNO_FIELD + " = ? "
                    + "AND F." + Constants.FARE_TYPE_FIELD + " = 'Hidden'"
                    + "AND A." + Constants.AIRLINEID_FIELD + " = ? "
                    + "AND A." + Constants.FLIGHTNO_FIELD + " = ? "
                    + "AND A." + Constants.CLASS_FIELD + " = ? "
                    + "AND F." + Constants.AIRLINEID_FIELD + " = A." + Constants.AIRLINEID_FIELD + "  "
                    + "AND F." + Constants.FLIGHTNO_FIELD + " = A." + Constants.FLIGHTNO_FIELD + " "
                    + "AND F." + Constants.CLASS_FIELD + " = A." + Constants.CLASS_FIELD + ";"
                    + "";
            PreparedStatement stmt = conn.prepareStatement(query);

            //TODO: set params
            stmt.setInt(1, customerAccNo);
            stmt.setString(2, airline);
            stmt.setInt(3, flightNo);
            //TODO: need to add flight class to auction ? or retrieve it from auction
            // stmt.setString(4, flightClass);

            ResultSet rs = stmt.executeQuery();
            conn.commit();

            auctions = new ArrayList<>();
            //TODO: get auction history given airline+airline

        } catch (SQLException ex) {
            Logger.getLogger(AuctionDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(AuctionDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return auctions;
    }

}
