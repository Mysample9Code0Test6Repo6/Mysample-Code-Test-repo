<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="content-type" content="text/html; charset=UTF-8">
<link rel="shortcut icon" href="favicon.ico" />

<title>inCyyte - Edit My Profile</title>
<meta charset="utf-8">
<link rel="stylesheet" href="ui/css/reset.css">
<link rel="stylesheet" href="ui/css/style.css">
<link rel="stylesheet" href="ui/css/layout.css">
<link rel="stylesheet" href="ui/css/buttons.css"  media="screen">
<link rel="stylesheet" href="ui/css/style_login.css">
<link rel="stylesheet" href="ui/css/accordionmenu.css" type="text/css">
<link rel="stylesheet" href="ui/css/icons_sprites.css">
<link rel="stylesheet" href="fonts/fonts_stylesheet.css">
<link rel="stylesheet" href="ui/css/style_help.css">
<link rel="stylesheet" href="ui/modal/colorbox.css">
<link rel="stylesheet" href="ui/css/ratingbar.css" type="text/css" />
<link rel="stylesheet" href="ui/css/jquery.selectbox.css" type="text/css" />

<script src="ui/js/jquery-1.4.2.min.js"></script>
<script src="ui/js/jquery-1.7.1.js"></script>
<script src="ui/js/login.js"></script>
<script src="ui/js/jquery.color.js"></script>
<script src="ui/js/search_script.js"></script>
<script src="ui/js/jquery_profile_rating.js" type="text/javascript"></script>
<script src="ui/js/star_rating.js" type="text/javascript"></script>
<script src="ui/modal/colorbox/jquery.colorbox.js"></script>
<!-- Left Navigation script starts here -->
<script src="ui/js/left_menu.js"></script>
<!-- Left Navigation script ends here -->

<script src="ui/js/modernizr-1.7.min.js"></script>
<script src="ui/js/jquery.selectbox-0.2.js"></script>

<script type="text/javascript">
		$(function () {
			$("select").selectbox();
			//$("#country_id1").selectbox();
		});
</script>

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
<script type="text/javascript">
$(document).ready(function(){


$("#help a").append("<em></em>");
	
	$("#help a").hover(function() {
		$(this).find("em").animate({opacity: "show", top: "35"}, "fast");
		var hoverText = $(this).attr("title");
	    $(this).find("em").text(hoverText);
	}, function() {
		$(this).find("em").animate({opacity: "hide", top: "50"}, "fast");
	});

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
<body>
<div id="gradient">
  <div class="extra">
    <!-- include header -->
    <jsp:include page="../common/includes/header.jsp" />

    <div class="main">
      <!--content -->
      <article id="content">
        <div id="main_content">
          <div class="grid_9">
            <h1>Edit Profile</h1>
            <table width="100%" border="0" cellspacing="0" cellpadding="0">
              <tr>
                <td width="21%" height="50" rowspan="2"><img src="ui/images/profile_picture_small.png" alt="Profile Picture"></td>
                <td width="79%" height="26"  class="font_16px"><strong>UserName</strong></td>
              </tr>
              <tr>
                <td class="view_my_profile"><a href="#">View my Profile</a></td>
              </tr>
            </table>
            <nav>
              <script type="text/javascript" src="ui/includes/leftmenu_profile.js"></script>
            </nav>
            <div class="hline"></div>
			<h3 >PROFILE OVERVIEW</h3>
            <br>
            <div class="tokens">You Have &euro;999!</div>
            <div id="progress_bar1">
              <div id="progress1" style="width:110px"></div>
  			  <a href="./editProfile.cyt"><div id="progress_text1">Your Profile is 40% Complete</div></a>
            </div>
            <br>
            <h1 class="font_18px">Fill In your:</h1>
            <ul class="redtext">
              <li> <span> >> </span> <a href="./editProfile.cyt">Profile Picture</a> </li>
              <li> <span> >> </span> <a href="./editProfile.cyt">Your insight</a></li>
            </ul>
            <div id="advert"><img src="ui/images/advert.png" alt="Advert"></div>
          </div>
          <div class="line"><span></span></div>
          <div class="grid_17">
            <!--- Edit Profile Starts---->
            <div id="edit_profile" >
              <div id="profile_picture">
                <h3 class="heading4" >Picture</h3>
                <table width="100%" border="0" cellspacing="0" cellpadding="0">
                  <tr>
                    <td width="20%" rowspan="3"><img src="ui/images/profile_picture.png" alt="Profile Picture"></td>
                    <td width="80%" valign="top"><a href="" title="UPLOAD A NEW PHOTO" id="" class="button_gray" style="width:210px; float:left; margin-bottom:20px;"> <span class="title_gray">UPLOAD A NEW PHOTO</span></a> </td>
                  </tr>
                  <tr>
                    <td height="26" valign="top" class="font_14px">Maximum size of 700k. JPG, GIF, PNG.</td>
                  </tr>
                  <tr>
                    <td valign="top" class="font_16px"><a href="#">Delete this image</a></td>
                  </tr>
                </table>
              </div>
              <div id="profile_name">
                <h3 class="heading4" >Name</h3>
                <table width="450" border="0" cellspacing="0" cellpadding="0">
                  <tr>
                    <td height="70" valign="top"><label>Your Name </label>
                      <input type="text" id="yname" >
                      </input></td>
                  </tr>
                  <tr>
                    <td width="47%"><label>User Name</label>
                      <input type="text" id="uname" >
                      </input>
                      <br>
                      <p>This will be shown on your profile</p></td>
                  </tr>
                </table>
              </div>
              <div id="profile_location">
                <h3 class="heading4" >Location</h3>
                <table width="450" border="0" cellspacing="0" cellpadding="0">
                  <tr>
                    <td width="47%"><label>City</label>
                      <input type="text" id="city" >
                      </input></td>
                  </tr>
                  <tr>
                    <td><label>Post Code</label>
                      <input type="text" id="pcode" >
                      </input>
					  <p>The ﬁrst part of your post code will be used to make </p>
					  <p>InCyyte stats more accurate.</p>
					  </td>
                  </tr>
                </table>
              </div>
            </div>
            <!--- Edit Profile Ends---->
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
