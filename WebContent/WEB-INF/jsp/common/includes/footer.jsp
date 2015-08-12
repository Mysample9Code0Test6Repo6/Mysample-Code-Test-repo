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
  <footer class="footer_inner" >
    <div class="footer_inner" style=" text-align:center; color: #ffffff">&copy; inCyyte <%=strFooterDate%> All rights reserved</div>
  </footer>
</div>
<!--footer end-->
