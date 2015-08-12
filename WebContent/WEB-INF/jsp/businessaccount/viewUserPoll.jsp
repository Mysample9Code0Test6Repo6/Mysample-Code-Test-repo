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

<%@include file="/WEB-INF/jsp/login_model/viewUserModelLogin.jsp"%>
<% User user = (User)request.getSession().getAttribute(SessionKeys.LOGIN_USER);
    User incyyteUser = (User) session.getAttribute("incyyteCreator");
    InCyyte cyyte = (InCyyte) session.getAttribute(SessionKeys.INCYYTE);
    boolean isUserVoted = (Boolean)session.getAttribute("isUserVoted");
    InCyyteChart  cyyteChart = (InCyyteChart)request.getSession().getAttribute(SessionKeys.INCYYTE_CHART);
    UserSettingsModel userSettingsModel = (UserSettingsModel)request.getSession().getAttribute(SessionKeys.LOGIN_USER_SETTINGS);%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="content-type" content="text/html; charset=UTF-8">
<link rel="shortcut icon" href="${pageContext.request.contextPath}/favicon.ico" />
<meta charset="utf-8">
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
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/ui/css/ratingbar.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/ui/css/prettyCheckboxes.css" type="text/css" media="screen">
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

<script>
    if (/*@cc_on!@*/false) {
        var headHTML = document.getElementsByTagName('head')[0].innerHTML;
        headHTML    += '<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/ui/css/ie10.css">';
        document.getElementsByTagName('head')[0].innerHTML = headHTML;
    }
</script>

<!--[if gte IE 9]>
<link href="${pageContext.request.contextPath}/ui/css/gte_ie9.css" media="screen" rel="stylesheet" type="text/css"/>
<![endif]-->
<script  language="JavaScript" type="text/javascript" src="${pageContext.request.contextPath}/ui/js/jquery-ui-1.8.16.custom.min.js"></script>
<script language="JavaScript" type="text/javascript" src="${pageContext.request.contextPath}/ui/js/jquery-1.8.1.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/ui/js/jquery_profile_rating.js"></script>
<script  language="JavaScript" src="${pageContext.request.contextPath}/ui/js/viewUserPoll.js"></script>
<script language="JavaScript" src="${pageContext.request.contextPath}/ui/js/table2CSV.js"></script>

<script src="${pageContext.request.contextPath}/ui/js/prettyCheckboxes.js" type="text/javascript" charset="utf-8"></script>

<script  language="JavaScript" src="${pageContext.request.contextPath}/ui/js/tooltip.js"></script>
<script  language="JavaScript" src="${pageContext.request.contextPath}/ui/js/jquery.color.js"></script>
<script  language="JavaScript" src="${pageContext.request.contextPath}/ui/js/search_script.js"></script>
<script  language="JavaScript" src="${pageContext.request.contextPath}/ui/js/jquery_poll_rating.js" type="text/javascript"></script>
<script  language="JavaScript" type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.form.js"></script>
<script  language="JavaScript" type="text/javascript" src="${pageContext.request.contextPath}/ui/js/jquery-ui.js"></script>
<script  language="JavaScript" type="text/javascript" src="${pageContext.request.contextPath}/ui/js/jquery.lightbox-0.5.js" ></script>
<script  language="JavaScript" type="text/javascript" src="${pageContext.request.contextPath}/ui/js/validate/jquery.validate.js" ></script>
<script src="${pageContext.request.contextPath}/ui/js/login.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/ui/js/jquery.orbit.js"></script>
<!-- Chart Script Start here -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/ui/Fancy-box/jquery.fancybox.css" type="text/css" media="screen" />
<script type="text/javascript" src="${pageContext.request.contextPath}/ui/Fancy-box/jquery.fancybox.pack.js"></script>
<script type="text/javascript">
    $(function(){
         $('.fancybox-popup').fancybox();
    });
</script>
<script type="text/javascript" src="${pageContext.request.contextPath}/ui/fancyplayer_code/js/jquery.easing.1.3.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/flowplayer/flowplayer-3.2.12.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/ui/fancyplayer_code/js/flowplayer-3.1.1.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/ui/fancyplayer_code/js/fancyplayer.js"></script>
<!-- <script src="ui/js/charts/js/charts.js"></script> -->

<script src="${pageContext.request.contextPath}/ui/js/dashboard/dash_my_polls.js"></script>
<script src="${pageContext.request.contextPath}/ui/js/ba-linkify.js"></script>
<!-- Chart Script end here -->
<script  language="JavaScript" src="${pageContext.request.contextPath}/ui/modal/colorbox/jquery.colorbox.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/ui/js/placeHolder.js"></script>
<%--<script src="${pageContext.request.contextPath}/ui/js/jquery.placeholder.js"></script>
<script>
    $(function() {
        $('input, textarea').placeholder();
    });
    </script>--%>

<!-- Left Navigation script starts here -->
<script  language="JavaScript" src="${pageContext.request.contextPath}/ui/js/jquery.selectbox-0.2.js"></script>
<!-- Left Navigation script ends here -->

<style type="text/css">
    #featured { width: 655px; height: 370px; margin-left: 2px; margin-top: 2px;  overflow: hidden; }
        /* Want a different Loading GIF - visit http://www.webscriptlab.com/ - that's where we go this one :) */
</style>
<script type="text/javascript">

function removeCbox() {
		$("#pollMessageContent").val("");
		parent.$.fn.colorbox.close();
       	window.location.reload();		
 }
 
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
    url: contextVar+'/sendMessageToPollRecipients.cyt',
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
    }
});
}

  function sendFriendRequest(username, counter){
		 $("#commentby").val(username);
		    $("#friendRequest").ajaxSubmit({
		        type: 'POST',
		        url: '../friendRequest.cyt',
		        success: function (data) {
	                var button = $('#dropBoxButton2'+counter);
	                var box = $('#dropBox2'+counter);
	                button.removeClass('active');
	                box.hide();

	                //display message
	                if (data.search("error") == 0) {
		                $("#message_" + counter).text(data.substring(5));
		                $("#message_" + counter).css("color", "fireBrick");
		                $("#message_" + counter).css("display", "");
		            }else{	                
		                $("#message_" + counter).text("request sent to " + username );
		                $("#message_" + counter).css("display", "");	            	
		            }
		        },
		        error: function (jqXHR, textStatus, errorThrown) {
	                $("#message_" + counter).text("There was an ERROR with processing this request.");
	                $("#message_" + counter).css("color", "fireBrick");
	                $("#message_" + counter).css("display", "");
		        }
		    });
	}
  
  function CommentIconAvailability(counter){
		$("#commenticonsuccess"+counter).text("AVAILABLE SOON!");
	    $("#commenticonsuccess"+counter).css("display", "");
	}
  
  function linkify(inputText) {
	    var replacedText, replacePattern1, replacePattern2, replacePattern3;

	    //URLs starting with http://, https://, or ftp://
	    replacePattern1 = /(\b(https?|ftp):\/\/[-A-Z0-9+&@#\/%?=~_|!:,.;]*[-A-Z0-9+&@#\/%=~_|])/gim;
	    replacedText = inputText.replace(replacePattern1, '<a href="$1" target="_blank">$1</a>');

	    //URLs starting with "www." (without // before it, or it'd re-link the ones done above).
	    replacePattern2 = /(^|[^\/])(www\.[\S]+(\b|$))/gim;
	    replacedText = replacedText.replace(replacePattern2, '$1<a href="http://$2" target="_blank">$2</a>');

	    //Change email addresses to mailto:: links.
	    replacePattern3 = /(\w+@[a-zA-Z_]+?\.[a-zA-Z]{2,6})/gim;
	    replacedText = replacedText.replace(replacePattern3, '<a href="mailto:$1">$1</a>');

	    return replacedText;
	}
  
  $(document).ready(function () {
	    $('.cbox input[type=checkbox]').prettyCheckboxes();
	});
  
 </script>
<!--  adding silver member code-->
 <script type="text/javascript" charset="utf-8">
$(document).ready(function() {
	
	var checked =  $("#ed_checked").val();
    if (checked != undefined) {
        var ids = checked.split(",");
        for( var i = 1 ; i <= ids.length ; i++){
            var labelid = "checkboxLabel_"+ids[i];
            $('#'+labelid).addClass('checked');
        }
	}
	
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

 
 <%UserPollPageModel model = (UserPollPageModel) request.getSession().getAttribute("pollSetup");
 %>

<%
	if (isUserVoted == true) {
%>
    <script src="${pageContext.request.contextPath}/ui/js/charts/js/highcharts.js"></script>
    <script src="${pageContext.request.contextPath}/ui/js/charts/js/chart_expanded_incyyte.js"></script>
<%
	}
%>

<% if (isUserVoted == false) { %>

     <script src="${pageContext.request.contextPath}/ui/js/customInput.jquery.js" type="text/javascript"></script>
     <script type="text/javascript">
		// Run the script on DOM ready:
		$(function(){
			$('input[type=radio]').customInput();
		});
	</script> 
<%	} %>
 
<%
	if (isUserVoted == true) {
%>
 <script  language="JavaScript" type="text/javascript"  charset="utf-8">
    $(document).ready(function () {
        $("#td1BeforeVoted").css("display", "none");
        $("#td2BeforeVoted").css("display", "none");

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
                    <%for (CyyteResponse cyyteResponse : cyyteChart.getCyyteResponses()) {%>
                    ['<%=cyyteResponse.getAnswer()%>',    <%=cyyteResponse.getResponse()%>],
                    <%}%>
                ]
            }]
        });
    });
</script>
<%}%>
<script type="text/javascript">
    function playVideo(id,playerId,playUrl) {
        parent.$.fn.colorbox({href:'div#emailList'+id, open:true, inline:true});
        $f(playerId, "../js/flowplayer/flowplayer-3.2.16.swf", {
            clip: {
                url: playUrl,
                autoPlay: true,
                autoBuffering: true
            },
            plugins: {
                controls:{
                    url: '../js/flowplayer/flowplayer.controls-3.2.15.swf',
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
    function playVideoBeforeVoted(id,playerId,playUrl) {
        parent.$.fn.colorbox({href:'div#emailListBeforeVoted'+id, open:true, inline:true});
        $f(playerId, "../js/flowplayer/flowplayer-3.2.16.swf", {
            clip: {
                url: playUrl,
                autoPlay: true,
                autoBuffering: true
            },
            plugins: {
                controls:{
                    url: '../js/flowplayer/flowplayer.controls-3.2.15.swf',
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

    function submitVoteForm(){
        $("#ans_sel_error").css("display", "none");
        var qId = $('#questionId').val();
        var mId = $('#memberId').val();

        $('#incyyteId').val(qId);
        $('#userId').val(mId);
        if($("input:radio[name='selectedAnswer']:checked").val() != null){
            $("#voteForm").ajaxSubmit({
                type: 'POST',
                url: '../votepoll.cyt',
                success: function(data) {
                    if(data.indexOf("notLogin") != -1) {
        				loginProceessInModelLoginPage('viewUserPollPage');
                    }
                    else if(data.indexOf("failure") != -1){
                        parent.$.fn.colorbox.close();
                        parent.$.fn.colorbox({'href':'div#poll_sysErrorMsg', 'open':true, 'inline':true});
                    }
                    else{
                        window.location.reload(true);
                    }
                },
                error: function(jqXHR, textStatus, errorThrown) {
                    alert("error:" + textStatus + " exception:" + errorThrown);
                }
            });
        }else {
            $("#ans_sel_error").text("Please select an answer");
            $("#ans_sel_error").show();
        }
    }
</script>
<!-- upload image start -->
 <script type="text/javascript">
        $(document).ready(function(){
         $('#searchSubmit_new_comments').click(function () {
        		googleImageSearchForComments();
           });
         $('#searchSubmit_new').click(function () {	
        	 googleImageSearchForFlyer();
         });  
       });
        
    </script>
    <script src="${pageContext.request.contextPath}/ui/js/googleSearch.js"></script>
     <script type="text/javascript">
     function googleImageSearchForComments(){
   		$('#googleSearchTrigger').val(1);
        var searchValue = document.getElementById("search_new_comments");
        var searchLoad = document.getElementById("googleImagesScrollComments");
        var searchSelect = "commentsImgSelect";
        loadGoogleSearchImages(searchValue, searchLoad, searchSelect);
     }
     function googleImageSearchForFlyer(){
    	 $('#googleSearchTrigger').val(1);
      	var searchValue = document.getElementById("search_new");
      	var searchLoad = document.getElementById("googleImagesScroll");
      	var searchSelect = "quesImgSelect";
      	loadGoogleSearchImages(searchValue, searchLoad, searchSelect);
     }
     
        var trigger = 1;
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
                        if (i % 3 == 0) {
                            imagesContainer = imagesContainer + '<tr>';
                        }
                        imagesContainer = imagesContainer + '<td><div><a><img onClick="javascript:loadImage(\'' + searchSelect + '\',this.src);" class="upload_photos_thumb_img" src="' + result.url + '"></a></div></td>';
                        //End of Each row close the row tag
                        if (count % 3 == 0) {
                            imagesContainer = imagesContainer + '</tr>';
                        }
                    }
                    imagesContainer = imagesContainer + '</table>';
                    searchLoad.innerHTML = searchLoad.innerHTML + imagesContainer;
                }
            }
        }
    </script>

<!-- upload image end -->  

<script  language="JavaScript" src="${pageContext.request.contextPath}/ui/js/star_rating.js" type="text/javascript"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/ui/js/jquery.ratingbar.js"></script>
<script type="text/javascript">
     $(document).ready(function(){
        $('.gender_rating').ratingbar();
     });

</script>
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
<title><%=model.getPageHeader()%></title>
<div class="extra">
<!-- include header -->
<% if(user != null && user.getStatus() != null && !user.getStatus().equals("NON_ACTIVE")) {%>	
<jsp:include page="../common/includes/header.jsp" />
<%} else { %>
<jsp:include page="../main/includes/emptyHeader.jsp" />
<%} %>

<div class="main">
<article id="content">
<div id="main_content">
<div class="preview_page_header_txt">
    <% String headerString = model.getPageHeader();
        if (StringUtils.isNotBlank(headerString)) {
            if(headerString.contains(" ")){
                int  p2 = headerString.indexOf(" ");
                String boldHeader = headerString.substring(0, p2);
                String thinHeader = headerString.substring(p2);
    %>
                    <span style="font-family: 'bariol_boldbold', 'ie8_bariol_bold';">
                    <%=boldHeader%></span><span><%=thinHeader%></span>
    <% }else{%>
                    	<span style="font-family: 'bariol_boldbold', 'ie8_bariol_bold';">
                        <%=headerString%></span>
    <%}
    }%>
</div>

<%
	String shareModal = "no_display";
	String incyytecode = cyyteChart.getIncyyte().getIncyyteCode();
	if (session.getAttribute( "DSM" ) == null)
		session.setAttribute( "DSM", "N");

	String displayShareModal = (String)session.getAttribute( "DSM" );		
	if (displayShareModal.equals("Y"))
		shareModal  = (String) request.getParameter("p");
	if (shareModal != null && shareModal.equals("share") && displayShareModal.equals("Y") ){
		session.setAttribute( "DSM", "N");
%>
	<jsp:include page="/WEB-INF/jsp/include/shareEmailPopup.jsp" />
<%}%>
	<input type="hidden" id="shrModal" value="<%=shareModal%>"/>
	<input type="hidden" id="shrCode" value="<%=incyytecode%>"/>

<div class="grid_9" style="min-height: 100px;" >
    <div class="preview_left_txt">
        <h1> <%=model.getPageHeader()%></h1>
        <div><%=model.getAddress1()%></div>
        <div><%=model.getAddress2()%></div>
        <div><%=model.getCity()%></div>
         <div style="margin-top: 10px; float: left;"><%=model.getCountry()%>
        <span style="text-align: left;width: 120px;"><%=model.getPostcode()%></span></div><br>

        <div  style="float: left;"><span><% if (!model.getContactPhone1().equals("")){ %> Tel1: <% }%></span><p><%=model.getContactPhone1()%></p></div>
        <div style="float: left;"><span><% if (!model.getContactPhone2().equals("")){ %> Tel2: <% }%></span><p><%=model.getContactPhone2()%></p></div>
        <% 
        String websiteUrl = model.getWebsiteUrl();
        if (websiteUrl.length() > 20) websiteUrl = model.getWebsiteUrl().substring(0, 20) + ". . .";
        
        String ContactEmail = model.getContactEmail();
        if (ContactEmail.length() > 20) ContactEmail = model.getContactEmail().substring(0, 20) + ". . .";                           
        %>
        
        <div style="float: left;margin-top: 30px;"><span> <% if (!model.getContactEmail().equals("")){ %>Email: <% }%></span><p onclick="javascript:window.location='mailto:<%=model.getContactEmail()%>'" style="cursor:pointer;" title="<%=model.getContactEmail()%>"><%=ContactEmail%></p></div>
        <div style="float: left;"><span> <% if (!model.getWebsiteUrl().equals("")){ %>Website:<% }%></span><p onClick="window.open('<%=model.getWebsiteUrl()%>','MyWindow'); return false;" style="cursor:pointer;" title="<%=model.getWebsiteUrl()%>"><%=websiteUrl%></p></div>

    </div>
</div>
<div class="line" style="height: 400px;">
</div>
<div class="grid_17"style="height: auto;">
<div class="preview_banner">
    <% if(StringUtils.isNotBlank(model.getLogoUrl())) {%>
    <div class="preview_logo" >
        <div class="preview_logo_img">
          <%if(StringUtils.isNotBlank(model.getLogoLink())){ %>
          <a href="" onClick="window.open('<%=model.getLogoLink()%>','MyWindow'); return false;">
          <img style="width:98px; height: 82px;" src="<%=model.getLogoUrl() %>"></a>
          <%}else{ %>
          <img style="width:98px; height: 82px;" src="<%=model.getLogoUrl() %>">
          <%}%>
          </div>
    </div>
    <%} else if(StringUtils.isNotBlank(incyyteUser.getProfilePicture())) { %>
    <div class="preview_logo" >
        <div class="preview_logo_img">
            <img style="width:98px; height: 82px;" src="<%=incyyteUser.getProfilePicture()%>">
        </div>
    </div>
    <%} else { %>
    <div class="preview_logo" >
        <div class="preview_logo_img">
            <img style="width:98px; height: 82px;" src="${pageContext.request.contextPath}/images/logo_image.png">
        </div>
    </div>
    <%}%>
    <%if(StringUtils.isNotBlank(model.getBannerUrl())){%>
    <div class="preview_banne_img">
        <%if(StringUtils.isNotBlank(model.getBannerLink())){ %>
                                <a href="" onClick="window.open('<%=model.getBannerLink()%>','MyWindow'); return false;">
                                 <img  style="width:657px;height: 367px;" src="<%=model.getBannerUrl() %>"></a>
                                 <%}else{ %>
                                 <img  style="width:657px;height: 367px;" src="<%=model.getBannerUrl() %>">
                                    <%}%>
    </div>
    <%}else{%>
    <div class="preview_banne_img">
        <img  style="width:657px;height: 367px;" src="${pageContext.request.contextPath}/ui/images/default_banner_image1.png">
    </div>
    <%}%>
</div>
<div class="preview_banner_border">

</div>
 </div>

<!-- Middle Content -->
<div style="width: 100%; float: left;margin-top: -50px;">
    <div class="grid_9">
        <div class="preview_ask_question"> It's  always a good
            time to ask a
            new question!
                               <span>
                           Find out what people think
                           about the things that
                           matter to you!
                           </span> </div>
    <div class="preview_ask_btn">
        <a href="${pageContext.request.contextPath}/create_question.cyt" title="GET INCYYTE!" class="ready_vote_bot" style="width:171px; float: left; margin-top:10px;">
            <span class="title_red title_red5_ie8">Get inCyyte !</span></a>
    </div>
    
<%--c:choose>
    <c:when test="${displayConnectUs == 'YES'}">
    <form:form id="ConnectForm" name="ConnectForm" commandName="UserContactModel" method="POST">
    <div>
    <div class="preview_ask_question" style="margin-top: 25px;float: left;">
        Connect with us..
                               <span>
                               Get on our list for special offers
                               And also receive $10 when you
                               Join us on inCyyte!
                                </span>
    </div>
    <div id="preview_form">
        <span>First Name</span>
        <form:input type="text" id="firstName" path="firstname" onKeyup="isfirstnameValid();"/>
        <p id="firstNameError"></p>
        <span>Last Name</span>
        <form:input type="text" id="lastName" path="lastname" onKeyup="islastnameValid();"/>
        <p id="lastNameError"></p>
        <span>Email</span>
        <form:input type="text" id="email" path="email" onKeyup="isemailValid();"/>
        <p id="emailError"></p>
        <span><b>Security Check</b></span>
        <span>Please Enter the text below</span>
        <div class="preview_security_img">
            <img src="${pageContext.request.contextPath}/ui/images/preview_security.jpg" style="float: left; padding-right: 5px;">
            <a href="#">Cant read it?<br>
                Try another
            </a>
        </div>
        <span>Enter text here</span>
        <form:input type="text" id="text" path="text" onKeyup="istextValid();"/>
        <p id="textfeildError"></p>
    </div>
    <div class="preview_ask_btn">
        <a class="poll_button1" style="width:100px;float: left; "  href="javascript:sendinvite();"><span class="poll_button_green ">SEND</span></a>
            </div>
        </div>
        </form:form>
        </c:when>
        </c:choose--%>
        </div>
    <div class="line">
    </div>
    <div class="grid_17">
    	<div class="preview_opinion_txt" >What' s Your <span>Opinion?</span></div>
    	<div id="preview_poll_inner">
    	
    	<!----------Include view poll fragment start---------------------->
    	
    		<jsp:include page="/WEB-INF/jsp/businessaccount/includes/viewPollFragment.jsp" />
    		
    	<!----------Include view poll fragment end ---------------------->
    
		</div>

		<div class="preview_opinion_txt"><br/><span>Info..</span></div>
		<div class="preview_info_txt">
		    <%
		    	if (StringUtils.isBlank(model.getInformation())) {
		    %>
				    <p> You're at the   <%=cyyteChart.getIncyyte().getPageName()%>  poll page    </p>
				    <p> You're opinion makes the difference. </p>
				
				    <p style="margin-top: 10px;"> Thanks for voting! </p>
		    <%
		    	} else {
		    %>
		        
		        <%
		            String info = model.getInformation();
		            info = info.replaceAll("\r\n", "<br/>");
		            info = info.replaceAll("\n", "<br/>");
		        %>
		        <script type="text/javascript">
					$(document).ready(function () {                                             	 		
		       			var inputText = "<%=info%>";
		       			var replaceText = linkify(inputText); 
		       			$('#info_text').html(replaceText);    
		       		});
		       	</script>
		        <span id="info_text"></span>
		        <br>
		        <%
		        	}
		        %>
		</div>
    </div>
</div>

<div style="width: 100%;float: left;border:1px solid #aeaead"></div>
<div class="grid_9">
 <c:choose>
    <c:when test="${displaySendMessage == 'YES'}">
    <form:form id="sendMsg"  name="sendMsg" commandName="messageModel" method="POST">
    <div>
    <div class="preview_ask_question" style="margin-top: 25px;float: left;">
        Need to send us a
        Message?
              <span>
          Send us your message
          And  we'll get back to you
          As soon as we can!
                  </span>
    </div>
    <div id="preview_form">
        <form:textarea id='messagetext' onKeyUp="messageValidation(this)"
                                                      placeholder="Add your message here..."
                                                      path="messageText"/><p id="textError"></p>
    </div>
    <div class="preview_ask_btn">
        <a class="poll_button1" style="width:170px;float: left; "  href="javascript:sendmesg()"><span class="poll_button_green ">SEND A Message</span></a>
    </div>
    </div>
    </form:form>
 </c:when>
</c:choose>
</div>
<div class="line"></div>
<div class="grid_17">
    <% if( StringUtils.isNotBlank(model.getImageURL1())  ||
            StringUtils.isNotBlank(model.getImageURL2()) ||
            StringUtils.isNotBlank(model.getImageURL3()) ||
            StringUtils.isNotBlank(model.getImageURL4()) ||
            StringUtils.isNotBlank(model.getImageURL5()) ||
            StringUtils.isNotBlank(model.getImageURL6()) ||
            StringUtils.isNotBlank(model.getImageURL7()) ||
            StringUtils.isNotBlank(model.getImageURL8()) ||
            StringUtils.isNotBlank(model.getImageURL9()) ||
            StringUtils.isNotBlank(model.getImageURL10())) {%>
    <div class="preview_opinion_txt"> <span>Take a look..</span></div>
    <div class="preview_photo_slider">
        <div id="featured">
            <%if(StringUtils.isNotBlank(model.getImageURL1())) {
            if(StringUtils.isNotBlank(model.getImageLink1())){%>
            <a   href="" onClick="window.open('<%=model.getImageLink1()%>','MyWindow'); return false;" target="_blank">
            <img id="editPagePhotos" src="<%=model.getImageURL1()%>" style="width:655px;height:364px;" alt="" /></a>
            <%}else{%>
            	<img id="editPagePhotos" src="<%=model.getImageURL1()%>" style="width:655px;height:364px;" alt="" />
           <% }}
            if(StringUtils.isNotBlank(model.getImageURL2())) {
            if(StringUtils.isNotBlank(model.getImageLink2())){%>
            <a   href="" onClick="window.open('<%=model.getImageLink2()%>','MyWindow'); return false;" target="_blank">
            <img id="editPagePhotos" src="<%=model.getImageURL2()%>" style="width:655px;height:364px;" alt="" /></a>
            <%}else{%>
            	<img id="editPagePhotos" src="<%=model.getImageURL2()%>" style="width:655px;height:364px;" alt="" />
           <% }}
            if(StringUtils.isNotBlank(model.getImageURL3())) {
            if(StringUtils.isNotBlank(model.getImageLink3())){%>
            <a   href="" onClick="window.open('<%=model.getImageLink3()%>','MyWindow'); return false;" target="_blank">
            <img id="editPagePhotos" src="<%=model.getImageURL3()%>" style="width:655px;height:364px;" alt="" /></a>
            <%}else{%>
            	<img id="editPagePhotos" src="<%=model.getImageURL3()%>" style="width:655px;height:364px;" alt="" />
           <% }}
           if(StringUtils.isNotBlank(model.getImageURL4())) {
            if(StringUtils.isNotBlank(model.getImageLink4())){%>
            <a   href="" onClick="window.open('<%=model.getImageLink4()%>','MyWindow'); return false;" target="_blank">
            <img id="editPagePhotos" src="<%=model.getImageURL4()%>" style="width:655px;height:364px;" alt="" /></a>
            <%}else{%>
            	<img id="editPagePhotos" src="<%=model.getImageURL4()%>" style="width:655px;height:364px;" alt="" />
           <% }}
           if(StringUtils.isNotBlank(model.getImageURL5())) {
            if(StringUtils.isNotBlank(model.getImageLink5())){%>
            <a   href="" onClick="window.open('<%=model.getImageLink5()%>','MyWindow'); return false;" target="_blank">
            <img id="editPagePhotos" src="<%=model.getImageURL5()%>" style="width:655px;height:364px;" alt="" /></a>
            <%}else{%>
            	<img id="editPagePhotos" src="<%=model.getImageURL5()%>" style="width:655px;height:364px;" alt="" />
           <% }}
           if(StringUtils.isNotBlank(model.getImageURL6())) {
            if(StringUtils.isNotBlank(model.getImageLink6())){%>
            <a   href="" onClick="window.open('<%=model.getImageLink6()%>','MyWindow'); return false;" target="_blank">
            <img id="editPagePhotos" src="<%=model.getImageURL6()%>" style="width:655px;height:364px;" alt="" /></a>
            <%}else{%>
            	<img id="editPagePhotos" src="<%=model.getImageURL6()%>" style="width:655px;height:364px;" alt="" />
           <% }}
           if(StringUtils.isNotBlank(model.getImageURL7())) {
            if(StringUtils.isNotBlank(model.getImageLink7())){%>
            <a   href="" onClick="window.open('<%=model.getImageLink7()%>','MyWindow'); return false;" target="_blank">
            <img id="editPagePhotos" src="<%=model.getImageURL7()%>" style="width:655px;height:364px;" alt="" /></a>
            <%}else{%>
            	<img id="editPagePhotos" src="<%=model.getImageURL7()%>" style="width:655px;height:364px;" alt="" />
           <% }}
           if(StringUtils.isNotBlank(model.getImageURL8())) {
            if(StringUtils.isNotBlank(model.getImageLink8())){%>
            <a   href="" onClick="window.open('<%=model.getImageLink8()%>','MyWindow'); return false;" target="_blank">
            <img id="editPagePhotos" src="<%=model.getImageURL8()%>" style="width:655px;height:364px;" alt="" /></a>
            <%}else{%>
            	<img id="editPagePhotos" src="<%=model.getImageURL8()%>" style="width:655px;height:364px;" alt="" />
           <% }}
           if(StringUtils.isNotBlank(model.getImageURL9())) {
            if(StringUtils.isNotBlank(model.getImageLink9())){%>
            <a   href="" onClick="window.open('<%=model.getImageLink9()%>','MyWindow'); return false;" target="_blank">
            <img id="editPagePhotos" src="<%=model.getImageURL9()%>" style="width:655px;height:364px;" alt="" /></a>
            <%}else{%>
            	<img id="editPagePhotos" src="<%=model.getImageURL9()%>" style="width:655px;height:364px;" alt="" />
           <% }}
           if(StringUtils.isNotBlank(model.getImageURL10())) {
            if(StringUtils.isNotBlank(model.getImageLink10())){%>
            <a   href="" onClick="window.open('<%=model.getImageLink10()%>','MyWindow'); return false;" target="_blank">
            <img id="editPagePhotos" src="<%=model.getImageURL10()%>" style="width:655px;height:364px;" alt="" /></a>
            <%}else{%>
            	<img id="editPagePhotos" src="<%=model.getImageURL10()%>" style="width:655px;height:364px;" alt="" />
           <% }}%>
        </div>
    </div>
    <%} %>
    <div class="preview_banner_border">
    </div>
</div>

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
		                        <td colspan="3" align="center"><img src="${pageContext.request.contextPath}/ui/images/indicator-loader.gif" id="commentsImageLoader"
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
										src="${pageContext.request.contextPath}/ui/images/uploading_big_img.png">
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
                            <!-- <div class="fileInputs">
                                <div id="incyyte_browse_comments_photos" class="button_gray"
                                     style=" width:140px; float:left; margin-bottom:20px; position: absolute; top: 0px;left: 0px; z-index: 1;">
                                    <span class="title_gray">BROWSE</span></div>
                            </div> -->
                        </td>
                        <!-- <td colspan="2" class="font_16px"><br><span style="float: left;">File Name:</span>
                            <span id="comments_video_file_name" style= "float: left; margin-left: 10px;" ></span></td> -->
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
                        <td align="center" style="display:none ; position: absolute;z-index: 200; float: left; margin-left: 118px; margin-top: 119px;"><img  src="${pageContext.request.contextPath}/ui/images/indicator-loader.gif" id="videoLoader"
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
															<iframe width='320' id="commentsVideoSelect"  height='190' class="upload_photos_container_big_img" src="${pageContext.request.contextPath}/ui/images/uploading_big_img.png" frameborder='0' type='text/html'></iframe>
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

						
<c:forEach items="${comments}" var="commentVar" varStatus="theCount">
<!----------view Photos Start-------------------->
		<div style="display: none;">
		<div id="view_comment_photos_${commentVar.commentid}" class="c_add_photos"
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
								<li><a id="commentPhotolinkId"
									href=" "
									target="_blank" class="group1 fancybox-popup"> <img
										id="commenthotoImg"
										src="${commentVar.commentPicture}"
										class="photos_thumb" alt="tour" />
								</a></li>
							</ul>
						</div>
					</td>
				</tr>
				<tr>
					<td colspan="2" valign="bottom"><span class="licence">You
							must have the licence to use this image</span>
						<div title="Delete" class="button_red" style="width: 140px; float: right;">
							<a  href="javascript:deleteCommentPhoto(${commentVar.commentid});" ><span class="title_red">DELETE</span></a>
						</div></td>
				</tr>
			</table>
		</div>
		<!----------view comment Photos end-------------------->
					
		<!----------view Videos Comments Start-------------------->
					<div style="display: none;">
								<div id="view_comment_videos_${commentVar.commentid}" class="c_add_photos"
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
                                   						 <img   id="viewQuesYouTubeVideoId" src="${pageContext.request.contextPath}/ui/images/video_thumb.png"  onClick="javascript:playCommentVideo( '${commentVar.commentid}');"  class="photos_thumb" alt="tour"/>
                                        					<div style="display:none;">
																<div id="emailListYouTube_${commentVar.commentid}" class="emailList " >
											    					<iframe width='390' id="iFrameYouTube_${commentVar.commentid}"  height='308'  src="${pageContext.request.contextPath}/ui/images/uploading_big_img.png" frameborder='0' type='text/html'></iframe>
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
													<a  href="javascript:deleteCommentPhoto( '${commentVar.commentid}' , 'Video');" ><span class="title_red">DELETE</span></a>
												</div></td>
										</tr>
									</table>
								</div>
			<!----------view comment Videos end-------------------->
				
</c:forEach>	

</div>
</article>
</div>
</div>
<!----------Add Photos Start-------------------->
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
									src="${pageContext.request.contextPath}/ui/images/indicator-loader.gif" id="imageLoader"
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
									<input type="text" name="search" id="search_new" onKeydown="Javascript: if (event.keyCode==13 )  googleImageSearchForFlyer();">
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
								This is used to display Google images
								<div id="googleImagesScroll"
									style="width: 319px; height: 171px; overflow-x: hidden; overflow-y: auto; margin-right: 10px;">
								</div>
							</td>
							<td align="right">
								<div>
									<img id="quesImgSelect"
										class="upload_photos_container_big_img"
										src="${pageContext.request.contextPath}/ui/images/uploading_big_img.png">
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
<!---Add Photos end------------ -->
<!-- include footer -->
<% if(user != null) {%>
<jsp:include page="../common/includes/footer.jsp" />
<%} else { %>
<jsp:include page="../main/includes/homeFooter.jsp" />
<%} %>
</div>
    
<!-- Image Slider -->
<script type="text/javascript">
    $(window).load(function() {
        $('#featured').orbit({
            'bullets': false,
            'timer' : false,
            'animation' : 'horizontal-slide'
        });
    });
    
    $("#openVideoUploadwindow, #modal_videos1, #modal_videos2").click(function(){
        parent.$.fn.colorbox({'href':'div#VideoModalWindow', 'open':true, 'inline':true});
    });
    $("#openuploadwindow, #modal_photos, #modal_photos1 , #modal_photos2").click(function(){
        $.fn.colorbox({'href':'div#ModalPhotoWindow', 'open':true, 'inline':true});
    });
    $("#openDocUploadwindow, #modal_docs2, #modal_docs1").click(function(){
        $.fn.colorbox({'href':'div#ModalDocWindow', 'open':true, 'inline':true});
    });
    //This is for adding images in COMMENTS.
 
    $("#modal_photos_comments, #modal_photos_comments1").click(function(){
    	$.fn.colorbox({'href':'div#ModalCommentPhotoWindow', 'open':true, 'inline':true});
	});
	$("#modal_videos_comments , #modal_videos_comments1").click(function(){
		parent.$.fn.colorbox({'href':'div#ModalCommentVideoWindow', 'open':true, 'inline':true});
    });
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
	      	$("#iFrameYouTube"+id).attr("src", "https://www.youtube.com/embed/"+videoId);
	      	parent.$.fn.colorbox({href:'div#emailListYouTube'+id, open:true, inline:true});
	   }
	   
    
    $("#hidecomments").click(function(){
    	window.location = $('#pageName').val()+".cyt?openSection=openSilverMemOptions";
    });
    
    $("#hideSilvercomments").click(function(){
    	window.location = $('#pageName').val()+".cyt";
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
<style>
    .emailList{
        width: 410px;
    }
    .emailList a object{
        width: 390px;
    }
</style>
<div id="Poll_save_confirm">
    <div class="Poll_save_confirm_bg">
        <div class="edit_pro_pop_txt">
        </div>
        <div style="height:auto; margin-left: 135px; float: left; margin-top: 25px;">
        <a class="poll_button1" href="#" style="width:170px;" id="ok">
        <span class="poll_button_red ">Ok</span></a></div>
    </div>
</div>
<jsp:include page="../include/report_abuse.jsp" />
<jsp:include page="../include/pollEmailCount.jsp" />
<script type="text/javascript" src="${pageContext.request.contextPath}/ui/js/googleanalytics.js"></script>
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
</body>
</html>
