// jquery initializer, do not alter.
$(document).ready(function() { document_onInit(); });

//all startup logic should exist in the init function.
function document_onInit()
{	    
	$('#req_pwd').click(function(){
		var emailValid = LogEmail();
        if(emailValid) {
    	    requestForgotPwd();
        }
    });
	$('#resend_actv_email').click(function(){
        var emailValid = LogEmail();
        if(emailValid) {
    	 	resendActivateEmail();
        }
    });
	$('#fgt_pwd').click(function(){window.location = "login/fgtpwd.cyt";});
	$('#home').click(function(){window.location = "../welcome.cyt";	});
	$('#loginUser').click(function(){window.location = "../login.cyt";	});
	$('#home2').click(function(){window.location = "../welcome.cyt";	});
	$('#loginUser2').click(function(){window.location = "../login.cyt";	});

    setDefaultFields();
}

function setDefaultFields(){	
	$("#fgtPwd_error").css("display", "none");	
	$("#sendMail_error").css("display", "none");
	$("#complete_fgtPwd_msg").css("display", "none");
	$("#complete_sendMail_msg").css("display", "none");
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
							$("#choose_your_answers_login").css("display", "none");
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

	function resendActivateEmail(){
		if ($("#createacct_form").valid()) {
			//Process our Form Submission with jQuery AJAX Function
				$("#createacct_form").ajaxSubmit({
					type: 'POST',
					url: 'sendActivationEmail.cyt',
					success: function(data) {
						if(data == 'success'){
							//display Message

							//1. hide forms
							$("#fgtPwd_error").css("display", "none");
							$("#choose_your_answers_heading").css("display", "none");
							$("#choose_your_answers_login").css("display", "none");
							$("#resend_actv_email").css("display", "none");
							$("#askyour_que_btn").css("display", "none");
							
							//2. display message
							$("#complete_sendMail_msg").css("display", "");
						}else{
							//display error message
							$("#sendMail_error").css("display", "");
							if (data != 'failure')
								$("#sendMail_error").text(data);
						}																	
					},
					error: function(jqXHR, textStatus, errorThrown) {
						window.location = "fgtpwd.cyt";	
					}
				});
		 }
	}
