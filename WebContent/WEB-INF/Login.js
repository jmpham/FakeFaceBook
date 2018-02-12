$(document).ready(function(){
	var input = $("#email");
	//disable submit button until email is proven available
	$('#signup').prop('disabled', true); //TO DISABLED $('.enableOnInput').prop('disabled', false); //TO ENABLE
	
	//check for email availability
	$(".Availability").click(function(){
		var email = input.val(); //val() returns the value of the attribute selected
		console.log("starting API with email: " + email);
		$.ajax({
			// need 'http://' in the beginning or else internet browser will complain. Need to use http protocol to talk
			// need '/api/ at the end because .com will be interpreted literally
			url: "http://localhost:8080/FakeFaceBook/post-validate-email/" + email+"/api", 
			type: "GET",
			dataType: "json",
			success: function(result){
				console.log(result);
				$("#emailResult").html("email availability: " + result);
				//enable submit button if email is available
				if(result == true && $("#firstName").val() && $("#lastName").val() && $("#password").val() ){
					$('#signup').prop('disabled', false);
				}
				else{
					$('#signup').prop('disabled', true);
				}
			},
			Error: function(){
	        console.log('Error: 505 - bad connection');
	      }
			});
	});
})