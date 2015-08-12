<%@ include file="/WEB-INF/jsp/common/include.jsp" %>
<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="content-type" content="text/html; charset=UTF-8">
<link rel="shortcut icon" href="favicon.ico" />

<title>Anonymity</title>
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
<script src="ui/js/jquery.limit.js"></script>

<script type="text/javascript" src="ui/js/easySlider1.7.js"></script>
<script type="text/javascript" src="ui/js/autoresize.jquery.js"></script>
<script type="text/javascript" src="ui/js/jquery.js"></script>
<script type="text/javascript" src="ui/js/jquery.lightbox-0.5.js"></script>
<script type="text/javascript" src="ui/js/common/jquery.form.js"></script>
<script type="text/javascript" src="ui/js/jquery-ui.js"></script>
<script type="text/javascript" src="ui/js/validate/jquery.validate.js" ></script>


<script type="text/javascript">
    $(function() {
        $('#gallery a').lightBox();
    });
    </script>
<style type="text/css">
<!--
.style1 {color: #CC0000}
-->
</style>

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
          <p class="page_heading_A">Anonymity and <strong>Security</strong></p>
          <div class="grid_24">
            <!-- Anonymity and Security -->
            <div class="grid_11"><img src="ui/images/anonymity_and_security.png"></div>
            <div class="grid_10">
            <p class="heading3">Your Identity Protected!</p>
            <p class="common_text">Use inCyyte without sharing your personal details.</p>
            <p class="common_text">inCyyte is hosted on a secure server much like your<br>
banks so all your content is encrypted and not shared on the internet. Send anonymous polls to neighbours, friends or co-workers to get honest opinions!</p>
            <ul>
            <li>Secure Encrypted Server</li>
            <li>Protected Identity</li>
            <li>Child Safe with safeguards</li>
            <!--li class="style1"><a href="red_card_system.html" class="common_text style1" style="text-decoration:none class="active"><strong>See our Red Card System</strong></a></li-->
            </ul>
             <a href="" title="Primary Action" id="" class="button_green_big" style="width:250px; margin-top:20px;"> <span class="title_green_big">Primary Action</span></a>
            </div>
            
        
          <!-- Anonymity and Security -->
          
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
