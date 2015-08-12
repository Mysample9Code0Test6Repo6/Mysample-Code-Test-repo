<%@page import="com.incyyte.app.web.SessionKeys"%>
<%@page import="com.incyyte.app.domain.User"%>
<%@page import="com.incyyte.app.domain.InCyyte" %>
<%@include file="/WEB-INF/jsp/common/include.jsp" %>
<%@page import="org.apache.commons.lang.StringUtils" %>
<%@page import="com.incyyte.app.domain.InCyyteChart"%>
<%@page import="com.incyyte.app.domain.CyyteResponse"%>
<%@page import="java.util.*"%>

<% User user = (User)request.getSession().getAttribute(SessionKeys.LOGIN_USER);
 User incyyteUser = (User) session.getAttribute("incyyteCreator");
 InCyyte cyyte = (InCyyte) session.getAttribute(SessionKeys.INCYYTE);
 boolean isUserVoted = (Boolean)session.getAttribute("isUserVoted");
 InCyyteChart cyyteChart = (InCyyteChart)request.getSession().getAttribute(SessionKeys.INCYYTE_CHART); 	
 %>
 <link rel="shortcut icon" href="${pageContext.request.contextPath}/ui/images/favicon.ico" />
 <title>inCyyte for Business</title>

<link rel="stylesheet" href="${pageContext.request.contextPath}/ui/css/tooltip.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/ui/modal/colorbox.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/ui/css/prettyCheckboxes.css" type="text/css" media="screen">
<link rel="stylesheet" href="${pageContext.request.contextPath}/ui/css/reset.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/ui/css/layout.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/ui/css/buttons.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/ui/css/style_login.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/ui/css/icons_sprites.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/ui/css/modal_window.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/ui/css/form_elements.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/fonts/fonts_stylesheet.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/ui/css/jquery.lightbox-0.5.css" media="screen"  type="text/css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/ui/css/accordionmenu.css" type="text/css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/ui/css/themes/base/jquery.ui.all.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/ui/css/jquery.selectbox.css" type="text/css" /> 
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/ui/css/jquery.lightbox-0.5.css" media="screen" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/ui/css/jquery.validate.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/ui/css/validate/screen.css"> 


<script  language="JavaScript" src="${pageContext.request.contextPath}/ui/js/accordian/jquery-1.6.2.js"></script>
<script  language="JavaScript" src="${pageContext.request.contextPath}/ui/js/jquery-1.7.2.js"></script>

<script  language="JavaScript" type="text/javascript" src="${pageContext.request.contextPath}/ui/js/jquery-ui-1.8.16.custom.min.js"></script> 
<script  language="JavaScript" src="${pageContext.request.contextPath}/ui/js/jquery-1.8.2.min.js" type="text/javascript"></script>
<script language="JavaScript" type="text/javascript" src="${pageContext.request.contextPath}/ui/js/jquery-1.8.1.js"></script>


<script  language="JavaScript" src="${pageContext.request.contextPath}/ui/js/login.js"></script>
<script  language="JavaScript" src="${pageContext.request.contextPath}/ui/js/home/signup_validations.js"></script>
	
<script  language="JavaScript" src="${pageContext.request.contextPath}/ui/js/tooltip.js"></script>
<script  language="JavaScript" src="${pageContext.request.contextPath}/ui/js/jquery.color.js"></script>
<script  language="JavaScript" src="${pageContext.request.contextPath}/ui/js/search_script.js"></script>
<script  language="JavaScript" src="${pageContext.request.contextPath}/ui/js/star_rating.js" type="text/javascript"></script>
<script  language="JavaScript" src="${pageContext.request.contextPath}/ui/js/jquery_poll_rating.js" type="text/javascript"></script>
<script  language="JavaScript" src="${pageContext.request.contextPath}/ui/modal/colorbox/jquery.colorbox.js"></script>

<script  language="JavaScript" type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.form.js"></script>
<script  language="JavaScript" type="text/javascript" src="${pageContext.request.contextPath}/ui/js/jquery-ui.js"></script>
<script  language="JavaScript" type="text/javascript" src="${pageContext.request.contextPath}/ui/js/jquery.lightbox-0.5.js" ></script>
<script  language="JavaScript" type="text/javascript" src="${pageContext.request.contextPath}/ui/js/validate/jquery.validate.js" ></script>

<!-- Left Navigation script starts here -->
<script  language="JavaScript" src="${pageContext.request.contextPath}/ui/js/jquery.selectbox-0.2.js"></script>
<!-- Left Navigation script ends here -->

<!-- Chart Script Start here -->
 
<script  language="JavaScript" src="${pageContext.request.contextPath}/ui/js/charts/js/charts.js"></script>
<script  language="JavaScript" src="${pageContext.request.contextPath}/ui/js/charts/js/charts_recent_incyyte.js"></script>
<script  language="JavaScript" src="${pageContext.request.contextPath}/ui/js/charts/js/highcharts.js"></script> 

<script  language="JavaScript" src="${pageContext.request.contextPath}/ui/js/usernotlogin.js"></script>

<style type="text/css">

.style8 {font-size: 50px}

  </style>

<script language="JavaScript" type="text/javascript" src="${pageContext.request.contextPath}/ui/js/ddaccordion.js"></script>

  	<script  language="JavaScript" type="text/javascript">
	$(function(){
        $('input').customInput();
	});

	    $(document).ready(function() {
	        $("#myTextarea2").limit({
	            limit: 150,
	            id_result: "counter",
	            alertClass: "alert"
	            });
	         });
			$(document).ready(function(){	
				$("#slider").easySlider({
					auto: true, 
					continuous: true
				});
			});	
//Initialize :
ddaccordion.init({
	//headerclass: "incomming_incyyte_tab", //Shared CSS class name of headers group
	contentclass: "displayUserPoll", //Shared CSS class name of contents group
	revealtype: "click", //Reveal content when user clicks or onmouseover the header? Valid value: "click", "clickgo", or "mouseover"
	mouseoverdelay: 200, //if revealtype="mouseover", set delay in milliseconds before header expands onMouseover
	collapseprev: true, //Collapse previous content (so only one open at any time)? true/false 
	defaultexpanded: [], //index of content(s) open by default [index1, index2, etc]. [] denotes no content.
	onemustopen: true, //Specify whether at least one header should be open always (so never all headers closed)
	animatedefault: false, //Should contents open by default be animated into view?
	scrolltoheader: false, //scroll to header each time after it's been expanded by the user?
	persiststate: false, //persist state of opened contents within browser session?
	oninit:function(expandedindices){ //custom code to run when headers have initalized
		//do nothing
	},
	onopenclose:function(header, index, state, isuseractivated){ //custom code to run whenever a header is opened or closed
		//do nothing
	}
});


</script>
<% if (isUserVoted ) { %>
	<script language="JavaScript" type="text/javascript" src="${pageContext.request.contextPath}/ui/js/jquery-1.8.3.min.js" ></script>
<%} %>
<script  language="JavaScript" type="text/javascript">

function submitVoteForm(){
	
	//alert("inside submitVoteForm");
	var qId = $('#questionId').val();
	var mId = $('#memberId').val();

	$('#incyyteId').val(qId);  
	$('#userId').val(mId);  
//alert("before ajax submitVoteForm");
	//alert($("input:radio[name='selectedAnswer']:checked").val());
	if($("input:radio[name='selectedAnswer']:checked").val() != null){
		//alert("inside if");
		$("#voteForm").ajaxSubmit({
			 type: 'POST',
			 url: '../votepoll.cyt',
			 success: function(data) { 		
				// alert("data submitVoteForm"+data);
				if(data.indexOf("notLogin") != -1){		
					//alert("if");
					parent.$.fn.colorbox({'href':'form#create_loginForm', 'open':true, 'inline':true});	
				}
				else if(data.indexOf("failure") != -1){	
					//alert(" else if");
					parent.$.fn.colorbox.close();
					parent.$.fn.colorbox({'href':'div#poll_sysErrorMsg', 'open':true, 'inline':true});
				}
				else{    
					
					window.location.reload(true);
					/* alert("after refresh")
					$('#voteTab').hide();
					alert("resultTab");
					$('#resultTab').show();
					alert("parent.$.fn.colorbox.close();");
					parent.$.fn.colorbox.close();
					
					$('#pollMessage').text(data);
					parent.$.fn.colorbox({'href':'div#poll_Msg', 'open':true, 'inline':true}); */
					//$('#cboxClose').setAttr("onClick","javascript:history.go(0)");
					//$('#cboxClose').addAttr("onClick","javascript:history.go(0)");
					//document.getElemntById("cboxClose")setAttribute("onClick","javascript:history.go(0)");
				} 		 				 	 	
			 },
			 error: function(jqXHR, textStatus, errorThrown) {
			    alert("error:" + textStatus + " exception:" + errorThrown);
			 }
		 });
	//alert("after ajax submitVoteForm");
	}else {
		$("#ans_sel_error").text("Please select an answer");
		$("#ans_sel_error").show();
	}
}

$(function () {
	//alert('fancybox');
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
</script>

<script  language="JavaScript" type="text/javascript" src="${pageContext.request.contextPath}/ui/js/jquery.ratingbar.js"></script>
<script  language="JavaScript" src="${pageContext.request.contextPath}/ui/js/jquery.ui.datepicker.js"></script>

<link rel="stylesheet" href="${pageContext.request.contextPath}/ui/fancyplayer_code/css/jquery.fancybox.css" type="text/css" media="screen" />
<script  language="JavaScript"  src="${pageContext.request.contextPath}/ui/fancyplayer_code/js/flowplayer-3.1.1.min.js"></script>
<script  language="JavaScript"  src="${pageContext.request.contextPath}/ui/fancyplayer_code/js/jquery.fancybox-1.2.1.pack.js"></script>
<script  language="JavaScript" src="${pageContext.request.contextPath}/ui/fancyplayer_code/js/jquery.easing.1.3.js"></script>
<script  language="JavaScript"  src="${pageContext.request.contextPath}/ui/fancyplayer_code/js/fancyplayer.js"></script>

<!-- Rating bar and profile starts here -->
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/ui/css/ratingbar.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/ui/css/style_login.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/ui/css/style.css">

<script  language="JavaScript" src="${pageContext.request.contextPath}/ui/js/jquery.limit.js"></script>
<script  language="JavaScript" type="text/javascript" src="${pageContext.request.contextPath}/ui/js/autoresize.jquery.js"></script> 
<script  language="JavaScript" src="${pageContext.request.contextPath}/ui/js/zoomphoto.js"></script>
<script  language="JavaScript" src="${pageContext.request.contextPath}/ui/js/prettyCheckboxes.js" type="text/javascript" charset="utf-8"></script>
<script  language="JavaScript" src="${pageContext.request.contextPath}/ui/js/accordian/jquery.ui.core.js"></script>
<script  language="JavaScript" src="${pageContext.request.contextPath}/ui/js/accordian/jquery.ui.widget.js"></script>
<script  language="JavaScript" src="${pageContext.request.contextPath}/ui/js/accordian/jquery.ui.accordion.js"></script>
<script  language="JavaScript" src="${pageContext.request.contextPath}/ui/js/jcarousellite.js"></script>
<script  language="JavaScript" src="${pageContext.request.contextPath}/ui/js/easySlider1.7.js" type="text/javascript"></script>
<script  language="JavaScript" src="${pageContext.request.contextPath}/ui/js/jquery.js" type="text/javascript"></script>
 <script  language="JavaScript" src="${pageContext.request.contextPath}/ui/js/customInput.jquery.js" type="text/javascript"></script>



<!-- <script  language="JavaScript" type="text/javascript" src="${pageContext.request.contextPath}/ui/js/jquery.ratingbar.js"></script>
<script  language="JavaScript" src="${pageContext.request.contextPath}/ui/js/jquery.ui.datepicker.js"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath}/ui/fancyplayer_code/css/jquery.fancybox.css" type="text/css" media="screen" />
<script  language="JavaScript" type="text/javascript" src="${pageContext.request.contextPath}/ui/fancyplayer_code/js/flowplayer-3.1.1.min.js"></script>
<script  language="JavaScript" type="text/javascript" src="${pageContext.request.contextPath}/ui/fancyplayer_code/js/jquery.fancybox-1.2.1.pack.js"></script>
<script  language="JavaScript" type="text/javascript" src="${pageContext.request.contextPath}/ui/fancyplayer_code/js/jquery.easing.1.3.js"></script>
<script  language="JavaScript" type="text/javascript" src="${pageContext.request.contextPath}/ui/fancyplayer_code/js/fancyplayer.js"></script> -->
	<!--[if gte IE 8]>
	<style>
	#welcome_user { margin:9px auto 0 auto;}
	</style>
	<![endif]-->
	<!--[if lt IE 9]>
	   <script  language="JavaScript" src="ui/js/html5.js"></script>
	<![endif]-->
	<!--[if lt IE 9]>
	    <meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7">
	  <link href="${pageContext.request.contextPath}/ui/css/iestyle.css" media="screen" rel="stylesheet" type="text/css" />
	  <![endif]-->
	  <!--[if gte IE 9]>
	 <link href="${pageContext.request.contextPath}/ui/css/gte_ie9.css" media="screen" rel="stylesheet" type="text/css" />
	<![endif]-->
	<!-- <style type="text/css">

.style8 {font-size: 50px}

  </style>-->

<script  language="JavaScript" type="text/javascript"  charset="utf-8">

	$(document).ready(function () {
		//alert('display pie chart');
		var chart;
 	
    // Build the chart
	chart = new Highcharts.Chart({
		chart: {
			renderTo: 'containersexpanded',
			plotBackgroundColor: null,
			plotBorderWidth: null,
			plotShadow: false
		},
		title: {
			text: ''
		},
		legend: {
			layout: 'vertical',
			align: 'left',
			verticalAlign: 'middle',
			// y: 50,
			padding:5,
			itemMarginTop: 5,
			itemMarginBottom: 5,
			itemStyle: {
				lineHeight: '14px'
			}
		},
		tooltip: {
			pointFormat: '{series.name}: <b>{point.percentage}%</b>',
			percentageDecimals: 1

		},
		plotOptions: {
			pie: {
				allowPointSelect: false,
				//center: [100, 100],
				size: 100,
				cursor: 'pointer',
				dataLabels: {
					enabled: false
				},
				showInLegend: true
			}
		},      
		colors:['#cfff00','#6ecafc','#c2002d','#1b303f','#a8dffd','#e2f4fe','#e2ff66','#f5ffcc','#da6681','#f3ccd5'],
		series: [{
			type: 'pie',
			name: 'Poll Share',
			data: [
				<%for (CyyteResponse cyyteResponse : cyyteChart.getCyyteResponses()) {%>
					['<%=cyyteResponse.getAnswer()%>',    <%=cyyteResponse.getResponse()%>],
			 	<%}%>			    	
			]
		}]		
	});
});

</script>
<script  language="JavaScript" src="${pageContext.request.contextPath}/ui/js/pollDisplay.js"></script>

<body onload="getUnreadCount();">
  <div id="gradient">
  <div class="extra">
     	<!-- include header -->
    <% if(user != null) {%>	 
    	<jsp:include page="../common/includes/header.jsp" />
    <%} else { %>
    	<jsp:include page="includes/emptyHeader.jsp" />
    <%} %>
         <div class="main">
      <!--content -->
      <article id="content_home">
        <div id="main_content">
           	<table width="800" border="1">
              <tr>
              <%if(StringUtils.equals(incyyteUser.getUserType(),"BUSINESS")) { %>
                <p class="page_heading_A"><span><strong><%=cyyte.getCompanyName() %></strong></span>&nbsp;&nbsp;<img src="<%=cyyte.getCompanyLogoUrl() %>" alt="Company Name" width="106" height="90"></p>
                <%} else {%>
                 <div class="userpoll">
                <p class="page_heading_A"><span>${username}&nbsp;<strong>Poll</strong></span></p>
                </div>
                <%}%>
              </tr>
            </table>
			<% if(user != null) {%>	          
					<div class="grid_9">
		          		<p class="page_heading1">It's always a good time to ask new question!</p>
		          		<p class="page_heading2">Find out what people think about the things that matter to you! </p>
		          		<a href="${pageContext.request.contextPath}/ask_question.cyt" title="Ask A Question" id="" class="button_red" style="width:175px; margin-top:10px; float:left;"> <span class="title_red">Ask A Question</span></a>
		          	</div>
	        <%} else { %>	  			
	          		<div class="grid_9">
	          			<h3 class="page_heading1">Not a member yet?</h3>
	          			<p class="page_heading2">You're missing out on creating unlimited Polls, instant results, Price Tag Payouts and more! </p>
	          			<p class="page_heading2"><a href="#">Take the Tour</a></p>
	          
	           			<a href="${pageContext.request.contextPath}/createAcct.cyt" title="Sign Up Now" id="" class="button_green_big" style="width:225px; margin-top:10px; float:left;"> <span class="title_green_big">Sign Up Now</span></a>
	          		</div>
	     	<%} %> 
<%if(incyyteUser != null && StringUtils.equals(incyyteUser.getUserType(),"BUSINESS")){ %>
<img src="<%=cyyte.getBannerUrl()%>" width="672" height="190">
          <div class="grid_18">
            <!-- My Poll Pages Modal Preview-->
            <p>&nbsp;</p>
              <p>&nbsp;</p>
              <p>&nbsp;</p>
              <p><span class="style8">What's your <strong>opinion?</strong></span></p>
              <p>&nbsp;</p>
              <p>&nbsp;</p>
              <p>&nbsp;</p>
            <div id="modal_preview_poll_page" >
              <div id="preview_poll_inner">
            	<table width="100%" border="0" cellspacing="0" cellpadding="0">
	              	<tr>
	                    <td width="11%" valign="top">
	                    <%if(incyyteUser!=null && incyyteUser.getProfilePicture()!=null && !StringUtils.equals(cyyte.getCreatedBy(),"ANONYMOUS") ){%>
	                    <img src="<%=incyyteUser.getProfilePicture()%>" style="width:60px;">
	                    <%}else if(incyyteUser!=null && StringUtils.equals(cyyte.getCreatedBy(),"ANONYMOUS") ) {%>
	                    <img src="ui/images/annonymous_icon.png" width="46" height="46" alt="User Photo" class="photoframe" />
	                    <%} else {%>
	                    <img src="ui/images/default_avatar.png" width="46" height="46" alt="User Photo" class="photoframe" />
						<%} %>		    				
	                    </td>
	                    <td width="89%">
                     <table width="20%" border="0" cellspacing="0" cellpadding="0" class="font_12px">
                     		<tr><td colspan="2"><p class="heading7"><%=cyyte.getIncyyte()%></p></td></tr>
                        		<tr>
                         			<td>Creator:</td>
                         			<%if(!StringUtils.equals(cyyte.getCreatedBy(),"ANONYMOUS" )) {%><td class="font_12px_strong">${username}</td>
                          			<%} else{%>
                          			<td class="font_12px_strong">anonymous</td>
                          			<%} %>
                        		</tr>
	                        	<tr>
	                          		<td>Created:</td>
	                          		<td width=100%><%=cyyte.getCreatedDate()%></td>
	                        	</tr>
	                       	 	<tr>
							    	<td>Closure:</td>
							    	<td width=100%><%=cyyte.getClosureDate()%></td>
	                        	</tr>
	                      	</table>
	                   	</td>
	              	</tr>
                </table><br>
                <table width="100%" border="0" cellspacing="0" cellpadding="0">
                  <tr>
                    <td width="60%" valign="top">
                    	    	<c:choose>
				      			<c:when test="${not empty incyyte.uploadLocation && not empty incyyte.upload_name}">
				      			<c:set var="url"  value="${incyyte.uploadLocation}"/>
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
									  						<a style="cursor:pointer;" href="${incyyte.uploadLocation}" class="fancybox-popup fancybox.iframe">
																<img src="${pageContext.request.contextPath}/ui/images/video_thumb.png" width="230" height="171" alt="tour" style="margin:0px 0px 0px 80px"  class="photoframe"   /></a>
									    				</li>
													</ul>
								      			</div>
								      			
										
						      					<%}else if(extImages.contains(extension)){ %>		      				
						      					<div class="media_thumbs" >
													<ul id="view_photos">
														<li> 
																<a style="cursor:pointer;" href="${incyyte.uploadLocation}" class="fancybox-popup fancybox.iframe">
																<img src="${incyyte.uploadLocation}" width="230" height="171" alt="tour" style="margin:0px 0px 0px 80px"  class="photoframe"   /></a>
														</li>
												  </ul>
							      				</div>
						      					
						      				<%}else if(extDocs.contains(extension)){ %>		
							      				<div class="media_thumbs" >
													<ul id="view_document">
														<li> 
															<a style="cursor:pointer" href="" onClick="window.open('${incyyte.uploadLocation}','MyWindow'); return false;" target="_blank" class="group">
																<img src="${pageContext.request.contextPath}/ui/images/view_docs_thumb.png" width="230" height="171" alt="tour" style="margin:0px 0px 0px 80px"  class="photoframe"  />
															</a> 
														</li>
													</ul>
							      				</div>
							      			<%}else if(extGoogleDocs.contains(extension)){ %>		
							      				<div class="media_thumbs" >
													<ul id="view_document">
														<li> 
														<a style="cursor:pointer" href="" onClick="window.open('<%=viewUrl%>','MyWindow'); return false;" target="_blank" class="group">
																<img src="${pageContext.request.contextPath}/ui/images/view_docs_thumb.png" width="230" height="171" alt="tour" style="margin:0px 0px 0px 80px"  class="photoframe"  />
															</a> 
														</li>
													</ul>
							      				</div>
						      				<%} else{%>		
						      				<div class="media_thumbs" >
											<ul id="view_photos">
						  						<li> 
						  							<img src="${pageContext.request.contextPath}/ui/images/photo1.png" width="230" height="171" alt="tour" style="margin:0px 0px 0px 80px" class="photoframe"/>
						  						</li>
											</ul>
					      				</div>	
					      				<%} %>
				      				</c:when>
				      				<c:otherwise>
				      					<div class="media_thumbs" >
											<ul id="view_photos">
						  						<li> 
						  							<img src="${pageContext.request.contextPath}/ui/images/photo1.png" width="230" height="171" alt="tour" style="margin:0px 0px 0px 80px" class="photoframe"/>
						  						</li>
											</ul>
					      				</div>		      					
				      				</c:otherwise>
								 </c:choose>
                    </td>
                     <td width="40%"  class="grid_6b" id="voteTab">                     
                     	<form:form id="voteForm" commandName="voteForm"  method="post">
				    		<input type="hidden" name="questionId" id="questionId" value="<%=cyyte.getId()%>"/>
				    		<input type="hidden" name="memberId" id="memberId" value="${memberId}"/>  
				    		<form:hidden path="incyyteId" id="incyyteId"/>
				    		<form:hidden path="userId" id="userId" />  				
				    		<%if(isUserVoted == false) {%>
	                     	<table width="100%" border="0" cellspacing="0" cellpadding="0">
		                        <tr>
		                          <td class="view_text">View</td>
		                          <td>&nbsp;</td>
		                        </tr>
		                        <c:forEach var="ans" items="${incyyte.answers}" varStatus="status">							 	
												<tr valign="top">
													<td style="width:5%;">
														<c:choose>													  												  		
															<c:when test="${not empty ans.uploadCDN_url}">
																<c:set var="extAnswer"  value="${ans.uploadExt}"/>
																<c:set var="ansUrl"  value="${ans.uploadCDN_url}"/>
																<%  
																	String extAnswer = (String)pageContext.getAttribute("extAnswer");
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
																	String viewUrl = docUrl + ansUrl; 
																%>	
										      					<div class="thumbnail view_icon">
										      						<%if(extAnsVideos.contains(StringUtils.lowerCase(extAnswer))) { %>
																		<a style="cursor:pointer" href="${ans.uploadCDN_url}" class="thumbnail view_icon fancybox-popup fancybox.iframe"></a>
																		<span><img src="${pageContext.request.contextPath}/ui/images/video_thumb.png" /></span>
																	<%} else if(extAnsImages.contains(StringUtils.lowerCase(extAnswer))){ %>	
																		<a style="cursor:pointer" href="${ans.uploadCDN_url}" class="thumbnail view_icon fancybox-popup fancybox.iframe"></a>
																		<span><img src="${ans.uploadCDN_url}" /></span>
																	<%}else if(extAnsGoogleDocs.contains(StringUtils.lowerCase(extAnswer))){ %>	
																		<a style="cursor:pointer" href="" onClick="window.open('<%=viewUrl %>','MyWindow'); return false;" target="_blank" class="thumbnail view_icon"></a>
																		<span><img src="${pageContext.request.contextPath}/ui/images/view_docs_thumb.png" /></span>
																	<%} else if(extAnsDocs.contains(StringUtils.lowerCase(extAnswer))){ %>
																		<a style="cursor:pointer" href="" onClick="window.open('${ans.uploadCDN_url}','MyWindow'); return false;" target="_blank" class="thumbnail view_icon"></a>
																		<span><img src="${pageContext.request.contextPath}/ui/images/view_docs_thumb.png" /></span>
																	<%} %>
																</div>
															</c:when>
															<c:otherwise>
																&nbsp;
															</c:otherwise>
														</c:choose> 
												  	</td>	
												  	<td><label for="radio-${status.index}" tabindex="${status.index}">${ans.answerOption}</label>                            	
		                            		<form:radiobutton path="selectedAnswer" id="radio-${status.index}" value="${ans.id}" />
		                            	</td>										  	
												</tr>
											</c:forEach>
		                        <tr>
		                        	<td>&nbsp;</td>
		                        	<td><span id="ans_sel_error" class="error" style="font-size: small;color:red;"></span></td>
		                        </tr>                      
		                        <tr>
		                          <td>&nbsp;</td>
		                          <td><a href="#" onclick="submitVoteForm();" title="READY? VOTE!" id="ready_vote" class="button_red" style="width:171px; margin-top:10px;"> 
		                          		<span class="title_red">READY? VOTE!</span></a> 
		                          </td>
		                        </tr>
	                     	</table>
	                     	<%} else {%>
	                     		<div class="">
								<table width="100%" border="0" cellspacing="0" cellpadding="0">
								<tr>
	                     		<td width="60%" valign="top"><p class="font_16px">Polls Result</p>
	                     		</td></tr></table></div>
				
							  <div id="containersexpanded" style="height: 300px; margin-top:-70px;"></div>
                         <%} %>
                       </form:form>
                     </td>
                  </tr>
                </table>
              </div>
              <p>&nbsp;</p>
              <p>&nbsp;</p>
              <p>&nbsp;</p>
              <p>&nbsp;</p>
              <p><span class="style8">Our <strong>information..</strong></span></p>
              <div class="grid_19">
              <p>&nbsp;</p>
             <p><%=cyyte.getCompanyInfoPara1()%><p>
             <br>
             <p><%=cyyte.getCompanyInfoPara2()%><p>
         	   </div>
              <p>
                <!-- My Poll Pages Modal Preview-->
              </p>
             <p>&nbsp;</p>
             <p>&nbsp;</p>
              <p>&nbsp;</p>
               <p><span class="style8">Our <strong>photos..</strong></span></p>
              <p>&nbsp;</p>
              <p>&nbsp;</p>
              <table width="585" border="0" align="center" cellpadding="3" cellspacing="3">
                <tr>
                  <td><img src="${pageContext.request.contextPath}/ui/images/business_image.png" width="117" height="88" border="3"></td>
                  <td><img src="${pageContext.request.contextPath}/ui/images/business_image.png" width="117" height="88"></td>
                  <td><img src="${pageContext.request.contextPath}/ui/images/business_image.png" width="117" height="88"></td>
                  <td><img src="${pageContext.request.contextPath}/ui/images/business_image.png" width="117" height="88"></td>
                  <td><img src="${pageContext.request.contextPath}/ui/images/business_image.png" width="117" height="88"></td>
                </tr>
                <tr>
                  <td><img src="${pageContext.request.contextPath}/ui/images/business_image.png" alt="" width="117" height="88"></td>
                  <td><img src="${pageContext.request.contextPath}/ui/images/business_image.png" width="117" height="88"></td>
                  <td><img src="${pageContext.request.contextPath}/ui/images/business_image.png" width="117" height="88"></td>
                  <td><img src="${pageContext.request.contextPath}/ui/images/business_image.png" width="117" height="88"></td>
                  <td><img src="${pageContext.request.contextPath}/ui/images/business_image.png" width="117" height="88"></td>
                </tr>
              </table>
            </div>
        </div>
      <!--content end -->
    <%} %>
    
  <!-- ############### display poll for normal user ############## -->
  <%if(incyyteUser != null && StringUtils.equals(incyyteUser.getUserType(),"USER")){ %>
	 <div class="grid_18">
        
		<div class="modal_poll_inner" style="width:650px;margin-right:10px;float:right;" >
		  
            	<table width="100%" border="0" cellspacing="0" cellpadding="0">
	              	<tr>
	                   	<td width="10%" valign="top" >
	                   	 <%if(incyyteUser!=null && incyyteUser.getProfilePicture()!=null && !StringUtils.equals(cyyte.getCreatedBy(),"ANONYMOUS") ){ %>
	                   
					  		<img src="<%=incyyteUser.getProfilePicture()%>" width="46" height="46" alt="User Photo" class="photoframe" />
					  		<%}else if(incyyteUser!=null  && StringUtils.equals(cyyte.getCreatedBy(),"ANONYMOUS") ) {%>
	                    <img src="ui/images/annonymous_icon.png" width="46" height="46" alt="User Photo" class="photoframe" />
					    							
	                    <%}  else{ %>
	                    <img src="ui/images/default_avatar.png" width="46" height="46" alt="User Photo" class="photoframe" />
						<%} %>
					  		</td> <td width="89%">
                            <table width="20%" border="0" cellspacing="0" cellpadding="0" class="font_12px">
                         		<tr>
                          			<td>Creator:</td>
                          			<%if(!StringUtils.equals(cyyte.getCreatedBy(),"ANONYMOUS") ) {%><td class="font_12px_strong">${username}</td>
                          			<%} else{%>
                          			<td class="font_12px_strong">anonymous</td>
                          			<%} %>
                         		</tr>
	                        	<tr>
	                          		<td colspan="2"><%=cyyte.getCreatedDate()%></td>
	                        	</tr>
	                              <% if(cyyte.getGroup() != null) {%>
		                        	<tr>
		                          		<td colspan="2"><%=cyyte.getGroup().getGroupName()%></td>
		                        	</tr>
		                        	<%} %>
	                      	</table>
	                   	</td>
	              	</tr>
                </table><br>
		<table width="640px" border="0" align="center" cellpadding="0" 	cellspacing="0" border-radius="30px">
			<tr>
				<td width="2%">
					<h3><%=cyyte.getIncyyte()%></h3>
				</td></tr>
			<tr>
				<td width="60%" align="left">
	
                    	<c:choose>
				      			<c:when test="${not empty incyyte.uploadLocation && not empty incyyte.upload_name}">
				      			<c:set var="url"  value="${incyyte.uploadLocation}"/>
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
									  						<a style="cursor:pointer;" href="${incyyte.uploadLocation}" class="fancybox-popup fancybox.iframe">
																<img src="${pageContext.request.contextPath}/ui/images/video_thumb.png" width="230" height="171" alt="tour" style="margin:0px 0px 0px 80px"  class="photoframe"   /></a>
									    				</li>
													</ul>
								      			</div>
								      			
										
						      					<%}else if(extImages.contains(extension)){ %>		      				
						      					<div class="media_thumbs" >
													<ul id="view_photos">
														<li> 
																<a style="cursor:pointer;" href="${incyyte.uploadLocation}" class="fancybox-popup fancybox.iframe">
																<img src="${incyyte.uploadLocation}" width="230" height="171" alt="tour" style="margin:0px 0px 0px 80px"  class="photoframe"   /></a>
														</li>
												  </ul>
							      				</div>
						      					
						      				<%}else if(extDocs.contains(extension)){ %>		
							      				<div class="media_thumbs" >
													<ul id="view_document">
														<li> 
															<a style="cursor:pointer" href="" onClick="window.open('${incyyte.uploadLocation}','MyWindow'); return false;" target="_blank" class="group">
																<img src="${pageContext.request.contextPath}/ui/images/view_docs_thumb.png" width="230" height="171" alt="tour" style="margin:0px 0px 0px 80px"  class="photoframe"  />
															</a> 
														</li>
													</ul>
							      				</div>
							      			<%}else if(extGoogleDocs.contains(extension)){ %>		
							      				<div class="media_thumbs" >
													<ul id="view_document">
														<li> 
														<a style="cursor:pointer" href="" onClick="window.open('<%=viewUrl%>','MyWindow'); return false;" target="_blank" class="group">
																<img src="${pageContext.request.contextPath}/ui/images/view_docs_thumb.png" width="230" height="171" alt="tour" style="margin:0px 0px 0px 80px"  class="photoframe"  />
															</a> 
														</li>
													</ul>
							      				</div>
						      				<%} else{%>		
						      				<div class="media_thumbs" >
											<ul id="view_photos">
						  						<li> 
						  							<img src="${pageContext.request.contextPath}/ui/images/photo1.png" width="230" height="171" alt="tour" style="margin:0px 0px 0px 80px" class="photoframe"/>
						  						</li>
											</ul>
					      				</div>	
					      				<%} %>
				      				</c:when>
				      				<c:otherwise>
				      					<div class="media_thumbs" >
											<ul id="view_photos">
						  						<li> 
						  							<img src="${pageContext.request.contextPath}/ui/images/photo1.png" width="230" height="171" alt="tour" style="margin:0px 0px 0px 80px" class="photoframe"/>
						  						</li>
											</ul>
					      				</div>		      					
				      				</c:otherwise>
								 </c:choose>
							</td>
							
                     <td width="40%"  class="grid_6b" id="voteTab">                     
                     	<form:form id="voteForm" commandName="voteForm"  method="post">
				    		<input type="hidden" name="questionId" id="questionId" value="${incyyteId}"/>
				    		<input type="hidden" name="memberId" id="memberId" value="${memberId}"/> 
				    		<form:hidden path="incyyteId" id="incyyteId"/> 
				    		<form:hidden path="userId" id="userId" />  				
				    		<%if(isUserVoted == false) {%>
	                     	<table width="100%" border="0" cellspacing="0" cellpadding="0">
		                        <tr>
		                          <td class="view_text">View</td>
		                          <td>&nbsp;</td>
		                        </tr>
		                        <c:forEach var="ans" items="${incyyte.answers}" varStatus="status">							 	
												<tr valign="top">
													<td style="width:5%;">
														<c:choose>													  												  		
															<c:when test="${not empty ans.uploadCDN_url}">
																<c:set var="extAnswer"  value="${ans.uploadExt}"/>
																<c:set var="ansUrl"  value="${ans.uploadCDN_url}"/>
																<%  
																	String extAnswer = (String)pageContext.getAttribute("extAnswer");
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
																	String viewUrl = docUrl + ansUrl; 
																%>	
										      					<div class="thumbnail view_icon">
										      						<%if(extAnsVideos.contains(StringUtils.lowerCase(extAnswer))) { %>
																		<a style="cursor:pointer" href="${ans.uploadCDN_url}" class="thumbnail view_icon fancybox-popup fancybox.iframe"></a>
																		<span><img src="${pageContext.request.contextPath}/ui/images/video_thumb.png" /></span>
																	<%} else if(extAnsImages.contains(StringUtils.lowerCase(extAnswer))){ %>	
																		<a style="cursor:pointer" href="${ans.uploadCDN_url}" class="thumbnail view_icon fancybox-popup fancybox.iframe"></a>
																		<span><img src="${ans.uploadCDN_url}" /></span>
																	<%}else if(extAnsGoogleDocs.contains(StringUtils.lowerCase(extAnswer))){ %>	
																		<a style="cursor:pointer" href="" onClick="window.open('<%=viewUrl %>','MyWindow'); return false;" target="_blank" class="thumbnail view_icon"></a>
																		<span><img src="${pageContext.request.contextPath}/ui/images/view_docs_thumb.png" /></span>
																	<%} else if(extAnsDocs.contains(StringUtils.lowerCase(extAnswer))){ %>
																		<a style="cursor:pointer" href="" onClick="window.open('${ans.uploadCDN_url}','MyWindow'); return false;" target="_blank" class="thumbnail view_icon"></a>
																		<span><img src="${pageContext.request.contextPath}/ui/images/view_docs_thumb.png" /></span>
																	<%} %>
																</div>
															</c:when>
															<c:otherwise>
																&nbsp;
															</c:otherwise>
														</c:choose> 
												  	</td>	
												  	<td><label for="radio-${status.index}" tabindex="${status.index}">${ans.answerOption}</label>                            	
		                            		<form:radiobutton path="selectedAnswer" id="radio-${status.index}" value="${ans.id}" />
		                            	</td>										  	
												</tr>
											</c:forEach>
		                        <tr>
		                        	<td>&nbsp;</td>
		                        	<td><span id="ans_sel_error" class="error" style="font-size: small;color:red;"></span></td>
		                        </tr>                      
		                        <tr>
		                          <td>&nbsp;</td>
		                          <td><a href="#" onclick="submitVoteForm();" title="READY? VOTE!" id="ready_vote" class="button_red" style="width:171px; margin-top:10px;"> 
		                          		<span class="title_red">READY? VOTE!</span></a> 
		                          </td>
		                        </tr>
	                     	</table>
	                     	<%} else {%>
                     	  <div class="">
								<table width="100%" border="0" cellspacing="0" cellpadding="0">
								<tr>
	                     		<td width="60%" valign="top"><p class="font_16px">Polls Result</p>
	                     		</td></tr></table></div>
				
							  <div id="containersexpanded" style="height: 300px; margin-top:-70px;"></div><%} %>
                       </form:form>
                     </td>				    		
						</tr>
						</table>
						</div>
					
      <!--  content end-->
    <%} %>
    </div>
	</article>
	</div>
	 <!-- include footer -->
	     <% if(user != null) {%>	 
    	<jsp:include page="../common/includes/footer.jsp" />
    <%} else { %>
    	<jsp:include page="includes/homeFooter.jsp" /> 
    <%} %>
	
	</div>
    </div> 
</body>
  <div style="display:none">
     	<!--------Login Modal ----------->
		<form:form  id="create_loginForm" commandName="loginForm" method="post">			
			<p class="heading1">Log In </p>
			<p class="font_16px"><span><a href="#create_signupform" id="signup-user">Don't have an account yet?</a></span></p>
			
			<form:input path="login_email" id="login_email"  placeholder="Email" onKeyUp="validateLoginEmail()" onFocus="this.placeholder = ''" onBlur="this.placeholder = 'Email'" />                
			<p><span id="loginEmailError"  style="display: none; color: #C2002D;font-size:15px;"></span></p>
			<br>
			<form:password path="login_pwd" id="password-password" placeholder="Password" onFocus="this.placeholder = ''" onBlur="this.placeholder = 'Password'" />                
			<br>
			<button type="button" title="Sign up"  id="create_login_btn" class="button_green1" style="width:100px; margin:20px 0 0 250px;"> <span class="title_green1">LOG IN</span></button>
			<span> <a href="#create_fgtpwdform"  id="fgtpwd-user" title="Request new password.">Forgotten your password?</a></span>
		</form:form >
		<!--------Login Modal ----------->		 
        	<!--------Sign UP Modal ----------->
		<form:form id="create_signupform" commandName="signUpForm" method="post"> 
			<p class="heading1">Sign up today, It's FREE!</p>
			<p class="font_16px">And...you can earn money too!</p>
			<form:input path="username" id="su_username" placeholder="Username" onFocus="this.placeholder = ''" onBlur="this.placeholder = 'Username'" />
			<br>  
			<form:input path="su_email"  id="su_email" placeholder="Email" onFocus="this.placeholder = ''" onBlur="this.placeholder = 'Email'" />
			<br>                
			<form:input path="su_password"  id="su_password" autocomplete="off" placeholder="Password" onFocus="this.placeholder = ''" onBlur="this.placeholder = 'Password'" />
			<br> 
			<button type="button" title="Sign up"  id="create_signup_btn" class="button_green1" style="width:100px; margin:20px 0 0 300px;"> <span class="title_green1">Sign up</span></button>
<!-- 			<span> <a href="#create_loginForm" id="login-user" title="Members login here">Already have an Account?</a></span>	 -->
      		</form:form>
		<!--------Sign UP Modal ----------->  
       		<form:form id="create_fgtpwdform" commandName="loginForm" method="post">        		
		      	<p class="heading1">Forgotten Password</p>	
		      	<p class="font_16px"></p>
		      	<p id="fgtpwdErrmsg" style="display: none;">The email does not exist </p>	
		      	<br>      		
			<form:input path="user_email"  id="user_email" placeholder="Email" onFocus="this.placeholder = ''" onBlur="this.placeholder = 'Email'"/>
			<br>      
			<button type="button" id="create_fgtpwd_btn" class="button_green1" style="width:100px; margin:20px 0 0 250px;"> <span class="title_green1">ENTER</span></button>
		        <br> 
			Enter your email address and your credentials will be sent to you.		
		</form:form>
		<div id="create_fgtpwdmsg" title="New Password sent.">
	        <p>  <br><br><br>          
	            <span>Your account password has been reset. Check your email for details.<br><br>
	            Once logged into your account, please change your password in your account settings.</span>
	  		</p>
		</div>
		<div class="msg_box" id="poll_sysErrorMsg" title="System Error">
	        <p>  <br><br><br>          
	            <span> 
	            	Error submitting your voting request. Please again try later.<br><br>
	            </span>
	  		</p>
		</div>
		<div  class="msg_box" id="poll_Msg" title="Message">
	        <p>  <br><br><br>          
	         <span id="pollMessage" ></span>
	  		</p>
		</div>
	</div>

