$(document).ready(function(){
	var input = $("#email");
	var APIKey_mailboxlayer = "4e4a68c1aeb47e4de0c0d60a904749c5";
	
	//disable submit button until email is proven available
	$('#signup').prop('disabled', true); //TO DISABLED $('.enableOnInput').prop('disabled', false); //TO ENABLE
	document.getElementById("signupbtn").style.visibility="hidden";
	//check for email availability
	$(".Availability").click(function(){
		
		var email = input.val(); //val() returns the value of the attribute selected
		var emailValid = false;
		console.log("starting API with email: " + email);
		$.ajax({
			// need 'http://' in the beginning or else internet browser will complain. Need to use http protocol to talk
			// need '/api/ at the end because .com will be interpreted literally
			url: "http://ec2-13-59-193-16.us-east-2.compute.amazonaws.com:8080/Valhalla/post-validate-email/" + email + "/api",
			//url: "http://localhost:8080/FakeFaceBook/post-validate-email/" + email+"/api", 
			type: "GET",
			dataType: "json",
			success: function(result){
				console.log(result);
				
				//enable submit button if email is available
				if(result == true && $("#firstName").val() && $("#lastName").val() && $("#password").val() ){
					console.log("ajax request done");
					emailValid = true;
					//$('#signup').prop('disabled', false);
				}
				$.ajax({
					url: "http://apilayer.net/api/check?access_key=" + APIKey_mailboxlayer + 
						 "&email=" + email +
						 "&smtp=1&format=1",
					dataType: "json",
					success: function(result){
						console.log("Email SMTP result is: " + result["smtp_check"]);
						console.log("emailValid: " + emailValid);
						if(result["smtp_check"] == true && emailValid == true){
							$('#signup').prop('disabled', false);
							document.getElementById("signupbtn").style.visibility="visible";
							document.getElementById("table2").insertAdjacentHTML('afterend', '<tr> <td>email is available</td> </tr>');
						}
						else{
							$('#signup').prop('disabled', true);
							document.getElementById("signupbtn").style.visibility="hidden";

							document.getElementById("table2").insertAdjacentHTML('afterend', '<tr> <td>email is not available</td> </tr>');
						}
					},
					Error: function(){
						console.log('Error: 505 - bad connection - mailboxlayer API attempt')
					}
				});
			},
			Error: function(){
	        console.log('Error: 505 - bad connection');
	      }
			});
		
		// API MAILBOX IS FINISHING BEFORE TOP REQUEST. NEED TO WAIT
		
		//Checking for email authenticity
		
	});
})