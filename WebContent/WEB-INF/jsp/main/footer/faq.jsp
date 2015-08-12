<%@ include file="/WEB-INF/jsp/common/include.jsp" %>
<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="content-type" content="text/html; charset=UTF-8">
<link rel="shortcut icon" href="favicon.ico" />

<title>faq</title>
<meta charset="utf-8">
<link rel="stylesheet" href="ui/css/reset.css">
<link rel="stylesheet" href="ui/css/style.css">
<link rel="stylesheet" href="ui/css/layout.css">
<link rel="stylesheet" href="ui/css/buttons.css">
<link rel="stylesheet" href="ui/css/style_login.css">
<link rel="stylesheet" href="ui/css/icons_sprites.css">
<link rel="stylesheet" href="ui/css/modal_window.css">
<link rel="stylesheet" href="ui/css/ddsmoothmenu.css" type="text/css" />
<link rel="stylesheet" href="ui/css/ddsmoothmenu-v.css" type="text/css" />
<link rel="stylesheet" href="fonts/fonts_stylesheet.css">
<link rel="stylesheet" href="ui/css/jquery.validate.css">
<link rel="stylesheet" href="ui/css/validate/screen.css">

<script src="ui/js/jquery-1.7.2.js"></script>
<script src="ui/js/login.js"></script>
<script src="ui/js/search_script.js"></script>
<script src="ui/js/jquery.lightbox-0.5.js" type="text/javascript"></script>
<script type="text/javascript" src="ui/js/validate/jquery.validate.js" ></script>

<script type="text/javascript" src="ui/js/easySlider1.7.js"></script>
<script type="text/javascript" src="ui/js/autoresize.jquery.js"></script>
<script type="text/javascript" src="ui/js/jquery.js"></script>
<script type="text/javascript" src="ui/js/jquery.lightbox-0.5.js"></script>
<script type="text/javascript" src="ui/js/common/jquery.form.js"></script>
<script type="text/javascript" src="ui/js/jquery-ui.js"></script>

<link rel="stylesheet" type="text/css" href="ui/css/jquery.lightbox-0.5.css" media="screen" />
<!-- Left Navigation script starts here -->
<!-- Left Navigation script ends here -->
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
  <jsp:include page="../includes/homeHeader.jsp" />
    <div class="main">
      <!--content -->
      <article id="content_home">
        <div id="main_content">
          <div class="grid_9">
            <h1>FAQs</h1>
            <h3 class="heading4">Quick Categories</h3>
            <nav>
             <script type="text/javascript" src="ui/includes/quick_categories.js"></script>
            </nav>
          </div>
          <div class="line"><span></span></div>
          <div class="grid_17">
            <!------ Search Box Starts -------->
            <div id="searchbox1" style="margin-bottom:0">
              <div id="searchmain" >
                <p class="search_heading">Search</p>
                <form id="searchForm">
                  <fieldset>
                  <div class="input">
                    <input type='text' name="search" id="search" value="Enter your search" />
                  </div>
                  <input type="submit" id="searchSubmit" value="" />
                  </fieldset>
                </form>
              </div>
            </div>
            <!------ Search Box End-------->
            <!--FAQs Start -->
            <div id="faqs">
              <div class="question"> Q. I've moved, how do I change my  postcode on inCyyte? </div>
              <div class="answer">A. When you join inCyyte your postcode is fixed for 3 months. You can edit or change your postcode only after this period. Once your postcode is changed you will no longer be able to poll your previous postcode for free.</div>
              <div class="question"> Q.  I keep receiving polls that i'm not interested in. How do I stop these from coming through? </div>
              <div class="answer">A. All inCyyte polls are categorised so that you can select or deselect the categories you are interested in recieving. Alternatively you can also block polls from individuals that you are not interested in getting polls from. Visit 'my profile'.</div>
              <div class="question"> Q. I'm receiving hurtful and abusive polls, can I block or report them? </div>
              <div class="answer">A. Yes, we take cyber bullying extremely seriously. You can block and report malicious or hurtful polls using our Red Card System. We will review the poll and if it is deemed seriously offensive (Protection from Harassment Act 1997) we will remove it from inCyyte. Members that send offensive polls will be banned for 12 month and have all their account messages and contacts deleted. Visit 'Red Card System'.</div>
              <div class="question"> Q. Do I have to be a Business Member to earn money from InCyyte? </div>
              <div class="answer">A. No, You do not have to be a Business Member to earn money with inCyyte. As a free member you can opt-in to recieve Price-tag-polls (see settings). When you recieve a new poll and register your vote, the value associated to that poll will be deposited into your free inCyyte account for you to redeem into you personal bank account. </div>
              <div class="question"> Q. What are Price-Tag-Polls? </div>
              <div class="answer">A. inCyyte Price-Tag-Polls are targeted questions which are sent to specific members by business account holders. These sponsored polls have a value attached to them which is passed on to the member once they have voted on the poll. There is no limit to the amount a member can earn from voting on Price-Tag-Polls. Once a members account has reached �20, it can redeemed to a personal bank account. </div>
              <div class="question"> Q. Can I create and send my inCyyte polls to anyone anywhere? </div>
              <div class="answer">A. When you join inCyyte we ask you to provide us with your postcode. You are able to send polls to members with the same postcode region (e.g. SE1) as you for free. To send polls to members outside your registered postcode region you will need to create a business account which will allow you to send polls outside of your registered postcode region. <br><br>Alternatively, you can invite individuals from other postcode regions to connect with you on inCyyte. Once you have up to 5 connections in another postcode region, you will be allowed to send polls to that entire region.</div>
              <div class="question"> Q. Can I send polls to friends that don't live in my postcode region for free? </div>
              <div class="answer">A. Yes, you can send inCyyte polls to friends or family members by sending your poll directly to their email address or by inviting them to join you on inCyyte. Once you are connected to someone on inCyyte you can send and unlimited amounts of polls to that individual.</div>
              <!-- FAQs Ends-->
              <!-- Pagination start---->
              <script type="text/javascript" src="ui/includes/pagination_contacts.js"></script>
              <!-- Pagination ends---->
            </div>
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
