<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"	isELIgnored="false"%>
<html>
<head>
<title>Add User</title>
</head>
<body>
	<spring:url value="addUser" var="urlAddUser"></spring:url>
	<form:form modelAttribute="UserJSP" action="${urlAddUser}" method="GET">
		<p>Login:</p>
		<form:input path="login"/>
		<p>Password:</p>
		<form:input path="password"/>
		<p>Role:</p>
		<form:input path="role"/>
		<p>Start date:</p>
		<form:input path="startDate"/>
		<p>End date:</p>
		<form:input path="endDate"/>
		<br />
		<form:button>Add</form:button>
	</form:form>
</body>
</html>