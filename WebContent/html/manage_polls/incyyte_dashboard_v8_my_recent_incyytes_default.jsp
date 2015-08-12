<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Date" %>
<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="content-type" content="text/html; charset=UTF-8">
<title>inCyyte_Dashboard_V8_My Recent InCyytes (Default)</title>
<meta charset="utf-8">
<link rel="stylesheet" href="../../ui/css/reset.css">
<link rel="stylesheet" href="../../ui/css/style.css">
<link rel="stylesheet" href="../../ui/css/layout.css">
<link rel="stylesheet" href="../../ui/css/buttons.css"  media="screen">
<link rel="stylesheet" href="../../ui/css/style_login.css">
<link rel="stylesheet" href="../../ui/css/accordionmenu.css" type="text/css">
<link rel="stylesheet" href="../../ui/css/icons_sprites.css">
<link rel="stylesheet" href="../../ui/css/form_elements.css">
<link rel="stylesheet" href="../../ui/css/tooltip.css">
<link rel="stylesheet" href="../../fonts/fonts_stylesheet.css">
<link rel="stylesheet" href="../../ui/modal/colorbox.css">
<script>
var yesPercentage=<%=Integer.parseInt("10")%>;
var noPercentage=<%=40%>;
var mayBePercentage=<%=50%>;
</script>
<script src="../../ui/js/jquery-1.7.1.js"></script>
<script src="../../ui/js/jquery-1.8.3.min.js" type="text/javascript"></script> 
<script src="../../ui/js/tooltip.js"></script>
<script src="../../ui/js/login.js"></script>
<script src="../../ui/js/jquery.color.js"></script>
<script src="../../ui/js/search_script.js"></script>
<script src="../../ui/js/jquery_dashboard_rating.js" type="text/javascript"></script>
<script src="../../ui/js/star_rating.js" type="text/javascript"></script>
<script src="../../ui/modal/colorbox/jquery.colorbox.js"></script>
<!-- Left Navigation script starts here -->
<script src="../../ui/js/left_menu.js"></script>
<!-- Left Navigation script ends here -->

<!-- Chart Script Start here -->
<script src="../../ui/js/charts/js/charts.js"></script>
<script src="../../ui/js/charts/js/highcharts.js"></script>
<!-- Chart Script end here -->

<!-- Rating bar and profile starts here -->
<link rel="stylesheet" type="text/css" href="../../ui/css/ratingbar.css" />
<script type="text/javascript" src="../../ui/js/jquery.ratingbar.js"></script>
<script type="text/javascript" charset="utf-8">
$(document).ready(function() {
	$('.gender_rating').ratingbar();
});
</script>
<!-- Rating bar and profile ends here -->

    <script type="text/javascript">
        $(document).ready(function () {
            $('input#purple').gips({ 'theme': 'purple', autoHide: true, text: 'This is purple tooltip, auto hide after pausess time elapses.' });
            $('input#green').gips({ 'theme': 'green', placement: 'left' });
            $('input#yellow').gips({ 'theme': 'yellow', autoHide: true, placement: 'right' });
            $('#hints').gips({ 'theme': 'red', placement: 'bottom', text:'<p>1. Click here at any time to create new InCyyte.</p> <p>InCyyte are your questions to friends, people in your area and more!</p> <a href="" title="Next: View Your Results" class="button_red" > <span class="title_red">Next: View Your Results</span></a>' });
        });
    </script>


<!--- Questions Tabs starts------->
<script type="text/javascript" src="../../ui/js/ddaccordion.js"></script>
<script type="text/javascript">
//Initialize :
ddaccordion.init({
	headerclass: "question_tab", //Shared CSS class name of headers group
	contentclass: "question_detail", //Shared CSS class name of contents group
	revealtype: "click", //Reveal content when user clicks or onmouseover the header? Valid value: "click", "clickgo", or "mouseover"
	mouseoverdelay: 200, //if revealtype="mouseover", set delay in milliseconds before header expands onMouseover
	collapseprev: true, //Collapse previous content (so only one open at any time)? true/false 
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
    });
</script>
<!--- placeholder Ends----->

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
#welcome_user { margin:9px auto 0 auto;}
</style>
<![endif]-->
</head>
<body>
<div id="gradient">
  <div class="extra">
    <!--header -->
    <header class="header36">
      <div id="headerinner">
        <div id="logocontainer"><a href="#" id="logo_inner">inCyyte</a></div>
        <div id="header_topnav_inner">
          <ul id="header_topmenu">
            <li><a href="#" >Home</a></li>
            <li><a href="#" >Messages</a></li>
            <li><a href="#" >Contacts</a></li>
            <li class="last"><a href="#" >Groups</a></li>
            <li style="padding-right:14px;">
              <form id="searchform_top">
                <input type='text' name="top_search" id="top_search"  placeholder="Search for people, questions etc." onFocus="this.placeholder = ''" onBlur="this.placeholder = 'Search for people, questions etc.'">
                <input type="submit" id="searchSubmit_top" value="" />
              </form>
            </li>
            <!-- User settings starts Here -->
			<li id="usersettingContainer" class="last"> <a href="#" id="user_setting"><span>Settings</span><em></em></a>
              <div style="clear:both"></div>
              <div id="usersettingBox">
                <div id="usersettingForm" >
                  <p>You are in the <u>N6</u> InCyyte Region </p>
                  <p style="padding-bottom:8px;">Rating: Influential </p>
                  <div id="fixed">
                    <p class="more_rating_details"><a href="#more_incyyte_rating" class="inline">More Rating Details</a></p>
                  </div>
                  <p class="hline_setting"></p>
                  <ul>
                    <li><a href="#">My Account</a></li>
                    <li><a href="#">Edit Profile</a></li>
                    <li><a href="#">Edit Settings</a></li>
                  </ul>
                </div>
              </div>
            </li>            <!-- User User settings Ends Here -->
            <li class="last"><a href="#" >Log Out</a></li>
          </ul>
        </div>
		        <!--More Incyyte rating Start---->
        <div style="display:none;">
          <div id="more_incyyte_rating" >
            <h3>My InCyyte Rating</h3>
            <table width="100%" border="0" cellspacing="0" cellpadding="0">
              <tr>
                <td width="17%" height="85"><img src="../../ui/images/profile_picture_rating.png" alt="Profile Picture"></td>
                <td width="34%"><p>User Name</p>
                  <p style="padding-bottom:10px;">Rating: Influential</p>
                  <div id="fixed"></div></td>
                <td width="49%" align="right"><p>Recipient response rate:</p>
                  <p class="rating_per">85%</p></td>
              </tr>
              <tr>
                <td colspan="3" style="border-bottom:1px solid #fff;"></td>
              </tr>
              <tr>
                <td colspan="3" height="40">Member Since .......</td>
              </tr>
              <tr>
                <td colspan="3"><table width="100%" border="0" cellspacing="0" cellpadding="0" class="rating_detail_table">
                    <tr>
                      <td>InCyyte Polls</td>
                      <td>Recipients</td>
                      <td>Responses</td>
                      <td>Regions</td>
                    </tr>
                    <tr class="alt">
                      <td>150</td>
                      <td>10,132</td>
                      <td>8,496</td>
                      <td>19</td>
                    </tr>
                  </table></td>
              </tr>
            </table>
          </div>
        </div>
        <!-- More Incyyte rating Start---->
		
		<!-- Welcome User Start---->
        <div id="welcome_user">
          <div id="userphoto"><img src="../../ui/images/user_photo.png"></div>
          <p class="username">Welcome Julian. <span>You are in the N6 region.</span></p>
          <p class="view_polls"><a href="#">Click here to view polls in your region.</a></p>
        </div>
		<!-- Welcome User End---->
      </div>
    </header>
    <!--header end-->
    <div class="main">
      <!--content -->
      <article id="content">
        <div id="main_content">
          <div class="grid_9"> 
		  <a href="" id="hints" class="button_red" style="margin-bottom:30px; width:218px;"> <span class="title_red">ASK A QUESTION!</span></a>
		  
            <nav>
			<script type="text/javascript" src="../../ui/includes/leftmenu_dashboard.js"></script> 
            </nav>
            <div class="hline"></div>
            <h3 >PROFILE OVERVIEW</h3>
            <br>
            <div class="tokens">You Have &pound;999!</div>
            <div id="progress_bar1">
              <div id="progress1" style="width:110px"></div>
              <div id="progress_text1">Your Profile is 40% Complete</div>
            </div>
            <br>
            <h1 class="font_18px">Fill In your:</h1>
            <ul class="redtext">
              <li> <span> >> </span> <a href="#">Address</a></li>
              <li> <span> >> </span> <a href="#">Postcode</a> </li>
            </ul>
            <div id="advert"><img src="../../ui/images/advert.png" alt="Advert"></div>
          </div>
          <div class="line"><span></span></div>
          <div class="grid_17">
            <div id="searchbox">
              <p class="sort_by_text">Sort by:</p>
              <nav id="sort_by">
                <li ><a href="#" class="active">Date</a></li>
                |
                <li><a href="#">Group</a></li>
                |
                <li><a href="#">Region</a></li>
                |
                <li><a href="#">Answered</a></li>
              </nav>
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
                    <td><p>12th December 2012</p>
                      <p>Group Name, Region</p></td>
                  </tr>
                  <tr>
                    <td><h3>Do You think I can eat 30 cakes?</h3></td>
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
                      <span>Gender</span><br>
                      <table id="gender">
                        <tr>
                          <td height="60" width="30"><img src="../../ui/images/male_gender.png"></td>
                          <td class="gender_rating">40%</td>
                        </tr>
                        <tr>
                          <td><img src="../../ui/images/female_gender.png"></td>
                          <td class="gender_rating">60%</td>
                        </tr>
                      </table>
                      <br>
                      <br>
                      <a href="" title="view details" id="view_details" class="button_gray" style="width:171px;"> <span class="title_gray">VIEW DETAILS</span></a> </td>
                  </tr>
                </table>
              </div>
              <div class="question_tab">
                <!--Dummy table for heading to hide gragh start-->
                <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" style="display:none;">
                  <tr>
                    <td ><h3>Have you heard of InCyyte?</h3></td>
                    <td ><p>12th December 2012</p>
                      <p>Group Name, Region</p></td>
                  </tr>
                </table>
                <!--Dummy table for heading to hide gragh end-->
                <table width="100%" border="0" cellspacing="0" cellpadding="0">
                  <tr>
                    <td width="17%" ><div id="containersmall1" style="width: 100px; height: 100px; "> </div></td>
                    <td width="57%" ><h3>Have you heard of InCyyte?</h3>
                      <p>12th December 2012</p>
                      <p>Group Name, Region</p>                      </td>
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
                      <span>Gender</span>
					  <br>
                      <table id="gender">
                        <tr>
                          <td><img src="../../ui/images/male_gender.png"></td>
                          <td class="gender_rating">40%</td>
                        </tr>
                        <tr>
                          <td><img src="../../ui/images/female_gender.png"></td>
                          <td class="gender_rating">100%</td>
                        </tr>
                      </table>
                      <br>
                      <br>
                      <a href="" title="view details" id="view_details" class="button_gray" style="width:171px;"> <span class="title_gray">VIEW DETAILS</span></a></td>
                  </tr>
                </table>
              </div>
              <div class="question_tab">
                <table width="100%" border="0" cellspacing="0" cellpadding="0">
                  <tr>
                    <td width="17%" ><div id="containersmall2" style="width: 100px; height: 100px; "> </div></td>
                    <td width="57%"  ><h3>Have you heard of InCyyte?</h3>
                      <p>12th December 2012</p>
                      <p>Group Name, Region</p>                      </td>
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
                      <span>Gender</span>
                      <table id="gender">
                        <tr>
                          <td><img src="../../ui/images/male_gender.png"></td>
                          <td class="gender_rating">40%</td>
                        </tr>
                        <tr>
                          <td><img src="../../ui/images/female_gender.png"></td>
                          <td class="gender_rating">60%</td>
                        </tr>
                      </table>
                      <br>
                      <br>
                      <a href="" title="view details" id="view_details" class="button_gray" style="width:171px;"> <span class="title_gray">VIEW DETAILS</span></a> </td>
                  </tr>
                </table>
              </div>
              <div class="question_tab">
                <table width="100%" border="0" cellspacing="0" cellpadding="0">
                  <tr>
                    <td width="17%" valign="top"><div id="containersmall3" style="width: 100px; height: 100px; "> </div></td>
                    <td width="57%"><h3>Here is a long question that uses the maximum characters possible and that will show an example of how this would look on page and - although it’s likely an end case - we’ll be able to see the impact of using many characters. I think 250 is too high.</h3>
                      <p>12th December 2012</p>
                      <p>Group Name, Region</p>                      </td>
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
                      <span>Gender</span>
                      <table id="gender">
                        <tr>
                          <td><img src="../../ui/images/male_gender.png"></td>
                          <td class="gender_rating">40%</td>
                        </tr>
                        <tr>
                          <td><img src="../../ui/images/female_gender.png"></td>
                          <td class="gender_rating">60%</td>
                        </tr>
                      </table>
                      <br>
                      <br>
                      <a href="" title="view details" id="view_details" class="button_gray" style="width:171px;"> <span class="title_gray">VIEW DETAILS</span></a> </td>
                  </tr>
                </table>
              </div>
            </div>
            <!-- Pagination start---->
            <script type="text/javascript" src="../../ui/includes/pagination.js"></script>
			<!-- Pagination ends---->
          </div>
        </div>
      </article>
      <!--content ends -->
    </div>
  </div>
  <!--footer Starts -->
  <footer class="footer_inner">
      <%
          String strFooterDate ="";
          try{
              SimpleDateFormat smp = new SimpleDateFormat("yyyy");
              strFooterDate = smp.format(new Date());
          }catch(Exception ex){}
      %>
      <div style=" text-align:center; "> &copy; inCyyte <%=strFooterDate%> All rights reserved - Website created by Alight <img src="../../ui/images/alight_logo_inner.png" align="middle" style="padding-top:4px;"> </div>
  </footer>
</div>
<!--footer ends -->
</body>
</html>
