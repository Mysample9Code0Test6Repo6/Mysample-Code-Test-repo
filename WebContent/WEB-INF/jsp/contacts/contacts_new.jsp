<%@ include file="/WEB-INF/jsp/common/include.jsp" %>
<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="content-type" content="text/html; charset=UTF-8">
<link rel="shortcut icon" href="favicon.ico" />

<title>inCyyte - Invite People</title>
<meta charset="utf-8">
<link rel="stylesheet" href="ui/css/reset.css">
<link rel="stylesheet" href="ui/css/layout.css">
<link rel="stylesheet" href="ui/css/buttons.css"  media="screen">
<link rel="stylesheet" href="ui/css/style_login.css">
<link rel="stylesheet" href="ui/css/accordionmenu.css" type="text/css">
<link rel="stylesheet" href="ui/css/icons_sprites.css">
<link rel="stylesheet" href="fonts/fonts_stylesheet.css">
<script src="ui/js/jquery-1.7.1.js"></script>
<script src="ui/js/jquery.color.js"></script>
<script src="ui/js/search_script.js"></script>
<!-- Left Navigation script starts here -->
<script src="ui/js/left_menu.js"></script>
       <script type="text/javascript" src="ui/js/jquery-1.5.1.min.js"></script>
    	<script type="text/javascript" src="ui/js/jquery-ui-1.8.13.custom.min.js"></script>
    	<script src="ui/modal/colorbox/jquery.colorbox.js"></script>
    	<script type="text/javascript" src="ui/js/external/jquery.bgiframe-2.1.2.js"></script>
    	<script type="text/javascript" src="ui/js/communicator.js"></script>
    	<script type="text/javascript" src="ui/js/contact.js"></script>
    	<script type="text/javascript" src="ui/js/jquery.form.js"></script>  
<link rel="stylesheet" href="ui/css/ui.progress-bar.css" type="text/css"/>
<link rel="stylesheet" href="ui/modal/colorbox.css">
<link rel="stylesheet" href="ui/css/ratingbar.css" type="text/css"/>
<script src="ui/js/jquery_profile_rating.js" type="text/javascript"></script>
<script src="ui/js/star_rating.js" type="text/javascript"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/json_parse.js"></script>
    	
<!-- Left Navigation script ends here -->
<!--- placeholder Starts----->
<script type="text/javascript" src="ui/js/IE-placeholder.js"></script>
<script type="text/javascript">
$(document).ready(function () {
	var importFromEmails = $('#importFrom').val();
	if(importFromEmails == 'importEmails'){
		$("#importEmails").css("display","");
		$("#invite_poeple").css("display","none");
	}
	
	$('#uploadCsvFileButton').click(function () {
		$("#errorForLogoFormat").css("display","none");
		$(".errorLabel").text("");
		parent.$.fn.colorbox({'href':'div#logouploadCSV', 'open':true, 'inline':true});
	});

	$("#browseLogoImage").click(function(){
		$('#uploadedDocFile').click();
	});
	
	//to display file names
	$('#uploadedDocFile').change(function () {
		var pName = $(this).val().substring(12,25).concat("...");
		$('#logoFileName').text(pName);
	});

	$('#logoUploadButton').click(function () {
		uploadLogoFunc();
	});
	
});
</script>
</head>
<%String importFrom = (String) request.getParameter("importFrom");%>
<input type="hidden" id="importFrom" value="<%=importFrom%>"/>
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
            <!-- Invite poeple Start -->
            <div id="invite_poeple" style="display: ''">
              <h4>Invite <span>People</span></h4>
              <p>Add one or more email addresses separated by a semicolon or comma</p>
              
              	<form:form  id="addContactForm"  commandName="addContactForm" method="post" >  
                <form:input path="email" id="email" placeholder="Friends email address" onFocus="this.placeholder = ''" onBlur="this.placeholder = 'Friends email address'" />
                      <span id="singlemaileroor" class="invalid" style="display:none;padding-left:24px;	color:#ec3f41;">Enter a valid email addresses</span>
                      <span id="invalidemail" class="invalid" style="display:none;padding-left:24px;	color:#ec3f41;">one of the email addresses is incorrect </span>
                <span id="sameemailmessage" class="invalid" style="display:none;padding-left:24px;	color:#ec3f41;">one of the email addresses is same as yours </span>
                <span id="existingContact" class="invalid" style="display:none;padding-left:24px;	color:#ec3f41;">one of the email address is already a contact of yours </span>
              	</form:form	>	
              <a href="#" title="Invite!" id="InviteFrends" class="button_green1" style="margin-top:28px; width:100px; float:right;"> <span class="title_green1">INVITE!</span></a> 
           </div> <!-- Invite poeple Ends-->
           
           
           <!-- Import Emails  Start -->
            <div id="importEmails" style="display:none">
              <h4>Import <span>People</span></h4>
              <p>You can add multiple email addresses to your contacts from Outlook, Hotmail e.t.c  </p>
              <c:out value="${emailsToImport}"></c:out>
              	<form:form  id="addImportEmailsForm"  commandName="addContactForm" method="post" >
              	<form:input path="uploadedDocFile" type="file" id="uploadedDocFile" style="display: none;"/>
              	<form:hidden path="fileName" id="fileName"/>
                <form:textarea  path="email" id="importEmail" placeholder="Paste multiple email addresses here (e.g. joseph@incyyte.com, tim@incyyte.com) " onFocus="this.placeholder = ''" onBlur="this.placeholder = 'Paste email addresses here separated with space or coma'" value="${emailsToImport}" ></form:textarea> 
           	    <span id="singlImportEmaileroor" class="invalid" style="display:none;padding-left:24px;	color:#ec3f41;">Enter a valid email addresses</span>
                <span id="invalidImportEmail" class="invalid" style="display:none;padding-left:24px;	color:#ec3f41;">one of the email addresses is incorrect </span>
                <span id="sameImportEmailmessage" class="invalid" style="display:none;padding-left:24px;	color:#ec3f41;">one of the email addresses is same as yours </span>
                <span id="existingImportEmail" class="invalid" style="display:none;padding-left:24px;	color:#ec3f41;">one of the email address is already a contact of yours </span>
              	</form:form	>	
              <a href="javascript:importEmails();" title="Import email to Contacts" id="importEmail" style="margin-top:28px; width:100px; float:right;"> IMPORT!</a>  
              <a title="Import email to a Group" id="displayGroupList" style="margin-top:28px; width:120px; float:right;">IMPORT TO A GROUP | </a>
              <a href="javascript:void(0);" id="uploadCsvFileButton" class="button_green1" style="margin-top:28px; width:170px; float:left;"> <span class="title_green1">UPLOAD .CSV FILE!</span></a>  
           </div> 
           <!-- Import Emails Ends-->
           
            <!-- Invite poeple success Start -->
            <div id="invite_poeple_sucess" style="display: none">
              <h4><span>Hooray!</span> <span style="font:26px/26px 'bariol_regularregular', 'ie8_bariol_regular'; color:#fff;">We&#32;have invited your friends to join inCyyte.</span></h4>
              <a href="./contacts_new.cyt" title="INVITE MORE PEOPLE" id="" class="button_green1" style="margin-top:28px; width:200px; float:right;"> <span class="title_green1 invite_people_ie8">INVITE MORE PEOPLE</span></a> </div>
            <!-- Invite poeple success Ends-->
            
              <!-- Import emails success Start -->
            <div id="import_emails_sucess" style="display: none">
              <h4><span>Hooray!</span> <span style="font:26px/26px 'bariol_regularregular', 'ie8_bariol_regular'; color:#fff;">We&#32;have imported all emails to your contacts.</span></h4>
              <a  href="./contacts_new.cyt?importFrom=importEmails" title="INVITE MORE POEPLE" id="" class="button_green1" style="margin-top:28px; width:200px; float:right;"> <span class="title_green1 invite_people_ie8">IMPORT MORE EMAILS</span></a> </div>
            <!-- Import emails success Ends-->
               
            <%-- Upload  Csv --%>
		<div style="display:none;">
			<div id="logouploadCSV">
			<div id="add_files" >
                <div id="modal_media_icon">
                    <ul>
      					 <li id="modal_docs"><a href="#" alt="Docs" title="Docs" class="active" ></a></li>
                    </ul>
                </div>

                <div>
					<div id="add_photos" class="c_add_photos" style="height: auto;">
                        <table width="522" border="0" cellspacing="0" cellpadding="0">
                            <tr>
                                <td class="heading1" colspan="2">Add CSV File</td>
                            </tr>
                            <tr>
                                <td valign="top" align="left" width="30%">
                                    <table width="100%" border="0" cellspacing="0" cellpadding="0">
                                        <tr>
                                            <td width="30%" >
                                                <div class="fileInputs">
                                                    <div id="browseLogoImage" class="button_gray"
                                                         style=" width:140px; float:left; margin-bottom:20px; position: absolute; top: 0px;left: 0px; z-index: 1;">
                                                        <span class="title_gray">BROWSE</span></div>
                                                </div>
                                            </td>
                                            <td colspan="2" class="font_16px"><br><span style="float: left;">File Name:</span> <span id="logoFileName" style= "float: left; margin-left: 10px;" ></span></td>
                                        </tr>
                                        <tr>
                                            <td colspan="3">&nbsp;</td>
                                        </tr>
                                        <tr valign="bottom" style="display: none;" id="errorForLogoFormat">
                                         <span class="errorLabel"></span>
                                        </tr>
                                       <tr valign="bottom" style="position: absolute;z-index: 200; float: left; margin-left: 410px; margin-top: 140px;">
                                            <td colspan="3" align="center"><img src="ui/images/indicator-loader.gif" id="imageLoaderLogo"
                                                                                width="32" height="32"
                                                                                style="padding:8px 0 0 0; margin-bottom:20px 0 100px;display: none;"></td>
                                        </tr>
                                    </table>
                                </td>
                            </tr>
                           <!--  <tr>
                                <td>
                                    <div class="upload_photo_searchbox" style="margin-bottom:0">
                                        <p class="sort_by_text">Search Google images</p>
                                        <div class="searchmain">
                                            <div>
                                                <input type="text" name="search" id="search_new_logo">
                                                <input type="button" id="searchSubmit_new_logo" value=""/>
                                            </div>
                                        </div>
                                    </div>

                                </td>
                            </tr> -->
                            <tr>
                                <table border="0" cellspacing="0" cellpadding="0" style="margin-top: 15px;">
                                    <tr>
                                        <td valign="top">
                                            <!-- This is used to display Google images -->
                                            <div id="logoGoogleImagesScroll"
                                                 style="width:319px; height:171px; overflow-x:hidden; overflow-y:auto; margin-right: 10px;">
                                            </div>
                                        </td>
                                        <td align="right">
                                            <div><img id="logoImgSelect" class="upload_photos_container_big_img" src="ui/images/uploading_big_img.png"></div>
                                        </td>
                                    </tr>
                                </table>

                            </tr>
                            <!-- <tr>
                                <td>
                                    <div class="upload_photo_add_more"><a href="javascript:void(0);" id="show_more_images_logo"> + Show more images </a></div>
                                </td>
                            </tr> -->
                            <!-- <tr>
                                <td>
                                <table>
                                    <tr>
                                       <td class="font_18px" width="150" align="right">Add Url</td>
                                       <td class="pollfrom" width="400" align="right"><input type="text" id="logoLinkText" onKeyup="isImageLinkValid('logoLinkText');"/>
                                       <br><span class="errorLabel" id="logoLinkTextError" style="font-weight: normal;float: left;margin-left: 42px;"></span><td>
									</tr>
                                </table>
                                </td>

                                </td>
                            </tr> -->
                            <tr>
                                <td colspan="2" valign="bottom">

                                    <div title="Upload Now" id="logoUploadButton" class="button_red"
                                         style="width:140px;margin-top: 10px; float:right;"><span class="title_red1">UPLOAD NOW</span></div>
                                </td>
                            </tr>
                        </table>
					</div>
				</div>
			</div>
			</div>
		</div>
<%--END CSV UPLOAD --%>

<%-- START UPLOAD INTO GROUP --%>
<div style="display: none;">
    <div id="groupList">
        <div id="share_contacts">
            <h3 class="heading1" style="padding:2px 0 8px 0px;">Add Contacts to Group</h3>

            <div id="scrollbar1">
                <div class="scrollbar">
                    <div class="track">
                        <div class="thumb">
                            <div class="end"></div>
                        </div>
                    </div>
                </div>
                <div class="viewport">
                    <div class="overview">

                        <table width="380" border="0" cellspacing="0" cellpadding="0" id="contact_list">
                            <tr>
                                <td colspan="12" class="select_all">Enter your Group Name: </td>
                            </tr>
                        </table>
                        <div style="width:380px; height:4px;"></div>
                        <!-- <div style="width:400px; height:200px; overflow:auto;"> -->
                        <!-- 
                        <div id="contactScroll" style="width:380px; height:200px; overflow-x:hidden; overflow-y:auto;">
                            <table width="380" border="0" id="groupsTable"  cellspacing="0" cellpadding="0">
                            <span id='groupRadio'>
                            </table>
                        </div>
                         -->
                    </div>
                </div>
            </div>
            <div style="width:380px; height:4px;"></div>
            <table width="380" border="0" cellspacing="0" cellpadding="0">
                <tr id="NewGrpRow" style="display:none;">
                	<td colspan="2">
                		<span id="gname_error" style="color:red; margin-left:70px;"></span></br>                		
                		<span style="float:right;"><input type="text" name="newGrp" id="newGrpName" size="55" style="height: 30px;" />
                		<!--img src="./ui/images/delete_blue.png" alt="remove" id="removeNewGrpName" style="padding-top:5px;" valign="bottom"-->
                		</span>
                		
                		
                	</td>
                </tr>
                <tr>
                <!-- 
                    <td valign="bottom" width="75%"><label class="panelLink" id="newGroup"><span class="importSpan"
                                                                                                      title="New Group">New Group</span></label>
                    </td>
                  -->   
                    <td>
                        <button type="button" title="Import" id="add_email_btn" class="button_green1"
                                onclick="importEmailsToGroup();"
                                style="width:150px; margin:20px 0 0 100px; float:right;"><span class="title_green1">Import to Group</span>
                        </button>
                    </td>
                </tr>
            </table>
        </div>
    </div>
</div>
<%-- END UPLOAD INTO GROUP--%>

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
