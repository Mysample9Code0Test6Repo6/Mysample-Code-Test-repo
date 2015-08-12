<%@ include file="/WEB-INF/jsp/common/include.jsp" %>
<%@page import="com.incyyte.app.web.SessionKeys"%>
<%@ page import="org.apache.commons.lang.StringUtils" %>
<%@page import="com.incyyte.app.domain.User"%>
<script>
 $(document).ready(function () {
  getUnreadCount();
});
 
 $(function () {
	    var button = $('#user_setting');
	    var box = $('#usersettingBox');
	    var form = $('#usersettingForm');
	    button.removeAttr('href');
	    button.mouseup(function (login) {
	        box.toggle();
	        button.toggleClass('active');
	    });
	    form.mouseup(function () {
	        return false;
	    });
	    $(this).mouseup(function (login) {
	        if (!($(login.target).parent('#user_setting').length > 0)) {
	            button.removeClass('active');
	            box.hide();
	        }
	    });
	});

function getUnreadCount(){
    var path = "${pageContext.request.contextPath}" + "/";
    $.ajax({
        type:"POST",
        url: path + "unreadCount.cyt",
        success:function (data) {
        if (data > 0) {
            $("#count").text(data);
        	$("#count").css("display","");
        } else {
        	$("#count").css("display","none");
        }
}});
}
</script>

<link href="${pageContext.request.contextPath}/ui/css/style.css" media="screen" rel="stylesheet" type="text/css" />
<!--[if lte IE 8]>
<script src="ui/js/html5.js"></script>
<link href="${pageContext.request.contextPath}/ui/css/ie8.css" media="screen" rel="stylesheet" type="text/css" />
<![endif]-->
<!--[if IE 9]>
<link href="${pageContext.request.contextPath}/ui/css/gte_ie9.css" media="screen" rel="stylesheet" type="text/css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/ui/css/ie9.css"/>
<![endif]-->
<script>
    if (/*@cc_on!@*/false) {
        var headHTML = document.getElementsByTagName('head')[0].innerHTML;
        headHTML    += '<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/ui/css/ie10.css">';
        document.getElementsByTagName('head')[0].innerHTML = headHTML;
    }
</script>

<link rel="shortcut icon" href="${pageContext.request.contextPath}/ui/images/favicon.ico" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/ui/css/accordionmenu.css" type="text/css">
<% User user = (User)request.getSession().getAttribute(SessionKeys.LOGIN_USER); %>

<c:if test="${not empty user.postalCodeArea}">	                 						                 						
	<c:set var="pRegion" value="${fn:substringBefore(user.postalCodeArea,' ')}" />
</c:if>

<!--header -->
    <body>
    <div class="header36" >
      <div id="headerinner" >
        <div id="logocontainer"><a href="${pageContext.request.contextPath}/dash.cyt" id="logo_inner">inCyyte</a></div>
        <div id="header_topnav_inner">
          <ul id="header_topmenu">
            <li><a id="header.dashboard" href="${pageContext.request.contextPath}/dash.cyt">Dashboard</a></li>
            <li><a id="header.messages" href="${pageContext.request.contextPath}/messages.cyt">Messages<span id="count" style="display:none; color:#fff; background: #c2002d  20px 20px no-repeat; border-radius:15px; font:bold 10px/18px 'bariol_boldbold', 'ie8_bariol_bold'; padding:0px 5px; margin:-10px 10px 0 0; right:-8px;top:0; z-index:99; position:absolute;" ></span></a></li>
            <li><a id="header.contacts" href="${pageContext.request.contextPath}/contactsHome.cyt" >Contacts</a></li>
            <li class="last"><a id="header.groups" href="${pageContext.request.contextPath}/grouphome.cyt" >Groups</a></li>
            <li style="padding-right:14px;">
              <form id="searchform_top">
                <input type='text' name="top_search" id="top_search"  placeholder="Search for people etc." onFocus="this.placeholder = ''" onBlur="this.placeholder = 'Search for people etc.'" disabled>
                <input type="submit" id="searchSubmit_top" value="" />
              </form>
            </li>
            <!-- User settings starts Here -->
            <li id="usersettingContainer" class="last"> <a href="#" id="user_setting"><span>Settings</span><em></em></a>
              <div style="clear:both"></div>
                <div id="usersettingBox">
                  <div id="usersettingForm" >
                     <p>You are in the <u title="Your polls are currently restricted to this region, click to&#13;upgrade your account and send polls outside ${pRegion} region.">${pRegion}</u> InCyyte Region </p>
                      <p style="padding-bottom:8px;">Rating: MicroPhone </p>
				    <div id="fixed"><p class="more_rating_details"><a href="#" title="AVAILABLE SOON">More Rating Details</a></p></div>
                    <p class="hline_setting"></p>
					<ul>
					<li><a id="header.settings.myAccount" href="#" title="AVAILABLE SOON">My Account</a></li>
                    <li><a id="header.settings.editProfile" href="${pageContext.request.contextPath}/editProfile.cyt">Edit Profile</a></li>
                    <li><a id="header.settings.editSettings" href="${pageContext.request.contextPath}/editSettings.cyt">Edit Settings</a></li>
					</ul>
                    <p class="hline_setting" style="margin-top: 10px;"></p>
                      <div class="incyyte_businesslink">
                       <% if(user != null && StringUtils.equals(user.getUserType(), "USER" )) { %>
                          <a href="${pageContext.request.contextPath}/incyyteBusiness.cyt">inCyyte Basic (Upgrade)</a>
                        <%}else if (user != null && StringUtils.equals(user.getUserType(), "BUSINESS_SILVER" )) { %>
                         <a href="${pageContext.request.contextPath}/incyyteBusiness.cyt">inCyyte Silver Member (Upgrade)</a>
                          <%}%>
                      </div>
                  </div>
                </div>
            </li>
            <!-- User User settings Ends Here -->
            
           	<c:choose>
	           	<c:when test="${not empty admin_user}">
	           		<c:url  value="/admindash.cyt" var="logoutURL" />
	           	</c:when>
	           	<c:otherwise>
	           		<c:url  value="/logout.cyt" var="logoutURL" />
	           	</c:otherwise>           		
           	</c:choose> 	
          
            <li class="last"><a  id="header.logout" href='<c:out value="${logoutURL}" />'>Log Out</a></li>
            
          </ul>
        </div>
		<!--More Incyyte rating Start---->
		<div style="display:none;">
		<div id="more_incyyte_rating" >
		<h3>My InCyyte Rating</h3>
		<table width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td width="17%" height="85"><img src="${pageContext.request.contextPath}/ui/images/profile_picture_rating.png" alt="Profile Picture"></td>
    <td width="34%"><p>User Name</p>
	<p style="padding-bottom:10px;">Rating: MicroPhone</p>
	<div id="fixed"></div>
	</td>
	<td width="49%" align="right"><p>Recipient response rate:</p>
	<p class="rating_per">85%</p>
	</td>
  </tr>
  <tr>
    <td colspan="3" style="border-bottom:1px solid #fff;"></td>
  </tr>
  <tr>
    <td colspan="3" height="40">Member Since .......</td>
  </tr>
    <tr>
    <td colspan="3"><table width="100%" border="0" cellspacing="0" cellpadding="0" class="rating_detail_table">
  <tr>
    <td>InCyyte Polls</td>
    <td>Recipients</td>
    <td>Responses</td>
    <td>Regions</td>
  </tr>
  <tr class="alt">
    <td>150</td>
    <td>10,132</td>
    <td>8,496</td>
    <td>19</td>
  </tr>
</table>
</td>
  </tr>
</table>

		</div>
		</div>
		<!-- More Incyyte rating Start---->
		<!-- Welcome User Start---->
		<div id="welcome_user">
      		<div id="userphoto">
      			<%if (user==null || user.getProfilePicture() == null || user.getProfilePicture().equals("")){ %>
      				<img src="${pageContext.request.contextPath}/ui/images/user_photo.png">
      			<%}else{ %>
      				<a href="./editProfile.cyt"><img src="${user.profilePicture}" width="24" height="24" alt="User Photo" /></a>
      			<%}%>
      		</div>
      		<p class="username">Welcome ${user.username}
      			<c:choose>
      				<c:when test="${not empty user.postalCodeArea}">
      					<span>
      						you are in the ${pRegion} region.
      					</span>
      				</c:when>
      				<c:otherwise>
      					
      				</c:otherwise>
      			</c:choose> 
	      	</p>
          	<p class="view_polls"><a href="./dashregion.cyt" title="View polls in your region">Click here to view polls in your region.</a></p>
        </div>
		<!-- Welcome User End---->
      </div>
    </div>
    </body>
    <!--header end-->
<%--<jsp:include page="../../main/includes/cookies.jsp" />--%>
