<%@ include file="/WEB-INF/jsp/common/include.jsp" %>


<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="content-type" content="text/html; charset=UTF-8">
<link rel="shortcut icon" href="favicon.ico" />

<title>inCyyte - My Contacts</title>
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
<script src="ui/js/jquery-1.4.2.min.js"></script>
<script src="ui/js/jquery-1.7.1.js"></script>
<script src="ui/js/jquery.color.js"></script>
<script src="ui/js/search_script.js"></script>
<script src="ui/js/jquery.raty.min.js" type="text/javascript"></script>
<script src="ui/js/star_rating.js" type="text/javascript"></script>
<!-- Left Navigation script starts here -->
<script src="ui/js/left_menu.js"></script>
<!-- Left Navigation script ends here -->
<!--- placeholder Starts----->


         <script type="text/javascript" src="ui/js/jquery-1.5.1.min.js"></script>
    	<script type="text/javascript" src="ui/js/jquery-ui-1.8.13.custom.min.js"></script>
    	<script type="text/javascript" src="ui/js/external/jquery.bgiframe-2.1.2.js"></script>
    	<script type="text/javascript" src="ui/js/communicator.js"></script>
    	<script type="text/javascript" src="ui/js/contact.js"></script>
    	<script type="text/javascript" src="ui/js/jquery.form.js"></script> 

<link rel="stylesheet" href="ui/css/ui.progress-bar.css" type="text/css"/>
<link rel="stylesheet" href="ui/modal/colorbox.css">
<link rel="stylesheet" href="ui/css/ratingbar.css" type="text/css"/>

<script src="ui/js/jquery_profile_rating.js" type="text/javascript"></script>  
<script src="ui/js/star_rating.js" type="text/javascript"></script>



<script>
    // placeholder polyfill
    $(document).ready(function(){
        	 $('#contact_details').hide();
    
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
</script>


<script type="text/javascript">
    $(document).ready( function() {
 
        // Select all
        $("A[href='#select_all']").click( function() {
            $("#" + $(this).attr('rel') + " INPUT[type='checkbox']").attr('checked', true);
            return false;
        });
 
        // Select none
        $("A[href='#select_none']").click( function() {
            $("#" + $(this).attr('rel') + " INPUT[type='checkbox']").attr('checked', false);
            return false;
        });
 
     
 
    });
</script>



<script type="text/javascript">
function split(val) {
    return val.split(/,\s*/);
}
function extractLast(term) {
    return split(term).pop();
}

$(document).ready(function() {

	$( "#search" ).autocomplete({
		source: '${pageContext.request.contextPath}/mycontacts/GetUserContactByFname.cyt'
	});

	
});
</script>


<!--- placeholder Ends----->
    <!---- Invite button enable and disable script -->
    <script type="text/javascript">
        $(document).ready( function() {
            $(':checkbox').click(function () {
                var checkboxes = $("input[type='checkbox']");
                if(!checkboxes.is(":checked")) {
                    $('#invitebtn').bind('click', function(e){
                        e.preventDefault();
                    });
                    $('#invitebtn').attr('class','button_disabled button_disabled_ie8');
                }
                else {
                    $('#invitebtn').unbind('click');
                    $('#invitebtn').attr('class','button_green1 button_enabled_ie8');
                }
            });
            // Select all
            $("A[href='#select_all']").click( function() {
                $("#" + $(this).attr('rel') + " INPUT[type='checkbox']").attr('checked', true);
                $('#invitebtn').unbind('click');
                $('#invitebtn').attr('class','button_green1 button_enabled_ie8');
                return false;
            });
            // Select none
            $("A[href='#select_none']").click( function() {
                $("#" + $(this).attr('rel') + " INPUT[type='checkbox']").attr('checked', false);
                $('#invitebtn').bind('click', function(e){ e.preventDefault();});
                $('#invitebtn').attr('class','button_disabled button_disabled_ie8');
                return false;
            });
        });
    </script>
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
    <!--header -->
        <jsp:include page="../common/includes/header.jsp" />
    <!--header end-->
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
            <!-- view_all_contacts Start -->
            <div id="view_all_contacts">
                <div style="width: 100%; height: 54px;">
                <a href="#" onClick="javascript:processMultipleContact('Invite');" title="Invite" id="invitebtn" class="button_disabled button_disabled_ie8" style=" width:84px; float:left;"> <span class="delete_bot">Invite</span></a>
                </div>
              <table width="100%" border="0" cellspacing="0" cellpadding="0" id="contact_list">
                <tr>
                  <td colspan="4" class="select_all">Select: <a rel="group_1" href="#select_all">All</a> | <a rel="group_1" href="#select_none">None</a></td>
                </tr>
                  	<c:forEach var="contact" items="${contacts}" varStatus="index">
		         <tr class='<c:if test="${index.count % 2 == 0}">sectiontableentry2</c:if><c:if test="${index.count % 2 != 0}">sectiontableentry1</c:if>'>
                  <td width="5%" valign="top">
                   <fieldset id="group_1">
                   
				       <c:if test="${contact.emailHash !='googleplus' && contact.emailHash !=  'yahoo'}">
				      
				         <c:choose>
				       <c:when test="${contact.disable =='Y'}">
				        <input name="numbers[]" type="checkbox" value="'${contact.id}'" DISABLED > 				    
					    </c:when>
					    <c:when test="${contact.disable!='Y'}">
				         <input name="numbers[]" type="checkbox" value="'${contact.id}'"> 					    
				       </c:when>
				       </c:choose>
			            <input  type="hidden" name="impmode" id="impmode" value="snsite'"> 
                      
						</c:if>
				       <c:if test="${contact.emailHash =='googleplus' || contact.emailHash ==  'yahoo'}">
				       				         <c:choose>
				       <c:when test="${contact.disable =='Y'}">
                        <input name="numbers[]" type="checkbox" value="'${contact.email}'" DISABLED> 
                        					    </c:when>
					    <c:when test="${contact.disable!='Y'}">
                        <input name="numbers[]" type="checkbox" value="'${contact.email}'"> 
					     </c:when>
				       </c:choose>
                        <input  type="hidden" name="impmode" id="impmode" value="mailsite'">
						</c:if>
                  </fieldset>
                  </td>
                     <td width="12%"><img src='ui/images/default_avatar.png' width="46" height="46" alt="User Photo" class="photoframe"></td>
                  <td width="64%"><p class="font_18px">${contact.firstName}  ${contact.lastName}/${contact.email}</p>
                    <p></p>
                    </td>
                </tr>
	</c:forEach>
                 
            </div>
            <!-- view_all_contacts Ends-->
          	<div id="contact_details"  >
			<div id="close"><a href=""  title="Close"></a></div>
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
    </table></td>
  </tr>
            <form:form  id="ViewContactForm"  commandName="ViewContactForm" method="post" >
          	<form:hidden path="checked" id="ed_checked"   />
          </form:form>
  <tr>
    <td>&nbsp;</td>
    <td colspan="3" class="green_hline">&nbsp;</td>
  <tr>
    <td>&nbsp;</td>
    <td colspan="3">
    <div class="contact_icons" >
	</div>
	</td>
    </tr>
</table>
			</div>
	        <!-- Pagination start---->
        </div>
      </article>
      <!--content ends -->
    </div>
  </div>
  <!--footer Starts -->
   <jsp:include page="../common/includes/footer.jsp" />

</div>
<!--footer ends -->
<script type="text/javascript" src="ui/js/googleanalytics.js"></script>
</body>
</html>