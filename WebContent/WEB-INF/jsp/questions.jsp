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
    	<script type="text/javascript" src="js/searchques.js"></script>
    	<script type="text/javascript" src="js/jquery.form.js"></script>  
    
   
	 
        
    
		
	</head>
	<body>
    <jsp:include page="include/header.jsp" />


	<form:form  id="searchQuesForm"  commandName="searchQuesForm" method="post" >   
       			
		        	<div id="content">        
		       			<P> Search Questions </P>
		            
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
			                       			<label>Enter the text to search:</label>
			                     		</div>
			                     		<div class="contentCell2">
				                       		<form:input path="question" id="sc_question" size="150"   />
				                       	</div>
			                   		</div>
				                   
				            
				                  	                 
						         	<div class="row">
						               		<div class="labelCell2"></div>
							                <div class="contentCell2">
							               		<span><input name="SearchQues" type="button" value="Search" class="login" id="SearchQues"/></span>
							               	</div>
						        	</div>                     
                                      
            					</div>
          								
          						<!-- End MAIN panel contents. -->
              				</div>   				
              				
          				
              	</div>
        	</form:form>	
        	                      					
<tr>
	
	
</tr>
  <c:forEach items="${searchresult}" var="QuestionModel">
<tr>
	
	
	
		<td><a href="#" onclick="javascript:ShowQuesDtls( '${QuestionModel.questionid}',' ${QuestionModel.question}','${QuestionModel.category}) title='${QuestionModel.question}' class="style22 link"><strong>'${QuestionModel.question}'</strong></a></td>
	
									
	
	
	
</tr>
</c:forEach>   	
              	
              	
             		
              				
    
             
             