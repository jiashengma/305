package com.ajax.model;

//import javax.validation.constraints.Pattern;

import javax.validation.constraints.Pattern;

public class Person {

    protected int id;
    @Pattern(regexp="\\w+", message = "First name must just have letters")
    protected String firstName;
    @Pattern(regexp="\\w+", message = "Last name must just have letters")
    protected String lastName;
    @Pattern(regexp = "[0-9]{10}", message = "Phone number has 10 digits")
    protected String phone;
    protected Address address;
    @Pattern(regexp="[a-zA-Z]+[_0-9]*", message = "Username can only have alpha numeric charactes and \"_\", and it must begin with a letter")
    protected String userName;
    @Pattern(regexp="[a-zA-Z_.!,~0-9]+", message = "Username can only have alpha numeric charactes and \"_ . ! , ~ \"")
    protected String password;
    protected AccessControl accessControl;

    public Person() {
    }
    
    public Person(String fname, String lname, String phone, Address addr) {
        this.firstName = fname;
        this.lastName = lname;
        this.phone = phone;
        this.address = addr;
    }

    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
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

    public AccessControl getAccessControl() {
        return accessControl;
    }

    public void setAccessControl(AccessControl accessControl) {
        this.accessControl = accessControl;
    }
}
