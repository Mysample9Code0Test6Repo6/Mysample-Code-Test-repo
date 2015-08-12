	<%@ include file="/WEB-INF/jsp/include.jsp"%>
	<div class="statusBar">
    	<div class="content">
      		<div class="left style23"><img src="images/incyyte_sm_logo2.png" width="144" height="90" /></div>
           		<div class="left">
                   	<span class="linkSpacer"></span>
            		<span class="linkSpacer"></span><span class="linkSpacer"></span>
                  	<span class="linkSpacer"></span><span class="linkSpacer"></span>
                    <a href="./home.cyt"  class="link" id="login-user2" title="Home"><strong>Home</strong></span>
                 	<span class="linkSpacer"></span><span class="linkSpacer"></span>
                  	<a href="./editProfile.cyt" title="Profile" class="style22 link"><strong>Profile</strong></a>
                    <span class="linkSpacer"></span><span class="linkSpacer"></span>
                    <a href="./contactsHome.cyt" title="Profile" class="style22 link"><strong>My Contacts</strong></a>
                    <span class="linkSpacer"></span><span class="linkSpacer"></span>
                   	<a href="#" title="Friends" class="style22 link"><strong>Friends</strong></a>                            
                   	<span class="linkSpacer"></span><span class="linkSpacer"></span>
                   	<a href="./grouphome.cyt" title="Groups" class="style22 link"><strong>Groups</strong></a>                            
                   	<span class="linkSpacer"></span><span class="linkSpacer"></span>
                    <a href="#" title="Email" class="style22 link"><strong>Email</strong></a>                            
                    <span class="linkSpacer"></span><span class="linkSpacer"></span>
                    <a href="#" title="Account" class="style22 link"><strong>Account</strong></a>                            
                    <span class="linkSpacer"></span><span class="linkSpacer"></span>
                    <a href="#" title="inCyyte" class="style22 link"><strong>inCyyte</strong></a> 
                    <span class="linkSpacer"></span><span class="linkSpacer"></span>
                    <a href="./questions.cyt" title="Qestions" class="style22 link"><strong>Questions</strong></a>
                    <span class="linkSpacer"></span><span class="linkSpacer"></span>
                    <c:url  value="/admin/admin.cyt" var="adminURL" />
                    <a href='<c:out value="${adminURL}" />' title="Admin" class="style22 link"><strong>Admin</strong></a>                   
               	</div>
                <div class="right style23">
                	<c:url  value="/logout.cyt" var="logoutURL" />
                	<a href='<c:out value="${logoutURL}" />' title="Logout" class="style22 link"><strong>Logout</strong></a>                    
                </div>
        </div>
	</div> 

