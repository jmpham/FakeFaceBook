package com.Valhalla.Controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import java.sql.*;


import org.springframework.web.bind.annotation.RestController;


@RestController
public class APIController {
	//variable for email validation
	public boolean valid = true;
	
	@RequestMapping(value="/post-validate-email/{email}/api", method = RequestMethod.GET)
	public boolean checkEmailValidation(@PathVariable("email") String email) {
		System.out.println("entering API");
		//connect to DB and check to see if email is available
		try {
			System.out.println("Trying connection to DBs");
			Class.forName("com.mysql.jdbc.Driver");
			Connection MyConn = DriverManager.getConnection("jdbc:mysql://mydbinstance.c0su7dxcxumd.us-east-2.rds.amazonaws.com:3306/Valhalla", "jmpham21", "Amazon1#");
			Statement MyStmt = MyConn.createStatement();
			String sql = "SELECT email FROM Users";
			ResultSet MyRs = MyStmt.executeQuery(sql);
			while(MyRs.next()){
				System.out.println(MyRs.getString("email"));
				System.out.println(email);
				if (MyRs.getString("email").equals(email)) {
					System.out.println("email not valid");
					valid = false;
				}
			}
		System.out.println("Email is available: " + valid);
		}
		catch(Exception exc) {
			exc.printStackTrace();
		}
		boolean temp = valid;
		valid = true;
		return temp;
	}
}
