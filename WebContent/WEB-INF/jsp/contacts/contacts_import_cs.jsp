<%@ include file="/WEB-INF/jsp/common/include.jsp" %>
<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="content-type" content="text/html; charset=UTF-8">
<link rel="shortcut icon" href="favicon.ico" />

<title>inCyyte - Import Contacts</title>
<meta charset="utf-8">

<link rel="stylesheet" href="ui/css/reset.css">
<link rel="stylesheet" href="ui/css/layout.css">
<link rel="stylesheet" href="ui/css/buttons.css"  media="screen">
<link rel="stylesheet" href="ui/css/style_login.css">
<link rel="stylesheet" href="ui/css/accordionmenu.css" type="text/css">
<link rel="stylesheet" href="ui/css/icons_sprites.css">
<link rel="stylesheet" href="fonts/fonts_stylesheet.css">
<link rel="stylesheet" href="ui/css/style_social.css">
<link rel="stylesheet" href="ui/css/ratingbar.css" type="text/css"/>
<link rel="stylesheet" href="ui/css/ui.progress-bar.css" type="text/css"/>
<link rel="stylesheet" href="ui/modal/colorbox.css">

<script src="ui/js/jquery-1.4.2.min.js"></script>
<script src="ui/js/jquery-1.7.1.js"></script>
<script src="ui/js/jquery.color.js"></script>
<script src="ui/js/search_script.js"></script>
<script src="ui/js/progress.js"></script>
<!-- Left Navigation script starts here -->
<script src="ui/js/left_menu.js"></script>
<script type="text/javascript" src="ui/js/jquery-1.8.3.min.js"></script>
<script src="ui/js/jquery_profile_rating.js" type="text/javascript"></script>  
<script src="ui/js/star_rating.js" type="text/javascript"></script>
<script type="text/javascript" src="//api.cloudsponge.com/address_books.js"></script>

<link rel="stylesheet" href="ui/js/datatable/css/jquery.dataTables.css">
<link rel="stylesheet" href="ui/js/datatable/css/dataTables.jqueryui.css">
<link rel="stylesheet" href="ui/js/datatable/css/jquery-ui.css">
<script type="text/javascript" src="ui/js/datatable/jquery-1.10.2.min.js"></script>
<script type="text/javascript" src="ui/js/datatable/dataTables.jqueryui.js"></script>
<script type="text/javascript" src="ui/js/datatable/jquery.dataTables.js"></script>


<!-- Left Navigation script ends here -->
<!--- placeholder Starts----->
<script>
    // placeholder polyfill
    $(document).ready(function(){
        function add() {
            if($(this).val() == ''){
                $(this).val($(this).attr('placeholder')).addClass('placeholder');
            }
        }

        function remove() {
            if($(this).val() == $(this).attr('placeholder')){
                $(this).val('').removeClass('placeholder');
            }
        }

        // Create a dummy element for feature detection
        if (!('placeholder' in $('<input>')[0])) {

            // Select the elements that have a placeholder attribute
            $('input[placeholder], textarea[placeholder]').blur(add).focus(remove).each(add);

            // Remove the placeholder text before the form is submitted
            $('form').submit(function(){
                $(this).find('input[placeholder], textarea[placeholder]').each(remove);
            });
        }
         

    });
    
    var dataSet = [];

    
  //these values will hold the owner information
   	
    var owner_email, owner_first_name, owner_last_name;
    var appendInTextarea = true;  // whether to append to existing contacts in the textarea
    var emailSep = ", ";  // email address separator to use in textarea
    function populateTextarea(contacts, source, owner) {
      var contact, name, email;//, entry;
      var entry,emails = [];
      var textarea = document.getElementById('contact_list');
      
      // preserve the original value in the textarea
      if (appendInTextarea && textarea.value.strip().length > 0) {
        emails = textarea.value.split(emailSep);
      }
     
      // format each email address properly
      for (var i = 0; i < contacts.length; i++) {
        contact = contacts[i];
        name = contact.fullName();
        email = contact.selectedEmail();
        //entry = "['"+name+"'," + "'" + email +"']";
        //entry = "['"+name+"'," + "'" + email +"']";
        entry = [name,email];
        //if (emails.indexOf(entry) < 0) {
        if (emails.indexOf(email) < 0) {
          emails.push(entry);
        }
      }
      dataSet = null;
      dataSet = emails;
      //alert("ArrayList: "+ dataSet[0]);

      // dump everything into the textarea
      textarea.value = emails.join(emailSep);
     
      //display table
      $('#sample').html( '<table cellpadding="0" cellspacing="0" border="0" class="display" id="example"></table>' );
      
      $('#example').dataTable( {
  	    "bJQueryUI": true,
          "data": dataSet,
  		"scrollY": 150,
  		"lengthMenu": [[5, 10, 50, -1], [5, 10, 50, "All"]],
          "columns": [
              { "title": "Name" },
              { "title": "Email" }            
              ]
      } );  
      
      // capture the owner information
      owner_email = (owner && owner.email && owner.email[0] && owner.email[0].address) || "";
      owner_first_name = (owner && owner.first_name) || "";
      owner_last_name = (owner && owner.last_name) || "";
    }


    var csPageOptions = {
      domain_key:"W2RRGMNMB7FJQ2VBUTD8", 
      afterSubmitContacts:populateTextarea,
      //textarea_id:'contact_list',
      //include:['email'],
      floatbox:{
    	    //controlsType:'international', // suppresses the text on the close button
    	    //controlsPos:'tr', // 'tl', 'br', 'bl' are acceptable too	    
    	    width:'400',
    	    height:'460'
    	},
    	stylesheet:'ui/css/red_address_books.css',
      // set skipSourceMenu to true when using deep links for a more consistent UX
      skipSourceMenu:true, // suppresses the source menu unless linked to directly
      initiallySelectedContacts:true,  
      // delay making the links that launch a popup clickable
      // until after the widget has initialized completly. a popup window must 
      // be opened in an onclick handler, so we don't support queueing these actions
      afterInit:function() {
        var i, links = document.getElementsByClassName('delayed');
        for(i = 0; i < links.length; i++) {
          // make the links that launch a popup clickable by setting the href property
          links[i].href = "#";
        }
      }
    };


    function listToAray(fullString, separator) {
    	  var fullArray = [];

    	  if (fullString !== undefined) {
    	    if (fullString.indexOf(separator) == -1) {
    	      fullAray.push(fullString);
    	    } else {
    	      fullArray = fullString.split(separator);
    	    }
    	  }
			alert("FULLARRAY: "+ fullArray[0]);
    	  return fullArray;
    	}
    
</script>

<script type='text/javascript'>

</script>
<script type="text/javascript" src="//api.cloudsponge.com/address_books.js"></script>
<!--- placeholder Ends-----> 
</head>
<%--
<script type="text/javascript">
    $(window).load(function() {
        $(".loader").fadeOut("slow");
    })
</script>
--%>
<body>
<%--
<div class="loader" style="background: url('${pageContext.request.contextPath}/ui/images/common/long_loading_bar.gif') 50% 50% no-repeat rgb(249,249,249)"></div>
--%>
<div id="gradient">
  <div class="extra">
    <!-- include header -->
    <jsp:include page="../common/includes/header.jsp" />
    <div class="main">
      <!--content -->
      <article id="content">
        <div id="main_content">
          <div class="grid_9">
            <h1>Contacts</h1>
            <nav>
              <script type="text/javascript" src="ui/includes/leftmenu_contacts.js"></script>
            </nav>
            <div class="hline"></div>
            <div id="advert"><img src="ui/images/advert.png" alt="Advert"></div>
          </div>
          <div class="line"><span></span></div>
          <div class="grid_17">
            <!------ Search Box Starts -------->
          
           
            <!------ Search Box End-------->
            <!-- import your Contacts Start -->
            <div id="import_contacts_cs">
              <h4><span>Import</span> Contacts</h4>
              <p>Choose a Social Network or Email Service to import your Contacts</p>
              <p style="font-size:12px;">Note: When you log out, please also log out of the corresponding account or website <u><b>separately</b></u></p>
              
			  <ul class="tt-wrapper">
			  <!-- 
				<li><a class="tt-gmail" href="importsocialauth.do?id=googleplus&mode='import'"><span>Gmail</span></a></li>
				<li><a class="tt-yahoo" href="importsocialauth.do?id=yahoo&mode='import'"><span>Yahoo</span></a></li>
				<li><a class="tt-mail" href="./contacts_new.cyt?importFrom=importEmails"><span>Emails</span></a></li>
			  -->
			  
				<li><a class="deep-link delayed tt-gmail" onclick="return cloudsponge.launch('gmail');"><span>Gmail</span></a></li>
				<li><a class="deep-link delayed tt-yahoo" onclick="return cloudsponge.launch('yahoo');"><span>Yahoo</span></a></li>
				<li><a class="tt-facebook deep-link delayed" onclick="return cloudsponge.launch('facebook');"><span>Facebook </span></a></li>
		 	    <li><a class="tt-linkedin deep-link delayed" onclick="return cloudsponge.launch('linkedin');"><span>LinkedIn </span></a></li>
				<li><a class="tt-mail" href="./contacts_new.cyt?importFrom=importEmails"><span>Emails</span></a></li>
			</ul>
			<p></p>
			  		 <div id="sample" style="width: 100%;"></div>
			
			
			<p></p>
			<p></p>
			<p></p>
			
			  <ul>
				<li><a href="#" title="Invite" onclick="javascript:processMultipleContact('Invite');" id="Invitebtn"
       				class="button_disabled button_disabled_ie8" style=" width:84px; float:left;"> <span class="delete_bot">INVITE</span></a></li>
				<li><a href="#" title="Import" onclick="javascript:processMultipleContact('Invite');" id="Invitebtn"
       				class="button_disabled button_disabled_ie8" style=" width:84px; float:left;"> <span class="delete_bot">IMPORT</span></a></li>
			 </ul>
			
			<p></p>
			<p></p>
			<p></p>
			<p></p>
			<p></p>
			<p></p>
			<p></p>
			<!-- 
              <h4><span>Connect With</span> Social Network Contacts</h4>
              <p>Share your inCyyte polls with Social Network friends</p>
              <p style="font-size:12px;">Note: When you log out, please also log out of the corresponding account or website <u><b>separately</b></u></p>
              
			  <ul class="tt-wrapper">
			  	<li><a class="tt-twitter deep-link delayed" href="#"><span>Twitter <BR> Not available</span></a></li>
			</ul>
          
           -->
          
              <!-- 
              <a href="#" title="Import Contacts" id="" class="button_green1" style="margin:20px 0 35px 0; width:182px; float:left;"> <span class="title_green1">IMPORT CONTACTS</span></a> 
			  <div style="clear:both;"></div>
			  <p style="width:385px;">Downloading Facebook Contacts - 60% complete </p>
			      <!-- Progress bar
             <div id="progress_bar" class="ui-progress-bar ui-container">
             <div class="ui-progress" style="width: 60%;">
             <span class="ui-label" style="display:none;">Processing <b class="value">60%</b></span>      </div>
             </div>
           --> 
    <!-- /Progress bar -->
			  </div>
            <!-- import your Contacts Ends-->
          </div>
        </div>
      </article>
      <!--content ends -->
    </div>
    
  </div>
				
					  <textarea id="contact_list" style="width:450px;height:82px;display:none;"></textarea>          
  
  <!-- include footer -->
  <jsp:include page="../common/includes/footer.jsp" />
  
  <script type="text/javascript" src="ui/js/googleanalytics.js"></script>
</body>
</html>
