<%@page import="com.incyyte.app.web.model.UserSettingsModel"%>
<%@page import="com.incyyte.app.web.SessionKeys"%>
<%@page import="com.incyyte.app.domain.InCyyteChart"%>
<%@page import="com.incyyte.app.domain.CyyteResponse"%>
<%@page import="com.incyyte.app.service.util.Utility"%>
<%@page import="com.incyyte.app.service.util.Constants"%>
<%@page import="com.incyyte.app.web.model.UserContactModel"%>
<%@page import="java.util.*"%>
<%@page import="com.incyyte.app.domain.User"%>
<%@ page import="org.apache.commons.lang.StringUtils"%>
<%@ include file="/WEB-INF/jsp/common/include.jsp"%>
<jsp:include page="../include/report_abuse.jsp" />
<jsp:include page="../include/pollEmailCount.jsp" />

<% InCyyteChart chart = (InCyyteChart)request.getSession().getAttribute(SessionKeys.INCYYTE_CHART); %>
<% UserSettingsModel userSettingsModel = (UserSettingsModel)request.getSession().getAttribute(SessionKeys.LOGIN_USER_SETTINGS); 
	User user = (User) session.getAttribute(SessionKeys.LOGIN_USER);	
%>
<link rel="stylesheet" href="ui/css/tooltip.css">
<link rel="stylesheet" href="ui/modal/colorbox.css">

<link rel="stylesheet" href="ui/css/prettyCheckboxes.css"
	type="text/css" media="screen">

<script type="text/javascript"
	src="ui/js/jquery-ui-1.8.16.custom.min.js"></script>
<script src="ui/js/jquery-1.8.1.js"></script>
<script src="ui/js/jquery-1.8.3.min.js" type="text/javascript"></script>

<script src="ui/js/prettyCheckboxes.js" type="text/javascript"
	charset="utf-8"></script>
<script src="ui/js/tooltip.js"></script>
<script src="ui/js/jquery.color.js"></script>
<script src="ui/js/search_script.js"></script>
<script src="ui/js/jquery_dashboard_rating.js" type="text/javascript"></script>
<script src="ui/js/star_rating.js" type="text/javascript"></script>
<script src="ui/modal/colorbox/jquery.colorbox.js"></script>
<script type="text/javascript" src="js/jquery.form.js"></script>
<script type="text/javascript" src="ui/js/jquery-ui.js"></script>
<script type="text/javascript" src="ui/js/jquery.lightbox-0.5.js"></script>
<script type="text/javascript" src="ui/js/validate/jquery.validate.js"></script>


<!-- Left Navigation script starts here -->
<script src="ui/js/left_menu.js"></script>
<script src="ui/js/jquery.selectbox-0.2.js"></script>
<!-- Left Navigation script ends here -->

<!-- Chart Script Start here -->
<script src="ui/js/charts/js/highcharts.js"></script>
<script src="ui/js/dashboard/dash_my_polls.js"></script>
<script src="ui/js/dashboard/dash_comments.js"></script>
<script src="ui/js/dashboard/dash_detail.js"></script>
<script src="ui/js/ba-linkify.js"></script>
<script src="ui/js/search_script.js"></script>
<script src="ui/js/table2CSV.js"></script>
<!-- Chart Script end here -->

<!-- Rating bar and profile starts here -->
<link rel="stylesheet" type="text/css" href="ui/css/ratingbar.css" />
<link rel="stylesheet" href="ui/css/style_login.css">
<link rel="stylesheet" href="ui/css/style.css">
<link rel="stylesheet" href="ui/css/style_social.css">

<script type="text/javascript" src="ui/js/jquery.ratingbar.js"></script>
<script src="ui/js/jquery.ui.datepicker.js"></script>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/ui/Fancy-box/jquery.fancybox.css"
	type="text/css" media="screen" />
<!-- this is for video play in flow player 3.2.12. -->
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
<script type="text/javascript" src="ui/js/jquery.placeholder.js"></script>

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
	
var checked =  $("#ed_checked").val();
var ids = checked.split(",");
for( var i = 1 ; i <= ids.length ; i++){
var labelid = "checkboxLabel_"+ids[i];
$('#'+labelid).addClass('checked');
}

$('#searchSubmit_new').click(function () {
	googleImageSearchForFlyer();
});

$('#searchSubmit_new_comments').click(function () {
	googleImageSearchForComments();
});

});
</script>
<script type="text/javascript">
function googleImageSearchForFlyer(){
	$('#googleSearchTrigger').val(1);
	var searchValue = document.getElementById("search_new");
	var searchLoad = document.getElementById("googleImagesScroll");
	var searchSelect = "quesImgSelect";
	loadGoogleSearchImages(searchValue, searchLoad, searchSelect);
}
function googleImageSearchForComments(){
	$('#googleSearchTrigger').val(1);
	var searchValue = document.getElementById("search_new_comments");
	var searchLoad = document.getElementById("googleImagesScrollComments");
	var searchSelect = "commentsImgSelect";
    loadGoogleSearchImages(searchValue, searchLoad, searchSelect);
}
function removeCbox() {
		$("#pollMessageContent").val("");
		parent.$.fn.colorbox.close();
       	window.location.reload();		
 }
  $(function() {
    $('input, textarea').placeholder();
   });
</script>

<script src="ui/js/googleSearch.js"></script>
<script type="text/javascript">
    //Following function is called when we call Show more images
     function showImages() {
    	var trigger = $('#googleSearchTrigger').val();
        imageSearch.gotoPage(trigger);
        $('#googleSearchTrigger').val(++trigger);
    }

    function loadImage(selectedImgContainer, url) {
        document.getElementById(selectedImgContainer).setAttribute("src", url);
        var index = url.lastIndexOf("/") + 1;
        var fileName = url.substr(index);
        document.getElementById("incyyte_photo_search_file").value = fileName;
        document.getElementById("incyyte_photo_search_url").value = url;
    }

   
</script>
<script type="text/javascript">
    google.load('search', '1');
    var imageSearch;
    function loadGoogleSearchImages(searchValue, searchLoad, searchSelect) {
        //Following function is executed when we click on Search
        var search_new = searchValue.value;
        searchLoad.innerHTML = "";
        imageCall(search_new);
        function imageCall(search) {
            imageSearch = new google.search.ImageSearch();
            imageSearch.setResultSetSize(6);
            // Set searchComplete as the callback function when a search is complete.
            // The imageSearch object will have results in it.
            imageSearch.setSearchCompleteCallback(this, searchComplete, [imageSearch]);
            imageSearch.execute(search);
        }

        function searchComplete() {
            if (imageSearch.results && imageSearch.results.length > 0) {
                var results = imageSearch.results;
                var imagesContainer;
                imagesContainer = '<table  width="294" border="0" cellspacing="0" cellpadding="0" style="margin-right: 10px;">';
                var count = 1;
                for (var i = 0; i < results.length; i++, count++) {
                    var result = results[i];
                    //Start of Each row start the row tag
                    if (i%3 == 0) {
                        imagesContainer = imagesContainer + '<tr>';
                    }
                    imagesContainer = imagesContainer + '<td><div><a><img onClick="javascript:loadImage(\'' + searchSelect + '\',this.src);" class="upload_photos_thumb_img" src="' + result.url + '"></a></div></td>';
                    //End of Each row close the row tag
                    if (count%3 == 0) {
                        imagesContainer = imagesContainer + '</tr>';
                    }
                }
                imagesContainer = imagesContainer + '</table>';
                searchLoad.innerHTML = searchLoad.innerHTML + imagesContainer;
            }
        }
    }
</script>

<script type="text/javascript" charset="utf-8">

    function myonclickhandlerPoll(t, val) {
        var checkBoxClassNames = document.getElementById("checkboxLabel_"+ val).className;
        $("#" + val + " INPUT[type='checkbox']").attr('checked', true);
	    if (checkBoxClassNames.indexOf("checked") == -1) {
            //If it does not contains then it will be removed after this.
            //Hence need to perform uncheck operation
            var selected =  $("#ed_checked").val();
            selected = selected + "," + val;
            $("#ed_checked").val(selected);
        } else {
            var selected =  $("#ed_checked").val();
            selected = selected.replace(val, "");
            $("#ed_checked").val(selected);
        }
    }

	function sendPollMessage(){
	    var contextVar = document.getElementById("contextPathVar").value;
            $("#PollMesageForm").ajaxSubmit({
                type: 'POST',
                url: 'sendMessageToPollRecipients.cyt',
                success: function (response) {
                if (response.search("success") == 0){
				$(".contactPoll_msg_txt_poll").text("Your message has been sent");
                   parent.$.fn.colorbox({'href':'div.contactPoll_msg', 'open':true, 'inline':true});
                   $('#cboxClose').remove();
                }else {
				$(".contactPoll_msg_txt_poll").text("Your message has not sent.");
				parent.$.fn.colorbox({'href':'div.contactPoll_msg', 'open':true, 'inline':true});
				$('#cboxClose').remove();
				}
			},
                
                error: function (jqXHR, textStatus, errorThrown) {
                    $("#communicator").css("display", "");
                    // setError("oldPwd", "You have entered an invalid password.");
                }
            });
        }



    $(document).ready(function () {
        $('.cbox input[type=checkbox]').prettyCheckboxes();
    });
</script>

<script type="text/javascript">
function playVideo(id,playerId,playUrl) {
	parent.$.fn.colorbox({href:'div#emailList'+id, open:true, inline:true});
	  $f(playerId, "js/flowplayer/flowplayer-3.2.16.swf", {
		    clip: {
		        url: playUrl,
		        autoPlay: false,
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
		            play: true,
		            scrubber: true,
		         tooltips: {
		             buttons: true,
		             fullscreen: 'Enter fullscreen mode'
		            }
		        }
		    },
		    onLoad: function(){
		    }
		});
}
</script>
<script type="text/javascript" charset="utf-8">
$(document).ready(function() {
	if($('#viewSilverMemberOption').val() == 'openSilverMemOptions'){
		 $(".commentsToggle").hide();
	     $(".SilverToggle").show();
	}

	$('#top_next_detail,#bottom_next_detail').click(function(){ 
		 event.preventDefault();	
		 $('#detailContent').load("loadnextdetail.cyt");
	});
	
	$('#top_prev_detail,#bottom_prev_detail').click(function(){ 
		 event.preventDefault();
		 $('#detailContent').load("loadprevdetail.cyt");
	});
});
</script>


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
</script>


<script type="text/javascript">
$(document).ready(function(){
		$("#importContact").click(function () {
			window.location = "importcontacts.cyt";
		});    	 
});

function addEmail(){
	//validateEmail();
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
	 else
	 {
		 if(emailArrVal != ""){
			 removeEmail(contactEmail);
		 }
	 }
	//validateEmail();
 }
 
 function shareContactEmail(){
		 //alert($("#emailArr").val());
		 if ($("#emailArr").val() == ""){
			 //DO NOTHING
		 }else{
		    	//if(validateEmail()){    				    		
		    		$("#sharedForm").ajaxSubmit({
		    			type: 'POST',
		    		    url: './shareIncyyte.cyt',
		   		        success: function (data) {
		   		        	if(data == "emailPollCountError"){
			   				    parent.$.fn.colorbox({'href':'div.pollEmailCountPopup_bg', 'open':true, 'inline':true, 'escKey':false, 'overlayClose':false,'closeBtn':false});
		   		        	}else{
		   		        		parent.$.fn.colorbox.close(); 
		   		        		window.location.reload(); 
		   		        	}
		   		        },
		   		        error: function (jqXHR, textStatus, errorThrown) {
		   	               
		   		        }
		    		});    		 
		    	//}
		    				 
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
        //validateEmail();
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


	function myonclickhandler(t,val) {
        if (t.checked) {
        	var check = document.getElementById("uncheckedlist").value ;
        
        	for (var i= 0 ; i<= check.length ; i++)
        	{
        	
        	 if (val== check[i]) 
        		 check=	check.trim(val) ; 
        	}
        
        	document.getElementById("uncheckedlist").value = check ;
       }
        else {
      
        	document.getElementById("uncheckedlist").value= document.getElementById("uncheckedlist").value+","+ val ;
        	alert(document.getElementById("uncheckedlist").value);        	
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
	var totalDiv = document.getElementById('scrollbar1');
	var totalHeight = totalDiv.offsetHeight;
	var scrolledHeight = window.pageYOffset;
	var y = scrolledHeight + window.innerHeight;
	var pageNumber = $('#pageNum').val();
	var param = $('#param').val();
	var search = $('#searchValue').val();
	//if (y >= totalHeight) {
		pageNumber++;
		$('#pageNum').val(pageNumber);
		httpRequest.open("GET", 'loadedContactsIncyyte.cyt?pageNumber=' + pageNumber + '&param='+param  + '&search='+ search , true);
		httpRequest.onreadystatechange = getContactsAfterScroll;
		httpRequest.send(null);
	//}
}

function getContactsAfterScroll() {
	 var resultText1 = httpRequest.responseText;
	var resultsToDecideEndPoint = json_parse(resultText1);
	var endOfFile = resultsToDecideEndPoint.endOfFile;
	var displayEndOfResult = $('#endOfFile').val();
	var currentContactList = document.getElementById('contactsTable');
	if(endOfFile == "FALSE"){
		if (httpRequest.readyState == 4 || httpRequest.readyState == 200 ) {
			var resultText = httpRequest.responseText;
			var contacts = json_parse(resultText);
			var contactList = contacts.contacts;
			for (var i = 0; i < contactList.length; i++) {
				currentContactList.innerHTML += "<tr>"
						+ '<td style="padding-left:10px;padding-right:10px;">'
						+ '<span id="group_1">'
						+ '<input type="checkbox" class="contactList" name="selectedGroupContactsList"'
						+ 'id="contactId' + contactList[i].contactid + '" value="' + contactList[i].email + '"/>'
					    + '</span>'							
						+ '</td>'
						+ '<td height="40" style="padding-right:10px;">'
						+ '<img	src="' + contactList[i].profileImgUrl + '" width="36" height="35" alt="User Photo">'						
						+ '</td>'						
						+ '<td>'+ contactList[i].contactEmail + '</td>'
						+ '<td>'+ contactList[i].username+ '</td>'	
						+ '</tr>';
			}
		}
	}else if ((endOfFile == "TRUE") && (displayEndOfResult == "SHOW")){
		currentContactList.innerHTML += "<tr>" 
			+ '<td width="5%" valign="top">'
			+ '</td>'
			+ '<td width="12%"></td> '
			+ '<td width="64%">'
			+ '<p style="font-family: bariol_boldbold, ie8_bariol_bold; font-size: 14px;">'
			+ '</p></td>'
			+ '<td width="19%" align="right"></td>'
			+ '</tr>';
			$('#endOfFile').val('STOP');
	}

	
}
</script>


<!--- Mddal--------------->
<!--[if IE 8]>
<link href="ui/css/ie8.css" media="screen" rel="stylesheet" type="text/css"/>
<![endif]-->
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

<script type="text/javascript">

$(document).ready(function () {
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
				size: 250,
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
			    			    	
				<%for (CyyteResponse cyyteResponse : chart.getCyyteResponses()) {%>
					['<%=cyyteResponse.getAnswer()%>',    <%=cyyteResponse.getResponse()%>],
			 	<%}%>			    	
			]
		}]		
		
	});

});
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
    
	$(function() {
	    var button = $('#dropBoxButton${status.index}');
	    var box = $('#dropBox${status.index}');
	    var form = $('#dropBoxContent${status.index}');
	    button.removeAttr('href');
	    button.mouseup(function(login) {
	        box.toggle();
	        button.toggleClass('active');
	    });
	    form.mouseup(function() { 
	        return false;
	    });
	    $(this).mouseup(function(login) {
	        if(!($(login.target).parent('#dropBoxButton${status.index}').length > 0)) {
	            button.removeClass('active');
	            box.hide();
	        }
	    });
	});
</script>




<div id="expanded_incyyte_container">

	<table width="100%" border="0" cellspacing="0" cellpadding="0">
		<tr>
			<td width="33%" class="navigation">
				<!--<a href="#" id="top_prev_detail">&lt; Previous</a>-->
			</td>
			<td width="33%" align="center" class="navigation"><a
				href="<%=session.getAttribute("backto")%>">Back to List</a></td>
			<td width="33%" align="right" class="navigation">
				<!--<a href="#" id="top_next_detail">Next &gt;</a-->
			</td>
		</tr>
	</table>

	<div id="detailContent">

		<div class="expanded_incyyte_heading">
			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0">
				<tr>
					<td width="78%">
						<h3>${chart.question} </h3>
					</td>
					<td width="22%" align="right">
						<!--  p>${chart.incyyte.createdDate}</p--> 
						<c:if test="${not empty chart.incyyte.uploadLocation && not empty chart.incyyte.upload_name}">
							<c:set var="url" value="${chart.incyyte.uploadLocation}" />
							<% 
									String name = chart.getIncyyte().getUpload_name();
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
									onClick="javascript:playVideo('${chart.incyyte.id}','player${chart.incyyte.id}', '${chart.incyyte.uploadLocation}')"><img
									src="ui/images/video_thumb.png" src="ui/images/video_thumb.png"
									style="display: block; float: right; margin: 10px 2px; width: 50px; height: 50px; border: 2px solid #ffffff; padding: 2px;" /></a>
								<div style="display: none;">
									<div id="emailList${chart.incyyte.id}" class="emailList ">
										<a style="cursor: pointer" id="player${chart.incyyte.id}"></a>
									</div>
								</div>
								<%
									} else if (extImages.contains(extension)) {
								%>
								<a href="${chart.incyyte.uploadLocation}"
									class="thumbnail fancybox-popup "> <img
									src="${chart.incyyte.uploadLocation}"
									style="display: block; float: right; margin: 10px 2px; width: 50px; height: 50px; border: 2px solid #ffffff; padding: 2px;" />
								</a> <span><img src="${chart.incyyte.uploadLocation}" /></span>
								<%
									} else if (extDocs.contains(extension)) {
								%>
								<a style="cursor: pointer" href=""
									onClick="window.open('${chart.incyyte.uploadLocation}','MyWindow'); return false;"
									target="_blank" class="thumbnail"> <img
									src="ui/images/view_docs_thumb.png"
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
							</p>
						</c:if>
						<c:if test="${not empty chart.incyyte.youtubeUrl}">
								<p>
								<b>Question file:</b>
								<div class="thumbnail">
						
								<a onClick="javascript:playYoutubeVideoQues('${chart.incyyte.id}' , '${chart.incyyte.youtubeUrl}');" >
								<img src="ui/images/video_thumb.png" src="ui/images/video_thumb.png" style="display: block; float: right; margin: 10px 2px; width: 50px; height: 50px; border: 2px solid #ffffff; padding: 2px;" /></a>
								
								<div style="display:none;">
									<div id="emailListYouTube_${chart.incyyte.id}" class="emailList " >
										 <iframe width='390' id="iFrameYouTube_${chart.incyyte.id}"  height='308'  src="ui/images/uploading_big_img.png" frameborder='0' type='text/html'></iframe>
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
		</div>
		<%
			int filesCount = 0;
		%>

		<c:forEach var="ans" items="${chart.incyyte.answers}"
			varStatus="ansStatus">
			<c:choose>
				<c:when test="${not empty ans.uploadCDN_url}">
					<%
						filesCount++;
					%>
				</c:when>
				<c:when test="${not empty ans.youtubeURLAnswer}">
				<%
						filesCount++;
					%>
				</c:when>
				
			</c:choose>
		</c:forEach>
		<%
			if (filesCount > 0) {
		%>
		<div class="expanded_incyyte_detail_answers"
			id="answerOptionFileImage">
			<table width="100%" border="0" cellspacing="0" id="">
				<tr>
					<td><h2>Answer option files :</h2></td>
				</tr>
				<tr>
					<td width="78%" style="padding-left: 10px;" bgcolor="#D8D8D8">
						<c:forEach var="ans" items="${chart.incyyte.answers}"
							varStatus="ansStatus">
							<c:choose>
								<c:when test="${not empty ans.uploadCDN_url}">
									<c:set var="answerExt" value="${ans.uploadExt}" />
									<c:set var="ansURL" value="${ans.uploadCDN_url}" />
									<%
										String extAnswer = (String) pageContext
																.getAttribute("answerExt");
														String ansVideos[] = { ".flv", ".mp4", ".mpg",
																".swf", ".wmv" };
														String ansImages[] = { ".gif", ".png", ".jpg",
																".jpeg", ".bmp" };
														String ansDocs[] = { ".wpd", ".wps", ".xml",
																".xlr", ".pdf" };
														String ansGooleDocs[] = { ".doc", ".docx", ".log",
																".rtf", ".txt", ".csv", ".pps", ".ppt",
																".xls", ".xlsx" };
														List<String> extAnsVideos = Arrays
																.asList(ansVideos);
														List<String> extAnsImages = Arrays
																.asList(ansImages);
														List<String> extAnsDocs = Arrays.asList(ansDocs);
														List<String> extAnsGoogleDocs = Arrays
																.asList(ansGooleDocs);
														String docUrl = "https://docs.google.com/viewer?url=";
														String ansUrl = (String) pageContext
																.getAttribute("ansURL");
														String viewUrl = docUrl + ansUrl;
									%>
									<%
										if (extAnsVideos.contains(StringUtils
																.lowerCase(extAnswer))) {
									%>

									<a style="cursor: pointer" class="thumbnail2"> <img
										onClick="javascript:playVideo('${ans.id}','player${ans.id}', '${ans.uploadCDN_url}')"
										src="ui/images/video_thumb.png" id="answerImageIncyyte"
										style="display: block; float: left; margin: 10px 2px; width: 50px; height: 50px; border: 2px solid #ffffff; padding: 2px;" />
										<span><b>${ans.answerOption}</b><br> <img
											src="ui/images/video_thumb.png" /></span></a>
									<div style="display: none;">
										<div id="emailList${ans.id}" class="emailList ">
											<a style="cursor: pointer" id="player${ans.id}"></a>
										</div>
									</div>
									<%
										} else if (extAnsImages.contains(StringUtils
																.lowerCase(extAnswer))) {
									%>
									<a style="cursor: pointer" href="${ans.uploadCDN_url}"
										class="thumbnail2 fancybox-popup "> <img
										src="${ans.uploadCDN_url}"
										style="display: block; float: left; margin: 10px 2px; width: 50px; height: 50px; border: 2px solid #ffffff; padding: 2px;" />
										<span><b>${ans.answerOption}</b><br> <img
											src="${ans.uploadCDN_url}" /></span></a>

									<%
										} else if (extAnsGoogleDocs.contains(StringUtils
																.lowerCase(extAnswer))) {
									%>
									<a style="cursor: pointer" href=""
										onClick="window.open('<%=viewUrl%>','MyWindow'); return false;"
										target="_blank" class="thumbnail2"> <img
										src="ui/images/view_docs_thumb.png"
										style="display: block; float: left; margin: 10px 2px; width: 50px; height: 50px; border: 2px solid #ffffff; padding: 2px;" />
										<span><b>${ans.answerOption}</b><br> <img
											src="ui/images/view_docs_thumb.png" /></span></a>
									<%
										} else if (extAnsDocs.contains(StringUtils
																.lowerCase(extAnswer))) {
									%>
									<a style="cursor: pointer" href=""
										onClick="window.open('${ans.uploadCDN_url}','MyWindow'); return false;"
										target="_blank" class="thumbnail2"> <img
										src="ui/images/view_docs_thumb.png"
										style="display: block; float: left; margin: 10px 2px; width: 50px; height: 50px; border: 2px solid #ffffff; padding: 2px;" />
										<span><b>${ans.answerOption}</b><br> <img
											src="ui/images/view_docs_thumb.png" /></span></a>
									<%
										}
									%>
								</c:when>
								<c:when test="${not empty ans.youtubeURLAnswer}">
								
								<a style="cursor: pointer" class="thumbnail2"> 
								<img onClick="javascript:playYoutubeVideoQues('${ans.id}_answer','${ans.youtubeURLAnswer}')" src="ui/images/video_thumb.png" id="answerImageIncyyte"	style="display: block; float: left; margin: 10px 2px; width: 50px; height: 50px; border: 2px solid #ffffff; padding: 2px;" />
								<span><b>${ans.answerOption}</b><br> <img src="ui/images/video_thumb.png" /></span></a>
								<div style="display:none;">
									<div id="emailListYouTube_${ans.id}_answer" class="emailList " >
										 <iframe width='390' id="iFrameYouTube_${ans.id}_answer"  height='308'  src="ui/images/uploading_big_img.png" frameborder='0' type='text/html'></iframe>
									</div>
								</div>	
								</c:when>
							</c:choose>
						</c:forEach>
					</td>
				</tr>
			</table>
		</div>
		<%
			}
		%>
		<div class="expanded_incyyte" style="width: 94%;">
        <form:form id="CommentsModel" commandName="CommentsModel" method="post">
        <form:input path="uploadCommentPhotoFile" type="file" id="uploadCommentPhotoFile" style="display:none;" />
        <form:hidden path="commentid" id="commentid" />
        <form:hidden path="uploadCommentType" id="uploadCommentType" />
        <form:hidden path="searchedFileURLComment" id="searchedFileURLComment" />
        <form:hidden path="searchedFileNameComment" id="searchedFileNameComment" />
        <form:hidden path="youtubeCommentVideoId" id="youtubeCommentVideoId" />
        <table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td width="60%" valign="top"><span class="font_16px">Poll
							Result</span>
					<!-- BR><span class="font_12px">${chart.incyyte.incyyteCreatedDateFmt}</span-->
						<div style='display: none'>
							<div id="view_anspics">
								<table style="width: 100%;">
									<c:forEach var="ans" items="${chart.incyyte.answers}"
										varStatus="ansStatus">
										<tr>
											<td style="width: 5%;"><c:choose>
													<c:when test="${not empty ans.uploadCDN_url}">
														<c:set var="extAnswer" value="${ans.uploadExt}" />
														<c:set var="ansUrl" value="${ans.uploadCDN_url}" />
														<%
															String extAnswer = (String) pageContext
																					.getAttribute("extAnswer");
																			String ansVideos[] = { ".flv", ".mp4", ".mpg",
																					".swf", ".wmv" };
																			String ansImages[] = { ".gif", ".png", ".jpg",
																					".jpeg", ".bmp" };
																			String ansDocs[] = { ".wpd", ".wps", ".xml",
																					".xlr", ".pdf" };
																			String ansGooleDocs[] = { ".doc", ".docx", ".log",
																					".rtf", ".txt", ".csv", ".pps", ".ppt",
																					".xls", ".xlsx" };
																			List<String> extAnsVideos = Arrays
																					.asList(ansVideos);
																			List<String> extAnsImages = Arrays
																					.asList(ansImages);
																			List<String> extAnsDocs = Arrays.asList(ansDocs);
																			List<String> extAnsGoogleDocs = Arrays
																					.asList(ansGooleDocs);
																			String docUrl = "https://docs.google.com/viewer?url=";
																			String ansUrl = (String) pageContext
																					.getAttribute("ansUrl");
																			String viewUrl = docUrl + ansUrl;
														%>
														<div class="thumbnail view_icon">
															<%
																if (extAnsVideos.contains(StringUtils
																						.lowerCase(extAnswer))) {
															%>
															<a style="cursor: pointer" href="${ans.uploadCDN_url}"
																class="thumbnail view_icon fancybox-popup "></a> <span><img
																src="ui/images/video_thumb.png" /></span>
															<%
																} else if (extAnsImages.contains(StringUtils
																						.lowerCase(extAnswer))) {
															%>
															<a style="cursor: pointer" href="${ans.uploadCDN_url}"
																class="thumbnail view_icon fancybox-popup "></a> <span><img
																src="${ans.uploadCDN_url}" /></span>
															<%
																} else if (extAnsGoogleDocs.contains(StringUtils
																						.lowerCase(extAnswer))) {
															%>
															<a style="cursor: pointer" href=""
																onClick="window.open('<%=viewUrl%>','MyWindow'); return false;"
																target="_blank" class="thumbnail view_icon"></a> <span><img
																src="ui/images/view_docs_thumb.png" /></span>
															<%
																} else if (extAnsDocs.contains(StringUtils
																						.lowerCase(extAnswer))) {
															%>
															<a style="cursor: pointer" href=""
																onClick="window.open('${ans.uploadCDN_url}','MyWindow'); return false;"
																target="_blank" class="thumbnail view_icon"></a> <span><img
																src="ui/images/view_docs_thumb.png" /></span>
															<%
																}
															%>
														</div>
													</c:when>
													<c:otherwise>
													&nbsp;
												</c:otherwise>
												</c:choose></td>
											<td style="width: 95%;">&nbsp;<label
												tabindex="${status.index}" class="font_20px">${ans.answerOption}</label>
											</td>
										</tr>
									</c:forEach>
									<tr>
										<td></td>
										<td align="right"><a href="#" id="display_ans_btn"
											class="button_green" style="width: 80px; float: right"> <span
												class="title_green"> Close </span>
										</a></td>
									</tr>
								</table>

							</div>
						</div>
						<div id="containersexpanded" style="height: 300px;"></div></td>
				</tr>
				<tr>
					<td height="55">
						<table width="100%" border="0" cellspacing="0" cellpadding="0">
							<tr>
								<td><h3>Statistics Break Down</h3></td>
								<td><a href="javascript:void(0)"
									onclick="submitReportAbuse(${chart.incyyte.id}, 'dashincomming.cyt')"
									class="report_abuse" id="report_abuse"><span></span>Report
										Abuse</a></td>
							</tr>
						</table>
					</td>
				</tr>
				<tr>
					<td valign="top"
						style="border: 2px solid #fff; border-left: none; border-right: none;">
						<table width="100%" border="0" cellspacing="0" cellpadding="0"
							class="statistics_table">
							<tr>
								<td width="33%" valign="top" class="border_right"><p
										style="margin-bottom: 15px;">
										<span>Gender:</span> <a href="#" class="right">More</a>
									</p>
									<table id="gender">
										<tr>
											<td height="40" width="30"><img
												src="ui/images/male_gender.png"></td>
											<td class="gender_rating">
												<%
													if (chart.getGenderChart().get(Constants.GENDER_MALE) != null) {
												%>
												<%=chart.getGenderChart().get(Constants.GENDER_MALE)%>% <%
 	}
 %>
											</td>
										</tr>
										<tr>
											<td><img src="ui/images/female_gender.png"></td>
											<td class="gender_rating">
												<%
													if (chart.getGenderChart().get(Constants.GENDER_FEMALE) != null) {
												%>
												<%=chart.getGenderChart().get(
							Constants.GENDER_FEMALE)%>% <%
 	}
 %>
											</td>
										</tr>
										<%
											if (chart.getGenderChart().get(Constants.GENDER_NOT_SURE) != null
														&& chart.getGenderChart()
																.get(Constants.GENDER_NOT_SURE) > 0) {
										%>
										<tr>
											<td height="40" width="30" align="left"><img
												src="ui/images/unspecified_gender.png" width="39"
												height="41"></td>
											<td class="gender_rating"><%=chart.getGenderChart().get(
							Constants.GENDER_NOT_SURE)%>%
											</td>
										</tr>
										<%
											}
										%>
									</table></td>
								<td width="34%" valign="top"><p
										style="margin-bottom: 15px;">
										<span>Poll Count:</span> <a href="#" class="right">More</a>
									</p>
									<p style="font-size: 14px;">Total Recipients -
										${recipientsCount}</p>
									<p style="font-size: 14px; margin-top: 10px;">Total
										Responses - ${chart.incyyte.totalResponded}</p> <c:set
										var="recipientsCount" value="${recipientsCount}" /> <c:set
										var="totalResponded" value="${chart.incyyte.totalResponded}" />
									<%--  <p style="font-size: 14px;margin-top: 10px;">Total Uresponded - ${chart.incyyte.totalResponded}</p>--%>
								</td>
							</tr>
						</table>
					</td>
				</tr>
				<tr>
					<td valign="top">&nbsp;</td>
				</tr>
                <!--
                 // Add Silver Membership option
                 //Add Add comment (only if enabled)
                 //Add Share this poll
                 // Add Display comments (only if enabled)
                -->
                <%
                    String code = (String) request.getParameter("code");
                    String from = (String) request.getParameter("frm");
                    String openSection = (String) request.getParameter("openSection");
                %>
                <input type="hidden" id="code" value="<%=code%>"/>
                <input type="hidden" id="from" value="<%=from%>" />
                <input type="hidden" id="viewSilverMemberOption" value="<%=openSection%>"/>

                <div id="commentsToggle">
                <tr>
                    <td valign="top">
                        <table width="100%" border="0" cellspacing="0" cellpadding="0" class="commentsToggle">
                            <tr>
                                <%
                                    if (StringUtils.equals(user.getUserType(), "BUSINESS_SILVER")) {
                                %>
                                <td width="58%" colspan="2">
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
                                <%
                                    }
                                %>
                            </tr>
                <%
							if (StringUtils.equals("Y", chart.getIncyyte().getAllowComment())
									&& StringUtils.equals("N", userSettingsModel.getDisableComments())) {
				%>
								<tr>
									<td width="42%" height="40" class="font_18px">
									Add Your Comment:
									</td>
								</tr>
									<tr>
										<td colspan="2">
                                            <textarea name="textarea" id="textarea"
												class="add_comment_ie8" cols="45" rows="5"
												placeholder="Post Comment Text"></textarea>
											<div id="commentErrorDiv">
												<span id="error" style="padding-left: 12px; display: none;"></span>
											</div>
											<br>
                                            <form:hidden path="comment" id="comment" />
                                            <form:hidden path="quesid" id="quesid" />
                                            <input type="hidden" name="questionid" id="questionid" value="${chart.incyyte.id}">
											<input type="hidden" name="returnpage" id="returnpage" value="${returnPage}">
                                            <input type="hidden" name="commentlst" id="commentlst" value="${size}">
									        <input type="hidden" id="googleSearchTrigger"/>
											</td>
									</tr>
                            <tr>
                            <td><a href="javascript:postcomment();"
                                           title="POST" id="addcmnt" class="button_green"
                                           style="width: 165px; float: left;" autofocus> <span
                                            class="title_green share_poll_ie8">POST</span></a>
                                    </td>
                                    <td><c:if test="${sharedPoll == 'true'}">
                                    	<c:if test="${chart.incyyteClosed == 'false'}">
	                                        <a title="SHARE" id="displaySharedEmailList"
	                                           class="button_green"
	                                           style="width: 165px; margin: 0 0 0 160px;"> <span
	                                                class="title_green share_poll_ie8">SHARE THIS POLL</span>
	                                        </a>
                                        </c:if>
                                    </c:if></td>
                            </tr>
                            <%
                                    } else {
                                %>
                                <tr align="right">
									<td><c:if test="${sharedPoll == 'true'}">
	                                    	<c:if test="${chart.incyyteClosed == 'false'}">
												<a title="SHARE" id="displaySharedEmailList"
													class="button_green"
													style="width: 165px; margin: 0 0 0 160px;"> <span
													class="title_green share_poll_ie8">SHARE THIS POLL</span>
												</a>
	                                        </c:if>
										</c:if></td>
								</tr>
                            <%
                                    }
                            %>
                        </table>
						</td>
					</tr>
                        <%
							if (StringUtils.equals("Y", chart.getIncyyte().getAllowComment())
									&& StringUtils.equals("N", userSettingsModel.getDisableComments())) {
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
							<table width="100%" border="0" cellspacing="0" cellpadding="0"
								class="comment_table">
								<c:forEach items="${comments}" var="CommentsModel" varStatus="theCount">
									<c:choose>
										<c:when test="${theCount.count mod 2 == 0}">
													<tr class="commentsWhite">
										</c:when>
										<c:otherwise>
													<tr class="commentsBlank">
										</c:otherwise>
									</c:choose>
											<c:choose>
												<c:when test="${not empty CommentsModel.userprofileimg}">
                                                    <c:set var="commentedUserProfilePic" value="${CommentsModel.userprofileimg}"/>
												</c:when>
                                                <c:otherwise>
                                                    <c:set var="commentedUserProfilePic" value="ui/images/default_avatar.png"/>
                                                </c:otherwise>
											</c:choose>
                                            <%
                                                    String cProfileImage =(String) pageContext.getAttribute("commentedUserProfilePic");
                                            %>
                                            <td width="8%" valign="top">
                                                <div id="dropBoxContainer2${theCount.index}" class="dropBoxContainer2">
                                                    <a href="#" id="dropBoxButton2${theCount.index}" class="dropBoxButton2">
                                                        <img width="32" height="32" class="photoframe" src="<%=cProfileImage%>">
                                                    </a>
                                                    <div style="clear: both"></div>
                                                    <div id="dropBox2${theCount.index}" class="dropBox2">
                                                        <div id="dropBoxContent2${theCount.index}"
                                                             class="dropBoxContent2">
                                                            <ul>
                                                                <li><a href="javascript:sendFriendRequest('${CommentsModel.commentby}', '${theCount.index}');" style="color: black; font-size: 14px;">Send Friend Request</a></li>
                                                            </ul>
                                                        </div>
                                                    </div>
                                                </div>
                                            </td>
                                            <td width="92%">
												<p class="font_12px">
													<font style="font-weight: bold;">${CommentsModel.commentby}</font>,
													<strong>${CommentsModel.commentPeriod}</strong>
												</p>
												<div id='message_<c:out value="${theCount.index}"/>'
													class="success_message" style="display: none;"></div> <c:set
													var="commentText" value="${fn:escapeXml(CommentsModel.comment)}" />
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
                                                <p class="commentText">
                                                    <span id="linkify_text_${theCount.index}" class="font_16px"></span>
                                                    <br>
                                                </p>
                                            </td></tr>
                                            <tr><td align="left">
                                                <c:choose>
                                                    <c:when test="${not empty CommentsModel.commentPicture}">
                                                        <img alt="" src="${CommentsModel.commentPicture}" style="width:39em; height:20em"/>
                                                    </c:when>
                                                </c:choose>
                                                <c:choose>
                                                    <c:when test="${not empty CommentsModel.youtubeCommentVideoURL}">
                                                        <iframe id="commentsVideoSelectPlay" style="width:39em; height:20em" class="upload_photos_container_big_img" src="${CommentsModel.youtubeCommentVideoURL}" frameborder='0' type='text/html'></iframe>
                                                    </c:when>
                                                </c:choose>
                                            </td></tr>
                                            <tr><td align="left">
                                                <div id='comments_icons_<c:out value="${theCount.index}"/>'>
                                                    &nbsp;&nbsp;
                                                    <a href="javascript:sendFriendRequest('${CommentsModel.commentby}', '${theCount.index}');" title="Friend Request">
                                                        <img src="images/addcontact-icon.png" width="15" align="middle" height="15"></a>&nbsp;&nbsp;
                                                    <a href="javascript:CommentIconAvailability(${theCount.count})" title="Send Message">
                                                        <img src="images/reply-icon.png" width="15" align="middle" height="15"></a>&nbsp;&nbsp;
                                                    <c:set var="commentby"  value="${CommentsModel.commentby}"></c:set>
                                                    <%
                                                        String userName = (String) pageContext.getAttribute("commentby");
                                                        if (StringUtils.equals(userName, user.getUsername())) {
                                                    %>
                                                    <c:choose>
                                                        <c:when test="${not empty CommentsModel.commentPicture}">
                                                            <a href="javascript:popupDeleteCommentPhoto('${CommentsModel.commentid}')" style="color: #ffffff">
                                                                <img src="ui/images/camera_icon.png"  align="middle" > </a>
                                                        </c:when>
                                                        <c:when test="${not empty CommentsModel.youtubeCommentVideoURL}">
                                                            <a	href="javascript:popupDeleteCommentVideo('${CommentsModel.commentid}' , '${CommentsModel.youtubeCommentVideoURL}' )" style="color: #ffffff">
                                                                <img src="ui/images/camera_icon.png"  align="middle" > </a>
                                                        </c:when>
                                                        <c:otherwise>
                                                            <a	href="javascript:processImageUpload('${CommentsModel.commentid}')" style="color: #ffffff" title="Upload Photo">
                                                                <img src="ui/images/camera_icon.png"  align="middle" > </a>
                                                        </c:otherwise>
                                                    </c:choose>
                                                    <%
                                                        }
                                                    %>
                                                    <div>
                                                        <span id='commenticonsuccess${theCount.count}' class="success_message" style="padding-left: 12px; display: none;"></span>
                                                    </div>
                                                </div>
                                            </td></tr>
                                        </table>
											</td>
								</c:forEach>
							</table>
						</td>
					</tr>
					<%
                        }
					%>
					<tr>
						<td valign="top">&nbsp;</td>
					</tr>
				</div>
			</table>
            </form:form>
            <%
            	String[] colors = new String[] { "#cfff00", "#6ecafc", "#c2002d",
            			"#1b303f", "#a8dffd", "#e2f4fe", "#e2ff66", "#f5ffcc",
            			"#da6681", "#f3ccd5" };
            	int i = 0;
            %>
			<div class="SilverToggle" style="display: none;">
				<h1>
					Now you can reach out to your poll recipients!
                    <a href="javascript:void(0)" id="hideSilvercomments">Hide Silver Member Options</a>
				</h1>
				<p>
					You can send promotional material, messages or videos to poll
					recipients based on how they <br> responded to you poll.
				</p>
				<c:forEach var="answer" items="${chart.incyyte.answers}" varStatus="ansStatus">
					<div class="header">
						<span style="background: <%=colors[i]%>"></span>${answer.answerOption}
					</div>
					<%
						i++;
					%>
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
												<img src="ui/images/male_icon.png">
												<span><a style="color: #000000;"
													href="javascript:displayMaleContacts(${answer.id})"><u>${answer.maleCount}</u></a></span>
											</c:when>
											<c:otherwise>
												<img src="ui/images/male_icon.png">
												<span>${answer.maleCount}</span>
											</c:otherwise>
										</c:choose>
									</div>

								</div>
								<div class="gender">
									<div class="icon">
										<c:choose>
											<c:when test="${answer.femaleCount gt 0}">
												<img src="ui/images/female_icon.png">
												<span><a style="color: #000000;"
													href="javascript:displayFemaleContacts(${answer.id})"><u>${answer.femaleCount}</u></a></span>
											</c:when>
											<c:otherwise>
												<img src="ui/images/female_icon.png">
												<span>${answer.femaleCount}</span>
											</c:otherwise>
										</c:choose>
									</div>

								</div>
								<div class="gender">
									<div class="icon">
										<c:choose>
											<c:when test="${answer.unspecifiedCount gt 0}">
												<img src="ui/images/unspecified_icon.png">
												<span><a style="color: #000000;"
													href="javascript:displayUnspecifiedContacts(${answer.id})"><u>${answer.unspecifiedCount}</u></a></span>
											</c:when>
											<c:otherwise>
												<img src="ui/images/unspecified_icon.png">
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
					<%
						UserContactModel usercModel = (UserContactModel) session
									.getAttribute("PollMesageForm");
					%>
					<form:hidden path="checked" id="ed_checked" />
					<div class="contactPoll_recipients">
						<div class="contactPoll_recipients_bg">
							<h1>
								Contact your poll<span> Recipients</span>
							</h1>
							<form:textarea id="pollMessageContent" path="pollMessageContent"
								rows="" cols=""></form:textarea>
							<%
								if (usercModel == null
											|| !StringUtils.isNotBlank(usercModel
													.getUploadedFileLocation())) {
							%>
							<div class="adddoclink">
								<span>Attach: </span><a href="javascript:void(0)"
									style="color: #ffffff" id="openuploadwindow">Flyer/Image |
								</a><a href="javascript:void(0)" style="color: #ffffff"
									id="openDocUploadwindow">Document </a><a
									href="javascript:void(0)" style="color: #ffffff"
									id="openVideoUploadwindow"> | Video ad.</a>
							</div>
							<%
								} else {
							%>
							<div id="incyyte_media_view">
								<span>View file</span>
								<div id="media_small_icon" style="width: 65px;">
									<ul>
										<li id="view_small_icon"><a class='inline'
											href="#view_files" alt="View" title="View"></a></li>
									</ul>
								</div>
							</div>
							<%
								}
							%>
						</div>
						<div class="button_wrap1">
							<a href="javascript:sendPollMessage()" class="button_green1"
								id="pollmsg"
								style="width: 162px; float: right; margin-right: 32px;"><span
								class="title_green1">SEND MESSAGE</span></a>
						</div>
					</div>
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
											<table width="100%" border="0" cellspacing="0"	cellpadding="0">
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
                    <tr valign="bottom" style="display: none;" id="incyyte_video_error_msg">
                        <td><span class="errorLabel">Please upload the correct video format (flv, mp4, ..)</span></td>
                    </tr>
                    <tr valign="bottom" style="display: none;" id="incyyte_video_error_msg2">
                        <td><span class="errorLabel">The uploaded video exceeds the maximum allowed size(5 MB)</span>
                        </td>
                    </tr>
                    <tr valign="bottom">
                        <td align="center" style="position: absolute;z-index: 200; float: left; margin-left: 118px; margin-top: 119px;"><img  src="ui/images/indicator-loader.gif" id="videoLoader"
                                                                                                                                              width="32" height="32"
                                                                                                                                              style="padding:8px 0 0 0;margin-bottom:20px 0 100px; "></td>
                    </tr>
                </table>
            </td>
        </tr>
        <tr>
            <td>
                <div class="upload_photo_searchbox" style="margin-bottom:0">
                    <p class="sort_by_text">Search Google Videos</p>
                    
                    <div class="searchmain">
                        <div>
                            <input type="text" name="search" id="search_new_question_videos"  onKeydown="Javascript: if (event.keyCode==13 || event.which==13) {$('#googleVideosScroll').html(''); makeYoutubeApiCall('search_new_question_videos' , 'start-index_promotion' , 'googleVideosScroll' , 'quesVideoSelect');}">
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
                        <iframe width='320' id="quesVideoSelect"  height='190' class="upload_photos_container_big_img" src="ui/images/uploading_big_img.png" frameborder='0' type='text/html'></iframe>
                        </div>
                    </td>
                </tr>
            </table>
        </tr>
        <tr>
            <td>
                <div class="upload_photo_add_more"><a href="javascript:showMoreVideos('search_new_question_videos' , 'start-index_promotion' , 'googleVideosScroll', 'quesVideoSelect');"> + Show more Videos </a></div>
            </td>
        </tr>
        <tr>
            <td colspan="2" valign="bottom"><span class="licence">You must have the licence to use this Video</span>
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
						<!----------Add Photos Start-------------------- -->
						<div style="display: none;">
							<div id="ModalPhotoWindow">
								<div id="modal_media_icon1">
									<ul>
										<li id="modal_videos1"><a href="javascript:void(0)"
											alt="Videos" title="Videos"></a></li>
										<li id="modal_photos1"><a href="javascript:void(0)"
											alt="Photos" title="Photos" class="active"></a></li>
										<li id="modal_docs1"><a href="javascript:void(0)"
											alt="Docs" title="Docs"></a></li>
									</ul>
								</div>
								<div id="add_photos" style="padding-bottom: 80px;"
									class="c_add_photos">
									<table width="522" border="0" cellspacing="0" cellpadding="0">
										<tr>
											<td class="heading1" colspan="2">Add Photos</td>
										</tr>
										<tr>
											<td valign="top" align="left" width="30%">
												<table width="100%" border="0" cellspacing="0"
													cellpadding="0">
													<tr>
														<td width="30%">
															<div class="fileInputs">
																<div id="incyyte_browse_photos" class="button_gray"
																	style="width: 140px; float: left; margin-bottom: 20px; position: absolute; top: 0px; left: 0px; z-index: 1;">
																	<span class="title_gray">BROWSE</span>
																</div>
															</div>
														</td>
														<td colspan="2" class="font_16px"><br>
														<span style="float: left;">File Name:</span> <span
															id="incyyte_photo_file_name"
															style="float: left; margin-left: 10px;"></span></td>
													</tr>
													<tr>
														<td colspan="3">&nbsp;</td>
													</tr>
													<tr valign="bottom" style="display: none;"
														id="incyyte_photos_error_msg">
														<td colspan="3"><span class="errorLabel">Please
																upload the correct image format (jpg, gif, ..)</span></td>
													</tr>
													<tr valign="bottom" style="display: none;"
														id="incyyte_photos_error_msg2">
														<td colspan="3"><span class="errorLabel">The
																uploaded image exceeds the maximum allowed size(2 MB)</span></td>
													</tr>
													<tr valign="bottom"
														style="position: absolute; z-index: 200; float: left; margin-left: 410px; margin-top: 140px;">
														<td colspan="3" align="center"><img
															src="ui/images/indicator-loader.gif" id="imageLoader"
															width="32" height="32"
															style="padding: 8px 0 0 0; margin-bottom: 20px 0 100px;"></td>
													</tr>
												</table>
											</td>
										</tr>
										<tr>
											<td>
												<div class="upload_photo_searchbox" style="margin-bottom: 0">
													<p class="sort_by_text">Search Google images</p>
													<div class="searchmain">
														<div>
															<input type="text" name="search" id="search_new" onKeydown="Javascript: if (event.keyCode==13 )  googleImageSearchForFlyer();" >
															<input type="submit" id="searchSubmit_new" value="" />
														</div>
													</div>
												</div>

											</td>
										</tr>
										<tr>
											<table border="0" cellspacing="0" cellpadding="0"
												style="margin-top: 15px;">
												<tr>
													<td valign="top">
														<!-- This is used to display Google images -->
														<div id="googleImagesScroll"
															style="width: 319px; height: 171px; overflow-x: hidden; overflow-y: auto; margin-right: 10px;">
														</div>
													</td>
													<td align="right">
														<div>
															<img id="quesImgSelect"
																class="upload_photos_container_big_img"
																src="ui/images/uploading_big_img.png">
														</div>
													</td>
												</tr>
											</table>

										</tr>
										<tr>
											<td>
												<div class="upload_photo_add_more">
													<a href="javascript:showImages();"> + Show more images
													</a>
												</div>
											</td>
										</tr>
										<tr>
											<td colspan="2" valign="bottom"><span class="licence">You
													must have the licence to use this image</span>

												<div title="Upload Now" id="incyyteUploadPhotoButton"
													class="button_red" style="width: 140px; float: right;">
													<span class="title_red1">UPLOAD NOW</span>
												</div></td>
										</tr>
									</table>
								</div>
							</div>
						</div>
						<!----------Add Photos end-------------------->
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
															src="ui/images/indicator-loader.gif" id="docLoader"
															width="32" height="32"
															style="padding: 8px 0 0 0; margin-bottom: 20px 0 100px;"></td>
													</tr>
													<tr>
														<td>&nbsp;</td>
													</tr>
													<tr>
														<td><div id="video_tumb_container">
																<img width="260" height="260" src="ui/images/photo1.png">
															</div></td>
													</tr>
												</table>

											</td>
											<td align="center" valign="top" id="incyyte_doc_loaded"
												style="display: none;" width="70%">
												<div id="#video_thumbs">
													<ul id="videos">
														<li><a id="addDoclink" href="#"> <img
																src="ui/images/view_docs_thumb.png" class="photos_thumb"
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
						<!----------Add Comment  Photos Start-------------------- -->
						<div style="display: none;">
							<div id="ModalCommentPhotoWindow">
								<div id="modal_media_comments_icon">
									<ul>
										<li id="modal_photos_comments"><a
											href="javascript:void(0)" alt="Photos" title="Photos" class="active"></a></li>
										<li id="modal_videos_comments"><a
											href="javascript:void(0)" alt="Videos" title="Videos" class=""></a></li>
									</ul>
								</div>
								<div id="add_photos_comments" style="padding-bottom: 80px;"
									class="c_add_photos_comments">
									<table width="522" border="0" cellspacing="0" cellpadding="0">
										<tr>
											<td class="heading1" colspan="2">Add photos to your comment</td>
										</tr>
										 <tr>
            <td valign="top" align="left" width="30%">
                <table width="100%" border="0" cellspacing="0" cellpadding="0">
                    <tr>
                         <td width="30%" >
                            <div class="fileInputs">
                                <div id="incyyte_browse_comments_photos" class="button_gray"
                                     style=" width:140px; float:left; margin-bottom:20px; position: absolute; top: 0px;left: 0px; z-index: 1;">
                                    <span class="title_gray">BROWSE</span></div>
                            </div>
                        </td>
                        <td colspan="2" class="font_16px"><br><span style="float: left;">File Name:</span>
                            <span id="comments_photo_file_name" style= "float: left; margin-left: 10px;" ></span></td>
                    </tr>
                    <tr>
                        <td colspan="3">&nbsp;</td>
                    </tr>
                    <tr valign="bottom" style="display: none;" id="comments_photo_error_msg">
                        <td colspan="3"><span class="errorLabel">Please upload the correct image format (jpg, gif, ..)</span></td>
                    </tr>
                    <tr valign="bottom" style="display: none;" id="comments_photo_error_msg2">
                        <td colspan="3"><span class="errorLabel">The uploaded image exceeds the maximum allowed size(2 MB)</span></td>
                    </tr>
                    <tr valign="bottom" style="position: absolute;z-index: 200; float: left; margin-left: 410px; margin-top: 140px;">
                        <td colspan="3" align="center"><img src="ui/images/indicator-loader.gif" id="commentsImageLoader"
                                                            width="32" height="32"
                                                            style="margin-bottom:20px 0 100px;display:none; padding:8px 0 0 0;"></td>
                    </tr>
                </table>
            </td>
        </tr> 
										<tr>
											<td>
												<div class="upload_photo_searchbox" style="margin-bottom: 0">
													<p class="sort_by_text">Search Google images</p>
													<div class="searchmain">
														<div>
															<input type="text" name="search" id="search_new_comments" onKeydown="Javascript: if (event.keyCode==13 ) googleImageSearchForComments();">
															<input type="submit" id="searchSubmit_new_comments"
																value="" />
														</div>
													</div>
												</div>
											</td>
										</tr>
										<tr>
											<table border="0" cellspacing="0" cellpadding="0"
												style="margin-top: 15px;">
												<tr>
													<td valign="top">
														<!-- This is used to display Google images -->
														<div id="googleImagesScrollComments"
															style="width: 319px; height: 171px; overflow-x: hidden; overflow-y: auto; margin-right: 10px;">
														</div>
													</td>
													<td align="right">
														<div>
															<img id="commentsImgSelect"
																class="upload_photos_container_big_img"
																src="ui/images/uploading_big_img.png">
														</div>
													</td>
												</tr>
											</table>

										</tr>
										<tr>
											<td>
												<div class="upload_photo_add_more">
													<a href="javascript:showImages();"> + Show more images
													</a>
												</div>
											</td>
										</tr>
										<tr>
											<td colspan="2" valign="bottom"><span class="licence">You
													must have the licence to use this image</span>
												<div title="Upload Now" id="CommentPhotoButton"
													class="button_red" style="width: 140px; float: right;">
													<span class="title_red1">UPLOAD NOW</span>
												</div></td>
										</tr>
									</table>
								</div>
							</div>
						</div>
						<!----------Add Comment Photos end-------------------->
						
							<!----------Add Youtube  Videos Comments Start-------------------- -->
						<div style="display: none;">
							<div id="ModalCommentVideoWindow">
								<div id="modal_media_comments_icon">
									<ul>
										<li id="modal_photos_comments1"><a
											href="javascript:void(0)" alt="Photos" title="Photos" class=""></a></li>
										<li id="modal_videos_comments1"><a
											href="javascript:void(0)" alt="Videos" title="Videos" class="active"></a></li>
									</ul>
								</div>
								<div id="add_videos_comments" style="padding-bottom: 80px;"
									class="c_add_photos_comments">
									<table width="522" border="0" cellspacing="0" cellpadding="0">
										<tr>
											<td class="heading1" colspan="2">Add Videos to your comment</td>
										</tr>
										 <tr>
            <td valign="top" align="left" width="30%">
                <table width="100%" border="0" cellspacing="0" cellpadding="0">
                    <tr>
                         <td width="30%" >
                        </td>
                    </tr>
                    <tr>
                        <td colspan="3">&nbsp;</td>
                    </tr>
                     <tr valign="bottom" style="display: none;" id="comment_video_error_msg">
                        <td><span class="errorLabel">Please upload the correct video format (flv, mp4, ..)</span></td>
                    </tr>
                    <tr valign="bottom" style="display: none;" id="comment_video_error_msg2">
                        <td><span class="errorLabel">The uploaded video exceeds the maximum allowed size(5 MB)</span>
                        </td>
                    </tr>
                    <tr valign="bottom">
                        <td align="center" style="display:none ; position: absolute;z-index: 200; float: left; margin-left: 118px; margin-top: 119px;"><img  src="ui/images/indicator-loader.gif" id="videoLoader"
                                                                                                                                              width="32" height="32"
                                                                                                                                              style="padding:8px 0 0 0;margin-bottom:20px 0 100px; "></td>
                    </tr>
                </table>
            </td>
        </tr> 
										<tr>
											<td>
												<div class="upload_photo_searchbox" style="margin-bottom: 0">
													<p class="sort_by_text">Search Youtube Videos</p>
													<div class="searchmain">
														<div>
															<input type="text" name="search" id="search_video_comments" onKeydown="Javascript: if (event.keyCode==13 ){$('#googleVideosScrollComments').html(''); makeYoutubeApiCall('search_video_comments' , 'start-index' , 'googleVideosScrollComments' , 'commentsVideoSelect');}">
															<input type="submit" id="searchSubmit_video_comments"
																value="" />
														</div>
													</div>
												</div>
											</td>
										</tr>
										<tr>
											<table border="0" cellspacing="0" cellpadding="0"
												style="margin-top: 15px;">
												<tr>
													<td valign="top">
														<!-- This is used to display Google images -->
														<div id="googleVideosScrollComments"
															style="width: 319px; height: 171px; overflow-x: hidden; overflow-y: auto; margin-right: 10px;">
														</div>
													</td>
													<td align="right">
														<div>
															<iframe width='320' id="commentsVideoSelect"  height='190' class="upload_photos_container_big_img" src="ui/images/uploading_big_img.png" frameborder='0' type='text/html'></iframe>
														</div>
													</td>
												</tr>
											</table>
										</tr>
										<tr>
											<td>
												<div class="upload_photo_add_more">
													<a href="javascript:showMoreVideos('search_video_comments' , 'start-index' , 'googleVideosScrollComments' , 'commentsVideoSelect');"> + Show More Videos
													</a>
												</div>
											</td>
										</tr>
										<tr>
											<td colspan="2" valign="bottom"><span class="licence">You
													must have the licence to use this video</span>
												<div title="Upload Now" id="CommentVideoButton"
													class="button_red" style="width: 140px; float: right;">
													<span class="title_red1">UPLOAD NOW</span>
												</div></td>
										</tr>
									</table>
								</div>
							</div>
						</div>
						<!----------Add Comment Youtub eVideos  end-------------------->

		<c:forEach items="${comments}" var="CommentsModel" varStatus="theCount">
						<!----------view Photos Start-------------------->
								<div style="display: none;">
								<div id="view_comment_photos_${CommentsModel.commentid}" class="c_add_photos"
									style="width: 422px; height: 300px;">
									<div id="modal_media_comments_icon">
									<ul>
										<li id="modal_view_photos_comments"><a
											href="javascript:void(0)" alt="Photos" title="Photos" class="active"></a></li>
									</ul>
								</div>
									<table width="100%" border="0" cellspacing="0" cellpadding="0">
										<tr>
											<td class="heading1" colspan="2">View Photos of Comments</td>
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
																<span id="view_comment_photo_file_name"></span>
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
														<li>
                                                            <a id="commentPhotolinkId" href="" target="_blank" class="group1 fancybox-popup">
                                                            <img id="commenthotoImg" src="${CommentsModel.commentPicture}" class="photos_thumb" alt="tour" />
														</a></li>
													</ul>
												</div>
											</td>
										</tr>
										<tr>
											<td colspan="2" valign="bottom"><span class="licence">You
													must have the licence to use this image</span>
												<div title="Delete" class="button_red" style="width: 140px; float: right;">
													<a  href="javascript:deleteCommentPhoto( '${CommentsModel.commentid}' , 'Image' );" ><span class="title_red">DELETE</span></a>
												</div></td>
										</tr>
									</table>
								</div>
								<!----------view comment Photos end-------------------->
								
								
								<!----------view Videos Comments Start-------------------->
					<div style="display: none;">
								<div id="view_comment_videos_${CommentsModel.commentid}" class="c_add_photos"
									style="width: 422px; height: 300px;">
									<div id="modal_media_comments_icon">
									<ul>
										<li ><a
											href="javascript:void(0)" alt="Photos" title="Photos" class="active"></a></li>
										<li  id="modal_view_videos_comments"><a
											href="javascript:void(0)" alt="Photos" title="Photos" class="active"></a></li>
									</ul>
								</div>
									<table width="100%" border="0" cellspacing="0" cellpadding="0">
										<tr>
											<td class="heading1" colspan="2">View Videos of Comments</td>
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
																<span id="view_comment_video_file_name"></span>
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
														<li>
													<a id="videolinkId" >
                                   						 <img   id="viewQuesYouTubeVideoId" src="ui/images/video_thumb.png"  onClick="javascript:playCommentVideo( '${CommentsModel.commentid}');"  class="photos_thumb" alt="tour"/>
                                        					<div style="display:none;">
																<div id="emailListYouTube_${CommentsModel.commentid}" class="emailList " >
											    					<iframe width='390' id="iFrameYouTube_${CommentsModel.commentid}"  height='308'  src="ui/images/uploading_big_img.png" frameborder='0' type='text/html'></iframe>
											    				</div>
															</div>  
																		
														</a></li>
													</ul>
												</div>
											</td>
										</tr>
										<tr>
											<td colspan="2" valign="bottom"><span class="licence">You
													must have the licence to use this image</span>
												<div title="Delete" class="button_red" style="width: 140px; float: right;">
													<a  href="javascript:deleteCommentPhoto( '${CommentsModel.commentid}' , 'Video');" ><span class="title_red">DELETE</span></a>
												</div></td>
										</tr>
									</table>
								</div>
								<!----------view comment Videos end-------------------->
								
						</c:forEach>	
					</div>


					<%
						if (usercModel != null) {
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
								<!----------Add Video Starts -------------------->
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
													 <c:choose>
                                                    <c:when test="${not empty CommentsModel.commentPicture}">
                                                        <img alt="" src="${CommentsModel.commentPicture}" style="width:39em; height:20em"/>
                                                        <br>
                                                        <br>
                                                    </c:when>
                                                </c:choose>
                                                <% if (usercModel != null && StringUtils.isNotBlank(usercModel.getUploadedFileLocation())
                                                		&& (usercModel.getUploadedFileLocation().length() ==  41) ) { %>
													<a id="videoIdPromotion" >
                                   						 <img   id="viewYouTubeVideoIdPromotion" src="ui/images/video_thumb.png"  onClick="javascript:playYoutubeVideoForpromotion( 'videoIdPromotion' , '<%=usercModel.getUploadedFileLocation()%>');"  class="photos_thumb" alt="tour"/>
                                        					<div style="display:none;">
																<div id="emailListYouTubevideoIdPromotion" class="emailList " >
											    					<iframe width='390' id="iFrameYouTubevideoIdPromotion"  height='308'  src="ui/images/uploading_big_img.png" frameborder='0' type='text/html'></iframe>
											    				</div>
															</div>  		
														</a>
													<%} else { %>	
														<a onClick="javascript:playVideo('${chart.incyyte.id}','player${chart.incyyte.id}', '<%=usercModel.getUploadedFileLocation()%>')"><img
																class="photos_thumb" src="ui/images/video_thumb.png" /></a>
															<div style="display: none;">
																<div id="emailList${chart.incyyte.id}" class="emailList ">
																	<a style="cursor: pointer" id="player${chart.incyyte.id}"></a>
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
								<!------------- Add ULR end------------->
								<!----------Add Photos Start-------------------->
								<div id="view_photos" class="c_add_photos"
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
								<!----------Add Photos end-------------------->
								<!----------Add docs Start-------------------->
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
 				String docs[] = { "wpd", "wps", "xml", "xlr", "pdf" };
 				String gooleDocs[] = { "doc", "docx", "log", "rtf",
 						"txt", "csv", "pps", "ppt", "xls", "xlsx" };
 				List<String> extDocs = Arrays.asList(docs);
 				List<String> extGoogleDocs = Arrays.asList(gooleDocs);
 				String docUrl = "https://docs.google.com/viewer?url=";
 				String url = (String) pageContext
 						.getAttribute("quesUrl");
 				String viewUrl = docUrl + url;
 %> <%
 	if (extDocs.contains(extension)) {
 %>
												<div id="#view_doc_thumbs">
													<ul id="videos">
														<li><a id="doclinkId" style="cursor: pointer"
															href='<%=viewUrl%>'
															onClick="window.open('<%=url%>','MyWindow'); return false;"
															target="_blank"><img
																src="ui/images/view_docs_thumb.png" class="photos_thumb"
																alt="tour" /></a></li>
													</ul>
												</div> <%
 	}
 				if (extGoogleDocs.contains(extension)) {
 %>
												<ul id="videos">
													<li><a id="doclinkId" style="cursor: pointer" href=""
														onClick="MyWindow=window.open('<%=viewUrl%>','MyWindow'); return false;"
														target="_blank"><img
															src="ui/images/view_docs_thumb.png" class="photos_thumb"
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
								<!----------Add docs end-------------------->
							</div>
						</div>
					</div>
					<%
						}
					%>
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
		</div>

		<div style="display: none;">
			<div id="sharedEmailList">
				<div id="share_contacts">
					<h3 class="heading1" style="padding: 2px 0 8px 0px;">Share with your Contacts</h3>
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

						<form:form id="sharedForm" name="sharedForm"
							commandName="inCyyteForm" method="post">
							<div class="viewport">
								<div class="overview">
									<table width="380" border="0" cellspacing="0" cellpadding="0"
										id="contact_list">
										<tr>
											<td colspan="12" class="select_all">Select: <a
												rel="group_1" href="javascript:checked('#selectall');">All</a>
												| <a rel="group_1"
												href="javascript:checked('#select_none');">None</a></td>
										</tr>
									</table>
									<div style="width: 380px; height: 4px;"></div>
									<!-- <div style="width:400px; height:200px; overflow:auto;"> -->
									<div id="contactShareScroll"
										style="width: 380px; height: 200px; overflow-x: hidden; overflow-y: auto;">
										<table width="350" border="0" id="contactsTable" cellspacing="0" cellpadding="0">
											<c:forEach items="${emailContactList}" var="UserContactModel">
												<tr>
													<td style="padding-left: 10px; padding-right: 10px;">
														<span id="group_1"> <c:choose>
																<c:when
																	test="${UserContactModel.receivedIncyyte == true}">
																	<img alt="already received this incyyte"
																		src="ui/images/check_save.png" />
																</c:when>
																<c:otherwise>
																	<input type="checkbox" class="contactList"
																		name="selectedGroupContactsList"
																		onclick="selectContact('contactId'+${UserContactModel.contactid})"
																		id="contactId${UserContactModel.contactid}"
																		value="${UserContactModel.email}" />
																</c:otherwise>
															</c:choose>

													</span>
													</td>
													<td height="40" style="padding-right: 10px;"><img
														src='${UserContactModel.profile_img}' width="36"
														height="35" alt="User Photo"></td>
													<td class="font_12px">${UserContactModel.email}</td>
													<td class="font_12px">${UserContactModel.username}</td>
												</tr>
											</c:forEach>
										</table>
										<table width="20%" border="0" cellpadding="0" cellspacing="0">
										  <tr style="padding-right: 300px;">
											<td align="middle" style="padding-left: 230px;" >
												<div><input value="Display more contacts" type="button" onClick="javascript:yHandler()" style="cursor: pointer;border: solid 1px grey;" id="share_poll"></div>
											</td>
										</tr>
									</table> 
									</div>
									<div style="width: 380px; height: 4px; float: left;"></div>
									<table>
										<tr>
											<td valign="top"><form:textarea cssClass="questionbox"
													path="emailArr" onchange="javascript:addEmail()"
													id="emailArr"
													placeholder="Add one or more email addresses separated by a space"
													onFocus="this.placeholder = ''"
													onBlur="this.placeholder = 'Add one or more email addresses separated by a space'"></form:textarea>
												<BR> <span id="email_error" class="error"
												style="font: 14px/20px 'bariol_regularregular', 'ie8_bariol_regular'; color: #C2002D; margin-left: 12px"></span>
											</td>
										</tr>
									</table>
								</div>
							</div>
						</form:form>
					</div>
					<table width="380px" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td valign="bottom" width="50%" align="left"><label
								class="panelLink" id="importContact"><span
									class="importSpan" title="Import Contacts">Import new
										contacts</span></label></td>
							<td valign="top">
								<button type="button" title="Share inCyyte" id="add_email_btn"
									class="button_green1 share_bot_margin_ie8"
									onclick="shareContactEmail();"
									style="width: 105px; margin: 10px 0 0 50px;">
									<span class="title_green1 share_bot_ie8">SHARE</span>
								</button>
							</td>
						</tr>
					</table>

				</div>
			</div>
		</div>

	</div>

	<table width="100%" border="0" cellspacing="0" cellpadding="0">
		<tr>
			<td width="33%" class="navigation">
				<!--<a href="#" id="bottom_prev_detail">&lt; Previous</a-->
			</td>
			<td width="33%" align="center" class="navigation"><a
				href="<%=session.getAttribute("backto")%>">Back to List</a></td>
			<td width="33%" align="right" class="navigation">
				<!--<a href="#" id="bottom_next_detail">Next &gt;</a-->
			</td>
		</tr>
	</table>
	<form:form id="friendRequest" commandName="CommentsModel" method="post">
		<form:hidden path="commentby" id="commentby" />
	</form:form>
</div>
<style>
.emailList {
	width: 410px;
}

.emailList a object {
	width: 390px;
}
</style>
<script type="text/javascript" src="js/json_parse.js"></script>
<script type="text/javascript">

	$("#modal_photos_comments, #modal_photos_comments1").click(function(){
    	$.fn.colorbox({'href':'div#ModalCommentPhotoWindow', 'open':true, 'inline':true});
	});
	$("#modal_videos_comments , #modal_videos_comments1").click(function(){
		parent.$.fn.colorbox({'href':'div#ModalCommentVideoWindow', 'open':true, 'inline':true});
    });
	
	$("#modal_videos, #modal_videos1, #modal_videos2").click(function(){
        parent.$.fn.colorbox({'href':'div#VideoModalWindow', 'open':true, 'inline':true});
    });
    $("#openuploadwindow, #modal_photos, #modal_photos1 , #modal_photos2").click(function(){
        $.fn.colorbox({'href':'div#ModalPhotoWindow', 'open':true, 'inline':true});
    });
    $("#modal_docs, #modal_docs2, #modal_docs1").click(function(){
        $.fn.colorbox({'href':'div#ModalDocWindow', 'open':true, 'inline':true});
    });
    //This is for adding images in COMMENTS.
    function processImageUpload(commentId) {
        $('#commentid').val(commentId);
        $.fn.colorbox({'href':'div#ModalCommentPhotoWindow', 'open':true, 'inline':true});
    };
    function popupDeleteCommentPhoto(commentId) {
        $('#commentid').val(commentId);
        $.fn.colorbox({'href':'div#view_comment_photos_'+commentId , 'open':true, 'inline':true});
    };
   
    function popupDeleteCommentVideo(commentId , commentVideoURL ) {
    	var videoId  = "" ;
    	//By assuming Video url for youtube  is of 41 characters and videoId is of 11 characters .
    
    	if(commentVideoURL.length == 41) {
    		videoId = commentVideoURL.substring(30 , 41);
		}else {
			videoId = null;	
		}
        $('#commentid').val(commentId);
        $('#videoIdForDelete').val(videoId);
        $.fn.colorbox({'href':'div#view_comment_videos_'+commentId , 'open':true, 'inline':true});
    }

   function playCommentVideo(id){
      var videoId =  $('#videoIdForDelete').val();
      	$("#iFrameYouTube_"+id).attr("src", "https://www.youtube.com/embed/"+videoId);	
      	parent.$.fn.colorbox({href:'div#emailListYouTube_'+id, open:true, inline:true});
   }
   
   function playYoutubeVideoForpromotion(id , VideoURL){
	   var videoId = VideoURL.substring(30 , 41 );
	      //var videoId =  $('#videoIdForDelete').val();
	      	$("#iFrameYouTube"+id).attr("src", "https://www.youtube.com/embed/"+videoId);	
	      	parent.$.fn.colorbox({href:'div#emailListYouTube'+id, open:true, inline:true});
	   }
	   

    $("#hidecomments").click(function(){
        $(".commentsToggle").hide();
        $(".SilverToggle").show();
    });
    $("#hideSilvercomments").click(function(){
        $(".commentsToggle").show();
        $(".SilverToggle").hide();
    });
    
//This is for Youtube Video upload for comments: 
$(document).ready(function(){
	$("#searchSubmit_video_comments").click(function(){
        $('#googleVideosScrollComments').html("");
        makeYoutubeApiCall('search_video_comments' , 'start-index' , 'googleVideosScrollComments' , 'commentsVideoSelect');
	});
	$("#searchSubmit_new_question_videos").click(function(){
        $('#googleVideosScroll').html("");
        makeYoutubeApiCall('search_new_question_videos' , 'start-index_promotion' , 'googleVideosScroll' , 'quesVideoSelect' );
	});
});

function makeYoutubeApiCall(searchInputId , startingIndexFor , googleVideosScroll , selectedVideoImageID ) {
	var search_input = $("#"+searchInputId).val();
	var keyword= encodeURIComponent(search_input);
	var imagesContainer = "";
	var count = 1;
	var startingIndex = $("#"+startingIndexFor).val();
	// Youtube API
    if (keyword == '') return;
    var yt_url='http://gdata.youtube.com/feeds/api/videos?q='+keyword+'&format=5&start-index='+startingIndex+'&max-results=6&v=2&alt=jsonc';
		$.ajax
		({
			type: "GET",
			url: yt_url,
			dataType:"jsonp",
			success: function(response) {
			if(response.data.items) {
				$.each(response.data.items, function(i,data) {
					var video_id=data.id;
					var video_title=data.title;
					var video_viewCount=data.viewCount;
					var video_duration = data.duration;
					var hours = Math.floor(video_duration / 3600);
					video_duration -= hours * 3600;
					var minutes = Math.floor(video_duration / 60);
					video_duration -= minutes * 60;
					var seconds = parseInt(video_duration % 60, 10);
					var durationInTimeStamp  = (hours>9?hours:"0"+hours) + ":" + (minutes>9?minutes:"0"+minutes) + ":" +  (seconds>9?seconds:"0"+seconds) ;
					
                    //Start of Each row start the row tag
                    if (i%3 == 0) {
                        imagesContainer = imagesContainer + '<tr>';
                    }
                    imagesContainer = imagesContainer + '<td><div><a><img  class="upload_photos_thumb_img"  src="http://i.ytimg.com/vi/'+video_id+'/hqdefault.jpg" onClick="javascript:selectVideoForUpload('+"'"+video_id +"' , '"+ selectedVideoImageID + "'" + ');" ></a>'
                    								  + '<a style="background-color: black;" ><strong>'+durationInTimeStamp+'</strong></a></div></td>';
                    //End of Each row close the row tag
                    if (count%3 == 0) {
                        imagesContainer = imagesContainer + '</tr>';
                    }
                    count++;
				});
				 imagesContainer = imagesContainer + '</table>';
				 $("#"+googleVideosScroll).append(imagesContainer);
			} else {
				$("#"+googleVideosScroll).html("<div id='no'>No Video</div>");
			}
			}
		});
}
	
function showMoreVideos(searchInputId , startingIndex , googleVideosScroll , selectedVideoImageID ) {
	var startingIndexValue = $('#'+startingIndex).val();
	startingIndexValue = parseInt(startingIndexValue) + parseInt("6") ;
	$('#'+startingIndex).val(startingIndexValue);
	makeYoutubeApiCall(searchInputId , startingIndex , googleVideosScroll , selectedVideoImageID );
}
    
function selectVideoForUpload(videoId , selectedVideoImageId ){
	setVideoIdForModelObjects(videoId , selectedVideoImageId);
	$("#"+selectedVideoImageId ).attr("src", "http://www.youtube.com/embed/"+videoId);	
}

function setVideoIdForModelObjects(videoId , selectedVideoImageId) {
	if(selectedVideoImageId == "commentsVideoSelect") {
		$("#youtubeCommentVideoId").val(videoId);
	}else if(selectedVideoImageId == "quesVideoSelect"){
		$("#youTubeVideoIdPromotion").val(videoId);
	}
}
 
</script>
<div style="display: none;">
	<div class="contactPoll_msg">
		<div class="contactPoll_msg_txt">
			<h1 class="contactPoll_msg_txt_poll"></h1>
		</div>

		<a class="poll_button1" href="javascript:removeCbox()"
			style="width: 170px;" id="ok"> <span class="poll_button_red">Ok</span>
		</a>
	</div>
</div>
