    <%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>User's Page</title>
		
	</head>
	<body>
	
		<div class="Banner">
			<img src="file/${user2.email}_ProfilePic.jpg" alt="imageFilefile" />
			<h1>${user2.firstName} ${user2.lastName}</h1>
			<h3>${user2.email}</h3>
			<embed src="file/${user2.email}_Resume.pdf" width="500" height="375" type='application/pdf'>
			
			<form action="/FakeFaceBook/search" method=GET>
				<input type="text" name="profile" >
				<input value="Search Profiles" type="submit">
			</form>
			<form action="/FakeFaceBook/UserPage">
				<input value="Go Back to UserPage" type="submit">
			</form>
			<form action="/FakeFaceBook/Logout">
				<input  value="Log Out" type="submit"/>
			</form>	
			
		</div>
	</body>
</html>