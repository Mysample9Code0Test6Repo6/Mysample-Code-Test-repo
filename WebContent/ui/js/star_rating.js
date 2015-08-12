
		$(function() {

		   $('div#star').raty({readOnly:	true,
				start:3,
				starOn:'../images/star-big-on.png',
			   	starOff:'../images/star-big-off.png'
				});

			$('div#fixed').raty({
				readOnly:	true,
				start:		3
		   });

			$('div#custom').raty({
				scoreName:	'entity.score',
				number:10
			});

			$('div#target').raty({
				hintList:	['a', '', null, 'd', '5'],
			   	starOn:		'../images/star_blue.png',
			   	starOff:	'../images/star_gray.png'
			});

			$('div#click').raty({
				onClick: function(score) {
					alert('score: ' + score);
				}
			});

			$('div#half').raty({
				start: 3.3,
				showHalf: true
			});

		   $('div#cancel').raty({
				showCancel: true
			});

		   $('div#cancel-custom').raty({
				cancelHint: 'remove my rating!',
				cancelPlace: 'right',
				showCancel: true,
				onClick: function(score) {
					alert('score: ' + score);
				}
			});

		});