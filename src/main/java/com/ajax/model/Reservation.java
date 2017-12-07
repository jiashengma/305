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
    private Timestamp reservationTime;
    private double bookingFee;
    private double totalFare;
    private Customer customer;
    private Employee rep;
    private List<Leg> legs;
    private Timestamp date;
    private Flight flight;

    public Flight getFlight() {
        return flight;
    }

    public void setFlight(Flight flight) {
        this.flight = flight;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public Reservation() {
        legs = new ArrayList<>();
    }

    public Reservation(int reservationNo, Timestamp reservationTime, double bookingFee, double totalFare, Customer customer, Employee rep, List<Leg> legs) {
        this.reservationNo = reservationNo;
        this.reservationTime = reservationTime;
        this.bookingFee = bookingFee;
        this.totalFare = totalFare;
        this.customer = customer;
        this.rep = rep;
        this.legs = legs;
    }

    public int getReservationNo() {
        return reservationNo;
    }

    public void setReservationNo(int reservationNo) {
        this.reservationNo = reservationNo;
    }

    public Timestamp getReservationTime() {
        return reservationTime;
    }

    public void setReservationTime(Timestamp reservationTime) {
        this.reservationTime = reservationTime;
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

    public Employee getRep() {
        return rep;
    }

    public void setRep(Employee rep) {
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
