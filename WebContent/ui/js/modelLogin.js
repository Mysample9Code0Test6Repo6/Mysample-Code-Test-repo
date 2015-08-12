$(document).ready(function () {
	
    $('#login-user').click(function(){
        parent.$.fn.colorbox({'href':'form#create_loginForm', 'open':true, 'inline':true});
    });
    $('#login-user1').click(function(){
        parent.$.fn.colorbox({'href':'form#create_loginForm', 'open':true, 'inline':true});
    });
    
    $('#single-login-user').click(function(){
        parent.$.fn.colorbox({'href':'form#singlePage_loginForm', 'open':true, 'inline':true});
    });
    
	 var create_fgtpwdform = $("#create_fgtpwdform").validate({
	        rules:{

	            user_email:{
	                required:true,
	                email:true
	            }        },
	        messages:{

	            user_email:"Please enter a valid email address"
	        }
	    });
	
	/*var poll_signupform = $("#create_signupform").validate({
	    rules:{
	        username:{
	            required:true,
	            minlength:3,
	            maxlength:20,
	            //usernameie:"Username"
	        },
	        su_password:{
	            required:true,
	            minlength:7,
	            maxlength:20,
	            //passwordie:"Password"
	        },
	        su_email:{
	            required:true,
	            email:true
	        }
	    },
	    messages:{
	        username:{
	            required:"Please enter a username",
	            minlength:"Username must be at least 3 characters",
	            maxlength:"Username must range between 3 and 20"
	        },
	        su_password:{
	            required:"Please provide a password",
	            minlength:"Password must be at least 7 characters",
	            maxlength:"Password must range between 7 and 20"
	        },
	        su_email:"Please enter a valid email address"
	    }
	});*/
	
	
/*	$('#create_signup_btn').click(function () {
	 if(poll_signupform.form()){
	    if (validateSignUpEmailPollPage()) {
	        submitSignUpForm();
	     }
		}
	});*/
	$('#create_fgtpwd_btn').click(function () {
        create_fgtpwdform.form();
		if ($('#fgtPwdEmail').val() != ''){
			submitFgtPwdPoll();
		}
	});

	/*function validateUserName() {
	    $("#signupusername_error").text("");
	    var username = $("#username").val();
	    if (!isSpclCharUserName(username)) {
	        $("#signupusername_error").text(" An invalid character has been entered  !@#$%^&*()+=_`~-[]';,./{ }|\":<>?\\ are not allowed");
	        $("#signupusername_error").css("display", "");
	    } else {
	        $("#signupusername_error").css("display", "none");
	    }
	}*/
	function submitFgtPwdPoll(){
    	
    	$("#create_fgtpwdform").ajaxSubmit({	
    		type: 'POST',
    		url: '../../votepassword.cyt',
    		success: function(data) {
    			if (data == 'success') {
                    $(".fgtpwd").css("display","none");
                    $(".fgpwdmsg").css("display","");
                    $("#login_acc").click(function(){
                        parent.$.fn.colorbox({'href':'form#create_loginForm', 'open':true, 'inline':true});
                    });
                    $("#login_back").click(function(){
                        window.location.reload(true);
                        $.fn.colorbox.close();
                        });
            	}
    			else {
    				$('#fgtpwdErrmsg').show('fast');
    			}
    		},
    		error:function (jqXHR, textStatus, errorThrown) {
    			alert("error:" + textStatus + " exception:" + errorThrown);
    		}
    	});	
    }
	
	
});