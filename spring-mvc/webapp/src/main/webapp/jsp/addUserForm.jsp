<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"
	isELIgnored="false"%>
<html>
<head>
<title>Add User</title>
<script src="<spring:url value="/webjars/jquery/1.12.0/jquery.js" />"></script>
<script src="<spring:url value="/webjars/jquery-ui/1.12.1/jquery-ui.js" />"></script>
<link rel="stylesheet" href="<spring:url value="/webjars/jquery-ui/1.12.1/jquery-ui.css"/>">
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