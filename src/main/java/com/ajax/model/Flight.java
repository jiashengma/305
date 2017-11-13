package com.ajax.model;

import java.util.List;

/**
 *
 * @author majiasheng
 */
public class Flight {
    
    private String airline;
    private int flightNo;
    private List<Leg> legs;
    private double fare;
    private double hiddenFare;

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

    public double getFare() {
        return fare;
    }

    public void setFare(double fare) {
        this.fare = fare;
    }

    public double getHiddenFare() {
        return hiddenFare;
    }

    public void setHiddenFare(double hiddenFare) {
        this.hiddenFare = hiddenFare;
    }
    
}
