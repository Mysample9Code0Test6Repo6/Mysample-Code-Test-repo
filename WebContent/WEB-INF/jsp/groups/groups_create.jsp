<!DOCTYPE html>
<%@ include file="/WEB-INF/jsp/common/include.jsp" %>

<html lang="en">
<head>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">
    <link rel="shortcut icon" href="favicon.ico" />

    <title>InCyyte - Create a Group</title>
    <meta charset="utf-8">
    <link rel="stylesheet" href="ui/css/reset.css">
    <link rel="stylesheet" href="ui/css/layout.css">
    <link rel="stylesheet" href="ui/css/buttons.css"  media="screen">
    <link rel="stylesheet" href="ui/css/style_login.css">
    <link rel="stylesheet" href="ui/css/accordionmenu.css" type="text/css">
    <link rel="stylesheet" href="ui/css/icons_sprites.css"/>
    <link rel="stylesheet" href="fonts/fonts_stylesheet.css"/>
    <link rel="stylesheet" href="ui/css/style_help.css">
    <link type="text/css" rel="stylesheet" href="ui/css/scrollbar.css">

    <script src="ui/js/jquery-1.8.3.min.js"></script>
    <script src="ui/js/jquery-1.4.2.min.js"></script>
    <script src="ui/js/search_script.js"></script>
    <script src="ui/js/jquery.color.js"></script>
    <!-- Left Navigation script starts here -->
    <script src="ui/js/left_menu.js"></script>
    <!-- Left Navigation script ends here -->
    <link rel="stylesheet" href="ui/css/sparkbox-select.css">
    <script src="ui/js/modernizr-1.7.min.js"></script>
    <script src="ui/js/search_script.js"></script>
<script src="ui/js/jquery.sparkbox-select.js"></script>
<script src="ui/js/jquery_profile_rating.js" type="text/javascript"></script>
<script src="ui/js/star_rating.js" type="text/javascript"></script>
<script>
$(document).ready(function() {
  $('.sparkbox-custom').sbCustomSelect({
    appendTo: 'body'
  });
});
</script>
<script type="text/javascript" src="ui/js/cufon.js"></script>
<script type="text/javascript" src="ui/js/scollbar.js"></script>
    <script type="text/javascript" src="ui/js/jquery-1.5.1.min.js"></script>
    <script type="text/javascript" src="/ui/js/group.js"></script>
    <script type="text/javascript" src="ui/js/jquery.form.js"></script>
<script type="text/javascript" src="ui/js/jquery.tinyscrollbar.min.js"></script>
<script type="text/javascript" src="ui/js/IE-placeholder.js"></script>
    <!--- placeholder Starts----->
      <script type="text/javascript" src="${pageContext.request.contextPath}/js/json_parse.js"></script>
    <script type="text/javascript">
        $(document).ready(function(){
            $("#help a").append("<em></em>");
            $("#help a").hover(function() {
                $(this).find("em").animate({opacity: "show", top: "35"}, "fast");
                var hoverText = $(this).attr("title");
                $(this).find("em").text(hoverText);
            }, function() {
                $(this).find("em").animate({opacity: "hide", top: "50"}, "fast");
            });
        });

function getXmlHttpRequestObject() {
	if (window.XMLHttpRequest) {
		return new XMLHttpRequest();
	} else if (window.ActiveXObject) {
		return new ActiveXObject("Microsoft.XMLHTTP");
	} else {
		alert("Your Browser Doesn't support AJAX!\\n Please upgrade your browser");
	}
}

var httpRequest = getXmlHttpRequestObject();

function yHandler12() {
	$('#create_group').hide();
	var totalDiv = document.getElementById('gradient');
	var totalHeight = totalDiv.offsetHeight;
	var scrolledHeight = window.pageYOffset;
	var y = scrolledHeight + window.innerHeight;

	var pageNumber = $('#pageNum').val();     
	var param = $('#param').val();
	var search = $('#searchValue').val();
		pageNumber++;
		$('#pageNum').val(pageNumber);
		httpRequest.open("GET", 'getLoadedContacts.cyt?pageNumber=' + pageNumber + '&param='+param  + '&search='+ search, true);
		httpRequest.onreadystatechange = getContactsAfterScroll;
		httpRequest.send(null);
}
 
 function getContactsAfterScroll() {
	 if (httpRequest.readyState == 4 || httpRequest.readyState == 200 ) {
	 var resultText1 = httpRequest.responseText;
     var resultsToDecideEndPoint = json_parse(resultText1);
     var endOfFile = resultsToDecideEndPoint.endOfFile;
    var displayEndOfResult = $('#endOfFile').val();
    var currentContactList = document.getElementById('group_contacts123');
    if(endOfFile == "FALSE") {
		var resultText = httpRequest.responseText;
		var contacts = json_parse(resultText);
		var contactList = contacts.contacts;
		for (var i = 0; i < contactList.length; i++) {
			 currentContactList.innerHTML += "<tr>"
	                       +'<td style="padding-left:10px;padding-right:10px;">'
	                       +   '<fieldset id="group_1">'
	                       +   		'<input type="checkbox" id="contactId_'+contactList[i].contactId+'" value="'+contactList[i].contactId+'" onclick="javascript:submitContact('+contactList[i].contactId+',$(this).attr(checked));"/>'
	                       + '</fieldset>'
	                       + '</td>'
	                       +  ' <td height="40" style="padding-right:10px;"><img src='+"'"+contactList[i].profileImgUrl+"'"+' width="36" height="35"  alt="User Photo"></td>'
	                       +  '<td >'+contactList[i].contactEmail+'</td>'     
	                       +   '<td>'+contactList[i].username+'</td>'
					       + '</tr>';
		}
		$('#create_group').show();
    } else if ((endOfFile == "TRUE") && (displayEndOfResult == "SHOW")) {
    	$('#create_group').hide();
    	currentContactList.innerHTML += "<tr>"
		+ '<td width="5%" valign="top">'
		+ '</td>'
		+ '<td width="12%"></td> '
		+ '<td width="64%">'
		+ '<p style="font-family: bariol_boldbold, ie8_bariol_bold; font-size: 14px;"> End Of Contact List  '
		+ '</p></td>'
		+ '<td width="19%" align="right"></td>'
		+ '</tr>';
		$('#endOfFile').val('STOP');
    }
    }
 }
    </script>

    <!--- placeholder Ends----->
</head>
<%--<script type="text/javascript">
    $(window).load(function() {
        $(".loader").fadeOut("slow");
    })
</script>--%>
<body onload="getUnreadCount();">
<%--
<div class="loader" style="background: url('${pageContext.request.contextPath}/ui/images/common/long_loading_bar.gif') 50% 50% no-repeat rgb(249,249,249)"></div>
--%>

<input type='hidden' id="pageNum" value="1"/>
<%
String parameter = request.getParameter("param");
String searchValue = request.getParameter("search");
%>
<input type='hidden' id="param" value="<%=parameter%>" />
<input type='hidden' id="searchValue" value="<%=searchValue%>" />
<input type='hidden' id="endOfFile" value="SHOW"/>

<div id="gradient" >
  <div class="extra" >
     <!-- include header -->
    <jsp:include page="../common/includes/header.jsp"  />
          <form:form  id="group"  commandName="group" method="post">
          	<form:hidden path="groupId" id="groupId"   />
          	<form:hidden path="checked" id="ed_checked"   />
    <div class="main">
      <!--content -->
      <article id="content">
        <div id="main_content" >
          <div class="grid_9">
            <h1>Groups</h1>
            <nav>
              <script type="text/javascript" src="ui/includes/leftmenu_groups.js"></script>
            </nav>
            <div class="hline"></div>
            <div id="advert"><img src="ui/images/advert.png" alt="Advert"></div>
          </div>
          <div class="line"><span></span></div>
            <span id="groupError" class="none" style="padding-left:24px; color:#ec3f41; font-size: 16px;"></span><br>
            <div class="grid_17">
            <!--- Select a Group Starts---->
            <div id="create_a_group">
              <h3 class="heading3" style="padding:0 0 40px 0">1. Create a Group</h3>
              <div id="help"><a href="#" style="z-index: 500" ><em style="z-index:99" class="arrow_box1">
                <p class="helpmodal">Creating new Groups</p>
                Create emails groups of friends, family or co-workers for easy polling. When you create a new group it will appear for selection each time you create a new poll. This saves you writing multiple email addresses. You can create an unlimited number of groups.</em></a></div>
              <p class="heading2" style="margin-top:10px;">Group Name</p>
                <form:input path="groupName"  type='text' name="groupName" id="groupName" value="${group.groupName}" maxlength="50"  placeholder="Enter Group Name" onFocus="this.placeholder = ''" onBlur="this.placeholder = 'Enter Group Name'" />
                <p class="heading2" style="margin-top:22px;">Description</p>
          <form:textarea path="description" name="description" id="description" cols="" rows="" style="resize: none;"  placeholder="Group Description goes here" onFocus="this.placeholder = ''" onBlur="this.placeholder = 'Group Description goes here'" value="${group.description}"/>
            </div>
            <!--- Select a Group Ends---->
            <!--- Add Contacts to Group Starts---->
            <div id="add_contacts_to_group" >
              <h3 class="heading3" style="padding:0 0 29px 28px;">2. Add Contacts to Group</h3>
              <div id="help"><a href="#" ><em style="z-index:99" class="arrow_box1">
                <p class="helpmodal">Who's in a group?</p>
                Choose who goes in each of your groups from your contact list below. Only individuals in this list can be added to a group. Increase your contacts list by inviting individuals to inCyyte or by importing new contacts from your social network by clicking on the Contacts link on the top menubar. </em></a></div>
              <!------ Search Box Starts -------->
              <div id="advanced_searchbox">
                <div id="searchbox" style="margin-bottom:0">
                  <p class="sort_by_text"></p>
                  <div id="searchmain" >
                    <p class="search_heading">Search</p>
                      <fieldset>
                      <div class="input">
                        <input type='text' name="search" id="search" value="Enter your search" />
                      </div>
                      <input type="submit" id="searchSubmit" value=""/>
                      </fieldset>
                  </div>
                </div>
                <nav id="alpha_list">
                    <c:forEach var="page" items="A,B,C,D,E,F,G,H,I,J,K,L,M,N,O,P,Q,R,S,T,U,V,W,X,Y,Z">
                        <li><a href="javascript:submitGroupCreatePagination('${page}')">${page}</a></li>
                    </c:forEach>
              </nav>
              </div>
              <!------ Search Box End-------->
              <!-- Search Result Start -->
              <div id="group_contacts">
                      <table width="400" border="0" cellspacing="0" cellpadding="0" id="contact_list">
	                      <tr>
	                  		<td colspan="12" class="select_all">Select: <a rel="group_1" href="javascript:checked('#selectall');" >All</a> | <a rel="group_1" href="javascript:checked('#select_none');">None</a></td>                  
	                	  </tr>
		     		   </table>
		     			<br>
			     	   <div id="contactScroll" style="width:400px; height:200px; overflow:auto;">
			     		<table  id="group_contacts123" width="350" border="0" cellspacing="0" cellpadding="0">
                      	<c:forEach items="${UserContactlist}" var="UserContactModel">
	                        <tr>
	                        <td style="padding-left:10px;padding-right:10px;">
	                          <fieldset id="group_1">
	                          		<form:checkbox path="selectedGroupContactsList" id="contactId_${UserContactModel.contactid}" value="${UserContactModel.contactid}" onclick="javascript:submitContact('${UserContactModel.contactid}',$(this).attr('checked'));"/>
	                        </fieldset>
	                        </td>
	                          <td height="40" style="padding-right:10px;"><img src='${UserContactModel.profile_img}' width="36" height="35"  alt="User Photo"></td>
	                        
	                         <td >${UserContactModel.email}</td>     
	                          <td>${UserContactModel.username}</td>
                        </tr>
                      	</c:forEach>                     
                      	</table>
                      <c:choose>
		               <c:when test="${groupSize >= 10}">
                      	 <table width="20%" border="0" cellpadding="0" cellspacing="0">
				            <tr style="padding-right: 300px;">
					          <td align="middle" style="padding-left: 230px;" >
						          <div><input value="Display more contacts" type="button" onClick="javascript:yHandler12()" style="cursor: pointer;border: solid 1px grey;" id="create_group"></div>
					         </td>
					      </tr>
				      </table>
				   </c:when>
		        </c:choose>     
                 </div>
              </div>
                <!-- Search result Ends-->
  <div id="add_selected_contacts"><p style="margin:0 0 20px 0;">Add Selected Contacts to your group</p>
  <a href="#" onclick="javascript:processMultipleContact('ADD');" title="ADD TO GROUP" id="ADDTOGROUP" class="button_disabled_addtogroup ">
  <span class="title_green1_addtogroup" >ADD TO GROUP</span></a> <br>
</div>
            </div>
            <!--- Add Contacts to Group Ends---->
          </div>
        </div>
      </article>
      <!--content ends -->
    </div>
    </form:form>
  </div>
  <!-- include footer -->
  <jsp:include page="../common/includes/footer.jsp" />
  <script type="text/javascript" src="ui/js/googleanalytics.js"></script>
</body>

<script type="text/javascript">
function split(val) {
    return val.split(/,\s*/);
}
function extractLast(term) {
    return split(term).pop();
}
</script>
</html>