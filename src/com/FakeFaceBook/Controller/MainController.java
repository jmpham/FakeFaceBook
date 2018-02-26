package com.FakeFaceBook.Controller;
import java.sql.Connection;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;

import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.*;
@EnableWebMvc
@ResponseBody

@Controller

@SessionAttributes("user1")
public class MainController {
	
	
	@ModelAttribute("user1")
	public User setUpUserForm() {
		return new User();
	}
	
	
	
	//LOGIN SCREEN
	@RequestMapping(value="/Login.html")
	public ModelAndView getLoginPage(){
		System.out.println("getting login page: ");
	
		ModelAndView view = new ModelAndView("Login");
		return view;
	}
	
	//SIGN UP SUBMISSION
	@RequestMapping(value="/signupLogin.html", method = RequestMethod.POST)
	public ModelAndView getLoginPage2(@ModelAttribute("user1") User user) {
		
		//Check for errors
		
		//Update the user class
		System.out.println("SignUp Processing...");
		System.out.println("firstName: " + user.getFirstName() + " lastName:  " + user.getLastName() + " email:  " + user.getemail());
		
		//Update Database with New User Info
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection myConn = DriverManager.getConnection("jdbc:mysql://mydbinstance.c0su7dxcxumd.us-east-2.rds.amazonaws.com:3306/FakeFaceBook", "jmpham21", "Amazon1#");
			Statement myStmt = myConn.createStatement();
			
			//Add user to DB 
			String sql = "insert into Users (firstName, lastName, email) values ('" + 
					user.getFirstName() +"','"+ 
					user.getLastName() +"','"+ 
					user.getemail() +"')";
			myStmt.execute(sql);
			System.out.println("Update DB Complete");
		}
		catch(Exception exc) {
			exc.printStackTrace();
		}
		
		//Change view to the User's Profile Page
		ModelAndView view = new ModelAndView("UserPage");
		return view;
	};
	
	
	
	//SIGN IN
	@RequestMapping(value="/userPage.html")
	public ModelAndView getUserPage(@ModelAttribute("user1") User user, HttpServletRequest request) {
		//@ModelAttribute("user1") User user
		//Default view and emailmatch variable
		boolean emailPasswordMatch = false;
		ModelAndView view = new ModelAndView("Login");
		
		//Try to find user email and password in DB.
		try {
		Class.forName("com.mysql.jdbc.Driver");
		Connection MyConn = DriverManager.getConnection("jdbc:mysql://mydbinstance.c0su7dxcxumd.us-east-2.rds.amazonaws.com:3306/FakeFaceBook", "jmpham21", "Amazon1#");
		Statement myStmt = MyConn.createStatement();
		String sql = "Select * from Users";
		ResultSet result = myStmt.executeQuery(sql);
		while(result.next()) {
			System.out.println("From DataBase: " + result.getString("email"));
			if(user.getemail().equals(result.getString("email"))) {	// '==' is to compare reference address. .equals() is comparing content
				emailPasswordMatch = true;
				view = new ModelAndView("UserPage");
			}
		}
		System.out.println("the email entered is matched: " + emailPasswordMatch);
		
		//Retrieve User info from MySQL
		sql = "Select * from Users where email = '" + user.getemail() + "'";
		//sql = "select * from Users where email = 'coolrun@yahoo.com'";
		result = myStmt.executeQuery(sql);
		System.out.println("here1");
		result.next();
		System.out.println("here2");
		user.setFirstName(result.getString("firstName"));
		user.setLastName(result.getString("lastName"));
		System.out.println("here3");
		}
		catch(Exception exc) {
			exc.printStackTrace();
		}
		
		
		
		return view;
	}
	
	//IMAGE Upload
	@RequestMapping(value="/uploadIMG/", method = RequestMethod.POST)
	public ModelAndView uploadImageCtlr(@SessionAttribute("user1") User user, @RequestParam MultipartFile file, HttpServletRequest request ) throws IOException {
		
		//Establish filepath from local machine to store image
		String filePath = "C:/Users/Jonathan/Desktop/Eclipse-Workspace/FakeFaceBook/resources/images/" + user.getemail() +"_ProfilePic.jpg";
		file.transferTo(new File(filePath));
		System.out.println("\nUploading Profile Picture Complete");		
		ModelAndView view = new ModelAndView("UserPage");
		return view;
	}
	
	//Status upload
	@RequestMapping(value="/uploadStatus/", method = RequestMethod.POST)
	public ModelAndView uploadStatusCtlr(@SessionAttribute("user1") User user, @RequestParam String status, HttpServletRequest request ) throws IOException {
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection MyConn = DriverManager.getConnection("jdbc:mysql://mydbinstance.c0su7dxcxumd.us-east-2.rds.amazonaws.com:3306/FakeFaceBook", "jmpham21", "Amazon1#");
			Statement myStmt = MyConn.createStatement();
			String sql = "UPDATE Users set status = '" + status + "'where email='" + user.getemail() +"'";
			System.out.println(sql);
			myStmt.executeUpdate(sql);
			System.out.println("updating status complete: "+ status);
			}
			catch(Exception exc) {
				exc.printStackTrace();
			}
		
		//System.out.println("\nUploading Status Complete: " + status);		
		ModelAndView view = new ModelAndView("UserPage");
		return view;
	}
	
	//Resume Upload
		@RequestMapping(value="/uploadResume/", method = RequestMethod.POST)
		public ModelAndView uploadResumeCtlr(@SessionAttribute("user1") User user, @RequestParam MultipartFile resume, HttpServletRequest request ) throws IOException {
			
			//Establish filepath from local machine to store image
			String filePath = "C:/Users/Jonathan/Desktop/Eclipse-Workspace/FakeFaceBook/resources/status/" + user.getemail() +"_Resume.pdf";
			resume.transferTo(new File(filePath));
			System.out.println("\nUploading Resume Complete: ");		
			ModelAndView view = new ModelAndView("UserPage");
			return view;
		}
	
	
}
