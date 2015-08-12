// jquery initializer, do not alter.
$(document).ready(function() { document_onInit(); });

//all startup logic should exist in the init function.
function document_onInit()
{	    
	/*
	var samePwd;
	jQuery.validator.addMethod("confirmPwd", function(value, element) {
	    samePwd = ($('#newPwd').val != value)?true : false;
	    return samePwd;
	}, "Passwords do not match");
	*/
	
	// validate login form on keyup and submit
    jQuery.validator.addMethod("CurrentPass", function(value, element, param) {
        return this.optional(element) || value !== "Enter your password";
    }, "Please enter your Current password");
    jQuery.validator.addMethod("NewPass", function(value, element, param) {
        return this.optional(element) || value !== "Enter New Password";
    }, "Please enter your new password");
    jQuery.validator.addMethod("ConfmNewPass", function(value, element, param) {
        return this.optional(element) || value !== "Confirm Password";
    }, "Please Re-enter your new password");
	var resetPwd_validator = $("#createacct_form").validate({
		rules: {
			login_pwd: {
				required: true,
				minlength: 7,
				maxlength: 20,
                CurrentPass:""
			},
			su_password: {
				required: true,
				minlength: 7,
				maxlength: 20,
                NewPass:""
			},
			confirmpassword: {
				required: true,
				equalTo: "#newPwd",
				minlength: 7,				
				maxlength: 20,
                ConfmNewPass:""
			}
		},
		messages: {
			login_pwd:{ 
				required:"Please enter your Current password",
				minlength:"password must not be lesser than 7 characters long",
				maxlength:"You have exceeded the password allowed limit"
					},		
			su_password:{ 
				required:"Please enter your new password",
				minlength:"password must not be lesser than 7 characters long",
				maxlength:"You have exceeded the password allowed limit"
			},		
			confirmpassword:{
				required:"Please Re-enter your new password",
				equalTo:"Passwords do not match",
				minlength:"password must not be lesser than 7 characters long",
				maxlength:"You have exceeded the password allowed limit"
			} 	
		}
	}); 	

	$('#resetPwd').click(function(){
		resetPwd_validator.form();
    	requestPwd();
    });

    setDefaultFields();
}

function setDefaultFields(){	
	$("#invalidPwd_error").css("display", "none");
}
	
	function requestPwd(){
		if ($("#createacct_form").valid()) {
			//Process our Form Submission with jQuery AJAX Function	
				$("#createacct_form").ajaxSubmit({	
					type: 'POST',
					url: 'resetPassword/submit.cyt',
					success: function(data) {
						
						if(data == 'success'){
							window.location = "dash.cyt";
						}else{
							//display error message
							$("#invalidPwd_error").css("display", "");
						}																	
					},
					error: function(jqXHR, textStatus, errorThrown) {
						window.location = "fgtpwd.cyt";	
					}
				});
		 }
	}

