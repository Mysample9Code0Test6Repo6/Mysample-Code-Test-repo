<%@ page import="java.util.Date" %>
<%@ page import="java.text.SimpleDateFormat" %>

<%@ include file="/WEB-INF/jsp/common/include.jsp" %>
<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="content-type" content="text/html; charset=UTF-8">
<link rel="shortcut icon" href="favicon.ico" />

<title>inCyyte Error Message</title>
<meta charset="utf-8">
<link rel="stylesheet" href="${pageContext.request.contextPath}/ui/css/reset.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/ui/css/layout.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/ui/css/style_login.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/ui/css/style_error.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/fonts/fonts_stylesheet.css">
<script src="ui/js/login.js"></script>
<!--[if gte IE 8]>
<style>
#myTextarea3 { line-height:22px;}
#header_topnav_inner
{
margin-top:-10px;
}
</style>
<![endif]-->
<style type="text/css">
<!--
.style1 {
	font-size: 28px
}
.style2 {
	font-size: 18px
}
.style3 {color: #FF0000}
-->
  </style>
</head>
<%
    //ConfigManager cfgMgr = getConfigManager();
	String errorCode = ""+request.getAttribute("javax.servlet.error.status_code");
	String errorKey = "incyyte.error.key."+errorCode;
%>
<body>
<div id="gradient">
  <div class="extra">
    <!--header -->
    <jsp:include page="../common/includes/header.jsp" />
    <!--header end-->
    <div class="main">
      <!--content -->
      <div id="main_content">
      <br>
      <br>
      <br>
      
          
      
      
      
      
      
      <article id="content">
        <div id="main_content">
          <div class="error_wrapper">
            <!----- Error Msg ----->
            <br>
            <h1 align="left" class="style1"><img src="${pageContext.request.contextPath}/ui/images/incyyte-error-icon.png" width="26" height="26"> Oops!.. the page you requested was not found.</h1>
            <p align="left" class="style2" > ${errors}.</p>
            <p align="left" class="style2" ><span style="position:absolute; left: 652px; top: 95px;"><img src="${pageContext.request.contextPath}/ui/images/contact_us_smily.png" alt="smiley"></span></p>
            <p align="left" class="style2" >&nbsp;</p>
            <p align="left" class="style2" >&nbsp;</p>
            <p align="left" class="style2" >&nbsp;</p>
            <p align="left" class="style2" >Use the menu bar above to continue</p>
            <p align="left" class="style2" >or return to your <a href="${pageContext.request.contextPath}/dash.cyt" target="_self" class="style3">Dashboard</a></p>
            <!----- Error Msg ----->
          </div>
        </div>
      </article>
      <!--content end -->
    </div>
  </div>
  <!--footer -->
  <footer class="footer_inner">
      <%
          String strFooterDate ="";
          try{
              SimpleDateFormat smp = new SimpleDateFormat("yyyy");
              strFooterDate = smp.format(new Date());
          }catch(Exception ex){}
      %>
      <div style=" text-align:center; "> &copy; inCyyte <%=strFooterDate%> all rights reserved   </div>
  </footer>
</div>
<!--footer end-->
<script type="text/javascript" src="ui/js/googleanalytics.js"></script>
</body>
</html>
