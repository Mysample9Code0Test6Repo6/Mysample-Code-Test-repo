<%@ include file="/WEB-INF/jsp/common/include.jsp" %>
<%@page import="com.incyyte.app.domain.User"%>
<%@page import="com.incyyte.app.web.model.UserSettingsModel"%>
<%@page import="org.apache.commons.lang.StringUtils" %>
<%@page import="com.incyyte.app.domain.InCyyte" %>
<%@page import="com.incyyte.app.service.util.Constants"%>
<%@page import="com.incyyte.app.domain.InCyyteChart"%>
<%@page import="com.incyyte.app.web.SessionKeys"%>
<%@ page import="com.incyyte.app.web.model.UserPollPageModel" %>
<%@page import="com.incyyte.app.domain.CyyteResponse"%>
<%@page import="com.incyyte.app.service.util.Utility"%>
<%@page import="java.util.*"%>
<%@include file="/WEB-INF/jsp/login_model/viewUserModelLogin.jsp"%>
<% 
	User user = (User)request.getSession().getAttribute(SessionKeys.LOGIN_USER);
    User incyyteUser = (User) session.getAttribute("incyyteCreator");
    UserSettingsModel userSettingsModel = (UserSettingsModel)request.getSession().getAttribute(SessionKeys.LOGIN_USER_SETTINGS);
%>
    
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
	<script  type="text/javascript" src="${pageContext.request.contextPath}/ui/js/jquery-ui-1.8.16.custom.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/ui/js/jquery-1.8.1.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/ui/js/jquery_profile_rating.js"></script>
	<script  src="${pageContext.request.contextPath}/ui/js/viewUserPoll.js"></script>
	
	<script  src="${pageContext.request.contextPath}/ui/js/tooltip.js"></script>
	<script  src="${pageContext.request.contextPath}/ui/js/jquery.color.js"></script>
	<script  src="${pageContext.request.contextPath}/ui/js/search_script.js"></script>
	<script  src="${pageContext.request.contextPath}/ui/js/jquery_poll_rating.js" type="text/javascript"></script>
	<script  type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.form.js"></script>
	<script  type="text/javascript" src="${pageContext.request.contextPath}/ui/js/jquery-ui.js"></script>
	<script  type="text/javascript" src="${pageContext.request.contextPath}/ui/js/jquery.lightbox-0.5.js" ></script>
	<script  type="text/javascript" src="${pageContext.request.contextPath}/ui/js/validate/jquery.validate.js" ></script>
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
	<script type="text/javascript" src="${pageContext.request.contextPath}/ui/js/charts/js/charts.js"></script>
	
	<script src="${pageContext.request.contextPath}/ui/js/dashboard/dash_my_polls.js"></script>
	<script src="${pageContext.request.contextPath}/ui/js/ba-linkify.js"></script>
	<!-- Chart Script Start here -->
	<script type="text/javascript" src="${pageContext.request.contextPath}/ui/js/charts/js/highcharts.js"></script>
	<!-- Chart Script end here -->
	<script type="text/javascript" src="${pageContext.request.contextPath}/ui/modal/colorbox/jquery.colorbox.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/ui/js/placeHolder.js"></script>
	<%--<script src="${pageContext.request.contextPath}/ui/js/jquery.placeholder.js"></script>
	<script>
	    $(function() {
	        $('input, textarea').placeholder();
	    });
	</script>--%>
	
	<!-- Left Navigation script starts here -->
	<script  src="${pageContext.request.contextPath}/ui/js/jquery.selectbox-0.2.js"></script>
	<!-- Left Navigation script ends here -->
	
	<style type="text/css">
	    #featured { width: 655px; height: 370px; margin-left: 2px; margin-top: 2px;  overflow: hidden; }
	        /* Want a different Loading GIF - visit http://www.webscriptlab.com/ - that's where we go this one :) */
	</style>
	<script type="text/javascript">
	  function showhiderowsInPollPage(){	
	  	  var   size = document.getElementById("commentlst").value;
	  	  for( var i =6 ; i<= size ; i++ ) {
	  		  document.getElementById("row_"+i).style.display = "";  		  
	      }	
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
	  
	</script>
	<% 	
	 	UserPollPageModel model = (UserPollPageModel) request.getSession().getAttribute("pollSetup");
	%>
	<title><%=model.getPageHeader()%></title>
	
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
	
	    function postComment() {
	        var comment = document.getElementById("textarea").value;
	        if(comment == '') {
	            $("#error").css("display", "none");
	            $("#error").text("Please enter your comment.");
	            $("#error").css("display","");
	            return;
	        }
	        $("#error").css("display", "none");
	        var quesid = document.getElementById("questionid").value;
	        $("#comment").val(comment);
	        $("#quesid").val(quesid);
	        var contextVar = document.getElementById("contextPathVar").value;
	       
	        $("#CommentsForm").ajaxSubmit({
	            type: 'POST',
	            url: '../addPollComments.cyt',
	            success: function(data) {
	            	 window.location.reload(true);
	            },
	            error: function(jqXHR, textStatus, errorThrown) {
	                alert("error:" + textStatus + " exception:" + errorThrown);
	            }
	        });        
	    }
	</script>
	
	<script src="${pageContext.request.contextPath}/ui/js/customInput.jquery.js" type="text/javascript"></script>
	<script src="${pageContext.request.contextPath}/ui/js/star_rating.js" type="text/javascript"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/ui/js/jquery.ratingbar.js"></script>

</head>
<body>

<div id="gradient">

	<div class="extra">
		<!-- include header -->
<% if(user != null && user.getStatus() != null && !user.getStatus().equals("NON_ACTIVE")) {%>	
<jsp:include page="/WEB-INF/jsp/common/includes/header.jsp" />
<%} else { %>
<jsp:include page="/WEB-INF/jsp/main/includes/emptyHeader.jsp" />
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
			                        	<%=headerString%>
			                        </span>
							    <%}
					   		}%>
					</div>
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
					<div class="line" style="height: 400px;"></div>
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
					        <div class="preview_ask_question"> 
					        	It's  always a good
					            time to ask a
					            new question!
					            <span>
							        Find out what people think
							        about the things that
							        matter to you!
						        </span> 
					        </div>
						    <div class="preview_ask_btn">
						        <a href="${pageContext.request.contextPath}/create_question.cyt" title="ASK A QUESTION" class="ready_vote_bot" style="width:171px; float: left; margin-top:10px;">
						            <span class="title_red title_red5_ie8">ASK A QUESTION</span>
						      	</a>
						    </div>    
					
					    </div>
					    <div class="line">
					    </div>
					    <div class="grid_17">
					    	<div class="preview_opinion_txt" >What' s Your <span>Opinion?</span></div>
					    	<div id="preview_poll_inner">
					    		 <c:forEach var="chart" items="${chart}" varStatus="status">
 									<div class="incomming_incyyte_detail" style=" width: 97%; float: left; border-bottom: 10px #aeaead solid;border-top:10px #aeaead solid;" >
										<table width="100%" border="0" cellspacing="0" cellpadding="0">
																		   
									    	<% 	InCyyteChart chart = (InCyyteChart) pageContext.getAttribute("chart");%>
									    	<% 	InCyyte incyyte = chart.getIncyyte();%>
									    	<c:set var="incyyte" value="<%=chart.getIncyyte()%>"/>
									    	
									    	<script type="text/javascript">
								                <!--
								                $(document).ready(function () {
								
								                    var chart0 = new Highcharts.Chart({
								                        chart: {
								                            renderTo: 'piecontainerGroupsmall${status.index}',
								                            plotBackgroundColor: null, plotBorderWidth: null, plotShadow: false
								                        },
								                        title: { text: '' },
								                        legend: {
								                            layout: 'vertical', align: 'left', verticalAlign: 'middle', padding:5, itemMarginTop: 5, itemMarginBottom: 5,
								                            itemStyle: {
								                                lineHeight: '14px'
								                            }
								                        },
								                        tooltip: {
								                            pointFormat: '<b>{point.percentage}%</b>',percentageDecimals: 1
								                        },
								                        plotOptions: {
								                            pie: {
								                                allowPointSelect: false, size: 60, cursor: 'pointer', 	dataLabels: {enabled: false}, showInLegend: false
								                            }
								                        },
								                        colors:['#cfff00','#6ecafc','#c2002d','#1b303f','#a8dffd','#e2f4fe','#e2ff66','#f5ffcc','#da6681','#f3ccd5'],
								
								                        series: [{
								                            type: 'pie', name: '',
								                            data: [
								                                <%for (CyyteResponse cyyteResponse : chart.getCyyteResponses() ){%>
								                                ['<%=cyyteResponse.getAnswer()%>',    <%=cyyteResponse.getResponse()%>],
								                                <%}%>
								                            ]
								                        }]
								                    });
								
								                   
								
								                });
								
								                $(function() {
								                    var button = $('#dropBoxButtonGroup${status.index}');
								                    var box = $('#dropBoxGroup${status.index}');
								                    var form = $('#dropBoxContentGroup${status.index}');
								                    button.removeAttr('href');
								                    button.mouseup(function(login) {
								                        box.toggle();
								                        button.toggleClass('active');
								                    });
								                    form.mouseup(function() {
								                        return false;
								                    });
								                    $(this).mouseup(function(login) {
								                        if(!($(login.target).parent('#dropBoxButtonGroup${status.index}').length > 0)) {
								                            button.removeClass('active');
								                            box.hide();
								                        }
								                    });
								                });
								
								                //-->
								            </script>

										    <tr>
												<td width="40%" valign="top" style="padding-left:0px; "  class="grid_6b" id="voteTab">
										
													<table width="100%" border="0"  style="cursor: pointer;" onclick="window.open('<%=Utility.getServerURL(request)%>/${userDomainPageName}/${incyyte.pageName}.cyt','_blank');">
													    <tr>
													        <td width="11%">
													        	<div id="piecontainerGroupsmall${status.index}" style="width: 100px; height: 100px;"></div>
									                        </td>
													
													        <td width="68%">
													            <%-- <h3>${incyyte.incyyte}</h3> --%>
													            <h3>${incyyte.incyyte}</h3>
																<p>Created: <span id="createdDateVal_${incyyte.id}"> ${incyyte.createdDate}</span></p>		
																<p>Total Responses: <span> ${incyyte.totalResponded}</span> </p>														
																<%-- <p>Closure: <span id="closingDateVal_${incyyte.id}"> ${incyyte.closureDate}</span>&nbsp;<span id="selecttimeVal_${incyyte.id}" ></span></p> --%> 
													        </td>
													        <td width="32%" align="right">
													
													            <c:if test="${not empty incyyte.uploadLocation && not empty incyyte.upload_name}">
													                <c:set var="url"  value="${incyyte.uploadLocation}"/>
													                <%
													                    String name = incyyte.getUpload_name();
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
													                    <a onClick="javascript:playVideo('${incyyte.id}','player${incyyte.id}', '${incyyte.uploadLocation}')"><img  src="${pageContext.request.contextPath}/ui/images/video_thumb.png" style="display: block; float: right; margin: 10px 2px; width: 50px; height: 50px; border: 2px solid #ffffff; padding: 2px;" /></a>
													                    <div style="display:none;">
													                        <div  id="emailList${incyyte.id}" class="emailList ">
													
													                            <a style="cursor:pointer" id="player${incyyte.id}" ></a>
													                        </div></div>
													                    <% } else if (extImages.contains(extension)) { %>
													                    <a href="${incyyte.uploadLocation}"  class="${pageContext.request.contextPath}/thumbnail fancybox-popup">
													                        <img src="${incyyte.uploadLocation}" style="display: block; float: right; margin: 10px 2px; width: 50px; height: 50px; border: 2px solid #ffffff; padding: 2px;" />
													                    </a><span><img src="${incyyte.uploadLocation}" /></span>
													                    <% } else if (extDocs.contains(extension)) { %>
													                    <a style="cursor:pointer" href="" onClick="window.open('${incyyte.uploadLocation}','MyWindow'); return false;" target="_blank" class="thumbnail">
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
													            <p>
													                <c:if test="${not empty incyyte.group}">
													                    group name: ${incyyte.group.groupName}
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
														                    </c:choose>
														                </c:forEach>
														            </td>
														        </tr>
														    </table>
														</div>
													<%} %>
												<%-- </form:form> --%>
												</td>
											</tr>										
										</table>
									</div>
								</c:forEach>
							</div>

							<div class="preview_opinion_txt"><br/>Our <span>Information..</span></div>
							<div class="preview_info_txt">
							    <%
							        if (StringUtils.isBlank(model.getInformation())) {
							    %>
									    <p> You're at the   <%=model.getPageHeader()%>    </p>
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
						<div class="preview_opinion_txt">Our <span>Photos..</span></div>
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

				</div>
			</article>
		</div>
	</div>
	<!-- include footer -->
	<% if(user != null) {%>
	<jsp:include page="/WEB-INF/jsp/common/includes/footer.jsp" />
	<%} else { %>
	<jsp:include page="/WEB-INF/jsp/main/includes/homeFooter.jsp" />
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
        $("#showAllComments").click(function () {
        	showhiderowsInPollPage();
        });
    });   
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
<jsp:include page="/WEB-INF/jsp/include/report_abuse.jsp" />
<jsp:include page="/WEB-INF/jsp/include/pollEmailCount.jsp" />
<script type="text/javascript" src="ui/js/googleanalytics.js"></script>
</body>
</html>