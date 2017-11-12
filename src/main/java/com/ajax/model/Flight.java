package com.ajax.model;

import java.util.List;

/**
 *
 * @author majiasheng
 */
public class Flight {
    
    private String Airline;
    private int flightNo;
    private List<Leg> legs;

    public String getAirline() {
        return Airline;
    }

    public void setAirline(String Airline) {
        this.Airline = Airline;
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
    
    
}
