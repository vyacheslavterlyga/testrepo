<%@ page contentType="text/html;charset=UTF-8" language="java"
	isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
		<div id="logout">
			<a href="/webapp/login?logout">Logout</a>
		</div>
	</div>
	<div class="menuItem">
		<div>
			<a href="/webapp/user/index" style="text-decoration: none;">My
				account</a>
		</div>
	</div>
	<div class="menuItem">
		<c:import url="languageList.jsp"></c:import>
	</div>
</div>