<%@page import="org.apache.commons.lang.StringUtils"%>
<%@ include file="/WEB-INF/jsp/common/include.jsp" %>
<%@ page import="com.incyyte.app.domain.User" %>

<% User userLocal = (User) session.getAttribute("user"); %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">
    <link rel="shortcut icon" href="favicon.ico" />

    <title>inCyyte for Business</title>
    <meta charset="utf-8">
    <link rel="stylesheet" href="ui/css/reset.css">
    <link rel="stylesheet" href="ui/css/style.css">
    <link rel="stylesheet" href="ui/css/layout.css">
    <link rel="stylesheet" href="ui/css/style_login.css">
    <link rel="stylesheet" href="ui/css/icons_sprites.css">
    <link rel="stylesheet" href="ui/css/buttons.css"  media="screen">
    <link rel="stylesheet" href="fonts/fonts_stylesheet.css">
    <link rel="stylesheet" href="ui/css/modal_window.css" type="text/css" media="screen">
    <link rel="stylesheet" type="text/css" href="ui/css/jquery.lightbox-0.5.css" media="screen" />
    <link rel="stylesheet" href="ui/css/jquery.validate.css">
    <link rel="stylesheet" href="ui/css/validate/screen.css">


        <script src="ui/js/jquery-1.7.2.js"></script>
    <script src="ui/js/jcarousellite.js"></script>
    <script src="ui/js/login.js"></script>
    <script src="ui/js/jquery.limit.js"></script>

    <script type="text/javascript" src="ui/js/easySlider1.7.js"></script>
    <script type="text/javascript" src="ui/js/autoresize.jquery.js"></script>
    <script type="text/javascript" src="ui/js/jquery.js"></script>
    <script type="text/javascript" src="ui/js/jquery.lightbox-0.5.js"></script>
    <script type="text/javascript" src="ui/js/common/jquery.form.js"></script>
    <script type="text/javascript" src="ui/js/jquery-ui.js"></script>
    <script type="text/javascript" src="ui/js/validate/jquery.validate.js" ></script>

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
       <%-- <jsp:include page="../includes/homeHeader.jsp" />--%>

        <% if (userLocal != null) {%>
            <jsp:include page="../../common/includes/header.jsp"/>
            <script type="text/javascript">
               $(document).ready(function(){
                  $(".package_txt").text(" ").css("height","50px").css("width","100%");
               });
            </script>
        <%} else { %>
            <jsp:include page="../includes/homeHeader.jsp"/>
                <%} %>



        <div class="main" style="min-height: 500px; margin-top: 65px;">
            <!--content -->
              <div class="grid_ui3">
                <div class="grid_ui3_1">
                    <h1>inCyyte for <strong>Business</strong></h1>
                    <p>inCyyte has a great range of packages for every size of business. Whether you

                    </p>
                    <p>just want to spread your personal polling wider or are looking for blanket national </p>
                    <p>coverage we've got something for you!</p>
                </div>
                <div class="grid_ui3_2">
                    <img src="ui/images/incyyte-for-business.png"/>
                </div>
                <div class="grid_ui3" style="margin-top: 30px;float: left;">
                    <div class="package">
                        <div class="title">Silver </div>
                        <div class="package_info">

                            <p>
                                Distribute your polls to filtered members within your own postcode for free and to filtered members outside your postcode for just 5p per recipient.
                                <em> Filter poll recipients by age, gender, postcode & embed videos, docs or images to enhance your polls. </em>
                            </p>
                            <p>
                                Use our online wizard tool to upload your contact lists and create new regional polling groups.
                                <em> Upload your email contacts from Gmail, Hotmail, Yahoo and your Outlook </em>
                            </p>
                            <p>
                                Send inCyyte polls out to your own multi-regional groups, friends, client groups or co-workers
                                <em> Create targeted groups to re-poll, Split your client list into sectors and send polls when required. </em>
                            </p>
                            <p>
                                Create stunning poll centres with your own promotional content uploaded and send to wide audience with an embedded poll.
                                <em>You can now isolate poll recipients by their responses & communicate directly with them. </em>
                            </p>
                            <p>
                                Send messages, connect & allow voters comments on your polls 
                                <em>Graphical easy to understand results pie-chart with printable statistical summary </em>
                            </p>
                            <h3>
                               
                               Only �60 per year
                            </h3>
                            <div class="button_container">
                                <div class="button_bg">
                                <%if(userLocal != null && !StringUtils.equalsIgnoreCase(userLocal.getUserType(),"BUSINESS_SILVER")) { %>
                                    <a href="${pageContext.request.contextPath}/businessUserPaymentProcess.cyt" id="Silver_package" class="button green" >Select Silver Package</a>
                                 <%}else if (userLocal == null ){%>
                                    <a href="${pageContext.request.contextPath}/login.cyt" id="Silver_package" class="button green">Select Silver Package</a>
                                 <%} else {%>
                                   <a href="javascript:void(0);" id="Silver_package" class="disabledbutton disabledgray">Select Silver Package</a>
                                   <%}%>
                                </div>
                            </div>
                        </div>

                    </div>
                    <div class="package gold">
                        <div class="title">Gold </div>
                        <div class="package_info">
                            <div class="badge"></div>
                            <p>
                                inCyyte GOLD polling accounts receive periodic discounts and special inCyyte offers
                                <em>Additional features <br>coming soon..</em>
                            </p>
                            <p>
                                Includes all the great benefits of the Silver membership
                                <em>Additional features <br>coming soon..</em>
                            </p>
                           <p>
                                Filter by age, gender, income, occupation and more!
                               <em>Additional features <br>coming soon..</em>
                            </p>
                            <p>
                                Genarate statistical and demographic reports and breakdowns
                                <em>Additional features <br>coming soon..</em>
                            </p>

                            <h3>
                            <br>
                            <br>
                            

                                Available soon..
                            </h3>
                            <div class="button_container">
                                <div class="button_bg">
                                    <a href="javascript:void(0)" class="disabledbutton disabledgray">Select Gold Package</a>
                                </div>
                            </div>
                        </div>

                    </div>
                    <div class="package">
                        <div class="title">Platinium </div>
                        <div class="package_info">

                            <p>
                                Create Branded Ad campaigns
                                <em>Additional features <br>coming soon..</em>
                            </p>
                            <p>
                                Distribute your ad to a selected location/region
                                <em>Additional features <br>coming soon..</em>
                            </p>
                            <p>
                                Send sales material directly to interested recipients
                                <em>Additional features <br>coming soon..</em>
                            </p>
                            <p>
                                Filter ad by age group, gender, occupation, income and more!
                                <em>Additional features <br>coming soon..</em>
                            </p>
                            <p>
                                Create Price-Tag-Polls for quick customer leads
                                <em>Additional features <br>coming soon..</em>
                            </p>
                            <p>
                                Re-poll previously polled groups
                                <em>Additional features <br>coming soon..</em>
                            </p>
                            <p>
                                Generate full statistical reports
                                <em>Additional features <br>coming soon..</em>
                            </p>
                            <h3>
                                <br>
                                <br>
                               Available soon..
                            </h3>
                            <div class="button_container">
                                <div class="button_bg">
                                    <a href="javascript:void(0)" class="disabledbutton disabledgray">Select Platinium  Package</a>
                                </div>
                            </div>
                        </div>

                    </div>
                </div>
                  <p class="package_txt">* You must be <a href="login.cyt">logged</a> in to select a package - you can create an account in seconds <a href="createAcct.cyt">here.</a></p>
              </div>
            <!--content end -->
        </div>
    </div>
    <% if (userLocal != null) {%>
    <jsp:include page="../../common/includes/footer.jsp" />
    <%} else { %>
    <jsp:include page="../includes/homeFooter.jsp" />
    <%} %>
    <!-- include footer -->

</div>
<!--footer end-->
<script>
    $("p").mouseover(function(){
        $(this).find('em').css('visibility', 'visible');
    });
    $("p").mouseout(function(){
        $(this).find('em').css('visibility', 'hidden');
    });
</script>
<script type="text/javascript" src="ui/js/googleanalytics.js"></script>
</body>
</html>
