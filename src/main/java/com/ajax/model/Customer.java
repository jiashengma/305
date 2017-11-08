package com.ajax.model;

public class Customer extends Person {

    // counter for account number
    public static int count = 0;

    private String email;
    private String userName;
    private String password;
    private int accNum;
    private int rating;

    public Customer() {
    }

//    public Customer(String email, String userName, String firstName, String lastName) {
//        this.email = email;
//        this.userName = userName;
//        this.firstName = firstName;
//        this.lastName = lastName;
//    }
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

    public void setAccNum(int accNum) {
        this.accNum = accNum;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    @Override
    public String toString() {
        return "username: " + userName + "\n"
                + "Full name: " + firstName + " " + lastName + "\n"
                + "email: " + email + "\n";
    }
}
