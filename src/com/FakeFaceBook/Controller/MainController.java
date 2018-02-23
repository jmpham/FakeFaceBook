package com.FakeFaceBook.Controller;
import java.sql.Connection;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
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
import java.sql.*;
@EnableWebMvc
@ResponseBody
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
	public ModelAndView getUserPage(@ModelAttribute("user1") User user, BindingResult error, HttpServletResponse response) {
		
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
			if(user.getemailSignIn().equals(result.getString("email"))) {	// '==' is to compare reference address. .equals() is comparing content
				emailPasswordMatch = true;
				view = new ModelAndView("UserPage");
			}
		}
		System.out.println("the email entered is matched: " + emailPasswordMatch);
		
		//Retrieve User info from MySQL
		sql = "Select * from Users where email = '" + user.getemail() + "'";
		result = myStmt.executeQuery(sql);
		user.setFirstName(result.getString("firstName"));
		user.setLastName(result.getString("lastName"));
		
		}
		catch(Exception exc) {
			exc.printStackTrace();
		}
		
		
		
		return view;
	}
	
	//IMAGE Upload
	@RequestMapping(value="/uploadIMG/{email}", method = RequestMethod.POST)
	public ModelAndView uploadImageCtlr(@RequestParam MultipartFile file, @PathVariable("email") String email, HttpServletRequest request) throws IOException {
		
		
		//String filePath = request.getServletContext().getRealPath("/images/imageFile.jpg"); 
		String filePath = "C:/Users/Jonathan/Desktop/Eclipse-Workspace/FakeFaceBook/resources/images/" + email +"_ProfilePic.jpg";
		file.transferTo(new File(filePath));
		//try to receive file input stream. This is how server receives file data from client side. Need to buffer serialize bit stream
		/*InputStream inputStream =  new BufferedInputStream(file.getInputStream());
		File imageFile = new File("\\imageFile.jpg");
		@SuppressWarnings("resource")
		FileOutputStream output = new FileOutputStream(imageFile);
		byte[] buffer = new byte[1024];
		while (inputStream.read( buffer) > 0) {
			output.write(buffer);
		}
		
		System.out.println("\nSaved to file: " + imageFile.getAbsolutePath());
		
		if (inputStream != null) {
			inputStream.close();
		}
		if(output != null) {
			output.close();
		}*/
		/*try {
			InputStream inputStream =  new BufferedInputStream(file.getInputStream());
			try {
				Class.forName("com.mysql.jdbc.Driver");
				Connection myConn = DriverManager.getConnection("jdbc:mysql://mydbinstance.c0su7dxcxumd.us-east-2.rds.amazonaws.com:3306/FakeFaceBook", "jmpham21", "Amazon1#");
				
				String sql = "update Users set profile_pic=? where email='jon@yahoo.com'";
				PreparedStatement myStmt = myConn.prepareStatement(sql);
				myStmt.setBinaryStream(1, inputStream);
				
				myStmt.executeUpdate(); //DO NOT ADD SQL TO THE PARAMETER. Does not work for 'PreparedStatements'
				System.out.println("Update DB Complete");
			}
			catch(Exception exc) {
				exc.printStackTrace();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} */
			
		
		
		
		ModelAndView view = new ModelAndView("UserPage");
		return view;
	}
	
}
