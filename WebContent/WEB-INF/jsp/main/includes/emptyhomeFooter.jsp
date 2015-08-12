<%@ page import="java.util.Date" %>
<%@ page import="java.text.SimpleDateFormat" %>
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
        </ul>
      </div>
      <div class="col colIndent">
        <ul class="listWithMarker">
          <li> <a><img src="${pageContext.request.contextPath}/ui/images/signal.png" style="padding-bottom:12px;"></a> </li>
        </ul>
      </div>
      <div class="col colIndent">
        <ul class="listWithMarker">
          <li> <a href="#"><img src="${pageContext.request.contextPath}/ui/images/circle.png" style="padding-bottom:12px;"></a> </li>
        </ul>
      </div>
      <div class="col">
        <ul class="listWithMarker">
          <li> <a href="#"><img src="${pageContext.request.contextPath}/ui/images/book.png" style="padding-bottom:12px;"></a> </li>
        </ul>
      </div>
      <div style="text-align:center; margin:140px 0 0 0; padding:0;"> &copy; inCyyte <%=strFooterDate%> All rights reserved</div>
    </div>
    <!-- {%FOOTER_LINK} -->
  </div>
</div>
<!--footer end-->
