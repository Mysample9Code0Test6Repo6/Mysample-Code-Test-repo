<%@ include file="/WEB-INF/jsp/common/include.jsp" %>
<!--- Modal ----->
<link rel="stylesheet" href="ui/modal/colorbox.css">
<script src="ui/modal/colorbox/jquery.colorbox.js"></script>
<script>
			$(document).ready(function(){
				
				$('#signupNew').click(function(){
					//alert('testing...');
					window.location = "./createAcct.cyt";	
			    });

				function getContextPath() {
					return "<%=request.getContextPath()%>";
				}

				$('#logo_inner').click(function(){
					path = getContextPath();
					//alert('path: '+ path);
					window.location = path+"/welcome.cyt";	
			    });

				
			});
		</script>
<!--- Mddal--------------->
    <!--header -->
    <div class="header36">
      <div id="headerinner">
        <div id="logocontainer"><a href="welcome.cyt" id="logo_inner">inCyyte</a></div>
        <div id="header_topnav_inner">
          <ul id="header_topmenu">
            <li><a href="#" ></a></li>
            <li><a href="#" ></a></li>
            <li><a href="#" ></a></li>
            <li class="last"><a href="#" ></a></li>
            <li class="last">
              <div id="usernameContainer"><span>do not have an account?</span><em></em>
                <div style="clear:both"></div>
              </div>
            </li>
            <li class="last"><a href="createAcct.cyt" id="signupNew">Sign up</a></li>
          </ul>
        </div>
      </div>
    </div>
    <!--header end-->
