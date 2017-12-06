package com.ajax.persistence;

import com.ajax.model.Constants;
import com.ajax.model.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.stereotype.Repository;

@Repository
public class FlightReservationDAO {
	private static final double SYSTEM_FEE = 1.2;
	private static final int DEFAULT_MAX_COST = Integer.MAX_VALUE;

	public List<Flight> searchFlight(FlightSearchForm flightSearchForm) {
		List<Flight> flights = new ArrayList<>();
		Connection conn = MySQLConnection.connect();
		try {
			boolean hasFlightFrom = !flightSearchForm.getFlyingFrom().equals("");
			boolean hasFlightTo = !flightSearchForm.getFlyingTo().equals("");

			if (!hasFlightFrom && !hasFlightTo) {
				Logger.getLogger(FlightReservationDAO.class.getName()).log(Level.FINE, "Requires one+ search entry");
				return null;
			}

			/*  Example query:
			 select L1.AirlineID, L1.FlightNo, L1.LegNo from leg L1, leg L2, airport AP1, airport AP2 where AP1.Name="LaGuardia"
			 //	        and AP1.Id=L1.DepAirportId and AP2.Name="Los Angeles International" and AP2.Id=L2.ArrAirportId;
			 -> obtains AirlineID, FlightNo of all possible routes
			 */
			StringBuilder query = new StringBuilder("SELECT L1.")
					.append(Constants.AIRLINEID_FIELD)
					.append(", L1.").append(Constants.FLIGHTNO_FIELD)
					.append(", L1.").append(Constants.LEGNO)
					.append(" FROM ").append(Constants.LEG_TABLE).append(" L1, ");

			if (hasFlightFrom && hasFlightTo)
				query.append(Constants.LEG_TABLE).append(" L2, ");
			query.append(Constants.AIRPORT_TABLE).append(" AP1");
			if (hasFlightFrom && hasFlightTo)
				query.append(", ").append(Constants.AIRPORT_TABLE).append(" AP2 ");
			query.append(" WHERE AP1.").append(Constants.NAME_FIELD).append("=\"%s\" and AP1.")
					.append(Constants.ID_FIELD).append("=L1.");
			if (hasFlightFrom ^ hasFlightTo) {
				query.append(hasFlightFrom ? Constants.DEPATURE_AIRPORT_ID : Constants.ARRIVAL_AIRPORT_ID);
			} else {
				query.append(Constants.DEPATURE_AIRPORT_ID)
						.append(" and AP2.").append(Constants.NAME_FIELD)
						.append("=\"%s\" and AP2.").append(Constants.ID_FIELD).append("=L2.")
						.append(Constants.ARRIVAL_AIRPORT_ID);
			}
			query.append(";");
//	        System.out.printf(query.toString(), flightSearchForm.getFlyingFrom(), flightSearchForm.getFlyingTo());
//	        System.out.println();

			PreparedStatement stmt = conn.prepareStatement(hasFlightFrom ^ hasFlightTo
					? String.format(query.toString(),
							hasFlightFrom ? flightSearchForm.getFlyingFrom() : flightSearchForm.getFlyingTo())
					: String.format(query.toString(),
							flightSearchForm.getFlyingFrom(), flightSearchForm.getFlyingTo()));
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {	// hard coded numbers because I chose order in SELECT query above
				flights.add(getFlight(conn, rs.getString(1), rs.getInt(2), rs.getInt(3),
						flightSearchForm.getPrefClass()));
			}

			flights.forEach(System.out::println);
			conn.commit();
		} catch (SQLException ex) {
			Logger.getLogger(FlightReservationDAO.class.getName()).log(Level.SEVERE, "SQL query Error", ex);
		} finally {
			try {
				conn.close();
			} catch (SQLException ex) {
				Logger.getLogger(FlightReservationDAO.class.getName()).log(Level.SEVERE, "SQL closing error", ex);
			}
		}
		return flights;
	}

	// obtaining L1.AirlineID, L1.FlightNo, L1.LegNo
	private Flight getFlight(Connection conn, String airlineID, int flightNum, int legNum, String prefClass) {
		StringBuilder query = new StringBuilder();
		try {
			// for this (airline, flight num) get fares
			// select F.FareType, F.Fare from Fare F where F.AirlineID="AA" and F.FlightNo=111;
			query.append("SELECT F.").append(Constants.FARE_TYPE_FIELD)
					.append(", F.").append(Constants.FARE_FIELD)
					.append(" FROM ").append(Constants.FARE_TABLE)
					.append(" F WHERE F.").append(Constants.CLASS_FIELD)
					.append("=? and F.").append(Constants.AIRLINEID_FIELD)
					.append("=? and F.").append(Constants.FLIGHTNO_FIELD).append("=?;");
			PreparedStatement stmt = conn.prepareStatement(query.toString());
			stmt.setString(1, prefClass);
			stmt.setString(2, airlineID);
			stmt.setInt(3, flightNum);

			ResultSet rs = stmt.executeQuery();
			double fare = DEFAULT_MAX_COST;
			double hiddenFare = DEFAULT_MAX_COST;
			while (rs.next()) {
				switch (rs.getString(1)) {
					case Constants.HIDDEN_FARE_FIELD:
						hiddenFare = rs.getInt(2);
						break;
					case Constants.REGULAR_FARE_FIELD:
						fare = rs.getInt(2);
						break;
					default:
						Logger.getLogger(FlightReservationDAO.class.getName()).log(Level.WARNING, "Weird Result" + rs.getString(2));
				}
			}

//			System.out.printf("%nfare:%.2f%nhiddenFare:%.2f%n%n", fare, hiddenFare);
			query.setLength(0);
			// select L.LegNo, AP1.Name, L.ArrTime, L.DepTime, AP2.Name from leg L, airport AP1, airport AP2
			//  where L.AirlineID="AA" and L.FlightNo=111 and L.LegNo>=1 and AP1.Id=L.DepAirportId and AP2.Id=L.ArrAirportId;
			query.append("SELECT L.").append(Constants.LEGNO)
					.append(", L.").append(Constants.DEPATURE_AIRPORT_ID)
					.append(", L.").append(Constants.ARRIVAL_TIME)
					.append(", L.").append(Constants.DEPATURE_TIME)
					.append(", L.").append(Constants.ARRIVAL_AIRPORT_ID)
					.append(" FROM ").append(Constants.LEG_TABLE)
					.append(" L WHERE L.").append(Constants.AIRLINEID_FIELD)
					.append("=? and L.").append(Constants.FLIGHTNO_FIELD)
					.append("=?").append(" and L.").append(Constants.LEGNO).append(">=?;");

//			System.out.printf(query.toString(), airlineIDs.get(i), flightNums.get(i), legNums.get(i));
//	 		System.out.println();
			stmt = conn.prepareStatement(query.toString());
			stmt.setString(1, airlineID);
			stmt.setInt(2, flightNum);
			stmt.setInt(3, legNum);
			rs = stmt.executeQuery();

			List<Leg> legs = new ArrayList<>();
			while (rs.next())
				legs.add(new Leg(rs.getInt(1), Airport.getAirportByID(rs.getString(2)),
						rs.getTimestamp(3), rs.getTimestamp(4),
						Airport.getAirportByID(rs.getString(5))));

			// select F.NoOfSeats, MAX(R.SeatNo) from flight F, includes I, reservationpassenger R
			// where I.ResrNo=R.ResrNo and I.AirlineId="JA" and I.FlightNo=111;
			query.setLength(0);
			query.append("SELECT F.").append(Constants.NUMSEATS_FIELD)
					.append(", MAX(R.").append(Constants.SEATNO_FIELD)
					.append(") FROM ").append(Constants.FLIGHT_TABLE).append(" F, ")
					.append(Constants.INCLUDES_TABLE).append(" I, ")
					.append(Constants.RESERVATION_PASSENGER_TABLE).append(" R WHERE I.")
					.append(Constants.RESERVATION_NO_FIELD).append("=R.").append(Constants.RESERVATION_NO_FIELD)
					.append(" and F.").append(Constants.AIRLINEID_FIELD).append("=? and F.")
					.append(Constants.FLIGHTNO_FIELD).append("=?;");
			System.out.println(query.toString());
			stmt = conn.prepareStatement(query.toString());
			stmt.setString(1, airlineID);
			stmt.setInt(2, flightNum);

			rs = stmt.executeQuery();
			rs.next();
			int maxSeat = rs.getInt(1);
			int currSeat = Integer.parseInt(rs.getString(2));
			System.out.printf("%n (%s %s) ->max: %d, curr: %d ", airlineID, flightNum, maxSeat, currSeat);
			return maxSeat <= ++currSeat ? null : new Flight(airlineID, flightNum, legs, prefClass, fare, hiddenFare, currSeat);
		} catch (SQLException ex) {
			Logger.getLogger(FlightReservationDAO.class.getName()).log(Level.SEVERE, "SQL parse error", ex);
		}
		return null;
	}

	public List<Airport> getAirports() {
		List<Airport> airports = new ArrayList<>();
		try {
			Connection conn = MySQLConnection.connect();
			String query = "SELECT * FROM "
					+ Constants.AIRPORT_TABLE
					+ " ORDER BY "
					+ Constants.NAME_FIELD + " ASC;";

			PreparedStatement stmt = conn.prepareStatement(query);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				airports.add(new Airport(
						rs.getString(Constants.AIRPORT_ID),
						rs.getString(Constants.AIRPORT_NAME),
						rs.getString(Constants.AIRPORT_CITY),
						rs.getString(Constants.AIRPORT_COUNTRY)));
			}

			conn.close();
		} catch (SQLException ex) {
			Logger.getLogger(FlightReservationDAO.class.getName()).log(Level.SEVERE, "SQL Error", ex);
		}
		return airports;
	}

	public boolean reserveFlight(Customer customer, String repSSN, Flight flight) {
		boolean processedRequest = false;
		Connection conn = MySQLConnection.connect();
		try {
			// INSERT INTO passenger VALUES (4, 4);
			StringBuilder query = new StringBuilder("INSERT INTO ");
			query.append(Constants.PASSENGER_TABLE).append(" VALUES (?, ?);");

			PreparedStatement stmt = conn.prepareStatement(query.toString());
			stmt.setInt(1, customer.getId());
			stmt.setInt(2, customer.getAccNum());
			stmt.executeUpdate();

			// INSERT INTO reservation (BookingFee, TotalFare, RepSSN, AccountNo) VALUES (100, 120, 0, 4);
			query.setLength(0);
			query.append("INSERT INTO ").append(Constants.RESERVATION_TABLE).append(" (")
					.append(Constants.BOOKING_FEE_FIELD).append(", ")
					.append(Constants.TOTAL_FARE_FIELD).append(", ")
					.append(Constants.REP_SSN_FIELD).append(", ")
					.append(Constants.ACCOUNTNO_FIELD).append(") ")
					.append("VALUES (?, ?, ?, ?);");

			stmt = conn.prepareStatement(query.toString());
			stmt.setDouble(1, flight.getFare() * SYSTEM_FEE);
			stmt.setDouble(2, flight.getFare());
			stmt.setString(3, repSSN);
			stmt.setInt(4, customer.getAccNum());

			stmt.executeUpdate();

			/* INSERT INTO reservationpassenger
				VALUES (
					(SELECT ResrNo FROM reservation WHERE AccountNo=5 LIMIT 1),
					(SELECT Id FROM passenger WHERE AccountNo=5 LIMIT 1),
					5,
					"14",
					"Economic",
					"Spaghetti Carbonara with Pancetta and Mushrooms"
				);
			*/
			query.setLength(0);
			query.append("INSERT INTO ").append(Constants.RESERVATION_PASSENGER_TABLE)
					.append(" VALUES (")
					.append("(SELECT ").append(Constants.RESERVATION_NO_FIELD)
					.append(" FROM ").append(Constants.RESERVATION_TABLE)
					.append(" WHERE ").append(Constants.ACCOUNTNO_FIELD).append("=? LIMIT 1), ")
					.append("(SELECT ").append(Constants.ID_FIELD)
					.append(" FROM ").append(Constants.PASSENGER_TABLE)
					.append(" WHERE ").append(Constants.ACCOUNTNO_FIELD).append("=? LIMIT 1), ?, ?, ?, ?);");
//			System.out.println(query.toString() + ">> " + customer.getPrefMeal());
			stmt = conn.prepareStatement(query.toString());
			stmt.setInt(1, customer.getAccNum());
			stmt.setInt(2, customer.getAccNum());
			stmt.setInt(3, customer.getAccNum());
			stmt.setString(4, flight.getSeatNum());
			stmt.setString(5, flight.getFlightClass().name());
			stmt.setString(6, customer.getPrefMeal());
			stmt.executeUpdate();

			/* INSERT INTO includes
				VALUES (
					(SELECT ResrNo FROM reservation WHERE AccountNo=5 LIMIT 1),
					"JA",
					111,
					1,
					CURRENT_TIMESTAMP);*/
			query.setLength(0);
			query.append("INSERT INTO ").append(Constants.INCLUDES_TABLE)
					.append(" VALUES ((SELECT ")
					.append(Constants.RESERVATION_NO_FIELD).append(" FROM ")
					.append(Constants.RESERVATION_TABLE).append(" WHERE ")
					.append(Constants.ACCOUNTNO_FIELD).append("=? LIMIT 1), ?, ?, ?, CURRENT_TIMESTAMP);");
			stmt = conn.prepareStatement(query.toString());
			stmt.setInt(1, customer.getAccNum());
			stmt.setString(2, flight.getAirline());
			stmt.setInt(3, flight.getFlightNo());
			stmt.setInt(4, flight.getLegs().get(0).getNumber());

//			System.out.println(query.toString());
			stmt.executeUpdate();
			conn.commit();
			conn.close();

			processedRequest = true;
		} catch (SQLException ex) {
			Logger.getLogger(FlightReservationDAO.class.getName()).log(Level.SEVERE, "SQL query Error", ex);
		}

		return processedRequest;
	}

	/**
	 * Reserves a flight from a successful auction
	 *
	 * @param auction
	 */
	public boolean reserveFlightFromAuction(Auction auction) {
		// TODO: use the info in the auction to do reservation, may call the reserveFlight() method above
		throw new UnsupportedOperationException("Not supported yet.");
	}

    public boolean cancelFlight(Customer customer, Flight flight) {
        Connection conn = MySQLConnection.connect();
        try {
            // DELETE FROM passenger WHERE id=3;
            StringBuilder query = new StringBuilder("INSERT INTO ");
            query.append(Constants.PASSENGER_TABLE).append(" VALUES (%d, %d);");

            PreparedStatement stmt = conn.prepareStatement(String.format(query.toString(), customer.getId(), customer.getAccNum()));
            stmt.executeUpdate();

            // INSERT INTO reservation (BookingFee, TotalFare, RepSSN, AccountNo) VALUES (100, 120, 0, 4);
            query.setLength(0);
            query.append("INSERT INFO ").append(Constants.RESERVATION_TABLE).append(" (")
                    .append(Constants.BOOKING_FEE_FIELD).append(", ")
                    .append(Constants.TOTAL_FARE_FIELD).append(", ")
                    .append(Constants.REP_SSN_FIELD).append(", ")
                    .append(Constants.ACCOUNTNO_FIELD).append(") VALUES (%d, %d, %s, %d);");
            stmt = conn.prepareStatement(String.format(query.toString(),
                    flight.getFare() * SYSTEM_FEE, flight.getFare(), customer.getAccNum()));
            stmt.executeUpdate();

			//  INSERT INTO reservationpassenger VALUES ((SELECT ResrNo FROM reservation WHERE AccountNo=5 LIMIT 1),
			// (SELECT Id FROM passenger WHERE AccountNo=5 LIMIT 1), 5, "14A", "Economic", "Spaghetti Carbonara with Pancetta and Mushrooms");
			query.setLength(0);
			query.append("INSERT INFO ").append(Constants.RESERVATION_PASSENGER_TABLE).append(" VALUES (")
					.append("(SELECT ").append(Constants.RESERVATION_NO_FIELD).append(" FROM ").append(Constants.RESERVATION_TABLE)
					.append(" WHERE ").append(Constants.ACCOUNTNO_FIELD).append("=%d LIMIT 1), (SELECT ")
					.append(Constants.ID_FIELD).append(" FROM ").append(Constants.PASSENGER_TABLE).append(" WHERE")
					.append(Constants.ACCOUNTNO_FIELD).append("=%d LIMIT 1), %s, %s, %s);");
			stmt = conn.prepareStatement(String.format(query.toString(), customer.getAccNum(), customer.getAccNum(),
					customer.getAccNum(), flight.getSeatNum(), flight.getFlightClass(), flight.getMeal()));
			stmt.executeUpdate();

			// INSERT INTO includes VALUES ((SELECT ResrNo FROM reservation WHERE AccountNo=5 LIMIT 1), "JA", 111, 1, CURRENT_TIMESTAMP);
			query.setLength(0);
			query.append("INSERT INTO ").append(Constants.INCLUDES_TABLE).append(" VALUES ((SELECT ")
					.append(Constants.RESERVATION_TABLE).append(" WHERE ")
					.append(Constants.ACCOUNTNO_FIELD).append("=%d LIMIT 1), %s, %d, %d, CURRENT_TIMESTAMP");
			stmt = conn.prepareStatement(String.format(query.toString(), customer.getAccNum(),
					flight.getAirline(), flight.getFlightNo(), flight.getLegs().get(0).getNumber()));
			stmt.executeUpdate();
			conn.commit();
			conn.close();

//			processedRequest = true;
        } catch (SQLException ex) {
            Logger.getLogger(FlightReservationDAO.class.getName()).log(Level.SEVERE, "SQL query Error", ex);
        }
        return false;
    }

    public List<Reservation> getReservationHistory(int accNo) {
        List<Reservation> reservations = new ArrayList<>();
        
        String query = "SELECT R." + Constants.RESERVATION_NO_FIELD
                + ", R." + Constants.RESERVATION_DATE_FIELD
                + ", R." + Constants.BOOKING_FEE_FIELD 
                + ", R." + Constants.TOTAL_FARE_FIELD 
                + ", R." + Constants.REP_SSN_FIELD 
                + ", E." + Constants.FIRSTNAME_FILED 
                + ", E." + Constants.LASTNAME_FILED 
                + ", E." + Constants.PHONE_FILED
                
                + " FROM " + Constants.RESERVATION_TABLE + " R, " + Constants.EMPLOYEE_TABLE + " E, " + Constants.PERSON_TABLE + " P "
                + "WHERE R." + Constants.ACCOUNTNO_FIELD + " = ? "
                + "AND R." + Constants.REP_SSN_FIELD + " = E." + Constants.EMPLOYEE_SSN_FIELD
                + " AND E." + Constants.ID_FIELD + " = P." + Constants.ID_FIELD + ";";
        
        Connection conn = MySQLConnection.connect();
        
        try {
            PreparedStatement stmt = conn.prepareCall(query);
            stmt.setInt(1, accNo);
            
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int reservationNo = rs.getInt(1);
                Timestamp resrTime = rs.getTimestamp(2);
                double bookingFee = rs.getDouble(3);
                double totalFare = rs.getDouble(4);
                String ssn = rs.getString(5);
                String fname = rs.getString(6);
                String lname = rs.getString(7);
                String phone = rs.getString(8);
                
                Reservation reservation = new Reservation(
                        reservationNo, 
                        resrTime, 
                        bookingFee, 
                        totalFare, 
                        null, 
                        new Employee(ssn, null, 0, fname, lname, phone, null), null);
                        
                reservations.add(reservation);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(FlightReservationDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return reservations;
    }
}
