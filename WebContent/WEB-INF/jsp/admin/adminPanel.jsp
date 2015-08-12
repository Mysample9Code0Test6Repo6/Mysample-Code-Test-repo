<%@ include file="/WEB-INF/jsp/common/include.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="content-type" content="text/html; charset=UTF-8">
<link rel="shortcut icon" href="favicon.ico" />

<title>inCyyte - Process Public Polls</title>
<meta charset="utf-8">
<%@page import="com.incyyte.app.web.SessionKeys"%>
<%@page import="com.incyyte.app.domain.User"%>
<%@page import="com.incyyte.app.domain.Answer"%>
<%@page import="com.incyyte.app.domain.InCyyte"%>
<%@page import="com.incyyte.app.domain.CyyteResponse"%>
<%@page import="com.incyyte.app.service.util.Constants"%>
<%@page import="com.incyyte.app.service.util.Utility"%>
<%@page import="java.util.*"%>
<%@ page import="org.apache.commons.lang.StringUtils"%>

<link rel="stylesheet" href="ui/css/reset.css">
<link rel="stylesheet" href="ui/css/layout.css">
<link rel="stylesheet" href="ui/css/buttons.css" media="screen">
<link rel="stylesheet" href="ui/css/style_login.css">
<link rel="stylesheet" href="ui/css/accordionmenu.css" type="text/css">
<link rel="stylesheet" href="ui/css/icons_sprites.css">
<link rel="stylesheet" href="fonts/fonts_stylesheet.css">
<link rel="stylesheet" href="ui/css/ratingbar.css" type="text/css" />
<link rel="stylesheet" href="ui/modal/colorbox.css">
<link rel="stylesheet" href="ui/css/form_elements.css">

<link href="ui/css/style.css" media="screen" rel="stylesheet"
	type="text/css" />
<script>
    if (/*@cc_on!@*/false) {
        var headHTML = document.getElementsByTagName('head')[0].innerHTML;
        headHTML    += '<link type="text/css" rel="stylesheet" href="ui/css/ie10.css">';
        document.getElementsByTagName('head')[0].innerHTML = headHTML;
    }
</script>
<script src="ui/js/jquery-1.7.1.js"></script>
<!-- <script src="ui/js/jquery-1.8.2.min.js"></script> -->
<script src="/ui/js/login.js"></script>
<script src="/ui/js/jquery.color.js"></script>
<script src="/ui/js/search_script.js"></script>

<!-- copied from sent page -->
<script type="text/javascript" src="ui/js/jquery-ui.js"></script>
<script type="text/javascript" src="ui/js/validate/jquery.validate.js"></script>
<script src="ui/js/jquery.selectbox-0.2.js"></script>
<script type="text/javascript" src="ui/js/jquery.ratingbar.js"></script>
<!-- ends here -->

<script src="ui/modal/colorbox/jquery.colorbox.js"></script>
<script src="ui/js/customInput.jquery.js" type="text/javascript"></script>
<script src="ui/js/jquery.limit.js"></script>
<script src="ui/js/zoomphoto.js"></script>
<script src="ui/js/prettyCheckboxes.js" type="text/javascript"
	charset="utf-8"></script>
<script type="text/javascript" src="ui/js/autoresize.jquery.js"></script>
<script src="ui/js/accordian/jquery.ui.core.js"></script>
<script src="ui/js/accordian/jquery.ui.widget.js"></script>
<script src="ui/js/accordian/jquery.ui.accordion.js"></script>
<script src="ui/js/jquery.ui.datepicker.js"></script>
<script type="text/javascript" src="ui/js/jquery.form.js"></script>
<script type="text/javascript" src="ui/js/jquery-ui.js"></script>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/ui/Fancy-box/jquery.fancybox.css"
	type="text/css" media="screen" />
<script type="text/javascript"
	src="js/flowplayer/flowplayer-3.2.12.min.js"></script>
<script type="text/javascript"
	src="ui/fancyplayer_code/js/flowplayer-3.1.1.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/ui/Fancy-box/jquery.fancybox.pack.js"></script>
<script type="text/javascript"
	src="ui/fancyplayer_code/js/jquery.easing.1.3.js"></script>
<script type="text/javascript"
	src="ui/fancyplayer_code/js/fancyplayer.js"></script>

<!-- Left Navigation script starts here
<script src="ui/js/left_menu.js"></script> -->
<!-- Left Navigation script ends here -->
<!-- Questions Tabs starts -->
<script type="text/javascript" src="ui/js/ddaccordion.js"></script>
<script type="text/javascript">
function playVideo(id,playerId,playUrl) {
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
<!-- Questions Tabs Ends -->
<!-- Modal Script Starts Here -->
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

	
	  $(function () {
          $(".fancybox-popup").fancybox({
              maxWidth:800,
              maxHeight:600,
              fitToView:false,
              width:'70%',
              height:'70%',
              autoSize:false,
              closeClick:false,
              openEffect:'none',
              closeEffect:'none'

          });
      });
	   
	  
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
    
    function loadImage(selectedImgContainer, url) {
        document.getElementById(selectedImgContainer).setAttribute("src", url);
        var index = url.lastIndexOf("/") + 1;
        var fileName = url.substr(index);
        document.getElementById("incyyte_photo_search_file").value = fileName;
        document.getElementById("incyyte_photo_search_url").value = url;
    }
 
 
</script>
<script>
function updateQuestion(id,name) {	
	$.post("updateQuestion.cyt?id="+id+"&name="+name,{},function(data) {});
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

</head>
<body>
<div id="gradient">
  <div class="extra">
  <!-- include header -->
	<jsp:include page="../main/includes/emptyHeader.jsp"/>
	
    <div class="main">
      <!--content -->
      <article id="content">
        <div id="main_content">

<%--Answer --%>
<div id="most_resent_incyyte">
	<div id="incomming_incyyte"  style="padding-top: 30px;">
			<a href="/admindash.cyt" class="ready_vote_bot" style="width: 171px;"> <span class="title_red title_red5_ie8">Back</span></a>
			<br>
		<c:forEach var="publicPoll" items="${allPolls}" varStatus="status">
		<div class="incomming_thickborder_line"></div>
				<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
				<tbody>
					<tr><td>
				<table width="80%" border="0" align="center" cellpadding="0" cellspacing="0">
					<tr>
						       <td width="10%" ><c:choose>
								<c:when test="${not empty publicPoll.senderProfilePic}">
									<img src="${publicPoll.senderProfilePic}" width="46"
										height="46" alt="User Photo" class="photoframe" />
								</c:when>
								<c:otherwise>
									<img src="ui/images/default_avatar.png" width="46" height="46"
										alt="User Photo" class="photoframe" />
								</c:otherwise>
							</c:choose></td>
						  <td width="50%" >
							<h3>
								<p class="questionText">${publicPoll.incyyte}</p>
							</h3>
							<p>
								Sender: <strong>${publicPoll.createdBy}</strong>
							</p>
							<p>
								Created: <strong>${publicPoll.createdDatePeriod}</strong>
							</p>
							<p>Closure:${publicPoll.closureDate}</p> <c:if
								test="${not empty publicPoll.group}">
								<p>Group: ${publicPoll.group.groupName}</p>
							</c:if>
						</td>						
						<%--Question --%>										
						<td width="40%" align="right">
						<c:if test="${not empty publicPoll.uploadLocation && not empty publicPoll.upload_name}">
								<c:set var="url" value="${publicPoll.uploadLocation}" />
								<c:set var="uploadName" value="${publicPoll.upload_name}" />
								<%  String name = (String)pageContext.getAttribute("uploadName");
									String extension = "";
									int i = name.lastIndexOf('.');
									if (i > 0) {
										extension = StringUtils.lowerCase((name.substring(i + 1)));
									}
									String videos[] = { "flv", "mp4", "mpg", "swf", "wmv" };
									String images[] = { "gif", "png", "jpg", "jpeg", "bmp" };
									String docs[] = { "wpd", "wps", "xml", "xlr", "pdf" };
									String gooleDocs[] = { "doc", "docx", "log", "rtf", "txt",
											"csv", "pps", "ppt", "xls", "xlsx" };
									List<String> extVideos = Arrays.asList(videos);
									List<String> extImages = Arrays.asList(images);
									List<String> extDocs = Arrays.asList(docs);
									List<String> extGoogleDocs = Arrays.asList(gooleDocs);

									String docUrl = "https://docs.google.com/viewer?url=";
									String url = (String) pageContext.getAttribute("url");
									String viewUrl = docUrl + url;
							%>
								<p>
									<b>Question file:</b>
								<div class="thumbnail">
									<%
									if (extVideos.contains(extension)) {
								%>
									<a
										onClick="javascript:playVideo('${publicPoll.id}','player${publicPoll.id}', '${publicPoll.uploadLocation}')"><img
										src="ui/images/video_thumb.png"
										src="ui/images/video_thumb.png"
										style="display: block; float: right; margin: 10px 2px; width: 50px; height: 50px; border: 2px solid #ffffff; padding: 2px;" /></a>
									<div style="display: none;">
										<div id="emailList${publicPoll.id}" class="emailList ">
											<a style="cursor: pointer" id="player${chart.incyyte.id}"></a>
										</div>
									</div>
									<%
									} else if (extImages.contains(extension)) {
								%>
									<%--Copy --%>
									<a href="${publicPoll.uploadLocation}"
										class="thumbnail fancybox-popup "> <img
										src="${publicPoll.uploadLocation}"
										style="display: block; float: right; margin: 10px 2px; width: 50px; height: 50px; border: 2px solid #ffffff; padding: 2px;" />
									</a> <span><img src="${publicPoll.uploadLocation}" /></span>
									<%
									} else if (extDocs.contains(extension)) {
								%>
									<a style="cursor: pointer" href=""
										onClick="window.open('${publicPoll.uploadLocation}','MyWindow'); return false;"
										target="_blank" class="thumbnail"> <img src="ui/images/view_docs_thumb.png"
										style="display: block; float: right; margin: 10px 2px; width: 50px; height: 50px; border: 2px solid #ffffff; padding: 2px;" />
									</a> <span><img src="ui/images/view_docs_thumb.png" /></span>
									<%
									} else if (extGoogleDocs.contains(extension)) {
								%>
									<a style="cursor: pointer" href=""
										onClick="window.open('<%=viewUrl%>','MyWindow'); return false;"
										target="_blank" class="thumbnail"> <img
										src="ui/images/view_docs_thumb.png"
										style="display: block; float: right; margin: 10px 2px; width: 50px; height: 50px; border: 2px solid #ffffff; padding: 2px;" />
									</a> <span><img src="ui/images/view_docs_thumb.png" /></span>
									
									<% } %>
								</div>
							</c:if> <c:if test="${not empty publicPoll.youtubeUrl}">
								<p>
									<b>Question file:</b>
								<div class="thumbnail">
									<a onClick="javascript:playYoutubeVideoQues('${ans.id}' , '${ans.youtubeUrl}');">
										<img src="ui/images/video_thumb.png" src="ui/images/video_thumb.png" style="display: block; float: right; margin: 10px 2px; width: 50px; height: 50px; border: 2px solid #ffffff; padding: 2px;" />
									</a>

									<div style="display: none;">
										<div id="emailListYouTube_${ans.id}" class="emailList ">
											<iframe width='390' id="iFrameYouTube_${ans.id}"
												height='308' src="ui/images/uploading_big_img.png"
												frameborder='0' type='text/html'></iframe>
										</div>
									</div>
								</div>								
							</c:if>
							<p>
								<c:if test="${not empty publicPoll.group}">
						group name: ${publicPoll.group.groupName}
					</c:if>
							</p>
						</td>
						<%--End Question --%>
					</tr>
					<tr>
						<td colspan="3">Answer option files:</td>
					</tr>
					<tr>
						<td colspan="3" bgcolor="#D8D8D8">						
							<c:forEach var="ans" items="${publicPoll.answers}" varStatus="ansStatus">
								<c:choose>
									<c:when test="${not empty ans.uploadCDN_url}">
										<c:set var="answerExt" value="${ans.uploadExt}" />
										<c:set var="ansURL" value="${ans.uploadCDN_url}" />										
										<% String extAnswer = (String) pageContext.getAttribute("answerExt");
														String ansVideos[] = { ".flv", ".mp4", ".mpg", ".swf", ".wmv" };
														String ansImages[] = { ".gif", ".png", ".jpg", ".jpeg", ".bmp" };
														String ansDocs[] = { ".wpd", ".wps", ".xml", ".xlr", ".pdf" };
														String ansGooleDocs[] = { ".doc", ".docx", ".log", ".rtf", ".txt", ".csv", ".pps", ".ppt", ".xls", ".xlsx" };
														List<String> extAnsVideos = Arrays.asList(ansVideos);
														List<String> extAnsImages = Arrays.asList(ansImages);
														List<String> extAnsDocs = Arrays.asList(ansDocs);
														List<String> extAnsGoogleDocs = Arrays.asList(ansGooleDocs);
														String docUrl = "https://docs.google.com/viewer?url=";
														String ansUrl = (String) pageContext.getAttribute("ansURL");
														String viewUrl = docUrl + ansUrl; %>																								
										<div class="thumbnail"> 
									<% if (extAnsVideos.contains(StringUtils.lowerCase(extAnswer))) {%>									
										<a style="cursor: pointer" class="thumbnail fancybox-popup">
										<img onClick="javascript:playVideo('${ans.id}','player${ans.id}', '${ans.uploadCDN_url}')"
											src="ui/images/video_thumb.png" id="answerImageIncyyte"
											style="display: block; float: left; margin: 10px 2px; width: 50px; height: 50px; border: 2px solid #ffffff; padding: 2px;"/></a>
											<span><b>${ans.answerOption}</b><br><img src="ui/images/video_thumb.png" /></span>
										<%} else if (extAnsImages.contains(StringUtils.lowerCase(extAnswer))) {%>
										    <a style="cursor: pointer" href="${ans.uploadCDN_url}" class="thumbnail fancybox-popup">
											<img src="${ans.uploadCDN_url}" style="display: block; float: left; width: 50px; height: 50px; border: 2px solid #ffffff; padding: 2px; margin: 10px 2px; " /></a>
										<span><b>${ans.answerOption}</b><br><img src="${ans.uploadCDN_url}" /></span>	
								    <%} else if (extAnsGoogleDocs.contains(StringUtils.lowerCase(extAnswer))) {%>	
											<a style="cursor:pointer" href="" onClick="window.open('<%=viewUrl%>','MyWindow'); return false;" target="_blank">
											<img src="ui/images/view_docs_thumb.png" id="answerImageIncyyte" style="display: block;  float: left;  margin: 10px 2px;  width: 50px;  height: 50px;  border: 2px solid #ffffff;  padding: 2px;  };" /></a>
										<span><b>${ans.answerOption}</b><br><img src="ui/images/view_docs_thumb.png" /></span>	
									<%} else if (extAnsDocs.contains(StringUtils.lowerCase(extAnswer))) { %> 
											<a style="cursor: pointer" href="" onClick="window.open('${ans.uploadCDN_url}','MyWindow'); return false;" target="_blank">
											<img src="ui/images/view_docs_thumb.png" style="display: block; float: left; margin: 10px 2px; width: 50px; height: 50px; border: 2px solid #ffffff; padding: 2px;"/></a>
											<span><b>${ans.answerOption}</b><br><img src="ui/images/view_docs_thumb.png" /></span>	
									<% } %>								
										</div>	
									</c:when>
									<c:when test="${not empty ans.youtubeURLAnswer}">
									<div class="thumbnail">
										<a style="cursor: pointer"> <img onClick="javascript:playYoutubeVideoQues('${ans.id}_answer','${ans.youtubeURLAnswer}')"
											src="ui/images/video_thumb.png" id="answerImageIncyyte"
											style="display: block; float: left; margin: 10px 2px; width: 50px; height: 50px; border: 2px solid #ffffff; padding: 2px;" />
											<span><b>${ans.answerOption}</b><br> <img src="ui/images/video_thumb.png" /></span></a>
									</div>
										<div style="display: none;">
											<div id="emailListYouTube_${ans.id}_answer" class="emailList ">
												<iframe width='390' id="iFrameYouTube_${ans.id}_answer"
													height='308' src="ui/images/uploading_big_img.png"
													frameborder='0' type='text/html'></iframe>
											</div>
										</div>
									</c:when>
								</c:choose>
							</c:forEach>
						</td>
					</tr>
				</table>
				</td>
				<td>
				<table width="20%" border="0" cellpadding="0" cellspacing="0">
					<tr>
						<td rowspan="3" align="left" valign="bottom" ><a href=""
							onclick="updateQuestion('${publicPoll.id}','DISPLAY');"
							title="view incyyte" id="view_incyyte" class="button_gray"
							style="width: 171px;"> <span class="title_gray title_vote_now_ie8">DISPLAY</span></a>
						 <br><a href="" onclick="updateQuestion('${publicPoll.id}','REJECT');"
							title="view incyyte" id="view_incyyte" class="button_gray"
							style="width: 171px;"> <span class="title_gray title_vote_now_ie8">REJECT</span>
						</a></td>
					</tr>
				</table>
				</td>
				</tr>
				</tbody>
				</table>
		</c:forEach>
	</div>

	<!-- Pagination start---->
	<div class='pagination'>
	    <%--For displaying Previous link except for the 1st page --%>
	    <c:if test="${currentPage != 1}">
	        <a class='prev page-numbers'
	           href="adminPanel.cyt?page=${currentPage - 1}&param=${text}">Prev</a>
	    </c:if>
	    <%--For displaying Page numbers.
	    The when condition does not display a link for the current page--%>
	    <c:forEach begin="1" end="${publicPollsPageCount}" var="i">
	        <c:choose>
	            <c:when test="${currentPage eq i}">
	                <span class='page-numbers current'>${i}</span>
	            </c:when>
	            <c:otherwise>
	                <c:if test="${i <= 5 }">
	                    <a class='page-numbers'
	                       href="adminPanel.cyt?page=${i}&param=${text}">${i}</a>
	                </c:if>
	                <c:if test="${i > 5}">
	                    <c:if test="${i+1 == publicPollsPageCount}">
	                        <a class='page-numbers'>..</a>
	                        <a class='page-numbers'
	                           href="adminPanel.cyt?page=${i}&param=${text}">${i}</a>
	                    </c:if>
	                    <c:if test="${i == publicPollsPageCount}">
	                        <a class='page-numbers'
	                           href="adminPanel.cyt?page=${i}&param=${text}">${i}</a>
	                    </c:if>
	                </c:if>
	            </c:otherwise>
	        </c:choose>
	    </c:forEach>
	    <%--For displaying Next link --%>
	    <c:if test="${currentPage lt publicPollsPageCount}">
	        <a class='next page-numbers'
	           href="adminPanel.cyt?page=${currentPage + 1}&param=${text}">Next</a>
	    </c:if>
	</div>
	<!-- Pagination ends---->
	<div id="loadmoreajaxloader" style="display:none;"><center><img src="/images/loading.gif" /></center></div>
 </div>
 <style>
     .emailList{
        width: 410px;
    }
    .emailList a object{
        width: 390px;
    }
 </style>
</div>
</article>
</div>
</div>
</div>
</body>
</html>