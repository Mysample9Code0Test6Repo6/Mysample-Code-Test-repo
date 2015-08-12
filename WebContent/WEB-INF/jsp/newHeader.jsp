<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!--[if IE 9]>			<html class="ie ie9"> <![endif]-->
<!--[if gt IE 9]><!-->	<html> <!--<![endif]-->
<head>
    <!-- Basic -->
    <meta charset="utf-8">
    <title>inCyyte - Social Polling</title>
    <meta name="keywords" content="InCyyte, Social Polling" />
    <meta name="description" content="InCyyte, Social Polling">
    <meta name="author" content="InCyyte">

    <!-- Favicons -->
    <link rel="shortcut icon" type='image/x-icon' href="img/favicon/favicon.ico">
    <link rel="apple-touch-icon-precomposed" sizes="144x144" href="apple-touch-icon-144x144-precomposed.png">
    <link rel="apple-touch-icon-precomposed" sizes="114x114" href="apple-touch-icon-114x114-precomposed.png">
    <link rel="apple-touch-icon-precomposed" sizes="72x72" href="apple-touch-icon-72x72-precomposed.png">
    <link rel="apple-touch-icon-precomposed" href="apple-touch-icon-precomposed.png">

    <!-- Mobile Metas -->
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <!-- Bootstrap CSS -->
     <link rel="stylesheet" href="css/newwelcome/bootstrap-header.css" />

    <!-- AWESOME and ICOMOON fonts -->
    <link rel="stylesheet" href="css/newwelcome/font-awesome.css">
    <link rel="stylesheet" href="css/newwelcome/style.css">

    <!-- Open Sans fonts -->
    <link rel="stylesheet"  href="http://fonts.googleapis.com/css?family=Open+Sans:300italic,400italic,600italic,700italic,800italic,400,300,600,700,800">

    <!-- Theme CSS -->
    <link rel="stylesheet" href="css/newwelcome/settings.css">
     <link rel="stylesheet" href="css/newwelcome/theme-header.css">
    <link rel="stylesheet" href="css/newwelcome/theme-elements.css">
    <link rel="stylesheet" href="css/newwelcome/teal.css">
    <link rel="stylesheet" href="css/newwelcome/animate.css">

    <link rel="stylesheet" href="css/newwelcome/styles.css">

    <!-- Modernizr -->
    <script type="text/javascript" src="js/NewWelcomejs/modernizr.js"></script>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"><style type="text/css">
    <!--
    body {
        background-image: url(/ui/images/bg_pattern.png);
    }
    -->
</style>

<!-- jQuery & Helper library -->
<script type="text/javascript" src="js/NewWelcomejs/jquery-1.10.2.min.js"></script>
<script type="text/javascript">

function filteredPolls(search) {
	window.location = "filteredPolls.cyt?search="+search;
	event.preventDefault();
};

function getPollsBycategory(category) {
	window.location = "filteredPolls.cyt?category="+category;
	event.preventDefault();
};

function socialMediaLogin(sn) {
    window.open("socialauth.do?id="+sn+"", "loginWindow", "location=1,status=1,scrollbars=1, width=600,height=600");
}

/* Login Validations */

function MailTest(){
    var sEmail = $("#login-username").val();
    var filter = /^([\w-`~!#$^&*()-+=";:,<>\.]+@([\w-`~!#$^&*()-+=";:,<>]+\.)+[\w-`~!#$^&*()-+=";:,<>]{2,4})?$/;
    if ($.trim(sEmail).length == 0) {
        $("#mailtesterror").text("Please enter a valid email address");
        $("#mailtesterror").css("display", "");
        return false;
    }
    if (filter.test(sEmail)) {
    	$("#mailtesterror").text("Please enter a valid email address");
        $("#mailtesterror").css("display", "");
        return true ;
    }
    else {
        $("#mailtesterror").text("Please enter a valid email address");
        $("#mailtesterror").css("display", "");
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

function PassTest(){
    var sPass = $("#login-password").val().length;
    if ($.trim(sPass) == 0) {
        $("#passtesterror").text("Please Provide Password");
        $("#passtesterror").css("display", "");
        return false;
    }
    if (validatePassword(sPass)) {
        $("#passtesterror").css("display", "none");
        return true;
    } else {
        $("#passtesterror").text("Password between 7 to 20 characters");
        $("#passtesterror").css("display", "");
        return false;
    }
}

/* Login Validations End */
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
function User_validation(){
    var sUser = $("#signup-username").val();
    if ($.trim(sUser.length) == 0) {
        $("#signupusername_error1").text("Please enter a Username");
        $("#signupusername_error1").css("display", "");
        return false;
    }
    if((sUser.length < 4) || (sUser.length > 20)){
        $("#signupusername_error1").text("User Name Between 3 to 20 characters");
        $("#signupusername_error1").css("display", "");
        return false;
    }
    if (validateUserName(sUser)) {
        $("#signupusername_error1").css("display", "none");
        return true;
    }
    else {
        $("#signupusername_error1")
                .text(
                " An invalid character has been entered  !@#$%^&*()+=_`~-[]';,./{ }|\":<>?\\ are not allowed");
        $("#signupusername_error1").css("display", "");
        return false;
    }
}

function SignupEmail_validate(){
    var sEmail = $("#signup-email").val();
    var filter = /^([\w-`~!#$^&*()-+=";:,<>\.]+@([\w-`~!#$^&*()-+=";:,<>]+\.)+[\w-`~!#$^&*()-+=";:,<>]{2,4})?$/;
    if ($.trim(sEmail).length == 0) {
        $("#signupemail_error1").text("Please enter a valid email address");
        $("#signupemail_error1").css("display", "");
        return false;
    }
    if (filter.test(sEmail)) {
        $("#signupemail_error1").css("display", "none");
        return true;
    } else {
        $("#signupemail_error1").text("Please enter a valid email address");
        $("#signupemail_error1").css("display", "");
        return false;
    }
}

function SignupPass_validate(){
    var sPass = $("#signup-password").val().length;
    if ($.trim(sPass) == 0) {
        $("#signupPWD_error1").text("Please Provide Password");
        $("#signupPWD_error1").css("display", "");
        return false;
    }
    if((sPass < 7) || (sPass > 20))
    {
        $("#signupPWD_error1").text("Password Betwwen 7 to 20 characters");
        $("#signupPWD_error1").css("display", "");
        return false;
    }
    else {
        $("#signupPWD_error1").css("display", "none");
        return true;
    }
}

function submitLogin() {
    var loginMailValid = MailTest();
    var loginPassValid = PassTest();
    if ( loginMailValid && loginPassValid) {
    var email = $("#login-username").val();
    var pwd = $("#login-password").val();
    jQuery.ajax({
        url: '/login/authenticateLogin.cyt?email=' + email + '&pwd=' + pwd,
        method: 'POST',
        data: $('#login').serialize()
    }).done(function (response) {
        if (response == 'resetPassword') {
            window.location = "resetPassword.cyt";
        } else if (response == 'login') {
            window.location = "login.cyt";
        } else if (response == 'Deactivated') {
            window.location = "login.cyt";
        } else  {
            window.location = "dash.cyt";
        }
    }).fail(function () {
        window.location = "login.cyt";
    });
    }
}

function createNewAccount() {
    var userSignupValid = User_validation();
    var emailSignupValid = SignupEmail_validate();
    var passwordSignValid = SignupPass_validate();
    if ( userSignupValid && emailSignupValid && passwordSignValid) {
     var user = $("#signup-username").val();
    var pwd = $("#signup-password").val();
    var email = $("#signup-email").val();
    jQuery.ajax({
        url: 'signup/authenticateSignup.cyt?user=' + user + '&pwd=' + pwd  + '&email=' + email,
        method: 'POST',
        data: $('#createAcct_form').serialize()
    }).done(function (response) {
        window.location = "createAcct.cyt";
    }).fail(function () {
        alert("error:" + textStatus + " exception:" + errorThrown);
        window.location = "createAcct.cyt";
    });
    }
}

</script>	
</head>
<body class="index">
<header id="header">
<aside class="topbar">
    <div class="container">
        <!--[PC LOG IN AND REGISTER BUTTONS]-->
        <ul class="social">
		    <li><a href="javascript:socialMediaLogin('facebook')" class="facebook" title="Log in with Facebook"></a></li>
		</ul><!-- .social -->
        <ul class="social">
		    <li><a></a></li>
		</ul><!-- .social -->
		<ul class="user-nav">
		    <li><a href="#login-register" data-toggle="modal" title="Log in" class="btn" style="font: 14px/1em 'bariol_boldbold', 'ie8_bariol_bold';">Log in</a></li>&nbsp;&nbsp;
			<li><a href="#login-register" data-toggle="modal" title="Get on inCyyte!" class="btn" style="font: 14px/1em 'bariol_boldbold', 'ie8_bariol_bold';">Join for free</a></li>
		</ul><!-- .user-nav -->
        <!--[PC LOG IN AND REGISTER BUTTONS - ENDS]-->
    </div><!-- .container -->
</aside><!-- .topbar -->
<div class="navbar">
<div class="container">
<aside id="main-search">
    <form id="searchForm" name="searchForm" method="get">
        <a href="#" class="close"><i class="icomoon-close"></i></a>
        <div class="form-field">
            <div class="placeholder">
                <label for="query" style="font: 20px/1em 'bariol_boldbold', 'ie8_bariol_bold'; margin-top: 11px;">search polls on inCyyte...</label>
                <input class="form-control" type="text" name="query" id="query" onKeydown="Javascript: if (event.keyCode==13 )  filteredPolls(this.value);"/>
            </div>
        </div>
    </form>
</aside><!-- #main-search -->

<!--[TOP SEARCH FOR POLLS BAR - ENDS]-->
<div class="navbar-inner">
<!--[INCYYTE LOGO ON NAVBAR]-->

<a href="welcome.cyt" class="logo">
    <img src="ui/images/logo.png" alt="INCYYTE">
</a><!-- .logo -->

<!--[INCYYTE LOGO ON NAVBAR - ENDS]-->
<!--[MOBILE LOG IN AND REGISTER BUTTONS]-->

<ul id="mobile-menu">
    <li><a href="#login-register" data-toggle="modal" title="Log in">Log In</a></li>
    <li><a href="#login-register" data-toggle="modal" title="Create an account">Register</a></li>
    <li><a class="btn-navbar" href="#"><i class="fa fa-reorder"></i></a></li>
    <li><a class="btn-search" href="#"><i class="fa fa-search"></i></a></li>
</ul><!-- #mobile-menu -->

<!--[MOBILE LOG IN AND REGISTER BUTTONS - ENDS]-->
<!--[MENU HOME BUTTON]-->
<ul id="main-menu" class="nav slide megamenu-width  carret">
<li ><a href="welcome.cyt"  style="font: 15px/1em 'bariol_boldbold', 'ie8_bariol_bold';">Home<span style="font: 12px'bariol_regularregular','ie8_bariol_regular';font-style: italic;">Back to top</span></a></li>
<!--[MENU HOME BUTTON - ENDS]-->

<!--[MENU VIDEO BUTTON AND BOX]-->
<li class="megamenu"><a href="incyyteVideoPage.cyt" style="font: 15px/1em 'bariol_boldbold', 'ie8_bariol_bold';">Video<span style="font: 12px'bariol_regularregular','ie8_bariol_regular';font-style: italic;">Promotional video</span></a>
    <ul class="dropdown megamenu-category">
        <li>
            <div class="row">
                <div class="col-md-3">

                    <nav class="category-nav">
                        <ul>
                            <li><a href="incyyteVideoPage.cyt" style="font: 15px/1em 'bariol_regularregular','ie8_bariol_regular';"">Find out more about inCyyte by watching our video.</a></li>
                        </ul>
                    </nav><!-- .category-nav" -->
                </div><!-- .col-md-3 -->
                <div class="col-md-9">

                    <div class="category-content">
                        <div class="current">

                            <div class="title">
                                <h4 style="font:30px/1em 'bariol_thinregular', 'ie8_bariol_thin';">InCyyte <strong style="font: 1em/1em 'bariol_boldbold', 'ie8_bariol_bold';"> Promotional Video</strong></h4>
                            </div><!-- .title -->

                            <div class="text">
                                <p style="font: 14px 'bariol_regularregular','ie8_bariol_regular';" ><i>Learn more about how inCyyte can help you. Watch our promotional video now on youtube</i></p>

                                <a href="incyyteVideoPage.cyt">
                                    <img src="/ui/images/new_tour_video.png"></a>

                            </div><!-- .text -->

                            <hr />
                            <div class="row">
                                <div class="col-xs-6">

                                </div><!-- .col-xs-4 -->
                                <div class="col-xs-4">

                                </div><!-- .col-xs-4 -->
                                <div class="col-xs-4">

                                </div><!-- .col-xs-4 -->
                            </div><!-- .row -->

                            <div class="row">
                                <div class="col-xs-4">

                                </div><!-- .col-xs-4 -->
                                <div class="col-xs-4">

                                </div><!-- .col-xs-4 -->
                                <div class="col-xs-4">

                                </div><!-- .col-xs-4 -->
                            </div><!-- .row -->
                        </div>

                        <div>
                            <div class="title">

                            </div><!-- .title -->
                            <div class="text">

                            </div><!-- .text -->
                            <hr />

                            <div class="row">
                                <div class="col-xs-4">

                                </div><!-- .col-xs-4 -->
                                <div class="col-xs-4">

                                </div><!-- .col-xs-4 -->
                                <div class="col-xs-4">

                                </div><!-- .col-xs-4 -->
                            </div><!-- .row -->
                            <hr />
                            <div class="row">
                                <div class="col-xs-6">

                                </div><!-- .col-xs-6 -->
                                <div class="col-xs-6">

                                </div><!-- .col-xs-6 -->
                            </div><!-- .row -->
                        </div>
                    </div><!-- .category-content -->

                </div><!-- .col-md-9 -->
            </div>
        </li>
    </ul><!-- .megamenu-category -->
</li>

<!--[MENU VIDEO BUTTON AND BOX - ENDS]-->

<ul class="dropdown">
    <li>
        <div class="row">
            <div class="col-sm-3">

            </div><!-- .col-sm-3 -->
            <div class="col-sm-3">

            </div><!-- .col-sm-3 -->
            <div class="col-sm-3">

            </div><!-- .col-sm-3 -->
            <div class="col-sm-3">

            </div><!-- .col-sm-3 -->
        </div><!-- .row -->
        <hr />
        <div class="row">
            <div class="col-sm-4">

                <div class="widget">
                    <div class="widget-heading">

                        <div class="title title-main">
                            <!--<h5>Latest from Blog</h5>-->
                        </div>

                    </div><!-- .widget-heading -->
                    <div class="widget-content">

                        <section class="posts">
                            <article class="post post-mini post-type-text devider-top">
                                <div class="post-heading">

                                    <div class="thumbnail">

                                    </div><!-- .thumbnail -->

                                </div><!-- .post-heading -->
                                <div class="post-content">

                                    <div class="title">

                                    </div><!-- .title -->

                                </div><!-- .post-content -->

                                <div class="post-heading">

                                    <div class="thumbnail">

                                    </div><!-- .thumbnail -->

                                </div><!-- .post-heading -->
                                <div class="post-content">

                                    <div class="title">

                                    </div><!-- .title -->

                                </div><!-- .post-content -->
                            </article><!-- .post -->
                        </section>

                    </div><!-- .widget-content -->
                </div><!-- .widget -->

            </div><!-- .col-sm-4 -->
            <div class="col-sm-4">

                <div class="widget">
                    <div class="widget-heading">

                        <div class="title title-main">

                        </div><!-- .title -->

                    </div><!-- .widget-heading -->
                    <div class="widget-content">

                    </div><!-- .widget-content -->
                </div><!-- .widget -->

            </div><!-- .col-sm-4 -->
            <div class="col-sm-4">

                <div class="widget">
                    <div class="widget-heading">

                        <div class="title title-main">

                        </div><!-- .title -->

                    </div><!-- .widget-heading -->
                    <div class="widget-content">

                        <div class="text">

                        </div><!-- .text -->

                    </div><!-- .widget-content -->
                </div><!-- .widget -->

            </div><!-- .col-sm-4 -->
        </div><!-- .row -->
    </li>
</ul><!-- .megamenu -->
</li>





<li><a href="#" style="font: 15px/1em 'bariol_boldbold', 'ie8_bariol_bold';">Learn More<span style="font: 12px'bariol_regularregular','ie8_bariol_regular';font-style: italic;">The full lowdown</span></a>
    <ul class="dropdown">
        <li><a href="${pageContext.request.contextPath}/inCyyte.cyt"  style="font: 15px 'bariol_regularregular','ie8_bariol_regular';">What is inCyyte?</a></li>
        <li><a href="${pageContext.request.contextPath}/tour.cyt" style="font: 15px 'bariol_regularregular','ie8_bariol_regular';">Take the tour</a></li>
        <li><a href="${pageContext.request.contextPath}/anonymity.cyt" style="font: 15px 'bariol_regularregular','ie8_bariol_regular';">Your anonymity & inCyyte</a></li>
        <li><a href="${pageContext.request.contextPath}/redcard.cyt" style="font: 15px 'bariol_regularregular','ie8_bariol_regular';">The Red Card system</a></li>
        <li><a href="#">Alerts & Messages</a></li>
        <li><a href="#" style="font: 15px 'bariol_regularregular','ie8_bariol_regular';">Testimonials</a></li>
        <li><a href="#" style="font: 15px 'bariol_regularregular','ie8_bariol_regular';">Your Postcode</a></li>
        <li><a href="#" style="font: 15px 'bariol_regularregular','ie8_bariol_regular';">Poll result royalty payments</a></li>
        <li><a href="shortcodes-slider.html" style="font: 15px 'bariol_regularregular','ie8_bariol_regular';">Poll sharing</a></li>
        <li><a href="shortcodes-carousel.html" style="font: 15px 'bariol_regularregular','ie8_bariol_regular';">inCyyte for business</a></li>
        <li><a href="shortcodes-pricingtables.html" style="font: 15px 'bariol_regularregular','ie8_bariol_regular';">Business pricing tables</a></li>
    </ul>
</li>
<li><a href="#" style="font: 15px/1em 'bariol_boldbold', 'ie8_bariol_bold';">Tutorials<span style="font: 12px'bariol_regularregular','ie8_bariol_regular';font-style: italic;">Video tutorials on how to..</span></a>
    <ul class="dropdown">
        <li><a href="http://www.incyyte.com/incyytenew/tutorials/create_a_poll_tutorial.mp4" style="font: 15px 'bariol_regularregular','ie8_bariol_regular';">Create an inCyyte poll</a></li>
        <li><a href="#" style="font: 15px 'bariol_regularregular','ie8_bariol_regular';">Create an inCyyte poll page</a></li>
        <li><a href="#" style="font: 15px 'bariol_regularregular','ie8_bariol_regular';">Import new contacts</a></li>
        <li><a href="#" style="font: 15px 'bariol_regularregular','ie8_bariol_regular';">Create a polling group</a></li>
    </ul>
</li>
<li><a href="#" style="font: 15px/1em 'bariol_boldbold', 'ie8_bariol_bold';">Our terms<span style="font: 12px'bariol_regularregular','ie8_bariol_regular';font-style: italic;">The legal stuff</span></a>
    <ul class="dropdown">
        <li><a href="${pageContext.request.contextPath}/termsConditions.cyt" style="font: 15px 'bariol_regularregular','ie8_bariol_regular';">Terms & Conditions</a></li>
        <li><a href="${pageContext.request.contextPath}/privacy.cyt" style="font: 15px 'bariol_regularregular','ie8_bariol_regular';">Privacy Policy</a></li>
        <li><a href="#" style="font: 15px 'bariol_regularregular','ie8_bariol_regular';">Cookie Policy</a></li>
    </ul>
</li>
<li><a href="#" style="font: 15px/1em 'bariol_boldbold', 'ie8_bariol_bold';">Vote on Polls<span style="font: 12px'bariol_regularregular','ie8_bariol_regular';font-style: italic;">Poll by category</span></a>
    <ul class="dropdown">
    	<c:forEach var="poll" items="${polls}" >
     		 <li><a href="javascript:getPollsBycategory('${poll}');" rel="tag">${poll} Polls</a></li>
     	</c:forEach>
            <!--<ul class="dropdown">
                                           <li><a href="blog-default.html">Blog (default)</a></li>
                                           <li><a href="blog-thumbs.html">Blog (thumbs)</a></li>
                                           <li><a href="blog-grid.html">Blog (grid)</a></li>
                                           <li><a href="blog-single.html">Single post</a></li>
                                       </ul>-->
        
        <li><a href="#">Top Trending Polls Chart</a>
            <ul class="dropdown">
                <li><a href="/latestPolls.cyt">Top polls on inCyyte</a></li>
                <!-- <li><a href="contact-2.html">Top polls in your postcode</a></li> -->
            </ul>
        </li>
    </ul>
</li>
<li class="search-nav">
    <a href="#" class="btn-search"><i class="fa fa-search"></i></a>						</li>
</ul><!-- #main-menu -->
</div><!-- .navbar-inner -->
</div><!-- .container -->
</div>
<!-- .navbar -->
</header><!-- #header -->
<div id="header-space" style="height: 139px;"></div>

<!-- Login/Register Modal -->
<div id="login-register" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="false">
    <div class="modal-dialog container">
        <div class="row">

            <div class="modal-bg" data-dismiss="modal"></div>

            <div class="col-sm-6">

                <div class="modal-body">
                    <div class="modal-content">
                        <a href="#" type="button" class="close btn btn-dark btn-icon-close" data-dismiss="modal" aria-hidden="true"></a>
                        <div class="tab" data-animation="slide">
                            <ul class="tab-heading">
                                <li class="current"><h6><a href="#">Log in</a></h6></li>
                                <li><h6><a href="#">Register</a></h6></li>
                            </ul><!-- .tab-heading -->
                            <div class="tab-content">
                                <div class="current">
                                    <form action="" method="POST" id="login">
                                        <div class="form-field">
                                            <div class="row">
                                                <label for="login-username" class="col-sm-3" style="font-size: 13px;">Email<span class="require">*</span></label>
                                                <div class="col-sm-9">
                                                    <input class="form-control" type="text" name="login-username"  onKeyUp="MailTest()" id="login-username" placeholder="Email"  />
                                                    <span class="text-error" id="mailtesterror"></span>
                                                </div>
                                            </div>
                                        </div><!-- .form-field -->
                                        <div class="form-field">
                                            <div class="row">
                                                <label for="login-password" class="col-sm-3" style="font-size: 13px;">Password <span class="require">*</span></label>
                                                <div class="col-sm-9">
                                                    <input class="form-control" onkeyup="PassTest()" id="login-password" type="password" placeholder="Password"  />
                                                    <span class="text-error" id="passtesterror"></span>
                                                </div>
                                            </div>
                                        </div><!-- .form-field -->
                                        <div class="form-field text-right">
                                            <div class="form-group">
                                                <a href="${pageContext.request.contextPath}/login/fgtpwd.cyt" title="Reset your password" target="_self">Forgot your password?</a>
                                            </div>
                                            <div class="form-group">
                                                <input type="button" value="Login" class="btn" onclick="submitLogin();" >
                                            </div>
                                        </div><!-- .form-field -->
                                    </form>
                                </div>
                                <div>
                                    <form id="createAcct_form" action="" method="post">
                                    <div class="form-field">
                                            <div class="row">
                                                <label for="signup-username" class="col-sm-3" style="font-size: 13px;">Username<span class="require">*</span></label>
                                                <div class="col-sm-9">
                                                    <input type="text"  id="signup-username"  onkeyup="User_validation()" class="form-control" placeholder="Username" />
                                                    <span class="text-error" id="signupusername_error1"></span>
                                                </div>
                                            </div>
                                        </div><!-- .form-field -->
                                        <div class="form-field">
                                            <div class="row">
                                                <label for="signup-password" class="col-sm-3" style="font-size: 13px;">Password <span class="require">*</span></label>
                                                <div class="col-sm-9">
                                                    <input type="password" class="form-control" onkeyup="SignupPass_validate()"  id="signup-password" placeholder="Password" />
                                                    <span class="text-error" id="signupPWD_error1"></span>
                                                </div>
                                            </div>
                                        </div><!-- .form-field -->
                                        <div class="form-field">
                                            <div class="row">
                                                <label for="signup-email" class="col-sm-3" style="font-size: 13px;">Email <span class="require">*</span></label>
                                                <div class="col-sm-9">
                                                    <input type="text"  class="form-control" onkeyup="SignupEmail_validate()" id="signup-email" placeholder="Email" />
                                                    <span class="text-error"  id="signupemail_error1"></span>
                                                </div>
                                            </div>
                                        </div><!-- .form-field -->
                                        <div class="form-field">
                                            <div class="row">
                                                <div class="col-sm-9">
                                                </div>
                                            </div>
                                        </div><!-- .form-field -->
                                        <div class="form-field text-right">
                                            <input type="button" value="Register" class="btn" onclick="createNewAccount()">
                                        </div><!-- .form-field -->
                                    </form>
                                </div>
                            </div><!-- .tab-content -->
                        </div><!-- .tab -->
                    </div><!-- .modal-content -->
                </div><!-- .modal-body -->

            </div><!-- .col-sm-6 -->
        </div><!-- .row -->
    </div><!-- .modal-dialog -->
</div><!-- #login-register-modal -->

<a class="btn btn-icon-top" id="toTop" href="#"></a>
<script type="text/javascript">
    $("#login input").keypress(function(event) {
        if (event.which == 13) {
            event.preventDefault();
            submitLogin();
        }
    });
    $("#createAcct_form input").keypress(function(event) {
        if (event.which == 13) {
            event.preventDefault();
            createNewAccount();
        }
    });
</script>
</body>
</html>
