package com.Valhalla.Controller;
import java.sql.Connection;
import java.util.concurrent.TimeUnit;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
import org.springframework.web.bind.support.SessionStatus;
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
	
	//This is automically called first if a servlet doesn't have a modelattribute in it's parameter, or if the attribute hasn't been set
	@ModelAttribute("user1")
	public User setUpUserForm() {
		return new User();
	}
	
	
	
	//LOGIN SCREEN
	@RequestMapping(value="/")
	public ModelAndView getLoginPage(){
		System.out.println("getting login page: " );
		
		ModelAndView view = new ModelAndView("Login");
		return view;
	}
	
	//LOGOUT Request
	@RequestMapping(value="/Logout")
	public void getLogoutPage(SessionStatus status, HttpServletResponse response) throws IOException {
		//end the current session when logging out
		status.setComplete();
		System.out.println("\nLogging out");
		//NEED TO REDIRECT TO LOGIN SERVLET. SESSION ATTRIBUTES STILL STICKY FOR THIS REQUEST UNTIL THE NEXT
		response.sendRedirect("/Valhalla/");
	}
	
	//BACK TO USERPAGE SCREEN
		@RequestMapping(value="/UserPage")
		public ModelAndView getUserPage(){
			System.out.println("\nGoing Back Home: " );
			
			ModelAndView view = new ModelAndView("UserPage");
			return view;
		}
	
	//SEARCH PROFILES Request
		@RequestMapping(value="/search")
		public ModelAndView getProfile(@ModelAttribute("user2") User user2, @RequestParam String profile) throws IOException {
			
			System.out.println("\nFinding Profile: " + profile);
			try {
				Class.forName("com.mysql.jdbc.Driver");
				//Connection MyConn = DriverManager.getConnection("jdbc:mysql://mydbinstance.c0su7dxcxumd.us-east-2.rds.amazonaws.com:3306/FakeFaceBook", "jmpham21", "Amazon1#");
				Connection MyConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Valhalla", "jmpham21", "Christine1!"); // Linux
				Statement MyStmt = MyConn.createStatement();
				String sql = "select * from Users";
				ResultSet result = MyStmt.executeQuery(sql);
				while(result.next()) {
					if(result.getString("email").equals(profile)) {
						user2.setEmail(result.getString("email"));
						user2.setFirstName(result.getString("firstName"));
						user2.setLastName(result.getString("lastName"));
						break;
					}
				}
				
			}
			catch(Exception exc){
				exc.printStackTrace();
			}
			
			ModelAndView view = new ModelAndView("FriendPage");
			return view;
		}
	
	//SIGN UP SUBMISSION
	@RequestMapping(value="/signupLogin.html", method = RequestMethod.POST)
	public ModelAndView getLoginPage2(@ModelAttribute("user1") User user) {
		
		//Check for errors
		
		//Update the user class
		System.out.println("SignUp Processing...");
		System.out.println("firstName: " + user.getFirstName() + " lastName:  " + user.getLastName() + " email:  " + user.getemail());
		
		//Update Database with New User Info. Email validation checked via Javascript API call.
		try {
			Class.forName("com.mysql.jdbc.Driver");
			//Connection MyConn = DriverManager.getConnection("jdbc:mysql://mydbinstance.c0su7dxcxumd.us-east-2.rds.amazonaws.com:3306/FakeFaceBook", "jmpham21", "Amazon1#");
			Connection MyConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Valhalla", "jmpham21", "Christine1!"); // Linux
			Statement myStmt = MyConn.createStatement();
			
			//Add user to DB 
			String sql = "insert into Users (firstName, lastName, password, email) values ('" + 
					user.getFirstName() +"','"+ 
					user.getLastName() +"','"+
					user.getPassword() +"','"+
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
	@SuppressWarnings("resource")
	@RequestMapping(value="/userPage.html")
	public ModelAndView getUserPage(@ModelAttribute("user1") User user, HttpServletRequest request) {
	
		//Default view emailmatch variable
		boolean emailPasswordMatch = false;
		ModelAndView view = new ModelAndView("Login");
		user.setinvalidCreds("");
		
		
		
		//Try to find user email and password in DB.
		try {
		Class.forName("com.mysql.jdbc.Driver");
		//Connection MyConn = DriverManager.getConnection("jdbc:mysql://mydbinstance.c0su7dxcxumd.us-east-2.rds.amazonaws.com:3306/FakeFaceBook", "jmpham21", "Amazon1#");
		Connection MyConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Valhalla", "jmpham21", "Christine1!"); // Linux
		Statement myStmt = MyConn.createStatement();
		String sql = "Select * from Users";
		ResultSet result = myStmt.executeQuery(sql);
		while(result.next()) {
			System.out.println("From DataBase: " + result.getString("email"));
			if(user.getemail().equals(result.getString("email")))  {	// '==' is to compare reference address. .equals() is comparing content
				if(user.getPassword().equals(result.getString("password"))){
				emailPasswordMatch = true;
				//Retrieve User info from MySQL
				sql = "Select * from Users where email = '" + user.getemail() + "'";
				result = myStmt.executeQuery(sql);
				result.next();
				user.setFirstName(result.getString("firstName"));
				user.setLastName(result.getString("lastName"));
				view = new ModelAndView("UserPage");
				}
			}
			else {
				user.setinvalidCreds("Invalid Email or Password");
			}
		}
		result.close();
		System.out.println("the email entered is matched: " + emailPasswordMatch);
		
		
		}
		catch(Exception exc) {
			exc.printStackTrace();
		}
		
		//**************DELETE AFTER TESTING ON AWS
		//user.setFirstName("test");
		//user.setLastName("test2");
		//view = new ModelAndView("UserPage");
		return view;
	}
	
	//IMAGE Upload
	@RequestMapping(value="/uploadIMG/", method = RequestMethod.POST)
	public void uploadImageCtlr(@SessionAttribute("user1") User user, @RequestParam MultipartFile file, HttpServletResponse response ) throws IOException {
		
		//Establish filepath from local machine to store image
		//String filePath = "C:/Users/Jonathan/Desktop/Eclipse-Workspace/FakeFaceBook/resources/files/" + user.getemail() +"_ProfilePic.jpg";
		String filePath = "/home/ec2-user/apache-tomcat-9.0.2/Valhalla/resources/" + user.getemail() +"_ProfilePic.jpg"; //Linux
		file.transferTo(new File(filePath));
		System.out.println("\nUploading Profile Picture Complete");		
		//ModelAndView view = new ModelAndView("UserPage");
		//return view;
		response.sendRedirect("/Valhalla/UserPage");
	}

	//BANNER PICTURE Upload
		@RequestMapping(value="/uploadBannerPic/", method = RequestMethod.POST)
		public void uploadBanner(@SessionAttribute("user1") User user, @RequestParam MultipartFile file, HttpServletResponse response ) throws IOException {
			
			//Establish filepath from local machine to store image
			//String filePath = "C:/Users/Jonathan/Desktop/Eclipse-Workspace/FakeFaceBook/resources/files/" + user.getemail() +"_BannerPic.jpg";
			String filePath = "/home/ec2-user/apache-tomcat-9.0.2/Valhalla/resources/" + user.getemail() +"_BannerPic.jpg"; //Linux
			file.transferTo(new File(filePath));
			System.out.println("\nUploading Banner Picture Complete");		
			//ModelAndView view = new ModelAndView("UserPage");
			//return view;
			response.sendRedirect("/Valhalla/UserPage");
		}
	
	//Status Upload
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
		public void uploadResumeCtlr(@SessionAttribute("user1") User user, @RequestParam MultipartFile resume, HttpServletResponse response ) throws IOException {
			
			//Establish filepath from local machine to store image
			//String filePath = "C:/Users/Jonathan/Desktop/Eclipse-Workspace/FakeFaceBook/resources/files/" + user.getemail() +"_Resume.pdf"; 
			String filePath = "/home/ec2-user/apache-tomcat-9.0.2/Valhalla/resources/" + user.getemail() +"_Resume.jpg"; //Linux
			resume.transferTo(new File(filePath));
			System.out.println("\nUploading Resume Complete: ");		
			//ModelAndView view = new ModelAndView("UserPage");
			//return view;
			response.sendRedirect("/Valhalla/UserPage");
		}
	
	
}
