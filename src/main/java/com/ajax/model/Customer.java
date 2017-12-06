package com.ajax.model;

import javax.validation.constraints.Pattern;
import org.hibernate.validator.constraints.Email;

public class Customer extends Person {

    final int CRED_CARD_LENGTH = 16;
    //@Pattern(regexp = "")
    @Email
    protected String email;
    @Pattern(regexp = "[0-9]{16}", message = "Credit card has to be a 16-digit number")
    private String creditCard;
    private int rating;
    private int accNum;
    private String prefMeal;

    public Customer() {
        super();
        // rating is default as 0
        rating = 0;
        this.address = new Address();
        this.accessControl = AccessControl.CUSTOMER;
    }

    public Customer(String fname, String lname, String phone, Address addr, String creditCard, String email) {
        super(fname, lname, phone, addr);
        rating = 0;
        this.creditCard = creditCard;
        this.email = email;
        this.accessControl = AccessControl.CUSTOMER;
    }

    public String getPrefMeal() {
        return prefMeal;
    }

    public void setPrefMeal(String prefMeal) {
        this.prefMeal = prefMeal;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getAccNum() {
        return accNum;
    }

    public void setAccNum(int accNum) {
        this.accNum = accNum;
    }

    public String getCreditCard() {
        return creditCard;
    }

    public void setCreditCard(String creditCard) {
        this.creditCard = creditCard;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    @Override
    public String toString() {
        return "Username: " + userName + "\n"
                + "Full name: " + firstName + " " + lastName + "\n"
                + "Email: " + email + "\n"
                + "Address :" + address.toString();

    }
}
