<%@ include file="/WEB-INF/jsp/common/include.jsp" %>

<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="content-type" content="text/html; charset=UTF-8">
<link rel="shortcut icon" href="favicon.ico" />

<title>inCyyte - My Groups</title>
<meta charset="utf-8">
<link rel="stylesheet" href="ui/css/reset.css">
<link rel="stylesheet" href="ui/css/layout.css">
<link rel="stylesheet" href="ui/css/buttons.css"  media="screen">
<link rel="stylesheet" href="ui/css/style_login.css">
<link rel="stylesheet" href="ui/css/accordionmenu.css" type="text/css">
<link rel="stylesheet" href="ui/css/icons_sprites.css">
<link rel="stylesheet" href="fonts/fonts_stylesheet.css">
<link rel="stylesheet" href="ui/css/style_social.css">
<link rel="stylesheet" href="ui/css/ratingbar.css" type="text/css"/>

<link rel="stylesheet" href="ui/css/ui.progress-bar.css" type="text/css"/>
<link rel="stylesheet" href="ui/modal/colorbox.css">
<script src="ui/js/jquery-1.4.2.min.js"></script>
<script src="ui/js/jquery-1.7.1.js"></script>
<script src="ui/js/search_script.js"></script>
<script src="ui/js/login.js"></script>
<script src="ui/js/jquery.color.js"></script>

<!-- Left Navigation script starts here -->
<script src="ui/js/left_menu.js"></script>
<!-- Left Navigation script ends here -->
<!--- placeholder Starts----->

<script src="ui/js/search_script.js"></script>

         <script type="text/javascript" src="ui/js/jquery-1.5.1.min.js"></script>
    	<script type="text/javascript" src="ui/js/jquery-ui-1.8.13.custom.min.js"></script>
    	<script type="text/javascript" src="ui/js/external/jquery.bgiframe-2.1.2.js"></script>
    	<script type="text/javascript" src="ui/js/communicator.js"></script>
    	<script type="text/javascript" src="ui/js/group.js"></script>
    	<script type="text/javascript" src="ui/js/jquery.form.js"></script> 
    	
    	<script src="ui/modal/colorbox/jquery.colorbox.js"></script>
        <script src="ui/js/jquery_profile_rating.js" type="text/javascript"></script>  
		<script src="ui/js/star_rating.js" type="text/javascript"></script>

		<!--  Modal
<script>
			$(document).ready(function(){
				//Examples of how to assign the ColorBox event to elements
				$(".photos").colorbox({rel:'photos'});
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
		</script>
<!--- Mddal--------------->

<!--- placeholder Starts----->
<script>

function  DeleteGroup(grpid) {
	var selected = '' ;
	if(grpid == 'test')
	 selected =$("#groupId").val();
	else
	 selected = grpid;	
	
	$("#ed_checked").val(selected) ;
	   var r=confirm("Are you sure you want to Delete the Group");
	   if (r != true) {
           window.location();
          return false ;
       	   }
	$("#group").ajaxSubmit({	
		type: 'POST',
		url: 'grouphome/deleteMultiple.cyt',
		success: function(data) {				
				window.location = "grouphome.cyt";																					
		},
		error: function(jqXHR, textStatus, errorThrown) {
			 $("#communicator").css("display", "");
		}
	});
}
$(document).ready(function(){
	 $('#group_details').hide();
  $(".inline").colorbox({inline:true});
$('#Deletebtn').bind('click', function(e){
e.preventDefault();
})
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
</script>
<script type="text/javascript">
    $(document).ready( function() {
      $(':checkbox').click(function () {
      var checkboxes = $("input[type='checkbox']");
      if(!checkboxes.is(":checked"))
      {
              $('#Deletebtn').bind('click', function(e){ e.preventDefault();});
              $('#Blockbtn').bind('click', function(e){e.preventDefault();});
              $('#Deletebtn').attr('class','button_disabled button_disabled_ie8');
              $('#Blockbtn').attr('class','button_disabled button_disabled_ie8');
      }
      else
      {
            $('#Blockbtn').unbind('click');
            $('#Deletebtn').unbind('click');
            $('#Deletebtn').attr('class','button_green1 button_enabled_ie8');
            $('#Blockbtn').attr('class','button_green1 button_enabled_ie8');
      }
       });
   
        // Select all
        $("A[href='#select_all']").click( function() {
            $("#" + $(this).attr('rel') + " INPUT[type='checkbox']").attr('checked', true);            
            $('#Blockbtn').unbind('click');
            $('#Deletebtn').unbind('click');
            $('#Deletebtn').attr('class','button_green1 button_enabled_ie8');
            $('#Blockbtn').attr('class','button_green1 button_enabled_ie8');
            return false;
        });
 
        // Select none
        $("A[href='#select_none']").click( function() {
            $("#" + $(this).attr('rel') + " INPUT[type='checkbox']").attr('checked', false);
              $('#Deletebtn').bind('click', function(e){ e.preventDefault();});
              $('#Blockbtn').bind('click', function(e){e.preventDefault();});
              $('#Deletebtn').attr('class','button_disabled button_disabled_ie8');
              $('#Blockbtn').attr('class','button_disabled button_disabled_ie8');
            return false;
        });
    });
</script>
<script type="text/javascript">
function split(val) {
    return val.split(/,\s*/);
}
function extractLast(term) {
    return split(term).pop();
}

$(document).ready(function() {
	$( "#search" ).autocomplete({
		source: '${pageContext.request.contextPath}/mycontacts/GetUserContactByFname.cyt'
	});
});
</script>

<!--- placeholder Ends----->
</head>
<%--<script type="text/javascript">
    $(window).load(function() {
        $(".loader").fadeOut("slow");
    })
</script>--%>
<body>
<%--
<div class="loader" style="background: url('${pageContext.request.contextPath}/ui/images/common/long_loading_bar.gif') 50% 50% no-repeat rgb(249,249,249)"></div>
--%>
<div id="gradient">
  <div class="extra">
    <!--header -->
    <div class="header36">
             <jsp:include page="../common/includes/header.jsp" />
    </div>
    <!--header end-->
    <div class="main">
      <!--content -->
      <article id="content">
        <div id="main_content">
          <div class="grid_9">
            <h1>Groups</h1>
            <nav>
              <script type="text/javascript" src="./ui/includes/leftmenu_groups.js"></script>
            </nav>
            <div class="hline"></div>
            <div id="advert"><img src="./ui/images/advert.png" alt="Advert"></div>
          </div>
          <div class="line"><span></span></div>
          <div class="grid_17">
            <!------ Search Box Starts -------->
            <div id="advanced_searchbox"  class="advanced_searchbox_ie8">
              <div id="searchbox" style="margin-bottom:0">
                <p class="sort_by_text"></p>
                <div id="searchmain" >
                  <p class="search_heading">Search</p>
                  <form id="searchForm">
                    <fieldset>
                    <div class="input">
                      <input type='text' name="search" id="search" value="Enter your search" />
                    </div>
                    <input type="submit" id="searchSubmit" value="" />
                    </fieldset>
                  </form>
                </div>
              </div>
              <nav id="alpha_list">
                <li><a href="./grouphome.cyt?param=A">A</a></li>
                <li><a href="./grouphome.cyt?param=B">B</a></li>
                <li><a href="./grouphome.cyt?param=C">C</a></li>
                <li><a href="./grouphome.cyt?param=D">D</a></li>
                <li><a href="./grouphome.cyt?param=E">E</a></li>
                <li><a href="./grouphome.cyt?param=F">F</a></li>
                <li><a href="./grouphome.cyt?param=G">G</a></li>
                <li><a href="./grouphome.cyt?param=H">H</a></li>
                <li><a href="./grouphome.cyt?param=I">I</a></li>
                <li><a href="./grouphome.cyt?param=J">J</a></li>
                <li><a href="./grouphome.cyt?param=K">K</a></li>
                <li><a href="./grouphome.cyt?param=L">L</a></li>
                <li><a href="./grouphome.cyt?param=M">M</a></li>
                <li><a href="./grouphome.cyt?param=N">N</a></li>
                <li><a href="./grouphome.cyt?param=O">O</a></li>
                <li><a href="./grouphome.cyt?param=P">P</a></li>
                <li><a href="./grouphome.cyt?param=Q">Q</a></li>
                <li><a href="./grouphome.cyt?param=R">R</a></li>
                <li><a href="./grouphome.cyt?param=S">S</a></li>
                <li><a href="./grouphome.cyt?param=T">T</a></li>
                <li><a href="./grouphome.cyt?param=U">U</a></li>
                <li><a href="./grouphome.cyt?param=V">V</a></li>
                <li><a href="./grouphome.cyt?param=W">W</a></li>
                <li><a href="./grouphome.cyt?param=X">X</a></li>
                <li><a href="./grouphome.cyt?param=Y">Y</a></li>
                <li><a href="./grouphome.cyt?param=Z">Z</a></li>
              </nav>
            </div>
            <!------ Search Box End-------->
            <!-- view_all_contacts Start -->
           
          <div id="view_all_contacts"> <a href="#" onclick="javascript:processMultipleContact('Delete');" title="Delete" id="Deletebtn" class="button_disabled button_disabled_ie8" style=" width:84px; float:left; margin:0 0 10px 0px; "> <span class="delete_bot">Delete</span></a> <br>
              <table width="100%" border="0" cellspacing="0" cellpadding="0" id="contact_list">
                <tr>
                  <td colspan="4" class="select_all">Select: <a rel="group_1" href="#select_all">All</a> | <a rel="group_1" href="#select_none">None</a></td>
                </tr>
                <c:forEach items="${userGroups}" var="group">
                <tr>
                  <td width="5%" valign="top">
                   <fieldset id="group_1">
                  <input name="numbers[]" type="checkbox" id="${group.groupId}" value="'${group.groupId}'"> 
                  </fieldset>    
                  </td>
                  <td width="12%" valign="top"><img src="./ui/images/group_photo.png" alt="Group Photo" class="photoframe"></td>
                  <td width="64%"><p class="font_18px">${group.groupName} </p>
                    <p>${group.groupSize} Members </p>
                    <p class="font_14px">
                        <a href="#" id='edit_<c:out value="${group.groupId}"/>' onclick="javascript:EditGrp('${group.groupId}');">&bull;Edit</a>
                        <a href="#" id='delete_<c:out value="${group.groupId}"/>' onclick="javascript:DeleteGroup('${group.groupId}')">&bull; Delete </a>
                        <a id='sentIncyyte_<c:out value="${group.groupId}"/>' href="javascript:sendGroupPollIncyyte('${group.groupId}','${group.groupName}');">&bull; Send InCyyte </a>
                    </p>
                  </td>
                  <td width="19%" align="right">
                    <p><a href="#group_details"  id='group_<c:out value="${group.groupId}"/>' onclick="javascript:processGroup('${group.groupId}','${group.description}','${group.groupName}','${group.groupSize}','${group.postCode}')" class="inline" >View Detail</a></p></td>
                </tr>
               </c:forEach>
              </table>
            </div>
            <!-- view_all_contacts Ends-->

             <div style="display: none;">
            <div id="group_details" >
                <table width="100%" border="0" cellspacing="0" cellpadding="0">
                <tr>
                  <td width="4%" valign="top"></td>
                  <td width="15%" valign="top"><img src="./ui/images/group_photo.png"  alt="User Photo" id=""></td>
                  <td width="30%" valign="top"><table width="100%" border="0" cellspacing="0" cellpadding="0">
                      <tr>
                        <td  class="real_name" id="vw_grpdesc" height="25" ></td>
                      </tr>
                          <tr>
                            <td colspan="1" height="20"></td>
                          </tr>
                           <tr>
                             <td class="group_desc">Description:</td>
                           </tr>
                      <tr>
                                  <td id="vw_grpname"> </td>
                      </tr>
                      <tr>
                        <td></td>
                      </tr>
                    </table></td>
                  <td width="51%" valign="top" ><table width="96%" border="0" align="right" cellpadding="0" cellspacing="0">
                       <tr>
                        <td width="48%" height="29">&nbsp;</td>
                        <td width="28%">&nbsp;</td>
                        <td width="24%">&nbsp;</td>
                      </tr>
                      <tr>
                        <td height="25">Location: </td>
                        <td colspan="2" id="vw_grploc" ></td>
                      </tr>
                      <tr>
                        <td colspan="1" height="13"></td>
                      </tr>
                      <tr>
                        <td height="25">Group Members:</td>
                      <td  id="vw_grpsize" ></td>
                        <td class="join_this_group"><a href="#">View Members</a></td>
                      </tr>
                      <tr  style="color:#A4A4A4;">
                        <td height="25">Number of Polls:</td>
                        <td></td>
                        <td class="join_this_group"><a href="#">View Polls</a></td>
                      </tr>
                      <tr  style="color:#A4A4A4;">
                        <td height="25">Overall Poll Reciepients: </td>
                        <td height="20"></td>
                        <td height="20">&nbsp;</td>
                      </tr>
                      <tr  style="color:#A4A4A4;">
                        <td height="25">Overall Responses: </td>
                        <td></td>
                        <td>&nbsp;</td>
                      </tr>
                    </table></td>
                </tr>
                <tr>
                  <td>&nbsp;</td>
                  <td class="green_hline">&nbsp;</td>
                  <td class="green_hline">&nbsp;</td>
                  <td class="green_hline">&nbsp;
                   <form:form  id="group"  commandName="group" method="post" >   
                 	<form:hidden path="groupId" id="groupId"/>
                 	<form:hidden path="groupName" id="groupName"/>
                 	<%
					String parameter = request.getParameter("param");
					String searchValue = request.getParameter("search");
					%>	
                 	<input type='hidden' id="pageNum" value="1"/>
                 	<input type='hidden' id="endOfFile" value="SHOW"/>
					<input type='hidden' id="param" value="<%=parameter%>" />
					<input type='hidden' id="searchValue" value="<%=searchValue%>" />
                 	<form:hidden path="checked" id="ed_checked" />
                 	 </form:form>
          </td>
                </tr>
              </table>
              <div class="contact_icons"> 
                <a href="javascript:EditGrp('test');"  class="edit">Edit this Group </a>  |
                <a href="javascript:DeleteGroup('test')" class="delete">Delete this Group </a>  | 
                <a href="javascript:sendGroupIncyyte()" class="invite">Send InCyyte</a>
              </div>
            </div></div>
         
          </div>
        </div>
      </article>
      <!--content ends -->
    </div>
  </div>
  <!--footer Starts -->
    <jsp:include page="../common/includes/footer.jsp" />

</div>
<!--footer ends -->
<script type="text/javascript" src="ui/js/googleanalytics.js"></script>

<script type="text/javascript" src="js/json_parse.js"></script>
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
	var totalDiv = document.getElementById('gradient');
	var totalHeight = totalDiv.offsetHeight;
	var scrolledHeight = window.pageYOffset;
	var y = scrolledHeight + window.innerHeight;
	var pageNumber = $('#pageNum').val();
	var param = $('#param').val();
	var search = $('#searchValue').val();
	if (y >= totalHeight) {
		pageNumber++;
		$('#pageNum').val(pageNumber);
		httpRequest.open("GET", 'getLoadedGroups.cyt?pageNumber=' + pageNumber + '&param='+param  + '&search='+ search , true);
		httpRequest.onreadystatechange = getGroupsAfterScroll;
		httpRequest.send(null);
	}
}
window.onscroll = yHandler;
function getGroupsAfterScroll() {	 
var resultText1 = httpRequest.responseText;
var resultsToDecideEndPoint = json_parse(resultText1);
var endOfFile = resultsToDecideEndPoint.endOfFile;
var displayEndOfResult = $('#endOfFile').val();
var currentGroupsList = document.getElementById('contact_list');
if(endOfFile == "FALSE"){
	if (httpRequest.readyState == 4 || httpRequest.readyState == 200 ) {
		var resultText = httpRequest.responseText;
		var groups = json_parse(resultText);
		var groupsList = groups.groups;
		for (var i = 0; i < groupsList.length; i++) {
			currentGroupsList.innerHTML += "<tr>"
				+ '<td width="5%" valign="top">'
				+ '<fieldset id="group_1">'
				+ '<input name="numbers[]" type="checkbox"' + groupsList[i].checked + ' id="' + groupsList[i].groupId + '" value="' + groupsList[i].groupId + '">'
				+ '</fieldset>'
				+ '</td>'
				+ '<td width="12%"><img src="./ui/images/group_photo.png" width="46" height="46" alt="Group Photo" class="photoframe"></td> '
				+ '<td width="64%"><p class="font_18px">' + groupsList[i].groupName + '</p>'
				+ ' <p>' + groupsList[i].groupSize + ' Members </p>'
				+'<p class="font_14px">' 
				+ '<a href="#" id="edit_' + groupsList[i].groupId + '"   onclick="javascript:EditGrp('+ "'" + groupsList[i].groupId  + "'" + ');"  >&bull; Edit</a>'
				+ '<a href="#" id="delete_' + groupsList[i].groupId + '"   onclick="javascript:DeleteGroup('+ "'" + groupsList[i].groupId  + "'" + ');"  >&bull; Delete</a>'
				+ '<a id="sentIncyyte_' + groupsList[i].groupId + '" href="javascript:sendGroupPollIncyyte('+ "'" + groupsList[i].groupId  + "' , '" + groupsList[i].groupName + "'" + ');" >&bull; Send InCyyte </a>'
				+ '</p></td>'
				+ '<td width="19%" align="right">'
				+ '<p><a href="#group_details" id="group_' + groupsList[i].groupId + '"'
				+ 'onclick="javascript:processGroup(' 
				+ "'" + groupsList[i].groupId 
				+ "','" + groupsList[i].description 
				+ "','"+ groupsList[i].groupName 
				+ "','" + groupsList[i].groupSize 
				+ "','" + groupsList[i].postCode 
				+ "');"+'"' + ' class = "inline cboxElement" > View Detail </a></p>'
				+ '</td>'
				+ '</tr>';
		}
	}
}else if ((endOfFile == "TRUE") && (displayEndOfResult == "SHOW")){
	currentGroupsList.innerHTML += "<tr>" 
		+ '<td width="5%" valign="top">'
		+ '</td>'
		+ '<td width="12%"></td> '
		+ '<td width="64%">'
		+ '<p style="font-family: bariol_boldbold, ie8_bariol_bold; font-size: 14px;">   End Of Group List  ' 
		+ '</p></td>'
		+ '<td width="19%" align="right"></td>'
		+ '</tr>';
		$('#endOfFile').val('STOP');
}


$(':checkbox').click(function () {
    var checkboxes = $("input[type='checkbox']");
    if(!checkboxes.is(":checked"))
    {
            $('#Deletebtn').bind('click', function(e){ e.preventDefault();});
            $('#Blockbtn').bind('click', function(e){e.preventDefault();});
            $('#Deletebtn').attr('class','button_disabled button_disabled_ie8');
            $('#Blockbtn').attr('class','button_disabled button_disabled_ie8');
    }
    else
    {
          $('#Blockbtn').unbind('click');
          $('#Deletebtn').unbind('click');
          $('#Deletebtn').attr('class','button_green1 button_enabled_ie8');
          $('#Blockbtn').attr('class','button_green1 button_enabled_ie8');
    }
     });
 
      // Select all
      $("A[href='#select_all']").click( function() {
          $("#" + $(this).attr('rel') + " INPUT[type='checkbox']").attr('checked', true);            
          $('#Blockbtn').unbind('click');
          $('#Deletebtn').unbind('click');
          $('#Deletebtn').attr('class','button_green1 button_enabled_ie8');
          $('#Blockbtn').attr('class','button_green1 button_enabled_ie8');
          return false;
      });

      // Select none
      $("A[href='#select_none']").click( function() {
          $("#" + $(this).attr('rel') + " INPUT[type='checkbox']").attr('checked', false);
            $('#Deletebtn').bind('click', function(e){ e.preventDefault();});
            $('#Blockbtn').bind('click', function(e){e.preventDefault();});
            $('#Deletebtn').attr('class','button_disabled button_disabled_ie8');
            $('#Blockbtn').attr('class','button_disabled button_disabled_ie8');
          return false;
      });

      
      
      $('#group_details').hide();
      $(".inline").colorbox({inline:true});
    $('#Deletebtn').bind('click', function(e){
    e.preventDefault();
    })
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
}
</script>
</body>
</html>
