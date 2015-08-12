<%@page import="com.incyyte.app.web.SessionKeys"%>
<%@page import="com.incyyte.app.domain.User"%>
<%@page import="com.incyyte.app.service.util.Utility"%>
<%@page import="com.incyyte.app.domain.InCyyteChart"%>
<%@page import="com.incyyte.app.domain.CyyteResponse"%>
<%@page import="com.incyyte.app.service.util.Constants"%>
<%@page import=" com.incyyte.app.domain.BusinessAccount"%>
<%@page import="java.util.*"%>
<%@page import="com.incyyte.app.web.model.UserModel"%>
<%@page import="org.apache.commons.lang.StringUtils"%>
<%@ include file="/WEB-INF/jsp/common/include.jsp" %>

<% User user = (User)request.getSession().getAttribute(SessionKeys.LOGIN_USER); %>

<link rel="stylesheet" href="ui/css/reset.css">

<link rel="stylesheet" href="ui/css/layout.css">
<link rel="stylesheet" href="ui/css/buttons.css"  media="screen">
<link rel="stylesheet" href="ui/css/accordionmenu.css" type="text/css">
<link rel="stylesheet" href="ui/css/icons_sprites.css">
<link rel="stylesheet" href="ui/css/form_elements.css" type="text/css">
<link rel="stylesheet" href="ui/css/tooltip.css">
<link rel="stylesheet" href="fonts/fonts_stylesheet.css">
<link rel="stylesheet" href="ui/modal/colorbox.css">
<link rel="stylesheet" type="text/css" href="ui/css/jquery.lightbox-0.5.css" media="screen" />
<link rel="stylesheet" href="ui/css/jquery.validate.css">
<link rel="stylesheet" href="ui/css/validate/screen.css">
<link rel="stylesheet" href="ui/css/jquery.selectbox.css" type="text/css" />
<link rel="stylesheet" href="ui/css/modal_window.css" type="text/css" media="screen">
<script type="text/javascript" src="ui/js/jquery-ui-1.8.16.custom.min.js"></script>
<script src="ui/js/jquery-1.8.1.js"></script>
<script src="ui/js/jquery-1.8.3.min.js" type="text/javascript"></script> 
<script src="ui/js/tooltip.js"></script>
<script src="ui/js/jquery.color.js"></script>
<script src="ui/js/search_script.js"></script>
<script src="ui/js/jquery_dashboard_rating.js" type="text/javascript"></script>
<script src="ui/js/star_rating.js" type="text/javascript"></script>
<script src="ui/modal/colorbox/jquery.colorbox.js"></script>
<script type="text/javascript" src="js/jquery.form.js"></script>
<script type="text/javascript" src="ui/js/jquery-ui.js"></script>
<script type="text/javascript" src="ui/js/jquery.lightbox-0.5.js"></script>
<script type="text/javascript" src="ui/js/validate/jquery.validate.js" ></script>
<!-- Left Navigation script starts here -->
<script src="ui/js/left_menu.js"></script>
<script src="ui/js/create_buz_account.js"></script>
<script src="ui/js/jquery.selectbox-0.2.js"></script>
<!-- Left Navigation script ends here -->

<!-- Chart Script Start here -->
<script src="ui/js/charts/js/charts_recent_incyyte.js"></script>
<script src="ui/js/charts/js/highcharts.js"></script>
<script src="ui/js/dashboard/dash_my_polls.js"></script>
<!-- Chart Script end here -->

<!-- Rating bar and profile starts here -->
<link rel="stylesheet" type="text/css" href="ui/css/ratingbar.css" />
<link rel="stylesheet" href="ui/css/style_login.css">


<script type="text/javascript" src="ui/js/jquery.ratingbar.js"></script>
<!-- Rating bar and profile ends here -->


<!--- Modal ----->

<script>
			$(document).ready(function(){
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
		        //$(".inline1").colorbox({inline:true, height:"100%"});
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
		
		<link href="ui/css/style.css" media="screen" rel="stylesheet" type="text/css" />
		<script>
		    if (/*@cc_on!@*/false) {
		        var headHTML = document.getElementsByTagName('head')[0].innerHTML;
		        headHTML    += '<link type="text/css" rel="stylesheet" href="ui/css/ie10.css">';
		        document.getElementsByTagName('head')[0].innerHTML = headHTML;
		    }
		</script>
		<script type='text/javascript'>
			$(document).ready(function(){
				$("#bizAccountForm").submit(function(){
					$("#imageBannerLoader").show();
					$("#imageLoader").show();
				});
			});
</script>
		
		<script type="text/javascript">
	
function validateFields(fieldName){
    if (clientValidation(fieldName)){
        return true;
    }
    return false;
}
		
function getContextPath() {
	return "<%=request.getContextPath()%>";
}
		</script>
<!--- Mddal--------------->

<!--[if gte IE 8]>
<style>
	.custom-checkbox input, 
	.custom-radio input {
	left: -3px;
</style>
<![endif]-->
<form:form id="bizAccountForm" name="bizAccountForm" commandName="bizAccountForm" enctype="multipart/form-data" method="post">
          	<% BusinessAccount businessAccount = (BusinessAccount)request.getSession().getAttribute("bizAccount");%>
	<div id="most_resent_incyyte">
	
				<div class="business_detail">
					<table width="100%" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td  valign="top" class="grid_25">
							<strong style="margin-left:25px;">Enter Business Account Details</strong>
							</td>
						</tr>
						<tr>
							<td  valign="top" class="grid_25">
									<table width="120%" border="1" cellspacing="0" cellpadding="0" align="center">
									<%String editable="";
										if(StringUtils.equals(user.getUserType(), "BUSINESS") && StringUtils.isBlank(businessAccount.getSaveChanges())){
											editable="disabled";
										}
										 %>
										<tr>
											<td  valign="top"> Company Name</td>
											<td  valign="top"><input type='text' value="${bizAccountForm.companyName}" <%=editable%> name="companyName" id="companyName" onKeyUp="validateFields('companyName')" style="width:280px" />
											<br>
											<p id="inValidCompName" style="padding-left: 12px; font:14px/20px 'bariol_regularregular','ie8_bariol_regular'; color: #C2002D;"/>
                                          </td>
										</tr>
									<tr><td>&nbsp;&nbsp;&nbsp;&nbsp;</td></tr>
										<tr>
											<td  valign="top">Address Line 1</td>
											<td  valign="top"><input type='text' value="${bizAccountForm.address1}" <%=editable%> name="address1" id="address1"  onKeyUp="validateFields('address1')" style="width:280px"/>
											<div id="inValidAddress1Div">
												<span id="inValidAddress1"  class="inValidAddress1" style="padding-left: 12px; display:none;"></span>
												</div>
											
											</td>
										</tr>
										<tr><td>&nbsp;&nbsp;&nbsp;&nbsp;</td></tr>
										<tr>
											<td  valign="top">Address Line 2</td>
											<td  valign="top"><input type='text' value="${bizAccountForm.address2}" <%=editable%>  name="address2" id="address2" onKeyUp="validateFields('address2')" style="width:280px"/>
											<div id="inValidAddress2Div">
												<span id="inValidAddress2"  class="inValidAddress2" style="padding-left: 12px; display:none;"></span>
												</div>
											
											</td>
										</tr>
										<tr><td>&nbsp;&nbsp;&nbsp;&nbsp;</td></tr>
										<tr>
											<td  valign="top">City</td>
											<td  valign="top"><input type='text' value="${bizAccountForm.city}" <%=editable%> name="city" id="city" onKeyUp="validateFields('city')" style="width:280px"/>
											
											<div id="inValidCityDiv">
												<span id="inValidCity"  class="inValidCity" style="padding-left: 12px; display:none;"></span>
												</div>
											</td>
										</tr>
										<tr><td>&nbsp;&nbsp;&nbsp;&nbsp;</td></tr>
										<tr>
											<td  valign="top">Postcode</td>
											<td  valign="top">
											<input type='text' name="postalCode" value="${bizAccountForm.postalCode}" <%=editable%> id="postalCode" onKeyUp="validateFields('postalCode')" />
											<div id="inValidPostcodeDiv">
												<span id="inValidPostcode" class="inValidPostcode" style="padding-left: 12px; display:none;"></span>
												</div>
											</td>
										</tr>
										<tr><td>&nbsp;&nbsp;&nbsp;&nbsp;</td></tr>
										<tr>
											<td  valign="top">Country</td>
											<td  valign="top"><input type='text' value="${bizAccountForm.country}" <%=editable%> name="country" id="country" onKeyUp="validateFields('country')" style="width:280px"/>
											
												<div id="inValidCountryDiv">
												<span id="inValidCountry"  class="inValidCountry" style="padding-left: 12px; display:none;"></span>
												</div>
											
											</td>
										</tr>
										<tr><td>&nbsp;&nbsp;&nbsp;&nbsp;</td></tr>
										<tr>
											<td  valign="top">Contact Email Address</td>
											<td  valign="top"><input type='text' value="${bizAccountForm.contactEmail}" <%=editable%> name="contactEmail" id="contactEmail" onKeyUp="validateFields('contactEmail')" style="width:280px"/>
												<div id="inValidEmailDiv">
												<span id="inValidEmail"  class="inValidEmail" style="padding-left: 12px; display:none;"></span>
												</div>
											</td>
										</tr>

										<tr><td>&nbsp;&nbsp;&nbsp;&nbsp;</td></tr>
										<tr>
											<td  valign="top">Phone</td>
											<td  valign="top"><input type='text' value="${bizAccountForm.phone}" <%=editable%> name="phone" id="phone"  onKeyUp="validateFields('phone')" style="width:280px"/>
											<div id="inValidPhoneDiv">
												<span id="inValidPhone"  class="inValidPhone" style="padding-left: 12px; display:none;"></span>
												</div>
											</td>
										</tr>
										<tr><td>&nbsp;&nbsp;&nbsp;&nbsp;</td></tr>
										<tr>
											<td  valign="top">Company Information Paragraph1</td>
											<td  valign="top"><textarea name="companyInfoPara1" <%=editable%> style="resize: none;" id="companyInfoPara1" onKeyUp="validateFields('companyInfoPara1')" cols="70" rows="5" />${bizAccountForm.companyInfoPara1}</textarea>
											<br>
											<p id="inValidDescription1" style="padding-left: 12px; font:14px/20px 'bariol_regularregular','ie8_bariol_regular'; color: #C2002D;"/>
										</tr>
										<tr><td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td></tr>
										<tr>
											<td  valign="top">Company Information Paragraph2</td>
											<td  valign="top"><textarea name="companyInfoPara2" <%=editable%> style="resize: none;" id="companyInfoPara2" onKeyUp="validateFields('companyInfoPara2')" cols="70" rows="5" />${bizAccountForm.companyInfoPara2}</textarea>
											<br>
											<p id="inValidDescription2" style="padding-left: 12px; font:14px/20px 'bariol_regularregular','ie8_bariol_regular'; color: #C2002D;"/>
											</td>
										</tr>
										
										<tr><td>&nbsp;&nbsp;&nbsp;&nbsp;</td></tr>
									
											<tr>
											<td  valign="top">Website URL</td>
											<td  valign="top"><input type='text' id="websiteUrl"  value="${bizAccountForm.websiteUrl}"<%=editable%> name="websiteUrl" onchange="validateFields('websiteUrl')" style="width:280px"/>
											<br>
											<p id="inValidWebsite" style="padding-left: 12px; font:14px/20px 'bariol_regularregular','ie8_bariol_regular'; color: #C2002D;"/>
											<p id="inValidWebsiteLength" style="padding-left: 12px; font:14px/20px 'bariol_regularregular','ie8_bariol_regular'; color: #C2002D;"/>
											</td>
										</tr>
										<tr><td>&nbsp;&nbsp;&nbsp;&nbsp;</td></tr>
										<tr>
											<td  valign="center" >Company Logo</td>
											<td  valign="top" id="companyLogoUrl">
												<table width="100%" border="0" cellspacing="0" cellpadding="0" >
												<tr><td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td></tr>
									             <tr>
									               <td width="20%" rowspan="3" valign="top">
									           	 
                                               	<%  if(businessAccount!=null && businessAccount.getCompanyLogoUrl() != null){%>
				                    			<img src="<%=businessAccount.getCompanyLogoUrl()%>" alt="Profile Picture" width="111" height="111" class="photoframe">
				                    	<%	} else { %>
				                    			 <img src="ui/images/profile_picture.png" alt="Profile Picture alt">           	
				                    	<%	} %>                  
									                  </td>
									                  <%if(StringUtils.equals(user.getUserType(), "BUSINESS")){ %>
									                  <c:choose>
										             <c:when test="${not empty bizAccountForm.saveChanges}">
										
									                    <td valign="bottom"><a id="uploadlogolink" class='inline' href="#add_files" title="UPLOAD IMAGE" id="" class="button_gray" style=" width:210px; float:left; margin-bottom:20px;"> <span  class="title_gray">UPLOAD IMAGE</span></a></td>
									                  
									                       <div id='companylogo_delete' class='nodisplay'  >Are you sure want to delete profile image.<br>
									                                    <a onClick="deleteCompLogo('false')" style="color:black;" >Cancel</a> | <a onClick="deleteCompLogo('true')" style="color:black;">Delete</a> </div>        
									                  <tr>
									                    <td height="26" valign="top" class="font_14px"  style=""  id="companyLogoMessage">Maximum size of 700k. JPG, GIF, PNG.</td>
									                  </tr>
									                  <tr>
									                    <td valign="top" class="font_16px"><a style="" id="deletelogolink" class="deleteComplink" href="javascript:deleteCompanyLogoConfirm('true');">Delete this image</a></td>
									                  </tr>
									                    
									                   </c:when>
									                   
									                   <c:otherwise>
									                    <td valign="bottom"><a id="uploadlogolink" class='inline' href="#add_files" title="UPLOAD IMAGE" id="" class="button_gray" style="display:none; width:210px; float:left; margin-bottom:20px;"> <span  class="title_gray">UPLOAD IMAGE</span></a></td>
									                  
									                       <div id='companylogo_delete' class='nodisplay'  >Are you sure want to delete profile image.<br>
									                                    <a onClick="deleteCompLogo('false')" style="color:black;" >Cancel</a> | <a onClick="deleteCompLogo('true')" style="color:black;">Delete</a> </div>        
									                  <tr>
									                    <td height="26" valign="top" class="font_14px"  style="display:none;"  id="companyLogoMessage">Maximum size of 700k. JPG, GIF, PNG.</td>
									                  </tr>
									                  <tr>
									                    <td valign="top" class="font_16px"><a style="display:none;" id="deletelogolink" class="deleteComplink" href="javascript:deleteCompanyLogoConfirm('true');">Delete this image</a></td>
									                  </tr>
									                   </c:otherwise>
									                    </c:choose>
									                   <%}%> 
                                                    
                                                   
									                 <%  if(StringUtils.equals(user.getUserType(), "USER")) {%>
									               
									                   <td valign="bottom"><a id="uploadlogolink" class='inline' href="#add_files" title="UPLOAD IMAGE" id="" class="button_gray" style="width:210px; float:left; margin-bottom:20px;"> <span  class="title_gray">UPLOAD IMAGE</span></a></td>
									                  </tr>
									                       <div id='companylogo_delete' class='nodisplay'  >Are you sure want to delete profile image.<br>
									                                    <a onClick="deleteCompLogo('false')" style="color:black;" >Cancel</a> | <a onClick="deleteCompLogo('true')" style="color:black;">Delete</a> </div>        
									                  <tr>
									                    <td height="26" valign="top" class="font_14px"    id="companyLogoMessage">Maximum size of 700k. JPG, GIF, PNG.</td>
									                  </tr>
									                  <tr>
									                    <td valign="top" class="font_16px"><a  id="deletelogolink" class="deleteComplink" href="javascript:deleteCompanyLogoConfirm('true');">Delete this image</a></td>
									                  </tr>
									                 
									                  <%} %>
									                
									                </table>
									                
									                  <div id="inValidCompanyLogoDiv">
												       <span id="inValidCompanyLogo"  style="padding-left: 12px; display:none;"></span>
											     	</div>
											
											</td>
										</tr>
										
										<tr>
											<td  valign="center">Banner Image</td>
											<td  valign="top" id="">
												<table width="100%" border="0" cellspacing="0" cellpadding="0">
												<tr><td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td></tr>
									               
									             <tr>
									               <td width="20%" rowspan="3" valign="top">
									           	 <%  if(businessAccount!=null && businessAccount.getBannerUrl() != null){%>
				                    			<img src="<%=businessAccount.getBannerUrl()%>" alt="Profile Picture" width="111" height="111" class="photoframe">
				                    	<%	} else { %>
				                    			 <img src="ui/images/profile_picture.png" alt="Profile Picture alt">           	
				                    	<%	} %>                            
									                  </td>
									                    <%if(StringUtils.equals(user.getUserType(), "BUSINESS")){ %>
									                        <c:choose>
										               <c:when test="${not empty bizAccountForm.saveChanges}">
									                    <td valign="bottom"><a id="uploadBannerlogolink" class='inline' href="#add_banner" title="UPLOAD IMAGE" id="" class="button_gray" style=" width:210px; float:left; margin-bottom:20px;"> <span  class="title_gray">UPLOAD IMAGE</span></a></td>
									                
									                       <div id='banner_delete' class='nodisplay'  >Are you sure want to delete profile image.<br>
									                                    <a onClick="deleteBanner('false')" style="color:black;">Cancel</a> | <a onClick="deleteBanner('true')" style="color:black;">Delete</a> </div>            
									                  <tr>
									                    <td height="26" valign="top" class="font_14px"  style=""  id="bannerLogoMessage">Maximum size of 700k. JPG, GIF, PNG.</td>
									                  </tr>
									                  <tr>
									                    <td valign="top" class="font_16px"><a  style=""  id="deletebannerlogolink" href="javascript:deleteBannerConfirm('true');">Delete this image</a></td>
									                  </tr>
									                  </c:when>
									                  <c:otherwise>
									                    <td valign="bottom"><a id="uploadBannerlogolink" class='inline' href="#add_banner" title="UPLOAD IMAGE" id="" class="button_gray" style="display:none; width:210px; float:left; margin-bottom:20px;"> <span  class="title_gray">UPLOAD IMAGE</span></a></td>
									                
									                       <div id='banner_delete' class='nodisplay'  >Are you sure want to delete profile image.<br>
									                                    <a onClick="deleteBanner('false')" style="color:black;">Cancel</a> | <a onClick="deleteBanner('true')" style="color:black;">Delete</a> </div>            
									                  <tr>
									                    <td height="26" valign="top" class="font_14px"  style="display:none;"  id="bannerLogoMessage">Maximum size of 700k. JPG, GIF, PNG.</td>
									                  </tr>
									                  <tr>
									                    <td valign="top" class="font_16px"><a  style="display:none;"  id="deletebannerlogolink" href="javascript:deleteBannerConfirm('true');">Delete this image</a></td>
									                  </tr>
									                  </c:otherwise>
									                  </c:choose>
									                  <%}%>
									                    <%if(StringUtils.equals(user.getUserType(), "USER")) {%>
									                  <td valign="bottom"><a id="uploadBannerlogolink" class='inline' href="#add_banner" title="UPLOAD IMAGE" id="" class="button_gray" style=" width:210px; float:left; margin-bottom:20px;"> <span  class="title_gray">UPLOAD IMAGE</span></a></td>
									                  </tr>
									                       <div id='banner_delete' class='nodisplay'  >Are you sure want to delete profile image.<br>
									                                    <a onClick="deleteBanner('false')" style="color:black;">Cancel</a> | <a onClick="deleteBanner('true')" style="color:black;">Delete</a> </div>            
									                  <tr>
									                    <td height="26" valign="top" class="font_14px"   id="bannerLogoMessage">Maximum size of 700k. JPG, GIF, PNG.</td>
									                  </tr>
									                  <tr>
									                    <td valign="top"  class="font_16px"><a   id="deletebannerlogolink" href="javascript:deleteBannerConfirm('true');">Delete this image</a></td>
									                  </tr>
									                  <%} %>
									                </table>
											<div id="inValidBannerDiv">
												       <span id="inValidBanner"  style="padding-left: 12px; display:none;"></span>
											     	</div>
											</td>
										</tr>
										
									</table>
							</td>
						</tr>
							<tr>
						</tr>
						<tr>
								<td  valign="top" class="grid_25">
								
								<%if(StringUtils.equals(user.getUserType(), "USER")){%>
									<a href="javascript:editSaveBuzAcct('companyName','address1','postalCode','websiteUrl','address2','city','country','contactEmail','phone','companyInfoPara1','companyInfoPara2','storeBusinessAccountInfo.cyt');" title="" id="saveUserAcctInfo" class="button_green" style="width: 200px; float: right;">
									<span	class="title_green" id="saveUserAcctInfoSpan">SAVE CHANGES</span></a>
												<div id="create_bizacctdiv">
												<span id="create_bizacct"  style="padding-left: 12px; display:none;"></span>
												</div>
												<div id="createbizerrorDiv">
												<span id="create_biz_error"  style="padding-left: 12px; display:none;"></span>
												</div>
												
												<div id="enterBizDetailsErrorDiv">
												<span id="bizdetails_error"  style="padding-left: 12px; display:none;"></span>
												</div>
										<%} if(StringUtils.equals(user.getUserType(), "BUSINESS")){%>
										<c:choose>
										<c:when test="${not empty bizAccountForm.saveChanges}">
									<a href="javascript:editSaveBuzAcct('companyName','address1','postalCode','websiteUrl','address2','city','country','contactEmail','phone','companyInfoPara1','companyInfoPara2','storeBusinessAccountInfo.cyt');" title="" id="saveBizAcctInfo" class="button_green" style="width: 200px; float: right;">
									<span	class="title_green" id="saveBizAcctInfoSpan">SAVE CHANGES</span></a>
								</c:when>
								<c:otherwise>
								<a href="javascript:editSaveBuzAcct('companyName','address1','postalCode','websiteUrl','address2','city','country','contactEmail','phone','companyInfoPara1','companyInfoPara2','storeBusinessAccountInfo.cyt');" title="" id="editBizAcctInfo" class="edit" style="width: 200px; float: right;">
								 <span class="title_green" id="editBizAcctInfoSpan">EDIT</span></a>
								</c:otherwise>
								</c:choose>																 
								<div id="edit_bizacctdiv"><span id="edit_bizacct"  style="padding-left: 12px; display:none;"></span></div>
								<div id="editbizerrorDiv">
												<span id="edit_biz_error"  style="padding-left: 12px; display:none;"></span>
								</div>							
							<%}%>
							</td>
						</tr>
						
					</table>
				</div>
				<!-- company logo upload -->
		    <div style="display:none;">
			<div id="add_files" >				
				<div id="modal_media_icon">
					<ul >						  
					  <li id="modal_photos"><a href="#" alt="Photos" title="Photos" class="active"></a></li>
					</ul>
				</div>

				<div id="file_details">
					<div id="add_photos" class="c_add_photos">				 
						<table width="100%" border="0" cellspacing="0" cellpadding="0" >
							<tr><td class="heading1">Add Photos</td><td>&nbsp;</td></tr>
							<tr><td valign="top" align="left">
								<table width="100%" border="0" cellspacing="0" cellpadding="0">
									<tr><td><div class="fileInputs">													
										<div id="incyyte_browse_photos" class="button_gray" style=" width:140px; float:left; margin-bottom:20px; position: absolute; top: 0px;left: 0px; z-index: 1;">
										<span class="title_gray">BROWSE</span></div></div></td></tr>
									<tr><td>&nbsp;&nbsp;</td></tr>
									<tr><td class="font_18px"><br> <br>File Name:</td></tr>
									<tr><td class="font_16px"><div style="word-wrap: break-word; width:160px;" ><span id="incyyte_photo_file_name"></span></div></td></tr>
									<tr><td>&nbsp;</td></tr>
									<tr valign="bottom" style="display: none;" id="incyyte_photos_error_msg">
                                	<td><span class="errorLabel">Please upload the correct image format (jpg, gif, ..)</span></td></tr>
                                	<tr valign="bottom" style="display: none;" id="incyyte_photos_error_msg2">
                                	<td><span class="errorLabel">The uploaded image exceeds the maximum allowed size(2 MB)</span></td></tr>
									<tr valign="bottom"><td  align="center"><img src="ui/images/indicator-loader.gif" id="imageLoader" width="32" height="32" style="padding:8px 0 0 0;margin-bottom:20px 0 100px; " ></td></tr>
									</table>
								</td>
								<td align="center" valign="top" id="incyyte_photo_not_loaded">
									<div id="photos_container">
									
									    	<%   	if(businessAccount!=null && businessAccount.getCompanyLogoUrl() != null){%>
				                    			<img id="CompanyLogoURL"src="<%=businessAccount.getCompanyLogoUrl()%>" alt="Profile Picture" width="233" height="233">
				                    	<%	} else{ %>
				                    			<img src="ui/images/photo1.png">                   	
				                    	<%	} 	%>
									</div>
								</td>
								<td align="center" valign="top" style="display:none;" id="incyyte_photo_loaded">
									<div id="#video_thumbs" > <ul id="videos"><li> <a href="ui/images/view_photos_thumb.png" class="group1"><img src="ui/images/camera_thumb.png" class="photos_thumb" alt="tour" /></a> </li></ul></div></td></tr>
							<tr><td colspan="2" valign="bottom" ><span class="licence">You must have the licence to use this image</span> <div title="Upload Now" id="incyyteUploadPhotoButton" class="button_red" style="width:140px; float:right;"> <span class="title_red">UPLOAD NOW</span></div></td></tr>
						</table>					
					</div>
				</div>
			</div>
		</div>
		<!-- end upload company logo -->
	<!-- banner logo upload -->
<div style="display:none;">
			<div id="add_banner" >				
				<div id="modal_media_icon">
					<ul >						  
					  <li id="modal_photos"><a href="#" alt="Photos" title="Photos" class="active"></a></li>
					</ul>
				</div>

				<div id="file_details">
					<div id="add_photos" class="c_add_photos">				 
						<table width="100%" border="0" cellspacing="0" cellpadding="0" >
							<tr><td class="heading1">Add Photos</td><td>&nbsp;</td></tr>
							<tr><td valign="top" align="left">
								<table width="100%" border="0" cellspacing="0" cellpadding="0">
									<tr><td><div class="fileInputs">													
										<div id="incyyte_browse_banner" class="button_gray" style=" width:140px; float:left; margin-bottom:20px; position: absolute; top: 0px;left: 0px; z-index: 1;">
										<span class="title_gray">BROWSE</span></div></div></td></tr>
									<tr><td>&nbsp;&nbsp;</td></tr>
									<tr><td class="font_18px"><br> <br>File Name:</td></tr>
									<tr><td class="font_16px"><div style="word-wrap: break-word; width:160px;" ><span id="incyyte_banner_file_name"></span></div></td></tr>
									<tr><td>&nbsp;</td></tr>
									<tr valign="bottom" style="display: none;" id="incyyte_banner_error_msg">
                                	<td><span class="errorLabel">Please upload the correct image format (jpg, gif, ..)</span></td></tr>
                                	<tr valign="bottom" style="display: none;" id="incyyte_banner_error_msg2">
                                	<td><span class="errorLabel">The uploaded image exceeds the maximum allowed size(2 MB)</span></td></tr>
									<tr valign="bottom"><td  align="center"><img src="ui/images/indicator-loader.gif" id="imageBannerLoader" width="32" height="32" style="padding:8px 0 0 0;margin-bottom:20px 0 100px; " ></td></tr>
									</table>
								</td>
								<td align="center" valign="top" id="incyyte_banner_not_loaded">
									<div id="photos_container">
									<%   	if(businessAccount!=null && businessAccount.getBannerUrl() != null){%>
				                    			<img src="<%=businessAccount.getBannerUrl()%>" alt="Profile Picture" width="233" height="233">
				                    	<%	} else{ %>
				                    			<img src="ui/images/photo1.png">                   	
				                    	<%	} 	%>
									</div>
								</td>
								<td align="center" valign="top" style="display:none;" id="incyyte_banner_loaded">
									<div id="#video_thumbs" > <ul id="videos"><li> <a href="ui/images/view_photos_thumb.png" class="group1"><img src="ui/images/camera_thumb.png" class="photos_thumb" alt="tour" /></a> </li></ul></div></td></tr>
							<tr><td colspan="2" valign="bottom" ><span class="licence">You must have the licence to use this image</span> <div title="Upload Now" id="incyyteUploadBannerButton" class="button_red" style="width:140px; float:right;"> <span class="title_red">UPLOAD NOW</span></div></td></tr>
						</table>					
					</div>
				</div>
			</div>
		</div>
		<!-- end upload banner logo -->
			        <form:input path="uploadedLogo"  type="file" id="incyyte_photo_file_input" style="display:none;" />
			         <form:input path="uploadedBannerLogo"  type="file" id="incyyte_banner_file_input" style="display:none;" />
					<form:hidden path="fileName" id="file_name_tst"/>
					<form:hidden path="bannerFileName" id="file_name_banner_tst"/>
					<form:hidden path="saveChanges" id="saveChanges"/>
					<form:hidden path="makeDefault" id="mkDefault"/>
					
	</div>
</form:form>
								
	