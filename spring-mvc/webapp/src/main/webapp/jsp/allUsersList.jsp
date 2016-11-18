<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"
	isELIgnored="false"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>All users</title>
<script src="<spring:url value="/webjars/jquery/1.12.0/jquery.js" />"></script>
<script
	src="<spring:url value="/webjars/jquery-ui/1.12.1/jquery-ui.js" />"></script>
<script
	src="<spring:url value="/webjars/datatables/1.10.12/media/js/jquery.dataTables.js " />"></script>
<script
	src="<spring:url value="/webjars/datatables.net-buttons/1.2.2/js/dataTables.buttons.js" />"></script>
<script
	src="<spring:url value="/webjars/datatables.net-buttons/1.2.2/js/buttons.flash.js" />"></script>
<script	src="https://cdn.datatables.net/select/1.2.0/js/dataTables.select.min.js"></script>
<link rel="stylesheet"
	href="<spring:url value="/webjars/jquery-ui/1.12.1/jquery-ui.css"/>">
<link rel="stylesheet"
	href="<spring:url value="/webjars/datatables/1.10.12/media/css/jquery.dataTables.css"/>">
<script>
	$(document).ready(function() {
		var data = eval('${userListJson}');
		var table = $('#tableJson').DataTable({
			//dom : 'Bfrtip',
			//select : 'row',
			//buttons : [ 'edit' ],
			aaData : data,
			aoColumns : [ {
				"mData" : "login"
			}, {
				"mData" : "password"
			}, {
				"mData" : "role"
			}, {
				"mData" : "startDate"
			}, {
				"mData" : "endDate"
			} , {
	        	"data": "id",
	            render: function ( data, type, row, meta ) {
	            	var user_role = $('#user_role').val();	
	            	var user_login = $('#user_login').val();	
	            
	            	if(user_role == 'ADMIN' || (user_role == 'USER' && user_login == row.login)){
	            		return '<a href="update?userId=' + data + '">' + "edit" + '</a>';
	            	} else {
	            		return "";
	            	}
	                
	        	}
			}]
		}); 
	});
</script>

</head>
<body>
<jsp:include page="menu.jsp"></jsp:include>
	<input id="user_role" hidden="true" value="${UserRole}"/>
	<input id="user_login" hidden="true" value="${UserLogin}"/>

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
				<spring:message code='datePattern' var="datePattern" />
				<td><fmt:formatDate value="${listValue.startDate}"
						pattern="${datePattern}" /></td>
				<td><fmt:formatDate value="${listValue.endDate}"
						pattern="${datePattern}" /></td>
				<c:if
					test="${UserRole == 'ADMIN' || (UserRole == 'USER' && UserLogin == listValue.login)}">
					<td><spring:url value="/user/update" var="urlUpdate">
							<spring:param name="userId" value="${listValue.id}" />
						</spring:url> <a href="${urlUpdate}">edit</a></td>
				</c:if>
			</tr>
		</c:forEach>
	</table>
	<br />
	<br />
	<table id='tableJson' border="1">
		<thead>
			<tr>
				<th>Login</th>
				<th>Password</th>
				<th>Role</th>
				<th>Start date</th>
				<th>End date</th>
				<th>Edit</th>
			</tr>
		</thead>
	</table>

	<p>
		<a href="<spring:url value="/user/index"/>">Back</a>
	</p>
</body>
</html>