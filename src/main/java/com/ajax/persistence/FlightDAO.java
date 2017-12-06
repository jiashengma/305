/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ajax.persistence;

import com.ajax.model.Flight;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
public class FlightDAO {

    public final int BEST_SELLER_NUM = 2;

    public List<Flight> getBestSellers() {
        List<Flight> bestSellers = new ArrayList<>();
        String query = "SELECT AirlineID, FlightNo, COUNT(ResrNo) AS NumOfResr "
                + " FROM Includes I, "
                + " GROUP BY AirlineID, FlightNo "
                + " HAVING COUNT(ResrNo) > " + BEST_SELLER_NUM
                + " ORDER BY NumOfResr DESC";
        Connection conn = null;
        try {
            conn = MySQLConnection.connect();

            PreparedStatement stmt = conn.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                //TODO:

                // bestSellers.add();
            }

            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(FlightReservationDAO.class.getName()).log(Level.SEVERE, "SQL Error", ex);
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(FlightDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        }
        return bestSellers;

    }

    /**
     *
     * @param accNum customer's account number
     * @return
     */
    public Object getFlightSuggestion(int accNum) {
        String query = "SELECT DISTINCT R.AccountNo, L.AirlineID, L.FlightNo \n"
                + "FROM Includes I, Reservation R, Leg L, Airport A, \n"
                + "	(SELECT MAX(LegNo) AS LegNo FROM Includes GROUP BY ResrNo) M\n"
                + "	WHERE R.ResrNo = I.ResrNo\n"
                + "		AND L.LegNo = I.LegNo\n"
                + "		AND L.FlightNo = I.FlightNo\n"
                + "		AND L.AirlineID = I.AirlineId\n"
                + "		AND L.ArrAirportId = A.Id\n"
                + "		AND M.LegNo = I.LegNo\n"
                + "		AND AccountNo = ?\n"
                + "	GROUP BY AccountNo, City\n"
                + "	ORDER BY COUNT(City) DESC;";
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
