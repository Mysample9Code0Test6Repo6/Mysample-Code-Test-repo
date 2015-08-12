<%@ include file="/WEB-INF/jsp/common/include.jsp" %>
<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="content-type" content="text/html; charset=UTF-8">
<link rel="shortcut icon" href="favicon.ico" />
<title>inCyyte</title>
<meta charset="utf-8">

<link rel="stylesheet" href="ui/css/reset.css">
<link rel="stylesheet" href="ui/css/style.css">
<link rel="stylesheet" href="ui/css/layout.css">
<link rel="stylesheet" href="ui/css/style_login.css">
<link rel="stylesheet" href="ui/css/icons_sprites.css">
<link rel="stylesheet" href="ui/css/buttons.css"  media="screen">
<link rel="stylesheet" href="fonts/fonts_stylesheet.css">
<link rel="stylesheet" href="ui/css/modal_window.css" type="text/css" media="screen">
<link rel="stylesheet" type="text/css" href="ui/css/jquery.lightbox-0.5.css" media="screen" />
<link rel="stylesheet" href="ui/css/jquery.validate.css">
<link rel="stylesheet" href="ui/css/validate/screen.css">

<script src="ui/js/jquery-1.7.2.js"></script>
<script src="ui/js/jcarousellite.js"></script>
<script src="ui/js/login.js"></script>
<script src="ui/js/home.js"></script>
<script src="ui/js/jquery.limit.js"></script>

<script type="text/javascript" src="ui/js/easySlider1.7.js"></script>
<script type="text/javascript" src="ui/js/autoresize.jquery.js"></script>
<script type="text/javascript" src="ui/js/jquery.js"></script>
<script type="text/javascript" src="ui/js/jquery.lightbox-0.5.js"></script>
<script type="text/javascript" src="ui/js/common/jquery.form.js"></script>
<script type="text/javascript" src="ui/js/jquery-ui.js"></script>
<script type="text/javascript" src="ui/js/validate/jquery.validate.js" ></script>
    <script type="text/javascript" src="ui/js/jquery.validate.1.8.1.min.js"></script>
<script type="text/javascript" src="ui/js/jquery.cookie.js" ></script>
<link rel="stylesheet" href="ui/css/incyyte.css">

<script type="text/javascript" src="ui/js/jquery.placeholder.js"></script>
<script>
  $(function() {
    $('input, textarea').placeholder();
   });
</script>
<script>
    $(function(){
        $('#contact').contactable({
            subject: 'cookie'
        });
          });
</script>

<!-- 
<script src="ui/js/jquery.validate.js" type="text/javascript"></script>
<script src="ui/js/home/signup_validations.js" type="text/javascript"></script>
 -->
<script  type="text/javascript">
    $(document).ready(function() {
        $("#myTextarea2").limit({
            limit: 150,
            id_result: "counter",
            alertClass: "alert"
            });
         });
</script>

<script type="text/javascript">
function storeData() {
alert('alert1:store data');
$("#businessAcctForm").ajaxSubmit({
		url:'storeBusinessAccountInfo.cyt',
		success:function (data) {
			alert('success');
		},
		error:function (jqXHR, textStatus, errorThrown) {
			alert("error:" + textStatus + " exception:" + errorThrown);
		}
	});
}
</script>
<script type="text/javascript">
		$(document).ready(function(){	
			$("#slider").easySlider({
				auto: true, 
				continuous: true
			});
		});	
	</script>
<script type="text/javascript">
    $(function() {
        $('#gallery a').lightBox();
    });
</script>
<script type="text/javascript" src="js/flowplayer/flowplayer-3.2.12.min.js"></script>
<!-- This is for video dispaly in home page  -->
<script type="text/javascript">
$(document).ready(function(){
$("#buttonaria").click(function () {
 $f(player, "js/flowplayer/flowplayer-3.2.16.swf", {
	    clip: {
	        url: 'http://www.incyyte.com/advert/incyyte_video_animatic_v01.mp4',
	        autoPlay: true,
	        autoBuffering: true
	    },
	    plugins: {
	        controls:{
	        	url: 'js/flowplayer/flowplayer.controls-3.2.15.swf',
	        	top: -20,
	            left: 0,
	            opacity: 0.95,
	            timeColor: '#980118',
	            all: true,
	            play: true,
	            scrubber: true,
	         tooltips: {
	             buttons: true,
	             fullscreen: 'Enter fullscreen mode'
	            }
	        }
	    },
	    onLoad: function(){
	    }
	}); 
});
});

function playVideo() {
	parent.$.fn.colorbox({href:'div#homeVideo', open:true, inline:true});
}
</script> 
<!-- Remember to include CSS files before JS -->
<link rel="stylesheet" href="ui/fancyplayer_code/css/jquery.fancybox.css" type="text/css" media="screen" />
<script type="text/javascript" src="ui/fancyplayer_code/js/flowplayer-3.1.1.min.js"></script>
<script type="text/javascript" src="ui/fancyplayer_code/js/jquery.fancybox-1.2.1.pack.js"></script>
<script type="text/javascript" src="ui/fancyplayer_code/js/jquery.easing.1.3.js"></script>
<script type="text/javascript" src="ui/fancyplayer_code/js/fancyplayer.js"></script>
<script type="text/javascript">
	var videopath = "http://localhost:8400/inCyyte/";
	var swfplayer = videopath + "ui/fancyplayer_code/videos/flowplayer-3.1.1.swf";
</script>
<!--- Modal ----->

<link rel="stylesheet" href="ui/modal/colorbox.css">
<script src="ui/modal/colorbox/jquery.colorbox.js"></script>
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

<!--[if lte IE 7]>
<div id="browser-detection" align="center">
<h4>Oops! Your Browser Needs Updating</h4>
<p>inCyyte has been designed for viewing with the most common modern web browsers. You need to upgrade to the latest web browser for better viewing pleasure and avoid encountering problems with sections of our website Thank you!</p>
<p>Please upgrade to <a href='http://getfirefox.com'>FireFox</a>, <a href='http://www.opera.com/download/'>Opera</a>, <a href='http://www.apple.com/safari/'>Safari</a> or <a href='http://www.microsoft.com/windows/downloads/ie/getitnow.mspx'>Internet Explorer 9 or 10</a>. Thank You!&nbsp;&nbsp;&nbsp;<a href="#" onClick="document.getElementById('browser-detection').style.display = 'none';"><b><img src="ui/images/close.png"></b></a></p>
</div>
<![endif]-->

<!--[if IE 8]>
<link href="ui/css/ie8.css" media="screen" rel="stylesheet" type="text/css" />
<![endif]-->
<script type="text/javascript" src="ui/js/cookieChecker.js"></script>
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
<div class="cookie-policy" >
    <!--slide bar start here-->
    <div id="contactForm">
        <div id="slider_cookie_part">
            <div class="privacy_content_part" style="margin-top: 30px; margin-left: 34px;">
                <div class="privacy_content_heading">Cookie Policy</div>

                <div class="cookie_content_part_sroll">
                    <div class="privacy_content_part_display">
                        <div class="privacy_content">
                            <h2>INFORMATION ABOUT OUR USE OF COOKIES</h2>
                            <P>Like most websites, our website  (the Site�) uses cookies to distinguish you from other users of the Site, to customise your experience and to improve the quality of the services that we provide. This helps us to provide you with a good experience when you browse the Site and also allows us to improve the Site. By continuing to browse the Site, you are agreeing to our use of cookies.</P>

                            <P> A cookie is a small file of letters and numbers that we store on your browser or the hard drive of your computer or other device if you agree. Cookies contain information that is transferred to your computer's hard drive.</P>
                            <div class="privacy_content">
                                <P><strong>We use the following cookies</strong> </P>
                                <ul>
                                    <li>Essential cookies. These are cookies that are required for the operation of the Site. They include, for example, cookies that enable you to log into secure areas of the Site and to make payments.</li>

                                </ul>
                            </div>
                        </div>


                        <div class="privacy_content">
                            <ul>
                                <li>Analytical/performance cookies. These cookies allow us to recognise and count the number of visitors and to see how visitors move around the Site when they are using it. This helps us to improve the way the Site works, for example, by ensuring that users are finding what they are looking for easily.</li>
                                <li>Functionality cookies. These cookies are used to recognise you when you return to the Site. This enables us to personalise our content for you, greet you by your username and remember your preferences.</li>

                                <li>Targeting cookies. These cookies record your visit to the Site, the pages you have visited and the links you have followed. We will use this information to make the Site and the advertising displayed on it more relevant to your interests. We may also share this information with third parties for this purpose.</li>
                            </ul>
                        </div>
                    </div>

                    <div class="privacy_content_part_display">
                        <table width="848" border="0" cellspacing="0" cellpadding="0" class="cookie_table_content">
                            <tr>
                                <th width="139">Cookie Category</th>
                                <th width="142">Name</th>
                                <th width="398">Purpose</th>
                                <th width="223">More information</th>
                            </tr>
                            <tr>
                                <td>Functionality</td>
                                <td><strong>Persistent Cookie</strong></td>
                                <td>This is set when you log into your inCyyte account and enables us to recognise you each time you revisit the site so that you can remain logged in.</td>
                                <td>FOR EASE OF USE cookie not Shared beyond your PC   </td>
                            </tr>
                            <tr>
                                <td>Analytical/performance</td>
                                <td><strong>Session Cookie</strong></td>
                                <td>This is used to identify a particular visit to our website. </td>
                                <td>This cookie will expire after a short time or when you close your web browser.</td>
                            </tr>
                           
                            <tr>
                                <td>Security/Retention</td>
                                <td><strong>Http Cookies,</strong><br><strong>Secure Cookies</strong></td>
                                <td><br><strong>DESCRIPTION OF THE PURPOSE FOR WHICH THE COOKIE IS USED</strong><br />
                                    Examples of purposes for which a cookie may be used:<br />
                                    This cookie is <strong>essential for the Site to enables us to</strong>:<br />
                                    <ul>
                                        <li>Estimate our audience size and usage pattern.</li>
                                        <li>Store information about your preferences and allow us to customise the Site and to provide you with offers that are targeted at your individual interests.</li>
                                        <li>Speed up your searches.</li>
                                        <li>Allow you to use the Site in a way that makes your browsing experience more convenient. If you register with us or complete our online forms, we will use cookies to remember your details during your current visit, and any future visits provided the cookie was not deleted in the interim.<br><br></li>
                                    </ul>
                                    
                                </td>
                                <td>TRACKER COOKIES, Enhances your browsing experience, makes things quicker and more convenient.</td>
                            </tr>
                        </table>

                    </div>

                    <div class="privacy_content_part_display">
                        <div class="privacy_content">

                            <P>Other technology such as Macromedia flash can be used to place the functional equivalent of a cookie on your computer. You can control flash objects� or flash cookies� using the Macromedia Website Privacy Settings Panel at www.macromedia.com. We may use cookie-equivalent technology for the limited purpose of enhancing the security of our services. We do not use flash cookies for advertising purposes.</P>

                            <P>In the course of serving advertisements or optimising the Services to our Users, we may allow authorised third parties to place or recognise a unique cookie on your browser, for the enhancement of you user experience (i.e. providing more relevant advertising). Any information provided to third parties through cookies will not be personal information but may provide general category information (e.g., your industry or geography). </P>

                            <P>Third parties (including, for example, advertising networks and providers of external services like web traffic analysis services) may also use cookies over which we have no control. These cookies are likely to be analytical/performance cookies or targeting cookies.</P>
                        </div>
                        <div class="privacy_content">
                            <P>You block cookies by activating the setting on your browser that allows you to refuse the setting of all or some cookies. However, if you use your browser settings to block all cookies (including essential cookies) you may not be able to access all or parts of the Site. </P>

                            <P>inCyyte does not store unencrypted personal information in cookies.</P>
                            <P>Except for essential cookies, all cookies will expire when your session is completed.</P>
                            <P><strong>CHANGES TO THIS COOKIE POLICY</strong></P>

                            <P>Any changes we may make to this cookie policy in the future will be posted on this page and, where appropriate, notified to you by e-mail. Please check back frequently to see any updates or changes to our cookie policy.</P>

                            <P><strong>CONTACT US</strong><br />
                                Questions, comments and requests regarding this cookie policy are welcomed and should be addressed to <a href="mailto:info@incyyte.com">info@incyyte.com</a> or Incyyte Limited, 24 CEME Innovation Centre, Marsh Way, Rainham, Essex, RM13 8EU, United Kingdom.<br></P>
                        </div>
                    </div>

                    <div class="privacy_content_part_display"><br>
                        <div class="footer_content">This version (v.1) of this cookie policy and was uploaded on 15th February 2014.</div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div id="contact"></div>
    <!--slide bar start here-->
</div>
<div id="bggray"></div>
<div id="gradient">
  <div class="extra">
  <!-- include header -->
  <jsp:include page="includes/homeHeader.jsp" />
     <div id="video_player"></div>
    <div class="main">
      <!--content -->
      <article id="content_home"> 
       <%--<div id='chromebanner'><a href="https://www.google.com/intl/en_uk/chrome/browser/?hl=en-GB&brand=CHMI" target="_blank"><img src="ui/images/google_banner.png"></a></div>--%>    
        <div id="main_content">        
          <h2>What's your <strong>Question?</strong></h2>
          <div class="smile_images"><img src="ui/images/smile_faces.png" alt="" height="288" width="559"></div>
           <div class="earn_more">
            <h3>Create your own polls to get<br>
              the answers you need from<br>
             friends, business associates,<br>
               co-workers or customers.</h3><br>
            <!-- div id="#video_thumbs" -->
            <div id="#video_thumbs" >
            	<ul id="videos">
              		<li> 
              			<a href="#" >
              				<div id="buttonaria" onClick="javascript:playVideo();" class="more_video1" ></div>
              				<div style="display:none;">
              				<div id="homeVideo" >
              				<a style="display:block;width:970px;height:600px;cursor:pointer" id="player" ></a>
              				</div></div>
              			</a> 
              			              			
              		</li>
                </ul> 
                          	
           </div>
          </div>
        </div>
      </article>
      <!--content end -->
    </div>
   <div id="que_inner" >
   
    <form:form  id="inCyyteForm" name="inCyyteForm"  commandName="inCyyteForm" method="post" action="enter_question.cyt" >
          <form:textarea  placeholder="Type your question here... " onFocus="this.placeholder = ''" onBlur="this.placeholder = 'Type your question here...'" 
	      	onKeyDown="countit(this)" onKeyUp="countit(this)" path="incyyte" maxlength="${questionMaxChar}"  id="myTextarea2" title="Type your question here" rows="1" cols="1" />
	      <div id="buttonaria_red" class="ask_que" ></div>
	      <div style="font-size:16px; text-align:left; width:560px; padding-left: 50px; padding-top: 7px; float:left;"><span > You've used 
        <label id="label1"  style="color:red"></label> of <c:out value="${questionMaxChar}"></c:out> characters.</span></div>
    </form:form>

    </div> 
    
    <!--  <div id="question"></div>-->
  <div id="bottom_shadow"><div class="bottom_shadow_img"></div></div>
    <div id="slider_container">
      <div id="slider">
        <ul>
        
          <li>
            <h3>NEED TO CREATE AN ANONYMOUS POLL?</h3>
            <br>
            <p>inCyyte lets you ask questions to anyone whilst <strong>protecting your identity.</strong></p>
          </li>
          <li>
            <h3>DO CUSTOMERS NEED MORE FROM YOUR SERVICE?</h3>
            <br>
            <p>Create a free business poll and find out in a  <strong>matter of minutes..</strong></p>
          </li>
          <li>
            <h3>WHERE EXACTLY ARE YOUR POTENTIAL CUSTOMERS?</h3>
            <br>
            <p>Use inCyyte to poll whole postcodes and locate new customers <strong>instantly..</strong></p>
          </li>
          <li>
            <h3>ORGANISING A GROUP OF PEOPLE?</h3>
            <br>
            <p>inCyyte lets you gather group statistics in a matter of seconds..</p>
          </li>
          <li>
            <h3>GET MORE OUT OF YOUR LOYALTY CARDHOLDERS</h3>
            <br>
            <p>Release the full potential of loyalty schemes with <strong>direct customer interaction. </strong> </p>
          </li>
        </ul>
       </div>
       <div id='pollcentre_ad'><a><img src="ui/images/frontpage_ad.png"></a><br><br><br></div>
    </div>
  </div>

 <!-- include footer -->
<jsp:include page="includes/homeFooter.jsp" />
<div class="cookie_bottom">
	<div class="cookie_bottom_wrap">
	<p>We use cookies on this website, you can read about them <a href="#" id="showcookie">Here</a>. To use the website as intended please..</p>
	<a href="javascript:void(0)" id="cookieTermsagree" style="float: left;height: auto;margin-left: 10px;margin-top: 7px;text-align: center;width: 62px;border:none; "  class="cookie_button">Accept</a>
</div>
</div>
</div>
<script>
$(function () {
  $("textarea").autoresize();
  $('body').cookieChecker();
})
</script>
<script type="text/javascript" src="ui/js/googleanalytics.js"></script>
</body>
</html>