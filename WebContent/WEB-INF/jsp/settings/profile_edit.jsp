<%@ include file="/WEB-INF/jsp/include.jsp"%>
<%@page import="com.incyyte.app.web.SessionKeys"%>
<%@page import="com.incyyte.app.domain.User"%>
<%@page import="com.incyyte.app.web.model.UserModel"%>
<%@page import="org.apache.commons.lang.StringUtils"%>
<%@page import="java.util.Date"%>

<html lang="en">
<head>
<meta http-equiv="content-type" content="text/html; charset=UTF-8">
<link rel="shortcut icon" href="favicon.ico" />

<title>inCyyte - Edit My Profile</title>
<meta charset="utf-8">
<link rel="stylesheet" href="ui/css/reset.css">
<link rel="stylesheet" href="ui/css/iestyle.css">
<link rel="stylesheet" href="ui/css/layout.css">
<link rel="stylesheet" href="ui/css/buttons.css"  media="screen">
<link rel="stylesheet" href="ui/css/style_login.css">
<link rel="stylesheet" href="ui/css/accordionmenu.css" type="text/css">
<link rel="stylesheet" href="ui/css/icons_sprites.css">
<link rel="stylesheet" href="fonts/fonts_stylesheet.css" type="text/css" media="screen">
<link rel="stylesheet" href="ui/css/style_help.css">
<link rel="stylesheet" href="ui/css/jquery.selectbox.css" type="text/css" />

<script src="ui/modal/colorbox/jquery.colorbox.js"></script>
<script src="ui/js/jquery.color.js"></script>
<link rel="stylesheet" href="ui/css/ratingbar.css" type="text/css" />
<link rel="stylesheet" href="ui/css/form_elements.css" type="text/css" />
<link rel="stylesheet" type="text/css" href="css/accountry.0.2/jquery.autocomplete.css" />
<link rel="stylesheet" type="text/css" href="css/accountry.0.2/jquery.autocomplete.country.css" />
    <link rel="stylesheet" href="ui/modal/colorbox.css">
<link rel="stylesheet" href="ui/css/themes/base/jquery.ui.all.css">

<script type="text/javascript" src="ui/js/jquery-ui-1.8.16.custom.min.js"></script>
<script type="text/javascript" src="ui/js/jquery-1.8.3.min.js"></script>
<script src="ui/js/accordian/jquery-1.6.2.js"></script>
<script src="ui/js/jquery-1.7.1.js"></script>
<script src="ui/js/jquery-1.4.2.min.js"></script>
<script type="text/javascript" src="js/jquery.form.js"></script>
<script src="ui/js/settings/profile.js"></script>
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
<script src="ui/js/home/signup_validations.js"></script>

<!-- Left Navigation script ends here -->
<script src="ui/js/jquery.selectbox-0.2.js"></script>
<script type="text/javascript">
		$(function () {
			$("select").selectbox();
            $("select").selectbox("disable");
		});
</script>
<script src="ui/js/modernizr-1.7.min.js"></script>
 <script type="text/javascript" src="ui/js/jquery.corner.js"></script>
 <script src="ui/js/googleSearch.js"></script>

<script type="text/javascript" src="ui/js/jquery.form.js"></script>
<script type="text/javascript"> 
function validateFields(UID_Name,value){
		if (clientValidation(UID_Name)){
	    	return true;
		}
		else
		{
			return false;
		}
}
function getContextPath() {
	return "<%=request.getContextPath()%>";
}</script>
    <script type="text/javascript">
        $(document).ready(function(){
           // $('.sbHolder').corner("15px sc:#e5e4e0");
           $(".sbHolder").corner("16px cc:#e5e4e0 tr br").corner("16px cc:#e5e4e0 tl bl");
           
           $('#searchSubmit_new_profile').click(function () {
        	   googleImageSearchForProfilePic();
        	});
          
         });
    </script>
     <script type="text/javascript">
 function googleImageSearchForProfilePic(){
   		$('#googleSearchTrigger').val(1);
        var searchValue = document.getElementById("search_new_profile");
        var searchLoad = document.getElementById("profileGoogleImagesScroll");
        var searchSelect = "profileImgSelect";
        loadGoogleSearchImages(searchValue, searchLoad, searchSelect);
 }
        var trigger = 1;
        //Following function is called when we call Show more images
       function showImages() {
    	var trigger = $('#googleSearchTrigger').val();
        imageSearch.gotoPage(trigger);
        $('#googleSearchTrigger').val(++trigger);
      }
        function loadImage(selectedImgContainer, url) {
            document.getElementById(selectedImgContainer).setAttribute("src", url);
            var index = url.lastIndexOf("/") + 1;
            var fileName = url.substr(index);
            document.getElementById("incyyte_photo_search_file").value = fileName;
            document.getElementById("incyyte_photo_search_url").value = url;
        }
    </script>
    <script type="text/javascript">
        google.load('search', '1');
        var imageSearch;
        function loadGoogleSearchImages(searchValue, searchLoad, searchSelect) {
            //Following function is executed when we click on Search
            var search_new = searchValue.value;
            searchLoad.innerHTML = "";
            imageCall(search_new);
            function imageCall(search) {
                imageSearch = new google.search.ImageSearch();
                imageSearch.setResultSetSize(6);
                // Set searchComplete as the callback function when a search is complete.
                // The imageSearch object will have results in it.
                imageSearch.setSearchCompleteCallback(this, searchComplete, [imageSearch]);
                imageSearch.execute(search);
            }

            function searchComplete() {
                if (imageSearch.results && imageSearch.results.length > 0) {
                    var results = imageSearch.results;
                    var imagesContainer;
                    imagesContainer = '<table  width="294" border="0" cellspacing="0" cellpadding="0" style="margin-right: 10px;">';
                    var count = 1;
                    for (var i = 0; i < results.length; i++, count++) {
                        var result = results[i];
                        //Start of Each row start the row tag
                        if (i % 3 == 0) {
                            imagesContainer = imagesContainer + '<tr>';
                        }
                        imagesContainer = imagesContainer + '<td><div><a><img onClick="javascript:loadImage(\'' + searchSelect + '\',this.src);" class="upload_photos_thumb_img" src="' + result.url + '"></a></div></td>';
                        //End of Each row close the row tag
                        if (count % 3 == 0) {
                            imagesContainer = imagesContainer + '</tr>';
                        }
                    }
                    imagesContainer = imagesContainer + '</table>';
                    searchLoad.innerHTML = searchLoad.innerHTML + imagesContainer;
                }
            }
        }
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
           <h1>Edit Profile</h1>
            <nav>
              <script src="ui/js/left_menu.js"></script>
              <script src="ui/includes/leftmenu_account_setting.js"></script>
            </nav>
            <div class="hline"></div>
			<jsp:include page="../include/profileOverview.jsp" />
            <br>
            <h1 class="font_18px">Fill In your:</h1>
            <ul class="redtext">
              <li> <span> >> </span> <a href="./editProfile.cyt">Profile Picture</a> </li>
              <li> <span> >> </span> <a href="./editProfile.cyt">Profile Description</a></li>
            </ul>
            <div id="advert"><img src="ui/images/advert.png" alt="Advert"></div>
          </div>
          <div class="line"><span></span></div>
          <div class="grid_17">
            <!--- Edit Profile Starts---->
            <div id="edit_profile_setup" >
          <form:form id="editProfileForm" commandName="editProfileForm"  method="post" enctype="multipart/formdata">
          	<% UserModel userModel = (UserModel)request.getAttribute("editProfileForm");
          	   User user = (User) session.getAttribute("user"); %>
              <form:hidden path="incyyteCategory" id="hid_incyyteCategory" />
              <form:hidden path="occupationId" id="customerIDFromJS" />
              <input type="hidden" id="occId" name="occId" value="" />
              <input type="hidden" id="religionId" name="religionId" value="" />
              <input type="hidden" id="googleSearchTrigger"/>

			  <input type="hidden" id="incyyte_photo_search_file"/>
			  <input type="hidden" id="incyyte_photo_search_url"/>
			   <form:hidden path="searchedFileNameProfile" id="searchedFileNameProfile"/>
			   <form:hidden path="searchedFileURLProfile" id="searchedFileURLProfile"/>
			  

              <div id="profile_picture">
              
	            		
                <table width="100%" border="0" cellspacing="0" cellpadding="0">
                  <tr>
                    <td valign="top" class="heading4">Picture</td>
                    <td valign="top">&nbsp; </td>
                    <td valign="top" class="heading4 my_insight_allignment_ie8">Brief Description</td>
                  </tr>
                  <tr>
                    <td width="20%" rowspan="3" valign="top">
                    	<%
                    		if(StringUtils.isNotBlank(userModel.getProfilePicture())){%>
                    			<img src="<%=userModel.getProfilePicture()%>" alt="Profile Picture" width="111" height="111" class="photoframe">
                    	<%	} else{ %>
                    			<img src="ui/images/profile_picture.png" alt="Profile Picture alt">
                        <%
                    		}
                    	%>
                    	
                    </td>
                      <% if(StringUtils.isNotBlank(userModel.getProfilePicture())){%>
                          <td valign="top" height="33" style="float: left;margin-top: 35px;display: block;"><a onClick="deleteLogo('true')"  href="javascript:void(0)" title="DELETE PHOTO"  class="button_gray" style="width:210px; float:left; margin-bottom:20px;"> <span class="title_red">REPLACE PHOTO</span></a></td>
                      <%}else{%>
                      <td valign="top" height="33" style="float: left;margin-top: 35px;"><a id="uploadlogolink" class='inline' href="#add_files" title="UPLOAD A NEW PHOTO" id="" class="button_gray" style="width:210px; float:left; margin-bottom:20px;"> <span class="title_gray edit_profile_upload_photo_ie8">UPLOAD A NEW PHOTO</span></a><br />
                          <div  class="maximum_size_ie8 maximum_size_ie9 maximum_size_ie10" style="margin-top: -10px;margin-left: 12px;float: left;">Maximum size of 700k. JPG, GIF, PNG.</div>
                      </td>
                       <%}%>
                      <td width="41%" rowspan="3" valign="top" class="my_insight_allignment_ie8"  ><label>Write a short statement below</label>
                    <form:textarea id="opinion" path="opinion" cols="" readonly="true" class="readonly_input" style="font-size:16px; padding:15px;"/>
                      <br>
                       <p id="invalidopinion"/>
                       <p id="validopinion"/>
                    
                    <p>This will be shown on your profile.<a id="saveopinionlink" class="edit" href=" javascript:editSaveField('opinion','opinion','saveopinion.cyt');" ><span>Edit</span></a>
                    </td>
                  </tr>
                </table>
              </div>
              	<div style="display:none;">
			<div id="add_files" >
                <div id="modal_media_icon">
                    <ul>
                        <li id="modal_photos"><a href="#" alt="Photos" title="Photos" class="active"></a></li>
                    </ul>
                </div>

                <div>
					<div id="add_photos" class="c_add_photos">
                        <table width="auto" border="0" cellspacing="0" cellpadding="0">
                            <tr>
                                <td class="heading1" colspan="2">Add Photos</td>
                            </tr>
                            <tr>
                                <td valign="top" align="left" width="30%">
                                    <table width="100%" border="0" cellspacing="0" cellpadding="0">
                                        <tr>
                                            <td width="30%" >
                                                <div class="fileInputs">
                                                    <div id="incyyte_browse_photos" class="button_gray"
                                                         style=" width:140px; float:left; margin-bottom:20px; position: absolute; top: 0px;left: 0px; z-index: 1;">
                                                        <span class="title_gray">BROWSE</span></div>
                                                </div>
                                            </td>
                                            <td colspan="2" class="font_16px"><br><span style="float: left;">File Name:</span> <span id="incyyte_photo_file_name" style= "float: left; margin-left: 10px;" ></span></td>
                                        </tr>
                                        <tr>
                                            <td colspan="3">&nbsp;</td>
                                        </tr>
                                        <tr valign="bottom" style="display: none;" id="incyyte_photos_error_msg">
                                            <td colspan="3"><span class="errorLabel">Please upload the correct image format (jpg, gif, ..)</span></td>
                                        </tr>
                                        <tr valign="bottom" style="display: none;" id="incyyte_photos_error_msg2">
                                            <td colspan="3"><span class="errorLabel">The uploaded image exceeds the maximum allowed size(2 MB)</span></td>
                                        </tr>
                                        <tr valign="bottom" style="position: absolute;z-index: 200; float: left; margin-left: 410px; margin-top: 140px;">
                                            <td colspan="3" align="center"><img src="ui/images/indicator-loader.gif" id="imageLoader"
                                                                                width="32" height="32"
                                                                                style="padding:8px 0 0 0; margin-bottom:20px 0 100px;"></td>
                                        </tr>
                                    </table>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <div class="upload_photo_searchbox" style="margin-bottom:0">
                                        <p class="sort_by_text">Search Google images</p>
                                        <div class="searchmain">
                                            <div>
                                                <input type="text" name="search" id="search_new_profile"  onKeydown="Javascript: if (event.keyCode==13 ) googleImageSearchForProfilePic();" >
                                                <input type="button" id="searchSubmit_new_profile" value=""/>
                                            </div>
                                        </div>
                                    </div>

                                </td>
                            </tr>
                            <tr>
                                <table border="0" cellspacing="0" cellpadding="0" style="margin-top: 15px;">
                                    <tr>
                                        <td valign="top">
                                            <!-- This is used to display Google images -->
                                            <div id="profileGoogleImagesScroll"
                                                 style="width:319px; height:171px; overflow-x:hidden; overflow-y:auto; margin-right: 10px;">
                                            </div>
                                        </td>
                                        <td align="right">
                                            <div><img id="profileImgSelect" class="upload_photos_container_big_img" src="ui/images/uploading_big_img.png"></div>
                                        </td>
                                    </tr>
                                </table>

                            </tr>
                            <tr>
                                <td>
                                    <div class="upload_photo_add_more"><a href="javascript:showImages();"> + Show more images </a></div>
                                </td>
                            </tr>
                            <tr>
                                <td colspan="2" valign="bottom"><span class="licence">You must have the licence to use this image</span>

                                    <div title="Upload Now" id="incyyteUploadPhotoButton" class="button_red"
                                         style="width:140px; float:right;"><span class="title_red1">UPLOAD NOW</span></div>
                                </td>
                            </tr>
                        </table>
					</div>
				</div>
			</div>
		</div>
				<form:input path="uploadedLogo"  type="file" id="incyyte_photo_file_input" style="display:none;" />
				<form:hidden path="uploadName" id="file_name_tst"/>
				<form:hidden path="makeDefault" id="mkDefault"/>
              <div id="profile_name">
             
                <table width="100%" border="0" cellspacing="0" cellpadding="0">
                  <tr>
                    <td valign="top" class="heading4">Name</td>
                    <td valign="top">&nbsp;</td>
                  </tr>
                  <tr>
                    <td width="74%"><label>User Name</label>
                      <form:input id="username" path="username" readonly="true" class="readonly_input edit_profile_input_ie8" />
                      <br>
                      <p id="invalidUname"/>
                      <p id="validUname"/>
                      <p>This will be shown on your profile<p>
                      </td>
                    <td width="24%" valign="middle">
                    	<p><a id="saveusernamelink" class="edit" href="javascript:editSaveField('Uname','username','saveusername.cyt');"><span>Edit</span></a></p>
                    </td>
                  </tr>
                  
                  <tr>
                    <td width="74%"><label>First Name</label>
                      <form:input id="firstname" path="firstname" readonly="true" class="readonly_input edit_profile_input_ie8"  />
                      <br>
                      <p id="invalidfirstName"></p>
                       <p id="validfirstName"></p></td>
                    <td width="24%">
                    	<p><a id="savefirstnamelink" class="edit" href="javascript:editSaveField('firstName','firstname','savefirstname.cyt');"><span>Edit</span></a></p>
                    </td>
                  </tr>
                  
                  <tr>
                    <td width="74%"><label>Last Name</label>
                      <form:input id="lastname" path="lastname" readonly="true" class="readonly_input edit_profile_input_ie8" />
                      <br>
                      <p id="invalidlastName"/>
                      <p id="validlastName"/></td>
                    <td width="24%">
                    	<p><a id="savelastnamelink" class="edit" href="javascript:editSaveField('lastName','lastname','savelastname.cyt');"><span>Edit</span></a></p>
                    </td>
                  </tr>
                </table>
              </div>
              <div id="more_abt_me">
                <h3 class="heading4" >More About Me</h3>
                <p>This is private but it helps us give you a better experiece while you are using inCyyte.</p>
                <table width="100%" border="0" cellspacing="0" cellpadding="0">
	                <%--  <tr>
	                   <td width="74%"><label>Country</label>
	                     <form:input id="ac_country" path='country' style="padding-left:32px;" readonly="true" class="readonly_input edit_profile_country_input_ie8" />
	                     <br>
	                     <p></p></td>
	                   <td width="24%">
	                   	<p><a id="savecountrylink" class="edit" href="javascript:editSaveField('country','country','savecountry.cyt');"></a></p>
	                   </td>
	                 </tr> --%>
                   <tr>
                       <td><label>Country</label>                                           
              			<form:select path="ac_country" id="ac_country">
							<c:forEach var="item" items="${countries}">
              					<form:option value="${item.key}"  label="${item.value}"/>
               				</c:forEach>										
						</form:select>
						<span id="country_error" class="error" style="font-size: small;color:red;"></span>
                      </td>
                  </tr>
                  <tr>
                    <td width="74%"><label>Location/City</label>
                      <form:input id="location" path="postal_area" readonly="true" class="readonly_input edit_profile_input_ie8" />
                      <br>
                      <p id="invalidLocation" />
                      <p id="validLocation" /></td>
                    <td width="24%">
                    	<p><a id="savelocationlink" class="edit" href="javascript:editSaveField('Location','location','savelocation.cyt');"><span>Edit</span></a></p>
                    </td>
                  </tr>
                  
                  <tr>
                    <td width="74%"><label>Post Code *</label>
                      <form:input id="postcode" path="postcode" readonly="true" class="readonly_input edit_profile_input_ie8" />
                      <p>WARNING! Once entered you cannot change your postcode. <a href="#">More Info.</a></p>
                      <p></p>
                      <p id="invalidPostcode" />
                      <p id="validPostcode" /></td>
                    <td width="24%">
                    <%
                    Date postCodeDate =user.getPostCodeDate();
                    Date currentDate = new Date();
                    long diffInDays;
                    if (postCodeDate != null) {
                    	diffInDays = (currentDate.getTime() - postCodeDate.getTime()) / (24 * 60 * 60 * 1000);
                    }else{
                    	diffInDays = 91;
                    }
                     if (diffInDays >= 90){ 
               		%>
                    	<p><a id="savepostcodelink" class="edit" onClick="javascript:editSaveField('Postcode','postcode','savepostcode.cyt');"><span>Edit</span></a></p>
                    <% 
                    } 
                     else {
                    	%>
                     <p><a id="savepostcodelink" style="display:none" class="edit" onClick="javascript:editSaveField('Postcode','postcode','savepostcode.cyt');"><span>Edit</span></a></p>
                    <%  
                    }
                    %>
                    </td>
                  </tr>
                 
                 <tr>
                    <td width="74%"><label>Mobile Number</label>
                      <form:input id="mobile" path="mobile"  readonly="true" class="readonly_input edit_profile_input_ie8"/>
                      
                      <p></p>
					  <p id="invalidMobile" />
					  <p id="validMobile" /></td>
                    <td width="24%" valign="middle">
                    	<p><a id="savemobilelink" class="edit" href="javascript:editSaveField('Mobile','mobile','savemobile.cyt');"><span>Edit</span></a></p>
                    </td>
                  </tr>
                  <tr>
                    <td width="74%"><label>Email *</label>
                      <form:input id="suemail" path="su_email" readonly="true" class="readonly_input edit_profile_input_ie8"/>
                      <p></p>
                      <p id="invalidEmail" />
                      <p id="validEmail"/></td>
                    <td valign="middle"><span style="padding:0px 10px 0px 10px; margin-top: 20px;float: left; ">Default</span>
                    	<P><a id="savesuemaillink" class="edit" href="javascript:editSaveField('Email','suemail','saveemail1.cyt');"><!--span>Edit</span--></a></P>
                    </td>
                  </tr>
                  
                   <tr id="email1tr">
                    <td>
                      <form:input id="email1" path="email1" readonly="true" class="readonly_input edit_profile_input_ie8"/>
                      <p></p>
                      <p id="invalidEmail1" />
                      <p id="validEmail1" /></td>
                    
                    <td valign="top">
                 <%          
                  if(userModel.getEmail1() != null && !userModel.getEmail1().isEmpty()) {
               	 %>
                    <span style="padding:0px 10px 0px 10px;"><a id="makedefaultlink2" href="javascript:makeDefault('2');">Make Default</a></span>
                    <%
                  }
                    %>
                        <P><a id="saveemail1link" class="edit" href="javascript:editSaveField('Email1','email1','saveemail2.cyt');"><span>Edit</span></a></p>
                    </td>
                  </tr>
                <%
                  if(userModel.getEmail2() == null || userModel.getEmail2().isEmpty()) {
                %>
                	  <tr id="addanothertr" >
                      <td height="30" valign="top"><a id="anotherlink" href="javascript:anotherlink();">+ Add Another email</a></td>
                      <td>&nbsp;</td>
                    </tr>
                     <tr id="email2tr" style='display:none;' >
                        <td><form:input id="email2" path="email2" cssClass="edit_profile_input_ie8" />
                         <p id="invalidEmail2" />
                           <p id="validEmail2" />
                        </td>
                         <td valign="bottom">
                    <span style="padding:0px 10px 0px 10px;"><a id="makedefaultlink3" href="javascript:makeDefault('3');">Make Default</a></span>
                     <a id="saveemail2link" class="button_green" href="javascript:editSaveField('Email2','email2','saveemail3.cyt');" style="width:60px; float:right; margin-bottom:8px;"><span class="title_green edit_profile_save_bot_ie8">SAVE</span></a>
                     </td>
                    </tr>
                      <%
                  } else if(userModel.getEmail2() != null && !userModel.getEmail2().isEmpty()) {
               	 %>
               	<tr>
               	  <td><form:input id="email2" path="email2"  readonly="true" class="readonly_input"/>
                       </td>  <td valign="bottom">
                    <span style="padding:0px 10px 0px 10px;"><a id="makedefaultlink3" href="javascript:makeDefault('3');">Make Default</a></span>
                       <P> <a id="saveemail2link" class="edit" href="javascript:editSaveField('email2','email2','saveemail3.cyt');"><span>Edit</span></a> </p>
                      </td>
                    </tr>
                    <%
                  }
                    %>  
                  <tr>
                    <td colspan="2" class="hline" height="10"></td>
                  </tr>
                   <tr>
                    <td><label >Date of Birth</label>
                          </td>
                    <td>&nbsp;</td>
                  </tr>
                 
                  <tr>
                    <td class="font_14px"><table width="90%" border="0" cellspacing="0" cellpadding="0">
                        <tr>
                          <td width="4%" align="center" ></td>
                          <td width="29%" class="edit_profile_date_spacing_ie8"><form:select id="day" path="birthDay" >
                    	<c:forEach var="c" begin="1" step="1" end="31">
                    		<form:option id="${c}" value="${c}"><c:out value="${c}" /></form:option>	
                    	</c:forEach>
                    </form:select ></td>
                          <td width="4%" align="center" ></td>
                          <td width="39%" class="edit_profile_date_spacing_ie8"><form:select id="month" path="birthMonth">
                    		<form:options items="${months}" />
                    </form:select ></td>
                          <td width="4%" align="center"></td>
                          <td width="30%"><form:select id="year" path="birthYear" >
                    	<c:forEach var="c" begin="1900" step="1" end="${birthYearLimit }">
                    		<form:option value="${c}"><c:out value="${c}" /></form:option>	
                    	</c:forEach>
                    </form:select></td>
                        </tr>
                      </table>
                     </td>
                     <td valign="top"><P><a id="savedoblink" class="edit" href="javascript:editSaveField('dob','dob','savedob.cyt');"><span>Edit</span></a></p></td>
                  </tr>
                  
                  <tr>
                    <td><label>Gender</label>
                    <form:select path="gender">
    					<form:options items="${genders}" readonly="true" class="readonly_input" />
					</form:select>
                    <td><P><a id="savegenderlink" class="edit" href="javascript:editSaveField('gender','gender','savegender.cyt');"><span>Edit</span></a></p></td>
                  </tr>
                  
                  <tr>
                    <td><label>Occupation</label>
                    
					
					<form:input id="occupation" path="occupation"  readonly="true" class="readonly_input edit_profile_input_ie8"/>
					</td>
                    <td><P><a id="saveoccupationlink" class="edit" href="javascript:editSaveField('occupation','occupation','saveoccupation.cyt');"><span>Edit</span></a></p></td>
                  </tr>
                  <tr>
                    <td><label>Religion</label>
					
					<form:input id="religion_id" path="religion"  readonly="true" class="readonly_input edit_profile_input_ie8"/>
					</td>
                    <td><P><a id="savereligion_idlink" class="edit" href="javascript:editSaveField('religion_id','religion_id','savereligion.cyt');"><span>Edit</span></a></p></td>
                  </tr>
                  
                  <tr>
                    <td><label>Sexuality</label>
                      <form:select id="sexuality" path="sexuality" readonly="true" class="readonly_input">
    					<form:options items="${sexualities}" />
					</form:select></td>
                    <td valign="middle"><P><a id="savesexualitylink" class="edit" href="javascript:editSaveField('sexuality','sexuality','savesexuality.cyt');"><span>Edit</span></a></p></td>
                  </tr>

                  <tr>
                    <td><label>Income</label>
                      <form:select path="income" readonly="true" class="readonly_input">
    					<form:options items="${incomes}" />
					</form:select>
                      </td>
                    <td valign="middle"><P><a id="saveincomelink" style="margin-bottom: 0px;" class="edit" href="javascript:editSaveField('income','income','saveincome.cyt');"><span>Edit</span></a></p></td>
               	</tr>
                  
                  
               	<tr>
                     <td><label>Ethnicity</label>                     
						<form:select path="ethnicity" readonly="true" class="readonly_input">				                    			
                   			<c:forEach var="item" items="${ethnicities}">
                  					<form:option value="${item.key}"  label="${item.value}"/>
			                </c:forEach>
                   		</form:select>
                 	</td>
                    <td valign="middle"><P><a id="saveethnicitylink" style="margin-bottom: 0px;" class="edit" href="javascript:editSaveField('ethnicity','ethnicity','saveEthnicity.cyt');"><span>Edit</span></a></p></td>
             	</tr>
                  
               	<tr>
                    <td><label>Educational level</label>                     
						<form:select path="educationLevel" readonly="true" class="readonly_input">				                    			
                   			<c:forEach var="item" items="${educationLevels}">
                  					<form:option value="${item.key}"  label="${item.value}"/>
			                </c:forEach>
                   		</form:select>
                 	</td>
                    <td valign="middle"><P><a id="saveeducationLevellink" style="margin-bottom: 0px;" class="edit" href="javascript:editSaveField('educationLevel','educationLevel','saveEducationLevel.cyt');"><span>Edit</span></a></p></td>
               	</tr>
                  
              	<tr>
                    <td><label>Adults in the house hold</label>
                     	<form:select path="adultsInHouseHold" readonly="true" class="readonly_input">				                    			
                  			<c:forEach var="item" items="${adultsInHouseHolds}">
                 					<form:option value="${item.key}"  label="${item.value}"/>
		                	</c:forEach>
                  		</form:select>
                      </td>
                    <td valign="middle"><P><a id="saveadultsInHouseHoldlink" style="margin-bottom: 0px;" class="edit" href="javascript:editSaveField('adultsInHouseHold','adultsInHouseHold','saveAdultsInHouseHold.cyt');"><span>Edit</span></a></p></td>
              	</tr>
                  
                   <tr>
                    <td><label>Children in the house hold</label>
                     	<form:select path="childrenInHouseHold"  readonly="true" class="readonly_input">				                    			
                   			<c:forEach var="item" items="${childrenInHouseHolds}">
                  					<form:option value="${item.key}"  label="${item.value}"/>
			                </c:forEach>
                   		</form:select>
                      </td>
                    <td valign="middle"><P><a id="savechildrenInHouseHoldlink" style="margin-bottom: 0px;" class="edit" href="javascript:editSaveField('childrenInHouseHold','childrenInHouseHold','saveChildrenInHouseHold.cyt');"><span>Edit</span></a></p></td>
                  </tr>
                  
                  
                  
                  
                </table>
              </div>
              <%
              	userModel = (UserModel)request.getAttribute("editProfileForm");
	              String musicClass = "music_choice";
	        		if(userModel.getCategories().contains("Music")) {
	        			musicClass = "music_choice active1";
	        		}
	        		String sportsClass = "sports_choice";
	        		if(userModel.getCategories().contains("Sports")) {
	        			sportsClass = "sports_choice active1";
	        		}
	        		String relClass = "religion_choice";
	        		if(userModel.getCategories().contains("Religion")) {
	        			relClass = "religion_choice active1";
	        		}
	        		String politicsClass = "politics_choice";
	        		if(userModel.getCategories().contains("Politics")) {
	        			politicsClass = "politics_choice active1";
	        		}
	        		String communityClass = "community_choice";
	        		if(userModel.getCategories().contains("Community")) {
	        			communityClass = "community_choice active1";
	        		}
	        		
	        		String relationshipsClass = "relationships_choice";
	        		if(userModel.getCategories().contains("Relationships")) {
	        			relationshipsClass = "relationships_choice active1";
	        		}
	        		String businessClass = "business_choice";
	        		if(userModel.getCategories().contains("Business")) {
                        businessClass = "business_choice active1";
	        		}
	        		String shoppingClass = "shopping_choice";
	        		if(userModel.getCategories().contains("Shopping")) {
	        			shoppingClass = "shopping_choice active1";
	        		}
	        		String healthClass = "health_choice";
	        		if(userModel.getCategories().contains("Health")) {
	        			healthClass = "health_choice active1";
	        		}
	        		String beautyClass = "beauty_choice";
	        		if(userModel.getCategories().contains("Beauty")) {
	        			beautyClass = "beauty_choice active1";
	        		}
	        		
	        		String travelClass = "travel_choice";
	        		if(userModel.getCategories().contains("Travel")) {
	        			travelClass = "travel_choice active1";
	        		}
	        		String foodClass = "food_choice";
	        		if(userModel.getCategories().contains("Food")) {
	        			foodClass = "food_choice active1";
	        		}
	        		String motoringClass = "motoring_choice";
	        		if(userModel.getCategories().contains("Motoring")) {
	        			motoringClass = "motoring_choice active1";
	        		}
	        		String internetClass = "internet_choice";
	        		if(userModel.getCategories().contains("Internet")) {
	        			internetClass = "internet_choice active1";
	        		}
	        		String entertainmentClass = "entertainment_choice";
	        		if(userModel.getCategories().contains("Entertainment")) {
	        			entertainmentClass = "entertainment_choice active1";
	        		}
	        		
	        		String othersClass = "others_choice";
	        		if(userModel.getCategories().contains("Others")) {
	        			othersClass = "others_choice active1";
	        		}
              %>
              <div id="wht_i_like">
                <h3 class="heading4" >What I Like</h3>
				<p>Choose categories that you like so we can send you polls that interest you. You can change this at any time. <BR>(switch off a category by clicking it - turning it white)</p>
				<div class="col_setting colIndent_setting">
                <div id="choice_list">
                  <ul>
					<li ><a class="<%=musicClass %>" id="musiclink" href="javascript:updateCategory('Music','music','musiclink');" alt="Music" title="Music">Music</a></li>
                    <li ><a class="<%=sportsClass %>" id="sportslink" href="javascript:updateCategory('Sports','sports','sportslink');" alt="Sports" title="Sports">Sports</a></li>
                    <li ><a class="<%= relClass %>" id="religionlink" href="javascript:updateCategory('Religion','religion','religionlink');" alt="Religion" title="Religion">Religion</a></li>
                    <li ><a class="<%=politicsClass %>" id="politicslink" href="javascript:updateCategory('Politics','politics','politicslink');" alt="Politics" title="Politics">Politics</a></li>
                    <li ><a class="<%=communityClass %>" id="communitylink" href="javascript:updateCategory('Community','community','communitylink');" alt="Community" title="Community">Community</a></li>
                    <li ><a class="<%=relationshipsClass %>" id="relationshipslink" href="javascript:updateCategory('Relationships','relationships','relationshipslink');" alt="Relationships" title="Relationships">Relationships</a></li>
                  </ul>
                </div>
				</div>
				<div class="col_setting colIndent_setting">
                <div id="choice_list">
                  <ul>
					<li><a class="<%=businessClass %>" id="businesslink" href="javascript:updateCategory('Business','business','businesslink');"  alt="Business" title="Business">Business</a></li>
                    <li><a class="<%=shoppingClass %>" id="shoppinglink" href="javascript:updateCategory('Shopping','shopping','shoppinglink');"  alt="Shopping" title="Shopping">Shopping</a></li>
                    <li><a class="<%=healthClass %>" id="healthlink" href="javascript:updateCategory('Health','health','healthlink');"  alt="Health" title="Health">Health</a></li>
                    <li><a class="<%=beautyClass %>" id="beautylink" href="javascript:updateCategory('Beauty','beauty','beautylink');"  alt="Beauty" title="Beauty">Beauty</a></li>
                    <li><a class="<%=travelClass %>" id="travellink" href="javascript:updateCategory('Travel','travel','travellink');"  alt="Travel" title="Travel">Travel</a></li>
                    <li><a class="<%=foodClass %>" id="foodlink" href="javascript:updateCategory('Food','food','foodlink');"  alt="Food" title="Food">Food</a></li>
                  </ul>
                </div>
				</div>
				<div class="col_setting colIndent_setting">
                <div id="choice_list">
                  <ul>
				  	<li ><a class="<%=motoringClass %>" id="motoringlink" href="javascript:updateCategory('Motoring','motoring','motoringlink');" alt="Motoring" title="Motoring">Motoring </a></li>
                    <li ><a class="<%=internetClass %>" id="internetlink" href="javascript:updateCategory('Internet','internet','internetlink');" alt="Internet" title="Internet">Internet </a></li>
                    <li ><a class="<%=entertainmentClass %>" id="entertainmentlink" href="javascript:updateCategory('Entertainment','entertainment','entertainmentlink');" alt="Entertainment" title="Entertainment">Entertainment </a></li>
                    <li ><a class="<%=othersClass %>" id="otherslink" href="javascript:updateCategory('Others','others','otherslink');" alt="Others" title="Others">Others </a></li>
                  </ul>
                </div>
				</div>
              </div>
              </form:form>
            </div>
            <!--- Edit Profile Ends---->
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
