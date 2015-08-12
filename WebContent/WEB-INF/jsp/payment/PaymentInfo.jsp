<%@page import="com.incyyte.app.web.SessionKeys"%>
<%@ page import="org.apache.commons.lang.StringUtils" %>
<%@page import="com.incyyte.app.web.model.PaymentModel"%>
<%@ page import="com.incyyte.app.web.model.IncyyteModel" %>
<%@page import="com.incyyte.app.domain.User"%>
<%@page import="com.incyyte.app.service.util.Constants" %>
<%@ include file="/WEB-INF/jsp/common/include.jsp" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">
    <link rel="shortcut icon" href="favicon.ico" />
     <% User user = (User) session.getAttribute(SessionKeys.LOGIN_USER); %>    
    <title>Payment Information</title>
    
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
<script src="ui/js/jquery-1.7.1.js"></script>
<script type="text/javascript" src="ui/js/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="js/jquery.form.js"></script>
<script type="text/javascript" src="ui/js/jquery-ui.js"></script>
<script type="text/javascript" src="ui/js/validate/jquery.validate.js" ></script>

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
     <script type="text/javascript">
    function paymentDetails(){
    	if(isFirstNameValid() && isLastNamevalid() && isAddressLine1Valid() &&  isAddressLine2Valid() && isCityValid() && isPostcodeValid() &&  isContactEmailValid() && isPhoneNumber1Valid() && isAddressLine3Valid() && isAddressLine4Valid() && isCity1Valid() && isPostcode1Valid()){
      	   $("#PaymentForm").attr("action", "paymentInfoOverview.cyt");
           $("#PaymentForm").submit(); 	
    	}
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
      <div class="main">
            <article id="content">
               <form:form id="PaymentForm" name="PaymentForm" commandName="PaymentForm" enctype="multipart/form-data" method="post">
               <form:input type="hidden" path="amountWOTax" value="${PaymentForm.amountWOTax}"/>
               <form:input type="hidden" path="amount" value="${PaymentForm.amount}"/>
                <div id="main_content">
                    <div class="grid_9" >
                    <c:set var="section" value="${section}"/>
                                    <%
                                        String section = String.valueOf(pageContext.getAttribute("section"));
                                        if (StringUtils.equals(section, Constants.POLL_SECTION)) {
                                    %>
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
                    	  <%  
                    	    } else { %>
                        <div class="payment_info_left_txtall" style="margin-bottom: 0px;"> Gender:<strong style="margin-left: 10px;">${incyyteModel.gender}</strong></div>
                       <% }%>
                       
                       <% if(StringUtils.equals(model.getAgeMin(), "select") && StringUtils.equals(model.getAgeMax(), "select")) {%>
                    	  <div class="payment_info_left_txtall" style="margin-bottom: 0px;"> Age:<strong style="margin-left: 10px;"> 0  -  0 </strong></div>
                    	 <%   
                       } else{%> 
                        <div class="payment_info_left_txtall" style="margin-bottom: 0px;"> Age:<strong style="margin-left: 10px;">${incyyteModel.ageMin}-${incyyteModel.ageMax}</strong></div>
                        <%} %>
                        <div class="payment_info_left_txtall" style="text-align: right;margin-right: 25px;float: left;"><a href="${pageContext.request.contextPath}/create_question.cyt">Edit</a></div>
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
                            <c:set var="pollCost" value="${PaymentForm.amountWOTax / 100}"/>
                        			<c:choose>
                         				<c:when test="${pollCost >= '1'}">
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
                        <div class="payment_info_header">
                    <%if (StringUtils.equals(section, Constants.POLL_SECTION)) {%>
                         <h1>Send Poll To A Region</h1>
                      <% } else if (StringUtils.equals(section, Constants.BUSINESS_SECTION)) {%>
                        <h1>Silver Membership</h1>
                        <%}%>
                      </div>
                        <div class="payment_info_right_wrap">
                         <div class="payment_info_steps_wrap">
                            <div class="payment_info_step1_active">Step 1</div>
                            <div class="payment_info_steps_inactive">Step 2</div>
                            <div class="payment_info_steps_inactive">Step 3</div>
                            <div class="payment_info_steps_inactive">Step 4</div>
                             <div id="help"><a href="#"><em style="z-index:99" class="arrow_box1">
       							 <p class="helpmodal">Your Contact Details & Billing Address</p>
        							You will need to provide us with your contact details for<br> future reference. Also enter the Billing address for this payment if it different from your contact details.<br>Your details will not be passed to any third parties.</em></a>
  							  </div>  
                         </div>
                            <div class="payment_info_steps_txt">Please enter your contact details</div>
                            <div class="payment_info_steps_txt" style="font-size: 14px;">We require your details for our records. No poll recipients will see your personal
                               <br> Information and it will not be passed to any third parties.
                            </div>
                              <div id="pollfrom">
                               <div class="payment_info_fields_wrap" style=" margin-top: 20px;"><label>Title <a style="color: #FF0000">*</a></label><span style="width: 150px;"><form:select id="title" path="title"><form:option value="Mr">Mr</form:option><form:option value="Mrs">Mrs</form:option><form:option value="Ms">Ms</form:option><form:option value="Miss">Miss</form:option></form:select></span></div>
                               <div class="payment_info_fields_wrap"><label>First Name <a style="color: #FF0000">*</a> </label><span><form:input type="text" id="Firstname" path="paymentFirstName" onkeyup="isFirstNameValid();"/> </span><br><p class="errorPaymentInfo" id="FirstnameError"></p> </div>
                                <div class="payment_info_fields_wrap"><label>Last Name <a style="color: #FF0000">*</a> </label><span><form:input type="text" id="LastName" path="paymentLastName" onkeyup="isLastNamevalid();"></form:input></span><br><p class="errorPaymentInfo" id="LastNameError" ></p> </div>
                                 <div class="payment_info_fields_wrap"><label>Address Line 1 <a style="color: #FF0000">*</a> </label><span><form:input name="addressPayment1" id="addressPayment1" path="addressPayment1" onKeyup="isAddressLine1Valid();" type="text"></form:input></span><br><p class="errorPaymentInfo" id="AddressLine1Error"></p></div>
                                  <div class="payment_info_fields_wrap"><label>Address Line 2 <a style="color: #FF0000">*</a></label><span><form:input name="addressPayment2" id="addressPayment2" path="addressPayment2"  onKeyup="isAddressLine2Valid();" type="text"></form:input> </span><br><p class="errorPaymentInfo"  id="AddressLine2Error"></p></div>
                                   <div class="payment_info_fields_wrap"><label>City <a style="color: #FF0000">*</a> </label><span><form:input name="city" id="city" path="city" onKeyup="isCityValid();" type="text"></form:input> </span><p class="errorPaymentInfo" id="cityError"></p></div>
                                  <div class="payment_info_fields_wrap"><label>Postcode <a style="color: #FF0000">*</a> </label><span style="width: 150px;"><form:input name="postcode" id="postcode"  path="postcode" onKeyup="isPostcodeValid();" placeholder="AB1 2CD" type="text"></form:input></span></div>
                                   <div class="payment_info_fields_wrap"> <p class="errorPaymentInfo" id="postcodeError"></p></div>
                                  <div class="payment_info_fields_wrap"><label>Country <a style="color: #FF0000">*</a> </label><span style="width: 380px;"><form:select id="country" path="country"><form:option value="UK">United Kingdom</form:option></form:select></span></div>
                                 <div class="payment_info_fields_wrap"><label>Contact Email Address <a style="color: #FF0000">*</a> </label><span><form:input type="text" id="email" path="email" onKeyup="isContactEmailValid();"></form:input> </span><br><p class="errorPaymentInfo" id="contactEmailError"></p> </div>
                                  <div class="payment_info_fields_wrap"><label>Mobile Phone <a style="color: #FF0000">*</a> </label><span><form:input type="text" id="mobileNumber" path="mobileNumber" onKeyup="isPhoneNumber1Valid();" placeholder="01234 567 890"></form:input></span><br><p class="errorPaymentInfo" id="Phone1Error"></p> </div>

                                  <div class="payment_info_steps_txt" style="margin-bottom: 12px;margin-top: 60px;float: left;">Billing Address</div>

                                  <div class="payment_info_fields_wrap  "><label>Use same address as above</label><span class="checkboxDemo">

                                      <input type="checkbox" name="copy" onClick="copyval(this.form)" id="checkbox-1" value="checkbox-1" />
                                      <label for="checkbox-1" style="width: auto; margin-left: -5px;"  tabindex="1"></label>
                                      </span> </div>
                                  <div class="payment_info_fields_wrap"><label>Company (Optional)</label><span><form:input name="companyName" type="text" id="companyName" path="companyName" onkeyup="iscompanynamevalid();"></form:input></span><br><p class="errorPaymentInfo" id="companynameError"></p> </div>
                                  <div class="payment_info_fields_wrap"><label>Address Line 1 <a style="color: #FF0000">*</a> </label><span><form:input name="addressPaymentBilling1" id="addressPaymentBilling1" path="addressPaymentBilling1" onKeyup="isAddressLine3Valid();" type="text"></form:input></span><br><p class="errorPaymentInfo" id="AddressLine3Error"></p> </div>
                                  <div class="payment_info_fields_wrap"><label>Address Line 2 <a style="color: #FF0000">*</a> </label><span><form:input name="addressPaymentBilling2" id="addressPaymentBilling2" path="addressPaymentBilling2" onKeyup="isAddressLine4Valid();" type="text"></form:input></span><br><p class="errorPaymentInfo" id="AddressLine4Error"></p> </div>
                                  <div class="payment_info_fields_wrap"><label>City <a style="color: #FF0000">*</a> </label><span><form:input name="cityBilling" id="cityBilling" path="cityBilling" onKeyup="isCity1Valid();" type="text"></form:input></span><br><p class="errorPaymentInfo" id="city1Error"></p></div>
                                  <div class="payment_info_fields_wrap"><label>Postcode <a style="color: #FF0000">*</a> </label><span style="width: 150px;"><form:input name="postcodeBilling" id="postcodeBilling"  path="postcodeBilling" onKeyup="isPostcode1Valid();" placeholder="AB1 2CD" type="text"></form:input></span></div>
                                  <div class="payment_info_fields_wrap"><p class="errorPaymentInfo" id="postcode1Error"></p></div>
                                  <div class="payment_info_fields_wrap"><label>Country <a style="color: #FF0000">*</a> </label><span style="width: 380px;"><form:select id="countryBilling" path="countryBilling"><form:option value="UK">United Kingdom</form:option></form:select></span></div>
                              </div>
                        </div>
                      <% if (StringUtils.equals(section, Constants.POLL_SECTION)) { %>
                        <div class="payment_info_btns_wrap">
                            <div class="payment_info_btn"><a class="poll_button1" style="width:170px;"  href="javascript:paymentDetails();"><span class="poll_button_green ">Next</span></a></div>
                            <div class="payment_info_btn"><a class="poll_button1" style="width:170px;"  href="${pageContext.request.contextPath}/create_question.cyt"><span class="poll_button_gray ">cancel</span></a></div>
                        </div>
                        <%}%>
                    <% if (StringUtils.equals(section, Constants.BUSINESS_SECTION)) { %>
                        <div class="payment_info_btns_wrap">
                            <div class="payment_info_btn"><a class="poll_button1" style="width:170px;"  href="javascript:paymentDetails();"><span class="poll_button_green ">Next</span></a></div>
                            <div class="payment_info_btn"><a class="poll_button1" style="width:170px;"  href="${pageContext.request.contextPath}/incyyteBusiness.cyt"><span class="poll_button_gray ">cancel</span></a></div>
                        </div>
                        <%}%>
                    </div>
                </div>
                </form:form>
                </article>
            </div>
     
    </div>
    <script type="text/javascript" charset="utf-8">
        $(document).ready(function () {
            $('#checkboxDemo input[type=checkbox],#radioDemo input[type=radio]').prettyCheckboxes();
            $('.inlineRadios input[type=radio]').prettyCheckboxes({'display':'inline'});
            $('.checkboxDemo input[type=checkbox]').prettyCheckboxes({'display':'inline'});
           
             function getContextPath() {
				return "<%=request.getContextPath()%>";
			}
        });
    </script>
    <!-- include footer -->
    <jsp:include page="../common/includes/footer.jsp"/>
</div>
<script type="text/javascript">
    function copyval(f) {
        if(f.copy.checked == true) {
          $('#addressPaymentBilling1').val($('#addressPayment1').val()) ;
          $('#addressPaymentBilling2').val($('#addressPayment2').val()) ;
          $('#cityBilling').val($('#city').val()) ;
          $('#postcodeBilling').val($('#postcode').val()) ;
          $('#countryBilling').val($('#country').val());
          document.getElementById('addressPaymentBilling1').setAttribute('readonly', true);
          document.getElementById('addressPaymentBilling2').setAttribute('readonly', true);
          document.getElementById('cityBilling').setAttribute('readonly', true);
          document.getElementById('postcodeBilling').setAttribute('readonly', true);
          document.getElementById('countryBilling').setAttribute('readonly', true);
			 document.getElementById('addressPaymentBilling1').setAttribute('class', 'readonly_input');
	         document.getElementById('addressPaymentBilling2').setAttribute('class', 'readonly_input');
	         document.getElementById('cityBilling').setAttribute('class', 'readonly_input');
	         document.getElementById('postcodeBilling').setAttribute('class', 'readonly_input');
	         document.getElementById('countryBilling').setAttribute('class', 'readonly_input');
          
        }else {
        	 document.getElementById('addressPaymentBilling1').removeAttribute('readonly');
             document.getElementById('addressPaymentBilling2').removeAttribute('readonly');
             document.getElementById('cityBilling').removeAttribute('readonly');
             document.getElementById('postcodeBilling').removeAttribute('readonly');
             document.getElementById('countryBilling').removeAttribute('readonly');
            	 document.getElementById('addressPaymentBilling1').setAttribute('class', '');
	             document.getElementById('addressPaymentBilling2').setAttribute('class', '');
	         	 document.getElementById('cityBilling').setAttribute('class' , '');
	        	 document.getElementById('postcodeBilling').setAttribute('class' , '');
	         	 document.getElementById('countryBilling').setAttribute('class', '');
        	 $('#addressPaymentBilling1').val('');
        	 $('#addressPaymentBilling2').val('');
        	 $('#cityBilling').val('');
        	 $('#postcodeBilling').val('');
        	 $('#countryBilling').val('');	
        }
    }
    
    
    function isAddressLine1Valid() {
   	 $('#AddressLine1Error').css("display", "none");
       var AddressLine1 = $("#addressPayment1").val();
       var valid = true;
       var min = 5;
       var max = 20;
       valid = checkLength($('#addressPayment1'), "AddressLine1", min, max);
       if (AddressLine1 != "") {
           if (!valid) {
               $('#AddressLine1Error').text('Address Line 1 must range between ' + min + ' and ' + max);
               $('#AddressLine1Error').css('color',"#C2002D");
               $('#AddressLine1Error').css("display", "");
               return false;
           }
           if (!isSpclCharValid(AddressLine1)) {
               $('#AddressLine1Error').text('Invalid characters are not allowed');
               $('#AddressLine1Error').css("display", "");
               return false;
           }
       }else{
    	   $('#AddressLine1Error').text('User AddressLine1 is required');
           $('#AddressLine1Error').css("display", "");
           return false;
       }
       return true;
    }
   
    function isSpclCharValid(value) {
        var iChars = "!@#$%^&*()+=_`~-[]\\\';,./{}|\":<>?";
        var isValid = true;
        for (var i = 0; i < value.length; i++) {
            if (iChars.indexOf(value.charAt(i)) != '-1') {
                isValid = false;
                break;
                return isValid;
            } else {
                isValid = true;
            }
        }
        return isValid;
    }
    
    function isSpclCharValidCompany(value) {
        var iChars = "!@#$%^*+=`~[]\\\';,{}|\":<>?";
        var isValid = true;
        for (var i = 0; i < value.length; i++) {
            if (iChars.indexOf(value.charAt(i)) != '-1') {
                isValid = false;
                break;
                return isValid;
            } else {
                isValid = true;
            }
        }
        return isValid;
    }
    
    
    
    function isAddressLine2Valid() {
      	 $('#AddressLine2Error').css("display", "none");
          var AddressLine1 = $("#addressPayment2").val();
          var valid = true;
          var min = 5;
          var max = 20;
          valid = checkLength($('#addressPayment2'), "AddressLine2", min, max);
          if (AddressLine1 != "") {
              if (!valid) {
                  $('#AddressLine2Error').text('Address Line 2 must range between ' + min + ' and ' + max);
                  $('#AddressLine2Error').css('color',"#C2002D");
                  $('#AddressLine2Error').css("display", "");
                  return false;
              }
              if (!isSpclCharValid(AddressLine1)) {
                  $('#AddressLine2Error').text('Invalid characters are not allowed');
                  $('#AddressLine2Error').css("display", "");
                  return false;
              }
          }else{
        	  $('#AddressLine2Error').text('User AddressLine2 is required');
              $('#AddressLine2Error').css("display", "");
              return false;
          }
          return true;
       }
       
       function isSpclCharValid(value) {
           var iChars = "!@#$%^&*()+=_`~-[]\\\';,./{}|\":<>?";
           var isValid = true;
           for (var i = 0; i < value.length; i++) {
               if (iChars.indexOf(value.charAt(i)) != '-1') {
                   isValid = false;
                   break;
                   return isValid;
               } else {
                   isValid = true;
               }
           }
           return isValid;
       }
       
       function isContactEmailValid() {
    		 $('#contactEmailError').css("display", "none");
    	    var contactEmail = $("#email").val();
    	    var checkRegexp = /^([\w-`~!#$^&*()-+=";:,<>\.]+@([\w-`~!#$^&*()-+=";:,<>]+\.)+[\w-`~!#$^&*()-+=";:,<>]{2,4})?$/;
    	    var valid = true;
    	    if ($("#contactEmail").val() != "") {
    	        if (!checkRegexp.test(contactEmail)) {
    	            $('#contactEmailError').text('Enter a valid email address');
    	            $('#contactEmailError').css("display", "");
    	            return false;
    	        }
    	    }else{
    	    	$('#contactEmailError').text('Email address is required');
	            $('#contactEmailError').css("display", "");
	            return false;
    	    }
    	    
    	    return true;

    	}
       
       function isPhoneNumber1Valid() {
    	    $('#Phone1Error').css("display", "none");
    	    var valid = true;
    	    var min = 10;
    	    var max = 15;
    	    var phone = $("#mobileNumber").val();
    	    var checkRegexp = /^[0-9\s\-\+]*$/;
    	    valid = checkLength($('#mobileNumber'), "phone", min, max);
    	    if ($("#mobileNumber").val() != "") {
    	        if (!valid) {
    	            $('#Phone1Error').text('phone number must range between ' + min + ' and ' + max);
    	            $('#Phone1Error').css("display", "");
    	            return false;
    	        }
    	        if (!checkRegexp.test(phone)) {
    	            $('#Phone1Error').text('Phone number should be Numeric');
    	            $('#Phone1Error').css("display", "");
    	            return false;
    	        }
    	    }else{
    	    	 $('#Phone1Error').text('Phone Number is required');
 	            $('#Phone1Error').css("display", "");
 	            return false;
    	    }
    	    return true;
    	}
       
       function isCityValid() { 
    	
    		 $('#cityError').css("display", "none");
    	    var city = $("#city").val();
    	    var valid = true;
    	    var min = 5;
    	    var max = 20;
    	    var checkRegexp = /^[a-zA-Z ]+$/;
    	    valid = checkLength($('#city'), "city", min, max);
    	    if ($("#city").val() != "") {
    	        if (!valid) {
    	            $('#cityError').text('city must range between ' + min + ' and ' + max);
    	            $('#cityError').css("display", "");
    	            return false;
    	        }
    	        if (!checkRegexp.test(city)) {
    	            $('#cityError').css("display", "none");
    	            $('#cityError').text('Alphabets only allowed');
    	            $('#cityError').css("display", "");
    	            return false;
    	        }
    	    }else{
    	    	$('#cityError').text('City is required');
 	            $('#cityError').css("display", "");
 	            return false;
    	    }
    	    return true;
    	}


    	function isSpclCharValid(value) {
    	    var iChars = "!@#$%^&*()+=_`~-[]\\\';,./{}|\":<>?";
    	    var isValid = true;
    	    for (var i = 0; i < value.length; i++) {
    	        if (iChars.indexOf(value.charAt(i)) != '-1') {
    	            isValid = false;
    	            break;
    	            return isValid;
    	        } else {
    	            isValid = true;
    	        }
    	    }
    	    return isValid;
    	}
       
    	function isPostcodeValid() {		
    		  $('#postcodeError').css("display", "none");
    		 $('#postcode').keyup(function(){
    			    this.value = this.value.toUpperCase();
    			});
    	    var postalCode = $("#postcode").val();
    	    var valid = true;
    	    var min = 6;
    	    var max = 8;
    	    var checkRegexp = /^([Gg][Ii][Rr]0[Aa]{2})|((([A-Za-z][0-9]{1,2})|(([A-Za-z][A-Ha-hJ-Yj-y][0-9]{1,2})|(([A-Za-z][0-9][A-Za-z])|([A-Za-z][A-Ha-hJ-Yj-y][0-9]?[A-Za-z])))){0,1}[0-9][A-Za-z]{2})$/;
    	    valid = checkLength($('#postcode'), "postalCode", min, max);
    	    if(postalCode == ""){
    	    	$('#postcodeError').text('Postcode is required');
    	        $('#postcodeError').css("display", "");
    	        return false;
    	    }
    	    if (!valid) {
    	        $('#postcodeError').text('postcode must range between ' + min + ' and ' + max);
    	        $('#postcodeError').css("display", "");
    	        return false;
    	    }
    	    if (!checkRegexp.test(postalCode)) {
    	        $('#postcodeError').text('Invalid Post Code');
    	        $('#postcodeError').css("display", "");
    	        return false;
    	    }
    	    return true;
    	}
    	
    	
    	function isFirstNameValid() {
   		 $('#FirstnameError').css("display", "none");
   	    var Firstname = $("#Firstname").val();
   	    var valid = true;
   	    var min = 3;
   	    var max = 30;
   	    var checkRegexp = /^[a-zA-Z]+$/;
   	    valid = checkLength($('#Firstname'), "Firstname", min, max);
   	    if ($("#Firstname").val() != "") {
   	        if (!valid) {
   	            $('#FirstnameError').text('Firstname must range between ' + min + ' and ' + max);
   	            $('#FirstnameError').css("display", "");
   	            return false;
   	        }
   
   	     if (!isSpclCharValid(Firstname)) {
             $('#FirstnameError').text('Invalid characters are not allowed');
             $('#FirstnameError').css("display", "");
             return false;
         }
         if (!checkRegexp.test(Firstname)) {
    	            $('#FirstnameError').css("display", "none");
    	            $('#FirstnameError').text('Alphabets only allowed');
    	            $('#FirstnameError').css("display", "");
    	            return false;
    	        }
         
   	    }else{
   	     $('#FirstnameError').text('FirastName is required');
         $('#FirstnameError').css("display", "");
            return false;
   	    }
   	    return true;
   	}

   	function isSpclCharValid(value) {
   	    var iChars = "!@#$%^&*()+=_`~-[]\\\';,./{}|\":<>?";
   	    var isValid = true;
   	    for (var i = 0; i < value.length; i++) {
   	        if (iChars.indexOf(value.charAt(i)) != '-1') {
   	            isValid = false;
   	            break;
   	            return isValid;
   	        } else {
   	            isValid = true;
   	        }
   	    }
   	    return isValid;
   	}
       
   	
   	function isLastNamevalid() {
		 $('#LastNameError').css("display", "none");
	    var LastName = $("#LastName").val();
	    var valid = true;
	    var min = 3;
	    var max = 20;
	    var checkRegexp = /^[a-zA-Z]+$/;
	    valid = checkLength($('#LastName')," Last Name", min, max);
	    if ($("#LastName").val() != "") {
	        if (!valid) {
	            $('#LastNameError').text('Last Name must range between ' + min + ' and ' + max);
	            $('#LastNameError').css("display", "");
	             $('#LastNameError').css('color',"#C2002D");
	            return false;
	        }
	        
	        
	        if (!isSpclCharValid(LastName)) {
	             $('#LastNameError').text('Following invalid characters @$&/_#%?+*^[]{}|`~<>!= are not allowed');
	             $('#LastNameError').css("display", "");
	             return false;
	         }
	         if (!checkRegexp.test(LastName)) {
    	            $('#LastNameError').css("display", "none");
    	            $('#LastNameError').text('Alphabets only allowed');
    	            $('#LastNameError').css("display", "");
    	            return false;
    	        }
	    }else{
	    	$('#LastNameError').text('LastName is required');
            $('#LastNameError').css("display", "");
            return false;
	    }
	    return true;
	}


	
	function isSpclCharValid(value) {
	    var iChars = "!@#$%^&*()+=_`~-[]\\\';,./{}|\":<>?";
	    var isValid = true;
	    for (var i = 0; i < value.length; i++) {
	        if (iChars.indexOf(value.charAt(i)) != '-1') {
	            isValid = false;
	            break;
	            return isValid;
	        } else {
	            isValid = true;
	        }
	    }
	    return isValid;
	}
     
	
	function isCity1Valid() { 

		$('#city1Error').css("display", "none");
	    var city = $("#cityBilling").val();
	    var valid = true;
	    var min = 5;
	    var max = 20;
	    var checkRegexp = /^[a-zA-Z ]+$/;
	    valid = checkLength($('#cityBilling'), "city", min, max);
	    if ($("#cityBilling").val() != "") {
	        if (!valid) {
	            $('#city1Error').text('city must range between ' + min + ' and ' + max);
	            $('#city1Error').css("display", "");
	            return false;
	        }
	        if (!checkRegexp.test(city)) {
    	            $('#city1Error').css("display", "none");
    	            $('#city1Error').text('Alphabets only allowed');
    	            $('#city1Error').css("display", "");
    	            return false;
    	        }
	    }else{
	    	 $('#city1Error').text('city is required');
	           $('#city1Error').css("display", "");
	           return false;
	    }
	    return true;
	}


	
	function isSpclCharValid(value) {
	    var iChars = "!@#$%^&*()+=_`~-[]\\\';,./{}|\":<>?";
	    var isValid = true;
	    for (var i = 0; i < value.length; i++) {
	        if (iChars.indexOf(value.charAt(i)) != '-1') {
	            isValid = false;
	            break;
	            return isValid;
	        } else {
	            isValid = true;
	        }
	    }
	    return isValid;
	}
	function isAddressLine3Valid() {
	   	 $('#AddressLine3Error').css("display", "none");
	       var AddressLine1 = $("#addressPaymentBilling1").val();
	       var valid = true;
	       var min = 5;
	       var max = 20;
	        var checkRegexp = /^[a-zA-Z]+$/;
	       valid = checkLength($('#addressPaymentBilling1'), "AddressLine3", min, max);
	       if (AddressLine1 != "") {
	           if (!valid) {
	               $('#AddressLine3Error').text('Address Line 1 must range between ' + min + ' and ' + max);
	               $('#AddressLine3Error').css("display", "");
	               return false;
	           }
	           if (!isSpclCharValid(AddressLine1)) {
	               $('#AddressLine3Error').text('Following invalid characters @$&/_#%?+*^[]{}|`~<>!= are not allowed');
	               $('#AddressLine3Error').css("display", "");
	               return false;
	           }
	           
	       }else{
	    	   $('#AddressLine3Error').text('Billing Address1 is required');
               $('#AddressLine3Error').css("display", "");
               return false;
	       }
	       return true;
	    }
	  
	    function isSpclCharValid(value) {
	        var iChars = "!@#$%^&*()+=_`~-[]\\\';,./{}|\":<>?";
	        var isValid = true;
	        for (var i = 0; i < value.length; i++) {
	            if (iChars.indexOf(value.charAt(i)) != '-1') {
	                isValid = false;
	                break;
	                return isValid;
	            } else {
	                isValid = true;
	            }
	        }
	        return isValid;
	    }
	    
	    function isAddressLine4Valid() {
		   	 $('#AddressLine4Error').css("display", "none");
		       var AddressLine2 = $("#addressPaymentBilling2").val();
		       var valid = true;
		       var min = 5;
		       var max = 20;
		        var checkRegexp = /^[a-zA-Z]+$/;
		       valid = checkLength($('#addressPaymentBilling2'), "AddressLine4", min, max);
		       if (AddressLine2 != "") {
		           if (!valid) {
		               $('#AddressLine4Error').text('Address Line 2 must range between ' + min + ' and ' + max);
		               $('#AddressLine4Error').css("display", "");
		               return false;
		           }
		           if (!isSpclCharValid(AddressLine2)) {
		               $('#AddressLine4Error').text('Following invalid characters @$&/_#%?+*^[]{}|`~<>!= are not allowed');
		               $('#AddressLine4Error').css("display", "");
		               return false;
		           }
		       }else{
		    	   $('#AddressLine4Error').text('Billing Address2 is required');
	               $('#AddressLine4Error').css("display", "");
	               return false;
		       }
		       return true;
		    }
		     
		    function isSpclCharValid(value) {
		        var iChars = "!@#$%^&*()+=_`~-[]\\\';,./{}|\":<>?";
		        var isValid = true;
		        for (var i = 0; i < value.length; i++) {
		            if (iChars.indexOf(value.charAt(i)) != '-1') {
		                isValid = false;
		                break;
		                return isValid;
		            } else {
		                isValid = true;
		            }
		        }
		        return isValid;
		    }
		    
		    function isPostcode1Valid() {
	    	  $('#postcode1Error').css("display", "none");
	    		 $('#postcodeBilling').keyup(function(){
	    			    this.value = this.value.toUpperCase();
	    			});
	    	    var postalCode1 = $("#postcodeBilling").val();
	    	    
	    	    var valid = true;
	    	    var min = 6;
	    	    var max = 8;
	    	    var checkRegexp = /^([Gg][Ii][Rr]0[Aa]{2})|((([A-Za-z][0-9]{1,2})|(([A-Za-z][A-Ha-hJ-Yj-y][0-9]{1,2})|(([A-Za-z][0-9][A-Za-z])|([A-Za-z][A-Ha-hJ-Yj-y][0-9]?[A-Za-z])))){0,1}[0-9][A-Za-z]{2})$/;
	    	    valid = checkLength($('#postcodeBilling'), "postalCode1", min, max);
	    	    if(postalCode1 == ""){
	    	    	$('#postcode1Error').text('Postcode1 is required');
	    	        $('#postcode1Error').css("display", "");
	    	        
	    	        return false;
	    	    }
	    	    if (!valid) {
	    	        $('#postcode1Error').text('postcode1 must range between ' + min + ' and ' + max);
	    	        $('#postcode1Error').css("display", "");
	    	        return false;
	    	    }
	    	    if (!checkRegexp.test(postalCode1)) {
	    	        $('#postcode1Error').text('Invalid Post Code');
	    	        $('#postcode1Error').css("display", "");
	    	        return false;
	    	    }
	    	    return true;
	    	}
		  
		    function iscompanynamevalid() {
		   		 $('#companynameError').css("display", "none");
		   	    var companyname = $("#companyName").val();
		     	var valid = true;
		   	    var min = 5;
		   	    var max = 20;
		   	    valid = checkLength($('#companyName'), "companyname", min, max);
		   	    if ($("#companyName").val() != "") {
		   	        if (!valid) {
		   	            $('#companynameError').text('companyname must range between ' + min + ' and ' + max);
		   	            $('#companynameError').css("display", "");
		   	            return false;
		   	        }
		   	     if (!isSpclCharValidCompany(companyname)) {
		             $('#companynameError').text('Following invalid characters @$#%?+*^[]{}|`~<>!= are not allowed');
		             $('#companynameError').css("display", "");
		             return false;
		         }
		   	    }
		   	    return true;
		   	}
		    function checkLength(o, n, min, max) {
		        if (o.val().length > max || o.val().length < min) {
		            return false;
		        } else {
		            return true;
		        }
		    }
</script>
<script type="text/javascript" src="ui/js/googleanalytics.js"></script>
</body>
</html>
