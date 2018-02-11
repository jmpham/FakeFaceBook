$(document).ready(function(){
	
	var input = $("#email");
	//check for email availability
	$(".Availability").click(function(){
		var email = input.val();
		console.log("starting API with email: " + email);
		$.ajax({
			url: "http://localhost:8080/FakeFaceBook/post-validate-email/" + email+"/api",
			type: "GET",
			dataType: "json",
			success: function(result){
				console.log(result);
				$("#emailResult").html(result);
			},
			Error: function(){
	        console.log('Error: 505 - bad connection');
	      }
			});
	});
})