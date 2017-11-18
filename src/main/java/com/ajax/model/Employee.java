package com.ajax.model;

import java.sql.Date;

/**
 *
 * @author majiasheng
 */
public class Employee extends Person {

    private int ssn;
    private Date startDate;
    private double hourlyRate;

    public Employee(){
        super();
    }
    public Employee(int ssn, Date startDate, double hourlyRate, String fname, String lname, long phone, Address addr) {
        super(fname, lname, phone, addr);
        this.ssn = ssn;
        this.startDate = startDate;
        this.hourlyRate = hourlyRate;
    }

    public int getSsn() {
        return ssn;
    }

    public void setSsn(int ssn) {
        this.ssn = ssn;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public double getHourlyRate() {
        return hourlyRate;
    }

    public void setHourlyRate(double hourlyRate) {
        this.hourlyRate = hourlyRate;
    }

}
