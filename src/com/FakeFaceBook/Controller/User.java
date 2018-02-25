package com.FakeFaceBook.Controller;

import java.util.ArrayList;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;


//Scope the Objects ,for example you have user object that want be in session every time:


public class User {
	private String emailSignIn;
	private String passwordSignIn;
	private String firstName;
	private String lastName;
	private String email;
	private String password;
	private String profilePicFileName;
	private String Status;
	private String Accomplishments;
	private ArrayList<String> skills;
	private boolean validEmail;		
	
	User(){
		this.firstName = "jony";
	}
	
	public String getemailSignIn() {
		return emailSignIn;
	}
	public void setemailSignIn(String emailSignIn) {
		this.emailSignIn = emailSignIn;
	}
	public String getpasswordSignIn() {
		return passwordSignIn;
	}
	public void setpasswordSignIn(String passwordSignIn) {
		this.passwordSignIn = passwordSignIn;
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
	public void setProfilePicFileName(String fileName) {
		this.profilePicFileName = fileName;
	}
	public String getProfilePicFileName() {
		return profilePicFileName;
	}
}
