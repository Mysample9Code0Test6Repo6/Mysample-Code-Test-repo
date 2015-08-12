<!DOCTYPE html>
<html lang="en">
<%@ include file="/WEB-INF/jsp/common/include.jsp" %>
<head>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">
    <link rel="shortcut icon" href="favicon.ico" />
    
    <title>inCyyte - Read Message</title>
    <meta charset="utf-8">
    <link rel="stylesheet" href="ui/css/reset.css">
    <link rel="stylesheet" href="ui/css/layout.css">
    <link rel="stylesheet" href="ui/css/buttons.css" media="screen">
    <link rel="stylesheet" href="ui/css/style_login.css">
    <link rel="stylesheet" href="ui/css/accordionmenu.css" type="text/css">
    <link rel="stylesheet" href="ui/css/icons_sprites.css">
    <link rel="stylesheet" href="fonts/fonts_stylesheet.css">
	<link rel="stylesheet" href="ui/css/ratingbar.css" type="text/css"/>    
    
	<link rel="stylesheet" href="ui/css/ui.progress-bar.css" type="text/css"/>
	<link rel="stylesheet" href="ui/modal/colorbox.css">
	
	<script src="ui/js/jquery-1.7.1.js"></script>
	<script type="text/javascript" src="ui/js/jquery-1.8.3.min.js"></script>
	<script src="ui/js/customSelect.jquery.js" type="text/javascript"></script>
	
	<script src="ui/js/jquery.color.js"></script>
	<script src="ui/modal/colorbox/jquery.colorbox.js"></script>
	<script src="ui/js/modernizr-1.7.min.js"></script>
	
	<script type="text/javascript" src="ui/js/external/jquery.bgiframe-2.1.2.js"></script>
	<script type="text/javascript" src="js/jquery-ui-1.8.13.custom.min.js"></script>
	<script type="text/javascript" src="js/external/jquery.bgiframe-2.1.2.js"></script>
	<script type="text/javascript" src="js/communicator.js"></script>
	<script type="text/javascript" src="js/login.js"></script>
	<script type='text/javascript' src='js/accountry.0.2/jquery.autocomplete.js'></script>
	<script type='text/javascript' src='js/accountry.0.2/countries.en.js'></script>
	<script type="text/javascript" src="js/jquery.form.js"></script>
	<script src="ui/js/jquery.selectbox-0.2.js" type="text/javascript"></script>
	
	
    <script type="text/javascript" src="ui/js/search_script.js"></script>
    <!-- Left Navigation script starts here -->
   
	
	<script type="text/javascript" src="ui/js/jquery_profile_rating.js"></script>
    <script type="text/javascript" src="ui/js/star_rating.js"></script>
    <script type="text/javascript" src="ui/js/ba-linkify.js"></script>
    <script type="text/javascript" src="ui/js/left_menu.js"></script>
	 
    <!-- Left Navigation script ends here -->
    <!--- placeholder Starts----->
    <script>
        // placeholder polyfill
        $(document).ready(function () {
            function add() {
                if ($(this).val() == '') {
                    $(this).val($(this).attr('placeholder')).addClass('placeholder');
                }
            }

            function remove() {
                if ($(this).val() == $(this).attr('placeholder')) {
                    $(this).val('').removeClass('placeholder');
                }
            }

            // Create a dummy element for feature detection
            if (!('placeholder' in $('<input>')[0])) {

                // Select the elements that have a placeholder attribute
                $('input[placeholder], textarea[placeholder]').blur(add).focus(remove).each(add);

                // Remove the placeholder text before the form is submitted
                $('form').submit(function () {
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
                    <div id="msg_contact_name"><span class="heading3"></span> <a href="messages.cyt" title="Back to messages" id="" class="button_gray" style=" width:215px; float:right; margin-top:0px; "> <span class="back_to_msg_bot back_to_msg_bot_ie8">BACK TO MESSAGES</span></a></div>
                        <!--All Message Start -->
                        <div id="all_messages">
                            <c:forEach items="${detailMessage}" var="message"  varStatus="msgStatus">
                                <div class="message_tab">
                                    <table width="100%" border="0" cellspacing="0" cellpadding="0">
                                        <tr>
                                           <c:choose>           
                                        <c:when test="${not empty message.photoUrl}">
                                            <td width="6%" valign="top"><img src="${message.photoUrl}" width="32" height="32" class="photoframe"></td>
                                        </c:when>
                                       <c:otherwise>
                                              <td width="6%" valign="top"><img src="http://ca71d62e2bf243f00409-a1804a4951d15e8bdbd5968fbdaecbef.r39.cf3.rackcdn.com/default_avatar.png" width="32" height="32" class="photoframe"></td>
                                       </c:otherwise> 
                                       </c:choose>
                                       <c:set var="messageText" value="${message.messageText}"/>
                                       	<%
                               			 	String mText = (String)pageContext.getAttribute("messageText");
                               				mText = mText.replaceAll("\r\n", "<br/>");
                               				mText = mText.replaceAll("\n", "<br/>");
                               			%>
                               			
                                      	<script type="text/javascript">
                               				$(document).ready(function () {  
                               					var msgText = "<%=mText%>";
                                       			var replaceText = linkify(msgText); 
                                       			$('#linkify_detail_text_${msgStatus.index}').html(replaceText);   
                                       		});
                                       	</script>
                                       <td width="72%" style="padding-left:30px;"><p class="message_heading">${message.userName}</p>
                                           <br>
                    <p class="message_text" style="margin-left: -68px;margin-top: 10px;width: 475px;">
                    	<span id="linkify_detail_text_${msgStatus.index}"></span>
                    </p></td>
                  <td width="22%" valign="top" ><p class="message_date">${message.formattedMessageDate}</p>
                    <p ><a class="delete_blue" id='deleteMessage_${message.id}' onClick='doCancel(true,<c:out value="${message.id}"/>)'>Delete This Message</a></p>
                      <div id='msg_delete_confirm_<c:out value="${message.id}"/>' class="nodisplay" name="msgdtl_delete_popup">This will permanently remove this Message from your Conversation.<br>
                      <a onClick='doCancel(false, <c:out value="${message.id}"/>)'>Cancel</a> | <a id='delete_${message.id}' onClick='confirmDelete("<c:out value="${message.id}"/>",100)'>Delete</a></div>
                  </td>
                </tr>
              </table>
            </div>
        </c:forEach>
        </div>
         
     <!--Send Reply Start -->
            <div id="msg_send_reply">
             <form:form id="messages" commandName="messageModel"  method="post">
              <h4>Send <span>Reply</span></h4>
              <table width="100%" border="0" cellspacing="0" cellpadding="0">
                <tr>
                  <td><form:textarea  onKeyUp="messageValidation(this)"  placeholder="Add your Reply here..."  id="messageContent" path="messageText" /></td>
                </tr>
                <tr>
                  <td>

                  <form:hidden id="contactId" path="contactId"  />
                  <form:hidden id="messageId" path="id" />
                  <a  id='messages.messageDetail.sendReplyMessage' onClick='replyMessage()'  title="Send Reply Now" class="button_green1" style="margin-top:20px; width:162px; float:right;" ><span class="title_green1 send_reply_bot_ie8">SEND REPLY NOW</span></a>
                      <div id="messageErrorDiv">
                                            <span id="sendmsg_error" style="padding-left: 12px; display:none;">
                                               </span></div>
                      <div id="message_reply_add_smile">
                          <p>Add Smiley : <a href="javascript:smileyIconAvailability()"><img src="images/upset-icon.png" width="20" height="20"></a>
                              <a href="javascript:smileyIconAvailability()"><img src="images/angry-icon.png" width="20" height="20"></a>
                              <a href="javascript:smileyIconAvailability()"><img src="images/happy-icon.png" width="20" height="20"></a>
                              <span id='smileyiconsuccess' class="success_message" style="padding-left: 12px; display:none;"></span>
                          </p>
                      </div>
											</td>
                </tr>
              </table>
              </form:form>
            </div>
            <!-- Send Reply Ends-->
         
        <!-- All Message Ends-->
      </div>
    </div>
    </article>
    <!--content ends -->
  </div>
</div>
  <!-- include footer -->
  <jsp:include page="../common/includes/footer.jsp" />
  <script type="text/javascript" src="ui/js/googleanalytics.js"></script>
</body>
</html>
