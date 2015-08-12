<%@ include file="/WEB-INF/jsp/include.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>Send InCyyte</title>
    	<style type="text/css"></style>
    	
    	<script type="text/javascript" src="js/jquery-1.5.1.min.js"></script>
    	<script type="text/javascript" src="js/jquery-ui-1.8.13.custom.min.js"></script>
    	<script type="text/javascript" src="js/external/jquery.bgiframe-2.1.2.js"></script>
    	<script type="text/javascript" src="js/send_question.js"></script>
    	<script type="text/javascript" src="js/communicator.js"></script>
    	<script type="text/javascript" src="js/accountry.0.2/jquery.autocomplete.js"></script>
    	<script type="text/javascript" src="js/accountry.0.2/countries.en.js"></script>
    	<script type="text/javascript" src="js/jquery.editable-select.pack.js"></script>
    	<script type="text/javascript" src="js/jquery.editable-select.js"></script>
    	<script type="text/javascript" src="js/backlink.js"></script>
    	<script type="text/javascript" src="js/jquery.form.js"></script>    

    	<link rel="stylesheet" type="text/css" href="css/accountry.0.2/jquery.autocomplete.css" />
    	<link rel="stylesheet" type="text/css" href="css/accountry.0.2/jquery.autocomplete.country.css" />
    	<link rel="stylesheet" type="text/css" href="css/common.css" />
    	<link rel="stylesheet" type="text/css" href="css/create_question.css" />
    	<link rel="stylesheet" type="text/css" href="css/themes/ui-lightness/jquery.ui.all.css">
    	<link rel="stylesheet" type="text/css" href="css/demos.css">
    	<link rel="stylesheet" type="text/css" href="css/jquery.editable-select.css" />
    
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
			.style21 {font-size: 12px}
			-->
		</style>
	</head>
	
	<body>
		<div id="container_wrapper">
	    	<div class="statusBar">
	                <div class="content">
	                    <div class="left"><img src="images/incyyte_sm_logo2.png" width="144" height="90" /></div>
	                    <div class="right">
	                            <span class="linkSpacer"></span>
	                      <span id="login-user2" class="link" title="Home">Home</span>
	                            <span class="linkSpacer"></span>
	                      <a href="http://www.incyyte.co.uk/results" target="_blank" class="link" title="View Results">View Results</a>
	                            <span class="linkSpacer"></span>
	                      <a href="http://www.incyyte.co.uk/about" target="_blank" class="link" title="New User - Sign up Now">Sign up</a>                    
	                            <span class="linkSpacer"></span>
	                            <span class="linkSpacer"></span>
	                            <span class="linkSpacer"></span>
	                            <span class="linkSpacer"></span>
	                      <a href="http://www.incyyte.co.uk/about" target="_blank" class="link" title="Sign In">Sign in</a>                    
	                	</div>
	              	</div>
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
					  <p>SENDING YOUR INCYYTE </p>
					</div>
		
        			<div id="content">        
          				<!-- MAIN Panel. -->
            			<div class="mainPanel">
            			
		              		<div class="mainPanelTop">
		                		<div class="cap"></div>
		              		</div>
              				<div class="mainPanelBody">
              				<!-- MAIN Panel contents. -->
							<!--
				                <div id="errorMsg#"> 
				                	<img src="images/icons/Warning2.gif" alt="" width="16" height="16"/>  
				                	<span id="communicatorMessage">Please fix the following errors before proceeding.</span>
				                </div>
				            -->
               
          					<div id="communicator" class="communicator">
			                  	<blockquote>
			                        <p>
			                          	<img src="images/icons/Warning2.gif" alt="" width="16" height="16"/>  
			                        	<span id="communicatorMessage">Please fix the following errors before proceeding.</span>
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
                          							<div class="row"></div>
													<table id="group" width="490px" >
							                            <thead>
							                                <tr>
							                                    <td class="panelTitle"><p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span class="style20">Choose how to send your inCyyte :</span></p></td>
							                              </tr>
							                            </thead>
							                            <tbody>
							                                <tr>
							                                </tr>
							                                <tr>
							                                </tr>
							                            </tbody>
								                       	<tfoot>
								                                
								                       	</tfoot>
    
                        							</table>
                        						</div>
                  						</div>
                  							
										<div class="panelTable">
									   		<div class="row">
									     		<div align="center"></div>
									      		<div class="rbCell" title="email to a group"> <input name="mail" type="radio" value="mail" checked  class="borderless" id="mail"/><label>By email</label></div>
									      		<div class="rbCell" title="post on your page"><input name="mail" type="radio" value="post" class="borderless" id="post"/><label>On Page</label></div>
									      		<div class="rbCell" title="Send to a postcode/town/country"><input name="mail" type="radio" value="area" class="borderless" id="area"/><label>Postal Region</label></div>
											</div>
										</div>
                    						
                  					<!-- End panel contents. -->
                					</div>
              					</div>
              						
					         	<div class="lightPanelBottom">
					           		<div class="cap"></div>
					        	</div>
            				</div>
            					
              					
				              
                            <form:form id="sendInCyyteForm" commandName="sendInCyyteForm"  method="post">
              					<!-- START MAIL -->            
                				<div id="mailWrap">  
                					<!-- Responses Panel. -->
                					<div class="questionlightPanel">
					                  	<div class="lightPanelTop">
					                    	<div class="cap"></div>
					                  	</div>
					                  	
                  						<div class="lightPanelBody">
                    						<div class="cap">
                      							<!-- Panel contents. -->
                        						<div class="contentWrap">
							                   		<table id="group" width="490px" >
							                            <thead>
							                                <tr>
							                                    <td class="panelTitle"><blockquote>
							                                      <p>&nbsp;&nbsp;&nbsp;<span class="style20">Create an email group</span></p>
							                                    </blockquote></td>
							                              </tr>
							                            </thead>
							                            <tbody>
							                                <tr>
							                                   <td class="style9"><form:input path="grpName" id="grpName" size="50" cssClass="inputwidth" title="Group Name" /></td>
							                                </tr>
							                                <!--
							                                <tr>
							                                   <td class="style9"><input name="grpType" type="text" id="grpType" size="50" class="inputwidth" title="Group Type" value="Categorise as Friends, Community, Work Colleagues etc .."/></td>
							                                </tr>
							                                -->
							                                <tr>
							                                   <td class="style9">        
							                                   		<%-- <form:select path="grpType" id="grpType" cssClass="editable-select inputwidth" title="Group Type">
							                                              <form:option selected="selected" value="Enter or select from list" />
							                                              <form:option value="Friend" />                                     
							                                              <form:option value="Community" />
							                                              <form:option value="Work Colleagues" />
							                                        </form:select> --%>
							                                        <form:select path="grpType" id="grpType" cssClass="editable-select inputwidth" title="Group Type">
																		<form:option selected="selected" value="Enter or select from list" />
																		<form:options items="${groupTypes}"/>
																	</form:select> 
							                                    </td>
							                                </tr>                                
							                            </tbody>
							                           <!-- tfoot>
							                                <tr>
							                                <td align="left">
							                                	<label class="panelLink" id="addUserGrp">
							                                    	<blockquote>
							                                  			<p>  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;select a previous group from your list</p>
							                                		</blockquote>
							                                    
							                                    </label>
							                                 </td>
							                                </tr>
							                          </tfoot-->
							    
							                        </table>
    
                        							</hr>
													
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
					                  	<div class="lightPanelTop">
					                    	<div class="cap"></div>
					                  	</div>
					                  	
                  						<div class="lightPanelBody">
                    						<div class="cap">
                      							<!-- Panel contents. -->
                        						<div class="contentWrap">
                        
						                      		<table id="responseOpts" width="490px" >
							                            <thead>
							                                <tr>
							                                    <td class="panelTitle"><blockquote>
							                                      <p>&nbsp;&nbsp;&nbsp;<span class="style20">Add Email Addresses for group / members</span></p>
							                                    </blockquote></td>
							                              </tr>
							                            </thead>
							                            <tbody>
							                                <tr>
							                                   <td class="style9"><input name="email1" type="text" id="email1" size="50" class="inputwidth" title="Email address" value=""/></td>
							                                </tr>
							                                <tr>
							                                   <td class="style9"><input name="email2" type="text" id="email2" size="50" class="inputwidth" title="Email address" value=""/></td>
							                                </tr>
							                                <tr>
							                                   <td class="style9"><input name="email3" type="text" id="email3" size="50" class="inputwidth" title="Email address" value=""/>&nbsp;&nbsp;&nbsp;<img src="images/icons/delete-icon2.png" alt="" width="12" height="12" id="delete" title="remove this option"/></td>
							                                </tr>
							                            </tbody>
							                            <tfoot>
							                                <tr>
							                                <td align="left">
							                                    <label class="panelLink" id="addEmail">
							                                    <blockquote>
							                                  		<p>  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;add another email address</p>
							                                	</blockquote>
							                                    </label>
							                                </td>
							                                </tr>
							                          	</tfoot>
						                        	</table>
                        							
                        							<form:hidden path="emailArr" id="responses" />
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
							                              	<input name="Create" type="submit" value="Edit your inCyyte" class="button" />
							                            </div>	 
							                            <div class="footerCell left"> </div>
							                            <div class="footerCell right">
							                            	<input class="button" id="send" type="button" value="Send inCyyte" tabindex="6" />
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
                
            					</div>  
              					<!-- END MAIL -->  
              				</form:form>          
							<form:form id="postInCyyteForm" commandName="postInCyyteForm"  method="post">
				              	<!-- START POST -->            
				              	<div id="postWrap" style="display:none"> 
				                    <div class="questionlightPanel">
				                      	<div class="lightPanelTop">
				                        	<div class="cap"></div>
				                      	</div>
				                      	<div class="lightPanelBody">
				                        	<div class="cap">
				                         	 	<!-- Panel contents. -->
				                          		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;THIS QUESTION WILL BE POSTED ON YOUR INCYYTE PAGE: ... ... ...
				                          		<!-- End panel contents. -->
				                        	</div>
				                        
				                      	</div>
				                      	<div class="lightPanelBottom">
				                        	<div class="cap"></div>
				                      	</div>   
				                    </div>  
				                    
				                    <div class="questionlightPanel">
				                      	<div class="lightPanelTop">
				                        	<div class="cap"></div>
				                      	</div>
				                      	<div class="lightPanelBody">
				                        	<div class="cap" >
				                        	<!-- Panel contents. -->
                        						<div class="contentWrap">
                        						
                        							<div class="panelTable">
                        								<p><span class="style20">Your incyyte page Name</span></p>
                        								<div class="row">							                            
								                            <div class="footerCell left">				                              
								                              	<span>Page Name:</span>
								                            </div>	 
								                            <div class="footerCell left"> <form:input path="pageName" id="pageName" size="30"  title="Enter the page name" />
							                                   		<%-- <img id="pageImg" src="${pageImgSrc}"/> --%>
							                                   		<br/><span id="pageName_warning" style="color:red"></span> 
							                                </div>
								                            <div class="footerCell right"></div>
							                          	</div>
							                          	<div class="row">							                            
								                            <div class="footerCell left">				                              
								                              	<span>Upload Logo / Image:</span>
								                            </div>	 
								                            <div class="footerCell left">
								                            	<span id="uploadDiv" >						                                          		
								                                	<span id="notLoaded">
								                                    	<form:input path="uploadedLogo" type="file" id="uploadedLogo" size="40" title="Add a picture or document related to your question"/> 
								                                       	<input type="button" id="uploadButton" value="Upload" />		                                          		
								                                        <span id="loadingDiv" style="display: none;"><img src="images/ajax-loader.gif" /></span>	
								                                        <br />	                                          		                                          	
								                                        <c:if test="${not empty uploadError}"><span id="uploadError" style="color:red;">${uploadError}.</span></c:if> 	
								                                 	</span>
								                                          	
								                                   	<span id="loaded" style="display: none;">
								                                    	<span id="loadText"> </span> &#160;&#160;&#160;&#160; <input type="button" id="uploadAnotherButton" value="Edit Upload" />
								                                    </span>
								                            	</span>
								                     		</div>
								                            <div class="footerCell right"></div>
							                          	</div>
							                          	<div class="row">							                            
								                            <div class="footerCell left">				                              
								                              	<span>Add Information / Strapline:</span>
								                            </div>	 
								                            <div class="footerCell left"> 
								                            	<form:textarea path="strapline" maxlength="${straplineMaxChar}" cols="30" rows="5" cssStyle="width: 350px;" cssClass="questionbox" id="strapline" title="Add your strapline" />
							                                </div>
								                            <div class="footerCell right" ></div>
							                          	</div>
							                          	<div class="row">							                            
								                            <div class="footerCell left">				                              
								                              	<span>protect this page?:</span>
								                            </div>	 
								                            <div class="footerCell left"> 
								                            	<form:radiobutton path="protectPage" value="Y"  cssClass="borderless"  title="Protect Page" />yes
								                            	&nbsp;&nbsp; 
							                                	<form:radiobutton path="protectPage" value="N"  cssClass="borderless"  title="Protect Page" />no
							                                </div>
								                            <div class="footerCell right" ></div>
							                          	</div>
							                        </div>
							                       
							                   </div>				                        	
				                        	</div>				                        
				                      	</div>
				                      	<div class="lightPanelBottom">
				                        	<div class="cap"></div>
				                      	</div>   
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
							                            <div class="footerCell left">				                              
							                              	<input name="Create" type="submit" value="Back" class="button" />
							                            </div>	 
							                            <div class="footerCell left"> </div>
							                            <div class="footerCell right">
							                            	<input class="button" id="postScript" type="button" value="Post inCyyte" tabindex="6" />
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
				             	</div>  
				              	<!-- END POST -->            
				            </form:form>
       						<!-- START AREA-->            
				              <div id="areaWrap"  style="display:none"> 
				                
				                <div class="questionlightPanel">
				                  <div class="lightPanelTop">
				                    <div class="cap"></div>
				                  </div>
				                  <div class="lightPanelBody">
				                    <div class="cap">
				                      <!-- Panel contents. -->
				                        <div class="contentWrap">
										
										<div>
				                         <table width="490px" >
				                                <tr>
				                                    <td class="panelTitle"><blockquote>
				                                      &nbsp;&nbsp;&nbsp;<span class="style20">Where to send your inCyyte:</span>
				                                      &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				                                      <input type="text" id='ac_country1' name='country' title="Change country" class="ui-widget-content2 ui-corner-all"/>                                                                           
				                                    </blockquote>
				                                    </td>
				                              </tr>							
				                                <tr>
				                                   <td class="style9">        
				                                   			<select name="locality" class="inputwidth" id="locality" title="select region">
				                                              <option selected="selected">Postcode</option>
				                                              <option>Region</option>                                                                                   
				                                              <option>County</option>
				                                            </select>
				                                  </td>
				                                </tr>
										</table>                        
										</div>
				
										<div id="postcodeSec">
				                         <table width="490px" >
				                                <!-- POSTCODE -->
				                                <tr>
				                                   <td class="style9 panelTitle"><input name="postcode" type="text" id="postcodex" size="50" class="inputMidwidth" title="Enter a postcode" value="RM16 6RL"/>                                
				                                   &nbsp;&nbsp;&nbsp;<span class="style20" title="target audience">3255</span>
				                                   </td>
				                                </tr>
										</table>                        
										</div>
				
										<div id="neighbourhoodSec" style="display:none">
				                         <table width="490px" >
				                                <!-- NEIGHBOURHOOD -->
				                                <tr>
				                                   <td class="style9 panelTitle"><input name="Neighbourhood" type="text" id="Neighbourhood" size="50" class="inputwidth" title="Enter your Region" value="Enter your Region"/>                                
				                                   </td>
				                                </tr>
										</table>                        
										</div>
				
										<div id="countySec" style="display:none">
				                         <table width="490px" >
				                                <!-- COUNTY -->
				                                <tr>
				                                   <td class="style9">
				                                   <select name="county" class="inputwidth" id="county" title="select county">
														<optgroup label="England">
														<option>Bedfordshire</option>
														<option>Berkshire</option>
														<option>Bristol</option>
														<option>Buckinghamshire</option>
														<option>Cambridgeshire</option>
														<option>Cheshire</option>
														<option>Cornwall</option>
														<option>Cumbria</option>
														<option>Derbyshire</option>
														<option>Devon</option>
														<option>Dorset</option>
														<option>Durham</option>
														<option>East Riding of Yorkshire</option>
														<option>East Sussex</option>
														<option selected="selected" >Essex</option>
														<option>Gloucestershire</option>
														<option>Greater Manchester</option>
														<option>Hampshire</option>
														<option>Herefordshire</option>
														<option>Hertfordshire</option>
														<option>Isle of Wight</option>
														<option>Kent</option>
														<option>Lancashire</option>
														<option>Leicestershire</option>
														<option>Lincolnshire</option>
														<option>London</option>
														<option>Merseyside</option>
														<option>Norfolk</option>
														<option>North Yorkshire</option>
														<option>Northamptonshire</option>
														<option>Northumberland</option>
														<option>Nottinghamshire</option>
														<option>Oxfordshire</option>
														<option>Rutland</option>
														<option>Shropshire</option>
														<option>Somerset</option>
														<option>South Yorkshire</option>
														<option>Staffordshire</option>
														<option>Suffolk</option>
														<option>Surrey</option>
														<option>Tyne and Wear</option>
														<option>Warwickshire</option>
														<option>West Midlands</option>
														<option>West Sussex</option>
														<option>West Yorkshire</option>
														<option>Wiltshire</option>
														<option>Worcestershire</option>
														</optgroup>
														<optgroup label="Wales">
														<option>Anglesey</option>
														<option>Brecknockshire</option>
														<option>Caernarfonshire</option>
														<option>Carmarthenshire</option>
														<option>Cardiganshire</option>
														<option>Denbighshire</option>
														<option>Flintshire</option>
														<option>Glamorgan</option>
														<option>Merioneth</option>
														<option>Monmouthshire</option>
														<option>Montgomeryshire</option>
														<option>Pembrokeshire</option>
														<option>Radnorshire</option>
														</optgroup>
														<optgroup label="Scotland">
														<option>Aberdeenshire</option>
														<option>Angus</option>
														<option>Argyllshire</option>
														<option>Ayrshire</option>
														<option>Banffshire</option>
														<option>Berwickshire</option>
														<option>Buteshire</option>
														<option>Cromartyshire</option>
														<option>Caithness</option>
														<option>Clackmannanshire</option>
														<option>Dumfriesshire</option>
														<option>Dunbartonshire</option>
														<option>East Lothian</option>
														<option>Fife</option>
														<option>Inverness-shire</option>
														<option>Kincardineshire</option>
														<option>Kinross</option>
														<option>Kirkcudbrightshire</option>
														<option>Lanarkshire</option>
														<option>Midlothian</option>
														<option>Morayshire</option>
														<option>Nairnshire</option>
														<option>Orkney</option>
														<option>Peeblesshire</option>
														<option>Perthshire</option>
														<option>Renfrewshire</option>
														<option>Ross-shire</option>
														<option>Roxburghshire</option>
														<option>Selkirkshire</option>
														<option>Shetland</option>
														<option>Stirlingshire</option>
														<option>Sutherland</option>
														<option>West Lothian</option>
														<option>Wigtownshire</option>
														</optgroup>
														<optgroup label="Northern Ireland">
														<option>Antrim</option>
														<option>Armagh</option>
														<option>Down</option>
														<option>Fermanagh</option>
														<option>Londonderry</option>
														<option>Tyrone</option>
														</optgroup>
														</select>                                                                  
				                                   </td>
				                                </tr>
										</table>                        
										</div>
				                        
				                        </hr>
				                      </div>
				                      <!-- End panel contents. -->
				                    </div>
				                  </div>
				                  <div class="lightPanelBottom">
				                    <div class="cap"></div>
				                  </div>
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
				                            <div class="footerCell left"><input class="button" id="backBtn" type="button" value="Back" tabindex="5" /></div>
				                            <div class="footerCell left"> </div>
				                            <div class="footerCell right"><input class="button" id="areaBtn" type="button" value="Send inCyyte" tabindex="6" /></div>
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
				            </div>  
				            <!-- END AREA -->         				            
          					<!-- End MAIN panel contents. -->
              				</div>
              				
				           	<div class="mainPanelBottom">
				                <div class="cap"></div>
				           	</div>
			            </div>
			            <p>
			              <!-- End panel. -->
			            </p>
			            <p>&nbsp;  
			          	</p>
			        </div>
				
        
			        <div id="adside">
			         	<h3 align="left" class="style11"><span class="style18">&nbsp;&nbsp;&nbsp;Why use inCyyte?</span></h3>
			        	<h3 align="right" class="style11">			
			        	<img src="images/advert1.png" width="204" height="211" border="0" usemap="#Map" />
						<map name="Map" id="Map"><area shape="rect" coords="123,184,181,199" href="#" title="find out more"/></map>
				  	</div>
				  	
			    	<div id="adside">
			        	<h3 align="left" class="style11"><span class="style18">&nbsp;&nbsp;&nbsp;Post free public notices</span></h3>
			        	<h3 align="right" class="style11">			
			        	<img src="images/advert2.png" width="204" height="211" border="0" usemap="#Map2" />
						<map name="Map2" id="Map2"><area shape="rect" coords="129,183,182,199" href="#" title="sign up to inCyyte" /></map>
					</div>    
			        
			 		<div id="adside">
			        	<h3 align="left" class="style11"><span class="style18">&nbsp;&nbsp;&nbsp;Our anonimity rule</span></h3>
			        	<h3 align="right" class="style11">			
			        	<img src="images/advert3.png" width="204" height="189" border="0" usemap="#Map3" />
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
    		<!--container end-->
    
			</div>
		
		
		<div class="demo">
		
		
			<form:form  id="loginForm"  commandName="loginForm" method="post" >	  		
	  			<form:hidden path="user_email" id="li_userEmail" />
	  			<form:hidden path="login_email" id="loginEmail" />
	  			<form:hidden path="login_pwd" id="password-password" />	  		
          	</form:form>
          	
          	<form:form  id="signUpForm"  commandName="signUpForm" method="post"> 
	       		<form:hidden path="username" id="su_username"/>
	       		<form:hidden path="su_email" id="sn_email" />
	       		<form:hidden path="su_password" id="sn_password"/> 
	       		
	            <form:hidden path="ac_country" id="su_country" />
	            <form:hidden path="postcode" id="su_postcode" />
	            <form:hidden path="postal_area" id="su_postalarea" />
	            <form:hidden path="ageGroup" id="su_ageGroup" />
	            <form:hidden path="gender" id="su_gender" />
	            <form:hidden path="activate_act" id="su_activate" />
	       </form:form>

			<div id="dialog-form" title="New User Sign Up">
				<p class="validateTips_df">Please sign up to send your inCyyte.</p>
			
				<p align="right"><img src="images/signup_step1sml.png" title="Step 1." width="66" height="22" /></p>
				<form>
					<fieldset>
						<label for="username" class="dialogLabel">Choose a username</label>
						<input type="text" name="username" id="username" class="dialogText ui-widget-content ui-corner-all" value=""/>
						<label for="su_email" class="dialogLabel">Email Address</label>
						<input type="text" name="su_email" id="su_email" class="dialogText ui-widget-content ui-corner-all" value=""/>
						<label for="confirm_email" class="dialogLabel">Re-Enter Email Address</label>
						<input type="text" name="confirm_email" id="confirm_email" class="dialogText ui-widget-content ui-corner-all" value=""/>
						<label for="su_password" class="dialogLabel">Choose a password</label>
						<input type="password" name="su_password" id="su_password" class="dialogText ui-widget-content ui-corner-all" value=""/>
						<br/>
						<label class="panelLink" id="login-user" title="Members login here">Already have an Account?</label>
				
					</fieldset>
				</form>
			</div>
			
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
						</br>
						</br>		
						<label class="panelLink" id="login-user3" title="Members login here">Already have an Account?</label>
				
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
						<input type="password" name="login_pwd" id="login_pwd" value="" class="dialogText ui-widget-content ui-corner-all" />
						<label class="panelLink" id="fgtpwd-user" title="Request new password.">Forgot password?</label>
				
						<label class="panelLink"  style="float:right;" id="signup-user" title="New User - Sign up Now">Sign Up</label>
				
					</fieldset>
				</form>
			</div>
			
			<div id="fgtpwd-dialog-form" title="Forgotten Password">
				<p class="validateTips_fdf">Request a new password.</p>
			
				<form>
					<fieldset>
						<label for="user_email" class="dialogLabel">Email</label>
						<input type="text" name="user_email" id="user_email" class="dialogText ui-widget-content ui-corner-all" />
				        <BR/>
						<span>Enter your email address and your credentials will be sent to you.</span>
					</fieldset>
				</form>
			</div>			
			
			<div id="activate-dialog-form" title="Activate Account">
				<p class="validateTips_adf">Enter your Activation code.</p>
			    
				<p align="right"><img src="images/signup_step3.png" title="Final step"  width="66" height="22" /></p>
			
				<form>
					<fieldset>
					  <label for="activate_act" class="dialogLabel" id="activate_msg"></label>
						<input type="text" name="activate_act" id="activate_act" value="" class="ui-widget-content ui-corner-all" />
				
						</br>
						</br>
						<label class="panelLink" id="resend_activation_cd" title="I have not received my activation code...">please resend my activation code.</label>
				
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


