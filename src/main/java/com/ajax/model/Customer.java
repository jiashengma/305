package com.ajax.model;

public class Customer extends Person {

    // counter for account number
    public static int count = 0;

    private String email;
    private String userName;
    private String password;
    private int creditCard;
    private int rating;
    private final int accNum;

    public Customer() {
        // rating is default as 0
        accNum = count++;
        rating = 0;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getAccNum() {
        return accNum;
    }

//    public void setAccNum(int accNum) {
//        this.accNum = accNum;
//    }
    public int getCreditCard() {
        return creditCard;
    }

    public void setCreditCard(int creditCard) {
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
