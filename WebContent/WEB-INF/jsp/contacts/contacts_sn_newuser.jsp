<%@ page import="com.incyyte.app.util.InCyyteUtil" %>
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

<script src="ui/js/jquery-1.7.1.js"></script>
<script type="text/javascript" src="ui/js/jquery-1.8.3.min.js"></script>
<script src="ui/js/customSelect.jquery.js" type="text/javascript"></script>

<script src="ui/js/jquery.color.js"></script>
<script src="ui/modal/colorbox/jquery.colorbox.js"></script>
<script src="ui/js/modernizr-1.7.min.js"></script>

<script type="text/javascript" src="ui/js/external/jquery.bgiframe-2.1.2.js"></script>
<script type="text/javascript" src="js/jquery-ui-1.8.13.custom.min.js"></script>
<script type="text/javascript" src="js/external/jquery.bgiframe-2.1.2.js"></script>
<script type="text/javascript" src="js/communicator.js"></script>
<script type="text/javascript" src="js/login.js"></script>
<script type='text/javascript' src='js/accountry.0.2/jquery.autocomplete.js'></script>
<script type='text/javascript' src='js/accountry.0.2/countries.en.js'></script>
<script type="text/javascript" src="js/jquery.form.js"></script>
<script src="ui/js/jquery.selectbox-0.2.js" type="text/javascript"></script>

<script>
    $(document).ready(function () {
        $(function () {
            $("select").selectbox();
        });
        
        if($("#country").val() == "GB")
			$('#postCodeDiv').show();
		else
			$('#postCodeDiv').hide();
    	
    	$('#country').change(function(){
			if($("#country").val() == "GB")
				$('#postCodeDiv').show();			
			else
				$('#postCodeDiv').hide();			
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
        
        $('#country').sortOptions();
        
        <c:set var="signUpForm" value="${snsignUpForm}"/>
        <c:if test="${signUpForm.country == null}" >
        	$('#country').append("<option selected value=''>Country</option>");
        </c:if>
        
       opener.location = 'authSuccess.do';
       window.close();
    });
</script>

<script>
    function validatePostcode() {
    	var valid = true;
    	if($("#country").val() == "GB"){
	    	$('#pcode').keyup(function(){
	    		this.value = this.value.toUpperCase();
	    	});
	        $("#postcoderr").css("display", "none");
	       
	        var min = 6;
	        var max = 8;
	        var pCode = $("#pcode").val();
	        valid = checkLength(pCode, min, max);
	       
	        if (valid == false) {
	            $("#postcoderr").text("Please enter a valid Postcode");
	            $("#postcoderr").css("display", "");
	            return valid;
	        }
	        if (!checkRegexp(pCode, /^([Gg][Ii][Rr] 0[Aa]{2})|((([A-Za-z][0-9]{1,2})|(([A-Za-z][A-Ha-hJ-Yj-y][0-9]{1,2})|(([A-Za-z][0-9][A-Za-z])|([A-Za-z][A-Ha-hJ-Yj-y][0-9]?[A-Za-z])))) {0,1}[0-9][A-Za-z]{2})$/)) {
	            $("#postcoderr").text("Please enter a valid Postcode");
	            $("#postcoderr").css("display", "");
	            return valid;
	        }
	        
	        if(pCode.indexOf(" ") < 0){
	            $("#postcoderr").text("Please add a space to your postcode e.g. AB1 2CD");
	            $("#postcoderr").css("display", "");
	            return valid;
	        }
    	}
    	return valid;
    }

    function checkLength(o, min, max) {
        if (o.length > max || o.length < min) {
            return false;
        } else {
            return true;
        }
    }
    function checkRegexp(o, regexp) {
        if (!( regexp.test(o) )) {
            return false;
        } else {
            return true;
        }
    }
    function updateStatus() {
        var welcomeURL = document.getElementById("welcomeURL").value;
        var email = $("#email").val();
        var firsname = $("#firstName").val();
        var lastname = $("#lastName").val();
        var country = $("#country").val();
        var cty = $("#location").val();
        var pcode = $("#pcode").val();
        var gen = $("#su_gender").val();
        var age = $("#birthYear").val();
        var loginname = $("#loginName").val();
        var profileurl = $("#ProfilePicture").val();
        $("#su_email").val(email);
        $("#ac_country").val(country);
        $("#su_postcode").val(pcode);
        $("#su_postalarea").val(cty);
        $("#su_firstname").val(firsname);
        $("#su_lastname").val(lastname);        
        $("#su_username").val(loginname);
        $("#cdnFileName").val(profileurl);
        $("#loginEmailError").css("display", "none");
        $("#postcoderr").css("display", "none");
        
        if (validatePostcode() == false)
        	 return false;
       
        if (email == '' || country == '' || gen == '' || age == '' || loginname == '') {
            alert("Please fill in mandatory fields to proceed");
            return false;
        }
        if (email != '') {
            var reg = /^\w+([-+.']\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/
            if (reg.test(email)) {
            } else {
                $("#loginEmailError").text("Please provide a valid email address");
                $("#loginEmailError").css("display", "");
                return false;
            }
        }

        var msg = "I've just signed onto inCyyte for questions & opinions from my friends, workmates & communities. Join me - " + welcomeURL;
        if (msg == null || msg.length == 0) {
            btn.disabled = false;
            return false;
        }
        msg = "statusMessage=" + msg;
        var req = new XMLHttpRequest();
        req.open("POST", "<%=request.getContextPath()%>/updateStatus.do");
        req.setRequestHeader("Accept", "text/xml");
        req.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
        req.setRequestHeader("Content-length", msg.length);
        req.setRequestHeader("Connection", "close");
        req.onreadystatechange = function () {
            if (req.readyState == 4) {
                if (req.responseText.length > 0) {
                    btn.disabled = false;
                }
            }
        };

        req.send(msg);
        $("#snsignUpForm").ajaxSubmit(
                {
                    type:'POST',
                    url:'snSignup.cyt',
                    success:function (data) {
                    	if(data == "username already exists"){
                    		 $("#lgnNameError").text("error-User name is NOT available");
                             $("#lgnNameError").css("display", "");
                    	}else{
                        window.location = "dash.cyt";}
                    },
                    error:function (jqXHR, textStatus, errorThrown) {
                    }
                });
    }

    function validateLoginName() {
        $("#lgnNameError").css("display", "none");
        if (($("#loginName").val().search(/[^a-zA-Z]+/) === -1)) {
        }
        else{
        	var iChars = "!@#$%^&*()+=_`~-[]\\\';,./{}|\":<>?";
            for (var i = 0; i < $("#loginName").val().length; i++) {
                if (iChars.indexOf($("#loginName").val().charAt(i)) != '-1') {
                	 $("#lgnNameError").text(" An invalid character has been entered  !@#$%^&*()+=_`~-[]';,./{ }|\":<>?\\ are not allowed");
                     $("#lgnNameError").css("display", "");}
                }
            }
           
        if ($("#loginName").val().length < 3 || $("#loginName").val().length > 20) {
            $("#lgnNameError").text("Username must range between 3 and 20");
            $("#lgnNameError").css("display", "");
        }
    }

    function validateFirstName() {
        $("#firstNameError").css("display", "none");
        if (!($("#firstName").val().search(/[^a-zA-Z]+/) === -1)) {
            $("#firstNameError").text("Only alpha characters are valid in this field");
            $("#firstNameError").css("display", "");
        }
    }

    function validateLastName() {
        $("#lastNameError").css("display", "none");
        if (!($("#lastName").val().search(/[^a-zA-Z]+/) === -1)) {
            $("#lastNameError").text(" Only alpha characters are valid in this field");
            $("#lastNameError").css("display", "");
        }
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
    });
</script>
<!--- placeholder Ends----->
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

<body>
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

                        <p class="heading3" style="line-height:30px;padding-left:0;">Please tell us a little bit about
                            you...</p>

                        <p class="page_heading2">You can always stay anonymous on inCyyte.</p>
                        <p class="page_heading2">Choose a unique username and tell us your location so <br>
                            we can add you the inCyyte Community!</p>
                        <br>
                    </div>
                    <div class="line"><span></span></div>
                    <div class="grid_17">
                        <!--- Edit Profile Starts---->
                        <div id="edit_profile_setup">
                            <form:form id="snsignUpForm" commandName="snsignUpForm" method="post">
                                <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
                                <%@ page isELIgnored="false" %>
                                <form:hidden path="ac_country" id="su_country"/>
                                <form:hidden path="postcode" id="su_postcode"/>
                                <form:hidden path="postal_area" id="su_postalarea"/>

                                <form:hidden path="su_email" id="su_email"/>
                                <form:hidden path="firstname" id="su_firstname"/>
                                <form:hidden path="lastname" id="su_lastname"/>
                                <form:hidden path="ac_country" id="su_cty"/>
                                <form:hidden path="nickname" id="su_nickname"/>
                                <form:hidden path="username" id="su_username"/>
                                <form:hidden path="su_password" id="su_pwd"/>
                                <form:hidden path="sn_mode" id="su_sn_mode"/>
                                <form:hidden path="cdnFileName" id="cdnFileName"/>
                            <div id="profile_name">
                                <table width="100%" border="0" cellspacing="0" cellpadding="0">
                                    <tr>
                                        <td valign="top" class="heading4">Name</td>
                                    </tr>
                                    <tr>
                                        <td valign="top"><label>User Name * <span style="font-size:14px;">This will be shown on your profile</span></label>
                                        	<c:choose>
                                        		<c:when test="${not empty profile.displayName }">
                                        			<input type="text" name="loginName" id="loginName" onkeyup="validateLoginName()" readonly="true" class="readonly_input" value="<c:out value="${profile.displayName}"/>"/>
                                        		</c:when>
                                        		<c:otherwise>
                                        			<input type="text" name="loginName" id="loginName" onkeyup="validateLoginName()" />
                                        		</c:otherwise>
                                        	</c:choose>
                                            <br>
                                            <span id="lgnNameError" style="padding-left: 12px; display:none; color:#C2002D;"></span>
                                            <br>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td><label>First Name </label>
                                            <input type="text" name="firstName" onKeyup="validateFirstName()" id="firstName" value="<c:out value="${profile.firstName}"/>"/>
                                            <br>
                                            <span id="firstNameError" style="padding-left: 12px; display:none; color:#C2002D;"></span>
                                            <br>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td><label>Last Name </label>
                                            <input type="text" name="lastName" id="lastName" onKeyup="validateLastName()" value="<c:out value="${profile.lastName}"/>"/>
                                            <br>
                                            <span id="lastNameError" style="padding-left: 12px; display:none; color:#C2002D;"></span>
                                            <br>
                                        </td>
                                    </tr>
                                </table>
                            </div>
                            <div id="more_abt_me">
                                <h3 class="heading4">More About Me</h3>
                                <p>This is private but it helps us give you a better experience while you are using inCyyte.</p>
                                <table width="100%" border="0" cellspacing="0" cellpadding="0">
                                    <tr>
                                        <td><label>Country *</label>
                                            <!-- <input type="text" id="country" value="United Kingdom" readonly="true"
                                                   class="readonly_input" style="border:0;"> -->
                                            <form:select path="country" id="country">
												<c:forEach var="item" items="${countries}">
			                    					<form:option value="${item.key}"  label="${item.value}"/>
								                </c:forEach>										
											</form:select>
                                        </td>
                                    </tr>
                                     <tr>
                                    	 <td>&nbsp;</td>
                                    </tr>
                                    <tr>
                                    	<td>
                                    		<label>Location/City</label>
                                    	</td>
                                    </tr>
                                    <tr>
                                        <td align="left">
                                            <input type="text" name="location" id="location"
                                                   value="<c:out value="${profile.location}"/>"/>
                                        </td>
                                    </tr>
                                     <tr>
                                    	 <td>&nbsp;</td>
                                    </tr>
                                    <tr id="postCodeDiv" >
                                        <td><label>Post Code *</label>
                                            <input type="text" id="pcode" onkeyup="validatePostcode()">
                                            <p>The first part of your post code will be used to help us provide you with more relevant polls.</p>
                                            <p><span id="postcoderr" style="display:none;font-size:15px;color:red;"></span></p>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td align="left"><label>Gender *</label></td>
                                    </tr>
                                    <tr>
                                        <td align="left">
                                               <form:select path="gender" id="su_gender">
                                                   <form:option selected="selected" value="" label="Gender"/>
                                                   <form:option value="Male" label="Male"/>
                                                   <form:option value="Female" label="Female"/>
                                               </form:select>
                                               <span id="gender_error" class="error" style="font-size: small;color:red;"></span>
                                        </td>
                                    </tr>
                                    <tr>
                                    	 <td>&nbsp;</td>
                                    </tr>
                                    
                                    <tr>
                                        <td><label>Year Of Birth *</label>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td align="left">
	                                        <form:select path="birthYear" id="birthYear" >
												<form:option selected="selected" value="" label="Year Of Birth"/>
												<c:forEach var="c" begin="1900" step="1" end="${birthYearLimit}">
			                    					<form:option value="${c}"><c:out value="${c}" /></form:option>	
			                    				</c:forEach>                    												
											</form:select>
                                    <%-- <form:select path="ageGroup" id="su_ageGroup" >
                                        <form:option selected="selected" value="" label="Age Group"/>
                                        <form:option value="08-12" label="08-12"/>
                                        <form:option value="13-18" label="13-18"/>
                                        <form:option value="19-25" label="19-25"/>
                                        <form:option value="26-30" label="26-30"/>
                                        <form:option value="31-40" label="31-40"/>
                                        <form:option value="41-50" label="41-50"/>
                                        <form:option value="51-Over" label="51-Over"/>
                                    </form:select> --%>
                                   <!--  <span id="ageGroup_error" class="error" style="font-size: small;color: red;"></span> -->
                                        </td>
                                    </tr>
                                    <tr>
                                    	 <td>&nbsp;</td>
                                    </tr>
                                    <tr>
                                        <td><label>Email *</label>
                                            <c:choose>
                                                <c:when test="${empty profile.email}">
                                                    <input type="text" name="email" id="email"
                                                           value="<c:out value="${profile.email}"/>"/>
                                                    <p><span id="loginEmailError" style="display:none;font-size:15px;color:red;"></span></p>
                                                </c:when>
                                                <c:otherwise>
                                                    <input type="text" name="email" id="email"  readonly="true" class="readonly_input" style="border:0;"
                                                           value="<c:out value="${profile.email}"/>"/>
                                                </c:otherwise>
                                            </c:choose>
                                        </td>
                                    </tr>
                                    <input type="hidden" name="signupmode" id="signupmode"
                                           value="<c:out value="${profile.providerId}"/>"/>
                                    <input type="hidden" name="ProfilePicture" id="ProfilePicture"
                                           value="<c:out value="${profile.profileImageURL}"/>"/>
                                </table>
                            </div>
                            </form:form>
                        </div>
                        <!--- Edit Profile Ends---->
                        <%
                            String webURL = InCyyteUtil.getWebURL();
                            String welcomeURL = webURL + "welcome.cyt";
                        %>
                        <input type="hidden" id="welcomeURL" value="<%=welcomeURL%>"/>
                        <a href="javascript:updateStatus();" title="Click to Confirm" id="btnUpdateStatus"
                           class="button_green1" style="margin-top:28px; width:200px; float:right;"> <span
                                class="title_green1">Click to Confirm</span></a></div>
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