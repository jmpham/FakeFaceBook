<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
 
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Fake Facebook</title>
		<style>
		<%@ include file="Login.css" %>
		</style>
		<script>
		<%@ include file="Login.js" %>
		</script>
	</head>
	<body>
	
	
		<div class="title">
		Fake Facebook: by Jonathan Pham
			<form>
			<table>
				<tr> <td>Username</td> <td><input type="text"/> </td> </tr>
				<tr> <td>Password</td> <td><input type="text"/> </td> </tr>
				<tr> <td> <input type="submit" value="Login"/> </td> </tr>
			</table>
		</form>
		<h3>SIGN UP</h3>
		<form action="/FakeFaceBook/signupLogin.html" method="POST">
			<table class="signup">
				<tr> <td>FirstName</td> <td><input type="text" name="firstName" id="firstName"/> </td> <td>LastName</td> <td><input type="text" name="lastName" id="lastName"/> </td> </tr>
				<tr> <td>Email</td> <td><input type="text" name="email" id="email"> </td> <td>New Password</td> <td><input type="text" name="password" id="password"/> </td>  </tr>
				<tr> <td> <input type="submit" value="SignUp" id="signup"/> </td> </tr>
				
			</table>
			
			<div id="emailResult" >
			</div>
		</form>	
		<input class="Availability" type="submit" value="Check Availability"/>	
		</div>
	</body>
</html>