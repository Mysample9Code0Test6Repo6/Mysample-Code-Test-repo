
<script type="text/javascript">

         function setPopUp(sn)
         {
        	// alert(sn) ;
        	
        	if(sn =='twitter')
          		window.open("socialauth.do?id="+sn+"", "loginWindow", "location=1,status=1,scrollbars=1, width=400,height=400");
        	else
                window.open("socialauth.do?id="+sn+"", "loginWindow", "location=1,status=1,scrollbars=1, width=600,height=600");
        	
         }         
 </script>

    <!--header -->
      <div id="headerinner">
            <div id="logocontainer"><a href="./welcome.cyt" id="logo">inCyyte</a></div>
        <div id="info" >
          <!-- Log in with faccebook and Twitter Starts Here -->
          <div id="loginContainer"> <a href="#" id="loginButton"><span>LOG IN WITH <img src="ui/images/icon_small.png"></span><em></em></a>
            <div style="clear:both"></div>
            <div id="loginBox" >
           
                <fieldset id="body">
                <p class="login_text">Log In</p>
                <span id="notice" style="font:14px/20px 'bariol_regularregular', 'ie8_bariol_regular';color: #C2002D;">Sorry, this service is currently not available.</span>
                <button type="button" onclick="setPopUp('facebook');" >         
                <span><img src="ui/images/facebook.png" align="absmiddle"></span>LOG IN WITH FACEBOOK</button>
                <button type="button"     onclick="setPopUp('twitter');" >   
                <span><img src="ui/images/twitter.png" align="absmiddle"></span>LOG IN WITH TWITTER </button>
                </fieldset>
            </div>
          </div>
          <!-- Log in with faccebook and Twitter Ends Here -->
          <!-- Log in starts Here -->
          <div id="loginContainer"> <a href="#" id="loginButton1"><span>LOG IN</span><em></em></a>
            <div style="clear:both"></div>
            <div id="loginBox1">
        
            </div>
          </div>
           