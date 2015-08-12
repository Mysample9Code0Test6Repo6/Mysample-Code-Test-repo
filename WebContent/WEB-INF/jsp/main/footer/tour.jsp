<%@ include file="/WEB-INF/jsp/common/include.jsp" %>
<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="content-type" content="text/html; charset=UTF-8">
<link rel="shortcut icon" href="favicon.ico" />

<title>tour</title>
<meta charset="utf-8">
<link rel="stylesheet" href="ui/css/reset.css">
<link rel="stylesheet" href="ui/css/layout.css">
<link rel="stylesheet" href="ui/css/buttons.css">
<link rel="stylesheet" href="ui/css/style_login.css">
<link rel="stylesheet" href="ui/css/icons_sprites.css">
<link rel="stylesheet" href="ui/css/modal_window.css">
<link rel="stylesheet" href="ui/css/form_elements.css">
<link rel="stylesheet" href="ui/slider/coda-slider.css">
<link rel="stylesheet" href="fonts/fonts_stylesheet.css">
<link rel="stylesheet" href="ui/css/jquery.lightbox-0.5.css" />
<link rel="stylesheet" href="ui/css/jquery.validate.css">
<link rel="stylesheet" href="ui/css/validate/screen.css">


<script src="ui/slider/jquery-1.2.6.js" type="text/javascript"></script>
<script src="ui/js/jquery-1.7.2.js"></script>
<script src="ui/js/jcarousellite.js"></script>
<script src="ui/js/login.js"></script>
<script src="ui/js/jquery.limit.js"></script>
<script src="ui/js/easySlider1.7.js" type="text/javascript"></script>
<script src="ui/js/autoresize.jquery.js" type="text/javascript"></script>
<script src="ui/js/jquery.js" type="text/javascript"></script>
<script src="ui/js/jquery.lightbox-0.5.js" type="text/javascript"></script>
<script src="ui/js/customInput.jquery.js" type="text/javascript"></script>
<script type="text/javascript" src="ui/js/validate/jquery.validate.js" ></script>

<script type="text/javascript" src="ui/js/common/jquery.form.js"></script>
<script type="text/javascript" src="ui/js/jquery-ui.js"></script>

<!-- Slider scripts --------->
<script src="ui/slider/jquery.scrollTo-1.3.3.js" type="text/javascript"></script>
<script src="ui/slider/jquery.localscroll-1.2.5.js" type="text/javascript" charset="utf-8"></script>
<script src="ui/slider/jquery.serialScroll-1.2.1.js" type="text/javascript" charset="utf-8"></script>
<script src="ui/slider/coda-slider.js" type="text/javascript" charset="utf-8"></script>
<!-- Slider scripts --------->

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


<script type="text/javascript">
	// Run the script on DOM ready:
	$(function(){
		$('input').customInput();
	});
	</script>
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
    $(function() {
        $('#gallery a').lightBox();
    });
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
  <jsp:include page="../includes/homeHeader.jsp" />
    <div class="main">
      <!--content -->
      <article id="content_home">
        <div id="main_content">
          <p class="page_heading_A">InCyyte <strong>Tour</strong></p>
          <div class="grid_24">
            <!-- tour -->
            <div id="tour_slider">
              <ul class="navigation">
                <li><a href="#create_polls">Create Polls</a></li>
                <li><a href="#results">Results</a></li>
                <li><a href="#social">Social</a></li>
                <li><a href="#payouts">Payouts</a></li>
                <li><a href="#video" class="last11">Video</a></li>
              </ul>
              <div class="scroll">
                <div class="scrollContainer">
                  <div class="panel" id="create_polls">
                    <div class="tour_img"><img src="ui/images/create_polls.png"></div>
                    <div class="tour_contents">
                     <p class="heading3">Discover your community</p>
                      <p class="common_text">Ask questions anonymously and attach images, videos or documents.</p>
                      <p class="common_text">Get fast results by using our easy to use poll creator to send out fast detailed questions to individuals that live within your postcode and community</p>
                      <ul>
                        <li>Polls designed for friends or co-workers</li>
                        <li>Polls that can be sent to entire postcodes or regions</li>
                        <li>Search for potential customers for your business</li>
                      </ul>
                      
                    </div>
                  </div>
                  <div class="panel" id="results">
                   <div class="tour_img"><img src="ui/images/results.png"></div>
                    <div class="tour_contents">
                      <p class="heading3">Poll results</p>
                      <p class="common_text">See all your statistics arrive in real-time!.</p>
                      <p class="common_text">Watch live as people respond to your questions in real-time. inCyyte breaks down your statistics instantly giving you easy detailed results of your poll.</p>
                      <ul>
                        <li>A great poll filter that isolates recipients to match your criteria</li>
                        <li>immediate "Real-Time" results</li>
                        <li>Read live comments from your recipients</li>
                         
                      </ul>
                     
                    </div>
                  </div>
                  <div class="panel" id="social">
                    <div class="tour_img"><img src="ui/images/tour_social.png"></div>
                    <div class="tour_contents">
                      <p class="heading3">Stay Connected!</p>
                      <p class="common_text">Easily import your contacts from social networks</p>
                      <p class="common_text">We've made it easy for you to share inCyyte with neighbours, friends or co-workers so you can find out the opinions of everyone on everything!</p>
                      <ul>
                        <li>Send direct or anonymous inCyytes</li>
                        <li>Create inCyytes for private groups or public groups</li>
                        <li>Create customer poll groups for your business</li>
                        <li>Join instant polling on Retail chains, TV Shows and Media groups </li>
                      </ul>
                      
                    </div>
                  </div>
                  <div class="panel" id="payouts">
                   <div class="tour_img"><img src="ui/images/payouts.png"></div>
                    <div class="tour_contents">
                      <p class="heading3">Price-Tag-Polling</p>
                      <p class="common_text">Earn money by voting on the things that interest you!</p>
                      <p class="common_text">You can select categories of interest to periodically vote on. Look out for inCyyte's with the piggy-bank icon and earn money each time you vote.</p>
                      <ul>
                        <li>Get paid royalties for your opinion</li>
                        <li>Promote your business with Price-Tag-Polling</li>
                        <li>Choose to Opt-in to start earning instantly.</li>
                        <li class="style1"><a href="red_card_system.html" class="common_text style1 style1" style="text-decoration:none class="active"><strong>Find out more about Price-Tag-Polling</strong></a></li>

                      </ul>
                     
                    </div>
                  </div>
                  <div class="panel" id="video">
                    <div class="tour_img"><img src="ui/images/tour_video.png"></div>
                    <div class="tour_contents">
                      <p class="heading3">  Still dont get it?..</p>
                      <p class="common_text">Let's explain.. </p>
                      <p class="common_text">We've put together a brief video to show you what we're all about.</p>
                      <p>&nbsp;</p>
                      <p>&nbsp;</p>
                        <!--a href="http://www.incyyte.com/investors/incyyte_video_animatic_v01.mp4" class="video_link"-->
                        <a href="#" class="video_link">
              				<div id="buttonaria" onClick="javascript:playVideo();" class="more_video1" ></div>
              				<div style="display:none;">
              				<div id="homeVideo" >
              				<a style="display:block;width:970px;height:600px;cursor:pointer" id="player" ></a>
              				</div></div>
              			</a> 
                    </div>
                  </div>
                </div>
              </div>
            </div>
            <!-- Tour end-->
            <div class="signup_button"> <a href="${pageContext.request.contextPath}/createAcct.cyt" title="Sign Up Now" id="" class="button_green_big" style="width:280px;"> <span class="title_green_big">Sign Up Now</span></a></div>
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
<script type="text/javascript" src="ui/js/googleanalytics.js"></script>
</body>
</html>
