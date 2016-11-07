<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"
	isELIgnored="false"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>All users</title>

</head>
<body>
	<table border="2">
		<tr>
			<td>Login</td>
			<td>Password</td>
			<td>Role</td>
			<td>Start date</td>
			<td>End date</td>
		</tr>
		<c:forEach var="listValue" items="${lists}">
			<tr>
				<td>${listValue.login}</td>
				<td>${listValue.password}</td>
				<td>${listValue.role}</td>
				<td>${listValue.startDate}</td>
				<td>${listValue.endDate}</td>
			</tr>
		</c:forEach>
	</table>

	<p>
		<a href="<spring:url value="/user/index"/>">Back</a>
	</p>
</body>
</html>