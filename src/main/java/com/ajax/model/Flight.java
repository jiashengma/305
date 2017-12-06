package com.ajax.model;

import java.util.List;
import java.util.Optional;

public class Flight {
	private static String[] arr = {"A", "B", "C", "D", "E", "F"};

	private String airline;
	private int flightNo;
	private List<Leg> legs;
	private FlightClass flightClass;
	private String meal;
	private String SeatNum;
	private double fare;
	private Optional<Double> hiddenFare;

	public Flight() {}

	public Flight(String airline, int flightNo, List<Leg> legs, String flightClass, double fare, Double hiddenFare, int seatNum) {
		this.airline = airline;
		this.flightNo = flightNo;
		this.legs = legs;
		this.flightClass = FlightClass.valueOf(flightClass);
		this.fare = fare;
		this.hiddenFare = Optional.ofNullable(hiddenFare);
		this.SeatNum = String.format("%d%s", seatNum / 6, arr[seatNum % 6]);
	}
	// TODO: add fare restrictions
	public String getAirline() {
		return airline;
	}

	public void setAirline(String Airline) {
		this.airline = Airline;
	}

	public int getFlightNo() {
		return flightNo;
	}

	public void setFlightNo(int flightNo) {
		this.flightNo = flightNo;
	}

	public List<Leg> getLegs() {
		return legs;
	}

	public void setLegs(List<Leg> legs) {
		this.legs = legs;
	}

	public FlightClass getFlightClass() {
		return flightClass;
	}

	public void setFlightClass(String flightClass) {
		this.flightClass = FlightClass.valueOf(flightClass);
	}

	public double getFare() {
		return fare;
	}

	public void setFare(double fare) {
		this.fare = fare;
	}

	public double getHiddenFare() {
		return hiddenFare.orElse(fare);
	}

	public void setHiddenFare(double hiddenFare) {
		this.hiddenFare = Optional.of(hiddenFare);
	}

	public String getMeal() {
		return meal;
	}

	public void setMeal(String meal) {
		this.meal = meal;
	}

	public String getSeatNum() {
		return SeatNum;
	}

	public void setSeatNum(String seatNum) {
		SeatNum = seatNum;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		legs.forEach(sb::append);
		return String.format("Flight{airline='%s', flightNo=%d, legs=<%s>, fare=%.2f, hiddenFare=%.2f}",
				airline, flightNo, sb.toString(), fare, hiddenFare.orElse(-1.0));
	}
}
