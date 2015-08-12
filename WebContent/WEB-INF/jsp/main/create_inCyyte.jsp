<%@ taglib prefix="display-el" uri="http://displaytag.sf.net/el" %>
<%@ include file="/WEB-INF/jsp/common/include.jsp" %>
<%@ page import="com.incyyte.app.domain.User" %>
<%@ page import="com.incyyte.app.web.model.AnswerModel" %>
<%@ page import="com.incyyte.app.web.model.IncyyteModel" %>
<%@ page import="org.apache.commons.lang.StringUtils" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Arrays" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.util.Iterator" %>
<%@page import="java.util.List" %>
<%@include file="/WEB-INF/jsp/login_model/modelLogin.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="content-type" content="text/html; charset=UTF-8">
<link rel="shortcut icon" href="favicon.ico"/>
<title>inCyyte Question</title>
<meta charset="utf-8">
<% User userLocal = (User) session.getAttribute("user");
   IncyyteModel model = (IncyyteModel) session.getAttribute("inCyyteForm");
%>
<link rel="stylesheet" href="ui/css/reset.css">
<link rel="stylesheet" href="ui/css/style.css">
<link rel="stylesheet" href="ui/css/layout.css">
<link rel="stylesheet" href="ui/css/buttons.css" media="screen">
<link rel="stylesheet" href="ui/css/accordionmenu.css" type="text/css">
<link rel="stylesheet" href="ui/css/icons_sprites.css">
<link rel="stylesheet" href="fonts/fonts_stylesheet.css">
<link rel="stylesheet" href="ui/modal/colorbox.css">
<link rel="stylesheet" href="ui/css/style_help.css">
<link rel="stylesheet" href="ui/css/prettyCheckboxes.css" type="text/css" media="screen">
<link rel="stylesheet" href="ui/css/themes/base/jquery.ui.all.css">
<link rel="stylesheet" href="ui/css/jquery.selectbox.css" type="text/css"/>
<link rel="stylesheet" href="ui/css/ratingbar.css" type="text/css"/>
<link rel="stylesheet" href="ui/css/ui.progress-bar.css" type="text/css"/>

<link rel="stylesheet" href="ui/css/modal_window.css" type="text/css" media="screen">
<link rel="stylesheet" type="text/css" href="ui/css/jquery.lightbox-0.5.css" media="screen"/>
<link rel="stylesheet" href="ui/css/jquery.validate.css">
<link rel="stylesheet" href="ui/css/validate/screen.css">

<script src="${pageContext.request.contextPath}/ui/js/jquery.min.1.9.0.js"></script>


<script src="ui/js/accordian/jquery-1.6.2.js"></script>
<script src="ui/js/jquery-1.7.2.js"></script>
<script src="ui/js/jcarousellite.js"></script>
<script type="text/javascript" src="ui/js/jquery-ui-1.8.16.custom.min.js"></script>
<script src="ui/js/jquery-1.8.2.min.js"></script>
<script src="ui/modal/colorbox/jquery.colorbox.js"></script>
<script src="ui/js/login.js"></script>
<script src="ui/js/home/signup_validations.js"></script>
<script src="ui/js/jquery.color.js"></script>
<script src="ui/js/search_script.js"></script>
<script src="ui/js/jquery.limit.js"></script>
<script src="ui/js/zoomphoto.js"></script>
<script src="ui/js/prettyCheckboxes.js" type="text/javascript" charset="utf-8"></script>
<script type="text/javascript" src="ui/js/autoresize.jquery.js"></script>
<script src="ui/js/accordian/jquery.ui.core.js"></script>
<script src="ui/js/accordian/jquery.ui.widget.js"></script>
<script src="ui/js/accordian/jquery.ui.accordion.js"></script>
<script src="ui/js/jquery.ui.datepicker.js"></script>
<script type="text/javascript" src="js/jquery.form.js"></script>
<script type="text/javascript" src="ui/js/jquery-ui.js"></script>
<script type="text/javascript" src="ui/js/create_incyyte.js"></script>
<script type="text/javascript" src="ui/js/jquery.lightbox-0.5.js"></script>
<script type="text/javascript" src="ui/js/validate/jquery.validate.js"></script>
<script type="text/javascript" src="ui/js/jquery.validate.1.8.1.min.js"></script>
<script src="ui/js/jquery.selectbox-0.2.js"></script>
<script src="ui/js/jquery_profile_rating.js" type="text/javascript"></script>
<script src="ui/js/star_rating.js" type="text/javascript"></script>
<link rel="stylesheet" href="ui/Fancy-box/jquery.fancybox.css" type="text/css" media="screen" />
<script type="text/javascript" src="ui/fancyplayer_code/js/flowplayer-3.1.1.min.js"></script>
<script type="text/javascript" src="ui/Fancy-box/jquery.fancybox.pack.js"></script>
<script type="text/javascript" src="ui/fancyplayer_code/js/jquery.easing.1.3.js"></script>
<script type="text/javascript" src="ui/fancyplayer_code/js/fancyplayer.js"></script>
<script type="text/javascript" src="js/flowplayer/flowplayer-3.2.12.min.js"></script>
<script type="text/javascript" src="ui/fancyplayer_code/js/flowplayer-3.1.1.min.js"></script>
<link rel="stylesheet" href="ui/css/style_login.css"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/json_parse.js"></script>

<input type='hidden' id="endOfFile" value="SHOW"/>
<input type='hidden' id="pageNum" value="1"/>
<%
String parameter = request.getParameter("param");
String searchValue = request.getParameter("search");
%>
<input type='hidden' id="param" value="<%=parameter%>" />
<input type='hidden' id="searchValue" value="<%=searchValue%>" />

<!--- placeholder Starts----->
<script>
    $(document).ready(function () {
    	<%if(userLocal != null){%>
    	$('#postcodeofSender').val('<%=userLocal.getPostalCodeArea()%>');
    	<%}%>
        $("#importContact").click(function () {
            window.location = "importcontacts.cyt";
        });
          $("#filterAgeMin").val('<%=model.getAgeMin()%>');
          $("#filterAgeMax").val('<%=model.getAgeMax()%>');
    });

    function addContactEmailList() {
        document.getElementById("emailArr").value="";
        var sList = "";
        $(".contactList").each(function () {
            if ($(this).is(':checked')) {
                sList += $(this).val() + "; ";
            }
        });
        if (sList != "") {
            var emailArrVal = $('#emailArr').val();
            if (emailArrVal != "") {
                $('#emailArr').val(emailArrVal + ' ' + sList);
            }
            else {
                $('#emailArr').val(sList);
            }
        }
        validateEmail();
        parent.$.fn.colorbox.close();
    }
    
    function playYoutubeVideoQues( id, videoId){
    	$("#iFrameYouTube"+id).attr("src", "https://www.youtube.com/embed/"+videoId);	
    	parent.$.fn.colorbox({href:'div#emailListYouTube'+id, open:true, inline:true});
    }
    
    function playVideo(id,playerId,playUrl) {
    	parent.$.fn.colorbox({href:'div#emailList'+id, open:true, inline:true});
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
    
    function playVideoFromAnswers(id,playerId,ansNum) {
    	var playUrl = $("#answer_uploaded_url_" + ansNum).val();
    	parent.$.fn.colorbox({href:'div#emailList'+id, open:true, inline:true});
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
    
  function playYoutubeVideoFromAnswers(id, videoId){
	 $("#iFrameYouTube"+id).attr("src", "https://www.youtube.com/embed/"+videoId);	
  	parent.$.fn.colorbox({href:'div#emailListYouTube'+id, open:true, inline:true});
    }
    
</script>


<script type="text/javascript">
function initiatePageProcess() {
	sendQuestion();
}
    function checked(xyz) {
        $(':checkbox').click(function () {
            var checkboxes = $("input[type='checkbox']");
            if (!checkboxes.is(":checked")) {
                $('#add_email_btn').bind('click', function (e) {
                    e.preventDefault();
                })
                $('#add_email_btn').attr('class', 'button_disabled');
            }
            else {
                $('#add_email_btn').unbind('click');
                $('#add_email_btn').attr('class', 'button_green1');
            }
        });
        // Select all
        if (xyz == '#selectall') {
            $("#group_1" + " INPUT[type='checkbox']").attr('checked', true);
            $('#add_email_btn').unbind('click');
            $('#add_email_btn').attr('class', 'button_green1');
        }
        // Select none
        if (xyz == '#select_none') {
            $("#group_1" + " INPUT[type='checkbox']").attr('checked', false);
            $('#add_email_btn').bind('click', function (e) {
                e.preventDefault();
            })
            $('#add_email_btn').attr('class', 'button_disabled');
        }
    }
</script>
<script type="text/javascript">
    function myonclickhandler(t, val) {
        if (t.checked) {
            var check = document.getElementById("uncheckedlist").value;
            for (var i = 0; i <= check.length; i++) {
                if (val == check[i])
                    check = check.trim(val);
            }
            document.getElementById("uncheckedlist").value = check;
        }
        else {
            document.getElementById("uncheckedlist").value = document.getElementById("uncheckedlist").value + "," + val;
            alert(document.getElementById("uncheckedlist").value);
        }
    }
</script>
<script type="text/javascript">
    $(function () {
        $("select").selectbox();
    });
</script>
<script type="text/javascript">
    $(function () {
        $(".fancybox-popup").fancybox({
         });
    });
    function abcd(ab) {
        alert("hi");
        alert(ab);
    }
</script>
<script>
    $(function () {
        $("#accordion").accordion();
    });
</script>
<script type="text/javascript">
    $(document).ready(function () {
        // Store variables
        var accordion_head = $('.accordion > li > a'),
                accordion_body = $('.accordion li > .sub-menu');
        // Open the first tab on load
        accordion_head.first().addClass('active').next().slideDown('normal');
        // Click function
        accordion_head.on('click', function (event) {
            // Disable header links
            event.preventDefault();
            // Show and hide the tabs on click
            if ($(this).attr('class') != 'active') {
                accordion_body.slideUp('normal');
                $(this).next().stop(true, true).slideToggle('normal');
                accordion_head.removeClass('active');
                $(this).addClass('active');
            }
        });
    });
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
	$('#create_incyyte').hide();
    var totalDiv = document.getElementById('gradient');
	var totalHeight = totalDiv.offsetHeight;
	var scrolledHeight = window.pageYOffset;
	var y = scrolledHeight + window.innerHeight;
	var pageNumber = $('#pageNum').val();
	var param = $('#param').val();
	var search = $('#searchValue').val();
	var contextPath = $('#contextPathVar').val();
	//if (y >= totalHeight) {
        pageNumber++;
		$('#pageNum').val(pageNumber);
		httpRequest.open("GET", contextPath+"/loadedContactsIncyyte.cyt?pageNumber=" + pageNumber + '&param='+param  + '&search='+ search , true);
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
				var spanValue  =  "<span id='group_1'>" 
					+ '<input type="checkbox"  class="contactList" name="selectedGroupContactsList" onclick="selectIndividualContact('+"'contactId"+contactList[i].contactIdValue+"'" +');"  id="contactId'+ contactList[i].contactIdValue +'"  value="' + contactList[i].contactEmail +'" /> ';
		
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
			$('#create_incyyte').show();
		}
	}else if ((endOfFile == "TRUE") && (displayEndOfResult == "SHOW")){
		$('#create_incyyte').hide();
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

 function selectIndividualContact(selectedContacId){
	
	$("#"+selectedContacId).attr("checked", "checked");
}
</script>

<!-- <script type="text/javascript" src="ui/js/ddaccordion.js"></script>
 --><script type="text/javascript">
    //Initialize :
    ddaccordion.init({
        headerclass:"question_tab", //Shared CSS class name of headers group
        contentclass:"question_detail", //Shared CSS class name of contents group
        revealtype:"click", //Reveal content when user clicks or onmouseover the header? Valid value: "click", "clickgo", or "mouseover"
        mouseoverdelay:200, //if revealtype="mouseover", set delay in milliseconds before header expands onMouseover
        collapseprev:false, //Collapse previous content (so only one open at any time)? true/false
        defaultexpanded:[], //index of content(s) open by default [index1, index2, etc]. [] denotes no content.
        onemustopen:true, //Specify whether at least one header should be open always (so never all headers closed)
        animatedefault:false, //Should contents open by default be animated into view?
        scrolltoheader:false, //scroll to header each time after it's been expanded by the user?
        persiststate:false, //persist state of opened contents within browser session?
        toggleclass:["closedquestion", "openquestion"], //Two CSS classes to be applied to the header when it's collapsed and expanded, respectively ["class1", "class2"]
        togglehtml:["none", "", " "], //Additional HTML added to the header when it's collapsed and expanded, respectively  ["position", "html1", "html2"] (see docs)
        animatespeed:"fast", //speed of animation: integer in milliseconds (ie: 200), or keywords "fast", "normal", or "slow"
        oninit:function (expandedindices) { //custom code to run when headers have initalized
            //do nothing
        },
        onopenclose:function (header, index, state, isuseractivated) { //custom code to run whenever a header is opened or closed
            //do nothing
        }
    })
</script>
<script type="text/javascript" src="./ui/js/autoresize.jquery.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/ui/js/placeHolder.js"></script>
<%--<script>
    // placeholder polyfill
    $(document).ready(function () {
        function add() {
            if ($(this).val() == '') {
                $(this).val($(this).attr('placeholder')).addClass('placeholder');
            }
        }
        function remove() {
            if ($(this).val() == $(this).attr('placeholder')) {
                $(this).val('').removeClass('placeholder');
            }
        }
        // Create a dummy element for feature detection
        if (!('placeholder' in $('<input>')[0])) {
            // Select the elements that have a placeholder attribute
            $('input[placeholder], textarea[placeholder]').blur(add).focus(remove).each(add);
            // Remove the placeholder text before the form is submitted
            $('form').submit(function () {
                $(this).find('input[placeholder], textarea[placeholder]').each(remove);
            });
        }
    });
</script>--%>
<script type="text/javascript">
    $(document).ready(function () {
        $("#myTextarea3").limit({
            limit:150,
            id_result:"counter",
            alertClass:"alert"
        });
    });
</script>
<script>
    $(function () {
        $("textarea").autoresize();
    })
</script>
<!--- Modal ----->
<script>
    $(document).ready(function () {
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
        $(".inline1").colorbox({inline:true, height:"100%"});
        $(".callbacks").colorbox({
            onOpen:function () {
                alert('onOpen: colorbox is about to open');
            },
            onLoad:function () {
                alert('onLoad: colorbox has started to load the targeted content');
            },
            onComplete:function () {
                alert('onComplete: colorbox has displayed the loaded content');
            },
            onCleanup:function () {
                alert('onCleanup: colorbox has begun the close process');
            },
            onClosed:function () {
                alert('onClosed: colorbox has completely closed');
            }
        });
        //Example of preserving a JavaScript event for inline calls.
        $("#click").click(function () {
            $('#click').css({"background-color":"#f00", "color":"#fff", "cursor":"inherit"}).text("Open this window again and this message will still be here.");
            return false;
        });
    });
</script>

<!--- Mddal--------------->
<script type="text/javascript">
    $(document).ready(function () {
        $("#help a").append("<em></em>");
        $("#help a").hover(function () {
            $(this).find("em").animate({opacity:"show", top:"35"}, "fast");
            var hoverText = $(this).attr("title");
            $(this).find("em").text(hoverText);
        }, function () {
            $(this).find("em").animate({opacity:"hide", top:"50"}, "fast");
        });
    });
</script>
<link rel="stylesheet" href="ui/css/sparkbox-select.css">
<script src="ui/js/modernizr-1.7.min.js"></script>
<script src="ui/js/jquery.sparkbox-select.js"></script>
<script>
    $(document).ready(function () {
        $('.sparkbox-custom').sbCustomSelect({
            appendTo:'body'
        });
        $('#incyyte_browse_videos').click(function () {
            $('#incyyte_video_file_input').click();
        });
        $('#incyyte_browse_photos').click(function () {
            $('#incyyte_photo_file_input').click();
        });
        $('#incyyte_browse_docs').click(function () {
            $('#incyyte_doc_file_input').click();
        });
        $('#ans_browse_videos_1').click(function () {
            $('#answer_file_video_1').click();
        });
        $('#ans_browse_photos_1').click(function () {
            $('#answer_file_photo_1').click();
        });
        $('#ans_browse_docs_1').click(function () {
            $('#answer_file_doc_1').click();
        });
        $('#ans_browse_videos_2').click(function () {
            $('#answer_file_video_2').click();
        });
        $('#ans_browse_photos_2').click(function () {
            $('#answer_file_photo_2').click();
        });
        $('#ans_browse_docs_2').click(function () {
            $('#answer_file_doc_2').click();
        });
        $('#ans_browse_videos_3').click(function () {
            $('#answer_file_video_3').click();
        });
        $('#ans_browse_photos_3').click(function () {
            $('#answer_file_photo_3').click();
        });
        $('#ans_browse_docs_3').click(function () {
            $('#answer_file_doc_3').click();
        });
        $('#ans_browse_videos_4').click(function () {
            $('#answer_file_video_4').click();
        });
        $('#ans_browse_photos_4').click(function () {
            $('#answer_file_photo_4').click();
        });
        $('#ans_browse_docs_4').click(function () {
            $('#answer_file_doc_4').click();
        });
        $('#ans_browse_videos_5').click(function () {
            $('#answer_file_video_5').click();
        });
        $('#ans_browse_photos_5').click(function () {
            $('#answer_file_photo_5').click();
        });
        $('#ans_browse_docs_5').click(function () {
            $('#answer_file_doc_5').click();
        });
        $('#ans_browse_videos_6').click(function () {
            $('#answer_file_video_6').click();
        });
        $('#ans_browse_photos_6').click(function () {
            $('#answer_file_photo_6').click();
        });
        $('#ans_browse_docs_6').click(function () {
            $('#answer_file_doc_6').click();
        });
        $('#ans_browse_videos_7').click(function () {
            $('#answer_file_video_7').click();
        });
        $('#ans_browse_photos_7').click(function () {
            $('#answer_file_photo_7').click();
        });
        $('#ans_browse_docs_7').click(function () {
            $('#answer_file_doc_7').click();
        });
        $('#ans_browse_videos_8').click(function () {
            $('#answer_file_video_8').click();
        });
        $('#ans_browse_photos_8').click(function () {
            $('#answer_file_photo_8').click();
        });
        $('#ans_browse_docs_8').click(function () {
            $('#answer_file_doc_8').click();
        });
        $('#ans_browse_videos_9').click(function () {
            $('#answer_file_video_9').click();
        });
        $('#ans_browse_photos_9').click(function () {
            $('#answer_file_photo_9').click();
        });
        $('#ans_browse_docs_9').click(function () {
            $('#answer_file_doc_9').click();
        });
        $('#ans_browse_videos_10').click(function () {
            $('#answer_file_video_10').click();
        });
        $('#ans_browse_photos_10').click(function () {
            $('#answer_file_photo_10').click();
        });
        $('#ans_browse_docs_10').click(function () {
            $('#answer_file_doc_10').click();
        });
        //Search submit for question
        $('#searchSubmit_new').click(function () {
        	googleImageSearchForQuestion();
        });
        //Search submit for answers
        $('#search_submit_1').click(function () {
        	googleImageSearchForAns1();
        });

        $('#search_submit_2').click(function () {
        	googleImageSearchForAns2();
        });
        $('#search_submit_3').click(function () {
        	googleImageSearchForAns3();
        });
        $('#search_submit_4').click(function () {
        	googleImageSearchForAns4();
        });
        $('#search_submit_5').click(function () {
        	googleImageSearchForAns5();
        });
        $('#search_submit_6').click(function () {
        	googleImageSearchForAns6();
        });
        $('#search_submit_7').click(function () {
        	googleImageSearchForAns7();
        });
        $('#search_submit_8').click(function () {
        	googleImageSearchForAns8();
        });
        $('#search_submit_9').click(function () {
        	googleImageSearchForAns9();
        });
        $('#search_submit_10').click(function () {
        	googleImageSearchForAns10();
        });
    });
</script>

 <script src="ui/js/googleSearch.js"></script>
<script type="text/javascript">

function googleImageSearchForQuestion() {
	$('#googleSearchTrigger').val(1);
    var searchValue = document.getElementById("search_new");
    var searchLoad = document.getElementById("googleImagesScroll");
    var searchSelect = "quesImgSelect";
    loadGoogleSearchImages(searchValue, searchLoad, searchSelect);
}
function googleImageSearchForAns1(){
	$('#googleSearchTrigger').val(1);
    var searchValue = document.getElementById("search_value_1");
    var searchLoad = document.getElementById("search_result_1");
    var searchSelect = "search_selected_pic_1";
    loadGoogleSearchImages(searchValue, searchLoad, searchSelect);
}

function googleImageSearchForAns2(){
	$('#googleSearchTrigger').val(1);
    var searchValue = document.getElementById("search_value_2");
    var searchLoad = document.getElementById("search_result_2");
    var searchSelect = "search_selected_pic_2";
    loadGoogleSearchImages(searchValue, searchLoad, searchSelect);
}
function googleImageSearchForAns3(){
	$('#googleSearchTrigger').val(1);
    var searchValue = document.getElementById("search_value_3");
    var searchLoad = document.getElementById("search_result_3");
    var searchSelect = "search_selected_pic_3";
    loadGoogleSearchImages(searchValue, searchLoad, searchSelect);
}
function googleImageSearchForAns4(){
	$('#googleSearchTrigger').val(1);
    var searchValue = document.getElementById("search_value_4");
    var searchLoad = document.getElementById("search_result_4");
    var searchSelect = "search_selected_pic_4";
    loadGoogleSearchImages(searchValue, searchLoad, searchSelect);
}
function googleImageSearchForAns5(){
	$('#googleSearchTrigger').val(1);
    var searchValue = document.getElementById("search_value_5");
    var searchLoad = document.getElementById("search_result_5");
    var searchSelect = "search_selected_pic_5";
    loadGoogleSearchImages(searchValue, searchLoad, searchSelect);
}
function googleImageSearchForAns6(){
	$('#googleSearchTrigger').val(1);
    var searchValue = document.getElementById("search_value_6");
    var searchLoad = document.getElementById("search_result_6");
    var searchSelect = "search_selected_pic_6";
    loadGoogleSearchImages(searchValue, searchLoad, searchSelect);
}
function googleImageSearchForAns7(){
	$('#googleSearchTrigger').val(1);
    var searchValue = document.getElementById("search_value_7");
    var searchLoad = document.getElementById("search_result_7");
    var searchSelect = "search_selected_pic_7";
    loadGoogleSearchImages(searchValue, searchLoad, searchSelect);
}
function googleImageSearchForAns8(){
	$('#googleSearchTrigger').val(1);
    var searchValue = document.getElementById("search_value_8");
    var searchLoad = document.getElementById("search_result_8");
    var searchSelect = "search_selected_pic_8";
    loadGoogleSearchImages(searchValue, searchLoad, searchSelect);
}
function googleImageSearchForAns9(){
	$('#googleSearchTrigger').val(1);
    var searchValue = document.getElementById("search_value_9");
    var searchLoad = document.getElementById("search_result_9");
    var searchSelect = "search_selected_pic_9";
    loadGoogleSearchImages(searchValue, searchLoad, searchSelect);
}
function googleImageSearchForAns10(){
	$('#googleSearchTrigger').val(1);
    var searchValue = document.getElementById("search_value_10");
    var searchLoad = document.getElementById("search_result_10");
    var searchSelect = "search_selected_pic_10";
    loadGoogleSearchImages(searchValue, searchLoad, searchSelect);
}


//Following function is called when we call Show more images
    function showImages() {
    	var trigger = $('#googleSearchTrigger').val();
        imageSearch.gotoPage(trigger);
        $('#googleSearchTrigger').val(++trigger);
    }
    
    function showMoreVideos(searchInputId , startingIndex , googleVideosScroll , selectedVideoImageID ) {
    	var startingIndexValue = $('#'+startingIndex).val();
    	startingIndexValue = parseInt(startingIndexValue) + parseInt("6") ;
    	$('#'+startingIndex).val(startingIndexValue);
    	makeYoutubeApiCall(searchInputId , startingIndex , googleVideosScroll , selectedVideoImageID );
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
<script>
   function toPayment(){
	   $("#inCyyteForm").ajaxSubmit({
           type:'POST',
           url:'send_question/paymentProcess.cyt',
           success:function (data) {
				window.location = "paymentInfo.cyt";
           },
           error:function (jqXHR, textStatus, errorThrown) {
               
           }
       });
	   
}
</script>
<!--[if IE 8]>
<link href="ui/css/ie8.css" media="screen" rel="stylesheet" type="text/css"/>
<style type="text/css">
    #header_topnav_inner {
        color: #9da6ac;
        font-size: 14px;
        font-family: 'bariol_regularregular', 'ie8_bariol_regular';
        float: right;
        margin-top: 0px !important;
    }
</style>
<![endif]-->
<!--[if gte IE 9]>
<link href="ui/css/gte_ie9.css" media="screen" rel="stylesheet" type="text/css"/>
<![endif]-->

<!--[if gte IE 8]>
<style>
    #myTextarea3 {
        line-height: 22px;
    }

    #header_topnav_inner {
        margin-top: -10px;
    }
</style>
<![endif]-->

<!--[if lt IE 9]>
<script src="ui/js/html5.js"></script>
<![endif]-->
<!--[if lt IE 9]>
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7">
<link href="ui/css/iestyle.css" media="screen" rel="stylesheet" type="text/css"/>
<![endif]-->

<%-- <% User userLocal = (User) session.getAttribute("user"); %> --%>
<%
	if (userLocal != null) {
%>
<jsp:include page="../common/includes/header.jsp"/>
<%
	} else {
%>
<jsp:include page="includes/emptyHeader.jsp"/>
<%
	}
%>

</head>
<%--
<script type="text/javascript">
    $(window).load(function() {
        $(".loader").fadeOut("slow");
    })
</script>
--%>
<body>
<%--
<div class="loader" style="background: url('${pageContext.request.contextPath}/ui/images/common/long_loading_bar.gif') 50% 50% no-repeat rgb(249,249,249)"></div>
--%>
<form:form id="inCyyteForm" name="inCyyteForm" commandName="inCyyteForm" enctype="multipart/form-data" method="post">
<div id="gradient">
<div class="extra">
<script>
    $(function () {
        var dDate = '<%=model.getClosureDate()%>';
        var maximumDate = new Date();
        maximumDate.setMonth(maximumDate.getMonth() + 3);
        
        if (dDate != null) {
            var dateArray = dDate.split(' ');
            var queryDate = dateArray[0];
            var parsedDate = $.datepicker.parseDate('dd/mm/yy', queryDate);
            $("#datepicker").datepicker({
            	minDate: 0,
            	maxDate:maximumDate,
                altField:"#alternate",
                altFormat:"dd/mm/yy"
            }).datepicker("setDate", parsedDate);
        }
        else {
            $("#datepicker").datepicker({
            	minDate: 0,
            	maxDate:maximumDate,
                altField:"#alternate",
                altFormat:"dd/mm/yy"
            }).datepicker("setDate", "+10");
        }
        //Make groups available if Group is selected
        var groupId = '<%=model.getGrpId()%>';
        if (groupId != null && groupId != '' && groupId != 'null') {
            $('#sendToGroup').attr('checked', 'checked');
            toggleMailGroup();
        }
    });
</script>
 <div class="main">
<!--content -->
<article id="content">

<div id="main_content" style="margin-top: 30px; float: left;">
<!----- Error Msg ----->
<div class="common_error failure" style="display: none;"><strong>Error : </strong> Please enter the necessary
    information.
</div>
<!----- Error Msg ----->
<!-- 1. Ask a Question & Pick a category starts-->
<div id="ask_que_category">

    <h3 class="heading1">1. Ask a question &amp; select a category...</h3>

    <div id="help"><a href="#"><em style="z-index:99" class="arrow_box1">
        <p class="helpmodal">Ask a question & select a category</p>
        Type your question in the text field and select the best category that fits your question from the icons below.<br>Members
        will not receive questions that are not from categories they have opted for. </em></a>
    </div>

    <div id="add_media1">
        <div id="typeyourquestion">
            <form:textarea id="myTextarea3" placeholder="Type your question here..." onFocus="this.placeholder = ' '"
                           onBlur="this.placeholder = 'Type your question here...'" style="line-height:120%;"
                           onKeyDown="countit(this)" onKeyUp="countit(this)" path="incyyte"
                           maxlength="${questionMaxChar}" title="Type your question here"/>
        </div>
        <div id="counter" style="display:none;"></div>

        <div id="rightcontent">
            <div id="incyyte_media_view" style="display: none;">
                <span>View file</span>

                <div id="media_small_icon" style="width:65px;">
                    <ul>
                        <li id="view_small_icon"><a class='inline' href="#view_files" alt="View" title="View"></a></li>
                    </ul>
                </div>

            </div>

            <div id="incyyte_media_add">
                <span>Add</span>

                <div id="media_icon">
                    <ul>
                        <li id="videos_icon"><a class='inline' href="#add_files" alt="Videos" title="Videos"></a></li>
                        <li id="photos"><a class='inline' href="#add_files" alt="Photos" title="Photos"></a></li>
                        <li id="docs"><a class='inline' href="#add_files" alt="Docs" title="Docs"></a></li>
                    </ul>
                </div>
            </div>
        </div>

        <form:input path="uploadedVideoFile" type="file" id="incyyte_video_file_input" style="display: none;"/>
        <form:input path="uploadedDocFile" type="file" id="incyyte_doc_file_input" style="display:none;"/>
        <form:input path="uploadedPhotoFile" type="file" id="incyyte_photo_file_input" style="display:none;"/>
        <input type="hidden" id="incyyte_photo_search_file"/>
        <input type="hidden" id="googleSearchTrigger"/>
        <input type="hidden" id="incyyte_photo_search_url"/>
        <form:hidden path="uploadedType" id="uploadedType"/>
        <form:hidden path="fileName" id="file_name_tst"/>
        <form:hidden path="searchedFileURL" id="searchedFileURL"/>
        <form:hidden path="searchedFileName" id="searchedFileName"/>
        <form:hidden path="youTubeQuestionVideoId" id="youTubeQuestionVideoId"/>
        <form:hidden path="youTubeAnswer_1_VideoId" id="youTubeAnswer_1_VideoId"/>
		<form:hidden path="youTubeAnswer_2_VideoId" id="youTubeAnswer_2_VideoId"/>
		<form:hidden path="youTubeAnswer_3_VideoId" id="youTubeAnswer_3_VideoId"/>
		<form:hidden path="youTubeAnswer_4_VideoId" id="youTubeAnswer_4_VideoId"/>
		<form:hidden path="youTubeAnswer_5_VideoId" id="youTubeAnswer_5_VideoId"/>
		<form:hidden path="youTubeAnswer_6_VideoId" id="youTubeAnswer_6_VideoId"/>
		<form:hidden path="youTubeAnswer_7_VideoId" id="youTubeAnswer_7_VideoId"/>
		<form:hidden path="youTubeAnswer_8_VideoId" id="youTubeAnswer_8_VideoId"/>
		<form:hidden path="youTubeAnswer_9_VideoId" id="youTubeAnswer_9_VideoId"/>
		<form:hidden path="youTubeAnswer_10_VideoId" id="youTubeAnswer_10_VideoId"/>
        <input type="hidden"  id="postcodeofSender"/>
        <input type="hidden"  id="start-index" value="1"/>
        <input type="hidden"  id="start-index_answer_0" value="1"/>
        <input type="hidden"  id="start-index_answer_1" value="1"/>
        <input type="hidden"  id="start-index_answer_2" value="1"/>
        <input type="hidden"  id="start-index_answer_3" value="1"/>
        <input type="hidden"  id="start-index_answer_4" value="1"/>
        <input type="hidden"  id="start-index_answer_5" value="1"/>
        <input type="hidden"  id="start-index_answer_6" value="1"/>
        <input type="hidden"  id="start-index_answer_7" value="1"/>
        <input type="hidden"  id="start-index_answer_8" value="1"/>
        <input type="hidden"  id="start-index_answer_9" value="1"/>
        <input type="hidden"  id="start-index_answer_10" value="1"/>
    </div>
</div>
<form:hidden path="category" id="category"/>
<div id="categories_container">
    <p class="heading2">Select category</p>

    <div id="categories_list">
        <ul id="categories_ul">
            <li id="music"><a href="#" alt="Music" class="tooltip"><span class="classic">Music</span></a></li>
            <li id="sports"><a href="#" alt="Sports" class="tooltip"><span class="classic">Sports</span></a></li>
            <li id="religion"><a href="#" alt="Religion" class="tooltip"><span class="classic">Religion</span></a></li>
            <li id="politics"><a href="#" alt="Politics" class="tooltip"><span class="classic">Politics</span></a></li>
            <li id="community"><a href="#" alt="Community" class="tooltip"><span class="classic">Community</span></a>
            </li>
            <li id="relationships"><a href="#" alt="Relationships" class="tooltip"><span
                    class="classic">Relationships</span></a></li>
            <li id="work_related"><a href="#" alt="Work Related" class="tooltip"><span class="classic" align="center">Work Related</span></a>
            </li>
            <li id="shopping"><a href="#" alt="Shopping" class="tooltip"><span class="classic">Shopping</span></a></li>
            <li id="health_medical"><a href="#" alt="Health Medical" class="tooltip"><span class="classic"
                                                                                           align="center">Health Medical</span></a>
            </li>
            <li id="fitness_beauty"><a href="#" alt="Fitness Beauty" class="tooltip"><span class="classic"
                                                                                           align="center">Fitness Beauty</span></a>
            </li>
            <li id="travel"><a href="#" alt="Travel" class="tooltip"><span class="classic">Travel</span></a></li>
            <li id="food_drink"><a href="#" alt="Food Drink" class="tooltip"><span class="classic" align="center">Food Drink</span></a>
            </li>
            <li id="motoring"><a href="#" alt="Motoring" class="tooltip"><span class="classic">Motoring</span></a></li>
            <li id="computer_internet"><a href="#" alt="Computer Internet" class="tooltip"><span class="classic"
                                                                                                 align="center">Computer Internet</span></a>
            </li>
            <li id="entertainment"><a href="#" alt="Entertainment" class="tooltip"><span
                    class="classic">Entertainment</span></a></li>
            <li id="other"><a href="#" alt="Other" class="tooltip"><span class="classic">Other</span></a></li>
        </ul>
    </div>
    <br>
    <span id="category_error" class="error" style="font-size: 12px; color:#c2002d;"></span>
</div>

<!-- 1. Ask a Question & Pick a category ends-->
<!-- 2. Choose your Answers starts-->
<form:hidden path="pollRecipients" id="pollRecipients"/>
<div id="choose_your_answers_heading">
    <form:hidden path="answerArr" id="responses"/>

    <form:hidden path="answer_count" id="answer_count"/>
    <form:hidden path="answer_upload_opt" id="answer_upload_opt"/>
    <form:hidden path="answerFileName" id="answerFileName"/>
    <form:hidden path="answerFileURL" id="answerFileURL"/>
    <form:input path="ans_uploaded_File" type="file" id="ans_uploaded_File" style="display: none;"/>

    <form:hidden path="answer_opt_1" id="answer_opt_1"/>
    <form:hidden path="answer_link_1" id="answer_link_1"/>
    <form:hidden path="answerType1" id="answerType1"/>
    <form:hidden path="answerFileName1" id="answerFileName1"/>
    <form:hidden path="answerUploadedType1" id="answerUploadedType1"/>
    <form:hidden path="answerUploadedFileName1" id="answerUploadedFileName1"/>
    <form:hidden path="answer_uploaded_url_1" id="answer_uploaded_url_1"/>
    <form:input path="answer_file_1" type="file" id="answer_file_1" style="display: none;"/>
    <form:input path="answer_file_video_1" type="file" id="answer_file_video_1" style="display: none;"/>
    <form:input path="answer_file_photo_1" type="file" id="answer_file_photo_1" style="display: none;"/>
    <form:input path="answer_file_doc_1" type="file" id="answer_file_doc_1" style="display: none;"/>

    <form:hidden path="answer_opt_2" id="answer_opt_2"/>
    <form:hidden path="answer_link_2" id="answer_link_2"/>
    <form:hidden path="answerType2" id="answerType2"/>
    <form:hidden path="answerFileName2" id="answerFileName2"/>
    <form:hidden path="answerUploadedType2" id="answerUploadedType2"/>
    <form:hidden path="answerUploadedFileName2" id="answerUploadedFileName2"/>
    <form:hidden path="answer_uploaded_url_2" id="answer_uploaded_url_2"/>
    <form:input path="answer_file_2" type="file" id="answer_file_2" style="display: none;"/>
    <form:input path="answer_file_video_2" type="file" id="answer_file_video_2" style="display: none;"/>
    <form:input path="answer_file_photo_2" type="file" id="answer_file_photo_2" style="display: none;"/>
    <form:input path="answer_file_doc_2" type="file" id="answer_file_doc_2" style="display: none;"/>

    <form:hidden path="answer_opt_3" id="answer_opt_3"/>
    <form:hidden path="answer_link_3" id="answer_link_3"/>
    <form:hidden path="answerType3" id="answerType3"/>
    <form:hidden path="answerFileName3" id="answerFileName3"/>
    <form:hidden path="answerUploadedType3" id="answerUploadedType3"/>
    <form:hidden path="answerUploadedFileName3" id="answerUploadedFileName3"/>
    <form:hidden path="answer_uploaded_url_3" id="answer_uploaded_url_3"/>
    <form:input path="answer_file_3" type="file" id="answer_file_3" style="display: none;"/>
    <form:input path="answer_file_video_3" type="file" id="answer_file_video_3" style="display: none;"/>
    <form:input path="answer_file_photo_3" type="file" id="answer_file_photo_3" style="display: none;"/>
    <form:input path="answer_file_doc_3" type="file" id="answer_file_doc_3" style="display: none;"/>

    <form:hidden path="answer_opt_4" id="answer_opt_4"/>
    <form:hidden path="answer_link_4" id="answer_link_4"/>
    <form:hidden path="answerType4" id="answerType4"/>
    <form:hidden path="answerFileName4" id="answerFileName4"/>
    <form:hidden path="answerUploadedType4" id="answerUploadedType4"/>
    <form:hidden path="answerUploadedFileName4" id="answerUploadedFileName4"/>
    <form:hidden path="answer_uploaded_url_4" id="answer_uploaded_url_4"/>
    <form:input path="answer_file_4" type="file" id="answer_file_4" style="display: none;"/>
    <form:input path="answer_file_video_4" type="file" id="answer_file_video_4" style="display: none;"/>
    <form:input path="answer_file_photo_4" type="file" id="answer_file_photo_4" style="display: none;"/>
    <form:input path="answer_file_doc_4" type="file" id="answer_file_doc_4" style="display: none;"/>

    <form:hidden path="answer_opt_5" id="answer_opt_5"/>
    <form:hidden path="answer_link_5" id="answer_link_5"/>
    <form:hidden path="answerType5" id="answerType5"/>
    <form:hidden path="answerFileName5" id="answerFileName5"/>
    <form:hidden path="answerUploadedType5" id="answerUploadedType5"/>
    <form:hidden path="answerUploadedFileName5" id="answerUploadedFileName5"/>
    <form:hidden path="answer_uploaded_url_5" id="answer_uploaded_url_5"/>
    <form:input path="answer_file_5" type="file" id="answer_file_5" style="display: none;"/>
    <form:input path="answer_file_video_5" type="file" id="answer_file_video_5" style="display: none;"/>
    <form:input path="answer_file_photo_5" type="file" id="answer_file_photo_5" style="display: none;"/>
    <form:input path="answer_file_doc_5" type="file" id="answer_file_doc_5" style="display: none;"/>

    <form:hidden path="answer_opt_6" id="answer_opt_6"/>
    <form:hidden path="answer_link_6" id="answer_link_6"/>
    <form:hidden path="answerType6" id="answerType6"/>
    <form:hidden path="answerFileName6" id="answerFileName6"/>
    <form:hidden path="answerUploadedType6" id="answerUploadedType6"/>
    <form:hidden path="answerUploadedFileName6" id="answerUploadedFileName6"/>
    <form:hidden path="answer_uploaded_url_6" id="answer_uploaded_url_6"/>
    <form:input path="answer_file_6" type="file" id="answer_file_6" style="display: none;"/>
    <form:input path="answer_file_video_6" type="file" id="answer_file_video_6" style="display: none;"/>
    <form:input path="answer_file_photo_6" type="file" id="answer_file_photo_6" style="display: none;"/>
    <form:input path="answer_file_doc_6" type="file" id="answer_file_doc_6" style="display: none;"/>

    <form:hidden path="answer_opt_7" id="answer_opt_7"/>
    <form:hidden path="answer_link_7" id="answer_link_7"/>
    <form:hidden path="answerType7" id="answerType7"/>
    <form:hidden path="answerFileName7" id="answerFileName7"/>
    <form:hidden path="answerUploadedType7" id="answerUploadedType7"/>
    <form:hidden path="answerUploadedFileName7" id="answerUploadedFileName7"/>
    <form:hidden path="answer_uploaded_url_7" id="answer_uploaded_url_7"/>
    <form:input path="answer_file_7" type="file" id="answer_file_7" style="display: none;"/>
    <form:input path="answer_file_video_7" type="file" id="answer_file_video_7" style="display: none;"/>
    <form:input path="answer_file_photo_7" type="file" id="answer_file_photo_7" style="display: none;"/>
    <form:input path="answer_file_doc_7" type="file" id="answer_file_doc_7" style="display: none;"/>

    <form:hidden path="answer_opt_8" id="answer_opt_8"/>
    <form:hidden path="answer_link_8" id="answer_link_8"/>
    <form:hidden path="answerType8" id="answerType8"/>
    <form:hidden path="answerFileName8" id="answerFileName8"/>
    <form:hidden path="answerUploadedType8" id="answerUploadedType8"/>
    <form:hidden path="answerUploadedFileName8" id="answerUploadedFileName8"/>
    <form:hidden path="answer_uploaded_url_8" id="answer_uploaded_url_8"/>
    <form:input path="answer_file_8" type="file" id="answer_file_8" style="display: none;"/>
    <form:input path="answer_file_video_8" type="file" id="answer_file_video_8" style="display: none;"/>
    <form:input path="answer_file_photo_8" type="file" id="answer_file_photo_8" style="display: none;"/>
    <form:input path="answer_file_doc_8" type="file" id="answer_file_doc_8" style="display: none;"/>

    <form:hidden path="answer_opt_9" id="answer_opt_9"/>
    <form:hidden path="answer_link_9" id="answer_link_9"/>
    <form:hidden path="answerType9" id="answerType9"/>
    <form:hidden path="answerFileName9" id="answerFileName9"/>
    <form:hidden path="answerUploadedType9" id="answerUploadedType9"/>
    <form:hidden path="answerUploadedFileName9" id="answerUploadedFileName9"/>
    <form:hidden path="answer_uploaded_url_9" id="answer_uploaded_url_9"/>
    <form:input path="answer_file_9" type="file" id="answer_file_9" style="display: none;"/>
    <form:input path="answer_file_video_9" type="file" id="answer_file_video_9" style="display: none;"/>
    <form:input path="answer_file_photo_9" type="file" id="answer_file_photo_9" style="display: none;"/>
    <form:input path="answer_file_doc_9" type="file" id="answer_file_doc_9" style="display: none;"/>

    <form:hidden path="answer_opt_10" id="answer_opt_10"/>
    <form:hidden path="answer_link_10" id="answer_link_10"/>
    <form:hidden path="answerType10" id="answerType10"/>
    <form:hidden path="answerFileName10" id="answerFileName10"/>
    <form:hidden path="answerUploadedType10" id="answerUploadedType10"/>
    <form:hidden path="answerUploadedFileName10" id="answerUploadedFileName10"/>
    <form:hidden path="answer_uploaded_url_10" id="answer_uploaded_url_10"/>
    <form:input path="answer_file_10" type="file" id="answer_file_10" style="display: none;"/>
    <form:input path="answer_file_video_10" type="file" id="answer_file_video_10" style="display: none;"/>
    <form:input path="answer_file_photo_10" type="file" id="answer_file_photo_10" style="display: none;"/>
    <form:input path="answer_file_doc_10" type="file" id="answer_file_doc_10" style="display: none;"/>

    <input type="hidden" value="${answerMaxOption}" id="answerMaxOption"/>

    <h3 class="heading1">2. Write your answers... <span>(Up to 50 characters per answer)</span></h3>

    <div id="help"><a href="#"><em style="z-index:99" class="arrow_box1">
        <p class="helpmodal">Write your answers</p>
        Use this section to write the answers you want your poll recipients to vote on.
        To support each answer you can add a video, a photo, a document file or even add a link to view a webpage.</em></a>
    </div>
</div>

<div id="choose_your_answers_new">
    <div id="answer_container">
        <div id="answer_form">
            <%
            	for (int i = 1; i <= model.getAnswers().size() || i <= 3; i++) {
            			AnswerModel answer = new AnswerModel();
            			Iterator<AnswerModel> itr = model.getAnswers().iterator();
            			while (itr.hasNext()) {
            				AnswerModel tempAns = itr.next();
            				if (tempAns.getAnswerOption() != null
            						&& tempAns.getAnswerOption().equals(
            								String.valueOf(i))) {
            					answer = tempAns;
            					break;
            				}
            			}
            			String ansResponse = "answerChoice" + i;
            			String ansMessage = "answer " + i + ".";
            			String ansImgView = "ans_one_view_" + i;
            			String ansAddView = "ans_one_add_" + i;
            			String ansAddFiles = "#add_files_" + i;
            			String ansViewFiles = "#view_files_" + i;

            			String videosSmall = "videos_small_" + i;
            			String photosSmall = "photos_small_" + i;
            			String docsSmall = "docs_small_" + i;
            			String viewSmall = "view_small_" + i;

            			String ansNewDiv = "ans_div" + i;
            %>
            <div  id="<%=ansNewDiv%>"><P style="display:inline-block; width:30px;"><%=i%>.</P>
                <%
                	if (ansMessage.equals("answer 1.")) {
                %>
                <c:set var="ansMsg" value="Yes"/>
                <%
                	} else if (ansMessage.equals("answer 2.")) {
                %>
                <c:set var="ansMsg" value="No"/>
                <%
                	} else if (ansMessage.equals("answer 3.")) {
                %>
                <c:set var="ansMsg" value="Not Sure"/>
                <%
                	} else {
                %>
                <c:set var="ansMsg" value="<%=ansMessage%>"/>
                <%
                	}
                %>
                <form:input path="<%=ansResponse%>" id="<%=ansResponse%>" placeholder="${ansMsg}"
                            onFocus="this.placeholder = ''"  onBlur="this.placeholder = '${ansMsg}'" maxlength="50"/>
                <%
                	if ((answer.getUploadedAnsFile() != null && StringUtils
                					.isNotBlank(answer.getAnswerFileName()))
                					|| (StringUtils.isNotBlank(answer
                							.getUploadedAnsFileYoutubeVideo()))) {
                %>
                	<span id="<%=ansImgView%>">
                        <span>View file</span>
                        <div  id="media_small_icon" style="width:65px; ">
                            <ul>
                                <li id="<%=viewSmall%>"><a class='inline' href="<%=ansViewFiles%>"
                                                           onClick="getAnsNum('<%=i%>')" alt="View" title="View"></a>
                                </li>
                            </ul>
                        </div>
                    </span>
                <%
                	} else {
                %>
                   <span id="<%=ansAddView%>">Add<div id="media_small_icon">
                       <ul>
                           <li id="<%=videosSmall%>"><a class='inline' onClick="getAnsNum('<%=i%>')"
                                                        href="<%=ansAddFiles%>" alt="Videos" title="Videos"></a></li>
                           <li id="<%=photosSmall%>"><a class='inline' onClick="getAnsNum('<%=i%>')"
                                                        href="<%=ansAddFiles%>" alt="Photos" title="Photos"></a></li>
                           <li id="<%=docsSmall%>"><a class='inline' onClick="getAnsNum('<%=i%>')"
                                                      href="<%=ansAddFiles%>" alt="Docs" title="Docs"></a></li>
                       </ul>
                   </div>
                    </span>
                <%
                	}
                %>

            </div>
            <span id="<%=ansResponse%>_error" class="error"
                  style="font-size: 12px; color:#c2002d; padding-left:52px; top:-7px;text-shadow:none; "></span>
            <%
            	}
            %>
            <div>
                <label class="panelLink" id="remove_an_answer"
                       onclick="deleteAnswerRow();"><span>-Remove an answer</span></label>
                <label class="panelLink" id="add_an_answer" onclick="createAnswerRow();"><span>+ Add an answer (you can have up to <c:out
                        value="${answerMaxOption}"/>)</span></label>
            </div>
        </div>
    </div>
</div>
<!-- 2. Choose your Answers ends-->

<!-- This contains the hidden content for inline calls starts-->
<div style='display:none'>
<div id="add_files">
<div id="modal_media_icon">
    <ul>
        <li id="modal_videos"><a href="#" alt="Videos" title="Videos" class="active"></a></li>
        <li id="modal_photos"><a href="#" alt="Photos" title="Photos"></a></li>
        <li id="modal_docs"><a href="#" alt="Docs" title="Docs"></a></li>
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
                                     style=" width:140px; float:left; margin-bottom:20px; position: absolute; top: 0px;left: 0px; z-index: 1;">
                                    <span class="title_gray">BROWSE</span></div>
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
                            <input type="text" name="search" id="search_new_question_videos"  onKeydown="Javascript: if (event.keyCode==13 || event.which==13) {$('#googleVideosScroll').html(''); makeYoutubeApiCall('search_new_question_videos' , 'start-index' , 'googleVideosScroll' , 'quesVideoSelect');}">
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
                <div class="upload_photo_add_more"><a href="javascript:showMoreVideos('search_new_question_videos' , 'start-index' , 'googleVideosScroll', 'quesVideoSelect');"> + Show more Videos </a></div>
            </td>
        </tr>
        <tr>
            <td colspan="2" valign="bottom"><span class="licence">You must have the licence to use this Video</span>

                <div title="Upload Now" id="incyyteUploadVideoButton" class="button_red"
                     style="width:140px; float:right;"><span class="title_red1">UPLOAD NOW</span></div>
            </td>
        </tr>
    </table>
</div>
<!------------- Add Videos end------------->
<!----------Add Photos Start-------------------- -->
<div id="add_photos" style="display:none; padding-bottom:80px;" class="c_add_photos">
    <table width="522" border="0" cellspacing="0" cellpadding="0">
        <tr>
            <td class="heading1" colspan="2">Add Photos</td>
        </tr>
        <tr>
            <td valign="top" align="left" width="30%">
                <table width="100%" border="0" cellspacing="0" cellpadding="0">
                    <tr>
                        <td width="30%" >
                            <div class="fileInputs">
                                <div id="incyyte_browse_photos" class="button_gray"
                                     style=" width:140px; float:left; margin-bottom:20px; position: absolute; top: 0px;left: 0px; z-index: 1;">
                                    <span class="title_gray">BROWSE</span></div>
                            </div>
                        </td>
                        <td colspan="2" class="font_16px"><br><span style="float: left;">File Name:&nbsp;</span> <span id="incyyte_photo_file_name" style= "float: left; margin-left: 10px;" ></span></td>
                    </tr>
                    <tr>
                        <td colspan="3">&nbsp;</td>
                    </tr>
                    <tr valign="bottom" style="display: none;" id="incyyte_photos_error_msg">
                        <td colspan="3"><span class="errorLabel">Please upload the correct image format (jpg, gif, ..)</span></td>
                    </tr>
                    <tr valign="bottom" style="display: none;" id="incyyte_photos_error_msg2">
                        <td colspan="3"><span class="errorLabel">The uploaded image exceeds the maximum allowed size(2 MB)</span></td>
                    </tr>
                    <tr valign="bottom" style="position: absolute;z-index: 200; float: left; margin-left: 410px; margin-top: 140px;">
                        <td colspan="3" align="center"><img src="ui/images/indicator-loader.gif" id="imageLoader"
                                                            width="32" height="32"
                                                            style="padding:8px 0 0 0; margin-bottom:20px 0 100px;"></td>
                    </tr>
                </table>
            </td>
        </tr>
        <tr>
            <td>
                <div class="upload_photo_searchbox" style="margin-bottom:0">
                    <p class="sort_by_text">Search Google images</p>
                    <div class="searchmain">
                        <div>
                            <input type="text" name="search" id="search_new" onKeydown="Javascript: if (event.keyCode==13 || event.which==13) googleImageSearchForQuestion();" >
                            <input type="submit" id="searchSubmit_new" value=""/>
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
                        <div id="googleImagesScroll"
                             style="width:319px; height:171px; overflow-x:hidden; overflow-y:auto; margin-right: 10px;">
                        </div>
                    </td>
                    <td align="right">
                        <div><img id="quesImgSelect" class="upload_photos_container_big_img" src="ui/images/uploading_big_img.png"></div>
                    </td>
                </tr>
            </table>

        </tr>
        <tr>
            <td>
                <div class="upload_photo_add_more"><a href="javascript:showImages();"> + Show more images </a></div>
            </td>
        </tr>
        <tr>
            <td colspan="2" valign="bottom"><span class="licence">You must have the licence to use this image</span>

                <div title="Upload Now" id="incyyteUploadPhotoButton" class="button_red"
                     style="width:140px; float:right;"><span class="title_red1">UPLOAD NOW</span></div>
            </td>
        </tr>
    </table>
</div>
<!----------Add Photos end-------------------->
<!----------Add docs Start-------------------->
<div id="add_docs" style="display:none;" class="c_add_docs">
    <table width="522" border="0" cellspacing="0" cellpadding="0">
        <tr>
            <td class="heading1" colspan="2">Add Docs</td>
        </tr>
        <tr>
            <td valign="top" align="left" width="50%">
                <table width="100%" border="0" cellspacing="0" cellpadding="0">
                    <tr>
                        <div class="fileInputs">
                            <div id="incyyte_browse_docs" class="button_gray"
                                 style=" width:140px; float:left; margin-bottom:20px; position: absolute; top: 0px;left: 0px; z-index: 1;">
                                <span class="title_gray">BROWSE</span>
                            </div>
                        </div>
                    </tr>
                    <tr>
                        <td>&nbsp;&nbsp;</td>
                    </tr>
                    <tr>
                        <td>
                            <div style="margin-top: 80px; float: left;" class="font_18px">Have you tried uploading<br> a photo to your poll?</div>
                            <div style="float: left; margin-top: 20px; line-height: 20px;" class="font_16px">
                                Hint:
                                You can Upload images <br>
                                clips or documents to <br>
                                share on your poll   <br>
                                using the buttons    <br>
                                above.
                            </div>
                        </td>
                    </tr>
                </table>
            </td>
            <td align="center" valign="top" id="incyyte_doc_not_loaded" width="50%">
                <table width="100%">
                    <tr>
                        <td align="left" class="font_16px"> <span style="float: left;"> File Name:&nbsp;&nbsp;</span><span style="word-wrap: break-word; float: left; width:185px;" id="incyyte_doc_file_name"></span></td>
                    </tr>
                    <tr valign="bottom" style="display: none;" id="incyyte_doc_error_msg">
                        <td><span class="errorLabel">Please upload the correct document format (doc, pdf, ..)</span>
                        </td>
                    </tr>
                    <tr valign="bottom" style="display: none;" id="incyyte_doc_error_msg2">
                        <td><span class="errorLabel">The uploaded document exceeds the maximum allowed size(2 MB)</span>
                        </td>
                    </tr>
                    <tr valign="bottom">
                        <td align="center" style="position: absolute;z-index: 200; float: left; margin-left: 118px; margin-top: 119px;"><img src="ui/images/indicator-loader.gif" id="docLoader"
                                                                                                                                             width="32" height="32"
                                                                                                                                             style="padding:8px 0 0 0;margin-bottom:20px 0 100px; "></td>
                    </tr>
                    <tr>
                        <td>&nbsp;</td>
                    </tr>
                    <tr>
                        <td><div id="video_tumb_container" ><img width="260" height="260" src="ui/images/photo1.png"></div></td>
                    </tr>
                </table>

            </td>
            <td align="center" valign="top" id="incyyte_doc_loaded" style="display:none;" width="70%">
                <div id="#video_thumbs">
                    <ul id="videos">
                        <li><a id="addDoclink" href="#">
                            <img src="ui/images/view_docs_thumb.png" class="photos_thumb" alt="tour"/></a>
                        </li>
                    </ul>
                </div>
            </td>
        </tr>
        <tr>
            <td colspan="2" valign="bottom">
                <div title="Upload Now" id="incyyteUploadDocButton" class="button_red"
                     style="  width:140px; float:right;"><span class="title_red1">UPLOAD NOW</span></div>
            </td>
        </tr>
    </table>
</div>
<!----------Add docs end-------------------->

</div>
</div>

<div style='display:none'>
    <div id="add_links">
        <div id="modal_media_icon">
            <ul>
                <li id="modal_links"><a href="#" alt="Links" title="Links" class="active"></a></li>
            </ul>
        </div>

        <div>
            <!------------- Add ULR start------------->
            <div id="add_webpage" class="c_add_webpage">
                <table width="100%" border="0" cellspacing="0" cellpadding="0">
                    <tr>
                        <td class="heading1">Add A Web Page</td>
                    </tr>
                    <tr>
                        <td>Add the URL (Address) of the webpage below</td>
                    </tr>
                    <tr>
                        <td><form:input path="eLink" id="incyyte_eLink" placeholder="http://..."
                                        onFocus="this.placeholder = ''" onBlur="this.placeholder = 'http://...'"/></td>
                    </tr>
                    <tr>
                        <td>&nbsp; &nbsp;</td>
                    </tr>
                    <tr>
                        <td valign="bottom"><br> &nbsp; &nbsp;
                            <span title="close" id="close_elink" class="button_red" style="  width:170px; float:right;"> <span
                                    class="title_red">SAVE AND ADD LINK</span></span>
                        </td>
                    </tr>
                </table>
            </div>
            <!------------- Add ULR end------------->
        </div>
    </div>
</div>

<div style='display:none'>
    <div id="view_files">
        <div id="modal_media_icon">
            <ul>
                <li id="modal_videos"><a href="#" alt="Videos" title="Videos" class="active"></a></li>
                <li id="modal_photos"><a href="#" alt="Photos" title="Photos"></a></li>
                <li id="modal_docs"><a href="#" alt="Docs" title="Docs"></a></li>
            </ul>
        </div>

        <div>
            <!----------View Video Starts -------------------->
            <div id="view_videos" class="c_add_videos" style="width: 422px; height: 300px;">
                <table width="100%" border="0" cellspacing="0" cellpadding="0">
                    <tr>
                        <td class="heading1" colspan="2">View Videos</td>
                    </tr>
                    <tr>
                        <td valign="top" align="left" width="30%">
                            <table width="100%" border="0" cellspacing="0" cellpadding="0">
                                <tr valign="top">
                                    <td class="font_18px"><br> <br>File Name:</td>
                                </tr>
                                <tr>
                                    <td class="font_16px">
                                        <div style="word-wrap: break-word; width:160px;"><span
                                                id="view_video_file_name"></span></div>
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
                                   		<a id="videolinkId" >
                                   						 <img   id="viewQuesYouTubeVideoId" src="ui/images/video_thumb.png"  onClick="javascript:playVideo('videolinkId','playervideolinkId', '<%=model.getUploadedFileLocation()%>')"  class="photos_thumb" alt="tour"/>
                                        	<div style="display:none;">
											    <div id="emailListvideolinkId" class="emailList ">
													<a style="cursor:pointer" id="playervideolinkId" ></a>
											    </div>
											</div>
											<div style="display:none;">
												<div id="emailListYouTubevideolinkId" class="emailList " >
											    <iframe width='390' id="iFrameYouTubevideolinkId"  height='308'  src="ui/images/uploading_big_img.png" frameborder='0' type='text/html'></iframe>
											    </div>
											</div>      
                                      </li>
                                </ul>
                            </div>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="2" valign="bottom"><span class="licence" >You must have the licence to use this Video</span>

                            <div title="Delete" id="incyyteDeleteVideoButton" class="button_red"
                                 style="width:140px; float:right;"><span class="title_red">DELETE</span></div>
                        </td>
                    </tr>
                </table>
            </div>
            <!------------- Add ULR end------------->
            <!----------View Photos Start-------------------->
            <div id="view_photos" class="c_add_photos" style="width: 422px;  height: 300px; float: left;">
                <table width="100%" border="0" cellspacing="0" cellpadding="0">
                    <tr>
                        <td class="heading1" colspan="2">View Photos</td>
                    </tr>
                    <tr>
                        <td valign="top" align="left" width="30%">
                            <table width="100%" border="0" cellspacing="0" cellpadding="0">
                                <tr valign="top">
                                    <td class="font_18px"><br> <br>File Name:</td>
                                </tr>
                                <tr>
                                    <td class="font_16px">
                                        <div style="word-wrap: break-word; width:160px;"><span
                                                id="view_photo_file_name"></span></div>
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
                                    <li><a id="photolinkId" href="<%=model.getUploadedFileLocation()%>" target="_blank"
                                           class="group1 fancybox-popup">
                                        <img id="photoImg" src="<%=model.getUploadedFileLocation()%>"
                                             class="photos_thumb" alt="tour"/>
                                    </a>
                                    </li>
                                </ul>
                            </div>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="2" valign="bottom"><span class="licence">You must have the licence to use this image</span>

                            <div title="Delete" id="incyyteDeletePhotoButton" class="button_red"
                                 style="width:140px; float:right;"><span class="title_red">DELETE</span></div>
                        </td>
                    </tr>
                </table>
            </div>
            <!----------Add Photos end-------------------->
            <!----------Add docs Start-------------------->
            <div id="view_docs" class="c_add_docs" style="width: 422px; height: 300px;">
                <table width="100%" border="0" cellspacing="0" cellpadding="0">
                    <tr>
                        <td class="heading1" colspan="2">View Docs</td>
                    </tr>
                    <tr>
                        <td valign="top" align="left" width="30%">
                            <table width="100%" border="0" cellspacing="0" cellpadding="0">
                                <tr valign="top">
                                    <td class="font_18px"><br><br>File Name:</td>
                                </tr>
                                <tr>
                                    <td class="font_16px">
                                        <div style="word-wrap: break-word; width:160px;"><span
                                                id="view_doc_file_name"></span></div>
                                    </td>
                                </tr>
                                <tr>
                                    <td>&nbsp;</td>
                                </tr>
                            </table>
                        </td>
                        <td align="center" valign="top" width="70%">
                            <c:set var="quesUrl" value="<%=model.getUploadedFileLocation()%>"/>
                            <%
                            	if (model != null
                            				&& StringUtils.isNotBlank(model
                            						.getUploadedFileLocation())
                            				&& StringUtils.isNotBlank(model.getFileName())) {
                            			String name = model.getFileName();
                            			String extension = "";
                            			int ii = name.lastIndexOf('.');
                            			if (ii > 0) {
                            				extension = name.substring(ii + 1);
                            			}
                            			String docs[] = { "wpd", "wps", "xml", "xlr", "pdf" };
                            			String gooleDocs[] = { "doc", "docx", "log", "rtf", "txt",
                            					"csv", "pps", "ppt", "xls", "xlsx" };
                            			List<String> extDocs = Arrays.asList(docs);
                            			List<String> extGoogleDocs = Arrays.asList(gooleDocs);
                            			String docUrl = "https://docs.google.com/viewer?url=";
                            			String url = (String) pageContext.getAttribute("quesUrl");
                            			String viewUrl = docUrl + url;
                            %>
                            <%
                            	if (extDocs.contains(extension)) {
                            %>
                            <div id="#view_doc_thumbs">
                                <ul id="videos">
                                    <li><a id="doclinkId" style="cursor:pointer" href='<%=viewUrl%>'
                                           onClick="window.open('<%=url%>','MyWindow'); return false;"
                                           target="_blank"><img
                                            src="ui/images/view_docs_thumb.png" class="photos_thumb" alt="tour"/></a>
                                    </li>
                                </ul>
                            </div>
                            <%
                            	}
                            			if (extGoogleDocs.contains(extension)) {
                            %>
                            <ul id="videos">
                                <li><a id="doclinkId" style="cursor:pointer" href=""
                                       onClick="MyWindow=window.open('<%=viewUrl%>','MyWindow'); return false;"
                                       target="_blank"><img
                                        src="ui/images/view_docs_thumb.png" class="photos_thumb" alt="tour"/></a>
                                </li>
                            </ul>
                            <%
                            	}
                            		}
                            %>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="2" valign="bottom">
                            <div title="Delete" id="incyyteDeleteDocButton" class="button_red"
                                 style="  width:140px; float:right;"><span class="title_red">DELETE</span></div>
                        </td>
                    </tr>
                </table>
            </div>
            <!----------Add docs end-------------------->
        </div>
    </div>
</div>

<!-- 3. Poll settings starts -->
<div id="poll_settings">
    <h3 class="heading1" style="padding:0px 0 33px 17px;">3. Poll settings</h3>

    <div id="help"><a href="#"><em style="z-index:99" class="arrow_box1">
        <p class="helpmodal">Poll settings</p>
        Use this section to set the conditions of your poll. You can choose to hide your identity by making your poll
        anonymous (see our Red Card System). You can also choose to allow others to comment on your question and you can set
        a date and time for your poll to close.</em></a>
    </div>
    <%
    	boolean anonymity = model.isAnonymity();
    %>
    <div id="poll_settings_left">
        <div class="checkboxDemo">

            <label for="checkbox-1" tabindex="1" style="margin-left:0; float:left;">Make Poll Anonymous</label>
            <form:checkbox path="anonymity" id="checkbox-1"/>
            <div id="help_small" style=" display:inline-block; vertical-align:top; margin:3px -15px 3px 20px;"><a
                    href="#" class="tooltip1"><img src="ui/images/help_icon_small.png"> <span class="classic1">Select this option to allow your poll recipients and others who view your poll results to post comments and discuss. </span></a>
            </div>
            <label for="checkbox-2" tabindex="2">Allow Comments</label>
            <form:checkbox path="allowComment" id="checkbox-2"/>

        </div>
    </div>

    <form:hidden path="closureDate" id="closure_Date"/>
    <input type="hidden" id="alternate" size="11"/>
    <%
    	String date = null;
    		if (model.getClosureDate() != null) {
    			date = model.getClosureDate();
    		} else {
    			SimpleDateFormat dateFormat = new SimpleDateFormat(
    					"dd/MM/yyyy hh24:mi");
    			date = dateFormat.format(new Date());
    		}
    %>
    <div id="poll_settings_right">
        <table width="100%" border="0" cellspacing="0" cellpadding="0">
            <tr>
                <td width="48%" class="closing_date_time">Closing Date/Time:</td>
                <td width="10%" class="closing_date_time1" align="right"><span id="closingDateVal"><%=date%></span></td>
                <td width="12%" class="closing_date_time1" align="left"><span id="selecttimeVal"></span></td>
                <td width="30%"><a href="#modal_datepicker" title="SET CLOSING DATE & TIME"
                                   id="createInCyyte.closingDate.change"
                                   class="button_gray inline1" style="margin-top:17px; width:111px; float:right"> <span
                        class="title_gray3">CHANGE</span></a></td>
            </tr>
        </table>
    </div>
</div>
<div style='display:none'>
    <div id="modal_datepicker">
        <h3 class="font_20px">Change the closing date</h3>

        <div id="datepicker"></div>
        <h3 class="font_20px" style="padding-top:15px;">Change the closing Time</h3>

        <p>

        <div id="form">
            <label style="width:130px;">
                <select id="selecttime">
                    <option value="01:00">01:00</option>
                    <option value="02:00">02:00</option>
                    <option value="03:00">03:00</option>
                    <option value="04:00">04:00</option>
                    <option value="05:00">05:00</option>
                    <option value="06:00">06:00</option>
                    <option value="07:00">07:00</option>
                    <option value="08:00">08:00</option>
                    <option value="09:00">09:00</option>
                    <option value="10:00">10:00</option>
                    <option value="11:00">11:00</option>
                    <option value="12:00" selected="selected">12:00</option>
                    <option value="13:00">13:00</option>
                    <option value="14:00">14:00</option>
                    <option value="15:00">15:00</option>
                    <option value="16:00">16:00</option>
                    <option value="17:00">17:00</option>
                    <option value="18:00">18:00</option>
                    <option value="19:00">19:00</option>
                    <option value="20:00">20:00</option>
                    <option value="21:00">21:00</option>
                    <option value="22:00">22:00</option>
                    <option value="23:00">23:00</option>
                    <option value="24:00">24:00</option>
                </select> </label>

            <div title="Save" id="modal_datepicker_btn" class="button_green" style="width:80px; float:right"><span
                    class="title_green title_green_new_ie8">SAVE</span></div>
        </div>
        </p>
    </div>
</div>
<!-- 3. Poll settings ends-->
<!-- 4. Send Your Poll starts -->
<div id="send_your_poll">
<h3 class="heading1" style="padding:0px 0 28px 17px;">4. Sending your poll</h3>

<div id="help"><a href="#"><em style="z-index:99" class="arrow_box1">
    <p class="helpmodal">Sending your poll</p>
    Set 'Keep Private' for polls that will only be seen by the individuals you send it to. Set 'Allow Sharing' to allow your poll recipients to share your polls with their own contacts.</em></a></div>
<div id="send_your_poll_top">
    <p style="float:left; margin-right:30px;">Keep this poll private, or allow recipients to share it?</p>
	
    <div class="inlineRadios">
        <form:radiobutton path="public_poll" id="radio-5" value="N"/>
        <label for="radio-5" tabindex="5">Keep Private</label>
        <form:radiobutton path="public_poll" id="radio-6" value="Y"/>
        <label for="radio-6" tabindex="6">Allow Sharing</label>
    </div>
</div>
<div id="send_your_poll_left">
    <div class="inlineRadios">
        <table width="100%" border="0" cellspacing="0" cellpadding="0" class="font_14px">
            <tr>
                <td height="40">Send to email contacts</td>
                <td align="center"><label for="mail" tabindex="5"></label>
                    <form:radiobutton path="sendType" id="mail" value="mail" checked="checked"/></td>
            </tr>
            <tr>
                <td height="40">Attach to your Poll Page </td>
                <td align="center"><label for="post" tabindex="6"></label>
                    <form:radiobutton path="sendType" id="post" value="post"/></td>
            </tr>
            <tr>
                <td height="40">Send to a postal region</td>
                <td align="center"><label for="area" tabindex="7"></label>
                    <form:radiobutton path="sendType" id="area" value="area"/></td>
            </tr>
        </table>
    </div>
</div>

<div id="send_your_poll_right">
<div id="by_email">
    <table id="form2" width="100%" border="0" cellspacing="0" cellpadding="0" class="font_14px">
        <tr>
            <td colspan="2" nowrap="nowrap">
                <div>
                    <p style="float:left; margin-right:30px;">Are you sending this poll to a group or to individuals?

                    <div class="inlineRadios">
                        <form:radiobutton path="sendToGroup" id="sendToGroup" value="Y"/>
                        <label for="sendToGroup" tabindex="9">Group</label>
                        <form:radiobutton path="sendToGroup" id="sendToIndividual" value="N"/>
                        <label for="sendToIndividual" tabindex="10">Individuals</label>
                    </div>
                    </p>
                </div>
            </td>
        </tr>      
        <tr id="mailIndividual">
            <td height="40" width="40%" nowrap="nowrap">Add email addresses</td>
            <td width="50%">
                <form:textarea cssStyle="width: 454px; height: 92px;float: left; " cssClass="questionbox emailArr_ie8"
                               class="sending_poll_textarea" path="emailArr" onchange="javascript:addEmail()"
                               id="emailArr" onFocus="this.placeholder = ''"
                               onBlur="this.placeholder = 'Add email addressses (e.g. joseph@incyyte.com, tim@incyyte.com)'"
                               placeholder="Add one or more email addressses separated by comma & a space"/>
                <span id="email_error" class="error"
                      style="font:14px/20px 'bariol_regularregular', 'ie8_bariol_regular';color:#C2002D;"></span>
                    
                    <%
                    	if (userLocal != null) {
                    %>
                    <div align="left" style="padding-top:10px">
                        <label class="panelLink" id="displayEmailList"><br><span class="addSpan">+ Add contact list emails</span></label>
                    </div>
                    <% } %>
            </td>
        </tr>
        <tr style="display: none;" id="mailGroup">
            <td>
                <table width="88%" border="0" cellspacing="0" cellpadding="0">
                    <%
                    	if (userLocal != null) {
                    %>
                    <tr>
                        <td width="40%" height="40" nowrap="nowrap"><br>Select from your list of groups.<br>Haven't created any groups?<br><a href="grouphome.cyt"><strong>Create a new group</strong></a></td>
                        <td width="21%">
                            <form:select path="grpId" id="grpId" class="grp_select">
                                <form:option value="" label="--Select--"/>
                                <c:forEach var="item" items="${groupList}">
                                    <c:set var="groupId" value="${item.groupId}"/>
                                    <%
                                    	String groupId = String.valueOf(pageContext
                                    							.getAttribute("groupId"));
                                    					if (StringUtils.equals(model.getGrpId(), groupId)) {
                                    %>
                                    <option value="${item.groupId}" selected="selected"><c:out
                                            value="${item.groupName}"/></option>
                                    <%
                                    	} else {
                                    %>
                                    <option value="${item.groupId}"><c:out value="${item.groupName}"/></option>
                                    <%
                                    	}
                                    %>
                                </c:forEach>
                            </form:select>
                            <span id="grp_error" class="error" style="font-size: small;color:red;"></span>
                        </td>
                    </tr>
                    <%
                    	}
                    %>
                    <%
                    	if (userLocal == null) {
                    %>
                    <tr>
                        <td colspan="2">
                            <p><strong>Sending inCyyte to a group is only available, when you login <br>
                                Click here to <span> <a href="#create_loginForm" id="login-user2">login</a></span>
                            </strong></p>
                        </td>
                    </tr>
                    <%
                    	}
                    %>
                    <tr>
                        <td colspan="2">&nbsp;</td>
                    </tr>

                </table>
            </td>
        </tr>
    </table>
</div>
<div id="form2">

<c:choose>
    <c:when test="${displayPostIncyyte == 'OFF'}">
        <div id="post_on_webpage" style="display:none; position:relative;">
            <div id="form2">
                <table width="100%" border="0" cellspacing="0" cellpadding="0">
                    <tr align="center">
                        <td colspan="2">
                            <p><strong>This section will be available soon. <br>
                                Apologies for the inconvenience.
                            </strong></p>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="2">&nbsp;

                        </td>
                    </tr>
                </table>
            </div>
        </div>
    </c:when>
    <c:otherwise>
       <% if (userLocal != null) {%> 
        <div id="post_on_webpage" style="display:none;">
            <p><strong>This poll will be embedded on your exclusive poll webpage.</strong><br>
                <strong>Setup your exclusive poll page <a href="pollSetup.cyt">here.</a></strong></p>
            <br>
            
            <table width="100%" border="1" cellspacing="0" cellpadding="0" class="font_14px">
           		<tr>
                    <td width="20%">
                       Poll Page name:
                    </td>
                    <td width="80%" align="left">
                        <form:input path="pageName" id="pageName" onFocus="this.placeholder = ''"
                                    onBlur="validateUniquePageName()" placeholder="myincyytepoll" onkeyup="validatePageName()" 
                                    style="width:400px;"/>
                       <br> <span id="pageName_error" class="error" style="font-size: small;color:red;"></span>
                   </td>
              	</tr>
                <tr>
                    <td width="100%" colspan="2">&nbsp;</td>
              	</tr>
              	<%
              		if (userLocal != null && !StringUtils.equals(userLocal.getUserType(), "USER")) {
              	%>
	              	<c:if test="${not empty templates}" >
						<tr>
							<td width="20%">Select Template: </td>
							<td width="80%" align="left">                 
						        <form:select path="templateId" id="templateId" cssStyle="width:60px;">        	
									<c:forEach var="item" items="${templates}">
							     		<form:option value="${item.key}"  label="${item.value}"/>
							      	</c:forEach>										
								</form:select>
							</td>
						</tr>
					</c:if>
                <%
                	}
                %> 
                <tr>
                    <td width="100%" colspan="2">
    					<div id="pollResultHidden_settings">
				            <div class="checkboxDemo">Hide Poll Results from Recepients:                    
		                        <label for="checkbox-3" tabindex="3"></label>
		                       	<form:checkbox path="pollResultHidden" id="checkbox-3"/>     
	            			</div>                  
            			</div>                  
                    </td>
             	</tr>
                  
              	<tr>
                    <td width="100%" colspan="2">&nbsp;</td>
              	</tr>
                
                <!-- 
                <tr>
                    <td>
                        Protect this page?:
                    </td>
                    <td align="left">
                        <div class="inlineRadios">
                            <form:radiobutton path="protectPage" id="radio-7" value="Y"/>
                            <label for="radio-7" tabindex="7">Yes</label>
                            <form:radiobutton path="protectPage" id="radio-8" value="N"/>
                            <label for="radio-8" tabindex="8">No</label>
                        </div>
                    </td>
                </tr>
                 -->
            </table>
        </div>
        <% }
        else { %>
                <div id="post_on_webpage" style="display:none;">
                  <p><strong>This poll will be embedded on your exclusive poll webpage.<br>
                                To setup your exclusive poll page, click here to <span> <a href="#create_loginForm" id="login-user2">login</a></span>
                            </strong></p>
        </div>
        <%} %>
    </c:otherwise>
</c:choose>
  
<c:choose>
<c:when test="${displayPostalRegion == 'OFF'}">
    <div id="postal_region" style="display:none; position:relative;">
        <div id="form2">
            <table width="100%" border="0" cellspacing="0" cellpadding="0">
                <tr align="center">
                    <td colspan="2">
                        <p><strong>This section will be available soon. <br>
                            Apologies for the inconvenience.
                        </strong></p>
                    </td>
                </tr>
                <tr>
                    <td colspan="2">&nbsp;
                    </td>
                </tr>
            </table>
        </div>
    </div>
</c:when>
<c:otherwise>
<div id="postal_region" style="display:none; position:relative;">
<div id="form2">
<%
	if (userLocal != null) {
%>

<table width="100%" border="0" cellspacing="0" cellpadding="0">
    <tr>
        <td width="12%" height="40">Country:</td>
        <td width="21%">
            <form:select path="country" id="ac_country1" class="sbSelector_ie8">
                <form:option selected="selected" value="UK" label="United Kingdom"/>
                <!-- <form:option value="US" label="United States"/> -->
            </form:select>
        </td>
        <td width="12%">Age Range:</td>
        	
        <td width="21%">        	
        	<div style="float: left; width: 50%;">
	        	<form:select path="ageMin" id="filterAgeMin" >
	        		<form:option selected="selected" value="select" label="-From-"/>
	        	 	<c:forEach var="item" items="${ageMin}">
	                    <option value="${item.key}" >
	                    	<c:out value="${item.value}"/>
	                    </option>
	                </c:forEach>        	 	
	        	 </form:select>
       		</div>
       		
       		<div style="float: left; width: 50%;">
	       		<form:select path="ageMax" id="filterAgeMax" >
	        	 	<form:option selected="selected" value="select" label="-To-"/>
	        	 	<c:forEach var="item" items="${ageMax}">
	                    <option value="${item.key}" >
	                    	<c:out value="${item.value}"/>
	                    </option>
	         		</c:forEach>        	 	
	        	 </form:select>
	        </div>
        	<br>
        	<span id="agerange_error" class="error" style="font-size: small;color:red;"></span>
        </td>
    </tr>
   
    <tr>
        <td height="40">Method:</td>
        <td>
            <select name="locality" id="locality">
                <option value="Postcode" selected="selected">Postcode (eg. RM1 1DA)</option>
                <option value="Region">Region (eg. RM1)</option>
                <option value="County">County</option>
            </select>
        </td>
        <td>Gender:</td>
        <td>
            <form:select path="gender" id="filterGender" cssClass="dialogDD ui-widget-content ui-corner-all">
                <form:option selected="selected" value="select" label="Both"/>
                <form:option value="Male" label="Male"/>
                <form:option value="Female" label="Female"/>
            </form:select>
        </td>
    </tr>
</table>
<div id="postcodeSec">
    <table width="100%" border="0" cellspacing="0" cellpadding="0">
        <!-- POSTCODE -->
        <tr>
            <td width="18%" height="40">Post Code:</td>
            <td width="27%" align="right">
                <form:input path="postcode" id="postcodex" cssClass="postal_region_ie8" title="Enter a postcode"
                            onFocus="this.placeholder = ''"
                            onBlur="this.placeholder = 'RM16 6RL'" placeholder="RM16 6RL" style="width:166px;"/>
            </td>
            <td colspan="2" align="left"></td>
        </tr>

    </table>
</div>
<div id="neighbourhoodSec" style="display:none">
    <table width="100%" border="0" cellspacing="0" cellpadding="0">
        <!-- NEIGHBOURHOOD -->
        <tr>
            <td width="12%" height="40">Region:</td>
            <td width="44%" align="left">
                <form:input path="region" id="Neighbourhood" title="Enter a region" onFocus="this.placeholder = ''"
                            onBlur="this.placeholder = 'Enter a region'" placeholder="Enter a region"
                            style="width:232px;"/>
            </td>
            <td colspan="2" align="left"></td>
        </tr>
    </table>
</div>
<div id="countySec" style="display:none">
    <table width="100%" border="0" cellspacing="0" cellpadding="0">
        <!-- COUNTY -->
        <tr>
            <td width="12%" height="40">County:</td>
            <td width="44%" align="left">
                <select name="county" class="inputwidth" id="county" title="select county" style="width:232px;">
                    <optgroup label="England">
                        <option value="Bedfordshire">Bedfordshire</option>
                        <option value="Berkshire">Berkshire</option>
                        <option value="Bristol">Bristol</option>
                        <option value="Buckinghamshire">Buckinghamshire</option>
                        <option value="Cambridgeshire">Cambridgeshire</option>
                        <option value="Cheshire">Cheshire</option>
                        <option value="Cornwall">Cornwall</option>
                        <option value="Cumbria">Cumbria</option>
                        <option value="Derbyshire">Derbyshire</option>
                        <option value="Devon">Devon</option>
                        <option value="Dorset">Dorset</option>
                        <option value="Durham">Durham</option>
                        <option value="East Sussex">East Sussex</option>
                        <option value="Essex">Essex</option>
                        <option value="Gloucestershire">Gloucestershire</option>
                        <option value="Greater Manchester">Greater Manchester</option>
                        <option value="Hampshire">Hampshire</option>
                        <option value="Hereford and Worcester">Hereford and Worcester</option>
                        <option value="Herefordshire">Herefordshire</option>
                        <option value="Hertfordshire">Hertfordshire</option>
                        <option value="Isle of Wight">Isle of Wight</option>
                        <option value="Kent">Kent</option>
                        <option value="Lancashire">Lancashire</option>
                        <option value="Leicestershire">Leicestershire</option>
                        <option value="Lincolnshire">Lincolnshire</option>
                        <option value="London">London</option>
                        <option value="Merseyside">Merseyside</option>
                        <option value="Norfolk">Norfolk</option>
                        <option value="North Yorkshire">North Yorkshire</option>
                        <option value="Northamptonshire">Northamptonshire</option>
                        <option value="Northumberland">Northumberland</option>
                        <option value="Nottinghamshire">Nottinghamshire</option>
                        <option value="Oxfordshire">Oxfordshire</option>
                        <option value="Rutland">Rutland</option>
                        <option value="Shropshire">Shropshire</option>
                        <option value="Somerset">Somerset</option>
                        <option value="South Yorkshire">South Yorkshire</option>
                        <option value="Staffordshire">Staffordshire</option>
                        <option value="Suffolk">Suffolk</option>
                        <option value="Surrey">Surrey</option>
                        <option value="Tyne and Wear">Tyne and Wear</option>
                        <option value="Warwickshire">Warwickshire</option>
                        <option value="West Midlands">West Midlands</option>
                        <option value="West Sussex">West Sussex</option>
                        <option value="West Yorkshire">West Yorkshire</option>
                        <option value="Wiltshire">Wiltshire</option>
                        <option value="Worcestershire">Worcestershire</option>
                        <option value="Yorkshire, East Riding">Yorkshire, East Riding</option>
                        <option value="Yorkshire, North Riding">Yorkshire, North Riding</option>
                        <option value="Yorkshire, West Riding">Yorkshire, West Riding</option>
                    </optgroup>
                    <optgroup label="Wales">
                        <option value="Anglesey">Anglesey</option>
                        <option value="Brecknockshire">Brecknockshire</option>
                        <option value="Caernarfonshire">Caernarfonshire</option>
                        <option value="Carmarthenshire">Carmarthenshire</option>
                        <option value="Cardiganshire">Cardiganshire</option>
                        <option value="Denbighshire">Denbighshire</option>
                        <option value="Flintshire">Flintshire</option>
                        <option value="Glamorgan">Glamorgan</option>
                        <option value="Merioneth">Merioneth</option>
                        <option value="Monmouthshire">Monmouthshire</option>
                        <option value="Montgomeryshire">Montgomeryshire</option>
                        <option value="Pembrokeshire">Pembrokeshire</option>
                        <option value="Radnorshire">Radnorshire</option>
                    </optgroup>
                    <optgroup label="Scotland">
                        <option value="Aberdeenshire">Aberdeenshire</option>
                        <option value="Angus">Angus</option>
                        <option value="Argyllshire">Argyllshire</option>
                        <option value="Ayrshire">Ayrshire</option>
                        <option value="Banffshire">Banffshire</option>
                        <option value="Berwickshire">Berwickshire</option>
                        <option value="Buteshire">Buteshire</option>
                        <option value="Cromartyshire">Cromartyshire</option>
                        <option value="Caithness">Caithness</option>
                        <option value="Clackmannanshire">Clackmannanshire</option>
                        <option value="Dumfriesshire">Dumfriesshire</option>
                        <option value="Dunbartonshire">Dunbartonshire</option>
                        <option value="East Lothian">East Lothian</option>
                        <option value="Fife">Fife</option>
                        <option value="Inverness-shire">Inverness-shire</option>
                        <option value="Kincardineshire">Kincardineshire</option>
                        <option value="Kinross">Kinross</option>
                        <option value="Kirkcudbrightshire">Kirkcudbrightshire</option>
                        <option value="Lanarkshire">Lanarkshire</option>
                        <option value="Midlothian">Midlothian</option>
                        <option value="Morayshire">Morayshire</option>
                        <option value="Nairnshire">Nairnshire</option>
                        <option value="Orkney">Orkney</option>
                        <option value="Peeblesshire">Peeblesshire</option>
                        <option value="Perthshire">Perthshire</option>
                        <option value="Renfrewshire">Renfrewshire</option>
                        <option value="Ross-shire">Ross-shire</option>
                        <option value="Roxburghshire">Roxburghshire</option>
                        <option value="Selkirkshire">Selkirkshire</option>
                        <option value="Shetland">Shetland</option>
                        <option value="Stirlingshire">Stirlingshire</option>
                        <option value="Sutherland">Sutherland</option>
                        <option value="West Lothian">West Lothian</option>
                        <option value="Wigtownshire">Wigtownshire</option>
                    </optgroup>
                    <optgroup label="Northern Ireland">
                        <option value="Antrim">Antrim</option>
                        <option value="Armagh">Armagh</option>
                        <option value="Down">Down</option>
                        <option value="Fermanagh">Fermanagh</option>
                        <option value="Londonderry">Londonderry</option>
                        <option value="Tyrone">Tyrone</option>
                    </optgroup>
                </select>
            </td>
            <td colspan="2" align="left"></td>
        </tr>
    </table>
</div>
<%
	}
%>
<%
	if (userLocal == null) {
%>
<table width="100%" border="0" cellspacing="0" cellpadding="0">

    <tr>
        <td colspan="2">
            <p><strong>
                Please <a href="#create_loginForm" id="login-user3">login</a> to send to a postal region
            </strong></p>
        </td>
    </tr>
    <tr>
        <td colspan="2">&nbsp;

        </td>
    </tr>
</table>
<%
	}
%>
</div>
<span id="region_error" class="error" style="font-size: small;color:red;"></span>
</div>
</c:otherwise>
</c:choose>
</div>
</div>
<div id="postal_region_left" style="display: none;">
    <div class="postal_region_left">
        <strong>INCYYTE POLLING CHARGES</strong>
        <ul>
            <li> Polls that are sent to your own   <br>
                postcode are free.</li>
            <li>Polls sent to members outside  <br>
                your postcode cost 5p per recipient.</li>
        </ul>
    </div>
</div>
<div id="postal_region_right" style="display: none;">

    <div class="postal_region_right">The audience for your poll<br>
        based on your selection above is: <span id="pollCount"><b>0 Poll Recipients</b></span>        
    </div>
</div>
</div>
<!-- 4. Send Your Poll ends-->
<!-- 5. Review and Send starts -->
<div id="review_and_send">
    <h3 class="heading1">5. Review &amp; send</h3>
</div>
<div id="review_and_send_top">
    <table width="100%" border="0" cellspacing="0" cellpadding="0">
        <tr>
            <td width="47%">&nbsp;</td>
            <td width="21%">
                <!-- <a href="#modal_review_poll"  title="REVIEW POLL" ></a> -->
                <div onClick="previewInCyyte();" title="REVIEW POLL" id="createInCyyte.reviewPoll"
                     class="button_gray inline"
                     style="width:140px; z-index: 0;">
                    <span class="title_gray4">REVIEW POLL</span>
                </div>
            </td>
            <td width="4%"><img src="ui/images/arrow1.png"></td>
            <td width="28%">
                <a title="SEND YOUR INCYYTE NOW" id="sendInCyytePoll" class="button_red"
                     style="width:240px;cursor:pointer; cursor:hand; z-index: 0;"><span
                        class="title_red">SEND YOUR INCYYTE NOW</span></a>
            </td>
        </tr>
    </table>
</div>
<!-- 5. Review and Send ends-->
</div>
</article>
<!--content end -->
</div>
</div>
<!-- include footer -->
    <jsp:include page="../common/includes/footer.jsp"/>
<script type="text/javascript" charset="utf-8">
    $(document).ready(function () {
        $('#checkboxDemo input[type=checkbox],#radioDemo input[type=radio]').prettyCheckboxes();
        $('.inlineRadios input[type=radio]').prettyCheckboxes({'display':'inline'});
        $('.checkboxDemo input[type=checkbox]').prettyCheckboxes({'display':'inline'});
    });
</script>
        <%
        	for (int i = 1; i <= model.getAnswers().size() || i <= 3; i++) {
        			AnswerModel answer = new AnswerModel();
        			Iterator<AnswerModel> itr = model.getAnswers().iterator();
        			while (itr.hasNext()) {
        				AnswerModel tempAns = itr.next();
        				if (tempAns.getAnswerOption() != null
        						&& tempAns.getAnswerOption().equals(
        								String.valueOf(i))) {
        					answer = tempAns;
        					break;
        				}
        			}
        			String ansResponse = "resp" + i;
        			String ansAddFilesId = "add_files_" + i;
        			String ansViewFilesId = "view_files_" + i;
        			String errorMessageId = "incyyte_ans_video_error_msg_" + i;
        			String errorImageMessageId = "incyyte_ans_image_error_msg_"
        					+ i;
        			String errorDocMessageId = "incyyte_ans_doc_error_msg_" + i;

        			String errorMessageId2 = "incyyte_ans_video_error_msg2_"
        					+ i;
        			String errorImageMessageId2 = "incyyte_ans_image_error_msg2_"
        					+ i;
        			String errorDocMessageId2 = "incyyte_ans_doc_error_msg2_"
        					+ i;

        			String modalVideos = "modal_videos_" + i;
        			String modalPhotos = "modal_photos_" + i;
        			String modalDocs = "modal_docs_" + i;

        			String viewVideos = "view_videos_" + i;
        			String viewPhotos = "view_photos_" + i;
        			String viewDocs = "view_docs_" + i;

        			String viewAnsVideoFileName = "view_ans_video_file_name_"
        					+ i;
        			String viewAnsPhotoFileName = "view_ans_photo_file_name_"
        					+ i;
        			String viewAnsDocFileName = "view_ans_doc_file_name_" + i;

        			String deleteAnsVideoButton = "ansDeleteVideoButton" + i;
        			String deleteAnsPhotoButton = "ansDeletePhotoButton" + i;
        			String deleteAnsDocButton = "ansDeleteDocButton" + i;

        			String ansVideoDisplay = "answerVideoDisplay_" + i;
        			String ansPhotoDisplay = "answerPhotoDisplay_" + i;
        			String ansDocDisplay = "answerDocDisplay_" + i;

        			String ansPhotoDisplaySrc = "answerPhotoDisplaySrc_" + i;

        			String addVideos = "add_videos_" + i;
        			String addPhotos = "add_photos_" + i;
        			String addDocs = "add_docs_" + i;

        			//These are added for new Google image search functionality
        			String googleSearchValue = "search_value_" + i;
        			String googleSearchSubmit = "search_submit_" + i;
        			String googleSearchResult = "search_result_" + i;
        			String searchSelectedPic = "search_selected_pic_" + i;

        			String ansBrowseVideos = "ans_browse_videos_" + i;
        			String ansBrowsePhotos = "ans_browse_photos_" + i;
        			String ansBrowseDocs = "ans_browse_docs_" + i;

        			String ansVideoFileName = "ans_video_file_name_" + i;
        			String ansPhotoFileName = "ans_photo_file_name_" + i;
        			String ansDocFileName = "ans_doc_file_name_" + i;

        			String uploadAnsVideoButton = "ansUploadVideoButton" + i;
        			String uploadAnsPhotoButton = "ansUploadPhotoButton" + i;
        			String uploadAnsDocButton = "ansUploadDocButton" + i;

        			String ansVideoLoader = "ansVideoLoader_" + i;
        			String ansPhotoLoader = "ansPhotoLoader_" + i;
        			String ansDocLoader = "ansDocLoader_" + i;

        			String ansVideoNotLoaded = "ans_video_not_loaded_" + i;
        			String ansPhotoNotLoaded = "ans_photo_not_loaded_" + i;
        			String ansDocNotLoaded = "ans_doc_not_loaded_" + i;
        			String search_answer_videos = "search_answer_videos_" + i;
        			String searchSubmit_answer_videos = "searchSubmit_answer_videos_"
        					+ i;
        			String googleVideosScrollAnswers = "googleVideosScrollAnswer_"
        					+ i;
        			String ansVideoSelect = "ansVideoSelect_" + i;

        			String startingIndexFornswer = "start-index_answer_" + i;
        			if ((answer.getUploadedAnsFile() != null && StringUtils
        					.isNotBlank(answer.getAnswerFileName()))
        					|| (StringUtils.isNotBlank(answer
        							.getUploadedAnsFileYoutubeVideo()))) {
        %>
<div style='display:none'>
    <div id="<%=ansViewFilesId%>">
        <div id="modal_media_icon">
            <ul>
                <li id="<%=modalVideos%>"><a href="#" alt="Videos" title="Videos"></a></li>
                <li id="<%=modalPhotos%>"><a href="#" alt="Photos" title="Photos"></a></li>
                <li id="<%=modalDocs%>"><a href="#" alt="Docs" title="Docs"></a></li>
            </ul>
        </div>
        <div>
            <!----------Add Video Starts -------------------->
            <div id="<%=viewVideos%>" class="c_add_videos" style="width: 422px;  height: 300px;">
                <table width="100%" border="0" cellspacing="0" cellpadding="0">
                    <tr>
                        <td class="heading1">View Videos</td>
                        <td>&nbsp;</td>
                    </tr>
                    <tr>
                        <td valign="top" align="left">
                            <table width="100%" border="0" cellspacing="0" cellpadding="0">
                                <tr valign="top">
                                    <td class="font_18px"><br> <br>File Name:</td>
                                </tr>
                                <tr>
                                    <td class="font_16px">
                                        <div style="word-wrap: break-word; width:160px;"><span
                                                id="<%=viewAnsVideoFileName%>"></span></div>
                                    </td>
                                </tr>
                                <tr>
                                    <td>&nbsp;</td>
                                </tr>
                            </table>
                        </td>
                        <td align="center" valign="top" id="incyyte_video_loaded">
                            <div id="#video_thumbs">
                                <ul id="videos">
                                    <li>
            				<%
            					if ((answer.getUploadedAnsFile() != null && StringUtils
            										.isNotBlank(answer.getAnswerFileName()))) {
            				%>
            				         <a id="<%=ansVideoDisplay%>" >
                                   						 <img   src="ui/images/video_thumb.png" onClick="javascript:playVideoFromAnswers('<%=ansVideoDisplay%>','player<%=ansVideoDisplay%>', <%=i%>)" class="photos_thumb" alt="tour"/></a>
                                        	<div style="display:none;">
											    <div id="emailList<%=ansVideoDisplay%>" class="emailList ">
													<a style="cursor:pointer" id="player<%=ansVideoDisplay%>" ></a>
											    </div>
											</div>
							<%
								} else {
							%>						
											<a id="<%=ansVideoDisplay%>" >
                                   						 <img   id="viewAnswerYouTubeVideoId" src="ui/images/video_thumb.png"  onClick="javascript:playYoutubeVideoFromAnswers('<%=ansVideoDisplay%>', '<%=answer.getUploadedAnsFileYoutubeVideo()%>' )" class="photos_thumb" alt="tour"/>
											<div style="display:none;">
												<div id="emailListYouTube<%=ansVideoDisplay%>" class="emailList " >
											    	<iframe width='390' id="iFrameYouTube<%=ansVideoDisplay%>"  height='308'  src="ui/images/uploading_big_img.png" frameborder='0' type='text/html'></iframe>
											    </div>
											</div> 
							<%
 								}
 							%>	
                                    </li>
                                </ul>
                            </div>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="2" valign="bottom"><span class="licence">You must have the licence to use this Video</span>
                            <div title="Delete" id="<%=deleteAnsVideoButton%>" class="button_red"
                                 style="width:140px; float:right;"><span class="title_red">DELETE</span></div>
                        </td>
                    </tr>
                </table>
            </div>
            <!------------- Add ULR end------------->
            <!----------Add Photos Start-------------------->
            <div id="<%=viewPhotos%>" style="display:none;width: 422px; height: 300px;" class="c_add_photos">
                <table width="100%" border="0" cellspacing="0" cellpadding="0">
                    <tr>
                        <td class="heading1">View Photos</td>
                        <td>&nbsp;</td>
                    </tr>
                    <tr>
                        <td valign="top" align="left">
                            <table width="100%" border="0" cellspacing="0" cellpadding="0">
                                <tr valign="top">
                                    <td class="font_18px"><br> <br>File Name:</td>
                                </tr>
                                <tr>
                                    <td class="font_16px">
                                        <div style="word-wrap: break-word; width:160px;"><span
                                                id="<%=viewAnsPhotoFileName%>"></span></div>
                                    </td>
                                </tr>
                                <tr>
                                    <td>&nbsp;</td>
                                </tr>
                            </table>
                        </td>
                        <td align="center" valign="top" id="incyyte_photo_loaded">
                            <div id="#video_thumbs">
                                <ul id="videos">
                                    <li><a id="<%=ansPhotoDisplay%>" href="" title="<%=model.getAnswerFileName1()%>"
                                           target="_blank"
                                           class=" fancybox-popup">
                                        <img id="<%=ansPhotoDisplaySrc%>" src="" class="photos_thumb"
                                             alt="tour"/>
                                    </a></li>
                                </ul>
                            </div>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="2" valign="bottom"><span class="licence">You must have the licence to use this image</span>

                            <div title="Delete" id="<%=deleteAnsPhotoButton%>" class="button_red"
                                 style="width:140px; float:right;"><span class="title_red">DELETE</span></div>
                        </td>
                    </tr>
                </table>
            </div>
            <!----------Add Photos end-------------------->
            <!----------Add docs Start-------------------->
            <div id="<%=viewDocs%>" style="display:none;width: 422px;  height: 300px;" class="c_add_docs">
                <table width="100%" border="0" cellspacing="0" cellpadding="0">
                    <tr>
                        <td class="heading1">View Docs</td>
                        <td>&nbsp;</td>
                    </tr>
                    <tr>
                        <td valign="top" align="left">
                            <table width="100%" border="0" cellspacing="0" cellpadding="0">
                                <tr valign="top">
                                    <td class="font_18px"><br><br>File Name:</td>
                                </tr>
                                <tr>
                                    <td class="font_16px">
                                        <div style="word-wrap: break-word; width:160px;"><span
                                                id="<%=viewAnsDocFileName%>"></span></div>
                                    </td>
                                </tr>
                                <tr>
                                    <td>&nbsp;</td>
                                </tr>
                            </table>
                        </td>
                        <td align="center" valign="top" id="incyyte_doc_loaded">
                            <div id="#video_thumbs">
                                <ul id="videos">
                                    <li><a id="<%=ansDocDisplay%>" href=""><img
                                            src="ui/images/view_docs_thumb.png" class="photos_thumb"
                                            alt="tour"/></a></li>
                                </ul>
                            </div>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="2" valign="bottom">
                            <div title="Delete" id="<%=deleteAnsDocButton%>" class="button_red"
                                 style="  width:140px; float:right;"><span class="title_red">DELETE</span></div>
                        </td>
                    </tr>
                </table>
            </div>
            <!----------Add docs end-------------------->
        </div>
    </div>
</div>
        <%
        	} else {
        %>
<div style='display:none'>
<div id="<%=ansAddFilesId%>">
<div id="modal_media_icon">
    <ul>
        <li id="<%=modalVideos%>"><a href="#" alt="Videos" title="Videos" class="active"></a></li>
        <li id="<%=modalPhotos%>"><a href="#" alt="Photos" title="Photos"></a></li>
        <li id="<%=modalDocs%>"><a href="#" alt="Docs" title="Docs"></a></li>
    </ul>
</div>
<div>
<!----------Add Video Starts -------------------->
<div id="<%=addVideos%>" class="c_add_videos">
    <table width="522" border="0" cellspacing="0" cellpadding="0">
        <tr>
            <td class="heading1">Add Videos</td>
        </tr>
        <tr>
            <td valign="top" width="50%" align="left">
                <table width="100%" border="0" cellspacing="0" cellpadding="0">
                    <tr>
                        <td>
                            <div class="fileInputs">
                                <div id="<%=ansBrowseVideos%>" class="button_gray"
                                     style=" width:140px; float:left; margin-bottom:20px; position: absolute; top: 0px;left: 0px; z-index: 1;">
                                    <span class="title_gray3">BROWSE</span></div>
                            </div>
                        </td>
                    </tr>
                    <tr>
			<td colspan="3">&nbsp;</td>
                    </tr>
                    <tr valign="bottom" style="display: none;" id="<%=errorMessageId%>">
                        <td><span class="errorLabel">Please upload the correct video format (flv, mp4, ..)</span></td>
                    </tr>
                    <tr valign="bottom" style="display: none;" id="<%=errorMessageId2%>">
                        <td><span class="errorLabel">The uploaded video exceeds the maximum allowed size(5 MB)</span>
                        </td>
                    </tr>
                    <tr valign="bottom">
                        <td align="center" style="position: absolute;z-index: 200; float: left; margin-left: 118px; margin-top: 107px;"><img src="ui/images/indicator-loader.gif"
                                                                                                                                             id="<%=ansVideoLoader%>"
                                                                                                                                             width="32" height="32"
                                                                                                                                             style="padding:8px 0 0 0;margin-bottom:20px 0 100px; "></td>
                    </tr>
                </table>
            </td>
        </tr>
                    <tr>
                        <td id="<%=ansVideoNotLoaded%>">
                <div class="upload_photo_searchbox" style="margin-bottom:0">
                    <p class="sort_by_text">Search Google Videos</p>
                    
                    <div class="searchmain">
                        <div>
                            <input type="text" name="search" id="<%=search_answer_videos%>"  onKeydown="Javascript: if (event.keyCode==13 || event.which==13) {$('#'+'<%=googleVideosScrollAnswers%>').html(''); makeYoutubeApiCall('<%=search_answer_videos%>' , '<%=startingIndexFornswer%>' ,  '<%=googleVideosScrollAnswers%>' , '<%=ansVideoSelect%>');}">
                            <input type="submit" id="<%=searchSubmit_answer_videos%>" value=""/>
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
                        <div id="<%=googleVideosScrollAnswers%>"
                             style="width:319px; height:198px; overflow-x:hidden; overflow-y:auto; margin-right: 10px;">
                        </div>
                    </td>
                    <td align="right">
                        <div>
                        <iframe width='320' id="<%=ansVideoSelect%>"  height='190' class="upload_photos_container_big_img" src="ui/images/uploading_big_img.png" frameborder='0' type='text/html'></iframe>
                        </div>
                    </td>
                </tr>
            </table>
        </tr>
        <tr>
            <td>
                <div class="upload_photo_add_more"><a href="javascript:showMoreVideos('<%=search_answer_videos%>' , '<%=startingIndexFornswer%>' ,  '<%=googleVideosScrollAnswers%>' , '<%=ansVideoSelect%>');"> + Show more Videos </a></div>
            </td>
        </tr>
        <tr>
            <td colspan="2" valign="bottom"><span class="licence">You must have the licence to use this Video</span>
                <div title="Upload Now" id="<%=uploadAnsVideoButton%>" class="button_red"
                     style="width:140px; float:right;"><span class="title_red1">UPLOAD NOW</span></div>
            </td>
        </tr>
    </table>
</div>
<!------------- Add Video For answers  end------------->
<!----------Add Photos Start-------------------->
<div id="<%=addPhotos%>" class="c_add_photos">
    <table  width="522" border="0" cellspacing="0" cellpadding="0">
        <tr>
            <td class="heading1" colspan="2">Add Photos  </td>
        </tr>
        <tr>
            <td valign="top" align="left" width="30%">
                <table width="100%" border="0" cellspacing="0" cellpadding="0">
                    <tr>
                        <td width="30%" >
                            <div class="fileInputs">
                                <div id="<%=ansBrowsePhotos%>" class="button_gray"
                                     style=" width:140px; float:left; margin-bottom:20px; position: absolute; top: 0px;left: 0px; z-index: 1;">
                                    <span class="title_gray">BROWSE</span></div>
                            </div>
                        </td>
                        <td colspan="2" class="font_16px"><br><span style="float: left;">File Name:</span><span style= "float: left; margin-left: 10px;" id="<%=ansPhotoFileName%>"></span></td>
                    </tr>
                    <tr>
                        <td colspan="3">&nbsp;</td>
                    </tr>
                    <tr valign="bottom" style="display: none;" id="<%=errorImageMessageId%>">
                        <td colspan="3"><span class="errorLabel">Please upload the correct image format (jpg, gif, ..)</span></td>
                    </tr>
                    <tr valign="bottom" style="display: none;" id="<%=errorImageMessageId2%>">
                        <td colspan="3"><span class="errorLabel">The uploaded image exceeds the maximum allowed size(2 MB)</span></td>
                    </tr>
                    <tr valign="bottom" style="position: absolute;z-index: 200; float: left; margin-left: 410px; margin-top: 140px;">
                        <td colspan="3" align="center"><img src="ui/images/indicator-loader.gif"
                                                            id="<%=ansPhotoLoader%>"
                                                            width="32" height="32"
                                                            style="padding:8px 0 0 0;margin-bottom:20px 0 100px; "></td>
                    </tr>
                </table>
            </td>
        </tr>
        <tr>
            <td>
                <div class="upload_photo_searchbox" style="margin-bottom:0">
                    <p class="sort_by_text">Search Google images</p>
                    <div class="searchmain">
                        <div>
                            <input type="text" name="search" id="<%=googleSearchValue%>"  onKeydown="Javascript: if (event.keyCode==13 ) googleImageSearchForAns<%=i%>();"  />
                            <input type="submit" id="<%=googleSearchSubmit%>" value=""/>
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
                        <div id="<%=googleSearchResult%>" style="width:319px; height:171px; overflow-x:hidden; overflow-y:auto; margin-right: 10px;">
                        </div>
                    </td>
                    <td align="right">
                        <div><img id="<%=searchSelectedPic%>" class="upload_photos_container_big_img" src="ui/images/uploading_big_img.png"></div>
                    </td>
                </tr>
            </table>
        </tr>
        <tr>
            <td>
                <div class="upload_photo_add_more"><a href="javascript:showImages();"> + Show more images </a></div>
            </td>
        </tr>
        <tr>
            <td colspan="2" valign="bottom"><span class="licence">You must have the licence to use this image</span>
                <div title="Upload Now" id="<%=uploadAnsPhotoButton%>" class="button_red"
                     style="  width:140px; float:right;"><span class="title_red1">UPLOAD NOW</span></div>
            </td>
        </tr>
    </table>
</div>
<!----------Add Photos end-------------------->
<!----------Add docs Start-------------------->
<div id="<%=addDocs%>" style="display:none;" class="c_add_docs">
    <table width="522" border="0" cellspacing="0" cellpadding="0">
        <tr>
            <td class="heading1">Add Docs</td>
            <td>&nbsp;</td>
        </tr>
        <tr>
            <td valign="top" align="left">
                <table width="100%" border="0" cellspacing="0" cellpadding="0">
                    <tr>
                        <td>
                            <div class="fileInputs">
                                <div id="<%=ansBrowseDocs%>" class="button_gray"
                                     style=" width:140px; float:left; margin-bottom:20px; position: absolute; top: 0px;left: 0px; z-index: 1;">
                                    <span class="title_gray">BROWSE</span></div>
                            </div>
                        </td>
                    </tr>
                    <tr>
                        <td>&nbsp;&nbsp;</td>
                    </tr>
                    <tr>
                        <td>
                            <div style="margin-top: 80px; float: left;" class="font_18px">Have you tried uploading<br> a photo to your poll?</div>
                            <div style="float: left; margin-top: 20px; line-height: 20px;" class="font_16px">
                                Hint:
                                You can Upload images <br>
                                clips or documents to <br>
                                share on your poll   <br>
                                using the buttons    <br>
                                above.
                            </div>
                        </td>
                    </tr>
                </table>
            </td>
            <td align="center" valign="top" >
                <table>
                    <tr>
                        <td class="font_16px" ><span style="float: left;">File Name:</span><span  style="word-wrap: break-word; float: left; width:190px;" id="<%=ansDocFileName%>"></span></td>
                    </tr>
                    <tr>
                        <td>&nbsp;</td>
                    </tr>
                    <tr valign="bottom" style="display: none;" id="<%=errorDocMessageId%>">
                        <td><span class="errorLabel">Please upload the correct document format (doc, pdf, ..)</span>
                        </td>
                    </tr>
                    <tr valign="bottom" style="display: none;" id="<%=errorDocMessageId2%>">
                        <td><span class="errorLabel">The uploaded document exceeds the maximum allowed size(2 MB)</span>
                        </td>
                    </tr>
                    <tr valign="bottom">
                        <td align="center" style="position: absolute;z-index: 200; float: left; margin-left: 118px; margin-top: 107px;"><img src="ui/images/indicator-loader.gif" id="<%=ansDocLoader%>"
                                                                                                                                             width="32" height="32"
                                                                                                                                             style="padding:8px 0 0 0;margin-bottom:20px 0 100px; "></td>
                    </tr>
                    <tr>
                        <td id="<%=ansDocNotLoaded%>"><div id="video_tumb_container" >
                            <img src="ui/images/photo1.png" height="260" width="260"></div></td>
                    </tr>
                </table>

            </td>
        </tr>
        <tr>
            <td colspan="2" valign="bottom">
                <div title="Upload Now" id="<%=uploadAnsDocButton%>" class="button_red"
                     style="  width:140px; float:right;"><span class="title_red1">UPLOAD NOW</span></div>
            </td>
        </tr>
    </table>
</div>
<!----------Add docs end-------------------->
</div>
</div>
</div>
     </div>   <%
   	}
   		}
   %>
</form:form>
<div id="poll_pay_pop" >
    <div id="cboxClose" style="right: -25px;"></div>
    <div class="poll_pay">
        <table width="100%" >
            <tr>
                <td >
                    <img style="float: left;" src="ui/images/poll_pay_popup-icon.png">
                    <div class="preview_ask_question" style="margin-left: 60px; color: #1b303f;line-height: 30px;" >You're about to send a poll<br> outside of your region!</div>
                </td>
            </tr>
            <tr><td  class="preview_ask_question" style="font-size: 16px; color: #1b303f; margin-left: 30px; float: left; line-height: 20px; ">
                Click `PROCEED` to view charges, make a payment<br>
                 and to send your poll.
            </td> </tr>
            <tr>
            <td class="preview_info_txt" style=" color: #1B303F; float: left;font-size: 14px; font-weight: bold; line-height: 20px;  margin-left: 30px; margin-top: 10px;">
                inCyyte polls sent to entire postcodes, regions or     <br>
                counties are chargeable at a rate of ${inCyyteForm.pollCharge}p per  
                <br>poll recipient.
            </td>
            </tr>
            <tr><td  class="preview_info_txt" style=" color: #1B303F; float: left;font-size: 14px; font-weight: bold; line-height: 20px;  margin-left: 30px; margin-top: 10px;">
                <br>
            </td>
            </tr>
            <tr>
                <td width="266" align="left" style="height:50px; "><a class="poll_button1" style="width:170px;"  href="#" onclick="toPayment();"><span class="poll_button_green ">Proceed</span></a></td>
            </tr>
        </table>
    </div>
</div>

<!-- This check is to ask user to reconfirm uploaded images / videos are loaded properly before submit-->
<div id="upload_content_pop" style="display:none;">
    <div id="upload_content" class="msg_box">
        <table width="100%" >
            <tr><td colspan="2">
            <p class="heading1">Uploaded videos/images</p><br>
            </td></tr>
            <tr><td colspan="2" align="center">
                <span style="color: #1b4d5f;font-size:20px;">Unfortunately, sometimes uploads may fail.<br> Have you checked that your images are viewable?<br><br><br></span>
            </td> </tr>
            <tr>
                <td width="266" align="left" style="height:50px;"><a class="poll_button2" href="javascript:checkUploadedContent();"><span class="poll_button_green">Go Back</span></a></td>
                <td width="266" align="left" style="height:50px;"><a class="poll_button2" href="javascript:sendInCyytePoll();"><span class="poll_button_red">Send Anyway</span></a></td>
            </tr>
        </table>
    </div>
</div>
<div id="mask"></div>
<div style="display: none;">
    <div id="contactEmailList">
        <div id="share_contacts">
            <h3 class="heading1" style="padding:2px 0 8px 0px;">Add Contacts</h3>
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
                <div class="viewport">
                    <div class="overview">
                        <table width="380" border="0" cellspacing="0" cellpadding="0" id="contact_list">
                            <tr>
                                <td colspan="12" class="select_all">Select: <a rel="group_1"   href="javascript:checked('#selectall');">All</a>
                                <a rel="group_1" href="javascript:checked('#select_none');">None</a></td>
                            </tr>
                        </table>
                        <div style="width:380px; height:4px;"></div>
                        <!-- <div style="width:400px; height:200px; overflow:auto;"> -->
                        <div id="contactScroll" style="width:380px; height:200px; overflow-x:hidden; overflow-y:auto;">
                            <table width="380" border="0" id="contactsTable"  cellspacing="0" cellpadding="0">
                                <%-- Now this code is not required as we are loading the contacts dynamically 
                                <c:forEach items="${emailContactList}" var="UserContactModel">
                                    <tr>
                                        <td style="padding-left:10px;padding-right:10px;">
			                 				<span id="group_1">
			 										<input type="checkbox" class="contactList"
                                                            name="selectedGroupContactsList"
                                                            id="contactId${UserContactModel.contactid}"
                                                            value="${UserContactModel.email}"/>
			 		              			</span>
                                        </td>
                                        <td height="40" style="padding-right:10px;"><img
                                                src='${UserContactModel.profile_img}' width="36" height="35"
                                                alt="User Photo"></td>
                                        <td>${UserContactModel.email}</td>
                                        <td>${UserContactModel.username}</td>
                                    </tr>
                                </c:forEach> --%>
                            </table>
                    <table width="20%" border="0" cellpadding="0" cellspacing="0">
				     <tr style="padding-right: 300px;">
					  <td align="middle" style="padding-left: 230px;" >
						<div><input value="Display more contacts" type="button" onClick="javascript:yHandler()" style="cursor: pointer;border: solid 1px grey;" id="create_incyyte"></div>
					 </td>
				   </tr>
				 </table>
                   </div>
                 </div>
               </div>
            </div>
            <div style="width:380px; height:4px;"></div>
            <table width="380" border="0" cellspacing="0" cellpadding="0">
                <tr>
                    <td valign="bottom" width="60%"><label class="panelLink" id="importContact"><span class="importSpan"
                                                           title="Import Contacts">Import new contacts</span></label>
                    </td>
                    <td>
                        <button type="button" title="Add emails" id="add_email_btn" class="button_green1" onclick="addContactEmailList();"  style="width:107px; margin:20px 0 0 100px;"><span class="title_green1">Add</span>
                        </button>
                    </td>
                </tr>
            </table>
        </div>
    </div>
</div>

<div id="Poll_save_confirm">
    <div class="Poll_save_confirm_bg">
        <div class="edit_pro_pop_txt">
        </div>
        <div style="height:auto; margin-left: 135px; float: left; margin-top: 25px;">
        	<a class="poll_button1" href="#" style="width:170px;" id="okButton">
        		<span class="poll_button_red ">Ok</span>
        	</a>
        </div>
    </div>
</div>
<script type="text/javascript">
    $( '#testtt').click(function() {
        $('html, body').animate({scrollTop:0}, 'fast');
        //show the mask and contact divs
        $('#mask').show().fadeTo('', 0.7);
        $('div#poll_pay_pop').fadeIn();
        // stop the apply link from doing its default action
        return false;
    });
    // close the apply window is close div or mask div are clicked.
    $('div#mask, div#cboxClose').click(function() {
        $('div#poll_pay_pop, div#mask').stop().fadeOut('slow');
    });
</script>

<script type="text/javascript" >
    $("#sendInCyytePoll").click(function(){
        var question = document.getElementById("myTextarea3").value;
        if(question == "Type your question here..."){
            document.getElementById("myTextarea3").value="";
           	document.getElementById("myTextarea3").placeholder="Type your question here...";
        }
        submitInCyytePoll();
    });
</script>
<script type="text/javascript">
$(document).ready(function(){
	$("#searchSubmit_new_question_videos").click(function() {
        $('#googleVideosScroll').html("");
        makeYoutubeApiCall('search_new_question_videos' , 'start-index' , 'googleVideosScroll' , 'quesVideoSelect' );
	});

	$("#searchSubmit_answer_videos_1").click(function(){
        $('#googleVideosScrollAnswer_1').html("");
        makeYoutubeApiCall('search_answer_videos_1' , 'start-index_answer_1' ,  'googleVideosScrollAnswer_1' , 'ansVideoSelect_1');
	});
	$("#searchSubmit_answer_videos_2").click(function(){
        $('#googleVideosScrollAnswer_2').html("");
        makeYoutubeApiCall('search_answer_videos_2' , 'start-index_answer_2' ,  'googleVideosScrollAnswer_2' , 'ansVideoSelect_2');
	});
	$("#searchSubmit_answer_videos_3").click(function(){
        $('#googleVideosScrollAnswer_3').html("");
        makeYoutubeApiCall('search_answer_videos_3' , 'start-index_answer_3' ,  'googleVideosScrollAnswer_3' , 'ansVideoSelect_3');
	});
	$("#searchSubmit_answer_videos_4").click(function(){
        $('#googleVideosScrollAnswer_4').html("");
        makeYoutubeApiCall('search_answer_videos_4' , 'start-index_answer_4' ,  'googleVideosScrollAnswer_4' , 'ansVideoSelect_4');
	});
	$("#searchSubmit_answer_videos_5").click(function(){
        $('#googleVideosScrollAnswer_5').html("");
        makeYoutubeApiCall('search_answer_videos_5' , 'start-index_answer_5' ,  'googleVideosScrollAnswer_5' , 'ansVideoSelect_5');
	});
	$("#searchSubmit_answer_videos_6").click(function(){
        $('#googleVideosScrollAnswer_6').html("");
        makeYoutubeApiCall('search_answer_videos_6' , 'start-index_answer_6' ,  'googleVideosScrollAnswer_6' , 'ansVideoSelect_6');
	});
	$("#searchSubmit_answer_videos_7").click(function(){
        $('#googleVideosScrollAnswer_7').html("");
        makeYoutubeApiCall('search_answer_videos_7' , 'start-index_answer_7' ,  'googleVideosScrollAnswer_7' , 'ansVideoSelect_7');
	});
	$("#searchSubmit_answer_videos_8").click(function(){
        $('#googleVideosScrollAnswer_8').html("");
        makeYoutubeApiCall('search_answer_videos_8' , 'start-index_answer_8' ,  'googleVideosScrollAnswer_8' , 'ansVideoSelect_8');
	});
	$("#searchSubmit_answer_videos_9").click(function(){
        $('#googleVideosScrollAnswer_9').html("");
        makeYoutubeApiCall('search_answer_videos_9' , 'start-index_answer_9' ,  'googleVideosScrollAnswer_9' , 'ansVideoSelect_9');
	});
	$("#searchSubmit_answer_videos_10").click(function(){
        $('#googleVideosScrollAnswer_10').html("");
        makeYoutubeApiCall('search_answer_videos_10' , 'start-index_answer_10' ,  'googleVideosScrollAnswer_10' , 'ansVideoSelect_10');
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
	
function selectVideoForUpload(videoId , selectedVideoImageId ){
	setVideoIdForModelObjects(videoId , selectedVideoImageId);
	$("#"+selectedVideoImageId ).attr("src", "http://www.youtube.com/embed/"+videoId);	
}
function setVideoIdForModelObjects(videoId , selectedVideoImageId) {
	if(selectedVideoImageId == "quesVideoSelect") {
		$("#youTubeQuestionVideoId").val(videoId);

		$("#incyyte_video_file_input").val('');
		$("#view_video_file_name").text('');
	}
	if (selectedVideoImageId == "ansVideoSelect_1") {
		$("#youTubeAnswer_1_VideoId").val(videoId);
	}else if (selectedVideoImageId == "ansVideoSelect_2") {
		$("#youTubeAnswer_2_VideoId").val(videoId);
		
	} else if (selectedVideoImageId == "ansVideoSelect_3") {
		$("#youTubeAnswer_3_VideoId").val(videoId);
	
	} else if (selectedVideoImageId == "ansVideoSelect_4") {
		$("#youTubeAnswer_4_VideoId").val(videoId);
	
	} else if (selectedVideoImageId == "ansVideoSelect_5") {
		$("#youTubeAnswer_5_VideoId").val(videoId);
	
	} else if (selectedVideoImageId == "ansVideoSelect_6") {
		$("#youTubeAnswer_6_VideoId").val(videoId);
	
	} else if (selectedVideoImageId == "ansVideoSelect_7") {
		$("#youTubeAnswer_7_VideoId").val(videoId);
	
	} else if (selectedVideoImageId == "ansVideoSelect_8") {
		$("#youTubeAnswer_8_VideoId").val(videoId);
		
	} else if (selectedVideoImageId == "ansVideoSelect_9") {
		$("#youTubeAnswer_9_VideoId").val(videoId);
		
	} else if (selectedVideoImageId == "ansVideoSelect_10") {
		$("#youTubeAnswer_10_VideoId").val(videoId);
			
	}

}
</script>
<![endif]-->
<jsp:include page="../include/pollEmailCount.jsp" />
<script type="text/javascript" src="ui/js/googleanalytics.js"></script>
</body>
</html>