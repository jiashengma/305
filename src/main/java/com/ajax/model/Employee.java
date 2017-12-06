package com.ajax.model;

import java.sql.Date;

public class Employee extends Person {

    private String ssn;
    private Date startDate;
    private double hourlyRate;

    public Employee(){
        super();
    }
    public Employee(String ssn, Date startDate, double hourlyRate, String fname, String lname, String phone, Address addr) {
        super(fname, lname, phone, addr);
        this.ssn = ssn;
        this.startDate = startDate;
        this.hourlyRate = hourlyRate;
    }

    public String getSsn() {
        return ssn;
    }

    public void setSsn(String ssn) {
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
