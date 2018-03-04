    <%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>User's Page</title>
		<link rel="stylesheet" href="/FakeFaceBook/staticResources/css/UserPage.css" type='text/css'/>
		
	</head>
	<body>
	 
		<div class="Banner">
			<img src="file/${user1.email}_BannerPic.jpg" id="bannerpic"/>
		</div>
		<div class="main">
			<img src="file/${user1.email}_ProfilePic.jpg" id="profilePic"/>
			<h1>${user1.firstName} ${user1.lastName}</h1>
			<h3>${user1.email}</h3>
			
			<!-- Need to understand why need object tags. Also, Google Chrome needs PDF Viewer Extension for this to work -->
			<object data="file/${user1.email}_Resume.pdf"  type="application/pdf" width="75%" height="750">
				<embed src="file/${user1.email}_Resume.pdf" type="application/pdf">
			</object>
		</div>
		<div id="forms">	
			<form action="/FakeFaceBook/search" method=GET>
				<input type="text" name="profile" >
				<input value="Search Profiles by Email" type="submit" class="btn">
			</form>
			<form action="/FakeFaceBook/uploadResume/" enctype="multipart/form-data" method=POST>
				<input type="file" accept=".pdf" name="resume" style="color: #e8ecf2"/>
				<input value="Upload Resume" type="submit" class="btn"/>
			</form>
			<form  action="/FakeFaceBook/uploadIMG/" enctype="multipart/form-data" method=POST>
				<input type="file" accept=".jpg" name="file" style="color: #e8ecf2"/>
				<input value="Upload Profile Picture" type="submit"  class="btn"/>
			</form>
			<form  action="/FakeFaceBook/uploadBannerPic/" enctype="multipart/form-data" method=POST>
				<input type="file" accept=".jpg" name="file" style="color: #e8ecf2"/>
				<input value="Upload Banner Picture" type="submit"  class="btn"/>
			</form>
			<form action="/FakeFaceBook/Logout">
				<input  value="Log Out" type="submit" class="btn"/>
			</form>
		</div>	
	</body>
</html>