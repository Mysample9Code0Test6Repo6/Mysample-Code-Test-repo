<%@ include file="/WEB-INF/jsp/include.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>InCyyte | Login</title>
    <style type="text/css">
    </style>
    <script type="text/javascript" src="js/jquery-1.5.1.min.js"></script>
    <script type="text/javascript" src="js/jquery-ui-1.8.13.custom.min.js"></script>
    <script type="text/javascript" src="js/external/jquery.bgiframe-2.1.2.js"></script>
    <script type="text/javascript" src="js/communicator.js"></script>
    <script type="text/javascript" src="js/login.js"></script>
    <script type='text/javascript' src='js/accountry.0.2/jquery.autocomplete.js'></script>
    <script type='text/javascript' src='js/accountry.0.2/countries.en.js'></script>
    <script type="text/javascript" src="js/jquery.form.js"></script>    
<head>
	<title>Register InCyyte</title>
	<style>
		.sectiontableheader {background-color:#C8D7E3;color:#293D6B;font-size:8pt;font-weight:bold;padding:2px;}
		.sectiontableentry2 {background:none repeat scroll 0 0 #F7F7F7;padding:2px;}
		.sectiontableentry1 {background:none repeat scroll 0 0 #FFFFF0;padding:2px;}
	</style>
	<script>
    	function updateStatus()
    	{
    		var email = $("#email").val();
			var firsname = $("#firstName").val();
			var lastname = $("#lastName").val();
			var country = $("#country").val();
			var nickname = $("#displayName").val();
			var cty = $("#postal_area").val();
			var pcode = $("#postcode").val();	
			var gen = $("#gender").val();
			var age = $("#dob").val();
			var fullname = $("#fullname").val();
			var loginname =  $("#loginName").val();
			var pwd =  $("#pwd").val();
			
			$("#su_nickname").val(nickname);
			$("#su_email").val(email);
			$("#ac_country").val(country);
			$("#su_postcode").val(pcode);
			$("#su_postalarea").val(cty);
			$("#su_firstname").val(firsname);
			$("#su_lastname").val(lastname);
			$("#su_ageGroup").val(age);
			$("#su_gender").val(gen);  
			$("#su_username").val(loginname);  			
			$("#su_pwd").val(pwd);  
    				 $("#snsignUpForm").ajaxSubmit(
    						
    						 {	
    						type: 'POST',
    						url: 'snSignup.cyt',
    						success: function(data) {		
    						
    						
    								window.location = "welcome.cyt";																					
    						},
    						error: function(jqXHR, textStatus, errorThrown) {
    							
    						
    							$("#communicator").css("display", "");
    							// setError("oldPwd", "You have entered an invalid password.");
    						}
    					});
    	}
	</script>
</head>
<body>
<form:form  id="snsignUpForm"  commandName="snsignUpForm" method="post">  	     
<%@page import="org.brickred.socialauth.Profile,java.util.*;" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isELIgnored="false"%>
<h2 align="center">Welcome <c:out value="${profile.firstName}"/> </h2>
<br/>

<br />


                                <form:hidden  path="ac_country" id="su_country" />
	                  			<form:hidden path="postcode" id="su_postcode" />
	                  			<form:hidden path="postal_area" id="su_postalarea" />
	                  			<form:hidden path="ageGroup" id="su_ageGroup" />
	                  		
	                  			<form:hidden path="su_email" id="su_email" />
	                  			

                                <form:hidden  path="firstname" id="su_firstname" />
	                  			<form:hidden path="lastname" id="su_lastname" />
	                  			<form:hidden path="ac_country" id="su_cty" />
	                  			<form:hidden path="gender" id="su_gender" />
	                  			<form:hidden  path="nickname" id="su_nickname" />
	                  			<form:hidden  path="username" id="su_username" />
                                <form:hidden  path="su_password" id="su_pwd" />

</form:form>




<h3 align="center">Please Confirm your details to us</h3>
<table cellspacing="2" cellspacing="6" border="0" bgcolor="e5e5e5" width="60%" align="center">
	
	<tr class="sectiontableentry1">
		<td>Email:</td>
		<td ><input type= "text" name="email" id="email"  value="<c:out value="${profile.email}"/>"/>
		     
	     </td>
	     
     
	     
	     
	     
	</tr>
	<tr class="sectiontableentry2">
		<td>First Name:</td>
	
		<td><input type= "text" name="firstName" id="firstName"  value="<c:out value="${profile.firstName}"/>"/>
	</tr>
	<tr class="sectiontableentry1">
		<td>Last Name:</td>
	
		<td><input type= "text" name="lastName" id="lastName"  value="<c:out value="${profile.lastName}"/>"/>
	</tr>
	<tr class="sectiontableentry2">
		<td>Country:</td>
	
		<td><input type= "text" name="country" id="country"  value="<c:out value="${profile.country}"/>"/>
	</tr>
	<tr class="sectiontableentry1">
		<td>Login Name:</td>
		
		<td><input type= "text" name="loginName" id="loginName"  value=""/>
	</tr>
	
	<tr class="sectiontableentry1">
		<td>Password:</td>
		
		<td><input type= "password" name="pwd" id="pwd"  value=""/>
	</tr>
	
	<tr class="sectiontableentry1">
		<td>Nick Name:</td>
		
		<td><input type= "text" name="displayName" id="displayName"  value="<c:out value="${profile.displayName}"/>"/>
	</tr>
	<tr class="sectiontableentry2">
		<td>DOB:</td>
		
		<td><input type= "text" name="dob" id="dob"  value="<c:out value="${profile.dob}"/>"/>
	</tr>
	<tr class="sectiontableentry1">
		<td>Gender:</td>
		<td><input type= "text" name="gender" id="gender"  value="<c:out value="${profile.gender}"/>"/>
	</tr>
	<tr class="sectiontableentry2">
		<td>Location:</td>
	
		<td><input type= "text" name="location" id="location"  value="<c:out value="${profile.location}"/>"/>
	</tr>
	<!--<tr class="sectiontableentry1">
		<td>Profile Image:</td>
		<td>
			<c:if test="${profile.profileImageURL != null}">
				<img src='<c:out value="${profile.profileImageURL}"/>'/>
			</c:if>
		</td>
	</tr>-->
	
</table>
<tr class="sectiontableheader">
</tr>

<table cellspacing="1" cellspacing="4" border="0" bgcolor="e5e5e5" align="center" width="60%">
	<tr class="sectiontableheader">
		
		<td></td>
		<td>
			<input type="button" value="Click to Confirm" onclick="updateStatus();" id="btnUpdateStatus"/>		
		</td>
	</tr>
	
	<c:forEach var="contact" items="${contacts}" varStatus="index">
		<tr class='<c:if test="${index.count % 2 == 0}">sectiontableentry2</c:if><c:if test="${index.count % 2 != 0}">sectiontableentry1</c:if>'>
			<td><c:out value="${contact.firstName}"/> <c:out value="${contact.lastName}"/></td>
			<td><c:out value="${contact.email}"/></td>
			<td><a href='<c:out value="${contact.profileUrl}"/>' target="_new"><c:out value="${contact.profileUrl}"/></a></td>
		</tr>
	</c:forEach>
</table>

</table>

<script type="text/javascript" src="ui/js/googleanalytics.js"></script>
</body>
</html>