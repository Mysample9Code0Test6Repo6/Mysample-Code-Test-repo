<%@ include file="/WEB-INF/jsp/common/include.jsp" %>

<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="content-type" content="text/html; charset=UTF-8">
<link rel="shortcut icon" href="favicon.ico" />

<title>inCyyte - Edit your group</title>
<meta charset="utf-8">
<link rel="stylesheet" href="ui/css/reset.css">
<link rel="stylesheet" href="ui/css/layout.css">
<link rel="stylesheet" href="ui/css/buttons.css"  media="screen">
<link rel="stylesheet" href="ui/css/style_login.css">
<link rel="stylesheet" href="ui/css/accordionmenu.css" type="text/css">
<link rel="stylesheet" href="ui/css/icons_sprites.css">
<link rel="stylesheet" href="fonts/fonts_stylesheet.css">
<link rel="stylesheet" href="ui/css/style_help.css">
<script src="ui/js/jquery-1.4.2.min.js"></script>
<script src="ui/js/search_script.js"></script>

<script src="ui/js/login.js"></script>
<script src="ui/js/jquery.color.js"></script>
<!-- Left Navigation script starts here -->
<script src="ui/js/left_menu.js"></script>
<!-- Left Navigation script ends here -->
<link rel="stylesheet" href="ui/css/sparkbox-select.css">
<script src="ui/js/modernizr-1.7.min.js"></script>
<script src="ui/js/jquery.sparkbox-select.js"></script>
<script>
$(document).ready(function() {
  $('.sparkbox-custom').sbCustomSelect({
    appendTo: 'body'
  });
});
</script>
<link type="text/css" rel="stylesheet" href="ui/css/scrollbar.css">
<link rel="stylesheet" href="ui/css/ratingbar.css" type="text/css"/>    
<link rel="stylesheet" href="ui/css/ui.progress-bar.css" type="text/css"/>
<link rel="stylesheet" href="ui/modal/colorbox.css">
<link rel="stylesheet" href="ui/css/prettyCheckboxes.css" type="text/css" media="screen">


<script type="text/javascript" src="ui/js/cufon.js"></script>
<script type="text/javascript" src="ui/js/scollbar.js"></script>

         <script type="text/javascript" src="ui/js/jquery-1.5.1.min.js"></script>
           <script src="ui/js/accordian/jquery-1.6.2.js"></script>
    	<script type="text/javascript" src="ui/js/jquery-ui-1.8.13.custom.min.js"></script>
    	<script type="text/javascript" src="/js/external/jquery.bgiframe-2.1.2.js"></script>
    	<script type="text/javascript" src="/js/communicator.js"></script>
    	<script type="text/javascript" src="ui/js/group.js"></script>
    	<script type="text/javascript" src="ui/js/jquery.form.js"></script> 
		<script src="ui/js/prettyCheckboxes.js" type="text/javascript" charset="utf-8"></script>

	<script src="ui/js/jquery_profile_rating.js" type="text/javascript"></script>
    <script src="ui/js/star_rating.js" type="text/javascript"></script>

    <script type="text/javascript" src="ui/js/jquery.tinyscrollbar.min.js"></script>

   <script type="text/javascript" src="ui/js/IE-placeholder.js"></script>
<!--- placeholder Starts----->
<script>
    // placeholder polyfill
     $(document).ready(function(){
    	 $('#scrollbar1').tinyscrollbar();
    	 $('#radio-2').change(function () {
    		 $("#showGroupMembers").css("display", "");
             $("#showAllContactsTbl").css("display", "none");
             $("#showAllContactsCSV").css("display", "none");
             $("#showMembersCSV").css("display", "");
    		});
    	 
    	 $('#radio-1').change(function () {
    		 $("#showAllContactsTbl").css("display", "");
             $("#showGroupMembers").css("display", "none");
             $("#showAllContactsCSV").css("display", "");
             $("#showMembersCSV").css("display", "none");
    		});
    	
     });
</script>

    <!--[if gte IE 9]>
    <link href="ui/css/gte_ie9.css" media="screen" rel="stylesheet" type="text/css"/>
    <![endif]-->
<script type="text/javascript">
    function checked (xyz){
      $(':checkbox').click(function () {
      var checkboxes = $("input[type='checkbox']");
      if(!checkboxes.is(":checked")) {
              $('#ADDTOGROUP').bind('click', function(e){ e.preventDefault();})
              $('#ADDTOGROUP').attr('class','button_disabled');
      } else {
  	    //alert("unchechecked") ;

            $('#ADDTOGROUP').unbind('click');
            $('#ADDTOGROUP').attr('class','button_green1');
      }
       });
   
        // Select all
        if(xyz=='#selectall')
        {	
            $("#group_1"  + " INPUT[type='checkbox']").attr('checked', true);    
            
            $('#ADDTOGROUP').unbind('click');
            $('#ADDTOGROUP').attr('class','button_green1');
            
            var selected = "";
            var checked = $("input[@name=" + 'numbers[]' + "]:checked");
                for (var i = 1; i < checked.length; i++) {
                    if (checked[i].value != 'on') {
                        if (selected == "") {                    	
                            selected = checked[i].value;
                        }else {
                            selected = selected + "," + checked[i].value;
                        }
                    }
                $("#ed_checked").val(selected);
        	}     
              $.ajax({
                    type:"GET",
                    url:"submitmultipleeditcontact.cyt?groupIds="+$("#ed_checked").val(),
                    dataType:"text"
                });  
        }
 
        // Select none
        if(xyz=='#select_none')
        	{
              $("#group_1" + " INPUT[type='checkbox']").attr('checked', false);
              $('#ADDTOGROUP').bind('click', function(e){ e.preventDefault();})
              $('#ADDTOGROUP').attr('class','button_disabled');
              
              var checked = "None";
              $.ajax({
                  type:"GET",
                  url:"submitmultipleeditcontact.cyt?checked="+checked,
                  dataType:"text"
              }); 
              //return false;
           }
 
     
 
    }
</script>




<script type="text/javascript">
    function myonclickhandler(t,val) {
        if (t.checked) {
       	alert(val) ;
        var check = document.getElementById("uncheckedlist").value ;
        
        for (var i= 0 ; i<= check.length ; i++)
        	{
        	
        	 if (val== check[i]) 
        		 check=	check.trim(val) ; 
        	}
        
        document.getElementById("uncheckedlist").value = check ;
       }
        else {
      // 	alert("false")   ;
       // 	alert(val) ;
        	document.getElementById("uncheckedlist").value= document.getElementById("uncheckedlist").value+","+ val ;
        	alert(document.getElementById("uncheckedlist").value);
        	
        }
    }
</script>








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
	var totalDiv = document.getElementById('gradient');
	var totalHeight = totalDiv.offsetHeight;
	var scrolledHeight = window.pageYOffset;
    var y = scrolledHeight + window.innerHeight;

	var allContactspageNum = $('#pageNum').val();    
	var grpContactspageNum = $('#pageNum1').val(); 
	var param = $('#param').val();
	var search = $('#searchValue').val();
	var grpId = $('#grpId').val();
	
		if ($("#radio-2").attr("checked")) {
			$('#getGroupContacts').hide();
			grpContactspageNum++;
			$('#pageNum1').val(grpContactspageNum);
			httpRequest.open("GET", 'getScrolledGroupMembers.cyt?pageNumber=' + grpContactspageNum + '&param='+param  + '&search='+ search + '&grpId='+ grpId, true);
			httpRequest.onreadystatechange = getGroupContactsAfterScroll;
		} else if ($("#radio-1").attr("checked")) {
			$('#view_incyyte').hide();
			allContactspageNum++;
			$('#pageNum').val(allContactspageNum);
			httpRequest.open("GET", 'getScrolledContacts.cyt?pageNumber=' + allContactspageNum + '&param='+param  + '&search='+ search + '&grpId='+ grpId, true);
			httpRequest.onreadystatechange = getAllContactsAfterScroll;
		}
		httpRequest.send(null);
}
 
 function getAllContactsAfterScroll() {
	 if (httpRequest.readyState == 4 || httpRequest.readyState == 200 ) {
     var grpPolled = $('#grpPolled').val();
     var grpId = $('#grpId').val();
 	 var resultText1 = httpRequest.responseText;
     var resultsToDecideEndPoint = json_parse(resultText1);
     var endOfFile = resultsToDecideEndPoint.endOfFile;
     var displayEndOfResult = $('#endOfFile').val();
     var currentAllContactList = document.getElementById('showAllContactsTbl');
    if(endOfFile == "FALSE"){
		var resultText = httpRequest.responseText;
		var contacts = json_parse(resultText);
		var contactList = contacts.contacts;
		var checkedAllContacts;
		var newAllChecked;
		if(contactList.length > 0) {
			
     		 for (var i = 0; i < contactList.length; i++) {
			newAllChecked=   '<td height="40" ><img src='+"'"+contactList[i].profileImgUrl+"'"+' width="36" height="35"  alt="User Photo"></td>'
          				+ '<td >'+contactList[i].contactEmail+'</td>'     
           				+ '<td>'+contactList[i].username+'</td>'
        				+ '</tr>';
					  if(contactList[i].checked== 'checked'){       
						  checkedAllContacts = '<tr>' + '<td>'                                                     
				          		  + '<fieldset id="group_1">' 
				            	  + '<img src="ui/images/check_save.png" alt="Polled" title="part of a Live Poll">' +  '</fieldset>' +'</td>'
				            	  +newAllChecked;
					  } else if(contactList[i].checked== 'Y'){   
						  checkedAllContacts = '<tr>' + '<td>'                                                     
				  		  + '<fieldset id="group_1">' 
				  		  +'<input type="checkbox"  id="contactId'+contactList[i].contactId+'" value="'+contactList[i].contactId 
				  		  +'" onclick="javascript:submitEditContact('+"'"+contactList[i].contactId+"'"+',$(this).attr('+"'"+'checked'+"'"+'),'+grpId+');" checked="checked" />' 
				  		  + '</fieldset>' + '</td>' + newAllChecked;
			         }  else{
                       checkedAllContacts = '<tr>' + '<td>'                                                     
		          		  		  + '<fieldset id="group_1">' 
		          		  		  +'<input type="checkbox"  id="contactId'+contactList[i].contactId+'" value="'+contactList[i].contactId
		          		  		  +'" onclick="javascript:submitEditContact('+"'"+contactList[i].contactId+"'"+',$(this).attr('+"'"+'checked'+"'"+'),'+grpId+');" />' + '</fieldset>' + '</td>'
		          		  		  +newAllChecked;
		         		}
					  currentAllContactList.innerHTML += checkedAllContacts;
			}
		}
		$('#view_incyyte').show();	
    }
    else if ((endOfFile == "TRUE") && (displayEndOfResult == "SHOW")){
    $('#view_incyyte').hide();
	currentAllContactList.innerHTML += "<tr>" 
		+ '<td width="5%" valign="top">'
		+ '</td>'
		+ '<td width="12%"></td> '
		+ '<td width="64%">'
		+ '<p style="font-family: bariol_boldbold, ie8_bariol_bold; font-size: 14px;">   End Of Contact List  ' 
		+ '</p></td>'
		+ '<td width="19%" align="right"></td>'
		+ '</tr>';
		$('#endOfFile').val('STOP');
	}
 
	}
 }
 
 
 function getGroupContactsAfterScroll() {
	 if (httpRequest.readyState == 4 || httpRequest.readyState == 200 ) {
     var grpPolled = $('#grpPolled').val();
     var grpId = $('#grpId').val();
 	 var resultText1 = httpRequest.responseText;
     var resultsToDecideEndPoint = json_parse(resultText1);
     var endOfFile = resultsToDecideEndPoint.endOfFile;
     var displayEndOfResult = $('#endOfFile').val();
     var currentContactList = document.getElementById('showGroupMembers');
    if(endOfFile == "FALSE"){
		var resultText = httpRequest.responseText;
		var contacts = json_parse(resultText);
		var contactList = contacts.contacts;
		var checkedContact;
		var newChecked;
		for (var i = 0; i < contactList.length; i++) {
			  if(contactList[i].checked== 'checked'){       
				  checkedContact = '<tr>' + '<td>'                                                     
		          		  + '<fieldset id="group_1">' 
		            	  + '<img src="ui/images/check_save.png" alt="Polled" title="part of a Live Poll">' +  '</fieldset>' +'</td>';
			  }  else{
                  	 checkedContact = '<tr>' + '<td>'                                                     
        		  		  + '<fieldset id="group_1">' 
        		  		  +'<input type="checkbox"  checked="checked"  id="contactId'+contactList[i].contactId+'" value="'+contactList[i].contactId+'" onclick="javascript:submitEditContact('+"'"+contactList[i].contactId+"'"+',$(this).attr('+"'"+'checked'+"'"+'),'+grpId+');" />' + '</fieldset>' + '</td>';
         		}
				newChecked =   '<td height="40"><img src='+"'"+contactList[i].profileImgUrl+"'"+' width="36" height="35"  alt="User Photo"></td>'
        				+ '<td >'+contactList[i].contactEmail+'</td>'     
         				+ '<td>'+contactList[i].username+'</td>'
      	 			+ '</tr>';
      				checkedContact = checkedContact +newChecked;
			  currentContactList.innerHTML += checkedContact;
			}
		$('#getGroupContacts').show();
		}else if ((endOfFile == "TRUE") && (displayEndOfResult == "SHOW")){
			$('#getGroupContacts').hide();
			currentContactList.innerHTML += "<tr>" 
			+ '<td width="5%" valign="top">'
			+ '</td>'
			+ '<td width="12%"></td> '
			+ '<td width="64%">'
			+ '<p style="font-family: bariol_boldbold, ie8_bariol_bold; font-size: 14px;">   End Of Contact List  ' 
			+ '</p></td>'
			+ '<td width="19%" align="right"></td>'
			+ '</tr>';
			$('#endOfFile').val('STOP');
		}
	 }
 }
 </script>
<script type="text/javascript" src="ui/js/table2CSV.js"></script>

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
	<input type='hidden' id="pageNum" value="1" />
	<input type='hidden' id="pageNum1" value="1" />
	<%
		String parameter = request.getParameter("param");
		String searchValue = request.getParameter("search");
		String grpId = String.valueOf(request.getParameter("grpid"));
	%>
	<input type='hidden' id="param" value="<%=parameter%>" />
	<input type='hidden' id="searchValue" value="<%=searchValue%>" />
	<input type='hidden' id="grpId" value="<%=grpId%>" />
	<input type='hidden' id="grpPolled" value="${GrpPolled}" />
	<input type='hidden' id="endOfFile" value="SHOW"/>

	<div id="gradient">
		<div class="extra">
			<!-- include header -->
			<jsp:include page="../common/includes/header.jsp" />

          <form:form  id="group"  commandName="group" method="post" >   
          	<form:hidden path="groupId" id="groupId"   />
          	<form:hidden path="checked" id="ed_checked"   />
          	<form:hidden path="polled" id="polled"   />
                       
    <div class="main">
      <!--content -->
      <article id="content">
        <div id="main_content">
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
            <div id="create_a_group" >
              <h3 class="heading3" style="padding:0 0 40px 0">Edit Group</h3>
              <div id="help" ><a href="#" ><em style="z-index:99" class="arrow_box1">
                <p class="helpmodal">Editing a Group</p>
                Each time you send a poll to a number of email addresses a new group containing all the polls recipients will automatically be created for you. Use this section to rename the group or to give the new group a description.</em></a></div>
              <p class="heading2" style="margin-top:10px;">Group Name</p>
                <input type='text' name="groupName" id="groupName"  maxlength="50" value="${group.groupName}" />
                <input type='hidden' name="vselected" id="vselected" value="${selcid}" />
			  	<p class="heading2" style="margin-top:20px;">Description</p>
                <textarea name="description" id="description" cols="" rows="" style="resize: none;" placeholder="Group Description goes here" onFocus="this.placeholder = ''" onBlur="this.placeholder = 'Group Description goes here'">${group.description}</textarea>
                <input type='hidden' name="uncheckedlist" id="uncheckedlist" value="" />                
            </div>
            <!--- Select a Group Ends---->
            <!--- Add Contacts to Group Starts---->
            <div id="add_contacts_to_group" >
              <h3 class="heading3" style="padding:0 0 29px 28px;">2. Add/Remove Contacts to Group</h3>
              <div id="help" ><a href="#" ><em style="z-index:99" class="arrow_box1">
                <p class="helpmodal">Choosing Group Contacts</p>
                You can use the contact list below to select or deselect members of a group. Selected contacts will receive polls sent to the group and deselected contacts will not recieve the groups polls.</em></a></div>
              <!------ Search Box Starts -------->
              <div id="advanced_searchbox">
                <div id="searchbox" style="margin-bottom:0">
                  <p class="sort_by_text"></p>
                  <div id="searchmain" >
                    <p class="search_heading">Search</p>
                    <!-- form id="searchForm" -->
                      <fieldset>
                      <div class="input">
                        <input type='text' name="search" id="search" value="" placeholder="Enter your search" onFocus="this.placeholder = ''" onBlur="this.placeholder = 'Enter your search'"/>
                      </div>
                      <input type="submit" id="searchSubmit" value="" />
                      </fieldset>
                    <!-- /form -->
                  </div>
                </div>
                <nav id="alpha_list">
                <li><a href="./groupModify.cyt?param=A&grpid=${grpid}">A</a></li>
                <li><a href="./groupModify.cyt?param=B&grpid=${grpid}">B</a></li>
                <li><a href="./groupModify.cyt?param=C&grpid=${grpid}">C</a></li>
                <li><a href="./groupModify.cyt?param=D&grpid=${grpid}">D</a></li>
                <li><a href="./groupModify.cyt?param=E&grpid=${grpid}">E</a></li>
                <li><a href="./groupModify.cyt?param=F&grpid=${grpid}">F</a></li>
                <li><a href="./groupModify.cyt?param=G&grpid=${grpid}">G</a></li>
                <li><a href="./groupModify.cyt?param=H&grpid=${grpid}">H</a></li>
                <li><a href="./groupModify.cyt?param=I&grpid=${grpid}">I</a></li>
                <li><a href="./groupModify.cyt?param=J&grpid=${grpid}">J</a></li>
                <li><a href="./groupModify.cyt?param=K&grpid=${grpid}">K</a></li>
                <li><a href="./groupModify.cyt?param=L&grpid=${grpid}">L</a></li>
                <li><a href="./groupModify.cyt?param=M&grpid=${grpid}">M</a></li>
                <li><a href="./groupModify.cyt?param=N&grpid=${grpid}">N</a></li>
                <li><a href="./groupModify.cyt?param=O&grpid=${grpid}">O</a></li>
                <li><a href="./groupModify.cyt?param=P&grpid=${grpid}">P</a></li>
                <li><a href="./groupModify.cyt?param=Q&grpid=${grpid}">Q</a></li>
                <li><a href="./groupModify.cyt?param=R&grpid=${grpid}">R</a></li>
                <li><a href="./groupModify.cyt?param=S&grpid=${grpid}">S</a></li>
                <li><a href="./groupModify.cyt?param=T&grpid=${grpid}">T</a></li>
                <li><a href="./groupModify.cyt?param=U&grpid=${grpid}">U</a></li>
                <li><a href="./groupModify.cyt?param=V&grpid=${grpid}">V</a></li>
                <li><a href="./groupModify.cyt?param=W&grpid=${grpid}">W</a></li>
                <li><a href="./groupModify.cyt?param=X&grpid=${grpid}">X</a></li>
                <li><a href="./groupModify.cyt?param=Y&grpid=${grpid}">Y</a></li>
                <li><a href="./groupModify.cyt?param=Z&grpid=${grpid}">Z</a></li>
              </nav>
              </div>
              <!------ Search Box End-------->
              <!-- Search Result Start -->
              <div id="group_contacts">
                <div id="scrollbar1">
                  <div class="scrollbar">
                    <div class="track">
                      <div class="thumb">
                        <div class="end"></div>
                      </div>
                      </div>
                    </div>
                  <div class="inlineRadios">
				        <form:radiobutton path="showAllContacts" id="radio-2" value="N"/>
				        <label for="radio-2" tabindex="2">Group members only</label>
				        <form:radiobutton path="showAllContacts" id="radio-1" value="Y"/>
				        <label for="radio-1" tabindex="1">Show all contacts</label>
				    </div>
                  <BR>
                  <div class="viewport">
                    <div class="overview">
                      <table width="400" border="0" cellspacing="0" cellpadding="0" id="contact_list">
	                      <tr>
	                  		<td colspan="12" class="select_all">Select: <a rel="group_1" href="javascript:checked('#selectall');" >All</a> | <a rel="group_1" href="javascript:checked('#select_none');">None</a></td>                  
	                	  </tr>
		     		   </table>
		     			<br>
					<div id="contactScroll" style="width:400px; height:200px; overflow:auto;">
					<!-- Show Only Members -->
                      <table  width="350" border="0" cellspacing="0" cellpadding="0" id="showMembers">

                         <c:forEach items="${SelectedUserContactlist}" var="UserContactModel">
	                        <tr>
						        <td>                                                     
        		                  <fieldset id="group_1">
									    <c:choose>
									        <c:when test="${GrpPolled}">
						         				<c:if test="${UserContactModel.checked == 'checked'}">
													<img src="ui/images/check_save.png" alt="Polled" title="part of a Live Poll">
												</c:if> 									        
						         				<c:if test="${UserContactModel.checked == NULL}">
									   				<form:checkbox path="selectedGroupContactsList" id="contactId${UserContactModel.contactid}" value="${UserContactModel.contactid}" onclick="javascript:submitEditContact('${UserContactModel.contactid}',$(this).attr('checked'),${grpid});" />                         
												</c:if> 									        
											</c:when>
									        <c:otherwise>
									   			<form:checkbox path="selectedGroupContactsList" id="contactId${UserContactModel.contactid}" value="${UserContactModel.contactid}" onclick="javascript:submitEditContact('${UserContactModel.contactid}',$(this).attr('checked'),${grpid});" />                         
									        </c:otherwise>
									    </c:choose>
            		              </fieldset>
                    		    </td>	
                        		<td height="40"><img src='${UserContactModel.profile_img}' width="36" height="35"  alt="User Photo"></td>
                        		<td >${UserContactModel.email}</td>     
                         		<td>${UserContactModel.username}</td>
                        	</tr>
                      	</c:forEach>
                      </table>
					<!-- Show ALL Contacts -->
                      <table  width="350" border="0" cellspacing="0" cellpadding="0" style="display:none;" id="showAllContactsTbl">
                         <c:forEach items="${UserContactlist}" var="UserContactModel">
	                        <tr>
						        <td>                                                     
        		                  <fieldset id="group_1">
									    <c:choose>
									        <c:when test="${GrpPolled}">
						         				<c:if test="${UserContactModel.checked == 'checked'}">
													<img src="ui/images/check_save.png" alt="Polled" title="part of a Live Poll">
												</c:if> 									        
						         				<c:if test="${UserContactModel.checked == NULL}">
									   				<form:checkbox path="selectedGroupContactsList" id="contactId${UserContactModel.contactid}" value="${UserContactModel.contactid}" onclick="javascript:submitEditContact('${UserContactModel.contactid}',$(this).attr('checked'),${grpid});" />                         
												</c:if> 									        
											</c:when>
									        <c:otherwise>
									   			<form:checkbox path="selectedGroupContactsList" id="contactId${UserContactModel.contactid}" value="${UserContactModel.contactid}" onclick="javascript:submitEditContact('${UserContactModel.contactid}',$(this).attr('checked'),${grpid});" />                         
									        </c:otherwise>
									    </c:choose>
            		              </fieldset>
                    		    </td>	
                        		<td height="40"><img src='${UserContactModel.profile_img}' width="36" height="35"  alt="User Photo"></td>
                        		<td >${UserContactModel.email}</td>     
                         		<td>${UserContactModel.username}</td>
                        	</tr>
                      	</c:forEach>
                      </table>
                    </div>
                  </div>
                </div>
                  <div>
	                  <input value="Export all Contacts to CSV" type="button" onClick="javascript:downloadCSV('showAllContactsTbl')" style="cursor:pointer; display:none; border:solid 1px grey;" id="showAllContactsCSV">                      
	                  <input value="Export Members to CSV" type="button" onClick="javascript:downloadCSV('showMembers')" style="cursor:pointer; border:solid 1px grey;" id="showMembersCSV">                      
                  </div>
		</div>
                            <!-- Pagination start---->
             <div class='pagination_small'> 
<!--           
    
    	 <%--For displaying Previous link except for the 1st page --%>
				      <c:if test="${currentPage != 1}">
				     <a class='prev pagination_small' href="javascript:navigate('./groupModify.cyt?page=${currentPage - 1}&param=${text}&grpid=${grpid}&paginate=true');">Prev</a>
				         </c:if>       
					<%--For displaying Page numbers.
				    The when condition does not display a link for the current page--%>
				             
				            
				                <c:forEach begin="1" end="${noOfPages}" var="i">
				                <c:choose>
				                    <c:when test="${currentPage eq i}">
				                 		<span class='page-numbers_small current_small'>${i}</span> 
				                    </c:when>

				                    <c:otherwise>
				                    
				                    <c:if test="${i <= 5}">
				                      <a class='page-numbers_small' href="javascript:navigate('./groupModify.cyt?page=${i}&param=${text}&grpid=${grpid}&paginate=true');">${i}</a> 
				                    
				                    </c:if>
				                     <c:if test="${i > 5}">
				                    
				                    <c:if test="${i+1 == noOfPages}">
				                      <a class='page-numbers_small'>..</a> 
				                    
				                      <a class='page-numbers_small' href="javascript:navigate('./groupModify.cyt?page=${i}&param=${text}&grpid=${grpid}&paginate=true');">${i}</a> 
				                    
				                    </c:if>
				                    
				                     <c:if test="${i == noOfPages}">
				                      <a class='page-numbers_small' href="javascript:navigate('./groupModify.cyt?page=${i}&param=${text}&grpid=${grpid}&paginate=true');">${i}</a> 
				                    
				                    </c:if>
				                    
				                    </c:if>
				                    </c:otherwise>
				                </c:choose>
				            </c:forEach>
				            
				            
				            
				            
					
					 <%--For displaying Next link --%>
				    <c:if test="${currentPage lt noOfPages}">
				        <a class='next page-numbers_small' href="javascript:navigate('./groupModify.cyt?page=${currentPage + 1}&param=${text}&grpid=${grpid}&paginate=true');">Next</a>
				    </c:if>
-->

										</div>
									</div>
									<!-- Pagination ends---->
									<!-- Search result Ends-->
									<div id="add_selected_contacts">
										<p style="margin: 0 auto 20px auto;">Save Changes To Group
										</p>
										<a href="#"
											onclick="javascript:processMultipleContact('Save');"
											title="Edit contacts - Disabled &#13; function under review"
											id="ADDTOGROUP" class="button_green1" style="width: 84px;">
											<span class="title_green1">Save</span>
										</a> <br>
										<!-- a title="Edit contacts - Disabled &#13; function under review" id="ADDTOGROUP" class="button_green1" style="width:84px; "> <span class="title_green1">Save</span></a> <br-->


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

$(document).ready(function() {

	$( "#grpname" ).autocomplete({
		source: '${pageContext.request.contextPath}/grouphome/GetUserGroupByname.cyt'
	});

	
});
</script>
<script type="text/javascript" charset="utf-8">
    $(document).ready(function () {
        $('#checkboxDemo input[type=checkbox],#radioDemo input[type=radio]').prettyCheckboxes();
        $('.inlineRadios input[type=radio]').prettyCheckboxes({'display':'inline'});
        $('.checkboxDemo input[type=checkbox]').prettyCheckboxes({'display':'inline'});
    });
</script>
</html>
