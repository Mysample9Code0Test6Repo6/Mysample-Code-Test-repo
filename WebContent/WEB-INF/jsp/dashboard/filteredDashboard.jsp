<%@page import="org.apache.commons.lang.StringUtils"%>
<%@page import="com.incyyte.app.web.SessionKeys"%>
<%@page import="com.incyyte.app.domain.User"%>
<%@page import="com.incyyte.app.domain.InCyyteChart"%>
<%@page import="com.incyyte.app.domain.CyyteResponse"%>
<%@page import="com.incyyte.app.service.util.Utility"%>
<%@page import="com.incyyte.app.service.util.Constants"%>
<%@page import="com.incyyte.app.web.model.MandatoryInfoModel"%>
<%@ include file="/WEB-INF/jsp/common/include.jsp" %>

<% User user = (User)request.getSession().getAttribute(SessionKeys.LOGIN_USER);%>

<html lang="en">
<head>
<meta http-equiv="content-type" content="text/html; charset=UTF-8">
<link rel="shortcut icon" href="favicon.ico" />

<title>inCyyte - My Dashboard</title>
<meta charset="utf-8">

<link rel="stylesheet" href="ui/css/reset.css">
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
<link rel="stylesheet" href="ui/css/prettyCheckboxes.css" type="text/css" media="screen">

    <!-- Theme CSS -->
    <link rel="stylesheet" href="css/newwelcome/settings.css">
    <link rel="stylesheet" href="css/newwelcome/theme-header.css">
    <link rel="stylesheet" href="css/newwelcome/theme-elements.css">
    <link rel="stylesheet" href="css/newwelcome/teal.css">
    <link rel="stylesheet" href="css/newwelcome/animate.css">

    <link rel="stylesheet" href="css/newwelcome/styles.css">
<style>
.ui-dialog {
	top:0px;
}
.no-close .ui-dialog-titlebar-close {
    display: none;
}
.ui-widget-overlay {
   background: repeat-x scroll 50% 50% #000000;
    opacity:0.5;
    filter:alpha(opacity=50);
}

.ui-widget-overlay {
    height:100%;
    left:0;
    position:absolute;
    top:0;
    width:100%;
}
.fancybox-inner{
    overflow: hidden !important;
}
select, .form-control {
    width: 94% !important;
}

</style>
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
<% User userLocal = (User) session.getAttribute("user"); %>
<% if (userLocal != null) {%>
<jsp:include page="../common/includes/header.jsp"/>
<%} else { %>
 <jsp:include page="/WEB-INF/jsp/newHeader.jsp" />
<%} %>

	<div class="extra">
    	<!-- include header -->
    	
    	 
    	<div class="main">
      		<!--content -->
      		<article id="content">
        		<div id="main_content">
          			<div class="grid_26">
		  				<nav>
							<ul id='left_mainmenu' class='accordion'/>
						</nav>
          			</div>
          			<div class="grid_17">
                        <div id="mask"></div>
             				<jsp:include page="filteredPolls.jsp" />           			    			
          				</div>
        		</div>
      		</article>
      		<!--content ends -->
    	</div>
  	</div>

    <%-- Checklist Popup --%>
    <div style="display: none">
    <div id="dashboard_checklist">
    <input type="hidden" id="contectVariable" value="${pageContext.request.contextPath}"/>
        <div class="headertxt">Getting you started..</div>
        <div class="pbody">
            <div class="checklist_wrap">
               <div class="checklist_no"><img src="ui/images/1.png"/></div>
                <div class="checklist_button"><a href="${pageContext.request.contextPath}/editProfile.cyt"><img src="ui/images/1_list.png"/></a></div>
                <div class="checklist_txt">
                    Complete your personal profile to ensure you only receive wanted polls.
                    You can always remain anonymous on inCyyte.
                </div>
            </div>

            <div class="checklist_wrap">
                <div class="checklist_no"><img src="ui/images/2.png"/></div>
                <div class="checklist_button"><a href="${pageContext.request.contextPath}/importcontacts.cyt"><img src="ui/images/2_list.png"/></a></div>
                <div class="checklist_txt">
                    Import your contacts from your current email providers.
                    i.e. business contacts, customers or just your friends..
                </div>
            </div>
            <div class="checklist_wrap">
                <div class="checklist_no"><img src="ui/images/3.png"/></div>
                <div class="checklist_button"><a href="${pageContext.request.contextPath}/create_question.cyt"><img src="ui/images/3_list.png"/></a></div>
                <div class="checklist_txt">
                    Ask your customers questions that matter to your business.
                    Find out who needs your services, when & where.
                    Embed videos, promo, images or documents.
                </div>
            </div>

            <div class="checklist_wrap">
                <div class="checklist_no"><img src="ui/images/4.png"/></div>
                <div class="checklist_button"><a href="${pageContext.request.contextPath}/pollSetup.cyt"><img src="ui/images/4_list.png"/></a></div>
                <div class="checklist_txt">
                    With just a few steps you can setup a unique webpage to
                    advertise your business or your interests. Attach your poll to the
                    page and customers or friends can share your poll and post comments.
                </div>
            </div>

            <div class="checklist_check_wrap">
                <div id="check-box"><label for="c1"></label><input type="checkbox" id="c1" /></div>
                <p>I get how it works, don't show this anymore.</p>
            </div>

        </div>
    </div>
    </div>
  	<!-- include footer -->
  	
 <% if (userLocal != null) {%>
 <jsp:include page="../common/includes/footer.jsp"/>
<%} else { %>
<jsp:include page="../newFooter.jsp" />
<%} %>
  	<script type="text/javascript" src="ui/js/googleanalytics.js"></script>
<script type="text/javascript" src="js/NewWelcomejs/jquery.easing.min.js"></script>
<script type="text/javascript" src="js/NewWelcomejs/jquery.touchSwipe.min.js"></script>
<script type="text/javascript" src="js/NewWelcomejs/jquery.mousewheel.min.js"></script>
<script type="text/javascript" src="js/NewWelcomejs/imagesloaded.pkgd.min.js"></script>
<script type="text/javascript" src="js/NewWelcomejs/SmoothScroll.js"></script>

<!-- Retina js -->
<script type="text/javascript" src="js/NewWelcomejs/retina.js"></script>

<!-- FancyBox -->
<script type="text/javascript" src="js/NewWelcomejs/jquery.fancybox.pack.js?v=2.1.5"></script>

<!-- Bootstrap js -->
<script type="text/javascript" src="js/NewWelcomejs/bootstrap.min.js"></script>

<!-- Validate -->
<script type="text/javascript" src="js/NewWelcomejs/jquery.validate.min.js"></script>

<!-- FlickrFeed  -->
<!-- <script type="text/javascript" src="js/NewWelcomejs/jflickrfeed.min.js"></script>  -->
<!-- carouFredSel -->
<script type="text/javascript" src="js/NewWelcomejs/jquery.carouFredSel-6.2.1-packed.js"></script>

<!-- SLIDER REVOLUTION 4.x SCRIPTS  -->
<script type="text/javascript" src="js/NewWelcomejs/jquery.themepunch.plugins.min.js"></script>
<script type="text/javascript" src="js/NewWelcomejs/jquery.themepunch.revolution.min.js"></script>

<!-- Scripts for current page -->
<script type="text/javascript" src="js/NewWelcomejs/home.js"></script>

<!-- Main theme javaScript file -->
<script type="text/javascript" src="js/NewWelcomejs/theme.js"></script>


<script src="ui/js/prettyCheckboxes.js" type="text/javascript" charset="utf-8"></script>
<script type="text/javascript" charset="utf-8">
    $(document).ready(function () {
        $('#check-box input[type=checkbox]').prettyCheckboxes();

        $('#c1').click(function() {
            var contxtvar=document.getElementById("contectVariable").value;
            window.location = contxtvar+"/dashCheckList.cyt";
        });

    });
</script>
<script src="ui/js/tooltip.js"></script>
<script src="ui/js/login.js"></script>
<script src="ui/js/jquery.color.js"></script>
<script src="ui/js/search_script.js"></script>
<script src="ui/js/jquery_dashboard_rating.js" type="text/javascript"></script>
<script src="ui/modal/colorbox/jquery.colorbox.js"></script>
<script type="text/javascript" src="ui/js/jquery-ui.js"></script>
<script type="text/javascript" src="ui/js/jquery.lightbox-0.5.js"></script>
<script type="text/javascript" src="ui/js/validate/jquery.validate.js" ></script>
<!-- Left Navigation script starts here -->
<script src="ui/js/left_menu.js"></script>
<script src="ui/js/jquery.selectbox-0.2.js"></script>
<script src="ui/js/jquery.ui.datepicker.js"></script>
<!-- Left Navigation script ends here -->

<!-- Chart Script Start here -->
<!-- <script src="ui/js/charts/js/charts.js"></script> -->
<script src="ui/js/charts/js/highcharts.js"></script>
<script src="ui/js/dashboard/dash_my_polls.js"></script>
<!-- Chart Script end here -->

<!-- Rating bar and profile starts here -->
<link rel="stylesheet" type="text/css" href="ui/css/ratingbar.css" />
<link rel="stylesheet" href="ui/css/style_login.css">

<script type="text/javascript" src="ui/js/jquery.ratingbar.js"></script>
<script type="text/javascript">

    function playYoutubeVideoQues( id, videoURL){
        var videoId  = "" ;
        //By assuming Video url for youtube  is of 41 characters and videoId is of 11 characters .

        if(videoURL.length == 41) {
            videoId = videoURL.substring(30 , 41);
        }else {
            videoId = null;
        }
        $("#iFrameYouTube_"+id).attr("src", "https://www.youtube.com/embed/"+videoId);
        parent.$.fn.colorbox({href:'div#emailListYouTube_'+id, open:true, inline:true});
    }

    <%
    if ((user != null) && (!StringUtils.equals(user.getDisplayCheckList(), "Y"))) {
    %>
    $(document).ready(function() {
        parent.$.fn.colorbox({'href':'div#dashboard_checklist', 'open':true, 'inline':true,  'closeButton':false, 'top':"50"});
    });
    <%
   user.setDisplayCheckList("Y");
   request.getSession().setAttribute(SessionKeys.LOGIN_USER,user);
   } %>
</script>

<script type="text/javascript" charset="utf-8">
    $(document).ready(function() {
        var s = document.URL;
        if (s.indexOf("sn") !== -1)
        { opener.location='authSuccess.do';
            window.close();
        }
        $('.gender_rating').ratingbar();

    });
</script>
<script type="text/javascript">
    $(function () {
        $("select").selectbox();
    });
    $(function() {
        $( "#datepicker" ).datepicker({
            minDate: 0,
            altField: "#alternate",
            altFormat: "yy-mm-dd"
        }).datepicker("setDate", "+1");
    });

</script>

</body>
</html>
