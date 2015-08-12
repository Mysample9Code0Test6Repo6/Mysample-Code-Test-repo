<%@ include file="/WEB-INF/jsp/include.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>InCyyte | Contacts</title>
    	<style type="text/css"></style>
    	<script type="text/javascript" src="js/jquery-1.5.1.min.js"></script>
    	<script type="text/javascript" src="js/jquery-ui-1.8.13.custom.min.js"></script>
    	<script type="text/javascript" src="js/external/jquery.bgiframe-2.1.2.js"></script>
    	<script type="text/javascript" src="js/communicator.js"></script>
    	<script type="text/javascript" src="js/contact.js"></script>
    	<script type="text/javascript" src="js/jquery.form.js"></script>  
    
    	
    
	 
        
    
		
	</head>
	<body>

	
        
                             
        <form:form  id="editContactForm"  commandName="editContactForm" method="post" >   
       			
		        	<div id="content">        
		       			<!-- MAIN Panel. -->
		            
		            	<div class="mainPanel">
		            	
		              		<div class="mainPanelTop">
		                		<div class="cap"></div>
		              		</div>
		              		
		              		<div class="mainPanelBody">
		              		
		              			<!-- MAIN Panel contents. -->
		              			<img src="images/line.png" />
               
								<div id="" class="communicator">
			                        <blockquote>
			                       		
			                        </blockquote>
          						</div>
            					<br/><br/>                            
            
            					<div class="panelTable">
                                                      
                   
                                                      
                                                      
                                                        
			                   		<div class="row">
			                     		<div class="labelCell2 loginText">
			                       			<label>Nickname:</label>
			                     		</div>
			                     		<div class="contentCell2">
				                       		<form:input path="nickname" id="nickname" size="30"  title="Confirm your new password" AUTOCOMPLETE="off" />
				                       	</div>
			                   		</div>
				                   	<div class="row">
				                   		<div class="labelCell2 loginText">
				                       		<label> First name :</label>
				                     	</div>
				                     	<div class="contentCell2">
				                       		<form:input path="firstname" id="firstname" size="30"  title="Confirm your new password" AUTOCOMPLETE="off" />
				                       	</div>
				                       	</div>
				                   	</div>
				                   	<div class="row">
				                    	<div class="labelCell2 loginText">
				                       		<label> Last name:</label>
				                     	</div>
				                     	<div class="contentCell2">
				                       		<form:input path="lastname" id="lastname" size="30"  title="Confirm your new password" AUTOCOMPLETE="off" />
				                       	</div>
				                  	</div>  
				                  	
				                  	
				                  		<div class="row">
				                    	<div class="labelCell2 loginText">
				                       		<label> Mobile:</label>
				                     	</div>
				                     	<div class="contentCell2">
				                       		<form:input path="mobile" id="mobile" size="30"  title="Confirm your new password" AUTOCOMPLETE="off" />
				                       	</div>
				                  	</div>    
				                  	
				                  		<div class="row">
				                    	<div class="labelCell2 loginText">
				                       		<label> Send invite</label>
				                     	</div>
				                     	<div class="contentCell2">
				                       		
				                            <form:select path="sent_invite"   id="sent_invite">
                                            <form:option value="Y" label="Y" />
                                            <form:option value="N" label="N" />
                                            </form:select>
				                       
				                       
				                       	</div>
				                  	</div>  
				                  	
				                  	<div class="row">
				                    	<div class="labelCell2 loginText">
				                       		<label> Note</label>
				                     	</div>
				                     	<div class="contentCell2">
				                       		<form:input path="note" id="note" size="30"  />
				                       	</div>
				                  	</div>    
				                  	                 
						         	<div class="row">
						               		<div class="labelCell2"></div>
							                <div class="contentCell2">
							               		<span><input name="updateContact" type="button" value="Save Contacts" class="login" id="updateContact"/></span>
							               	</div>
						        	</div>                     
                                      
            					</div>
          								
          						<!-- End MAIN panel contents. -->
              				</div>
              					
        
        
        
        
			
    		<!--container end-->
    
		</div>

				</form:form>
<script type="text/javascript" src="ui/js/googleanalytics.js"></script>				
	</body>
</html>
