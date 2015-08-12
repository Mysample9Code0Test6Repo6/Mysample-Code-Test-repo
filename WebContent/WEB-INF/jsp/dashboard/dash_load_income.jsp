<%@page import="com.incyyte.app.web.SessionKeys"%>
<%@page import="com.incyyte.app.domain.User"%>
<%@page import="com.incyyte.app.domain.Answer"%>
<%@page import="com.incyyte.app.domain.InCyyteChart"%>
<%@page import="com.incyyte.app.domain.CyyteResponse"%>
<%@page import="com.incyyte.app.service.util.Constants"%>
<%@page import="com.incyyte.app.service.util.Utility"%>
<%@page import="java.util.*"%>
<%@ page import="org.apache.commons.lang.StringUtils" %>
<%@ include file="/WEB-INF/jsp/common/include.jsp" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

</head>
<body>
	<div id="uploadcontent">

		<script src="ui/js/accordian/jquery-1.6.2.js"></script>
		<script type="text/javascript" src="ui/js/jquery-ui-1.8.16.custom.min.js"></script>
		<script src="ui/js/jquery-1.3.2.min.js"></script>
		<script src="ui/js/jquery-1.7.1.js"></script>
		<script src="ui/js/jquery-1.8.2.min.js"></script>
		<script src="../../ui/js/login.js"></script>
		<script src="ui/js/jquery.color.js"></script>
		<script src="ui/js/search_script.js"></script>
		
		<!-- copied from sent page -->
		<script type="text/javascript" src="ui/js/jquery-ui.js"></script>
		<script type="text/javascript" src="ui/js/validate/jquery.validate.js" ></script>
		<script src="ui/js/jquery.selectbox-0.2.js"></script>
		<script type="text/javascript" src="ui/js/jquery.ratingbar.js"></script>
		<!-- ends here -->
		
		<script src="ui/modal/colorbox/jquery.colorbox.js"></script>
		<script src="ui/js/jquery_dashboard_rating.js" type="text/javascript"></script>
		<script src="ui/js/star_rating.js" type="text/javascript"></script>
		<script src="ui/js/customInput.jquery.js" type="text/javascript"></script>
		
		<script src="ui/js/dashboard/dash_income.js" type="text/javascript"></script>		
		
		<script src="ui/js/jquery.limit.js"></script>
		<script src="ui/js/zoomphoto.js"></script>
		<script src="ui/js/prettyCheckboxes.js" type="text/javascript" charset="utf-8"></script>
		<script type="text/javascript" src="ui/js/autoresize.jquery.js"></script>
		<script src="ui/js/accordian/jquery.ui.core.js"></script>
		<script src="ui/js/accordian/jquery.ui.widget.js"></script>
		<script src="ui/js/accordian/jquery.ui.accordion.js"></script>
		<script src="ui/js/jquery.ui.datepicker.js"></script>
		<script type="text/javascript" src="ui/js/jquery.form.js"></script>
		<script type="text/javascript" src="ui/js/jquery-ui.js"></script>
		<link rel="stylesheet" href="ui/fancyplayer_code/css/jquery.fancybox.css" type="text/css" media="screen" />
		<script type="text/javascript" src="ui/fancyplayer_code/js/flowplayer-3.1.1.min.js"></script>
		<script type="text/javascript" src="ui/fancyplayer_code/js/jquery.fancybox-1.2.1.pack.js"></script>
		<script type="text/javascript" src="ui/fancyplayer_code/js/jquery.easing.1.3.js"></script>
		<script type="text/javascript" src="ui/fancyplayer_code/js/fancyplayer.js"></script>
		
		<!-- Left Navigation script starts here -->
		<script src="ui/js/left_menu.js"></script>
		<!-- Left Navigation script ends here -->
		<!--- Questions Tabs starts------->
		<script type="text/javascript" src="ui/js/ddaccordion.js"></script>
		
		<script type="text/javascript">
		function MM_CheckFlashVersion(reqVerStr,msg){
		  with(navigator){
		    var isIE  = (appVersion.indexOf("MSIE") != -1 && userAgent.indexOf("Opera") == -1);
		    var isWin = (appVersion.toLowerCase().indexOf("win") != -1);
		    if (!isIE || !isWin){  
		      var flashVer = -1;
		      if (plugins && plugins.length > 0){
		        var desc = plugins["Shockwave Flash"] ? plugins["Shockwave Flash"].description : "";
		        desc = plugins["Shockwave Flash 2.0"] ? plugins["Shockwave Flash 2.0"].description : desc;
		        if (desc == "") flashVer = -1;
		        else{
		          var descArr = desc.split(" ");
		          var tempArrMajor = descArr[2].split(".");
		          var verMajor = tempArrMajor[0];
		          var tempArrMinor = (descArr[3] != "") ? descArr[3].split("r") : descArr[4].split("r");
		          var verMinor = (tempArrMinor[1] > 0) ? tempArrMinor[1] : 0;
		          flashVer =  parseFloat(verMajor + "." + verMinor);
		        }
		      }
		      // WebTV has Flash Player 4 or lower -- too low for video
		      else if (userAgent.toLowerCase().indexOf("webtv") != -1) flashVer = 4.0;
		
		      var verArr = reqVerStr.split(",");
		      var reqVer = parseFloat(verArr[0] + "." + verArr[2]);
		  
		      if (flashVer < reqVer){
		        if (confirm(msg))
		          window.location = "http://www.macromedia.com/shockwave/download/download.cgi?P1_Prod_Version=ShockwaveFlash";
		      }
		    }
		  } 
		}
		</script>
		
		<script type="text/javascript">
		
		//Initialize :
		ddaccordion.init({
			headerclass: "incomming_incyyte_tab", //Shared CSS class name of headers group
			contentclass: "incomming_incyyte_detail", //Shared CSS class name of contents group
			revealtype: "click", //Reveal content when user clicks or onmouseover the header? Valid value: "click", "clickgo", or "mouseover"
			mouseoverdelay: 200, //if revealtype="mouseover", set delay in milliseconds before header expands onMouseover
			collapseprev: true, //Collapse previous content (so only one open at any time)? true/false 
			defaultexpanded: [], //index of content(s) open by default [index1, index2, etc]. [] denotes no content.
			onemustopen: true, //Specify whether at least one header should be open always (so never all headers closed)
			animatedefault: false, //Should contents open by default be animated into view?
			scrolltoheader: false, //scroll to header each time after it's been expanded by the user?
			persiststate: false, //persist state of opened contents within browser session?
			toggleclass: ["closedincomming_incyyte", "openincomming_incyyte"], //Two CSS classes to be applied to the header when it's collapsed and expanded, respectively ["class1", "class2"]
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
		<!--- Questions Tabs Ends------->
		<!--- Modal Script Starts Here----->
		<script>
			$(document).ready(function(){
				//Examples of how to assign the ColorBox event to elements
				$(".group1").colorbox({rel:'group1'});
				$(".group2").colorbox({rel:'group2', transition:"fade"});
				$(".group3").colorbox({rel:'group3', transition:"none", width:"75%", height:"75%"});
				$(".group4").colorbox({rel:'group4', slideshow:true});
				$(".ajax").colorbox();
				$(".youtube").colorbox({iframe:true, innerWidth:425, innerHeight:344});
				$(".vimeo").colorbox({iframe:true, innerWidth:500, innerHeight:409});
				$(".iframe").colorbox({iframe:true, width:"80%", height:"80%"});
				$(".inline").colorbox({inline:true});
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
		<!--- Mddal Script Starts --------------->
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
		    
		    function submitVoteForm(formId){
		    	$('#'+formId).submit();
		    }
		</script>
		<script type="text/javascript">
			// Run the script on DOM ready:
			$(function(){
				$('input').customInput();
			});
			</script>
		<!--- placeholder Ends----->
		<!--[if gte IE 8]>
		<style>
		.custom-checkbox input, 
		.custom-radio input {
			left: -1px;
		}
		</style>
		<![endif]-->
	</div>
	<c:forEach var="myIncoming" items="${inCyyteResults}" varStatus="status" end="${INCOME_ROW_MAX}">
			
			<% InCyyteChart cyyteChart = (InCyyteChart)pageContext.getAttribute("myIncoming");%>
			
			<script type="text/javascript">
				$(function() {
				    var button = $('#dropBoxButton${status.index}');
				    var box = $('#dropBox${status.index}');
				    var form = $('#dropBoxContent${status.index}');
				    button.removeAttr('href');
				    button.mouseup(function(login) {
				        box.toggle();
				        button.toggleClass('active');
				    });
				    form.mouseup(function() { 
				        return false;
				    });
				    $(this).mouseup(function(login) {
				        if(!($(login.target).parent('#dropBoxButton${status.index}').length > 0)) {
				            button.removeClass('active');
				            box.hide();
				        }
				    });
				});
				
			    $(function () {
			    	$(".fancybox-popup").fancybox({
			        maxWidth	: 800,
			    		maxHeight	: 600,
			    		fitToView	: false,
			    		width		: '70%',
			    		height		: '70%',
			    		autoSize	: false,
			    		closeClick	: false,
			    		openEffect	: 'none',
			    		closeEffect	: 'none'});
			    		});
			    function abcd(ab){
			    	alert("hi");
			    	alert(ab);
			    }
		    </script>
			
			<c:choose>
				<c:when test="${myIncoming.incyyteClosed}">
					<div class="incomming_thickborder_line"></div>
					<div class="incomming_incyyte_tab2">
						<table width="100%" border="0" cellspacing="0" cellpadding="0">
					  		<tr>
					    		<td width="12%" valign="top" >
					    			<c:if test="${myIncoming.incyyte.createdBy != 'ANONYMOUS'}">
					    			<c:choose>
								       <c:when test="${not empty myIncoming.incyyte.senderProfilePic}">
								       <img src="<%=cyyteChart.getIncyyte().getSenderProfilePic() %>" width="46" height="46" alt="User Photo" class="photoframe"/>
								       </c:when>
								       <c:otherwise>
								       <img src="ui/images/default_avatar.png" width="46" height="46" alt="User Photo" class="photoframe" />
								       </c:otherwise>
								    </c:choose>
								    </c:if>	
								    <c:if test="${myIncoming.incyyte.createdBy == 'ANONYMOUS'}">
								       <img src="ui/images/annonymous_icon.png" width="46" height="46" alt="User Photo" class="photoframe" />
								    </c:if>
					    		</td>
					    		<td width="58%"  >
					    			<h3><p class="questionText">${myIncoming.question}</p></h3>
					      			<p>Sender: <strong>${myIncoming.incyyte.createdBy}</strong></p>
					      			<p>Created:  <strong>${myIncoming.incyyte.createdDatePeriod}</strong></p>
					      			<p>Closure:${myIncoming.incyyte.closureDate}</p>
					      			<c:if test="${not empty myIncoming.incyyte.group}">
										<p>Group: ${myIncoming.incyyte.group.groupName}</p>	
									</c:if>	
									<BR>			      			    		
					      		</td>	
			      				<td width="30%">
							      	<p style="float:right;margin-top:-15px;margin-right:5px;" align="center"><span class="font_14px">VOTING <br> CLOSED!</span></p>
									<br><a href="#" onclick="deleteIncomming('${myIncoming.incyyte.id}');" title="Delete Incyyte" class="trash_icon"></a><a href="dashdetails.cyt?code=${myIncoming.incyyte.incyyteCode}&frm=incomming" title="View Result" id="vote_completed" class="vote_completed vote_completed_ie8">  View Result</a>
								</td>			      			     		
					  		</tr>
						</table>
					</div>
					<%int filesCount=0; %>
					<c:forEach var="ans" items="${myIncoming.incyyte.answers}" varStatus="ansStatus">
		         <c:choose>
			   <c:when test="${not empty ans.uploadCDN_url}">
			  <% 
			   filesCount++;
			   %>
			</c:when>
		</c:choose>
	</c:forEach>
	<% if (filesCount >0) { %>
					<div class="" id="" style="margin-bottom:20px">
									      <table width="100%" border="0" cellspacing="1">
					                          <tr width="100%" >
								                <% int fileCount = 1; %>
											   <c:forEach var="ans" items="${myIncoming.incyyte.answers}" varStatus="ansStatus">							 	
												  		<% if (fileCount <=3 ) {%>
												  		<c:choose>
															<c:when test="${not empty ans.uploadCDN_url}">
															<%fileCount = fileCount + 1;%>
															<c:set var="extAnswer"  value="${ans.uploadExt}"/>
															<c:set var="ansUrl"  value="${ans.uploadCDN_url}"/>
															<%  String extAnswer = (String)pageContext.getAttribute("extAnswer");
															String ansVideos[] = {".flv",".mp4",".mpg",".swf",".wmv"};
															String ansImages[] = {".gif",".png",".jpg",".jpeg",".bmp"};
															String ansDocs[] = {".wpd",".wps",".xml",".xlr",".pdf"};
									      					String ansGooleDocs[] = {".doc",".docx",".log",".rtf",".txt",".csv",".pps",".ppt",".xls",".xlsx"};
									      					List <String> extAnsVideos = Arrays.asList(ansVideos); 
									      					List <String> extAnsImages = Arrays.asList(ansImages); 
									      					List <String> extAnsDocs = Arrays.asList(ansDocs);
									      					List <String> extAnsGoogleDocs = Arrays.asList(ansGooleDocs);
									      					String docUrl = "https://docs.google.com/viewer?url=";
															String ansUrl = (String)pageContext.getAttribute("ansUrl");
															String viewUrl = docUrl + ansUrl; %>	
									      					
									      				  <td style="padding:5px;" bgcolor="#D8D8D8"> 
									      				  <%if(extAnsVideos.contains(StringUtils.lowerCase(extAnswer))) {%>
															<a id="answerlink">
															<p id="answerOptionStyle">${fn:substring(ans.answerOption, 0, 35)}</p>
															<a onClick="javascript:playVideo('${ans.id}','player${ans.id}', '${ans.uploadCDN_url}')"><img  src="ui/images/video_thumb.png"  id="answerImageIncyyte"/></a>
															</a>
													        <div style="display:none;">
													        <div id="emailList${ans.id}" class="emailList">
															<a style="cursor:pointer" id="player${ans.id}" ></a>
													        </div></div> 
															<% }else if(extAnsImages.contains(StringUtils.lowerCase(extAnswer))){%>
															<a style="cursor:pointer" href="${ans.uploadCDN_url}" class="fancybox-popup fancybox.iframe">
															<p id="answerOptionStyle">${fn:substring(ans.answerOption, 0, 35)}</p><img  src="${ans.uploadCDN_url}" id="answerImageIncyyte"/>
															</a>
															<%}else if(extAnsGoogleDocs.contains(StringUtils.lowerCase(extAnswer))){ %>	
															<a style="cursor:pointer" href="" onClick="window.open('<%=viewUrl %>','MyWindow'); return false;" target="_blank" >
															<p id="answerOptionStyle">${fn:substring(ans.answerOption, 0, 35)}</p><img src="ui/images/view_docs_thumb.png" id="answerImageIncyyte"/>
															</a>
															<%} else if(extAnsDocs.contains(StringUtils.lowerCase(extAnswer))){ %>
															<a style="cursor:pointer" href="" onClick="window.open('${ans.uploadCDN_url}','MyWindow'); return false;" target="_blank">
															<p id="answerOptionStyle">${fn:substring(ans.answerOption, 0, 35)}</p><img src="ui/images/view_docs_thumb.png" id="answerImageIncyyte"/>
															</a>
															<%} %>
															
													</td>
															</c:when>
														</c:choose> 
												  		<%} %>
											  </c:forEach>
											   <%for (int i = 3; i >= fileCount; i--) {%>
												<td></td>
												
												<%} %>
												</tr>
												</table>
											</div>
											<%} %>
				</c:when>
				<c:when test="${myIncoming.voted}">
					<div class="incomming_thickborder_line"></div>				
					<div class="incomming_incyyte_tab2">
						<table width="100%"  border="0" cellspacing="0" cellpadding="0">
					  		<tr>
					    		<td width="12%" valign="top" >
					    			<c:if test="${myIncoming.incyyte.createdBy != 'ANONYMOUS'}">
					    			<c:choose>
								       <c:when test="${not empty myIncoming.incyyte.senderProfilePic}">
								       <img src="<%=cyyteChart.getIncyyte().getSenderProfilePic() %>" width="46" height="46" alt="User Photo" class="photoframe"/>
								       </c:when>
								       <c:otherwise>
								       <img src="ui/images/default_avatar.png" width="46" height="46" alt="User Photo" class="photoframe" />
								       </c:otherwise>
								       </c:choose> </c:if>	
								       <c:if test="${myIncoming.incyyte.createdBy == 'ANONYMOUS'}">
								       <img src="ui/images/annonymous_icon.png" width="46" height="46" alt="User Photo" class="photoframe" />
								       </c:if>				    			
					    		</td>
					    		<td width="58%">
					    			<p onclick="javascript:window.location.href='dashdetails.cyt?code=${myIncoming.incyyte.incyyteCode}&frm=incomming';"><h3><p class="questionText" >${myIncoming.question}</p></h3></p>
					      			<p>Sender: <strong>${myIncoming.incyyte.createdBy}</strong></p>
					      			<p>Created:  <strong>${myIncoming.incyyte.createdDatePeriod}</strong></p>
					      			<p>Closure:${myIncoming.incyyte.closureDate}</p>
					      			<c:if test="${not empty myIncoming.incyyte.group}">
										<p>Group: ${myIncoming.incyyte.group.groupName}</p>	
									</c:if>	
									<BR>			      			    		
					      		</td>		
			      				<td width="30%" >		
			      					<p style="float:right;margin-top:-15px;margin-right:5px;" align="center"><span   class="font_14px" style="color: red;">STILL <br> VOTING! </span></p>      								      	
									<br><a href="#" onclick="deleteIncomming('${myIncoming.incyyte.id}');" title="Delete Incyyte"><span class="trash_icon"></span></a><a href="dashdetails.cyt?code=${myIncoming.incyyte.incyyteCode}&frm=incomming" title="View Result" id="vote_completed" class="vote_completed vote_completed_ie8">View Result</a>
								</td>
								 </tr>
						</table>
					</div>
								<!-- displaying first three images after voting -->	
			
	               
	    
											<%int filesCnt=0; %>
					       <c:forEach var="ans" items="${myIncoming.incyyte.answers}" varStatus="ansStatus">
		                       <c:choose>
			                        <c:when test="${not empty ans.uploadCDN_url}">
			                                  <% 
			                                  filesCnt++;
			                                     %>
			                           </c:when>
		                      </c:choose>
	                         </c:forEach>
							<%if(filesCnt>0){%>
										<div class="" id="" style="margin-bottom:20px">
									      <table width="100%" border="0" cellspacing="0">
					                          <tr width="100%">
								                <% int fileCount = 1; %>
											   <c:forEach var="ans" items="${myIncoming.incyyte.answers}" varStatus="ansStatus">							 	
												  		<% if (fileCount <=3 ) {%>
												  		<c:choose>
															<c:when test="${not empty ans.uploadCDN_url}">
															<%fileCount = fileCount + 1;%>
															<c:set var="extAnswer"  value="${ans.uploadExt}"/>
															<c:set var="ansUrl"  value="${ans.uploadCDN_url}"/>
															<%  String extAnswer = (String)pageContext.getAttribute("extAnswer");
															String ansVideos[] = {".flv",".mp4",".mpg",".swf",".wmv"};
															String ansImages[] = {".gif",".png",".jpg",".jpeg",".bmp"};
															String ansDocs[] = {".wpd",".wps",".xml",".xlr",".pdf"};
									      					String ansGooleDocs[] = {".doc",".docx",".log",".rtf",".txt",".csv",".pps",".ppt",".xls",".xlsx"};
									      					List <String> extAnsVideos = Arrays.asList(ansVideos); 
									      					List <String> extAnsImages = Arrays.asList(ansImages); 
									      					List <String> extAnsDocs = Arrays.asList(ansDocs);
									      					List <String> extAnsGoogleDocs = Arrays.asList(ansGooleDocs);
									      					String docUrl = "https://docs.google.com/viewer?url=";
															String ansUrl = (String)pageContext.getAttribute("ansUrl");
															String viewUrl = docUrl + ansUrl; %>	
									      					
									      				 <td style="padding:5px;" bgcolor="#D8D8D8"> 
									      					<%if(extAnsVideos.contains(StringUtils.lowerCase(extAnswer))) {%>
									      					
															<!-- this is for flow player -->
															<a id="answerlink">
															<p id="answerOptionStyle">${fn:substring(ans.answerOption, 0, 35)}</p>
															<a onClick="javascript:playVideo('${ans.id}','player${ans.id}', '${ans.uploadCDN_url}')"><img  src="ui/images/video_thumb.png"  id="answerImageIncyyte"/></a>
															</a>
													        <div style="display:none;">
													        <div id="emailList${ans.id}" class="emailList ">
															<a style="cursor:pointer" id="player${ans.id}" ></a>
													        </div></div> 
													      <%}else if(extAnsImages.contains(StringUtils.lowerCase(extAnswer))){%>
															<a style="cursor:pointer" href="${ans.uploadCDN_url}" class="fancybox-popup fancybox.iframe">
															<p id="answerOptionStyle">${fn:substring(ans.answerOption, 0, 35)}</p><img  src="${ans.uploadCDN_url}" id="answerImageIncyyte"/>
															</a>
															<%}else if(extAnsGoogleDocs.contains(StringUtils.lowerCase(extAnswer))){ %>	
															<a style="cursor:pointer" href="" onClick="window.open('<%=viewUrl %>','MyWindow'); return false;" target="_blank" >
															<p id="answerOptionStyle">${fn:substring(ans.answerOption, 0, 35)}</p><img src="ui/images/view_docs_thumb.png" id="answerImageIncyyte"/>
															</a>
															<%} else if(extAnsDocs.contains(StringUtils.lowerCase(extAnswer))){ %>
															<a style="cursor:pointer" href="" onClick="window.open('${ans.uploadCDN_url}','MyWindow'); return false;" target="_blank" >
															<p id="answerOptionStyle">${fn:substring(ans.answerOption, 0, 35)}</p><img src="ui/images/view_docs_thumb.png" id="answerImageIncyyte"/>
															</a>
															<%} %>
																</td>
															</c:when>
														</c:choose> 
												  		<%} %>
											  </c:forEach>
											   <%for (int i = 3; i >= fileCount; i--) {%>
													<td ></td>
												<%} %>
												</tr>
												</table>
											</div>
											<%} %>
								<%-- <%} %> --%>
							<!-- end display images -->	    			     		
					  		
				</c:when>
				<c:otherwise>
					<div class="incomming_thickborder_line"></div>				
					<div class="incomming_incyyte_tab">
						<table width="100%" border="0" cellspacing="0" cellpadding="0">
					  		<tr>
					    		<td width="12%" valign="top" >
					    			<c:if test="${myIncoming.incyyte.createdBy != 'ANONYMOUS'}">
					    			<c:choose>
								       <c:when test="${not empty myIncoming.incyyte.senderProfilePic}">
								       <img src="<%=cyyteChart.getIncyyte().getSenderProfilePic() %>" width="46" height="46" alt="User Photo" class="photoframe"/>
								       </c:when>
								       <c:otherwise>
								       <img src="ui/images/default_avatar.png" width="46" height="46" alt="User Photo" class="photoframe" />
								       </c:otherwise>
								       </c:choose> </c:if>	
								       <c:if test="${myIncoming.incyyte.createdBy == 'ANONYMOUS'}">
								       <img src="ui/images/annonymous_icon.png" width="46" height="46" alt="User Photo" class="photoframe" />
								       </c:if> 
					    		</td>
					    		<td width="58%"  >
					    			<h3><p class="questionText">${myIncoming.question}</p></h3>
					      			<p>Sender: <strong>${myIncoming.incyyte.createdBy}</strong></p>
					      			<p>Created:  <strong>${myIncoming.incyyte.createdDatePeriod}</strong></p>
					      			<p>Closure:${myIncoming.incyyte.closureDate}</p>
					      			<c:if test="${not empty myIncoming.incyyte.group}">
										<p>Group: ${myIncoming.incyyte.group.groupName}</p>	
									</c:if>				      			    		
					      		</td>			      		
					      		
			      				<td width="30%" >
					    			<a href="" title="view incyyte" id="view_incyyte" class="button_gray" style="width:171px; "> 
					    				<span class="title_gray title_vote_now_ie8">VOTE NOW </span>
					    			</a>
					    			
					    			<!-- Options Drop Box Starts Here -->
								   <%--  <div id="dropBoxContainer${status.index}" class="dropBoxContainer"> 
								    	<a href="#" id="dropBoxButton${status.index}" class="dropBoxButton">
								    		<span>No Thanks</span><em></em>
								    	</a>
										<div style="clear:both"></div>
										<div id="dropBox${status.index}" class="dropBox">
									  		<div id="dropBoxContent${status.index}" class="dropBoxContent">
											    <ul>
											      <li><a href="#" onclick="delete_incyyte('${myIncoming.incyyte.id}');" id="delete_incyyte" >Delete this InCyyte <span>The inCyyte will be deleted</span></a></li>
											    </ul>
									  		</div>
										</div>
									</div> --%>
								    <!-- Options Drop Box Ends Here -->
					    							  
					      		</td>		      				      		
					  		</tr>
						</table>
					</div>
					<div class="incomming_incyyte_detail">
						<table width="100%" border="0" cellspacing="0" cellpadding="0">
					  		<tr>
					    		<td width="60%" valign="top" >
					    			<table width="100%" border="0" cellspacing="0" cellpadding="0">
										<tr>
						  					<td width="20%" valign="top" >
						  						<c:if test="${myIncoming.incyyte.createdBy != 'ANONYMOUS'}">
					    			<c:choose>
								       <c:when test="${not empty myIncoming.incyyte.senderProfilePic}">
								       <img src="<%=cyyteChart.getIncyyte().getSenderProfilePic() %>" width="46" height="46" alt="User Photo" class="photoframe"/>
								       </c:when>
								       <c:otherwise>
								       <img src="ui/images/default_avatar.png" width="46" height="46" alt="User Photo" class="photoframe" />
								       </c:otherwise>
								       </c:choose> </c:if>	
								       <c:if test="${myIncoming.incyyte.createdBy == 'ANONYMOUS'}">
								       <img src="ui/images/annonymous_icon.png" width="46" height="46" alt="User Photo" class="photoframe" />
								       </c:if>
						  					</td>
						  					<td width="80%">
								    			<h3><p class="questionText" >${myIncoming.question}</p></h3>
								      			<p>Sender: <strong>${myIncoming.incyyte.createdBy}</strong></p>
								      			<p>Created:  <strong>${myIncoming.incyyte.createdDatePeriod}</strong></p>
								      			<p>Closure:${myIncoming.incyyte.closureDate}</p>
								      			<c:if test="${not empty myIncoming.incyyte.group}">
													<p>Group: ${myIncoming.incyyte.group.groupName}</p>	
												</c:if>													      			    		
								      		</td>	
										</tr>
					      			</table>
					      			<br>
					      			<c:choose>
					      			<c:when test="${not empty myIncoming.incyyte.uploadLocation && not empty myIncoming.incyyte.upload_name}">
					      			<c:set var="url"  value="${myIncoming.incyyte.uploadLocation}"/>
					      					<%		String name = cyyteChart.getIncyyte().getUpload_name();
							      					String extension = "";
							      					int i = name.lastIndexOf('.');
							      					if (i > 0) {
							      					    extension = StringUtils.lowerCase(name.substring(i+1));
							      					}
							      					String videos[] = {"flv","mp4","mpg","swf","wmv"};
							      					String images[] = {"gif","png","jpg","jpeg","bmp"};
							      					String docs[] = {"wpd","wps","xml","xlr","pdf"};
													String gooleDocs[] = {"doc","docx","log","rtf","txt","csv","pps","ppt","xls","xlsx"};
							      					List <String> extVideos = Arrays.asList(videos); 
							      					List <String> extImages = Arrays.asList(images); 
							      					List <String> extDocs = Arrays.asList(docs); 
									      			List <String> extGoogleDocs = Arrays.asList(gooleDocs);
									      			String docUrl = "https://docs.google.com/viewer?url=";
													String url = (String)pageContext.getAttribute("url");
													String viewUrl = docUrl + url; %>
					      					<%if(extVideos.contains(extension)) {%>
							      					<div class="media_thumbs" >
														<ul id="view_videos">
										  					<li> 
										  					<!-- this is for flow player for questions -->
															<a style="cursor:pointer"   style="display:block;width:425px;height:300px;"></a>
															<span><img onClick="javascript:playVideo('${myIncoming.incyyte.id}','player${myIncoming.incyyte.id}', '${myIncoming.incyyte.uploadLocation}')" src="ui/images/video_thumb.png" /></span>
															<div style="display:none;">
													        <div id="emailList${myIncoming.incyyte.id}" class="emailList ">
															<a style="cursor:pointer" id="player${myIncoming.incyyte.id}" ></a>
													        </div></div>
										    				</li>
														</ul>
									      			</div>
									      			
												  	<div style="display:none;">
												  		<div id="videos_modal">
												    		<object classid="clsid:D27CDB6E-AE6D-11cf-96B8-444553540000" codebase="http://download.macromedia.com/pub/shockwave/cabs/flash/swflash.cab#version=7,0,0,0" width="480" height="360" id="FLVPlayer">
																<param name="movie" value="FLVPlayer_Progressive.swf" />
															  	<param name="salign" value="lt" />
															  	<param name="quality" value="high" />
															  	<param name="scale" value="noscale" />
															  	<param name="FlashVars" value="&MM_ComponentVersion=1&skinName=Clear_Skin_2&streamName=${myIncoming.incyyte.uploadLocation}&autoPlay=true&autoRewind=false" />
															  	<embed src="FLVPlayer_Progressive.swf" flashvars="&MM_ComponentVersion=1&skinName=Clear_Skin_2&streamName=${myIncoming.incyyte.uploadLocation}&autoPlay=true&autoRewind=false" quality="high" scale="noscale" width="480" height="360" name="FLVPlayer" salign="LT" type="application/x-shockwave-flash" pluginspage="http://www.macromedia.com/go/getflashplayer" />
															</object>
												  		</div>
												  	</div>
							      					<%}else if(extImages.contains(extension)){ %>		      				
							      					<div class="media_thumbs" >
														<ul id="view_photos">
															<li> 
																	<a style="cursor:pointer;" href="${myIncoming.incyyte.uploadLocation}" class="fancybox-popup fancybox.iframe">
																	<img src="${myIncoming.incyyte.uploadLocation}" width="230" height="171" alt="tour" style="margin:0px 0px 0px 80px"  class="photoframe"   /></a>
															</li>
													  </ul>
								      				</div>
							      					
							      				<%}else if(extDocs.contains(extension)){ %>		
								      				<div class="media_thumbs" >
														<ul id="view_document">
															<li> 
																<a style="cursor:pointer" href="" onClick="window.open('${myIncoming.incyyte.uploadLocation}','MyWindow'); return false;" target="_blank" class="group">
																	<img src="ui/images/view_docs_thumb.png" width="230" height="171" alt="tour" style="margin:0px 0px 0px 80px"  class="photoframe"  />
																</a> 
															</li>
														</ul>
								      				</div>
								      			<%}else if(extGoogleDocs.contains(extension)){ %>		
								      				<div class="media_thumbs" >
														<ul id="view_document">
															<li> 
															<a style="cursor:pointer" href="" onClick="window.open('<%=viewUrl%>','MyWindow'); return false;" target="_blank" class="group">
																	<img src="ui/images/view_docs_thumb.png" width="230" height="171" alt="tour" style="margin:0px 0px 0px 80px"  class="photoframe"  />
																</a> 
															</li>
														</ul>
								      				</div>
							      				<%} else{%>		
							      				<div class="media_thumbs" >
												<ul id="view_photos">
							  						<li> 
							  							<img src="ui/images/photo1.png" width="230" height="171" alt="tour" style="margin:0px 0px 0px 80px" class="photoframe"/>
							  						</li>
												</ul>
						      				</div>	
						      				<%} %>
					      				</c:when>
					      				<c:otherwise>
					      					<div class="media_thumbs" >
												<ul id="view_photos">
							  						<li> 
							  							<img src="ui/images/photo1.png" width="230" height="171" alt="tour" style="margin:0px 0px 0px 80px" class="photoframe"/>
							  						</li>
												</ul>
						      				</div>		      					
					      				</c:otherwise>
									 </c:choose>
								</td>
					    		<td width="40%" valign="top" class="grid_6b">
					    			<form:form id="voteForm${status.index}" commandName="voteForm"  action="voteincome.cyt" method="post">
					    			
					    				<input type="hidden" name="incyyteId" id="incyyteId" value="${myIncoming.incyyte.id}"/>
					    				<input type="hidden" name="userId" id="userId" value="${userId}"/>    				
					    				
						    			<table width="100%" border="0" cellspacing="0" cellpadding="0">
											<tr>
												<td class="view_text">View</td>
											  	<td>&nbsp;</td>
											</tr>
											<c:forEach var="ans" items="${myIncoming.incyyte.answers}" varStatus="ansStatus">
												<tr>
												  	<td align="top">
												  		<c:choose>
															<c:when test="${not empty ans.uploadCDN_url}">
															<c:set var="extAnswer"  value="${ans.uploadExt}"/>
															<c:set var="ansUrl"  value="${ans.uploadCDN_url}"/>
															<%  String extAnswer = (String)pageContext.getAttribute("extAnswer");
															String ansVideos[] = {".flv",".mp4",".mpg",".swf",".wmv"};
									      					String ansImages[] = {".gif",".png",".jpg",".jpeg",".bmp"};
									      					String ansDocs[] = {".wpd",".wps",".xml",".xlr",".pdf"};
									      					String ansGooleDocs[] = {".doc",".docx",".log",".rtf",".txt",".csv",".pps",".ppt",".xls",".xlsx"};
									      					List <String> extAnsVideos = Arrays.asList(ansVideos); 
									      					List <String> extAnsImages = Arrays.asList(ansImages); 
									      					List <String> extAnsDocs = Arrays.asList(ansDocs);
									      					List <String> extAnsGoogleDocs = Arrays.asList(ansGooleDocs);
									      					String docUrl = "https://docs.google.com/viewer?url=";
															String ansUrl = (String)pageContext.getAttribute("ansUrl");
															String viewUrl = docUrl + ansUrl; %>	
															<%if (StringUtils.isNotEmpty(extAnswer)) {%>
									      					<div class="thumbnail view_icon">
									      					<%if(extAnsVideos.contains(StringUtils.lowerCase(extAnswer))) { %>
															<a style="cursor:pointer" onClick="javascript:playVideo('${ans.id}','player${ans.id}', '${ans.uploadCDN_url}')"  class="thumbnail view_icon"></a>
															<span><img src="ui/images/video_thumb.png" /></span>
															 <div style="display:none;">
															 <div id="emailList${ans.id}" class="emailList ">
															<a style="cursor:pointer" id="player${ans.id}" ></a>
															</div>
															</div> 
															<% }else if(extAnsImages.contains(StringUtils.lowerCase(extAnswer))){ %>	
															<a style="cursor:pointer" href="${ans.uploadCDN_url}" class="thumbnail view_icon fancybox-popup fancybox.iframe"></a>
															<span><img src="${ans.uploadCDN_url}" /></span>
															<%}else if(extAnsGoogleDocs.contains(StringUtils.lowerCase(extAnswer))){ %>	
															<a style="cursor:pointer" href="" onClick="window.open('<%=viewUrl %>','MyWindow'); return false;" target="_blank" class="thumbnail view_icon"></a>
															<span><img src="ui/images/view_docs_thumb.png" /></span>
															<%} else if(extAnsDocs.contains(StringUtils.lowerCase(extAnswer))){ %>
															<a style="cursor:pointer" href="" onClick="window.open('${ans.uploadCDN_url}','MyWindow'); return false;" target="_blank" class="thumbnail view_icon"></a>
															<span><img src="ui/images/view_docs_thumb.png" /></span>
															<%} %>
															</div>
															<%} %>
															</c:when>
															<c:otherwise>
																&nbsp;
															</c:otherwise>
														</c:choose> 
												  	</td>
												  	<td><label for="radio-${status.index}${ansStatus.index}" tabindex="${status.index}">${ans.answerOption}</label>		                            		
					                            		<form:radiobutton path="selectedAnswer" id="radio-${status.index}${ansStatus.index}" value="${ans.id}" />
					                            	</td>
												</tr>
											</c:forEach>
											<tr>
											  	<td>&nbsp;</td>
											  	<td><a href="#" onclick="submitVoteForm('voteForm${status.index}');" title="READY? VOTE!" id="ready_vote" class="ready_vote_bot" style="width:171px; margin-top:10px;">
											  		<span class="title_red title_red5_ie8">READY? VOTE!</span></a>
											  	</td>
											</tr>
										</table>
									</form:form>
								</td>
							</tr>
						</table>
				  	</div>
				</c:otherwise>
			</c:choose>
		 	
	  	</c:forEach>
	  	<script type="text/javascript" src="ui/js/googleanalytics.js"></script>
</body>
</html>