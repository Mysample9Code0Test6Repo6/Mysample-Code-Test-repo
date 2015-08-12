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
         
		  	<div class="pageHighlight">
		    	<!-- Div for graphical highlight only. -->
		  	</div>
		  	
			<!--container start-->
    		<div id="container">
        		<div id="top">        
         			 <!-- <div>    <p id="strapline">Instant poll results when you inCyyte friends, workgroup and even your community!.</p></div>-->       
  				</div> 

				<div class="header">
		 	 		<p>Group Details</p>
				</div>
				<form:form  id="addGroupForm"  modelAttribute="group" method="post" >   
       			
		        	<div id="content">        
		       			<!-- MAIN Panel. -->
		            
		            	<div class="mainPanel">
		            	
		              		<div class="mainPanelTop">
		                		<div class="cap"></div>
		              		</div>
		              		
		              		<div class="mainPanelBody">
		              		
		              			<!-- MAIN Panel contents. -->
		              			<img src="images/line.png" />
               
								
            					<br/><br/>                            
            
            					<div class="panelTable">                                                    
			                   		<div class="row">
				                   		<div class="labelCell2 loginText">
				                     	</div>
				                     	<div class="contentCell2">
				                       		
				                       	</div>
				                   	</div>                
						         	        
				                   	
				                   					                     	
				                     	
				                     	<div class="contentCell2">
				                     		
				                     		<c:forEach items="${userGroups}" var="group">
				                     		<form:hidden path="groupId"/> v
				                     		
				                     			Group Name::<font size="4" face="bold"> <c:out value="${group.groupName }" /></font><br/><br/><br/><br/>
				                     			Group Logo::<img src="<c:url value="/imagecontent.cyt?grpId=${group.groupId }"/>" height="100	" width="100"/>
				                     			<br/><br/>
				                     			Contacts Present in the Group:-<br/>
				                     			<c:forEach items="${group.selectedGroupContacts}" var="contact" varStatus="rowItem">
				                     			<c:out value="${rowItem.index + 1 }" />&nbsp;&nbsp;&nbsp;&nbsp;
				                     			<c:out value="${contact.nickname }" />&nbsp;&nbsp;&nbsp;&nbsp;
				                     			<c:out value="${contact.firstName }" />&nbsp;&nbsp;
				                     			<c:out value="${contact.lastName }" />&nbsp;&nbsp;&nbsp;&nbsp;
				                     			<c:out value="${contact.email }" />
				                     			<br/>
				                     			</c:forEach>
				                     			<br/><br/>
				                     			</font>&nbsp;&nbsp;&nbsp;&nbsp;<a href="#" onclick="editGroup('<c:url value='editgrouppage.cyt' />','${group.groupId }');"/>EDIT</a>&nbsp;<a href='#' onclick="removeGroup('<c:url value='removegroup.cyt' />','${group.groupId }');"/>DELETE</a><br/>
				                     		
				                     		</c:forEach>
				                       		<!-- form:select  path="groupName"  itemvalue="groupName" items="${userGroups}" itemLabel="groupName" title="User Groups" AUTOCOMPLETE="off" / -->
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
