<%@ page import="org.apache.commons.lang.StringUtils" %>
<%@ page import="com.incyyte.app.web.model.IncyyteModel" %>
<%@page import="com.incyyte.app.service.util.Constants" %>
<%@ include file="/WEB-INF/jsp/common/include.jsp" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">
    <link rel="shortcut icon" href="favicon.ico" />
    <title>paymentFailure</title>
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
            if($('#errorReason').val() == "DECLINE"){
            	$('#errorMessage').text('Payment process request failed Because No.of attempts are over So Declined from the Process');	
            	$('#errorMessage').css("display","");	
            }else if($('#errorReason').val() == "EXCEPTION"){
           	 	$('#errorMessage').text('Payment process request failed due to an Exception Occured');
            	$('#errorMessage').css("display","");	
            }else if ($('#errorReason').val() == "CANCEL"){
            	$('#errorMessage').text('Payment process request was cancelled.');	
            	$('#errorMessage').css("display","");	
            }            
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
<% IncyyteModel model = (IncyyteModel) session.getAttribute("inCyyteForm");%>
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
                      if (StringUtils.equals(section, Constants.POLL_SECTION)) { %>
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
                                <span class="title_red title_red5_ie8"> Get inCyyte !</span></a>
                        </div>
                   <% } else if (StringUtils.equals(section, Constants.BUSINESS_SECTION)) {%>  
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
                                <div class="payment_info_left_txtall" style="color: #797979;"> � ${PaymentForm.displayAmountWOTax} ex vat.  </div>
                                <div class="payment_info_left_txtall"> <strong> � ${PaymentForm.displayAmount} incl vat.</strong></div>
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
                		<form:form id="PaymentFormOverview" name="PaymentFormOverview" commandName="PaymentForm" enctype="multipart/form-data" method="post">
                                <div id="errorMessage" class="payment_info_steps_txt" style="color: #c1002c; margin-top: 40px;margin-left: 20px;"></div>
                                 <form:input type="hidden"  id="errorReason"  path="errorReason"/>
                                </form:form>
                            </div>
                             <% if (StringUtils.equals(section, Constants.POLL_SECTION)) { %>
                            <div class="payment_info_btns_wrap">
                                <div class="payment_info_btn"><a class="poll_button1" style="width:100px;"  href="${pageContext.request.contextPath}/create_question.cyt"><span class="poll_button_red">Back </span></a></div>
                            		  <c:set var="pollPaymentFailureCount" value="${pollPaymentFailureCount}"/>
                            		  <c:choose>
                         				<c:when test="${pollPaymentFailureCount < 3}">
                         					<div class="payment_info_btn"><a class="poll_button1" style="width:170px;"  href="${pageContext.request.contextPath}/paymentInfo.cyt"><span class="poll_button_red">Retry</span></a></div>
                            			</c:when>
				                     	<c:otherwise>
                            			</c:otherwise>
									</c:choose>
                            </div>
                            <%} else if (StringUtils.equals(section, Constants.BUSINESS_SECTION)){%>
                           <div class="payment_info_btns_wrap">
                                <div class="payment_info_btn"><a class="poll_button1" style="width:100px;"  href="${pageContext.request.contextPath}/incyyteBusiness.cyt"><span class="poll_button_red">Back </span></a></div>
                                <c:set var="businessPaymentFailureCount" value="${businessPaymentFailureCount}"/>
                            		  <c:choose>
                         				<c:when test="${businessPaymentFailureCount < 3}">
                         					 <div class="payment_info_btn"><a class="poll_button1" style="width:170px;"  href="${pageContext.request.contextPath}/businessUserPaymentProcess.cyt"><span class="poll_button_red">Retry</span></a></div>
                            			</c:when>
				                     	<c:otherwise>
                            			</c:otherwise>
								</c:choose>
                            </div> 
                            <%}%>
                        </form>
                    </div>

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
