package com.ajax.model;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author majiasheng
 */
public class Reservation {
    private int reservationNo;
    private double bookingFee;
    private double totalFare;
    private Customer customer;
    private CustomerRepresentative rep;
    private List<Leg> legs;
    private Timestamp date;

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public Reservation() {
        legs = new ArrayList<Leg>();
    }
    
    public int getReservationNo() {
        return reservationNo;
    }

    public void setReservationNo(int reservationNo) {
        this.reservationNo = reservationNo;
    }

    public double getBookingFee() {
        return bookingFee;
    }

    public void setBookingFee(double bookingFee) {
        this.bookingFee = bookingFee;
    }

    public double getTotalFare() {
        return totalFare;
    }

    public void setTotalFare(double totalFare) {
        this.totalFare = totalFare;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public CustomerRepresentative getRep() {
        return rep;
    }

    public void setRep(CustomerRepresentative rep) {
        this.rep = rep;
    }

    public List<Leg> getLegs() {
        return legs;
    }

    public void setLegs(List<Leg> legs) {
        this.legs = legs;
    }
    
    public void addLeg(Leg leg) {
        legs.add(leg);
    }
    
}
