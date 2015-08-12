<%@ include file="/WEB-INF/jsp/common/include.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="content-type" content="text/html; charset=UTF-8">
<link rel="shortcut icon" href="favicon.ico" />

<link href="./ui/css/incyyte.css" rel="stylesheet" type="text/css" />

<title>inCyyte - Terms And Conditions</title>
<meta charset="utf-8">
<link rel="stylesheet" href="ui/css/reset.css">
<link rel="stylesheet" href="ui/css/style.css">
<link rel="stylesheet" href="ui/css/layout.css">
<link rel="stylesheet" href="ui/css/style_login.css">
<link rel="stylesheet" href="ui/css/icons_sprites.css">
<link rel="stylesheet" href="ui/css/buttons.css" media="screen">
<link rel="stylesheet" href="fonts/fonts_stylesheet.css">
<link rel="stylesheet" href="ui/css/modal_window.css" type="text/css"
	media="screen">
<link rel="stylesheet" type="text/css"
	href="ui/css/jquery.lightbox-0.5.css" media="screen" />
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
<link rel="stylesheet"
	href="ui/fancyplayer_code/css/jquery.fancybox.css" type="text/css"
	media="screen" />
<script type="text/javascript"
	src="ui/fancyplayer_code/js/flowplayer-3.1.1.min.js"></script>
<script type="text/javascript"
	src="ui/fancyplayer_code/js/jquery.fancybox-1.2.1.pack.js"></script>
<script type="text/javascript"
	src="ui/fancyplayer_code/js/jquery.easing.1.3.js"></script>
<script type="text/javascript"
	src="ui/fancyplayer_code/js/fancyplayer.js"></script>
<script type="text/javascript">
	var videopath = "http://localhost:8400/inCyyte/";
	var swfplayer = videopath
			+ "ui/fancyplayer_code/videos/flowplayer-3.1.1.swf";
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
			<div class="main" id="1"><br>
				<div class="privacy_content_part">
					<div class="privacy_content_heading">Membership Terms and
						Conditions</div>

					<div class="privacy_content"><br>
						<P>
							These terms and conditions ("Terms") (together with the documents
							referred to in them) (collectively the "Agreement") tell you
							information about Incyyte Limited and the legal terms and
							conditions on which you may become a member of our website (Site) and use the services that we make available
							through the Site ("Services").
						</P>
						<P>Please read the Agreement carefully and make sure that you
							understand it, before you agree to sign up as a member of the
							Site.</P>
					</div>

					<div class="privacy_content"><br>
						<P>
							By placing a tick in the "I Accept" checkbox
						, you hereby agree to enter into and be bound
							by the Agreement. If you do not agree with any of the terms of
							the Agreement, you should place a tick in the "I Accept" checkbox when signing up and you
							will not be able to become a member of the Site or use our
							Services.
						</P>
						<P>You should print a copy of the Agreement or save it to your
							computer for future reference.</P>
                        <p>&nbsp;</p>
					</div>

					<div class="privacy_content">
						<P>
							<strong>These Terms are only available in the <a
								href="#">English language</a>.
							</strong>
						</P>
						<h2>1. INFORMATION ABOUT US</h2>
						<p>
							<span class="membership_number">1.1</span> The Site is operated
							by Incyyte Limited ("inCyyte" or "we"). We are a limited company
							registered in England and Wales under company number 07884094 and
							have our registered office in 
							South Ockendon, Essex, United Kingdom. Our main trading
							address is 24 CEME Innovation Centre, Marsh Way, Rainham, Essex
							RM13 8EU, United Kingdom. Our VAT number is <a href="#">VAT
								NUMBER</a>.
						</p>
						<P>
							<span class="membership_number">1.2</span> To contact us, please
							see our <a href="contactUs.cyt">Contact Us</a> page.
						</P>
					</div>

					<div class="privacy_content">
						<h2>2. USE OF OUR SITE</h2>
						<p>
							<span class="membership_number">2.1</span> Your use of the Site
							is governed by our Website Membership Terms of Use. Please
							take the time to read these, as they include important terms
							which apply to you and by which you are bound.
						</P>
					</div>

					<div class="privacy_content">
						<h2>3. HOW WE USE YOUR PERSONAL INFORMATION</h2>
						<p>
							<span class="membership_number">3.1</span> We only use your
							personal information in accordance with our <a href="privacy.cyt">Privacy
								Policy </a>. Please take the time to read this, as it includes
							important terms which apply to you.
						</P>
					</div>

					<div class="privacy_content_part_display">

						<div class="privacy_content">
							<h2>4. COOKIES</h2>
							<p>
								<span class="membership_number">4.1</span> The Site uses
								technology known as "cookies" to provide certain features and
								functionality. Our <a href="welcome.cyt">Cookie Policy</a> sets out
								detailed information about what cookies are, what cookies we
								use, and the purposes for which we use them and similar
								technologies.<br>
							</P>
							<div class="privacy_content"><br>
								<h2>6. IMPORTING YOUR CONTACTS</h2>
								<p>
									<span class="membership_number">6.1</span> Once you have
									registered as a member of the Site, we provide a feature which
									allows you to import your contacts from a number of existing
									social media networks and email clients. Please ensure you only use this feature
									in relation to other accounts which you are entitled to access
									and that the relevant contacts have agreed that you may share
									their information in this way.<br>
								</P>
							</div>

							<div class="privacy_content"><br>
								<h2>7. PAID SUBSCRIPTIONS</h2>
								<p>
									<span class="membership_number">7.1</span> Membership of the
									Site is free. However, you can upgrade your membership at any
									time by taking out a paid subscription to our Silver, Gold or
									Platinum Business Packages.
									Service descriptions and prices for each of these packages can
									be viewed here ("<a href="incyyteBusiness.cyt">inCyyte For Business Subscription Packages</a>").
								</p>
								<p>
									<span class="membership_number">7.2</span> If you are an
									individual, you may only take out a Subscription if you are at
									least 18 years old. If you are taking out a
									Subscription on behalf of a business, you must have the
									necessary authority to bind the business on whose behalf you
									are taking out the Subscription.
								</p>

								<p>
									<span class="membership_number">7.3</span> When taking out a
									Subscription (unless this is done at the same time as your
									original registration as a member), you will be required to
									re-affirm your acceptance of the Agreement to incorporate any
									additional terms relating to your Subscription. If you refuse
									to re-affirm your acceptance, you will not be able to take out
									a Subscription.
								</p>

							</div>

							<div class="privacy_content"><br>
								<h2>9. TRIALS</h2>
								<p>
									<span class="membership_number">9.1.</span> From time to time,
									we may offer trial Subscriptions for a specified period without
									payment (a Trial). We reserve the right, in
									our absolute discretion, to determine your eligibility for a
									Trial, and to withdraw or to modify a Trial at any time without
									prior notice and with no liability
								</p>
								<p>
									<span class="membership_number">9.2</span> For some Trials, we
									may require you to provide your payment details to start the
									Trial. At the end of the Trial, we may automatically start to
									charge you the Subscription fees on the first day following the
									end of the Trial, on a recurring monthly basis. By providing
									your payment details in conjunction with the Trial, you agree
									to pay these fees. If you do not want to incur these fees, you
									must cancel the Subscription prior to the end of the Trial,<strong>
										by providing us with at least 48hrs written (or emailed) notice</strong>.
								</p>
							</div>

							<div class="privacy_content"><br>
								<h2>10. PAYMENT</h2>

								<p>
									<span class="membership_number"> 10.1 </span>If you take out a
									Subscription, you will pay the fees published on the Site from
									time to time for that Subscription to Incyyte
									Limited. We reserve the right to change these fees at any time
									and in our sole discretion, provided that any change to the
									fees will not come into effect until the end of the
									then-current term of your Subscription. We will notify you in
									advance of any change in your Subscription fees to allow you to
									cancel your Subscription by providing us with
										at least 30 days' written notice prior to the end of the
									then-current term of your Subscription.
								</p>

								<p>
									<span class="membership_number">10.2 </span>All fees published
									on the Site are in Pounds Sterling (�), inclusive/exclusive
									of VAT and other taxes, which shall be payable by you (where
									applicable).
								</p>

								<p>
									<span class="membership_number">10.3 </span>Subscriptions are
									on a continuous service basis. This means that, subject to
									these Terms, we will automatically renew your Subscription at
									the end of the then-current term unless you cancel your
									Subscription by providing us with at least 30
										days' written notice prior to the end of the then-current
									Subscription term. You will be charged renewal fees equal to
									the fees for the immediately preceding Subscription term,
									unless we have notified you of new fees in accordance with
									clause 10.1.
								</p>

								<p>
									<span class="membership_number">10.4 </span>If you cancel your
									Subscription in accordance with clause 10.1 or clause 10.3, the
									cancellation will take effect the day after the last day of the
									then-current Subscription term and you will be downgraded to a
									free membership.
								</p>

								<p>
									<span class="membership_number">10.5 </span>You will ensure
									that the account or payment card details that you provide for
									the payment of your Subscription fees are accurate and you will
									promptly notify inCyyte of any changes to these details. You
									acknowledge and agree that if for any reason and at any time we
									are unable to process your payment, we may suspend or cancel
									your Subscription at our absolute discretion.
								</p>

								<p>
									<span class="membership_number">10.6 </span>If you fail to make
									any payment by the due date for payment, we have the right to
									charge interest on the overdue amount at the rate of
										10 per cent per annum above the then
									current BANK of England's base rate accruing
									on a daily basis from the due date for payment until the date
									of actual payment of the overdue amount, whether before or
									after judgment.
								</p>

							</div>
							<div class="privacy_content"><br>
								<h2>15. OUR LIABILITY IF YOU ARE A BUSINESS</h2>
								<p>
									<span class="membership_number">15.1</span> Nothing in the
									Agreement excludes or limits our liability for death or
									personal injury arising from our negligence, or our fraud or
									fraudulent misrepresentation, or any other liability that
									cannot be excluded or limited by English law.
								</p>
								<p>
									<span class="membership_number">15.2 </span>If we fail to
									comply with the Agreement, we are, subject to clause 15.3,
									responsible for loss or damage you suffer that is a foreseeable
									result of our breach of the Agreement or our negligence, but,
									subject to clause 15.1, we are not responsible for any loss or
									damage that is not foreseeable. Loss or damage are foreseeable
									if they are an obvious consequence of our breach or if they
									were contemplated by you and us at the time of entering into
									the Agreement.
								</p>
								<p>
									<span class="membership_number">15.3 </span>Subject to clause
									15.1, if you use the Site and/or the Services for any business
									or commercial purpose, we will under no circumstances whatever
									be liable to you, whether in contract, tort (including
									negligence), breach of statutory duty, or otherwise, arising
									under or in connection with the Agreement for:
								</p>
								<P>
									(a) any loss of profits, sales, business, or revenue;<br />
									(b) business interruption; <br /> (c) loss of anticipated
									savings;<br /> (d) loss or corruption of data, information or
									software;<br /> (e) loss of business opportunity, goodwill or
									reputation; or<br /> (f) any indirect or consequential loss.<br />
								</P>
								<p>
									<span class="membership_number">15.4 </span>Subject to clause
									15.1, our total, aggregate liability to you in respect of all
									losses arising under or in connection with the Agreement,
									whether in contract, tort (including negligence), breach of
									statutory duty, or otherwise, shall in no circumstances exceed
									<strong>the total amount paid by you to
										us in connection with your Subscription.</strong>.
								</p>
								<p>
									<span class="membership_number">15.5 </span>Except as expressly
									stated in the Agreement, any representation, condition or
									warranty which might be implied or incorporated into the
									Agreement by statute, common law or otherwise is excluded to
									the fullest extent permitted by law. In particular, you
									acknowledge that the results of polls and other content found
									on the Site are provided for general information only and are
									not intended to provide accurate advice on which you should
									rely.
								</p>
							</div>
							<div class="privacy_content">
								<h2>17. COMMUNICATIONS BETWEEN US</h2>
								<p>
									<span class="membership_number">17.1 </span>When we refer in
									these Terms to "written" or "in writing", this will include
									e-mail.
								</p>
								<p>
									<span class="membership_number">17.2 </span>Your questions,
									comments and requests are welcomed and you can contact us at
									any time by sending an email to <a
										href="mailto:info@incyyte.com">info@incyyte.com</a>, by
									writing to Incyyte Limited, 24 CEME Innovation Centre, Marsh
									Way, Rainham, Essex RM13 8EU, United Kingdom or by telephoning
									our Customer Services telephone line during working hours on <a
										href="#">+44 208 166 1663</a>.
								</p>
								<p>
									<span class="membership_number">17.3 </span>If we have to
									contact you or give you notice in writing, we will do so by
									e-mail or by pre-paid post to the address you provide to us as
									part of your registration information.
								</p>

								<p>
									<span class="membership_number">17.4 </span>Except as set out
									otherwise in clause 14.3, any notice given by you to us, or by
									us to you, will be deemed received and properly served
									immediately when posted on our website, 24 hours after an
									e-mail is sent, or three days after the date of posting of any
									letter. In proving the service of any notice, it will be
									sufficient to prove, in the case of a letter, that such letter
									was properly addressed, stamped and placed in the post and, in
									the case of an e-mail, that such e-mail was sent to the
									specified e-mail address of the addressee. The provisions of
									this clause shall not apply to the service of any proceedings
									or other documents in any legal action.
								</p>
							</div>
						</div>
						<div class="privacy_content">
							<h2>5. MEMBER REGISTRATION AND PASSWORDS</h2>
							<p>
								<span class="membership_number">5.1</span> If you wish to
								respond to any polls on our Site or to use the Site to create
								your own polls, you will need to register as a member of the
								Site.
							</p>
							<p>
								<span class="membership_number">5.2</span> You must ensure that
								the information you provide to us in connection with your
								registration as a member of the Site is complete and accurate
								and that this information is kept up to date.
							</p>

							<p>
								<span class="membership_number">5.3</span> Upon registration as
								a member of the Site, you will be required to choose and verify
								a username and password, which you will then be able to use to
								access your account. These registration credentials are unique
								to you and should not be shared with any other person.
							</p>

							<p>
								<span class="membership_number">5.4</span> You acknowledge and
								agree that you are responsible for maintaining the security and
								confidentiality of your username and password and for all
								activities occurring in connection with the use of your account,
								whether or not authorised by you.
							</p>

							<p>
								<span class="membership_number">5.5</span> If you forget your
								username or password, or if you believe there has been
								unauthorised access to your account by a third party, please
								notify us immediately at <a href="mailto:info@incyyte.com">info@incyyte.com</a>
								and change your password as soon as possible.
							</p>
						</div>

						<div class="privacy_content"><br>
							<h2>8. PRICE-TAG-POLLING</h2>
							<p>
								NOTE: The features in this section are not currently available.
							</p>
							<p>
								<span class="membership_number">8.1</span> As a member of the
								Site, you may create and respond to a variety of polls, which
								may be private or public. The Site's "price-tag-poll" feature
								enables members to create or respond to polls which have a price
								element, either for the creator of the poll, or for those
								responding to the poll. You can find out more about <a href="#">price-tag-polls</a>
								here.
							</p>

							<p>
								<span class="membership_number">8.2</span> If you create a
								business price-tag-poll, you will need to assign a small
								monetary value to that poll to encourage users to respond.
							</p>

							<p>
								<span class="membership_number">8.3</span> Each user that
								responds to a business price-tag-poll will earn a fee. This fee
								will be calculated by reference to a percentage of the monetary
								value assigned to the poll by its creator in accordance with
								clause 8.2.
							</p>

							<p>
								<span class="membership_number">8.4</span> If you create a
								public price-tag-poll, the value of this poll will increase as
								the number of responses received to the poll increases.
							</p>

							<p>
								<span class="membership_number">8.5</span> Any user may purchase
								poll data relating to the results of a public price-tag-poll.
								For example a user may wish to know the number of women aged
								25-35 that answered "Yes" to a particular question and can pay a
								nominal fee to find this out.
							</p>

							<p>
								<span class="membership_number">8.6</span> If you create a
								public price-tag-poll and any resulting poll data is sold to
								other users, you will receive a fee. This fee will be calculated
								by reference to a percentage of the value assigned to the poll
								by inCyyte in accordance with clause 8.4 at the time the data is
								purchased.
							</p>

							<p>
								<span class="membership_number">8.7</span> If you create a
								private price-tag-poll which receives responses, or if you
								purchase any poll data, you must pay the relevant fees <a
									href="#"> PAYMENT TERMS</a>.
							</p>

							<p>
								<span class="membership_number">8.8</span> If you earn any money
								through participating in price-tag-polls or through sales of
								your poll data, this will be paid to you via <a href="#">PAYMENT
									METHOD</a> within <a href="#">PAYMENT PERIOD</a>.
							</p>


						</div>

						<div class="privacy_content"><br>
							<h2>11. OUR RIGHT TO VARY THESE TERMS</h2>
							<p>
								<span class="membership_number">11.1</span> We may revise these
								Terms from time to time. If we make an amendment to these Terms,
								we will post an updated set of terms and conditions on the Site
								and it is your responsibility to check these Terms from time to
								time to take notice of any changes we have made. If we make a
								change to these Terms which we consider to be material, we will
								<strong>post a change notice the next time you access
									the Site informing you that the Terms have been updated and/or
									send members an email notice of the changes in advance of
									implementing the changes.</strong>
							</p>

							<p>
								<span class="membership_number">11.2</span> Your continued use
								of the Site and/or the Services following any such amendment
								will signify your acceptance of the revised terms and
								conditions, and the Agreement shall be deemed to be amended to
								incorporate the revised terms and conditions for the purposes of
								your future use of the Site and/or the Services.
							</p>


						</div>

						<div class="privacy_content"><br>
							<h2>12. CONFIDENTIALITY</h2>
							<p>The Site provides a social medium where individuals,
								businesses and communities can anonymously connect, share polls
								and vote on each others' polls, without revealing their
								identity.</p>
							<p>You acknowledge and agree that it is your responsibility
								to maintain the anonymity of your identity when using the Site
								and that we have no responsibility for any information that you
								choose to make public through your use of the Site and/or any
								further use made of any such information by any third party.</p>
						</div>
						<div class="privacy_content"><br>
							<h2>13. INACTIVE ACCOUNTS</h2>
							<p>
								<span class="membership_number">13.1</span> If you do not log in
								to your membership account for a continuous period of
									<strong>24 months</strong>, we will set your account to inactive
								and, if you do not use it for a further <strong>12 months</strong>
								, we will deactivate your account and may delete all
								information and other content relating to it. Please see our <a
									href="privacy.cyt">Privacy Policy</a> for more details of how we deal
								with your information following deactivation of your account.
							</p>
						</div>
						<div class="privacy_content"><br>
							<h2>14. YOUR CONSUMER RIGHT TO CANCEL A SUBSCRIPTION</h2>
							<P>
								<strong>This clause 14 only applies if you are a
									consumer.</strong>
							</P>
							<p>
								<span class="membership_number">14.1 </span>If you are a
								consumer and have taken out a Subscription and you have not yet sent a poll outside your region which has generated results, you have a legal
								right to cancel your Subscription during the period of 7 days
								commencing on <strong>the date you receive our
									confirmation email stating that your Subscription has been
									successfully set up</strong>. This means that if you change your mind or
								for any other reason decide you want to cancel your Subscription
								at any time during this 7 day period, you can notify us of your
								decision to cancel and receive a refund of any payment you have
								already made. Advice about your legal right to cancel a contract
								is available from your local Citizens' Advice Bureau or Trading
								Standards office.
							</p>

							<p>
								<span class="membership_number">14.2 </span>This
								cancellation right does not apply if you have logged into the
								Site and/or used any of the Services during this 7 day period.
							</p>

							<p>
								<span class="membership_number">14.3 </span>To cancel your
								Subscription under this clause 14, <strong>please
									telephone our Customer Services department on [+44 208 166
									1663] or e-mail us at <a href="mailto:info@incyyte.com">info@incyyte.com</a>
									to tell us. If you send us your cancellation notice by e-mail,
									your cancellation will be effective from the date you send us
									the e-mail. If you call us to notify us of your cancellation,
									then your cancellation will be effective from the date you
									telephone us. You may wish to keep a record of your
									cancellation email or telephone call.
								</strong> 
							</p>

							<p>
								<span class="membership_number">14.4 </span>You will receive a
								full refund of any payments you have made prior to cancellation
								of your Subscription under this clause 14. We will process the
								refund due to you as soon as possible and, in any case, within <a
									href="#">30</a> calendar days of the day on which you gave us
								notice of cancellation.
							</p>

							<p>
								<span class="membership_number">14.5 </span>We will refund you
								by the method used to pay for your Subscription.
							</p>



						</div>

						<div class="privacy_content"><br>
							<h2>16. CANCELLATION OF YOUR SUBSCRIPTION AND DEACTIVATION
								OF YOUR ACCOUNT</h2>
							<p>
								<span class="membership_number">16.1</span> The Agreement will
								continue to apply to you until you or we cancel any Subscription
								that you have taken out and deactivate your account.
							</p>
							<p>
								<span class="membership_number">16.2</span> You may cancel your
								Subscription at any time <strong>by providing us with
									at least 30 days' written notice prior to the end of a
									Subscription month</strong> and you may deactivate your account at any
								time (for details of how to do this please see our "<a href="#">settings</a>"
								section).
							</p>

							<p>
								<span class="membership_number">16.3 </span>We may cancel your
								Subscription and/or deactivate your account at any time,
								including in the event of your actual or suspected unauthorised
								use of the Site or non-compliance with these Terms.
							</p>

							<p>
								<span class="membership_number">16.4 </span>If you or we cancel
								your Subscription and/or deactivate your account, you agree that
								we shall have no liability or responsibility to you and we will
								not refund any amounts that you have already paid, to the
								fullest extent permitted under applicable law.
							</p>

							<p>
								<span class="membership_number">16.5 </span>Following
								deactivation of your account, we reserve the right to remove and
								permanently delete your account and all data and other content
								belonging to you in relation to your account unless you contact
								us in writing within <strong>28 days</strong> of such
								deactivation requesting the reactivation of your account (if
								deactivated by you) or the return of any such data or other
								content. We reserve the right to charge you an administrative
								fee to cover our reasonable costs in relation to returning any
								data or other content as requested by you.
							</p>




						</div>

						<div class="privacy_content_part_display">

							<div class="privacy_content">
								<h2>18. OTHER IMPORTANT TERMS</h2>
								<p>
									<span class="membership_number">18.1</span> The Agreement
									constitutes all the terms and conditions agreed upon between
									you and inCyyte and supersedes any prior agreements in relation
									to the subject matter of the Agreement, whether written or
									oral. You acknowledge and agree that you have not accepted the
									Agreement in reliance on any oral or written representations
									made by inCyyte that are not contained in the Agreement. Please
									note, however, that any additional uses that you make of the
									Site and the Services may be governed by additional terms. For
									example, if you wish to take out a Subscription, to upgrade
									your current Subscription or to participate in a free or
									discounted Trial, you may be required to agree to additional
									terms and conditions, which will then form part of the
									Agreement.
								</p>

								<p>
									<span class="membership_number">18.2 </span>We may transfer our
									rights and obligations under the Agreement to another
									organisation, but this will not affect your rights or our
									obligations under the Agreement.
								</p>

								<p>
									<span class="membership_number">18.3 </span>You may only
									transfer your rights or obligations under the Agreement to
									another person or organisation if we agree to this in writing.
								</p>

								<p>
									<span class="membership_number">18.4 </span>No third party
									shall have any rights to enforce the Agreement.
								</p>

								<p>
									<span class="membership_number">18.5 </span>Each of the
									paragraphs of these Terms operates separately. If any court or
									relevant authority decides that any of them are unlawful or
									unenforceable, the remaining paragraphs will remain in full
									force and effect.
								</p>

							</div>
							<div class="privacy_content">
								<p>
									<span class="membership_number">18.6 </span>If we fail to
									insist that you perform any of your obligations under the
									Agreement, or if we do not enforce our rights against you, or
									if we delay in doing so, that will not mean that we have waived
									our rights against you and will not mean that you do not have
									to comply with those obligations. If we do waive a default by
									you, we will only do so in writing, and that will not mean that
									we will automatically waive any later default by you.
								</p>

								<p>
									<span class="membership_number">18.7 </span>If you are a
									consumer, please note that the Agreement is governed by English
									law. This means any dispute or claim arising out of the
									Agreement will be governed by English law. You and we both
									agree that the courts of England and Wales will have
									non-exclusive jurisdiction. However, if you are a resident of
									Northern Ireland you may also bring proceedings in Northern
									Ireland, and if you are a resident of Scotland, you may also
									bring proceedings in Scotland.
								</p>

								<p>
									<span class="membership_number">18.8 </span>If you are a
									business, the Agreement is governed by English law. This means
									that any dispute or claim arising out of or in connection with
									the Agreement or its subject matter or formation (including
									non-contractual disputes or claims), will be governed by
									English law. We both agree to the exclusive jurisdiction of the
									courts of England and Wales.
								</p>

								<p>
									<span class="membership_number">18.9 </span>You acknowledge and
									agree that you are responsible for determining which country's
									laws may apply to your use of the Site and for assessing your
									obligations under such laws.
								</p>

							</div>


						</div>

					</div>

					<div class="privacy_content_part_display">
						<div class="footer_content">
							This is the first version of this Terms and Conditions and was uploaded
							on 14th February 2014.
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
