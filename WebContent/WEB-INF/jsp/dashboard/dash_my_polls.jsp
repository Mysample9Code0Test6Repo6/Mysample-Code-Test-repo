<%@page import="com.incyyte.app.web.SessionKeys"%>
<%@page import="com.incyyte.app.domain.User"%>
<%@page import="com.incyyte.app.service.util.Utility"%>
<%@ include file="/WEB-INF/jsp/common/include.jsp" %>

<% User user = (User)request.getSession().getAttribute(SessionKeys.LOGIN_USER); %>

<link rel="stylesheet" href="ui/css/reset.css">

<link rel="stylesheet" href="ui/css/layout.css">
<link rel="stylesheet" href="ui/css/buttons.css"  media="screen">
<link rel="stylesheet" href="ui/css/style_login.css">
<link rel="stylesheet" href="ui/css/accordionmenu.css" type="text/css">
<link rel="stylesheet" href="ui/css/icons_sprites.css">
<link rel="stylesheet" href="ui/css/form_elements.css">
<link rel="stylesheet" href="fonts/fonts_stylesheet.css">
<link rel="stylesheet" href="ui/modal/colorbox.css">

<script src="ui/js/accordian/jquery-1.6.2.js"></script>
<script type="text/javascript" src="ui/js/jquery-ui-1.8.16.custom.min.js"></script>
<script src="ui/js/jquery-1.3.2.min.js"></script>
<script src="ui/js/jquery-1.7.1.js"></script>
<script src="ui/js/jquery-1.8.2.min.js"></script>
<script src="../../ui/js/login.js"></script>
<script src="ui/js/jquery.color.js"></script>
<script src="ui/js/search_script.js"></script>
<script src="ui/js/contact.js"></script>

<!-- copied from sent page -->
<script type="text/javascript" src="ui/js/jquery-ui.js"></script>
<script type="text/javascript" src="ui/js/validate/jquery.validate.js" ></script>
<script src="ui/js/jquery.selectbox-0.2.js"></script>
<script type="text/javascript" src="ui/js/jquery.ratingbar.js"></script>
<!-- ends here -->

<script src="ui/modal/colorbox/jquery.colorbox.js"></script>
<script src="ui/js/jquery_dashboard_rating.js" type="text/javascript"></script>
<script src="ui/js/star_rating.js" type="text/javascript"></script>
<script src="ui/js/customInput.jquery.js" type="text/javascript"></script>

<script src="ui/js/jquery.limit.js"></script>
<script src="ui/js/zoomphoto.js"></script>
<script src="ui/js/prettyCheckboxes.js" type="text/javascript" charset="utf-8"></script>
<script type="text/javascript" src="ui/js/autoresize.jquery.js"></script>
<script src="ui/js/accordian/jquery.ui.core.js"></script>
<script src="ui/js/accordian/jquery.ui.widget.js"></script>
<script src="ui/js/accordian/jquery.ui.accordion.js"></script>
<script src="ui/js/jquery.ui.datepicker.js"></script>
<script type="text/javascript" src="ui/js/jquery.form.js"></script>
<script type="text/javascript" src="ui/js/jquery-ui.js"></script>

<script src="ui/js/dashboard/dash_my_polls.js"></script>
<script src="ui/js/jquery.simplePagination.js"></script>
<!-- Left Navigation script starts here -->
<script src="ui/js/left_menu.js"></script>
<!-- Left Navigation script ends here -->
<!-- Chart Script Start here -->
<!-- <script src="ui/js/charts/js/charts_sent_incyyte.js"></script> -->
<script src="ui/js/charts/js/highcharts.js"></script>
<!-- Chart Script end here -->
<!-- Rating bar and profile starts here -->
<link rel="stylesheet" type="text/css" href="ui/css/ratingbar.css" />
<link rel="stylesheet" type="text/css" href="ui/css/simplePagination.css" />
<link rel="stylesheet" href="ui/css/themes/base/jquery.ui.all.css">
<link href="ui/css/style.css" media="screen" rel="stylesheet" type="text/css" />
<script>
    if (/*@cc_on!@*/false) {
        var headHTML = document.getElementsByTagName('head')[0].innerHTML;
        headHTML    += '<link type="text/css" rel="stylesheet" href="ui/css/ie10.css">';
        document.getElementsByTagName('head')[0].innerHTML = headHTML;
    }
</script>

<!-- Rating bar and profile ends here -->
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

    $(function() {
        $( "#datepicker" ).datepicker({
            minDate: 0,
            altField: "#alternate",
            altFormat: "yy-mm-dd"
        }).datepicker("setDate", "+1");
    });   
    function displaySharedPopup(incyyteCode){
        $("#incyyteCode").val(incyyteCode);
        parent.$.fn.colorbox({'href':'div#sharedEmailList', 'open':true, 'inline':true});
     	sharedContactsJsonCall();
    }
</script>

<script type="text/javascript">
    $(document).ready(function() {
        //Default Action
        $(".tab_content").hide(); //Hide all content
        $("ul.Stabs li:first").addClass("active").show(); //Activate first tab
        $(".tab_content:first").show(); //Show first tab content
        //On Click Event
        $("ul.Stabs li").click(function() {
            $("ul.Stabs li").removeClass("active"); //Remove any "active" class
            $(this).addClass("active"); //Add "active" class to selected tab
            $(".tab_content").hide(); //Hide all tab content
            var activeTab = $(this).find("a").attr("href"); //Find the rel attribute value to identify the active tab + content
            $(activeTab).fadeIn(); //Fade in the active content
            return false;
        });
    });
</script>

<!--- placeholder Ends----->
<script type="text/javascript">
    // Run the script on DOM ready:
    $(function(){
        $('input').customInput();
    });
</script>
<!--- Modal ----->
<script>
    $(document).ready(function(){
        //Examples of how to assign the ColorBox event to elements
        $(".photos").colorbox({rel:'photos'});
        $(".group2").colorbox({rel:'group2', transition:"fade"});
        $(".group3").colorbox({rel:'group3', transition:"none", width:"75%", height:"75%"});
        $(".group4").colorbox({rel:'group4', slideshow:true});
        $(".ajax").colorbox();
        $(".youtube").colorbox({iframe:true, innerWidth:425, innerHeight:344});
        $(".vimeo").colorbox({iframe:true, innerWidth:500, innerHeight:409});
        $(".iframe").colorbox({iframe:true, width:"80%", height:"80%"});
        $(".inline").colorbox({inline:true});
        $(".callbacks").colorbox({
            onOpen:function(){ alert('onOpen: colorbox is about to open'); },
            onLoad:function(){ alert('onLoad: colorbox has started to load the targeted content'); },
            onComplete:function(){ alert('onComplete: colorbox has displayed the loaded content'); },
            onCleanup:function(){ alert('onCleanup: colorbox has begun the close process'); },
            onClosed:function(){ alert('onClosed: colorbox has completely closed'); }
        });

        //Example of preserving a JavaScript event for inline calls.
        $("#click").click(function(){
            $('#click').css({"background-color":"#f00", "color":"#fff", "cursor":"inherit"}).text("Open this window again and this message will still be here.");
            return false;
        });
    });
</script>
<!--- Mddal--------------->
<script type="text/javascript" src="ui/js/ZeroClipboard.js"></script>
<script type="text/javascript" src="ui/js/ddaccordion.js"></script>

<script type="text/javascript">
    //Initialize :
    ddaccordion.init({
        headerclass: "my_poll_tab", //Shared CSS class name of headers group
        contentclass: "my_poll_detail", //Shared CSS class name of contents group
        revealtype: "click", //Reveal content when user clicks or onmouseover the header? Valid value: "click", "clickgo", or "mouseover"
        mouseoverdelay: 200, //if revealtype="mouseover", set delay in milliseconds before header expands onMouseover
        collapseprev: true, //Collapse previous content (so only one open at any time)? true/false
        defaultexpanded: [], //index of content(s) open by default [index1, index2, etc]. [] denotes no content.
        onemustopen: true, //Specify whether at least one header should be open always (so never all headers closed)
        animatedefault: false, //Should contents open by default be animated into view?
        scrolltoheader: false, //scroll to header each time after it's been expanded by the user?
        persiststate: false, //persist state of opened contents within browser session?
        toggleclass: ["closedmy_poll", "openmy_poll"], //Two CSS classes to be applied to the header when it's collapsed and expanded, respectively ["class1", "class2"]
        togglehtml: ["none", "", " "], //Additional HTML added to the header when it's collapsed and expanded, respectively  ["position", "html1", "html2"] (see docs)
        animatespeed: "fast", //speed of animation: integer in milliseconds (ie: 200), or keywords "fast", "normal", or "slow"
        oninit:function(expandedindices){ //custom code to run when headers have initalized
            //do nothing
        },
        onopenclose:function(header, index, state, isuseractivated){ //custom code to run whenever a header is opened or closed
            //do nothing
        }
    })

</script>
<script type="text/javascript">
    //Initialize :
    ddaccordion.init({
        headerclass: "my_shared_poll_tab", //Shared CSS class name of headers group
        contentclass: "my_shared_poll_detail", //Shared CSS class name of contents group
        revealtype: "click", //Reveal content when user clicks or onmouseover the header? Valid value: "click", "clickgo", or "mouseover"
        mouseoverdelay: 200, //if revealtype="mouseover", set delay in milliseconds before header expands onMouseover
        collapseprev: true, //Collapse previous content (so only one open at any time)? true/false
        defaultexpanded: [], //index of content(s) open by default [index1, index2, etc]. [] denotes no content.
        onemustopen: true, //Specify whether at least one header should be open always (so never all headers closed)
        animatedefault: false, //Should contents open by default be animated into view?
        scrolltoheader: false, //scroll to header each time after it's been expanded by the user?
        persiststate: false, //persist state of opened contents within browser session?
        toggleclass: ["closedmy_poll", "openmy_poll"], //Two CSS classes to be applied to the header when it's collapsed and expanded, respectively ["class1", "class2"]
        togglehtml: ["none", "", " "], //Additional HTML added to the header when it's collapsed and expanded, respectively  ["position", "html1", "html2"] (see docs)
        animatespeed: "fast", //speed of animation: integer in milliseconds (ie: 200), or keywords "fast", "normal", or "slow"
        oninit:function(expandedindices){ //custom code to run when headers have initalized
            //do nothing
        },
        onopenclose:function(header, index, state, isuseractivated){ //custom code to run whenever a header is opened or closed
            //do nothing
        }
    })

</script>
<!--[if gte IE 8]>
<style>
.custom-checkbox input,
.custom-radio input {
left: -3px;
</style>
<![endif]-->
<!--[if lt IE 9]>
<script src="ui/js/html5.js"></script>
<![endif]-->
<!--[if lt IE 9]>
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7">
<link href="ui/css/iestyle.css" media="screen" rel="stylesheet" type="text/css" />
<![endif]-->

<!--[if gte IE 9]>
<link href="ui/css/gte_ie9.css" media="screen" rel="stylesheet" type="text/css" />
<![endif]-->

<div class="my_poll">
<form:form id="inCyyteForm" name="inCyyteForm" commandName="inCyyteForm" method="post">
<form:hidden path="id" id="inc_id"/>
<form:hidden path="closureDate" id="closureDate"/>
<input type="hidden" id="selectedPoll" value=""/>
<div class="tabsHolder">
<ul class="Stabs">
    <li><a href="#myPollPages">My Poll Pages</a></li>
    <li><a href="#sharedPollPages">Shared Poll Pages</a></li>
</ul>
<div class="tab_container">
<div id="myPollPages" class="tab_content">
    <c:forEach var="myPollPage" items="${myPollPages}" varStatus="status">

        <c:if test="${not myPollPage.deleted}">
            <script type="text/javascript">
                <!--
                $(document).ready(function () {

                    var chart = new Highcharts.Chart({
                        chart: {
                            renderTo: 'piecontainersmall${status.index}',
                            plotBackgroundColor: null,
                            plotBorderWidth: null,
                            plotShadow: false
                        },
                        title: {
                            text: ''
                        },
                        tooltip: {
                            pointFormat: '<b>{point.percentage}%</b>',
                            percentageDecimals: 1
                        },
                        plotOptions: {
                            pie: {
                                size: 60,
                                allowPointSelect: false,
                                cursor: 'pointer',
                                dataLabels: {
                                    enabled: false
                                },
                                showInLegend: false
                            }
                        },
                        series: [{
                            type: 'pie',
                            name: 'Total Responce 1,234',
                            data: [
                                ['Yes',    40],
                                ['No',     40],
                                ['May be',   20]
                            ]
                        }]
                    });
                });
                //-->

                $(function() {
                    var button = $('#dropBoxButton${status.index}');
                    var box = $('#dropBox${status.index}');
                    var form = $('#dropBoxContent${status.index}');
                    button.removeAttr('href');
                    button.mouseup(function(login) {
                        box.toggle();
                        button.toggleClass('active');
                    });
                    form.mouseup(function() {
                        return false;
                    });
                    $(this).mouseup(function(login) {
                        if(!($(login.target).parent('#dropBoxButton${status.index}').length > 0)) {
                            button.removeClass('active');
                            box.hide();
                        }
                    });
                });

            </script>

            <div class="my_poll_tab">
                <table width="100%" border="0" cellspacing="0" cellpadding="0">
                    <tr>
                        <td width="15%">
                            <div id="piecontainersmall${status.index}"
                                 style="width: 100px; height: 100px;">
                            </div>
                        </td>
                        <td width="59%"><h3><a href="#" onclick="window.open('<%=Utility.getServerURL(request)%>/${userDomainPageName}/${myPollPage.pageName}.cyt','_blank');" id="viewpage" target="_blank">${myPollPage.incyyte}</a></h3>
                            <p>Created: <span id="createdDateVal_${myPollPage.id}"> ${myPollPage.createdDate}</span></p>
                            <p>Closure: <span id="closingDateVal_${myPollPage.id}"> ${myPollPage.closureDate}</span>&nbsp;<span id="selecttimeVal_${myPollPage.id}" ></span></p>
                        </td>

                        <td width="26%" >
                            <a title="Share Page" id="displaySharedEmailList" onclick="displaySharedPopup('${myPollPage.incyyteCode}')" class="button_gray" style="width:166px;">
                                <span class="title_gray share_page_bot_ie8">Share Page</span>
                            </a>

                            <!-- Options Drop Box Starts Here -->
                            <div id="dropBoxContainer${status.index}" class="dropBoxContainer">
                                <a href="#" id="dropBoxButton${status.index}" class="dropBoxButton">
                                    <span>Options</span><em></em>
                                </a>
                                <div style="clear:both"></div>
                                <div id="dropBox${status.index}" class="dropBox">
                                    <div id="dropBoxContent${status.index}" class="dropBoxContent share_option_BoxContent_ie8">
                                        <ul>
                                            <li><a href="<%=Utility.getServerURL(request)%>/${userDomainPageName}/${myPollPage.pageName}.cyt" onclick="window.open('<%=Utility.getServerURL(request)%>/${userDomainPageName}/${myPollPage.pageName}.cyt','_blank');" target="_blank">Preview This Poll Page</a></li>
                                            <li><a href="#" onclick="display_datepicker('${myPollPage.id}');" class="inline">Edit Closing Time &amp; Date</a></li>
                                            <c:choose>
                                                <c:when test="${myPollPage.published}">
                                                    <li><a href="#" onclick="unpublish_poll('${myPollPage.id}');" id="unpublich_poll_page" >Unpublish This Poll Page<span>Page will no longer be accessible</span></a></li>
                                                </c:when>
                                                <c:otherwise>
                                                    <li><a href="#" onclick="publish_poll('${myPollPage.id}');" id="publich_poll_page" >Publish this poll page</a></li>
                                                </c:otherwise>
                                            </c:choose>

                                            <li><a href="#" onclick="delete_incyyte('${myPollPage.id}','mypolls');" id="delete_incyyte" >Delete inCyyte Poll <span>The attached poll will be deleted</span></a></li>
                                        </ul>
                                    </div>
                                </div>
                            </div>
                            <!-- Options Drop Box Ends Here -->
                        </td>
                    </tr>
                </table>
            </div>

            <div class="my_poll_detail">
                <table width="100%" border="0" cellspacing="0" cellpadding="0">

                    <tr>
                        <td width="14%">&nbsp;</td>
                        <td width="86%" class="my_poll_heading"> <%=Utility.getServerURL(request)%>/${userDomainPageName}/${myPollPage.pageName}.cyt </td>
                    </tr>
                    <tr>
                        <td height="24">&nbsp;</td>
                        <td align="right" class="font_14px">
                            <a href="#" id="d_clip_button${status.index}" data-clipboard-text="<%=Utility.getServerURL(request)%>/${userDomainPageName}/${myPollPage.pageName}.cyt" >Copy Page Address</a> |
                            <a href="javascript:shareToGroup(${myPollPage.id})" id="displayGroupList">Send to Group</a> | <a href="mailto:name@domain.com?subject=check out my new poll page&body=<%=Utility.getServerURL(request)%>/${userDomainPageName}/${myPollPage.pageName}.cyt">Send via Email</a>
                        </td>
                    </tr>
                    <script language="JavaScript">
                        var clip = new ZeroClipboard( $('#d_clip_button${status.index}') );
                    </script>
                </table>
            </div>
        </c:if>
    </c:forEach>
</div>
<div id="sharedPollPages" class="tab_content">
    <c:forEach var="mySharePollPage" items="${mySharedPollPages}" varStatus="status">
        <c:if test="${not mySharePollPage.deleted}">
            <script type="text/javascript">
                <!--
                $(document).ready(function () {
                    var chart = new Highcharts.Chart({
                        chart: {
                            renderTo: 'sharedPiecontainersmall${status.index}',
                            plotBackgroundColor: null,
                            plotBorderWidth: null,
                            plotShadow: false
                        },
                        title: {
                            text: ''
                        },
                        tooltip: {
                            pointFormat: '<b>{point.percentage}%</b>',
                            percentageDecimals: 1
                        },
                        plotOptions: {
                            pie: {
                                size: 60,
                                allowPointSelect: false,
                                cursor: 'pointer',
                                dataLabels: {
                                    enabled: false
                                },
                                showInLegend: false
                            }
                        },
                        series: [{
                            type: 'pie',
                            name: 'Total Response 1,234',
                            data: [
                                ['Yes',    40],
                                ['No',     40],
                                ['May be',   20]
                            ]
                        }]
                    });
                });
                //-->
            </script>

            <div class="my_shared_poll_tab">
                <table width="100%" border="0" cellspacing="0" cellpadding="0">
                    <tr>
                        <td width="15%">
                            <div id="sharedPiecontainersmall${status.index}"
                                 style="width: 100px; height: 100px;">
                            </div>
                        </td>
                        <td width="59%"><h3><a href="#" onclick="window.open('<%=Utility.getServerURL(request)%>/${mySharePollPage.createdBy}/${mySharePollPage.pageName}.cyt','_blank');" id="viewpage" target="_blank">${mySharePollPage.incyyte}</a></h3>
                            <p>Created: <span id="createdDateVal_${mySharePollPage.id}"> ${mySharePollPage.createdDate}</span></p>
                            <p>Closure: <span id="closingDateVal_${mySharePollPage.id}"> ${mySharePollPage.closureDate}</span>&nbsp;<span id="selecttimeVal_${mySharePollPage.id}" ></span></p>
                        </td>

                        <td width="26%" >
                            <a title="Share Page" id="javascript:void(0);" onclick="window.open('<%=Utility.getServerURL(request)%>/${mySharePollPage.createdBy}/${mySharePollPage.pageName}.cyt','_blank');" class="button_gray" style="width:166px;">
                                <span class="title_gray share_page_bot_ie8">View Poll Page</span>
                            </a>
                        </td>
                    </tr>
                </table>
            </div>
        </c:if>
    </c:forEach>
</div>
</div>
</form:form>

<div id="smart-paginator" style="margin: auto;">
    <!-- Pagination start---->
    <div class='pagination'>
        <%--For displaying Previous link except for the 1st page --%>
        <c:if test="${currentPage != 1}">
            <a class='prev page-numbers'
               href="dashpost.cyt?page=${currentPage - 1}&param=${text}">Prev</a>
        </c:if>
        <%--For displaying Page numbers.
              The when condition does not display a link for the current page--%>
        <c:forEach begin="1" end="${pollnoOfPages}" var="i">
            <c:choose>
                <c:when test="${currentPage eq i}">
                    <span class='page-numbers current'>${i}</span>
                </c:when>
                <c:otherwise>
                    <c:if test="${i <= 5}">
                        <a class='page-numbers'
                           href="dashpost.cyt?page=${i}&param=${text}">${i}</a>
                    </c:if>
                    <c:if test="${i > 5}">
                        <c:if test="${i+1 == pollnoOfPages}">
                            <a class='page-numbers'>..</a>
                            <a class='page-numbers'
                               href="dashpost.cyt?page=${i}&param=${text}">${i}</a>
                        </c:if>
                        <c:if test="${i == pollnoOfPages}">
                            <a class='page-numbers'
                               href="dashpost.cyt?page=${i}&param=${text}">${i}</a>
                        </c:if>
                    </c:if>
                </c:otherwise>
            </c:choose>
        </c:forEach>
        <%--For displaying Next link --%>
        <c:if test="${currentPage lt pollnoOfPages}">
            <a class='next page-numbers'
               href="dashpost.cyt?page=${currentPage + 1}&param=${text}">Next</a>
        </c:if>
    </div>
    <!-- Pagination ends---->
</div>
<div style='display:none'>
    <input type="hidden" id="alternate" size="11"  />

    <div id="modal_datepicker">
        <h3 class="font_20px">Change the closing date</h3>

        <div id="datepicker"></div>
        <h3 class="font_20px" style="padding-top:15px;">Change the closing Time</h3>

        <p>

        <div id="form">
            <label style="width:130px;">
                <select id="selecttime">
                    <option value="01:00">01:00</option>
                    <option value="02:00">02:00</option>
                    <option value="03:00">03:00</option>
                    <option value="04:00">04:00</option>
                    <option value="05:00">05:00</option>
                    <option value="06:00">06:00</option>
                    <option value="07:00">07:00</option>
                    <option value="08:00">08:00</option>
                    <option value="09:00">09:00</option>
                    <option value="10:00">10:00</option>
                    <option value="11:00">11:00</option>
                    <option value="12:00" selected="selected">12:00</option>
                    <option value="13:00">13:00</option>
                    <option value="14:00">14:00</option>
                    <option value="15:00">15:00</option>
                    <option value="16:00">16:00</option>
                    <option value="17:00">17:00</option>
                    <option value="18:00">18:00</option>
                    <option value="19:00">19:00</option>
                    <option value="20:00">20:00</option>
                    <option value="21:00">21:00</option>
                    <option value="22:00">22:00</option>
                    <option value="23:00">23:00</option>
                    <option value="24:00">24:00</option>
                </select> </label>

            <div title="Save" id="modal_datepicker_btn" class="button_green" style="width:80px; float:right">
                <span class="title_green share_date_bot_ie8"  onclick="">SAVE</span>
            </div>
        </div>
        </p>
    </div>
</div>

</div>

<%-- START SHARE A POLL TO GROUP--%>
<div style="display: none;">
    <div id="groupList">
        <div id="share_contacts">
            <h3 class="heading1" style="padding:2px 0 8px 0px;">Share the Poll to a Group</h3>
            <div id="scrollbar1">
                <div class="scrollbar">
                    <div class="track">
                        <div class="thumb">
                            <div class="end"></div>
                        </div>
                    </div>
                </div>
                <div style="width:380px; height:4px;"></div>
                <div class="viewport">
                    <div class="overview">
                        <div style="width:380px; height:4px;"></div>
                        <div style="width:400px; height:250px; overflow:auto;">
                            <div id="contactScroll" style="width:380px; height:250px; overflow-x:hidden; overflow-y:auto;">
                                <table width="380" border="0" id="groupsTable"  cellspacing="0" cellpadding="0">
                                    <c:forEach var="group"  items="${groups}">
                                        <tr>
                                            <td>&nbsp;</td>
                                            <td><span id='groupRadio_${group.groupID}' name="groupRadio" value="${group.groupID}">&nbsp;${group.groupName}</span></td>
                                        </tr>
                                    </c:forEach>
                                </table>
                            </div>
                        </div>
                    </div>
                </div>
                <div style="width:380px; height:10px;"></div>
                <table width="380" border="0" cellspacing="0" cellpadding="0">
                    <tr>
                        <td>
                            <button type="button" title="Import" id="add_email_btn" class="button_green1"
                                    onclick="javascript:sharePollToGroup();"
                                    style="width:150px; margin:20px 0 0 100px; float:right;"><span class="title_green1">Send to Group</span>
                            </button>
                        </td>
                    </tr>
                </table>
            </div>
        </div>
    </div>
    <%-- END SHARE A POLL TO GROUP--%>

    <jsp:include page="../include/shareEmailPopup.jsp" />
    <jsp:include page="../include/pollEmailCount.jsp" />
