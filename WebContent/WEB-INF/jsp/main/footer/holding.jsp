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
   <script src="ui/jsk/html5.js"></script>
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
	<jsp:include page="../includes/emptyHeader.jsp"/>
     <div id="video_player"></div>

    <div class="main">
      <!--content -->
      <article id="content_home"> 
        <div id="main_content">        
          <h2>We're making <strong>Changes</strong></h2>
          <div class="smile_images"><img src="ui/images/smile_faces.png" alt="" height="288" width="559" style="float:right"></div>
           <div class="earn_more">
            <h3>Please bear with us while we update our website <br>
              we apologise for the inconvenience<br>
              </h3>
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

  </div>
<!-- include footer -->
<jsp:include page="../includes/emptyhomeFooter.jsp" />
</div>
<script type="text/javascript" src="ui/js/googleanalytics.js"></script>
</body>
</html>
