<%@ include file="/WEB-INF/jsp/common/include.jsp" %>
<%@page import="com.incyyte.app.domain.User"%>
<%@page import="com.incyyte.app.web.model.UserSettingsModel"%>
<%@page import="com.incyyte.app.web.model.UserContactModel"%>
<%@page import="org.apache.commons.lang.StringUtils" %>
<%@page import="com.incyyte.app.domain.InCyyte" %>
<%@page import="com.incyyte.app.service.util.Constants"%>
<%@page import="com.incyyte.app.domain.InCyyteChart"%>
<%@page import="com.incyyte.app.web.SessionKeys"%>
<%@ page import="com.incyyte.app.web.model.UserPollPageModel" %>
<%@page import="com.incyyte.app.domain.CyyteResponse"%>
<%@page import="java.util.*"%>

<script type="text/javascript">
function displayMaleContacts(id) {
	//parent.$.fn.colorbox({href:'div#maleEmailList'+id, open:true, inline:true});
	closeAllContactListBoxes();
	$("#maleEmailList"+id).css("display", "");
}
function displayFemaleContacts(id) {
	//parent.$.fn.colorbox({href:'div#femaleEmailList'+id, open:true, inline:true});
	closeAllContactListBoxes();
	$("#femaleEmailList"+id).css("display", "");
}
function displayUnspecifiedContacts(id) {
	//parent.$.fn.colorbox({href:'div#unspecifiedEmailList'+id, open:true, inline:true});
	closeAllContactListBoxes();
	$("#unspecifiedEmailList"+id).css("display", "");
}

function closeAllContactListBoxes(){
	$("div[id^='maleEmailList']").css("display", "none");
	$("div[id^='femaleEmailList']").css("display", "none");
	$("div[id^='unspecifiedEmailList']").css("display", "none");	
}

function playYoutubeVideoQues(id, videoId){
    $("#iFrameYouTube_"+id).attr("src", videoId);
    parent.$.fn.colorbox({href:'div#emailListYouTube_'+id, open:true, inline:true});
}
function playYoutubeVideoQuesPie(id, videoId){
    $("#iFrameYouTubePie_"+id).attr("src", videoId);
    parent.$.fn.colorbox({href:'div#emailListYouTubePie_'+id, open:true, inline:true});
}
</script>

<% User user = (User)request.getSession().getAttribute(SessionKeys.LOGIN_USER);
    User incyyteUser = (User) session.getAttribute("incyyteCreator");
    InCyyte cyyte = (InCyyte) session.getAttribute(SessionKeys.INCYYTE);
    boolean isUserVoted = (Boolean)session.getAttribute("isUserVoted");
    InCyyteChart  cyyteChart = (InCyyteChart)request.getSession().getAttribute(SessionKeys.INCYYTE_CHART);
    UserSettingsModel userSettingsModel = (UserSettingsModel)request.getSession().getAttribute(SessionKeys.LOGIN_USER_SETTINGS);%>
    
<div class="incomming_incyyte_detail" style=" width: 97%; float: left; border-bottom: 10px #aeaead solid;border-top:10px #aeaead solid;" >
    <table width="100%" border="0" cellspacing="0" cellpadding="0">
    <tr>
    <td width="60%" id="td2BeforeVoted" valign="top">
        <table width="100%" id="td1BeforeVoted" border="0" cellspacing="0" cellpadding="0">
            <tr>
                <td width="5%" valign="top">
                   <%if(incyyteUser!=null && incyyteUser.getProfilePicture()!=null && !StringUtils.equals(cyyte.getCreatedBy(),"ANONYMOUS") ){
                        %>
                    <img src="<%=incyyteUser.getProfilePicture()%>" style="width:60px;">
                    <%}else if(incyyteUser!=null && StringUtils.equals(cyyte.getCreatedBy(),"ANONYMOUS") ) {
                        %>
                    <img src="${pageContext.request.contextPath}/ui/images/annonymous_icon.png" width="46" height="46" alt="User Photo" class="photoframe" />
                    <%} else {
                        %>
                    <img src="${pageContext.request.contextPath}/ui/images/default_avatar.png" width="46" height="46" alt="User Photo" class="photoframe" />
                    <%} %>
                </td>
                <td width="11%">
                    <table width="60%" border="0"  cellspacing="0" cellpadding="0" class="font_12px">
                        <tr><td colspan="2"><p class="questionText" style="margin-bottom: 10px;"><%=cyyte.getIncyyte()%></p></td></tr>
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
            <tr>
        </table>
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
                            <a style="cursor:pointer" style="display:block;width:425px;height:300px;"></a>
                            <span><img onClick="javascript:playVideoBeforeVoted('${chart.incyyte.id}','playerBeforeVoted${chart.incyyte.id}', '${chart.incyyte.uploadLocation}')" src="${pageContext.request.contextPath}/ui/images/video_thumb.png" /></span>
                            <div  style="display:none;">
                                <div id="emailListBeforeVoted${chart.incyyte.id}" class="emailList ">
                                    <a style="cursor:pointer;" id="playerBeforeVoted${chart.incyyte.id}" ></a>
                                </div>
                            </div>
                        </li>
                    </ul>
                </div>
                <%}else if(extImages.contains(extension)){ %>
                <div class="media_thumbs" >
                    <ul id="view_photos" style="margin-top: 15px;">
                        <li >
                            <a style="cursor:pointer;" href="${incyyte.uploadLocation}" rel="single" class="fancybox-popup">
                                <img src="${incyyte.uploadLocation}" width="230" height="171" alt="tour" style="margin:0px 0px 0px 80px"  class="photoframe"   /></a>
                        </li>
                    </ul>
                </div>
            <%}else if(extDocs.contains(extension)){ %>
            <div class="media_thumbs" >
                <ul id="view_document">
                    <li >
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
            <c:when test="${not empty chart.incyyte.youtubeUrl}">
                <div class="media_thumbs" >
                    <ul id="view_videos">
                        <li>
                            <a style="cursor:pointer" onClick="javascript:playYoutubeVideoQues('${chart.incyyte.id}' , '${chart.incyyte.youtubeUrl}')" style="display:block;width:425px;height:300px;">
                            <span><img src="${pageContext.request.contextPath}/ui/images/video_thumb.png" width="230" height="171" style="margin:0px 0px 0px 80px" class="photoframe"/></span></a>
                            <div  style="display:none;">
                                <div id="emailListYouTube_${chart.incyyte.id}" class="emailList ">
                                    <iframe width='390' id="iFrameYouTube_${chart.incyyte.id}"  height='308'  src="${pageContext.request.contextPath}/ui/images/uploading_big_img.png" frameborder='0' type='text/html'></iframe>
                                </div>
                            </div>
                        </li>
                    </ul>
                </div>
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

<td width="40%" valign="top" style="padding-left:0px; "  class="grid_6b" id="voteTab">
<%if(isUserVoted == false) {%>
<form:form id="voteForm" commandName="voteForm"  method="post">
<input type="hidden" name="questionId" id="questionId" value="<%=cyyte.getId()%>"/>
<input type="hidden" name="memberId" id="memberId" value="${memberId}"/>
<form:hidden path="incyyteId" id="incyyteId"/>
<form:hidden path="userId" id="userId" />
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
                        <% String extAnswer = (String)pageContext.getAttribute("extAnswer");
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
                            <a style="cursor:pointer" onClick="javascript:playVideo('${ans.id}','player${ans.id}', '${ans.uploadCDN_url}')"  class="thumbnail view_icon"></a>
                            <span><img src="${pageContext.request.contextPath}/ui/images/video_thumb.png" /></span>
                            <div style="display:none;">
                                <div id="emailList${ans.id}" class="emailList ">
                                    <a style="cursor:pointer" id="player${ans.id}" ></a>
                                </div>
                            </div>
                            <% }else if(extAnsImages.contains(StringUtils.lowerCase(extAnswer))){ %>
                            <a style="cursor:pointer" href="${ans.uploadCDN_url}" rel="single" class="thumbnail view_icon fancybox-popup"></a>
                            <span><img src="${ans.uploadCDN_url}" /></span>
                            <%}else if(extAnsGoogleDocs.contains(StringUtils.lowerCase(extAnswer))){ %>
                            <a style="cursor:pointer" href="" onClick="window.open('<%=viewUrl %>','MyWindow'); return false;" target="_blank" class="thumbnail view_icon"></a>
                            <span><img src="${pageContext.request.contextPath}/ui/images/view_docs_thumb.png" /></span>
                            <%} else if(extAnsDocs.contains(StringUtils.lowerCase(extAnswer))){ %>
                            <a style="cursor:pointer" href="" onClick="window.open('${ans.uploadCDN_url}','MyWindow'); return false;" target="_blank" class="thumbnail view_icon"></a>
                            <span><img src="${pageContext.request.contextPath}/ui/images/view_docs_thumb.png" /></span>
                            <%}%>
                        </div>
                    </c:when>
                    <c:when test="${not empty ans.youtubeURLAnswer}">
                        <div class="thumbnail view_icon">
                            <a style="cursor:pointer" onClick="javascript:playYoutubeVideoQues('${ans.id}_answer','${ans.youtubeURLAnswer}')" class="thumbnail view_icon"></a>
                            <span><img src="${pageContext.request.contextPath}/ui/images/video_thumb.png" /></span>
                            <div style="display:none;">
                                <div id="emailListYouTube_${ans.id}_answer" class="emailList " >
                                    <iframe width='390' id="iFrameYouTube_${ans.id}_answer"  height='308'  src="${pageContext.request.contextPath}/ui/images/uploading_big_img.png" frameborder='0' type='text/html'></iframe>
                                </div>
                            </div>
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
        <td><a href="javascript:void(0);" onclick="submitVoteForm();" title="READY? VOTE!" id="ready_vote" class="button_red" style="width:171px; margin-top:10px;">
            <span class="title_red">READY? VOTE!</span></a>
        </td>
     </tr>
     <%if(user != null){ %>
     <tr>
     	<td colspan="2" valign="bottom" >
				<a href="javascript:void(0)" onclick="submitReportAbuse(${chart.incyyte.id}, '')" style="margin-top: 32px;float: right;"><span id="report_abuse" class="report_abuse"><span></span>Report Abuse</span></a>
				</td>
		</tr>
	<%} %>
     
</table>
</form:form>

<%} else {%>
<table width="100%" border="0" cellspacing="0" cellpadding="0">
    <tr>
        <td width="11%" valign="top">
            <%if(incyyteUser!=null && incyyteUser.getProfilePicture()!=null && !StringUtils.equals(cyyte.getCreatedBy(),"ANONYMOUS") ){
                 %>
            <img src="<%=incyyteUser.getProfilePicture()%>" style="width:60px;">
            <%}else if(incyyteUser!=null && StringUtils.equals(cyyte.getCreatedBy(),"ANONYMOUS") ) {
                 %>
            <img src="${pageContext.request.contextPath}/ui/images/annonymous_icon.png" width="46" height="46" alt="User Photo" class="photoframe" />
            <%} else {
                %>
            <img src="${pageContext.request.contextPath}/ui/images/default_avatar.png" width="46" height="46" alt="User Photo" class="photoframe" />
            <%} %>
        </td>

        <td width="68%" valign="top">
            <h3>${chart.question}</h3>
        </td>
        <td width="32%" align="right">

            <c:if test="${not empty chart.incyyte.uploadLocation && not empty chart.incyyte.upload_name}">
                <c:set var="url"  value="${chart.incyyte.uploadLocation}"/>
                <%
                    String name = cyyteChart.getIncyyte().getUpload_name();
                    String extension = "";
                    int i = name.lastIndexOf('.');
                    if (i > 0) {
                        extension = StringUtils.lowerCase((name.substring(i + 1)));
                    }
                    String videos[] = { "flv", "mp4", "mpg", "swf", "wmv" };
                    String images[] = { "gif", "png", "jpg", "jpeg", "bmp" };
                    String docs[] = {"wpd","wps","xml","xlr","pdf"};
                    String gooleDocs[] = {"doc","docx","log","rtf","txt","csv","pps","ppt","xls","xlsx"};
                    List<String> extVideos = Arrays.asList(videos);
                    List<String> extImages = Arrays.asList(images);
                    List <String> extDocs = Arrays.asList(docs);
                    List <String> extGoogleDocs = Arrays.asList(gooleDocs);

                    String docUrl = "https://docs.google.com/viewer?url=";
                    String url = (String)pageContext.getAttribute("url");
                    String viewUrl = docUrl + url; %>
                <p>
                    <b>Question file:</b>
                <div class="thumbnail">
                    <% if (extVideos.contains(extension)) {%>
                    <a onClick="javascript:playVideo('${chart.incyyte.id}','player${chart.incyyte.id}', '${chart.incyyte.uploadLocation}')"><img  src="${pageContext.request.contextPath}/ui/images/video_thumb.png" style="display: block; float: right; margin: 10px 2px; width: 50px; height: 50px; border: 2px solid #ffffff; padding: 2px;" /></a>
                    <div style="display:none;">
                        <div  id="emailList${chart.incyyte.id}" class="emailList ">

                            <a style="cursor:pointer" id="player${chart.incyyte.id}" ></a>
                        </div></div>
                    <% } else if (extImages.contains(extension)) { %>
                    <a href="${chart.incyyte.uploadLocation}"  class="${pageContext.request.contextPath}/thumbnail fancybox-popup">
                        <img src="${chart.incyyte.uploadLocation}" style="display: block; float: right; margin: 10px 2px; width: 50px; height: 50px; border: 2px solid #ffffff; padding: 2px;" />
                    </a><span><img src="${chart.incyyte.uploadLocation}" /></span>
                    <% } else if (extDocs.contains(extension)) { %>
                    <a style="cursor:pointer" href="" onClick="window.open('${chart.incyyte.uploadLocation}','MyWindow'); return false;" target="_blank" class="thumbnail">
                        <img src="${pageContext.request.contextPath}/ui/images/view_docs_thumb.png" style="display: block; float: right; margin: 10px 2px; width: 50px; height: 50px; border: 2px solid #ffffff; padding: 2px;" />
                    </a>
                    <span><img src="${pageContext.request.contextPath}/ui/images/view_docs_thumb.png" /></span>
                    <% } else if (extGoogleDocs.contains(extension)) { %>
                    <a style="cursor:pointer" href="" onClick="window.open('<%=viewUrl%>','MyWindow'); return false;" target="_blank" class="thumbnail">
                        <img src="${pageContext.request.contextPath}/ui/images/view_docs_thumb.png" style="display: block; float: right; margin: 10px 2px; width: 50px; height: 50px; border: 2px solid #ffffff; padding: 2px;" />
                    </a>
                    <span><img src="${pageContext.request.contextPath}/ui/images/view_docs_thumb.png" /></span>
                    <% } %>
                </div>
                </p>
            </c:if>
            <c:if test="${not empty chart.incyyte.youtubeUrl}">
				<p>
				<b>Question file:</b>
				<div class="thumbnail">
				<a onClick="javascript:playYoutubeVideoQuesPie('${chart.incyyte.id}' , '${chart.incyyte.youtubeUrl}');" >
				<img src="${pageContext.request.contextPath}/ui/images/video_thumb.png" style="display: block; float: right; margin: 10px 2px; width: 50px; height: 50px; border: 2px solid #ffffff; padding: 2px;" /></a>
				<div style="display:none;">
					<div id="emailListYouTubePie_${chart.incyyte.id}" class="emailList " >
						 <iframe width='390' id="iFrameYouTubePie_${chart.incyyte.id}"  height='308'  src="${pageContext.request.contextPath}/ui/images/uploading_big_img.png" frameborder='0' type='text/html'></iframe>
					</div>
				</div> 
				</div>
			</p>
			</c:if>
            <p>
                <c:if test="${not empty chart.incyyte.group}">
                    group name: ${chart.incyyte.group.groupName}
                </c:if>
            </p>
        </td>
    </tr>
</table>
<% int filesCount = 0;%>
<c:forEach var="ans" items="${incyyte.answers}" varStatus="ansStatus">
    <c:choose>
        <c:when test="${not empty ans.uploadCDN_url}">
            <% filesCount++;%>
        </c:when>
        <c:when test="${not empty ans.youtubeURLAnswer}">
			<% filesCount++;%>
		</c:when>
    </c:choose>
</c:forEach>
<% if (filesCount > 0) { %>
<div class="expanded_incyyte_detail_answers" id="answerOptionFileImage"  >
    <table width="100%" border="0" cellspacing="0" id="">
        <tr><td><h2>Answer option files :</h2></td></tr>
        <tr>
            <td width="78%" style="padding-left:10px;" bgcolor="#D8D8D8">
                <c:forEach var="ans" items="${incyyte.answers}" varStatus="ansStatus">
                    <c:choose>
                        <c:when test="${not empty ans.uploadCDN_url}">
                            <c:set var="answerExt" value="${ans.uploadExt}"/>
                            <c:set var="ansURL"  value="${ans.uploadCDN_url}"/>
                            <% 	String extAnswer = (String) pageContext.getAttribute("answerExt");
                                String ansVideos[] = { ".flv", ".mp4", ".mpg", ".swf",".wmv" };
                                String ansImages[] = { ".gif", ".png", ".jpg", ".jpeg",".bmp" };
                                String ansDocs[] = { ".wpd", ".wps", ".xml", ".xlr",".pdf" };
                                String ansGooleDocs[] = { ".doc", ".docx", ".log",".rtf", ".txt", ".csv", ".pps", ".ppt", ".xls",".xlsx" };
                                List<String> extAnsVideos = Arrays.asList(ansVideos);
                                List<String> extAnsImages = Arrays.asList(ansImages);
                                List<String> extAnsDocs = Arrays.asList(ansDocs);
                                List<String> extAnsGoogleDocs = Arrays.asList(ansGooleDocs);
                                String docUrl = "https://docs.google.com/viewer?url=";
                                String ansUrl = (String) pageContext.getAttribute("ansURL");
                                String viewUrl = docUrl + ansUrl;
                                if (extAnsVideos.contains(StringUtils.lowerCase(extAnswer))) {%>

                            <a style="cursor:pointer"class="thumbnail2">
                                <img onClick="javascript:playVideo('${ans.id}','player${ans.id}', '${ans.uploadCDN_url}')" src="${pageContext.request.contextPath}/ui/images/video_thumb.png" id="answerImageIncyyte" style="display: block; float: left; margin: 10px 2px; width: 50px; height: 50px; border: 2px solid #ffffff; padding: 2px;" />
                                <span><b>${ans.answerOption}</b><br> <img src="${pageContext.request.contextPath}/ui/images/video_thumb.png" /></span></a>
                            <div  style="display:none;">
                                <div id="emailList${ans.id}" class="emailList ">
                                    <a style="cursor:pointer" id="player${ans.id}" ></a>
                                </div>
                            </div>
                            <% }  else if (extAnsImages.contains(StringUtils.lowerCase(extAnswer))) { %>
                            <a style="cursor: pointer" href="${ans.uploadCDN_url}" class="thumbnail2 fancybox-popup">
                                <img src="${ans.uploadCDN_url}"style="display: block; float: left; margin: 10px 2px; width: 50px; height: 50px; border: 2px solid #ffffff; padding: 2px;" />
                                <span><b>${ans.answerOption}</b><br> <img src="${ans.uploadCDN_url}" /></span></a>
                            <% } else if (extAnsGoogleDocs.contains(StringUtils.lowerCase(extAnswer))) { %>
                            <a style="cursor: pointer" href="" onClick="window.open('<%=viewUrl%>','MyWindow'); return false;" target="_blank"  class="thumbnail2">
                                <img src="${pageContext.request.contextPath}/ui/images/view_docs_thumb.png"style="display: block; float: left; margin: 10px 2px; width: 50px; height: 50px; border: 2px solid #ffffff; padding: 2px;" />
                                <span><b>${ans.answerOption}</b><br> <img src="${pageContext.request.contextPath}/ui/images/view_docs_thumb.png" /></span></a>
                            <% } else if (extAnsDocs.contains(StringUtils.lowerCase(extAnswer))) { %>
                            <a style="cursor: pointer" href="" onClick="window.open('${ans.uploadCDN_url}','MyWindow'); return false;" target="_blank" class="thumbnail2">
                                <img src="${pageContext.request.contextPath}/ui/images/view_docs_thumb.png"style="display: block; float: left; margin: 10px 2px; width: 50px; height: 50px; border: 2px solid #ffffff; padding: 2px;" />
                                <span><b>${ans.answerOption}</b><br> <img src="${pageContext.request.contextPath}/ui/images/view_docs_thumb.png" /></span></a>
                            <% } %>
                        </c:when>
                        <c:when test="${not empty ans.youtubeURLAnswer}">
							<a style="cursor: pointer" class="thumbnail2"> 
							<img onClick="javascript:playYoutubeVideoQues('${ans.id}_answer','${ans.youtubeURLAnswer}')" src="${pageContext.request.contextPath}/ui/images/video_thumb.png" id="answerImageIncyyte"	style="display: block; float: left; margin: 10px 2px; width: 50px; height: 50px; border: 2px solid #ffffff; padding: 2px;" />
							<span><b>${ans.answerOption}</b><br> <img src="${pageContext.request.contextPath}/ui/images/video_thumb.png" /></span></a>
							<div style="display:none;">
								<div id="emailListYouTube_${ans.id}_answer" class="emailList " >
									 <iframe width='390' id="iFrameYouTube_${ans.id}_answer"  height='308'  src="${pageContext.request.contextPath}/ui/images/uploading_big_img.png" frameborder='0' type='text/html'></iframe>
								</div>
							</div>	
						</c:when>
                    </c:choose>
                </c:forEach>
            </td>
        </tr>
    </table>
</div>
<% } %>

<div class="expanded_incyyte" style="background:none;">
 <form:form  id="CommentsForm" name="CommentsForm" commandName="CommentsModel" method="post" >
<table width="100%" border="0" cellspacing="0" cellpadding="0">
<%if (StringUtils.equals("Y",cyyteChart.getIncyyte().getPollResultHidden()) && !incyyteUser.getUsername().equals(user.getUsername())){ %>
<tr>
    <td width="60%" valign="top">
            <div class="vote_confirm_bg">
    	        <div id="successMessage" class="vote_confirm_txt">
               <span>Hooray!</span> Thanks! We have now received your vote.
               </div>
             </div>
        <div id="containersexpanded" style="display:none ; height: 300px; margin-top:0px;">
        </div>
    </td>
</tr>
<%}else{ %>
<tr>
    <td width="60%" valign="top"><span class="font_16px">Poll Result</span>
        <div style='display:none'>
            <div id="view_anspics" >
                <table style="width: 100%;">
                    <c:forEach var="ans" items="${incyyte.answers}" varStatus="ansStatus">
                        <tr>
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
                                            <a style="cursor:pointer" href="${ans.uploadCDN_url}" class="thumbnail view_icon fancybox-popup "></a>
                                            <span><img src="${pageContext.request.contextPath}/ui/images/video_thumb.png" /></span>
                                            <%} else if(extAnsImages.contains(StringUtils.lowerCase(extAnswer))){ %>
                                            <a style="cursor:pointer" href="${ans.uploadCDN_url}" class="thumbnail view_icon fancybox-popup"></a>
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
                            <td style="width:95%;">
                                &nbsp;<label tabindex="${status.index}"  class="font_20px">${ans.answerOption}</label>
                            </td>
                        </tr>
                    </c:forEach>
                    <tr>
                        <td></td>
                        <td align="right">
                            <a	href="#" id="display_ans_btn" class="button_green" style="width:80px; float:right">
                                <span class="title_green"> Close </span>
                            </a>
                        </td>
                    </tr>
                </table>
            </div>
        </div>
        <div id="containersexpanded" style="height: 300px; margin-top:0px;"></div>
    </td>
</tr>
<tr>
    <td height="55">
        <table width="100%" border="0" cellspacing="0" 	cellpadding="0">
            <tr>
                <td><h3>Statistics Break Down</h3></td>
                <td><a href="javascript:void(0)"  onclick="submitReportAbuse(${chart.incyyte.id}, '')"><span id="report_abuse" class="report_abuse"><span></span>Report Abuse</span></a>
                </td>
            </tr>
        </table>
    </td>
</tr>
<tr>
    <td valign="top" style="border: 2px solid #fff;border-left: none;border-right:none;">
        <table width="100%" border="0" cellspacing="0" cellpadding="0" class="statistics_table">
            <tr>
                <td width="33%" valign="top" class="border_right"><p style="margin-bottom: 15px;" >
                    <span>Gender:</span> <a href="#" class="right">More</a>
                </p>
                    <table id="gender">
                        <tr>
                            <td height="40" width="30"><img	src="${pageContext.request.contextPath}/ui/images/male_gender.png"></td>
                            <td class="gender_rating">
                                <%if (cyyteChart.getGenderChart().get(Constants.GENDER_MALE) != null) {%>
                                <%=cyyteChart.getGenderChart().get(Constants.GENDER_MALE)%>%
                                <%}%>
                            </td>
                        </tr>
                        <tr>
                            <td><img src="${pageContext.request.contextPath}/ui/images/female_gender.png"></td>
                            <td class="gender_rating">
                                <%if(cyyteChart.getGenderChart().get(Constants.GENDER_FEMALE) != null) {%>
                                <%=cyyteChart.getGenderChart().get(Constants.GENDER_FEMALE)%>%
                                <%}%>
                            </td>
                        </tr>
                        <%if (cyyteChart.getGenderChart().get(Constants.GENDER_NOT_SURE) != null
                                && cyyteChart.getGenderChart().get(Constants.GENDER_NOT_SURE) > 0) {%>
                        <tr>
                            <td height="40" width="30" align="left">
                                <img src="${pageContext.request.contextPath}/ui/images/unspecified_gender.png" width="39" height="41" ></td>
                            <td class="gender_rating">
                                <%=cyyteChart.getGenderChart().get(Constants.GENDER_NOT_SURE)%>%
                            </td>
                        </tr>
                        <%}%>
                    </table>
                </td>
                <td width="34%" valign="top"><p style="margin-bottom: 15px;">
                    <span>Poll Count:</span> <a href="#" class="right">More</a>
                </p>
                    <!--p style="font-size: 14px;">Total Recipients - ${recipientsCount}</p-->
                    <p style="font-size: 14px;margin-top: 10px;">Total Responses - ${chart.incyyte.totalResponded}</p>
                    <!--p style="font-size: 14px;margin-top: 10px;">Total Unresponded - ${chart.incyyte.totalResponded}</p-->
                </td>

            </tr>
        </table>
    </td>
</tr>

<%}%>

<tr>
    <td valign="top">&nbsp;</td>
</tr>
<input type="hidden" name="code" id= "code" value="${code}" >
<%
    String openSection = (String) request.getParameter("openSection");
%>
<input type="hidden" id="viewSilverMemberOption" value="<%=openSection%>" />
<input type='hidden'  id="createdBy" value="<%=cyyteChart.getIncyyte().getCreatedBy()%>" />
<input type='hidden'  id="pageName" value="<%=cyyteChart.getIncyyte().getPageName()%>" />
<input type="hidden" name="questionid" id= "questionid" value="<%=cyyteChart.getIncyyteId()%>" >

<div id="commentsToggle">
    <tr>
        <td valign="top">
            <table width="100%" border="0" cellspacing="0" cellpadding="0" class="commentsToggle">
                <%
                    if (StringUtils.equals(user.getUserType(), "BUSINESS_SILVER")) {
                %>
                <tr align="right">
                <td colspan="2">
                        <c:set var="userIdFromIncyyte" value="${chart.incyyte.userId}"></c:set>
                        <c:set var="userId" value="<%=user.getId()%>"></c:set>
                        <c:choose>
                            <c:when test="${userIdFromIncyyte == userId}">
                                <div class="dash_sliver_mem">
                                    <a href="javascript:void(0)" id="hidecomments">View Silver Member Options</a>
                                </div>
                                <div class="silver_badge"></div>
                            </c:when>
                        </c:choose></td>
                </tr>
                <%
                    }
                %>
                <%
                    if (StringUtils.equals("Y", cyyteChart.getIncyyte()	.getAllowComment())
                            && StringUtils.equals("N",	userSettingsModel.getDisableComments())) {
                %>
                <tr>
                    <td width="42%" height="40" class="font_18px">
                        Add Your Comment:
                    </td>
                </tr>
                <tr>
                    <td colspan="2">
                        <textarea name="textarea" style="width: 585px;" id="textarea" class="add_comment_ie8" cols="45" rows="5" placeholder="Post Comment Text"></textarea>
                        <div id="commentErrorDiv" ><span id="error" style="padding-left: 12px; display:none;"></span></div><br>
                        <form:hidden path="comment" id="comment"  />
                        <form:hidden path="commentid" id="commentid" />
                        <form:input path="uploadCommentPhotoFile" type="file" id="uploadCommentPhotoFile" style="display:none;" />
                        <form:hidden path="uploadCommentType" id="uploadCommentType" />
                        <form:hidden path="searchedFileURLComment" id="searchedFileURLComment" />
                        <form:hidden path="searchedFileNameComment" id="searchedFileNameComment" />
                        <form:hidden path="youtubeCommentVideoId" id="youtubeCommentVideoId" />

                        <input type="hidden" id="incyyte_photo_search_file" />
                        <input type="hidden" id="incyyte_photo_search_url" />
                        <input type="hidden" id="googleSearchTrigger" />
                        <form:hidden path="quesid" id="quesid"  />
                        <input type="hidden" name="commentlst" id= "commentlst" value="${size}" >
                            <input type="hidden" id="videoIdForDelete" >
                    </td>
                </tr>
                <tr>
                    <td><a title="POST" id="addComment" class="button_green" style="width: 165px; float: left;" autofocus>
                        <span class="title_green share_poll_ie8">POST</span></a>
                    </td>
                    <td>
                        <c:if test="${sharedPoll == 'true'}">
	                        <c:if test="${chart.incyyteClosed == 'false'}">
	                            <a title="SHARE" id="displaySharedEmailList" onclick="displaySharedPopup('${chart.incyyte.incyyteCode}')" class="button_green" style="width: 165px; margin: 0 0 0 177px;" >
	                                <span class="title_green share_poll_ie8">SHARE THIS POLL</span>
	                            </a>
	                        </c:if>
                        </c:if>
                    </td>
                </tr>
                <%} else {%>
                <tr align="right">
                    <td>
                        <c:if test="${sharedPoll == 'true'}">
	                        <c:if test="${chart.incyyteClosed == 'false'}">
	                            <a title="SHARE" id="displaySharedEmailList" onclick="displaySharedPopup('${chart.incyyte.incyyteCode}')" class="button_green" style="width: 165px; margin: 0 0 0 177px;" >
	                                <span class="title_green share_poll_ie8">SHARE THIS POLL</span>
	                            </a>
	                        </c:if>
                        </c:if>
                    </td>
                </tr>
                <%}%>
            </table>
        </td>
    </tr>
</div>
<%
    if (StringUtils.equals("Y", cyyteChart.getIncyyte()	.getAllowComment())
            && StringUtils.equals("N",	userSettingsModel.getDisableComments())) {
%>
<tr class="commentsToggle">
    <td valign="top">&nbsp;</td>
</tr>
<tr class="commentsToggle">
    <td valign="top">&nbsp;</td>
</tr>

<tr class="commentsToggle">
    <td valign="top">
        <table width="100%" border="0" cellspacing="0" cellpadding="0">
            <tr>
                <td width="42%" height="40" class="font_18px">Comments:</td>
            </tr>
        </table>
    </td>
</tr>
<tr class="commentsToggle">
<td valign="top">
<table width="100%" border="0" cellspacing="0" cellpadding="0" class="comment_table">
<c:forEach items="${comments}" var="commentVar" varStatus="theCount">
<!-- Used to apply the alternate background colors for Comments-->
<c:choose>
    <c:when test="${theCount.count mod 2 == 0}">
        <tr class="commentsWhite">
    </c:when>
    <c:otherwise>
        <tr class="commentsBlank">
    </c:otherwise>
</c:choose>

<c:choose>
<c:when test="${not empty commentVar.userprofileimg}">
    <c:set var="commentProfileImage" value="${commentVar.userprofileimg}"/>
</c:when>
<c:otherwise>
    <c:set var="commentProfileImage" value="${pageContext.request.contextPath}/ui/images/default_avatar.png"/>
</c:otherwise>
</c:choose>
<%
        String cProfileImage =(String) pageContext.getAttribute("commentProfileImage");
%>
<td width="8%" valign="top">
    <div id="dropBoxContainer2${theCount.index}" class="dropBoxContainer2">

        <a href="#" id="dropBoxButton2${theCount.index}" class="dropBoxButton2">
            <img width="32" height="32" class="photoframe" src=<%=cProfileImage%>>
        </a>
        <div style="clear:both"></div>
        <div id="dropBox2${theCount.index}" class="dropBox2">
            <div id="dropBoxContent2${theCount.index}" class="dropBoxContent2">
                <ul>
                    <li><a href="javascript:sendFriendRequest('${commentVar.commentby}', '${theCount.index}');" style="color:black; font-size:14px;">Send Friend Request</a></li>
                </ul>
            </div>
        </div>
    </div>
</td>
<td width="92%">
    <p class="font_12px"><font style="font-weight:bold;">${commentVar.commentby}</font>,
        <strong>${commentVar.commentPeriod}</strong></p>
    <div id='message_<c:out value="${theCount.index}"/>' class="success_message" style="display:none;"></div>

    <c:set var="commentText" value="${fn:escapeXml(commentVar.comment)}"/>
    <%
        String cText = (String) pageContext.getAttribute("commentText");
        cText = cText.replaceAll("\r\n", "<br/>");
        cText = cText.replaceAll("\n", "<br/>");
        cText = cText.replaceAll("\t", "<br/>");
    %>

    <script type="text/javascript">
        $(document).ready(function () {
            var inputText = "<%=cText%>";
            var replaceText = linkify(inputText);
            $('#linkify_text_${theCount.index}').html(replaceText+".");
        });
    </script>
    <table width="100%">
        <tr><td align="left">
            <p class="commentText"><span id="linkify_text_${theCount.index}" class="font_16px"></span></p>
        </td></tr>
        <tr><td align="left">
            <c:choose>
                <c:when test="${not empty commentVar.commentPicture}">
                    <img alt="" src="${commentVar.commentPicture}" style="width:35em; height:18em">
                </c:when>
            </c:choose>
            <c:choose>
                <c:when test="${not empty commentVar.youtubeCommentVideoURL}">
                    <iframe id="commentsVideoSelectPlay"  style="width:35em; height:18em" class="upload_photos_container_big_img" src="${commentVar.youtubeCommentVideoURL}" frameborder='0' type='text/html'></iframe>
                </c:when>
            </c:choose>
        </td></tr>
        <tr><td align="left">
            <div id='comments_icons_<c:out value="${theCount.index}"/>'>
                &nbsp;&nbsp;<a href="javascript:sendFriendRequest('${commentVar.commentby}', '${theCount.index}');" title="Friend Request" ><img src="${pageContext.request.contextPath}/images/addcontact-icon.png" width="15" align="middle" height="15"  ></a>&nbsp;&nbsp;
                <a href="javascript:CommentIconAvailability(${theCount.count})" title="Send Message"><img src="${pageContext.request.contextPath}/images/reply-icon.png" width="15" align="middle" height="15" ></a>&nbsp;&nbsp;
                <c:set var="commentby"  value="${commentVar.commentby}"></c:set>
                <%
                    String userName = (String) pageContext.getAttribute("commentby");
                    if (StringUtils.equals(userName,user.getUsername())) {
                %>
                <c:choose>
                    <c:when test="${not empty commentVar.commentPicture}">
                        <a href="javascript:popupDeleteCommentPhoto('${commentVar.commentid}')" style="color: #ffffff" title="Delete Photo">
                            <img src="${pageContext.request.contextPath}/ui/images/camera_icon.png"  align="middle" > </a>
                    </c:when>
                    <c:when test="${not empty commentVar.youtubeCommentVideoURL}">
                        <a	href="javascript:popupDeleteCommentVideo('${commentVar.commentid}' , '${commentVar.youtubeCommentVideoURL}' )" style="color: #ffffff">
                            <img src="${pageContext.request.contextPath}/ui/images/camera_icon.png"  align="middle" > </a>
                    </c:when>
                    <c:otherwise>
                        <a href="javascript:processImageUpload('${commentVar.commentid}')" style="color: #ffffff" title="Upload Photo">
                            <img src="${pageContext.request.contextPath}/ui/images/camera_icon.png"  align="middle"  > </a>
                    </c:otherwise>
                </c:choose>
                <%}%>
                <div >
                    <span id='commenticonsuccess${theCount.count}' class="success_message" style="padding-left: 12px; display:none;"></span>
                </div>
            </div>
        </td></tr>
    </table>
</td>
</c:forEach>
</table>
</td>
</tr>
<% } %>
<tr>
    <td valign="top">&nbsp;</td>
</tr>
</table>
 
  </form:form>
  <!-- adding code for silvermember --> 
   <%
        String[] colors = new String[] {"#cfff00","#6ecafc","#c2002d","#1b303f","#a8dffd","#e2f4fe","#e2ff66","#f5ffcc","#da6681","#f3ccd5"} ;
        int i=0;
          %>
			<div class="SilverToggle" style="display: none;">
				<h1>
					Now you can reach out to your poll recipients! <a
						href="javascript:void(0)" id="hideSilvercomments">Hide Silver
						Member Options</a>
				</h1>
				<p>
					You can send promotional material, messages or videos to poll
					recipients based on how they <br> responded to you poll.
				</p>
				<c:forEach var="answer" items="${chart2.incyyte.answers}" varStatus="ansStatus">
					<div class="header">
						<span style="background: <%=colors[i]%>"></span>${answer.answerOption}
					</div>
					<%i++; %>
					<div class="rbox">
						<div class="cbox">
							<label id="checkboxLabel_${answer.id}" for="${answer.id}" onclick="myonclickhandlerPoll(this,${answer.id});"></label>
							<input type="checkbox" name="numbers[]" id="${answer.id}" value="${answer.id}"/>
						</div>
						<div class="rbox_bg">
							<div class="rbox_content">
								<div class="gender">
									<div class="icon">
												<c:choose>
													<c:when test="${answer.maleCount gt 0}">
														<img
															src="${pageContext.request.contextPath}/ui/images/male_icon.png">
												<span><a style="color: #000000;"
													href="javascript:displayMaleContacts(${answer.id})"><u>${answer.maleCount}</u></a></span>
													</c:when>
													<c:otherwise>
													<img
															src="${pageContext.request.contextPath}/ui/images/male_icon.png"><span>${answer.maleCount}</span>
													</c:otherwise>
												</c:choose>
											</div>

								</div>
								<div class="gender">
									<div class="icon">
												<c:choose>
													<c:when test="${answer.femaleCount gt 0}">
														<img
															src="${pageContext.request.contextPath}/ui/images/female_icon.png">
												<span><a style="color: #000000;"
													href="javascript:displayFemaleContacts(${answer.id})"><u>${answer.femaleCount}</u></a></span>
													</c:when>
													<c:otherwise>
														<img
															src="${pageContext.request.contextPath}/ui/images/female_icon.png">
														<span>${answer.femaleCount}</span>
													</c:otherwise>
												</c:choose>
											</div>

								</div>
								<div class="gender">
									<div class="icon">
												<c:choose>
													<c:when test="${answer.unspecifiedCount gt 0}">
														<img
															src="${pageContext.request.contextPath}/ui/images/unspecified_icon.png">
												<span><a style="color: #000000;"
													href="javascript:displayUnspecifiedContacts(${answer.id})"><u>${answer.unspecifiedCount}</u></a></span>
													</c:when>
													<c:otherwise>
														<img
															src="${pageContext.request.contextPath}/ui/images/unspecified_icon.png">
														<span>${answer.unspecifiedCount}</span>
													</c:otherwise>
												</c:choose>
											</div>

								</div>
								<div class="txt_Unspecified_Gender">&nbsp;&nbsp;&nbsp;
									Send promotional material or a message to this group</div>
							</div>
							
						</div>
					</div>
					<!-- ADD TABLE HERE -->
						<div id="maleEmailList${answer.id}" style="float: left; margin: 0px 0 0px 60px; border:solid 1px grey; width:540px; padding:10px; display:none;">
								<div id="contactScroll" style="width: 100%; height: 150px; overflow-x: hidden; overflow-y: auto;">
									<table id="mresponders<%=i%>" width="100%" border="0" cellspacing="0" cellpadding="0">
										<% int maleNonContactCount = 0; %>
										<tr style="padding: 2px 0 8px 0px;"><td><h3>Responded Male Contacts</h3></td></tr>													
										<c:forEach var="maleRecord"  items="${answer.maleRecord}" varStatus="ansStatus">
											<c:choose>
												<c:when test="${maleRecord.isContact == 'Y'}">
												<tr>
													<td>${maleRecord.email}</td>
												</tr>
												</c:when>
												<c:otherwise>
												<% maleNonContactCount++;%>	
												</c:otherwise>
											</c:choose>
										</c:forEach>
									<%if(maleNonContactCount != 0) { %>
										<tr>
											<td height="40"><h2>Including 1 or more contacts not in your contact list.</h2></td>
										</tr>
									<%}%>
										</table>
								</div>
								<input value="Export as CSV" type="button" onClick="javascript:downloadCSV('mresponders<%=i%>')" style="cursor:pointer; border:solid 1px grey; float: right;margin:10px 0px 0px 10px;">										
						</div>
						<div id="femaleEmailList${answer.id}" style="float: left; margin: 0px 0 0px 60px; border:solid 1px grey; width:540px; padding:10px; display:none;">
								<div id="contactScroll" style="width: 100%; height: 150px; overflow-x: hidden; overflow-y: auto;">
									<table id="fresponders<%=i%>" width="100%" border="0" cellspacing="0" cellpadding="0">
												<% int femaleNonContactCount = 0; %>
										<tr style="padding: 2px 0 8px 0px;"><td><h3>Responded Female Contacts</h3></td></tr>													
										<c:forEach var="femaleRecord" items="${answer.femaleRecord}" varStatus="ansStatus">
									<c:choose>
									<c:when test="${femaleRecord.isContact == 'Y'}">
											<tr>
												<td>${femaleRecord.email}</td>
											</tr>
										</c:when>
										<c:otherwise>
											<% femaleNonContactCount++;%>
										</c:otherwise>
										</c:choose>
										</c:forEach>
											<%if(femaleNonContactCount != 0 ) { %>
												<tr>
													<td height="40"><h2>Including 1 or more contacts not in your contact list.</h2></td>
												</tr>
											<%}%>
									 </table>
								</div>
								<input value="Export as CSV" type="button" onClick="javascript:downloadCSV('fresponders<%=i%>')" style="cursor:pointer; border:solid 1px grey; float: right;margin:10px 0px 0px 10px;">
						</div>
	
						<div id="unspecifiedEmailList${answer.id}" style="float: left; margin: 0px 0 0px 60px; border:solid 1px grey; width:540px; padding:10px; display:none;">
								<div id="contactScroll" style="width: 100%; height: 150px; overflow-x: hidden; overflow-y: auto;">
									<table id="unSpecresponders<%=i%>" width="100%" border="0" cellspacing="0" cellpadding="0">
												<% int unSpecifiedNonContactCount = 0; %>
										<tr style="padding: 2px 0 8px 0px;"><td><h3>Unspecified Contacts</h3></td></tr>													
										<c:forEach var="unSpecifiedRecord" items="${answer.unSpecifiedRecord}" varStatus="ansStatus">
													<c:choose>
														<c:when test="${unSpecifiedRecord.isContact == 'Y'}">
											<tr>
												<td>${unSpecifiedRecord.email}</td>
											</tr>
													</c:when>
														<c:otherwise>
														<% unSpecifiedNonContactCount++;%>	
														</c:otherwise>
													</c:choose>
												</c:forEach>
											<%if(unSpecifiedNonContactCount != 0 ) { %>
												<tr>
													<td height="40"><h2>Including 1 or more contacts not in your contact list.</h2></td>
												</tr>
											<%}%>
									</table>
								</div>
								<input value="Export as CSV" type="button" onClick="javascript:downloadCSV('unSpecresponders<%=i%>')" style="cursor:pointer; border:solid 1px grey; float: right;margin:10px 0px 0px 10px;">
						</div>
					</c:forEach>
						<input type='hidden' name="uncheckedlist" id="uncheckedlist"
					value="" />
					 
				 <form:form id="PollMesageForm" commandName="PollMesageForm" method="post">
					<% 	UserContactModel usercModel = (UserContactModel) session.getAttribute("PollMesageForm");%>
					<form:hidden path="checked" id="ed_checked" />
					<div class="contactPoll_recipients">
						<div class="contactPoll_recipients_bg">
							<h1>
								Contact your poll<span> Recipients</span>
							</h1>
							<form:textarea id="pollMessageContent" path="pollMessageContent"
								rows="" cols=""></form:textarea>
							<% if(usercModel == null || !StringUtils.isNotBlank(usercModel.getUploadedFileLocation())){%>
							<div class="adddoclink">
								<span>Attach: </span><a href="javascript:void(0)"
									style="color: #ffffff" id="openuploadwindow">Flyer/Image |
								</a><a href="javascript:void(0)" style="color: #ffffff"
									id="openDocUploadwindow">Document </a><a
									href="javascript:void(0)" style="color: #ffffff"
									id="openVideoUploadwindow"> | Video ad.</a>
							</div>
							<% } else {%>
							<div id="incyyte_media_view">
								<span>View file</span>
								<div id="media_small_icon" style="width: 65px;">
									<ul>
										<li id="view_small_icon"><a class='inline'
											href="#view_files" alt="View" title="View"></a></li>
									</ul>
								</div>
							</div>
							<%} %>
						</div>
						<div class="button_wrap1">
							<a href="javascript:sendPollMessage()" class="button_green1"
								id="pollmsg"
								style="width: 162px; float: right; margin-right: 32px;"><span
								class="title_green1">SEND MESSAGE</span></a>
						</div>
					</div>
					<!----------Add Videos Starts -------------------->
				<div style='display: none'>
						<div id="VideoModalWindow">
							<div id="modal_media_icon">
								<ul>
									<li id="modal_videos"><a href="javascript:void(0)"
										alt="Videos" title="Videos" class="active"></a></li>
									<li id="modal_photos"><a href="javascript:void(0)"
										alt="Photos" title="Photos"></a></li>
									<li id="modal_docs"><a href="javascript:void(0)"
										alt="Docs" title="Docs"></a></li>
								</ul>
							</div>
	<!----------Add Videos Starts -------------------->
							<div id="add_videos" class="c_add_videos">
								<table width="522" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td class="heading1" colspan="2">Add Videos</td>
									</tr>
									<tr>
										<td valign="top" align="left" width="50%">
											<table width="100%" border="0" cellspacing="0" cellpadding="0">
												<tr>
													<td>
														<div class="fileInputs">
															<div id="incyyte_browse_videos" class="button_gray"
																style="width: 140px; float: left; margin-bottom: 20px; position: absolute; top: 0px; left: 0px; z-index: 1;">
																<span class="title_gray">BROWSE</span>
															</div>
														</div>
													</td>
												</tr>
												<tr>
													
                       											 <td colspan="3">&nbsp;</td>
												</tr>
											</table>
										</td>

										<td align="center" valign="top" id="incyyte_video_not_loaded"
											width="50%">
											<table widh="100%">
												<tr>
													<td class="font_16px" align="left"><span
														style="float: left;">File Name:</span> <span
														style="word-wrap: break-word; float: left; width: 190px;"
														id="incyyte_video_file_name"></span></td>
												</tr>
												<tr>
													<td height="20px"></td>
												</tr>
												<tr valign="bottom" style="display: none;"
													id="incyyte_video_error_msg">
													<td><span class="errorLabel">Please upload the
															correct video format (flv, mp4, ..)</span></td>
												</tr>
												<tr valign="bottom" style="display: none;"
													id="incyyte_video_error_msg2">
													<td><span class="errorLabel">The uploaded video
															exceeds the maximum allowed size(5 MB)</span></td>
												</tr>
												<tr valign="bottom">
													<td align="center"
														style="position: absolute; z-index: 200; float: left; margin-left: 118px; margin-top: 119px;"><img
														src="${pageContext.request.contextPath}/ui/images/indicator-loader.gif" id="videoLoader"
														width="32" height="32"
														style="padding: 8px 0 0 0; margin-bottom: 20px 0 100px;"></td>
												</tr>
												</tr>
												<tr>
            <td>
                <div class="upload_photo_searchbox" style="margin-bottom:0">
                    <p class="sort_by_text">Search Google Videos</p>
                    
                    <div class="searchmain">
                        <div>
                            <input type="text" name="search" id="search_new_question_videos"  onKeydown="Javascript: if (event.keyCode==13 || event.which==13){$('#googleVideosScroll').html(''); makeYoutubeApiCall('search_new_question_videos' , 'start-index_promotion' , 'googleVideosScroll' , 'quesVideoSelect');}">
                            <input type="submit" id="searchSubmit_new_question_videos" value=""/>
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
                        <div id="googleVideosScroll"
                             style="width:319px; height:198px; overflow-x:hidden; overflow-y:auto; margin-right: 10px;">
                        </div>
                    </td>
                    <td align="right">
                        <div>
                       		 <iframe width='320' id="quesVideoSelect"  height='190' class="upload_photos_container_big_img" src="${pageContext.request.contextPath}/ui/images/uploading_big_img.png" frameborder='0' type='text/html'></iframe>
                        </div>
                    </td>
				</tr>
				</table>


										</td>
										<td align="center" valign="top" style="display: none;"
											id="incyyte_video_loaded" width="70%">
											<div id="#video_thumbs">
												<ul id="videos">
													<li><a id="addVideolink"
														href="${pageContext.request.contextPath}/ui/fancyplayer_code/videos/video01.flv"
														class="video_link"><img
															src="${pageContext.request.contextPath}/ui/images/dg_video_thumb.png" class="photos_thumb"
															alt="tour" /></a></li>
												</ul>
											</div>
										</td>
									</tr>
									<tr>
							               <div class="upload_photo_add_more"><a href="javascript:showMoreVideos('search_new_question_videos' , 'start-index_promotion' , 'googleVideosScroll', 'quesVideoSelect');"> + Show more Videos </a></div>
							        </tr>
									<tr>
										<td colspan="2" valign="bottom"><span class="licence">You
												must have the licence to use this Video</span>

											<div title="Upload Now" id="incyyteUploadVideoButton"
												class="button_red" style="width: 140px; float: right;">
												<span class="title_red1">UPLOAD NOW</span>
											</div></td>
									</tr>
								</table>
							</div>
						</div>
						<!------------- Add Videos end------------->
					
						<!----------Add docs Start-------------------->
						<div style="display: none;">
							<div id="ModalDocWindow">
								<div id="modal_media_icon2">
									<ul>
										<li id="modal_videos2"><a href="javascript:void(0)"
											alt="Videos" title="Videos"></a></li>
										<li id="modal_photos2"><a href="javascript:void(0)"
											alt="Photos" title="Photos"></a></li>
										<li id="modal_docs2"><a href="javascript:void(0)"
											alt="Docs" title="Docs" class="active"></a></li>
									</ul>
								</div>
								<div id="add_docs" class="c_add_docs">
									<table width="522" border="0" cellspacing="0" cellpadding="0">
										<tr>
											<td class="heading1" colspan="2">Add Docs</td>
										</tr>
										<tr>
											<td valign="top" align="left" width="50%">
												<table width="100%" border="0" cellspacing="0"
													cellpadding="0">
													<tr>
														<div class="fileInputs">
															<div id="incyyte_browse_docs" class="button_gray"
																style="width: 140px; float: left; margin-bottom: 20px; position: absolute; top: 0px; left: 0px; z-index: 1;">
																<span class="title_gray">BROWSE</span>
															</div>
														</div>
													</tr>
													<tr>
														<td>&nbsp;&nbsp;</td>
													</tr>
													<tr>
														<td>
															<div style="margin-top: 80px; float: left;"
																class="font_18px">
																Have you tried uploading<br> a photo to your poll?
															</div>
															<div
																style="float: left; margin-top: 20px; line-height: 20px;"
																class="font_16px">
																Hint: You can Upload images <br> clips or documents
																to <br> share on your poll <br> using the
																buttons <br> above.
															</div>
														</td>
													</tr>
												</table>
											</td>
											<td align="center" valign="top" id="incyyte_doc_not_loaded"
												width="50%">
												<table width="100%">
													<tr>
														<td align="left" class="font_16px"><span
															style="float: left;"> File Name:</span><span
															style="word-wrap: break-word; float: left; width: 190px;"
															id="incyyte_doc_file_name"></span></td>
													</tr>
													<tr valign="bottom" style="display: none;"
														id="incyyte_doc_error_msg">
														<td><span class="errorLabel">Please upload the
																correct document format (doc, pdf, ..)</span></td>
													</tr>
													<tr valign="bottom" style="display: none;"
														id="incyyte_doc_error_msg2">
														<td><span class="errorLabel">The uploaded
																document exceeds the maximum allowed size(2 MB)</span></td>
													</tr>
													<tr valign="bottom">
														<td align="center"
															style="position: absolute; z-index: 200; float: left; margin-left: 118px; margin-top: 119px;"><img
															src="${pageContext.request.contextPath}/ui/images/indicator-loader.gif" id="docLoader"
															width="32" height="32"
															style="padding: 8px 0 0 0; margin-bottom: 20px 0 100px;"></td>
													</tr>
													<tr>
														<td>&nbsp;</td>
													</tr>
													<tr>
														<td><div id="video_tumb_container">
																<img width="260" height="260" src="${pageContext.request.contextPath}/ui/images/photo1.png">
															</div></td>
													</tr>
												</table>

											</td>
											<td align="center" valign="top" id="incyyte_doc_loaded"
												style="display: none;" width="70%">
												<div id="#video_thumbs">
													<ul id="videos">
														<li><a id="addDoclink" href="#"> <img
																src="${pageContext.request.contextPath}/ui/images/view_docs_thumb.png" class="photos_thumb"
																alt="tour" /></a></li>
													</ul>
												</div>
											</td>
										</tr>
										<tr>
											<td colspan="2" valign="bottom">
												<div title="Upload Now" id="incyyteUploadDocButton"
													class="button_red" style="width: 140px; float: right;">
													<span class="title_red1">UPLOAD NOW</span>
												</div>
											</td>
										</tr>
									</table>
								</div>
							</div>
						</div>
						<!----------Add docs end-------------------->
					
					


					<% if(usercModel != null) {
%>
					<input type="hidden" id="name"
						value="<%=usercModel.getUploadedFileName()%>" />
					<input type="hidden" id="type"
						value="<%=usercModel.getUploadedType()%>" />
					<div style='display: none'>

						<div id="view_files">
							<div id="modal_media_icon">
								<ul>
									<li id="view_modal_videos"><a href="#" alt="Videos"
										title="Videos" class="active"></a></li>
									<li id="view_modal_photos"><a href="#" alt="Photos"
										title="Photos"></a></li>
									<li id="view_modal_docs"><a href="#" alt="Docs"
										title="Docs"></a></li>
								</ul>
							</div>

							<div>
								<!----------view Video Starts -------------------->
								<div id="view_videos" class="c_add_videos"
									style="width: 422px; height: 300px;">
									<table width="100%" border="0" cellspacing="0" cellpadding="0">
										<tr>
											<td class="heading1" colspan="2">View Videos</td>
										</tr>
										<tr>
											<td valign="top" align="left" width="30%">
												<table width="100%" border="0" cellspacing="0"
													cellpadding="0">
													<tr valign="top">
														<td class="font_18px"><br> <br>File Name:</td>
													</tr>
													<tr>
														<td class="font_16px">
															<div style="word-wrap: break-word; width: 160px;">
																<span id="view_video_file_name"></span>
															</div>
														</td>
													</tr>
													<tr>
														<td>&nbsp;</td>
													</tr>
												</table>
											</td>
											<td align="center" valign="top" width="70%">
												<div id="#view_video_thumbs">
													<ul id="videos">
														<li>
														
												
                                                
                                                <% if (usercModel != null && StringUtils.isNotBlank(usercModel.getUploadedFileLocation())
                                                		&& (usercModel.getUploadedFileLocation().length() ==  41) ) { %>
                                                		
													<a id="videoIdPromotion" >
                                   						 <img   id="viewYouTubeVideoIdPromotion" src="${pageContext.request.contextPath}/ui/images/video_thumb.png"  onClick="javascript:playYoutubeVideoForpromotion( 'videoIdPromotion' , '<%=usercModel.getUploadedFileLocation()%>');"  class="photos_thumb" alt="tour"/>
                                        					<div style="display:none;">
																<div id="emailListYouTubevideoIdPromotion" class="emailList " >
											    					<iframe width='390' id="iFrameYouTubevideoIdPromotion"  height='308'  src="${pageContext.request.contextPath}/ui/images/uploading_big_img.png" frameborder='0' type='text/html'></iframe>
											    				</div>
															</div>  		
														</a>
													<%} else { %>	
														<a onClick="javascript:playVideo('${chart.incyyte.id}','player${chart.incyyte.id}', '<%=usercModel.getUploadedFileLocation()%>')"><img
																class="photos_thumb" src="${pageContext.request.contextPath}/ui/images/video_thumb.png" /></a>
															<div style="display: none;">
																<div id="emailList${chart.incyyte.id}"
																	class="emailList ">
																	<a style="cursor: pointer"
																		id="player${chart.incyyte.id}"></a>
																</div>
															</div>	
													<%}%>
														
														
														</li>
													</ul>
												</div>
											</td>
										</tr>
										<tr>
											<td colspan="2" valign="bottom"><span class="licence">You
													must have the licence to use this Video</span>

												<div title="Delete" id="incyyteDeleteVideoButton"
													class="button_red" style="width: 140px; float: right;">
													<span class="title_red">DELETE</span>
												</div></td>
										</tr>
									</table>
								</div>
								<!------------- view Video end------------->
								<!----------View Photos Start-------------------->
								<div id="view_silvermember_photos" class="c_add_photos"
									style="width: 422px; height: 300px;">
									<table width="100%" border="0" cellspacing="0" cellpadding="0">
										<tr>
											<td class="heading1" colspan="2">View Photos</td>
										</tr>
										<tr>
											<td valign="top" align="left" width="30%">
												<table width="100%" border="0" cellspacing="0"
													cellpadding="0">
													<tr valign="top">
														<td class="font_18px"><br> <br>File Name:</td>
													</tr>
													<tr>
														<td class="font_16px">
															<div style="word-wrap: break-word; width: 160px;">
																<span id="view_photo_file_name"></span>
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
														<li><a id="photolinkId"
															href="<%=usercModel.getUploadedFileLocation()%>"
															target="_blank" class="group1 fancybox-popup"> <img
																id="photoImg"
																src="<%=usercModel.getUploadedFileLocation()%>"
																class="photos_thumb" alt="tour" />
														</a></li>
													</ul>
												</div>
											</td>
										</tr>
										<tr>
											<td colspan="2" valign="bottom"><span class="licence">You
													must have the licence to use this image</span>

												<div title="Delete" id="incyyteDeletePhotoButton"
													class="button_red" style="width: 140px; float: right;">
													<span class="title_red">DELETE</span>
												</div></td>
										</tr>
									</table>
								</div>
								<!----------View Photos end--------------------> 
								<!----------View docs Start-------------------->
								<div id="view_docs" class="c_add_docs"
									style="width: 422px; height: 300px;">
									<table width="100%" border="0" cellspacing="0" cellpadding="0">
										<tr>
											<td class="heading1" colspan="2">View Docs</td>
										</tr>
										<tr>
											<td valign="top" align="left" width="30%">
												<table width="100%" border="0" cellspacing="0"
													cellpadding="0">
													<tr valign="top">
														<td class="font_18px"><br>
														<br>File Name:</td>
													</tr>
													<tr>
														<td class="font_16px">
															<div style="word-wrap: break-word; width: 160px;">
																<span id="view_doc_file_name"></span>
															</div>
														</td>
													</tr>
													<tr>
														<td>&nbsp;</td>
													</tr>
												</table>
											</td>
											<td align="center" valign="top" width="70%"><c:set
													var="quesUrl"
													value="<%=usercModel.getUploadedFileLocation() %>" /> <%
                                if (usercModel.getUploadedFileLocation() != null) {
                                    String name = usercModel.getFileName();
                                    String extension = "";
                                    int ii = name.lastIndexOf('.');
                                    if (ii > 0) {
                                        extension = name.substring(ii + 1);
                                    }
                                    String docs[] = {"wpd", "wps", "xml", "xlr", "pdf"};
                                    String gooleDocs[] = {"doc", "docx", "log", "rtf", "txt", "csv", "pps", "ppt", "xls", "xlsx"};
                                    List<String> extDocs = Arrays.asList(docs);
                                    List<String> extGoogleDocs = Arrays.asList(gooleDocs);
                                    String docUrl = "https://docs.google.com/viewer?url=";
                                    String url = (String) pageContext.getAttribute("quesUrl");
                                    String viewUrl = docUrl + url; %> <%if (extDocs.contains(extension)) {%>
												<div id="#view_doc_thumbs">
													<ul id="videos">
														<li><a id="doclinkId" style="cursor: pointer"
															href='<%=viewUrl%>'
															onClick="window.open('<%=url%>','MyWindow'); return false;"
															target="_blank"><img
																src="${pageContext.request.contextPath}/ui/images/view_docs_thumb.png" class="photos_thumb"
																alt="tour" /></a></li>
													</ul>
												</div> <% }
                                if (extGoogleDocs.contains(extension)) { %>
												<ul id="videos">
													<li><a id="doclinkId" style="cursor: pointer" href=""
														onClick="MyWindow=window.open('<%=viewUrl%>','MyWindow'); return false;"
														target="_blank"><img
															src="${pageContext.request.contextPath}/ui/images/view_docs_thumb.png" class="photos_thumb"
															alt="tour" /></a></li>
												</ul> <%
                                    }
                                }
                            %></td>
										</tr>
										<tr>
											<td colspan="2" valign="bottom">
												<div title="Delete" id="incyyteDeleteDocButton"
													class="button_red" style="width: 140px; float: right;">
													<span class="title_red">DELETE</span>
												</div>
											</td>
										</tr>
									</table>
								</div>
								<!----------View docs end-------------------->
								  
								
							</div>
						</div>
					</div>
					<%} %>
					<form:input path="uploadedVideoFile" type="file" id="incyyte_video_file_input" style="display: none;" />
					<form:input path="uploadedDocFile" type="file" id="incyyte_doc_file_input" style="display:none;" />
					<form:input path="uploadedPhotoFile" type="file" id="incyyte_photo_file_input" style="display:none;" />
					<input type="hidden" id="incyyte_photo_search_file" />
					<input type="hidden" id="incyyte_photo_search_url" />
					<form:hidden path="uploadedType" id="uploadedType" />
					<form:hidden path="fileName" id="file_name_tst" />
					<form:hidden path="searchedFileURL" id="searchedFileURL" />
					<form:hidden path="searchedFileName" id="searchedFileName" />
					<form:hidden path="youTubeVideoIdPromotion" id="youTubeVideoIdPromotion" />
					<input type="hidden"  id="start-index" value="1"/>
					<input type="hidden"  id="start-index_promotion" value="1"/>
				</form:form> 
			</div>
  <!-- ending code for silver member -->
<jsp:include page="/WEB-INF/jsp/include/shareEmailPopup.jsp" />

<form:form  id="friendRequest"  commandName="CommentsModel" method="post" >  			
			<form:hidden path="commentby" id="commentby"  />
</form:form>

<%} %>
<%-- </form:form> --%>
</td>
</tr>
</table>
</div>