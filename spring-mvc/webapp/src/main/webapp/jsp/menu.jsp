<%@ page contentType="text/html;charset=UTF-8" language="java"
	isELIgnored="false"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page
	import="org.springframework.security.core.userdetails.UserDetails"%>
<%@ page
	import="org.springframework.security.core.context.SecurityContextHolder"%>
<%
	UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication()
			.getPrincipal();
%>
<link rel="stylesheet" href="\webapp\css\menu.css">
<div id="mainMenu">
	<div class="menuLabel">
		<div>
			Hi,
			<%=userDetails.getUsername()%>!
		</div>
	</div>
	<div class="menuItem">
		<spring:url value='/logout' var="urlLogout"/>
		<form:form id="logoutForm" action="${urlLogout}" method="post" style="cursor: pointer; margin: 0 auto;">
			<a onclick="logoutForm.submit()" style="text-decoration: none;">
				<div>Logout</div>
			</a>
		</form:form>
	</div>
	<div class="menuItem">
		<spring:url value='index' var="urlIndexUser"/>
		<a href="${urlIndexUser}" style="text-decoration: none;">
			<div>My account</div>
		</a>
	</div>
	<div class="menuItem">
		<c:import url="languageList.jsp"></c:import>
	</div>
</div>