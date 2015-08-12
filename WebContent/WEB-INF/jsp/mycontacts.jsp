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
	<jsp:include page="include/header.jsp" />

				
				<div class="header">
		 		<P><a href="#" id="add" class="style22 link"><strong>ADD Contact</strong></a></P>
            	<P>  <a href="importcontacts.cyt" id="import" class="style22 link"><strong>Import Contact</strong></a></P>
										
				</div>
				<div id="addfrm" style="display: none"> 
				<form:form  id="addContactForm"  commandName="addContactForm" method="post" >   
       			
		        	<div id="content">        
		       		<P> Add Contact </P>
		            
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
				                       		<label> Email:</label>
				                     	</div>
				                     	<div class="contentCell2">
				                       		<form:input path="email" id="email" size="30"  title="Enter the email id to invite" AUTOCOMPLETE="off" />
				                       	</div>
				                  	</div>  
				                  	
				          
				                  	                 
						         	<div class="row">
						               		<div class="labelCell2"></div>
							                <div class="contentCell2">
							               		<span><input name="addContact" type="button" value="Add Contacts" class="login" id="addContact"/></span>
							               	</div>
						        	</div>                     
                                      
            					</div>
          								
          						<!-- End MAIN panel contents. -->
              				</div>
              		</div>		
              	</form:form	>	
           
        </div>

</div>

				<div class="header">
		 		<P><a href="#" id="search" class="style22 link"><strong>Search Contact</strong></a></P>
    						
				</div>
				<div id="searchfrm" style="display: none">     
				<form:form  id="SearchContactForm"  commandName="SearchContactForm" method="post" >   
       			
		        	<div id="content">        
		       			<P> Search Contact </P>
		            
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
				                       		<form:input path="nickname" id="sc_nickname" size="30"  title="Confirm your new password" AUTOCOMPLETE="off" />
				                       	</div>
			                   		</div>
				                   	<div class="row">
				                   		<div class="labelCell2 loginText">
				                       		<label> First name :</label>
				                     	</div>
				                     	<div class="contentCell2">
				                       		<form:input path="firstname" id="sc_firstname" size="30"  title="Confirm your new password" AUTOCOMPLETE="off" />
				                       	</div>
				                       	</div>
				                   	</div>
				                   	<div class="row">
				                    	<div class="labelCell2 loginText">
				                       		<label> Last name:</label>
				                     	</div>
				                     	<div class="contentCell2">
				                       		<form:input path="lastname" id="sc_lastname" size="30"  title="Confirm your new password" AUTOCOMPLETE="off" />
				                       	</div>
				                  	</div>  
				                  	
				                  	
				                  	<div class="row">
				                    	<div class="labelCell2 loginText">
				                       		<label> Email:</label>
				                     	</div>
				                     	<div class="contentCell2">
				                       		<form:input path="email" id="sc_email" size="30"  />
				                       	</div>
				                  	</div>  
				                  	
				                  	
				                  	
				                  	
				                  		
				                  	
				                  	
				                     
				                  	                 
						         	<div class="row">
						               		<div class="labelCell2"></div>
							                <div class="contentCell2">
							               		<span><input name="SearchContact" type="button" value="Search Contacts" class="login" id="SearchContact"/></span>
							               	</div>
						        	</div>                     
                                      
            					</div>
          								
          						<!-- End MAIN panel contents. -->
              				</div>   				
              				
          				
              	</div>
              	
              	
              	
              	
              	
              	
              	
              	
              	
              	
              	
              	
              	
              	
              	
              	
              	
              	
              	</form:form>			
              				
             </div>		
             
             						<div id="top">        
         		   
  				</div> 
  
              						

             
             
             
             
             
             
             
             
             
             
             
             
             
             
             
             		
          					
              						<div id="top">        
         			My contacts     
  				</div> 
  
              						
              						<table border="1">
<tr>
	<th><input type="checkbox" id="selectall"/></th>
	<th>Nickname</th>
	<th>First name</th>
	<th>Last name</th>
	<th>Email</th>
	<th>Mobile</th>
	<th>Note</th>
	<th>Edit Account</th>
	<th>Block Account</th>
</tr>
  <c:forEach items="${UserContactlist}" var="UserContactModel">
<tr>
	<td align="center">
	
	
									
	<td> ${UserContactModel.nickname}</td>
	<td> ${UserContactModel.firstname}</td>
	<td> ${UserContactModel.lastname}</td>
	<td> ${UserContactModel.email}</td>
	<td> ${UserContactModel.mobile}</td>
	<td> ${UserContactModel.note}</td>
	<c:if test="${UserContactModel.accept_inv=='Y'}">
	<td><a href="#" onclick="javascript:processContact('Edit','${UserContactModel.contactid}','${UserContactModel.nickname}','${UserContactModel.firstname}','${UserContactModel.lastname}','${UserContactModel.email}','${UserContactModel.mobile}','${UserContactModel.status}','${UserContactModel.definingQuestion}','${UserContactModel.tmpPostcode}','${UserContactModel.formattedMessageDate}','${UserContactModel.blocked}','${UserContactModel.accept_inv}','${UserContactModel.sent_invite}','${UserContactModel.profile_img}','${UserContactModel.username}','${UserContactModel.pollHomePage}')" title="Edit" class="style22 link"><strong>Edit</strong></a></td>
  
    <td><a href="#" onclick="javascript:processContact('Block','${UserContactModel.contactid}','${UserContactModel.nickname}','${UserContactModel.firstname}','${UserContactModel.lastname}','${UserContactModel.email}','${UserContactModel.mobile}','${UserContactModel.status}','${UserContactModel.definingQuestion}','${UserContactModel.tmpPostcode}','${UserContactModel.formattedMessageDate}','${UserContactModel.blocked}','${UserContactModel.accept_inv}','${UserContactModel.sent_invite}','${UserContactModel.profile_img}','${UserContactModel.username}','${UserContactModel.pollHomePage}')" title="Block" class="style22 link"><strong>Block</strong></a></td>
      </c:if>
      <c:if test="${UserContactModel.accept_inv=='N'}">
	<td><strong>Edit</strong></a></td>
  
    <td><strong>Block</strong></a></td>
      </c:if>
      
      
</tr>
</c:forEach>
                      	<div class="row">
				                    	
				                     	<div class="contentCell2">
				                       		
				                            <select    id="action" onchange="javascript:processContact(this.options[this.selectedIndex].value)">     
				                            <option value="select" >select </option>                                      
                                            <option value="SendInvite" >Invite Selected account </option>
                                            <option value="Delete" >Delete Selected account </option>
                                            <option value="AddGroup" >AddtoGroup</option>
                                            </select>
				                       
				                       
				                       	</div>
				                  	</div>  
</table>


</div>
              						
              						
              						
              						
              						
              						
              						
              						
              						
              						
              						
              					
              						
              						
              						
              						
              						
					
        <div id="contactform" style="display: none">     
        
                             
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
				                       		<form:input path="nickname" id="ed_nickname" size="30"  value=""/>
				                       	</div>
			                   		</div>
				                   	<div class="row">
				                   		<div class="labelCell2 loginText">
				                       		<label> First name :</label>
				                     	</div>
				                     	<div class="contentCell2">
				                       		<form:input path="firstname" id="ed_firstname" size="30"  value="" />
				                       	</div>
				                       	</div>
				                   	</div>
				                   	<div class="row">
				                    	<div class="labelCell2 loginText">
				                       		<label> Last name:</label>
				                     	</div>
				                     	<div class="contentCell2">
				                       		<form:input path="lastname" id="ed_lastname" size="30"  value="" />
				                       	</div>
				                  	</div>  
				                  		<div class="row">
				                    	<div class="labelCell2 loginText">
				                       		<label> Email:</label>
				                     	</div>
				                     	<div class="contentCell2">
				                       		<form:input path="email" id="ed_email" size="30" value="" />
				                       	</div>
				                  	</div>    
				                  	
				                  	<form:hidden path="contactid" id="ed_contactid"   />
				                  	<form:hidden path="checked" id="ed_checked"   />
				                  		<div class="row">
				                    	<div class="labelCell2 loginText">
				                       		<label> Mobile:</label>
				                     	</div>
				                     	<div class="contentCell2">
				                       		<form:input path="mobile" id="ed_mobile" size="30" value="" />
				                       	</div>
				                  	</div>    
				                  	
				                  	
				                  	
				                  	<div class="row">
				                    	<div class="labelCell2 loginText">
				                       		<label> Note</label>
				                     	</div>
				                     	<div class="contentCell2">
				                       		<form:input path="note" id="ed_note"  value="" size="30"  title="Confirm your new password" AUTOCOMPLETE="off" />
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
	</body>
	
	
	
	
	
	<SCRIPT>
$(function(){

	// add multiple select / deselect functionality
	$("#selectall").click(function () {
		  $('.chkbox').attr('checked', this.checked);
	});

	// if all checkbox are selected, check the selectall checkbox
	// and viceversa
	$(".chkbox").click(function(){

		if($(".chkbox").length == $(".chkbox:checked").length) {
			$("#selectall").attr("checked", "checked");
		} else {
			$("#selectall").removeAttr("checked");
		}

	});
});


</SCRIPT>
	
	
	
	
	
	
</html>
