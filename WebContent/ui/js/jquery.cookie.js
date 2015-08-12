/*
 * contactable 1.2.1 - jQuery Ajax contact form
 *
 * Copyright (c) 2009 Philip Beel (http://www.theodin.co.uk/)
 * Dual licensed under the MIT (http://www.opensource.org/licenses/mit-license.php) 
 * and GPL (http://www.opensource.org/licenses/gpl-license.php) licenses.
 *
 * Revision: $Id: jquery.contactable.js 2010-01-18 $
 *
 */
 
//extend the plugin
(function($){

	//define the new for the plugin ans how to call it	
	$.fn.contactable = function(options) {
		//set default options  
		var defaults = {
			name: 'Name',
			email: 'Email',
			message : 'Message',
			subject : 'A contactable message',
			recievedMsg : 'Thankyou for your message',
			notRecievedMsg : 'Sorry but your message could not be sent, try again later',
			disclaimer: 'Please feel free to get in touch, we value your feedback',
			hideOnSubmit: true
		};

		//call in the default otions
		var options = $.extend(defaults, options);
		//act upon the element that is passed into the design    
		return this.each(function(options) {
			//construct the form
			$(this).html('<div id="contactable"></div>');
			//show / hide function
			$('div#contact').click(function() {
				$('#overlay').css({display: 'block'});
				$("#contactable").toggleClass("cookie_contactable",1000);
				$('#contactForm').toggleClass("cookie_contactform",1000);
			});
			$('#showcookie').click(function() {
				$('#overlay').css({display: 'block'});
				$("#contactable").addClass("cookie_contactable",1000);
				$('#contactForm').addClass("cookie_contactform",1000);
			});
			$(".extra").click(function(){
				$("#contactable").removeClass("cookie_contactable",1000);
				$('#contactForm').removeClass("cookie_contactform",1000);
				$('#overlay').css({display: 'none'});
				
			});
			
			
		});

	};
})(jQuery);

