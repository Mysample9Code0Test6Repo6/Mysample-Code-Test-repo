<%@page import="com.incyyte.app.web.SessionKeys"%>
<%@page import="com.incyyte.app.domain.InCyyteChart"%>
<%@page import="com.incyyte.app.domain.CyyteResponse"%>
<%@page import="com.incyyte.app.service.util.Utility"%>
<%@page import="com.incyyte.app.service.util.Constants"%>
<%@page import="java.util.*"%>
<%@ page import="org.apache.commons.lang.StringUtils" %>
<%@ include file="/WEB-INF/jsp/common/include.jsp" %>

<% InCyyteChart chart = (InCyyteChart)request.getSession().getAttribute(SessionKeys.INCYYTE_CHART); %>


<link rel="stylesheet" href="ui/css/tooltip.css">
<link rel="stylesheet" href="ui/modal/colorbox.css">

<script type="text/javascript" src="ui/js/jquery-ui-1.8.16.custom.min.js"></script>
<script src="ui/js/jquery-1.8.1.js"></script>
<script src="ui/js/jquery-1.8.3.min.js" type="text/javascript"></script> 
<script src="ui/js/tooltip.js"></script>
<script src="ui/js/jquery.color.js"></script>
<script src="ui/js/search_script.js"></script>
<script src="ui/js/jquery_dashboard_rating.js" type="text/javascript"></script>
<script src="ui/js/star_rating.js" type="text/javascript"></script>
<script src="ui/modal/colorbox/jquery.colorbox.js"></script>
<script type="text/javascript" src="js/jquery.form.js"></script>
<script type="text/javascript" src="ui/js/jquery-ui.js"></script>
<script type="text/javascript" src="ui/js/jquery.lightbox-0.5.js"></script>
<script type="text/javascript" src="ui/js/validate/jquery.validate.js" ></script>

<!-- Left Navigation script starts here -->
<script src="ui/js/left_menu.js"></script>
<script src="ui/js/jquery.selectbox-0.2.js"></script>
<!-- Left Navigation script ends here -->

<!-- Chart Script Start here -->
<!-- <script src="ui/js/charts/js/charts.js"></script> -->
<script src="ui/js/charts/js/charts_recent_incyyte.js"></script>
<script src="ui/js/charts/js/highcharts.js"></script>
<!-- <script src="ui/js/charts/js/chart_expanded_incyyte.js"></script> -->
<script src="ui/js/dashboard/dash_my_polls.js"></script>
<script src="ui/js/dashboard/dash_comments.js"></script>
<!-- Chart Script end here -->

<!-- Rating bar and profile starts here -->
<link rel="stylesheet" type="text/css" href="ui/css/ratingbar.css" />
<link rel="stylesheet" href="ui/css/style_login.css">
<link rel="stylesheet" href="ui/css/style.css">
<link rel="stylesheet" href="ui/css/style_social.css">


<script type="text/javascript" src="ui/js/jquery.ratingbar.js"></script>
<script src="ui/js/jquery.ui.datepicker.js"></script>
<link rel="stylesheet" href="ui/fancyplayer_code/css/jquery.fancybox.css" type="text/css" media="screen" />
<script type="text/javascript" src="ui/fancyplayer_code/js/flowplayer-3.1.1.min.js"></script>
<script type="text/javascript" src="ui/fancyplayer_code/js/jquery.fancybox-1.2.1.pack.js"></script>
<script type="text/javascript" src="ui/fancyplayer_code/js/jquery.easing.1.3.js"></script>
<script type="text/javascript" src="ui/fancyplayer_code/js/fancyplayer.js"></script>

<script type="text/javascript" charset="utf-8">
$(document).ready(function() {
	$('.gender_rating').ratingbar();
	
});
</script>

<script type="text/javascript">
$(document).ready(function(){
		$("#importContact").click(function () {
			window.location = "importcontacts.cyt";
		});    	 
});

function addEmail(){
	validateEmail();
}	

 function selectContact(selectedContactId){
	
	var contactEmail = $("#"+selectedContactId).val();	  	
	var emailArrVal = $('#emailArr').val();
	
	if($("#"+selectedContactId).is(':checked') ) {		
		
 		if(emailArrVal != ""){
 			$('#emailArr').val(emailArrVal+ '; ' +contactEmail);
 		}
 		else{
 			$('#emailArr').val(contactEmail);
 		} 		
	 }
	 else
	 {
	    // alert("Unchecked - " + $("#"+selectedContactId).val());
		 if(emailArrVal != ""){
			 removeEmail(contactEmail);
		 }
	 }
	validateEmail();
 }
 
 function shareContactEmail(){
		 //alert($("#emailArr").val());
		 if ($("#emailArr").val() == ""){
			 //DO NOTHING
		 }else{
		    	if(validateEmail()){    				    		
		    		$("#sharedForm").ajaxSubmit({
		    			type: 'POST',
		    		    url: './shareIncyyte.cyt',
		   		        success: function (data) {
		   		        	parent.$.fn.colorbox.close(); 
		   		        	window.location.reload();
		   		        },
		   		        error: function (jqXHR, textStatus, errorThrown) {
		   	               
		   		        }
		    		});    		 
		    	}
		    				 
		 }
    	  	
     }

    function checked (xyz){
   		
        // Select all
        if(xyz=='#selectall')
        {	
            $("#group_1"  + " INPUT[type='checkbox']").attr('checked', true);  
            addContactEmailList();
            //return false;
        } 
        // Select none
        if(xyz=='#select_none')
        {
        	RemoveContactEmailList();		
        	$("#group_1" + " INPUT[type='checkbox']").attr('checked', false);
              //return false;
      	}
        validateEmail();
    }
    
    function RemoveContactEmailList(){
    	var sList = "";	
    	 
    	$(".contactList").each(function(){
  			if($(this).is(':checked')){
  				 sList +=  $(this).val() + "; ";
            }            
        });
    	
    	if(sList != ""){
    		var re = /\s*[,;]\s*/;
    		var maillAdrArr = sList.split(re);	
    		
    		for(var x=0; x < maillAdrArr.length; x++){
    			var addr = maillAdrArr[x].trim();
    			
    			 if(addr != '' && addr != ' '){
    				 removeEmail(addr);
    			 }
    		}
    	} 	
    	  	
     }
    
    function addContactEmailList(){
    	var sList = "";	
    	var count = 0;
    	
    	$(".contactList").each(function(){
  			if($(this).is(':checked')){  				
  				if(count == 0){  
  					sList += $(this).val();
  				}
  				else{
  					sList +=  "; " + $(this).val();
  				}  					
  				count++;
            }            
        });
    	
    	if(sList != ""){
    		var emailArrVal = $('#emailArr').val();
    		if(emailArrVal != ""){
    		 $('#emailArr').val(emailArrVal+ '; ' +sList);
    		}
    		else{
    			 $('#emailArr').val(sList);
    		}    		
    	}
    	
		/* if(validateEmail()){    		
    		
    		$("#sharedForm").ajaxSubmit({
    			type: 'POST',
    		    url: './shareIncyyte.cyt',
   		        success: function (data) {
   		        	parent.$.fn.colorbox.close(); 
   		        },
   		        error: function (jqXHR, textStatus, errorThrown) {
   	               
   		        }
    		});    		 
    	} 	 */
     }
	function myonclickhandler(t,val) {
        if (t.checked) {
        	var check = document.getElementById("uncheckedlist").value ;
        
        	for (var i= 0 ; i<= check.length ; i++)
        	{
        	
        	 if (val== check[i]) 
        		 check=	check.trim(val) ; 
        	}
        
        	document.getElementById("uncheckedlist").value = check ;
       }
        else {
      
        	document.getElementById("uncheckedlist").value= document.getElementById("uncheckedlist").value+","+ val ;
        	alert(document.getElementById("uncheckedlist").value);        	
        }
    }
</script>


<!--- Mddal--------------->
<!--[if IE 8]>
<link href="ui/css/ie8.css" media="screen" rel="stylesheet" type="text/css"/>
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

<script type="text/javascript">

$(document).ready(function () {
	var chart;	
    // Build the chart
	chart = new Highcharts.Chart({
		chart: {
			renderTo: 'containersexpanded',
			plotBackgroundColor: null,
			plotBorderWidth: null,
			plotShadow: false
		},
		title: {
			text: ''
		},
		legend: {
			layout: 'vertical',
			align: 'left',
			verticalAlign: 'middle',
			// y: 50,
			padding:5,
			itemMarginTop: 5,
			itemMarginBottom: 5,
			itemStyle: {
				lineHeight: '14px'
			}
		},
		tooltip: {
			pointFormat: '{series.name}: <b>{point.percentage}%</b>',
			percentageDecimals: 1

		},

		plotOptions: {
			pie: {
				allowPointSelect: false,
				//center: [100, 100],
				size: 250,
				cursor: 'pointer',
				dataLabels: {
					enabled: false
				},
				showInLegend: true
			}
		},      
		colors:['#cfff00','#6ecafc','#c2002d','#1b303f','#a8dffd','#e2f4fe','#e2ff66','#f5ffcc','#da6681','#f3ccd5'],

		series: [{

			type: 'pie',
			name: 'Poll Share',
			data: [
			    			    	
				<%for (CyyteResponse cyyteResponse : chart.getCyyteResponses()) {%>
					['<%=cyyteResponse.getAnswer()%>',    <%=cyyteResponse.getResponse()%>],
			 	<%}%>			    	
			]
		}]		
		
	});

});
    $(function () {
	$(".fancybox-popup").fancybox({
    maxWidth	: 800,
		maxHeight	: 600,
		fitToView	: false,
		width		: '70%',
		height		: '70%',
		autoSize	: false,
		closeClick	: false,
		openEffect	: 'none',
		closeEffect	: 'none'});
		});
    
	$(function() {
	    var button = $('#dropBoxButton${status.index}');
	    var box = $('#dropBox${status.index}');
	    var form = $('#dropBoxContent${status.index}');
	    button.removeAttr('href');
	    button.mouseup(function(login) {
	        box.toggle();
	        button.toggleClass('active');
	    });
	    form.mouseup(function() { 
	        return false;
	    });
	    $(this).mouseup(function(login) {
	        if(!($(login.target).parent('#dropBoxButton${status.index}').length > 0)) {
	            button.removeClass('active');
	            box.hide();
	        }
	    });
	});
</script>

	
	<div class="expanded_incyyte_heading">
		<table width="100%" border="0" align="center" cellpadding="0" 	cellspacing="0">
			<tr>
				<td width="78%">
					<h3>${chart.question}</h3>
				</td>
				<td width="22%" align="right">
					<!--  p>${chart.incyyte.createdDate}</p-->
					<c:if test="${not empty chart.incyyte.uploadLocation && not empty chart.incyyte.upload_name}">
						<c:set var="url"  value="${chart.incyyte.uploadLocation}"/>
						<%
							String name = chart.getIncyyte().getUpload_name();
							String extension = "";
							int i = name.lastIndexOf('.');
							if (i > 0) {
								extension = StringUtils.lowerCase((name.substring(i + 1)));
							}
							String videos[] = { "flv", "mp4", "mpg", "swf", "wmv" };
							String images[] = { "gif", "png", "jpg", "jpeg", "bmp" };
							String docs[] = {"wpd","wps","xml","xlr","pdf"};
							String gooleDocs[] = {"doc","docx","log","rtf","txt","csv","pps","ppt","xls","xlsx"};
							List<String> extVideos = Arrays.asList(videos);
							List<String> extImages = Arrays.asList(images);
                            List <String> extDocs = Arrays.asList(docs); 
							List <String> extGoogleDocs = Arrays.asList(gooleDocs);
								      					
							String docUrl = "https://docs.google.com/viewer?url=";
							String url = (String)pageContext.getAttribute("url");
							String viewUrl = docUrl + url; %>
						<p>
							<b>Question file:</b>
							<div class="thumbnail">
								<% if (extVideos.contains(extension)) { %>
								<a href="${chart.incyyte.uploadLocation}"  class="thumbnail fancybox-popup fancybox.iframe">
									<img src="ui/images/video_thumb.png" style="display: block; float: right; margin: 10px 2px; width: 50px; height: 50px; border: 2px solid #ffffff; padding: 2px;" />
								</a>
								<span><img src="ui/images/video_thumb.png" /></span>
								<% } else if (extImages.contains(extension)) { %>
							     <a href="${chart.incyyte.uploadLocation}"  class="thumbnail fancybox-popup fancybox.iframe">
									<img src="${chart.incyyte.uploadLocation}" style="display: block; float: right; margin: 10px 2px; width: 50px; height: 50px; border: 2px solid #ffffff; padding: 2px;" />
							     </a>
							     <span><img src="${chart.incyyte.uploadLocation}" /></span>
								<% } else if (extDocs.contains(extension)) { %>		
								<a style="cursor:pointer" href="" onClick="window.open('${chart.incyyte.uploadLocation}','MyWindow'); return false;" target="_blank" class="thumbnail">
									<img src="ui/images/view_docs_thumb.png" style="display: block; float: right; margin: 10px 2px; width: 50px; height: 50px; border: 2px solid #ffffff; padding: 2px;" />
								</a>
								<span><img src="ui/images/view_docs_thumb.png" /></span>
								<% } else if (extGoogleDocs.contains(extension)) { %>		
								<a style="cursor:pointer" href="" onClick="window.open('<%=viewUrl%>','MyWindow'); return false;" target="_blank" class="thumbnail">
									<img src="ui/images/view_docs_thumb.png" style="display: block; float: right; margin: 10px 2px; width: 50px; height: 50px; border: 2px solid #ffffff; padding: 2px;" />
								</a>
								<span><img src="ui/images/view_docs_thumb.png" /></span>
								<% } %>
								</div>
						</p>
					</c:if>
					<p>
					<c:if test="${not empty chart.incyyte.group}">
						group name: ${chart.incyyte.group.groupName}
					</c:if>
					</p>
				</td>
			</tr>
		</table>
	</div>
	<% 
	int filesCount = 0;
	%>

	<c:forEach var="ans" items="${chart.incyyte.answers}"
		varStatus="ansStatus">
		<c:choose>
			<c:when test="${not empty ans.uploadCDN_url}">
			<% 
			filesCount++;
			%>
			</c:when>
		</c:choose>
	</c:forEach>
	<% if (filesCount > 0) { %>
	<div class="expanded_incyyte_detail_answers" id="answerOptionFileImage">
					<table width="100%" border="0" cellspacing="0" id="">
						<tr><td><h2>Answer option files :</h2></td></tr>
						<tr>
							<td width="78%" style="padding-left:10px;" bgcolor="#D8D8D8">
								<c:forEach var="ans" items="${chart.incyyte.answers}" varStatus="ansStatus">
								<c:choose>
								<c:when test="${not empty ans.uploadCDN_url}"> 
								<c:set var="answerExt" value="${ans.uploadExt}"/>
								<c:set var="ansURL"  value="${ans.uploadCDN_url}"/>	
								<% 	String extAnswer = (String) pageContext.getAttribute("answerExt");
									String ansVideos[] = { ".flv", ".mp4", ".mpg", ".swf",".wmv" };
									String ansImages[] = { ".gif", ".png", ".jpg", ".jpeg",".bmp" };
									String ansDocs[] = { ".wpd", ".wps", ".xml", ".xlr",".pdf" };
									String ansGooleDocs[] = { ".doc", ".docx", ".log",".rtf", ".txt", ".csv", ".pps", ".ppt", ".xls",".xlsx" };
									List<String> extAnsVideos = Arrays.asList(ansVideos);
									List<String> extAnsImages = Arrays.asList(ansImages);
									List<String> extAnsDocs = Arrays.asList(ansDocs);
									List<String> extAnsGoogleDocs = Arrays.asList(ansGooleDocs);
									String docUrl = "https://docs.google.com/viewer?url=";
									String ansUrl = (String) pageContext.getAttribute("ansURL");
									String viewUrl = docUrl + ansUrl;
								%>	
								<% if (extAnsVideos.contains(StringUtils.lowerCase(extAnswer))) { %>
								 <a style="cursor: pointer" href="${ans.uploadCDN_url}" class="thumbnail2 fancybox-popup fancybox.iframe"> 
								<img src="ui/images/video_thumb.png"style="display: block; float: left; margin: 10px 2px; width: 50px; height: 50px; border: 2px solid #ffffff; padding: 2px;" />
									<span><b>${ans.answerOption}</b><br> <img src="ui/images/video_thumb.png" /></span></a>
									
								<% } else if (extAnsImages.contains(StringUtils.lowerCase(extAnswer))) { %>	
								<a style="cursor: pointer" href="${ans.uploadCDN_url}" class="thumbnail2 fancybox-popup fancybox.iframe"> 
								<img src="${ans.uploadCDN_url}"style="display: block; float: left; margin: 10px 2px; width: 50px; height: 50px; border: 2px solid #ffffff; padding: 2px;" />
									<span><b>${ans.answerOption}</b><br> <img src="${ans.uploadCDN_url}" /></span></a>
								
								<% } else if (extAnsGoogleDocs.contains(StringUtils.lowerCase(extAnswer))) { %>		
									<a style="cursor: pointer" href="" onClick="window.open('<%=viewUrl%>','MyWindow'); return false;" target="_blank"  class="thumbnail2"> 
								<img src="ui/images/view_docs_thumb.png"style="display: block; float: left; margin: 10px 2px; width: 50px; height: 50px; border: 2px solid #ffffff; padding: 2px;" />
									<span><b>${ans.answerOption}</b><br> <img src="ui/images/view_docs_thumb.png" /></span></a>
								<% } else if (extAnsDocs.contains(StringUtils.lowerCase(extAnswer))) { %>
								<a style="cursor: pointer" href="" onClick="window.open('${ans.uploadCDN_url}','MyWindow'); return false;" target="_blank" class="thumbnail2"> 
								<img src="ui/images/view_docs_thumb.png"style="display: block; float: left; margin: 10px 2px; width: 50px; height: 50px; border: 2px solid #ffffff; padding: 2px;" />
									<span><b>${ans.answerOption}</b><br> <img src="ui/images/view_docs_thumb.png" /></span></a>
								<% } %>
								</c:when>
							</c:choose>
					</c:forEach>
							</td>
						</tr>
					</table>
				</div>
				<% } %>
	<div class="expanded_incyyte">
		<table width="100%" border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td width="60%" valign="top"><span class="font_16px">Poll Result</span><!--BR><span class="font_12px">${chart.incyyte.incyyteCreatedDateFmt}</span-->
					<div style='display:none'>
						<div id="view_anspics" >
							<table style="width: 100%;">
								<c:forEach var="ans" items="${chart.incyyte.answers}" varStatus="ansStatus">							 	
									<tr>
										<td style="width:5%;">
											<c:choose>													  												  		
												<c:when test="${not empty ans.uploadCDN_url}">
													<c:set var="extAnswer"  value="${ans.uploadExt}"/>
													<c:set var="ansUrl"  value="${ans.uploadCDN_url}"/>													
													<%  
														String extAnswer = (String)pageContext.getAttribute("extAnswer");
														String ansVideos[] = {".flv",".mp4",".mpg",".swf",".wmv"};
							      						String ansImages[] = {".gif",".png",".jpg",".jpeg",".bmp"};
							      						String ansDocs[] = {".wpd",".wps",".xml",".xlr",".pdf"};
							      						String ansGooleDocs[] = {".doc",".docx",".log",".rtf",".txt",".csv",".pps",".ppt",".xls",".xlsx"};
							      						List <String> extAnsVideos = Arrays.asList(ansVideos); 
							      						List <String> extAnsImages = Arrays.asList(ansImages); 
							      						List <String> extAnsDocs = Arrays.asList(ansDocs);
							      						List <String> extAnsGoogleDocs = Arrays.asList(ansGooleDocs);
							      						String docUrl = "https://docs.google.com/viewer?url=";
														String ansUrl = (String)pageContext.getAttribute("ansUrl");
														String viewUrl = docUrl + ansUrl; 
													%>	
							      					<div class="thumbnail view_icon">
							      						<%if(extAnsVideos.contains(StringUtils.lowerCase(extAnswer))) { %>
															<a style="cursor:pointer" href="${ans.uploadCDN_url}" class="thumbnail view_icon fancybox-popup fancybox.iframe"></a>
															<span><img src="ui/images/video_thumb.png" /></span>
														<%} else if(extAnsImages.contains(StringUtils.lowerCase(extAnswer))){ %>	
															<a style="cursor:pointer" href="${ans.uploadCDN_url}" class="thumbnail view_icon fancybox-popup fancybox.iframe"></a>
															<span><img src="${ans.uploadCDN_url}" /></span>
														<%}else if(extAnsGoogleDocs.contains(StringUtils.lowerCase(extAnswer))){ %>	
															<a style="cursor:pointer" href="" onClick="window.open('<%=viewUrl %>','MyWindow'); return false;" target="_blank" class="thumbnail view_icon"></a>
															<span><img src="ui/images/view_docs_thumb.png" /></span>
														<%} else if(extAnsDocs.contains(StringUtils.lowerCase(extAnswer))){ %>
															<a style="cursor:pointer" href="" onClick="window.open('${ans.uploadCDN_url}','MyWindow'); return false;" target="_blank" class="thumbnail view_icon"></a>
															<span><img src="ui/images/view_docs_thumb.png" /></span>
														<%} %>
													</div>
												</c:when>
												<c:otherwise>
													&nbsp;
												</c:otherwise>
											</c:choose> 
									  	</td>	
									  	<td style="width:95%;">
									  		&nbsp;<label tabindex="${status.index}"  class="font_20px">${ans.answerOption}</label>	
	                            		</td>											  	
									</tr>
								</c:forEach>
								<tr>
									<td></td>
									<td align="right">
										<a	href="#" id="display_ans_btn" class="button_green" style="width:80px; float:right"> 
											<span class="title_green"> Close </span>
										</a>
									</td>
								</tr>
							</table>									
							
						</div>
					</div>
					<div id="containersexpanded" style="height: 300px;"></div>
				</td>
			</tr>
			<tr>
				<td height="55">
					<table width="100%" border="0" cellspacing="0" 	cellpadding="0">
						<tr>
							<td><h3>Statistics Break Down</h3></td>
							<td><a href="#" title="AVAILABLE SOON"><span style="color:red;float:right;">Report this poll</span></a>
 							</td>
						</tr>
					</table>
				</td>
			</tr>
			<tr>
				<td valign="top">
					<table width="100%" border="0" cellspacing="0" cellpadding="0" class="statistics_table">
						<tr>
							<td width="33%" valign="top" class="border_right">
								<P> 
									<span>Region Responses</span>  <a href="#" class="right">More</a>
								</P>
								<!-- <p>AB1 - 20%</p>
								<p>AB2 - 40%</p>
								<p>AB3 - 40%</p> -->
							</td>
							<td width="33%" valign="top" class="border_right"><p>
									<span>Gender:</span> <a href="#" class="right">More</a>
								</p>
								<p>Male   - <%=chart.getGenderChart().get(Constants.GENDER_MALE)%>%</p>
								<p>Female - <%=chart.getGenderChart().get(Constants.GENDER_FEMALE)%>%</p></td>
							<td width="34%" valign="top"><p>
									<span>Top Age Groups:</span> <a href="#" class="right">More</a>
								</p>
								<p>Recipients - ${chart.incyyte.totalInCyyted}</p>
								<p>Responses - ${chart.incyyte.totalResponded}</p>
							</td>
						</tr>
					</table>
				</td>
			</tr>
			<tr>
				<td valign="top">&nbsp;</td>
			</tr>
			<% if (chart.getIncyyte().getAllowComment().equals("Y")){ %>
			<tr>
				<td valign="top">
					<table width="100%" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td width="42%" height="40" class="font_18px">
								Add Your Comment:
							</td>
							<td width="58%"></td>
						</tr>
					<form:form  id="CommentsModel"  commandName="CommentsModel" method="post" >  
						
						<tr>
							<td colspan="2">
								<textarea name="textarea" id="textarea" class="add_comment_ie8" cols="45" rows="5" placeholder="Post Comment Text" onFocus="this.placeholder = ''" onBlur="this.placeholder = 'Comment text'"></textarea>
								<div id="commentErrorDiv" ><span id="error" style="padding-left: 12px; display:none;"/></div><br>
								
								<form:hidden path="comment" id="comment"  />
								<form:hidden path="quesid" id="quesid"  />
								 <input type="hidden" name="questionid" id= "questionid" value="${chart.incyyte.id}" >
								 <input type="hidden" name="code" id= code value="${code}" >
								 <input type="hidden" name="commentlst" id= "commentlst" value="${size}" >
							</td>
						</tr>
						</form:form>
						<tr>
							<td><a href="javascript:postcomment();" title="POST" id="addcmnt" class="button_green" style="width: 165px; float: left;" autofocus> <span
									class="title_green share_poll_ie8">POST</span></a>
							</td>
							<td>
								<c:if test="${sharedPoll == 'true'}">
									<a title="SHARE" id="displaySharedEmailList" class="button_green" style="width: 165px; margin: 0 0 0 160px;" > 
										<span class="title_green share_poll_ie8">SHARE THIS POLL</span>
									</a>
								</c:if>
							</td>
						</tr>
					</table>
				</td>
			</tr>
			<tr>
				<td valign="top">&nbsp;</td>
			</tr>
			<tr>
				<td valign="top">&nbsp;</td>
			</tr>
			
			<tr>
				<td valign="top">
					<table width="100%" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td width="42%" height="40" class="font_18px">Comments:</td>
						</tr>
					</table>
				</td>
			</tr>
			<tr>
				<td valign="top">
					<table width="100%" border="0" cellspacing="0" cellpadding="0" class="comment_table">
                 <c:forEach items="${comments}" var="CommentsModel" varStatus="theCount">
                     <c:choose>
                     <c:when test="${theCount.count mod 2 == 0}">
                             <tr class="commentsWhite">
                     </c:when>
                     <c:otherwise>
                                 <tr class="commentsBlank">
                     </c:otherwise>
                     </c:choose>
                               <c:choose>
                                <c:when test="${not empty CommentsModel.userprofileimg}">
                                    <c:set var="commentedUserProfilePic" value="${CommentsModel.userprofileimg}"/>
                                </c:when>
                                <c:otherwise>
                                    <c:set var="commentedUserProfilePic" value="ui/images/default_avatar.png"/>
                                </c:otherwise>
                                </c:choose>
                     <%
                         String cProfileImage =(String) pageContext.getAttribute("commentedUserProfilePic");
                     %>
                     <td width="8%" valign="top">
                         <div id="dropBoxContainer2${theCount.index}" class="dropBoxContainer2">

                             <a href="#" id="dropBoxButton2${theCount.index}" class="dropBoxButton2">
                                 <img src="<%=cProfileImage%>" width="32" height="32" class="photoframe">
                             </a>
                             <div style="clear:both"></div>
                             <div id="dropBox2${theCount.index}" class="dropBox2">
                                 <div id="dropBoxContent2${theCount.index}" class="dropBoxContent2">
                                     <ul>
                                         <li><a href="javascript:sendFriendRequest('${CommentsModel.commentby}', '${theCount.index}');" style="color:black; font-size:14px;">Send Friend Request</a></li>
                                     </ul>
                                 </div>
                             </div>
                         </div>
                     </td>
                     <td width="92%">
                         <p class="font_12px"><font style="font-weight:bold;">${CommentsModel.commentby}</font>,
                            <strong>${CommentsModel.commentPeriod}</strong></p>
                            <div id='message_<c:out value="${theCount.index}"/>' class="success_message" style="display:none;"></div>
                            <script type="text/javascript">
                                $(document).ready(function () {
                                    var inputText = "<c:out value="${CommentsModel.comment}"/>";
                                    var replaceText = linkify(inputText);
                                    $('#linkify_text_${theCount.count}').html(replaceText+".");
                                });
                            </script>
                            <p class="font_14px commentText" id="linkify_text_${theCount.count}" ></p><br>
                            <div id="comments_icons">
                                            &nbsp;&nbsp;<a href="javascript:sendFriendRequest('${CommentsModel.commentby}', '${theCount.index}');"><img src="images/addcontact-icon.png" width="15" align="middle" height="15" ></a>&nbsp;&nbsp;
                                                        <a href="javascript:CommentIconAvailability(${theCount.count})"><img src="images/reply-icon.png" width="15" align="middle" height="15" ></a>&nbsp;&nbsp;
                                                        <a href="javascript:CommentIconAvailability(${theCount.count})"><img src="images/viewprofile-icon.png" width="15"  align="middle" height="15" ></a>
                                                        <div >
                                                        <span id='commenticonsuccess${theCount.count}' class="success_message" style="padding-left: 12px; display:none;"></span>
                                                        </div>
                                                    </p>
                                                </div>
                                              </td>
                                         </tr>
                 </c:forEach>
                    </table>
				</td>
			</tr>
			<% } %>			
			<tr>
				<td valign="top">&nbsp;</td>
			</tr>
		</table>
	</div>
		
	<div style="display: none;">		
		
		<div id="sharedEmailList" >
			<div id="share_contacts">
				<h3 class="heading1" style="padding:2px 0 8px 0px;">Share with your Contacts</h3>
				<div id="scrollbar1">
					<div class="scrollbar">
			       		<div class="track">
			          		<div class="thumb">
			             		<div class="end"></div>
			           		</div>
			         	</div>
			       	</div>
			       	
			       	<form:form id="sharedForm" name="sharedForm" commandName="inCyyteForm"  method="post">
				       	<div class="viewport">
				      		<div class="overview">
				           		<table width="380" border="0" cellspacing="0" cellpadding="0" id="contact_list">
				           			<tr>
				       					<td colspan="12" class="select_all">Select: <a rel="group_1" href="javascript:checked('#selectall');">All</a> | <a rel="group_1" href="javascript:checked('#select_none');">None</a></td>
				     				</tr>
				     			</table>
				     			<div style="width:380px; height:4px;"></div>
				     			<!-- <div style="width:400px; height:200px; overflow:auto;"> -->
				     			<div id="contactScroll" style="width:380px; height:200px; overflow-x:hidden;overflow-y:auto;">
					     			<table  width="350" border="0" cellspacing="0" cellpadding="0">
					              		<c:forEach items="${emailContactList}" var="UserContactModel">
					              			<tr>
					   							<td style="padding-left:10px;padding-right:10px;">                                                     
					                 				<span id="group_1">
					                 					<c:choose>
					                 						<c:when test="${UserContactModel.receivedIncyyte == true}">
					                 							<img alt="already received this incyyte" src="ui/images/check_save.png" />
					                 						</c:when>
					                 						<c:otherwise>
					                 							<input type="checkbox" class="contactList" name="selectedGroupContactsList" onclick="selectContact('contactId'+${UserContactModel.contactid})" id="contactId${UserContactModel.contactid}" value="${UserContactModel.email}"/>
					                 						</c:otherwise>
					                 					</c:choose>
					 										                         			                 				
					 		              			</span>
					         		    		</td>	
					             				<td height="40" style="padding-right:10px;"><img src='${UserContactModel.profile_img}' width="36" height="35"  alt="User Photo"></td>
					             				<td >${UserContactModel.email}</td>     
					              				<td>${UserContactModel.username}</td>
					             			</tr>
					           			</c:forEach>          
					           		</table>
				           		</div>
				     			<div style="width:380px; height:4px; float: left;"></div>
				           		<table>
				           			<tr>
				           				<td>
					           				<form:textarea cssClass="questionbox" path="emailArr" onchange="javascript:addEmail()"
				                               id="emailArr" onFocus="this.placeholder = ''"
				                               onBlur="this.placeholder = 'Add one or more email addresses separated by a space'"
				                               placeholder="Add one or more email addresses separated by a space"/><BR>
	                						<span id="email_error" class="error" style="font:14px/20px 'bariol_regularregular', 'ie8_bariol_regular';color:#C2002D; margin-left:12px"></span>
	                					</td>
	                				</tr>
				           		</table>
				         	</div>
				       	</div>
			       </form:form>
			    </div>			    
			    <table width="380px" border="0" cellspacing="0" cellpadding="0">
			    <tr >
				    <td valign="bottom" width="50%" align="left"><label class="panelLink" id="importContact" ><span class="importSpan" title="Import Contacts">Import new contacts</span></label></td>
				    <td  valign="top">
				    	<button type="button" title="Share inCyyte" id="add_email_btn" class="button_green1 share_bot_margin_ie8" onclick="shareContactEmail();"
	                style="width:105px; margin:10px 0 0 50px;"><span class="title_green1 share_bot_ie8">SHARE</span>
	        			</button>
				    </td>
			    </tr>
		    </table>
			    			    
			</div>			
		</div>			
	</div>
