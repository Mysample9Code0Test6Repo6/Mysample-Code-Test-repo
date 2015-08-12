<%@ include file="/WEB-INF/jsp/common/include.jsp" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">
    <link rel="shortcut icon" href="favicon.ico" />

    <title>Vote Confirmation</title>
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

    <script src="${pageContext.request.contextPath}/ui/js/jquery-1.7.2.js"></script>
    <script src="${pageContext.request.contextPath}/ui/js/jcarousellite.js"></script>
    <script src="${pageContext.request.contextPath}/ui/js/login.js"></script>
    <script src="${pageContext.request.contextPath}/ui/js/jquery.limit.js"></script>

    <script type="text/javascript" src="ui/js/easySlider1.7.js"></script>
    <script type="text/javascript" src="ui/js/autoresize.jquery.js"></script>
    <script type="text/javascript" src="ui/js/jquery.js"></script>
    <script type="text/javascript" src="ui/js/jquery.lightbox-0.5.js"></script>
    <script type="text/javascript" src="ui/js/common/jquery.form.js"></script>
    <script type="text/javascript" src="ui/js/jquery-ui.js"></script>
    <script type="text/javascript" src="ui/js/validate/jquery.validate.js" ></script>

    <script type="text/javascript">
    $(document).ready(function () {
    	var  voteMsg = $('#voteMsgModel').val();
    	if(voteMsg == "Sorry ... You have already voted!"){
    	 $('#successMessage').css("display","none");
    	$('#errorAlreadyVotedMessage').css("display","");
    	}
    });
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
    <script>
        $(document).ready(function(){
            $('#signupNew2').click(function(){
                window.location = "./createAcct.cyt";
            });
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
            <div id='mobilebanner1'><a href="#" id="signupNew2"><img src="ui/images/mobile_sign_up_button.png"></a></div>
            <input type="hidden" id="voteMsgModel" value="${voteMsg}">
           <div class="vote_confirm_bg">
               <div id="successMessage" class="vote_confirm_txt">
               <span>Hooray!</span> Thanks! We have now received your vote.
               </div>               
               <div id="errorAlreadyVotedMessage" class="vote_confirm_txt" style="display: none;">
                   <span>Oops!</span> Sorry, you have already voted on this poll.
               </div>
           </div>
            <div id="askyour_que_btn" style="margin-bottom: 100px;"><a href="dash.cyt?code=${incyyteCode}&actcode=${activationCode}&frm=incomming&email=${email}&page=details" title="VIEW RESULTS" id="login_acct" class="button_red" style="width:240px;"> <span class="title_red title_red_ie8">VIEW RESULTS</span></a></div>
            <!--content end -->
        </div>
    </div>
    <!-- include footer -->
    <jsp:include page="includes/homeFooter.jsp" />
</div>
<!--footer end-->
<script type="text/javascript" src="${pageContext.request.contextPath}/ui/js/googleanalytics.js"></script>
</body>
</html>
