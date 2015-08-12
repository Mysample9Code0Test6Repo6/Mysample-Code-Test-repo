<%@ include file="/WEB-INF/jsp/common/include.jsp" %>

<%@page import="com.incyyte.app.web.SessionKeys"%>
<%@page import="com.incyyte.app.domain.User"%>
<%@page import="org.apache.commons.lang.StringUtils"%>
<%@page import=" com.incyyte.app.domain.BusinessAccount"%>

<% User user = (User)request.getSession().getAttribute(SessionKeys.LOGIN_USER); %>

<c:if test="${not empty user.postalCodeArea}">	                 						                 						
	<c:set var="pRegion" value="${fn:substringBefore(user.postalCodeArea,' ')}" />
</c:if>

<a href="ask_question.cyt" id="hints" class="button_red_dash_page" style="margin-bottom:30px; width:218px;"> <span class="dash_details_red dash_details_red_ie8">Get inCyyte !</span></a>

<nav>
	<ul id='left_mainmenu' class='accordion'>
        <% String incomingCount = (session.getAttribute("incomeCount") == null ? "-" : String.valueOf(session.getAttribute("incomeCount")));
        String sentCount = (session.getAttribute("sentCount") == null ? "-" : String.valueOf(session.getAttribute("sentCount")));
        String pollCount = (session.getAttribute("pollCount") == null ? "-" : String.valueOf(session.getAttribute("pollCount")));
        String regionCount = (session.getAttribute("regionCount") == null ? "-" : String.valueOf(session.getAttribute("regionCount")));  %>

		<li><a id="incomming_link" href='dashincomming.cyt'>New inCyytes <span><%=incomingCount%></span></a></li>
		<li><a id="sent_link" href='dashsent.cyt'>Sent inCyytes <span><%=sentCount%></span></a></li>
		<li><a id="post_link" href='dashpost.cyt'>My inCyyte Poll Pages <span><%=pollCount%></span>	</a></li>
		<li><a id="region_link" href='dashregion.cyt'>Polls in My Region (${pRegion}) <span><%=regionCount%></span>	</a></li>
		<li><a id="friends_link" href='pollSetup.cyt'>Setup your Poll Page<span style='display: none'></span></a></li>
		<li	style="font: 14px 'bariol_regularregular', 'ie8_bariol_regular'; margin-left: 10px;">Show Archive Polls
		 <c:choose>
		   <c:when test="${showArchivedPolls == 'true'}">
			 <input style="margin-left: 100px;" type="checkbox" id="archive_poll" checked/>
		   </c:when>
		  <c:otherwise>
			<input style="margin-left: 100px;" type="checkbox" id="archive_poll" />
		  </c:otherwise>
		 </c:choose> 
		</li>

		<%
		if(StringUtils.equals(user.getUserType(), "BUSINESS")){
		%>
		<!--li ><a id="business_link"  href='#'>inCyyte for Business<span style='display:none'></span></a></li-->
		<%} if(StringUtils.equals(user.getUserType(), "USER")){
		%>
		<!--li ><a id="business_link" href='#'>inCyyte for Business<span style='display:none'></span></a></li-->
		<%} %>
		
	</ul>
</nav>
<div class="hline"></div>
<jsp:include page="../include/profileOverview.jsp" />
<br>
<h1 class="font_18px">Fill In your:</h1>
<ul class="redtext">
  	<li> <span> >> </span> <a href="./editProfile.cyt">Profile Picture</a> </li>
	<li> <span> >> </span> <a href="./editProfile.cyt">Profile Description</a></li>
</ul>

<div id="advert">
	<img src="ui/images/advert.png" alt="Advert">
</div>
<script type="text/javascript">
$(document).ready(function () {
$('#archive_poll').change(function () {
    if ($(this).is(':checked')) {
    	var flag = true;
		window.location = './dashincomming.cyt?flag=' + flag; 
    } else {
    	$('#archive_poll').attr("checked", false);
    	window.location = './dashincomming.cyt';
    	}
    });
});
</script>
