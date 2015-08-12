<%@ include file="/WEB-INF/jsp/include.jsp"%>
<%@page import="com.incyyte.app.web.RequestKeys"%>
<%@page import="com.incyyte.app.domain.Dashboard"%>

<script>
	$(document).ready(function() {
		$.ajaxSetup({ cache: false });
		$("#portletLeftBody").load("dashboard.cyt"); 		
		setInterval(function(){  			 
			$.ajax({url: "dashboard.cyt",
				cache: false,  
				success: function(html){ 	
					$("#portletLeftBody").empty();
					$("#portletLeftBody").append(html);  
				}
			});
		}, 10000);
   		$.ajaxSetup({ cache: false });
	});
</script>

<div class="portletLeftTop"></div>
<div class="portletLeftBodyW" id="portletLeftBody">
	<div class="style24 panelLink" id="portletLinks2"><strong> INCYYTE POLLS</strong></div><BR/>    
	<div id="portletLinks" class="panelLink">		
		Incoming (${dashboard.incoming})
	</div><BR/>
	<div id="portletLinks" class="panelLink"> 		
	    Sent (${dashboard.sent})
	</div><BR/>
	<div id="portletLinks" class="panelLink">		
	    Completed (${dashboard.completed})
	</div><BR/>
	<div id="portletLinks" class="panelLink"> 		
	   	Petitions (${dashboard.petitions})	    	
	</div><BR/>
    
</div>
<div class="portletLeftBodyG"></div>
<div class="portletLeftBottom"></div>

<br>
<div class="portletLeftTop"></div>
<div class="portletLeftBodyW">
		<div class="style24 panelLink" id="portletLinks"><strong> BUSINESS SPLASHES</strong></div>
		<BR/>
		<div id="portletLinks" class="panelLink"> Categories (unread 25)</div>
		<BR/>
		<div id="portletLinks" class="panelLink"> Inbox (20)</div>
		<BR/>
		<div id="portletLinks" class="panelLink"> Sent (2)</div>
		<BR/>
     <div id="portletLinks" class="panelLink"> My ads (6)</div>
</div>
<div class="portletLeftBodyG"></div>
<div class="portletLeftBottom"></div>
