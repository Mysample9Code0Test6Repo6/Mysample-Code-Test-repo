	<%@ include file="/WEB-INF/jsp/include.jsp"%>
	<div class="statusBar">
    	<div class="content">
      		<div class="left style23"><img src="../images/incyyte_sm_logo2.png" width="144" height="90" /></div>
           		<div class="left">
                   	<span class="linkSpacer"></span>
            		<span class="linkSpacer"></span><span class="linkSpacer"></span>
                  	<span class="linkSpacer"></span><span class="linkSpacer"></span>
                  	<c:url  value="/home.cyt" var="homeURL" />
                  	<a href='<c:out value="${homeURL}" />' title="Home" class="style22 link">
                  		<strong>Home</strong>
					</a>
                 	<span class="linkSpacer"></span><span class="linkSpacer"></span>
                  	<a href="#" title="Profile" class="style22 link"><strong>Profile</strong></a>
                    <span class="linkSpacer"></span><span class="linkSpacer"></span>
                   	<a href="#" title="Friends" class="style22 link"><strong>Friends</strong></a>                            
                   	<span class="linkSpacer"></span><span class="linkSpacer"></span>
                   	<a href="#" title="Groups" class="style22 link"><strong>Groups</strong></a>                            
                   	<span class="linkSpacer"></span><span class="linkSpacer"></span>
                    <a href="#" title="Email" class="style22 link"><strong>Email</strong></a>                            
                    <span class="linkSpacer"></span><span class="linkSpacer"></span>
                    <a href="#" title="Account" class="style22 link"><strong>Account</strong></a>                            
                    <span class="linkSpacer"></span><span class="linkSpacer"></span>
                    <a href="#" title="inCyyte" class="style22 link"><strong>inCyyte</strong></a>  
                    <span class="linkSpacer"></span><span class="linkSpacer"></span>
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

