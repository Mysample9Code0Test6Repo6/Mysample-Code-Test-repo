<%@ include file="/WEB-INF/jsp/include.jsp"%>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>InCyyte | Access Page</title>
    
    
     	<script type="text/javascript" src="js/jquery-1.5.1.min.js"></script>
        <script type="text/javascript" src="js/jquery-ui-1.8.13.custom.min.js"></script>
        <script type='text/javascript' src="js/postscript.js"></script>
        
	    <script type="text/javascript" src="js/external/jquery.bgiframe-2.1.2.js"></script>
        <script type="text/javascript" src="js/communicator.js"></script>
        <script type="text/javascript" src="js/jquery.form.js"></script>  
        
        <link rel="stylesheet" type="text/css" href="css/themes/base/jquery.ui.all.css"/>
        <link rel="stylesheet" type="text/css" href="css/postscript.css" /> 
</head>
<body>


   	<div id="login-box" class="login-popup">   		
		<form:form  id="accessform"  commandName="accessform" method="post" cssClass="signin">
			<fieldset class="textbox">
				<label class="username">
			       		<span>Access Code</span>
			            <form:input path="access_code" id="access_code"  title="Access Code"/>
			    </label>
			    <div id="communicator" class="communicator">
					<p><img src="images/icons/Warning2.gif" alt="" width="16" height="16"/>  
				   		<span id="communicatorMessage">Please fix the following errors before proceeding.</span>
					</p>
				</div>       
			</fieldset>			          
		</form:form>
	</div>              	   
<script type="text/javascript" src="ui/js/googleanalytics.js"></script>          	
</body>
</html>
