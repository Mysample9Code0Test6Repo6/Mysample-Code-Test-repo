<%@ include file="/WEB-INF/jsp/include.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>InCyyte Main Page</title>
    <link rel="stylesheet" href="ui/css/style_login.css"/>
    <script type="text/javascript" src="js/jquery-1.5.1.min.js"></script>
    <script type="text/javascript" src="js/jquery-ui-1.8.13.custom.min.js"></script>
    <script type="text/javascript" src="js/external/jquery.bgiframe-2.1.2.js"></script>
    <script type="text/javascript" src="js/communicator.js"></script>
    <script type="text/javascript" src="js/create_question.js"></script>
	<script type="text/javascript" src="js/jquery-ui-timepicker-addon.js"></script>
	<script type="text/javascript" src="js/jquery.form.js"></script> 
	
    <link href="css/common.css" rel="stylesheet" type="text/css" />
    <link href="css/create_question.css" rel="stylesheet" type="text/css" />
    <link href="css/demos.css" rel="stylesheet" type="text/css" />
    <link rel="stylesheet" type="text/css" href="css/themes/ui-lightness/jquery.ui.all.css">
    
    <style type="text/css">
		<!--
		.style9 {
			text-align: left;
			padding-left: 90px;
			font-size:10px;
		}
		.style8 {
			padding-left: 45px;
		}
		.style11 {
			font-family: Calibri;
			font-size: 16px;
			color: #33527F;
		}
		.style17 {
			font-size: 185%
		}
		.style18 {font-size: 12px}
		.style19 {
			color: #2D4268;
			font-size: 14px;
		}
		.style21 {
			color: #DCDCED
		}
		.style22 {color: #9999CC}	 
		-->
    </style>
<!--[if lte IE 8]>
    <script src="ui/js/html5.js"></script>
    <link href="ui/css/ie8.css" media="screen" rel="stylesheet" type="text/css" />
  <![endif]-->
  <!--[if gte IE 9]>
 <link href="ui/css/gte_ie9.css" media="screen" rel="stylesheet" type="text/css" />
<![endif]-->


</head>
<!--JOE CHANGES-->
<body>

	<div id="container_wrapper">
   		<div class="statusBar">
       		<div class="content">
       			<div class="left"><img src="images/incyyte_sm_logo2.png" width="144" height="90" /></div>
               	<div class="right">
                            <span class="linkSpacer"></span>
                      <span id="login-user2" class="link" title="Home"><img src="images/Home-icon.png" /></span>
                            <span class="linkSpacer"></span>
                            <span class="linkSpacer"></span>
                      <a href="http://www.incyyte.co.uk/results" title="View Results" target="_blank" class="link style22"><strong>view results</strong></a>
                            <span class="linkSpacer"></span>
                            <span class="linkSpacer"></span>
                      <a href="http://www.incyyte.co.uk/about" title="New User - Sign up Now" target="_blank" class="link style22"><strong>sign up</strong></a>                            
                      		<span class="linkSpacer"></span>
                            <span class="linkSpacer"></span>
          			  <a href="http://www.incyyte.co.uk/about" title="Sign In" target="_blank" class="link style21"><strong>sign in</strong></a> 
               	</div>
          	</div>
   		</div> 
  		<div class="pageHighlight">
    	<!-- Div for graphical highlight only. -->
  		</div>
  		
  		<form:form id="createInCyyteForm" name="createInCyyteForm" commandName="createInCyyteForm"	enctype="multipart/form-data" method="post">
			<!--container start-->
    		<div id="container">
        		<div id="top">
        			<!--
			          <div>    <p id="strapline">Instant poll results when you inCyyte friends, workgroup and even your community!</p>
			            <p>.</p>
			          </div> 
			          -->      
  				</div>
            
				<div class="header"><p>CREATE YOUR INCYYTE </p></div>
				<BR>
				
		        <div id="content">        
		          <!-- MAIN Panel. -->
		            <div class="mainPanel">
		              <div class="mainPanelTop">
		                <div class="cap"></div>
		              </div>
		              <div class="mainPanelBody">
		                <div class="cap">
		                    <!-- MAIN Panel contents. -->
		                    <!-- QuestionPanel. -->
		                  	<p></p>
		                  	<div id="communicator" class="communicator">
                        		<blockquote>
                          			<p>
                          				<img src="images/icons/Warning2.gif" alt="" width="16" height="16"/>  
                              			<span id="communicatorMessage">Please fix the following errors before proceeding.</span>
                              			<c:if test="${not empty errors}"><span id="error" style="color:red;">${errors}.</span></c:if>
                                  	</p>
                        		</blockquote>
          				  </div>
		                  <div class="questionlightPanel">
		                          <div class="lightPanelTop">
		                            <div class="cap"></div>
		                          </div>
		                          <div class="lightPanelBody">
		                            <div class="cap">
		                              <!-- Panel contents. -->
		                                <div class="contentWrap">
		                                    <div class="panelTable">
		                                      <div class="row">
		                                        <div class="contentCell"> <label></label>
		                                        </div>
		                                      </div>
		                                      <div class="row" id="questionText">
			                            		<div class="contentCell style8"> 
			                            
			                            			<div align="left">
			                                			<p></p>
			                                			<p><span class="style17"><c:out value="${createInCyyteForm.incyyte}" /></span>                                              
			                                			<br/>
			                                			</p>
			                              			</div>
				                              		<label class="panelLink" id="editQuestion" title="Edit Question">
					                              		<div align="left" width="10" >
					                                		<div align="right">Edit your question</div>
					                              		</div>
		                                      		</label> 
		                                      		<div align="left" width="10" >
                                    					<div align="right"> <form:input path="closureDate"  id="closureDate" title="Select a date you want this poll to Complete"  cssClass="closeDate"/></div>                                    
                                  					</div>
		                                      	</div>
		                                      </div>
		                                      <div class="row" id="questionField">
		                                        <div class=" style8 contentCell">
		                                          <form:textarea path="incyyte" maxlength="${questionMaxChar}" cols="50" rows="5" cssStyle="overflow:hidden; width: 350px;" cssClass="questionbox" id="question" title="Edit your question" />
		                                          <input name="closureDate" type="text" id="closureDateE" title="Select a date you want this poll to Complete" value="Select a closing date" class="closeDateE"/>
		                                        </div>
		                                      </div>
		                                      
		                                      <div class="row">
		                                        <div class="contentCell"><label> add a file : </label>
		                                          <form:checkbox path="addFile" id="addFile" onclick="isUploadChecked();" cssClass="borderless" title="tick, to add file"/>&nbsp;&nbsp; 
		                                          <!--<spring:bind path="createInCyyteForm.uploadedFile">-->
		                                          	<span id="uploadDiv" >
		                                          		
				                                    	<span id="notLoaded">
				                                        	<form:input path="uploadedFile" type="file" id="uploadfile" size="40" title="Add a picture or document related to your question"/> 
				                                          	<input type="button" id="uploadButton" value="Upload" />		                                          		
				                                          	<span id="loadingDiv" style="display: none;"><img src="images/ajax-loader.gif" /></span>	
				                                          	<br>	                                          		                                          	
				                                          	<c:if test="${not empty uploadError}"><span id="uploadError" style="color:red;">${uploadError}.</span></c:if> 	
				                                         </span>
				                                          	
				                                         <span id="loaded" style="display: none;">
				                                          	<span id="loadText"> </span> &#160;&#160;&#160;&#160; <input type="button" id="uploadAnotherButton" value="Edit Upload" />
				                                         </span>
				                                  	</span>
				                                  <!--</spring:bind>-->
				                                  </div>
		                                      </div>
		                                      <div class="row" >
		                                        <div class="contentCell"><label> add a link : </label>
		                                          <form:checkbox path="addLink" id="addLink" onclick="isAddLinkChecked();" cssClass="borderless" title="tick, to add link"/>&nbsp;&nbsp; 
		                                          <form:input path="eLink"  id="eLink"  size="30"  title="Enter a link related to your question"/>
		                                        </div>
		                                      </div>
		                                      
		                                      <div class="row">
		                                        <div class="contentCell"><label> add anonymity : </label>
		                                          <form:checkbox path="anonymity" id="anonymity"  onclick="isAddAnonymityChecked();" cssClass="borderless" title="tick, to add anonymity"/>&nbsp;&nbsp; 
		                                          <span id="senderDetails">
		                                          	firstname : <form:input path="senderfname"  id="senderfname"  size="15"  title="Enter your firstname"/>&nbsp;
		                                          	surname : <form:input path="senderlname"  id="senderlname"  size="15"  title="Enter your surname"/>
		                                          </span> 		                                       
		                                        </div>
		                                      </div>
		                                      
		                                     <div class="row">
		                                        <div class="contentCell"><label> make this incyyte searchable : </label>&nbsp;&nbsp; 
		                                        	<form:radiobutton path="public_poll" cssClass="borderless"  value="N"/>Private 
		                                        	&nbsp;&nbsp; 
													<form:radiobutton path="public_poll" cssClass="borderless"  value="Y"/>Public		                                       
		                                        </div>
		                                      </div> 
		                                      
		                                      <div class="row" >
		                                        <div class="contentCell"><label> allow comment : </label>
		                                          <form:checkbox path="allowComment" id="allowComment" cssClass="borderless" title="tick, to add comment"/>
		                                        </div>
		                                      </div>
		                                      
		                                    </div>
		                                </div>
		                              <!-- End panel contents. -->                              
		                            </div>
		                          </div>
		                          <div class="lightPanelBottom">
		                            <div class="cap"></div>
		                          </div>
		                  </div>
		                 <!-- End panel. -->  
                      
             <!-- Responses Panel. -->
            <div class="questionlightPanel">
              <div class="lightPanelTop">d
                <div class="cap"></div>
              </div>
              <div class="lightPanelBody">
                <div class="cap">
                  <!-- Panel contents. -->
                    <div class="contentWrap">   
                      <input type="hidden" value="${answerMaxOption}" id="answerMaxOption"/>                 	
                      <table id="responseOpts" width="490px">
                        <thead>
                            <tr>
                                <th><div align="left">
                                  <blockquote>
                                    <p class="panelTitle style19">Add up to ${answerMaxOption} answer options</p>
                                  </blockquote>
                                </div></th>
                          </tr>
                        </thead>                        
                        <tbody>                        	
                            <tr>
                               <td class="style9"><form:input path="resp1" id="resp1" size="50"  cssClass="inputwidth" title="answer 1."/></td>
                            </tr>
                            <tr>
                               <td class="style9"><form:input path="resp2" id="resp2" size="50" cssClass="inputwidth" title="answer 2."/></td>
                            </tr>
                            <tr>
                              <td class="style9"><form:input path="resp3" id="resp3" size="50"  cssClass="inputwidth" title="answer 3."/><img src="images/icons/delete-icon3.png" alt="" width="12" height="12" id="delete" title="remove this option"/>
                              </td>
                          </tr>
                        </tbody>
                        <tfoot>
                            <tr>
                            <td><label class="panelLink" id="addResOpt">
                              <div align="left">
                                <blockquote>
                                  <p> add another answer option</p>
                                </blockquote>
                              </div>
                            </label></td>
                          </tr>
                      </tfoot>
					</table>
                    <form:hidden path="answerArr" id="responses" />
                  </div>
                  <!-- End panel contents. -->
                </div>
              </div>
              <div class="lightPanelBottom">
                <div class="cap"></div>
              </div>
            </div>
          <!-- End panel. -->       
    

            <!-- Category Panel. -->
            <div class="questionlightPanel">
              <div class="lightPanelTop">
                <div class="cap"></div>
              </div>
              <div class="lightPanelBody">
                <div class="cap">
                  <!-- Panel contents. -->
                    <div class="contentWrap">
						<div class="panelTable">
                          <div class="row">
                            <div class="contentCell"> <label>
                              <p align="left" class="panelTitle style19">Select inCyyte categories </p>
                            </label></div>
                          </div>
                          <div class="row">
                            <div class="contentCell style9">
                            	<form:select path="category" id="category" cssClass="inputwidth" title="category">
									<form:options items="${categories}"/>
								</form:select>                                
                            </div>
                          </div>
                        </div>
                    </div>
                  <!-- End panel contents. -->
                </div>
              </div>
              <div class="lightPanelBottom">
                <div class="cap"></div>
              </div>
            </div>
          <!-- End panel. -->       
    
             <!-- Preview & send button. -->  
           
            <div class="questionlightPanel">
              <div class="lightPanelTop">
                <div class="cap"></div>
              </div>
              <div class="lightPanelBody">
                <div class="cap">
                  <!-- Panel contents. -->
                    <div class="contentWrap">
						<div class="panelTable">
                          <div class="row">
                            <div class="footerCell left">
                              <div align="center">
                              	<input name="Preview" type="button" onclick="previewInCyyte();" value="Preview inCyyte" class="button" title="View your question's layout "/>
                              <!-- <a href="javascript:document.createInCyyteForm.submit();" title="View your question's layout " class="style18">Preview inCyyte</a> -->
                              </div>
                            </div>
                            <div class="footerCell left"> </div>
                            <div class="footerCell right"><input name="Send" type="button" onclick="sendInCyyte();" value="Continue" class="button" /></div>
                          </div>

                        </div>
                    </div>
                  <!-- End panel contents. -->
                </div>
              </div>
              <div class="lightPanelBottom">
                <div class="cap"></div>
              </div>
            </div>
                  
		              		<!-- Preview & send button. -->              
		                      
		                 	<!-- End MAIN panel contents. -->
		                	</div>
		              	</div>
              			<div class="mainPanelBottom">
                			<div class="cap"></div>
              			</div>
            		</div>
		            <p>
		              <!-- End panel. -->
		            </p>
            		<p>&nbsp;</p>
        		</div>
        
        		<div id="adside">
		       		<h3 align="left" class="style11"><span class="style18">&nbsp;&nbsp;&nbsp;Why use inCyyte?</span></h3>
		        	<h3 align="right" class="style11"><img src="images/advert1.png" width="204" height="211" border="0" usemap="#Map" /></h3>
					<map name="Map" id="Map"><area shape="rect" coords="123,184,181,199" href="#" title="find out more"/></map>
			  	</div>
			  	
		    	<div id="adside">
		        	<h3 align="left" class="style11"><span class="style18">&nbsp;&nbsp;&nbsp;Post free public notices</span></h3>
		        	<h3 align="right" class="style11"><img src="images/advert2.png" width="204" height="211" border="0" usemap="#Map2" /></h3>
					<map name="Map2" id="Map2"><area shape="rect" coords="129,183,182,199" href="#" title="sign up to inCyyte" /></map>
				</div>    
		        
		 		<div id="adside">
		        	<h3 align="left" class="style11"><span class="style18">&nbsp;&nbsp;&nbsp;Our anonimity rule</span></h3>
		        	<h3 align="right" class="style11"><img src="images/advert3.png" width="204" height="189" border="0" usemap="#Map3" /></h3>
					<map name="Map3" id="Map3"><area shape="rect" coords="123,162,182,176" href="#" title="find out more"/></map>    
				</div> 
        
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
		</form:form>
  	<!--container end-->
	</div>

<script type="text/javascript" src="ui/js/googleanalytics.js"></script>
</body>
</html>
