<%@ include file="/WEB-INF/jsp/common/include.jsp" %>

<link rel="stylesheet" href="${pageContext.request.contextPath}/ui/css/reset.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/ui/css/layout.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/ui/css/buttons.css"  media="screen">
<link rel="stylesheet" href="${pageContext.request.contextPath}/ui/css/style_login.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/ui/css/accordionmenu.css" type="text/css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/ui/css/icons_sprites.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/fonts/fonts_stylesheet.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/ui/css/ratingbar.css" type="text/css"/>
<link rel="stylesheet" href="${pageContext.request.contextPath}/ui/css/ui.progress-bar.css" type="text/css"/>
<link rel="stylesheet" href="${pageContext.request.contextPath}/ui/modal/colorbox.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/ui/css/form_elements.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/ui/css/image_slider.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/ui/css/validate/cmxform.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/ui/css/style.css">

<script type="text/javascript" src="${pageContext.request.contextPath}/js/json_parse.js"></script>

<input type='hidden' id="endOfFile" value="SHOW"/>
<input type='hidden' id="pageNum" value="1"/>
<%
String parameter = request.getParameter("param");
String searchValue = request.getParameter("search");
%>
<input type='hidden' id="param" value="<%=parameter%>" />
<input type='hidden' id="searchValue" value="<%=searchValue%>" />
<script type="text/javascript">
    
$(document).ready(function() {
 
$("#searchSubmitContacts").click(function () {
 	sharedContactsJsonCall();
});

});
    
	function addEmail(){
		$("#email_error").hide();
		if(validateShareEmail()){
			$("#inCyyteForm").ajaxSubmit({
				type:'POST',
				url:'addEmailAddress.cyt',
				success:function (data) {
				},
				error:function (jqXHR, textStatus, errorThrown) {
					alert("error:" + textStatus + " exception:" + errorThrown);
				}
			});
		}
	}
    function shareContactEmail(){    	
		 if ($("#emailArr").val() == ""){
			 //DO NOTHING
		 }else{
		    	if(validateShareEmail()){
		    		var contextVar = document.getElementById("contextPathVar").value;
		    		 $("#sharedPollForm").ajaxSubmit({
		    			type: 'POST',
		    		    url: contextVar + '/emailSharedPoll.cyt',
		   		        success: function (data) {
		   		        	if(data == "pollEmailCountError"){
			   				    parent.$.fn.colorbox({'href':'div.pollEmailCountPopup_bg', 'open':true, 'inline':true, 'escKey':false, 'overlayClose':false,'closeBtn':false});
		   		        	}else{
		   		        	  parent.$.fn.colorbox.close();
		   		        	}
		   		              },
		   		        error: function (jqXHR, textStatus, errorThrown) {
		   		              }
		    		});
		    	}
		 }
     }
     
     
function checked (xyz){
        // Select all
        if(xyz=='#selectall')
        {	
        	RemoveContactEmailList();		
    		$("#group_1" + " INPUT[type='checkbox']").attr('checked', false);
            $("#group_1"  + " INPUT[type='checkbox']").attr('checked', true);  
            addContactEmailList();
            //return false;
        } 
        // Select none
        if(xyz=='#select_none')
        {
        	RemoveContactEmailList();		
        	$("#group_1" + " INPUT[type='checkbox']").attr('checked', false);
              //return false;
      	}
        validateEmail();
    }
    function RemoveContactEmailList(){
    	var sList = "";	
    	 
    	$(".contactList").each(function(){
  			if($(this).is(':checked')){
  				 sList +=  $(this).val() + "; ";
            }            
        });
    	
    	if(sList != ""){
    		var re = /\s*[,;]\s*/;
    		var maillAdrArr = sList.split(re);	
    		
    		for(var x=0; x < maillAdrArr.length; x++){
    			var addr = maillAdrArr[x].trim();
    			
    			 if(addr != '' && addr != ' '){
    				 removeEmail(addr);
    			 }
    		}
    	} 	
    	  	
     }
   function addContactEmailList(){
    	var sList = "";	
    	var count = 0;
    	
    	$(".contactList").each(function(){
  			if($(this).is(':checked')){  				
  				if(count == 0){  
  					sList += $(this).val();
  				}
  				else{
  					sList +=  "; " + $(this).val();
  				}  					
  				count++;
            }            
        });
    	
    	if(sList != ""){
    		var emailArrVal = $('#emailArr').val();
    		if(emailArrVal != ""){
    		 $('#emailArr').val(emailArrVal+ '; ' +sList);
    		}
    		else{
    			 $('#emailArr').val(sList);
    		}    		
    	} 	
    	  	
     }

    function selectContact(selectedContactId){
    	var contactEmail = $("#"+selectedContactId).val();
    	var emailArrVal = $('#emailArr').val();
    	if($("#"+selectedContactId).is(':checked') ) {
    		$("#"+selectedContactId).attr("checked", true);
     		if(emailArrVal != ""){
     			$('#emailArr').val(emailArrVal+ '; ' +contactEmail);
     		}
     		else{
     			$('#emailArr').val(contactEmail);
     		}
    	 }
    	 else {
    		 if(emailArrVal != ""){
    			 removeEmail(contactEmail);
    		 }
    	 }
    	validateShareEmail();
     }
    function validateShareEmail(){
    	var mailAddrs = $("#emailArr").val();
    	var re = /\s*[,;]\s*/;
    	/* var maillAdrArr = mailAddrs.split(re);

    		 for(var x=0; x < maillAdrArr.length; x++){
    			 var addr = maillAdrArr[x].trim();

    			 if(addr != '' && addr != ' '){

    				  if(!isValidateEmailStr(addr)){
    					 $("#email_error").text("You have entered an invalid email address.");
    					 $("#email_error").show();
    					 //scroll(0,0);
    					 return false;
    				 } 

    				  if(!isSpecialCharEmail(addr)){
    					 $("#email_error").text("You have entered an invalid email address.");
    					 $("#email_error").show();
    					 //scroll(0,0);
    					 return false;
    				 } 

    				 $("#email_error").text("");
    				 $("#email_error").hide();
    			 }

    		 }

    		  if(hasDuplicate(maillAdrArr)){
    			 $("#email_error").text("You have entered duplicate email address.");
    			 $("#email_error").show();
    			 return false;
    		 }else{
    			$("#email_error").text("");
    			$("#email_error").hide();
    		 }  */


    	return true;
    }

    function isValidateEmailStr(emailStr) {
        /* The following variable tells the rest of the function whether or not
         to verify that the address ends in a two-letter country or well-known
         TLD.  1 means check it, 0 means don't. */

        var checkTLD = 1;
        /* The following is the list of known TLDs that an e-mail address must end with. */
        var knownDomsPat = /^(com|net|org|edu|int|mil|gov|arpa|biz|aero|name|coop|info|pro|museum)$/;
        /* The following pattern is used to check if the entered e-mail address
         fits the user@domain format.  It also is used to separate the username
         from the domain. */

        var emailPat = /^(.+)@(.+)$/;

        /* The following string represents the pattern for matching all special
         characters.  We don't want to allow special characters in the address.
         These characters include ( ) < > @ , ; : \ " . [ ] */

        var specialChars = "\\(\\)><@,;:\\\\\\\"\\.\\[\\]";

        /* The following string represents the range of characters allowed in a
         username or domainname.  It really states which chars aren't allowed.*/

        var validChars = "\[^\\s" + specialChars + "\]";

        /* The following pattern applies if the "user" is a quoted string (in
         which case, there are no rules about which characters are allowed
         and which aren't; anything goes).  E.g. "jiminy cricket"@disney.com
         is a legal e-mail address. */

        var quotedUser = "(\"[^\"]*\")";

        /* The following pattern applies for domains that are IP addresses,
         rather than symbolic names.  E.g. joe@[123.124.233.4] is a legal
         e-mail address. NOTE: The square brackets are required. */

        var ipDomainPat = /^\[(\d{1,3})\.(\d{1,3})\.(\d{1,3})\.(\d{1,3})\]$/;

        /* The following string represents an atom (basically a series of non-special characters.) */

        var atom = validChars + '+';

        /* The following string represents one word in the typical username.
         For example, in john.doe@somewhere.com, john and doe are words.
         Basically, a word is either an atom or quoted string. */

        var word = "(" + atom + "|" + quotedUser + ")";

        // The following pattern describes the structure of the user

        var userPat = new RegExp("^" + word + "(\\." + word + ")*$");

        /* The following pattern describes the structure of a normal symbolic
         domain, as opposed to ipDomainPat, shown above. */

        var domainPat = new RegExp("^" + atom + "(\\." + atom + ")*$");

        /* Finally, let's start trying to figure out if the supplied address is valid. */

        /* Begin with the coarse pattern to simply break up user@domain into
         different pieces that are easy to analyze. */

        var matchArray = emailStr.match(emailPat);

        if (matchArray == null) {

            /* Too many/few @'s or something; basically, this address doesn't
             even fit the general mould of a valid e-mail address. */

            //alert("Email address seems incorrect (check @ and .'s)");
            return false;
        }
        var user = matchArray[1];
        var domain = matchArray[2];

        // Start by checking that only basic ASCII characters are in the strings (0-127).

        for (var i = 0; i < user.length; i++) {
            if (user.charCodeAt(i) > 127) {
                //alert("Ths username contains invalid characters.");
                return false;
            }
        }
        for (var i = 0; i < domain.length; i++) {
            if (domain.charCodeAt(i) > 127) {
                //alert("Ths domain name contains invalid characters.");
                return false;
            }
        }

        // See if "user" is valid

        if (user.match(userPat) == null) {
            // user is not valid
            return false;
        }

        /* if the e-mail address is at an IP address (as opposed to a symbolic
         host name) make sure the IP address is valid. */

        var IPArray = domain.match(ipDomainPat);
        if (IPArray != null) {

            // this is an IP address

            for (var i = 1; i <= 4; i++) {
                if (IPArray[i] > 255) {
                    return false;
                }
            }
            return true;
        }

        // Domain is symbolic name.  Check if it's valid.

        var atomPat = new RegExp("^" + atom + "$");
        var domArr = domain.split(".");
        var len = domArr.length;
        for (i = 0; i < len; i++) {
            if (domArr[i].search(atomPat) == -1) {
                return false;
            }
        }

        /* domain name seems valid, but now make sure that it ends in a
         known top-level domain (like com, edu, gov) or a two-letter word,
         representing country (uk, nl), and that there's a hostname preceding
         the domain or country. */

        if (checkTLD && domArr[domArr.length - 1].length != 2 &&
            domArr[domArr.length - 1].search(knownDomsPat) == -1) {
            ///alert("The address must end in a well-known domain or two letter " + "country.");
            return false;
        }

        // Make sure there's a host name preceding the domain.

        if (len < 2) {
            return false;
        }

        // If we've gotten this far, everything is valid!
        return true;
    }
    
    function isSpecialCharEmail(emailStr){
    	var iChars = "/^&?+*[]{}\|`~<>%#$!='";
    	var isValid=true;
    	for (var i = 0; i < emailStr.length; i++)
        {
    	    if (iChars.indexOf(emailStr.charAt(i))!='-1') {
    	    	isValid=false;
    	    	break;
    	      }else{
    	    	 isValid=true;
    		 }
        }
    	return isValid;
    }
    function hasDuplicate(arr) {
        var i = arr.length, j, val;

        while (i--) {
        	val = arr[i];
        	j = i;
        	while (j--) {
        		if (arr[j] === val) {
        			return true;
        		}
        	}
        }
        return false;
    }
    function removeEmail(cEmail){
    	var mailAddrs = $("#emailArr").val();
    	var re = /\s*[,;]\s*/;
    	var maillAdrArr = mailAddrs.split(re);
    	$("#emailArr").val('');

    		 for(var x=0; x < maillAdrArr.length; x++){
    			 var addr = maillAdrArr[x].trim();
    			 if(addr != '' && addr != ' '){
    				 if(addr.indexOf(cEmail) == -1){
    					 if($('#emailArr').val() != ""){
    						 	var emailArrVal = $('#emailArr').val();
    				 			$('#emailArr').val(emailArrVal+ '; ' +addr);
    					 }
    					 else{
    				 			$('#emailArr').val(addr);
    				 		}
    				 }

    			 }

    		 }
    }
</script>
<script type="text/javascript">
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
function sharedContactsJsonCall(){
	 var table = document.getElementById('contactsTable');
	 var rowCount = table.rows.length;
     for(var i=0; i<rowCount; i++) {
             table.deleteRow(i);
             rowCount--;
             i--;
     }
     var contextPath = $('#contextPathVar').val();
	 var param = $('#search_share_contacts').val();
	 var code = $('#incyyteCode').val();
     httpRequest.open("GET", contextPath+"/searchSharedContacts.cyt?param=" + param + "&code="+code  , true);
 	httpRequest.onreadystatechange = getSharedContactsAfterSearch;
 	httpRequest.send(null);
}

function getSharedContactsAfterSearch(){
	sharedContactsDiv = document.getElementById("contactsTable");
	if (httpRequest.readyState == 4 || httpRequest.readyState == 200 ) {
        var contextPath = $('#contextPathVar').val();
        var resultText = httpRequest.responseText;
		var contacts = json_parse(resultText);
		var contactList = contacts.contacts;
		for (var i = 0; i < contactList.length; i++) {
	
		var spanValue  = "";
		if(contactList[i].displayForCheckedCheckBox == "none" ){
			spanValue  =  "<span id='group_1'>" 
						+ '<input type="checkbox" style="display:' + contactList[i].displayForNormalCheckBox + '" class="contactList" name="selectedGroupContactsList" onclick="selectContact('+"'contactId'+" + contactList[i].contactIdValue +');"  id="contactId'+ contactList[i].contactIdValue +'"  value="' + contactList[i].contactEmail +'" /> ';
		}else if(contactList[i].displayForCheckedCheckBox == ""){
			spanValue  =  "<span id='group_1'>" 
						+ '<img alt="already received this incyyte" style="display:' + contactList[i].displayForCheckedCheckBox + '" src="' + contextPath + '/ui/images/check_save.png" />';
		}
			
	sharedContactsDiv.innerHTML += "<tr>"
								   + '<td style="padding-left: 10px; padding-right: 10px;"> '
								   + spanValue
								   + '</span>'
								   + '</td>'
								   + '<td height="40" style="padding-right: 10px;">'
								   + '<img src="'+ contactList[i].profileImgUrl + '" width="36" height="35" alt="User Photo"></td>'
								   + '<td class="font_12px">'+ contactList[i].contactEmail + '</td>'
								   + '<td class="font_12px">'+ contactList[i].username + '</td>'
								   + "</tr>" ; 
		}
		$('#view_user').show();
		if(contactList.length < 10) {
			$('#view_user').hide();
		}
	}
}
</script>
<script type="text/javascript">
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
function yHandler() {
	$('#view_user').hide();
	var totalDiv = document.getElementById('scrollbar1');
	var totalHeight = totalDiv.offsetHeight;
	var scrolledHeight = window.pageYOffset;
	var y = scrolledHeight + window.innerHeight;
	var pageNumber = $('#pageNum').val();
	var param = $('#param').val();
	var search = $('#searchValue').val();
	var contextPath = $('#contextPathVar').val(); 
	
	 var code = $('#incyyteCode').val();
		pageNumber++;
		$('#pageNum').val(pageNumber);
		 httpRequest.open("GET", contextPath+"/searchSharedContacts.cyt?param=" + param + "&code="+code + "&pageNumber="+pageNumber   , true);
		httpRequest.onreadystatechange = getContactsAfterScroll;
		httpRequest.send(null);
}



function getContactsAfterScroll() {
	if (httpRequest.readyState == 4 || httpRequest.readyState == 200 ) {
		var resultText1 = httpRequest.responseText;
		var resultsToDecideEndPoint = json_parse(resultText1);
		var endOfFile = resultsToDecideEndPoint.endOfFile;
		var displayEndOfResult = $('#endOfFile').val();
		var currentContactList = document.getElementById('contactsTable');
		if(endOfFile == "FALSE"){
			var resultText = httpRequest.responseText;
			var contacts = json_parse(resultText);
			var contactList = contacts.contacts;
			for (var i = 0; i < contactList.length; i++) {
				var spanValue  = "";
				if(contactList[i].displayForCheckedCheckBox == "none" ){
					spanValue  =  "<span id='group_1'>" 
								+ '<input type="checkbox" style="display:' + contactList[i].displayForNormalCheckBox + '" class="contactList" name="selectedGroupContactsList" onclick="selectContact('+"'contactId'+" + contactList[i].contactIdValue +');"  id="contactId'+ contactList[i].contactIdValue +'"  value="' + contactList[i].contactEmail +'" /> ';
				}else if(contactList[i].displayForCheckedCheckBox == ""){
					spanValue  =  "<span id='group_1'>" 
								+ '<img alt="already received this incyyte" style="display:' + contactList[i].displayForCheckedCheckBox + '" src="/ui/images/check_save.png" />';
				}
				currentContactList.innerHTML += "<tr>"
						+ '<td style="padding-left:10px;padding-right:10px;">'
						+ spanValue
					    + '</span>'							
						+ '</td>'
						+ '<td height="40" style="padding-right:10px;">'
						+ '<img	src="' + contactList[i].profileImgUrl + '" width="36" height="35" alt="User Photo">'						
						+ '</td>'						
						+ '<td>'+ contactList[i].contactEmail + '</td>'
						+ '<td>'+ contactList[i].username+ '</td>'	
						+ '</tr>';
			}
	      $('#view_user').show();
	}else if ((endOfFile == "TRUE") && (displayEndOfResult == "SHOW")){
		$('#view_user').hide();
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

<div style="display: none;">
    <div id="sharedEmailList" >
        <div id="share_contacts">
            <h3 class="heading1" style="padding:2px 0 8px 0px;">Share with your Contacts</h3>
						 <!------ Search Box Starts -------->
			 			<div id="newsearchbox" style="margin-bottom: 0">
							<div id="searchmainSharePopUp">
								<p class="search_heading">Search</p>
								 <input type="text" name="search_share_contacts" id="search_share_contacts" onKeydown="Javascript: if (event.keyCode==13 )  sharedContactsJsonCall();" >
								 <input type="submit" id="searchSubmitContacts" value="" />
							</div>
						</div>
            <div id="scrollbar1">
                <div class="scrollbar">
                    <div class="track">
                        <div class="thumb">
                            <div class="end"></div>
                        </div>
                    </div>
                </div>
                <input type="hidden" id="contextPathVar" value="${pageContext.request.contextPath}">
                <form:form id="sharedPollForm" name="sharedPollForm" commandName="inCyyteForm"  method="post">
                	<form:hidden path="incyyteCode" id="incyyteCode"/>
                    <div class="viewport">
                        <div class="overview">
                            <table width="380" border="0" cellspacing="0" cellpadding="0" id="contact_list">
                                    <td colspan="12" class="select_all">Select: <a rel="group_1" href="javascript:checked('#selectall');">All</a> | <a rel="group_1" href="javascript:checked('#select_none');">None</a></td>
                                </tr>
                            </table>
                            <div style="width:380px; height:4px;"></div>
                             <div id="contactShareScroll" style="width:380px; height:200px; overflow-x:hidden;overflow-y:auto;">
                                <table  width="350" border="0" id="contactsTable" cellspacing="0" cellpadding="0">
                                    <c:forEach items="${emailContactList}" var="UserContactModel">
                                        <tr>
                                            <td style="padding-left:10px;padding-right:20px;margin-top: 10px;float: left;">
					                 				<span id="group_1">
					                 					<c:choose>
                                                             <c:when test="${UserContactModel.receivedIncyyte == true}">
                                                                 <img alt="already received this incyyte" src="${pageContext.request.contextPath}/ui/images/check_save.png" />
                                                             </c:when>
                                                             <c:otherwise>
                                                                 <input type="checkbox" class="contactList" name="selectedGroupContactsList" onclick="selectContact('contactId'+${UserContactModel.contactid})" id="contactId${UserContactModel.contactid}" value="${UserContactModel.email}"/>
                                                             </c:otherwise>
                                                         </c:choose>
					 		              			</span>
                                            </td>
                                             <td height="40" style="padding-right:10px;"><img src='${UserContactModel.profile_img}' style="" width="36" height="35"  alt="User Photo"></td>
                                                <td class="font_12px" >${UserContactModel.email}</td>
                                                <td class="font_12px">${UserContactModel.username}</td>
                                        </tr>
                                    </c:forEach>
                                </table>
                                   <table width="20%" border="0" cellpadding="0" cellspacing="0">
				                     <tr style="padding-right: 300px;">
					                  <td align="middle" style="padding-left: 230px;" >
						                <div><input value="Display more contacts" type="button" onClick="javascript:yHandler()" style="cursor: pointer;border: solid 1px grey;" id="view_user"></div>
					                  </td>
				                   </tr>                   
				               </table>
                            </div>
                            <div style="width:380px; height:4px; float: left;"></div>
                            <table>
                                <tr>
                                    <td valign="top">
                                        <form:textarea cssClass="questionbox" path="emailArr" onchange="javascript:addEmail()"
                                                       id="emailArr" placeholder="Add one or more email addresses separated by a space" onFocus="this.placeholder = ''"
                                                       onBlur="this.placeholder = 'Add one or more email addresses separated by a space'"></form:textarea>
                                        <BR>
                                        <span id="email_error" class="error" style="font:14px/20px 'bariol_regularregular', 'ie8_bariol_regular';color:#C2002D; margin-left:12px"></span>
                                    </td>
                                </tr>
                            </table>
                        </div>
                    </div>
                </form:form>
            </div>
            <table width="380px" border="0" cellspacing="0" cellpadding="0">
                <tr >
                    <td valign="bottom" width="50%" align="left"><label class="panelLink" id="importContact" ><span class="importSpan" title="Import Contacts">Import new contacts</span></label></td>
                    <td  valign="top">
                        <button type="button" title="Share inCyyte" id="add_email_btn" class="button_green1 share_bot_margin_ie8" onclick="shareContactEmail()";   style="width:105px; margin:10px 0 0 50px;"><span class="title_green1 share_bot_ie8">SHARE</span>
                        </button>
                    </td>
                </tr>
            </table>
        </div>
    </div>
</div>