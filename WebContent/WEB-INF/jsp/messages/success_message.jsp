<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">
    <link rel="shortcut icon" href="favicon.ico" />
    
    <title>inCyyte - Message Sent!</title>
    <meta charset="utf-8">
    <link rel="stylesheet" href="ui/css/reset.css">
    <link rel="stylesheet" href="ui/css/layout.css">
    <link rel="stylesheet" href="ui/css/buttons.css" media="screen">
    <link rel="stylesheet" href="ui/css/style_login.css">
    <link rel="stylesheet" href="ui/css/accordionmenu.css" type="text/css">
    <link rel="stylesheet" href="ui/css/icons_sprites.css">
    <link rel="stylesheet" href="fonts/fonts_stylesheet.css">
	<link rel="stylesheet" href="ui/css/ratingbar.css" type="text/css"/>

    <!-- Left Navigation script starts here -->
    <script src="ui/js/left_menu.js"></script>
    <script type="text/javascript" src="ui/js/jquery-ui-1.8.13.custom.min.js"></script>
    <!-- Left Navigation script ends here -->
    <!--- placeholder Starts----->

    <script src="ui/js/jquery_profile_rating.js" type="text/javascript"></script>    
	<script src="ui/js/star_rating.js" type="text/javascript"></script>
	<script src="ui/js/jquery-1.8.3.min.js" type="text/javascript"></script>
	<link rel="stylesheet" href="/resources/demos/style.css"/>
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
   
</head>
<%--<script type="text/javascript">
    $(window).load(function() {
        $(".loader").fadeOut("slow");
    })
</script>--%>
<body onload="getUnreadCount();">
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
                       <!--success Message Start -->
            <div id="sendmsg_success" style="height:85px;" >
              <h4><span id="messages.ComposeNew.Hooray">Hooray!</span> <span style="font:28px/28px 'bariol_regularregular', 'ie8_bariol_regular'; color:#fff;" id="messages.ComposeNew.SuccessMessage"> &nbsp; Your Message has been sent</span></h4>
              <a href="./composeNew.cyt" title="Compose New Message" id="messages.ComposeNew.ComposeMessage" class="button_green1" style="margin-top:3px; width:200px; float:right;"> <span class="title_green1 sendmsg_success_ie8">Compose New Message</span></a> </div>
           <!-- success message End -->
           </td>
        </tr>
             </div>
             </table>
    <!-- success Message Ends-->
                    </div>
                </div>
            </article>
            <!--content ends -->
    </div>
    <!-- include footer -->
    <jsp:include page="../common/includes/footer.jsp"/>
    <script type="text/javascript" src="ui/js/googleanalytics.js"></script>
</body>
</html>