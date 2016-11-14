<%@ page contentType="text/html; charset=UTF-8" language="java"
	isELIgnored="false"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<html>
<head>
</head>
<body>
	<jsp:include page="menu.jsp"></jsp:include>
	<h2>Default start page</h2>
	<a href="<spring:url value="/user/index"/>">User info page</a>
</body>
</html>

