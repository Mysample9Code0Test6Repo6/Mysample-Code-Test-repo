<%@ include file="/WEB-INF/jsp/common/include.jsp" %>
<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="content-type" content="text/html; charset=UTF-8">
<link rel="shortcut icon" href="favicon.ico" />

<title>inCyyte - Admin Dashboard</title>
<meta charset="utf-8">
<link rel="stylesheet" href="ui/css/reset.css">
<link rel="stylesheet" href="ui/css/style.css">
<link rel="stylesheet" href="ui/css/layout.css">
<link rel="stylesheet" href="ui/css/buttons.css"  media="screen">
<link rel="stylesheet" href="ui/css/style_login.css">
<link rel="stylesheet" href="ui/css/accordionmenu.css" type="text/css">
<link rel="stylesheet" href="ui/css/icons_sprites.css">
<link rel="stylesheet" href="fonts/fonts_stylesheet.css">
<link rel="stylesheet" href="ui/css/prettyCheckboxes.css" type="text/css" media="screen">
<link rel="stylesheet" href="ui/css/themes/base/jquery.ui.all.css">
<link rel="stylesheet" href="ui/css/jquery.validate.css">
<link rel="stylesheet" href="ui/css/validate/screen.css">

<script src="ui/js/jquery-1.7.1.js"></script>
<script src="ui/js/login.js"></script>
<script src="ui/js/admin.js"></script>
<script src="ui/js/jquery.color.js"></script>
<script src="ui/js/search_script.js"></script>
<script src="ui/js/jquery.limit.js"></script>
<script src="ui/js/prettyCheckboxes.js" type="text/javascript" charset="utf-8"></script>
<script type="text/javascript" src="ui/js/autoresize.jquery.js"></script>
<script type="text/javascript" src="ui/js/easySlider1.7.js"></script>
<script src="ui/js/accordian/jquery.ui.core.js"></script>
<script src="ui/js/accordian/jquery.ui.widget.js"></script>
<script src="ui/js/accordian/jquery.ui.accordion.js"></script>
<script type="text/javascript" src="ui/js/common/jquery.form.js"></script>
<script type="text/javascript" src="ui/js/validate/jquery.validate.js" ></script>

<link rel="stylesheet" href="ui/js/tablesorter/css/jquery.tablesorter.style.css">
<script type="text/javascript" src="ui/js/tablesorter/jquery.tablesorter.js"></script>
<script type="text/javascript" src="ui/js/tablesorter/jquery.tablesorter.pager.js"></script>

<link rel="stylesheet" href="ui/modal/colorbox.css">
<script src="ui/modal/colorbox/jquery.colorbox.js"></script>

<script>
	$(function() {
		$( "#accordion" ).accordion();
	});
	</script>
<script type="text/javascript">
		$(document).ready(function() {
			// Store variables
			var accordion_head = $('.accordion > li > a'),
				accordion_body = $('.accordion li > .sub-menu');
			// Open the first tab on load
			accordion_head.first().addClass('active').next().slideDown('normal');
			// Click function
			accordion_head.on('click', function(event) {
				// Disable header links
				event.preventDefault();
				// Show and hide the tabs on click
				if ($(this).attr('class') != 'active'){
					accordion_body.slideUp('normal');
					$(this).next().stop(true,true).slideToggle('normal');
					accordion_head.removeClass('active');
					$(this).addClass('active');
				}

			});
			
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

		function getContextPath() {
			return "<%=request.getContextPath()%>";
		}
	</script>
<!-- Chart Script Start here -->
<script src="ui/js/charts/js/charts.js"></script>
<script src="ui/js/charts/js/highcharts.js"></script>
<!-- Chart Script end here -->
<link rel="stylesheet" type="text/css" href="ui/css/ratingbar.css" />
<script type="text/javascript" src="ui/js/jquery.ratingbar.js"></script>
<script type="text/javascript" charset="utf-8">
$(document).ready(function() {
	$('.gender_rating').ratingbar();
});
</script>
<script type="text/javascript" src="ui/js/ddaccordion.js"></script>
<script type="text/javascript">

//Initialize :
ddaccordion.init({
	headerclass: "question_tab", //Shared CSS class name of headers group
	contentclass: "question_detail", //Shared CSS class name of contents group
	revealtype: "click", //Reveal content when user clicks or onmouseover the header? Valid value: "click", "clickgo", or "mouseover"
	mouseoverdelay: 200, //if revealtype="mouseover", set delay in milliseconds before header expands onMouseover
	collapseprev: false, //Collapse previous content (so only one open at any time)? true/false 
	defaultexpanded: [], //index of content(s) open by default [index1, index2, etc]. [] denotes no content.
	onemustopen: true, //Specify whether at least one header should be open always (so never all headers closed)
	animatedefault: false, //Should contents open by default be animated into view?
	scrolltoheader: false, //scroll to header each time after it's been expanded by the user?
	persiststate: false, //persist state of opened contents within browser session?
	toggleclass: ["closedquestion", "openquestion"], //Two CSS classes to be applied to the header when it's collapsed and expanded, respectively ["class1", "class2"]
	togglehtml: ["none", "", " "], //Additional HTML added to the header when it's collapsed and expanded, respectively  ["position", "html1", "html2"] (see docs)
	animatespeed: "fast", //speed of animation: integer in milliseconds (ie: 200), or keywords "fast", "normal", or "slow"
	oninit:function(expandedindices){ //custom code to run when headers have initalized
		//do nothing
	},
	onopenclose:function(header, index, state, isuseractivated){ //custom code to run whenever a header is opened or closed
		//do nothing
	}
})

</script>
<script type="text/javascript" src="ui/js/autoresize.jquery.js"></script>
<script>
$(function () {
  $("textarea").autoresize();
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
  <style type="text/css">
<!--
.style1 {font-size: medium}
-->
  </style>
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
	<jsp:include page="../main/includes/emptyHeader.jsp"/>
	
  
    <div class="main">
      <!--content -->
      <article id="content">
        <div id="main_content">

			<!-- 2. Choose your Answers starts-->
			<div id="choose_your_answers_heading">
			
			
			    <h3 class="heading1">Admin Dashboard... </span></h3>
			
			</div>
			
			<div id="choose_your_answers">
			<div id="answer_container">
			    <div id="answer_form">
			        <BR>
			        <BR>
			        <span class="style1">System Info.</span><BR>
			        <table width="100%" border="1">
                      <tr>
                        <td>&nbsp;</td>
                        <td>&nbsp;</td>
                      </tr>
                      <tr>
                        <td>Actively Engaged Users Per Month</td>
                        <td><c:out value="${totalUsers}"/></td>
                      </tr>
                      <tr>
                        <td>&nbsp;</td>
                        <td>&nbsp;</td>
                      </tr>
                      <tr>
                        <td>Total Members</td>
                        <td><c:out value="${totalContacts}"/></td>
                      </tr>
                      <tr>
                        <td>&nbsp;</td>
                        <td>&nbsp;</td>
                      </tr>
                      <tr>
                        <td>Total Groups</td>
                        <td><c:out value="${totalGroups}"/></td>
                      </tr>
                      <tr>
                        <td>&nbsp;</td>
                        <td>&nbsp;</td>
                      </tr>
                      <tr>
                        <td>Total InCyytes Created</td>
                        <td><c:out value="${totalIncyyte}"/></td>
                      </tr>
                      <tr>
                        <td>&nbsp;</td>
                        <td>&nbsp;</td>
                      </tr>
                      <tr>
                        <td>&nbsp;</td>
                        <td>&nbsp;</td>
                      </tr>
                      <tr>
                        <td>&nbsp;</td>
                        <td>			        

                    </td>
                      </tr>
                    </table>
<BR>
			        <BR>
			        <BR>
			        <BR>
			                 <div>
                            	<label class="panelLink" id="listTopinCyyteUsers" ><span>List latest inCyyte users</span></label>
                            </div>
			        <BR>
                    <div>
                    <a href="adminPanel.cyt" > 
                     <label class="panelLink" id="processPublicPolls"><span>Process Public Polls</span></label></a>
                    </div>
			    </div>
			</div>
			</div>
			<!-- 2. Choose your Answers ends-->
            <div id="review_and_send_top">
		    <table width="100%" border="0" cellspacing="0" cellpadding="0">
		        <tr>
		            <td width="47%" align="left">
		            	<form:form  id="addImportEmailsForm"  commandName="addContactForm" method="post" >
			            	<form:input path="uploadedDocFile" type="file" id="uploadedDocFile" style="display: none;"/>
	              			<form:hidden path="fileName" id="fileName"/>
	              			<form:hidden path="incyyteCode" id="incyyteCode"/>
	              		</form:form>
		           		<div title="upload pre-registered members" id="uploadCsvFileButton" class="button_red" 
		                     style="width:270px;cursor:pointer; cursor:hand; z-index: 0; float: left;">
		                     	<span class="title_red">UPLOAD PRE-REGISTERED MEMBERS</span>
		                </div>
		            </td>
		            <td width="21%">
		            	
		            	<div id="searchmain">	
		            		<form:form id="searchForm" commandName="userModel"  method="post">		                  	
		                  		<fieldset>
		                  			<div class="input">
		                    			<form:input path="su_email" id="search" value="Enter user email" />
		                  			</div>		                  			
		                  		</fieldset>
		                	</form:form>
		              	</div>
						
		            </td>
		            <td width="4%">&nbsp;&nbsp;<img src="ui/images/arrow1.png"></td>
		            <td width="28%">
		                <div title="SEARCH USER EMAIL" id="searchUserEmail" class="button_red" 
		                     style="width:240px;cursor:pointer; cursor:hand; z-index: 0;">
		                     	<span class="title_red">SEARCH USER EMAIL</span>
		                </div>
		            </td>
		        </tr>
		    </table>
		</div >        
        </div>
      </article>
      <!--content end -->
    </div>
  </div>
  
  <div style="display: none;">		
	<div id="topInCyyteList" >
		<br>
		<table style="width: 100%;">
			<tr>
				<td>
					<table id="topInCyyteTable" cellspacing="1" class="tablesorter" style="width: 100%;">
						<thead>
							<tr>								
								<th>IDs</th>
								<th>Email Address</th>
								<th>Details Button</th>		
							</tr>
						</thead>
				
						<tbody>
							<c:forEach var="user" items="${topIncyyteUsers}" varStatus="userStatus">
								<tr>
									<td>${user.id}</td>
									<td>${user.email}</td>									
									<td>
										<div title="Detail" id="userDetailBtn" class="button_red" onclick="displayUserDetail('${user.email}')"
						                     style="width:70px;cursor:pointer; cursor:hand; z-index: 0;">
						                     	<span class="title_red">DETAIL</span>
						                </div>
									</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</td>
			</tr>
			<tr>
				<td>
					<div id="pagination"  style="width: 100%;">
						<form>
							<img src="ui/js/tablesorter/icons/first.png" class="first"/>
							<img src="ui/js/tablesorter/icons/prev.png" class="prev"/>
							<input type="text" class="pagedisplay"/>
							<img src="ui/js/tablesorter/icons/next.png" class="next"/>
							<img src="ui/js/tablesorter/icons/last.png" class="last"/>
							<select class="pagesize">
								<option selected="selected"  value="10">10</option>
								<!-- <option value="20">20</option>
								<option value="30">30</option>
								<option  value="40">40</option> -->
							</select>
						</form>
					</div>
				</td>
			</tr>
		</table>
		
		<button type="button" id="closePopup" class="button_green1" style="width:100px; margin:20px 0 0 250px;">
            <span class="title_green1">CLOSE</span></button>
		</div>
  </div>
  
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
	                               		
	                                    <td colspan="3" align="center">
	                                    	<img src="ui/images/indicator-loader.gif" id="imageLoaderLogo"
                                                 width="32" height="32"
                                                 style="padding:8px 0 0 0; margin-bottom:20px 0 100px;display: none;"/>
                                        </td>
	                                </tr>
	                            </table>
	                        </td>
	                    </tr>
                          
                       	<tr>
                       		<td>
	                            <table border="0" cellspacing="0" cellpadding="0" style="margin-top: 15px;">
	                                <tr>	                                	
	                                    <td valign="top">
	                                        <!-- This is used to display Google images -->
	                                        <div id="logoGoogleImagesScroll"
	                                             style="width:319px; height:171px; overflow-x:hidden; overflow-y:auto; margin-right: 10px;">
	                                             
	                                             <select name="cyytecode" id="cyytecode" >
					                                <option value="">--Select--</option>
					                                <c:forEach var="item" items="${myPostInCyytes}">                                    
					                                    <option value="${item.incyyteCode}"><c:out value="${item.pageName}"/></option>                                   
					                                </c:forEach>
					                            </select>
	                                             <br>
	                                             <span id="msgLabel" ></span>
	                                        </div>
	                                    </td>
	                                    <td align="left">
	                                        <div><img id="logoImgSelect" class="upload_photos_container_big_img" src="ui/images/uploading_big_img.png"></div>
	                                    </td>
	                                </tr>
	                            </table>
                       		</td>
                       	</tr>
                       
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
  
  <script type="text/javascript">
	$(document).ready(function() {
		
		$("#topInCyyteTable") 
	    .tablesorter({
	    	widgets: ['zebra'],
	    	sortList: [[0,1]],
	    	headers: {2: {sorter: false}}
	    })
	    .tablesorterPager({container: $("#pagination")}); 			

	});
</script>

<script type="text/javascript" src="ui/js/googleanalytics.js"></script>

</body>
</html>

