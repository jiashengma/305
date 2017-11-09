package com.ajax.model;

//import javax.validation.constraints.Pattern;
/**
 *
 * @author majiasheng
 */
public class Person {

    // count for person id (used in creations)
    public static int count = 0;

    protected final int id;
//    @Pattern(regexp="\c+")
    protected String firstName;
//    @Pattern(regexp="\c+")
    protected String lastName;
    protected int phone;
    protected Address address;

    public Person() {
        id = count++;
    }

    public int getId() {
        return id;
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

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

}
