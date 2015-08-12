<%@ include file="/WEB-INF/jsp/include.jsp"%>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>InCyyte | Login</title>
    <style type="text/css">
    </style>
    
    <script type="text/javascript" src="js/jquery-1.5.1.min.js"></script>
    <script type="text/javascript" src="js/jquery-ui-1.8.13.custom.min.js"></script>
    <script type="text/javascript" src="js/external/jquery.bgiframe-2.1.2.js"></script>
    <script type="text/javascript" src="js/communicator.js"></script>
    <script type="text/javascript" src="js/welcome.js"></script>
    <script type='text/javascript' src='js/accountry.0.2/jquery.autocomplete.js'></script>
    <script type='text/javascript' src='js/accountry.0.2/countries.en.js'></script>
    <script type="text/javascript" src="js/jquery.form.js"></script>    
     
    <link rel="stylesheet" type="text/css" href="css/accountry.0.2/jquery.autocomplete.css" />
    <link rel="stylesheet" type="text/css" href="css/accountry.0.2/jquery.autocomplete.country.css" />
    <link rel="stylesheet" type="text/css" href="css/common.css" />
    <link rel="stylesheet" type="text/css" href="css/vote_result.css" />
    <link rel="stylesheet" type="text/css" href="css/themes/ui-lightness/jquery.ui.all.css" />
    <link rel="stylesheet" type="text/css" href="css/demos.css" />
    
    <style>
		fieldset { padding:0; border:0; margin-top:25px; }
		h1 { font-size: 1.2em; margin: .6em 0; }
		div#users-contain { width: 350px; margin: 20px 0; }
		div#users-contain table { margin: 1em 0; border-collapse: collapse; width: 100%; }
		div#users-contain table td, div#users-contain table th { border: 1px solid #eee; padding: .6em 10px; text-align: left; }
		.ui-dialog .ui-state-error { padding: .3em; }
		.validateTips_df { border: 1px solid transparent; padding: 0.3em;}
		.validateTips_daf { border: 1px solid transparent; padding: 0.3em;}
		.validateTips_ldf { border: 1px solid transparent; padding: 0.3em;}
		.validateTips_adf { border: 1px solid transparent; padding: 0.3em;}
		.validateTips_fdf { border: 1px solid transparent; padding: 0.3em;}
	</style>
    
    
	<style type="text/css">
	<!--
	
	.style9 {text-align: left;	padding-left: 100px;}
	.style11 {
		font-family: Calibri;
		font-size: 16px;
		color: #33527F;
	}
	.style18 {font-size: 14px}
	.style20 {font-size: 14px; color: #2D4268; }	
	
	-->
	</style>
</head>
<body>

<div id="container_wrapper">
            <div class="statusBar">
                <div class="content">
                    <div class="left"><img src="images/incyyte_sm_logo2.png" width="144" height="90" /></div>
                    <div class="right">
                            <span class="linkSpacer"></span>
                      <span id="login-user2" class="link" title="Home">Home</span>
                            <span class="linkSpacer"></span>
                      <a href="http://www.incyyte.co.uk/results" target="_blank" class="link" title="View Results">View Results</a>
                            <span class="linkSpacer"></span>
                      <a href="http://www.incyyte.co.uk/about" target="_blank" class="link" title="New User - Sign up Now">Sign up</a>                    
                            <span class="linkSpacer"></span>
                            <span class="linkSpacer"></span>
                            <span class="linkSpacer"></span>
                            <span class="linkSpacer"></span>
                      <a href="http://www.incyyte.co.uk/about" target="_blank" class="link" title="Sign In">Sign in</a>                    </div>
              </div>
            </div> 
  <div class="pageHighlight">
    <!-- Div for graphical highlight only. -->
  </div>
<!--container start-->
    <div id="container">
        <div id="top">        
          <!-- <div>    <p id="strapline">Instant poll results when you inCyyte friends, workgroup and even your community!.</p></div>-->       
  		</div> 

		<div class="header">
		  <p>Vote Result</p>
		</div>
		
        
        <div id="content">        
          <!-- MAIN Panel. -->
            	
          	<div class="mainPanel">
              <div class="mainPanelTop">
                <div class="cap"></div>
              </div>
              <div class="mainPanelBody">
              	<!-- MAIN Panel contents. -->
              	<img src="images/line.png" />
              	<div class="communicator">
                        <blockquote>
                          <p align="center"><img src="images/chart-icon.png" class="communicatorIcon" alt="Communicator indiction icon."/>  
                              <span><c:out value="${voteMsg}"/></span>
                          </p>
                        </blockquote>                        
          		</div>
				<div class="cpView">         
		            <div class="question"><a href="login.cyt">Click here to view the result</a></div>
		            <%-- <div class="pieChart">
		            	<jsp:include page="include/viewChart.jsp" /> 
		            </div> --%>
		        </div>    
		        <div class="cap">
                  	<!-- Panel contents. -->
                  		<form:form  id="createInCyyteForm" name="inCyyteForm"  commandName="inCyyteForm" method="post" onsubmit="if(validatedQ()) {return true;} else{ return false;}" >
			              	<center>
			                    <div class="contentWrap">
			                      <br/><br/>
			                      <p class="panelFont"> Ask Your Question :
			                      	<form:textarea path="incyyte" cols="50" rows="2" style="overflow:hidden; width: 400px; font-size: 15px; vertical-align: middle" cssClass="name" id="question" title="Type your question here" />
			                      	<input name="enter" type="submit" value="Start" style="width:70px; height:40px; vertical-align:middle" id="enterQuestion"/>			                      	
			                      </p>
			                      <div style="clear: both;"></div>
			                    </div>
		                  	</center>
		            	</form:form>
                  	<!-- End panel contents. -->
                </div>       
          		<!-- End MAIN panel contents. -->
              </div>
              <div class="mainPanelBottom">
                <div class="cap"></div>
              </div>
            </div>
          	
            <p>&nbsp;</p>
            <p>&nbsp;</p>
            <p>&nbsp;</p>
            <p>&nbsp;</p>
            <p>&nbsp;</p>
            <p>&nbsp;</p>
            <p>&nbsp;</p>
            <p>&nbsp;</p>
            <p>&nbsp;</p>
            <p>&nbsp;</p>
            <p>&nbsp;</p>
            <p>&nbsp;</p>
            <p>&nbsp;</p>
            <p>&nbsp;</p>
            <p>&nbsp;</p>
            <p>&nbsp;</p>
            <p>&nbsp;</p>
            <p>&nbsp;</p>
          <p>&nbsp;  
          </p>
        </div>
        
        <div id="footer">  
               <div id="footer-bottom">
 
					<p class="bottom-right"><a href="index.html">Home</a> |		<a href="privacySettings.html">Privacy Settings</a> |
		<a href="anonymityRules.html">Anonymity Rule</a> |
      <strong><a href="#top">Back to Top</a></strong>   </p>
	<p class="bottom-left">
		&copy; 2011 <strong>Copyright inCyyte.com</strong>&nbsp; &nbsp; &nbsp;</p>
    <!-- /footer-bottom-->
			</div>
      </div>
</div>
    <!--container end-->
    
</div>

<script type="text/javascript" src="ui/js/googleanalytics.js"></script>
</body>
</html>
