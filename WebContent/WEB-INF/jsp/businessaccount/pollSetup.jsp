<%@page import="com.incyyte.app.domain.PagePhoto" %>
<%@page import="com.incyyte.app.domain.User" %>
<%@ include file="/WEB-INF/jsp/common/include.jsp" %>
<%@page import="com.incyyte.app.web.SessionKeys" %>
<%@page import="com.incyyte.app.web.model.UserPollPageModel" %>
<%@ page import="org.apache.commons.lang.StringUtils" %>

<% User user = (User) request.getSession().getAttribute(SessionKeys.LOGIN_USER); %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">
    <link rel="shortcut icon" href="favicon.ico"/>
    <title>Setup your Poll Page</title>
    <meta charset="utf-8">
    <link rel="stylesheet" href="ui/css/reset.css">
    <link rel="stylesheet" href="ui/css/layout.css">
    <link rel="stylesheet" href="ui/css/buttons.css" media="screen">
    <link rel="stylesheet" href="ui/css/style_login.css">
    <link rel="stylesheet" href="ui/css/accordionmenu.css" type="text/css">
    <link rel="stylesheet" href="ui/css/icons_sprites.css">
    <link rel="stylesheet" href="fonts/fonts_stylesheet.css">
    <link rel="stylesheet" href="ui/css/ratingbar.css" type="text/css"/>
    <link rel="stylesheet" href="ui/css/ui.progress-bar.css" type="text/css"/>
    <link rel="stylesheet" href="ui/modal/colorbox.css">
    <link rel="stylesheet" href="ui/css/jquery.selectbox.css" type="text/css"/>
    <!--[if IE 8]>
    <link href="ui/css/ie8.css" media="screen" rel="stylesheet" type="text/css"/>
    <style type="text/css">
        #header_topnav_inner {
            color: #9da6ac;
            font-size: 14px;
            font-family: 'bariol_regularregular', 'ie8_bariol_regular';
            float: right;
            margin-top: 0px !important;
        }
    </style>
    <![endif]-->
    <script>
        if (/*@cc_on!@*/false) {
            var headHTML = document.getElementsByTagName('head')[0].innerHTML;
            headHTML += '<link type="text/css" rel="stylesheet" href="ui/css/ie10.css">';
            document.getElementsByTagName('head')[0].innerHTML = headHTML;
        }
    </script>
    <!--[if gte IE 9]>
    <link href="${pageContext.request.contextPath}/ui/css/gte_ie9.css" media="screen" rel="stylesheet" type="text/css"/>
    <![endif]-->

    <script type="text/javascript" src="ui/js/jquery-1.8.3.min.js"></script>
    <script src="ui/modal/colorbox/jquery.colorbox.js"></script>
    <script src="ui/js/search_script.js"></script>
    <script type="text/javascript" src="ui/js/left_menu.js"></script>
    <script type="text/javascript" src="ui/js/jquery_profile_rating.js"></script>
    <script type="text/javascript" src="ui/js/star_rating.js"></script>
    <script type="text/javascript" src="js/jquery.form.js"></script>
    <script type="text/javascript" src="ui/js/jquery-ui.js"></script>
    <script type="text/javascript" src="ui/js/jquery.ratingbar.js"></script>
    <script type="text/javascript" src="ui/js/pollSetup.js"></script>
    <script src="ui/js/jquery.selectbox-0.2.js"></script>
 	<script src="ui/js/googleSearch.js"></script>
    <script type="text/javascript">
        $(function () {
            $("select").selectbox();
        });
    </script>
    <script type="text/javascript">
        $(document).ready(function () { 	
        	if($('#ServersideheaderError').val() == "Page header is already used. Provide a unique page header."){
        		$('#headerError').text("Page header is already used. Provide a unique page header.");
    			$('#headerError').css("display", "");
        	}
    		
            $('#logoButton').click(function () {
                 var searchValue = document.getElementById("search_new_logo");
                 var searchLoad = document.getElementById("logoGoogleImagesScroll");
                 var searchSelect = "logoImgSelect";
                 if(searchValue != null){
                 	$('#googleSearchTrigger').val(1);
                     loadGoogleSearchImages(searchValue, searchLoad, searchSelect);
                   }
            	parent.$.fn.colorbox({'href':'div#logoupload', 'open':true, 'inline':true});
             });
            var uploaded = $("#uploadedSection").val();
            if(uploaded == "uploaded"){
        				$("#link3").slideDown();
       					$("#opencolor3").css("color","red");
       					$("#opencolor2").css("color","#1b303f");
       					$("#opencolor1").css("color","#1b303f");
       					$(".link3").hide();
       					$("#link1").hide();
       					$("#link2").hide();
       					$(".link1").show();
       					$(".link2").show();
       					$(".open3").show();
       					$(".open2").hide();
       					$(".open1").hide();
       		for(var j=1 ;j<=10;j++){$("#add_files"+j).hide();}
           } 
                      
          });
    </script>
    <script type="text/javascript">
        $(document).ready(function () {
            $('#bannerButton').click(function () {
            	var searchValue = document.getElementById("search_new_banner");
                var searchLoad = document.getElementById("bannerGoogleImagesScroll");
                var searchSelect = "bannerImgSelect";
                if(searchValue != null){
                $('#googleSearchTrigger').val(1);
                loadGoogleSearchImages(searchValue, searchLoad, searchSelect);
                }
            	parent.$.fn.colorbox({'href':'div#Bannerupload', 'open':true, 'inline':true});
            });
        });
    </script>
    <script type="text/javascript">
 function googleImageSearchForLogo(){
	$('#googleSearchTrigger').val(1);
	var searchValue = document.getElementById("search_new_logo");
	var searchLoad = document.getElementById("logoGoogleImagesScroll");
	var searchSelect = "logoImgSelect";
	loadGoogleSearchImages(searchValue, searchLoad, searchSelect);
}
 function googleImageSearchForBanner(){
	 	$('#googleSearchTrigger').val(1);
		var searchValue = document.getElementById("search_new_banner");
		var searchLoad = document.getElementById("bannerGoogleImagesScroll");
		var searchSelect = "bannerImgSelect";
		loadGoogleSearchImages(searchValue, searchLoad, searchSelect);
	}
 
    function googleImageSearchForImg1(){
    	$('#googleSearchTrigger').val(1);
    	var searchValue = document.getElementById("search_value_1");
    	var searchLoad = document.getElementById("search_result_1");
    	var searchSelect = "image_searched_1";
    	loadGoogleSearchImages(searchValue, searchLoad, searchSelect);
    }
    function googleImageSearchForImg2(){
    	$('#googleSearchTrigger').val(1);
    	var searchValue = document.getElementById("search_value_2");
    	var searchLoad = document.getElementById("search_result_2");
    	var searchSelect = "image_searched_2";
    	loadGoogleSearchImages(searchValue, searchLoad, searchSelect);
    }
    function googleImageSearchForImg3(){
    	$('#googleSearchTrigger').val(1);
    	var searchValue = document.getElementById("search_value_3");
    	var searchLoad = document.getElementById("search_result_3");
    	var searchSelect = "image_searched_3";
    	loadGoogleSearchImages(searchValue, searchLoad, searchSelect);
    }
    function googleImageSearchForImg4(){
    	$('#googleSearchTrigger').val(1);
    	var searchValue = document.getElementById("search_value_4");
    	var searchLoad = document.getElementById("search_result_4");
    	var searchSelect = "image_searched_4";
    	loadGoogleSearchImages(searchValue, searchLoad, searchSelect);
    }
    function googleImageSearchForImg5(){
    	$('#googleSearchTrigger').val(1);
    	var searchValue = document.getElementById("search_value_5");
    	var searchLoad = document.getElementById("search_result_5");
    	var searchSelect = "image_searched_5";
    	loadGoogleSearchImages(searchValue, searchLoad, searchSelect);
    }
    function googleImageSearchForImg6(){
    	$('#googleSearchTrigger').val(1);
    	var searchValue = document.getElementById("search_value_6");
    	var searchLoad = document.getElementById("search_result_6");
    	var searchSelect = "image_searched_6";
    	loadGoogleSearchImages(searchValue, searchLoad, searchSelect);
    }
    function googleImageSearchForImg7(){
    	$('#googleSearchTrigger').val(1);
    	var searchValue = document.getElementById("search_value_7");
    	var searchLoad = document.getElementById("search_result_7");
    	var searchSelect = "image_searched_7";
    	loadGoogleSearchImages(searchValue, searchLoad, searchSelect);
    }
    function googleImageSearchForImg8(){
    	$('#googleSearchTrigger').val(1);
    	var searchValue = document.getElementById("search_value_8");
    	var searchLoad = document.getElementById("search_result_8");
    	var searchSelect = "image_searched_8";
    	loadGoogleSearchImages(searchValue, searchLoad, searchSelect);
    }
    function googleImageSearchForImg9(){
    	$('#googleSearchTrigger').val(1);
    	var searchValue = document.getElementById("search_value_9");
    	var searchLoad = document.getElementById("search_result_9");
    	var searchSelect = "image_searched_9";
    	loadGoogleSearchImages(searchValue, searchLoad, searchSelect);
    }
    function googleImageSearchForImg10(){
    	$('#googleSearchTrigger').val(1);
    	var searchValue = document.getElementById("search_value_10");
    	var searchLoad = document.getElementById("search_result_10");
    	var searchSelect = "image_searched_10";
    	loadGoogleSearchImages(searchValue, searchLoad, searchSelect);
    }
    		

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
<%String editFrom = (String) request.getParameter("editFrom");
String uploadedSection = (String) request.getParameter("uploadedSection");%>
<input type="hidden" id="editFrom" value="<%=editFrom%>"/>
<input type="hidden" id="uploadedSection" value="<%=uploadedSection%>" />
<input type="hidden" id="googleSearchTrigger"/>

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

<% UserPollPageModel model = (UserPollPageModel) request.getSession().getAttribute("pollSetup");
    if (!(user.getId().equals(model.getUserId()))) {
        model = new UserPollPageModel();
    }
%>
<div class="extra">
<!-- include header -->
<jsp:include page="../common/includes/header.jsp"/>
<div class="main">
<article id="content">
<div id="main_content">
<div class="grid_9">
    <jsp:include page="../dashboard/leftmenu_dashboard.jsp"/>
</div>
<div class="line"><span></span></div>
<div class="grid_17">
<div class="pollwarp">
<table width="100%" border="0" cellspacing="0" cellpadding="0">
<tr>
    <td class="poll_txt_head" valign="top">Set Up Your Exclusive inCyyte Poll Page</td>
</tr>
<tr>
    <td>
        <table width="100%" cellpadding="0" cellspacing="0">
            <tr>
                <td width="450" align="top"><br><br>
                    <p class="poll_heading2">What is a Poll Page?</p>
                    <p class="poll_txt_p">InCyyte Poll Pages are a neat new way to reach people with the questions you want answers to. <br>
                     Anyone can set up an inCyyte poll page,
                     they are free and can be sent far and wide to individuals <br>across postcodes and national regions.<br>
                     Your poll page can be customised to present your business or your interests to others. It can be<br> 
                     used to share your views and to get real-time responses from those you send it to.<br> <br><br>
                     <p class="poll_heading2">Attach newly created inCyyte polls to your page.</p>
                     <p class="poll_txt_p">After you have setup your Poll Page you can start attaching new polls to it. When creating a new<br>
                     poll simply select 'Attach it to your Poll Page' to have it embedded in your page.<br>
                     Use your exclusive page to share polls with much more detail or to promote your business and <br>
                     find new customers.</p>
                   
                        
                </td>
                <td>
                    
                </td>
            </tr>
        </table>
    </td>
</tr>
<tr>
<td>
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
 	$(document).ready(function () {
	     	     
 		if($('#pagename').val() == ''){
 		   <%if(user.getUsername() != null){%>
 			   $('#pagename').val('<%=user.getUsername()%>');
 		   <%}%>
 		} else {
 			$("#savepagenamelink").css("display", "none");
 		}
 		 	
		$("#okForUniqPageName").click(function(){
	     	$.fn.colorbox.close();
	     	SaveUniquePageName();
	  	});
	   	$("#closepopForUniqPageName").click(function(){
	         $.fn.colorbox.close();
	  	});
 	});
 	
 	function isSpclCharuniquePageName(pagename){
 		var pagename=$("#pagename").val();
 		var iChars = "!@#$%^&*()+=_`~-[]\\\';,./{}|\":<>?";
 		var isValid=true;
 		if(pagename != ""){
 			for (var i = 0; i < pagename.length; i++) 
 		    {
 			   if (iChars.indexOf(pagename.charAt(i))!='-1') {
 			    	isValid=false;
 			    	break;
 			    	return isValid;
 			     }else{
 			    	 isValid=true;
 			     }
 		    }
 		}else{
 			isValid=false;
 		}
 		return isValid;
 	}
 	function uniquePageValidate(){
 		var pagename=$("#pagename").val();
 		if(!isSpclCharuniquePageName(pagename)){
 		   	$("#uniquepagename_error").text("Special characters are not allowed");
 		   	$("#uniquepagename_error").css("display","");
 		}	
 		else{
 			if(pagename.indexOf(" ") != -1){
 			   	$("#uniquepagename_error").text("Character space is not allowed");
 			   	$("#uniquepagename_error").css("display","");
 			}
 			else{
 				$("#uniquepagename_error").css("display","none");	
 			}
 		}
 	}

 	function toggleButton(linkname,submitUrl) { 		
	
 		if($("#save" + linkname + "link").hasClass("button_green")) {
 		    $("#editSettingsForm").ajaxSubmit({
 		        type:"POST",
 		        url:submitUrl,
 		        dataType:"text",
 		        success:function (data) {
 		        	if (data.search("success") == 0) {
 	            		//alert("in ajax success::"+data);
 		        		 $("#savepagenamelink").css("display", "none");
 	            		$("#sucess" + linkname).show();
 	                    $("#invalid" + linkname).css("display", "none");
 	                   $("#" + linkname).css("background", "#cfff00");
 	                    $("#" + linkname).css("color", "#1b4d5f");
 	                    $("#valid" + linkname).css("display", "");
 	                    $("#valid" + linkname).css('color', "#3a7d34");
 	                   $("#valid" + linkname).text("Success");
 		    		$("#save" + linkname + "link").removeClass("button_green").addClass("edit").css("width","").css("float","").css("margin-bottom","");
 		    		$("#save" + linkname + "link" + " span").removeClass("title_green").text('Edit');
 		    		$("#" + linkname).attr("readOnly","true").addClass("readonly_input");
 		        	}else if (data.search("error") == 0) {
 	                	 $("#sucess" + linkname).hide();
 	                    $("#valid" + linkname).css("display", "none");
 	                    $("#" + linkname).css("color", "#ffffff");
 	                    $("#" + linkname).css("background", "#c2002d");
 	                    $("#invalid" + linkname).css("display", "");
 	                    $("#invalid" + linkname).css('color', "#ec3f41");
 	                    $("#invalid" + linkname).text(data);
 	                    
 	                }
 		        }
 		    });
 		}
 		else {
			if(linkname == 'pagename') {
				$("#save" + linkname + "link").attr("onFocus","this.placeholder = ''").attr("onBlur","this.placeholder = 'Type your name here...'").attr("placeholder","Type your name here... ").attr("title","Save").removeClass("edit").addClass("button_green").css("width","60px").css("float","right").css("margin-bottom","8px");
			}
			else {
				$("#save" + linkname + "link").attr("href","#").attr("title","Save").removeClass("edit").addClass("button_green").css("width","60px").css("float","right").css("margin-bottom","8px");
			}
			$("#" + linkname).removeAttr("readOnly").removeClass("readonly_input");
			$("#save" + linkname + "link" + " span").addClass("title_green edit_profile_save_bot_ie8").text("SAVE");
 		}
 	}
    function uniquePage(){
   		$("#validpagename").css("display","none");
   		$("#pagename").css("background-color","");
   		var pagename=$("#pagename").val();
   		parent.$.fn.colorbox({'href':'div.edit_Uniquepagename_pop_bg', 'open':true, 'inline':true, 'escKey':false, 'overlayClose':false,'closeBtn':false});
   	}
     
    function SaveUniquePageName(){
   		var pagename=$("#pagename").val();
   		if(!isSpclCharuniquePageName(pagename)){
   		   	$("#uniquepagename_error").text("Special characters are not allowed");
   		   	$("#uniquepagename_error").css("display","");
   		}
   		else{
   			if(pagename.indexOf(" ") != -1){
   			   	$("#uniquepagename_error").text("Character space is not allowed");
   			   	$("#uniquepagename_error").css("display","");
   			}
   			else{
   				$("#uniquepagename_error").css("display","none");	
   				toggleButton('pagename','savepagename.cyt');
   			}
   		}
   	}
 </script>
 <br><br>
<form:form id="editSettingsForm" commandName="editSettingsForm"  method="post">
 <div id="edit_profile_setup" >
     <div id="profile_name">
		<table width="100%" border="0" cellspacing="0" cellpadding="0">
			<tr>
				
				 <td colspan="2" valign="top" class="heading5" style="padding-left:40px;">Your Unique Page Name</td>
			</tr>
			<tr>
				<td class="poll_txt_p_label" >			
					Choose a page name
				</td>
				<td class="pollfrom"  class="border_left leftIndent3">
		   			<div id="page_name_state1" align="left">
		     			<form:input id="pagename" path="uniquePageName" onKeyUp="uniquePageValidate();" readonly="true" class="readonly_input edit_setting_input_ie8"  cssStyle="width:300px;"/>
		     			&nbsp;&nbsp;<a id="savepagenamelink" class="edit" href="javascript:uniquePage()" style="float:none;"><span>Edit</span></a> 
		     			<br>
		     			<span class="example">http://poll.incyyte.com/<span style="color: red;">pagename</span></span>
		     			<div id="settingsPageNameDiv">
		     				<span id="uniquepagename_error" style="padding-left: 12px; display:none;"></span>
		     			</div>		     			
		     		</div>
		     		<p id = "validpagename" />
		    		<p id = "invalidpagename" />
		     	</td>		     	
			</tr>
		</table>
	</div>
</div>
</form:form>
<form:form id="pollSetupForm" name="pollSetupForm" commandName="pollSetupForm" enctype="multipart/form-data" method="post">
<table width="100%">

<% if(user != null && !StringUtils.equals(user.getUserType(), "USER" )) { %>
	<c:if test="${not empty templates}" >
		<tr>
			<td class="poll_txt_p_label" height="30px" width="166" valign="top">Template </td>
			<td>
	        	<table>
	            	<tr>	               
					    <td height="30px" width="250" valign="top">                   
					        <form:select path="templateId" id="templateId" cssStyle="width:100px;" >        	
								<c:forEach var="item" items="${templates}">
						     		<form:option value="${item.key}"  label="${item.value}"/>
						      	</c:forEach>										
							</form:select>
						</td>
						<td height="30px" width="170" align="left">
							<a class="poll_button1" id="newTemplate">
								<span class="poll_button_green ">Add New Template</span>
				            </a>				        
					    </td>
	            	</tr>
	        	</table>
	    	</td>
		</tr>
	</c:if>
<%}%>
<tr>

    <td class="poll_txt_p_label" height="70px" width="166" valign="bottom"><br/>Your Page Header <span
            style="color: #FF0000">*</span></td>
    <td width="500" class="pollfrom">
        <form:hidden path="pageId" id="pageId"/>
        <form:hidden path="userId" id="userId"/>
        <form:hidden path="searchedFileURLPollPage" id="searchedFileURLPollPage"/>
        <form:hidden path="searchedFileNamePollPage" id="searchedFileNamePollPage"/>
        <input type="hidden" id="incyyte_photo_search_file"/>
        <input type="hidden" id="incyyte_photo_search_url"/>

        <form:input id="pageHeader" path="pageHeader" type="text"/><br>
         <input id="ServersideheaderError" type="hidden" value="${pageHeaderError}"/>
        <span id="headerError" ></span>
    </td>
</tr>

<tr>

    <td class="poll_txt_p_label" id="opencolor1" height="60" width="166" valign="bottom">Logo & Banner

    </td>
    <td class="polls_more_link">
        <div class="open1" style="display: none;"><img src="images/dropdown_arrow_down.png" style=""></div>
        <a href="javascript:void(0);" class="link1">
            <div class="poll_more_img"><img src="images/dropdown_arrow.png"/></div>
            <div class="poll_line"></div><br/>
            <span>click here to upload your personal photo, logo and banner images</span>
        </a>
    </td>
</tr>
<tr>
    <td width="160"></td>
    <td>
        <div class="poll_logo_baneer_wrap" style="display: none;" id="link1">
            <div class="poll_banner_wrap">
                <% if (StringUtils.isNotBlank(model.getLogoUrl())) {%>
                <div class="poll_steup_logo"><img style="width:100px;height:100px;" src="<%=model.getLogoUrl() %>">
                <a class="poll_button1" style="width:170px; float: left;margin-top: -77px; margin-left:181px; position: absolute; z-index: 10;  " href="javascript:void(0)">
                <span id="logoDeleteButton" class="poll_button_red">Delete</span></a></div>
                <%} else {%>
                <div class="poll_steup_logo"><img style="width:100px;height:100px;" src="images/logo_image.png"> 
                <a class="poll_button1" style="width:170px; float: left;margin-top: -77px; margin-left:181px; position: absolute; z-index: 10;  " href="javascript:void(0)">
                <span id="logoButton" class="poll_button_gray">Upload</span></a></div>
                <%}%>
                <% if (StringUtils.isNotBlank(model.getBannerUrl())) {%>
                <div class="poll_banner"><img style="width:380px;height:200px;" src="<%=model.getBannerUrl()%>">
                <a class="poll_button1" style="width:170px; float: left;margin-top:15px; margin-left:204px;  " id="bannerDeleteButton" href="#">
                <span class="poll_button_red">Delete</span></a></div>
                <%} else {%>
                <div class="poll_banner"><img style="width:380px;height:200px;" src="images/banner_image.png">
                <a class="poll_button1" style="width:170px; float: left;margin-top:15px; margin-left:204px;"  id="bannerButton" href="javascript:void(0)">
                <span class="poll_button_gray" href="javascript:void(0)">Upload</span></a></div>
                <%}%>
            </div>
        </div>
    </td>
</tr>
<tr>
    <td class="poll_txt_p_label" id="opencolor2" height="60" width="166" valign="bottom">Details & Info.</td>
    <td class="polls_more_link">
        <div class="open2" style="display: none;"><img src="images/dropdown_arrow_down.png" style=""></div>
        <a href="javascript:void(0);" class="link2">
            <div class="poll_more_img"><img src="images/dropdown_arrow.png"/></div>
            <div class="poll_line"></div><br/>
            <span>click here to enter your contact details, website details and other information</span>
        </a>
    </td>
</tr>
<tr>
    <td colspan="2">
        <table style="display: none;" id="link2" class="pollfrom">

            <tr width="100%">
                <td class="poll_txt_p_label" height="30px" width="166" valign="bottom">Address Line 1</td>
                <td width="500" height="50px"><form:input type="text" id="address1" path="address1" onKeyup="isAddress1Valid();" placeholder="Address Line 1"/><br>
                    <span id="address1Error"></span>
                </td>
            </tr>
            <tr width="100%">
                <td class="poll_txt_p_label" height="30px" width="166" valign="bottom">Address Line 2</td>
                <td width="500"><form:input type="text" id="address2" path="address2" onKeyup="isAddress2Valid();" placeholder="Address Line 2"/><br>
                    <span id="address2Error"></span>
                </td>
            </tr>
            <tr width="100%">
                <td class="poll_txt_p_label" height="30px" width="166" valign="bottom">City</td>
                <td width="500"><form:input type="text" id="city" path="city" onKeyup="isCityValid();" placeholder="City"/><br>
                    <span id="cityError"></span>
                </td>
            </tr>
            <tr width="100%">
                <td class="poll_txt_p_label" height="30px" width="166" valign="bottom">Postcode <!--span style="color: #FF0000">*</span--></td>
                <td width="500"><form:input type="text" id="postcode" path="postcode" onKeyup="isPostcodeValid();" placeholder="AB1 2CD"/><br>
                    <span id="postcodeError"></span>
                </td>
            </tr>
            <tr width="100%">
                <td class="poll_txt_p_label" height="30px" width="166" valign="bottom">Country <!--span style="color: #FF0000">*</span--></td>
                <td width="500" height="30">                   
                    <form:select path="country" id="country">
                    	<form:option value="" label="Country"/>
						<c:forEach var="item" items="${countries}">
	                		<form:option value="${item.key}"  label="${item.value}"/>
		                </c:forEach>										
					</form:select>
                    <br>
                    <span id="countryError"></span>
                </td>
            </tr>
            <tr width="100%">
                <td class="poll_txt_p_label" height="30px" width="166" valign="bottom">Contact Email Address</td>
                <td width="500"><form:input type="text" id="contactEmail" path="contactEmail" onKeyup="isContactEmailValid();" placeholder="user@domain.com"/><br>
                    <span id="contactEmailError"></span>
                </td>
            </tr>
            <tr width="100%">
                <td class="poll_txt_p_label" height="30px" width="166" valign="bottom">Website Address</td>
                <td width="500"><form:input id="websiteUrl" path="websiteUrl" type="text" onKeyup="isWebsiteUrlValid();" placeholder="domain.com"/><br>
                    <span id="websiteUrlError"></span>

                </td>
            </tr>
            <tr width="100%">
                <td class="poll_txt_p_label" height="30px" width="166" valign="bottom">Phone 1</td>
                <td width="500"><form:input id="contactPhone1" path="contactPhone1" type="text" onKeyup="isPhoneNumber1Valid();" placeholder="01234 567 890"/><br>
                    <span id="Phone1Error"></span>
                </td>
            </tr>
            <tr width="100%">
                <td class="poll_txt_p_label" height="30px" width="166" valign="bottom">Phone 2</td>
                <td width="500"><form:input id="contactPhone2" path="contactPhone2" type="text" onKeyup="isPhoneNumber2Valid();" placeholder="+12 123 123 1234"/><br>
                    <span id="Phone2Error"></span>
                </td>
            </tr>
            <tr>
                <td height="30"></td>
                <td height="30"></td>
            </tr>
            <tr width="100%" style="margin-top: 30px;">
                <td class="poll_txt_p_label" height="auto" width="166" valign="bottom">Information</td>
                <td width="500"><form:textarea id="information" path="information"></form:textarea><br>
                </td>
            </tr>
        </table>
    </td>
</tr>
<tr>
    <td class="poll_txt_p_label" height="60" width="166" id="opencolor3" valign="bottom">Page Photos</td>
    <td class="polls_more_link">
        <div class="open3" style="display: none;"><img src="images/dropdown_arrow_down.png" style=""></div>
        <a href="javascript:void(0);" class="link3">
            <div class="poll_more_img"><img src="images/dropdown_arrow.png"/></div>
            <div class="poll_line"></div>
            <br/>
            <span>click here to upload linked images you want to shares with others</span>
        </a>
        <span class="errorLabel" id="errorDeleteImage">This image is Not Deleted.</span>
    </td>
</tr>
<tr>
    <td width="166"></td>
    <td>
        <table width="100%" style="display: none;" id="link3">
            <tr>
                <td class="poll_img_txt"><%if (StringUtils.isEmpty(model.getImageURL1())) {%>
                    <a id="imagelink1" href="javascript:void(0);" class="uploadimg1">Image 1</a>
                    <%} else { %><a class="view_image1" href="javascript:void(0);">
                        <img src="ui/images/view_gray.png" style="float: left; margin-left: -30px;margin-top: 4px; ">Image 1</a>
                    <%}%></td>
                <td class="poll_img_txt"><%if (StringUtils.isEmpty(model.getImageURL2())) {%><a id="imagelink2"
                                                                                                href="javascript:void(0);"
                                                                                                class="uploadimg2">Image
                    2</a>
                    <%} else { %><a class="view_image2" href="javascript:void(0);">
                        <img src="ui/images/view_gray.png" style="float: left; margin-left: -30px;margin-top: 4px; ">Image
                        2</a>
                    <%}%></td>
                <td class="poll_img_txt"><%if (StringUtils.isEmpty(model.getImageURL3())) {%><a id="imagelink3"
                                                                                                href="javascript:void(0);"
                                                                                                class="uploadimg3">Image
                    3</a>
                    <%} else { %><a class="view_image3" href="javascript:void(0);">
                        <img src="ui/images/view_gray.png" style="float: left; margin-left: -30px;margin-top: 4px; ">Image
                        3</a>
                    <%}%></td>
                <td class="poll_img_txt"><%if (StringUtils.isEmpty(model.getImageURL4())) {%><a id="imagelink4"
                                                                                                href="javascript:void(0);"
                                                                                                class="uploadimg4">Image
                    4</a>
                    <%} else { %><a class="view_image4" href="javascript:void(0);">
                        <img src="ui/images/view_gray.png" style="float: left; margin-left: -30px;margin-top: 4px; ">Image
                        4</a>
                    <%}%></td>
                <td class="poll_img_txt"><%if (StringUtils.isEmpty(model.getImageURL5())) {%><a id="imagelink5"
                                                                                                href="javascript:void(0);"
                                                                                                class="uploadimg5">Image
                    5</a>
                    <%} else { %><a class="view_image5" href="javascript:void(0);">
                        <img src="ui/images/view_gray.png" style="float: left; margin-left: -30px;margin-top: 4px; ">Image
                        5</a>
                    <%}%></td>
            </tr>
            <tr>
                <td class="poll_img_txt"><%if (StringUtils.isEmpty(model.getImageURL6())) {%><a id="imagelink6"
                                                                                                href="javascript:void(0);"
                                                                                                class="uploadimg6">Image
                    6</a>
                    <%} else { %><a class="view_image6" href="javascript:void(0);">
                        <img src="ui/images/view_gray.png" style="float: left; margin-left: -30px;margin-top: 4px; ">Image
                        6</a>
                    <%}%></td>
                <td class="poll_img_txt"><%if (StringUtils.isEmpty(model.getImageURL7())) {%><a id="imagelink7"
                                                                                                href="javascript:void(0);"
                                                                                                class="uploadimg7">Image
                    7</a>
                    <%} else { %><a class="view_image7" href="javascript:void(0);">
                        <img src="ui/images/view_gray.png" style="float: left; margin-left: -30px;margin-top: 4px; ">Image
                        7</a>
                    <%}%></td>
                <td class="poll_img_txt"><%if (StringUtils.isEmpty(model.getImageURL8())) {%><a id="imagelink8"
                                                                                                href="javascript:void(0);"
                                                                                                class="uploadimg8">Image
                    8</a>
                    <%} else { %><a class="view_image8" href="javascript:void(0);">
                        <img src="ui/images/view_gray.png" style="float: left; margin-left: -30px;margin-top: 4px; ">Image
                        8</a>
                    <%}%></td>
                <td class="poll_img_txt"><%if (StringUtils.isEmpty(model.getImageURL9())) {%><a id="imagelink9"
                                                                                                href="javascript:void(0);"
                                                                                                class="uploadimg9">Image
                    9</a>
                    <%} else { %><a class="view_image9" href="javascript:void(0);">
                        <img src="ui/images/view_gray.png" style="float: left; margin-left: -30px;margin-top: 4px; ">Image
                        9</a>
                    <%}%></td>
                <td class="poll_img_txt"><%if (StringUtils.isEmpty(model.getImageURL10())) {%><a id="imagelink10"
                                                                                                 href="javascript:void(0);"
                                                                                                 class="uploadimg10">Image
                    10</a>
                    <%} else {%><a class="view_image10" href="javascript:void(0);">
                        <img src="ui/images/view_gray.png" style="float: left; margin-left: -30px;margin-top: 4px; ">Image
                        10</a>
                    <%}%></td>

            </tr>
        </table>
    </td>

</tr>
<form:input path="image1" type="file" id="image1" style="display: none;"/>
<form:input path="image2" type="file" id="image2" style="display: none;"/>
<form:input path="image3" type="file" id="image3" style="display: none;"/>
<form:input path="image4" type="file" id="image4" style="display: none;"/>
<form:input path="image5" type="file" id="image5" style="display: none;"/>
<form:input path="image6" type="file" id="image6" style="display: none;"/>
<form:input path="image7" type="file" id="image7" style="display: none;"/>
<form:input path="image8" type="file" id="image8" style="display: none;"/>
<form:input path="image9" type="file" id="image9" style="display: none;"/>
<form:input path="image10" type="file" id="image10" style="display: none;"/>
<form:input path="logoImage" type="file" id="logoImage" style="display: none;"/>
<form:input path="bannerImage" type="file" id="bannerImage" style="display: none;"/>

<form:hidden path="ImageURL1" id="ImageURL1"/>
<form:hidden path="ImageURL2" id="ImageURL2"/>
<form:hidden path="ImageURL3" id="ImageURL3"/>
<form:hidden path="ImageURL4" id="ImageURL4"/>
<form:hidden path="ImageURL5" id="ImageURL5"/>
<form:hidden path="ImageURL6" id="ImageURL6"/>
<form:hidden path="ImageURL7" id="ImageURL7"/>
<form:hidden path="ImageURL8" id="ImageURL8"/>
<form:hidden path="ImageURL9" id="ImageURL9"/>
<form:hidden path="ImageURL10" id="ImageURL10"/>
<form:hidden path="image1CdnFileName" id="image1CdnFileName"/>
<form:hidden path="image2CdnFileName" id="image2CdnFileName"/>
<form:hidden path="image3CdnFileName" id="image3CdnFileName"/>
<form:hidden path="image4CdnFileName" id="image4CdnFileName"/>
<form:hidden path="image5CdnFileName" id="image5CdnFileName"/>
<form:hidden path="image6CdnFileName" id="image6CdnFileName"/>
<form:hidden path="image7CdnFileName" id="image7CdnFileName"/>
<form:hidden path="image8CdnFileName" id="image8CdnFileName"/>
<form:hidden path="image9CdnFileName" id="image9CdnFileName"/>
<form:hidden path="image10CdnFileName" id="image10CdnFileName"/>
<form:hidden path="logoCdnFileName" id="logoCdnFileName"/>
<form:hidden path="bannerCdnFileName" id="bannerCdnFileName"/>
<form:hidden path="uploadedFileName" id="uploadedFileName"/>
<form:hidden path="uploadedFileType" id="uploadedFileType"/>
<form:hidden path="imageLink1" id="imageLink1"/>
<form:hidden path="imageLink2" id="imageLink2"/>
<form:hidden path="imageLink3" id="imageLink3"/>
<form:hidden path="imageLink4" id="imageLink4"/>
<form:hidden path="imageLink5" id="imageLink5"/>
<form:hidden path="imageLink6" id="imageLink6"/>
<form:hidden path="imageLink7" id="imageLink7"/>
<form:hidden path="imageLink8" id="imageLink8"/>
<form:hidden path="imageLink9" id="imageLink9"/>
<form:hidden path="imageLink10" id="imageLink10"/>
<form:hidden path="logoLink" id="logoLink"/>
<form:hidden path="bannerLink" id="bannerLink"/>
<form:hidden path="imageName1" id="imageName1"/>
<form:hidden path="imageName2" id="imageName2"/>
<form:hidden path="imageName3" id="imageName3"/>
<form:hidden path="imageName4" id="imageName4"/>
<form:hidden path="imageName5" id="imageName5"/>
<form:hidden path="imageName6" id="imageName6"/>
<form:hidden path="imageName7" id="imageName7"/>
<form:hidden path="imageName8" id="imageName8"/>
<form:hidden path="imageName9" id="imageName9"/>
<form:hidden path="imageName10" id="imageName10"/>
<form:hidden path="bannerImageName" id="bannerImageName"/>
<form:hidden path="logoImageName" id="logoImageName"/>
<% for (int i = 1; i <= 10; i++) {
    PagePhoto pagePhoto = new PagePhoto();
    String ansAddFilesId = "add_files" + i;
    String errorImageMessageId = "incyyte_ans_image_error_msg_" + i;
    String addPhotos = "add_photos_" + i;
    String imageBrowseButtons = "imageBrowseButton" + i;
    String imageLinkTexts = "imageLinkText" + i;
    String images = "image" + 1;
    String incyyteUploadPhotoButton = "incyyteUploadPhotoButton" + i;
    String view_images = "view_image" + i;
    String photoImgs = "photoImg" + i;
    String imageDeleteButtons = "imageDeleteButton" + i;
    String imageLoaders = "imageLoader" + i;
    String errorForImageFormat = "errorForImageFormat" + i;
    String fileNames = "fileName" + i;
    String viewFileNames = "viewFileName" + i;

    //for Googlesearch Images 
    String searchValue = "search_value_" + i;
    String searchSubmit = "search_submit_" + i;
    String googleImagesScroll = "search_result_" + i;
    String imageSearched = "image_searched_" + i;
    String showMoreImages = "show_more_images_" + i;
%>
<tr>

<td colspan="2">
<!-- view image Start -->
<div id="<%=view_images%>" style="display: none;">

    <div id="view_files">
        <div id="modal_media_icon" style="float: left;margin-left: 106px;position: relative; ">
            <ul>
                <li id="modal_photos"><a href="#" alt="Photos" title="Photos" class="active"></a></li>
            </ul>
        </div>
        <div>
            <div id="view_photos" class="c_add_photos"
                 style="width: 422px; margin-top: -167px;margin-left: 156px;  height: 300px;">
                <table width="100%" border="0" cellspacing="0" cellpadding="0">
                    <tr>
                        <td class="heading1" colspan="2">View Image&nbsp;<%=i%>
                        </td>
                    </tr>
                    <tr>
                        <td valign="top" align="left" width="30%">
                            <table width="100%" border="0" cellspacing="0" cellpadding="0">
                                <tr valign="top">
                                    <td class="font_18px"><br> <br>File Name:</td>
                                </tr>
                                <tr>
                                    <td class="font_16px">
                                        <div style="word-wrap: break-word; width:160px;"><span class="font_14px"
                                                                                               style="word-wrap: break-word; margin-left:79px; margin-top:-18px; float: left; width:auto;"
                                                                                               id="<%=viewFileNames%>"></span>
                                        </div>
                                    </td>
                                </tr>
                                <tr>
                                    <td>&nbsp;</td>
                                </tr>
                            </table>
                        </td>
                        <td align="center" valign="top" width="70%">
                            <div id="#view_photos_thumbs">
                                <ul id="videos">
                                    <li><img id="<%=photoImgs %>" src=""
                                             class="photos_thumb" alt="tour"/>
                                    </li>
                                </ul>
                            </div>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="2" valign="bottom"><span class="licence">You must have the licence to use this image</span>

                            <div title="Delete" id="<%=imageDeleteButtons%>" class="button_red"
                                 style="width:140px; float:right;"><span class="title_red">DELETE</span></div>
                        </td>
                    </tr>
                </table>
            </div>

        </div>
    </div>
</div>
<!----------View Images end-------------------->

<div style="width: auto;height: auto;float: left;">
    <div id="<%=ansAddFilesId%>" style="display: none;">

        <div id="modal_media_icon" style="float: left;margin-left: 70px;position: relative; ">
            <ul>

                <li id="modal_photos"><a href="#" alt="Photos" title="Photos" class="active"></a></li>

            </ul>
        </div>
        <div id="">
            <div id="<%=addPhotos%>" class="c_add_photos"
                 style="margin-bottom: 25px; margin-left: 120px;height: 465px; margin-top: -166px; width: 480px;">
                <table width="100%" border="0" cellspacing="0" cellpadding="0">
                    <tr><td class="heading1" height="40" valign="top">Add Image&nbsp;<%=i%></td><td>&nbsp;</td>
                    </tr>
                    <tr>
                        <td>
                            <table>
                                <tr>
                                    <td class="fileInputs" width="150">
                                        <div id="<%=imageBrowseButtons%>" class="button_gray"
                                             style=" width:140px; float:left; margin-bottom:20px;">
                                            <span class="profle_pic_browse_bot profle_pic_browse_bot_ie8">BROWSE</span>
                                        </div>
                                    </td>
                                    <td class="font_16px" valign="top">
                                        <span style="float: left;">File Name:</span><span class="font_14px"
                                                                                          style="word-wrap: break-word; margin-left:10px; float: left; width:auto;"
                                                                                          id="<%=fileNames%>">
                                                            </span>
                                    </td>
                                </tr>
                            </table>
                        </td>
                    </tr>
                    <tr>
                        <td valign="top">
                            <div class="upload_photo_searchbox"
                                 style="margin-bottom:0;width: 460px;margin-left:0px;margin-top: 0px; ">
                                <p class="sort_by_text">Search Google images</p>
                                <div class="searchmain">
                                    <div>
                                        <input type="text" name="search" id="<%=searchValue%>" onKeydown="Javascript: if (event.keyCode==13 ) googleImageSearchForImg<%=i%>();"  >
                                        <input type="button" id="<%=searchSubmit%>" value=""/>
                                    </div>
                                </div>
                            </div>

                        </td>
                    </tr>

                    <tr>
                        <td>
                            <table style="margin-top: 10px;">
                                <tr>
                                    <td>
                                        <!-- This is used to display Google images -->
                                        <div id="<%=googleImagesScroll%>"
                                             style="width:272px; height:171px; overflow-x:hidden; overflow-y:auto; margin-right: 10px;">
                                        </div>

                                    </td>
                                    <td align="right">
                                        <div><img id="<%=imageSearched%>" class="upload_photos_container_big_img"
                                                  src="ui/images/uploading_big_img.png"></div>

                                    </td>
                                </tr>
                                <tr>
                                    <td colspan="3"><img src="ui/images/indicator-loader.gif" id="<%=imageLoaders%>"
                                                         width="32" height="32"
                                                         style=" display: none; position: absolute;z-index: 200; padding:8px 0 0 0; margin: -120px 0px 0px 356px;">
                                    </td>
                                </tr>
                            </table>
                        </td>
                    </tr>
                    <tr>
                        <td valign="top">
                            <div class="upload_photo_add_more"><a href="javascript:void(0);" id="<%=showMoreImages%>"> + Show more images </a>
                            </div>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <table>
                                <tr>
                                    <td class="font_18px" width="150" align="right">Add Url</td>
                                    <td class="pollfrom" width="400" align="right"><input type="text" id="<%=imageLinkTexts%>"  onKeyup="isImageLinkValid('<%=imageLinkTexts%>');"/><br>
                                    <span class="errorLabel" id="<%=imageLinkTexts%>Error" style="margin-left: 38px;float: left;font-weight: normal;"></span></td>
                                </tr>
                            </table>
                        </td>

                    </tr>
                    <tr>
                        <td>
                            <table style="margin-top: 10px;">
                                <tr>
                                    <td width="332" align="right">
                                        <span class="licence" style="margin-right: 10px;">You must have the licence to use this image</span>
                                    </td>
                                    <td>
                                        <div title="Upload Now" id="<%=incyyteUploadPhotoButton%>" class="button_red"
                                             style="width:140px; float:right;"><span
                                                class="title_red1">UPLOAD NOW</span></div>
                                    </td>
                                </tr>
                            </table>
                        </td>

                    </tr>

                </table>
             </div>
        </div>
    </div>
</div>
</td>
</tr>
<% } %>
</table>
</form:form>
</td>
</tr>
<tr>
    <td>
        <table>
            <tr>
                <td width="266" align="left"
                    style="height:100px; border-bottom:1px solid #a3a29f; border-top:1px solid #a3a29f;"><a
                        class="poll_button1" style="width:170px;" id="previewButton"><span class="poll_button_red ">Preview Page</span></a>
                </td>
                <td width="266" align="left"
                    style="height:100px; border-bottom:1px solid #a3a29f; border-top:1px solid #a3a29f;"><a
                        class="poll_button1" style="width:170px;"
                        href="${pageContext.request.contextPath}/cancelSetup.cyt"><span
                        class="poll_button_gray ">cancel</span></a></td>
                <td width="266" align="left"
                    style="height:100px; border-bottom:1px solid #a3a29f; border-top:1px solid #a3a29f;"><a
                        class="poll_button1" style="width:170px;" id="saveChangesButton"><span
                        class="poll_button_green ">Save Changes</span></a></td>
            </tr>
        </table>
    </td>
</tr>
</table>
</div>
</div>
</div>
</article>
</div>
</div>
<!-- include footer -->
<jsp:include page="../common/includes/footer.jsp"/>
</div>
<%-- LogoUpload  --%>
<div style="display:none;">
	<div id="logoupload">
			<div id="add_files" >
                <div id="modal_media_icon">
                    <ul>
                        <li id="modal_photos"><a href="#" alt="Photos" title="Photos" class="active"></a></li>
                    </ul>
                </div>

                <div>
					<div id="add_photos" class="c_add_photos" style="height: auto;">
                        <table width="522" border="0" cellspacing="0" cellpadding="0">
                            <tr>
                                <td class="heading1" colspan="2">Add Photos</td>
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
                                            <td><span class="errorLabel"></span></td>
                                        </tr>
                                       <tr valign="bottom" style="position: absolute;z-index: 200; float: left; margin-left: 410px; margin-top: 140px;">
                                            <td colspan="3" align="center"><img src="ui/images/indicator-loader.gif" id="imageLoaderLogo"
                                                                                width="32" height="32"
                                                                                style="padding:8px 0 0 0; margin-bottom:20px 0 100px;display: none;"></td>
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
                                                <input type="text" name="search" id="search_new_logo" onKeydown="Javascript: if (event.keyCode==13 ) googleImageSearchForLogo();">
                                                <input type="button" id="searchSubmit_new_logo" value=""/>
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
                            <tr>
                                <td>
                                    <div class="upload_photo_add_more"><a href="javascript:void(0);" id="show_more_images_logo"> + Show more images </a></div>
                                </td>
                            </tr>
                            <tr>
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
                            </tr>
                            <tr>
                                <td colspan="2" valign="bottom"><span class="licence" style="margin-top: 25px;float: left;">You must have the licence to use this image</span>

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

<%--END LOGO UPLOAD --%>
<%-- Baneer Upload  --%>
<div style="display:none;">
	<div id="Bannerupload">
			<div id="add_files" >
                <div id="modal_media_icon">
                    <ul>
                        <li id="modal_photos"><a href="#" alt="Photos" title="Photos" class="active"></a></li>
                    </ul>
                </div>

                <div>
					<div id="add_photos" class="c_add_photos" style="height: auto;">
                        <table width="522" border="0" cellspacing="0" cellpadding="0">
                            <tr>
                                <td class="heading1" colspan="2">Add Banner</td>
                            </tr>
                            <tr>
                                <td valign="top" align="left" width="30%">
                                    <table width="100%" border="0" cellspacing="0" cellpadding="0">
                                        <tr>
                                            <td width="30%" >
                                                <div class="fileInputs">
                                                    <div id="browseBannerImage" class="button_gray"
                                                         style=" width:140px; float:left; margin-bottom:20px; position: absolute; top: 0px;left: 0px; z-index: 1;">
                                                        <span class="title_gray">BROWSE</span></div>
                                                </div>
                                            </td>
                                            <td colspan="2" class="font_16px"><br><span style="float: left;">File Name:</span> 
                                            <span id="bannerFileName" style= "float: left; margin-left: 10px;" ></span></td>
                                        </tr>
                                        <tr>
                                            <td colspan="3">&nbsp;</td>
                                        </tr>
                                        <tr valign="bottom" style="display: none;" id="errorForBannerFormat">
                                            <td><span class="errorLabel"></span></td>
                                        </tr>
                                        <tr valign="bottom" style="position: absolute;z-index: 200; float: left; margin-left: 410px; margin-top: 140px;">
                                            <td colspan="3" align="center"><img src="ui/images/indicator-loader.gif" id="imageLoaderBanner"
                                                                                width="32" height="32"
                                                                                style="padding:8px 0 0 0; margin-bottom:20px 0 100px;display: none;"></td>
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
                                                <input type="text" name="search" id="search_new_banner" onKeydown="Javascript: if (event.keyCode==13 ) googleImageSearchForBanner();">
                                                <input type="button" id="searchSubmit_new_banner" value=""/>
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
                                            <div id="bannerGoogleImagesScroll"
                                                 style="width:319px; height:171px; overflow-x:hidden; overflow-y:auto; margin-right: 10px;">
                                            </div>
                                        </td>
                                        <td align="right">
                                            <div><img id="bannerImgSelect" class="upload_photos_container_big_img" src="ui/images/uploading_big_img.png"></div>
                                        </td>
                                    </tr>
                                </table>

                            </tr>
                            <tr>
                                <td>
                                    <div class="upload_photo_add_more"><a  href="javascript:void(0);" id="show_more_images_banner"> + Show more images </a></div>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <table>
                                        <tr>
                                            <td class="font_18px" width="150" align="right">Add Url</td>
                                       		<td class="pollfrom" width="400" align="right"><input type="text" id="bannerLinkText" onKeyup="isImageLinkValid('bannerLinkText');"/>
                                       	    <br><span class="errorLabel" id="bannerLinkTextError" style="font-weight: normal;float: left;margin-left: 42px;"></span><td>
                                        </tr>
                                    </table>
                                </td>

                                </td>
                            </tr>
                            <tr>
                                <td colspan="2" valign="bottom" ><span class="licence" style="margin-top: 25px;float: left;">You must have the licence to use this image</span>

                                    <div title="Upload Now" id="bannerUploadButton" class="button_red"
                                         style="width:140px; margin-top: 10px; float:right;"><span class="title_red1">UPLOAD NOW</span></div>
                                </td>
                            </tr>
                        </table>
					</div>
				</div>
			</div>
		</div>
<%--END BANNER UPLOAD --%>

<style>
    .sbHolder {
        background: none repeat scroll 0 0 #FFFFFF;
        border: 1px solid #9BA8AE;
        border-radius: 20px;
        font: 14px 'bariol_regularregular', 'ie8_bariol_regular';
        height: 28px;
        margin: 5px 0 0;
        margin-bottom: -15px;
        position: relative;
        width: 90%;
    }
</style>
<div id="Poll_save_confirm">
    <div class="Poll_save_confirm_bg">
        <div class="edit_pro_pop_txt">
            Your Poll Page template has been saved.
        </div>
        <div style="height:auto; " >        	
        	<a class="poll_button1" id="attachNewTemplate" style="width:310px;" href="ask_question.cyt">
        		<span class="poll_button_red " >Attach a new poll to this template</span>
        	</a><br>
        	<a class="poll_button1" href="pollSetup.cyt" style="width:170px;">
        		<span class="poll_button_gray">Not right now</span>
        	</a>
        </div>
    </div>
</div>

<div id="Poll_save_prompt">
    <div class="Poll_save_prompt_bg" align="center">
    	<span class="edit_pro_pop_txt">
          Do you wish to save a new template called
        </span><br>
        <span class="edit_pro_pop_txt" id="save_prompt_txt" style="color: red;"></span>
        <div style="height:auto; " >        	
        	<br>
        	<table>
	            <tr>
	                <td>
			        	<a class="poll_button1" id="saveTemplate" style="width:100px;">
			        		<span class="poll_button_green">Save</span>
			        	</a>
			        </td>
			        <td>
			        	<a class="poll_button1" id="cancelSaveTemplate" style="width:100px;">
			        		<span class="poll_button_green">Cancel</span>
			        	</a>
			        </td>
			 	</tr>
		   	</table>
        </div>
    </div>
</div>
<script type="text/javascript" src="ui/js/googleanalytics.js"></script>
</body>
</html>
