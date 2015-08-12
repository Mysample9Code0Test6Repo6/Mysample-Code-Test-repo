<%@ include file="/WEB-INF/jsp/common/include.jsp" %>
<%@page import="com.incyyte.app.domain.User" %>
<%@page import="com.incyyte.app.web.model.UserContactModel" %>
<%@page import="com.incyyte.app.web.SessionKeys" %>

<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="content-type" content="text/html; charset=UTF-8">
<link rel="shortcut icon" href="favicon.ico" />

<title>inCyyte - My Contacts</title>
<meta charset="utf-8">
<link rel="stylesheet" href="ui/css/reset.css">
<link rel="stylesheet" href="ui/css/layout.css">
<link rel="stylesheet" href="ui/css/buttons.css" media="screen">
<link rel="stylesheet" href="ui/css/style_login.css">
<link rel="stylesheet" href="ui/css/accordionmenu.css" type="text/css">
<link rel="stylesheet" href="ui/css/icons_sprites.css">
<link rel="stylesheet" href="fonts/fonts_stylesheet.css">
<link rel="stylesheet" href="ui/css/style_social.css">
<link rel="stylesheet" href="ui/css/ratingbar.css" type="text/css"/>
<link rel="stylesheet" href="ui/css/ui.progress-bar.css" type="text/css"/>
<link rel="stylesheet" href="ui/modal/colorbox.css">
<link rel="stylesheet" href="ui/css/ratingbar.css" type="text/css"/>
<script src="ui/js/jquery-1.4.2.min.js"></script>
<script src="ui/js/jquery-1.7.1.js"></script>
<script src="ui/js/jquery.color.js"></script>
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
<script src="ui/modal/colorbox/jquery.colorbox.js"></script>
<script src="ui/js/jquery_profile_rating.js" type="text/javascript"></script>
<script src="ui/js/star_rating.js" type="text/javascript"></script>
<script>
    // placeholder polyfill
    $(document).ready(function(){
        	 $('#contact_details').hide();
        $(".inline").colorbox({inline: true});
        	   $('#Deletebtn').bind('click', function(e){
        	        e.preventDefault();
        	})

        	  $('#Blockbtn').bind('click', function(e){
        	        e.preventDefault();
        	})
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
<% User user = (User) request.getSession().getAttribute(SessionKeys.LOGIN_USER); %>


<script type="text/javascript">
    $(document).ready( function() {
    	  $(':checkbox').click(function () {
    	      
    	      var checkboxes = $("input[type='checkbox']");
    	      if(!checkboxes.is(":checked"))
    	      {
    	              $('#Deletebtn').bind('click', function(e){ e.preventDefault();})
    	              $('#Blockbtn').bind('click', function(e){e.preventDefault();})
    	              $('#Deletebtn').attr('class','button_disabled');
    	              $('#Blockbtn').attr('class','button_disabled');
    	      
    	      }
    	      else
    	      {
    	      
    	            $('#Blockbtn').unbind('click');
    	            $('#Deletebtn').unbind('click');
    	            $('#Deletebtn').attr('class','button_green1');
    	            $('#Blockbtn').attr('class','button_green1');
    	      
    	      
    	      }
    	      
    	      
    	      
    	       });
        // Select all
        $("A[href='#select_all']").click( function() {
            $("#" + $(this).attr('rel') + " INPUT[type='checkbox']").attr('checked', true);
            $('#Blockbtn').unbind('click');
            $('#Deletebtn').unbind('click');
            $('#Deletebtn').attr('class','button_green1');
            $('#Blockbtn').attr('class','button_green1');
            
            
            return false;
        });
 
        // Select none
        $("A[href='#select_none']").click( function() {
            $("#" + $(this).attr('rel') + " INPUT[type='checkbox']").attr('checked', false);
            $('#Deletebtn').bind('click', function(e){ e.preventDefault();})
            $('#Blockbtn').bind('click', function(e){e.preventDefault();})
            $('#Deletebtn').attr('class','button_disabled');
            $('#Blockbtn').attr('class','button_disabled');
            
            
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
    $(document).ready(function () {
        $("#search").autocomplete({
            source: '${pageContext.request.contextPath}/mycontacts/GetUserContactByFname.cyt'
        });
    });
</script>



<script type="text/javascript">
    function myonclickhandler(t, val) {
        if (t.checked) {
            var check = document.getElementById("uncheckedlist").value;
            for (var i = 0; i <= check.length; i++) {
                if (val == check[i])
                    check = check.trim(val);
            }
            document.getElementById("uncheckedlist").value = check;
        }
        else {
            document.getElementById("uncheckedlist").value = document.getElementById("uncheckedlist").value + "," + val;
        }
    }
    function Createurl(url) {
        var selected = "";
        var checked = $("input[@name=" + 'numbers[]' + "]:checked");
        var unselected = "";
        var cid = $("#vselected").val();
        for (var i = 0; i < checked.length; i++) {
            if (checked[i].value != 'on') {
                if (selected == "")
                    selected = checked[i].value;
                else
                    selected = selected + "," + checked[i].value;
            }
        }
        if (cid != '') {
            if (selected != '')
                selected = selected + ',' + cid;
            else
                selected = cid;
        }
        var input = selected;
        var splitted = input.split(',');
        var collector = {};
        for (i = 0; i < splitted.length; i++) {
            key = splitted[i].replace(/^\s*/, "").replace(/\s*$/, "");
            collector[key] = true;
        }
        var out = [];
        for (var key in collector) {
            out.push(key);
        }
        var output = out.join(',');
        var unchecked = document.getElementById("uncheckedlist").value;
        var splitted = unchecked.split(',');
        for (var i = 0; i < splitted.length; i++) {
            if (splitted[i] != '')
                selected = selected.replace(splitted[i]);
        }
        selected = selected.replace('undefined');
        window.location = url + '&cids=' + selected;
    }
    function split(val) {
        return val.split(/,\s*/);
    }
    function extractLast(term) {
        return split(term).pop();
    }
    $(document).ready(function () {
        $("#search").autocomplete({
            source: '${pageContext.request.contextPath}/mycontacts/GetUserContactByFname.cyt'
        });
    });
</script>


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
            <!------ Search Box Starts -------->
            <div id="advanced_searchbox">
              <div id="searchbox" style="margin-bottom:0">
                <p class="sort_by_text">Advanced Search</p>
                <div id="searchmain" >
                  <p class="search_heading">Search</p>
                  <form id="searchform_new">
                    <input type="text" name="search" id="search_new" >
                    <input type="submit" id="searchSubmit_new" value="" />
                  </form>
                </div>
              </div>
              <nav id="alpha_list">
        <li><a href="./contactsHome.cyt?param=A">A</a></li>
        <li><a href="./contactsHome.cyt?param=B">B</a></li>
        <li><a href="./contactsHome.cyt?param=C">C</a></li>
        <li><a href="./contactsHome.cyt?param=D">D</a></li>
        <li><a href="./contactsHome.cyt?param=E">E</a></li>
        <li><a href="./contactsHome.cyt?param=F">F</a></li>
        <li><a href="./contactsHome.cyt?param=G">G</a></li>
        <li><a href="./contactsHome.cyt?param=H">H</a></li>
        <li><a href="./contactsHome.cyt?param=I">I</a></li>
        <li><a href="./contactsHome.cyt?param=J">J</a></li>
        <li><a href="./contactsHome.cyt?param=K">K</a></li>
        <li><a href="./contactsHome.cyt?param=L">L</a></li>
        <li><a href="./contactsHome.cyt?param=M">M</a></li>
        <li><a href="./contactsHome.cyt?param=N">N</a></li>
        <li><a href="./contactsHome.cyt?param=O">O</a></li>
        <li><a href="./contactsHome.cyt?param=P">P</a></li>
        <li><a href="./contactsHome.cyt?param=Q">Q</a></li>
        <li><a href="./contactsHome.cyt?param=R">R</a></li>
        <li><a href="./contactsHome.cyt?param=S">S</a></li>
        <li><a href="./contactsHome.cyt?param=T">T</a></li>
        <li><a href="./contactsHome.cyt?param=U">U</a></li>
        <li><a href="./contactsHome.cyt?param=V">V</a></li>
        <li><a href="./contactsHome.cyt?param=W">W</a></li>
        <li><a href="./contactsHome.cyt?param=X">X</a></li>
        <li><a href="./contactsHome.cyt?param=Y">Y</a></li>
        <li><a href="./contactsHome.cyt?param=Z">Z</a></li>
              </nav>
             </div>
            <!------ Search Box End-------->
<input type='hidden' name="vselected" id="vselected" value="${selcid}"/>
<input type='hidden' name="uncheckedlist" id="uncheckedlist" value=""/>
<!-- view_all_contacts Start -->
<div id="view_all_contacts">
    <a href="#" title="Delete" onclick="javascript:processMultipleContact('Delete');" id="Deletebtn"
       class="button_disabled" style=" width:84px; float:left;"> <span class="title_green1">Delete</span></a>
    <a href="#"  onclick="javascript:processMultipleContact('Block');" title="Block" id="Blockbtn" class="button_disabled"
       style=" width:84px; float:left; margin:0 0 10px 5px;"> <span class="title_green1">Block</span></a><br>

    <table width="100%" border="0" cellspacing="0" cellpadding="0" id="contact_list">
        <tr>
            <td colspan="4" class="select_all">Select: <a rel="group_1" href="#select_all"
                                                          onclick="javascript:selectallcontacts()">All</a> | <a
                    rel="group_1" href="#select_none">None</a></td>
        </tr>
        <c:forEach items="${UserContactlist}" var="UserContactModel">
            <tr>
                <c:choose>
                    <c:when test="${UserContactModel.checked eq 'checked'}">
                        <td width="5%" valign="top">
                            <fieldset id="group_1">
                                <input name="numbers[]" type="checkbox" checked id="${UserContactModel.contactid}" value="'${UserContactModel.contactid}'" onclick="myonclickhandler(this,'${UserContactModel.contactid}');">
                            </fieldset>
                        </td>
                    </c:when>
                    <c:otherwise>
                        <td>
                            <fieldset id="group_1">
                                <input name="numbers[]" type="checkbox" id="${UserContactModel.contactid}" value="'${UserContactModel.contactid}'" onclick="myonclickhandler(this,'${UserContactModel.contactid}');">
                            </fieldset>
                        </td>
                    </c:otherwise>
                </c:choose>
                <td width="12%"><img src='${UserContactModel.profile_img}' width="46" height="46" alt="User Photo" class="photoframe"></td>

                <c:choose>
                <c:when test="${not empty UserContactModel.firstname}">
                <td width="64%"><p class="font_18px">${UserContactModel.firstname}/${UserContactModel.email}</p>
                    </c:when>
                    <c:otherwise>
                <td width="64%"><p class="font_18px">${UserContactModel.email}</p>
                    </c:otherwise>
                    </c:choose>
                    <p>${UserContactModel.postalcode}</p>
                    <% String invite = "";
                        String reinvite = "none";
                        String block = "none";
                        String unblock = "";
                    %>
                    <p class="font_14px">
                        <c:if test="${UserContactModel.blocked != 'Y'}">
                <%  block="";
                    unblock="none";
                    %>
                        </c:if>
                        <c:if test="${UserContactModel.sent_invite == 'Y' && UserContactModel.accept_inv !='Y'}">
                <%  invite="none";
                    reinvite="";
                    %>
                        </c:if>
                        <c:if test="${UserContactModel.sent_invite == 'Y' && UserContactModel.accept_inv =='Y'}">
                <%  invite="none";
                    reinvite="none";
                    %>
                        </c:if>
                        <a id='delete_<c:out value="${UserContactModel.contactid}"/>'
                        href="#" onclick="javascript:processDeleteContact('${UserContactModel.contactid}');">&bull; Delete</a>
                        <a id='block_<c:out value="${UserContactModel.contactid}"/>'
                           href="#" onclick="javascript:processBlockContact('${UserContactModel.contactid}','${UserContactModel.email}');" 
                           style="display:<%=block%>;">&bull; Block</a>
                        <a id='unblock_<c:out value="${UserContactModel.contactid}"/>'
                           href="javascript:processUnBlockContact('${UserContactModel.contactid}','${UserContactModel.email}');" 
                           style="display:<%=unblock%>">&bull; UnBlock</a>
                        <a id='invite_<c:out value="${UserContactModel.contactid}"/>'
                           href="javascript:processInvite('${UserContactModel.contactid}','${UserContactModel.email}');"
                           style="display:<%=invite%>">&bull; Invite</a>
                        <a id='reInvite_<c:out value="${UserContactModel.contactid}"/>'
                           href="javascript:processReinvite('${UserContactModel.contactid}','${UserContactModel.email}');"
                           style="display:<%=reinvite%>" title="Resend Invitation">&bull; Awaiting Acceptance</a>
                    <div id='message_<c:out value="${UserContactModel.contactid}"/>' class="success_message"></div>
                    </p></td>
                <td width="19%" align="right">
                    <input type="hidden" id='memberStatus_<c:out value="${UserContactModel.contactid}"/>'
                           value="${UserContactModel.status}"/>
                    <input type="hidden" id='blockStatus_<c:out value="${UserContactModel.contactid}"/>'
                           value="${UserContactModel.blocked}"/>
                    <input type="hidden" id='sendInvite_<c:out value="${UserContactModel.contactid}"/>'
                           value="${UserContactModel.sent_invite}"/>
                    <input type="hidden" id='inviteStatus_<c:out value="${UserContactModel.contactid}"/>'
                           value="${UserContactModel.accept_inv}"/>
                    <a href="#contact_details" class="inline"
                       onclick="javascript:processContact('${UserContactModel.contactid}','${UserContactModel.nickname}','${UserContactModel.firstname}','${UserContactModel.lastname}','${UserContactModel.email}','${UserContactModel.mobile}','${UserContactModel.status}','${UserContactModel.definingQuestion}','${UserContactModel.tmpPostcode}','${UserContactModel.formattedMessageDate}','${UserContactModel.blocked}','${UserContactModel.accept_inv}','${UserContactModel.sent_invite}','${UserContactModel.profile_img}','${UserContactModel.username}','${UserContactModel.pollHomePage}')">View
                        &amp; Edit</a></td>
            </tr>
        </c:forEach>
    </table>
</div>
<!-- view_all_contacts Ends-->
<div style="display: none;">
    <div id="contact_details">
        <table width="100%" border="0" cellspacing="0" cellpadding="0">
            <tr>
                <td width="4%" valign="top"></td>
                <td width="15%" valign="top"><img src='' width="72" height="73" alt="User Photo" id="profileImage"></td>
                <td width="30%" valign="top">
                    <table width="100%" border="0" cellspacing="0" cellpadding="0">
                        <tr>
                            <td id="vw_username" class='contact_username'></td>
                        </tr>
                        <tr>
                            <td colspan="1" height="13"></td>
                        </tr>
                        <tr>
                            <td>Rating:Megaphone</td>
                        </tr>
                        <tr>
                            <td height="30">
                                <div id="fixed"></div>
                            </td>
                        </tr>
                        <tr>
                            <td class='today_opinion'>Today's opinion:</td>
                        </tr>
                        <tr>
                            <td id="vw_note"></td>
                        </tr>
                    </table>
                </td>
                <td width="51%" valign="top">
                    <table width="90%" border="0" align="right" cellpadding="0" cellspacing="0">
                        <tr>
                            <td width="38%" height="29">&nbsp;</td>
                            <td width="37%">&nbsp;</td>
                            <td width="25%">&nbsp;</td>
                        </tr>
                        <tr>
                            <td>Member Since:</td>
                            <td id="vw_memsince"></td>
                            <td>&nbsp;</td>
                        </tr>
                        <tr>
                            <td>Real Name:</td>
                            <td id="vw_realname"></td>
                            <td>&nbsp;</td>
                        </tr>
                        <tr>
                            <td colspan="3" height="20">&nbsp;</td>
                        </tr>
                        <tr>
                            <td>Postcode:</td>
                            <td id="vw_pcode"></td>
                            <td>&nbsp;</td>
                        </tr>
                        <tr>
                            <td>Telephone:</td>
                            <td id="vw_mobile"></td>
                            <td>&nbsp;</td>
                        </tr>
                        <tr>
                            <td colspan="3" height="20">&nbsp;</td>
                        </tr>
                        <tr>
                            <td>Email:</td>
                            <td id="vw_email"></td>
                            <td>&nbsp;</td>
                        </tr>
                        <tr>
                            <td colspan="3" height="20">&nbsp;</td>
                        </tr>
                        <tr>
                            <td>Groups:</td>
                            <td>Groupname 01</td>
                            <td class="join_this_group"><a href="#">Join This Group</a></td>
                        </tr>
                        <tr>
                            <td>&nbsp;</td>
                            <td>Groupname 02</td>
                            <td class="join_this_group"><a href="#">Join This Group</a></td>
                        </tr>
                    </table>
                </td>
            </tr>
            <tr>
                <td>&nbsp;</td>
                <td colspan="3" class="green_hline">&nbsp;</td>

                <form:form id="ViewContactForm" commandName="ViewContactForm" method="post">
                <% UserContactModel usercontModel = (UserContactModel) session.getAttribute("ViewContactForm"); %>
                    <form:hidden path="contactid" id="contactid"/>
                    <form:hidden path="email" id="email"/>
                    <form:hidden path="checked" id="ed_checked"/>
                </form:form>
            </tr>
            <tr>
                <td>&nbsp;</td>
                <td colspan="3">
                    <div class="contact_icons">
                        <a id="sendMessage" href="javascript:processSendMsg();" class="invite">Send a Message |</a>
                        <a id="delete" href="javascript:processDelete();" class="delete">Delete this Contact |</a>
                        <a id="invite" href="javascript:processInviteContact();" class="invite" style="display:none;">Invite
                            to InCyyte |</a>
                        <a id="reinvite" href="javascript:processReinviteContact();" class="invite"
                           style="display:none;" title="Resend Invitation">Awaiting Acceptance |</a>
                        <a id="unblock" href="javascript:processUnBlock();" class="edit" style="display:none;">UnBlock
                            this Contact |</a>
                        <a id="block" href="javascript:processBlock();" class="delete" style="display:none;">Block this
                            Contact |</a>
                    </div>
                </td>
            </tr>
        </table>
    </div>
</div>
<!-- Pagination start---->
<div class='pagination'>
    <%--For displaying Previous link except for the 1st page --%>
    <c:if test="${currentPage != 1}">
        <a class='prev page-numbers'
           href=javascript:Createurl('./search.cyt?page=${currentPage - 1}&param=${text}');>Prev</a>
    </c:if>
    <%--For displaying Page numbers.
    The when condition does not display a link for the current page--%>
    <c:forEach begin="1" end="${noOfPages}" var="i">
        <c:choose>
            <c:when test="${currentPage eq i}">
                <span class='page-numbers current'>${i}</span>
            </c:when>
            <c:otherwise>
                <c:if test="${i <= 5}">
                    <a class='page-numbers'
                       href=javascript:Createurl('./search.cyt?page=${i}&param=${text}');>${i}</a>
                </c:if>
                <c:if test="${i > 5}">
                    <c:if test="${i+1 == noOfPages}">
                        <a class='page-numbers'>..</a>
                        <a class='page-numbers'
                           href=javascript:Createurl('./search.cyt?page=${i}&param=${text}');>${i}</a>
                    </c:if>
                    <c:if test="${i == noOfPages}">
                        <a class='page-numbers'
                           href=javascript:Createurl('./search.cyt?page=${i}&param=${text}');>${i}</a>
                    </c:if>
                </c:if>
            </c:otherwise>
        </c:choose>
    </c:forEach>
    <%--For displaying Next link --%>
    <c:if test="${currentPage lt noOfPages}">
        <a class='next page-numbers'
           href=javascript:Createurl('./search.cyt?page=${currentPage + 1}&param=${text}');>Next</a>
    </c:if>
</div>
</div>
</article>
<!--content ends -->
</div>
</div>
<!--footer Starts -->
<jsp:include page="../common/includes/footer.jsp"/>
</div>
<!--footer ends -->
<script type="text/javascript" src="ui/js/googleanalytics.js"></script>
</body>
</html>
