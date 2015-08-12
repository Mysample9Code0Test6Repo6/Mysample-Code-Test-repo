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
function validateUserName() {
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

function validateLoginEmailPollPage() {
    $("#login_email_Error").text("");
    var loginEmail = $("#login_email").val();
    var iePwd = $("#login_pwd").val();
    if ((loginEmail == "Enter email address")
            || (iePwd == " Enter your password")) {
        if (loginEmail == "Enter email address") {
            $("#login_email_Error").text("Please enter a valid email address");
            $("#login_email_Error").css("display", "");
            return false;
        }
        if (iePwd == " Enter your password") {
            $("#login_pwd_Error").text("Please provide a password");
            $("#login_pwd_Error").css("display", "");
            return false;
        }
    }
    if (!isSpclCharEmail(loginEmail)) {
        $("#login_email_Error").text("Please enter a valid email address");
        $("#login_email_Error").css("display", "");
        return false;
    }
    $("#login_email_Error").css("display", "none");
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
function loginProceessInModelLoginPage(category){
    $('#loginFrom').val(category);
 	parent.$.fn.colorbox({'href':'form#signup_modal_big', 'open':true, 'inline':true});	
}

/*var pollPage_login_validator = $("#create_loginForm").validate({
 ignore: [],
 rules:{
 login_pwd :{
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
 });*/


function signupPage(){
    var loginFrom = $('#loginFrom').val();
    if(loginFrom == "viewUserPollPage"){
        parent.$.fn.colorbox({'href':'form#signup_modal_big', 'open':true, 'inline':true});
    }
    else{
        parent.$.fn.colorbox({'href':'form#create_signupform', 'open':true, 'inline':true});
    }
}
function forgotPwd(){
    parent.$.fn.colorbox({'href':'form#create_fgtpwdform', 'open':true, 'inline':true});
}
function loginModelLogin(){
    var emailValid = LogEmail();
    var passwordValid = LogPass();
    if ( emailValid && passwordValid) {
        submitLoginForm();
    }
}

function submitLoginForm(){
    var loginFrom = $('#loginFrom').val();

    if(loginFrom == "login_incyyte"){
        $("#create_loginForm").ajaxSubmit({
            type:'POST',
            url:'send_question/login.cyt',
            success:function (data) {
                if (data == 'success') {
                    parent.$.fn.colorbox.close();
                    window.location.reload(true);
                }else if(data == 'Deactivated'){
                    $("#loginErrorForDeactAcct").css("display", "");
                    $("#loginError").css("display", "none");
                }else if(data == 'login'){
                    $("#loginErrorForDeactAcct").css("display", "none");
                    $("#loginError").css("display", "");
                }else {
                    $("#loginErr").show('fast');
                }
            },
            error:function (jqXHR, textStatus, errorThrown) {
                $("#loginErr").show();
            }
        });
    }
    if(loginFrom == "create_incyyte"){
        $("#create_loginForm").ajaxSubmit({
            type:'POST',
            url:'send_question/login.cyt',
            success:function (data) {
                if (data == 'success') {
                    parent.$.fn.colorbox.close();
                    window.location = "dash.cyt";
                }else if(data == 'Deactivated'){
                    $("#loginErrorForDeactAcct").css("display", "");
                    $("#loginError").css("display", "none");
                }else if(data == 'login'){
                    $("#loginErrorForDeactAcct").css("display", "none");
                    $("#loginError").css("display", "");
                }else {
                    $("#loginErr").show('fast');
                }
            },
            error:function (jqXHR, textStatus, errorThrown) {
                $("#loginErr").show();
            }
        });
    }
    if(loginFrom == "viewUserPollPage"){
        $("#create_loginForm").ajaxSubmit({
            type: 'POST',
            url: '../votelogin.cyt',
            success: function(data) {
                if(data.indexOf("notLogin") != -1){
                    var str = data;
                    var data = str.substring(8);
                    $('#loginemail_error').text(data);
                    $('#loginemail_error').css("display","");
                }
               /*  else if(data.indexOf("failure") != -1){
                    parent.$.fn.colorbox({'href':'div#errorAlreadyVotedPopUp', 'open':true, 'inline':true});
                }  */
                else if(data.indexOf("failure") != -1){
        			parent.$.fn.colorbox({'href':'form#errorPopUp', onClosed:closePopUp, 'open':true, 'inline':true});	
                }
                else{
                	$('#errorMsg').html(data);
    				parent.$.fn.colorbox({'href':'form#msgPopUp',  onClosed:closePopUp, 'open':true, 'inline':true});	
                }
            },
            error: function(jqXHR, textStatus, errorThrown) {
                alert("error:" + textStatus + " exception:" + errorThrown);
            }
        });
    }
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
    // var userSignupValid = ModalUservalidate();
    var emailSignupValid = ModalEmailValidate();
    //var passwordValid = ModalPassValidate();
    var genderValid = GenderValidation();
    if (emailSignupValid && genderValid) {
        submitSignUpForm();
    }
}

function submitSignUpForm() {
    var loginFrom = $('#loginFrom').val();

    if(loginFrom == "login_incyyte"){
        var contextVar = document.getElementById("contextPathVar").value;
        $("#create_signupform").ajaxSubmit({
            type:'POST',
            url:contextVar+'/send_question/createAcct.cyt',
            success:function (data) {
                /* parent.$.fn.colorbox.close();
                 window.location.reload(true); */
                parent.$.fn.colorbox({'href':'form#thankYouIncyytePopUp', onClosed:closePopUp, 'open':true, 'inline':true});
            },
            error:function (jqXHR, textStatus, errorThrown) {
                alert("error:" + textStatus + " exception:" + errorThrown);
            }
        });
    } else if(loginFrom == "create_incyyte"){
        var contextVar = document.getElementById("contextPathVar").value;
        $("#create_signupform").ajaxSubmit({
            type:'POST',
            url:contextVar+'/send_question/createAcct.cyt',
            success:function (data) {
                parent.$.fn.colorbox.close();
                window.location = contextVar+"/createAccountConfirmation.cyt";
            },
            error:function (jqXHR, textStatus, errorThrown) {
                alert("error:" + textStatus + " exception:" + errorThrown);
            }
        });
    } else if(loginFrom == "viewUserPollPage" || loginFrom == "filteredPoll"){
        var contextVar = document.getElementById("contextPathVar").value;
        $("#signup_modal_big").ajaxSubmit({
            type:'POST',
            url:contextVar+'/createAcctPollPage.cyt',
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
        <div id="loginemail_error" class="common_error failure" style="display:none;"></div>
        <div>
            <div id="loginError" class="common_error failure" style="display:none;"><strong>Please re-enter:</strong> You have entered an incorrect email and password combination.</div>
            <div id="loginErrorForDeactAcct" class="common_error failure" style="display:none;"><strong>Your account is not activate : </strong>To activate or reactivate your account click on "REACTIVATE ACCOUNT" link in Login page</div>
        </div>
        <form:input path="login_email" id="login_email" placeholder="Email" onKeyUp="LogEmail();" onKeydown="Javascript: if (event.keyCode==13 ) loginModelLogin();" onFocus="this.placeholder = ''" onBlur="this.placeholder = 'Email'" />
        <span id="loginEmailError" style="display: none; color: #C2002D;font-size:15px;margin-left: 5px;">Invalid credentials, please try again</span>
        <div id="login_wrapper">
            <form:password path="login_pwd" id="login_pwd"
                           placeholder="Enter your password" onFocus="this.placeholder = ''"
                           onBlur="this.placeholder = 'Enter your password'" onkeyup="LogPass()"  onKeydown="Javascript: if (event.keyCode==13 ) loginModelLogin();" />
            <div id="loginPwdError" style="display: none; color: #C2002D;font-size:15px;margin-left: 5px;"></div>

            <div id="innerlogin">
                <input type="button" id="create_login_btn" class="new_button" onclick="javascript:loginModelLogin();" value="LOG IN">
            </div>
        </div>
        <div class="new_login_txt">
            <!-- <p>
                       <a href="javascript:void(0);" id="fgtpwd-user" onclick="javascript:forgotPwd();" >Forgotten your password?</a>
                   </p> -->
            <p>
                Not a Member?<a href="javascript:void(0);" id="signup-user" onclick="javascript:signupPage();" style="color: #ed1c24;">
                View Results</a>
            </p>
        </div>
    </form:form>
</div>
<!--------End Login Modal ----------->
<!--------SignUp Modal ----------->
<div style="display: none;">
    <form:form id="create_signupform" commandName="signUpForm" method="post">
        <p class="heading11">
            <a href="javascript:void(0)" style="color: #ed1c24; cursor: none;">Hooray!</a> Thanks for voting
        </p>
        <form:input path="username" id="username"
                    placeholder="Username" onFocus="this.placeholder = ''"
                    onBlur="this.placeholder = 'Username'" onKeyUp="UserVal()"/>
        <div id="signupusername_error" style="display: none; color: #C2002D;font-size:15px;margin-left: 5px;"></div>
        <form:input path="su_email" id="su_email" placeholder="Email"
                    onFocus="this.placeholder = ''"
                    onBlur="this.placeholder = 'Email'" onkeyup="SignupEmail()" />
        <div id="signupemail_error" style="display: none; color: #C2002D;font-size:15px;margin-left: 5px;"></div>

        <div id="login_wrapper">
            <form:password path="su_password" id="pollSignupPassword"
                           placeholder="Password" onFocus="this.placeholder = ''"
                           onBlur="this.placeholder = 'Password'" onkeyup="SignupModalPass()" />
            <div id="signupPWD_error" style="display: none; color: #C2002D;font-size:15px;margin-left: 5px;"></div>
            <div id="innerlogin">
                <input type="button" id="create_signup_btn"  class="new_button"  onclick="javascript:callSignup();"
                       value="SIGN UP" />
            </div>
        </div>
        <div class="new_login_txt">
            <p>
                Already a member? <a href="javascript:void(0);" style="color: #ed1c24;" id="login-user"> Log in</a>
            </p>
        </div>
    </form:form>
</div>
<!--------End SignUP Modal ----------->
<!--------New SignUp Modal Big ----------->
<div style="display: none;">
    <form:form id="signup_modal_big" commandName="signUpForm" method="post">
        <p class="heading11">
            <a href="javascript:void(0)" style="color: #ed1c24; cursor: none;">Hooray!</a> Thanks for voting
        </p>
        <form:input type="text" id="emailPollPageSignUp" path="su_email" autocomplete="off" placeholder="Email" onFocus="this.placeholder = ''" onBlur="this.placeholder = 'Email'" onkeyup="ModalEmailValidate()" onKeydown="Javascript: if (event.keyCode==13 ) callModalSignup();" />
        <div id="email_invalid" style="display: none; color: #C2002D;font-size:15px;margin-left: 5px;"></div>
        <!-- <form:input type="password"  id="passwordPollPageSignUp" path=""  placeholder="Enter your password"  onFocus="this.placeholder = ''"	onBlur="this.placeholder = 'Enter your password'" onkeyup="ModalPassValidate()" />  -->
        <div id="password_invalid" style="color: #C2002D;font-size:15px;margin-left: 5px;">&nbsp;</div>

        <form:select id="gender" path="gender" onchange="GenderValidation()"  onKeydown="Javascript: if (event.keyCode==13 ) callModalSignup();" >
            <option value="Gender">Gender</option>
            <option value="Male">Male</option>
            <option value="Female">Female</option>
        </form:select>
        <div id="gender_invalid" style="display: none;margin-top: -7px; color: #C2002D;font-size:15px;margin-left: 5px;"></div>
        <%-- <form:select id="year" path="birthYear" >
                  <c:forEach var="c" begin="1900" step="1" end="${birthYearLimit }">
                      <form:option value="${c}"><c:out value="${c}" /></form:option>
                  </c:forEach>
       </form:select> --%>

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
<!--------Forgot Password Modal ----------->
<div style="display: none;">
    <form:form id="create_fgtpwdform" commandName="loginForm"
               method="post">
        <div class="fgtpwd">
            <p class="heading1">Forgotten Password</p>
            <p class="font_16px"></p>
            <div id="fgtpwdErrmsg" class="common_error failure"
                 style="display: none;">
                <strong>Please re-enter:</strong> The email does not exist
            </div>
            <div id="login_wrapper">
                <form:input path="user_email" cssClass="error"
                            onkeyup="validateFgtPwdEmail()" id="fgtPwdEmail"
                            placeholder="Email" onFocus="this.placeholder = ''"
                            onBlur="this.placeholder = 'Email'" />
                <div id="innerlogin">
                    <input type="button" id="create_fgtpwd_btn" class="frgtpass"
                           value="SEND">
                </div>
            </div>
				<span id="fgtPwdError"
                      style="display: none; color: #C2002D; font-size: 15px;"></span>
            <br><div style="margin-top: 35px;">Enter your email address and
            your credentials will be sent to you.</div>
        </div>
        <div class="fgpwdmsg" style="display: none;">
            <p class="heading1"
               style="text-align: center; line-height: 35px; font-size: 20px;">
                Your Password has been sent.<br> Please check your email.
            </p>
            <a id="login_acc" class="button_green1" style="width: 150px;"
               href="javascript:void(0)"> <span class="title_red"
                                                style="border: none;">LOGIN</span>
            </a>

        </div>
    </form:form>
    <div id="forgotPasswordSent" title="New Password sent.">
        <p>
            <br>
            <br>
            <br> <span>Your account password has been reset. Check
					your email for details.<br>
				<br> Once logged into your account, please change your password
					in your account settings.
				</span>
        </p>
    </div>
    <div id="no_msg_sent_dialog" class="msg_box" title="No Incyyte Sent">
        <p class="heading1">No Incyyte sent </p>
        <p  ><br>
            <span style="color: #1b4d5f;font-size:23px;" > <strong>Please check your selection and try again. </strong> <br><br></span>
        </p>
        <a id="close_sent_dialog" class="button_green1" style="width:240px; margin:20px 0 0 50px; " href="javascript:void(0)">
            <span class="title_red" style="border: none;">CLOSE</span>
        </a>
    </div>
</div>
<!--------End Forgot Password Modal ----------->
<div style="display:none ;">
    <div id="errorAlreadyVotedPopUp"  >
        <div class="vote_confirm_txt" style="line-height: 25px;">
            <span>Oops!</span> Sorry you have already voted on this poll.

            <!-- <input type="button" id="create_fgtpwd_btn" class="frgtpass" value="OK"> -->
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