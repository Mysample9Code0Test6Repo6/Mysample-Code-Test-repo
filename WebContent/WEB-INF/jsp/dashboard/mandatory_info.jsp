<!DOCTYPE html>
<%@ include file="/WEB-INF/jsp/include.jsp" %>

<html lang="en">
<head>
<meta http-equiv="content-type" content="text/html; charset=UTF-8">
<link rel="shortcut icon" href="favicon.ico"/>

<title>InCyyte NewUser Signup</title>
<meta charset="utf-8">

<link rel="stylesheet" href="ui/css/reset.css">
<link rel="stylesheet" href="ui/css/layout.css">
<link rel="stylesheet" href="ui/css/buttons.css" media="screen">
<link rel="stylesheet" href="ui/css/style_login.css">
<link rel="stylesheet" href="ui/css/accordionmenu.css" type="text/css">
<link rel="stylesheet" href="ui/css/icons_sprites.css">
<link rel="stylesheet" href="fonts/fonts_stylesheet.css">
<link rel="stylesheet" href="ui/css/style_help.css">
<link rel="stylesheet" href="ui/modal/colorbox.css">
<link rel="stylesheet" href="ui/css/ratingbar.css" type="text/css"/>
<link rel="stylesheet" href="ui/css/form_elements.css" type="text/css"/>
<link rel="stylesheet" href="ui/css/jquery.selectbox.css" type="text/css">

<link rel="stylesheet" type="text/css" href="css/accountry.0.2/jquery.autocomplete.css" />
<link rel="stylesheet" type="text/css" href="css/accountry.0.2/jquery.autocomplete.country.css" />
<link rel="stylesheet" href="ui/css/themes/base/jquery.ui.all.css">

<script src="ui/js/jquery-1.7.1.js"></script>
<script type="text/javascript" src="ui/js/jquery-1.8.3.min.js"></script>
<script src="ui/js/customSelect.jquery.js" type="text/javascript"></script>

<script src="ui/js/jquery.color.js"></script>
<script src="ui/modal/colorbox/jquery.colorbox.js"></script>
<script src="ui/js/modernizr-1.7.min.js"></script>

<script type="text/javascript" src="js/jquery-ui-1.8.13.custom.min.js"></script>
<script type="text/javascript" src="js/external/jquery.bgiframe-2.1.2.js"></script>
<script type="text/javascript" src="js/communicator.js"></script>
<script type="text/javascript" src="js/login.js"></script>
<script type='text/javascript' src='js/accountry.0.2/jquery.autocomplete.js'></script>
<script type='text/javascript' src='js/accountry.0.2/countries.en.js'></script>
<script type="text/javascript" src="js/jquery.form.js"></script>
<script src="ui/js/jquery.selectbox-0.2.js" type="text/javascript"></script>
<script type="text/javascript" src="ui/js/validate/jquery.validate.js" ></script>

<script>
function submitMandateInfo() {
    $("#mandateDataError").css("display", "none");
    validationMandatory("Uname");
    validateSel();
    if($("#snsignUpForm").valid() && validateSel() && validationMandatory("Uname")){
        if(UnameFlag)
            submitMandatoryInfo();
    }
}
// placeholder polyfill
    $(document).ready(function () {
    	 $(function () {
             $("select").selectbox();
         }); 
    	 cleanUname();
    	 $("#su_username").change(function() {    		
	    	if(validationMandatory("Uname")){
	    		processManData("Uname",$("#su_username").val(), getContextPath());
	    	}
		 });
    	 
    	 if($("#su_country").val() == "GB")
 			$('#postCodeDiv').show();
 		else
 			$('#postCodeDiv').hide();
     	
     	$('#country').keyup(function(){     		
 			if($("#country").val() == "") {
                 $('#postCodeDiv').hide();
             }
 			$("#snsignUpForm").valid();
 		}); 
     		
		$('#birthYear').keyup(function(){
			$("#birthYear_error").text("");
			$("#birthYear_error").hide();
		});
		
		$('#su_gender').change(function(){
			$("#gender_error").text("");
			$("#gender_error").hide();
		});
		
        $('#su_postcode').keyup(function(){
			this.value = this.value.toUpperCase();
		});
        
		$.validator.addMethod(
			"pc_space", 
			function(value, element) { 
				if($("#su_country").val() == "GB")
					return value.indexOf(" ") > 0; 
				else
					return true;
			},
			"Please add a space to your postcode e.g. AB1 2CD"						
		);
		
		$.validator.addMethod(
			"postcodeUK", 
			function(value, element) { 
			    var postcodeFilter = /^([Gg][Ii][Rr] 0[Aa]{2})|((([A-Za-z][0-9]{1,2})|(([A-Za-z][A-Ha-hJ-Yj-y][0-9]{1,2})|(([A-Za-z][0-9][A-Za-z])|([A-Za-z][A-Ha-hJ-Yj-y][0-9]?[A-Za-z])))) {0,1}[0-9][A-Za-z]{2})$/;
			    if($("#su_country").val() == "GB")
			    	return this.optional(element) || postcodeFilter.test(value);
			    else
					return true;
			}, 
			"Please enter a valid Postcode"
		);
		
		$.validator.addMethod("minGlobalZip", function(value, element) {
		    var isGB = $("#su_country").val() === "GB";
		    if ((isGB && value.length < 6 )) {
		        return false;
		    } else return true;
		}, "Post Code is not long enough");
		
		$.validator.addMethod("maxGlobalZip", function(value, element) {
		    var isGB = $("#su_country").val() === "GB";
		    if ( isGB && value.length > 10 )  {
		        return false;
		    } else return true;
		}, "Post Code is too long");
		$.validator.addMethod("reqGlobalZip", function(value, element) {
		    var isGB = $("#su_country").val() === "GB";
		    if ( isGB && value == "" )  {
		        return false;
		    } else return true;
		}, "Post Code is required");
		
		// validate login form on keyup and submit
		var details_validator = $("#snsignUpForm").validate({
			rules: {
				postalCodeArea: {	
					reqGlobalZip: true,
					minGlobalZip: true,
					maxGlobalZip: true,
					postcodeUK: true,
					pc_space: true	    						
				}
			},
			messages: {
				postalCodeArea: {	    						
				}
			}
		}); 	
		
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
        }
        
        $.fn.sortOptions = function(){
            $(this).each(function(){
                var op = $(this).children("option");
                op.sort(function(a, b) {
                    return a.text > b.text ? 1 : -1;
                });
                return $(this).empty().append(op);
            });
        }
        
       /*  $('#country').sortOptions();
        
        <c:set var="detail" value="${detailsform}"/>
        <c:if test="${detail.countryCode == null}" >
        	$('#country').append("<option selected value=''>Country</option>");
        </c:if>   */
        
        //$('#su_occupation').sortOptions();
        
        $.ajax({
            type:"GET",
            url:"occupations.json",
            dataType:"json",
            async:false,
            success:function (data) {
                contactJSONData = data.occupations;
            }
        });
        contactJSONData = JSON.stringify(contactJSONData);
        contactJSONData = jQuery.parseJSON(contactJSONData);

        var acOptions = {
            minChars:2,
            matchContains:true,
            autoFill:true,
            autoFocus:true,
            mustMatch:true,
            formatItem:function (item) {
                return item.occupation;
            }
        };

        $('#occupation').autocomplete(contactJSONData, acOptions);
        $('#occupation').result(
            function (event, data, formatted) {
                if (data) {
                    $("#su_occupation").val(data.id);
                } 
        });
        
        $.ajax({
            type:"GET",
            url:"incomes.json",
            dataType:"json",
            async:false,
            success:function (data) {
                contactJSONData = data.incomes;
            }
        });
        contactJSONData = JSON.stringify(contactJSONData);
        contactJSONData = jQuery.parseJSON(contactJSONData);

        var acOptions = {
            minChars:1,
            matchContains:true,
            autoFill:true,
            autoFocus:true,
            mustMatch:true,
            formatItem:function (item) {
                return item.income;
            }
        };

        $('#income').autocomplete(contactJSONData, acOptions);
        $('#income').result(
            function (event, data, formatted) {
                if (data) {
                    $("#su_income").val(data.id);
                } 
        });
        
        /* $.ajax({
            type:"GET",
            url:"birthyears.json",
            dataType:"json",
            async:false,
            success:function (data) {
                contactJSONData = data.birthyears;
            }
        });
        contactJSONData = JSON.stringify(contactJSONData);
        contactJSONData = jQuery.parseJSON(contactJSONData);

        var acOptions = {
            minChars:1,
            matchContains:true,
           	autoFill:true,
            autoFocus:true,
            mustMatch:true,
            formatItem:function (item) {            	
                return item.birthYear;
            }
        };

       $('#birthYear').autocomplete(contactJSONData, acOptions);

        $('#birthYear').result(
            function (event, data, formatted) {
                if (data) {
                    $("#su_birthYear").val(data.id);
                } 
        }); */
        
        $.ajax({
            type:"GET",
            url:"countries.json",
            dataType:"json",
            async:false,
            success:function (data) {
                contactJSONData = data.countries;
            }
        });
        contactJSONData = JSON.stringify(contactJSONData);
        contactJSONData = jQuery.parseJSON(contactJSONData);

        var acOptions = {
            minChars:2,
            matchContains:true,
            autoFill:true,
            autoFocus:true,
            mustMatch:true,
            formatItem:function (item) {
                return item.country;
            }
        };

        $('#country').autocomplete(contactJSONData, acOptions);
        $('#country').result(
            function (event, data, formatted) {
                if (data) {                	
                    $("#su_country").val(data.id);
                    
                    if($("#su_country").val() == "GB")
         				$('#postCodeDiv').show();			
         			else
         				$('#postCodeDiv').hide();
                    
                    $("#country_error").text("");
        			$("#country_error").hide();
         			$("#snsignUpForm").valid();
                } 
        });
        
        jQuery(".content").hide();
        //toggle the componenet with class msg_body
        jQuery(".heading4").click(function() {
       	 	jQuery(this).next(".content").slideToggle(500);
       	 	
       	 	if ( $("#arrow_down").attr('src') == 'images/dropdown_arrow_down.png') 
    	 		$("#arrow_down").attr("src", "images/dropdown_arrow.png");
    	 	else
    			$("#arrow_down").attr("src", "images/dropdown_arrow_down.png");
        });
    });
</script>
<script>
	var UnameFlag = true;

	function getContextPath() {
		return "<%=request.getContextPath()%>";
	}
	function cleanUname() {
	    $("#defaultUnameNote").css("display", "none");
	    $("#invalidUname").css("display", "none");
	    $("#validUname").css("display", "none");
	    $("#UnameLoader").css("display", "none");
	}
    function validateSel(){
		if($("#birthYear").val() == "" ){
			$("#birthYear_error").text("Year of birth is required");
			$("#birthYear_error").show();
            $("#mandateDataError").text("Year of birth is required");
            $("#mandateDataError").css("display", "");
            return false;
		}
		else if(!validateBirthYear()){
			$("#birthYear_error").text("Please enter a valid year of birth");
			$("#birthYear_error").show();
            $("#mandateDataError").text("Please enter a valid year of birth");
            $("#mandateDataError").css("display", "");
            return false;
		}
		else{
			$("#birthYear_error").text("");
			$("#birthYear_error").hide();
		}
		
		if($("#su_gender").val() == ""){
			$("#gender_error").text("Please select gender");
			$("#gender_error").show();
            $("#mandateDataError").text("Please select gender");
            $("#mandateDataError").css("display", "");
            return false;
		}else{
			$("#gender_error").text("");
			$("#gender_error").hide();
		}
		
		if($("#country").val() == "" || $("#su_country").val() == "" ){
			$("#country_error").text("Please select country");
			$("#country_error").show();
            $("#mandateDataError").text("Please select country");
            $("#mandateDataError").css("display", "");
            return false;
		}else{
			$("#country_error").text("");
			$("#country_error").hide();
		}		
		return true;
	}
    function checkLength(o, min, max) {
        if (o.length > max || o.length < min) {
            return false;
        } else {
            return true;
        }
    }
    function checkRegexp(o, regexp) {
        return regexp.test(o);
    }
   	
    function validateLoginName() {
        $("#lgnNameError").css("display", "none");
        if (($("#su_username").val().search(/[^a-zA-Z]+/) === -1)) {
        }
        else{
        	var iChars = "!@#$%^&*()+=_`~- []\\\';,./{}|\":<>?";
            for (var i = 0; i < $("#su_username").val().length; i++) {
                if (iChars.indexOf($("#su_username").val().charAt(i)) != '-1') {
                	 $("#lgnNameError").text(" An invalid character has been entered  !@#$%^&*()+=_`~-[]';,./{ }|\":<>?\\ are not allowed");
                     $("#lgnNameError").css("display", "");}
                }
            }
           
        if ($("#su_username").val().length < 3 || $("#su_username").val().length > 20) {
            $("#lgnNameError").text("Username must range between 3 and 20");
            $("#lgnNameError").css("display", "");
        }
    }
    function validatePassword(){    	
        var sPass = $("#su_password").val().length;
        if ($.trim(sPass) == 0) {
            $("#password_invalid").text("Password is required");
            $("#password_invalid").css("display", "");
            $("#mandateDataError").text("Password is required");
            $("#mandateDataError").css("display", "");
            return false;
        }
        if((sPass < 7) || (sPass > 20)) {
            $("#password_invalid").text("Password must be between 7 to 20 characters");
            $("#password_invalid").css("display", "");
            $("#mandateDataError").text("Password must be between 7 to 20 characters");
            $("#mandateDataError").css("display", "");
            return false;
        }
        else {
            $("#password_invalid").css("display", "none");
            return true;
        }
    }

    function validateFirstName() {
        $("#firstNameError").css("display", "none");
        if (!($("#su_firstname").val().search(/[^a-zA-Z- ]+/) === -1)) {
            $("#firstNameError").text("Only alpha characters are valid in this field");
            $("#firstNameError").css("display", "");
        }
    }

    function validateLastName() {
        $("#lastNameError").css("display", "none");
        if (!($("#su_lastname").val().search(/[^a-zA-Z- ]+/) === -1)) {
            $("#lastNameError").text(" Only alpha characters are valid in this field");
            $("#lastNameError").css("display", "");
        }
    }
    
    function validateBirthYear(){
    	 var birthYearLimit = $("#birthYearLimit").val();    	 
    	 var birthYear =$("#birthYear").val();
        if(birthYear < 1905 || birthYear > birthYearLimit) {
            return false;
        }
        if(!checkLength($("#birthYear").val(), 4, 4)) {
             return false;
         }
    	return true;
    }
    
    function submitMandatoryInfo(){
		$("#snsignUpForm").ajaxSubmit({	
			type: 'POST',
			url: 'submitMandatoryInfo.cyt',
			success: function(data) {
				if (data == "success") {
					window.location = "dash.cyt";
				}else if (data == "SNsuccess"){
					window.location = "dash.cyt?sn";
                }else {
                    $("#mandateDataError").text("Error: " + data);
                    $("#mandateDataError").css("display", "");
                }
			},
			error: function(jqXHR, textStatus, errorThrown) {
				$('#mandatoryErr').show();
			}
		});
	}
    
  //iterates over form elements making sure input validation is good.
    function validationMandatory(UID_Name) {
        $("#" + UID_Name + "Loader").css("display", "");
        // perform each of the different key types of input validation only, no
        // business rules outside of input validation should be done client side.
        if (!isRequiredManFields(UID_Name)) {
            return false;
        }

        if (UID_Name == 'Uname') {
            if(!isManUsernameValid(UID_Name)){ 
            	return false;
            }
        }
        $("#" + UID_Name + "Loader").css("display", "none");
        return true;
    }
  
    function isRequiredManFields(UID_Name) {    	
        if (UID_Name == 'Uname') {
            return validManReqFields('su_username', UID_Name);
        } 
        return true;
    }   // function

    function validManReqFields(fieldName, UID_Name) {
        //clear all previous errors
        if ($('#' + fieldName).val() == "") {        	
            if (UID_Name == 'Uname') {
                cleanUname();
                displayErrorMsg(fieldName, UID_Name, 'Username is required');
            }
            $("#mandateDataError").text("Username is required");
            $("#mandateDataError").css("display", "");
            return false;
        }
        return true;
    }

    function isManUsernameValid(UID_Name) {
        var min = 3;
        var max = 20;
        var username = $("#su_username").val();
        if (!checkLength(username, min, max)) {
        	cleanUname();
            displayErrorMsg('#su_username', UID_Name, 'Username must range between ' + min + ' and ' + max);
            $("#mandateDataError").text('Username must range between ' + min + ' and ' + max);
            $("#mandateDataError").css("display", "");
            return false;
        }
        if (!isManSpclCharUserName(username)) {
            $("#validUname").css("display", "none");
            $("#defaultUnameNote").css("display", "none");
            cleanUname();
            displayErrorMsg('#su_username', UID_Name, "Special Characters are not allowed");
            $("#mandateDataError").text("Special Characters are not allowed in Username");
            $("#mandateDataError").css("display", "");
            return false;
        }
        return true;
    }  
    
    function isManSpclCharUserName(username) {
        var iChars = "!@#$%^&*() +=_`~-[]\\\';,./{}|\":<>?";
        for (var i = 0; i < username.length; i++) {
            if (iChars.indexOf(username.charAt(i)) != '-1') {
                return false;
            }
        }
        return true;
    }
    
    function displayErrorMsg(fieldName, UID_Name, msg) {
        $("#" + UID_Name + "Loader").css("display", "none");
        $("#invalid" + UID_Name).css("display", "");
        $("#invalid" + UID_Name).text(msg);
    }
    function processManData(UID_Name, value, url) {
    	 cleanUname();
        $("#" + UID_Name + "Loader").css("display", "");
        $("#default" + UID_Name + "Note").css("display", "none");

        var serverDomain = url;

        $.ajax({
            type:"POST",
            url:serverDomain + "/process" + UID_Name + ".cyt",
            data:"action=" + value,
            dataType:"text",
            success:function (data) {
                if (data.search("success") == 0) {
                    $("#" + UID_Name + "Loader").css("display", "none");
                    $("#valid" + UID_Name).css("display", "");
                    $("#valid" + UID_Name).text(data);
                    //set flags
                    if (UID_Name == 'Uname')UnameFlag = true;                    
                } else if (data.search("error") == 0) {
                    $("#" + UID_Name + "Loader").css("display", "none");
                    $("#invalid" + UID_Name).css("display", "");
                    $("#invalid" + UID_Name).text(data);
                    //set flags
                    if (UID_Name == 'Uname') {
                        UnameFlag = false;
                    }
                }
            }
        });
    }

</script>
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

        if($('#su_sn_mode').val() == "facebook"){
        	opener.location='authSuccess.do';
            window.close();
        }
    });
</script>
<!--- placeholder Ends----->

<!--[if lt IE 9]>
<script src="ui/js/html5.js"></script>
<![endif]-->
<!--[if lt IE 9]>
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7">
<link href="ui/css/iestyle.css" media="screen" rel="stylesheet" type="text/css"/>
<![endif]-->

<!--[if gte IE 9]>
<link href="ui/css/gte_ie9.css" media="screen" rel="stylesheet" type="text/css"/>
<![endif]-->
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
        <!-- include header -->
        <!--header -->
        <jsp:include page="../main/includes/emptyHeader.jsp"/>
        <!--header end-->
        <div class="main">
            <!--content -->
            <article id="content">
                <div id="main_content">
                    <div class="grid_9">
                        <p class="page_heading_welcome">Welcome</p>

                        <p class="heading3" style="line-height:30px;padding-left:0;">Lets see what polls are linked<br>to you..
                            </p>

                       <p class="page_heading2">You can always stay<br>
                            anonymous on inCyyte.</p>
                            <p class="page_heading2">You'll only be asked to<br>
                            vote on things directly<br>
                            related to you and your<br>
                            neighbourhood.<br>
                    </div>
                    <div class="line"><span></span></div>
                    <div class="grid_17">
                        <!--- Edit Profile Starts---->
                        <div id="edit_profile_setup">
                            <form:form id="snsignUpForm" commandName="detailsform" method="post">
                                <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
                                <%@ page isELIgnored="false" %>
                                <form:hidden path="countryCode" id="su_country" />
                                <form:hidden path="occupation" id="su_occupation" />
                                <input type="hidden" name="birthYearLimit" id="birthYearLimit" value="${birthYearLimit}"/>
                                <form:hidden path="income"  id="su_income" />
                                <form:hidden path="su_password" id="su_pwd"/>
                                <form:hidden path="sn_mode" id="su_sn_mode"/>    
                                <form:hidden path="cdnFileName" id="cdnFileName"/>  
                            <div id="profile_name">
                            	<h3 class="heading4">One last thing . .</h3>
                                <span id="mandateDataError" class="common_text" style="padding-left: 12px; display:none; color:#C2002D;"></span>
                            	<div class="content1">
	                                <table width="100%" border="0" cellspacing="0" cellpadding="0">
	                                    <!-- <tr>
	                                        <td valign="top" >&nbsp;</td>
	                                    </tr> -->
                                        <c:choose>
                                        	<c:when test="${empty detailsform.username}">
                                        		<tr>
                                             		<td valign="top"><label>User Name <span style="color: red; font-weight: bold;">*</span> <span style="font-size:14px;">This will be shown on your profile</span></label>
                                           				<form:input path="username" id="su_username" placeholder="Username" onFocus="this.placeholder = ''" onBlur="this.placeholder = 'Username'"/>
                                                		<img src="ui/images/indicator-loader.gif" id="UnameLoader" width="32" height="32" style="padding:8px 0 0 0;">&nbsp;
										        		<span id="defaultUnameNote" style="padding-left:24px;color:#2E2E2E; font:15px 'bariol_regularregular', 'ie8_bariol_regular';">Don't worry, you can change it later.</span>&nbsp;
										        		<span id="invalidUname" class="invalid" style="padding-left:24px;font-size:15px;color:#C2002D ;">Username taken.</span>&nbsp;
										        		<span id="validUname" class="validMand" style="padding-left:24px;font-size:15px; color:#3a7d34;">Username available.</span>      
                                                	</td>
                                                	<br>
	                                           		 	<span id="lgnNameError" style="padding-left: 12px; display:none; color:#C2002D;"></span>
	                                             	<br>
		                                    	</tr>
	                                  		</c:when>
	                                  		<c:otherwise>
	                                  			<form:hidden path="username" id="su_username"/>
	                                  		</c:otherwise>
                                		</c:choose>
                                		<%-- <c:choose>
                                        	<c:when test="${empty detailsform.su_password}">
                                        		<tr>
                                        			<td><br>
                                        				<label>Password <span style="color: red; font-weight: bold;">*</span></label>  
                                        				<form:input type="password"  id="su_password" path="su_password"  placeholder="Enter your password"  onFocus="this.placeholder = ''"	onBlur="this.placeholder = 'Enter your password'" onkeyup="validatePassword()" />
                                        				<span id="password_invalid" class="invalid"  style="padding-left:24px;display:none;font-size:15px;color:#C2002D ;"></span>
                                        			</td>
                                        		</tr>
                                        	</c:when>
                                        	<c:otherwise>
	                                  			<form:hidden path="su_password" id="su_password"/>
	                                  		</c:otherwise>
                                        </c:choose> --%>
                                		
	                                        	<form:input type="hidden" path="email" id="email" />
	                                   	<tr>
	                                        <td><br>
	                                        <label>Country <span style="color: red; font-weight: bold;">*</span></label>  
	                                        	<form:input path="country" id="country"/>
	                                        	<p>Click on your country from the list that appears as you type</p>
	                                           <%--  <form:select path="countryCode" id="country">
													<c:forEach var="item" items="${countries}">
				                    					<form:option value="${item.key}"  label="${item.value}"/>
									                </c:forEach>										
												</form:select> --%>
												<br>
												<span id="country_error" class="invalid"  style="padding-left:24px;display:none;font-size:15px;color:#C2002D ;"></span>
	                                        </td>
	                                    </tr>
	                                   <%--  <tr>
	                                    	<td>
	                                    		<label>Location/City</label>
	                                    	</td>
	                                    </tr>
	                                    <tr>
	                                        <td align="left">
	                                        	<form:input path="location" id="su_postalarea"/>
	                                            <input type="text" name="location" id="location"
	                                                   value="<c:out value="${profile.location}"/>"/>
	                                        </td>
	                                    </tr>
	                                     <tr>
	                                    	 <td>&nbsp;</td>
	                                    </tr> --%>
	                                    <tr id="postCodeDiv" >
	                                        <td><label>Post Code <span style="color: red; font-weight: bold;">*</span></label>
	                                        	<form:input path="postalCodeArea" id="su_postcode" />
	                                           <!--  <input type="text" id="pcode" onkeyup="validatePostcode()"> -->
	                                            <p>The first part of your post code will be used to help us provide you with more relevant polls.</p>
	                                            <p><span id="postcoderr" class="invalid"   style="padding-left:24px;display:none;font-size:15px;color:#C2002D ;"></span></p>
	                                        </td>
	                                    </tr>
	                                    
	                                    <c:choose>
	                                    <c:when test="${empty detailsform.gender}">
	                                    <tr>
	                                        <td align="left"><label><br>Gender <span style="color: red; font-weight: bold;">*</span></label></td>
	                                    </tr>
	                                    <tr>
	                                        <td align="left">
	                                               <form:select path="gender" id="su_gender">
	                                                   <form:option selected="selected" value="" label="Gender"/>
	                                                   <form:option value="Male" label="Male"/>
	                                                   <form:option value="Female" label="Female"/>
	                                               </form:select>
	                                               <span id="gender_error"  class="invalid"  style="padding-left:24px;display:none;font-size:15px;color:#C2002D ;" ></span>
	                                        </td>
	                                    </tr>
	                                    </c:when>
                                        </c:choose>
	                                    <c:choose>
	                                    <c:when test="${empty detailsform.birthYear}">
	                                    <tr>
	                                        <td><label><br>Just your birth year <span style="color: red; font-weight: bold;">*</span></label>
	                                        </td>
	                                    </tr>
	                                    <tr>
	                                        <td align="left">
	                                        	<form:input path="birthYear" placeholder = "e.g. 1991" id="birthYear" />
	                                        	<p>We need this to ensure you receive age appropriate polls</p>
		                                       <%--  <form:select path="birthYear" id="birthYear" >
													<form:option selected="selected" value="" label="Year Of Birth"/>
													<c:forEach var="c" begin="1900" step="1" end="${birthYearLimit}">
				                    					<form:option value="${c}"><c:out value="${c}" /></form:option>
				                    				</c:forEach>
												</form:select>      --%>

												<span id="birthYear_error" class="invalid"  style="padding-left:24px;display:none;font-size:15px;color:#C2002D ;" ></span>
	                                        </td>
	                                    </tr>
	                                    </c:when>
	                                    <c:otherwise>
	                                   		 <form:input type="hidden" path="birthYear" placeholder = "e.g. 1991" id="birthYear" value="${detailsform.birthYear}" />
	                                    </c:otherwise>
                                        </c:choose>
	                                </table>
	                       		</div>
                            </div>
                            <!--div id="more_abt_me">
                                <h3 class="heading4" style="cursor: pointer;"><img src="images/dropdown_arrow_down.png" id="arrow_down" />&nbsp;&nbsp;More About Me (Optional)</h3>                                
                                <div class="content">	    
                                	<p>This section is optional but it helps us give you a better experience while you are using inCyyte.</p>                           
	                                <table width="100%" border="0" cellspacing="0" cellpadding="0">
	                                    <tr>
	                                        <td><label>First Name </label>
	                                        	<form:input path="firstname" id="su_firstname" onKeyup="validateFirstName()" />
	                                           <%--  <input type="text" name="firstName" onKeyup="validateFirstName()" id="firstName" value="<c:out value="${profile.firstName}"/>"/> --%>
	                                            <br>
	                                            <span id="firstNameError" style="display:none;font-size:15px;color: #C2002D ;" ></span>
	                                            <br>
	                                        </td>
	                                    </tr>
	                                    <tr>
	                                        <td><label>Last Name </label>
	                                        	<form:input path="lastname"  id="su_lastname" onKeyup="validateLastName()" />
	                                            <%-- <input type="text" name="lastName" id="lastName" onKeyup="validateLastName()" value="<c:out value="${profile.lastName}"/>"/> --%>
	                                            <br>
	                                            <span id="lastNameError" style="display:none;font-size:15px;color: #C2002D ;"  ></span>
	                                            <br>
	                                        </td>
	                                    </tr>
	                                    <tr>
					                   		<td><label>Occupation</label>
					                   		</td>
					                   	</tr>
					                   	<tr>
	                                    	<td>
	                                    		<input name="occupation"  id="occupation" />
	                                    		<p>Please select your occupation from the list that appears as you type</p>
					                    		<%-- <form:select path="occupation" id="su_occupation" >				                    			
					                    			<c:forEach var="item" items="${occupations}">
				                    					<form:option value="${item.key}"  label="${item.value}"/>
									                </c:forEach>
					                    		</form:select> --%>
											</td>				                    
					                  	</tr>
					                  	<tr>
	                                    	 <td>&nbsp;</td>
	                                    </tr> 
					                  	<tr>
					                    	<td><label>Income</label>
					                    		<form:input path="IncomeVal"  id="income" />
					                    		<p>Please select your income from the list that appears as you type</p>
					                     		<%-- <form:select path="income"  id="su_income" >
					    							<form:options items="${incomes}" />
												</form:select> --%>
					                      	</td>				                    
					                  	</tr>
	                                   <tr>
	                                    	 <td>&nbsp;</td>
	                                   </tr> 
	                                   
	                                   <tr>
					                   		<td><label>Ethnicity</label>
					                   		</td>
					                   	</tr>
					                   	<tr>
	                                    	<td>                                    		
					                    		<form:select path="ethnicity" id="su_ethnicity" >				                    			
					                    			<c:forEach var="item" items="${ethnicities}">
				                    					<form:option value="${item.key}"  label="${item.value}"/>
									                </c:forEach>
					                    		</form:select>
					                    		<p>Please select your ethnicity from the list</p>
											</td>				                    
					                  	</tr>
					                  	<tr>
	                                    	 <td>&nbsp;</td>
	                                    </tr>                                     
	                                    
	                                    <tr>
					                   		<td><label>Educational level</label>
					                   		</td>
					                   	</tr>
					                   	<tr>
	                                    	<td>
					                    		<form:select path="educationLevel" id="su_educationLevel" >				                    			
					                    			<c:forEach var="item" items="${educationLevels}">
				                    					<form:option value="${item.key}"  label="${item.value}"/>
									                </c:forEach>
					                    		</form:select>
					                    		<p>Please select your education level from the list</p>
											</td>				                    
					                  	</tr>
					                  	<tr>
	                                    	 <td>&nbsp;</td>
	                                    </tr> 
	                                    
	                                    <tr>
					                   		<td><label>Adults in the house hold</label>
					                   		</td>
					                   	</tr>
					                   	<tr>
	                                    	<td>                                    		
	                                    		<form:select path="adultsInHouseHold" id="su_adultsInHouseHold" >				                    			
					                    			<c:forEach var="item" items="${adultsInHouseHolds}">
				                    					<form:option value="${item.key}"  label="${item.value}"/>
									                </c:forEach>
					                    		</form:select>
					                    		<p>Please select number of adults in the house hold from the list</p>
											</td>				                    
					                  	</tr>
					                  	<tr>
	                                    	 <td>&nbsp;</td>
	                                    </tr> 
	                                    
	                                    <tr>
					                   		<td><label>Children in the house hold</label>
					                   		</td>
					                   	</tr>
					                   	<tr>
	                                    	<td>                                    		
	                                    		<form:select path="childrenInHouseHold" id="su_childrenInHouseHold" >				                    			
					                    			<c:forEach var="item" items="${childrenInHouseHolds}">
				                    					<form:option value="${item.key}"  label="${item.value}"/>
									                </c:forEach>
					                    		</form:select>
					                    		<p>Please select number of children in the house hold from the list</p>
											</td>				                    
					                  	</tr>
					                  	<tr>
	                                    	 <td>&nbsp;</td>
	                                    </tr> 
	                                   
	                                </table>
	                       		</div>
                            </div-->
                            </form:form>
                        </div>
                        <!--- Edit Profile Ends---->
                        <a title="Click to Confirm" id="submit_detailsform_btn" href="javascript:submitMandateInfo();"
                           class="button_green1" style="margin-top:28px; width:200px; float:right;">
                            <span class="title_green1">Lets get started..</span></a></div>
                </div>
        </article>
        </div>
        <!--content ends -->
    </div>
<!-- include footer -->
<!--footer Starts -->
<jsp:include page="../common/includes/footer.jsp"/>
</div>
<!--footer ends -->
<script type="text/javascript" src="ui/js/googleanalytics.js"></script>
</body>
</html>
