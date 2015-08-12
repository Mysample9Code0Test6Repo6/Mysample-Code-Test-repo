<%@ include file="/WEB-INF/jsp/common/include.jsp" %>
<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="content-type" content="text/html; charset=UTF-8">
<link rel="shortcut icon" href="favicon.ico" />

<title>inCyyte - Login</title>
<meta charset="utf-8">
<link rel="stylesheet" href="ui/css/reset.css">
<link rel="stylesheet" href="ui/css/style.css">
<link rel="stylesheet" href="ui/css/layout.css">
<link rel="stylesheet" href="ui/css/buttons.css"  media="screen">
<link rel="stylesheet" href="ui/css/style_login.css">
<link rel="stylesheet" href="ui/css/accordionmenu.css" type="text/css">
<link rel="stylesheet" href="ui/css/icons_sprites.css">
<link rel="stylesheet" href="fonts/fonts_stylesheet.css">
<link rel="stylesheet" href="ui/css/prettyCheckboxes.css" type="text/css" media="screen">
<link rel="stylesheet" href="ui/css/themes/base/jquery.ui.all.css">
<link rel="stylesheet" href="ui/css/jquery.validate.css">
<link rel="stylesheet" href="ui/css/validate/screen.css">


<script src="ui/js/jquery-1.7.1.js"></script>
<script src="ui/js/login.js"></script>
<script src="ui/js/relogin.js"></script>
<script src="ui/js/home/signup_validations.js"></script>
<script src="ui/js/jquery.color.js"></script>
<script src="ui/js/search_script.js"></script>
<script src="ui/js/jquery.limit.js"></script>
<script src="ui/js/prettyCheckboxes.js" type="text/javascript" charset="utf-8"></script>
<script type="text/javascript" src="ui/js/autoresize.jquery.js"></script>
<script type="text/javascript" src="ui/js/easySlider1.7.js"></script>
<script src="ui/js/accordian/jquery.ui.core.js"></script>
<script src="ui/js/accordian/jquery.ui.widget.js"></script>
<script src="ui/js/accordian/jquery.ui.accordion.js"></script>
<script type="text/javascript" src="ui/js/common/jquery.form.js"></script>
<script type="text/javascript" src="ui/js/validate/jquery.validate.js" ></script>
    <script type="text/javascript" src="ui/js/jquery.validate.1.8.1.min.js"></script>


<script>
	$(function() {
		$( "#accordion" ).accordion();
	});
	</script>
<script type="text/javascript">
		$(document).ready(function() {
			// Store variables
			var accordion_head = $('.accordion > li > a'),
				accordion_body = $('.accordion li > .sub-menu');
			// Open the first tab on load
			accordion_head.first().addClass('active').next().slideDown('normal');
			// Click function
			accordion_head.on('click', function(event) {
				// Disable header links
				event.preventDefault();
				// Show and hide the tabs on click
				if ($(this).attr('class') != 'active'){
					accordion_body.slideUp('normal');
					$(this).next().stop(true,true).slideToggle('normal');
					accordion_head.removeClass('active');
					$(this).addClass('active');
				}

			});

		});

		function getContextPath() {
			return "<%=request.getContextPath()%>";
		}
	</script>
<link rel="stylesheet" type="text/css" href="ui/css/ratingbar.css" />
    <script type="text/javascript" src="${pageContext.request.contextPath}/ui/js/placeHolder.js"></script>
<script type="text/javascript" src="ui/js/jquery.ratingbar.js"></script>
<script type="text/javascript" charset="utf-8">
$(document).ready(function() {
$('#signupNew2').click(function(){
	window.location = "./createAcct.cyt";	
});

$("#complete_sendMail_msg").css("display", "none");

$('#loginUser2').click(function () {
	window.setTimeout(location.reload(true),1);
});

('.gender_rating').ratingbar();
});
</script>
<script type="text/javascript" src="ui/js/ddaccordion.js"></script>
<script type="text/javascript">

//Initialize :
ddaccordion.init({
	headerclass: "question_tab", //Shared CSS class name of headers group
	contentclass: "question_detail", //Shared CSS class name of contents group
	revealtype: "click", //Reveal content when user clicks or onmouseover the header? Valid value: "click", "clickgo", or "mouseover"
	mouseoverdelay: 200, //if revealtype="mouseover", set delay in milliseconds before header expands onMouseover
	collapseprev: false, //Collapse previous content (so only one open at any time)? true/false 
	defaultexpanded: [], //index of content(s) open by default [index1, index2, etc]. [] denotes no content.
	onemustopen: true, //Specify whether at least one header should be open always (so never all headers closed)
	animatedefault: false, //Should contents open by default be animated into view?
	scrolltoheader: false, //scroll to header each time after it's been expanded by the user?
	persiststate: false, //persist state of opened contents within browser session?
	toggleclass: ["closedquestion", "openquestion"], //Two CSS classes to be applied to the header when it's collapsed and expanded, respectively ["class1", "class2"]
	togglehtml: ["none", "", " "], //Additional HTML added to the header when it's collapsed and expanded, respectively  ["position", "html1", "html2"] (see docs)
	animatespeed: "fast", //speed of animation: integer in milliseconds (ie: 200), or keywords "fast", "normal", or "slow"
	oninit:function(expandedindices){ //custom code to run when headers have initalized
		//do nothing
	},
	onopenclose:function(header, index, state, isuseractivated){ //custom code to run whenever a header is opened or closed
		//do nothing
	}
});

</script>
<script type="text/javascript" src="ui/js/jquery.placeholder.js"></script>
<script>
  $(function() {
    $('input, textarea').placeholder();
   });
</script>
<!--[if lt IE 9]>
   <script src="http://html5shiv.googlecode.com/svn/trunk/html5.js"></script>
<![endif]-->
<!--[if lt IE 9]>
    <meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7">
  <link href="ui/css/iestyle.css" media="screen" rel="stylesheet" type="text/css" />
  <![endif]-->
  
  <!--[if gte IE 9]>
 <link href="ui/css/gte_ie9.css" media="screen" rel="stylesheet" type="text/css" />
<![endif]-->
    <!--[if IE 8]>
    <link href="ui/css/ie8.css" media="screen" rel="stylesheet" type="text/css" />
    <![endif]-->
</head>
<%--<script type="text/javascript">
    $(window).load(function() {
        $(".loader").fadeOut("slow");
    })
</script>--%>
<body>
<%--
<div class="loader" style="background: url('${pageContext.request.contextPath}/ui/images/common/long_loading_bar.gif') 50% 50% no-repeat rgb(249,249,249)"></div>
--%>

<div id="gradient">
  <div class="extra">
  <!-- include header -->
  <jsp:include page="includes/signupAcctHeader.jsp" />
  
    <div class="main">
      <!--content -->
      <article id="content">
        <div id='mobilebanner'><a href="#" id="signupNew2"><img src="ui/images/mobile_sign_up_button.png"></a></div>                
        <div id="main_content" style="min-height: 450px;">
          <!----- Error Msg ----->
          <div id="loginError" class="common_error failure" style="display:none;"><strong>Please re-enter:</strong> You have entered an incorrect email and password combination.</div>
          <div id="loginErrorForDeactAcct" class="common_error failure" style="display:none;"><strong>Your Account has been deactivated : </strong>To Reactivate your account click on "REACTIVATE ACCOUNT" link</div>
          <!----- Error Msg ----->
          <!-- 2. Choose your Answers starts-->
          <div id="choose_your_answers_heading">
            <h3 class="heading1_creatacct">Login to inCyyte. </h3>
          </div>
          
          <div id="choose_your_answers">
            <div id="answer_container">
              <form:form  id="createacct_form"  commandName="loginForm" method="post">
                <div id="email_two">  <div class="login_text02"><span>Returning to inCyyte? </span> <a id="reactivateAccount" href="reactiveAcct.cyt"> Reactivate Account</a></div>
                  <form:input path="login_email" tabindex="1" id="login_email" onKeyUp="LogEmail()" placeholder="Email" onFocus="this.placeholder = ''" onBlur="this.placeholder = 'Email'"/>
                <div id="loginEmailError" style="padding-left: 48px; display:none; color:#C2002D;float: left;margin-top: -9px;margin-bottom: 10px;"></div>

                </div>
                 
                 <div id="pwd_three">
                     <div class="login_text02" style="margin-right: 124px;"><span>New to inCyyte? </span><a href="createAcct.cyt"> SIGN UP</a></div>
                  <form:password path="login_pwd" id="login_pwd" tabindex="2" placeholder="Re-enter Password" onFocus="this.placeholder = ''" onBlur="this.placeholder = 'Re-enter Password'" onkeyup="LogPass()" />
                   <div id="loginPwdError" style="padding-left: 48px; display:none; color:#C2002D;float:left;margin-top: -9px;margin-bottom: 10px;"></div>
                </div>
               <form:hidden path="cd" id="cd"/>
              </form:form> 
              
             </div>
          </div>

          <!-- 2. Login Account-->
            <div class="login_text02" style="float: left; margin-top: 20px;margin-left: 25px;"><span>Already a member? </span><a href="#"  id="fgt_pwd"> Forgotten password?</a></div>

          <div id="askyour_que_btn"><a href="#" title="LOGIN ACCOUNT" id="login_acct" class="button_red" style="width:240px;"> <span class="title_red title_red_ie8">LOGIN</span></a></div>
         
        </div>
        <div id="complete_sendMail_msg">
            <h3 class="heading1_creatacct">Your Activation Email has been resent. Please check your email. </h3>
            <BR>
          	<div id="fgt_pwd_back_btn"><a href="#" title="HOME" id="home2" class="button_red" style="width:240px;"> <span class="title_red">BACK</span></a></div>
          	<div id="fgt_pwd_login_btn"><a href="#" title="LOGIN" id="loginUser2" class="button_red" style="width:240px;"> <span class="title_red">LOGIN TO ACCOUNT</span></a></div>            
          </div>
      </article>
      <!--content end -->
    </div>
  </div>
  <!-- include footer -->
<jsp:include page="includes/homeFooter.jsp" />
<script type="text/javascript" src="ui/js/googleanalytics.js"></script> 
<script type="text/javascript">
$(document).keypress(function(event){
	var keycode = (event.keyCode ? event.keyCode : event.which);
	if(keycode == '13'){
		$("#login_acct").click();
	}
}); 
</script>
</body>
</html>
