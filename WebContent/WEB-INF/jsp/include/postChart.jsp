
    <!-- 
    Smart developers always View Source. 
    
    This application was built using Adobe Flex, an open source framework
    for building rich Internet applications that get delivered via the
    Flash Player or to desktops via Adobe AIR. 
    
    Learn more about Flex at http://flex.org 
    // -->
    
    <%  String incyyteCode = (String)request.getSession().getAttribute("incyyteCode") ;%>
    <%  String memberId = (String)request.getSession().getAttribute("memberId") ;%>
     
    
        <style type="text/css" media="screen">            
            object:focus { outline:none; }
            #flashContent { display:none; }
        </style>
	   	<!-- Enable Browser History by replacing useBrowserHistory tokens with two hyphens -->
	    <!-- BEGIN Browser History required section -->
	    <link rel="stylesheet" type="text/css" href="flex/history/history.css" />
	    <script type="text/javascript" src="flex/history/history.js"></script>
	    <!-- END Browser History required section -->  
    
	    <script type="text/javascript" src="flex/swfobject.js"></script>
	    
	    <script type="text/javascript">
	            // For version detection, set to min. required Flash Player version, or 0 (or 0.0.0), for no version detection. 
	            var swfVersionStr = "10.2.0";
	            // To use express install, set to playerProductInstall.swf, otherwise the empty string. 
	            var xiSwfUrlStr = "flex/playerProductInstall.swf";
	            var flashvars = {};  
	            flashvars.incyyteCode = "<%=incyyteCode %>"; 
    		    flashvars.memberId = "<%=memberId%>"; 
	            var params = {};
	            params.quality = "high";
	            params.bgcolor = "#ffffff";
	            params.allowscriptaccess = "sameDomain";
	            params.allowfullscreen = "true";
	            params.wmode = "transparent";
	            var attributes = {};
	            attributes.id = "postChart";
	            attributes.name = "postChart";
	            attributes.align = "middle";
	            swfobject.embedSWF(
	                "flex/postChart.swf", "flashContent", 
	                "100%", "100%", 
	                swfVersionStr, xiSwfUrlStr, 
	                flashvars, params, attributes);
	            // JavaScript enabled so display the flashContent div in case it is not replaced with a swf object.
	            swfobject.createCSS("#flashContent", "display:block;text-align:left;");
	    </script>    
	     
   		<!-- SWFObject's dynamic embed method replaces this alternative HTML content with Flash content when enough 
             JavaScript and Flash plug-in support is available. The div is initially hidden so that it doesn't show
             when JavaScript is disabled.
        -->
        <div id="flashContent">
	    	<p>
	       		To view this page ensure that Adobe Flash Player version 
	            10.2.0 or greater is installed. 
	      	</p>
	   		<script type="text/javascript"> 
	                var pageHost = ((document.location.protocol == "https:") ? "https://" : "http://"); 
	                document.write("<a href='http://www.adobe.com/go/getflashplayer'><img src='" 
	                                + pageHost + "www.adobe.com/images/shared/download_buttons/get_flash_player.gif' alt='Get Adobe Flash player' /></a>" ); 
	     	</script> 
        </div>        
	       
	   	<noscript>
	            <object classid="clsid:D27CDB6E-AE6D-11cf-96B8-444553540000" width="100%" height="600" id="postChart">
	                <param name="movie" value="flex/postChart.swf" />
	                <param name="wmode" value="transparent">
	                <param name="quality" value="high" />
	                <param name='flashVars' value='incyyteCode=<%=incyyteCode %>&memberId=<%=memberId%>'/>
	                <param name="bgcolor" value="#ffffff" />
	                <param name="allowScriptAccess" value="sameDomain" />
	                <param name="allowFullScreen" value="true" />
	                <param name='flashVars' value='incyyteCode=<%=incyyteCode %>&memberId=<%=memberId%>'/>	
	                <embed src="flex/postChart.swf" quality="high" bgcolor="#ffffff"		
						width="100%" height="100%"  name="postChart" align="middle" 
		                play="true"
		                loop="false"
		                quality="high"
		                allowScriptAccess="sameDomain"
		                flashVars="incyyteCode=<%=incyyteCode %>&memberId=<%=memberId%>"
		                type="application/x-shockwave-flash"
		                pluginspage="http://www.adobe.com/go/getflashplayer">
	                </embed>
	       	    </object>
        	</noscript> 
	
