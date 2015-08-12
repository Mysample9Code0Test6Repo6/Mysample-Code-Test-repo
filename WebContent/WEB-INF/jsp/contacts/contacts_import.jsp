<%@ include file="/WEB-INF/jsp/common/include.jsp" %>
<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="content-type" content="text/html; charset=UTF-8">
<link rel="shortcut icon" href="favicon.ico" />

<title>inCyyte - Import Contacts</title>
<meta charset="utf-8">
<link rel="stylesheet" href="ui/css/reset.css">
<link rel="stylesheet" href="ui/css/layout.css">
<link rel="stylesheet" href="ui/css/buttons.css"  media="screen">
<link rel="stylesheet" href="ui/css/style_login.css">
<link rel="stylesheet" href="ui/css/accordionmenu.css" type="text/css">
<link rel="stylesheet" href="ui/css/icons_sprites.css">
<link rel="stylesheet" href="fonts/fonts_stylesheet.css">
<link rel="stylesheet" href="ui/css/style_social.css">
<link rel="stylesheet" href="ui/css/ratingbar.css" type="text/css"/>
<link rel="stylesheet" href="ui/css/ui.progress-bar.css" type="text/css"/>
<link rel="stylesheet" href="ui/modal/colorbox.css">


<script src="ui/js/jquery-1.4.2.min.js"></script>
<script src="ui/js/jquery-1.7.1.js"></script>
<script src="ui/js/jquery.color.js"></script>
<script src="ui/js/search_script.js"></script>
<script src="ui/js/progress.js"></script>
<!-- Left Navigation script starts here -->
<script src="ui/js/left_menu.js"></script>
<script type="text/javascript" src="ui/js/jquery-1.8.3.min.js"></script>
<script src="ui/js/jquery_profile_rating.js" type="text/javascript"></script>  
<script src="ui/js/star_rating.js" type="text/javascript"></script>


<!-- Left Navigation script ends here -->
<!--- placeholder Starts----->
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
<!--- placeholder Ends-----> 
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
    <jsp:include page="../common/includes/header.jsp" />
    <div class="main">
      <!--content -->
      <article id="content">
        <div id="main_content">
          <div class="grid_9">
            <h1>Contacts</h1>
            <nav>
              <script type="text/javascript" src="ui/includes/leftmenu_contacts.js"></script>
            </nav>
            <div class="hline"></div>
            <div id="advert"><img src="ui/images/advert.png" alt="Advert"></div>
          </div>
          <div class="line"><span></span></div>
          <div class="grid_17">
            <!------ Search Box Starts -------->
          
           
            <!------ Search Box End-------->
            <!-- import your Contacts Start -->
            <div id="import_contacts">
              <h4><span>Import</span> Contacts</h4>
              <p>Choose a Social Network or Email Service to import your Contacts</p>
              <p style="font-size:12px;">Note: When you log out, please also log out of the corresponding account or website <u><b>separately</b></u></p>
              
			  <ul class="tt-wrapper">
				<li><a class="tt-gmail" href="importsocialauth.do?id=googleplus&mode='import'"><span>Gmail</span></a></li>
				<li><a class="tt-yahoo" href="importsocialauth.do?id=yahoo&mode='import'"><span>Yahoo</span></a></li>
				<li><a class="tt-mail" href="./contacts_new.cyt?importFrom=importEmails"><span>Emails</span></a></li>
			</ul>
			<p></p>
			<p></p>
			<p></p>
			<p></p>
			<p></p>
			<p></p>
			<p></p>
			<p></p>
			<p></p>
              <h4><span>Connect With</span> Social Network Contacts</h4>
              <p>Share your inCyyte polls with Social Network friends</p>
              <p style="font-size:12px;">Note: When you log out, please also log out of the corresponding account or website <u><b>separately</b></u></p>
              
			  <ul class="tt-wrapper">
				<li><a class="tt-facebook" href="#"><span>Facebook <BR> Not available</span></a></li>
		 	    <li><a class="tt-linkedin" href="#"><span>LinkedIn <BR> Not available</span></a></li>
			  	<li><a class="tt-twitter" href="#"><span>Twitter <BR> Not available</span></a></li>
			</ul>
          
          
          
              <!-- 
              <a href="#" title="Import Contacts" id="" class="button_green1" style="margin:20px 0 35px 0; width:182px; float:left;"> <span class="title_green1">IMPORT CONTACTS</span></a> 
			  <div style="clear:both;"></div>
			  <p style="width:385px;">Downloading Facebook Contacts - 60% complete </p>
			      <!-- Progress bar
             <div id="progress_bar" class="ui-progress-bar ui-container">
             <div class="ui-progress" style="width: 60%;">
             <span class="ui-label" style="display:none;">Processing <b class="value">60%</b></span>      </div>
             </div>
           --> 
    <!-- /Progress bar -->
			  </div>
            <!-- import your Contacts Ends-->
          </div>
        </div>
      </article>
      <!--content ends -->
    </div>
  </div>
  <!-- include footer -->
  <jsp:include page="../common/includes/footer.jsp" />
  
  <script type="text/javascript" src="ui/js/googleanalytics.js"></script>
</body>
</html>
