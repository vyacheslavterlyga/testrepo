<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Redirect</title>
	<link rel='stylesheet' href='<spring:url value="/webjars/jquery-ui/1.12.1/jquery-ui.min.css"/>'>
</head>
<body>
	<h2>Default start page</h2>
	<a href="<spring:url value="/user/index"/>">User info page</a>
</body>
</html>

