

 $(document).ready(function(){
	 var lg_validator = $("#create_loginForm").validate({
	        rules:{
	            login_pwd:{
	                required:true,
	                minlength:7,
	                maxlength:20
	            },
	            login_email:{
	                required:true,
	                email:true
	            }        },
	        messages:{
	            login_pwd:{
	                required:"Please provide a password",
	                minlength:"Password must be at least 7 characters",
	                maxlength:"Password must range between 7 and 20"
	            },
	            login_email:"Please enter a valid email address"
	        }
	    });
 });