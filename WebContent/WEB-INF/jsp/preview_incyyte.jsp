<%@ include file="/WEB-INF/jsp/include.jsp"%>
<%@ page import="com.incyyte.app.web.model.IncyyteModel" %>

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>InCyyte Preview</title>
	    <style type="text/css">
	    </style>	    
	    
	    <script type="text/javascript" src="css/colorbox/jquery.min.js"></script>
	    <script type="text/javascript" src="css/colorbox/jquery.colorbox.js"></script>
	    <script>
			$(document).ready(function(){
				//Examples of how to assign the ColorBox event to elements
				$(".group1").colorbox({rel:'group1'});
				$(".group2").colorbox({rel:'group2', transition:"fade", width:"35%", height:"50%"});
				$(".group3").colorbox({rel:'group3', transition:"none", width:"35%", height:"50%"});
				$(".group4").colorbox({rel:'group4', slideshow:true, width:"35%", height:"50%"});
				$(".ajax").colorbox();
				$(".youtube").colorbox({iframe:true, innerWidth:425, innerHeight:344});
				$(".iframe").colorbox({iframe:true, width:"35%", height:"50%"});
				$(".inline").colorbox({inline:true, width:"50%"});
				$(".callbacks").colorbox({
					onOpen:function(){ alert('onOpen: colorbox is about to open'); },
					onLoad:function(){ alert('onLoad: colorbox has started to load the targeted content'); },
					onComplete:function(){ alert('onComplete: colorbox has displayed the loaded content'); },
					onCleanup:function(){ alert('onCleanup: colorbox has begun the close process'); },
					onClosed:function(){ alert('onClosed: colorbox has completely closed'); }
				});
				
				//Example of preserving a JavaScript event for inline calls.
				$("#click").click(function(){ 
					$('#click').css({"background-color":"#f00", "color":"#fff", "cursor":"inherit"}).text("Open this window again and this message will still be here.");
					return false;
				});
			});
		</script>
	
	    <link href="css/common.css" rel="stylesheet" type="text/css" />
	    <link href="css/preview_incyyte.css" rel="stylesheet" type="text/css" />
	    <link rel="stylesheet" href="css/demos.css"/>
	    <link rel="stylesheet" href="css/colorbox/colorbox.css" />
	    
	    <style type="text/css">
			<!--
			.style12 {
				color: #33527F;
				font-size: 220%;
			}
			.style14 {
				font-size: 11px
			}
			-->
	    </style>
	    <script Language="JavaScript">
		<!-- Script courtesy of http://www.web-source.net - Your Guide to Professional Web Site Design and Development
		function load(urlstring) {
			var load = window.open(urlstring,'','scrollbars=no,menubar=no,height=600,width=800,resizable=yes,toolbar=no,location=no,status=no');
		}
		// -->
		</script>
	</head>
		
	<body>

		<%
			IncyyteModel model = (IncyyteModel) session.getAttribute("createInCyyteForm");
		%>
		
		<c:set var="incyyteModel" scope="page" value="<%=model%>" />
		
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
        	<div id="top"> </div>

      		<div id="content">        
            	<p><span class="style12">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;PREVIEW YOUR INCYYTE </span>
            	<p>&nbsp;</p>
                   
				<div class="preview">
                    	<div class="previewTable">
                          <div class="row">
                            <div class="questionCell"> 
                            	<c:out value="${incyyteModel.incyyte}" />
                            </div>
                          </div>
                          <div class="row"><div class="spacerCell"></div></div>                          
                        </div>
                          
                    	<div class="previewTable">
                          	<c:forEach var="ans" items="${incyyteModel.answers}">

	                          <div class="row">
	                            <div class="answButtonCell"><a href="#"><img src="images/vote_button.png" width="67" height="32" /></a></div>
	                            <div class="answerCell">${ans.answerOption}</div>
	                          </div>
	                       	</c:forEach>
	                     	<%if(model.geteLink() != null && !model.geteLink().equalsIgnoreCase("http://...")){ %>
                          		<div class="row">                         
                            		<div class="answButtonCell"><b>View Link: </b></div>
                            		<div class="answerCell">
                            			<a href="javascript:load('<c:out value="${incyyteModel.eLink}" />')" ><c:out value="${incyyteModel.eLink}" /></a>
                            		</div>
                            	</div>
                          	<%} %>
                          	<%if(model.getUploadedFile() != null && model.getUploadedFile().getSize() > 0){ %>
                          		<c:choose>
	                          		<c:when test="${incyyteModel.uploadedFileType == 'image'}">
	                          			</br>
										<div>		                          			
		                          			<div align="right">
		                          				<a class="group4"  href="${incyyteModel.uploadedFileLocation}" title="${incyyteModel.fileName}">
		                          					<img src="${incyyteModel.uploadedFileLocation}"  style="width:100px; height:70px" />
		                          				</a>
											</div>
											<div align="center">
												<c:out value="${incyyteModel.fileName}" />
											</div>
										</div>
									</c:when>
									<c:when test="${incyyteModel.uploadedFileType == 'video'}">
										</br>
										<div> 
										                  			
		                          			<div align="right">
		                          				<a class="iframe" href="displayuploadfile.cyt?uploadfile=${incyyteModel.uploadedFileLocation}&ctype=${incyyteModel.contentType}&ftype=${incyyteModel.uploadedFileType}&fname=${incyyteModel.fileName}" 
		                          				title="${incyyteModel.fileName}">
													<img src="images/Video-Icon.png"  border="0" style="width:100px; height:70px"  />													
												</a>
											</div>
											<div align="center" >
												<c:out value="${incyyteModel.fileName}" />	
											</div>
										</div>
									</c:when>
									<c:otherwise>
		                          		<div class="row">		                          			
		                          			<div class="answButtonCell"><b>File Link: </b></div>
		                            		<div class="answerCell">
		                            			<a href="javascript:load('displayuploadfile.cyt?uploadfile=${incyyteModel.uploadedFileLocation}&ctype=${incyyteModel.contentType}&ftype=${incyyteModel.uploadedFileType}&fname=${incyyteModel.fileName}')" >
		                            				<c:out value="${incyyteModel.fileName}" />
		                            			</a>
		                            		</div>
		                          		</div>	
	                          		</c:otherwise>
                          		</c:choose>
                          	<%} %>					
                        </div>
        		</div>
                    <!--preview End-->
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
		                        		<form:form commandName="createInCyyteForm" method="post">	                            	
	                            			<div class="footerCell left">				                              
				                              	<input name="Create" type="submit" value="Edit your inCyyte" class="button" />
				                            </div>	                            			
	                            			<div class="footerCell left"> </div>
	                            			<div class="footerCell right">			                            	
				                            	<input name="Send" type="submit" value="Continue" class="button" id="create"/>			                            	
				                            </div>
			                            </form:form>
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
        <!-- This contains the hidden content for inline calls -->
		<div style='display:none'>
			<div id='inline_content' style='padding:10px; background:#fff;'>
			<p><strong>This content comes from a hidden element on this page.</strong></p>
			<p>The inline option preserves bound JavaScript events and changes, and it puts the content back where it came from when it is closed.</p>
			<p><a id="click" href="#" style='padding:5px; background:#ccc;'>Click me, it will be preserved!</a></p>
			
			<p><strong>If you try to open a new ColorBox while it is already open, it will update itself with the new content.</strong></p>
			<p>Updating Content Example:<br />
			<a class="ajax" href="../content/flash.html">Click here to load new content</a></p>
			</div>
		</div>
    	<div id="adside">
			<h3 align="left" class="style11 style14">&nbsp;</h3>
			<h3 align="left" class="style11 style14">&nbsp;</h3>
			<h3 align="left" class="style11 style14">Why use incyyte?</h3>
		  	<p><img src="images/advert1.png" width="204" height="211" border="0" usemap="#Map" />
				<map name="Map" id="Map"><area shape="rect" coords="124,184,182,199" title="find out more" /></map>
			</p>
	  	</div>
     	<div id="adside">
			<h3 align="left" class="style11 style14">Our anonimity rule</h3>
                
            <p><img src="images/advert3.png" width="204" height="189" border="0" usemap="#Map2" />
			<map name="Map2" id="Map2"><area shape="rect" coords="124,162,180,175" title="find out more" /></map></p>
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

<script type="text/javascript" src="ui/js/googleanalytics.js"></script>
	</body>
</html>


