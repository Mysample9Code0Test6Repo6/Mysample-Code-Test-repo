<%@page import="com.incyyte.app.web.SessionKeys"%>
<%@page import="com.incyyte.app.domain.User"%>
<%@page import="com.incyyte.app.service.util.Utility"%>
<%@page import="com.incyyte.app.domain.InCyyteChart"%>
<%@page import="com.incyyte.app.domain.CyyteResponse"%>
<%@page import="com.incyyte.app.service.util.Constants"%>
<%@page import="java.util.*"%>
<%@ page import="org.apache.commons.lang.StringUtils" %>
<%@ include file="/WEB-INF/jsp/common/include.jsp" %>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

</head>
<body>
<div id="uploadcontent">

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
<script src="ui/js/charts/js/charts_recent_incyyte.js"></script>
<script src="ui/js/charts/js/highcharts.js"></script>
<script src="ui/js/dashboard/dash_sent.js"></script>
<!-- Chart Script end here -->


<script type="text/javascript" src="ui/js/jquery.ratingbar.js"></script>
<script src="ui/js/jquery.ui.datepicker.js"></script>
<script type="text/javascript" src="ui/fancyplayer_code/js/flowplayer-3.1.1.min.js"></script>
<script type="text/javascript" src="ui/fancyplayer_code/js/jquery.fancybox-1.2.1.pack.js"></script>
<script type="text/javascript" src="ui/fancyplayer_code/js/jquery.easing.1.3.js"></script>
<script type="text/javascript" src="ui/fancyplayer_code/js/fancyplayer.js"></script>

<% User user = (User)request.getSession().getAttribute(SessionKeys.LOGIN_USER); %>
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
        
        $("#view_anspics").dialog({
			dialogClass: "no-close",
			autoOpen : false, 						
			modal : true, 
			width : 450,			
			show:{
				effect:"blind",
				duration:1000
			},
			hide:{
				effect:"explode",
				duration:1000
			}
		});	
        
        $("#displayAns").click(function(){ 
        	$("#view_anspics").dialog("open");
        });
        
        $("#display_ans_btn").click(function(){
        	$("#view_anspics").dialog('close');
        });
        
        
    });
    
    function displayUploadImage(id){
    	//alert(id);
    	$('#'+id+' a').lightBox();
    }
    
</script>
<!--- placeholder Ends----->
<script>
    $(function () {
        $("#datepicker").datepicker({
        	minDate: 0,
            altField:"#alternate",
            altFormat:"dd/mm/yy"
        }).datepicker("setDate", "+1");
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
			closeEffect	: 'none'
		});
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
        //$(".inline1").colorbox({inline:true, height:"100%"});
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
<script type="text/javascript" src="ui/js/ddaccordion.js"></script>

	<c:forEach var="myInCyyte" items="${myInCyytes}" varStatus="status"  end="${SENT_ROW_MAX}">
	
		<% InCyyteChart cyyteChart = (InCyyteChart)pageContext.getAttribute("myInCyyte");%>
		
		<script type="text/javascript">
			<!--
			$(document).ready(function () {
			 												 	
			 	var chart0 = new Highcharts.Chart({
					chart: {
						renderTo: 'piesentcontainersmall${status.index}',
						plotBackgroundColor: null, plotBorderWidth: null, plotShadow: false
					},
					title: { text: '' },
					legend: {
						layout: 'vertical', align: 'left', verticalAlign: 'middle', padding:5, itemMarginTop: 5, itemMarginBottom: 5,
						itemStyle: {
							lineHeight: '14px'
						}
					},
					tooltip: {
						pointFormat: '<b>{point.percentage}%</b>',percentageDecimals: 1
					},											
					plotOptions: {
						pie: {
							allowPointSelect: false, size: 60, cursor: 'pointer', 	dataLabels: {enabled: false}, showInLegend: false
						}
					}, 
					colors:['#cfff00','#6ecafc','#c2002d','#1b303f','#a8dffd','#e2f4fe','#e2ff66','#f5ffcc','#da6681','#f3ccd5'],
			
					series: [{									
						type: 'pie', name: '',
						data: [															
							<%for (CyyteResponse cyyteResponse : cyyteChart.getCyyteResponses() ){%>
								['<%=cyyteResponse.getAnswer()%>',    <%=cyyteResponse.getResponse()%>],
							<%}%>
						]
					}]
				});
			 	
				var chart1 = new Highcharts.Chart({
					chart: {
						renderTo: 'piesentcontainer${status.index}',
						plotBackgroundColor: null, plotBorderWidth: null, plotShadow: false
					},
					title: { text: '' },
					legend: {
						layout: 'vertical', align: 'left', verticalAlign: 'middle', padding:5, itemMarginTop: 5, itemMarginBottom: 5,
						itemStyle: {
							lineHeight: '14px'
						}
					},
					tooltip: {
						pointFormat: '{series.name}: <b>{point.percentage}%</b>',percentageDecimals: 1
					},											
					plotOptions: {
						pie: {
							allowPointSelect: false, size: 250, cursor: 'pointer', 	dataLabels: {enabled: false}, showInLegend: true
						}
					}, 
					colors:['#cfff00','#6ecafc','#c2002d','#1b303f','#a8dffd','#e2f4fe','#e2ff66','#f5ffcc','#da6681','#f3ccd5'],
			
					series: [{											
						type: 'pie', name: 'Poll Share',
						data: [															
							<%for (CyyteResponse cyyteResponse : cyyteChart.getCyyteResponses() ){%>
								['<%=cyyteResponse.getAnswer()%>',    <%=cyyteResponse.getResponse()%>],
							<%}%>
						]
					}]
				});
			
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
			
			//-->
		</script>
		<!--<a href="#" onClick="ddaccordion.collapseall('question_tab'); return false">Collapse all</a> | <a href="#" onClick="ddaccordion.expandall('question_tab'); return false">Expand all</a>-->
		<div class="question_tab">
			<table width="100%" border="0" cellspacing="0" cellpadding="0" id="pie_q_tab_${status.index}">
				<tr>
					<td width="17%"><div id="piesentcontainersmall${status.index}"
							style="width: 100px; height: 100px;"></div>
					</td>
					<td width="57%">
						<h3><p class="questionText">${myInCyyte.question}</p></h3>
						<p>Created:${myInCyyte.incyyte.createdDate}</p>
		      			<p>Closure:${myInCyyte.incyyte.closureDate}</p>
		      			
		      			<c:if test="${not empty myInCyyte.incyyte.group}">
							<p>Group: ${myInCyyte.incyyte.group.groupName}</p>	
						</c:if>		
					</td>
					<td width="26%"><a href="" title="view incyyte"	id="view_incyyte${status.index}" class="button_gray" style="width: 171px;"> 
						<span class="title_gray">VIEW INCYYTE</span></a>
					</td>
				</tr>
			</table>
			<table width="100%" border="0" cellspacing="0" cellpadding="0" style="display:none;" id="q_tab_${status.index}">
				<tr>													
					<td><h3><p class="questionText">${myInCyyte.question}</p></h3>						
		      			<p>Created:
							<span id="createdDateVal_${myInCyyte.incyyteId}"> ${myInCyyte.incyyte.createdDate}</span>
						</p>
		      			<p>Closure: 
		      			<span id="closureDateVal_${myInCyyte.incyyteId}"> ${myInCyyte.incyyte.closureDate}
		      			</span>&nbsp;
		      			<span id="selectClosureTimeVal_${myInCyyte.incyyteId}" ></span>
		      			</p>
		      			<c:if test="${not empty myInCyyte.incyyte.group}">
							<p>Group: ${myInCyyte.incyyte.group.groupName}</p>	
						</c:if>	
					</td>
					<td>
						<c:if test="${not empty myInCyyte.incyyte.uploadLocation && not empty myInCyyte.incyyte.upload_name}">
						<c:set var="url"  value="${myInCyyte.incyyte.uploadLocation}"/>
						<%		String name = cyyteChart.getIncyyte().getUpload_name();
				      					String extension = "";
				      					int i = name.lastIndexOf('.');
				      					if (i > 0) {
				      					    extension = StringUtils.lowerCase(name.substring(i+1));
				      					}
				      					String videos[] = {"flv","mp4","mpg","swf","wmv"};
				      					String images[] = {"gif","png","jpg","jpeg","bmp"};
				      					String docs[] = {"wpd","wps","xml","xlr","pdf"};
										String gooleDocs[] = {"doc","docx","log","rtf","txt","csv","pps","ppt","xls","xlsx"};
				      					List <String> extVideos = Arrays.asList(videos); 
				      					List <String> extImages = Arrays.asList(images); 
				      					List <String> extDocs = Arrays.asList(docs); 
										List <String> extGoogleDocs = Arrays.asList(gooleDocs);
				      					String docUrl = "https://docs.google.com/viewer?url=";
										String url = (String)pageContext.getAttribute("url");
										String viewUrl = docUrl + url; %>
							<p>
							<div style="text-align:right;font-size:12px;"><b>Question file:</b></div>
							<div class="thumbnail">
								<%if(extVideos.contains(extension)) {%>
								<a href="${myInCyyte.incyyte.uploadLocation}" class="thumbnail fancybox-popup fancybox.iframe">
									<img src="ui/images/video_thumb.png" style="display: block; float: right; margin: 10px 2px; width: 50px; height: 50px; border: 2px solid #ffffff; padding: 2px;" />										
								</a>
								<span><img src="ui/images/video_thumb.png" /></span>										
								<%}else if(extImages.contains(extension)){ %>
									<a href="${myInCyyte.incyyte.uploadLocation}" class="thumbnail fancybox-popup fancybox.iframe">
										<img src="${myInCyyte.incyyte.uploadLocation}" style="display: block; float: right; margin: 10px 2px; width: 50px; height: 50px; border: 2px solid #ffffff; padding: 2px;" />
									</a>
									<span><img src="${myInCyyte.incyyte.uploadLocation}" /></span>
								<%}else if(extDocs.contains(extension)){ %>	
								<a style="cursor:pointer" href="" onClick="window.open('${myInCyyte.incyyte.uploadLocation}','MyWindow'); return false;" target="_blank" class="thumbnail">
									<img src="ui/images/view_docs_thumb.png" style="display: block; float: right; margin: 10px 2px; width: 50px; height: 50px; border: 2px solid #ffffff; padding: 2px;" />										
								</a>
								<span><img src="ui/images/view_docs_thumb.png" /></span>
								<%}else if(extGoogleDocs.contains(extension)){ %>		
								<a style="cursor:pointer" href="" onClick="window.open('<%=viewUrl%>','MyWindow'); return false;" target="_blank" class="thumbnail">
									<img src="ui/images/view_docs_thumb.png" style="display: block; float: right; margin: 10px 2px; width: 50px; height: 50px; border: 2px solid #ffffff; padding: 2px;" />																				
								</a>
								<span><img src="ui/images/view_docs_thumb.png" /></span>
								<%} %>
								</div>
							</p>
						</c:if>
					</td>													
				</tr>
			</table>
		</div>
						
		<div class="question_detail">
			<% 	int filesCount = 0;	%>
	
			<c:forEach var="ans" items="${myInCyyte.incyyte.answers}"
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
				<div class="sent_incyyte_answers" id="">
					<table width="100%" border="0" cellspacing="0" id="">
						<tr><td style="font-size:12px;"><h2>Answer option files :</h2></td></tr>
						<tr>
							<td width="78%" style="padding-left:10px;" bgcolor="#D8D8D8">								
								<c:forEach var="ans" items="${myInCyyte.incyyte.answers}" varStatus="ansStatus">
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
												<span style="font-size:12px;"><b>${ans.answerOption}</b><br> <img src="ui/images/video_thumb.png" /></span></a>
												
											<% } else if (extAnsImages.contains(StringUtils.lowerCase(extAnswer))) { %>	
												<a style="cursor: pointer" href="${ans.uploadCDN_url}" class="thumbnail2 fancybox-popup fancybox.iframe"> 
												<img src="${ans.uploadCDN_url}"style="display: block; float: left; margin: 10px 2px; width: 50px; height: 50px; border: 2px solid #ffffff; padding: 2px;" />
												<span style="font-size:12px;"><b>${ans.answerOption}</b><br> <img src="${ans.uploadCDN_url}" /></span></a>
											
											<% } else if (extAnsGoogleDocs.contains(StringUtils.lowerCase(extAnswer))) { %>		
												<a style="cursor: pointer" href="" onClick="window.open('<%=viewUrl%>','MyWindow'); return false;" target="_blank"  class="thumbnail2"> 
												<img src="ui/images/view_docs_thumb.png"style="display: block; float: left; margin: 10px 2px; width: 50px; height: 50px; border: 2px solid #ffffff; padding: 2px;" />
												<span style="font-size:12px;"><b>${ans.answerOption}</b><br> <img src="ui/images/view_docs_thumb.png" /></span></a>
											<% } else if (extAnsDocs.contains(StringUtils.lowerCase(extAnswer))) { %>
												<a style="cursor: pointer" href="" onClick="window.open('${ans.uploadCDN_url}','MyWindow'); return false;" target="_blank" class="thumbnail2"> 
												<img src="ui/images/view_docs_thumb.png"style="display: block; float: left; margin: 10px 2px; width: 50px; height: 50px; border: 2px solid #ffffff; padding: 2px;" />
												<span style="font-size:12px;"><b>${ans.answerOption}</b><br> <img src="ui/images/view_docs_thumb.png" /></span></a>
											<% } %>
										</c:when>
									</c:choose>
								</c:forEach>
							</td>
						</tr>
					</table>
				</div>
			<% } %>
			
			<table width="1%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td width="60%" valign="top" class="chart_padding" style="padding:20px 20px;">Polls Result<br>
						<%--
							<%if(cyyteChart.checkAnswerImage()){ %>								
								<a href="#" id="displayAns" ><span style="color: red;">View Answer Files</span></a>
							<%}%>
						 --%>
						<div style='display:none'>		
							<div id="view_anspics" >
								<table style="width: 100%;">
									<c:forEach var="ans" items="${myInCyyte.incyyte.answers}" varStatus="ansStatus">							 	
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
							
						<div id="piesentcontainer${status.index}" style="width: 370px; height: 260px;"> </div>
					</td>
					<td width="40%" valign="top" class="grid_6a"><span>Responses</span>
						<br> <strong>${myInCyyte.incyyte.totalResponded}</strong> of
						<strong>${myInCyyte.incyyte.totalInCyyted}</strong> people responded<br> <br> 
						<span>Gender</span><br>
						<table id="gender">
							<tr>
								<td height="60" width="30"><img	src="ui/images/male_gender.png"></td>
								<td class="gender_rating">
									<%if(cyyteChart.getGenderChart().get(Constants.GENDER_MALE) != null) {%>
										<%=cyyteChart.getGenderChart().get(Constants.GENDER_MALE)%>%
									
									<%}%>
								</td>
							</tr>
							<tr>
								<td><img src="ui/images/female_gender.png"></td>
								<td class="gender_rating">
									<%if(cyyteChart.getGenderChart().get(Constants.GENDER_FEMALE) != null) {%>
										<%=cyyteChart.getGenderChart().get(Constants.GENDER_FEMALE)%>%
									<%}%>
								</td>
							</tr>
						</table> <br>
						<a	href="dashdetails.cyt?code=${myInCyyte.incyyte.incyyteCode}&frm=sent"
							title="view details" id="view_details" class="button_green"
							style="width: 180px;"> <span class="title_green">DETAILS</span>
						</a>								
						<!-- Options Drop Box Starts Here -->
					    <div id="dropBoxContainer${status.index}" class="dropBoxContainer"> 
					    	<a href="#" id="dropBoxButton${status.index}" class="dropBoxButton">
					    		<span>more options</span><em></em>
					    	</a>
							<div style="clear:both"></div>
							<div id="dropBox${status.index}" class="dropBox">
						  		<div id="dropBoxContent${status.index}" class="dropBoxContent">
								    <ul>																      
								      <!-- <li><a href="#" onclick="display_datepicker('${myInCyyte.incyyteId}');" class="inline" style="color:black; font-size:14px;">Edit Closing Time &amp; Date</a></li> -->
								      <li><a href="#modal_datepicker" onclick="set_datepicker('${myInCyyte.incyyteId}');" class="inline" style="color:black; font-size:14px;">Edit Closing Time &amp; Date</a></li>
								      <li><a href="#" onclick="delete_incyyte('${myInCyyte.incyyteId}','sent');" id="delete_incyyte" style="color:black; font-size:14px;">Delete this InCyyte </a></li>
								    </ul>
						  		</div>
							</div>
						</div>								
					    <!-- Options Drop Box Ends Here -->
					    <br>
					</td>
				</tr>
			</table>
		</div>
	
	</c:forEach>
</div>
<script type="text/javascript" src="ui/js/googleanalytics.js"></script>
</body>
</html>