<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page contentType="text/html; charset=UTF-8" language="java"
	isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Welcome, ${User.login}!</title>

<script src="<spring:url value="/webjars/jquery/1.12.0/jquery.js" />"></script>

<script>
	$(document)
			.ready(
					function() {
						$(".toggleButton").hide();
						$("#personalInfo").click(function() {
							$(".toggleButton").toggle();
						});
						$("#addLink").hide();
						$("#personalInfoRole")
								.click(
										function() {
											var role = "${User.role}";
											var greeting;
											if (role == "ADMIN") {
												greeting = "Congrats, you're admin!) You can change the values in database";
												$("#addLink").show();
											} else if (role == "USER") {
												greeting = "You're not admin! We're sorry(";
											} else {
												greeting = "You're just a guest now! Please log in the system";
											}
											$("#roleSwither").text(greeting);
										});
					});
</script>
</head>

<body>

	<h2>Hello, ${User.login}!</h2>

	<button id="personalInfo">Show/hide personal info</button>
	<p class="toggleButton">Your ID in our database: ${User.id}</p>
	<p class="toggleButton">Your login is "${User.login}"</p>
	<p class="toggleButton">Your password is "${User.password}"</p>
	<p class="toggleButton">Your start date is "${User.startDate}"</p>
	<p class="toggleButton">Your end date is "${User.endDate}"</p>

	<p>Push the button to request your rights:</p>
	<button id="personalInfoRole">Request</button>
	<p id="roleSwither"></p>

	<h2>
		<a id="addLink" href="<spring:url value="/user/add"/>">Add new
			user</a>
	</h2>

	<h2>
		<a href="<spring:url value="/user/allUsersList"/>">View all users</a>
	</h2>
	<h2>
		<spring:url value="/user/update" var="urlUpdate">
			<spring:param name="userId" value="${User.id}" />
		</spring:url>
		<a href="${urlUpdate}">Update your information</a>
	</h2>

</body>
</html>