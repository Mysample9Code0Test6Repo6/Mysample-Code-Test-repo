<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="content-type" content="text/html; charset=UTF-8">
<link rel="shortcut icon" href="favicon.ico" />

<title>inCyyte_Groups_V2_Edit - Delete Groups</title>
<meta charset="utf-8">
<link rel="stylesheet" href="ui/css/reset.css">
<link rel="stylesheet" href="ui/css/layout.css">
<link rel="stylesheet" href="ui/css/buttons.css"  media="screen">
<link rel="stylesheet" href="ui/css/style_login.css">
<link rel="stylesheet" href="ui/css/accordionmenu.css" type="text/css">
<link rel="stylesheet" href="ui/css/icons_sprites.css">
<link rel="stylesheet" href="fonts/fonts_stylesheet.css">
<link rel="stylesheet" href="ui/css/style_social.css">
<link rel="stylesheet" href="ui/modal/colorbox.css">


<script src="ui/js/jquery-1.4.2.min.js"></script>
<script src="ui/js/jquery-1.7.1.js"></script>
<script src="ui/js/login.js"></script>
<script src="ui/js/jquery.color.js"></script>
<script src="ui/js/search_script.js"></script>
<script src="ui/modal/colorbox/jquery.colorbox.js"></script>

<script src="ui/js/jquery_profile_rating.js" type="text/javascript"></script>  
<script src="ui/js/star_rating.js" type="text/javascript"></script>
		

<!-- Left Navigation script starts here -->
<script src="ui/js/left_menu.js"></script>
<!-- Left Navigation script ends here -->
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


<!--- placeholder Ends----->
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
            <h1>Groups</h1>
            <nav>
              <script type="text/javascript" src="ui/includes/leftmenu_groups.js"></script>
            </nav>
            <div class="hline"></div>
            <div id="advert"><img src="ui/images/advert.png" alt="Advert"></div>
          </div>
          <div class="line"><span></span></div>
          <div class="grid_17">
            <!------ Search Box Starts -------->
            <div id="advanced_searchbox">
              <div id="searchbox" style="margin-bottom:0">
                <p class="sort_by_text"></p>
                <div id="searchmain" >
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
              <nav id="alpha_list">
                <li><a href="#">1</a></li>
                <li><a href="#">2</a></li>
                <li><a href="#">3</a></li>
                <li><a href="#">A</a></li>
                <li><a href="#">B</a></li>
                <li><a href="#">C</a></li>
                <li><a href="#">D</a></li>
                <li><a href="#">E</a></li>
                <li><a href="#">F</a></li>
                <li><a href="#">G</a></li>
                <li><a href="#">H</a></li>
                <li><a href="#">I</a></li>
                <li><a href="#">J</a></li>
                <li><a href="#">K</a></li>
                <li><a href="#">L</a></li>
                <li><a href="#">M</a></li>
                <li><a href="#">N</a></li>
                <li><a href="#">O</a></li>
                <li><a href="#">P</a></li>
                <li><a href="#">Q</a></li>
                <li><a href="#">R</a></li>
                <li><a href="#">S</a></li>
                <li><a href="#">T</a></li>
                <li><a href="#">U</a></li>
                <li><a href="#">V</a></li>
                <li><a href="#">W</a></li>
                <li><a href="#">X</a></li>
                <li><a href="#">Z</a></li>
              </nav>
            </div>
            <!------ Search Box End-------->
            <!-- view_all_contacts Start -->
            <div id="view_all_contacts">
              <table width="100%" border="0" cellspacing="0" cellpadding="0" id="contact_list">
                <tr>
                  <th width="3%">&nbsp;</th>
				  <th width="7%">&nbsp;</th>
                  <th width="18%">Group Name</th>
                  <th width="18%">Members</th>
                  <th width="18%">Location</th>
                  <th width="18%">Edit Group</th>
                  <th width="18%">Delete Group</th>
                </tr>
                <tr>
                  <td><input name="" type="checkbox" value=""></td>
                  <td height="40"><img src="ui/images/group_photo.png" alt="User Photo"></td>
                  <td>Group  Name</td>
                  <td>123</td>
                  <td>Location</td>
                  <td ><a href="#" class="edit_blue">Edit</a></td>
                  <td><a href="#edit_delete_groups" class="delete_blue inline">Delete</a></td>
                </tr>
                <tr>
                  <td><input name="" type="checkbox" value=""></td>
                  <td height="40"><img src="ui/images/group_photo.png" alt="User Photo"></td>
                  <td>Group  Name</td>
                  <td>123</td>
                  <td>Location</td>
                  <td ><a href="#" class="edit_blue">Edit</a></td>
                  <td><a href="#edit_delete_groups" class="delete_blue inline">Delete</a></td>
                </tr>
                <tr>
                  <td><input name="" type="checkbox" value=""></td>
                  <td height="40"><img src="ui/images/group_photo.png" alt="User Photo"></td>
                  <td>Group  Name</td>
                  <td>123</td>
                  <td>Location</td>
                  <td ><a href="#" class="edit_blue">Edit</a></td>
                  <td><a href="#edit_delete_groups" class="delete_blue inline">Delete</a></td>
                </tr>
                <tr>
                  <td><input name="" type="checkbox" value=""></td>
                  <td height="40"><img src="ui/images/group_photo.png" alt="User Photo"></td>
                  <td>Group  Name</td>
                  <td>123</td>
                  <td>Location</td>
                  <td ><a href="#" class="edit_blue">Edit</a></td>
                  <td><a href="#edit_delete_groups" class="delete_blue inline">Delete</a></td>
                </tr>
                <tr>
                  <td><input name="" type="checkbox" value=""></td>
                  <td height="40"><img src="ui/images/group_photo.png" alt="User Photo"></td>
                  <td>Group  Name</td>
                  <td>123</td>
                  <td>Location</td>
                  <td ><a href="#" class="edit_blue">Edit</a></td>
                  <td><a href="#edit_delete_groups" class="delete_blue inline">Delete</a></td>
                </tr>
                <tr>
                  <td><input name="" type="checkbox" value=""></td>
                  <td height="40"><img src="ui/images/group_photo.png" alt="User Photo"></td>
                  <td>Group  Name</td>
                  <td>123</td>
                  <td>Location</td>
                  <td ><a href="#" class="edit_blue">Edit</a></td>
                  <td><a href="#edit_delete_groups" class="delete_blue inline">Delete</a></td>
                </tr>
                <tr>
                  <td><input name="" type="checkbox" value=""></td>
                  <td height="40"><img src="ui/images/group_photo.png" alt="User Photo"></td>
                  <td>Group  Name</td>
                  <td>133</td>
                  <td>Location</td>
                  <td ><a href="#" class="edit_blue">Edit</a></td>
                  <td><a href="#edit_delete_groups" class="delete_blue inline">Delete</a></td>
                </tr>
                <tr>
                  <td><input name="" type="checkbox" value=""></td>
                  <td height="40"><img src="ui/images/group_photo.png" alt="User Photo"></td>
                  <td>Group  Name</td>
                  <td>133</td>
                  <td>Location</td>
                  <td ><a href="#" class="edit_blue">Edit</a></td>
                  <td><a href="#edit_delete_groups" class="delete_blue inline">Delete</a></td>
                </tr>
                <tr>
                  <td><input name="" type="checkbox" value=""></td>
                  <td height="40"><img src="ui/images/group_photo.png" alt="User Photo"></td>
                  <td>Group  Name</td>
                  <td>123</td>
                  <td>Location</td>
                  <td ><a href="#" class="edit_blue">Edit</a></td>
                  <td><a href="#edit_delete_groups" class="delete_blue inline">Delete</a></td>
                </tr>
                <tr>
                  <td><input name="" type="checkbox" value=""></td>
                  <td height="40"><img src="ui/images/group_photo.png" alt="User Photo"></td>
                  <td>Group  Name</td>
                  <td>133</td>
                  <td>Location</td>
                  <td ><a href="#" class="edit_blue">Edit</a></td>
                  <td><a href="#edit_delete_groups" class="delete_blue inline">Delete</a></td>
                </tr>
                <tr>
                  <td><input name="" type="checkbox" value=""></td>
                  <td height="40"><img src="ui/images/group_photo.png" alt="User Photo"></td>
                  <td>Group  Name</td>
                  <td>133</td>
                  <td>Location</td>
                  <td ><a href="#" class="edit_blue">Edit</a></td>
                  <td><a href="#edit_delete_groups" class="delete_blue inline">Delete</a></td>
                </tr>
                <tr>
                  <td><input name="" type="checkbox" value=""></td>
                  <td height="40"><img src="ui/images/group_photo.png" alt="User Photo"></td>
                  <td>Group  Name</td>
                  <td>123</td>
                  <td>Location</td>
                  <td ><a href="#" class="edit_blue">Edit</a></td>
                  <td><a href="#edit_delete_groups" class="delete_blue inline">Delete</a></td>
                </tr>
                <tr>
                  <td><input name="" type="checkbox" value=""></td>
                  <td height="40"><img src="ui/images/group_photo.png" alt="User Photo"></td>
                  <td>Group  Name</td>
                  <td>123</td>
                  <td>Location</td>
                  <td ><a href="#" class="edit_blue">Edit</a></td>
                  <td><a href="#edit_delete_groups" class="delete_blue inline">Delete</a></td>
                </tr>
                <tr>
                  <td><input name="" type="checkbox" value=""></td>
                  <td height="40"><img src="ui/images/group_photo.png" alt="User Photo"></td>
                  <td>Group  Name</td>
                  <td>123</td>
                  <td>Location</td>
                  <td ><a href="#" class="edit_blue">Edit</a></td>
                  <td><a href="#edit_delete_groups" class="delete_blue inline">Delete</a></td>
                </tr>
              </table>
            </div>
            <!-- view_all_contacts Ends-->
            <div style="display:none;">
			<div id="#edit_delete_groups">
			<div id="close"><a href="" title="Close"></a></div>
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td width="4%" valign="top"><input name="" type="checkbox" value=""></td>
    <td width="15%" valign="top"><img src="ui/images/user_photo_80X80.png" alt="User Photo"></td>
    <td width="30%" valign="top"><table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td class="real_name">Group Name</td>
      </tr>
      <tr>
        <td height="26">Group Discription.</td>
      </tr>
      
      <tr>
        <td>Sociis ehoncus, enim ut cursus adipiscing! Ac et, ac turpis dolor, mauris? Placerat, porta ac parturient ut, dapibus augue tincidunt porttitor integer platea lorem? Purus!</td>
      </tr>
    </table></td>
    <td width="51%" valign="top" ><table width="58%" border="0" align="right" cellpadding="0" cellspacing="0">
      <tr>
        <td width="55%" height="29">&nbsp;</td>
        </tr>
      
      <tr>
        <td height="20" class="font_16px">This will Permanently delete this group. </td>
        </tr>
      <tr>
        <td>Are you sure? </td>
        </tr>
      <tr>
        <td><a href="#" title="DELETE GROUP" id="" class="button_red" style="margin:10px 0 0 0; width:160px; "> <span class="title_red">DELETE GROUP</span></a></td>
      </tr>
      
    </table></td>
  </tr>
</table>
			</div>
			</div>
			<!-- Pagination start---->
            <script type="text/javascript" src="ui/includes/pagination_contacts.js"></script>
          	<!-- Pagination ends---->
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
