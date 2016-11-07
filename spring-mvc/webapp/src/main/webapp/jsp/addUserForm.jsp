<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"
	isELIgnored="false"%>
<html>
<head>
<title>Add User</title>
<link rel="stylesheet"
	href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<link rel="stylesheet" href="/resources/demos/style.css">
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script>
	$(function() {
		$("#datepicker_start").datepicker({
			dateFormat : "yy-mm-dd"
		});
	});
	$(function() {
		$("#datepicker_end").datepicker({
			dateFormat : "yy-mm-dd"
		});
	});
</script>
</head>
<body>
	<spring:url value="/user/save" var="saveUserUrl" />
	<form:form modelAttribute="User" action="${saveUserUrl}" method="POST">
		<p>Login:</p>
		<form:input path="login" />
		<p>Password:</p>
		<form:input path="password" />
		<p>Role:</p>
		<form:input path="role" />

		<p>Start date:</p>
		<form:input type="text" id="datepicker_start" path="startDate" />

		<p>End date:</p>
		<form:input type="text" id="datepicker_end" path="endDate" />

		<br />
		<form:button>Add</form:button>
	</form:form>
	<a href="<spring:url value="/user/index"/>">Back</a>

</body>
</html>