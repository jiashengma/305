package com.ajax.model;

import java.sql.Timestamp;
import java.time.LocalTime;

/**
 *
 * @author majiasheng
 */
public class Leg {
    private int number;
	private Airport depAirport;
	private Timestamp arrTime;
	private Timestamp depTime;
	private Airport arrAirport;

    public Leg() {}

	public Leg(int number, Airport depAirport, Timestamp arrTime, Timestamp depTime, Airport arrAirport) {
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

    public Timestamp getArrTime() {
        return arrTime;
    }

    public void setArrTime(Timestamp arrTime) {
        this.arrTime = arrTime;
    }

    public Timestamp getDepTime() {
        return depTime;
    }

    public void setDepTime(Timestamp depTime) {
        this.depTime = depTime;
    }

    public Airport getArrAirport() {
        return arrAirport;
    }

    public void setArrAirport(Airport arrAirport) {
        this.arrAirport = arrAirport;
    }

	@Override
	public String toString() {
		return "Leg{" +
				"number=" + number +
				", depAirport=" + depAirport +
				", arrTime=" + arrTime +
				", depTime=" + depTime +
				", arrAirport=" + arrAirport +
				'}';
	}
}
