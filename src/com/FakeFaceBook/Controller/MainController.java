package com.FakeFaceBook.Controller;
import java.sql.Connection;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import java.sql.*;

@Controller
public class MainController {
	
	//LOGIN SCREEN
	@RequestMapping(value="/Login.html")
	public ModelAndView getLoginPage(){
		System.out.println("getting login page");

		ModelAndView view = new ModelAndView("Login");
		return view;
	}
	
	//SIGN UP SUBMISSION
	@RequestMapping(value="/signupLogin.html", method = RequestMethod.POST)
	public ModelAndView getLoginPage2(@ModelAttribute("user1") User user, BindingResult error) {
		
		//Check for errors
		if(error.hasErrors()) {
			ModelAndView view = new ModelAndView("Login");
			System.out.println("error has occurred");
		}
		//Update the user class
		System.out.println("SignUp Complete");
		System.out.println("firstName: " + user.getFirstName() + " lastName:  " + user.getLastName() + " email:  " + user.getemail());
		
		//Update Database
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection myConn = DriverManager.getConnection("jdbc:mysql://mydbinstance.c0su7dxcxumd.us-east-2.rds.amazonaws.com:3306/FakeFaceBook", "jmpham21", "Amazon1#");
			Statement myStmt = myConn.createStatement();
			//Add user to DB
			String sql = "insert into Users (firstName, lastName, email) values ('" + user.getFirstName() +"','"+ user.getLastName() +"','"+ user.getemail()+"')";
			myStmt.execute(sql);
			System.out.println("Update DB Complete");
		}
		catch(Exception exc) {
			exc.printStackTrace();
		}
		
		ModelAndView view = new ModelAndView("Login");
		return view;
	}
	
}
