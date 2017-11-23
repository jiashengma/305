package com.ajax.model;

import java.sql.Date;

/**
 *
 * @author majiasheng
 */
public class Auction {

    private int personAccNo;
    private double NYOP; // bid price
    private String airline;
    private int flightNo;
    private FlightClass flightClass;

    private Date date;
    private boolean accepted;

    public Auction() {
    }

    public Auction(int personAccNo, double NYOP, String airline, int flightNo, String flightClass) {
        this.personAccNo = personAccNo;
        this.NYOP = NYOP;
        this.airline = airline;
        this.flightNo = flightNo;
        this.flightClass = FlightClass.valueOf(flightClass);
        
    }

    // <editor-fold defaultstate="collapsed" desc=" getters and setters ">
    public int getPersonAccNo() {
        return personAccNo;
    }

    public void setPersonAccNo(int personAccNo) {
        this.personAccNo = personAccNo;
    }

    public double getNYOP() {
        return NYOP;
    }

    public void setNYOP(double NYOP) {
        this.NYOP = NYOP;
    }

    public String getAirline() {
        return airline;
    }

    public void setAirline(String airline) {
        this.airline = airline;
    }

    public int getFlightNo() {
        return flightNo;
    }

    public void setFlightNo(int flightNo) {
        this.flightNo = flightNo;
    }

    public FlightClass getFlightClass() {
        return flightClass;
    }

    public void setFlightClass(FlightClass flightClass) {
        this.flightClass = flightClass;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
    
    public boolean isAccepted() {
        return accepted;
    }

    public void setAccepted(boolean accepted) {
        this.accepted = accepted;
    }
    // </editor-fold>
    
    @Override
    public String toString() {
        return "Account: " + personAccNo + "\n"
                + "Airline: " + airline + "\n"
                + "Flight: " + flightNo + "\n"
                + "Class: " + flightClass.name() + "\n"
                + "NYOP: " + NYOP + "\n"
                + "Date: " + date.toString();
    }

}
