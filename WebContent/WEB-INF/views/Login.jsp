<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
 
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Fake Facebook</title>
		<link rel="stylesheet" href="/Valhalla/staticResources/css/Login.css" type='text/css'/>
		<script src="/Valhalla/staticResources/javascript/Login.js"></script>
		
	</head>
	<body>
		<div class="frontpage">
			<div class="grid-container">
  				<div class="forms">
  					<h4>SIGN IN</h4>
					<form action="/Valhalla/userPage.html" class="a">
						<table>
							<tr> <td>Email</td> <td><input type="text" name="email"/> </td> </tr>
							<tr> <td>Password</td> <td><input type="text" name="password"/> </td> </tr>
							<tr> <td> <input type="submit" value="Login" class="button"/> </td> </tr>
							<tr> <td>${user1.invalidCreds}<td></tr>
						</table>
					</form>
  				</div>
				<div class="forms">
				  	<form action="/Valhalla/signupLogin.html" method="POST" class="signup">
						<h4>SIGN UP</h4>
						<table id="table2">
							<tr> <td>FirstName</td> <td><input type="text" name="firstName" id="firstName"/> </td> <td>LastName</td> <td><input type="text" name="lastName" id="lastName"/> </td> </tr>
							<tr> <td>Email</td> <td><input type="text" name="email" id="email"> </td> <td>New Password</td> <td><input type="text" name="password" id="password"/> </td>  </tr>
							<tr> <td> <input type="submit" value="SignUp" id="signupbtn" class="button"/> </td> </tr>	
						</table>			
					</form>	
					<input class="Availability" type="submit" value="Check Availability" id="check"/>
				</div>
  				<div class="banner"><h1>V A L H A L L A</h1></div>
  				<div class="subtitle"><h6>A PLACE WHERE YOU AND YOUR FRIENDS CAN SHARE YOUR ACCOMPLISHMENTS</h6>
				<h5>By: Jonathan Pham</h5></div> 
			</div>	
		</div>
	</body>
</html>