<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"
	isELIgnored="false"%>

<spring:message code='datePattern' var="datePattern" />

<html>
<head>
<title>Update user</title>
<script src="<spring:url value="/webjars/jquery/1.12.0/jquery.js" />"></script>
<script
	src="<spring:url value="/webjars/jquery-ui/1.12.1/jquery-ui.js" />"></script>
<link rel="stylesheet"
	href="<spring:url value="/webjars/jquery-ui/1.12.1/jquery-ui.css"/>">
<link rel="stylesheet"
	href="<spring:url value="/css/languageList.css"/>">
<script>
	$(document).ready(function() {
		dateFormatLocation = "<spring:message code='datePatternJQuery'/>";
		$("#startDate").datepicker({
			dateFormat : dateFormatLocation
		});
		$("#endDate").datepicker({
			dateFormat : dateFormatLocation
		});
	});
</script>
</head>
<body>
	<jsp:include page="languageList.jsp" />
	<br />
	<table>
		<spring:url value="/user/saveUpdateUser" var="urlUpdateUser" />
		<form:form modelAttribute="User" action="${urlUpdateUser}"
			method="POST">
			<form:hidden path="id" />
			<form:hidden path="login" />
			<tr>
				<td><spring:message code='label.login' /></td>
				<td><form:input path="login" disabled="true" /></td>
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
				<td><form:input type="text" path="startDate" /></td>
			</tr>
			<tr>
				<td><spring:message code='label.endDate' /></td>
				<td><form:input type="text" path="endDate" /></td>
			</tr>
			<tr>
				<td><br /></td>
			</tr>
			<tr>
				<td><form:button id="submitButton">Update information</form:button>
					<p id="submitStatus"></p></td>
			</tr>
		</form:form>

	</table>
	<br />
	<br />
	<a href="<spring:url value="/user/index"/>">Back</a>

</body>
</html>