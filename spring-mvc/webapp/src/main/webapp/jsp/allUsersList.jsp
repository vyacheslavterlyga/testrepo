<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>All users</title>
<script src="<spring:url value="/webjars/jquery/1.12.0/jquery.js" />"></script>
<script src="<spring:url value="/webjars/jquery-ui/1.12.1/jquery-ui.js" />"></script>
<script src="<spring:url value="/webjars/datatables/1.10.12/media/js/jquery.dataTables.js " 

/>"></script>
<script src="<spring:url value="/webjars/datatables.net-buttons/1.2.2/js/dataTables.buttons.js" 

/>"></script>
<script src="<spring:url value="/webjars/datatables.net-buttons/1.2.2/js/buttons.flash.js" 

/>"></script>
<script src="https://cdn.datatables.net/select/1.2.0/js/dataTables.select.min.js"></script>
<link rel="stylesheet" href="<spring:url value="/webjars/jquery-ui/1.12.1/jquery-ui.css"/>">
<link rel="stylesheet" href="<spring:url 

value="/webjars/datatables/1.10.12/media/css/jquery.dataTables.css"/>">
<script>
	$(document)
			.ready(
					function() {
						var data = eval('${userListJson}');
						var table = $('#tableJson').DataTable({
											aaData : data,
											aoColumns : [
													{
														"mData" : "login"
													},
													{
														"mData" : "password"
													},
													{
														"mData" : "role"
													},
													{
														"mData" : "startDate"
													},
													{
														"mData" : "endDate"
													},
													{
														"data" : "id",
														render : function(data, type, row, meta) {
															var user_role = $('#user_role').val();
															var user_login = $('#user_login').val();

															if (user_role == 'ADMIN' || (user_role == 'USER' && user_login == row.login)) {
																return '<a href="update?userId=' + data + '">' + "edit" + '</a>';
															} else {
																return "";
															}
														}
													} ]
										});

						var order = "id";
						var asc = true;
												
						$('#showTable').click(function() {
							showTable();
						});

						var firstRow = 0;
						
						$('#prev').click(function() {
							firstRow -= parseInt($('#showEntitiesSelector').val());
							if (firstRow < 0) {
								firstRow = 0;
							}
							showTable();
						});
											
						$('#next').click(
								function() {
									firstRow += parseInt($('#showEntitiesSelector').val());
									if (firstRow + parseInt($('#showEntitiesSelector').val()) <= parseInt($('#count').val())) {
										showTable();
									}
						});
						
						$('#first').click(
								function() {
									firstRow = 0;
									showTable();
									});
						
						$('#last').click(
								function() {
									firstRow = parseInt($('#count').val())-(parseInt($('#count').val())%$('#showEntitiesSelector').val());
									showTable();
							});
																	
							var showTable = function() {
							$('#curr').text(firstRow / parseInt($('#showEntitiesSelector').val()) + 1);
							var entitiesOnTable = parseInt($('#showEntitiesSelector').val());
								$.get("getAllUsersTable", {
								"firstRow" : firstRow,
								"countRows" :	entitiesOnTable,
								"orderBy" : order,
								"asc" : asc
							}).done(function(data) {
								$('#allUsersTable').html (data);
								if (asc) {
									$('#' + order).children().text(' ↑');
								} else {
									$('#' + order).children().text(' ↓');
								}
								$('.selectedColumn').click(function(){
									order = $(this).attr('id');
									if (asc) {															
										asc = false;
									} else {										
									asc = true;
									};
									showTable();
								});														
							});
						}
						showTable();
					});
</script>
</head>
<body>
	<jsp:include page="menu.jsp"></jsp:include>
	<input id="user_role" hidden="true" value="${UserRole}" />
	<input id="user_login" hidden="true" value="${UserLogin}" />
	<input id="count" hidden="true" value="${Count}" />
	<p id="showTable">
		Show <select id="showEntitiesSelector">
			<option>5</option>
			<option>10</option>
			<option>15</option>
			<option>20</option>
			<option>25</option>
			<option>50</option>
			<option>100</option>
		</select> of ${Count} entities <br /> <br />
		<button id='first'> |<< First </button>
		<button id='prev'> <<< Prev. </button>
		<button id='curr'></button>
		<button id='next'> Next >>> </button>
		<button id='last'> Last >>| </button>
	</p>
	<div id="allUsersTable"></div>
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