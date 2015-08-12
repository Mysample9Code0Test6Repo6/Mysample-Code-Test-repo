<%@ include file="/WEB-INF/jsp/common/include.jsp" %>
<%@page import="com.incyyte.app.web.SessionKeys"%>
<%@page import="com.incyyte.app.domain.User"%>
<%@page import="com.incyyte.app.web.model.UserModel"%>
<html lang="en">
<head>
<meta http-equiv="content-type" content="text/html; charset=UTF-8">
<link rel="shortcut icon" href="favicon.ico" />

<title>inCyyte - Edit My Settings</title>
<meta charset="utf-8">
<% User user = (User)request.getSession().getAttribute(SessionKeys.LOGIN_USER);%>
 <link rel="stylesheet" href="ui/css/prettyCheckboxes.css" type="text/css" media="screen">
<link rel="stylesheet" href="ui/css/reset.css">
<link rel="stylesheet" href="ui/css/layout.css">
<link rel="stylesheet" href="ui/css/buttons.css"  media="screen">
<link rel="stylesheet" href="ui/css/style_login.css">
<link rel="stylesheet" href="ui/css/accordionmenu.css" type="text/css">
<link rel="stylesheet" href="ui/css/icons_sprites.css">
<link rel="stylesheet" href="fonts/fonts_stylesheet.css">
<link rel="stylesheet" href="ui/css/style_help.css">
<script src="ui/modal/colorbox/jquery.colorbox.js"></script>
<script src="ui/js/login.js"></script>
<script src="ui/js/jquery.color.js"></script>
<link rel="stylesheet" href="ui/css/ratingbar.css" type="text/css" />
<link rel="stylesheet" href="ui/css/form_elements.css" type="text/css" />
<link rel="stylesheet" type="text/css" href="css/accountry.0.2/jquery.autocomplete.css" />
<link rel="stylesheet" type="text/css" href="css/accountry.0.2/jquery.autocomplete.country.css" />

<link rel="stylesheet" href="ui/css/jquery.selectbox.css" type="text/css" />

<link rel="stylesheet" href="ui/modal/colorbox.css">

<script src="ui/js/jquery-1.4.2.min.js"></script>
<script src="ui/js/jquery-1.7.1.js"></script>
<script type="text/javascript" src="js/jquery.form.js"></script>
<script type="text/javascript" src="ui/js/jquery-1.8.3.min.js"></script> 
<script src="ui/js/prettyCheckboxes.js" type="text/javascript" charset="utf-8"></script>
<script src="ui/js/settings/settings.js"></script>
<script src="ui/js/jquery.color.js"></script>
<script src="ui/js/search_script.js"></script>
<script src="ui/js/jquery_profile_rating.js" type="text/javascript"></script>
<script src="ui/js/star_rating.js" type="text/javascript"></script>
<script src="ui/js/customSelect.jquery.js" type="text/javascript"></script>
<script src="ui/modal/colorbox/jquery.colorbox.js"></script>
<script type="text/javascript" src="js/accountry.0.2/jquery.autocomplete.js"></script>
<script type="text/javascript" src="js/accountry.0.2/countries.en.js"></script>
<!-- Left Navigation script starts here -->
<script type="text/javascript" src="ui/js/validate/jquery.validate.js" ></script>


<script type="text/javascript" src="ui/js/jquery.form.js"></script>
<script type="text/javascript"> 

function getContextPath() {
	return "<%=request.getContextPath()%>";
}
</script>

<script type="text/javascript" charset="utf-8">
 $(document).ready(function () {
                        
      $('.poll_setting input[type=radio]').prettyCheckboxes({'display':'inline'});
       $('.price_tag_polling input[type=radio]').prettyCheckboxes({'display':'inline'});
      // $('.poll_setting').find("input[type='radio']:checked").removeAttr('checked');
    });
</script>


<!-- Left Navigation script ends here -->
<link rel="stylesheet" href="ui/css/sparkbox-select.css">
<script src="ui/js/modernizr-1.7.min.js"></script>
<script type="text/javascript" src="ui/js/jquery.form.js"></script>
<script src="ui/js/jquery.sparkbox-select.js"></script>

<script type="text/javascript">

$(document).ready(function () {
if($('#pagename').val() == ''){
   <%if(user.getUsername() != null){%>
	   $('#pagename').val('<%=user.getUsername()%>');
   <%}%>
} else {
	$("#savepagenamelink").css("display", "none");
}
});
</script>
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
    <jsp:include page="../common/includes/header.jsp" />
    <div class="main">
      <!--content -->
      <article id="content">
        <div id="main_content">
          <div class="grid_9">
            <h1>Edit Settings</h1>
            <nav>
              <script src="ui/js/left_menu.js"></script>
              <script type="text/javascript" src="ui/includes/leftmenu_account_setting.js"></script>
            </nav>
            <div class="hline"></div>
			<input type="hidden" id="saveSetting" />
			<jsp:include page="../include/profileOverview.jsp" />
            <br>
            <h1 class="font_18px">Fill In your:</h1>
            <ul class="redtext">
              <li> <span> >> </span> <a href="./editProfile.cyt">Profile Picture</a> </li>
              <li> <span> >> </span> <a href="./editProfile.cyt">Your insight</a></li>
            </ul>
           <div id="advert"><img src="ui/images/advert.png" alt="Advert"></div>
          </div>
          <div class="line"><span></span></div>
          <div class="grid_17">
          <form:form id="editSettingsForm" commandName="editSettingsForm"  method="post" enctype="multipart/formdata">
            <!--- Edit Profile Starts---->
            <div id="edit_profile_setup" >
              <div id="profile_name">
                <table width="100%" border="0" cellspacing="0" cellpadding="0">
                  <tr>
                    <td valign="top" class="heading4">Change Password</td>
                    <td valign="top">&nbsp;</td>
                    <td valign="top" class="heading4 border_left" style="padding-left:20px;">Your Unique Page Name</td>
                  </tr>
                  <tr>
                    <td valign="top"><label>Current Password </label>
                      <form:password id="password" path="password" onKeyUp="cleanConfirmPwd()" readonly="true" class="readonly_input edit_setting_input_ie8" />
                     </td>
                    <td valign="bottom">&nbsp;</td>
                    <td width="47%" rowspan="3" valign="top" class="border_left leftIndent3" ><label>Choose A Page Name</label>
                      <p class="example">http://incyyte.com/example</p>
                      <div id="page_name_state1">
                        <form:input id="pagename" path="uniquePageName" onKeyUp="uniquePageValidate();" readonly="true" class="readonly_input edit_setting_input_ie8" />
                        <div id="settingsPageNameDiv">
                        <span id="uniquepagename_error" style="padding-left: 12px; display:none;">
                        </span></div>
                        <P> <a id="savepagenamelink" class="edit" href="javascript:uniquePage()"><span>Edit</span></a></P>
                      </div>
                      <p id = "validpagename" />
                      <p id = "invalidpagename" />
                      </td>
                  </tr>
                  <tr>
                    <td width="40%"><label>New Password</label>
                      <input type="password" id="newpass"  name="newpass" onKeyUp="cleanConfirmPwd()"  readonly="true" class="readonly_input edit_setting_input_ie8" />
                      </td>
                    <td width="13%">&nbsp;</td>
                  </tr>
                  <tr>
                    <td><label>Confirm New Password</label>
                      <form:password id="newpassword" path="newPassword"  name="newPassword"   onKeyUp="cleanConfirmPwd()"  readonly="true" class="readonly_input edit_setting_input_ie8" />
                      <br>
                      <p id="invalidnewpassword" class="error"/>
                      <p id="validnewpassword" class="error"/>
                      </td>
                    <td><p class="toppadding"><a id="savepasswordlink" class="edit" onClick="togglePwd('password','savepassword.cyt')"><span>Edit</span></a></p></td>
                  </tr>
                </table>
              </div>
            </div>

            <div id="polling">
              <h3 class="heading4" style="float:left;">Polling</h3>
              <div class="current_rating">
                <p style="float:left;">Your Current Rating: <span>MicroPhone</span></p>
                <div id="star" style="margin-top:3px;"></div>
              </div>
               <div class="clearboth"></div>
              <p class="font_14px">Edit Your Poll Preferences.</p>
              
            <div class="disabled_radio_button">Display my rating <span>
              <form:radiobutton path="displayRating" id="rating" value="Y" disabled="true" onclick="javascript:saveoptions('saverating.cyt');"/>
              <label for="rating">Yes</label>
                <form:radiobutton path="displayRating"  id="rating1" value="N" disabled="true" onclick="javascript:saveoptions('saverating.cyt');"/>
               <label for="rating1">No</label>
                 </span></div>
                 
              
              <div class="poll_setting">Disable Poll Comments <span >
                <form:radiobutton path="disableComments" id="disablePollComments" value="Y"  onclick="javascript:saveSettingsOptions('savedisablepoll.cyt','Y');"/>
                <label for="disablePollComments">Yes</label>
              	<form:radiobutton path="disableComments" id="disablePollComments1" value="N"  onclick="javascript:saveSettingsOptions('savedisablepoll.cyt','N');"/>
                <label for="disablePollComments1">No</label>
                </span></div>
              <div class="disabled_radio_button">Disable recent inCyyte on my profile <span >
                <form:radiobutton path="disbaleIncyytes" id="disableincyytes" value="Y" disabled="true" onclick="javascript:saveoptions('savedisableincyytes.cyt');"/>
                <label for="disableincyytes">Yes</label>
              	<form:radiobutton path="disbaleIncyytes" id="disableincyytes1" value="N" disabled="true" onclick="javascript:saveoptions('savedisableincyytes.cyt');"/>
                <label for="disableincyytes1">No</label>
                </span></div>
              <div class="disabled_radio_button">Restrict all comments on my profile <span >
                <form:radiobutton path="restrictComments" id="restrictcomments" value="Y" disabled="true" onclick="javascript:saveoptions('saverestrictcomments.cyt');"/>
                <label for="restrictcomments">Yes</label>
              	<form:radiobutton path="restrictComments" id="restrictcomments1" value="N" disabled="true" onclick="javascript:saveoptions('saverestrictcomments.cyt');"/>
                <label for="restrictcomments1">No</label>
                </span></div>
				<div class="disabled_radio_button">
				<h3 class="heading4">Price Tag Polling </h3>
				<p class="font_14px" style="float:left; margin-right:25px;">Opt in or out of our 'Price Tag Polls'.</p>
                 <form:radiobutton path="optInPriceTag" id="optin" value="Y" disabled="true"  onclick="javascript:saveoptions('saveoptins.cyt');"/>
                <label for="optin">Opt In</label>
                 <form:radiobutton path="optInPriceTag" id="optin1" value="N"  disabled="true" onclick="javascript:saveoptions('saveoptins.cyt');"/>
                <label for="optin1">Opt Out</label>
                </div>
				<div id="email_notification">
				<h3 class="heading4">Email Notification</h3>
				<p class="font_14px" >Decide when you would like to receive emails from inCyyte.</p>
				<div class="poll_setting">
				Email me when someone sends me a poll
				<span>
                <form:radiobutton path="notifypolls" id="notifypolls" value="Y"  onclick="javascript:saveSettingsOptions('savenotifypolls.cyt', 'Y');"/>
                <label for="notifypolls">Yes</label>
                <form:radiobutton path="notifypolls" id="notifypolls1" value="N"  onclick="javascript:saveSettingsOptions('savenotifypolls.cyt', 'N');"/>
                <label for="notifypolls1">No</label></span>
				</div>
				
				<div class="disabled_radio_button">
				Let me know when I have a new follower
				<span>
                <form:radiobutton path="notifyFollower" id="notifyfollowers" value="Y"  disabled="true"  onclick="javascript:saveoptions('savenotifyfollowers.cyt');"/>
                <label for="notifyfollowers">Yes</label>
                <form:radiobutton  path="notifyFollower" id="notifyfollowers1" value="N"  disabled="true" onclick="javascript:saveoptions('savenotifyfollowers.cyt');"/>
                <label for="notifyfollowers1">No</label></span>
				</div>
				
				<div class="disabled_radio_button">
				Email me news
				<span>
                <form:radiobutton path="notifyNews" id="notifynews" value="Y" disabled="true"  onclick="javascript:saveoptions('savenotifynews.cyt');"/>
                <label for="notifynews">Yes</label>
                <form:radiobutton path="notifyNews" id="notifynews1" value="N" disabled="true"  onclick="javascript:saveoptions('savenotifynews.cyt');"/>
                <label for="notifynews1">No</label></span>
				</div>
				
				<div id="email_notification">
				<h3 class="heading4">De-activate Your Account</h3>
				<p class="font_14px" >This will deactivate your account and log you out.</p>
				<p class="font_14px" >Your account will be reactivated if you log back in again in the future. <a href="javascript:deactivateAccount('deactivateacc.cyt');" title="Deactivate" id="deactivate" class="button_gray" style="width:160px; margin-top:-30px; float:right;"> <span class="deactive_bot deactive_bot_ie8">DEACTIVATE NOW</span></a></p>
				</div>
				
				</div>
            </div>
            </form:form>
            <!--- Polling Ends---->
          </div>
        </div>
      </article>
      <!--content ends -->
    </div>
  </div>
  <!-- include footer -->
  <jsp:include page="../common/includes/footer.jsp" />
    <%-- Conform popup --%>
  <div class="edit_pro_pop" style="display: none;">
      <div class="edit_pro_pop_bg">
          <div class="edit_pro_pop_txt">
             <span id="dynamicText"></span>
          </div>
          <div style="height:auto; margin-left: 30px; float: left; margin-top: 25px;"><a class="poll_button1" href="#" style="width:170px;" id="ok" ><span class="poll_button_red ">Ok</span></a></div>
          <div style="height:auto; margin-left: 10px; float: left; margin-top: 25px;"><a class="poll_button1" href="#" style="width:170px;" id="closepop" ><span class="poll_button_red ">Cancel</span></a></div>
      </div>
  </div>
    <%-- Deactive PopUp --%>
    <div class="deactive_pop" style="display: none;">
        <div class="deactive_pop_bg">
            <div class="deactivte_txt">
                Your account has now been de-activated.<br>
                We're sorry to see you leave..<br>
                You will no longer be able to create or send any inCyyte polls or messages.
                To re-activate your account simply login using your email and password then select "reactivate".
               </div>
            <div style="height:100px; margin-top: 25px;"><a class="poll_button1" href="${pageContext.request.contextPath}/welcome.cyt" style="width:180px;" id="previewButton" ><span class="poll_button_red ">Thank You</span></a></div>
        </div>
    </div>
     <%--uniquepagename PopUp --%>
     <div class="edit_Uniquepagename_pop" style="display: none;">
      <div class="edit_Uniquepagename_pop_bg">
          <div class="edit_Uniquepagename_pop_txt">
             Once saved this name cannot be changed
          </div>
          <div style="height:auto; margin-left: 30px; float: left; margin-top: 25px;"><a class="poll_button1" href="#" style="width:170px;" id="okForUniqPageName" ><span class="poll_button_red ">Ok</span></a></div>
          <div style="height:auto; margin-left: 10px; float: left; margin-top: 25px;"><a class="poll_button1" href="#" style="width:170px;" id="closepopForUniqPageName" ><span class="poll_button_red ">Cancel</span></a></div>
      </div>
  </div>
    
    <script type="text/javascript">
        $("#closepop").click(function(){
            $.fn.colorbox.close();
        });
        
        $("#okForUniqPageName").click(function(){
        	$.fn.colorbox.close();
        	SaveUniquePageName();
        });
        $("#closepopForUniqPageName").click(function(){
            $.fn.colorbox.close();
        });
    </script>
    <script type="text/javascript" src="ui/js/googleanalytics.js"></script>
</body>
</html>
