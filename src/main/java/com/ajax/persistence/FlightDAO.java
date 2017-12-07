/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ajax.persistence;

import com.ajax.model.Airport;
import com.ajax.model.Constants;
import com.ajax.model.Flight;
import com.ajax.model.FlightClass;
import com.ajax.model.Leg;
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
                + " FROM includes I "
                + " GROUP BY AirlineID, FlightNo "
                + " ORDER BY NumOfResr DESC "
                + " LIMIT 5;";
        Connection conn = null;
        try {
            conn = MySQLConnection.connect();

            PreparedStatement stmt = conn.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                //TODO:
                Flight f = new Flight();
                f.setAirline(rs.getString(1));
                f.setFlightNo(rs.getInt(2));
                bestSellers.add(f);

                // bestSellers.add();
            }

            conn.commit();
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
    public List<Flight> getFlightSuggestion(int accNum) {
        String query = "SELECT DISTINCT L.AirlineID, L.FlightNo "
                + "FROM Includes I, Reservation R, Leg L, Airport A, "
                + "	(SELECT MAX(LegNo) AS LegNo FROM Includes GROUP BY ResrNo) M"
                + "	WHERE R.ResrNo = I.ResrNo\n"
                + "		AND L.LegNo = I.LegNo\n"
                + "		AND L.FlightNo = I.FlightNo\n"
                + "		AND L.AirlineID = I.AirlineId\n"
                + "		AND L.ArrAirportId = A.Id\n"
                + "		AND M.LegNo = I.LegNo\n"
                + "		AND AccountNo = ?\n"
                + "	GROUP BY AccountNo, City\n"
                + "	ORDER BY COUNT(City) DESC;";

        Connection conn = null;
        List<Flight> flights = new ArrayList<>();
        try {
            conn = MySQLConnection.connect();

            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, accNum);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                //TODO:
                Flight f = new Flight();
                f.setAirline(rs.getString(1));
                f.setFlightNo(rs.getInt(2));
                flights.add(f);
                // .add();
            }

            conn.commit();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }

        }

        return flights;

    }

    public List<Flight> getFlightSuggestion(int repid, int accNum) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
//    private Flight getFlight(Connection conn, String airlineID, int flightNum, int legNum) {
//		StringBuilder query = new StringBuilder();
//		try {
//			/* for this (airline, flight num) get fares
//				select F.FareType, F.Fare from Fare F where F.AirlineID="AA" and F.FlightNo=111;
//			*/
//			query.append("SELECT DISTINCT F.").append(Constants.FARE_TYPE_FIELD)
//					.append(", F.").append(Constants.FARE_FIELD)
//					.append(" FROM ").append(Constants.FARE_TABLE)
//					.append(" F WHERE F.").append(Constants.AIRLINEID_FIELD)
//					.append("=? and F.").append(Constants.FLIGHTNO_FIELD).append("=?;");
//			PreparedStatement stmt = conn.prepareStatement(query.toString());
//			
//			stmt.setString(1, airlineID);
//			stmt.setInt(2, flightNum);
//
//			ResultSet rs = stmt.executeQuery();
//			double fare = DEFAULT_MAX_COST;
//			double hiddenFare = DEFAULT_MAX_COST;
//			while (rs.next()) {
//				switch (rs.getString(1)) {
//					case Constants.HIDDEN_FARE_FIELD:
//						hiddenFare = rs.getInt(2);
//						break;
//					case Constants.REGULAR_FARE_FIELD:
//						fare = rs.getInt(2);
//						break;
//					default:
//						Logger.getLogger(FlightReservationDAO.class.getName()).log(Level.WARNING, "Weird Result" + rs.getString(2));
//				}
//			}
//
////			System.out.printf("%nfare:%.2f%nhiddenFare:%.2f%n%n", fare, hiddenFare);
//			query.setLength(0);
//			// select L.LegNo, AP1.Name, L.ArrTime, L.DepTime, AP2.Name from leg L, airport AP1, airport AP2
//			//  where L.AirlineID="AA" and L.FlightNo=111 and L.LegNo>=1 and AP1.Id=L.DepAirportId and AP2.Id=L.ArrAirportId;
//			query.append("SELECT L.").append(Constants.LEGNO)
//					.append(", L.").append(Constants.DEPATURE_AIRPORT_ID)
//					.append(", L.").append(Constants.ARRIVAL_TIME)
//					.append(", L.").append(Constants.DEPATURE_TIME)
//					.append(", L.").append(Constants.ARRIVAL_AIRPORT_ID)
//					.append(" FROM ").append(Constants.LEG_TABLE)
//					.append(" L WHERE L.").append(Constants.AIRLINEID_FIELD)
//					.append("=? and L.").append(Constants.FLIGHTNO_FIELD)
//					.append("=?").append(" and L.").append(Constants.LEGNO).append(">=?;");
//
////			System.out.printf(query.toString(), airlineIDs.get(i), flightNums.get(i), legNums.get(i));
////	 		System.out.println();
//			stmt = conn.prepareStatement(query.toString());
//			stmt.setString(1, airlineID);
//			stmt.setInt(2, flightNum);
//			stmt.setInt(3, legNum);
//			rs = stmt.executeQuery();
//
//			List<Leg> legs = new ArrayList<>();
//			while (rs.next())
//				legs.add(new Leg(rs.getInt(1), Airport.getAirportByID(rs.getString(2)),
//						rs.getTimestamp(3), rs.getTimestamp(4),
//						Airport.getAirportByID(rs.getString(5))));
//
//			// select F.NoOfSeats, MAX(R.SeatNo) from flight F, includes I, reservationpassenger R
//			// where I.ResrNo=R.ResrNo and I.AirlineId="JA" and I.FlightNo=111;
//			query.setLength(0);
//			query.append("SELECT F.").append(Constants.NUMSEATS_FIELD)
//					.append(", MAX(R.").append(Constants.SEATNO_FIELD)
//					.append(") FROM ").append(Constants.FLIGHT_TABLE).append(" F, ")
//					.append(Constants.INCLUDES_TABLE).append(" I, ")
//					.append(Constants.RESERVATION_PASSENGER_TABLE).append(" R WHERE I.")
//					.append(Constants.RESERVATION_NO_FIELD).append("=R.").append(Constants.RESERVATION_NO_FIELD)
//					.append(" and F.").append(Constants.AIRLINEID_FIELD).append("=? and F.")
//					.append(Constants.FLIGHTNO_FIELD).append("=?;");
//			System.out.println(query.toString());
//			stmt = conn.prepareStatement(query.toString());
//			stmt.setString(1, airlineID);
//			stmt.setInt(2, flightNum);
//
//			rs = stmt.executeQuery();
//			rs.next();
//			int maxSeat = rs.getInt(1);
//			int currSeat;
//			try {
//				currSeat = Integer.parseInt(rs.getString(2));
//			} catch (NumberFormatException n) {
//				String seat = rs.getString(2);
//				currSeat = Integer.parseInt(seat.substring(0, seat.length() -1));
//				currSeat *= 6;
//				currSeat += (seat.charAt(seat.length() -1) - 'A');
//			}
//			System.out.printf("%n (%s %s) ->max: %d, curr: %d ", airlineID, flightNum, maxSeat, currSeat);
//			return maxSeat <= ++currSeat ? null : new Flight(airlineID, flightNum, legs, prefClass, fare, hiddenFare, currSeat);
//		} catch (SQLException ex) {
//			Logger.getLogger(FlightReservationDAO.class.getName()).log(Level.SEVERE, "SQL parse error", ex);
//		}
//		return null;
//	}
}
