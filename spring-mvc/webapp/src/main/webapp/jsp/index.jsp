<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"
	isELIgnored="false"%>
<html>
<body>
	<h2>Hello, ${User.login}!</h2>
	<p>Your ID in the database: ${User.id}</p>
	<h1>_________________________________________</h1>
	<h3>You can see list of all users below:</h3>
	<p>$(UserDAOImpl.getAllUsers)</p>
</body>
</html>
