$(function() {

	// load the modal window
	$('a.read_terms').click(function(){

		// scroll to top
		$('html, body').animate({scrollTop:0}, 'fast');

		// before showing the modal window, reset the form incase of previous use.
		$('.success, .error').hide();
		$('form#read_terms_txtForm').show();

		//show the mask and contact divs
		$('#mask').show().fadeTo('', 0.7);
		$('div#read_terms_txt').fadeIn();

		// stop the modal link from doing its default action
		return false;
	});

	// close the modal window is close div or mask div are clicked.
	$('div#close1, div#mask').click(function() {
        $('div#read_terms_txt, div#mask').stop().fadeOut('slow');

	});

});
