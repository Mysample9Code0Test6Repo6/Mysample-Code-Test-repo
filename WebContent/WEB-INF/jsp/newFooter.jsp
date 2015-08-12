<!DOCTYPE html>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<footer id="newFooter">
    <div class="container">
        <div class="row row-inline">
            <div class="col-sm-6">

                <div class="title title-main">
                    <h5 style="font: 17px/1em 'bariol_boldbold', 'ie8_bariol_bold';">inCyyte Limited</h5>
                </div>
                <div class="text">
                    <p style="font: 15px 'bariol_regularregular','ie8_bariol_regular';">Keep up on our always evolving product features. Post a new poll and we'll share it to the world on your behalf. You could earn £1,000's for you poll results. Good luck!</p>
                </div>

            </div><!-- .col-sm-6 -->

        </div><!-- .row-->

        <hr class="devider-heavy" />
        <div class="row">
            <div class="col-sm-3">



            </div><!-- .widget -->

        </div><!-- .col-sm-3 -->
        <div class="col-sm-3">

            <div class="widget">

                <div class="widget-content">

                    <section class="posts">
                        <article class="post post-mini post-type-text">

                        </article><!-- .post -->
                    </section><!-- .posts -->

                </div><!-- .widget-content -->
            </div><!-- .widget -->

        </div><!-- .col-sm-3 -->
        <div class="col-sm-3">

            <div class="widget">
                <div class="widget-heading">

                    <div class="title title-main">
                        <h5 style="font: 17px 'bariol_boldbold', 'ie8_bariol_bold';">Members Public Poll Pages</h5>
                    </div><!-- .title -->

                </div><!-- .widget-heading -->
                <div class="widget-content">

                    <div class="tags">
                    	<p>
                     	 <c:forEach var="polls" items="${pollsByCategory}" >
            				<a href="javascript:getFilteredPoll('${polls.value}');" rel="tag" style="font: 11px 'bariol_regularregular','ie8_bariol_regular';">${polls.key}</a>
           				</c:forEach>
           				</p>
                    </div><!-- .tags -->

                </div><!-- .widget-content -->
            </div><!-- .widget -->

        </div><!-- .col-sm-3 -->
        <div class="col-sm-3">

            <div class="widget">
                <div class="widget-heading">
                    <div class="title title-main">
                        <h5 style="font: 17px 'bariol_boldbold', 'ie8_bariol_bold';">Members Public Poll Images</h5>
                    </div>
                </div><!-- .widget-heading -->
                <div class="widget-content" id="urlpass">
                    <ul class="photo-stream">
            			    <input type='hidden' id="pollCode" value=""/>
                         <c:forEach var="musicPollsByCategory" items="${musicPollsByCategory}" >
            				<li>
							<a class="lightbox" href="${musicPollsByCategory.incyyte.uploadLocation}" onclick="assignValue('${musicPollsByCategory.incyyte.incyyteCode}')" data-fancybox-group="flickr" data-fancybox-title="<p class='text-right'>
							<a href='javascript:getMusicPoll()' class='btn'>View Poll</a></p>">
							<span class="btn btn-icon-view"></span>
							<img src="${musicPollsByCategory.incyyte.uploadLocation}" alt="{{title}}" >
							</a>
						</li>
						
                    </c:forEach>
           				
           				 <c:forEach var="entertainmentPollsByCategory" items="${entertainmentPollsByCategory}" >
            				<li>
							<a class="lightbox" href="${entertainmentPollsByCategory.incyyte.uploadLocation}"    onclick="assignValue('${entertainmentPollsByCategory.incyyte.incyyteCode}')"   data-fancybox-group="flickr"
							 data-fancybox-title="<p class='text-right'><a href='javascript:getEntertainmentPoll();' class='btn'>View Poll</a></p>">
							<span class="btn btn-icon-view"></span>
							<img src="${entertainmentPollsByCategory.incyyte.uploadLocation}" alt="{{title}}" width="60" height=60>
							</a>
						</li>
                    </c:forEach>
                    </ul>
                </div><!-- .widget-content -->
            </div><!-- .widget -->

        </div><!-- .col-sm-3 -->
    </div><!-- .row-->
    <hr class="devider-heavy" />
    <ul class="nav text-center" style="font: 15px 'bariol_boldbold', 'ie8_bariol_bold';">
        <li><a href="#">Home</a></li>
        <li><a href="incyyteVideoPage.cyt">Video</a></li>
        <!--<li><a href="#">Shortcodes</a></li>-->
        <!--<li><a href="#">Portfolio</a></li>-->
        <!--<li><a href="#">Pages</a></li>-->
        <li><a href="https://www.facebook.com/incyyte">Blog</a></li>
        <li><a href="${pageContext.request.contextPath}/contactUs.cyt">Contact</a></li>
    </ul>
    <hr class="devider-heavy" />
<!--     <div class="row row-inline" id="socialMediaFooter">
        <div class="col-md-7">

            <ul class="touch">
                <li><i class="fa icomoon-location"></i><p>24 CEME INNOVATION CENTRE<br />Marsh Way RM13 8EU</p></li>
                <li><i class="fa fa-phone"></i><p>0208 166 1663<br />0703 197 8114</p></li>
                <li><i class="fa fa-envelope"></i><p><a href="mailto:info@incyyte.com">info@incyyte.com</a><br /><a href="mailto:support@incyyte.com">support@incyyte.com</a></p></li>
            </ul>

        </div>.col-md-7
        <div class="col-md-5">

            <ul class="social">
                <li><a href="#" class="rss"></a></li>
                <li><a href="#" class="google"></a></li>
                <li><a href="#" class="vimeo"></a></li>
                <li><a href="http://www.youtube.com/watch?v=v4PIP7LKeG8" class="youtube"></a></li>
                <li><a href="https://www.facebook.com/incyyte" class="facebook"></a></li>
                <li><a href="https://www.twitter.com/incyyte" class="twitter"></a></li>
            </ul>

        </div>.col-md-5
    </div>.row
 -->
  <table width="100%" height="84px">
  <tr>
  <td align="left">
  		<ul class="touch" >
        	<li style=" font: 13px 'bariol_regularregular','ie9_bariol_regular';"><i class="fa icomoon-location"></i><p  >24 CEME INNOVATION CENTRE<br />Marsh Way RM13 8EU</p></li>
            <li style=" font: 13px 'bariol_regularregular','ie9_bariol_regular';"><i class="fa fa-phone"></i><p >0208 166 1663<br />0703 197 8114</p></li>
            <li style=" font: 13px 'bariol_regularregular','ie9_bariol_regular';"><i class="fa fa-envelope"></i><p><a href="mailto:info@incyyte.com">info@incyyte.com</a><br /><a href="mailto:support@incyyte.com">support@incyyte.com</a></p></li>
        </ul>
  </td>
  <td align="right">
  <ul class="social">
                <!-- <li><a href="#" class="rss"></a></li>
                <li><a href="#" class="google"></a></li>
                <li><a href="#" class="vimeo"></a></li> -->
                <li><a href="http://www.youtube.com/watch?v=v4PIP7LKeG8" class="youtube"></a></li>
                <li><a href="https://www.facebook.com/incyyte" class="facebook"></a></li>
                <li><a href="https://www.twitter.com/incyyte" class="twitter"></a></li>
            </ul>
            </td>
  </tr>
  </table>
 
     </div><!-- .container -->
    <div class="credits"><span style="font: 15px/1em 'bariol_thinregular', 'ie8_bariol_thin'; color: rgba(255, 255, 255, 1);">inCyyte Limited &copy; 2013 </span><span>|</span><a href="${pageContext.request.contextPath}/termsConditions.cyt" style="font: 15px/1em 'bariol_boldbold', 'ie8_bariol_bold';">Terms</a><span>|</span><a href="${pageContext.request.contextPath}/privacy.cyt" style="font: 15px/1em 'bariol_boldbold', 'ie8_bariol_bold';">Privacy Policy</a></div>
</footer>
</html>

<script type="text/javascript">

function getFilteredPoll(code) {
	window.location = "viewPoll.cyt?code="+code;
	 event.preventDefault();
};

 function getEntertainmentPoll() {
	var code = $("#pollCode").val();
	window.location = "viewPoll.cyt?code="+code;
	 event.preventDefault();
};

 function getMusicPoll() {
	var code = $("#pollCode").val();
	window.location = "viewPoll.cyt?code="+code;
	 event.preventDefault();
};

function assignValue(code){
	document.getElementById('pollCode').value=code;
};

</script>	
           
