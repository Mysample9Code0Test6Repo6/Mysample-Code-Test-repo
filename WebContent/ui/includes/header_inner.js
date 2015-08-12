

document.write("<div id='headerinner'>");
        document.write("<div id='logocontainer'><a href='#' id='logo_inner'>inCyyte</a></div>");
        document.write("<div id='header_topnav_inner'>");
         document.write(" <ul id='header_topmenu'>");
           document.write(" <li><a href='#' >Home</a></li>");
           document.write(" <li><a href='#' >Messages</a></li>");
           document.write(" <li><a href='#' >Contacts</a></li>");
          document.write("  <li class='last'><a href='#' >Groups</a></li>");
           document.write(" <li style='padding-right:14px;'>");
            document.write("  <form id='searchform_top'>");
             document.write("   <input type='text' name='top_search' id='top_search'  placeholder='Search for people, questions etc.' onFocus='this.placeholder = ''' onBlur='this.placeholder = 'Search for people, questions etc.''>");
                document.write("<input type='submit' id='searchSubmit_top' value='' />");
             document.write(" </form>");
           document.write(" </li>");
            <!-- User settings starts Here -->
           document.write(" <li id='usernameContainer' class='last'> <a href='#' id='top_userName'><span>Settings</span><em></em></a>");
              <!--<div style='clear:both'></div>-->
               //document.write(" <div id='userBox'>");
               // document.write("  <div id='userForm' >");
               //   document.write("  <p class='login_text01'>You are in the N6 InCyyte Region <br>");
               //   document.write("    <span >Rating: Influential </span>More Ration Details<br>");
               ///  document.write("   <div class='hline'></div>");
               //  document.write("   <a href='#'>My Account</a><br>");
               //   document.write("  <a href='#'>Edit Profile</a><br>");
               //  document.write("   <a href='#'>Edit Settings</a>");
               // document.write("    </p>");
              // document.write("   </div>");
           // document.write("    </div>-->");
            document.write(" </li>");
            <!-- User User settings Ends Here -->
            document.write(" <li class='last'><a href='#' >Log Out</a></li>");
           document.write("</ul>");
        document.write(" </div>");
		<!-- Welcome User Start---->
		 document.write("<div id='welcome_user'>");
         document.write("  <div id='userphoto'><img src='../ui/images/user_photo.png'></div>");
         document.write("  <p class='username'>Welcome Julian. <span>You are in the N6 region.</span></p>");
          document.write(" <p class='view_polls'><a href='#'>Click here to view polls in your region.</a></p>");
        document.write(" </div>");
		<!-- Welcome User End---->
      document.write(" </div>");