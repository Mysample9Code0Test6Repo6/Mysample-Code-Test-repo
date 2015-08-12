<%@ include file="/WEB-INF/jsp/include.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>InCyyte | Login</title>
    	<style type="text/css"></style>
    	<script type="text/javascript" src="js/jquery-1.5.1.min.js"></script>
    	<script type="text/javascript" src="js/jquery-ui-1.8.13.custom.min.js"></script>
    	<script type="text/javascript" src="js/external/jquery.bgiframe-2.1.2.js"></script>
    	<script type="text/javascript" src="js/communicator.js"></script>
    	<script type="text/javascript" src="js/group.js"></script>
    	<script type="text/javascript" src="js/jquery.form.js"></script>  
    
    	<link rel="stylesheet" type="text/css" href="css/common.css" />
    	<link rel="stylesheet" type="text/css" href="css/login.css" />
    	<link rel="stylesheet" type="text/css" href="css/themes/ui-lightness/jquery.ui.all.css">
    	<link rel="stylesheet" type="text/css" href="css/demos.css">
    
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
		</style>
    
    
		<style type="text/css">
		<!--
			.style9 {text-align: left;	padding-left: 100px;}
			.style11 {
				font-family: Calibri;
				font-size: 16px;
				color: #33527F;
			}
			.style18 {font-size: 14px}
			.style20 {font-size: 14px; color: #2D4268; }
		-->
		</style>
		
	</head>
	<body>
		

		<div id="container_wrapper">
		<jsp:include page="include/header.jsp" />
           
            </div> 
		  	<div class="pageHighlight">
		    	<!-- Div for graphical highlight only. -->
		  	</div>
		  	
			<!--container start-->
    		<div id="container">
        		<div id="top">        
         			 <!-- <div>    <p id="strapline">Instant poll results when you inCyyte friends, workgroup and even your community!.</p></div>-->       
  				</div> 

				<div class="header">
		 	 		UPDATE INCYYTE GROUP
				</div>
				<form:form  id="addGroupForm"  modelAttribute="group" method="post"  enctype="multipart/form-data">   
       			
		        	<div id="content">        
		       			<!-- MAIN Panel. -->
		            
		            	<div class="mainPanel">
		            	
		              		<div class="mainPanelTop">
		                		<div class="cap"></div>
		              		</div>
		              		
		              		<div class="mainPanelBody">
		              		
		              			<!-- MAIN Panel contents. -->
		              		            
            <form:hidden path="groupId" />
            					<div class="panelTable">
                                                        
			                   		<div class="row">
			                     		<div class="labelCell2 loginText">
			                       			<label> Group PostCode:</label>
			                     		</div>
			                     		<div class="contentCell2">
			                       			<form:input path="postCode"  id="pCode" size="30"  title="Post Code" AUTOCOMPLETE="off" />
			                     		</div>
			                   		</div>
				                   	<div class="row">
				                   		<div class="labelCell2 loginText">
				                       		<label> Group Name :</label>
				                     	</div>
				                     	<div class="contentCell2">
				                       		<form:input path="groupName"  id="gName" size="30"  title="Group Name" AUTOCOMPLETE="off" />
				                       	</div>
				                   	</div>
				                   	<div class="row">
				                   		<div class="labelCell2 loginText">
				                       		<label> Group Type :</label>
				                     	</div>
				                     	<div class="contentCell2">
				                       		<form:select  path="groupType"  itemvalue="gType" items="${groupTypes}"  title="Group Type" AUTOCOMPLETE="off" />
				                       	</div>
				                       	<div class="labelCell2 loginText">
				                       		<label> Group Image :</label>
				                     	</div>
				                     	<div class="contentCell2">
				                       		<input type="file" name="file" title="${group.logo }" />
				                       	</div>
				                   	</div>                
						         	<div class="row">
				                   		<div class="labelCell2 loginText">
				                       		<label> Description :</label>
				                     	</div>
				                     	<div class="contentCell2">
				                       		<form:textarea path="description"  id="desc" size="30"  title="Description" AUTOCOMPLETE="off" />
				                       	</div>
				                   	</div> 
            					</div>
          								
		 	 		ADD GROUP MEMBERS
            
            					<div class="panelTable">
	            					<div class="row">
					                   		<div class="labelCell2 loginText">
				                       		<label> Imports contacts from :</label>
				                       		<label> Change Admin Roles :</label>
				                     	</div>
				                     	
				                     	
					                </div> 
					                
					                <div class="row">
				                   		<div class="contentCell2">
					                   		<spring:bind path="selectedGroupContactsList">
					                   		<form:select path="selectedGroupContactsList" id="leftBox">
											   <form:options items="${group.selectedGroupContacts}" id="contact" itemLabel="nickname" itemValue="contactId" />
											</form:select>
										</spring:bind>
											<input type="button" onclick="copyItems('rightBox','leftBox');"  value = "<<<" />
											<input type="button" onclick="copyItems('leftBox','rightBox');"  value = ">>>" />
											<form:select path="${selectedGroupContactsList}" multiple="true" id="rightBox">
											   <form:options items="${ userContacts}" itemLabel="nickname" itemValue="contactId" />
											</form:select>
				                       	
				                       	</div>
				                   	</div> 
            					</div>
            					 	<div class="contentCell2">
				                   		<spring:bind path="selectedGroupContactsAdminList">
					                   		<form:select path="selectedGroupContactsAdminList" id="adminleftBox">
											   <form:options items="${group.selectedGroupContacts}" id="admincontact" itemLabel="nickname" itemValue="contactId" />
											</form:select>
										</spring:bind>
											<input type="button" onclick="copyItems('adminrightBox','adminleftBox');"  value = "<<<" />
											<input type="button" onclick="copyItems('adminleftBox','adminrightBox');"  value = ">>>" />
											<form:select path="${selectedGroupContactsAdminList}" multiple="true" id="adminrightBox">
											   <form:options items="${ userContacts}" itemLabel="nickname" itemValue="contactId" />
											</form:select>
											
				                       	
				                       	</div>
		 	 	
            					
            						<div class="row">
				                   		<div class="labelCell2 loginText">
				                     	</div>
				                     	<div class="contentCell2">
				                       		<input type="button" value="Update Group" onclick="selValues('<c:url value='editgroup.cyt' />');"/>
				                       	</div>
				                   	</div> 
          						<!-- End MAIN panel contents. -->
              				</div>
              						
					     
            			</div>
					          
					</div>
				</form:form>
        
				<div id="footer">  
					<div id="footer-bottom"> 
						<p class="bottom-right"><a href="index.html">Home</a> |		<a href="privacySettings.html">Privacy Settings</a> |
						<a href="anonymityRules.html">Anonymity Rule</a> |
					    <strong><a href="#top">Back to Top</a></strong>   </p>
						<p class="bottom-left">
							&copy; 2011 <strong>Copyright inCyyte.com</strong>&nbsp; &nbsp; &nbsp;</p>
					    <!-- /footer-bottom-->
					</div>
				</div>
			</div>
    		<!--container end-->
    
		</div>
<script type="text/javascript" src="ui/js/googleanalytics.js"></script>
	</body>
</html>
