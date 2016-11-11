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
	var startDateField;
	var endDateField;
	var loginField;
	var passwordField;
	var roleField;

	$(document).ready(function() {
		dateFormatLocation = "<spring:message code='datePatternJQuery'/>";
		$('#submitButton').prop('disabled', true);

		$("#startDate").datepicker();
		$("#startDate").change(function() {
			startDateField = $(this).datepicker("getDate");
			validateDate();
			dateFormat: dateFormatLocation;
		});

		$("#endDate").datepicker();
		$("#endDate").change(function() {
			endDateField = $(this).datepicker("getDate");
			validateDate();
			dateFormat: dateFormatLocation;
		});
	});

	function validateDate() {
		if (startDateField == "") {
			alert('Start date should be filled!');
			$("#startDate").focus();
		} else if (endDateField == "") {
			alert('End date should be filled!');
			$("#endDate").focus();
		} else if ((startDateField == "") || (endDateField == "")) {
			alert('Date fields should be filled!');
			$("#startDate").focus();
		} else if (startDateField > endDateField) {
			alert('Start date should be before End date!');
			$.datepicker._clearDate('#startDate');
			$("#startDate").focus();
		}
		checkEmptyFields();
	}

	function checkEmptyFields() {
		loginField = $("#login").val();
		passwordField = $("#password").val();
		roleField = $("#role").val();

		if (loginField != "" && passwordField != "" && roleField != ""
				&& startDateField != null && endDateField != null) {
			$('#submitButton').prop("disabled", false);
		}
	}
</script>
</head>

<body>
	<jsp:include page="languageList.jsp"></jsp:include>
	<br />
	<table>
		<spring:url value="/user/saveNewUser" var="saveUserUrl" />
		<form:form modelAttribute="User" action="${saveUserUrl}" method="POST">
			<tr>
				<td><spring:message code='label.login' /></td>
				<td><form:input type="textbox" path="login" value="" /></td>
			</tr>
			<tr>
				<td><spring:message code='label.password' /></td>
				<td><form:input type="textbox" path="password" value="" /></td>
			</tr>
			<tr>
				<td><spring:message code='label.role' /></td>
				<td><form:input type="textbox" path="role" value="" /></td>
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
				<td><form:button id='submitButton'>Add this user</form:button>
			</tr>
		</form:form>
	</table>
	<br />
	<br />
	<a href="<spring:url value="/user/index"/>">Back</a>
</body>
</html>