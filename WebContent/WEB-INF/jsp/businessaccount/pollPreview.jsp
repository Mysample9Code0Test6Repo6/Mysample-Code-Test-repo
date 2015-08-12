<%@page import="org.apache.commons.lang.StringUtils"%>
<%@ include file="/WEB-INF/jsp/common/include.jsp" %>
<%@page import="com.incyyte.app.domain.User"%>
<%@page import="com.incyyte.app.web.SessionKeys"%>
<%@ page import="com.incyyte.app.web.model.UserPollPageModel" %>
<% User user = (User)request.getSession().getAttribute(SessionKeys.LOGIN_USER);%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">
    <link rel="shortcut icon" href="favicon.ico" />
    <title>Preview your Poll Page</title>
    <meta charset="utf-8">
    <link rel="stylesheet" href="ui/css/reset.css">
    <link rel="stylesheet" href="ui/css/layout.css">
    <link rel="stylesheet" href="ui/css/buttons.css"  media="screen">
    <link rel="stylesheet" href="ui/css/style_login.css">
    <link rel="stylesheet" href="ui/css/accordionmenu.css" type="text/css">
    <link rel="stylesheet" href="ui/css/icons_sprites.css">
    <link rel="stylesheet" href="fonts/fonts_stylesheet.css">
    <link rel="stylesheet" href="ui/css/ratingbar.css" type="text/css"/>
    <link rel="stylesheet" href="ui/css/ui.progress-bar.css" type="text/css"/>
    <link rel="stylesheet" href="ui/modal/colorbox.css">
    <link rel="stylesheet" href="ui/css/form_elements.css">
    <link rel="stylesheet" href="ui/css/image_slider.css">
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
            headHTML    += '<link type="text/css" rel="stylesheet" href="ui/css/ie10.css">';
            document.getElementsByTagName('head')[0].innerHTML = headHTML;
        }
    </script>

    <!--[if gte IE 9]>
    <link href="ui/css/gte_ie9.css" media="screen" rel="stylesheet" type="text/css"/>
    <![endif]-->
    <script src="ui/js/jquery-1.7.1.js"></script>
    <script type="text/javascript" src="ui/js/jquery-1.8.3.min.js"></script>
    <script type="text/javascript" src="ui/js/jquery_profile_rating.js"></script>
    <script src="ui/modal/colorbox/jquery.colorbox.js"></script>
    <script type="text/javascript" src="ui/js/star_rating.js"></script>
       <script type="text/javascript" src="ui/js/jquery.orbit.js"></script>
    <script src="ui/js/customInput.jquery.js" type="text/javascript"></script>
    <script type="text/javascript">
        // Run the script on DOM ready:
        $(function(){
            $('input').customInput();
        });
    </script>
    <script type="text/javascript" src="ui/js/jquery.ratingbar.js"></script>
    <script type="text/javascript" src="ui/js/pollSetup.js"></script>
	<script type="text/javascript" src="ui/js/ba-linkify.js"></script>
    <style type="text/css">
        #featured { width: 657px; height: 367px; margin-left: 2px; margin-top: 2px;  overflow: hidden; }
            /* Want a different Loading GIF - visit http://www.webscriptlab.com/ - that's where we go this one :) */
    </style>
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
<form:form id="pollPreviewForm" name="pollPreviewForm" commandName="pollPreviewForm" enctype="multipart/form-data" method="post">
<div id="gradient">
<% 	UserPollPageModel model = (UserPollPageModel) request.getSession().getAttribute("pollSetup");%>

    <div class="extra">
        <!-- include header -->
        <jsp:include page="../common/includes/header.jsp"/>
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
                    <%=boldHeader%><span><%=thinHeader%></span>
                    <% }else{%>
                        <%=headerString%>
                    	<%}
                    }%>
                    </div>
                       <div class="grid_9"  style="min-height: 100px;">
                           <div class="preview_left_txt">
                            <h1><%=model.getPageHeader()%></h1>
                        <div><%=model.getAddress1()%></div>
                        <div><%=model.getAddress2()%></div>
                            <div><%=model.getCity()%></div>
                            <div style="margin-top: 10px; float: left;"><%=model.getCountry()%>
                            <span style="text-align: left;width: 120px;"><%=model.getPostcode()%></span></div><br>
                            <br>
                           <div  style="float: left;"><span><% if (!model.getContactPhone1().equals("")){ %> Tel1: <% }%></span><p><%=model.getContactPhone1()%></p></div>
                           <div style="float: left;"><span><% if (!model.getContactPhone2().equals("")){ %> Tel2: <% }%></span><p><%=model.getContactPhone2()%></p></div>
                           <% 
                           String url = model.getWebsiteUrl();
                           if (url.length() > 20) url = model.getWebsiteUrl().substring(0, 20) + ". . .";
                           
                           String email = model.getContactEmail();
                           if (email.length() > 20) email = model.getContactEmail().substring(0, 20) + ". . .";                           
                           %>
                           
                           <div style="float: left;margin-top: 30px;"><span> <% if (!model.getContactEmail().equals("")){ %>Email: <% }%></span><p onclick="javascript:window.location='mailto:<%=model.getContactEmail()%>'" style="cursor:pointer;" title="<%=model.getContactEmail()%>"><%=email%></p></div>
                           <div style="float: left;"><span> <% if (!model.getWebsiteUrl().equals("")){ %>Website:<% }%></span><p onClick="window.open('<%=model.getWebsiteUrl()%>','MyWindow'); return false;" style="cursor:pointer;" title="<%=model.getWebsiteUrl()%>"><%=url%></p></div>
                               <a href="javascript:editPollSetupFromPreview(2);"><img id="editPollPageDetails" src="ui/images/review_icon.png" style="margin-top: 10px" class=""></a>
                           </div>

                            </div>
                        <div class="line" style="height: 400px;">
                        </div>
                        <div class="grid_17" style="height: auto;">
                              <div class="preview_banner">
                                <%if(StringUtils.isNotBlank(model.getLogoUrl())) {%>
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
                                 <%} else if(StringUtils.isNotBlank(user.getProfilePicture())) { %> 
                                  <div class="preview_logo" >
                                      <div class="preview_logo_img">
                                      <img style="width:98px; height: 82px;" src="<%=user.getProfilePicture()%>">
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
                                 <img  style="width:657px;height: 367px;" src="${pageContext.request.contextPath}/ui/images/poll_banner_default.jpg">
                                 </div>
                                     <%}%>
                             </div>
                            <div class="preview_banner_border">
                                <div class="preview_banner_border_icon"> <a  href="javascript:editPollSetupFromPreview(1);">
                                    <img id="editLogoBanner"  src="ui/images/review_icon3.png">
                                </a> </div>
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
                                <a href="${pageContext.request.contextPath}/create_question.cyt" title="GET INCYYTE" id="" class="ready_vote_bot" style="width:171px; float: left; margin-top:10px;">
                                    <span class="title_red title_red5_ie8">Get inCyyte !</span></a>

                            </div>
                            <%--<img src="ui/images/preview_connect.png" style="margin-left: -10px;"/> --%>
                            <!-- div style="display: none;">
                                <div class="preview_ask_question" style="margin-top: 25px;float: left;">
                                    Connect with us..
                               <span>
                               Get on our list for special offers
                               Ans also receive $10 when you
                               Join us on inCyyte!
                                </span>
                                </div>

                                <div id="preview_form">
                                    <span style="margin-top: 20px;">First Name</span>
                                    <input type="text">
                                    <span>Last Name</span>
                                    <input type="text">
                                    <span>Email</span>
                                    <input type="text">
                                    <span><b>Security Check</b></span>
                                    <span>Please Enter the text below</span>
                                    <div class="preview_security_img">
                                        <img src="ui/images/preview_security.jpg" style="float: left; padding-right: 5px;">

                                        <a href="#">Cant read it?<br>
                                            Try another
                                        </a>

                                    </div>
                                    <span>Enter text here</span>
                                    <input type="text">

                                </div>
                                <div class="preview_ask_btn">
                                    <a class="poll_button1" style="width:100px;float: left; "  href="#"><span class="poll_button_green ">SEND</span></a>
                                </div>
                            </div-->
                        </div>
                        <div class="line"></div>
                        <div class="grid_17">
                            <div class="preview_opinion_txt">What' s Your <span>Opinion?</span></div>
                            <img   src="${pageContext.request.contextPath}/ui/images/poll-page-default.jpg">                            
                            <div class="preview_opinion_txt"><br/> <span>Info..</span></div>
                            <div class="preview_info_txt">
                                <%
                                    if (StringUtils.isBlank(model.getInformation())) {
                                %>
                                <p> You're at the < poll_page_name > poll page    </p>
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
                                <%
                                    }
                                %>
                            </div>
                        </div>
                    </div>
                  <div style="width: 100%;float: left;border:1px solid #aeaead"></div>
                <div class="grid_9">
                    <%--<img src="ui/images/preview_send.png" style="margin-left: -10px;">--%>
                    <div style="display: none;">
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
                        <textarea>

                        </textarea>

                    </div>
                    <div class="preview_ask_btn">
                        <a class="poll_button1" style="width:170px;float: left; "  href="#"><span class="poll_button_green ">SEND A Message</span></a>

                    </div>
                </div>  </div>
                <div class="line"></div>
                
                <% if( StringUtils.isNotBlank(model.getImageURL1())  || 
                		StringUtils.isNotBlank(model.getImageURL2()) || 
                		StringUtils.isNotBlank(model.getImageURL3()) || 
                		StringUtils.isNotBlank(model.getImageURL4()) || 
                		StringUtils.isNotBlank(model.getImageURL5()) || 
                		StringUtils.isNotBlank(model.getImageURL6()) || 
                		StringUtils.isNotBlank(model.getImageURL7()) || 
                		StringUtils.isNotBlank(model.getImageURL8()) || 
                		StringUtils.isNotBlank(model.getImageURL9()) || 
                		StringUtils.isNotBlank(model.getImageURL10())){%>
                		<div class="grid_17">
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

                    <div class="preview_banner_border" style="width: 669px">
                        <div class="preview_banner_border_icon"><a  href="javascript:editPollSetupFromPreview(3);"><img src="ui/images/review_icon3.png"></a></div>
                    </div>
                     </div>
                     <%}%>
                </div>
                </article>
            </div>
    </div>
    <!-- include footer -->
    <jsp:include page="../common/includes/footer.jsp"/>
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
</script>
</form:form>

<script type="text/javascript" src="ui/js/googleanalytics.js"></script>
</body>
</html>
