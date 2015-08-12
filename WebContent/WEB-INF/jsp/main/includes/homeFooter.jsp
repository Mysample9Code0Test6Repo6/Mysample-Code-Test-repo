<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Date" %>
<%@ include file="/WEB-INF/jsp/common/include.jsp" %>
<%
    String strFooterDate ="";
    try{
        SimpleDateFormat smp = new SimpleDateFormat("yyyy");
        strFooterDate = smp.format(new Date());
    }catch(Exception ex){}

%>

<!--footer -->
  <div id="footer">
    <div >
      <div class="col colIndent">
        <ul class="listWithMarker">
          <li> <a href="#"><img src="${pageContext.request.contextPath}/ui/images/key.png" style="padding-bottom:12px;"></a> </li>
          <li> <a>Top Polls</a> </li>
          <li> <a>Search Questions</a> </li>
          <li> <a>Search Petitions</a> </li>
        </ul>
      </div>
      <div class="col colIndent">
        <ul class="listWithMarker">
          <li> <a><img src="${pageContext.request.contextPath}/ui/images/signal.png" style="padding-bottom:12px;"></a> </li>
          <li> <a href="${pageContext.request.contextPath}/tour.cyt">Take The Tour</a> </li>
          <li> <a href="${pageContext.request.contextPath}/inCyyte.cyt">What is inCyyte?</a> </li>
          <li> <a href="${pageContext.request.contextPath}/incyyteBusiness.cyt">inCyyte For Business</a></li>
        </ul>
      </div>
      <div class="col colIndent">
        <ul class="listWithMarker">
          <li> <a href="#"><img src="${pageContext.request.contextPath}/ui/images/circle.png" style="padding-bottom:12px;"></a> </li>
          <li> <a href="${pageContext.request.contextPath}/anonymity.cyt">Anonymity  &amp; inCyyte</a> </li>
          <li> <a href="${pageContext.request.contextPath}/redcard.cyt">The Red Card System</a> </li>
          <li> <a href="${pageContext.request.contextPath}/faq.cyt">FAQs</a> </li>
        </ul>
      </div>
      <div class="col">
        <ul class="listWithMarker">
          <li> <a href="#"><img src="${pageContext.request.contextPath}/ui/images/book.png" style="padding-bottom:12px;"></a> </li>
          <li> <a href="${pageContext.request.contextPath}/termsConditions.cyt">Terms &amp; Conditions</a> </li>
          <li> <a href="${pageContext.request.contextPath}/privacy.cyt">Privacy Policy</a> </li>
          <li> <a href="${pageContext.request.contextPath}/contactUs.cyt">Contact Us</a> </li>
        </ul>
      </div>
      <div style="text-align:center; margin:140px 0 0 0; padding:0;">&copy; inCyyte <%=strFooterDate%> All rights reserved</div>
    </div>
    <!-- {%FOOTER_LINK} -->
  </div>
</div>
<!--footer end-->
