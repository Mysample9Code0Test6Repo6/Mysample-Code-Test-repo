<%@ include file="/WEB-INF/jsp/common/include.jsp" %>
<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="content-type" content="text/html; charset=UTF-8">

<title>inCyyte - Reactive Account</title>
<meta charset="utf-8">
<link rel="shortcut icon" href="${pageContext.request.contextPath}/favicon.ico" />

<link rel="stylesheet" href="${pageContext.request.contextPath}/ui/css/reset.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/ui/css/style.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/ui/css/layout.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/ui/css/buttons.css"  media="screen">
<link rel="stylesheet" href="${pageContext.request.contextPath}/ui/css/style_login.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/ui/css/accordionmenu.css" type="text/css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/ui/css/icons_sprites.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/fonts/fonts_stylesheet.css">

<link rel="stylesheet" href="${pageContext.request.contextPath}/ui/css/jquery.validate.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/ui/css/validate/screen.css">

    <script src="${pageContext.request.contextPath}/ui/js/jquery-1.7.1.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/ui/js/common/jquery.form.js"></script>
    <script src="${pageContext.request.contextPath}/ui/js/login.js"></script>
<script src="${pageContext.request.contextPath}/ui/js/home/signup_validations.js"></script>
<script src="${pageContext.request.contextPath}/ui/js/fgtpwd.js"></script>

<script type="text/javascript" src="${pageContext.request.contextPath}/ui/js/validate/jquery.validate.js" ></script>

<script type="text/javascript" charset="utf-8">
$(document).ready(function() {
	$('#login2').click(function(){
		window.location = "<%=request.getContextPath()%>"+"/login.cyt";	
    });
});
function reActivate(){
	var email = $('#login_email').val();
    if ($("#createacct_form").valid()) {
        $("#createacct_form").ajaxSubmit({
            type: 'POST',
            url: 'login/reActivate.cyt',
            success: function(data) {
            	  if(data == 'success'){
                    //display Message
                    //1. hide forms
                    $("#choose_your_answers_heading").css("display", "none");
                    $("#choose_your_answers_login").css("display", "none");
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
</script>
<script>
    // placeholder polyfill
    $(document).ready(function(){
        function add() {
            if($(this).val() == ''){
                $(this).val($(this).attr('placeholder')).addClass('placeholder');
            }
        }

        function remove() {
            if($(this).val() == $(this).attr('placeholder')){
                $(this).val('').removeClass('placeholder');
            }
        }

        // Create a dummy element for feature detection
        if (!('placeholder' in $('<input>')[0])) {
            // Select the elements that have a placeholder attribute
            $('input[placeholder], textarea[placeholder]').blur(add).focus(remove).each(add);
            // Remove the placeholder text before the form is submitted
            $('form').submit(function(){
                $(this).find('input[placeholder], textarea[placeholder]').each(remove);
            });
        }
    });
</script>
    <!--[if IE 8]>
    <link href="${pageContext.request.contextPath}/ui/css/ie8.css" media="screen" rel="stylesheet" type="text/css" />
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
  <div class="extra" >
  <!-- include header -->
  <jsp:include page="includes/createAcctHeader.jsp" />
    <div class="main">
      <!--content -->
      <article id="content">
        <div id='mobilebanner'><a href="#" id="login2"><img src="ui/images/mobile_login_button.png"></a></div>                
        <div id="main_content" style="min-height: 450px; float: left;">
          <!----- Error Msg ----->
          <div class="common_error failure" id="sendMail_error" ><strong>Please re-enter:</strong> This email is already an active email on inCyyte.</div>
          <!----- Error Msg ----->
          <div id="complete_sendMail_msg">
            <h3 class="heading1_creatacct">Your Activation Email has been resent. Please check your email. </h3>
            <BR>
          	<div id="fgt_pwd_back_btn"><a href="welcome.cyt" title="HOME" id="home2" class="button_red" style="width:240px;"> <span class="title_red">BACK</span></a></div>
          	<div id="fgt_pwd_login_btn"><a href="login.cyt" title="LOGIN" id="loginUser2" class="button_red" style="width:240px;"> <span class="title_red">LOGIN TO ACCOUNT</span></a></div>            
          </div>

          <!-- 2. Choose your Answers starts-->
          <div id="choose_your_answers_heading">
            <h3 class="heading1_creatacct">What's your email address? </h3>
          </div>
          <div id="choose_your_answers_login" >
            <div id="answer_container">
              <form:form  id="createacct_form"  commandName="loginForm" method="post"> 
                <div id="email_two">
                  <form:input path="user_email" id="login_email"  onKeyUp="validateLoginEmail()" placeholder="Email" onFocus="this.placeholder = ''" onBlur="this.placeholder = 'Email'"/>
                </div>
                <div id="loginErrorDiv">
                     <span id="loginEmailError" style="display: none; color: #C2002D;font-size:15px; font-weight:normal; font:14px/20px 'bariol_regularregular', 'ie8_bariol_regular'"></span>
                </div>
              </form:form>
             </div>
          </div>
          <!-- 2. Reactivate Account-->
          <div id="askyour_que_btn"><a href="#" title="Reactivate your account"  onClick="reActivate()" class="button_red" style="width:240px;"> <span class="title_red title_red_ie8">Reactivate</span></a></div>
          
        </div>
      </article>
      <!--content end -->
    </div>
  	<!-- include footer -->
	<jsp:include page="includes/homeFooter.jsp"/>
  </div>
</div>
<script type="text/javascript" src="ui/js/googleanalytics.js"></script>  
</body>
</html>
