<!DOCTYPE html>
<html lang="en">
<%@ include file="/WEB-INF/jsp/common/include.jsp" %>
<head>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">
    <link rel="shortcut icon" href="favicon.ico"/>

    <title>inCyyte - Compose New Message</title>
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

	<link rel="stylesheet" href="ui/css/style_help.css">
	<link rel="stylesheet" href="ui/css/form_elements.css" type="text/css"/>
	<link rel="stylesheet" href="ui/css/jquery.selectbox.css" type="text/css">
	
	<link rel="stylesheet" type="text/css" href="css/accountry.0.2/jquery.autocomplete.css" />
	<link rel="stylesheet" type="text/css" href="css/accountry.0.2/jquery.autocomplete.country.css" />
	<link rel="stylesheet" href="ui/css/themes/base/jquery.ui.all.css">


    <script src="ui/js/jquery-1.4.2.min.js"></script>
    <script src="ui/js/jquery-1.7.1.js"></script>    
    <script src="ui/js/login.js"></script>
    <script src="ui/js/jquery.color.js"></script>
    <script src="ui/js/search_script.js"></script>
    <script type="text/javascript" src="js/accountry.0.2/jquery.autocomplete.js"></script>    
   
    <script src="ui/js/jquery_profile_rating.js" type="text/javascript"></script>
    <script src="ui/js/star_rating.js" type="text/javascript"></script>
    <script type="text/javascript" src="js/jquery-ui-1.8.13.custom.min.js"></script>
    <script type="text/javascript" src="js/jquery.form.js"></script>
    <script src="ui/js/jquery.selectbox-0.2.js" type="text/javascript"></script>
    
    
    
  


<!-- Left Navigation script starts here -->
    <script src="ui/js/left_menu.js"></script>
    
    

    <!-- Left Navigation script ends here -->  
    <!--- placeholder Starts----->
    <link rel="stylesheet" href="/resources/demos/style.css"/>
    <% response.setHeader("Cache-Control", "no-cache"); //HTTP 1.1
        response.setHeader("Pragma", "no-cache"); //HTTP 1.0  %>
    <script>
        // placeholder polyfill
        $(document).ready(function () {
        	 $(function () {
                 $("select").selectbox();
             }); 
        	 
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
                        <!--Send New Message Start -->
                        <div id="send_new_msg">
                            <form:form id="composeNewMsg"  name="composeNewMsg" commandName="messageModel" >
                                <h4>Send New <span>Message</span></h4>
                                <table width="100%" border="0" cellspacing="0" cellpadding="0">
                                    <tr>
                                        <td height="70" valign="top">
                                            <label for="contactList">To:</label>
                                            <form:input id="contactList"
                                                   onKeyUp="displayContacts()" placeholder="Enter Contact Name"
                                                   path="contactName" /> ${username} <%-- </input> --%>

                                            <div id="messageErrorDiv"><span id="error" style="padding-left: 12px; display:none;"> </span>
                                            </div>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td width="47%"><label>Message:</label>
                                            <form:textarea id='messagetext' onKeyUp="messageValidation(this)"
                                                      placeholder="Add your message here..."
                                                      path="messageText" />
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>
                                            <a onClick="composeMsg()" title="Send Message" id="messages.ComposeNew.sendMessage" class="send_message_bot"
                                               style="margin-top:20px; width:162px; float:right;"> <span
                                                    class="title_green1 title_send_message_ie8">SEND MESSAGE</span></a>

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
                        <!-- Send New Message Ends-->
                    </div>
                </div>
            </article>
            <!--content ends -->
        </div>
    </div>
    <!-- include footer -->
    <jsp:include page="../common/includes/footer.jsp"/>
    <script type="text/javascript" src="ui/js/googleanalytics.js"></script>
</body>
</html>