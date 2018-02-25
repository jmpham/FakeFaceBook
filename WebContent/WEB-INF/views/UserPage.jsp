    <%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>User's Page</title>
		
	</head>
	<body>
	
		<div class="Banner">
			<img src="file/${user1.email}_ProfilePic.jpg" alt="imageFilefile" />
			<h1>${user1.firstName} ${user1.lastName}</h1>
			<h3>${user1.email}</h3>
			<form  action="/FakeFaceBook/uploadIMG/${user1.email}" enctype="multipart/form-data" method=POST>
				<input type="file" accept=".jpg" name="file"/>
				<input value="Upload" type="submit" />
			</form>
			<form action="/FakeFaceBook/Login.html">
				<input  value="Log Out" type="submit"/>
			</form>	
		</div>
	</body>
</html>