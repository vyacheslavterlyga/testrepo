<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"
	isELIgnored="false"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>Welcome, ${User.login}!</title>

</head>
<body>

	<h2>Hello, ${User.login}!</h2>
	<p>Your ID in our database: ${User.id}</p>

	<a href="<spring:url value="/user/add"/>">Add User</a>

	<h2>
		<a href="<spring:url value="/user/allUsersList"/>">All users</a>
	</h2>

</body>
</html>