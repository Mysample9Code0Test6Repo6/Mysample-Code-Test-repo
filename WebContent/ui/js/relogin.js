// jquery initializer, do not alter.
$(document).ready(function() { document_onInit(); });

//all startup logic should exist in the init function.
function document_onInit() {	    
	// validate login from login page
    /*jQuery.validator.addMethod("notEqual", function(value, element, param) {
        return this.optional(element) || value !== "Re-enter Password";
    }, "Please provide a password");
	var lg_validator = $("#createacct_form").validate({
		rules: {
			login_pwd: {
				required: true,
				minlength: 7,
				maxlength: 20,
                notEqual:"Re-enter Password"
			},
			login_email: {
				required: true,
				email: true
			}		},
		messages: {
			login_pwd: {
				required: "Please provide a password",
				minlength: "Password must be at least 7 characters",
   			    maxlength:"Password must range between 7 and 20"
			},
			login_email: "Please enter a valid email address"
		}
	});*/

	// validate login form on keyup and submit
	var fgtPwd_validator = $("#createacct_form").validate({
		rules: {
			user_email: {
				required: true,
				email: true
			}		},
		messages: {
			user_email: "Please enter a valid email address"		}
	}); 	
	
	$('#login_acct').click(function(){
    	//lg_validator.form();
		var emailValid = LogEmail();
        var passwordValid = LogPass();
        if ( emailValid && passwordValid) {
            loginUserAccount();
        }
     });

	$('#req_pwd').click(function(){
    	fgtPwd_validator.form();
    	requestForgotPwd();
    });
	$('#fgt_pwd').click(function(){window.location = "login/fgtpwd.cyt";});
	$('#home').click(function(){window.location = "../welcome.cyt";	});
	$('#login').click(function(){window.location = "../login.cyt";	});

    setDefaultFields();
}

function setDefaultFields(){	
	$("#fgtPwd_error").css("display", "none");
	$("#complete_fgtPwd_msg").css("display", "none");
}

function loginUserAccount() {
    if ($("#createacct_form").valid()) {
        //Process our Form Submission with jQuery AJAX Function
        $("#createacct_form").ajaxSubmit({
            type:'POST',
            url:'login/submit.cyt',
            success:function (data) {
                if(data == 'Deactivated'){
                     $("#loginError").css("display", "none");
                	 $("#loginErrorForDeactAcct").css("display", "");
                }else if (data == 'resetPassword') {
                    window.location = "resetPassword.cyt";
                } else if (data == 'login') {
               	   $("#loginErrorForDeactAcct").css("display", "none");
                    $("#loginError").css("display", "");
				}else if(data == 'contacthome'){
					window.location = "contactsHome.cyt";
                } else {
                    window.location = "dash.cyt";
                }
            },
            error:function (jqXHR, textStatus, errorThrown) {
                $("#loginError").css("display", "");
            }
        });
    }
}

	function requestForgotPwd(){
		if ($("#createacct_form").valid()) {
			//Process our Form Submission with jQuery AJAX Function	
				$("#createacct_form").ajaxSubmit({	
					type: 'POST',
					url: 'fpassword.cyt',
					success: function(data) {
						
						if(data == 'success'){
							//display Message
							
							//1. hide forms
							$("#fgtPwd_error").css("display", "none");
							$("#choose_your_answers_heading").css("display", "none");
							$("#choose_your_answers").css("display", "none");
							$("#askyour_que_btn").css("display", "none");
							
							//2. display message
							$("#complete_fgtPwd_msg").css("display", "");
						}else{
							//display error message
							$("#fgtPwd_error").css("display", "");
							if (data != 'failure')
								$("#fgtPwd_error").text(data);
						}																	
					},
					error: function(jqXHR, textStatus, errorThrown) {
						window.location = "fgtpwd.cyt";	
					}
				});
		 }
	}

