<%@ include file="/WEB-INF/jsp/common/include.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN">
<html>
<head>
<title>inCyyte Video</title>
	<script src="ui/flowplayer/script/jquery.tools.min.js" type="text/javascript"></script>
	<script src="ui/flowplayer/script/flowplayer-3.1.4.min.js" 	type="text/javascript"></script>
	<link rel="stylesheet" href="ui/flowplayer/css/overlay.css" type="text/css" media="screen" />
	<link rel="shortcut icon" href="favicon.ico" />
	
</head>
<body>



	<div class="video" id="player" 
		style="background-image: url(ui/images/video_img.png); background-repeat: no-repeat; width: 100%; height: 100%;">
		<a href="ui/flowplayer/video/video01.flv" > 
			<img src="ui/flowplayer/images/play.png" alt="play" title="play" border="0">
		</a>
		<div class="info">
			inCyyte Video <span> 00:27 </span>
		</div>
	</div>
	

	<!-- this script block will install Flowplayer inside previous A tag -->
	<script>
		flowplayer("player", "ui/flowplayer/swf/flowplayer-3.1.5.swf", {

			// clip does not start automatically
			clip : {
				url : 'ui/flowplayer/video/video01.flv',
				autoPlay : true
			},
			// default controls with the same background color as the page background
			plugins : {
				controls : {

					backgroundColor : 'transparent',
					backgroundGradient : 'none',

					scrubber : true,
					height : 40,
					sliderColor : '#333333',
					progressColor : '#ca000a',
					bufferColor : '#666666',
					autoHide : false
				}
			},
			// fiercy red background color with a little gradient and curving        
			canvas : {
				backgroundColor : 'transparent',
				backgroundGradient : [ 0.3, 0 ],
				borderRadius : 10
			},
			// set screen dimensions so that it goes near the canvas borders        
			screen : {
				width : 960,
				height : 640,
				top : 3
			}
		});
	</script>
	<script type="text/javascript" src="ui/js/googleanalytics.js"></script>
</body>
</html>
