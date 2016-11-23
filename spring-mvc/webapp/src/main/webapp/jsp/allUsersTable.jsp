<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<table border='1'>
	<thead>
		<tr>
			<th>Login</th>
			<th>Password</th>
			<th>Role</th>
			<th>Start date</th>
			<th>End date</th>
		</tr>
	</thead>
	<c:forEach var="listValue" items="${lists}">
		<tr>
			<td>${listValue.login}</td>
			<td>${listValue.password}</td>
			<td>${listValue.role}</td>
			<td>${listValue.startDate}</td>
			<td>${listValue.endDate}</td>
			<c:if test="${UserRole == 'ADMIN' || (UserRole == 'USER' && UserLogin == listValue.login)}">
				<td><spring:url value="/user/addOrUpdateUser" var="addOrUpdateUserUrl">
						<spring:param name="userId" value="${listValue.id}" />
					</spring:url> <a href="${urlUpdate}">edit</a></td>
			</c:if>
		</tr>
	</c:forEach>
</table>