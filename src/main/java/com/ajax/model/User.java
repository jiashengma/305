package com.ajax.model;

public class User {
	private String email;
	private String userName;
	private String password;
	private String firstName;
	private String lastName;
	
	
	public User() {}
	public User(String email, String userName, String firstName, String lastName) {
		this.email = email;
		this.userName = userName;
		this.firstName = firstName;
		this.lastName = lastName;
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
	
	@Override
	public String toString() {
		return "username: " + userName + "\n"
				+ "Full name: " + firstName + " " + lastName + "\n"
				+ "email: " + email + "\n";
				
	}
}