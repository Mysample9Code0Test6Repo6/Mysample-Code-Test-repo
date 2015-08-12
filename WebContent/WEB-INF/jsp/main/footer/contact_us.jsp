<%@ page import="java.util.Date" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ taglib prefix="display-el" uri="http://displaytag.sf.net/el" %>
<%@ include file="/WEB-INF/jsp/common/include.jsp" %>
<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="content-type" content="text/html; charset=UTF-8">
<link rel="shortcut icon" href="favicon.ico" />

<title>Contact Us</title>
<meta charset="utf-8">
<link rel="stylesheet" href="ui/css/reset.css">
<link rel="stylesheet" href="ui/css/style.css">
<link rel="stylesheet" href="ui/css/layout.css">
<link rel="stylesheet" href="ui/css/buttons.css">
<link rel="stylesheet" href="ui/css/style_login.css">
<link rel="stylesheet" href="ui/css/style_social_contactus.css">
<link rel="stylesheet" href="ui/css/icons_sprites.css">
<link rel="stylesheet" href="ui/css/modal_window.css">
<link rel="stylesheet" href="ui/css/ddsmoothmenu.css" type="text/css" />
<link rel="stylesheet" href="ui/css/ddsmoothmenu-v.css" type="text/css" />
<link rel="stylesheet" href="fonts/fonts_stylesheet.css">
<link rel="stylesheet" href="ui/css/jquery.validate.css">
<link rel="stylesheet" href="ui/css/validate/screen.css">

<link rel="stylesheet" href="ui/modal/colorbox.css">
<link rel="stylesheet" href="ui/css/form_elements.css">
<link rel="stylesheet" href="ui/css/image_slider.css">
<link rel="stylesheet" href="ui/css/validate/cmxform.css">

<script src="ui/js/jquery.js" type="text/javascript"></script>
<script src="ui/js/jquery-1.7.2.js"></script>
<script src="ui/js/login.js"></script>
<script src="ui/js/search_script.js"></script>
<script src="ui/js/jquery.lightbox-0.5.js" type="text/javascript"></script>
<script type="text/javascript" src="ui/js/validate/jquery.validate.js" ></script>

<script type="text/javascript" src="ui/js/jquery-ui-1.8.16.custom.min.js"></script>
<script src="ui/js/jquery-1.8.2.min.js"></script>
<script src="ui/modal/colorbox/jquery.colorbox.js"></script>

<script type="text/javascript" src="ui/js/easySlider1.7.js"></script>
<script type="text/javascript" src="ui/js/autoresize.jquery.js"></script>
<script type="text/javascript" src="ui/js/jquery.js"></script>
<script type="text/javascript" src="ui/js/jquery.lightbox-0.5.js"></script>
<script type="text/javascript" src="ui/js/common/jquery.form.js"></script>
<script type="text/javascript" src="ui/js/jquery-ui.js"></script>
<script type="text/javascript" src="ui/js/validate/jquery.validate.js" ></script>


<link rel="stylesheet" type="text/css" href="ui/css/jquery.lightbox-0.5.css" media="screen" />
<!-- Left Navigation script starts here -->
<!-- Left Navigation script ends here -->
<script>
    $(document).ready(function () {
    	$("#okbutton").click(function () {
    		$.fn.colorbox.close();
    		window.location.reload(true);
    	});
    	
    	$("#mailtoLink").click(function () {
    		
    		$("#name_error").hide();
   	   		$("#email_error").hide();
   	    	$("#message_error").hide();
   	    	
   	    	if($("#senderName").val() == ''){
   	    		$("#name_error").show();
   				return false;
   	    	}
   	    	if( $("#emailFrom").val() == ''){
   	    		$("#email_error").show();
   				return false;
   	    	}		
   	    	if( $("#message").val() == ''){
   	    		$("#message_error").show();
   				return false;
   	    	}
   	    	
   	    	$("#send_us_msg").ajaxSubmit({
	   	 		type:'POST',
	   	 		url:'sendcontactemail.cyt',
	   	 		success:function (data) {
	   	 			if (data.indexOf("success") != -1) {
   						$(".edit_pro_pop_txt").text("Your message has been sent");
   						parent.$.fn.colorbox({'href':'div.Poll_save_confirm_bg', 'open':true, 'inline':true, 'escKey':false, 'overlayClose':false});   						
	   	 			}
	   	 			else {
	   	 				$(".edit_pro_pop_txt").text("Error sending your message, please try again later");
						parent.$.fn.colorbox({'href':'div.Poll_save_confirm_bg', 'open':true, 'inline':true, 'escKey':false, 'overlayClose':false});
	   	 			}
	   	 		},
	   	 		error:function (jqXHR, textStatus, errorThrown) {	   	 			
		   	 		$(".edit_pro_pop_txt").text("Error sending your message, please try again later");
					parent.$.fn.colorbox({'href':'div.Poll_save_confirm_bg', 'open':true, 'inline':true, 'escKey':false, 'overlayClose':false});
	   	 		}
   	 		});
    		
         });
    });
   
</script>
<script type="text/javascript">
    $(function() {
        $('#gallery a').lightBox();
    });
    </script>
<script type="text/javascript" src="ui/js/ddaccordion.js"></script>
<script type="text/javascript">
//Initialize :
ddaccordion.init({
	headerclass: "question", //Shared CSS class name of headers group
	contentclass: "answer", //Shared CSS class name of contents group
	revealtype: "click", //Reveal content when user clicks or onmouseover the header? Valid value: "click", "clickgo", or "mouseover"
	mouseoverdelay: 200, //if revealtype="mouseover", set delay in milliseconds before header expands onMouseover
	collapseprev: true, //Collapse previous content (so only one open at any time)? true/false 
	defaultexpanded: [], //index of content(s) open by default [index1, index2, etc]. [] denotes no content.
	onemustopen: true, //Specify whether at least one header should be open always (so never all headers closed)
	animatedefault: false, //Should contents open by default be animated into view?
	scrolltoheader: false, //scroll to header each time after it's been expanded by the user?
	persiststate: false, //persist state of opened contents within browser session?
	toggleclass: ["closedquestionfaq", "openquestionfaq"], //Two CSS classes to be applied to the header when it's collapsed and expanded, respectively ["class1", "class2"]
	togglehtml: ["prefix", "<img src='ui/images/faq_down_arrow.png' style='width:16px; height:15px; float:right;'/> ", "<img src='ui/images/faq_up_arrow.png' style='width:16px; height:15px; float:right;' /> "], //Additional HTML added to the header when it's collapsed and expanded, respectively  ["position", "html1", "html2"] (see docs)
	animatespeed: "fast", //speed of animation: integer in milliseconds (ie: 200), or keywords "fast", "normal", or "slow"
	oninit:function(expandedindices){ //custom code to run when headers have initalized
		//do nothing
	},
	onopenclose:function(header, index, state, isuseractivated){ //custom code to run whenever a header is opened or closed
		//do nothing
	}
})

</script>
<script src="ui/js/ddsmoothmenu.js" type="text/javascript" ></script>
<script type="text/javascript">

ddsmoothmenu.init({
	mainmenuid: "smoothmenu1", //menu DIV id
	orientation: 'h', //Horizontal or vertical menu: Set to "h" or "v"
	classname: 'ddsmoothmenu', //class added to menu's outer DIV
	//customtheme: ["#1c5a80", "#18374a"],
	contentsource: "markup" //"markup" or ["container_id", "path_to_menu_file"]
})

ddsmoothmenu.init({
	mainmenuid: "smoothmenu2", //Menu DIV id
	orientation: 'v', //Horizontal or vertical menu: Set to "h" or "v"
	classname: 'ddsmoothmenu-v', //class added to menu's outer DIV
	//customtheme: ["#804000", "#482400"],
	contentsource: "markup" //"markup" or ["container_id", "path_to_menu_file"]
})

</script>

<style type="text/css">
<!--
.style1 {
	font-size: 29px;
	font-family: 'bariol_boldbold', 'ie8_bariol_bold';
	font-weight: bold;
	color: #1b4d5f;
}
-->
</style>
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
  <jsp:include page="../includes/homeHeader.jsp" />
  
    <div class="main">
      <!--content -->
      
      <article id="content_home">
        <div id="main_content">
          <div class="grid_15 marginbottom">
            <h1>Contact Us</h1>
            <div class="telephone_number rounded_35"> Telephone: <span> +44 208 166 1663</span> </div>
            <div class="address rounded_35 left">
               <p class="page_heading1 style1">&nbsp;</p>
               <p class="page_heading1 style1">Address</p>
               <p class="page_heading1 style1">&nbsp;</p>
              <div class="grid_3 font_14px">
                <p>INCYYTE LIMITED</p>
                <p>CEME INNOVATION CENTRE</p>
                <p>Marsh Way</p>
                <p>Rainham</p>
                <p>RM13 8EU</p>
              </div>
              <div style="position:absolute; left: 291px; top: -21px;"><img src="ui/images/contact_us_smily.png"></div>
              <div class="clearboth" style="padding-top:40px; width:130px;"> <a href="mailto:info@incyyte.com" title="Email Us" id="" class="button_green1" ><span class="contact_btn "><img src="ui/images/email_icon.png" class="emailus"> Email Us</span></a></div>
            </div>
              <%
                  String strFooterDate ="";
                  try{
                      SimpleDateFormat smp = new SimpleDateFormat("yyyy");
                      strFooterDate = smp.format(new Date());
                  }catch(Exception ex){}
              %>
              <div class="company_detail rounded_35 left">
              <p class="heading6">Company Details</p>
              <p class="font_14px">Company Number : 07884094 </p>
              <p class="font_14px">Registered in the UK &amp; Wales</p>
              <p class="font_14px">&copy; inCyyte <%=strFooterDate%></p>
            </div>
            <div class="social_network rounded_35 left">
              <p class="heading6">Social</p>
              <ul class="tt-wrapper">
                <li><a class="tt-facebook" href="https://www.facebook.com/incyyte" target="_blank"><span>Facebook</span></a></li>
                <li><a class="tt-twitter" href="https://twitter.com/@incyyte" target="_blank"><span>Twitter</span></a></li>
                <li><a class="tt-youtube" href="http://www.youtube.com/watch?v=v4PIP7LKeG8" target="_blank"><span>You Tube</span></a></li>
              </ul>
            </div>
          </div>
         
          <div class="grid_8">
            <!------  -------->
            <form:form id="send_us_msg"  commandName="contactForm" class="rounded_35" method="post">           
       			<h4>Send us a <span>message</span></h4> 
       			             	
		  		<label>Name*</label>
		  		<span id="name_error"  class="errorLabel" style="padding-left: 12px; display: none;">please enter your name </span>
		  		<form:input id="senderName" path="senderName" />
               
		  		<label>Email*</label>
		  		<span id="email_error"  class="errorLabel" style="padding-left: 12px; display: none;">please enter your email address</span>
		  		<form:input id="emailFrom" path="emailFrom" />
		  		
		  		<label>Telephone</label><form:input id="telephone" path="telephone" />
		  		
		  		<label>Message</label>
		  		<span id="message_error"  class="errorLabel" style="padding-left: 12px; display: none;">please enter your message </span>
		  		<form:textarea path="message" cols="" rows="5" id="message"  placeholder="Write your message here" />
		  		
				<div class="send_message" id="sendDiv">			  		
			  		<a href="#" title="SEND MESSAGE" id="mailtoLink" class="button_green1" > <span class="contact_btn">SEND MESSAGE</span></a>
			  	</div>           
           	</form:form>
           	<!------ -------->
     	  </div>
          
        </div>
      </article>
      <!--content end -->
      
    </div>
  </div>
 
	<!-- include footer -->
	<jsp:include page="../includes/homeFooter.jsp" />
</div>
<!--footer end-->
<div id="Poll_save_confirm">
    <div class="Poll_save_confirm_bg">
        <div class="edit_pro_pop_txt">
        </div>
        <div style="height:auto; margin-left: 135px; float: left; margin-top: 25px;">
        <a class="poll_button1" href="#" style="width:170px;" id="okbutton">
        <span class="poll_button_red ">Ok</span></a></div>
    </div>
</div>
<script type="text/javascript" src="ui/js/googleanalytics.js"></script>
</body>
</html>
