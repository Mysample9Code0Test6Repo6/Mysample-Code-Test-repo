<%@ page import="org.apache.commons.lang.StringUtils" %>
<%@page import="java.util.*"%>

<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!--[if IE 9]>			<html class="ie ie9"> <![endif]-->
<!--[if gt IE 9]><!-->	<html> <!--<![endif]-->
<head>
    <!-- Basic -->
    <meta charset="utf-8">
    <title>inCyyte - Social Polling</title>
    <meta name="keywords" content="InCyyte, Social Polling" />
    <meta name="description" content="InCyyte, Social Polling">
    <meta name="author" content="InCyyte">

    <!-- Favicons -->
    <link rel="shortcut icon" type='image/x-icon' href="favicon.ico">
    <link rel="apple-touch-icon-precomposed" sizes="144x144" href="apple-touch-icon-144x144-precomposed.png">
    <link rel="apple-touch-icon-precomposed" sizes="114x114" href="apple-touch-icon-114x114-precomposed.png">
    <link rel="apple-touch-icon-precomposed" sizes="72x72" href="apple-touch-icon-72x72-precomposed.png">
    <link rel="apple-touch-icon-precomposed" href="apple-touch-icon-precomposed.png">

    <!-- Mobile Metas -->
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="css/newwelcome/bootstrap.css" />

    <!-- AWESOME and ICOMOON fonts -->
    <link rel="stylesheet" href="css/newwelcome/font-awesome.css">
    <link rel="stylesheet" href="css/newwelcome/style.css">

    <!-- Open Sans fonts -->
    <link rel="stylesheet"  href="http://fonts.googleapis.com/css?family=Open+Sans:300italic,400italic,600italic,700italic,800italic,400,300,600,700,800">
  <link rel="stylesheet" href="css/newwelcome/theme-header.css">
    <!-- Theme CSS -->
    <link rel="stylesheet" href="css/newwelcome/settings.css">
    <link rel="stylesheet" href="css/newwelcome/theme.css">
   <!--  <link rel="stylesheet" href="css/newwelcome/theme-elements.css"> -->
    <link rel="stylesheet" href="css/newwelcome/teal.css">
    <link rel="stylesheet" href="css/newwelcome/animate.css">

    <link rel="stylesheet" href="css/newwelcome/styles.css">
    <link rel="stylesheet" href="fonts/fonts_stylesheet.css">

    <!-- Modernizr -->
    <script type="text/javascript" src="js/NewWelcomejs/modernizr.js"></script>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"><style type="text/css">
    <!--
    body {
        background-image: url(../../ui/images/bg_pattern.png);
    }
    -->
</style>

<script type="text/javascript">

function filteredPolls(search) {
	window.location = "filteredPolls.cyt?search="+search;
	 event.preventDefault();
};

function getSinglePollPage(incyyteCode) {
	window.location = "viewPoll.cyt?code="+incyyteCode;
	 event.preventDefault();
};

</script>
<script type="text/javascript" src="js/NewWelcomejs/jquery-1.10.2.min.js"></script>

</head>
<body class="index">
 <jsp:include page="/WEB-INF/jsp/newHeader.jsp" />

<div class="page-slider-wrap" >
    <div id="page-slider" >
        <ul>
            <li data-transition="zoomout" data-masterspeed="1300">

                <!-- MAIN IMAGE DON'T MISS THE TRAIN -->
                <img src="ui/images/slide3.gif"  alt="slide1"  data-bgfit="cover" data-bgposition="left top" data-bgrepeat="no-repeat">
                <!-- LAYER NR. 1 -->
                <div class="tp-caption slider-title text-left sft" data-x="center" data-y="center" data-voffset="-130" data-speed="1000" data-start="500" data-easing="Power1.easeOut" data-endspeed="300" data-endeasing="Power1.easeIn" style="font:90px/1em 'bariol_boldbold', 'ie8_bariol_bold'; color:#FFFFFF">Don't miss this train..</div>
                <!-- LAYER NR. 2 -->
                <div class="tp-caption slider-sub-title text-left sfl" data-x="center" data-y="center" data-speed="800" data-start="600" data-easing="Power1.easeOut" data-endspeed="300" data-endeasing="Power1.easeIn" style=" font: 40px 'bariol_regularregular','ie8_bariol_regular';color:#ffffff">VOTE, SHARE, GET PAID!<br><br></div>
                <!-- LAYER NR. 3 -->
                <a href="incyyteVideoPage.cyt" class="tp-caption sfb btn btn-big" data-x="center" data-y="center" data-voffset="140" data-speed="1000" data-start="700" data-easing="Power4.easeOut" data-endspeed="300" data-endeasing="Power1.easeIn" style=" font: 'bariol_regularregular','ie8_bariol_regular';color:#182035">Watch our Video</a> </li>
            <li data-transition="zoomout" data-masterspeed="1300">

                <!-- MAIN IMAGE BROADCAST THE VOTE -->
                <img src="ui/images/slide2.jpg"  alt="slide2"  data-bgfit="cover" data-bgposition="left top" data-bgrepeat="no-repeat">
                <!-- LAYER NR. 1 -->
                <div class="tp-caption slider-title text-left sft" data-x="center" data-y="center" data-voffset="-130" data-speed="1000" data-start="500" data-easing="Power1.easeOut" data-endspeed="300" data-endeasing="Power1.easeIn" style=" font:90px/1em 'bariol_boldbold', 'ie8_bariol_bold';color:#FFFFFF">Broadcast the vote!</div>
                <!-- LAYER NR. 2 -->
                <!-- <div class="tp-caption slider-sub-title text-left sfl" data-x="center" data-y="center" data-speed="800" data-start="600" data-easing="Power1.easeOut" data-endspeed="300" data-endeasing="Power1.easeIn" style="color:#ffffff">Poll share with 1000's of voters</div>-->
                <!-- LAYER NR. 3 -->
                <a href="incyyteVideoPage.cyt" class="tp-caption sfb btn btn-big" data-x="center" data-y="center" data-voffset="140" data-speed="1000" data-start="700" data-easing="Power4.easeOut" data-endspeed="300" data-endeasing="Power1.easeIn" style="font: 'bariol_regularregular','ie8_bariol_regular';color:#182035">Watch our Video</a> </li>
            <li data-transition="zoomout" data-masterspeed="1300">

                <!-- MAIN IMAGE GET NEW CUSTOMERS -->
                <img src="ui/images/slide.jpg"  alt="slide3"  data-bgfit="cover" data-bgposition="left top" data-bgrepeat="no-repeat">
                <!-- LAYER NR. 1 -->
                <div class="tp-caption slider-title text-left sft" data-x="center" data-y="center" data-voffset="-130" data-speed="1000" data-start="500" data-easing="Power1.easeOut" data-endspeed="300" data-endeasing="Power1.easeIn" style=" font:90px/1em 'bariol_boldbold', 'ie8_bariol_bold';color:#FFFFFF">Get new customers..</div>
                <!-- LAYER NR. 2 -->
                <!--<div class="tp-caption slider-sub-title sfl" data-x="15" data-y="center" data-speed="1000" data-start="800" data-easing="Back.easeInOut" data-endspeed="300" style="color:#FFFFFF">POTENTIAL CUSTOMERS ARE OUT THERE, <br />
              OUR FREE POLLS BRING THEM <BR>
              DIRECTLY TO YOU!</div>-->
                <!-- LAYER NR. 3 -->
                <a href="incyyteVideoPage.cyt" class="tp-caption sfb btn btn-big" data-x="center" data-y="center" data-voffset="140" data-speed="1000" data-start="700" data-easing="Power4.easeOut" data-endspeed="300" data-endeasing="Power1.easeIn" style="font: 'bariol_regularregular','ie8_bariol_regular';color:#182035">Watch our Video</a> </li>
            </li>
        </ul>
        <div class="tp-bannertimer tp-bottom"></div>
    </div>
</div>
<!-- .page-slider-wrap -->







<div id="page-content" role="main">
<div class="container">
<div id="content">

<div class="container-out container-light">

    <div class="title title-section">
        <h2 style="font: 35px/1em 'bariol_boldbold', 'ie8_bariol_bold';">We have a whole new opinion about polling</h2>
        <p style="font: 20px 'bariol_regularregular','ie8_bariol_regular';">inCyyte helps companies by making everday people their instant poll researchers, and pays cash for their poll results.</p> </span>
						<span class="sticker">
							<!--<i class="icon fa fa-cogs"></i>-->
                        <a href="welcome.cyt" class="logo">
                            <img src="ui/images/smile_faces.png" alt="inCyyte" width="393" height="203"></a><!-- .logo -->
                            </span>
    </div><!-- .title.title-section -->

</div><!-- .container-out -->
<div class="container-out container-no-bottom">
    <div class="row">
        <div class="col-sm-4 animate fadeInDown">
            <div class="iconbox iconbox-style3">
                <div class="iconbox-heading">
                    <div class="icon">
                        <img src="ui/images/wizard.png" alt="inCyyte" width="393" height="203"><!--<i class="fa fa-magic">--></i>
                    </div>
                    <div class="title">
                        <h4 style="font: 25px 'bariol_boldbold', 'ie8_bariol_bold';">Create poll magic</h4>
                    </div>
                </div><!-- .iconbox-heading -->
                <div class="iconbox-content">
                    <div class="text">
                        <p style="font: 15px 'bariol_regularregular','ie8_bariol_regular';">Get clever by turning your questions into classy polls which help to answer the things businesses want to know. Send private polls to your own contacts or public polls to any UK postcode. Like magic, we'll turn your poll results into cash! </p>
                        <p><a href="#" class="btn " style="font: 15px 'bariol_boldbold', 'ie8_bariol_bold';">Read more</a></p>
                    </div>
                </div><!-- .iconbox-content -->
            </div><!-- .iconbox -->

        </div><!-- .col-sm-4 -->
        <div class="col-sm-4 animate fadeInDown">

            <div class="iconbox iconbox-style3">
                <div class="iconbox-heading">
                    <div class="icon">
                        <img src="ui/images/1000.png" alt="inCyyte" width="393" height="203"><!--<i class="icomoon-mobile2">--></i>
                    </div>
                    <div class="title">
                        <h4 style="font: 25px 'bariol_boldbold', 'ie8_bariol_bold';">Build 1000's of Voters </h4>
                    </div>
                </div><!-- .iconbox-heading -->
                <div class="iconbox-content">
                    <div class="text">
                        <p style="font: 15px 'bariol_regularregular','ie8_bariol_regular';">Polls you post will be seen by members & visitors to our site, they can also be shared on by voters increasing your voter count. The more voters you have the more money you can earn through your poll results.  </p>
                        <p><a href="#" class="btn "  style="font: 15px 'bariol_boldbold', 'ie8_bariol_bold';">Read more</a></p>
                    </div>
                </div><!-- .iconbox-content -->
            </div><!-- .iconbox -->


        </div><!-- .col-sm-4 -->
        <div class="col-sm-4 animate fadeInDown">

            <div class="iconbox iconbox-style3">
                <div class="iconbox-heading">
                    <div class="icon">
                        <img src="ui/images/cash.png" alt="inCyyte" width="393" height="203"><!--<i class="fa fa-thumbs-o-up">--></i>
                    </div>
                    <div class="title">
                        <h4 style="font: 25px 'bariol_boldbold', 'ie8_bariol_bold';">Cash Payments for Poll Results</h4>
                    </div>
                </div><!-- .iconbox-heading -->
                <div class="iconbox-content">
                    <div class="text">
                        <p style="font: 15px 'bariol_regularregular','ie8_bariol_regular';">If your polls helps match voters with services or products they want, our registered companies will pay you to reach those new prospective customers. All voters remain anonymous and we do not share any email addresses.   </p>
                        <p><a href="#" class="btn " style="font: 15px 'bariol_boldbold', 'ie8_bariol_bold';">Read more</a></p>
                    </div>
                </div><!-- .iconbox-content -->
            </div><!-- .iconbox -->

        </div><!-- .col-sm-4 -->
    </div><!-- .row -->

    <hr  class="devider-heavy devider-margin-big" />
    <div class="row row-inline inline-bottom">
        <div class="col-sm-5">

            <div class="title title-section">
                <h2 style=" font: 24px 'bariol_boldbold', 'ie8_bariol_bold';">Access to 1000's of new potential customers..</h2>
                <p style="font: 18px 'bariol_regularregular','ie8_bariol_regular';">Cut through clutter, use the free power of inCyyte <br>to get directly to consumers looking for your business.</p>					                                 </div><!-- .title -->

            <div class="iconbox iconbox-style3 iconbox-list animate bounceIn">
                <div class="iconbox-heading">
                    <div class="icon">
                        <i class="icomoon-checkmark2"></i>
                    </div>
                </div><!-- .iconbox-heading -->
                <div class="iconbox-content">
                    <div class="title">
                        <h5 style="font: 22px 'bariol_boldbold', 'ie8_bariol_bold';">When you post a free business poll..</h5>
                    </div>
                    <div class="text">
                        <p style="font: 16px 'bariol_regularregular','ie8_bariol_regular';">You can target a specific demograph, selecting customers by postcode, region, age, gender, income and much more. You can also search for polls that are likely to have your kind of customers.</p>

                    </div>
                </div><!-- .iconbox-content -->
            </div><!-- .iconbox -->
            <hr class="devider-half animate bounceIn" />
            <div class="iconbox iconbox-style3 iconbox-list animate bounceIn">
                <div class="iconbox-heading">
                    <div class="icon">
                        <i class="icomoon-clock"></i>
                    </div>
                </div><!-- .iconbox-heading -->
                <div class="iconbox-content">
                    <div class="title">
                        <h5 style="font: 22px 'bariol_boldbold', 'ie8_bariol_bold';">You can watch the results in real-time!..</h5>
                    </div>
                    <div class="text">
                        <p style="font: 16px 'bariol_regularregular','ie8_bariol_regular';">While 1000's of customers vote on your polls, inCyyte incentives mean they can share your poll introducing many more voters.</p>

                    </div>
                </div><!-- .iconbox-content -->
            </div><!-- .iconbox -->
            <hr class="devider-half animate bounceIn" />
            <div class="iconbox iconbox-style3 iconbox-list animate bounceIn">
                <div class="iconbox-heading">
                    <div class="icon">
                        <i class="icomoon-heart2"></i>
                    </div>
                </div><!-- .iconbox-heading -->
                <div class="iconbox-content">
                    <div class="title">
                        <h5 style="font: 22px 'bariol_boldbold', 'ie8_bariol_bold';">Make direct contact with new customers!</h5>
                    </div>
                    <div class="text">
                        <p style="font: 16px 'bariol_regularregular','ie8_bariol_regular';">inCyyte puts you in contact with individuals that actually want your service, based on how they have responded to your poll. You can introduce your products and even send promotional material directly to only the interested voters.</p>
                        <br>

                    </div>
                </div><!-- .iconbox-content -->
            </div><!-- .iconbox -->

        </div><!-- .col-sm-5 -->
        <div class="col-sm-7">

            <div class="thumbnail thumbnail-no-margin animate fadeInRight">
                <img src="ui/images/two-iphones-preview.png" alt="Typical business poll" title="Post free business polls" />
            </div>

        </div><!-- .col-sm-7 -->
    </div><!-- .row -->
</div><!-- .container-out -->




<div class="container-out container-dark">

    <div class="title title-section">
        <h2 style="font: 35px/1em 'bariol_thinregular', 'ie8_bariol_thin';">inCyyte <strong style="color: #ffffff;">Social Polling</strong></h2>
        <p style="font: 20px 'bariol_regularregular','ie8_bariol_regular';">The network that means business with your opinion</p>
						<span class="sticker">
						<a><img src="ui/images/logo_short.png" alt="inCyyte" width="88" height="88"></a>
						</span>
    </div><!-- .title.title-section -->

</div><!-- .container-out -->

<div class="container-out container-full-width">

    <div class="carousel-wrap carousel-no-margins">
        <div class="projects carousel" data-visible="4" style="text-align: left; float: none; position: relative; top: 0px; right: auto; bottom: auto; left: 0px; margin: 0px; width: 5408px; height: 237px;">
            <article class="project project-animated illustration design">
                <div class="project-heading">
                    <div class="thumbnail">
                        <img src="ui/images/createpoll_tb.png" alt="Image" title="Image" />
                    </div>
                    <ul class="project-action">
                        <li style="font: 20px 'bariol_boldbold', 'ie8_bariol_bold';"><a class="lightbox btn btn-icon-view" href="ui/images/createpoll.png" data-fancybox-title="<h4>USE OUR EASY POLL WIZARD</h4><p>You can create polls to present your questions with stunning effects. Attach photos, video or documents and much more for free. You can poll your own contacts or individuals in any UK postcode.</p><p class='text-right'><a href='${pageContext.request.contextPath}/create_question.cyt' class='btn'>Create a quick poll</a></p>" data-fancybox-group="portfolio"></a></li>
                        <!--<li><a class="link btn btn-icon-link" href="portfolio-single.html"></a></li>-->
                    </ul>
                </div><!-- .project-heading -->
                <div class="project-content">

                    <p class="meta">
                        <!--<span class="meta-like">48</span>									</p><!-- .meta -->
                    <div class="title">
                        <h2 class="h5"  style="font: 20px/1em 'bariol_boldbold', 'ie8_bariol_bold';">Create Free Polls</h2>
                        <p class="desc" style=" font: 15px 'bariol_regularregular','ie8_bariol_regular';">Reach customers in all national regions, attach video, photos or docs. </p>
                    </div><!-- .title -->
                </div>
                <!-- .project-content -->
            </article><!-- .project -->
            <article class="project project-animated illustration photography">
                <div class="project-heading">
                    <div class="thumbnail">
                        <img src="ui/images/messages_tb.png" alt="Image" title="Image" />
                    </div>
                    <ul class="project-action">
                        <li><a class="lightbox btn btn-icon-view" href="ui/images/messages.png" data-fancybox-title="<h4>ATTRACT NEW CUSTOMERS TO YOUR BUSINESS</h4><p>Exclusive poll webpages can help you reveal consumer sentiment and identify customers that are ready to engage with your business. Our free poll webpages also promote your business details and allow communication between you and propective customers.</p><p class='text-right'><a href='${pageContext.request.contextPath}/create_question.cyt' class='btn'>Take a quick look</a></p>" data-fancybox-group="portfolio"></a></li>
                        <!--<li><a class="link btn btn-icon-link" href="portfolio-single.html"></a></li>-->
                    </ul>
                </div><!-- .project-heading -->
                <div class="project-content">

                    <p class="meta">
                        <!--<span class="meta-like">26</span>									</p><!-- .meta -->
                    <div class="title">
                        <h2 class="h5" style="font: 20px/1em 'bariol_boldbold', 'ie8_bariol_bold';">EXCLUSIVE POLL PAGES</h2>
                        <p class="desc"  style=" font: 15px 'bariol_regularregular','ie8_bariol_regular';">Your own independant free poll webpages that can be distributed as far as you like.</p>
                    </div><!-- .title -->
                </div>
                <!-- .project-content -->
            </article><!-- .project -->
            <article class="project project-animated illustration animation">
                <div class="project-heading">
                    <div class="thumbnail">
                        <img src="ui/images/contacts_tb.png" alt="Image" title="Image" />

                    </div>

                    <ul class="project-action">
                        <li><a class="lightbox btn btn-icon-view" href="ui/images/contacts.png" data-fancybox-title="<h4>POLL YOUR ENTIRE LIST OF CUSTOMERS/FRIENDS FOR FREE</h4><p>InCyyte lets you create short quick polls to send to your customers or associates. Polls can be shared on by recipients or even set as private. You can import new or existing contacts and invite others to share in your polls thus increasing amount of voters. </p><p class='text-right'><a href='${pageContext.request.contextPath}/create_question.cyt' class='btn'>Try out inCyyte now</a></p>"  data-fancybox-group="portfolio"></a></li>
                        <!--<li><a class="link btn btn-icon-link" href="portfolio-single.html"></a></li>-->
                    </ul>
                </div><!-- .project-heading -->
                <div class="project-content">

                    <p class="meta">
                        <!--<span class="meta-like">5</span>									</p><!-- .meta -->
                    <div class="title">
                        <h2 class="h5" style="font: 20px/1em 'bariol_boldbold', 'ie8_bariol_bold';">Upload Contact Databases</h2>
                        <p class="desc" style=" font: 15px 'bariol_regularregular','ie8_bariol_regular';">Invite, import, poll & maintain your entire customer lists to get more business..</p>
                    </div><!-- .title -->
                </div>
                <!-- .project-content -->
            </article><!-- .project -->
            <article class="project project-animated animation web">
                <div class="project-heading">
                    <div class="thumbnail">
                        <img src="ui/images/dash_tb.png" alt="Image" title="Image" />
                    </div>
                    <ul class="project-action">
                        <li><a class="lightbox btn btn-icon-view" href="ui/images/dash.png" data-fancybox-title="<h4>Real-Time Results</h4><p>Watch in real-time as the results from your poll roll in. As individuals share your poll, the number of voters will continue to increase giving you vital information and useful statistics. All for free! </p><p class='text-right'><a href='${pageContext.request.contextPath}/create_question.cyt' class='btn'>Try out inCyyte now</a></p>" data-fancybox-group="portfolio"></a></li>
                        <!--<li><a class="link btn btn-icon-link" href="portfolio-single.html"></a></li>-->
                    </ul>
                </div><!-- .project-heading -->
                <div class="project-content">

                    <p class="meta">
                        <!--<span class="meta-like">18</span>									</p><!-- .meta -->
                    <div class="title">
                        <h2 class="h5" style="font: 20px/1em 'bariol_boldbold', 'ie8_bariol_bold';">Real Time Results in Seconds</h2>
                        <p class="desc" style=" font: 15px 'bariol_regularregular','ie8_bariol_regular';">Watch poll results arrive in seconds and carefully select customers from responses..</p>
                    </div><!-- .title -->
                </div>
                <!-- .project-content -->
            </article><!-- .project -->
            <article class="project project-animated illustration photography">
                <div class="project-heading">
                    <div class="thumbnail">
                        <img src="ui/images/reach_tb.png" alt="Image" title="Image" />
                    </div>
                    <ul class="project-action">
                        <li><a class="lightbox btn btn-icon-view" href="ui/images/reach.png" data-fancybox-title="<h4>GET IN TOUCH WITH INTERESTED VOTERS</h4><p>After individuals have voted on your poll and expressed an interest in your product or service, inCyyte will let you separate voters by how they have responded to your poll and reach them directly through our platform. We uphold voter anonymity by allowing promotional material to be sent directly to voters inCyyte inbox.<br> Videos, Vouchers, Discounts and Offers can be sent to voters that want to engage with your business. </a></p><p class='text-right'><a href='${pageContext.request.contextPath}/create_question.cyt' class='btn'>Try out inCyyte now</a></p>" data-fancybox-group="portfolio"></a></li>
                        <!--<li><a class="link btn btn-icon-link" href="portfolio-single.html"></a></li>-->
                    </ul>
                </div><!-- .project-heading -->
                <div class="project-content">

                    <p class="meta">
                        <!--<span class="meta-like">37</span>									</p><!-- .meta -->
                    <div class="title">
                        <h2 class="h5">Find Voting Customers</h2>
                        <p class="desc">As vote results arrive, select voters to send promotional material directly to..</p>
                    </div><!-- .title -->
                </div>
                <!-- .project-content -->
            </article><!-- .project -->
            <article class="project project-animated photography"/>
        </div>
        <div class="title">
        </div>
    </div>
</div><!-- .carousel -->
</div><!-- .carousel-wrap -->
</div><!-- .container-out -->

 <div class="container-out container-light">
					<div class="title title-section">
						<h2 style="font: 40px 'bariol_boldbold', 'ie8_bariol_bold';">Vote on today's polls..</h2>
						<p style="font: 22px 'bariol_regularregular','ie8_bariol_regular';">Have your say and start building your earning potential</p>
						<span class="sticker">
							<a>
						<img src="ui/images/logo_short.png" alt="inCyyte" width="88" height="88">					</a>
						</span>
					</div><!-- .title.title-section -->
				</div><!-- .container-out -->
					<%int i = 0;%>
					
                      <c:forEach  var="PublicIncyytes" items="${PublicIncyytes}" begin="0" end="11" >
                  
                      <%if (i%4 == 0) {
                      %>
                           <!-- <hr  class="devider-heavy devider-margin-big" />      -->       
				<div class="container-out">
					<div class="carousel-wrap animate fadeInLeft">
						<ul class="carousel-nav">
						</ul><!-- .carousel-nav -->
					  <div class="carousel" data-visible="4">
					  <%}%>
            				<article class="post post-latest post-type-video">
								<div class="post-heading">
									<div class="thumbnail">
										<div class="slidher">
								    <c:choose>
					      			<c:when test="${not empty PublicIncyytes.incyyte.uploadLocation && not empty PublicIncyytes.incyyte.upload_name}">
					      			<c:set var="url"  value="${PublicIncyytes.incyyte.uploadLocation}"/>
					      					<c:set var="uploadName"  value="${PublicIncyytes.incyyte.upload_name}"/>
					      					<%		String name = (String)pageContext.getAttribute("uploadName");
							      					String extension = "";
							      					int j = name.lastIndexOf('.');
							      					if (j > 0) {
							      					    extension = StringUtils.lowerCase(name.substring(j+1));
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
					      					<%if(extVideos.contains(extension)) {%>
							      					<img  src="/ui/images/video_thumb.png"  height="168" width="478" alt="Image" title="Image" />
							      					
						  					<%}else if(extImages.contains(extension)){ %>		      				
							      					<img  src="${PublicIncyytes.incyyte.uploadLocation}"  height="168" width="478" alt="Image" title="Image" />
							      					
							      			<%}else if(extDocs.contains(extension) || extGoogleDocs.contains(extension)){ %>		
								      					<img  src="/ui/images/view_docs_thumb.png"  height="168" width="478" alt="Image" title="Image" />
						      				<%}%>
					      				</c:when>
					      				<c:when test="${ empty PublicIncyytes.incyyte.uploadLocation}">
					      					<img  src="/ui/images/video_thumb.png"  height="168" width="478" alt="Image" title="Image" />
					      				</c:when>
									 </c:choose>
										</div>
									</div>

								</div>
								<div class="post-content">
									<div class="title">
									<h2 class="h6"><a style="font: 20px 'bariol_boldbold', 'ie8_bariol_bold';">${PublicIncyytes.incyyte.incyyte}</a><br><br><a href="javascript:getSinglePollPage('${PublicIncyytes.incyyte.incyyteCode}');"><img  src="ui/images/vote_button.png"></a></h2>
									  </div>
   								   	  </div>
						      	      </article>
					<%
					i++;
					if (i%4 == 0) {
                      %>
                       </div><!-- .carousel -->
					</div><!-- .carousel-wrap -->
				</div><!-- .container-out -->         
                      <%}
                      %>
                    </c:forEach>
                 <br><br>   

</div><!-- .carousel -->
</div><!-- .carousel-wrap -->
<hr  class="devider-heavy devider-margin-big" />
<div class="title title-section">
    <h2 style="font: 35px/1em 'bariol_thinregular', 'ie8_bariol_thin';"><strong>What are people saying about</strong> inCyyte?</h2>
    <p style="font: 20px 'bariol_regularregular','ie9_bariol_regular';">Testimonials from our business subcribers & members..</p>


</div><!-- .container-out -->
<div class="container-out container-image" style="background-image:url(/ui/images/testimonial2.jpg)" >

    <div class="animate fadeIn">
        <div class="testimonial-wrap">
            <div class="testimonial-outer">
                <ul class="testimonial" data-auto="true">
                    <li>
                        <div class="testimonial-content">

                            <div class="text">
                                <p>I uploaded all my customers emails and sent a quick poll advertising our new services. I was shocked when the results came back.. We had made £10k in new orders in a matter of minutes! <br>inCyyte is simple and clever, a truly amazing tool, i'd recommend it to everyone.</p>
                            </div>

                        </div><!-- .testimonial-content  -->
                        <div class="testimonial-heading">

                            <div class="title">
                                <h4>Chris Thomas</h4>
                                <p class="position"><a href="http://www.ctcontracts.co.uk" target="_blank">CT Contracts</a></p>
                            </div>

                        </div><!-- .testimonial-heading  -->
                    </li>
                    <li>
                        <div class="testimonial-content">

                            <div class="text">
                                <p>Duis dignissim quis ante ut dapibus. Aenean ut ullamcorper quam, nec viverra lorem. Pellentesque eget libero vitae dolor egestas hendrerit. Cras vehicula venenatis libero in facilisis. Maecenas nec elit quis urna molestie vulputate. Lorem ipsum dolor sit amet, consectetur adipiscing elit.</p>
                            </div>

                        </div><!-- .testimonial-content  -->
                        <div class="testimonial-heading">

                            <div class="title">
                                <h4>Mary Smith</h4>
                                <p class="position">themeforest</p>
                            </div>

                        </div><!-- .testimonial-heading  -->
                    </li>
                    <li>
                        <div class="testimonial-content">

                            <div class="text">
                                <p>Nulla sollicitudin odio orci, quis pharetra lacus pretium sed. Suspendisse non justo lorem. Morbi elit arcu, commodo eu velit nec, ornare molestie tortor. Vestibulum purus lorem, malesuada vitae lectus id, luctus gravida erat. Nam bibendum erat vel augue viverra convallis.</p>
                            </div>

                        </div><!-- .testimonial-content  -->
                        <div class="testimonial-heading">

                            <div class="title">
                                <h4>Daniel Anderson</h4>
                                <p class="position">apple</p>
                            </div>

                        </div><!-- .testimonial-heading  -->
                    </li>
                    <li>
                        <div class="testimonial-content">

                            <div class="text">
                                <p>Aenean viverra neque odio, vel aliquam dui sodales ut. Nunc id purus vitae odio varius condimentum. Duis eu augue metus. Morbi a dolor ac elit auctor lobortis eget vitae lectus. Duis bibendum gravida euismod. Integer euismod nunc id tincidunt hendrerit. Aenean egestas vel sem ac accumsan.</p>
                            </div>

                        </div><!-- .testimonial-content  -->
                        <div class="testimonial-heading">

                            <div class="title">
                                <h4>Jane Johnson</h4>
                                <p class="position">google</p>
                            </div>

                        </div><!-- .testimonial-heading  -->
                    </li>
                </ul><!-- .testimonial -->
            </div><!-- .testimonial-outer -->
            <div class="testimonial-pagi"></div>
        </div><!-- .testimonial-wrap -->
    </div>

</div><!-- .container-out -->

</div><!-- #content -->
</div><!-- .container -->
</div><!-- #page-content -->

<footer id="footer">
<jsp:include page="/WEB-INF/jsp/newFooter.jsp" />
</footer>

<!-- jQuery & Helper library -->

<script type="text/javascript" src="js/NewWelcomejs/jquery.easing.min.js"></script>
<script type="text/javascript" src="js/NewWelcomejs/jquery.touchSwipe.min.js"></script>
<script type="text/javascript" src="js/NewWelcomejs/jquery.mousewheel.min.js"></script>
<script type="text/javascript" src="js/NewWelcomejs/imagesloaded.pkgd.min.js"></script>
<script type="text/javascript" src="js/NewWelcomejs/SmoothScroll.js"></script>

<!-- Retina js -->
<script type="text/javascript" src="js/NewWelcomejs/retina.js"></script>

<!-- FancyBox -->
<script type="text/javascript" src="js/NewWelcomejs/jquery.fancybox.pack.js?v=2.1.5"></script>

<!-- Bootstrap js -->
<script type="text/javascript" src="js/NewWelcomejs/bootstrap.min.js"></script>

<!-- Validate -->
<script type="text/javascript" src="js/NewWelcomejs/jquery.validate.min.js"></script>

<!-- FlickrFeed  -->
<!-- <script type="text/javascript" src="js/NewWelcomejs/jflickrfeed.min.js"></script>  -->
<!-- carouFredSel -->
<script type="text/javascript" src="js/NewWelcomejs/jquery.carouFredSel-6.2.1-packed.js"></script>

<!-- SLIDER REVOLUTION 4.x SCRIPTS  -->
<script type="text/javascript" src="js/NewWelcomejs/jquery.themepunch.plugins.min.js"></script>
<script type="text/javascript" src="js/NewWelcomejs/jquery.themepunch.revolution.min.js"></script>

<!-- Scripts for current page -->
<script type="text/javascript" src="js/NewWelcomejs/home.js"></script>

<!-- Main theme javaScript file -->
<script type="text/javascript" src="js/NewWelcomejs/theme.js"></script>

</body>
</html>