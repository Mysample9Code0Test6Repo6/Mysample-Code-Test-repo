<%@ include file="/WEB-INF/jsp/include.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
   	<link rel="shortcut icon" href="favicon.ico" >
   	<link rel="icon" type="image/gif" href="animated_favicon1.gif" >
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>InCyyte Main Page</title>
    <style type="text/css">
    </style>
        
    <script type="text/javascript" src="js/jquery-1.5.1.min.js"></script>
    <script type="text/javascript" src="js/jquery-ui-1.8.13.custom.min.js"></script>
    <script type="text/javascript" src="js/external/jquery.bgiframe-2.1.2.js"></script>
    <script type="text/javascript" src="js/welcome.js"></script>
    <script type="text/javascript" src="js/communicator.js"></script>
    <script type="text/javascript" src="js/accountry.0.2/jquery.autocomplete.js"></script>
    <script type="text/javascript" src="js/accountry.0.2/countries.en.js"></script>
    <script type="text/javascript" src="js/jquery.form.js"></script>    

    <link rel="stylesheet" type="text/css" href="css/accountry.0.2/jquery.autocomplete.css" />
    <link rel="stylesheet" type="text/css" href="css/accountry.0.2/jquery.autocomplete.country.css" />
    <link rel="stylesheet" href="css/welcome.css" type="text/css" />
	<link rel="stylesheet" href="css/themes/ui-lightness/jquery.ui.all.css">
    <link rel="stylesheet" href="css/demos.css">
   
	<style>
		fieldset { padding:0; border:0; margin-top:25px; }
		h1 { font-size: 1.2em; margin: .6em 0; }
		div#users-contain { width: 350px; margin: 20px 0; }
		div#users-contain table { margin: 1em 0; border-collapse: collapse; width: 100%; }
		div#users-contain table td, div#users-contain table th { border: 1px solid #eee; padding: .6em 10px; text-align: left; }
		.ui-dialog .ui-state-error { padding: .3em; }
		.validateTips_df { border: 1px solid transparent; padding: 0.3em;}
		.validateTips_daf { border: 1px solid transparent; padding: 0.3em;}
		.validateTips_ldf { border: 1px solid transparent; padding: 0.3em;}
		.validateTips_adf { border: 1px solid transparent; padding: 0.3em;}
		.validateTips_fdf { border: 1px solid transparent; padding: 0.3em;}
		.style1 {font-size: 10px}
	    .style2 {
			font-size: 14px;
			color: #2B3C55;
			font-weight: bold;
		}
    	.style3 {
			font-family: Arial, Helvetica, sans-serif;
			font-size: 13px;
			line-height: 16px;
		}
	    .style4 {color: #335480}
	    .style6 {
			line-height: 16px;
			font-family: Arial, Helvetica, sans-serif;
			font-size: 10px;
		}
    </style>

</head>

<body>
    <div id="container">

        <div id="content">
        	<img src="images/main_logo.png" alt="image post" height="130" width="700" class="logo"/>
            <div id="borderLine"></div>
            <div id="headerText">The best place to get an accurate insight on the opinions of your friends, workmates & community.</div>

 			<div id="suggestionText"> <a href="#">What is inCyyte?</a> | <a href="#" title="Check you recent polls">View Results</a> | <a href="#" title="View how we protect you">Privacy Policy</a> | <a href="#" title="Our promise to you">Anonymity Rule</a> | <a href="#" title="Abuse protection and control">Red Card System</a> | <a href="#" title="How to get in touch">Contact Us</a></div>
          	
          	<!-- Panel. -->
			<div class="lightPanel">
              	<div class="lightPanelTop">
                	<div class="cap"></div>
              	</div>
              	<div class="lightPanelBody">
                	<div class="cap">
                  	<!-- Panel contents. -->
                  		<form:form  id="createInCyyteForm" name="createInCyyteForm"  commandName="createInCyyteForm" method="post" onsubmit="if(validatedQ()) {return true;} else{ return false;}" >
			              	<center>
			                    <div class="contentWrap">
			                      <p class="panelFont"> Ask Your Question :
			                      	<form:textarea path="incyyte" maxlength="${questionMaxChar}"  cols="50" rows="2" style="overflow:hidden; width: 400px; font-size: 15px; vertical-align: middle" cssClass="name" id="question" title="Type your question here" />
			                      	<input name="enter" type="submit" value="Start" style="width:70px; height:40px; vertical-align:middle" id="enterQuestion"/>			                      	
			                      </p>
			                      <div style="clear: both;"></div>
			                    </div>
		                  	</center>
		            	</form:form>
                  	<!-- End panel contents. -->
                	</div>
              	</div>
              	<div class="lightPanelBottom">
                	<div class="cap"></div>
              	</div>
            </div>
          	<!-- End panel. --> 
          
          	<img src="images/thinking_people.png"  class="thinking_ppl"/>  
     	</div>
     	
	  	<div id="adside">
	  		<form:form  id="loginForm"  commandName="loginForm" method="post" > 
	  		
	  			<form:hidden path="user_email" id="li_userEmail" />
	  			   
				<table width="100%" border="0">
	      			<tr>
						<td><span class="style4">Already a Member?</span></td>
				        <td>&nbsp;</td>
				        <td>&nbsp;</td>
				  	</tr>
	      			<tr>
				        <td><form:input path="login_email" id="loginEmail" cssClass="inputTxt" title="email address"/></td>
				        <td>&nbsp;</td>
				        <td>
				            <div align="right">
				              <input id="password-clear" type="text" value="Password" autocomplete="off" size="10" maxlength="8" class="inputTxt" title="password"/>  
				              <form:password path="login_pwd" id="password-password" autocomplete="off" size="10" maxlength="10" cssClass="inputTxt" title="password"/>  
				            </div>
				    	</td>
				 	</tr>
			      	<tr>
			        	<td><a href="#" title="Reset your login info." class="style1" id="fgtpwd-user">Forgot password?</a></td>
			        	<td>&nbsp;</td>
			        	<td><div align="right"><input name="LogIn" type="button" value="Log In" id="login-user" class="LogIn"/></div></td>
			        	<td><div align="right"><input name="Login_SN" type="button" value="Login via Social network" id="login-social" class="LogIn"/></div></td>
			      	</tr>
			    </table>
          	<!-- Panel. -->
          	</form:form>
          	
          	
          	    
        		<div class="signupPanel">
	           		<div class="lightPanelTop">
	                	<div class="cap"></div>
	              	</div>
	              	
	              	<div class="lightPanelBody">
	                	<div class="cap">
		             		<!-- Panel contents. -->
		                  	<center>
	                  		<form:form  id="signUpForm"  commandName="signUpForm" method="post">  	                  		
	                  		
	                  			<form:hidden path="ac_country" id="su_country" />
	                  			<form:hidden path="postcode" id="su_postcode" />
	                  			<form:hidden path="postal_area" id="su_postalarea" />
	                  			<form:hidden path="ageGroup" id="su_ageGroup" />
	                  			<form:hidden path="gender" id="su_gender" />
	                  			<form:hidden path="activate_act" id="su_activate" />	                  			                  			
	                  			
			               		<table width="100%" border="0">
				      				<tr>
					                    <td><div align="center" class="style2">
					                      <p><img src="images/join_logo.png" alt="join" width="180" height="42" /></p>
					                      </div>
					                     </td>
					             	</tr>
				                  	<tr>
				                    	<td><div align="center">
				                      		<form:input path="username" id="username"  cssClass="inputTxt" title="username"/>
				                    	</div></td>
				                  	</tr>
				                  	<tr>
				                    	<td><div align="center">
				                      		<form:input path="su_email" id="su_email" cssClass="inputTxt" title="email address"/>
				                    	</div></td>
				                  	</tr>
				                  	<tr>
				                    	<td><div align="center">
				                      		<form:input path="confirm_email" id="confirm_email"  cssClass="inputTxt" title="re-confirm email address"/>
				                    	</div></td>
				                  	</tr>
				                  	<tr>
				                    	<td><div align="center">
				                      		<input id="spassword-clear" type="text" value="Password" autocomplete="off"  class="inputTxt" title="password"/>  
				              				<form:password path="su_password" id="spassword-password" autocomplete="off" cssClass="inputTxt" title="password"/> 
				                    	</div></td>
				                  	</tr>                  
				                  	<tr>
				        				<td><div align="right"><input name="SignUp" type="button" value="Sign Up" id="reg-user" class="SignUp"/></div></td>
				                  	</tr>
				                  	<tr>
				        				<td>
				                       		<div id="communicator" class="communicator">
				                          		<p><img src="images/icons/Warning2.gif" alt="" width="16" height="16"/>  
				                              		<span id="communicatorMessage">Please fix the following errors before proceeding.</span>
				                                </p>
				          					</div>
				                    	</td>
				                  	</tr>
			                	</table>
			                </form:form>
		                  	</center>
		                  	<!-- End panel contents. -->
	                	</div>
	              	</div>
	              	<div class="lightPanelBottom">
	                	<div class="cap"></div>
	              	</div>
	        	</div>
	          	<!-- End panel. --> 
	
	         	<span class="adbottomTxt style3"> <strong>Ask your country.. </strong><BR/>
	         		<BR/>
	            	For the first time ever ask your community or even your entire country the questions you need answers to.<BR/>
	            	<BR/>
	            </span>
	            <span class="adbottomTxt  style6"><a href="#" title="Business Sign up">Find Out more.. </a></span> 
	    	</div>        
	        
			<div id="footer"> 
	            <div><P ALIGN=RIGHT>
	           	<FONT SIZE="1"><FONT FACE="Arial,Helvetica,Monaco"><FONT COLOR="#363636">inCyyte &copy; 2010. Cyyte Ltd</FONT></FONT></FONT>
	            </div>     
	        </div>
	        
	   	</div>

		<div class="demo">
			<div id="dialog-area-form" title="Complete Sign Up">
			<p class="validateTips_daf">All form fields are required.</p>
			<p align="right"><img src="images/signup_step2.png" title="Step 2." width="66" height="22" /></p>
			<form>
			<fieldset>
				<label for="country" class="dialogLabel">Country</label>
				<input type="text" id='ac_country' name='country' title="Change country" class="ui-widget-content ui-corner-all" /> 
				<label for="postcode" class="dialogLabel" id="postcode_lb">Postcode</label>
				<input type="text" id='postcode' name='postcode' value="" maxlength="10" class="dialogPCText ui-widget-content ui-corner-all" /> 
		        
				<label for="postal_area" class="dialogLabel" id="postal_area_lb">Postal Area</label>
				<input type="text" id='postal_area' name='postal_area' value="" class="dialogText ui-widget-content ui-corner-all" /> 
		        
				<hr/>
				<label for="gender" class="dialogLabel">I am</label>
		        <select name="gender" id="gender"  class="dialogDD ui-widget-content ui-corner-all" >
		          <option selected="selected" value="select">-- Select your gender --</option>
		          <option>Male</option>
		          <option>Female</option>
		        </select>
		
				<label for="age" class="dialogLabel">My age group is between</label>
		        <select name="ageGroup" id="ageGroup"  class="dialogDD ui-widget-content ui-corner-all" >
		          <option selected="selected" value="select">--Select your age group --</option>
					<option>08-12</option>
					<option>13-18</option>
					<option>19-25</option>
					<option>26-30</option>
					<option>31-40</option>
					<option>41-50</option>
					<option>51-Over</option>
		        </select>
		        <input id="chkTerms" type="checkbox" name="chkTerms" class="ui-widget-content ui-corner-all" /> 		
		        <label class="panelLink" id="terms" title="Read Terms">Terms and Conditions</label>                
				<br/>        
				<br/>
				<br/>
				<label class="panelLink" id="login-user3" title="Members login here">Already have an Account?</label>
		
			</fieldset>
			</form>
		</div>
		
		<div id="activate-dialog-form" title="Activate Account">
			<p class="validateTips_adf">Enter your Activation code.</p>
			<p align="right"><img src="images/signup_step3.png" title="Final step"  width="66" height="22" /></p>
				<form>
					<fieldset>
					  <label for="activate_act" class="dialogLabel" id="activate_msg"></label>
						<input name="activate_act" id="activate_act" class="dialogPCText ui-widget-content ui-corner-all" />				
						<br/>
						<br/>
						<label class="panelLink" id="resend_activation_cd" title="I have not received my activation code...">please resend my activation code.</label>
					</fieldset>
				</form>
		</div>

		  
		<div id="login-dialog-form" title="Member Login">
			<p class="validateTips_ldf">Please log in to send your inCyyte.</p>
			
			  <form>
				<fieldset>
					<label for="login_email" class="dialogLabel">Email</label>
					<input type="text" name="login_email" id="login_email" class="dialogText ui-widget-content ui-corner-all" />
					<label for="login_pwd" class="dialogLabel">Password</label>
					<input type="password"  name="login_pwd" id="login_pwd" class="dialogText ui-widget-content ui-corner-all" />
					<label class="panelLink" id="fgtpwd-user2" title="Request new password.">Forgot password?</label>				 
				</fieldset>
			</form>
		</div>

		<div id="dialog-message" title="Please Enter a Question">
	        <p>
	            To get insight on opinions you need to enter a probing question.
	  		</p>
		</div>

		<div id="fgtpwd-dialog-form" title="Forgotten Password">
			<p class="validateTips_fdf">Request a new password.</p>
			<form>
				<fieldset>
					<label for="user_email" class="dialogLabel">Email</label>
					<input type="text" name="user_email"  id="user_email" class="dialogText ui-widget-content ui-corner-all" />
			        <BR/>
					<span>Enter your email address and your password will be sent to you.</span>
				</fieldset>
			</form>
		</div> 

		<div id="fgtpwd-dialog-msg" title="New Password sent.">
	        <p>            
	            Your account password has been reset. Check your email for details.<BR/><BR/>
	            Once logged into your account, please change your password in your account settings.
	  		</p>
		</div>
	
	</div>
<script type="text/javascript" src="ui/js/googleanalytics.js"></script>
</body>
</html>
