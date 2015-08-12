<%@ include file="/WEB-INF/jsp/common/include.jsp" %>
<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="content-type" content="text/html; charset=UTF-8">
<link rel="shortcut icon" href="favicon.ico" />

<title>inCyyte - Dashboard</title>
<meta charset="utf-8">
<link rel="stylesheet" href="ui/css/reset.css">
<link rel="stylesheet" href="ui/css/layout.css">
<link rel="stylesheet" href="ui/css/buttons.css"  media="screen">
<link rel="stylesheet" href="ui/css/style_login.css">
<link rel="stylesheet" href="ui/css/accordionmenu.css" type="text/css">
<link rel="stylesheet" href="ui/css/icons_sprites.css">
<link rel="stylesheet" href="fonts/fonts_stylesheet.css">
<script src="ui/js/jquery-1.7.1.js"></script>
<script src="ui/js/login.js"></script>
<script src="ui/js/jquery.color.js"></script>
<script src="ui/js/search_script.js"></script>
<script type="text/javascript" src="ui/js/easySlider1.7.js"></script>
<link rel="stylesheet" href="ui/css/themes/base/jquery.ui.all.css">
<script src="ui/js/accordian/jquery.ui.core.js"></script>
<script src="ui/js/accordian/jquery.ui.widget.js"></script>
<script src="ui/js/accordian/jquery.ui.accordion.js"></script>
<script type="text/javascript">
   //opener.location='authSuccess.do';
   // window.close();
</script>
<script>
	$(function() {
		$( "#accordion" ).accordion();
	});
	</script>
<script type="text/javascript">
		$(document).ready(function() {
			// Store variables
			var accordion_head = $('.accordion > li > a'),
				accordion_body = $('.accordion li > .sub-menu');
			// Open the first tab on load
			accordion_head.first().addClass('active').next().slideDown('normal');
			// Click function
			accordion_head.on('click', function(event) {
				// Disable header links
				event.preventDefault();
				// Show and hide the tabs on click
				if ($(this).attr('class') != 'active'){
					accordion_body.slideUp('normal');
					$(this).next().stop(true,true).slideToggle('normal');
					accordion_head.removeClass('active');
					$(this).addClass('active');
				}

			});

		});

	</script>
<!-- Chart Script Start here -->
<script src="ui/js/charts/js/charts.js"></script>
<script src="ui/js/charts/js/highcharts.js"></script>
<!-- Chart Script end here -->
<link rel="stylesheet" type="text/css" href="ui/css/ratingbar.css" />
<script type="text/javascript" src="ui/js/jquery.ratingbar.js"></script>
<script type="text/javascript" charset="utf-8">
$(document).ready(function() {
	$('.gender_rating').ratingbar();
});
</script>
<script type="text/javascript" src="ui/js/ddaccordion.js"></script>
<script type="text/javascript">

//Initialize :
ddaccordion.init({
	headerclass: "question_tab", //Shared CSS class name of headers group
	contentclass: "question_detail", //Shared CSS class name of contents group
	revealtype: "click", //Reveal content when user clicks or onmouseover the header? Valid value: "click", "clickgo", or "mouseover"
	mouseoverdelay: 200, //if revealtype="mouseover", set delay in milliseconds before header expands onMouseover
	collapseprev: false, //Collapse previous content (so only one open at any time)? true/false 
	defaultexpanded: [], //index of content(s) open by default [index1, index2, etc]. [] denotes no content.
	onemustopen: true, //Specify whether at least one header should be open always (so never all headers closed)
	animatedefault: false, //Should contents open by default be animated into view?
	scrolltoheader: false, //scroll to header each time after it's been expanded by the user?
	persiststate: false, //persist state of opened contents within browser session?
	toggleclass: ["closedquestion", "openquestion"], //Two CSS classes to be applied to the header when it's collapsed and expanded, respectively ["class1", "class2"]
	togglehtml: ["none", "", " "], //Additional HTML added to the header when it's collapsed and expanded, respectively  ["position", "html1", "html2"] (see docs)
	animatespeed: "fast", //speed of animation: integer in milliseconds (ie: 200), or keywords "fast", "normal", or "slow"
	oninit:function(expandedindices){ //custom code to run when headers have initalized
		//do nothing
	},
	onopenclose:function(header, index, state, isuseractivated){ //custom code to run whenever a header is opened or closed
		//do nothing
	}
})

</script>
<script type="text/javascript" src="ui/js/autoresize.jquery.js"></script>
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
<script>
$(function () {
  $("textarea").autoresize();
})
</script>
<!--[if lt IE 9]>
   <script src="ui/js/html5.js"></script>
<![endif]-->
<!--[if lt IE 9]>
    <meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7">
  <link href="ui/css/iestyle.css" media="screen" rel="stylesheet" type="text/css" />
  <![endif]-->
</head>
<body>
<div id="gradient">
  <div class="extra">
    <!-- include header -->
    <jsp:include page="../common/includes/header.jsp" />
  
    <div class="main">
      <!--content -->
      <article id="content">
        <div id="main_content">
          <div id="ask_que_now">
            <h3 class="heading1">Ask a Question...</h3>
            <div id="close"><a href="" title="Close"></a></div>
            <div id="add_media">
              <div id="typeyourquestion">
                <input  type="text" placeholder="Type your question here... " onFocus="this.placeholder = ''" onBlur="this.placeholder = 'Type your question here...'" id="myTextarea3">
                </input>
              </div>
              <div id="media_icon"><span>Add</span>
                <ul>
                  <li id="videos"><a href="#"></a></li>
                  <li id="photos"><a href="#"></a></li>
                  <li id="docs"><a href="#"></a></li>
                </ul>
              </div>
            </div>
            <p class="heading2">Pick Category</p>
            <div id="categories_list">
              <ul>
                <li id="music"><a href="#"></a></li>
                <li id="sports"><a href="#"></a></li>
                <li id="religion"><a href="#"></a></li>
                <li id="politics"><a href="#"></a></li>
                <li id="community"><a href="#"></a></li>
                <li id="relationships"><a href="#"></a></li>
                <li id="business"><a href="#"></a></li>
                <li id="shopping"><a href="#"></a></li>
                <li id="health"><a href="#"></a></li>
                <li id="beauty"><a href="#"></a></li>
                <li id="travel"><a href="#"></a></li>
                <li id="food"><a href="#"></a></li>
                <li id="motoring"><a href="#"></a></li>
                <li id="internet"><a href="#"></a></li>
                <li id="entertainment"><a href="#"></a></li>
                <li id="others"><a href="#"></a></li>
              </ul>
            </div>
            <div id="ask_que_inner" ><a href="" title="ASK YOUR QUESTION NOW!" class="button_red" > <span class="title_red" >ASK YOUR QUESTION NOW!</span></a> </div>
          </div>
          <div style="margin-left:90px;"><img src="ui/images/up_arrow.png"></div>
          <div class="grid_9"> <a href="" title="GET INCYYTE !" id="" class="button_gray" style="margin-bottom:30px; width:218px;"> <span class="title_gray">Get inCyyte !</span></a>
            <nav>
              <ul class="accordion">
                <li ><a href="#">My Recent InCyytes <span>3</span></a></li>
                <li ><a href="#">Income InCyytes <span>5</span></a></li>
                <li ><a href="#">Sent InCyytes <span style="display:none"></span></a></li>
                <li ><a href="#">Completed InCyytes <span style="display:none"></span> </a></li>
                <li ><a href="#">Polls in My Region (N6) <span style="display:none"></span></a></li>
                <li ><a href="#">My Friends InCyytes <span style="display:none"></span></a></li>
                <li ><a href="#">Business Splashes <span style="display:none"></span></a></li>
                <li ><a href="#">Quick Tour <span style="display:none"></span></a></li>
              </ul>
            </nav>
            <div class="hline"></div>
            <h1 >PROFILE OVERVIEW</h1>
            <br>
            <div class="tokens">500 TOKENS</div>
            <div id="progress_bar">
              <div id="progress" style="width:90px"></div>
              <div id="progress_text">Your Profile is 40% Complete</div>
            </div>
            <br>
            <h1 class="font_18px">Fill In your:</h1>
            <ul class="redtext">
              <li> <span> >> </span> <a href="#">Address</a></li>
              <li> <span> >> </span> <a href="#">Postcode</a> </li>
            </ul>
            <div id="advert"><img src="ui/images/advert.png" alt="Advert"></div>
          </div>
          <div class="line"><span></span></div>
          <div class="grid_17">
            <div id="searchbox">
              <jsp:include page="../include/dashboardFilter.jsp" />
              <div id="searchmain">
                <p class="search_heading">Search</p>
                <form id="searchForm">
                  <fieldset>
                  <div class="input">
                    <input type='text' name="search" id="search" value="Enter your search" />
                  </div>
                  <input type="submit" id="searchSubmit" value="" />
                  </fieldset>
                </form>
              </div>
            </div>
            <div id="most_resent_incyyte">
              <!--<a href="#" onClick="ddaccordion.collapseall('question_tab'); return false">Collapse all</a> | <a href="#" onClick="ddaccordion.expandall('question_tab'); return false">Expand all</a>-->
              <div class="question_tab">
                <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" >
                  <tr>
                    <td class="font_16px">My Most Recent InCyyte:</td>
                    <td class="grid_4a">12th December 2012<br>
                      Group Name, Region</td>
                  </tr>
                  <tr>
                    <td>Do You think I can eat 30 cakes?</td>
                    <td>&nbsp;</td>
                  </tr>
                </table>
              </div>
              <div class="question_detail">
                <table width="100%" border="0" cellspacing="0" cellpadding="0">
                  <tr>
                    <td width="60%" valign="top" class="chart_padding">Polls Result<br>
                      <div id="container" style="width: 370px; height: 260px; "></div></td>
                    <td width="40%" valign="top" class="grid_6a"><span>Responses</span> <br>
                      <strong>1,254</strong> of <strong>2,000</strong> people responded<br>
                      <br>
                      Gender<br>
                      <table id="gender">
                        <tr>
                          <td><img src="ui/images/male_gender.png"></td>
                          <td class="gender_rating">40%</td>
                        </tr>
                        <tr>
                          <td><img src="ui/images/female_gender.png"></td>
                          <td class="gender_rating">60%</td>
                        </tr>
                      </table>
                      <br>
                      <br>
                      <a href="" title="view incyyte" id="view_incyyte" class="button_gray" style="width:171px;"> <span class="title_gray">VIEW INCYYTE</span></a> </td>
                  </tr>
                </table>
              </div>
              <div class="question_tab">
              <!--Dummy table for heading to hide gragh start-->
                 <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" style="display:none;">
                  <tr>
                    <td >Have you heard of InCyyte?</td>
                    <td class="grid_4a">12th December 2012<br>
                      Group Name, Region</td>
                  </tr>
                </table>
 				<!--Dummy table for heading to hide gragh end-->
                <table width="100%" border="0" cellspacing="0" cellpadding="0">
                  <tr>
                    <td width="17%" ><div id="containersmall1" style="width: 100px; height: 100px; "> </div></td>
                    <td width="57%"  >Have you heard of InCyyte?<br>
                      <span class="grid_4a">12th December 2012<br>
                      Group Name, Region<br>
                      </span></td>
                    <td width="26%" ><a href="" title="view incyyte" id="view_incyyte" class="button_gray" style="width:171px;"> <span class="title_gray">VIEW INCYYTE</span></a></td>
                  </tr>
                </table>
              </div>
              <div class="question_detail">
                <table width="100%" border="0" cellspacing="0" cellpadding="0">
                  <tr>
                    <td width="60%" valign="top" class="chart_padding">Polls Result<br>
                      <div id="container1" style="width: 370px; height: 260px; "></div></td>
                    <td width="40%" valign="top" class="grid_6a"><span>Responses</span> <br>
                      <strong>1,254</strong> of <strong>2,000</strong> people responded<br>
                      <br>
                      Gender<br>
                      <table id="gender">
                        <tr>
                          <td><img src="ui/images/male_gender.png"></td>
                          <td class="gender_rating">40%</td>
                        </tr>
                        <tr>
                          <td><img src="ui/images/female_gender.png"></td>
                          <td class="gender_rating">100%</td>
                        </tr>
                      </table>
                      <br>
                      <br>
                      <a href="" title="view incyyte" id="view_incyyte" class="button_gray" style="width:171px;"> <span class="title_gray">VIEW INCYYTE</span></a></td>
                  </tr>
                </table>
              </div>
              <div class="question_tab">
                <table width="100%" border="0" cellspacing="0" cellpadding="0">
                  <tr>
                    <td width="17%" ><div id="containersmall2" style="width: 100px; height: 100px; "> </div></td>
                    <td width="57%"  >Have you heard of InCyyte?<br>
                      <span class="grid_4a">12th December 2012<br>
                      Group Name, Region<br>
                      </span></td>
                    <td width="26%" ><a href="" title="view incyyte" id="view_incyyte" class="button_gray" style="width:171px;"> <span class="title_gray">VIEW INCYYTE</span></a></td>
                  </tr>
                </table>
              </div>
              <div class="question_detail">
                <table width="100%" border="0" cellspacing="0" cellpadding="0">
                  <tr>
                    <td width="60%" valign="top" class="chart_padding">Polls Result<br>
                      <div id="container2" style="width: 370px; height: 260px; "></div></td>
                    <td width="40%" valign="top" class="grid_6a"><span>Responses</span> <br>
                      <strong>1,254</strong> of <strong>2,000</strong> people responded<br>
                      <br>
                      Gender<br>
                      <table id="gender">
                        <tr>
                          <td><img src="ui/images/male_gender.png"></td>
                          <td class="gender_rating">40%</td>
                        </tr>
                        <tr>
                          <td><img src="ui/images/female_gender.png"></td>
                          <td class="gender_rating">60%</td>
                        </tr>
                      </table>
                      <br>
                      <br>
                      <a href="" title="view incyyte" id="view_incyyte" class="button_gray" style="width:171px;"> <span class="title_gray">VIEW INCYYTE</span></a> </td>
                  </tr>
                </table>
              </div>
              <div class="question_tab">
                <table width="100%" border="0" cellspacing="0" cellpadding="0">
                  <tr>
                    <td width="17%" ><div id="containersmall3" style="width: 100px; height: 100px; "> </div></td>
                    <td width="57%"  >Have you heard of InCyyte?<br>
                      <span class="grid_4a">12th December 2012<br>
                      Group Name, Region<br>
                      </span></td>
                    <td width="26%" ><a href="" title="view incyyte" id="view_incyyte" class="button_gray" style="width:171px;"> <span class="title_gray">VIEW INCYYTE</span></a></td>
                  </tr>
                </table>
              </div>
              <div class="question_detail">
                <table width="100%" border="0" cellspacing="0" cellpadding="0">
                  <tr>
                    <td width="60%" valign="top" class="chart_padding">Polls Result<br>
                      <div id="container3" style="width: 370px; height: 260px; "></div></td>
                    <td width="40%" valign="top" class="grid_6a"><span>Responses</span> <br>
                      <strong>1,254</strong> of <strong>2,000</strong> people responded<br>
                      <br>
                      Gender<br>
                      <table id="gender">
                        <tr>
                          <td><img src="ui/images/male_gender.png"></td>
                          <td class="gender_rating">40%</td>
                        </tr>
                        <tr>
                          <td><img src="ui/images/female_gender.png"></td>
                          <td class="gender_rating">60%</td>
                        </tr>
                      </table>
                      <br>
                      <br>
                      <a href="" title="view incyyte" id="view_incyyte" class="button_gray" style="width:171px;"> <span class="title_gray">VIEW INCYYTE</span></a> </td>
                  </tr>
                </table>
              </div>
            </div>
          </div>
        </div>
      </article>
      <!--content end -->
    </div>
  </div>
  <!-- include footer -->
  <jsp:include page="../common/includes/footer.jsp" />
  <script type="text/javascript" src="ui/js/googleanalytics.js"></script>
</body>
</html>
