<%@ include file="/WEB-INF/jsp/common/include.jsp" %>
<%@ page import="org.apache.commons.lang.StringUtils" %>
<%@page import="com.incyyte.app.domain.User"%>
<%@ page import="com.incyyte.app.web.model.IncyyteModel" %>
<%@ page import="com.incyyte.app.web.model.PaymentModel" %>
<%@page import="com.incyyte.app.service.util.Constants" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">
    <link rel="shortcut icon" href="favicon.ico" />
    <title>Setup your Poll Page</title>
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
    <link rel="stylesheet" href="ui/css/style_help.css">
    <!-- for select box -->
    <link rel="stylesheet" href="ui/css/jquery.selectbox.css" type="text/css"/>

    <script type="text/javascript" src="ui/js/jquery-1.8.3.min.js"></script>
    <script src="ui/js/jquery-1.7.1.js"></script>

    <script src="ui/js/jquery.selectbox-0.2.js"></script>
    <script src="ui/modal/colorbox/jquery.colorbox.js"></script>
    <script type="text/javascript">
        $(function () {
            $("select").selectbox();
        });
    </script>

    <link rel="stylesheet" href="ui/css/sparkbox-select.css">
    <script src="ui/js/modernizr-1.7.min.js"></script>
    <script src="ui/js/jquery.sparkbox-select.js"></script>
    <script type="text/javascript">
        $(document).ready(function () {
            $('.sparkbox-custom').sbCustomSelect({
                appendTo:'body'
            });
        });
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

<%--
    <script type="text/javascript" src="ui/js/left_menu.js"></script>
--%>
    <script type="text/javascript" src="ui/js/jquery_profile_rating.js"></script>
    <script type="text/javascript" src="ui/js/star_rating.js"></script>
    <!-- check box-->
    <link rel="stylesheet" href="ui/css/prettyCheckboxes.css" type="text/css" media="screen">
    <script src="ui/js/prettyCheckboxes.js" type="text/javascript" charset="utf-8"></script>
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

<% IncyyteModel model = (IncyyteModel) session.getAttribute("inCyyteForm");
User user  = (User) session.getAttribute("user");
String orderId = request.getParameter("orderID");
%>
<div id="gradient">
    <div class="extra">
        <!-- include header -->
        <jsp:include page="../main/includes/paymentHeader.jsp"/>

        <div class="main" >
            <article id="content">
                <div id="main_content">
                    <div class="grid_9" >
                    <c:set var="section" value="${section}"/>
                     <% String section = String.valueOf(pageContext.getAttribute("section"));
                     if (StringUtils.equals(section, Constants.POLL_SECTION)) {%> 
                        <div class="payment_info_left_head">
                            <div class="payment_info_steps_txt" style="line-height: 28px;  margin-left: 15px;  margin-top: 20px; width: auto;">
                                Order<br> Overview
                            </div>
                        </div>
                        <div class="payment_info_left_txt">
                            <div class="payment_info_left_txtall">
                               <strong>${incyyteModel.incyyte}</strong>
                            </div>
                        </div>
                        <div class="payment_info_left_txt" style="margin-top: 10px">
                            <div class="payment_info_left_txtall">
                                This poll will be Sent To:
                            </div>

                      <% if (StringUtils.equals(model.getLocality(), "Postcode")) {%>
                        <div class="payment_info_left_txtall " style="margin-bottom: 0px;"> Postcode:<strong style="margin-left: 10px;">${incyyteModel.postcode}</strong></div>
                        <% } else if(StringUtils.equals(model.getLocality(), "Region")){ %>
                          <div class="payment_info_left_txtall " style="margin-bottom: 0px;"> Region:<strong style="margin-left: 10px;">${incyyteModel.region}</strong></div>
                        <% } else { %>
                        <div class="payment_info_left_txtall " style="margin-bottom: 0px;"> County:<strong style="margin-left: 10px;">${incyyteModel.county}</strong></div>
                        <%}%>
                       <% if (StringUtils.equals(model.getGender(), "select")) {%>
                    	   <div class="payment_info_left_txtall" style="margin-bottom: 0px;"> Gender:<strong style="margin-left: 10px;">Both</strong></div>
                    	  <% } else { %>
                        <div class="payment_info_left_txtall" style="margin-bottom: 0px;"> Gender:<strong style="margin-left: 10px;">${incyyteModel.gender}</strong></div>
                       <% }%>
                       
                       <% if(StringUtils.equals(model.getAgeMin(), "select") && StringUtils.equals(model.getAgeMax(), "select")) {%>
                    	  <div class="payment_info_left_txtall" style="margin-bottom: 0px;"> Age:<strong style="margin-left: 10px;"> 0  -  0 </strong></div>
                    	 <%   
                       } else{%> 
                        <div class="payment_info_left_txtall" style="margin-bottom: 0px;"> Age:<strong style="margin-left: 10px;">${incyyteModel.ageMin}-${incyyteModel.ageMax}</strong></div>
                        <%} %>
                    </div>
                        <div class="payment_info_left_txt" style="margin-top: 10px">
                            <div class="payment_info_left_txtall">
                                The audience for this <br> selection is:
                            </div>
                            <div class="payment_info_left_txtall">
                             <strong>${incyyteModel.pollRecipients} &nbsp;
                            Poll  Recipients</strong>
                              </div>
                        </div>
                        <div class="payment_info_left_txt_bottom" style="margin-top: 10px;">
                            <div class="payment_info_left_txtall">
                                The total cost for this <br>selection is:
                            </div>
                        <c:set var="amountWithoutTax" value="${PaymentForm.amountWOTax / 100}"/>
                        <c:choose>
                        	<c:when test="${amountWithoutTax >= '1'}">
                            	<div class="payment_info_left_txtall" style="color: #797979;"> � ${PaymentForm.displayAmountWOTax}  ex vat.  </div>
                            </c:when>
				            <c:otherwise>
                            	<div class="payment_info_left_txtall" style="color: #797979;">  ${PaymentForm.displayAmountWOTax} p ex vat. </div>
                            </c:otherwise>
						</c:choose>
						<c:set var="totalPollCost" value="${PaymentForm.amount / 100}"/>
                   		<c:choose>
                        	<c:when test="${totalPollCost >= '1'}">
                            	<div class="payment_info_left_txtall"> <strong> � ${PaymentForm.displayAmount}  incl vat.</strong></div>
							</c:when>
				            <c:otherwise>
                            	<div class="payment_info_left_txtall"> <strong> ${PaymentForm.displayAmount} p incl vat.</strong></div>
							</c:otherwise>
						</c:choose>
                        </div>
                        <div class="preview_ask_question" style="margin-top: 30px;float: left;"> It's  always a good
                            Time to ask a <br>
                            New question! <br>
                               <span>
                           Find out what people think <br>
                           About the things that<br>
                           Matter to you!
                           </span> </div>
                        <div class="preview_ask_btn">
                            <a href="${pageContext.request.contextPath}/create_question.cyt" title="GET INCYYTE?" id="" class="ready_vote_bot" style="width:171px; float: left; margin-top:10px;">
                                <span class="title_red title_red5_ie8">Get inCyyte !</span></a>

                        </div>
                         <% } else if (StringUtils.equals(section, Constants.BUSINESS_SECTION)) { %>  
                    	   <div id="SilverMemberOptions">
                            <div class="payment_info_left_head">
                                <div class="payment_info_steps_txt" style="line-height: 28px;  margin-left: 15px;  margin-top: 20px; width: auto;">
                                    Package<br> Selection
                                </div>
                            </div>
                            <div class="payment_info_left_txt">
                                <div class="payment_info_left_txtall">
                                    <strong>Silver <br>Membership</strong>
                                    <div class="silverPayment_badge">
                                        <img src="ui/images/SilverPackahe.png">
                                    </div>
                                </div>
                            </div>
                            <div class="payment_info_left_txt_bottom" style="margin-top: 10px;">
                          
                                <div class="payment_info_left_txtall">
                                    The total cost for this <br>selection is:
                                </div>
                                <div class="payment_info_left_txtall" style="color: #797979;"> � ${PaymentForm.displayAmountWOTax} ex vat.</div>
										
                                <div class="payment_info_left_txtall"> <strong> �  ${PaymentForm.displayAmount} incl vat.</strong></div>
                            </div>
                        </div>
                     <% } %>
                    </div>
                    <div class="line"></div>
                    <div class="grid_17 grid_17_bg">
                        <form>
                        
                    <% if (StringUtils.equals(section, Constants.POLL_SECTION)) { %>
                            <div class="payment_info_header">
                                <h1>Send Poll To A Region</h1>
                            </div>
                     <%} else if (StringUtils.equals(section, Constants.BUSINESS_SECTION)){%> 
                             <div class="payment_info_header">
                                <h1>Checkout Complete!</h1>
                            </div>
                      <% } %>   
                            <div class="payment_info_right_wrap">
                                <div class="payment_info_steps_wrap">
                                    <div class="payment_info_step1_active" style="color:#747474; ">Step 1</div>
                                    <div class="payment_info_steps_active" style="color:#747474; ">Step 2</div>
                                    <div class="payment_info_steps_active" style="color:#747474; ">Step 3</div>
                                    <div class="payment_info_steps_active">Step 4</div>

                                    <div id="help" style="top: 114px;">
                                        <a style="z-index: 500" href="#">
                                            <em class="arrow_box1" style="z-index: 99; display: none; top: 50px;"></em>
                                            <em style="display: none; top: 50px;"></em>
                                        </a>
                                    </div>
                                </div>
                 <%  if (StringUtils.equals(section, Constants.POLL_SECTION)) { %>
                                <div class="payment_info_steps_txt">Thank you.. Your order is now completed!</div>
                                <div class="payment_info_steps_txt" style="font-size: 14px;">Your inCyyte poll has now been sent to your selected region. <br>
                                    Click the `Done` button below to return to your inCyyte dashboard and <br>
                                    to watch your poll results roll on in.

                                </div>
                                
                <% } else  if (StringUtils.equals(section, Constants.BUSINESS_SECTION)){%>
                                
                               <div class="payment_info_steps_txt"><b>Thank you.. Your order is now completed!<br><br>
                               You are now a Silver Member</b>
                               </div>  
              			  <% } %>
                                <div class="payment_info_steps_txt" style="font-size: 16px;">Your Order Reference number is  <%=orderId %>        <br> <br>
                                    A confirmation email has been sent to <b><%=user.getEmail()%></b>

                                </div>
                                <div class="payment_info_steps_txt" style="width: 76%; border-bottom: 1px dashed  #747474"></div>

                                 <div class="payment_info_steps_txt" style="font-size: 18px;line-height: 25px; ">
                                   ${PaymentForm.title}.&nbsp;${PaymentForm.paymentFirstName}&nbsp;${PaymentForm.paymentLastName}<br>
                                   ${PaymentForm.companyName}<br>
                                   ${PaymentForm.countryBilling}<br>
                                   ${PaymentForm.postcodeBilling}</div>
                                
                                <div class="payment_info_steps_txt" style="font-size: 18px;line-height: 25px; ">
                                  ORDER DETAILS

                                </div>
                                
                     <% if (StringUtils.equals(section, Constants.POLL_SECTION)) { %>
                                <div id="pollfrom">
                                    <div class="payment_info_fields_wrap" style=" margin-top: 20px;"><label>Poll: </label>
                                        <span  ><label style="width: auto;"><strong>${incyyteModel.incyyte} </strong></label></span> </div>
                        <% if (StringUtils.equals(model.getLocality(), "Postcode")) {%>
                          <div class="payment_info_fields_wrap" style=" margin-top: 20px;"><label>Postcode: </label> <span><label style="width: auto;"><strong>${incyyteModel.postcode} </strong></label></span> </div>
                        <% } else if(StringUtils.equals(model.getLocality(), "Region")){ %>
                          <div class="payment_info_fields_wrap" style=" margin-top: 20px;"><label>Region: </label> <span><label style="width: auto;"><strong>${incyyteModel.region} </strong></label></span> </div>
                        <% } else { %>
                          <div class="payment_info_fields_wrap" style=" margin-top: 20px;"><label>County: </label> <span  ><label style="width: auto;"><strong>${incyyteModel.county} </strong></label></span> </div>
                        <%}%>
                          <% if(StringUtils.equals(model.getAgeMin(), "select") && StringUtils.equals(model.getAgeMax(), "select")) {%>
                    	  <div class="payment_info_fields_wrap" style=" margin-top: 20px;"><label>Age: </label><span  ><label style="width: auto;"><strong>0  -  0 </strong></label></span></div>
                    	  <%} else{%> 
                    	  <div class="payment_info_fields_wrap" style=" margin-top: 20px;"><label>Age: </label><span  ><label style="width: auto;"><strong>${incyyteModel.ageMin}-${incyyteModel.ageMax}</strong></label></span></div>
                        <%} %>  
                        
                         <% if (StringUtils.equals(model.getGender(), "select")) {%>
                    	   <div class="payment_info_fields_wrap" style=" margin-top: 20px;"><label>Gender: </label><span  ><label style="width: auto;"><strong>Both</strong></label></span> </div>
                    	  <% } else { %>
                    	  <div class="payment_info_fields_wrap" style=" margin-top: 20px;"><label>Gender: </label><span  ><label style="width: auto;"><strong>${incyyteModel.gender}</strong></label></span> </div>
                         <% }%>
                         
                       <% if (StringUtils.equals(model.getLocality(), "Postcode")) {%>
                          <div class="payment_info_fields_wrap" style=" margin-top: 20px;"><label>Postcode: </label> <span  ><label style="width: auto;"><strong>${incyyteModel.postcode} </strong></label></span> </div>
                        <% } else if(StringUtils.equals(model.getLocality(), "Region")){ %>
                          <div class="payment_info_fields_wrap" style=" margin-top: 20px;"><label>Region: </label> <span  ><label style="width: auto;"><strong>${incyyteModel.region} </strong></label></span> </div>
                        <% } else { %>
                          <div class="payment_info_fields_wrap" style=" margin-top: 20px;"><label>County: </label> <span  ><label style="width: auto;"><strong>${incyyteModel.county} </strong></label></span> </div>
                        <%}%> 
                                    <div class="payment_info_fields_wrap" style=" margin-top: 20px;"><label>Audience: </label>
                                        <span  ><label style="width: auto;"><strong>${incyyteModel.pollRecipients}</strong></label></span> </div>
                                    <div class="payment_info_fields_wrap" style=" margin-top: 20px;"><label>Fee Paid: </label>
								    <c:set var="totalPollCost" value="${PaymentForm.amount / 100}"/>
                                    <c:choose>
                         				<c:when test="${totalPollCost >= '1'}">
                                 				<span  ><label style="width: auto;"><strong> � ${PaymentForm.displayAmount} </strong></label></span> </div>		  	
										</c:when>
				                     	<c:otherwise>
				                			  <span  ><label style="width: auto;"><strong> ${PaymentForm.displayAmount} p </strong></label></span> </div>
										</c:otherwise>
									</c:choose> 
                                   </div>
            <% } else  if (StringUtils.equals(section, Constants.BUSINESS_SECTION)) { %>
                                   <div class="payment_info_fields_wrap" style=" margin-top: 20px;"><label>Package:</label>
                                   <span><label style="width:auto;"> <strong> Silver Member </strong></label></span> 
                                   <div class="payment_info_fields_wrap" style=" margin-top: 20px;"><label>Fee paid:</label>
                                    <span><label style="width:auto;"> <strong> � ${PaymentForm.amount / 100} </strong></label></span> 
                                    </div>
                  <% } %>
                  
                        
                    </div>
                    <% if (StringUtils.equals(section, Constants.POLL_SECTION)) { %>
                            <div class="payment_info_btns_wrap" style="margin-left: -60px;">
                                <div class="payment_info_btn"><a class="poll_button1" style="width:170px;"  href="dashincomming.cyt"><span class="poll_button_green ">Done</span></a></div>
                            </div>
                   <% } else  if (StringUtils.equals(section, Constants.BUSINESS_SECTION)) { %>
                    <div class="payment_info_btns_wrap" style="margin-left: -60px;">
                                <div class="payment_info_btn"><a class="poll_button1" style="width:100px; float: left;"  href="dashincomming.cyt"><span class="poll_button_green ">Done</span></a></div> 
                                 <div class="payment_info_btn"><a class="poll_button1" style="width:170px;float: left; "  href="${pageContext.request.contextPath}/create_question.cyt"><span class="poll_button_red" style="width:1left70px;height:27px;"> Get inCyyte !</span></a></div> 
                            </div>
                     <% } %>
                     </form>
                </div>
            </article>
        </div>
    </div>
    <script type="text/javascript" charset="utf-8">
        $(document).ready(function () {
            $('#checkboxDemo input[type=checkbox],#radioDemo input[type=radio]').prettyCheckboxes();
            $('.inlineRadios input[type=radio]').prettyCheckboxes({'display':'inline'});
            $('.checkboxDemo input[type=checkbox]').prettyCheckboxes({'display':'inline'});
        });
    </script>
    <!-- include footer -->
    <jsp:include page="../common/includes/footer.jsp"/>
</div>
<script type="text/javascript">

    function copyval(f) {
        if(f.copy.checked == true) {
            f.address3.value=f.address.value;
            f.address4.value=f.address1.value ;
            f.city1.value=f.city.value ;
            f.pcode1.value=f.pcode.value ;
            f.country1.value=f.country.value ;
        }
        else
        {
            f.address3.value="";
            f.address4.value="";
            f.city1.value="";
            f.pcode1.value="";
            f.country1.value="";

        }
    }
</script>
<script type="text/javascript" src="ui/js/googleanalytics.js"></script>
</body>
</html>
