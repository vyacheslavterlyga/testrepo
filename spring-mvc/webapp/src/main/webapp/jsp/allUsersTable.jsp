<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<table border='1'>
	<thead>
		<tr>
			<th class="selectedColumn" id="login">Login<a></a></th>
			<th class="selectedColumn" id="password">Password<a></a></th>
			<th class="selectedColumn" id="role">Role<a></a></th>
			<th class="selectedColumn" id="startDate">Start date<a></a></th>
			<th class="selectedColumn" id="endDate">End date<a></a></th>
		</tr>
	</thead>
	<c:forEach var="listValue" items="${lists}">
		<tr>
			<td>${listValue.login}</td>
			<td>${listValue.password}</td>
			<td>${listValue.role}</td>
			<td>${listValue.startDate.toGregorianCalendar().time}</td>
			<td>${listValue.endDate.toGregorianCalendar().time}</td>
			<c:if test="${UserRole == 'ADMIN' || (UserRole == 'USER' && UserLogin == listValue.login)}">
				<td><spring:url value="/user/addOrUpdateUser" var="addOrUpdateUserUrl">
						<spring:param name="userId" value="${listValue.id}" />
					</spring:url> <a href="${addOrUpdateUserUrl}">edit</a></td>
			</c:if>
		</tr>
	</c:forEach>
</table>