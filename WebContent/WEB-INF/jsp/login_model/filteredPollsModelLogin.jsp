<!DOCTYPE html>
<%@page import="java.util.Date"%>
<html lang="en">
<head>
<meta http-equiv="content-type" content="text/html; charset=UTF-8">
<link rel="shortcut icon" href="${pageContext.request.contextPath}/favicon.ico" />

<meta http-equiv="content-type" content="text/html; charset=UTF-8">
<link rel="shortcut icon" href="favicon.ico" />
<meta charset="utf-8">
<link rel="stylesheet" href="${pageContext.request.contextPath}/ui/css/reset.css">
<link rel="stylesheet"
      href="${pageContext.request.contextPath}/ui/css/layout.css">
<link rel="stylesheet"
      href="${pageContext.request.contextPath}/ui/css/buttons.css"
      media="screen">
<link rel="stylesheet"
      href="${pageContext.request.contextPath}/ui/css/style_login.css">
<link rel="stylesheet"
      href="${pageContext.request.contextPath}/ui/css/accordionmenu.css"
      type="text/css">
<link rel="stylesheet"
      href="${pageContext.request.contextPath}/ui/css/icons_sprites.css">
<link rel="stylesheet"
      href="${pageContext.request.contextPath}/fonts/fonts_stylesheet.css">
<link rel="stylesheet"
      href="${pageContext.request.contextPath}/ui/css/ui.progress-bar.css"
      type="text/css" />
<link rel="stylesheet"
      href="${pageContext.request.contextPath}/ui/modal/colorbox.css">
<link rel="stylesheet"
      href="${pageContext.request.contextPath}/ui/css/prettyCheckboxes.css"
      type="text/css" media="screen">
<link rel="stylesheet"
      href="${pageContext.request.contextPath}/ui/css/form_elements.css">
<link rel="stylesheet"
      href="${pageContext.request.contextPath}/ui/css/validate/cmxform.css">
<link rel="stylesheet"
      href="${pageContext.request.contextPath}/ui/pirobox/css/style.css">
<link rel="stylesheet"
      href="${pageContext.request.contextPath}/ui/css/jquery.selectbox.css"
      type="text/css" />
<style type="text/css">
    .sbHolder {
        background: #ffffff;
        border: 1px solid #9BA8AE;
        -webkit-border-radius: 35px;
        -moz-border-radius: 35px;
        border-radius: 35px;
        -o-border-radius: 35px;
        border: 1px solid #9da6ac;
        font: 14px/15px 'bariol_regularregular', 'ie8_bariol_regular';
        color: #000;
        height: 31px;
        margin: 3px 0 10px;
        position: relative;
        width: 98%;
    }
</style>

<!--[if IE 8]>
<link href="ui/css/ie8.css" media="screen" rel="stylesheet" type="text/css"/>
<style type="text/css">
    #header_topnav_inner {
        color: #9da6ac;
        font-size: 14px;
        font-family: 'bariol_regularregular', 'ie8_bariol_regular';
        float: right;
        margin-top: 0px !important;
    }
</style>
<![endif]-->
<script>
    if (/*@cc_on!@*/false) {
        var headHTML = document.getElementsByTagName('head')[0].innerHTML;
        headHTML += '<link type="text/css" rel="stylesheet" href="ui/css/ie10.css">';
        document.getElementsByTagName('head')[0].innerHTML = headHTML;
    }
</script>

<!--[if gte IE 9]>
<link href="${pageContext.request.contextPath}/ui/css/gte_ie9.css" media="screen" rel="stylesheet" type="text/css"/>
<![endif]-->


<script
        src="${pageContext.request.contextPath}/ui/js/jquery-1.8.3.min.js"
        type="text/javascript"></script>
<script
        src="${pageContext.request.contextPath}/ui/js/customInput.jquery.js"
        type="text/javascript"></script>

<script language="JavaScript" src="${pageContext.request.contextPath}/ui/modal/colorbox/jquery.colorbox.js"></script>
<script language="JavaScript" src="${pageContext.request.contextPath}/ui/js/modelLogin.js"></script>
<script language="JavaScript" src="${pageContext.request.contextPath}/ui/js/login.js"></script>
<script language="JavaScript" type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.form.js"></script>
<script language="JavaScript" type="text/javascript" src="${pageContext.request.contextPath}/ui/js/jquery-ui.js"></script>
<script language="JavaScript" type="text/javascript" src="${pageContext.request.contextPath}/ui/js/jquery.lightbox-0.5.js"></script>
<script language="JavaScript" type="text/javascript" src="${pageContext.request.contextPath}/ui/js/validate/jquery.validate.js"></script>
<script src="${pageContext.request.contextPath}/ui/js/jquery.selectbox-0.2.js"></script>
<script type="text/javascript">
    $(function() {
        $("select").selectbox();
    });
</script>

<script language="JavaScript" src="${pageContext.request.contextPath}/ui/js/prettyCheckboxes.js" type="text/javascript" charset="utf-8"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/ui/js/jquery.orbit.js"></script>
<script type="text/javascript">
/*function validateUserName() {
    $("#signupusername_error").text("");
    var username = $("#username").val();
    if (!isSpclCharUserName(username)) {
        $("#signupusername_error")
                .text(
                " An invalid character has been entered  !@#$%^&*()+=_`~-[]';,./{ }|\":<>?\\ are not allowed");
        $("#signupusername_error").css("display", "");
    } else {
        $("#signupusername_error").css("display", "none");
    }
}
function isSpclCharUserName(username) {
    var iChars = "!@#$%^&*()+=_`~-[]\\\';,./{}|\":<>?";
    for ( var i = 0; i < username.length; i++) {
        if (iChars.indexOf(username.charAt(i)) != '-1') {
            return false;
        }
    }
    return true;
}
function validateSignUpEmailPollPage() {
    $("#signupemail_error").text("");
    var signUpEmail = $("#su_email").val();
    var iePwd = $("#pollSignupPassword").val();
    if ((signUpEmail == "Enter email address")
            || (iePwd == " Enter your password")) {
        if (loginEmail == "Enter email address") {
            $("#signupemail_error").text(
                    "Please enter a valid email address");
            $("#signupemail_error").css("display", "");
            return false;
        }
        if (iePwd == " Enter your password") {
            $("#signupPwd_error").text("Please provide a password");
            $("#signupPwd_error").css("display", "");
            return false;
        }
    }

    if (!isSpclCharEmail(signUpEmail)) {
        $("#signupemail_error").text("An invalid character has been entered /^&?+*[]{}\|`~<>%#$!=' are not allowed");
        $("#signupemail_error").css("display", "");
        return false;
    }
    $("#signupemail_error").css("display", "none");
    return true;
}
function validateFgtPwdEmail() {
    $("#fgtpwdErrmsg").css("display", "none");
    $("#fgtPwdError").css("display", "none");
    $("#fgtPwdError").text("");
    var fgtPwdEmail = $("#fgtPwdEmail").val();
    if (!isSpclCharEmail(fgtPwdEmail)) {
        $("#fgtPwdError").text("An invalid character has been entered /^&?+*[]{}\|`~<>%#$!=' are not allowed");
        $("#fgtPwdError").css("display", "");
        return false;
    }
    $("#fgtPwdError").css("display", "none");
    return true;
}

function isSpclCharEmail(su_email) {
    var iChars = "/?%'";
    for ( var i = 0; i < su_email.length; i++) {
        if (iChars.indexOf(su_email.charAt(i)) != '-1') {
            return false;
        }
    }
    return true;
}
*/

function loginProcessInModelLoginPage(category, incyyteId){
    $('#loginFrom').val(category);
    document.getElementById("fpQuesId").value = incyyteId;
 	parent.$.fn.colorbox({'href':'form#signup_modal_big_Form', 'open':true, 'inline':true});	
}

function signupPage(){
	parent.$.fn.colorbox({'href':'form#signup_modal_big_Form', 'open':true, 'inline':true});
}

function fpSubmitLogin(){
    var emailValid = validateFPEmail();
    var passwordValid = validateFPPassword();
    if (emailValid && passwordValid) {
        submitLoginForm();
    }
}


function validateFPEmail(){
  var email = $("#fpLoginEmail").val();
    var filter = /^([\w-`~!#$^&*()-+=";:,<>\.]+@([\w-`~!#$^&*()-+=";:,<>]+\.)+[\w-`~!#$^&*()-+=";:,<>]{2,4})?$/;
  	if ($.trim(email).length == 0) {
		$("#loginEmailError").text("Please enter a valid email address");
		$("#loginEmailError").css("display", "");
         return false;
	}
    if (filter.test(email)) {
            $("#loginEmailError").css("display", "none");
            return true;
    } else {
    	$("#loginEmailError").text("Please enter a valid email address");
    	$("#loginEmailError").css("display", "");
    	return false;
	}
}

function validateFPPassword(){
	var sPass = $("#fpLoginPassword").val().length;
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

function validatePassword(sPass) {
	    if((sPass < 7) || (sPass > 20)) {
        return false;
    } else {
        $("#loginPwdError").css("display", "none");
        return true;
    }
}
//Not login
function submitLoginForm() {
	var qid = document.getElementById("fpQuesId").value;
	var ans = $("input:radio[name='selectedAnswer']:checked").val();
    $("#create_loginForm").ajaxSubmit({
        type: 'POST',
        url: '../filteredvotelogin.cyt?qid=' + qid + '&ans=' + ans,
        success: function(data) {
        	if(data.indexOf("notLogin") != -1){
                var str = data;
                var data = str.substring(8);
                $('#loginemail_error').text(data);
                $('#loginemail_error').css("display","");
            } else if(data.indexOf("failure") != -1){
    			parent.$.fn.colorbox({'href':'form#errorPopUp', onClosed:closePopUp, 'open':true, 'inline':true});	
            } else {
            	$('#errorMsg').html(data);
				parent.$.fn.colorbox({'href':'form#msgPopUp',  onClosed:closePopUp, 'open':true, 'inline':true});	
            }
        },
        error: function(jqXHR, textStatus, errorThrown) {
            alert("error:" + textStatus + " exception:" + errorThrown);
        }
    });
}
function callSignup(){
    var userSignupValid = UserVal();
    var emailSignupValid = SignupEmail();
    var passwordValid = SignupModalPass();
    if ( userSignupValid && emailSignupValid && passwordValid) {

        submitSignUpForm();
    }
}
function callModalSignup(){
   // alert("inside callModalsignup");
    var emailSignupValid = fpModalEmailValidate();
   // alert("emailSignupValid:::"+emailSignupValid);
    var genderValid = fpGenderValidation();
  //  alert("genderValid::"+genderValid);
    if (emailSignupValid && genderValid) {
      //alert("inside emailSignupValid && genderValid");
        submitSignUpForm();
    }
}

function fpModalEmailValidate(){
   //alert("inside fpModalValidate");
    var sEmail = $("#fpemailPollPageSignUp").val();
    var filter = /^([\w-`~!#$^&*()-+=";:,<>\.]+@([\w-`~!#$^&*()-+=";:,<>]+\.)+[\w-`~!#$^&*()-+=";:,<>]{2,4})?$/;    
    if ($.trim(sEmail).length == 0) {
        $("#fp_email_invalid").text("Please enter a valid email address");
        $("#fp_email_invalid").css("display", "");
        return false;
    }
    if (filter.test(sEmail)) {
    	$("#fp_email_invalid").text("");
        $("#fp_email_invalid").css("display", "none");
        return true;
    }
    else {
        $("#fp_email_invalid").text("Please enter a valid email address");
        $("#fp_email_invalid").css("display", "");
        return false;
    }
}


function fpGenderValidation(){
    var gender = $("#fp_gender").val();
    if(gender == "Gender")
    {
        $("#fp_gender_invalid").text("Please select Gender");
        $("#fp_gender_invalid").css("display", "");
        return false;
    }
    else{
        $("#fp_gender_invalid").css("display", "none");
        return true;
    }

}



function submitSignUpForm() {
	var qid = document.getElementById("fpQuesId").value;
	var ans = $("input:radio[name='selectedAnswer']:checked").val();
  	 //alert("qid::"+qid);
    	// alert("ans::"+ans);
   
       // var contextVar = document.getElementById("contextPathVar").value;

        $("#signup_modal_big_Form").ajaxSubmit({
            type:'POST',
            url:'../fpcreateAcctPollPage.cyt?qid=' + qid + '&ans=' + ans,
            success:function (data) {
            	if(data.indexOf("userAlreadyExist") != -1){	
        			parent.$.fn.colorbox({'href':'form#userAlreadyExistPopUp', onClosed:closePopUp, 'open':true, 'inline':true});
        		}else if(data.indexOf("success") != -1){
        			parent.$.fn.colorbox({'href':'form#thankYouPopUp', onClosed:closePopUp, 'open':true, 'inline':true});	
	        	}else if(data.indexOf("failure") != -1){
        			parent.$.fn.colorbox({'href':'form#errorPopUp', onClosed:closePopUp, 'open':true, 'inline':true});	
	        	}else{
	        		$('#errorMsg').text(data);
    				parent.$.fn.colorbox({'href':'form#msgPopUp', onClosed:closePopUp, 'open':true, 'inline':true});	   
    			}
            },
            error:function (jqXHR, textStatus, errorThrown) {
                alert("error:" + textStatus + " exception:" + errorThrown);
            }
        });
}

function closePopUp(){
    parent.$.fn.colorbox.close();
    window.location.reload(true);
}
</script>

</head>
<body>
<%String loginFrom = (String) request.getParameter("loginFrom");%>
<input type="hidden" id="loginFrom"/>
<input type="hidden" id="contextPathVar" value="${pageContext.request.contextPath}">
<!--------Login Modal ----------->
<div style="display: none;">
    <form:form id="create_loginForm" commandName="loginForm" method="post">
        <p class="heading11">
            <a href="javascript:void(0)" style="color: #ed1c24; cursor: none;">Hooray!</a> Thanks for voting</p>
        <form:input path="login_email" id="fpLoginEmail" placeholder="Email" onKeydown="Javascript: if (event.keyCode==13 ) fpSubmitLogin();" onFocus="this.placeholder = ''" onBlur="this.placeholder = 'Email'" />
        <input type="hidden" id="fpQuesId"/>
        <span id="LoginEmailError" style="display: none; color: #C2002D;font-size:15px;margin-left: 5px;"></span>
        <div id="login_wrapper">
            <form:password path="login_pwd" id="fpLoginPassword" placeholder="Enter your password" onFocus="this.placeholder = ''" onBlur="this.placeholder = 'Enter your password'"   onKeydown="Javascript: if (event.keyCode==13 ) fpSubmitLogin();" />
            <div id="loginPwdError" style="display: none; color: #C2002D;font-size:15px;margin-left: 5px;"></div>
            <div id="innerlogin">
                <input type="button" id="create_login_btn" class="new_button" onclick="fpSubmitLogin();" value="LOG IN">
            </div>
        </div>
        <div class="new_login_txt">
            <p>
                Not a Member?<a href="javascript:void(0);" id="signup-user" onclick="javascript:signupPage();" style="color: #ed1c24;">
                View Results</a>
            </p>
        </div>
    </form:form>
</div>
<!--------End Login Modal ----------->
<!--------New SignUp Modal Big ----------->
<div style="display: none;">
    <form:form id="signup_modal_big_Form" commandName="signUpForm" method="post">
        <p class="heading11">
            <a href="javascript:void(0)" style="color: #ed1c24; cursor: none;">Hooray!</a> Thanks for voting
        </p>
        <form:input type="text" id="fpemailPollPageSignUp" path="su_email" autocomplete="off" placeholder="Email" onFocus="this.placeholder = ''" onBlur="this.placeholder = 'Email'" onkeyup="fpModalEmailValidate()" onKeydown="Javascript: if (event.keyCode==13 ) callModalSignup();" />
        <div id="fp_email_invalid" style="display: none; color: #C2002D;font-size:15px;margin-left: 5px;"></div>
        <!-- <form:input type="password"  id="passwordPollPageSignUp" path=""  placeholder="Enter your password"  onFocus="this.placeholder = ''"	onBlur="this.placeholder = 'Enter your password'" onkeyup="ModalPassValidate()" />  -->
        <div id="password_invalid" style="color: #C2002D;font-size:15px;margin-left: 5px;">&nbsp;</div>

        <form:select id="fp_gender" path="gender" onchange="GenderValidation()"  onKeydown="Javascript: if (event.keyCode==13 ) callModalSignup();" >
            <option value="Gender">Gender</option>
            <option value="Male">Male</option>
            <option value="Female">Female</option>
        </form:select>
        <div id="fp_gender_invalid" style="display: none;margin-top: -7px; color: #C2002D;font-size:15px;margin-left: 5px;"></div>
        <div class="button_wrap" style="margin-top: 20px;">
            <input type="button" onclick="javascript:callModalSignup();" class="new_button" value="SEND YOUR VOTE" />
        </div>
        <div class="new_login_txt" style="margin-top: 10px;">
            <p>
                Already a member? <a href="javascript:void(0)" style="color: #ed1c24;" id="login-user1"> Log in</a>
            </p>
        </div>
    </form:form>
</div>
<!--------End New SignUP Modal ----------->

<div style="display:none ;">
    <div id="errorAlreadyVotedPopUp"  >
        <div class="vote_confirm_txt" style="line-height: 25px;">
            <span>Oops!</span> Sorry you have already voted on this poll.
        </div>
        <div style="width: 100%;height: 25px;float: left;margin-top: 10px;margin-bottom: 20px;">
            <a class="poll_button1" style="width:100px; margin: 0 auto;" id="previewButton" onclick="javascript:closePopUp();"><span class="poll_button_red ">OK</span></a>
        </div>
    </div>
</div>

<div style="display:none ;">
    <form:form id="thankYouIncyytePopUp" >
        <div class="vote_confirm_txt" style="line-height: 25px;">
            <span>Hooray!</span> Thanks for signing up.<br><br>
            An email has been sent to activate your account.
        </div>
        <div style="width: 100%;height: 25px;float: left;margin-top: 10px;margin-bottom: 20px;">
            <a class="poll_button1" style="width:100px; margin: 0 auto;" id="previewButton" onclick="javascript:closePopUp();"><span class="poll_button_red ">OK</span></a>
        </div>
    </form:form>
</div>

<div style="display:none ;">
    <form:form id="thankYouPopUp"  >
        <div class="vote_confirm_txt" style="line-height: 25px;">
            <span>Hooray!</span> Thanks! We have now received your vote.<br><br>
            We have also sent you an email to activate your account.
        </div>
        <div style="width: 100%;height: 25px;float: left;margin-top: 10px;margin-bottom: 20px;">
            <a class="poll_button1" style="width:100px; margin: 0 auto;" id="previewButton" onclick="javascript:closePopUp();"><span class="poll_button_red ">OK</span></a>
        </div>
    </form:form>
</div>
<div style="display:none ;">
	  <form:form id="errorPopUp">
	  	<div class="vote_confirm_txt" style="line-height: 25px;">	  		
	  		An error occurred, please try again later.
        </div>
	  	
        <div style="width: 100%;height: 25px;float: left;margin-top: 10px;margin-bottom: 20px;">
            <a class="poll_button1" style="width:100px; margin: 0 auto;" id="previewButton" onclick="javascript:closePopUp();"><span class="poll_button_red ">OK</span></a>
     	</div>
     </form:form>
 </div>
 <div style="display:none ;">
	  <form:form id="msgPopUp">
	  	<div class="vote_confirm_txt" style="line-height: 25px; text-align: center;" id="errorMsg" align="center">	  		
	  		
        </div>
	  	
        <div style="width: 100%;height: 25px;float: left;margin-top: 10px;margin-bottom: 20px;">
            <a class="poll_button1" style="width:100px; margin: 0 auto;" id="previewButton" onclick="javascript:closePopUp();"><span class="poll_button_red ">OK</span></a>
     	</div>
     </form:form>
 </div> 
 <div style="display:none ;">    
     <form:form id="userAlreadyExistPopUp">
	  	<div class="vote_confirm_txt" style="line-height: 25px;">	  		
	  		A user already exists with this email address.<br>
	  		Please select a different email address or login.
        </div>
        <div style="width: 100%;height: 25px;float: left;margin-top: 10px;margin-bottom: 20px;">
            <a class="poll_button1" style="width:100px; margin: 0 auto;" id="previewButton" onclick="javascript:closePopUp();"><span class="poll_button_red ">OK</span></a>
     	</div>
     </form:form>
</div>
<script type="text/javascript" src="${pageContext.request.contextPath}/ui/js/googleanalytics.js"></script>
</body>
</html>