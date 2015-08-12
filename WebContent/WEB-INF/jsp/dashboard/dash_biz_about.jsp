<%@page import="com.incyyte.app.web.SessionKeys"%>
<%@page import="com.incyyte.app.domain.User"%>
<%@page import="com.incyyte.app.service.util.Utility"%>
<%@page import="com.incyyte.app.domain.InCyyteChart"%>
<%@page import="com.incyyte.app.domain.CyyteResponse"%>
<%@page import="com.incyyte.app.service.util.Constants"%>
<%@page import="java.util.*"%>
<%@ page import="org.apache.commons.lang.StringUtils" %>
<%@ include file="/WEB-INF/jsp/common/include.jsp" %>

<% User user = (User)request.getSession().getAttribute(SessionKeys.LOGIN_USER); %>

<link rel="stylesheet" href="ui/css/reset.css">
<link rel="stylesheet" href="ui/css/style.css">
<link rel="stylesheet" href="ui/css/layout.css">
<link rel="stylesheet" href="ui/css/buttons.css"  media="screen">

<link rel="stylesheet" href="ui/css/accordionmenu.css" type="text/css">
<link rel="stylesheet" href="ui/css/icons_sprites.css">
<link rel="stylesheet" href="ui/css/form_elements.css">
<link rel="stylesheet" href="ui/css/tooltip.css">
<link rel="stylesheet" href="fonts/fonts_stylesheet.css">
<link rel="stylesheet" href="ui/modal/colorbox.css">
<link rel="stylesheet" type="text/css" href="ui/css/jquery.lightbox-0.5.css" media="screen" />
<link rel="stylesheet" href="ui/css/jquery.validate.css">
<link rel="stylesheet" href="ui/css/validate/screen.css">
<link rel="stylesheet" href="ui/css/jquery.selectbox.css" type="text/css" />
<link rel="stylesheet" href="ui/css/modal_window.css" type="text/css" media="screen">
<script type="text/javascript" src="ui/js/jquery-ui-1.8.16.custom.min.js"></script>
<script src="ui/js/jquery-1.8.1.js"></script>
<script src="ui/js/jquery-1.8.3.min.js" type="text/javascript"></script> 
<script src="ui/js/tooltip.js"></script>
<script src="ui/js/jquery.color.js"></script>
<script src="ui/js/search_script.js"></script>
<script src="ui/js/jquery_dashboard_rating.js" type="text/javascript"></script>
<script src="ui/js/star_rating.js" type="text/javascript"></script>
<script src="ui/modal/colorbox/jquery.colorbox.js"></script>
<script type="text/javascript" src="js/jquery.form.js"></script>
<script type="text/javascript" src="ui/js/jquery-ui.js"></script>
<script type="text/javascript" src="ui/js/jquery.lightbox-0.5.js"></script>
<script type="text/javascript" src="ui/js/validate/jquery.validate.js" ></script>

<!-- Left Navigation script starts here -->
<script src="ui/js/left_menu.js"></script>
<script src="ui/js/jquery.selectbox-0.2.js"></script>
<!-- Left Navigation script ends here -->

<!-- Chart Script Start here -->
<script src="ui/js/charts/js/charts_recent_incyyte.js"></script>
<script src="ui/js/charts/js/highcharts.js"></script>
<script src="ui/js/dashboard/dash_my_polls.js"></script>
<!-- Chart Script end here -->

<!-- Rating bar and profile starts here -->
<link rel="stylesheet" type="text/css" href="ui/css/ratingbar.css" />
<link rel="stylesheet" href="ui/css/style_login.css">
<link rel="stylesheet" href="ui/css/style.css">


<script type="text/javascript" src="ui/js/jquery.ratingbar.js"></script>
<!-- Rating bar and profile ends here -->


<!--- Modal ----->
<script>
			$(document).ready(function(){
				//Examples of how to assign the ColorBox event to elements
				$(".photos").colorbox({rel:'photos'});
				$(".group2").colorbox({rel:'group2', transition:"fade"});
				$(".group3").colorbox({rel:'group3', transition:"none", width:"75%", height:"75%"});
				$(".group4").colorbox({rel:'group4', slideshow:true});
				$(".ajax").colorbox();
				$(".youtube").colorbox({iframe:true, innerWidth:425, innerHeight:344});
				$(".vimeo").colorbox({iframe:true, innerWidth:500, innerHeight:409});
				$(".iframe").colorbox({iframe:true, width:"80%", height:"80%"});
				$(".inline").colorbox({inline:true});
		        //$(".inline1").colorbox({inline:true, height:"100%"});
				$(".callbacks").colorbox({
					onOpen:function(){ alert('onOpen: colorbox is about to open'); },
					onLoad:function(){ alert('onLoad: colorbox has started to load the targeted content'); },
					onComplete:function(){ alert('onComplete: colorbox has displayed the loaded content'); },
					onCleanup:function(){ alert('onCleanup: colorbox has begun the close process'); },
					onClosed:function(){ alert('onClosed: colorbox has completely closed'); }
				});
				
				//Example of preserving a JavaScript event for inline calls.
				$("#click").click(function(){ 
					$('#click').css({"background-color":"#f00", "color":"#fff", "cursor":"inherit"}).text("Open this window again and this message will still be here.");
					return false;
				});
			});
		</script>
<!--- Mddal--------------->

<!--[if gte IE 8]>
<style>
	.custom-checkbox input, 
	.custom-radio input {
	left: -3px;
</style>

<![endif]-->
<!--[if lt IE 9]>
   <script src="ui/js/html5.js"></script>
<![endif]-->
<!--[if lt IE 9]>
    <meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7">
  <link href="ui/css/iestyle.css" media="screen" rel="stylesheet" type="text/css" />
  <![endif]-->
  
  <!--[if gte IE 9]>
 <link href="ui/css/gte_ie9.css" media="screen" rel="stylesheet" type="text/css" />
<![endif]-->
		

<form:form id="inCyyteForm" name="inCyyteForm" commandName="inCyyteForm" method="post">	
			
	<div id="most_resent_incyyte">
	
				<div class="business_detail">
					<table width="100%" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td  valign="top" class="grid_25">
							<strong style="margin-left:25px;">Create   Your   Business   Account</strong>
							</td>
				
						
					<table style="margin-left:50px;" width="621" border="0" align="center">
          <tr>
                                    <td><p class="heading2"><b>What you can do with a business account..</b></p><br></td>
                                    <td width="209" rowspan="2"><div align="right"><span class="smile_images"><img src="ui/images/businesspage.png" alt="business page" width="146" height="324" align="right" /></span></div></td>
          </tr>
                               
								
								
                                  <tr>
                                    <td width="396"><div align="left">
                                          <br>
                                      <p>With an inCyyte Business Account you can create Targeted  Advert  Campaigns and new Price Tag Polls  all designed to<br>
                                        reach
                                          individuals that meet your marketing criteria, in  postal <br>
                                        regions of your choice. </p>
                                        </div>
                                        <br>
                                        <div align="left"></div>
                                      <div align="left">These allow you to spread your business message and gain detailed statistical insights into what your customers think at a very low price.</div>
                                      <br />
                                        <div align="left">Creating a Business Account is free, and anyone can the great features to gain data about their potential audiences <a href="#" style="color:#FF0000"> more info</a></div>
                                      
                                      <br />
                                        <div align="left"></div>
                                       
                                      <br />
                                     
                                     
                                      <br>
                                    <span style="margin-left:50px;"><a href="createBizAcct.cyt" title="CREATE A BUSINESS ACCOUNT" id="addcmnt2" class="business_account" style="width: 250px; float: left; margin-left: 70px; "> <span
										class="title_green title_green_comment_ie8">CREATE BUSINESS ACCOUNT</span></a> <br><br>                                      </td>
                      </tr>						  <tr><td colspan="2"></td>
                                    
                                  </tr>
                                
								<p>&nbsp;</p>
								<p>&nbsp;</p>
						        <p>&nbsp;</p>
								<div class="smile_images"></div>
								<br>
				  </table>
				</div>
	</div>

</form:form>
								
	
