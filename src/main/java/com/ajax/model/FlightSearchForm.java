package com.ajax.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author majiasheng
 */
public class FlightSearchForm {

    private String flyingFrom;  // city or airport, case insensitive
    private String flyingTo;  // city or airport, case insensitive
    private Date depDate;
    private Date retDate;
    private String prefClass;

    public String getFlyingFrom() {
        return flyingFrom;
    }

    public void setFlyingFrom(String flyingFrom) {
        this.flyingFrom = flyingFrom;
    }

    public String getFlyingTo() {
        return flyingTo;
    }

    public void setFlyingTo(String flyingTo) {
        this.flyingTo = flyingTo;
    }

    public Date getDepDate() {
        return depDate;
    }

    public void setDepDate(String depDate) {
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            this.depDate = format.parse(depDate);
        } catch (ParseException ex) {
            Logger.getLogger(FlightSearchForm.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Date getRetDate() {
        return retDate;
    }

    public void setRetDate(String retDate) {
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            this.retDate = format.parse(retDate);
        } catch (ParseException ex) {
            Logger.getLogger(FlightSearchForm.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String getPrefClass() {
        return prefClass;
    }

    public void setPrefClass(String prefClass) {
        this.prefClass = prefClass;
    }

    @Override
    public String toString() {
        return "Flight Search form: \nFlying From: " + flyingFrom + "\n"
                + "Flying to: " + flyingTo + "\n"
                + "Departure date: " + depDate.toString() + "\n"
                + "Returning date: " + retDate.toString() + "\n";

    }

}
