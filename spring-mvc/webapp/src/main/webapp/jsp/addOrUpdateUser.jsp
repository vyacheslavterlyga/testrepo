<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false"%>
<spring:message code='datePattern' var="datePattern" />
<html>
<head>
<title>Edit User</title>
<script src="<spring:url value="/webjars/jquery/1.12.0/jquery.js" />"></script>
<script src="<spring:url value="/webjars/jquery-ui/1.12.1/jquery-ui.js" />"></script>
<link rel="stylesheet" href="<spring:url value="/webjars/jquery-ui/1.12.1/jquery-ui.css"/>">
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
		
		$("#login").change(function() {
			$.get("validateLogin", {
				login : $("#login").val()
			}).done(function(data) {
				if (data == "false") {
					$("#errorMessage").text("Incorrect login");
				} else {
					$("#errorMessage").text("");
				}
			});
		});
		
		function() {
			loginField = $("#login").val();
			if (loginField != null) {
				$("#login").prop('disabled', true);
				$('#submitButton').setText('Update info');
			} else {
				$('#submitButton').setText('Add this user');
			}
		};
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
	<jsp:include page="menu.jsp"></jsp:include>
	<br />
	<p id="errorMessage">${errorMessage}</p>
	<table>
		<spring:url value="/user/addOrUpdateUser" var="addOrUpdateUser" />
		<form:form modelAttribute="User" action="${addOrUpdateUserUrl}" method="POST">
			<form:hidden path="id" />
			<form:hidden path="login" />
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
				<td><spring:message code='label.firstName' /></td>
				<td><form:input path="firstName" /></td>
			</tr>
			<tr>
				<td><spring:message code='label.lastName' /></td>
				<td><form:input path="lastName" /></td>
			</tr>
			<tr>
				<td><spring:message code='label.age' /></td>
				<td><form:input path="age" /></td>
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
				<td><form:button id='submitButton'></form:button>
			</tr>
		</form:form>
	</table>
	<br />
	<br />
</body>
</html>