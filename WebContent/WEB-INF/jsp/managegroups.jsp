<%@ include file="/WEB-INF/jsp/include.jsp"%>
<%@page import="com.incyyte.app.web.SessionKeys"%>
<%@page import="com.incyyte.app.domain.User"%>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>InCyyte | Home</title>
    <style type="text/css">
    </style>    
    
    <script type="text/javascript" src="js/jquery-1.5.1.min.js"></script>
    <script type="text/javascript" src="js/jquery-ui-1.8.13.custom.min.js"></script>
    <script type="text/javascript" src="js/external/jquery.bgiframe-2.1.2.js"></script>
    <script type='text/javascript' src='js/accountry.0.2/jquery.autocomplete.js'></script>
    <script type='text/javascript' src='js/home.js'></script>
    <script type="text/javascript" src="js/communicator.js"></script>
    
    <link rel="stylesheet" type="text/css" href="css/home_common.css" />
    <link rel="stylesheet" type="text/css" href="css/demos.css">
	<link rel="stylesheet" type="text/css" href="css/themes/base/jquery.ui.all.css">
    <link rel="stylesheet" type="text/css" href="css/groupList.css"/>    
    
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

<div id="container_wrapper">

	<jsp:include page="include/header.jsp" />

<!--container start-->
<div id="container">
    
    <div id="leftPane">   
  		<jsp:include page="include/dashboard.jsp" />
  	</div>
   	<div class="centerPane">        
        <div class="cpTop">                
            <div class="bgTop"> 
                <div id="communicator" class="communicator">
                            <blockquote>
                              <p align="center"><img style=" vertical-align:bottom" src="images/icons/Warning3.gif" alt="" width="16" height="16"/>  
                                  <span id="communicatorMessage"></span>
                              </p>
                            </blockquote>
                </div>            
            </div>          
          	
            <div class="bgBody">
            
            </div>
            <div class="bgBottom">
            	
             </div>
        </div>
        <div class="cpView">    
        		<center>
        		<br/><br/><br/><br/><br/><br/>
        		<font face="times, serif" size="3"><u> <a href='<c:url value="/addgroup.cyt"/>'>Create Group</a></u></font><br/><br/>
        		<font face="times, serif" size="3"><u><a href='<c:url value="/usergroups.cyt"/>'>Manage Groups</a></u></font><br/><br/>
        		<font face="times, serif" size="3"><u><a href='<c:url value="/searchgroups.cyt"/>'>Search Groups</a></u></font><br/><br/><br/></center>
        </div>
        
        <BR>
        <div class="cpTop">                
            <div class="bgTop"> 
                <div id="communicator" class="communicator">
                            <blockquote>
                              <p class="style24">Post a comment on this topic below.</p>
                            </blockquote>
        		</div>            
            </div>
            <div class="bgBody">
                      <p class="panelFont">
                       &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <textarea name="textfield" cols="50" rows="2" style="overflow:hidden; width: 400px; font-size: 15px;
                         vertical-align: middle" class="name" id="question" title="Post your coment here" ></textarea>
                       <input name="send2" type="button" value="Post" style="width:65px; height:40px; vertical-align:middle" id="send2"/>
              </p>            
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
	                 		<div class="card">
	                 			<div class="icon">
	                 				<img src="photo.cyt" width="76px" height="72px"/>
	                 			</div>
	                 			<div class="label">
	                 				<c:choose>
	                 					<c:when test="${not empty user.firstname}">
	                 						${user.firstname} &nbsp;&nbsp; ${user.lastname}
	                 					</c:when>
	                 					<c:otherwise>
	                 						${user.username}
	                 					</c:otherwise>
	                 				</c:choose>                                	
	                 			</div>
	                 			<div class="editLink">
                                	<span class="panelLink" title="edit profile">edit</span>
	                 			</div>
                                
	                 			<br class="clear"/>
	                 		</div>
	                 		<div class="details">
	                 		I've spent the last 6 months plus developing InCyyte with my close friends. We hoped that we could add something to the huge networking phenomenon that has swept the planet. Do you think the InCyyte can help the world? <span class="panelLink" title="Vote"><b>Vote here </b></span> 
	                 		</div>
	                 	</div>   			
        			</div>

        </div>
        <div class="portletBottom"> </div>    
        <BR/>
        <!--my incyytes-->
        <div class="portletTop" title="You sent out these InCyytes.">        
   			<div class="label">
   				
			</div>
    		<div class="control"></div>
    		<br class="clear"/>
     	</div>
        <div class="portletBody">        
        			<div class="content">
	                 	<div id="noticeboardPortlet">
	                 		<div class="card">                                
	                 		</div>
	                 		<div class="details">
                             
	                 		</div>
	                 	</div>   			
        			</div>

        </div>
        <div class="portletBottom"> </div>    
        <BR/>
        <!--incyyte links-->
        <div class="portletTop" title="You participated to responding to these InCyytes.">        
   			<div class="label">
   				
			</div>
    		<div class="control"></div>
    		<br class="clear"/>
     	</div>
        <div class="portletBody">        
                <div class="content">
                    <div id="noticeboardPortlet">
                        <div class="card">                                
                        </div>
                        <div class="details">
                        	
                        </div>
                    </div>   			
                </div>
        </div>
        <div class="portletBottom"> </div>    

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

<div class="demo">
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

    <div id="grpList-dialog-form" title="Your List of Groups">    
        <form>
        <fieldset>
            <table id="groupList" border="0" cellpadding="0" cellspacing="0">
                <tbody>                        
                    <tr>
                        <td></td>
                        <td><input name="grp1" id="grp1" type="checkbox" value="Work Coleagues" /></td>
                        <td><span class="groupListFont">Work Colleagues </span></td>
                        <td></td>
                    </tr>							       		
                    <tr>
                        <td></td>
                        <td><input name="grp2" id="grp2" type="checkbox" value="Arsenal Football Team" /></td>
                        <td><span class="groupListFont">Arsenal Football Team</span></td>
                        <td></td>
                    </tr>						       		
                    <tr>
                        <td></td>
                        <td><input name="grp3" id="grp3" type="checkbox" value="Media Team Church Members" /></td>
                        <td><span class="groupListFont">Media Team Church Members</span></td>
                        <td></td>
                    </tr>						       		
                    <tr>
                        <td></td>
                        <td><input name="grp4" id="grp4" type="checkbox" value="School Friends" /></td>
                        <td><span class="groupListFont">School Friends</span></td>
                        <td></td>
                    </tr>						       		
                </tbody> 
                 <tfoot>
                    <tr>
                        <td></td>
                        <td></td>
                        <td align="right"> 
                        <img src="images/addNew.png" width="18" height="18" /> 
                        <span class="panelLink" style="display:inline-block; vertical-align:top;">create new group </span>	
                        </td>
                        <td></td>
                    </tr>
                  </tfoot>  				     					     
            </table>
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

