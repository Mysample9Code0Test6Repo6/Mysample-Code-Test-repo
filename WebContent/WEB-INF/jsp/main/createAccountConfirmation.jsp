<%@ include file="/WEB-INF/jsp/common/include.jsp" %>
<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="content-type" content="text/html; charset=UTF-8">
<link rel="shortcut icon" href="favicon.ico" />

<title>inCyyte - Create Account</title>
<meta charset="utf-8">
<link rel="stylesheet" href="ui/css/reset.css">
<link rel="stylesheet" href="ui/css/style.css">
<link rel="stylesheet" href="ui/css/layout.css">
<link rel="stylesheet" href="ui/css/buttons.css"  media="screen">
<link rel="stylesheet" href="ui/css/style_login.css">
<link rel="stylesheet" href="ui/css/icons_sprites.css">
<link rel="stylesheet" href="./fonts/fonts_stylesheet.css">
<script src="ui/js/jquery-1.7.1.js"></script>
<script type="text/javascript" charset="utf-8">
$(document).ready(function() {
	$('#login2').click(function(){
		window.location = "<%=request.getContextPath()%>"+"/login.cyt";	
    });
});
</script>
</head>
<%--
<script type="text/javascript">
    $(window).load(function() {
        $(".loader").fadeOut("slow");
    })
</script>
--%>
<body>
<%--
<div class="loader" style="background: url('${pageContext.request.contextPath}/ui/images/common/long_loading_bar.gif') 50% 50% no-repeat rgb(249,249,249)"></div>
--%>
<div id="gradient">
  <div class="extra">
  <!-- include header -->
  <jsp:include page="includes/createAcctHeader.jsp" />
    <div class="main">
      <!--content -->
      <article id="content">
      <div id='mobilebanner'><a href="#" id="login2"><img src="ui/images/mobile_login_button.png"></a></div> 
        <div id="main_content" style="float: left;min-height: 450px;">

          <div  style="margin-top: 30px;float: left;">
           <img src="ui/images/smiley_red.png"> <h3 class="heading1_creatacct" style="margin-top:-80px;padding:10px 0 135px 135px;">
           Congratulations!! Thank you for joining inCyyte.<br><br> We have sent you an email to activate your account. </h3>
          </div>
        </div>
      </article>
      <!--content end -->
    </div>
  </div>
  <!-- include footer -->
<jsp:include page="includes/homeFooter.jsp" />
<script type="text/javascript" src="ui/js/googleanalytics.js"></script>
</body>
</html>
