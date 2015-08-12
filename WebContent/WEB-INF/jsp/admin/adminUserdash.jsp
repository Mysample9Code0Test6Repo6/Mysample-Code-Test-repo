<%@ include file="/WEB-INF/jsp/common/include.jsp"%>
<link rel="stylesheet" href="ui/css/buttons.css"  media="screen">
<link rel="stylesheet" href="ui/css/style_login.css">

<script src="ui/js/jquery-1.7.1.js"></script>
<script src="ui/js/login.js"></script>
<script src="ui/js/admin.js"></script>
<script type="text/javascript" src="ui/js/common/jquery.form.js"></script>
<script type="text/javascript" src="ui/js/validate/jquery.validate.js" ></script>

<link rel="stylesheet" href="ui/js/tablesorter/css/jquery.tablesorter.style.css">
<link rel="stylesheet" href="ui/js/tablesorter/css/jquery.tablesorter.pager.css">
<script type="text/javascript" src="ui/js/tablesorter/jquery.tablesorter.js"></script>
<script type="text/javascript" src="ui/js/tablesorter/jquery.tablesorter.pager.js"></script>

<link rel="stylesheet" href="ui/modal/colorbox.css">
<script src="ui/modal/colorbox/jquery.colorbox.js"></script>


<div id="answer_container">
	<c:choose>
		<c:when test="${not empty username}">

			<div id="answer_form">
				<BR> <BR> <span class="style1">User Info.</span><BR>
				<table width="100%" border="1">

					<tr>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
					</tr>
					<tr>
						<td>Username</td>
						<td><c:out value="${userModel.username}" /></td>
					</tr>
					<tr>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
					</tr>
					<tr>
						<td>User Email</td>
						<td><c:out value="${userModel.su_email}" /></td>
					</tr>
					<tr>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
					</tr>
					<tr>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
					</tr>
					<tr>
						<td>Total Contacts</td>
						<td><c:out value="${totalContacts}" /></td>
					</tr>
					<tr>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
					</tr>
					<tr>
						<td>Total Groups</td>
						<td><c:out value="${totalGroups}" /></td>
					</tr>
					<tr>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
					</tr>
					<tr>
						<td>Total InCyytes</td>
						<td><c:out value="${totalIncyyte}" /></td>
					</tr>
					<tr>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
					</tr>
					<tr>
						<td>&nbsp;</td>
						<td>
							<div title="Enter Account" id="enterAccount" class="button_red" 
		                     style="width:240px;cursor:pointer; cursor:hand; z-index: 0;">
		                     	<span class="title_red" style="color:#fff;">ENTER ACCOUNT</span>
		                	</div>
		                </td>
					</tr>

				</table>
				<BR> <BR> <BR> <BR>
				<div>
					<label class="panelLink" id="listTopinCyyteUsers" ><span>List top inCyyte users</span></label>
				</div>
			</div>

		</c:when>
		<c:otherwise>

			<div id="answer_form">
				<BR> <BR> <span class="style1">System Info.</span><BR>
				<table width="100%" border="1">
					<tr>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
					</tr>
					<tr>
						<td>Total Users</td>
						<td><c:out value="${totalUsers}" /></td>
					</tr>
					<tr>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
					</tr>
					<tr>
						<td>Total Contacts</td>
						<td><c:out value="${totalContacts}" /></td>
					</tr>
					<tr>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
					</tr>
					<tr>
						<td>Total Groups</td>
						<td><c:out value="${totalGroups}" /></td>
					</tr>
					<tr>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
					</tr>
					<tr>
						<td>Total InCyytes</td>
						<td><c:out value="${totalIncyyte}" /></td>
					</tr>
					<tr>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
					</tr>
					<tr>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
					</tr>
					<tr>
						<td>&nbsp;</td>
						<td></td>
					</tr>
				</table>
				<BR> <BR> <BR> <BR>
				<div>
					<label class="panelLink" id="listTopinCyyteUsers" ><span>List top inCyyte users</span></label>
				</div>
			</div>
		</c:otherwise>
	</c:choose>
</div>
