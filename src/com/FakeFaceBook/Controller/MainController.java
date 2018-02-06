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

	@RequestMapping(value="/Login.html")
	public ModelAndView getLoginPage(){
		System.out.println("getting login page");

		ModelAndView view = new ModelAndView("Login");
		return view;
	}
	
}
