<%@page import="com.incyyte.app.web.SessionKeys"%>
<%@page import="com.incyyte.app.domain.User"%>
<%@page import="com.incyyte.app.service.util.Utility"%>
<%@ include file="/WEB-INF/jsp/common/include.jsp" %>

<link rel="stylesheet" href="ui/css/reset.css">
<link rel="stylesheet" href="ui/css/style.css">
<link rel="stylesheet" href="ui/css/layout.css">
<link rel="stylesheet" href="ui/css/buttons.css"  media="screen">
<link rel="stylesheet" href="ui/css/style_login.css">
<link rel="stylesheet" href="ui/css/accordionmenu.css" type="text/css">
<link rel="stylesheet" href="ui/css/icons_sprites.css">
<link rel="stylesheet" href="ui/css/form_elements.css">
<link rel="stylesheet" href="fonts/fonts_stylesheet.css">
<link rel="stylesheet" href="ui/modal/colorbox.css">
<script src="ui/js/jquery-1.3.2.min.js"></script>
<script src="ui/js/jquery-1.7.1.js"></script>
<script src="ui/js/jquery-1.8.2.min.js"></script>
<script src="../../ui/js/login.js"></script>
<script src="ui/js/jquery.color.js"></script>
<script src="ui/js/search_script.js"></script>
<script src="ui/modal/colorbox/jquery.colorbox.js"></script>
<script src="ui/js/jquery_dashboard_rating.js" type="text/javascript"></script>
<script src="ui/js/star_rating.js" type="text/javascript"></script>
<script src="ui/js/customInput.jquery.js" type="text/javascript"></script>

<!-- Left Navigation script starts here -->
<script src="ui/js/left_menu.js"></script>
<!-- Left Navigation script ends here -->
<!-- Chart Script Start here -->
<script src="ui/js/charts/js/charts_sent_incyyte.js"></script>
<script src="ui/js/charts/js/highcharts.js"></script>
<!-- Chart Script end here -->
<!-- Rating bar and profile starts here -->
<link rel="stylesheet" type="text/css" href="ui/css/ratingbar.css" />
<!-- Rating bar and profile ends here -->
<!--- placeholder Starts----->
<script>
    // placeholder polyfill
    $(document).ready(function(){
        function add() {
            if($(this).val() == ''){
                $(this).val($(this).attr('placeholder')).addClass('placeholder');
            }
        }

        function remove() {
            if($(this).val() == $(this).attr('placeholder')){
                $(this).val('').removeClass('placeholder');
            }
        }

        // Create a dummy element for feature detection
        if (!('placeholder' in $('<input>')[0])) {

            // Select the elements that have a placeholder attribute
            $('input[placeholder], textarea[placeholder]').blur(add).focus(remove).each(add);

            // Remove the placeholder text before the form is submitted
            $('form').submit(function(){
                $(this).find('input[placeholder], textarea[placeholder]').each(remove);
            });
        }	
    });
   	
</script>
<!--- placeholder Ends----->
<script type="text/javascript">
	// Run the script on DOM ready:
	$(function(){
		$('input').customInput();
	});
</script>
<!--- Modal ----->
<script>
	$(document).ready(function(){
		//Examples of how to assign the ColorBox event to elements
		$(".photos").colorbox({rel:'photos'});
		$(".group2").colorbox({rel:'group2', transition:"fade"});
		$(".group3").colorbox({rel:'group3', transition:"none", width:"75%", height:"75%"});
		$(".group4").colorbox({rel:'group4', slideshow:true});
		$(".ajax").colorbox();
		$(".youtube").colorbox({iframe:true, innerWidth:425, innerHeight:344});
		$(".vimeo").colorbox({iframe:true, innerWidth:500, innerHeight:409});
		$(".iframe").colorbox({iframe:true, width:"80%", height:"80%"});
		$(".inline").colorbox({inline:true});
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
<!--- Mddal--------------->
<script type="text/javascript" src="ui/js/ZeroClipboard.js"></script>
<script type="text/javascript" src="ui/js/ddaccordion.js"></script>


<!--[if gte IE 8]>
<style>
	.custom-checkbox input, 
	.custom-radio input {
	left: -3px;
</style>
<![endif]-->
<!--[if lt IE 9]>
   <script src="ui/js/html5.js"></script>
<![endif]-->
<!--[if lt IE 9]>
    <meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7">
  <link href="ui/css/iestyle.css" media="screen" rel="stylesheet" type="text/css" />
  <![endif]-->
  
  <!--[if gte IE 9]>
 <link href="ui/css/gte_ie9.css" media="screen" rel="stylesheet" type="text/css" />
<![endif]-->

<div class="my_poll">		
	<!-- My Poll Pages Modal Preview-->      	
	<div id="modal_preview_poll_page" >
		<h2 class="modal_heading1">My Poll Page <span>Preview</span> </h2>
	  	<div id="modal_preview_poll_inner">
	      	<table width="100%" border="0" cellspacing="0" cellpadding="0">
	        	<tr>
	          		<td width="11%" valign="top" ><img src="ui/images/default_avatar.png" width="46" height="46" alt="User Photo" class="photoframe"></td>
	          		<td width="89%">
	          			<table width="100%" border="0" cellspacing="0" cellpadding="0" class="font_12px">
	                 		<c:if test="${not empty myPollPage.createdBy}">
		                 		<tr>
		                 			<td width="12%">Creater:</td>
		                   			<td width="88%" class="font_12px_strong">${myPollPage.createdBy}</td>
		                		</tr>
	                		</c:if>
	                 		<tr>
             					<td>Created:</td>
             					<td>${myPollPage.createdDate}</td>
             				</tr>
             				<tr>
             					<td>Closure:</td>
             					<td>${myPollPage.closureDate}</td>
             				</tr>
             				<c:if test="${not empty myPollPage.group}">
		              			<tr>	
		              				<td>Group:</td>
		                			<td>${myPollPage.group.groupName}</td>
		                		</tr>
	                		</c:if>	                		
	            		</table>
	            	</td>
	       		</tr>
	      	</table>
	      	<br>
	
	      	<table width="100%" border="0" cellspacing="0" cellpadding="0">
	       		<tr>
	        		<td width="60%" valign="top"><p class="heading7">${myPollPage.incyyte}</p>
	          			<c:choose>
							<c:when test="${not empty myPollPage.uploadLocation}">
								<c:choose>
									<c:when test="${myPollPage.upload_type == 'video'}">						      					
										<a href="${myPollPage.uploadLocation}" class="videos">
											<img src="ui/images/video_thumb.png" width="322" height="190" alt="tour" />
											</a> 
						  			</c:when>				      				
						  			<c:when test="${myPollPage.upload_type == 'image'}">
						  					<a href="${myPollPage.uploadLocation}"  class="group1">
												<img src="${myPollPage.uploadLocation}" width="273" height="191" alt="tour" />
											</a> 					      					
									</c:when>
									<c:otherwise>						      					
									   		<a href="${myPollPage.uploadLocation}" class="group1">
												<img src="ui/images/view_docs_thumb.png" width="136" height="190" alt="tour" />
											</a> 
									</c:otherwise>
								</c:choose>	
							</c:when>
							<c:otherwise>
									<img src="ui/images/photo1.png" width="273" height="191" alt="tour" />      					
							</c:otherwise>
						</c:choose>        
				  	</td>
	           		<td width="40%"  class="grid_6b">
	           			<table width="100%" border="0" cellspacing="0" cellpadding="0">
			                 <tr>
			               		<td class="view_text">View</td>
			                   	<td>&nbsp;</td>
			                 </tr>
			                 <c:forEach var="ans" items="${myPollPage.answers}" varStatus="status">
			                 	<tr valign="top">                        		
			                   		<td>
			                   			<c:choose>
			                   				<c:when test="${not empty ans.uploadCDN_url}">
			                   					<a href="${ans.uploadCDN_url}" class="view_icon"></a>
			                   				</c:when>
			                   				<c:when test="${not empty ans.urlLink}">
			                   					<a href="${ans.urlLink}" class="view_icon"></a>
			                   				</c:when>
			                   				<c:otherwise>
			                   					&nbsp;
			                   				</c:otherwise>
			                   			</c:choose>                          			
			                   		</td>
			                   		<td>
			                   			<label for="radio-${status.index}" tabindex="${status.index}">${ans.answerOption}</label>
			                     		<input type="radio" name="radio-button" id="radio-${status.index}" value="radio-${status.index}" />
			                     	</td>
			                 	</tr>	
			                 </c:forEach>  
						  	<tr>
						    	<td>&nbsp;</td>
						 		<td><a href="#" title="READY? VOTE!" class="button_red" style="width:171px; margin-top:10px;"> <span class="title_red">READY? VOTE!</span></a> </td>
							</tr>
						</table>
				  	</td>
				</tr>
			</table>
		</div>              
	</div>

</div>