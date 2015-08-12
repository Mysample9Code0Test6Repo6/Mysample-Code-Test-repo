<%@ include file="/WEB-INF/jsp/common/include.jsp" %>
<%@include file="/WEB-INF/jsp/login_model/singlePageModelLogin.jsp"%> 

<%@ page import="com.incyyte.app.web.model.IncyyteModel" %>
<%@page import="com.incyyte.app.web.model.AnswerModel"%>
<%@page import="com.incyyte.app.domain.InCyyte"%>

<%@page import="com.incyyte.app.web.SessionKeys"%>
<%@page import="com.incyyte.app.domain.User"%>
<%@page import="com.incyyte.app.domain.Answer"%>
<%@page import="com.incyyte.app.domain.InCyyteChart"%>
<%@page import="com.incyyte.app.domain.CyyteResponse"%>
<%@page import="com.incyyte.app.service.util.Constants"%>
<%@page import="com.incyyte.app.service.util.Utility"%>
<%@page import="java.util.*"%>

<%@ page import="org.apache.commons.lang.StringUtils" %>
<%-- <jsp:include page="../include/report_abuse.jsp" /> --%>

<link rel="stylesheet" href="/ui/css/newReset.css">
<link rel="stylesheet" href="/ui/css/layout.css">
<link rel="stylesheet" href="/ui/css/buttons.css"  media="screen">
<link rel="stylesheet" href="/ui/css/style_login.css">
<link rel="stylesheet" href="/ui/css/accordionmenu.css" type="text/css">
<link rel="stylesheet" href="/ui/css/icons_sprites.css">
<link rel="stylesheet" href="/fonts/fonts_stylesheet.css">
<link rel="stylesheet" href="/ui/css/ratingbar.css" type="text/css"/>
<link rel="stylesheet" href="/ui/modal/colorbox.css">
<link rel="stylesheet" href="/ui/css/form_elements.css">
<link rel="stylesheet" href="/ui/css/style.css">

<link href="/ui/css/newStyle.css" media="screen" rel="stylesheet" type="text/css" />
<script>
    if (/*@cc_on!@*/false) {
        var headHTML = document.getElementsByTagName('head')[0].innerHTML;
        headHTML    += '<link type="text/css" rel="stylesheet" href="/ui/css/ie10.css">';
        document.getElementsByTagName('head')[0].innerHTML = headHTML;
    }
</script>

    <!-- Theme CSS -->
    <link rel="stylesheet" href="css/newwelcome/settings.css">
    <link rel="stylesheet" href="css/newwelcome/theme-elements.css">
    <link rel="stylesheet" href="css/newwelcome/teal.css">
    <link rel="stylesheet" href="css/newwelcome/animate.css">

    <link rel="stylesheet" href="css/newwelcome/styles.css">

<script src="/ui/js/jquery-1.8.1.js"></script>

<script src="/ui/js/login.js"></script>
<script src="/ui/js/jquery.color.js"></script>
<script src="/ui/js/search_script.js"></script>
<script type="text/javascript" src="/ui/js/jquery.form.js"></script>
<!-- copied from sent page -->
<script type="text/javascript" src="/ui/js/jquery-ui.js"></script>
<script type="text/javascript" src="ui/js/validate/jquery.validate.js" ></script>
<script src="/ui/js/jquery.selectbox-0.2.js"></script>
<script type="text/javascript" src="ui/js/jquery.ratingbar.js"></script>
<!-- ends here -->

<script src="/ui/modal/colorbox/jquery.colorbox.js"></script>
<script src="/ui/js/jquery_dashboard_rating.js" type="text/javascript"></script>
<script src="/ui/js/star_rating.js" type="text/javascript"></script>
<script src="/ui/js/customInput.jquery.js" type="text/javascript"></script>

<script src="/ui/js/dashboard/dash_income.js" type="text/javascript"></script>

<script src="/ui/js/jquery.limit.js"></script>
<script src="/ui/js/zoomphoto.js"></script>
<script src="/ui/js/prettyCheckboxes.js" type="text/javascript" charset="utf-8"></script>
<script type="text/javascript" src="ui/js/autoresize.jquery.js"></script>
<script src="/ui/js/accordian/jquery.ui.core.js"></script>
<script src="/ui/js/accordian/jquery.ui.widget.js"></script>
<script src="/ui/js/accordian/jquery.ui.accordion.js"></script>
<script src="/ui/js/jquery.ui.datepicker.js"></script>

<script type="text/javascript" src="ui/js/jquery-ui.js"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath}/ui/Fancy-box/jquery.fancybox.css" type="text/css" media="screen" />
<script type="text/javascript" src="js/flowplayer/flowplayer-3.2.12.min.js"></script>
<script type="text/javascript" src="ui/fancyplayer_code/js/flowplayer-3.1.1.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/ui/Fancy-box/jquery.fancybox.pack.js"></script>
<script type="text/javascript" src="ui/fancyplayer_code/js/jquery.easing.1.3.js"></script>
<script type="text/javascript" src="ui/fancyplayer_code/js/fancyplayer.js"></script>

<!--- Questions Tabs starts------->
<script type="text/javascript" src="ui/js/ddaccordion.js"></script>
<script type="text/javascript">
function getSinglePoll(incyyteCode) {
	window.location = "viewPoll.cyt?code="+incyyteCode;
	 event.preventDefault();
};

function submitVote(formId, incyyteId, ansId){
	//Assigning selected answer to hidden var
	   $("#selectedAnsVal").val(ansId);

	  $('#questionId').val(incyyteId);
	 $('#incyyteId' + incyyteId).val(incyyteId);
	    $("#ans_sel_error_" + incyyteId).css("display", "none");
      
      if ($("#selectedAnsVal").val() != null){
    	  //If we use only $.post method, We are able to get into controller but data in VoteModel object is null.
    	   $.post("/singlePagevoteloggedinUser.cyt?incyyteId="+incyyteId+"&ansId="+ansId,
    			  {
    		  
    			  },
    			  function(data)
    			  {
    				  if(data.indexOf("notLogin") != -1) {
    					  loginProcessInSinglePollPage('filteredPoll',incyyteId,ansId);
    	                  }else if(data.indexOf("failure") != -1){
    	            			parent.$.fn.colorbox({'href':'form#errorPopUp', onClosed:closePopUp, 'open':true, 'inline':true});	
    	                    }  else {
    	                    	$('#errorMsg').html(data);
    	        				parent.$.fn.colorbox({'href':'form#msgPopUp',  onClosed:closePopUp, 'open':true, 'inline':true});	
    	                    }
    			  });
           }
     else {
          $("#ans_sel_error_" + incyyteId).text("Please select an answer");
          $("#ans_sel_error_" + incyyteId).show();
      }
  } 
  
  
  
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

</script>

 


<body>
<div id="header">	
<% User userLocal = (User) session.getAttribute("user"); %>
<% if (userLocal != null) {%>
<jsp:include page="../common/includes/header.jsp"/>

<!-- Bootstrap CSS -->
<link rel="stylesheet" href="/css/newwelcome/bootstrap-header.css" />

<!-- AWESOME and ICOMOON fonts -->
<link rel="stylesheet" href="/css/newwelcome/font-awesome.css">
<link rel="stylesheet" href="/css/newwelcome/style.css">


<!-- Theme CSS -->
<link rel="stylesheet" href="./css/newwelcome/settings.css">
<link rel="stylesheet" href="./css/newwelcome/theme-header.css">
<link rel="stylesheet" href="./css/newwelcome/theme-elements.css">
<link rel="stylesheet" href="./css/newwelcome/teal.css">
<link rel="stylesheet" href="./css/newwelcome/animate.css">

<link rel="stylesheet" href="./css/newwelcome/styles.css">
<div class="navbar">
</div>

<%} else { %>
 <jsp:include page="/WEB-INF/jsp/newHeader.jsp" />
<%} %>
 </div>
 
 <% 	IncyyteModel model = (IncyyteModel) session.getAttribute("inCyyteForm"); 	
	InCyyteChart chart = (InCyyteChart) session.getAttribute("incyyte_chart"); 	
	 InCyyte cyyte = (InCyyte) session.getAttribute("incyyte");  
	 %>
	         <div>
	          	<p style="margin-top:200px; text-align:center;font: 50px/1em 'bariol_thinregular', 'ie8_bariol_thin';">
				<span style="font: 1em/1em 'bariol_boldbold', 'ie8_bariol_bold';  color: #1B303F;">${category} </span>Poll</p>
			</div>	
	            <div>
	          <%   String code = cyyte.getIncyyteCode(); %>
	            <p style=" padding-left: 500px; width= 30% ; font: 20px 'bariol_regularregular','ie8_bariol_regular';"  class="font_12px_strong">poll.incyyte.com/viewpoll.cyt?Code=<%=code%> </p>
	            
	            </div>
	            
	             <div class="hline"></div>
	                <div style="padding-left: 600px; font:  20px 'bariol_regularregular','ie8_bariol_regular';">
	                      <tr>
							<td style="width: 90px;"> <a  class="report_abuse"></a>Share on :</td> 
							<td> <a style=" padding-left: 10px;" class="facebook"><img src="${pageContext.request.contextPath}/ui/images/fb_icon.png" /></a> </td>
							<td> <a style=" padding-left: 10px;" class="twitter"><img src="${pageContext.request.contextPath}/ui/images/twitter_icon.png" /></a> </td>
							<td> <a style=" padding-left: 10px;" class="incyyte_contacts"><img src="${pageContext.request.contextPath}/ui/images/incyyte_contacts_icon.png" /></a> </td>
						   <%--  <td ><a href="#" class="report_abuse" onclick="submitReportAbuse(${model.incyyte.id}, 'filteredPolls.cyt');">Report Abuse :<img src="${pageContext.request.contextPath}/ui/images/reportabuse_flag.png" /></a></td> --%>
							</tr>
						</div>
	               
	                 <div class="hline"></div>
	               
   <!-- Review Poll starts--->
   <input type="hidden" id="selectedAnsVal" value=""/>
 <div id="modal_review_poll" style="  margin-top: 100px;  margin-left: 300px;  margin-bottom: 150px;">
	 <script type="text/javascript">
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
		    function playYoutubeVideoQuesPie(id, videoId){
		        $("#iFrameYouTubePie_"+id).attr("src", videoId);
		        parent.$.fn.colorbox({href:'div#emailListYouTubePie_'+id, open:true, inline:true});
		    }
		    
		    function playVideo(id,playerId,playUrl) {
		    	
		    	parent.$.fn.colorbox({href:'div#emailList'+id, open:true, inline:true});
		    	  $f(playerId, "js/flowplayer/flowplayer-3.2.16.swf", {
		    		    clip: {
		    		        url: playUrl,
		    		        autoPlay: true,
		    		        autoBuffering: true
		                    },
		    		    plugins: {
		    		        controls:{
		    		        	url: 'js/flowplayer/flowplayer.controls-3.2.15.swf',
		    		        	top: -20,
		    		            left: 0,
		    		            opacity: 0.95,
		    		            timeColor: '#980118',
		    		            all: true,
		    		            play: false,
		    		            scrubber: true,
		                        playing:true,
		    	         tooltips: {
		    		             buttons: false,
		    		             fullscreen: 'Enter fullscreen mode'
		    		            }
		    		        }
		    		    },
		    		    onLoad: function(){
		    		    }
		    		});
		    }
		    </script>
	<form>
		<div id="modal_review_poll_inner">
		   	<table width="100%" border="0" cellspacing="0" cellpadding="0">
		  <tr>
					<%if(model.getCreatedbyImageLink() !=null && !model.getCreatedBy().equals("ANONYMOUS")){%>
							<td width="11%" style="padding: 0px; padding-top: 40px;" valign="top" >
								<img src="<%=model.getCreatedbyImageLink()%>"  width="46" height="46" alt="User Photo" class="photoframe" style="width: 80%;  height: 60px;">
							</td>
						<%}else if(model.getCreatedbyImageLink() ==null && !model.getCreatedBy().equals("ANONYMOUS") ){ %>
							<td width="11%"  style="padding: 0px; padding-top: 40px;"  valign="top" ><img src="ui/images/default_avatar.png" width="46" height="46" alt="User Photo" class="photoframe" style="width: 80%;  height: 60px;"></td>
						<%}else if(model.getCreatedBy() !=null && model.getCreatedBy().equals("ANONYMOUS")){%>
	           			<td width="11%" style="padding: 0px; padding-top: 40px;"  valign="top" ><img src="ui/images/annonymous_icon.png" width="46" height="46" alt="User Photo" class="photoframe" style="width: 80%;  height: 60px;"></td>
					<%}%>

	               	<td width="89%" style="padding: 0px; padding-bottom: 20px;">
	               		<table width="100%" border="0" cellspacing="0" cellpadding="0" class="font_12px"  style="margin-left: 300px;">
	               			<tr>
						<%
						if(StringUtils.isNotBlank(model.getCreatedBy())) { %>
							<td width="20%" style="font: 15px 'bariol_boldbold', 'ie8_bariol_bold';"><b>Creator:</b></td>
							<td width="30%" class="font_12px_strong" style="font: 15px 'bariol_regularregular','ie8_bariol_regular';"><%=model.getCreatedBy()%></td>
						<%} else{%>
							<td width="20%">&nbsp;</td>
							<td width="30%">&nbsp;</td>
						<%}%>
						<td width="20%">&nbsp;</td>
						<td width="30%">&nbsp;</td>
	                   		</tr>
	                   		<tr>
		                   		<td style="font: 15px 'bariol_boldbold', 'ie8_bariol_bold';"><b>Created:</b></td>
		                     	<td><span style="font: 15px 'bariol_regularregular','ie8_bariol_regular';"> <%=model.getCreatedDate()%></span></td>
		                   	</tr>
		                   	<tr>
		                   	<%if(model.getGrpName() != null) {%>
						<td><b>Group:</b></td>
						<td><%=model.getGrpName()%></td>
		                     	<%} %>
		                     	<td style="font: 15px 'bariol_boldbold', 'ie8_bariol_bold';"><b>Closure:</b> </td>
		                     	<td style="font: 15px 'bariol_regularregular','ie8_bariol_regular';"><%=model.getClosureDate()%></td>
		                   	</tr>
		                 </table>
		        	</td>
	          	</tr>
		   	</table>
		    <table width="100%" border="0" cellspacing="0" cellpadding="0" >
		    	<tr>
		        	<td >&nbsp;</td>
		       	</tr>
		   		<tr>
		   			<td style=""; valign="top"><h3><%=model.getIncyyte()%></h3></td> 
	           		<td valign="top">
	           			<table width="100%" border="0" cellspacing="0" cellpadding="0">
	                   		<tr>
	                   		<% if(cyyte.getUploadLocation() != null && cyyte.getUpload_name() != null) { %>
	                   		
					      					<%		String name = cyyte.getCdnFileName();
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
													String url = cyyte.getUploadLocation();
													String viewUrl = docUrl + url; %>
					      					<%if(extVideos.contains(extension)) {%>
							      					<div class="media_thumbs" >
														<ul id="view_videos">
										  					<li> 
										  					<!-- this is for flow player for questions -->
															<a style="cursor:pointer"   style="display:block;width:425px;height:300px;"></a>
															<span><img onClick="javascript:playVideo('${cyyte.id}','player${cyyte.id}', '${cyyte.uploadLocation}')" src="ui/images/video_thumb.png" width="230" height="171" alt="tour" style="margin:0px 0px 0px 80px"   /></span>
															<div style="display:none;">
													        <div id="emailList${cyyte.id}" class="emailList ">
															<a style="cursor:pointer" id="player${cyyte.id}" ></a>
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
															  	<param name="FlashVars" value="&MM_ComponentVersion=1&skinName=Clear_Skin_2&streamName=${cyyte.uploadLocation}&autoPlay=true&autoRewind=false" />
															  	<embed src="FLVPlayer_Progressive.swf" flashvars="&MM_ComponentVersion=1&skinName=Clear_Skin_2&streamName=${cyyte.uploadLocation}&autoPlay=true&autoRewind=false" quality="high" scale="noscale" width="480" height="360" name="FLVPlayer" salign="LT" type="application/x-shockwave-flash" pluginspage="http://www.macromedia.com/go/getflashplayer" />
															</object>
												  		</div>
												  	</div>
							      					<%}else if(extImages.contains(extension)){ %>		      				
							      					<div class="media_thumbs" >
														<ul id="view_photos">
															<li> 
																	<a style="cursor:pointer;" href="${cyyte.uploadLocation}" class="fancybox-popup">
																	<img src="${cyyte.uploadLocation}" width="230" height="171" alt="tour" style="margin:0px 0px 0px 80px"  class="photoframe"   /></a>
															</li>
													  </ul>
								      				</div>
							      					
							      				<%}else if(extDocs.contains(extension)){ %>		
								      				<div class="media_thumbs" >
														<ul id="view_document">
															<li> 
																<a style="cursor:pointer" href="" onClick="window.open('${cyyte.uploadLocation}','MyWindow'); return false;" target="_blank" class="group">
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
						      				<%} 
	                   						}else if(cyyte.getYoutubeUrl() != null){ 
	                   							String youtubeUrl = cyyte.getYoutubeUrl();%>
							      					<div class="media_thumbs" >
													<ul id="view_videos">
									  					<li> 
									  					<!-- this is for flow player for questions -->
														<a style="cursor:pointer"   style="display:block;width:425px;height:300px;"></a>
														<span><img width="230" height="171" alt="tour" style="margin:0px 0px 0px 80px" onClick="javascript:playYoutubeVideoQues('${cyyte.id}' ,'<%=youtubeUrl%>');" src="ui/images/video_thumb.png" /></span>
														<div style="display:none;">
															<div id="emailListYouTube_${cyyte.id}" class="emailList " >
										   						 <iframe width='390' id="iFrameYouTube_${cyyte.id}"  height='308'  src="ui/images/uploading_big_img.png" frameborder='0' type='text/html'></iframe>
										    			</div>
														</div> 
									    				</li>
													</ul>
								      			</div>
							      					
							      			 <%}else{%>		
							      			 
							      				<div class="media_thumbs" >
												<ul id="view_photos">
							  						<li> 
							  							<img src="ui/images/photo1.png" width="230" height="171" alt="tour" style="margin:0px 0px 0px 80px" class="photoframe"/>
							  						</li>
												</ul>
						      				</div>	
					      				<%} %>
	                   		</tr>
	                 	</table>
	                 </td>
		      	</tr>
	           	<tr>
	               <td valign="top">&nbsp;</td>
	            </tr>
	           	<tr>
	               <td valign="top" class="top_border">&nbsp;</td>
	               <td valign="top" class="top_border">&nbsp;</td>
	          	</tr>
	           	<tr>
	           		
	            		<table width="100%" border="0" cellspacing="0" cellpadding="0" class="review_poll">
		                 <form:form id="voteForm${cyyte.id}" commandName="voteForm"  action="voteincome.cyt" method="post">  
					    			<input type="hidden" name="questionId" id="questionId" value="${cyyte.id}"/>
					    			 <c:if test="${userLocal != null}">
	                                  <input type="hidden" name="memberId" id="memberId" value="<%userLocal.getId();%>"/>
	                                   </c:if>
                                        <form:hidden path="incyyteId" id="incyyteId${cyyte.id}"/> 
                                           <form:hidden path="userId" id="userId" />
                                          
                                         <table width="100%" border="0" cellspacing="0" cellpadding="0">
                                         <% boolean hover = false; %>
										<c:forEach var="ansfile" items="${cyyte.answers}">
										<c:choose>
											<c:when test="${not empty ansfile.uploadCDN_url}">
												<% hover = true; %>
											</c:when>
										</c:choose>
										</c:forEach>
										<% if(hover){ %>
											<tr>
												<td class="view_text" style="font: 25px 'bariol_boldbold', 'ie8_bariol_bold';"><span>Hover</span></td>
											  	<td>&nbsp;</td>
											</tr>
										<%} %>
											<c:forEach var="ans" items="${cyyte.answers}" varStatus="ansStatus">
		              
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
															
															<td width="71%" style="padding-bottom: 20px;">${fn:substring(ans.answerOption, 0, 35)}</td>
			                     		                    <td width="22%" height="46"><a href="#" onclick="submitVote('voteForm${cyyte.id}','${cyyte.id}','${ans.id}');" title="VOTE!" id="" class="button_gray1" style="width:96px;"> <span class="title_gray1">VOTE!</span></a></td>
															
															
															
															<% }else if(extAnsImages.contains(StringUtils.lowerCase(extAnswer))){ %>	
															<a style="cursor:pointer" href="${ans.uploadCDN_url}" class="thumbnail view_icon fancybox-popup "></a>
															<span><img src="${ans.uploadCDN_url}" /></span>
															<td width="71%" style="padding-bottom: 20px;">${fn:substring(ans.answerOption, 0, 35)}</td>
			                     		                    <td width="22%" height="46"><a href="#" onclick="submitVote('voteForm${cyyte.id}','${cyyte.id}','${ans.id}');" title="VOTE!" id="" class="button_gray1" style="width:96px;"> <span class="title_gray1">VOTE!</span></a></td>
															
															
															
															<%}else if(extAnsGoogleDocs.contains(StringUtils.lowerCase(extAnswer))){ %>	
															<a style="cursor:pointer" href="" onClick="window.open('<%=viewUrl %>','MyWindow'); return false;" target="_blank" class="thumbnail view_icon"></a>
															<span><img src="ui/images/view_docs_thumb.png" /></span>
															<td width="71%" style="padding-bottom: 20px;">${fn:substring(ans.answerOption, 0, 35)}</td>
			                     		                    <td width="22%" height="46"><a href="#" onclick="submitVote('voteForm${cyyte.id}','${cyyte.id}','${ans.id}');"title="VOTE!" id="" class="button_gray1" style="width:96px;"> <span class="title_gray1">VOTE!</span></a></td>
															
															
															<%} else if(extAnsDocs.contains(StringUtils.lowerCase(extAnswer))){ %>
															<a style="cursor:pointer" href="" onClick="window.open('${ans.uploadCDN_url}','MyWindow'); return false;" target="_blank" class="thumbnail view_icon"></a>
															<span><img src="ui/images/view_docs_thumb.png" /></span>
															<td width="71%" style="padding-bottom: 20px;">${fn:substring(ans.answerOption, 0, 35)}</td>
			                     		                    <td width="22%" height="46"><a href="#" onclick="submitVote('voteForm${cyyte.id}','${cyyte.id}','${ans.id}');" title="VOTE!" id="" class="button_gray1" style="width:96px;"> <span class="title_gray1">VOTE!</span></a></td>
														
															
															<%} %>
															
															</div>
															<%} %>
															</c:when>
																
																<c:when test="${not empty ans.youtubeURLAnswer}">
                                                              <div class="thumbnail view_icon">
                                                              <a style="cursor:pointer" onClick="javascript:playYoutubeVideoQues('${ans.id}_answer','${ans.youtubeURLAnswer}')"  class="thumbnail view_icon"></a>
                                                                  <span><img src="${pageContext.request.contextPath}/ui/images/video_thumb.png" /></span>
                                                                  <div style="display:none;">
																<div id="emailListYouTube_${ans.id}_answer" class="emailList " >
											   						 <iframe width='390' id="iFrameYouTube_${ans.id}_answer"  height='308'  src="ui/images/uploading_big_img.png" frameborder='0' type='text/html'></iframe>
											    			</div>
															</div>
                                                            </div>
                                                            <td width="71%" style="padding-bottom: 20px;">${fn:substring(ans.answerOption, 0, 35)}</td>
			                     		                    <td width="22%" height="46"><a href="#" onclick="submitVote('voteForm${cyyte.id}','${cyyte.id}','${ans.id}');" title="VOTE!" id="" class="button_gray1" style="width:96px;"> <span class="title_gray1">VOTE!</span></a></td>
								                            
					      									</c:when>
															<c:otherwise>
																&nbsp;
																 <td width="71%" style="padding-bottom: 20px;">${fn:substring(ans.answerOption, 0, 35)}</td>
			                     		                    <td width="22%" height="46"><a href="#" onclick="submitVote('voteForm${cyyte.id}','${cyyte.id}','${ans.id}');" title="VOTE!" id="" class="button_gray1" style="width:96px;"> <span class="title_gray1">VOTE!</span></a></td>
															
															</c:otherwise>
																													
															
														</c:choose> 
														
														 
 											</tr>
											</c:forEach>
										</table>
									</form:form>
	                 	</table>
	             </tr>
		 	</table>
		</div>
	</form>
 </div>   	
    <!-- Review Poll ends--->
  <!-- include footer -->
  	<div>
  	<%int i = 0;%>
					
                      <c:forEach  var="getPollsByCategory" items="${getPollsByCategory}" >
                 	 	 <c:if test="${getPollsByCategory.incyyte.id ne cyyte.id}">
                 	 	  <%if (i<=2) {
                      %>
                 	 	 
                 	 	 
                 	 	 <%if (i%4 == 0) {
                 	 		 
                      %>
                            <hr>       
                          
				<div class="container-out">
				<div ">
	          	<p style=" text-align:center;font: 50px/1em 'bariol_thinregular', 'ie8_bariol_thin';">
				<span style=" font: 1em/1em 'bariol_boldbold', 'ie8_bariol_bold';    color: #1B303F; padding-right: 230px; padding-right: 490px;font-size: 25px;">More Polls in this category :</span></p>
			</div>	
					<div class="carousel-wrap animate fadeInLeft">
						<ul class="carousel-nav">
						</ul><!-- .carousel-nav -->
					  <div class="carousel" data-visible="3" style="margin-left: 300px ;"> 
					   <%}%>
            				<article class="singlepost post-latest post-type-video" style="width: 236px;">
								<div class="post-heading">
									<div class="thumbnail">
										<div class="slidher">
								    <c:choose>
					      			<c:when test="${not empty getPollsByCategory.incyyte.uploadLocation && not empty getPollsByCategory.incyyte.upload_name}">
					      			<c:set var="url"  value="${getPollsByCategory.incyyte.uploadLocation}"/>
					      					<c:set var="uploadName"  value="${getPollsByCategory.incyyte.upload_name}"/>
					      					<%		String name = (String)pageContext.getAttribute("uploadName");
							      					String extension = "";
							      					int j = name.lastIndexOf('.');
							      					if (j > 0) {
							      					    extension = StringUtils.lowerCase(name.substring(j+1));
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
							      					<img  src="/ui/images/video_thumb.png"  height="168" width="478" alt="Image" title="Image" />
							      					
						  					<%}else if(extImages.contains(extension)){ %>		      				
							      				<a href="javascript:getSinglePoll('${getPollsByCategory.incyyte.incyyteCode}');">	<img  src="${getPollsByCategory.incyyte.uploadLocation}"  height="168" width="478" alt="Image" title="Image" /> </a>
							      					
							      			<%}else if(extDocs.contains(extension) || extGoogleDocs.contains(extension)){ %>		
								      					<img  src="/ui/images/view_docs_thumb.png"  height="168" width="478" alt="Image" title="Image" />
						      				<%}%>
					      				</c:when>
					      				<c:when test="${ empty getPollsByCategory.incyyte.uploadLocation}">
					      					<img  src="/ui/images/video_thumb.png"  height="168" width="478" alt="Image" title="Image" />
					      				</c:when>
									 </c:choose>
										</div>
									</div>

								</div>
								<div class="post-content">
									<div class="title">
									<h4><a href="javascript:getSinglePoll('${getPollsByCategory.incyyte.incyyteCode}');">${getPollsByCategory.incyyte.incyyte}</a><br><br><!-- <img  src="ui/images/vote_button.png"> --></h4>
									  </div>
   								   	  </div>
						      	      </article>
					<%
					i++;
					if (i%3 == 0) {
                      %>
                       </div><!-- .carousel -->
					</div><!-- .carousel-wrap -->
				</div><!-- .container-out -->         
                      <%}
                	 
                 	 	  }
                      %>
                      
                       </c:if>  
                    </c:forEach>
         </div>           
  	      <div>
	          	<p style=" text-align:center; font-size: 30px;">Do you have a question?
				<span style="font: 1em/1em 'bariol_boldbold', 'ie8_bariol_bold';  color: #1B303F;">Make its a poll right now.. </span></p>
			</div>
  	<div id="que_inner" >
   
    <form:form  id="inCyyteForm" name="inCyyteForm"  commandName="inCyyteForm" method="post" >
          <form:textarea  placeholder="Type your question here... " onFocus="this.placeholder = ''" onBlur="this.placeholder = 'Type your question here...'" 
	      	onKeyDown="countit(this)" onKeyUp="countit(this)" path="incyyte" maxlength="${questionMaxChar}"  id="myTextarea2" title="Type your question here" rows="1" cols="1" style="overflow: hidden;  height: 48px;"/>
	      <div id="buttonaria_red" class="ask_que"   onClick=submitIncyyte() ></div>
	      <div style="font-size:16px; text-align:left; width:560px; padding-left: 50px; padding-top: 7px; float:left;"><span > You've used 
        <label id="label1"  style="color:red">0</label> of <c:out value="${questionMaxChar}"></c:out> characters.</span></div>
    </form:form>

    </div> 
  	
  	
   <div id="bottom_shadow" style="margin-bottom: 100px;"><div class="bottom_shadow_img"></div></div>
  	
 <% if (userLocal != null) {%>
 <jsp:include page="../common/includes/footer.jsp"/>
<%} else { %>
<jsp:include page="../newFooter.jsp" />
<%} %>
<script type="text/javascript" src="ui/js/googleanalytics.js"></script>			

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
<!-- Chart Script end here -->

<!-- Rating bar and profile starts here -->
<link rel="stylesheet" type="text/css" href="ui/css/ratingbar.css" />
<link rel="stylesheet" href="ui/css/style_login.css">

<script type="text/javascript" src="ui/js/jquery.ratingbar.js"></script>

<script type="text/javascript" charset="utf-8">
    $(document).ready(function () {
        $('#check-box input[type=checkbox]').prettyCheckboxes();

        $('#c1').click(function() {
            var contxtvar=document.getElementById("contectVariable").value;
            window.location = contxtvar+"/dashCheckList.cyt";
        });

    });
    

    function submitIncyyte() {
        $("#inCyyteForm").attr("action", "enter_question.cyt");
        $("#inCyyteForm").submit();
    }
</script>
</body>
</html>
