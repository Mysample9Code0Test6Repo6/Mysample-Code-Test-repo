<%@ include file="/WEB-INF/jsp/include.jsp"%>
<%@page import="com.incyyte.app.web.SessionKeys"%>
<%@page import="com.incyyte.app.domain.User"%>


<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>InCyyte | Admin</title>
    <style type="text/css">
    </style>    
    
    <script type="text/javascript" src="../js/jquery-1.5.1.min.js"></script>
    <script type="text/javascript" src="../js/jquery-ui-1.8.13.custom.min.js"></script>
    <script type="text/javascript" src="../js/external/jquery.bgiframe-2.1.2.js"></script>
    <script type='text/javascript' src='../js/accountry.0.2/jquery.autocomplete.js'></script>
    <script type='text/javascript' src='../js/admin.js'></script>
    <script type="text/javascript" src="../js/communicator.js"></script>
    
    <link rel="stylesheet" type="text/css" href="../css/home_common.css" />
    <link rel="stylesheet" type="text/css" href="../css/demos.css">
    <link rel="stylesheet" type="text/css" href="../css/displaytag-table.css">
	<link rel="stylesheet" type="text/css" href="../css/themes/base/jquery.ui.all.css">
    <link rel="stylesheet" type="text/css" href="../css/groupList.css"/>    
    
    <style>
		fieldset { padding:0; border:0; }
		h1 { font-size: 1.2em; margin: .6em 0; }
		div#users-contain { width: 350px; margin: 20px 0; }
		div#users-contain table { margin: 1em 0; border-collapse: collapse; width: 100%; }
		div#users-contain table td, div#users-contain table th { border: 1px solid #eee; padding: .6em 10px; text-align: left; }
		.ui-dialog .ui-state-error { padding: .3em; }
		.style22 {color: #9999CC}
		.style23 {margin-left: 15px}
		.style23 {margin-right: 30px}

	</style>
	
</head>
<body>

<% User user = (User)request.getSession().getAttribute(SessionKeys.LOGIN_USER); %>

<div id="container_wrapper">

	<jsp:include page="include/adminheader.jsp" />

	<!--container start-->
    <div id="container">
    
 		<div id="leftPane">   
       		<div class="portletLeftTop"></div>
			<div class="portletLeftBodyW" id="portletLeftBody">
				<div class="style24 panelLink" id="portletLinks2"><strong> Menus</strong></div><BR/>    
				<div id="portletLinks" class="panelLink">		
					Manage Incyyte
				</div><br/>
				<div id="portletLinks" class="panelLink"> 		
				    Manage User
				</div><br/>
				<div id="portletLinks" class="panelLink">		
				    Manage Role
				</div><br/>
				<div id="portletLinks" class="panelLink"> 		
				   	Manage something	    	
				</div><br/>    
			</div>
			<div class="portletLeftBodyG"></div>
			<div class="portletLeftBottom"></div>       		
      	</div>
      	
      	<div class="centerPane">        
      		<div class="cpTop">                
            	<div class="bgTop"> 
                	<div id="communicator" class="communicator">
                    	<blockquote>
                        	<p align="center"><img style="vertical-align:bottom" src="images/icons/Warning2.gif" alt="" width="16" height="16"/>  
                            	<span id="communicatorMessage">send your instant question...</span>
                           	</p>
                       	</blockquote>
                	</div>            
            	</div>          
          	
            	<div class="bgBody">
            		<form:form id="sendInCyyteForm" commandName="sendInCyyteForm"  method="post">
	            		<form:hidden path="anonymity" id="hid_anonymity" />
	            		<form:hidden path="answerArr" id="hid_answerArr" />
	            		<form:hidden path="category" id="hid_category" />
	            		<form:hidden path="eLink"  id="hid_eLink"/>
	            		<form:hidden path="uploadedFile" id="hid_uploadfile" /> 
	            		<form:hidden path="emailArr" id="hid_emailArr" />
	            		<form:hidden path="grpType" id="hid_grpType"/>
	            		<form:hidden path="grpName" id="hid_grpName"/>
	            	
	                	<p class="panelFont"> General Question :
	                    	<form:textarea path="incyyte" cols="50" rows="2" style="overflow:hidden; width: 400px; font-size: 15px; vertical-align: middle" cssClass="name" id="question" title="Type your question here" />
	                     
	                      	<input name="send_gen" type="button" value="Send" style="width:70px; height:40px; vertical-align:middle" id="send_gen"/>
	                 	</p>
                 	</form:form>            
            		<div class="quControlBar"> 
                  		<span class="panelLink" title="select a category" id="categoryLabel">category</span>
						<span class="linkSpacer"></span> | <span class="linkSpacer"></span>                 
                        <span class="panelLink" title="change answer options" id="optionsLabel">answer options</span>
						<span class="linkSpacer"></span> | <span class="linkSpacer"></span>
                        <span class="panelLink" title="add a picture, file or reference link" id="linkFileLabel">add file or link</span>
                	</div>
            	</div>
            	
            	<div class="bgBottom"> </div>
        	</div> 
        	<br>   
        	<div class="cpTop">                
            	<div class="bgTop">                 	            
            	</div>          
          	            	
            	<div class="bgBody" align="center">
            	
            		<form:form id="searchIncyyteForm" commandName="searchIncyyteForm" action="searchIncyyte.cyt" method="post">
            			Select Created by:
            			<form:select path="createdBy" id="createdBy" cssClass="inputnowidth" title="Created By">
							<form:option value="ADMIN" label="ADMIN" />   
							<form:option value="USER" label="USER" />            
						</form:select> 
            			&nbsp;&nbsp;
            			
            			Select Question Type:
            			<form:select path="quesType" id="quesType" cssClass="inputnowidth" title="Question Type">
							<form:option value="Q" label="Q" />   
							<form:option value="P" label="P" /> 
							<form:option value="G" label="G" />            
						</form:select> 
	                   
	                    &nbsp;&nbsp;
	               		<input name="searchIncyyte" type="submit" value="Search Incyyte"  id="searchIncyyte"/>
	            	</form:form>
	            	<br/>
	            	<div class="quControlBar" align="left">
            			<form:form id="editIncyyteForm" commandName="editIncyyteForm"   method="post">            			
            				
            				<form:select path="incyyte" id="selIncyyte" cssClass="inputwidth" title="select Incyyte">
            					<form:option value="0" label="Select" />             
								<form:options items="${incyyteList}" itemValue="id" itemLabel="incyyte" />          
							</form:select> 
     					                   
	                    	&nbsp;&nbsp;
	                    	<input name="editIncyyte" type="button" value="Edit Incyyte"  id="editIncyyte"/>	                 	
                 		</form:form>
                 	</div>
            	</div>
            	
            	<div class="bgBottom"> </div>
        	</div>  
        	
        	<br>   
        	<div class="cpTop">                
            	<div class="bgTop">                 	            
            	</div>          
          	            	
            	<div class="bgBody" align="center">
            		<form:form id="searchUserForm" commandName="searchUserForm"  action="searchUser.cyt" method="post">            			
            			Enter User Name:
	                    <form:input path="username" cssClass="name" id="username" title="User Name" />
	                    &nbsp;&nbsp;
	                    Enter Email:
	                    <form:input path="user_email" cssClass="name" id="user_email" title="Email" />
	                    &nbsp;&nbsp;
	                    <input name="searchUser" type="submit" value="Search User"  id="searchUser"/>	                 	
                 	</form:form>
                 	<br/>
                 	
                 	<div class="quControlBar" align="left">
	            		<display:table uid="userlist" name="sessionScope.userList" defaultsort="1" defaultorder="ascending" 
	            			pagesize="10" decorator="com.incyyte.app.web.decorator.UserDisplayDecorator">     
	            			<display:column property="id" sortable="true" title="User ID" maxLength="25" />     
	            			<display:column property="username" sortable="true" title="Username" maxLength="25" /> 
	            			<display:column property="email" sortable="true" title="Email Address" maxLength="25" /> 
	            			<display:column  title="Action" >
	            				<input type="button" value="Edit" />
	            			</display:column>
	            			<display:setProperty name="basic.empty.showtable" value="true"/>
	            			<div><display:setProperty name="basic.show.header" value="true"/></div>     
	            			<display:setProperty name="paging.banner.group_size" value="10" />     
	            			<display:setProperty name="paging.banner.item_name" value="user" />     
	            			<display:setProperty name="paging.banner.item_names" value="users" />
	            			<display:setProperty name="paging.banner.onepage" value="<span>&nbsp;</span>" />
	            		</display:table>             
                        </div>
                 	
            	</div>
            	
            	<div class="bgBottom"> </div>
        	</div>      	           	    	
                
      	</div>
      	
      	<div class="rightPane"> 
      		<!--profile-->
        	<div class="portletTop" title="Edit My profile.">        
   				<div class="label">
   					<a href="#">
   						My Profile
					</a>
				</div>
    			<div class="control"></div>
    			<br class="clear"/>
     		</div>
        	<div class="portletBody">        
        		<div class="content">
	           		<div id="profilePortlet">
	              		
	                </div>   			
        		</div>
        	</div>
	        <div class="portletBottom"> </div>    
	        <BR/>
	        <!--my incyytes-->
	        
	        
  		</div>
        
		<div id="footer">  
	    	<div id="footer-bottom">
	 
				<p class="bottom-right"><a href="index.html">Home</a> |		<a href="privacySettings.html">Privacy Settings</a> |
				<a href="anonymityRules.html">Anonymity Rule</a> |
	      		<strong><a href="#top">Back to Top</a></strong>   </p>
				<p class="bottom-left">
					&copy; 2011 <strong>Copyright inCyyte.com</strong>&nbsp; &nbsp; &nbsp;
				</p>
	    			<!-- /footer-bottom-->
			</div>
   		</div>
	</div>
    <!--container end-->
    
</div>
<div class="demo">  
    <div id="category-dialog-form" title="Please Select a category">    
        <form>
        <fieldset>
            <select name="category" id="category" class="ui-corner-all dialogDD ui-widget-content" title="category">
            <option selected="selected" value="select">--Select--</option>
            <option>Religion</option>
            <option>Relationships</option>
            <option>Politics</option>
            <option>Sports</option>
            <option>Current Affairs</option>
            <option>Controversial</option>
            <option>Shopping</option>
            <option>Entertainment</option>
            <option>Community</option>
            <option>Business</option>
            <option>Music</option>
            <option>Miscellaneous</option>
          </select>
        </fieldset>
        </form>
    </div>
    
    <div id="options-dialog-form" title="Your answer options">    
        <form>
        <fieldset>
            <input type="text" name="opt1" id="opt1" class="dialogText ui-widget-content ui-corner-all" value="Yes"/>
            <input type="text" name="opt2" id="opt2" class="dialogText ui-widget-content ui-corner-all" value="No"/>
            <input type="text" name="opt3" id="opt3" class="dialogText ui-widget-content ui-corner-all" />
            <input type="text" name="opt4" id="opt4" class="dialogText ui-widget-content ui-corner-all" />
            <input type="text" name="opt5" id="opt5" class="dialogText ui-widget-content ui-corner-all" />        
        </fieldset>
        </form>
    </div>

    <div id="linkfile-dialog-form" title="Attach a file or enter a link">    
        <form>
        <fieldset>
        <input name="eLink" type="text" id="eLink" value="http://..." size="30"  title="Enter a link related to your question"  class="dialogText ui-widget-content ui-corner-all"/>
        <input name="uploadfile" type="file" id="uploadfile" value="upload file" size="30"  title="Add a picture or document related to your question"  class="dialogText ui-widget-content ui-corner-all"/>
        </fieldset>
        </form>
    </div>
  
</div>

<script type="text/javascript" src="ui/js/googleanalytics.js"></script>
</body>
</html>

