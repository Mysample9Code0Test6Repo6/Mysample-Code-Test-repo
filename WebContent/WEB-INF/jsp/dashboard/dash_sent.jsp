<%@page import="com.incyyte.app.web.SessionKeys"%>
<%@page import="com.incyyte.app.domain.User"%>
<%@page import="com.incyyte.app.service.util.Utility"%>
<%@page import="com.incyyte.app.domain.InCyyteChart"%>
<%@page import="com.incyyte.app.domain.CyyteResponse"%>
<%@page import="com.incyyte.app.service.util.Constants"%>
<%@page import="java.util.*"%>
<%@ page import="org.apache.commons.lang.StringUtils" %>
<%@ include file="/WEB-INF/jsp/common/include.jsp" %>

<% User user = (User)request.getSession().getAttribute(SessionKeys.LOGIN_USER); %>
<link rel="stylesheet" href="ui/css/reset.css">
<link rel="stylesheet" href="ui/css/layout.css">
<link rel="stylesheet" href="ui/css/buttons.css"  media="screen">
<link rel="stylesheet" href="ui/css/accordionmenu.css" type="text/css">
<link rel="stylesheet" href="ui/css/icons_sprites.css">
<link rel="stylesheet" href="ui/css/form_elements.css">
<link rel="stylesheet" href="ui/css/tooltip.css">
<link rel="stylesheet" href="fonts/fonts_stylesheet.css">
<link rel="stylesheet" href="ui/modal/colorbox.css">
<link rel="stylesheet" type="text/css" href="ui/css/jquery.lightbox-0.5.css" media="screen" />
<link rel="stylesheet" href="ui/css/jquery.validate.css">
<link rel="stylesheet" href="ui/css/validate/screen.css">
<link rel="stylesheet" href="ui/css/modal_window.css" type="text/css" media="screen">
<script type="text/javascript" src="ui/js/jquery-ui-1.8.16.custom.min.js"></script>
<script src="ui/js/jquery-1.8.1.js"></script>
<script src="ui/js/jquery-1.8.3.min.js" type="text/javascript"></script> 
<script src="ui/js/prettyCheckboxes.js" type="text/javascript" charset="utf-8"></script>
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
<script src="ui/js/charts/js/highcharts.js"></script>
<script src="ui/js/dashboard/dash_sent.js"></script>
<!-- Chart Script end here -->

<link rel="stylesheet" href="ui/css/themes/base/jquery.ui.all.css">

<link rel="stylesheet" href="ui/css/style_login.css">

<link href="ui/css/style.css" media="screen" rel="stylesheet" type="text/css" />
<script>
    if (/*@cc_on!@*/false) {
        var headHTML = document.getElementsByTagName('head')[0].innerHTML;
        headHTML    += '<link type="text/css" rel="stylesheet" href="ui/css/ie10.css">';
        document.getElementsByTagName('head')[0].innerHTML = headHTML;
    }
</script>
<!-- this is for video play in flow player 3.2.12. -->
<script type="text/javascript" src="js/flowplayer/flowplayer-3.2.12.min.js"></script>
<script type="text/javascript">
function playVideo(id,playerId,playUrl) {
	parent.$.fn.colorbox({href:'div#emailList'+id, open:true, inline:true});
	  $f(playerId, "js/flowplayer/flowplayer-3.2.16.swf", {
		    clip: {
		        url: playUrl,
		        autoPlay: false,
		        autoBuffering: true
		    },
		    plugins: {
		        controls:{
		        	url: 'js/flowplayer/flowplayer.controls-3.2.15.swf',
		        	top: -20,
		            left: 0,
		            opacity: 0.95,
		            timeColor: '#980118',
		            all: true,
		            play: true,
		            scrubber: true,
		         tooltips: {
		             buttons: true,
		             fullscreen: 'Enter fullscreen mode'
		            }
		        }
		    },
		    onLoad: function(){
		    }
		});
}
</script>
<!-- Rating bar and profile starts here -->
<link rel="stylesheet" type="text/css" href="ui/css/ratingbar.css" />
<script type="text/javascript" src="ui/js/jquery.ratingbar.js"></script>

<!-- Rating bar and profile ends here -->


<script src="ui/js/jquery.ui.datepicker.js"></script>
<link rel="stylesheet" href="ui/Fancy-box/jquery.fancybox.css" type="text/css" media="screen" />
<link rel="stylesheet" href="ui/Fancy-box/popupbox/lytebox.css" type="text/css" media="screen" />
<script type="text/javascript" src="ui/fancyplayer_code/js/flowplayer-3.1.1.min.js"></script>
<script type="text/javascript" src="ui/Fancy-box/jquery.fancybox.pack.js"></script>
<script type="text/javascript" src="ui/Fancy-box/popupbox/lytebox.js"></script>
<script type="text/javascript" src="ui/fancyplayer_code/js/jquery.easing.1.3.js"></script>
<script type="text/javascript" src="ui/fancyplayer_code/js/fancyplayer.js"></script>
<!-- Rating bar and profile ends here -->

<script type="text/javascript">
    $(function () {
        $("select").selectbox();
    });
</script>
<!--- Questions Tabs starts------->
<script type="text/javascript" src="ui/js/ddaccordion.js"></script>
<script type="text/javascript">
	ddaccordion.init({
		headerclass: "question_tab_group", //Shared CSS class name of headers group
		contentclass: "question_detail_group", //Shared CSS class name of contents group
		revealtype: "click", //Reveal content when user clicks or onmouseover the header? Valid value: "click", "clickgo", or "mouseover"
		mouseoverdelay: 200, //if revealtype="mouseover", set delay in milliseconds before header expands onMouseover
		collapseprev: true, //Collapse previous content (so only one open at any time)? true/false 
		defaultexpanded: [], //index of content(s) open by default [index1, index2, etc]. [] denotes no content.
		onemustopen: true, //Specify whether at least one header should be open always (so never all headers closed)
		animatedefault: false, //Should contents open by default be animated into view?
		scrolltoheader: false, //scroll to header each time after it's been expanded by the user?
		persiststate: false, //persist state of opened contents within browser session?
		toggleclass: ["closedquestion_group", "openquestion_group"], //Two CSS classes to be applied to the header when it's collapsed and expanded, respectively ["class1", "class2"]
		togglehtml: ["none", "", " "], //Additional HTML added to the header when it's collapsed and expanded, respectively  ["position", "html1", "html2"] (see docs)
		animatespeed: "fast", //speed of animation: integer in milliseconds (ie: 200), or keywords "fast", "normal", or "slow"
		oninit:function(expandedindices){ //custom code to run when headers have initalized
			//do nothing
		},
		onopenclose:function(header, index, state, isuseractivated){ //custom code to run whenever a header is opened or closed
			var pieTab = 'pie_q_tab_group'+index;
			var qTab = 'q_tab_group'+index;
			if (state=="block" ){				
				$('#'+pieTab).hide();
				$('#'+qTab).show();
			}
			else{
				$('#'+pieTab).show();
				$('#'+qTab).hide();
			}
		}
	});
	
	
	ddaccordion.init({
		headerclass: "question_tab_region", //Shared CSS class name of headers group
		contentclass: "question_detail_region", //Shared CSS class name of contents group
		revealtype: "click", //Reveal content when user clicks or onmouseover the header? Valid value: "click", "clickgo", or "mouseover"
		mouseoverdelay: 200, //if revealtype="mouseover", set delay in milliseconds before header expands onMouseover
		collapseprev: true, //Collapse previous content (so only one open at any time)? true/false 
		defaultexpanded: [], //index of content(s) open by default [index1, index2, etc]. [] denotes no content.
		onemustopen: true, //Specify whether at least one header should be open always (so never all headers closed)
		animatedefault: false, //Should contents open by default be animated into view?
		scrolltoheader: false, //scroll to header each time after it's been expanded by the user?
		persiststate: false, //persist state of opened contents within browser session?
		toggleclass: ["closedquestion_region", "openquestion_region"], //Two CSS classes to be applied to the header when it's collapsed and expanded, respectively ["class1", "class2"]
		togglehtml: ["none", "", " "], //Additional HTML added to the header when it's collapsed and expanded, respectively  ["position", "html1", "html2"] (see docs)
		animatespeed: "fast", //speed of animation: integer in milliseconds (ie: 200), or keywords "fast", "normal", or "slow"
		oninit:function(expandedindices){ //custom code to run when headers have initalized
			//do nothing
		},
		onopenclose:function(header, index, state, isuseractivated){ //custom code to run whenever a header is opened or closed
			
			var pieTab = 'pie_q_tab_region'+index;
			var qTab = 'q_tab_region'+index;
			if (state=="block" ){				
				$('#'+pieTab).hide();
				$('#'+qTab).show();
				
			}
			else{
				$('#'+pieTab).show();
				$('#'+qTab).hide();
			}
		}
	});
</script>
<!--- Questions Tabs Ends------->

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
        $("#datepickerGroup").datepicker({
        	minDate: 0,
            altField:"#alternateGroup",
            altFormat:"yy-mm-dd"
        }).datepicker("setDate", "+1");
        
        $("#datepickerRegion").datepicker({
        	minDate: 0,
            altField:"#alternateRegion",
            altFormat:"yy-mm-dd"
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
		closeEffect	: 'none'});
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

<script type="text/javascript">

/* $(window).scroll(function(){	
	if($(window).scrollTop() >= $(document).height() - $(window).height() - 10){
		$('div#loadmoreajaxloader').show();
		$.ajax({
			type:'POST',
			url: "loadSentIncyyte.cyt",
			success: function(data){
				if (data.indexOf("success") != -1) {
					$(window).scrollTop(101);
					$("#sent_incyyte").load('displaySentIncyyte.cyt');	
					$('div#loadmoreajaxloader').hide();
					return false;
				}else{
					$('div#loadmoreajaxloader').html('<center>End of list.</center>');	
				}
			}
		});
	}
}); */
</script>
<script type="text/javascript">

    $(document).ready(function() {
        //Default Action
        $(".tab_content").hide(); //Hide all content
        $("ul.Stabs li:first").addClass("active").show(); //Activate first tab
        $(".tab_content:first").show(); //Show first tab content

        //On Click Event
        $("ul.Stabs li").click(function() {
            $("ul.Stabs li").removeClass("active"); //Remove any "active" class
            $(this).addClass("active"); //Add "active" class to selected tab
            $(".tab_content").hide(); //Hide all tab content
            var activeTab = $(this).find("a").attr("href"); //Find the rel attribute value to identify the active tab + content
            $(activeTab).fadeIn(); //Fade in the active content
            return false;
        });

    });
</script>

<form:form id="inCyyteForm" name="inCyyteForm" commandName="inCyyteForm" method="post">
	<form:hidden path="id" id="inc_id"/>
	<form:hidden path="closureDate" id="closureDate"/>


    <div class="tabsHolder">
        <ul class="Stabs">
            <li><a href="#SentToGroup">Sent to Group</a></li>
            <li><a href="#SentToRegion">Sent to Region</a></li>
        </ul>
        <div class="tab_container">
            <div id="SentToGroup" class="tab_content">
            <div id="most_resent_incyyte">

            <div id="sent_incyyte">

                <%-- <c:forEach var="myInCyyte" items="${myInCyytes}" varStatus="status" end="${SENT_ROW_MAX}"> --%>

            <c:forEach var="myGroupInCyyte" items="${myGroupSentInCyytes}" varStatus="status" >

            <c:if test="${not myGroupInCyyte.incyyte.deleted}">

            <% InCyyteChart cyyteChart = (InCyyteChart)pageContext.getAttribute("myGroupInCyyte");%>
            <script type="text/javascript">
                <!--
                $(document).ready(function () {

                    var chart0 = new Highcharts.Chart({
                        chart: {
                            renderTo: 'piecontainerGroupsmall${status.index}',
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
                            renderTo: 'piecontainerGroup${status.index}',
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
                    var button = $('#dropBoxButtonGroup${status.index}');
                    var box = $('#dropBoxGroup${status.index}');
                    var form = $('#dropBoxContentGroup${status.index}');
                    button.removeAttr('href');
                    button.mouseup(function(login) {
                        box.toggle();
                        button.toggleClass('active');
                    });
                    form.mouseup(function() {
                        return false;
                    });
                    $(this).mouseup(function(login) {
                        if(!($(login.target).parent('#dropBoxButtonGroup${status.index}').length > 0)) {
                            button.removeClass('active');
                            box.hide();
                        }
                    });
                });

                //-->
            </script>
            <!--<a href="#" onClick="ddaccordion.collapseall('question_tab'); return false">Collapse all</a> | <a href="#" onClick="ddaccordion.expandall('question_tab'); return false">Expand all</a>-->
            <div class="question_tab_group">
                <table width="100%" border="0" cellspacing="0" cellpadding="0" id="pie_q_tab_group${status.index}">
                    <tr>
                        <td width="17%"><div id="piecontainerGroupsmall${status.index}"
                                             style="width: 100px; height: 100px;"></div>
                        </td>
                        <td width="57%">
                            <h3><p class="questionText_group">${myGroupInCyyte.question}</p></h3>
                            <p>Created:${myGroupInCyyte.incyyte.createdDate}</p>
                            <p>Closure:<span id="closureDateValX_group${myGroupInCyyte.incyyteId}">${myGroupInCyyte.incyyte.closureDate}</span></p>

                            <c:if test="${not empty myGroupInCyyte.incyyte.group}">
                                <p>Group: ${myGroupInCyyte.incyyte.group.groupName}</p>
                            </c:if>
                        </td>
                        <td width="26%"><a href="" title="view incyyte"	id="view_incyyte${status.index}" class="title_view_incyyte" style="width: 171px;">
                            <span class="title_gray title_green_comment_ie8">VIEW INCYYTE</span></a>
                        </td>
                    </tr>
                </table>
                <table width="100%" border="0" cellspacing="0" cellpadding="0" style="display:none;" id="q_tab_group${status.index}">
                    <tr>
                        <td><h3><p class="questionText_group">${myGroupInCyyte.question}</p></h3>
                            <p>Created:
                                <span id="createdDateVal_group${myGroupInCyyte.incyyteId}"> ${myGroupInCyyte.incyyte.createdDate}</span>
                            </p>
                                <%-- <p>Closure:${myGroupInCyyte.incyyte.closureDate}</p> --%>
                            <p>Closure:
				      			<span id="closureDateVal_group${myGroupInCyyte.incyyteId}"> ${myGroupInCyyte.incyyte.closureDate}
                                  </span>&nbsp;
                                <span id="selectClosureTimeVal_group${myGroupInCyyte.incyyteId}" ></span>
                            </p>
                            <c:if test="${not empty myGroupInCyyte.incyyte.group}">
                                <p>Group: ${myGroupInCyyte.incyyte.group.groupName}</p>
                            </c:if>
                        </td>
                        <td>
                            <c:if test="${not empty myGroupInCyyte.incyyte.uploadLocation && not empty myGroupInCyyte.incyyte.upload_name}">
                                <c:set var="url"  value="${myGroupInCyyte.incyyte.uploadLocation}"/>
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
                                    <%if(extVideos.contains(extension)) {
                                    %>
                                    <a onClick="javascript:playVideo('${myGroupInCyyte.incyyte.id}','player${myGroupInCyyte.incyyte.id}', '${myGroupInCyyte.incyyte.uploadLocation}')"><img src="ui/images/video_thumb.png"  src="ui/images/video_thumb.png" style="display: block; float: right; margin: 10px 2px; width: 50px; height: 50px; border: 2px solid #ffffff; padding: 2px;" /></a>
                                    <div style="display:none;">
                                        <div id="emailList${myGroupInCyyte.incyyte.id}" class="emailList " >
                                            <a style="cursor:pointer;" id="player${myGroupInCyyte.incyyte.id}" ></a>
                                        </div>
                                    </div>
                                    <%}else if(extImages.contains(extension)){ %>
                                    <a href="${myGroupInCyyte.incyyte.uploadLocation}" class="thumbnail lytebox" >
                                        <img src="${myGroupInCyyte.incyyte.uploadLocation}"  style="display: block; float: right; margin: 10px 2px; width: 50px; height: 50px; border: 2px solid #ffffff; padding: 2px;" />
                                    </a>
                                    <span><img src="${myGroupInCyyte.incyyte.uploadLocation}" /></span>
                                    <%}else if(extDocs.contains(extension)){ %>
                                    <a style="cursor:pointer" href="" onClick="window.open('${myGroupInCyyte.incyyte.uploadLocation}','MyWindow'); return false;" target="_blank" class="thumbnail">
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
                            <c:if test="${not empty myGroupInCyyte.incyyte.youtubeUrl}">
								<p>
								<b>Question file:</b>
								<div class="thumbnail">
						
								<a onClick="javascript:playYoutubeVideoQues('${myGroupInCyyte.incyyte.id}' , '${myGroupInCyyte.incyyte.youtubeUrl}');" >
								<img src="ui/images/video_thumb.png" src="ui/images/video_thumb.png" style="display: block; float: right; margin: 10px 2px; width: 50px; height: 50px; border: 2px solid #ffffff; padding: 2px;" /></a>
								
								<div style="display:none;">
									<div id="emailListYouTube_${myGroupInCyyte.incyyte.id}" class="emailList " >
										 <iframe width='390' id="iFrameYouTube_${myGroupInCyyte.incyyte.id}"  height='308'  src="ui/images/uploading_big_img.png" frameborder='0' type='text/html'></iframe>
									</div>
								</div> 
								
							</div>
							</p>
						
						</c:if>
                        </td>
                    </tr>
                </table>
            </div>



            <div class="question_detail_group">
                <% 	int filesCount = 0;	%>
                <c:forEach var="ans" items="${myGroupInCyyte.incyyte.answers}"
                           varStatus="ansStatus">
                    <c:choose>
                        <c:when test="${not empty ans.uploadCDN_url}">
                            <%
                                filesCount++;
                            %>
                        </c:when>
				<c:when test="${not empty ans.youtubeURLAnswer}">
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
                                <c:forEach var="ans" items="${myGroupInCyyte.incyyte.answers}" varStatus="ansStatus">
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
                                            <% if (extAnsVideos.contains(StringUtils.lowerCase(extAnswer))) {%>

                                            <a style="cursor:pointer"class="thumbnail2">
                                                <img onClick="javascript:playVideo('${ans.id}','player${ans.id}', '${ans.uploadCDN_url}')" src="ui/images/video_thumb.png" id="answerImageIncyyte" style="display: block; float: left; margin: 10px 2px; width: 50px; height: 50px; border: 2px solid #ffffff; padding: 2px;" />
                                                <span><b>${ans.answerOption}</b><br> <img src="ui/images/video_thumb.png" /></span></a>
                                            <div style="display:none;">
                                                <div id="emailList${ans.id}" class="emailList ">
                                                    <a style="cursor:pointer" id="player${ans.id}" ></a>
                                                </div>
                                            </div>
                                            <% } else if (extAnsImages.contains(StringUtils.lowerCase(extAnswer))) { %>
                                            <a style="cursor: pointer" href="${ans.uploadCDN_url}" class="thumbnail2 fancybox-popup ">
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
					<c:when test="${not empty ans.youtubeURLAnswer}">
								
								<a style="cursor: pointer" class="thumbnail2"> 
								<img onClick="javascript:playYoutubeVideoQues('${ans.id}_answer','${ans.youtubeURLAnswer}')" src="ui/images/video_thumb.png" id="answerImageIncyyte"	style="display: block; float: left; margin: 10px 2px; width: 50px; height: 50px; border: 2px solid #ffffff; padding: 2px;" />
								<span><b>${ans.answerOption}</b><br> <img src="ui/images/video_thumb.png" /></span></a>
								<div style="display:none;">
									<div id="emailListYouTube_${ans.id}_answer" class="emailList " >
										 <iframe width='390' id="iFrameYouTube_${ans.id}_answer"  height='308'  src="ui/images/uploading_big_img.png" frameborder='0' type='text/html'></iframe>
									</div>
								</div>	
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
                                        <c:forEach var="ans" items="${myGroupInCyyte.incyyte.answers}" varStatus="ansStatus">
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
                                                                <a style="cursor:pointer" href="${ans.uploadCDN_url}" class="thumbnail view_icon fancybox-popup "></a>
                                                                <span><img src="ui/images/video_thumb.png" /></span>
                                                                <%} else if(extAnsImages.contains(StringUtils.lowerCase(extAnswer))){ %>
                                                                <a style="cursor:pointer" href="${ans.uploadCDN_url}" class="thumbnail view_icon fancybox-popup "></a>
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


                            <div id="piecontainerGroup${status.index}" style="width: 370px; height: 260px;"> </div>
                        </td>
                        <td width="40%" valign="top" class="grid_6a"><span>Responses</span>
                            <br> <strong>${myGroupInCyyte.incyyte.totalResponded}</strong> of
                            <strong>${myGroupInCyyte.incyyte.totalInCyyted}</strong> people responded<br> <br>
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
                            <a	href="dashdetails.cyt?code=${myGroupInCyyte.incyyte.incyyteCode}&frm=sent"
                                  title="view details" id="view_details" class="title_comments"
                                  style="width: 180px;"> <span class="title_green title_green_comment_ie8">DETAILS</span>
                            </a>
                            <!-- Options Drop Box Starts Here -->
                            <div id="dropBoxContainerGroup${status.index}" class="dropBoxContainer">
                                <a href="#" id="dropBoxButtonGroup${status.index}" class="dropBoxButton active">
                                    <span>more options</span><em></em>
                                </a>
                                <div style="clear:both"></div>
                                <div id="dropBoxGroup${status.index}" class="dropBox">
                                    <div id="dropBoxContentGroup${status.index}" class="dropBoxContent">
                                        <ul>
                                            <!-- <li><a href="#" onclick="display_datepicker('${myGroupInCyyte.incyyteId}');" class="inline" style="color:black; font-size:14px;">Edit Closing Time &amp; Date</a></li> -->
                                            <li><a href="#modal_datepicker_group" onclick="set_datepickerGroup('${myGroupInCyyte.incyyteId}');" class="inline" style="color:black; font-size:14px;">Edit Closing Time &amp; Date</a></li>
                                            <c:if test="${not empty myGroupInCyyte.incyyte.public_poll && myGroupInCyyte.incyyte.public_poll=='N'}">
                                                <li><a href="#" onclick="change_incyyte_type('${myGroupInCyyte.incyyteId}', 'PU');" style="color:black; font-size:14px;">Make Public</a></li>
                                            </c:if>
                                            <c:if test="${not empty myGroupInCyyte.incyyte.public_poll && myGroupInCyyte.incyyte.public_poll=='Y'}">
                                                <li><a href="#" onclick="change_incyyte_type('${myGroupInCyyte.incyyteId}','PR');" style="color:black; font-size:14px;">Turn Off Sharing</a></li>
                                            </c:if>
                                            <li><a href="#" onclick="delete_incyyte('${myGroupInCyyte.incyyteId}', 'sent');" id="delete_incyyte" style="color:black; font-size:14px;">Delete this InCyyte </a></li>
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
            </c:if>
            </c:forEach>

            </div>
            <!-- Pagination start---->
            <div class='pagination'>
                    <%--For displaying Previous link except for the 1st page --%>
                <c:if test="${currentPage != 1}">
                    <a class='prev page-numbers'
                       href="dashsent.cyt?page=${currentPage - 1}&param=${text}">Prev</a>
                </c:if>
                    <%--For displaying Page numbers.
                 The when condition does not display a link for the current page--%>
                <c:forEach begin="1" end="${sentnoOfPages}" var="i">
                    <c:choose>
                        <c:when test="${currentPage eq i}">
                            <span class='page-numbers current'>${i}</span>
                        </c:when>
                        <c:otherwise>
                            <c:if test="${i <= 5}">
                                <a class='page-numbers'
                                   href="dashsent.cyt?page=${i}&param=${text}">${i}</a>
                            </c:if>
                            <c:if test="${i > 5}">
                                <c:if test="${i+1 == sentnoOfPages}">
                                    <a class='page-numbers'>..</a>
                                    <a class='page-numbers'
                                       href="dashsent.cyt?page=${i}&param=${text}">${i}</a>
                                </c:if>
                                <c:if test="${i == sentnoOfPagesentnoOfPages}">
                                    <a class='page-numbers'
                                       href="dashsent.cyt?page=${i}&param=${text}">${i}</a>
                                </c:if>
                            </c:if>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>
                    <%--For displaying Next link --%>
                <c:if test="${currentPage lt noOfPages}">
                    <a class='next page-numbers'
                       href="dashsent.cyt?page=${currentPage + 1}&param=${text}">Next</a>
                </c:if>
            </div>
            <!-- Pagination ends---->
            <div id="loadmoreajaxloader" style="display:none;"><center><img src="images/loading.gif" /></center></div>

            </div>
            <div style='display:none'>
                <input type="hidden" id="alternateGroup" size="11"  />

                <div id="modal_datepicker_group">
                    <h3 class="font_20px">Change the closing date</h3>

                    <div id="datepickerGroup"></div>
                    <h3 class="font_20px" style="padding-top:15px;">Change the closing Time</h3>

                    <p>

                    <div id="form">
                        <label style="width:130px;">
                            <form:select path="closureTime" id="selectClosureTimeGroup" >
                                <form:option value="01:00">01:00</form:option>
                                <form:option value="02:00">02:00</form:option>
                                <form:option value="03:00">03:00</form:option>
                                <form:option value="04:00">04:00</form:option>
                                <form:option value="05:00">05:00</form:option>
                                <form:option value="06:00">06:00</form:option>
                                <form:option value="07:00">07:00</form:option>
                                <form:option value="08:00">08:00</form:option>
                                <form:option value="09:00">09:00</form:option>
                                <form:option value="10:00">10:00</form:option>
                                <form:option value="11:00">11:00</form:option>
                                <form:option value="12:00" selected="selected">12:00</form:option>
                                <form:option value="13:00">13:00</form:option>
                                <form:option value="14:00">14:00</form:option>
                                <form:option value="15:00">15:00</form:option>
                                <form:option value="16:00">16:00</form:option>
                                <form:option value="17:00">17:00</form:option>
                                <form:option value="18:00">18:00</form:option>
                                <form:option value="19:00">19:00</form:option>
                                <form:option value="20:00">20:00</form:option>
                                <form:option value="21:00">21:00</form:option>
                                <form:option value="22:00">22:00</form:option>
                                <form:option value="23:00">23:00</form:option>
                                <form:option value="24:00">24:00</form:option>
                            </form:select>
                        </label>

                        <div title="Save" id="modal_datepicker_group_btn" class="button_green" style="width:80px; float:right">
                            <span class="title_green"  onclick="">SAVE</span>
                        </div>
                    </div>
                    </p>
                </div>
            </div>
            </div>
    <div id="SentToRegion" class="tab_content">
       <%--Sent To Region--%>
       <div id="most_resent_incyyte1">

       <div id="sent_incyyte1">
       <c:forEach var="myRegionInCyyte" items="${myRegionSentInCyytes}" varStatus="status" >
       <c:if test="${not myRegionInCyyte.incyyte.deleted}">
       <% InCyyteChart cyyteChart = (InCyyteChart)pageContext.getAttribute("myRegionInCyyte");%>
       <script type="text/javascript">
           <!--
           $(document).ready(function () {

               var chart0 = new Highcharts.Chart({
                   chart: {
                       renderTo: 'piecontainerRegionsmall${status.index}',
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
                       renderTo: 'piecontainerRegion${status.index}',
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
               var button = $('#dropBoxButtonRegion${status.index}');
               var box = $('#dropBoxRegion${status.index}');
               var form = $('#dropBoxContentRegion${status.index}');
               button.removeAttr('href');
               button.mouseup(function(login) {
                   box.toggle();
                   button.toggleClass('active');
               });
               form.mouseup(function() {
                   return false;
               });
               $(this).mouseup(function(login) {
                   if(!($(login.target).parent('#dropBoxButtonRegion${status.index}').length > 0)) {
                       button.removeClass('active');
                       box.hide();
                   }
               });
           });

       </script>
       <!--<a href="#" onClick="ddaccordion.collapseall('question_tab'); return false">Collapse all</a> | <a href="#" onClick="ddaccordion.expandall('question_tab'); return false">Expand all</a>-->
       <div class="question_tab_region">
           <table width="100%" border="0" cellspacing="0" cellpadding="0" id="pie_q_tab_region${status.index}">
               <tr>
                   <td width="17%"><div id="piecontainerRegionsmall${status.index}"
                                        style="width: 100px; height: 100px;"></div>
                   </td>
                   <td width="57%">
                                <h3><p class="questionText_region">${myRegionInCyyte.question}</p></h3>
                                <p>Created:${myRegionInCyyte.incyyte.createdDate}</p>
                                <p>Closure:<span id="closureDateValX_region${myRegionInCyyte.incyyteId}">${myRegionInCyyte.incyyte.closureDate}</span></p>

                       <c:if test="${not empty myRegionInCyyte.incyyte.group}">
                                    <p>Group: ${myRegionInCyyte.incyyte.group.groupName}</p>
                       </c:if>
                   </td>
                   <td width="26%"><a href="" title="view incyyte"	id="view_incyyte${status.index}" class="title_view_incyyte" style="width: 171px;">
                       <span class="title_gray title_green_comment_ie8">VIEW INCYYTE</span></a>
                   </td>
               </tr>
           </table>
           <table width="100%" border="0" cellspacing="0" cellpadding="0" style="display:none;" id="q_tab_region${status.index}">
               <tr>
                            <td><h3><p class="questionText_region">${myRegionInCyyte.question}</p></h3>
                       <p>Created:
                                    <span id="createdDateVal_region${myRegionInCyyte.incyyteId}"> ${myRegionInCyyte.incyyte.createdDate}</span>
                       </p>
                                    <%-- <p>Closure:${myInCyyte.incyyte.closureDate}</p> --%>
                       <p>Closure:
				      			<span id="closureDateVal_region${myRegionInCyyte.incyyteId}"> ${myRegionInCyyte.incyyte.closureDate}
                                  </span>&nbsp;
                                    <span id="selectClosureTimeVal_region${myRegionInCyyte.incyyteId}" ></span>
                       </p>
                                <c:if test="${not empty myRegionInCyyte.incyyte.group}">
                                    <p>Group: ${myRegionInCyyte.incyyte.group.groupName}</p>
                       </c:if>
                   </td>
                   <td>
                       <c:if test="${not empty myRegionInCyyte.incyyte.uploadLocation && not empty myRegionInCyyte.incyyte.upload_name}">
                           <c:set var="url"  value="${myRegionInCyyte.incyyte.uploadLocation}"/>
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
                               <%if(extVideos.contains(extension)) {
                               %>
                               <a onClick="javascript:playVideo('${myRegionInCyyte.incyyte.id}','player${myRegionInCyyte.incyyte.id}', '${myRegionInCyyte.incyyte.uploadLocation}')"><img src="ui/images/video_thumb.png"  src="ui/images/video_thumb.png" style="display: block; float: right; margin: 10px 2px; width: 50px; height: 50px; border: 2px solid #ffffff; padding: 2px;" /></a>
                               <div style="display:none;">
                                            <div id="emailList${myRegionInCyyte.incyyte.id}" class="emailList " >
                                       <a style="cursor:pointer;" id="player${myRegionInCyyte.incyyte.id}" ></a>
                                   </div>
                               </div>
                               <%}else if(extImages.contains(extension)){ %>
                               <a href="${myRegionInCyyte.incyyte.uploadLocation}" class="thumbnail lytebox" >
                                   <img src="${myRegionInCyyte.incyyte.uploadLocation}"  style="display: block; float: right; margin: 10px 2px; width: 50px; height: 50px; border: 2px solid #ffffff; padding: 2px;" />
                               </a>
                               <span><img src="${myRegionInCyyte.incyyte.uploadLocation}" /></span>
                               <%}else if(extDocs.contains(extension)){ %>
                               <a style="cursor:pointer" href="" onClick="window.open('${myRegionInCyyte.incyyte.uploadLocation}','MyWindow'); return false;" target="_blank" class="thumbnail">
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
                       <c:if test="${not empty myRegionInCyyte.incyyte.youtubeUrl}">
								<p>
								<b>Question file:</b>
								<div class="thumbnail">
						
								<a onClick="javascript:playYoutubeVideoQues('${myRegionInCyyte.incyyte.id}' , '${myRegionInCyyte.incyyte.youtubeUrl}');" >
								<img src="ui/images/video_thumb.png" src="ui/images/video_thumb.png" style="display: block; float: right; margin: 10px 2px; width: 50px; height: 50px; border: 2px solid #ffffff; padding: 2px;" /></a>
								
								<div style="display:none;">
									<div id="emailListYouTube_${myRegionInCyyte.incyyte.id}" class="emailList " >
										 <iframe width='390' id="iFrameYouTube_${myRegionInCyyte.incyyte.id}"  height='308'  src="ui/images/uploading_big_img.png" frameborder='0' type='text/html'></iframe>
									</div>
								</div> 
								
							</div>
							</p>
						
						</c:if>
                   </td>
               </tr>
           </table>
       </div>



       <div class="question_detail_region">
           <% 	int filesCount = 0;	%>
           <c:forEach var="ans" items="${myRegionInCyyte.incyyte.answers}"
                      varStatus="ansStatus">
               <c:choose>
                   <c:when test="${not empty ans.uploadCDN_url}">
                       <%
                           filesCount++;
                       %>
                   </c:when>
                   <c:when test="${not empty ans.youtubeURLAnswer}">
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
                           <c:forEach var="ans" items="${myRegionInCyyte.incyyte.answers}" varStatus="ansStatus">
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
                                       <% if (extAnsVideos.contains(StringUtils.lowerCase(extAnswer))) {%>

                                       <a style="cursor:pointer"class="thumbnail2">
                                           <img onClick="javascript:playVideo('${ans.id}','player${ans.id}', '${ans.uploadCDN_url}')" src="ui/images/video_thumb.png" id="answerImageIncyyte" style="display: block; float: left; margin: 10px 2px; width: 50px; height: 50px; border: 2px solid #ffffff; padding: 2px;" />
                                           <span><b>${ans.answerOption}</b><br> <img src="ui/images/video_thumb.png" /></span></a>
                                       <div style="display:none;">
                                           <div id="emailList${ans.id}" class="emailList ">
                                               <a style="cursor:pointer" id="player${ans.id}" ></a>
                                           </div>
                                       </div>
                                       <% } else if (extAnsImages.contains(StringUtils.lowerCase(extAnswer))) { %>
                                       <a style="cursor: pointer" href="${ans.uploadCDN_url}" class="thumbnail2 fancybox-popup ">
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
					<c:when test="${not empty ans.youtubeURLAnswer}">
								
								<a style="cursor: pointer" class="thumbnail2"> 
								<img onClick="javascript:playYoutubeVideoQues('${ans.id}_answer','${ans.youtubeURLAnswer}')" src="ui/images/video_thumb.png" id="answerImageIncyyte"	style="display: block; float: left; margin: 10px 2px; width: 50px; height: 50px; border: 2px solid #ffffff; padding: 2px;" />
								<span><b>${ans.answerOption}</b><br> <img src="ui/images/video_thumb.png" /></span></a>
								<div style="display:none;">
									<div id="emailListYouTube_${ans.id}_answer" class="emailList " >
										 <iframe width='390' id="iFrameYouTube_${ans.id}_answer"  height='308'  src="ui/images/uploading_big_img.png" frameborder='0' type='text/html'></iframe>
									</div>
								</div>	
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
                                   <c:forEach var="ans" items="${myRegionInCyyte.incyyte.answers}" varStatus="ansStatus">
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
                                                           <a style="cursor:pointer" href="${ans.uploadCDN_url}" class="thumbnail view_icon fancybox-popup "></a>
                                                           <span><img src="ui/images/video_thumb.png" /></span>
                                                           <%} else if(extAnsImages.contains(StringUtils.lowerCase(extAnswer))){ %>
                                                           <a style="cursor:pointer" href="${ans.uploadCDN_url}" class="thumbnail view_icon fancybox-popup "></a>
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


                       <div id="piecontainerRegion${status.index}" style="width: 370px; height: 260px;"> </div>
                   </td>
                   <td width="40%" valign="top" class="grid_6a"><span>Responses</span>
                       <br> <strong>${myRegionInCyyte.incyyte.totalResponded}</strong> of
                       <strong>${myRegionInCyyte.incyyte.totalInCyyted}</strong> people responded<br> <br>
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
                       <a	href="dashdetails.cyt?code=${myRegionInCyyte.incyyte.incyyteCode}&frm=sent"
                                      title="view details" id="view_details" class="title_comments"
                             style="width: 180px;"> <span class="title_green title_green_comment_ie8">DETAILS</span>
                       </a>
                       <!-- Options Drop Box Starts Here -->
                                <div id="dropBoxContainerRegion${status.index}" class="dropBoxContainer">
                                    <a href="#" id="dropBoxButtonRegion${status.index}" class="dropBoxButton active">
                               <span>more options</span><em></em>
                           </a>
                           <div style="clear:both"></div>
                                    <div id="dropBoxRegion${status.index}" class="dropBox">
                                        <div id="dropBoxContentRegion${status.index}" class="dropBoxContent">
                                   <ul>
                                       <!-- <li><a href="#" onclick="display_datepicker('${myInCyyte.incyyteId}');" class="inline" style="color:black; font-size:14px;">Edit Closing Time &amp; Date</a></li> -->
                                                <li><a href="#modal_datepicker_region" onclick="set_datepickerRegion('${myRegionInCyyte.incyyteId}');" class="inline" style="color:black; font-size:14px;">Edit Closing Time &amp; Date</a></li>
                                       <c:if test="${not empty myRegionInCyyte.incyyte.public_poll && myRegionInCyyte.incyyte.public_poll=='N'}">
                                           <li><a href="#" onclick="change_incyyte_type('${myRegionInCyyte.incyyteId}', 'PU');" style="color:black; font-size:14px;">Make Public</a></li>
                                       </c:if>
                                       <c:if test="${not empty myRegionInCyyte.incyyte.public_poll && myRegionInCyyte.incyyte.public_poll=='Y'}">
                                           <li><a href="#" onclick="change_incyyte_type('${myRegionInCyyte.incyyteId}','PR');" style="color:black; font-size:14px;">Turn Off Sharing</a></li>
                                       </c:if>
                                                <li><a href="#" onclick="delete_incyyte('${myRegionInCyyte.incyyteId}', 'sent');" id="delete_incyyte" style="color:black; font-size:14px;">Delete this InCyyte </a></li>
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
       </c:if>
       </c:forEach>

       </div>
       <!-- Pagination start---->

                <div class='pagination'>
                        <%--For displaying Previous link except for the 1st page --%>
                    <c:if test="${currentPage != 1}">
                        <a class='prev page-numbers'
                           href="dashsent.cyt?page=${currentPage - 1}&param=${text}">Prev</a>
                    </c:if>
                        <%--For displaying Page numbers.
             The when condition does not display a link for the current page--%>
                    <c:forEach begin="1" end="${sentnoOfPages}" var="i">
                        <c:choose>
                            <c:when test="${currentPage eq i}">
                                <span class='page-numbers current'>${i}</span>
                            </c:when>
                            <c:otherwise>
                                <c:if test="${i <= 5}">
                                    <a class='page-numbers'
                                       href="dashsent.cyt?page=${i}&param=${text}">${i}</a>
                                </c:if>
                                <c:if test="${i > 5}">
                                    <c:if test="${i+1 == sentnoOfPages}">
                                        <a class='page-numbers'>..</a>
                                        <a class='page-numbers'
                                           href="dashsent.cyt?page=${i}&param=${text}">${i}</a>
                                    </c:if>
                                    <c:if test="${i == sentnoOfPagesentnoOfPages}">
                                        <a class='page-numbers'
                                           href="dashsent.cyt?page=${i}&param=${text}">${i}</a>
                                    </c:if>
                                </c:if>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>
                        <%--For displaying Next link --%>
                    <c:if test="${currentPage lt noOfPages}">
                        <a class='next page-numbers'
                           href="dashsent.cyt?page=${currentPage + 1}&param=${text}">Next</a>
                    </c:if>
                </div>
       <!-- Pagination ends---->
                <div id="loadmoreajaxloader" style="display:none;"><center><img src="images/loading.gif" /></center></div>

       </div>
       <div style='display:none'>
                    <input type="hidden" id="alternateRegion" size="11"  />

                    <div id="modal_datepicker_region">
               <h3 class="font_20px">Change the closing date</h3>

                        <div id="datepickerRegion"></div>
               <h3 class="font_20px" style="padding-top:15px;">Change the closing Time</h3>

               <p>

                        <div id="form">
                   <label style="width:130px;">
                       <form:select path="closureTime" id="selectClosureTimeRegion" >
                           <form:option value="01:00">01:00</form:option>
                           <form:option value="02:00">02:00</form:option>
                           <form:option value="03:00">03:00</form:option>
                           <form:option value="04:00">04:00</form:option>
                           <form:option value="05:00">05:00</form:option>
                           <form:option value="06:00">06:00</form:option>
                           <form:option value="07:00">07:00</form:option>
                           <form:option value="08:00">08:00</form:option>
                           <form:option value="09:00">09:00</form:option>
                           <form:option value="10:00">10:00</form:option>
                           <form:option value="11:00">11:00</form:option>
                           <form:option value="12:00" selected="selected">12:00</form:option>
                           <form:option value="13:00">13:00</form:option>
                           <form:option value="14:00">14:00</form:option>
                           <form:option value="15:00">15:00</form:option>
                           <form:option value="16:00">16:00</form:option>
                           <form:option value="17:00">17:00</form:option>
                           <form:option value="18:00">18:00</form:option>
                           <form:option value="19:00">19:00</form:option>
                           <form:option value="20:00">20:00</form:option>
                           <form:option value="21:00">21:00</form:option>
                           <form:option value="22:00">22:00</form:option>
                           <form:option value="23:00">23:00</form:option>
                           <form:option value="24:00">24:00</form:option>
                       </form:select>
                   </label>

                            <div title="Save" id="modal_datepicker_region_btn" class="button_green" style="width:80px; float:right">
                       <span class="title_green"  onclick="">SAVE</span>
                   </div>
               </div>
               </p>
           </div>
       </div>
       <%--Sent To Region--%>
    </div>
            </div>
    </div>
</form:form>
<style>
    .emailList{
        width: 410px;
    }
    .emailList a object{
        width: 390px;
    }
</style>