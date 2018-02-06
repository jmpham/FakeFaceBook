package com.FakeFaceBook.Controller;

import java.util.ArrayList;

public class User {
	private String firstName;
	private String lastName;
	private String email;
	private String password;
	private String profilePicFileName;
	private String Status;
	private String Accomplishments;
	private ArrayList<String> skills;
			
	
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
	public String getemail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
}
