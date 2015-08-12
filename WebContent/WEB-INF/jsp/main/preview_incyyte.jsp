<%@ include file="/WEB-INF/jsp/common/include.jsp" %>
<%@ page import="com.incyyte.app.web.model.IncyyteModel" %>
<%@page import="com.incyyte.app.web.model.AnswerModel"%>
<%@ page import="com.incyyte.app.domain.User" %>
<%@ page import="com.incyyte.app.web.SessionKeys" %>
<%@page import="java.util.*"%>
<%@ page import="org.apache.commons.lang.StringUtils" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="shortcut icon" href="favicon.ico" />

<link rel="stylesheet" href="ui/css/reset.css">
<link rel="stylesheet" href="ui/css/style.css">
<link rel="stylesheet" href="ui/css/layout.css">
<link rel="stylesheet" href="ui/css/buttons.css"  media="screen">
<link rel="stylesheet" href="ui/css/style_login.css">
<link rel="stylesheet" href="ui/css/accordionmenu.css" type="text/css">
<link rel="stylesheet" href="ui/css/icons_sprites.css">
<link rel="stylesheet" href="fonts/fonts_stylesheet.css">
<link rel="stylesheet" href="ui/modal/colorbox.css">
<link rel="stylesheet" href="ui/css/style_help.css">
<link rel="stylesheet" href="ui/css/prettyCheckboxes.css" type="text/css" media="screen">
<link rel="stylesheet" href="ui/css/themes/base/jquery.ui.all.css">
<link rel="stylesheet" href="ui/css/modal_window.css" type="text/css" media="screen">
<script src="ui/js/jquery.ui.datepicker.js"></script>
<script type="text/javascript" src="js/jquery.form.js"></script>
<script type="text/javascript" src="ui/js/jquery-ui.js"></script>
<script src="ui/js/create_incyyte.js"></script>
<link rel="stylesheet" href="ui/fancyplayer_code/css/jquery.fancybox.css" type="text/css" media="screen" />
<script type="text/javascript" src="ui/fancyplayer_code/js/flowplayer-3.1.1.min.js"></script>
<script type="text/javascript" src="ui/fancyplayer_code/js/jquery.fancybox-1.2.1.pack.js"></script>
<script type="text/javascript" src="ui/fancyplayer_code/js/jquery.easing.1.3.js"></script>
<script type="text/javascript" src="ui/fancyplayer_code/js/fancyplayer.js"></script>
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
</head>
<body>
	
   <!-- Review Poll starts--->
 <div id="modal_review_poll" >
	<% 	IncyyteModel model = (IncyyteModel) session.getAttribute("inCyyteForm"); 	
	 %>
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
		    function abcd(ab){
		    	alert("hi");
		    	alert(ab);
		    }
		    </script>
	<form>
	    <h3 class="font_20px" style="padding-left:20px">Poll Preview</h3>
		<div id="modal_review_poll_inner">
		   	<table width="100%" border="0" cellspacing="0" cellpadding="0">
		  <tr>
		   	<%	if(model.getCreatedbyImageLink() != null && !model.getCreatedbyImageLink().equals("")&&!model.getCreatedBy().equals("ANONYMOUS")) {	               				
						if(model.getCreatedbyImageLink().contains("default_avatar.png")){%>
							<td width="11%" valign="top" ><img src="ui/images/default_avatar.png" width="46" height="46" alt="User Photo" class="photoframe"></td>
						<%}else{ %>
							<td width="11%" valign="top" >
							<img src="<%=model.getCreatedbyImageLink()%>"  width="46" height="46" alt="User Photo" class="photoframe">
							</td>
						<%}%>
			<%} else{%>
					<%if(model.getCreatedBy() !=null && model.getCreatedBy().equals("ANONYMOUS")){%>
	           			<td width="11%" valign="top" ><img src="ui/images/annonymous_icon.png" width="46" height="46" alt="User Photo" class="photoframe"></td>
					<%}else{ %>
	           			<td width="11%" valign="top" ><img src="ui/images/default_avatar.png" width="46" height="46" alt="User Photo" class="photoframe"></td>
					<%}%>
			<%}%>
	               	<td width="89%">
	               		<table width="100%" border="0" cellspacing="0" cellpadding="0" class="font_12px">
	               			<tr>
						<%
						if(model.getCreatedBy() != null && !model.getCreatedBy().equals("")) { %>
							<td width="20%"><b>Creator:</b></td>
							<td width="30%" class="font_12px_strong"><%=model.getCreatedBy()%></td>
						<%} else{%>
							<td width="20%">&nbsp;</td>
							<td width="30%">&nbsp;</td>
						<%}%>
						<td width="20%">&nbsp;</td>
						<td width="30%">&nbsp;</td>
	                   		</tr>
	                   		<tr>
		                   		<td><b>Created:</b></td>
		                     	<td><span> <%=model.getCreatedDate()%></span></td>
		                     	<td><b>Poll Reference:</b></td>
		                     	<td><span> <%=model.getPollRef()%></span></td>
		                   	</tr>
		                   	<tr>
		                   	<%if(model.getGrpName() != null) {%>
						<td><b>Group:</b></td>
						<td><%=model.getGrpName()%></td>
		                     	<%} %>
		                     	<td><b>Closing Date:</b> </td>
		                     	<td><%=model.getClosureDate()%></td>
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
	           		<td valign="top">
	           			<table width="100%" border="0" cellspacing="0" cellpadding="0">
	                   		<tr>
	                   		<%if(model.getUploadedFileType() != null  && (model.getUploadedFile() != null && model.getUploadedFile().getSize() > 0)){ %>
	                   			<c:set var="fileType" value="<%=model.getUploadedType() %>"/>
	                   			 <c:set var="quesUrl" value="<%=model.getUploadedFileLocation() %>"/> 
	               			    <td width="47%" style=""; valign="top"><h3><%=model.getIncyyte()%></h3></td>    
                        			  <c:choose>
                         				<c:when test="${fileType == 'Image'}">	 
			                     		  	<td width="53%" align="right" valign="top">
                         						<a href="javascript:void(0);" >
				                     			<img src="<%=model.getUploadedFileLocation()%>" border="0" style="width:200px; height:150px">
				                     			</a> 
				                     		</td>
		                     		</c:when>
                         			<c:when test="${fileType == 'Video'}">											
				                     	<td width="53%" align="right" valign="top">
	                        					<span id="#video_thumbs" >
													<ul id="videos">
		             									<li>
							                     		<a href="javascript:void(0);">
				                     				    <img src="ui/images/video_thumb.png"  border="0"   />
							                     			</a>
				                     					</li>
		               								</ul>  
		              							</span> 
		                     				</td>
				                     	</c:when>
				                     	<c:otherwise>
				                     	<%String name = model.getFileName();
				      					String extension = "";
				      					int i = name.lastIndexOf('.');
				      					if (i > 0) {
				      					    extension = name.substring(i+1);
				      					}
				      					String docs[] = {"wpd","wps","xml","xlr","pdf"};
										String gooleDocs[] = {"doc","docx","log","rtf","txt","csv","pps","ppt","xls","xlsx"};
				      					List <String> extDocs = Arrays.asList(docs); 
						      			List <String> extGoogleDocs = Arrays.asList(gooleDocs);
						      			String docUrl = "https://docs.google.com/viewer?url=";
								        String url = (String)pageContext.getAttribute("quesUrl");
								        String viewUrl = docUrl + url; %>
		      					       <%if(extDocs.contains(extension)) {%> 
											<td width="53%" align="right" valign="top">                        					
				                     			<a style="cursor:pointer"  class="group1">
				                     				<img src="ui/images/view_docs_thumb.png"  border="0" style="width:200px; height:150px"  />
				                     			</a>			                     					
		                     				</td>	
		                     	      <%}else if(extGoogleDocs.contains(extension)){ %>
		                     	      <td width="53%" align="right" valign="top">                        					
				                     			<a style="cursor:pointer"  class="group1">
				                     				<img src="ui/images/view_docs_thumb.png"  border="0" style="width:200px; height:150px"  />
				                     			</a>			                     					
		                     				</td>
		                     	      <%} %>
		                     				
		                     	       </c:otherwise>
		                     	   </c:choose> 		                          		
		                          		
	                          	<%} else { %>	
	                     			<td width="47%" valign="top"><h3><%=model.getIncyyte()%></h3></td>    
	                     			<td width="53%" align="right" valign="top">
	                     			<% if(StringUtils.isEmpty(model.getUploadedFileLocation())){ %>
	                     				<img style="width:200px; height:150px" src="ui/images/photo1.png">	
	                     				<%}else {%>
	                     				<img style="width:200px; height:150px" src="<%=model.getUploadedFileLocation()%>">
	                     				<%}%>	                     			
		                     		</td>
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
	          	</tr>
	           	<tr>
	           		<td valign="top">
	            		<table width="100%" border="0" cellspacing="0" cellpadding="0" class="review_poll">
		                   	<tr>
		                  		<td class="view_text">VIEW</td>
		                     	<td>&nbsp;</td>
		                     	<td >&nbsp;</td>
		                   	</tr>
		                   	<c:forEach var="ans" items="<%=model.getAnswers()%>">			                		                         
	                       		<tr>
	                       		<c:set var="extAnswer" value="${ans.answerUploadExt}"/>
	                       		<c:set var="ansUrl"  value="${ans.answerUploadedUrl}"/>
									<%  String extAnswer = (String)pageContext.getAttribute("extAnswer");
									String ansVideos[] = {".flv",".mp4",".mpg",".swf",".wmv"};
									String ansImages[] = {".gif",".png",".jpg",".jpeg",".bmp",".PNG"};
									String ansDocs[] = {".wpd",".wps",".xml",".xlr",".pdf"};
								 	String ansGoogleDocs[] = {".doc",".docx",".log",".rtf",".txt",".csv",".pps",".ppt",".xls",".xlsx"};
								 	List <String> extAnsVideos = Arrays.asList(ansVideos); 
								 	List <String> extAnsImages = Arrays.asList(ansImages); 
								 	List <String> extAnsDocs = Arrays.asList(ansDocs);
								 	List <String> extGoogleDocs = Arrays.asList(ansGoogleDocs);
								 	String docUrl = "https://docs.google.com/viewer?url=";
							        String url = (String)pageContext.getAttribute("ansUrl");
							        String viewUrl = docUrl + url;%>	
								 	<%if(extAnsVideos.contains(StringUtils.lowerCase(extAnswer))) { %>
			                     		<td width="7%"><a href="javascript:void(0)" class="review_view_icon"></a></td>
			                     		<td width="71%"><c:out value="${ans.answerChoice}"/></td>
			                     		<td width="22%" height="46"><a href="javascript:void(0)" title="VOTE!" id="" class="button_gray1" style="width:96px;"> <span class="title_gray1">VOTE!</span></a></td>
		                     		<%} else if(extAnsImages.contains(StringUtils.lowerCase(extAnswer))){ %>
			                     		<td width="7%"><a href="javascript:void(0)" class="review_view_icon"></a></td>
			                     		<td width="71%"><c:out value="${ans.answerChoice}"/></td>
			                     		<td width="22%" height="46"><a href="javascript:void(0)" title="VOTE!" id="" class="button_gray1" style="width:96px;"> <span class="title_gray1">VOTE!</span></a></td>
		                     		<%} else if(extAnsDocs.contains(StringUtils.lowerCase(extAnswer))){ %>	
			                     		<td width="7%"><a style="cursor:pointer" href="javascript:void(0)"   target="_blank" class="review_view_icon"></a></td>
			                     		<td width="71%"><c:out value="${ans.answerChoice}"/></td>
			                     		<td width="22%" height="46"><a href="javascript:void(0)" title="VOTE!" id="" class="button_gray1" style="width:96px;"> <span class="title_gray1">VOTE!</span></a></td>
			                     	<%}  else if(extGoogleDocs.contains(StringUtils.lowerCase(extAnswer))){ %>
			                     	    <td width="7%"><a style="cursor:pointer" href="javascript:void(0)"  target="_blank" class="review_view_icon"></a></td>
			                     		<td width="71%"><c:out value="${ans.answerChoice}"/></td>
			                     		<td width="22%" height="46"><a href="javascript:void(0)" title="VOTE!" id="" class="button_gray1" style="width:96px;"> <span class="title_gray1">VOTE!</span></a></td>
		                     		<%} else {%>
			                     		<td width="7%">&nbsp;</td>
			                     		<td width="71%"><c:out value="${ans.answerChoice}"/></td>
			                     		<td width="22%" height="46"><a href="javascript:void(0)" title="VOTE!" id="" class="button_gray1" style="width:96px;"> <span class="title_gray1">VOTE!</span></a></td>
		                     		<%} %>
		                   		</tr>		                          
	                       	</c:forEach>
		                  
	                 	</table>
	            	</td>
	             </tr>
		 	</table>
		</div>
	    <!-- p> <div title="SEND YOUR POLL NOW" id="previewSendInCyyte" class="button_green1" style="width:215px; margin:20px 0 0 465px; cursor:pointer; cursor:hand;"> <span class="title_green1">SEND YOUR POLL NOW</span></div></p-->
	    <p> <div title="SEND YOUR POLL NOW" id="previewSendInCyyte" class="button_green1" style="width:215px; margin:20px 0 0 465px; cursor:pointer; cursor:hand;"> <span class="title_green1">CONTINUE</span></div></p>
	</form>
 </div>   	
    <!-- Review Poll ends--->
  
<script type="text/javascript" src="ui/js/googleanalytics.js"></script>				
</body>
</html>
