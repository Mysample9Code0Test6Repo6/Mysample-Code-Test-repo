$(function() {
	var button = $('#loginButton');
	var box = $('#loginBox');
	var form = $('#loginFormSN');
	button.removeAttr('href');
	button.mouseup(function(login) {
		box.toggle();
		button.toggleClass('active');
	});
	form.mouseup(function() {
		return false;
	});
	$(this).mouseup(function(login) {
		if (!($(login.target).parent('#loginButton').length > 0)) {
			button.removeClass('active');
			box.hide();
		}
	});
});

$(function() {
	var button = $('#loginButton1');
	var box = $('#loginBox1');
	var form = $('#loginForm');
	var action = $('#login_incyt');
	button.removeAttr('href');
	button.mouseup(function(login) {
		box.toggle();
		button.toggleClass('active');
		action.focus();
	});
	form.mouseup(function() {
		return false;
	});
	$(this).mouseup(function(login) {
		if (!($(login.target).parent('#loginButton1').length > 0)) {
			button.removeClass('active');
			box.hide();
		}
	});
});

$(function() {
	var button = $('#signupButton');
	var box = $('#signupBox');
	var form = $('#signUpForm');
	var action = $('#signup');
	button.removeAttr('href');
	button.mouseup(function(login) {
		box.toggle();
		button.toggleClass('active');
		action.focus();
	});
	form.mouseup(function() {
		return false;
	});
	$(this).mouseup(function(login) {
		if (!($(login.target).parent('#signupButton').length > 0)) {
			button.removeClass('active');
			box.hide();
		}
	});
});

$(function() {
	var button = $('#top_userName');
	var box = $('#userBox');
	var form = $('#userForm');
	button.removeAttr('href');
	button.mouseup(function(login) {
		box.toggle();
		button.toggleClass('active');
	});
	form.mouseup(function() {
		return false;
	});
	$(this).mouseup(function(login) {
		if (!($(login.target).parent('#top_userName').length > 0)) {
			button.removeClass('active');
			box.hide();
		}
	});
});

$(function() {
	var button = $('#dropBoxButton');
	var box = $('#dropBox');
	var form = $('#dropBoxContent');
	button.removeAttr('href');
	button.mouseup(function(login) {
		box.toggle();
		button.toggleClass('active');
	});
	form.mouseup(function() {
		return false;
	});
	$(this).mouseup(function(login) {
		if (!($(login.target).parent('#dropBoxButton').length > 0)) {
			button.removeClass('active');
			box.hide();
		}
	});
});


function validateLoginEmail() {
	$("#loginEmailError").text("Please enter a valid email address");
	var loginEmail = $("#login_email").val();
	var iePwd = $("#login_pwd").val();
	if ((loginEmail == "Enter email address")
			|| (iePwd == " Enter your password")) {
		if (loginEmail == "Enter email address") {
			$("#loginEmailError").text("Please enter a valid email address");
			$("#loginEmailError").css("display", "");
			return false;
		}
		if (iePwd == " Enter your password") {
			$("#loginPwdError").text("Please provide a password");
			$("#loginPwdError").css("display", "");
			return false;
		}

	}

	if (!isSpclCharEmail(loginEmail)) {
		$("#loginEmailError").text("Please enter a valid email address");
		$("#loginEmailError").css("display", "");
		return false;
	}
	$("#loginEmailError").css("display", "none");
	return true;
}

$(document).ready(
		function() {
			$("#signupusername_error").css("display", "none");
			$("#signupemail_error").css("display", "none");
			$("#loginEmailError").css("display", "none");
			// validate signup form on keyup and submit
			// validate signup form on keyup and submit
			/*jQuery.validator.addMethod("usernameie", function(value, element,
					param) {
				return this.optional(element) || value !== "Username";
			}, "Please provide a password");
			jQuery.validator.addMethod("passwordie", function(value, element,
					param) {
				return this.optional(element) || value !== "Password";
			}, "Please provide a password");
			var su_validator = $("#signUpForm").validate({
				rules : {
					username : {
						required : true,
						minlength : 3,
						maxlength : 20,
						usernameie : "Username"

					},
					su_password : {
						required : true,
						minlength : 7,
						maxlength : 20,
						passwordie : "Password"
					},
					su_email : {
						required : true,
						email : true
					}
				},
				messages : {
					username : {
						required : "Please enter a username",
						minlength : "Username must be at least 3 characters",
						maxlength : "Username must range between 3 and 20",

					},
					su_password : {
						required : "Please provide a password",
						minlength : "Password must be at least 7 characters",
						maxlength : "Password must range between 7 and 20"
					},
					su_email : "Please enter a valid email address"
				}
			});*/

			$("#signup").click(
					function() {
						//su_validator.form();
						var userSignupValid = UserVal();
                        var emailSignupValid = SignupEmail();
                        var passwordSignValid = SignupPass();

						var nameError = $("#signupusername_error").text();
						var usernameIE = $("#username").val();
						var su_emailIE = $("#su_email").val();
						var su_passwordIE = $("#su_password").val();
						if ((usernameIE == "Username")
								|| (su_emailIE == "Email")
								|| (su_passwordIE == "Password")) {
							if (usernameIE == "Username") {
								$("#signupusername_error").text(
										"Please enter a username");
								$("#signupusername_error").css("display", "");
								return false;
							}
							if (su_emailIE == "Email") {
								$("#signupemail_error").text(
										"Please enter a valid email address");
								$("#signupemail_error").css("display", "");
								return false;
							}
							if (su_passwordIE == "Password") {
								$("#signupPWD_error").text(
										"Please provide a password");
								$("#signupPWD_error").css("display", "");
								return false;
							}
						}
                            if ( userSignupValid && emailSignupValid && passwordSignValid) {
                                signUpUser();
						      }
					});
			$("#signup").keyup(function(event) {
				if (event.keyCode == 13) {
					$("#signup").click();
				}
			});
			$("#su_password").change(function() {
				$("#signup").focus();
			});

			// validate login form on Home page header
			/*var header_login_validator = $("#loginForm").validate({
				rules : {
					login_pwd : {
						required : true,
						minlength : 7,
						maxlength : 20
					},
					login_email : {
						required : true,
						email : true
					}
				},
				messages : {
					login_pwd : {
						required : "Please provide a password",
						minlength : "Password must be at least 7 characters",
						maxlength : "Password must range between 7 and 20"
					},
					login_email : "Please enter a valid email address"
				}
			});*/

			$('#login_incyt').click(function() {
				var emailValid = LogEmail();
				var passwordValid = LogPass();
                if (emailValid && passwordValid) {
				    logInFromHeader();
                }
			});
			$("#login_incyt").keyup(function(event) {
				if (event.keyCode == 13) {
					$("#login_incyt").click();
				}
			});

			$("#login_pwd").change(function() {
				$("#login_incyt").focus();
			});
			/* this is code for login incyyte page */
			$('#fgt_pwd').click(function() {
				window.location = "login/fgtpwd.cyt";
			});
		});

function signUpUser() {
	if ($("#signUpForm").valid()) {
		// Process our Form Submission with jQuery AJAX Function
		$("#signUpForm").ajaxSubmit({
			type : 'POST',
			url : 'signup/new_account.cyt',
			success : function(data) {
				window.location = data + ".cyt";
			},
			error : function(jqXHR, textStatus, errorThrown) {
				alert("error:" + textStatus + " exception:" + errorThrown);
			}
		});
	}
}

function logInFromHeader() {
	if ($("#loginForm").valid()) {
		// Process our Form Submission with jQuery AJAX Function
		$("#loginForm").ajaxSubmit({
			type : 'POST',
			url : 'login/submit.cyt',
			success : function(data) {
				if (data == 'resetPassword') {
					window.location = "resetPassword.cyt";
				} else if (data == 'login') {
					window.location = "login.cyt";
				} else if (data == 'Deactivated') {
					window.location = "login.cyt";
				} else  {
					window.location = "dash.cyt";
				}
			},
			error : function(jqXHR, textStatus, errorThrown) {
				window.location = "login.cyt";
			}
		});
	}
}
function HomeLoginVal(){
	var LogEmail = $("#isSpclCharEmail").val;
}
/* Login PopUp E-mail and Password validation  */
function validateEmail(sEmail) {
    var filter = /^([\w-`~!#$^&*()-+=";:,<>\.]+@([\w-`~!#$^&*()-+=";:,<>]+\.)+[\w-`~!#$^&*()-+=";:,<>]{2,4})?$/;
    if (filter.test(sEmail)) {
    	$("#loginEmailError").css("display", "none");
        return true;
    }
    else {
        return false;
    }
}
function LogEmail(){
	var sEmail = $("#login_email").val();
	var filter = /^([\w-`~!#$^&*()-+=";:,<>\.]+@([\w-`~!#$^&*()-+=";:,<>]+\.)+[\w-`~!#$^&*()-+=";:,<>]{2,4})?$/;
	if ($.trim(sEmail).length == 0) {
		$("#loginEmailError").text("Please enter a valid email address");
		$("#loginEmailError").css("display", "");
        return false;
    }
    if (filter.test(sEmail)) {
        $("#loginEmailError").css("display", "none");
        return true;
    }
    else {
        $("#loginEmailError").text("Please enter a valid email address");
        $("#loginEmailError").css("display", "");
        return false;
    }
}
function validatePassword(sPass) {
    if((sPass < 7) || (sPass > 20)) {
        return false;
    } else {
        $("#loginPwdError").css("display", "none");
        return true;
    }
}

function LogPass(){
	var sPass = $("#login_pwd").val().length;
	if ($.trim(sPass) == 0) {
		$("#loginPwdError").text("Please Provide Password");
		$("#loginPwdError").css("display", "");
		return false;
	}
	if (validatePassword(sPass)) {
	    $("#loginPwdError").css("display", "none");
        return true;
    } else {
        $("#loginPwdError").text("Password between 7 to 20 characters");
    	$("#loginPwdError").css("display", "");
        return false;
    }
}

/* -------------------Sign Up Popup Validation---------------------------- */
function validateUserName(sUser) {
	 var iChars = "!@#$%^&*() +=_`~-[]\\\';,./{}|\":<>?";
		    for (var i = 0; i < sUser.length; i++) {
	        if (iChars.indexOf(sUser.charAt(i)) != '-1') {
	            return false;
	        }
	    }
	    return true;
	}
function UserVal(){
	var sUser = $("#username").val();
	if ($.trim(sUser.length) == 0) {
		$("#signupusername_error").text("Please enter a Username");
		$("#signupusername_error").css("display", "");
	    return false;
    }
	if((sUser.length < 4) || (sUser.length > 20)){
		$("#signupusername_error").text("User Name Between 3 to 20 characters");
		$("#signupusername_error").css("display", "");
            return false;
		
	}
	if (validateUserName(sUser)) {
		$("#signupusername_error").css("display", "none");
        return true;
    }
	else {
		$("#signupusername_error")
		.text(
				" An invalid character has been entered  !@#$%^&*()+=_`~-[]';,./{ }|\":<>?\\ are not allowed");
	     $("#signupusername_error").css("display", "");
        return false;
    }
}

function SignupEmail(){
	var sEmail = $("#su_email").val();
    var filter = /^([\w-`~!#$^&*()-+=";:,<>\.]+@([\w-`~!#$^&*()-+=";:,<>]+\.)+[\w-`~!#$^&*()-+=";:,<>]{2,4})?$/;
  	if ($.trim(sEmail).length == 0) {
		$("#signupemail_error").text("Please enter a valid email address");
		$("#signupemail_error").css("display", "");
         return false;
	}
    if (filter.test(sEmail)) {
            $("#signupemail_error").css("display", "none");
            return true;
        } else {
                $("#signupemail_error").text("Please enter a valid email address");
    			$("#signupemail_error").css("display", "");
                 return false;
		        }
}

function SignupPass(){
	var sPass = $("#su_password").val().length;
	if ($.trim(sPass) == 0) {
		$("#signupPWD_error").text("Please Provide Password");
		$("#signupPWD_error").css("display", "");
            return false;
		        }
            if((sPass < 7) || (sPass > 20))
            {
                $("#signupPWD_error").text("Password Betwwen 7 to 20 characters");
                $("#signupPWD_error").css("display", "");
                return false;
            }
	       else {
	        	$("#signupPWD_error").css("display", "none");
                 return true;
		        }

}
function SignupModalPass() {

    var sPass = $("#pollSignupPassword").val().length;
    if ($.trim(sPass) == 0) {
        $("#signupPWD_error").text("Please Provide Password");
        $("#signupPWD_error").css("display", "");
        return false;
    }
    if((sPass < 7) || (sPass > 20))
    {
        $("#signupPWD_error").text("Password Betwwen 7 to 20 characters");
        $("#signupPWD_error").css("display", "");
        return false;
    }
    else {
        $("#signupPWD_error").css("display", "none");
        return true;
    }

}

function FgtPassModalEmail(){
	var sEmail = $("#user_email").val();
	if ($.trim(sEmail).length == 0) {
		$("#loginEmailError").text("Please enter a valid email address");
		$("#loginEmailError").css("display", "");
            return false;
		        }
	        if (validateEmail(sEmail)) {
	        	$("#loginEmailError").css("display", "none");
                return true;
	        }
            else {
            	$("#loginEmailError").text("Please enter a valid email address");
    			$("#loginEmailError").css("display", "");
                return false;
		        }
}
function ModalUservalidate(){
    var sUser = $("#usernamePollPageSignUp").val();
    if ($.trim(sUser.length) == 0) {
        $("#user_inavalid").text("Please enter a Username");
        $("#user_inavalid").css("display", "");
        return false;
    }
    if((sUser.length < 4) || (sUser.length > 20)){
        $("#user_inavalid").text("User Name Between 3 to 20 characters");
        $("#user_inavalid").css("display", "");
        return false;

    }
    if (validateUserName(sUser)) {
        $("#user_inavalid").css("display", "none");
        return true;
    }
    else {
        $("#user_inavalid")
            .text(
            " An invalid character has been entered  !@#$%^&*()+=_`~-[]';,./{ }|\":<>?\\ are not allowed");
        $("#user_inavalid").css("display", "");
        return false;
    }
}
function ModalEmailValidate(){
    var sEmail = $("#emailPollPageSignUp").val();
    var filter = /^([\w-`~!#$^&*()-+=";:,<>\.]+@([\w-`~!#$^&*()-+=";:,<>]+\.)+[\w-`~!#$^&*()-+=";:,<>]{2,4})?$/;    
    if ($.trim(sEmail).length == 0) {
        $("#email_invalid").text("Please enter a valid email address");
        $("#email_invalid").css("display", "");
        return false;
    }
    if (filter.test(sEmail)) {
    	$("#email_invalid").text("");
        $("#email_invalid").css("display", "none");
        return true;
    }
    else {
        $("#email_invalid").text("Please enter a valid email address");
        $("#email_invalid").css("display", "");
        return false;
    }
}
function ModalPassValidate(){
    var sPass = $("#passwordPollPageSignUp").val().length;
    if ($.trim(sPass) == 0) {
        $("#password_invalid").text("Please Provide Password");
        $("#password_invalid").css("display", "");
        return false;
    }
    if((sPass < 7) || (sPass > 20))
    {
        $("#password_invalid").text("Password Betwwen 7 to 20 characters");
        $("#password_invalid").css("display", "");
        return false;
    }
    else {
        $("#password_invalid").css("display", "none");
        return true;
    }

}
function GenderValidation(){
    var gender = $("#gender").val();
    if(gender == "Gender")
    {
        $("#gender_invalid").text("Please select Gender");
        $("#gender_invalid").css("display", "");
        return false;
    }
    else{
        $("#gender_invalid").css("display", "none");
        return true;
    }

}