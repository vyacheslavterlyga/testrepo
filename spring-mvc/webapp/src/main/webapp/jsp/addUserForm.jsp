<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"
	isELIgnored="false"%>
<html>
<head>
<title>Add User</title>
<script src="<spring:url value="/webjars/jquery/1.12.0/jquery.js" />"></script>
<script
	src="<spring:url value="/webjars/jquery-ui/1.12.1/jquery-ui.js" />"></script>
<link rel="stylesheet"
	href="<spring:url value="/webjars/jquery-ui/1.12.1/jquery-ui.css"/>">
<script>
	$(document).ready(function() {
		dateFormatLocation = "<spring:message code='datePatternJQuery'/>";
		$("#startDate").datepicker({
			dateFormat : dateFormatLocation
		});
		$("#endDate").datepicker({
			dateFormat : dateFormatLocation
		});
		$("#submitButton").click(function() {
			var status = "New user added succesfully";
			document.getElementById("submitStatus").innerHTML = status;
		});
	});
</script>
</head>
<body>
	Language:
	<spring:message code='language' />
	<p align="right">
		<a href="?language=en">English</a>|<a href="?language=ru">Русский</a>
	</p>
	<br />
	<table>
		<spring:url value="/user/saveNewUser" var="saveUserUrl" />
		<form:form modelAttribute="User" action="${saveUserUrl}" method="POST">
			<tr>
				<td><spring:message code='label.login' /></td>
				<td><form:input path="login" /></td>
			</tr>
			<tr>
				<td><spring:message code='label.password' /></td>
				<td><form:input path="password" /></td>
			</tr>
			<tr>
				<td><spring:message code='label.role' /></td>
				<td><form:input path="role" /></td>
			</tr>
			<tr>
				<td><spring:message code='label.startDate' /></td>
				<td><form:input type="text"	path="startDate" /></td>
			</tr>
			<tr>
				<td><spring:message code='label.endDate' /></td>
				<td><form:input type="text" path="endDate" /></td>
			</tr>
			<tr>
				<td><br /></td>
			</tr>
			<tr>
				<td><form:button id="submitButton">Add this user</form:button>
					<p id="submitStatus"></p></td>
			</tr>
		</form:form>

	</table>
	<br />
	<br />
	<a href="<spring:url value="/user/index"/>">Back</a>

</body>
</html>