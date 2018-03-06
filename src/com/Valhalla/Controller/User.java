package com.Valhalla.Controller;

import java.util.ArrayList;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;


//Scope the Objects ,for example you have user object that want be in session every time:


public class User {
	private String firstName;
	private String lastName;
	private String email;
	private String password;
	private String Status;
	private String Accomplishments;
	private ArrayList<String> skills;
	private boolean validEmail;		
	private String invalidCreds;
	
	User(){
		this.invalidCreds = "";
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
	public String getemail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public boolean getValidEmail() {
		return validEmail;
	}
	public void setinvalidCreds(String cred) {
		this.invalidCreds = cred;
	}
	public String getinvalidCreds() {
		return invalidCreds;
	}
}
