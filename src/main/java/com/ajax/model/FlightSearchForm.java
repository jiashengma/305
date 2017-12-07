package com.ajax.model;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

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
        this.flyingFrom = flyingFrom.trim();
    }

    public String getFlyingTo() {
        return flyingTo;
    }

    public void setFlyingTo(String flyingTo) {
        this.flyingTo = flyingTo.trim();
    }

    public Date getDepDate() {
        return depDate;
    }

    public void setDepDate(String depDate) {
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            this.depDate = format.parse(depDate);
        } catch (ParseException ex) {
	        System.out.println("Error parsing depDate in FlightSearchForm");
//            Logger.getLogger(FlightSearchForm.class.getName()).log(Level.SEVERE, "Invalid Date format", ex);
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
	        System.out.println("Error parsing retDate in FlightSearchForm");
//            Logger.getLogger(FlightSearchForm.class.getName()).log(Level.SEVERE, "Invalid Date format", ex);
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
        StringBuilder res = new StringBuilder("Flight Search form:");
        if (flyingFrom != null)
        	res.append("\nFlying From: <").append(flyingFrom).append(">");
        if (flyingTo != null)
        	res.append("\nFlying to: <").append(flyingTo).append(">");
        if (depDate != null)
        	res.append("\nDeparture date: ").append(depDate.toString());
        if (retDate != null)
        	res.append("\nReturning date: ").append("retDate.toString()");
        return res.append("\n").toString();
    }

}
