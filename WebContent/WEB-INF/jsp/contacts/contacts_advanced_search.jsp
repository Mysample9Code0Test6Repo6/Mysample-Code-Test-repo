<%@ include file="/WEB-INF/jsp/common/include.jsp" %>
<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="content-type" content="text/html; charset=UTF-8">
<link rel="shortcut icon" href="favicon.ico" />

<title>inCyyte - Advanced Search</title>
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
<script src="ui/js/jquery-1.4.2.min.js"></script>
<script src="ui/js/jquery-1.7.1.js"></script>
<script src="ui/js/jquery.color.js"></script>
<script src="ui/js/search_script.js"></script>


<!-- Left Navigation script starts here -->
<script src="ui/js/left_menu.js"></script>

       <script type="text/javascript" src="ui/js/jquery-1.5.1.min.js"></script>
    	<script type="text/javascript" src="ui/js/jquery-ui-1.8.13.custom.min.js"></script>
    	<script type="text/javascript" src="ui/js/external/jquery.bgiframe-2.1.2.js"></script>
    	<script type="text/javascript" src="ui/js/communicator.js"></script>
    	<script type="text/javascript" src="ui/js/jquery.form.js"></script> 
    	
<link rel="stylesheet" href="ui/css/ui.progress-bar.css" type="text/css"/>
<link rel="stylesheet" href="ui/modal/colorbox.css">
<link rel="stylesheet" href="ui/css/ratingbar.css" type="text/css"/>

<script src="ui/js/jquery_profile_rating.js" type="text/javascript"></script>  
<script src="ui/js/star_rating.js" type="text/javascript"></script>
    	
<!-- Left Navigation script ends here -->
<!--- placeholder Starts----->
<script>
    // placeholder polyfill
    $(document).ready(function(){
       
    	function add()
    	{
            if($(this).val() == '')
            {
                $(this).val($(this).attr('placeholder')).addClass('placeholder');
            }
        }

        function remove() {
            if($(this).val() == $(this).attr('placeholder')){
                $(this).val('').removeClass('placeholder');
            }
        }
        
    	$("#SearchContact").click(function() { SearchContact(); });


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
    <!--[if IE 8]>
    <link href="ui/css/ie8.css" media="screen" rel="stylesheet" type="text/css" />
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
            <!-- advanced_contact_search Start -->
 <div id="advanced_contact_search" style="display: ''"> 
              <h4>Advanced <span>Search</span></h4>
              <p>Choose a Social Network or Email Service to import your Contacts</p>
              
<form:form  id="SearchContactForm"  commandName="SearchContactForm" method="GET" >   
              
 <table width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td colspan="3" height="70" valign="top"><label>Keywords</label><form:input path="keywords" id="sc_keywords" size="30"  title="Enter keywords " AUTOCOMPLETE="off" /></input></td>
  </tr>
  <tr>
    <td width="47%"><label>First Name</label>
        <form:input path="firstname" id="sc_firstname" size="30" class="advanced_contact_search_input"  title="Enter First name" AUTOCOMPLETE="off" />
    </td>
    
    <td width="6%">&nbsp;</td>
    <td width="47%"><label>Last Name</label>
    <form:input path="lastname" id="sc_lastname" size="30" class="advanced_contact_search_input"  title="Enter last name" AUTOCOMPLETE="off" />
   </td>
  </tr>
  <tr>
    <td><label>Country</label>
    <form:input path="country" id="sc_country" class="advanced_contact_search_input" size="30"  /></td>
    <td>&nbsp;</td>
    <td><label>Telephone</label><form:input path="mobile" id="sc_mobile" size="30" class="advanced_contact_search_input"  /></td>
  </tr>
  <tr>
    <td><label>City</label><form:input path="city" id="sc_city" size="30" class="advanced_contact_search_input"  title="Enter last name" AUTOCOMPLETE="off" />
</td>
    <td>&nbsp;</td>
    <td><label>Gender</label><form:input path="gender" id="sc_gender" size="30" class="advanced_contact_search_input"  title="Choose Gender" AUTOCOMPLETE="off" />
</td>
  </tr>
  <tr>
    <td><label>Post Code</label> <form:input path="postalcode" id="sc_postalcode" size="30" class="advanced_contact_search_input"  title="Enter Postal code" AUTOCOMPLETE="off" />
    </td>
    <td>&nbsp;</td>
        <td><a href="#" onClick="javascript:SearchContact();" title="Search" id="" class="button_green1" style="margin-top:10px; width:100px; float:right;"> <span class="title_green1">SEARCH</span></a> </td>
    
    
  </tr>
</table>
    </form:form>
 </div>
            <!-- advanced_contact_search Ends-->
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
