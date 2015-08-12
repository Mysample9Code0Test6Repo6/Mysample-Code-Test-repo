<%@ include file="/WEB-INF/jsp/common/include.jsp" %>
<link rel="shortcut icon" href="ui/images/favicon.ico"/>
<link rel="stylesheet" href="ui/css/style.css"/>
<!--[if IE 8]>
<link rel="stylesheet" href="ui/css/ie8.css"/>
<![endif]-->
<script src="ui/js/home/signup_validations.js"></script>
<script type="text/javascript">

    function setPopUp(sn) {
        if (sn == 'twitter')
            window.open("socialauth.do?id=" + sn + "", "loginWindow", "location=1,status=1,scrollbars=1, width=400,height=400");
        else
            window.open("socialauth.do?id=" + sn + "", "loginWindow", "location=1,status=1,scrollbars=1, width=600,height=600");
    }
</script>
<!--header -->
<div id="header_bar">
    <div id="headerinner">
        <div id="logocontainer"><a href="./welcome.cyt" id="logo">inCyyte</a></div>
        <div id="info">
            <!-- Log in with faccebook and Twitter Starts Here -->
            <div id="loginContainer"><a href="#" id="loginButton"><span>LOG IN WITH <img src="ui/images/icon_small.png"></span><em></em></a>
                <div style="clear:both"></div>
                <div id="loginBox">
                    <form id="loginFormSN" class="arrow_box">
                        <fieldset id="body">
                            <p class="login_text">Log In</p>
                            <!--span id="notice"
                                  style="font:14px/20px 'bariol_regularregular', 'ie8_bariol_regular';color: #C2002D;">Sorry, this service is currently not available.</span-->
                            <button type="button" onclick="setPopUp('facebook');">
                                <span><img src="ui/images/facebook.png" align="absmiddle"></span>LOG IN WITH FACEBOOK
                            </button>
                            <button type="button" onclick="setPopUp('twitter');" disabled="true" class="button_disabled">
                                <span><img src="ui/images/twitter.png" align="absmiddle"></span>LOG IN WITH TWITTER
                            </button>
                        </fieldset>
                    </form>
                </div>
            </div>
            <!-- Log in with facebook and Twitter Ends Here -->
            <!-- Log in starts Here -->
            <div id="loginContainer"><a href="#" id="loginButton1"><span>LOG IN</span><em></em></a>
                <div style="clear:both"></div>
                <div id="loginBox1">
                    <form:form id="loginForm" commandName="loginForm" method="post" cssClass="arrow_box">
                        <p class="login_text">Log In</p>
                        <form:input type="email" name="login_email" path="login_email" onkeyup="LogEmail()" id="login_email" size="30" placeholder="Enter email address"/>
                                    <div id="loginEmailError" style="padding-left: 12px; display:none; color:#C2002D;"></div>
                        <div id="login_wrapper">
                            <form:password  name="login_pwd" path="login_pwd" id="login_pwd" onkeyup="LogPass()" size="30" placeholder="Enter your password"/>
                                           <div id="loginPwdError" style="padding-left: 12px; display:none; color:#C2002D;"></div>
                            <div id="innerlogin">
                                 <input type="button" id="login_incyt" class="button" value="LOG IN"> 
                            </div>
                        </div>
                        <BR>

                        <div class="login_text" style="margin:20px 0 0 8px;"><span><a href="#" id="fgt_pwd" style="text-decoration: underline;">Forgotten your
                            password?</a></span></div>
                    </form:form>
                </div>
            </div>
            <!-- Log in ends Here -->
            <div id="divider"></div>
            <!-- Sign up starts Here -->
            <div id="loginContainer"><a href="#" id="signupButton"><span>Sign Up For FREE</span><em></em></a>
                <div style="clear:both"></div>
                <div id="signupBox" style="display:block">
                    <form:form id="signUpForm" commandName="signUpForm" method="post" cssClass="arrow_box">
                        <p class="login_text01">Sign up today, It's
                            <span style="color:#cfff00; font-size:25px; font-family:bariol_boldbold, ie8_bariol_bold;">FREE!</span> <br>
                            <span>and your identity is protected!</span>
                        </p>
                        <form:input path="username" id="username" title="username" onKeyUp="UserVal()"
                                    placeholder="Username" onFocus="this.placeholder = ''"
                                    onBlur="this.placeholder = 'Enter your username'"/>
                        <div id="signupErrorDiv">
                     <span id="signupusername_error" style="padding-left: 12px; display:none;">
                     </span></div>
                        <form:input path="su_email" id="su_email" title="email address" onkeyup="SignupEmail()"
                                    placeholder="Email" onFocus="this.placeholder = ''"
                                    onBlur="this.placeholder = 'Enter your email address'"/>
                        <div id="signupEmailErrorDiv">
                     <span id="signupemail_error" style="padding-left: 12px; display:none;">
                     </span></div>
                        <div id="login_wrapper">
                            <form:password path="su_password" id="su_password" autocomplete="off" placeholder="Password" onkeyup="SignupPass()"
                                           onFocus="this.placeholder = ''"
                                           onBlur="this.placeholder = 'Enter your password'"
                                           style="padding-right:100px; width:164px;"/>
                            <div id="signupbtn">
                                <input type="button" id="signup" class="button" value="SIGN UP NOW">
                            </div>
                            <p id="signupPWD_error" style="padding-left: 12px; display:none;" ></p>
                        </div>
                        <p class="signup_text" style="margin:0 0 0 8px; padding-top:30px;"><span></span></p>

                    </form:form>
                </div>
            </div>
            <div id="point"></div>
            <!-- Sign up Ends Here -->
            <div>
                <ul>
                    <li><a href="./tour.cyt" title="" style="float:right; padding:3px 12px 0 0;"> Take The Tour </a>
                    </li>
                </ul>
            </div>
        </div>
    </div>
</div>
<!--header end-->
