<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"
	isELIgnored="false"%>
<html>
<body>
	<h2>Hello World!!!</h2>
	<p>Id: ${User.id}</p>
	<p>Login user : ${User.login}</p>
	<a href="<spring:url value="/addUser"/>">Add User</a>	
	<h2>All users:</h2>
	<p>$(UserDAO.getAllUsers)</p>
</body>
</html>
