package com.ajax.model;

import java.time.LocalTime;

/**
 *
 * @author majiasheng
 */
public class Leg {
    private int number;
	private Airport depAirport;
	private LocalTime arrTime;
	private LocalTime depTime;
	private Airport arrAirport;

    public Leg() {}

	public Leg(int number, Airport depAirport, LocalTime arrTime, LocalTime depTime, Airport arrAirport) {
		this.number = number;
		this.depAirport = depAirport;
		this.arrTime = arrTime;
		this.depTime = depTime;
		this.arrAirport = arrAirport;
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
