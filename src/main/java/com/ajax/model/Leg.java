package com.ajax.model;

import java.time.LocalTime;

/**
 *
 * @author majiasheng
 */
public class Leg {
    private int number;
    Airport depAirport;
    LocalTime arrTime;
    LocalTime depTime;
    Airport arrAirport;

    public Leg() {
        
    }
    
    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public Airport getDepAirport() {
        return depAirport;
    }

    public void setDepAirport(Airport depAirport) {
        this.depAirport = depAirport;
    }

    public LocalTime getArrTime() {
        return arrTime;
    }

    public void setArrTime(LocalTime arrTime) {
        this.arrTime = arrTime;
    }

    public LocalTime getDepTime() {
        return depTime;
    }

    public void setDepTime(LocalTime depTime) {
        this.depTime = depTime;
    }

    public Airport getArrAirport() {
        return arrAirport;
    }

    public void setArrAirport(Airport arrAirport) {
        this.arrAirport = arrAirport;
    }
    
    
    
}
