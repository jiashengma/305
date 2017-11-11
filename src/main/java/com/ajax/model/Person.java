package com.ajax.model;

//import javax.validation.constraints.Pattern;
/**
 *
 * @author majiasheng
 */
public class Person {

    // count for person id (used in creations)
    public static int count = 0;

    protected int id;
//    @Pattern(regexp="\c+")
    protected String firstName;
//    @Pattern(regexp="\c+")
    protected String lastName;
    protected long phone;
    protected Address address;
    protected String userName;
    protected String password;

    public Person() {
        id = count++;
    }

    public void setId(int id) {
        this.id = id;
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

    public long getPhone() {
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

}
