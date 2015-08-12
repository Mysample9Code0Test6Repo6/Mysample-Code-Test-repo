<%@ include file="/WEB-INF/jsp/common/include.jsp" %>
<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="content-type" content="text/html; charset=UTF-8">
<link rel="shortcut icon" href="favicon.ico" />

<title>inCyyte - Create Account</title>
<meta charset="utf-8">
<link rel="stylesheet" href="ui/css/reset.css">
<link rel="stylesheet" href="ui/css/style.css">
<link rel="stylesheet" href="ui/css/layout.css">
<link rel="stylesheet" href="ui/css/buttons.css"  media="screen">
<link rel="stylesheet" href="ui/css/style_login.css">
<link rel="stylesheet" href="ui/css/accordionmenu.css" type="text/css">
<link rel="stylesheet" href="ui/css/icons_sprites.css">
<link rel="stylesheet" href="fonts/fonts_stylesheet.css">
<link rel="stylesheet" href="ui/css/prettyCheckboxes.css" type="text/css" media="screen">
<link rel="stylesheet" href="ui/css/themes/base/jquery.ui.all.css">
<link rel="stylesheet" href="ui/css/jquery.validate.css">
<link rel="stylesheet" href="ui/css/validate/screen.css">


<script src="ui/js/jquery-1.7.1.js"></script>
<script src="ui/js/login.js"></script>
<script src="ui/js/create_acct.js"></script>
<script src="ui/js/jquery.color.js"></script>
<script src="ui/js/search_script.js"></script>
<script src="ui/js/jquery.limit.js"></script>
<script src="ui/js/prettyCheckboxes.js" type="text/javascript" charset="utf-8"></script>
<script type="text/javascript" src="ui/js/autoresize.jquery.js"></script>
<script type="text/javascript" src="ui/js/easySlider1.7.js"></script>
<script src="ui/js/accordian/jquery.ui.core.js"></script>
<script src="ui/js/accordian/jquery.ui.widget.js"></script>
<script src="ui/js/accordian/jquery.ui.accordion.js"></script>
<script type="text/javascript" src="ui/js/common/jquery.form.js"></script>
<script type="text/javascript" src="ui/js/validate/jquery.validate.js" ></script>

<script src="ui/js/home/signup_validations.js"></script>

<script>
	$(function() {
		$( "#accordion" ).accordion();
	});
</script>

<script type="text/javascript">
		$(document).ready(function() {
			// Store variables
			var accordion_head = $('.accordion > li > a'),
				accordion_body = $('.accordion li > .sub-menu');
			// Open the first tab on load
			accordion_head.first().addClass('active').next().slideDown('normal');
			// Click function
			accordion_head.on('click', function(event) {
				// Disable header links
				event.preventDefault();
				// Show and hide the tabs on click
				if ($(this).attr('class') != 'active'){
					accordion_body.slideUp('normal');
					$(this).next().stop(true,true).slideToggle('normal');
					accordion_head.removeClass('active');
					$(this).addClass('active');
				}

			});

			if( $("#username").val() != "")
				processData("Uname",$("#username").val(), getContextPath());
			if( $("#su_email").val() != "")
				processData("Email",$("#su_email").val(), getContextPath());

		    $("#username").change(function() {
		    	if (clientValidation("Uname"))
		    		processData("Uname",$("#username").val(), getContextPath());
		    });
		    $("#su_email").change(function() {		    	
		    	if (clientValidation("Email"))
		    		processData("Email",$("#su_email").val(), getContextPath());
		    });
		    $("#su_password").change(function() {
		    	clientValidation("Pwd");
		    });

		});

		function getContextPath() {
			return "<%=request.getContextPath()%>";
		}

	</script>

<link rel="stylesheet" type="text/css" href="ui/css/ratingbar.css" />
<script type="text/javascript" src="ui/js/jquery.ratingbar.js"></script>
<script type="text/javascript" charset="utf-8">
$(document).ready(function() {
	$('.gender_rating').ratingbar();

	$('#login2').click(function(){
		window.location = "<%=request.getContextPath()%>"+"/login.cyt";	
    });

});
</script>
<script type="text/javascript" src="ui/js/ddaccordion.js"></script>
<script type="text/javascript">

//Initialize :
ddaccordion.init({
	headerclass: "question_tab", //Shared CSS class name of headers group
	contentclass: "question_detail", //Shared CSS class name of contents group
	revealtype: "click", //Reveal content when user clicks or onmouseover the header? Valid value: "click", "clickgo", or "mouseover"
	mouseoverdelay: 200, //if revealtype="mouseover", set delay in milliseconds before header expands onMouseover
	collapseprev: false, //Collapse previous content (so only one open at any time)? true/false 
	defaultexpanded: [], //index of content(s) open by default [index1, index2, etc]. [] denotes no content.
	onemustopen: true, //Specify whether at least one header should be open always (so never all headers closed)
	animatedefault: false, //Should contents open by default be animated into view?
	scrolltoheader: false, //scroll to header each time after it's been expanded by the user?
	persiststate: false, //persist state of opened contents within browser session?
	toggleclass: ["closedquestion", "openquestion"], //Two CSS classes to be applied to the header when it's collapsed and expanded, respectively ["class1", "class2"]
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
<script type="text/javascript" src="ui/js/autoresize.jquery.js"></script>
<script>
$(function () {
  $("textarea").autoresize();
})
</script>
<script type="text/javascript" src="ui/js/jquery.placeholder.js"></script>
<script>
  $(function() {
    $('input, textarea').placeholder();
   });
</script>
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

 <!--[if IE 8]>
    <link href="ui/css/ie8.css" media="screen" rel="stylesheet" type="text/css" />
    <![endif]-->

    <!-- Popup Script Start here -->
    <script src="ui/js/read_terms.js"></script>
    <link href="ui/css/incyyte.css" media="screen" rel="stylesheet" type="text/css" />
    <!-- Popup Script End here -->
</head>
<%--<script type="text/javascript">
    $(window).load(function() {
        $(".loader").fadeOut("slow");
    })
</script>--%>
<body>
<div id="gradient">
  <div class="extra">
  <!-- include header -->
  <jsp:include page="includes/createAcctHeader.jsp" />
  
    <div class="main">
      <!--content -->
      <article id="content">
        <div id='mobilebanner'><a href="#" id="login2"><img src="ui/images/mobile_login_button.png"></a></div>          
        <div id="main_content">

          <div id="activate_your_acct_msg">
           <img src="ui/images/smiley_red.png"> <h3 class="heading1_creatacct" style="margin-top:-80px;padding:10px 0 135px 135px;">
           Congratulations!! Thank you for joining inCyyte.<br><br> We have sent you an email to activate your account. </h3>
          </div>
   		  <BR>
          <!-- 2. Choose your Answers starts-->
          <div id="choose_your_answers_heading">
            <h3 class="heading1_creatacct">Join inCyyte Today. </h3>
          </div>
          
          <div id="choose_your_answers">          
            <div id="answer_container">
            <%
            String param = request.getParameter("inviteNew"); 
            if (param !=null && param.equals("Y")){ %>
             <div id="loginError" class="common_error failure"><strong>Thanks!</strong> Now make sure your vote is counted.. sign-up in 2 simple steps!</div>
			<%} %>            
              <form:form  id="createacct_form"  commandName="signUpForm" method="post"> 
              
                <div id="uname_one">
                	<form:input path="username" id="username" placeholder="Username" onFocus="this.placeholder = ''" onBlur="this.placeholder = 'Username'"/>
	                <img src="ui/images/indicator-loader.gif" id="UnameLoader" width="32" height="32" style="padding:8px 0 0 0;">&nbsp;
	                <span id="defaultUnameNote" style="padding-left:24px;color:#2E2E2E; font:13px 'bariol_regularregular', 'ie8_bariol_regular';">Don't worry, you can change it later.</span>&nbsp;
	                <span id="invalidUname" class="invalid" style="padding-left:24px;	color:#ec3f41;">Username taken.</span>&nbsp;
	                <span id="validUname" class="valid" style="padding-left:24px; color:#3a7d34;">Username available.</span>                
	                </div>
                <div id="email_two">
                  <form:input path="su_email" id="su_email" placeholder="Email" onFocus="this.placeholder = ''" onBlur="this.placeholder = 'Email'"/>
                <img src="ui/images/indicator-loader.gif" id="EmailLoader" width="32" height="32" style="padding:8px 0 0 0;">&nbsp;
	            <span id="defaultEmailNote" style="padding-left:24px;color:#2E2E2E; font:13px 'bariol_regularregular', 'ie8_bariol_regular';">Use your personal email address</span>&nbsp;
                <span id="invalidEmail" class="invalid" style="padding-left:24px;letter-spacing: 0.5px;	color:#ec3f41;">inValid Email.</span>&nbsp;
                <span id="validEmail" class="valid" style="padding-left:24px; color:#3a7d34;">Email looks great.</span>
                </div>
                 
                 <div id="pwd_three">
                  <form:password path="su_password" id="su_password" placeholder="Re-enter Password" onFocus="this.placeholder = ''" onBlur="this.placeholder = 'Create Password'"/>
                <img src="ui/images/indicator-loader.gif" id="PwdLoader" width="32" height="32" style="padding:8px 0 0 0;">&nbsp;
	            <span id="defaultPwdNote" style="padding-left:24px;color:#2E2E2E; font:13px 'bariol_regularregular', 'ie8_bariol_regular';">Enter 7 characters or more! Be tricky.</span>&nbsp;
                <span id="invalidPwd" class="invalid" style="padding-left:24px;	color:#ec3f41;">invalid password</span>&nbsp;
                <span id="validPwd" class="valid" style="padding-left:24px; color:#3a7d34;">Password looks great.</span>
                </div>
              </form:form> 
             </div>
          </div>

          <!-- 2. Terms of Service-->
          <div id="term_settings">
            <h3 class="heading1_creatacct" style="padding:0px 0 33px 50px;">Terms of Service</h3>
            <!-- div id="help"><a href="" title="Help"></a></div-->
            <div id="term_settings_left">
              <form class="checkboxDemo" >
                <input type="checkbox" id="checkbox-1" value="checkbox-1" />
                <label for="checkbox-1" tabindex="1">I Accept these terms <span class="help_small"><img src="ui/images/help_icon_small.png" align="absmiddle"></span></label>
                <span id="term_notice" style="padding:0px 0px 0px 90px; color:red;">Please read the terms of service.</span>
              </form>
            </div>
              <div id="term_settings_right" style="padding-top:24px ; font-size:20px; color:#1b4d5f; text-align:center;"><a href="#" class="read_terms" > Read Terms of Service</a>
              </div>
          </div>
          <!-- 3. Create Account-->
          <div id="askyour_que_btn"><a href="#" title="CREATE ACCOUNT" id="create_acct" class="button_red" style="width:240px;"> <span class="title_red title_red_ie8">CREATE ACCOUNT</span></a></div>
          
        </div>
      </article>
      <!--content end -->
    </div>
  </div>
    <!--pop up terms content start here-->
    <div id="read_terms_txt">
         <div id="close1"></div>

    <div class="main" id="1">
        <div class="privacy_content_part_popup">
        <div class="privacy_content_heading">Terms Of Use</div>
        <div class="privacy_content_part_sroll">
        <div class="privacy_content">
            <h3>Please read these terms carefully before using this
                website</h3><br>
            <h2>1. TERMS OF WEBSITE USE</h2>
            <P>
                These terms of use (together with the documents referred to in
                them) tell you the terms on which you may make use of our website
                ("Site"), whether as a guest or as a member. Use
                of the Site includes accessing, browsing, or registering as a
                member to use the Site.
            </P>

            <P>Please read these terms of use carefully before you start
                to use the Site, as these will apply to your use of the Site. We
                also recommend that you print a copy for future reference.</P>
            <P>By using the Site, you confirm that you accept these terms
                of use and that you agree to comply with them.</P>
            <P>If you do not agree to these terms of use, you must not use
                the Site.</P>
            <P>
                These terms of use are only available in the English
                language.
            </P>


        </div>
        <div class="privacy_content"><br>
            <h2>2. OTHER APPLICABLE TERMS</h2>
            <P>These terms of use refer to the following additional terms,
                which also apply to your use of the Site:</P>
            <ul>
                <li>Our <a href="termsConditions.cyt">Membership Terms and Conditions</a> ,
                    which set out the terms on which you may sign-up to become a
                    member of the Site.
                </li>

                <li><a href="privacy.cyt">Our Privacy Policy</a>, which sets out the
                    terms on which we process any personal information we collect
                    from you, or that you provide to us. By using the Site, you
                    consent to such processing and you warrant that all information
                    provided by you is accurate.</li>

                <li>Our <a href="welcome.cyt">Cookie Policy</a>, which sets out
                    information about the cookies we use on the Site.
                </li>
            </ul>

        </div>

        <div class="privacy_content_part_display">
        <div class="privacy_content">
        <h2>3. INFORMATION ABOUT US</h2>
        <P>
            The Site is operated by Incyyte Limited ("inCyyte" or "we"). We
            are a limited company registered in England and Wales under
            company number 07884094 and have our registered office at 15
            Holly Drive, Brandon Grove, South Ockendon, Essex RM15 6TG,
            United Kingdom. Our main trading address is 24 CEME Innovation
            Centre, Marsh Way, Rainham, Essex RM13 8EU, United Kingdom. Our
            VAT number is.
        </P> <br>

        <h2>5. CHANGES TO THESE TERMS</h2>
        <P>
            We may revise these terms of use at any time. If we make an
            amendment to these terms of use, we will post an updated set of
            terms of use on the Site and it is your responsibility to check
            these terms of use from time to time to take notice of any
            changes we have made. If we make a change to these terms of use
            which we consider to be material, we will <strong>post
            a change notice the next time you access the Site informing you
            that the terms or use have been updated and/or send members an
            email notice of the changes in advance of implementing the
            changes. </strong>
        </P>
        <P>Your continued use of the Site following any such
            amendment will signify your acceptance of the revised terms of
            use.</P>

        <div class="privacy_content"><br>
        <h2>7. ACCESSING OUR SITE</h2>
        <P>You may access and use the Site anonymously as a visitor.
            If you wish to create and/or participate in polls and to access
            other services and content available on the Site, you will need
            to register as a member.</P>

        <P>The Site is made available free of charge. However,
            please be aware that if you access the Site from a mobile
            device, your mobile network operator may charge you a data
            usage fee. We recommend speaking to your mobile network
            operator for more details about these types of fees.</P>

        <P>Whilst we try to ensure that the Site is available 24
            hours a day, we do not give any guarantee that the Site, or any
            content on it, will always be available or be uninterrupted.
            Access to the Site is permitted on a temporary basis. We may
            suspend, withdraw, discontinue or change all or any part of the
            Site without notice. We will not be liable to you if for any
            reason the Site is unavailable at any time or for any period.</P>

        <P>You are responsible for making all arrangements necessary
            for you to have access to the Site.</P>

        <P>You are also responsible for ensuring that all persons
            who access the Site through your internet connection are aware
            of these terms of use and other applicable terms and
            conditions, and that they comply with them.</P>

        <div class="privacy_content"><br>
            <h2>10. INTELLECTUAL PROPERTY RIGHTS</h2>
            <P>
                The Site, incyyte.com (and all content
                provided on the Site and through the services that we provide
                on the Site, including, without limitation, the "inCyyte"
                trademark as detailed in section 19 below, inCyyte-created
                polls, the Site text, graphics and images and all other
                materials, information, documents and materials on the Site,
                other than Your Content and Third Party Content (both as
                defined in section 13 below), (collectively, "inCyyte
                Content") are provided to you by inCyyte and are the
                copyrighted and/or trademarked work of inCyyte or inCyyte's
                contributors.
            </P>

            <P>We own or are licensed to use all intellectual property
                rights in the inCyyte Content and the Site. We grant to you a
                limited, personal, non-exclusive and non-transferable license
                to use and to display the inCyyte Content solely for your use
                in connection with the Site and as otherwise permitted under
                these terms of use.</P>
            <P>You may print off one copy, and may download extracts,
                of any page(s) from the Site for personal, informational and
                non-commercial use only and you may draw the attention of
                others within your organisation to content posted on the Site.
            </P>

            <P>You must not modify the paper or digital copies of any
                materials you print off or download from the Site in any way,
                and you must not use any illustrations, photographs, video or
                audio sequences or any graphics separately from any
                accompanying text.</P>

            <P>Our status (and that of any identified contributors) as
                the authors of inCyyte Content must always be acknowledged.</P>

            <P>You must not use any inCyyte Content for commercial
                purposes without obtaining our written permission to do so.</P>

            <P>If you print off, copy or download or otherwise use
                inCyyte Content in breach of these terms of use, your right to
                use the Site will cease immediately and you must, at our
                option, return or destroy any copies of the materials you have
                made.</P>

            <P>Except as expressly permitted in these terms of use, you
                have no right to modify, edit, copy, reproduce, create
                derivative works of, reverse engineer, alter, enhance or in
                any way exploit any of the inCyyte Content in any manner. The
                license granted to you under this section 10 to use the
                inCyyte Content terminates automatically, without prior notice
                to you, if you breach these terms of use in any way. Except
                for the limited rights granted hereunder, you acknowledge that
                you have no right, title or interest in or to any inCyyte
                Content.</P>

        </div>

        <div class="privacy_content"><br>
            <h2>11. NO RELIANCE ON POLL OUTCOMES AND OTHER CONTENT</h2>
            <P>The results of polls and other content found on the Site
                are provided for general information only. They are not
                intended to provide accurate advice on which you should rely.
                You must obtain professional or specialist advice before
                taking, or refraining from, any action on the basis of the
                results of polls or any other content found on the Site.</P>
            <P>Although we make reasonable efforts to update the
                information on the Site, we make no representations,
                warranties or guarantees, whether express or implied, that the
                content on the Site is accurate, complete or up-to-date.</P>

        </div>

        <div class="privacy_content"><br>
            <h2>12. LIMITATION OF OUR LIABILITY</h2>
            <P>Nothing in these terms of use excludes or limits our
                liability for death or personal injury arising from our
                negligence, or our fraud or fraudulent misrepresentation, or
                any other liability that cannot be excluded or limited by
                English law.</P>

            <P>The Site is provided by inCyyte on an "as is" and "as
                available" basis. To the extent permitted by law, we exclude
                all conditions, warranties, representations or other terms
                which may apply to the Site or any content on it, whether
                express or implied.</P>

            <P>We will not be liable to any user of the Site for any
                loss or damage, whether in contract, tort (including
                negligence), breach of statutory duty, or otherwise, even if
                foreseeable, arising under or in connection with:</P>

            <ul>
                <li>use of, or inability to use, the Site; or</li>
                <li>use of or reliance on any content displayed on the
                    Site.</li>
            </ul>
            <ul>
                <P>
                    <strong>If you are using the Site for any business
                        or commercial purpose, please note that in particular, we
                        will not be liable for:</strong>
                </P>
                <li>loss of profits, sales, business, or revenue;</li>
                <li>business interruption;</li>
                <li>loss of anticipated savings;</li>
                <li>loss or corruption of data, information or software;</li>
                <li>loss of business opportunity, goodwill or reputation;
                    or</li>
                <li>any indirect or consequential loss or damage.</li>
            </ul>

            <P>We will not be liable for any loss or damage caused by a
                virus, distributed denial-of-service attack, or other
                technologically harmful material that may infect your computer
                equipment, computer programs, data or other proprietary
                material due to your use of the Site or to your downloading of
                any content on it, or on any website linked to it.</P>

            <P>We assume no responsibility for the content of websites
                linked on the Site. Such links should not be interpreted as
                endorsement by us of those linked websites. We will not be
                liable for any loss or damage that may arise from your use of
                them.</P>

            <P>
                To the extent inCyyte's liability in respect of these terms of
                use is not excluded, inCyyte's total aggregate liability to
                you in respect of all losses arising under or in connection
                with the Agreement, whether in contract, tort (including
                negligence), breach of statutory duty, or otherwise, shall in
                no circumstances exceed �100.
            </P>
            <P>
                Different limitations and exclusions of liability will apply
                to liability arising as a result of membership to the Site,
                which are set out in our <a href="termsConditions.cyt">Membership Terms and
                Conditions</a>.
            </P>


        </div>

        <div class="privacy_content"><br>
            <h2>14. USE OF OUR SITE BY MINORS</h2>
            <P>Use of the Site, including any participation in polls,
                by individuals under the age of majority in his or her place
                of residence ("Minors") is subject to the consent of their
                parent or guardian. We advise parents or guardians who permit
                Minors to use an interactive service that it is important that
                they communicate with these individuals about their safety
                online, as moderation is not foolproof. Minors who are using
                any interactive service should be made aware of the potential
                risks to them.</P>

            <P>Further, you must not use the Site to send polls or
                other materials to Minors unless you are permitted to do so
                under all applicable laws in the UK and in any country from or
                to which such materials are transmitted or received, and you
                shall not send polls or other materials to Minors that would
                subject inCyyte to any local or international law, rule or
                regulation governing children's privacy, rights of personality
                or otherwise related to protecting Minors.</P>

        </div>

        <div class="privacy_content"><br>
            <h2>15. LINKING TO OUR SITE</h2>
            <P>You may provide links to (but may not replicate) our
                home page or any polls you have posted on the Site through
                other mainstream social networks in which you participate or
                through your own personal or business website, provided you do
                so in a way that is fair and legal and does not damage our
                reputation or take advantage of it.</P>
            <P>You must not establish a link in such a way as to
                suggest any form of association, approval or endorsement on
                our part where none exists.</P>
            <P>We reserve the right to withdraw linking permission
                without notice.</P>
            <P>The website in which you are providing the link must
                comply in all respects with the Content Rules.</P>
            <P>
                If you wish to provide links to any content on the Site other
                than as set out above, please contact <a
                    href="mailto:info@incyyte.com">info@incyyte.com</a>,
                requesting our permission to do so.
            </P>
        </div>

        <div class="privacy_content">
            <h2>17. SUSPENSION AND TERMINATION</h2>
            <P>We will determine, in our discretion, whether there has
                been a breach of these terms of use through your use of the
                Site. When a breach of these terms of use has occurred, we may
                take such action as we deem appropriate.</P>
            <P>Failure to comply with these terms of use may result in
                inCyyte taking all or any of the following actions:</P>
            <ul>
                <li>immediate, temporary or permanent withdrawal of your
                    right to use the Site;</li>
                <li>immediate, temporary or permanent deactivation of
                    your account if you have one;</li>
                <li>immediate, temporary or permanent removal of any
                    polls, postings or other content uploaded by you to the Site;</li>
                <li>issue of a warning to you;</li>
                <li>legal proceedings against you for reimbursement of
                    all costs (including, but not limited to, reasonable
                    administrative and legal costs) resulting from the breach;</li>
                <li>further legal action against you; and/or</li>
                <li>disclosure of such information to law enforcement
                    authorities as we reasonably feel is necessary.</li>
            </ul>
            <P>If we deactivate your account, you may not create
                another account to gain access to the Site unless we give you
                permission in writing to do so.</P>
            <P>We exclude liability for actions taken in response to
                breaches of these terms of use. The responses described in
                this section 17 are not limited, and we may take any other
                action we reasonably deem appropriate.</P>

        </div>
        </div>
        </div>

        <div class="privacy_content">
        <h2>4. SITE FEATURES</h2>
        <P>
            The Site includes social and interactive features, including the
            ability to post and share polls and content and to make certain
            information public. You can learn more about these features by <a
                href="#">clicking here</a>.
        </P>
        <P>You acknowledge that shared or publicly available
            information may be used and re-shared by other users of the Site
            and across the web, and we strongly advise you to use the Site
            carefully and, in the case of members, to manage your account
            settings regularly. We have no responsibility for any
            information you choose to share or make public through your use
            of the Site and it is your responsibility to remove any
            information that you no longer wish to be displayed in
            connection with your account.</P>
        <h2>6. CHANGES TO OUR SITE</h2>
        <P>We may update the Site and any of its content from time to
            time. However, please note that any of the content on the Site
            may be out of date at any given time.</P>
        <P>We are under no obligation to update, upgrade, maintain or
            support the Site, or to provide any specific content through the
            Site.</P>
        <P>We do not guarantee that the Site, or any content on it,
            will be free from errors or omissions.</P>

        <div class="privacy_content">
        <h2>8. YOUR ACCOUNT AND PASSWORD</h2>
        <P>If you choose to become a member of the Site, you will
            need to choose a unique username and password which will enable
            you to access the members only areas of the Site. You must
            treat these user credentials as confidential and must not
            disclose them to any third party.</P>
        <P>We have the right to disable your username and password
            at any time, if in our reasonable opinion we consider that you
            have failed to comply with any of the provisions of these terms
            of use.</P>
        <P>If you know or suspect that anyone other than you knows
            your username and/or password, you must promptly notify us at
            [info@incyyte.com].</P>


        <div class="privacy_content">
            <h2>9. PROHIBITED USES</h2>

            <P>
                <strong>You may use the Site only for lawful
                    purposes. You may not use the Site:</strong>
            </P>
            <ul>
                <li>in any way that breaches any applicable local,
                    national or international law or regulation;</li>
                <li>in any way that is unlawful or fraudulent, or has any
                    unlawful or fraudulent purpose or effect;</li>
                <li>for the purpose of harming or attempting to harm
                    minors in any way;</li>
                <li>to send, knowingly receive, upload, download, use or
                    re-use any material which does not comply with our Content
                    Rules as defined in section 13 below;</li>
                <li>to transmit, or procure the sending of, any
                    unsolicited or unauthorised advertising or promotional
                    material or any other form of similar solicitation (spam); or</li>
                <li>to knowingly transmit any data or send or upload any
                    material that contains viruses, trojan horses, worms,
                    time-bombs, keystroke loggers, spyware, adware or any other
                    harmful programs or similar computer code designed to
                    adversely affect the operation of any computer software or
                    hardware.</li>
            </ul>
            <P>You also agree not to:</P>
            <ul>
                <li>use spiders, robots or other automated data mining
                    technologies to catalogue, download, store or otherwise
                    reproduce, duplicate, copy or distribute any content
                    available on the Site or to manipulate the results of any
                    poll;</li>
                <li>otherwise reproduce, duplicate, copy, distribute or
                    sell any content available on the Site in breach of these
                    terms of use;</li>
                <li>engage in excessive usage of the Site, as determined
                    by inCyyte in its sole discretion, including usage that
                    adversely affects the speed, responsiveness, or functionality
                    of the Site, or disrupts the availability of the Site for
                    other users;</li>
                <li>frame portions of the Site on any other website or in
                    any way alter the appearance of the Site (including when
                    linking the Site from another website); or</li>
                <li>access without authority, interfere with, damage or
                    disrupt any part of the Site, any equipment or network on
                    which the Site is stored, any software used in the provision
                    of the Site or any equipment or network or software owned or
                    used by any third party.</li>
            </ul>
            <P>If you breach any part of this section 9, you will be
                liable to us and will indemnify us in full and on demand for
                your breach. This means you will compensate us for any loss or
                damage that we suffer as a result of your breach.</P>
            <P>
                If you believe that any other user of the Site is using the
                Site in breach of this section 9 or any other provisions of
                these terms of use, please notify us by sending an email to <a
                    href="mailto:info@incyyte.com">info@incyyte.com</a>
                confirming the username of the offending user and details of
                the alleged breach.
            </P>


        </div>

        <div class="privacy_content">
            <h2>13. RULES FOR UPLOADING CONTENT TO OUR SITE</h2>
            <P>Any polls, poll responses, information, data, text,
                software, music, sound, photographs, images, video messages or
                other materials that you post or upload to the Site or
                otherwise communicate through your use of the Site ("Your
                Content"), whether publicly posted or privately transmitted,
                belong to you and are your sole responsibility.</P>
            <P>Your Content must be accurate (where it states facts),
                be genuinely held (where it states opinions), must comply with
                applicable law in the UK and in any country from which it is
                posted and must not:</P>
            <ul>
                <li>contain any material which is defamatory of any
                    person;</li>
                <li>contain any material which is obscene, offensive,
                    hateful or inflammatory;</li>
                <li>promote sexually explicit material;</li>
                <li>promote violence;</li>
                <li>promote discrimination based on race, sex, religion,
                    nationality, disability, sexual orientation or age;</li>
                <li>infringe any copyright, database right or trade mark
                    of any other person;</li>
                <li>be likely to deceive any person;</li>
                <li>be made in breach of any legal duty owed to a third
                    party, such as a contractual duty or a duty of confidence;</li>
                <li>promote any illegal activity;</li>
                <li>be threatening, abuse or invade another's privacy, or
                    cause annoyance, inconvenience or needless anxiety;</li>
                <li>be likely to harass, upset, embarrass, alarm or annoy
                    any other person;</li>
                <li>be used to impersonate any person, or to misrepresent
                    your identity or affiliation with any person;</li>
                <li>give the impression that they emanate from inCyyte,
                    if this is not the case; or</li>
                <li>advocate, promote or assist any unlawful act such as
                    (by way of example only) copyright infringement or computer
                    misuse.</li>
            </ul>
            <P>You warrant that you will comply with all of the above
                rules ("Content Rules") in relation to Your Content and
                contacting other users of the Site, and further that any
                username that you register and/or use in connection with the
                Site will not breach the Content Rules. You will be liable to
                us and will indemnify us in full and on demand for any breach
                of this warranty. This means you will compensate us for any
                loss or damage that we suffer as a result of your breach of
                this warranty.</P>

            <P>
                Other than your personal information, which is covered under
                our <a href="privacy.cyt">Privacy Policy</a>, Your Content will be
                considered non-confidential and non-proprietary, and we have
                the right to use, copy, distribute and disclose to third
                parties Your Content for any purpose.
            </P>

            <P>We have the right to disclose your identity to any third
                party who is claiming that any of Your Content constitutes a
                violation of their intellectual property rights, or of their
                right to privacy. Further, we will fully cooperate with any
                law enforcement authorities or court order requesting that we
                disclose your identity.</P>

            <P>We will not be responsible, or liable to any third
                party, for the content or accuracy of Your Content or of any
                content (including poll responses) posted by any other user of
                the Site.</P>

            <P>We are under no obligation to oversee, monitor or
                moderate any poll or other content posted on the Site or
                otherwise transmitted using the Site by any user or other
                third party ("Third Party Content") , and we expressly exclude
                our liability for any loss or damage arising from Third Party
                Content, including any Third Party Content which does not
                comply with the Content Rules, regardless of any monitoring or
                moderation that we may choose to carry out.</P>

            <P>We have the right to remove any of Your Content from the
                Site without notice if we consider that it does not comply
                with any of the Content Rules. We may also delete Your Content
                from our databases at any time without notice. You are solely
                responsible for backing up Your Content. We shall not be
                liable for any removal of, deletion of or failure to retain
                any of Your Content.</P>

            <P>The views expressed by other users on the Site do not
                represent our views or values.</P>

        </div>

        <div class="privacy_content">
            <h2>16. THIRD PARTY LINKS AND RESOURCES IN OUR SITE</h2>
            <P>Where the Site contains links to other websites and
                resources provided by third parties, these links are provided
                for your convenience and information only. If you use these
                links, you will be leaving the Site and do so entirely at your
                own risk.</P>

            <P>We have not reviewed all of these third party websites
                and resources and we do not control or have any responsibility
                for the contents or availability of these websites or
                resources. We therefore do not endorse or make any
                representations about them or any results that may be obtained
                from using them.</P>
            <P>We encourage you to review the terms of use and privacy
                policy of any third party website which is linked to through
                the Site.</P>
        </div>

        <div class="privacy_content">
            <h2>18. APPLICABLE LAW</h2>
            <P>If you are a consumer, please note that these terms of
                use, its subject matter and its formation, are governed by
                English law. You and we both agree that the courts of England
                and Wales will have non-exclusive jurisdiction. However, if
                you are a resident of Northern Ireland you may also bring
                proceedings in Northern Ireland, and if you are resident of
                Scotland, you may also bring proceedings in Scotland.</P>

            <P>If you are a business, these terms of use, its subject
                matter and its formation (and any non-contractual disputes or
                claims) are governed by English law. We both agree to the
                exclusive jurisdiction of the courts of England and Wales.</P>

            <P>You acknowledge and agree that you are responsible for
                determining which country's laws may apply to your use of the
                Site and for assessing your obligations under such laws.</P>
        </div>

        <div class="privacy_content">
            <h2>19. TRADE MARKS</h2>
            <P>"inCyyte" is a UK registered trade mark of Incyyte
                Limited.</P>
        </div>

        <div class="privacy_content">
            <h2>20. CONTACT US</h2>
            <P>Questions, comments and requests regarding these terms
                of use are welcomed and should be addressed to
                [info@incyyte.com] or Incyyte Limited, 24 CEME Innovation
                Centre, Marsh Way, Rainham, Essex RM13 8EU, United Kingdom.
            <P>
                <strong>Thank you for visiting our Site.</strong>
            </P>
        </div>
        </div>
        </div>
        </div>
        <div class="privacy_content_part_display">
            <div class="footer_content">
                This is the first version of this Terms of Use and was uploaded
                on 16th February 2014.
            </div>
        </div>
        </div>
        </div>
    </div>

    </div>
    <div id="mask"></div>
    <!---pop up terms content start here-->
  <!-- include footer -->
<jsp:include page="includes/homeFooter.jsp" />
<script type="text/javascript" charset="utf-8">
    $(document).ready(function () {
        $('#checkboxDemo input[type=checkbox],#radioDemo input[type=radio]').prettyCheckboxes();
        $('.inlineRadios input[type=radio]').prettyCheckboxes({'display':'inline'});
        $('.checkboxDemo input[type=checkbox]').prettyCheckboxes({'display':'inline'});
    });
</script>
  <script type="text/javascript" src="ui/js/googleanalytics.js"></script>
  <script type="text/javascript">
$(document).keypress(function(event){
	var keycode = (event.keyCode ? event.keyCode : event.which);
	if(keycode == '13'){
		$("#create_acct").click();
	}
}); 
</script>
  
</body>
</html>
