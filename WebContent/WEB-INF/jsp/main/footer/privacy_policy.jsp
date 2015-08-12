<%@ include file="/WEB-INF/jsp/common/include.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="content-type" content="text/html; charset=UTF-8">
<link rel="shortcut icon" href="favicon.ico" />

<link href="./ui/css/incyyte.css" rel="stylesheet" type="text/css" />

<title>inCyyte - Privacy-Policy</title>
<meta charset="utf-8">
<link rel="stylesheet" href="ui/css/reset.css">
<link rel="stylesheet" href="ui/css/style.css">
<link rel="stylesheet" href="ui/css/layout.css">
<link rel="stylesheet" href="ui/css/style_login.css">
<link rel="stylesheet" href="ui/css/icons_sprites.css">
<link rel="stylesheet" href="ui/css/buttons.css" media="screen">
<link rel="stylesheet" href="fonts/fonts_stylesheet.css">
<link rel="stylesheet" href="ui/css/modal_window.css" type="text/css" media="screen">
<link rel="stylesheet" type="text/css" href="ui/css/jquery.lightbox-0.5.css" media="screen" />
<link rel="stylesheet" href="ui/css/jquery.validate.css">
<link rel="stylesheet" href="ui/css/validate/screen.css">

<script src="ui/js/jquery-1.7.2.js"></script>
<script src="ui/js/jcarousellite.js"></script>
<script src="ui/js/login.js"></script>
<script src="ui/js/jquery.limit.js"></script>

<script type="text/javascript" src="ui/js/easySlider1.7.js"></script>
<script type="text/javascript" src="ui/js/autoresize.jquery.js"></script>
<script type="text/javascript" src="ui/js/jquery.js"></script>
<script type="text/javascript" src="ui/js/jquery.lightbox-0.5.js"></script>
<script type="text/javascript" src="ui/js/common/jquery.form.js"></script>
<script type="text/javascript" src="ui/js/jquery-ui.js"></script>
<script type="text/javascript" src="ui/js/validate/jquery.validate.js"></script>


<script type="text/javascript">
	$(function() {
		$('#gallery a').lightBox();
	});
</script>
<style type="text/css">
<!--
.style1 {
	color: #CC0000
}
-->
</style>

<script>
	// placeholder polyfill
	$(document)
			.ready(
					function() {
						function add() {
							if ($(this).val() == '') {
								$(this).val($(this).attr('placeholder'))
										.addClass('placeholder');
							}
						}
						function remove() {
							if ($(this).val() == $(this).attr('placeholder')) {
								$(this).val('').removeClass('placeholder');
							}
						}

						// Create a dummy element for feature detection
						if (!('placeholder' in $('<input>')[0])) {

							// Select the elements that have a placeholder attribute
							$('input[placeholder], textarea[placeholder]')
									.blur(add).focus(remove).each(add);

							// Remove the placeholder text before the form is submitted
							$('form')
									.submit(
											function() {
												$(this)
														.find(
																'input[placeholder], textarea[placeholder]')
														.each(remove);
											});
						}
					});
</script>
<!-- Remember to include CSS files before JS -->
<link rel="stylesheet" href="ui/fancyplayer_code/css/jquery.fancybox.css" type="text/css" media="screen" />
<script type="text/javascript" src="ui/fancyplayer_code/js/flowplayer-3.1.1.min.js"></script>
<script type="text/javascript" src="ui/fancyplayer_code/js/jquery.fancybox-1.2.1.pack.js"></script>
<script type="text/javascript" src="ui/fancyplayer_code/js/jquery.easing.1.3.js"></script>
<script type="text/javascript" src="ui/fancyplayer_code/js/fancyplayer.js"></script>
<script type="text/javascript">
	var videopath = "http://localhost:8400/inCyyte/";
	var swfplayer = videopath + "ui/fancyplayer_code/videos/flowplayer-3.1.1.swf";
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

</head>
<%--<script type="text/javascript">
    $(window).load(function() {
        $(".loader").fadeOut("slow");
    })
</script>--%>
<body>
<%--
<div class="loader" style="background: url('${pageContext.request.contextPath}/ui/images/common/long_loading_bar.gif') 50% 50% no-repeat rgb(249,249,249)"></div>
--%>
	<div id="gradient">
		<div class="extra">
			<!-- include header -->
			<jsp:include page="../includes/homeHeader.jsp" />
			<div class="main" id="1">
				<div class="privacy_content_part"><br>
					<div class="privacy_content_heading">Privacy Policy</div>
					<div class="privacy_content"><br>
						<P>Incyyte Limited ("we" or "inCyyte") is committed to
							protecting and respecting your privacy and it is important to us
							that you have a clear understanding of how the personal
							information you provide to inCyyte is used.</P>

						<P>
							This policy (together with our <a href="termsConditions.cyt">terms of use</a> and
							any other documents referred to in it) sets out the basis on
							which any personal information we collect from you, or that you
							provide to us, will be processed by us. Please read the following
							carefully to understand our views and practices regarding your
							personal information and how we will treat it. By visiting (the "Site") you are accepting and consenting to the
							practices described in this policy.
						</P>

						<div class="privacy_content"><br>
							<h2>2. COOKIES</h2>
							<P>
								The Site uses cookies to distinguish you from our other users.
								This helps us to provide you with a good experience when you
								browse the Site and also allows us to improve the Site. For
								detailed information on the cookies we use and the purposes for
								which we use them see our <a href="welcome.cyt">Cookie Policy</a>.
							</P>
						</div>

						<div class="privacy_content"><br>
							<h2>3. USES MADE OF THE INFORMATION</h2>

							<P>We use information held about you to</P>
							<ul>
								<li>to allow you to participate in polls and other
									interactive features of our service, when you choose to do so;</li>
								<li>enable you to communicate with other users, or to
									provide non-personal information to third parties offering
									combined services with inCyyte;</li>
								<li>provide the services that you have subscribed to and
									any additional information and services that you request from
									us;</li>
								<li>administer your account with us and customise the
									service we provide to you and other users;</li>
								<li>communicate with you and to inform you about changes to
									and new features of the Site and our services;</li>
								<li>occasionally send you service or promotional
									communications through email and notices on the Site;</li>
								<li>allow subscribing businesses to send you
									price-tag-polls via email if you have opted-in to
									price-tag-polls;</li>
								<li>create and distribute advertising relevant to you or
									your network's inCyyte experience, for example, we may create
									social advertisements for your network on inCyyte using your
									profile picture and username;</li>
								<li>administer the Site and for internal operations,
									including troubleshooting, information analysis, testing,
									research, statistical and survey purposes;</li>
								<li>help keep the Site safe and secure; and</li>
								<li>ensure that content from the Site is presented in the
									most effective manner for you and for your computer.</li>
							</ul>
							<P>We may combine information we have received about you from
								other sources with information you give to us and information we
								collect about you. We may use this information and the combined
								information for the purposes set out above (depending on the
								types of information we receive).</P>
							<P>We do not sell, rent, or otherwise provide personal
								information to third parties without your consent except where
								it is necessary to carry out your instructions (to process your
								payment information, for example) or as described in Section 11
								of this privacy policy.</P>
						</div>

						<div class="privacy_content"><br>
							<h2>5. YOUR POLL DATA</h2>
							<P>We treat any polls that you create and responses that you
								receive (collectively "Poll Data") as private to you. Unless you
								choose to make your Poll Data public (for example if you create
								public "price-tag-polls") it will remain private and will not be
								used by inCyyte other than in accordance with this privacy
								policy or if we have your express consent.</P>
							<P>
								We will not sell your Poll Data to third parties, with the
								exception of the results of any public price-tag-polls that you
								create, which may be sold to other users and for which you will
								receive a percentage fee (please see out Membership <a href="termsConditions.cyt">Terms
									and Conditions</a> for further information.
							</P>
						</div>
					</div>

					<div class="privacy_content"><br>
						<P>For the purpose of the Data Protection Act 1998 (the
							"Act"), the data controller is Incyyte Limited of Innovation Centre, 24 Marsh Way, Rainham, 
							Essex RM15 6TG, United Kingdom.</P>
					</div>
					<div class="privacy_content"><br>
						<h2>1. INFORMATION WE MAY COLLECT FROM YOU</h2>

						<P>We may collect and process the following information about
							you:</P>
						<ul>
							<li><strong>Information you give us.</strong> You may give
								us information about you by filling in forms on the Site or by
								corresponding with us by phone, e-mail or otherwise. This
								includes information you provide when you register as a member
								of the Site and subsequently modify your profile, subscribe to
								one of our business packages, participate in polls or other
								social media functions on the Site and when you report a problem
								with the Site. The information you give us may include your
								name, gender, address and postcode, e-mail address and phone
								number, financial and credit card information. 
								It is your responsibility to keep this information
									up to date. InCyyte do not retain any financial, credit or debit card information. All such information is dealt with by third party secure banking systems whos services are employed by inCyyte. <a href="https://www.barclaycard.co.uk/personal/barclaycard-secure-terms-conditions">Barclaycard Merchant Services</a></li>

							<li><strong>Information we collect about you.</strong> Each
								time you view or interact with the Site and any of its features
								or functionality (including inCyyte mobile applications and
								software), we may automatically collect the following
								information:</li>

							<li>technical information, including the Internet protocol
								(IP) address used to connect your computer to the Internet, your
								internet service provider (ISP), your login information, browser
								type and version, time zone setting, browser plug-in types and
								versions, mobile carrier, operating system and platform and
								sites that have embedded our platform technology; and</li>

							<li>information about your visit, including the full Uniform
								Resource Locators (URL) clickstream to, through and from the
								Site (including date and time); polls, groups etc. viewed,
								participated in or searched for; traffic data related to
								messages sent or received; settings adjusted; page response
								times, download errors, length of visits to certain pages, page
								interaction information (such as scrolling, clicks, and
								mouse-overs), and methods used to browse away from the page and
								any phone number used to call our customer service number.</li>
							<li><strong>Information we receive from other
									sources.</strong> We may receive information about you if you use any of
								the other websites we operate or the other services we provide.
								In this case we will have informed you when we collected that
								information that it may be shared internally and combined with
								information collected on the Site. We are also working closely
								with third parties (including, for example, business partners,
								sub-contractors in technical and payment services, advertising
								networks, analytics providers, search information providers,
								credit reference agencies) and may receive information about you
								from them.</li>
						</ul>
					</div>

					<div class="privacy_content"><br>
						<h2>4. POLLS</h2>
						<P>Polls may be conducted by inCyyte, members of the Site and
							third parties. If you register as a member of the Site, you may
							be invited to participate in polls by any of these parties. Your
							selection may be random, or it may be based on your
							non-personally identifiable information, geography, company size
							and/or industry. Whether or not you decide to participate in a
							poll is completely up to you.</P>

						<P>After you complete a poll, you will be given access to the
							aggregate responses of the poll on a results page. Some third
							parties may target advertisements to you on the results page
							based on your answers to the poll. We use third parties to
							deliver incentives to you to participate in polls. If the
							delivery of incentives requires your contact information, you may
							be asked to provide personal information to the third party
							fulfilling the incentive offer, which will only be used for the
							purpose of delivering incentives and/or verifying your contact
							information. It is up to you whether you provide this information
							and whether you wish to take advantage of an incentive.</P>
						<P>We will not disclose any personal information to any third
							parties in connection with the conduct of any polls. Your consent
							to use any personal information for any purpose identified in a
							poll will be explicitly required by the party conducting it.</P>
					</div>
					<div class="privacy_content_part_display">
						<div class="privacy_content">
							<h2>6. YOUR INFORMATION CHOICES</h2>
							<P>The Site enables you to communicate with your connections
								and other users. This may be a one-to-one, groups or public
								conversation or discussion. You decide how much or how little
								you wish to communicate with individual users or groups.</P>

							<P>' that you can use privately (for example, searching,
								sending polls to a private group, or adding notes to your
								connections information.) These actions and information are
								private and we don't share or distribute them to other users of
								the Site.</P>
						</div>
						<div class="privacy_content">
							<P>Depending on your account preferences:</P>
							<ul>
								<li>we may receive information about you from the users you
									are connected to on the Site, for example when someone uploads
									a photo of you or refers to you in a post on the Site; and</li>
								<li>we may provide notifications to other users informing
									them about your activities on the Site.</li>
							</ul>
							<P>The Site allows you to upload information about your
								contacts from other social media networks. When you do this, you
								will have the option to let those contacts know that you are a
								member of the Site.</P>
						</div>
						<div class="privacy_content">
							<P>If you upload anything which contains information about
								another user (such as pictures), please make sure that you have
								the user's prior permission to do this.</P>
							<P>inCyyte offers a "public exclusive web page" feature which
								allows users to publish portions of their inCyyte profile on the
								Internet. This public content will be crawled by and displayed
								through search engines if someone searches for your username.
								You may opt-out of this feature by editing your account
								preferences. However, inCyyte does not control how often
								third-party search engines will update their caches, which may
								contain out of date search results.</P>
						</div>
						<div class="privacy_content">
							<P>Content distributed through inCyyte's sharing features and
								third party integrations may result in the display of some of
								your information outside the Site. For example, when you post
								content within an inCyyte group that is open for public
								discussion, your content, including your username as the
								contributor, may be displayed in search engine results, if you
								have not selected the "anonymous" option when posting the
								content.</P>
						</div>
					</div>
					<div class="privacy_content_part_display">
						<div class="privacy_content">
							<h2>7. SEARCH</h2>
							<P>We offer a search service to help you find information and
								share opinions with other users (for example, you can search for
								users with particular interests). We use information from user
								profiles and contributions to public areas of the Site to inform
								and refine our search service.</P>
						</div>
						<div class="privacy_content">
							<h2>8. OTHER SERVICES</h2>
							<P>If you post on an "exclusive webpage" or participate in
								"inCyyte petitions", you should be aware that any personal
								information you choose to provide can be viewed, collected and
								used by other users of these forums, as well as platform
								developers and other third parties, and may be used to send you
								unsolicited messages. inCyyte is not responsible for the
								information you choose to submit in these forums.</P>
						</div>
						<div class="privacy_content">
							<h2>9. TESTIMONIALS AND ADVERTISEMENTS PLACED THROUGH
								INCYYTE ADS SERVICE</h2>
							<P>If you provide a testimonial about the Site or place
								advertisements through the inCyyte Ads feature, we may post
								those testimonials and examples of advertisements you place in
								connection with our promotion of the Site to third parties.
								Testimonials and advertisements may include your username and
								other personal information that you have provided.</P>
						</div>
						<div class="privacy_content"><br>
							<h2>10. YOUR ACCOUNT PREFERENCES</h2>
							<P>
								Your account preferences enable you to review and amend the
								personal information that we hold about you and to adjust how
								your personal information is used. For more information on your
								account preferences, please see our "settings"
								section.
							</P>
						</div>
					</div>
					<div class="privacy_content_part_display">
						<div class="privacy_content">
							<h2>11. DISCLOSURE OF YOUR INFORMATION</h2>
							<P>We may share your personal information with any member of
								our group, which means our subsidiaries, our ultimate holding
								company and its subsidiaries, as defined in section 1159 of the
								UK Companies Act 2006.</P>
							<P>
								<strong>We may share your information with selected
									third parties including:</strong>
							</P>
							<ul>
								<li>business partners, suppliers and sub-contractors for
									the performance of any contract we enter into with you;</li>
								<li>advertisers and advertising networks that require the
									information to select and serve relevant adverts to you and
									others. We do not disclose information about identifiable
									individuals to our advertisers, but we may provide them with
									aggregate information about our users (for example, we may
									inform them that 500 men aged under 30 have clicked on their
									advertisement on any given day). We may also use such aggregate
									information to help advertisers reach the kind of audience they
									want to target (for example, women in SW1). We may make use of
									the personal information we have collected from you to enable
									us to comply with our advertisers' wishes by displaying their
									advertisement to that target audience; and
								</li>
								<li>analytics and search engine providers that assist us in
									the improvement and optimisation of the Site.</li>
							</ul>
						</div>
						<div class="privacy_content">
							<P>
								<strong>We may disclose your personal information to
									third parties:</strong>
							</P>
							<ul>
								<li>in the event that we sell or buy any business or
									assets, in which case we may disclose your personal information
									to the prospective seller or buyer of such business or assets;
								</li>
								<li>if Incyyte Limited or substantially all of its assets
									are acquired by a third party, in which case personal
									information held by it about its customers will be one of the
									transferred assets; and</li>
								<li>if we are under a duty to disclose or share your
									personal information in order to comply with any legal
									obligation, or in order to enforce or apply our terms
									of useor our Membership 
									Terms and Conditions and other agreements; or to protect the rights,
									property, or safety of inCyyte, our customers, or others. This
									includes exchanging information with other companies and
									organisations for the purposes of fraud protection and credit
									risk reduction.
								</li>
							</ul>
						</div>
					</div>
					<div class="privacy_content_part_display">
						<div class="privacy_content">
							<h2>12. WHERE WE STORE YOUR PERSONAL DATA</h2>
							<P>The information that we collect from you may be
								transferred to, and stored at, a destination outside the
								European Economic Area ("EEA"). It may also be processed by
								staff operating outside the EEA who work for us or for one of
								our suppliers. Such staff maybe engaged in, among other things,
								the fulfilment of your membership registration or subscription
								to a business package, the processing of your payment details
								and the provision of support services. By submitting your
								personal information, you agree to this transfer, storing or
								processing. We will take all steps reasonably necessary to
								ensure that your information is treated securely and in
								accordance with this privacy policy.</P>
							<P>
								All information you provide to us is stored on secure servers.
								Any payment transactions will be encrypted using
									SSL technology. Where you have chosen a password which enables
								you to access certain parts of the Site, you are responsible for
								keeping this password confidential. We ask you not to share a
								password with anyone.
							</P>
							<P>Unfortunately, the transmission of information via the
								internet is not completely secure. Although we will do our best
								to protect your personal information, we cannot guarantee the
								security of your information transmitted to the Site and any
								transmission is at your own risk. Once we have received your
								information, we will use strict procedures and security features
								to try to prevent unauthorised access. However, please note that
								emails, instant messages and other similar means of
								communication with other users of the Site are not encrypted and
								we strongly advise you not to use these means to communicate any
								confidential information.</P>
						</div>
						<div class="privacy_content">
							<h2>13. YOUR RIGHTS</h2>
							<P>
								You have the right to ask us not to process your personal
								information for marketing purposes. We will usually inform you
								(before collecting your information) if we intend to use your
								information for such purposes or if we intend to disclose your
								information to any third party for such purposes. You can
								exercise your right to prevent such processing by checking
								certain boxes on the forms we use to collect your information.
								You can also exercise the right at any time by contacting us at
								<a href="mailto:info@incyyte.com">info@incyyte.com</a>.
							</P>

							<p>Our site may, from time to time, contain links to and from
								the websites of our partner networks, advertisers and
								affiliates. If you follow a link to any of these websites,
								please note that these websites have their own privacy policies
								and that we do not accept any responsibility or liability for
								these policies. Please check these policies before you submit
								any personal information to these websites.</P>
						</div>

						<div class="privacy_content">
							<h2>14. ACCESS TO INFORMATION</h2>
							<P>The Act gives you the right to access information held
								about you. Your right of access can be exercised in accordance
								with the Act. Any access request may be subject to a fee of £10
								to meet our costs in providing you with details of the
								information we hold about you.</P>
						</div>
					</div>

					<div class="privacy_content_part_display">
						<div class="privacy_content">
							<h2>15. DELETION AND RETENTION OF YOUR DATA</h2>
							<P>We generally retain your personal information for as long
								as you have an active account with us, or to comply with our
								legal obligations, resolve disputes or to enforce our legal
								agreements.</P>
							<p>If your account is deactivated by us or by you, we have no
								obligation to retain your information, and may delete any or all
								of your account information without liability, except as
								otherwise stated in our Membership Terms and Conditions [INSERT
								LINK]. However, we may retain certain data contributed by you if
								we believe it may be necessary to prevent fraud or future abuse,
								or for legitimate business purposes, such as analysis of
								aggregated, non-personally identifiable data, account recovery,
								or if required by law. We may also retain and use your
								information if necessary to provide services to other users of
								the Site, for example, messages you have sent to other users as
								well as your contributions to polls of inCyyte groups, may
								remain visible after your account has been deactivated.</p>

							<p>
								Following deactivation of your account, we may retain your
								personal information in a residual form on our offsite backup
								media for approximately [24] months before it is permanently
								deleted. The same will apply if you remove personal information
								from your active account.
							</p>
						</div>
						<div class="privacy_content">
							<h2>16. MEMORIALIZING ACCOUNTS</h2>
							<p>If we learn that any user of the Site is deceased, we may
								memorialize the user's account. In these cases we may restrict
								profile access, remove messaging functionality, and close an
								account if we receive a formal request from the user's next of
								kin or any other legally valid request to do so.</p>
						</div>
						<div class="privacy_content">
							<h2>17. CHANGES TO OUR PRIVACY POLICY</h2>
							<p>Any changes we may make to our privacy policy in the
								future will be posted on this page and, where appropriate,
								notified to you by e-mail. Please check back frequently to see
								any updates or changes to our privacy policy.</p>
						</div>
						<div class="privacy_content">
							<h2>18. CONTACT</h2>
							<p>
								Questions, comments and requests regarding this privacy policy
								are welcomed and should be addressed to <a
									href="mailto:info@incyyte.com">info@incyyte.com</a> or Incyyte
								Limited, 24 CEME Innovation Centre, Marsh Way, Rainham, Essex
								RM13 8EU, United Kingdom.
							</p>
						</div>
					</div>
					<div class="privacy_content_part_display">
						<div class="footer_content">
							This is the first version of this privacy policy and was uploaded
							on 16th February 2014.
						</div>
					</div>
					<div class="privacy_content_part_display">
						<div class="back_top_bot">
							<a href="#1"><img src="images/back_top_bot.png" width="35"
								height="34" border="0" alt="" /><span>Back To Top</span></a>
						</div>
					</div>
				</div>
			</div>
		</div>

		<!-- include footer -->
		<jsp:include page="../includes/homeFooter.jsp" />
	</div>
	<script type="text/javascript" src="ui/js/googleanalytics.js"></script>
</body>
</html>
