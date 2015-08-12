<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ page import="com.incyyte.app.web.controller.MultiController"%>

<%@ include file="/WEB-INF/jsp/include.jsp"%>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Display Download File</title>

<script type="text/javascript" src="js/jquery-1.5.1.min.js"></script>
<script type="text/javascript" src="js/jquery-ui-1.8.13.custom.min.js"></script>
<script type="text/javascript" src="js/external/jquery.bgiframe-2.1.2.js"></script>

<script type="text/javascript" src="js/jquery.tools.min.js"></script>
<!-- flowplayer javascript component -->
<script type="text/javascript" src="flowplayer/flowplayer-3.2.9.min.js"></script>


<!-- some minimal styling, can be removed -->
<link rel="stylesheet" type="text/css" href="flowplayer/style.css" />


</head>
<body>



	<div align="center">
		<c:choose>
			<c:when test="${ftype == 'image'}">
				<img src="${uploadfile}" width="620" height="540" />
			</c:when>
			<c:when test="${ftype == 'video'}">

				<!-- player container-->
				<a href="${uploadfile}" class="player" id="player2"> </a>

				<div id="player" class="player" style="width: 100%; height: 95%;"></div>
				<div id="info" class="info">
					<!-- You have Flash version 9.115 or above. Enjoy high quality video -->
				</div>

				<!-- this script block will install Flowplayer inside previous A tag -->
				<script>
										
											flowplayer("player", "flowplayer/flowplayer-3.2.10.swf",
											  {   
												
											  	// clip does not start automatically
											  	clip: {
											  	            url: '${uploadfile}',
											  	            autoPlay: false
											  	 },
											  	 // default controls with the same background color as the page background
											  	 plugins:  {
											  	             controls:  { 
											  	            	 
														  	            	backgroundColor: '#000',
											  	                            backgroundGradient: 'none',                
											  	                                            
											  	                            scrubber: true,                
											  	                            height: 40,                
											  	                            sliderColor: '#333333',                
											  	                            progressColor: '#ca000a',                
											  	                            bufferColor: '#666666',                
											  	                            autoHide: false
											  	                      	}        
											  	           	},         
											  	// fiercy red background color with a little gradient and curving        
												canvas: {            
															backgroundColor: '#ca000a',            
															backgroundGradient: [0.3, 0],            
															borderRadius: 10        
														},         
												// set screen dimensions so that it goes near the canvas borders        
												screen: {            
															width: 494,            
															height: 303,            
															top: 3        
														} 
												});
										
										</script>

			</c:when>
			<c:otherwise>
				<%
        			MultiController.downloadFile(request, response);
        		%>
			</c:otherwise>
		</c:choose>


	</div>

	<!-- End MAIN panel contents. -->

<script type="text/javascript" src="ui/js/googleanalytics.js"></script>
</body>
</html>
