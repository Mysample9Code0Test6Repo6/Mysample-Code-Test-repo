<%@ include file="/WEB-INF/jsp/common/include.jsp" %>
<!--- Modal ----->
<link rel="stylesheet" href="ui/modal/colorbox.css">
<script src="ui/modal/colorbox/jquery.colorbox.js"></script>
<script>
			$(document).ready(function(){
				var path = getContextPath();
				
				$('#login').click(function(){
					//alert('path: '+ path)
					window.location = path+"/login.cyt";	
			    });

				function getContextPath() {
					return "<%=request.getContextPath()%>";
				}

				$('#logo_inner').click(function(){
					//alert('path: '+ path);
					window.location = path+"/welcome.cyt";	
			    });

			});
		</script>
<!--- Mddal--------------->
    <!--header -->
    <div class="header36">
      <div id="headerinner">
        <div id="logocontainer"><a href="#" id="logo_inner">inCyyte</a></div>
        <div id="header_topnav_inner">
          <ul id="header_topmenu">
            <li><a href="#" ></a></li>
            <li><a href="#" ></a></li>
            <li><a href="#" ></a></li>
            <li class="last"><a href="#" ></a></li>
            <li class="last">
              <div id="usernameContainer"><span>Already have an account?</span><em></em>
                <div style="clear:both"></div>
              </div>
            </li>
            <!-- li class="last"><a href="#create_loginForm" class="inline">Log In</a></li-->
            <li class="last"><a href="#" id="login">Log In</a></li>
          </ul>
        </div>
      </div>
    </div>
    <!--header end-->
