<%@ include file="/WEB-INF/jsp/common/include.jsp" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">
    <link rel="shortcut icon" href="favicon.ico" />

    <title>inCyyte - My Messages</title>
    <meta charset="utf-8">
    <link rel="stylesheet" href="ui/css/reset.css">
    <link rel="stylesheet" href="ui/css/layout.css">
    <link rel="stylesheet" href="ui/css/buttons.css"  media="screen">
    <link rel="stylesheet" href="ui/css/style_login.css">
    <link rel="stylesheet" href="ui/css/accordionmenu.css" type="text/css">
    <link rel="stylesheet" href="ui/css/icons_sprites.css">
    <link rel="stylesheet" href="fonts/fonts_stylesheet.css">
    <link rel="stylesheet" href="ui/css/ratingbar.css" type="text/css"/>

    <link rel="stylesheet" href="ui/css/ui.progress-bar.css" type="text/css"/>
    <link rel="stylesheet" href="ui/modal/colorbox.css">
    <script src="ui/js/jquery-1.4.2.min.js"></script>
    <script src="ui/js/jquery-1.7.1.js"></script>
    <script src="ui/js/search_script.js"></script>
    <script src="ui/js/jquery.color.js"></script>
    <!-- Left Navigation script starts here -->
    <script src="ui/js/left_menu.js"></script>
    <script src="ui/js/ba-linkify.js"></script>

    <link rel="stylesheet" href="ui/css/ui.progress-bar.css" type="text/css"/>
    <link rel="stylesheet" href="ui/modal/colorbox.css">
    <link rel="stylesheet" href="ui/css/ratingbar.css" type="text/css"/>

    <link rel="stylesheet" href="ui/css/style.css">

    <script type="text/javascript" src="ui/js/jquery-1.8.3.min.js"></script>
    <script src="ui/js/jquery_profile_rating.js" type="text/javascript"></script>
    <script src="ui/js/star_rating.js" type="text/javascript"></script>
    <!-- Left Navigation script ends here -->
    <!--- placeholder Starts----->
    <script>
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
    <script type="text/javascript" src="ui/js/ddaccordion.js"></script>
    <script type="text/javascript">
        //Initialize :
        ddaccordion.init({
            headerclass:"message_tab", //Shared CSS class name of headers group
            contentclass:"message_detail", //Shared CSS class name of contents group
            revealtype:"click", //Reveal content when user clicks or onmouseover the header? Valid value: "click", "clickgo", or "mouseover"
            mouseoverdelay:200, //if revealtype="mouseover", set delay in milliseconds before header expands onMouseover
            collapseprev:true, //Collapse previous content (so only one open at any time)? true/false
            defaultexpanded:[], //index of content(s) open by default [index1, index2, etc]. [] denotes no content.
            onemustopen:true, //Specify whether at least one header should be open always (so never all headers closed)
            animatedefault:false, //Should contents open by default be animated into view?
            scrolltoheader:false, //scroll to header each time after it's been expanded by the user?
            persiststate:false, //persist state of opened contents within browser session?
            toggleclass:["closedmessage", "openmessage"], //Two CSS classes to be applied to the header when it's collapsed and expanded, respectively ["class1", "class2"]
            togglehtml:["none", "", " "], //Additional HTML added to the header when it's collapsed and expanded, respectively  ["position", "html1", "html2"] (see docs)
            animatespeed:"fast", //speed of animation: integer in milliseconds (ie: 200), or keywords "fast", "normal", or "slow"
            oninit:function (expandedindices) { //custom code to run when headers have initalized
                //do nothing
            },
            onopenclose:function (header, index, state, isuseractivated) { //custom code to run whenever a header is opened or closed
                //do nothing
            }
        })

    </script> 
    <!--[if IE 8]>
    <link href="ui/css/ie8.css" media="screen" rel="stylesheet" type="text/css" />
    <![endif]-->
</head>
<%--<script type="text/javascript">
    $(window).load(function() {
        $(".loader").fadeOut("slow");
    })
</script>--%>
<body>
<%--
<div class="loader" style="background: url('${pageContext.request.contextPath}/ui/images/common/long_loading_bar.gif') 50% 50% no-repeat rgb(249,249,249)"></div>
--%>
<div id="gradient">
    <div class="extra">
        <!-- include header -->
        <jsp:include page="../common/includes/header.jsp"/>
        <div class="main">
            <!--content -->
            <article id="content">
                <div id="main_content">
                    <div class="grid_9">
                        <h1>Messages</h1>
                        <nav>
                            <script type="text/javascript" src="ui/includes/leftmenu_messages.js"></script>
                        </nav>
                        <div class="hline"></div>
                        <div id="advert"><img src="ui/images/advert.png" alt="Advert"></div>
                    </div>
                    <div class="line"><span></span></div>
                    <div class="grid_17">
                        <!------ Search Box Starts -------->
                        <div id="advanced_searchbox"  class="advanced_searchbox_ie8">
                        <div id="searchbox" style="margin-bottom:0">
                            <p class="sort_by_text">Advanced Search</p>
                            <div id="searchmain">
                                <p class="search_heading">Search</p>
                                <form id="searchForm">
                                    <fieldset>
                                        <div class="input">
                                            <input type='text' name="search" id="search" value="Enter your search"/>
                                        </div>
                                        <input type="submit" id="searchSubmit" value=""/>
                                    </fieldset>
                                </form>
                            </div>
                        </div>
                        </div>
                        <!------ Search Box End-------->
                        <!--All Message Start -->
                        <%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
                        
                        <div id="all_messages">
                            <c:forEach items="${messages}" var="message" varStatus="msgStatus">
                                <div class="message_tab">
                                    <table width="100%" border="0" cellspacing="0" cellpadding="0">
                               			<tr>
                                        	<c:choose>           
                                        		<c:when test="${not empty message.photoUrl}">
                                            		<td width="6%"><img src="${message.photoUrl}" width="32" height="32" class="photoframe"></td>
                                        		</c:when>
                                       			<c:otherwise>
                                              		<td width="6%"><img src="ui/images/default_avatar.png" width="32" height="32" class="photoframe"></td>
                                       			</c:otherwise> 
                                       		</c:choose>
                                          	<td width="72%" style="padding-left:30px;">
                                          	
                                                <p class="message_heading">
                                     				<a  id = 'detailView_${message.id}' onClick='doDetailView("<c:out value="${message.id}"/>","<c:out value="${message.contactId}"/>", "<c:out value="${user.email} "/>")'> ${message.contactName}</a>
                                     			</p>
                                     			<c:set var="msgTitle" value="${fn:substring(message.messageText, 0, 50)}" />
                                     			<c:set var="messageText" value="${message.messageText}"/>
                                     			<%
                                     			 	String mText = (String)pageContext.getAttribute("messageText");
                                     				mText = mText.replaceAll("\r\n", "<br/>");
                                     				mText = mText.replaceAll("\n", "<br/>");
                                     				
                                     				String mTitle = (String)pageContext.getAttribute("msgTitle");
                                     				mTitle = mTitle.replaceAll("\r\n", "<br/>");
                                     				mTitle = mTitle.replaceAll("\n", "<br/>");
                                     				
                                     				if(mTitle.indexOf("<br/>") != -1)
                                     					mTitle = mTitle.substring(0, mTitle.indexOf("<br/>"));                                     				
                                     				
                                     			%>
                                     			<script type="text/javascript">
                                     				$(document).ready(function () {                                             	 		
                                             			var msgTitle = "<%=mTitle%>";
                                             			var replaceTitle = linkify(msgTitle);
                                             			$('#linkify_text_${msgStatus.index}').html(replaceTitle);
                                             			
                                             			var msgText = "<%=mText%>";
                                             			var replaceText = linkify(msgText);                                              			
                                             			$('#linkify_detail_text_${msgStatus.index}').html(replaceText);   
                                             		});
                                             	</script>
                                             	
                                     			<c:choose>           
                                     				<c:when test="${message.status =='UNREAD'}">
                                        				<font style="font-weight:bold;"> 
                                               		 		<p class="message_text" id="linkify_text_${msgStatus.index}">
                                               		 		</p>
                                       					</font> 
                                     				</c:when>
                                     				<c:otherwise>                                      	 				
                                               			<p class="message_text" id="linkify_text_${msgStatus.index}">  		 		
                                               		 	</p>
                                     				</c:otherwise>
                                    			</c:choose>
                                    			
                                       		</td>
                                            <td width="22%"><p class="message_date"> ${message.formattedMessageDate}</p>
                                                <p><a class="delete_blue" id='deleteConversation_<c:out value="${message.id}"/>' style="margin-left: 10px;"
                                                      onclick='doHideShow(true,<c:out value="${message.id}"/>)'>Delete Conversation</a>
                                                </p>

                                                <div id='msg_delete_confirm_<c:out value="${message.id}"/>' class='nodisplay ' name="msg_delete_popup" >This will permanently remove this Conversation from your feed.<br>
                                                    <input type="hidden" value="1" id="test1">
                                                    <a onClick='doHideShow(false, <c:out value="${message.id}"/>)'>Cancel</a> | <a id='delete_<c:out value="${message.id}"/>' onClick='submitDelete("<c:out value="${message.id}"/>",100)'>Delete</a>
                                                </div>
                                          	</td>
                                        </tr>
                                    </table>
                                </div>
                                <div id="message_detail_${message.id}" class="message_detail">
                                	<span id="linkify_detail_text_${msgStatus.index}"></span>
                                </div>

                            </c:forEach>
                        </div>
                        <!-- All Message Ends-->
                       </div>
                    
                    
                     <div class='pagination'> 
    
    
    	 <%--For displaying Previous link except for the 1st page --%>
				      <c:if test="${currentPage != 1}">
				     <a class='prev page-numbers' href="./messages.cyt?page=${currentPage - 1}">Prev</a>
				         </c:if>       
					<%--For displaying Page numbers.
				    The when condition does not display a link for the current page--%>
				    
				 
					        <c:forEach begin="1" end="${noOfPages}" var="i">
				                <c:choose>
				                    <c:when test="${currentPage eq i}">
				                 	<span class='page-numbers current'>${i}</span> 
				                    </c:when>
				                 
				                    <c:otherwise>
				                    
				                    <c:if test="${i <= 5}">
				                      <a class='page-numbers' href='./messages.cyt?page=${i}'>${i}</a> 
				                    
				                    </c:if>
				                 
				                   <c:if test="${i > 5}">
				                    
				                    <c:if test="${i+1 == noOfPages}">
				                      <a class='page-numbers'>..</a> 
				                    
				                      <a class='page-numbers' href='./messages.cyt?page=${i}'>${i}</a> 
				                    
				                    </c:if>
				                    
				                     <c:if test="${i == noOfPages}">
				                      <a class='page-numbers' href='./messages.cyt?page=${i}'>${i}</a> 
				                    
				                    </c:if>
				                    </c:if>
				                    </c:otherwise>
				                </c:choose>
				            </c:forEach>
					
					 <%--For displaying Next link --%>
				    <c:if test="${currentPage lt noOfPages}">
				        <a class='next page-numbers' href="./messages.cyt?page=${currentPage + 1}">Next</a>
				    </c:if>
					
        </div>	
                </div>
            </article>
            <!--content ends -->
        </div>
    </div>
    <!-- include footer -->
    <jsp:include page="../common/includes/footer.jsp"/>
    </div>
    <script type="text/javascript" src="ui/js/googleanalytics.js"></script>
</body>
</html>
