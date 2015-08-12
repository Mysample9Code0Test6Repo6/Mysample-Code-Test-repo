<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>incyyte-Business Account</title>
</head>
<body>

</body>
<script type="text/javascript">
function storeData(){
alert('alert1');
$("#businessAcctForm").ajaxSubmit({
		type:'POST',
		url:'storeBusinessAccountInfo.cyt',
		success:function (data) {
		
		},
		error:function (jqXHR, textStatus, errorThrown) {
			alert("error:" + textStatus + " exception:" + errorThrown);
		}
	});

}
</script>
<div id="gradient">
  <div class="">
    <!-- include header -->
    <jsp:include page="../common/includes/header.jsp" />
   <form:form id="businessAcctForm" name="businessAcctForm" commandName="businessAcctForm" enctype="multipart/form-data" method="post">
   <input type="submit" name="submit" class="submit" value="STORE DATA" onclick="storeData()" />
   </form:form>
    </div>
    </div>
</html>