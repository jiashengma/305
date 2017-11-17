package com.ajax.model;

/**
 *
 * @author majiasheng
 */
public class Auction {

    private int personId;
    private double NYOP; // bid price
    private String airline;
    private int flightNo;
//    private Date date

    public Auction() {
    }

    public Auction(int personId, double NYOP, String airline, int flightNo) {
        this.personId = personId;
        this.NYOP = NYOP;
        this.airline = airline;
        this.flightNo = flightNo;
    }

    public int getPersonId() {
        return personId;
    }

    public void setPersonId(int personId) {
        this.personId = personId;
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

}
