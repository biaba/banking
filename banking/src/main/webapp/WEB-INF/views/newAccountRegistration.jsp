<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>

<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>


<script>
	$(document)
			.ready(
					function() {

						$("#lookUpUser")
								.click(
										function() {

											$
													.ajax({
														url : 'register/findCustomer',

														data : {
															userName : $(
																	"#userName")
																	.val()
														},

														success : function(
																result) {
															if (result == "no user found") {
																$("#userName")
																		.val('');
																$("#errmsg")
																		.text(
																				'No user found. You can^t open account for non existing customer');
																$("#id")
																		.val('');
																$("#msg").text(
																		'');
															}
															if (result != "no user found") {
																$("#id").val(
																		result);
																$("#msg")
																		.text(
																				'user with id '
																						+ result
																						+ ' found');
																$("#errmsg")
																		.text(
																				'');
															}
														}

													});

										});

					});
</script>

<link href="<c:url value='/resources/css/bootstrap.css' />"
	rel="stylesheet"></link>

<link href="<c:url value='/resources/css/app.css' />" rel="stylesheet"></link>

<style>
.error {
	color: red
}
</style>
<title>New Account Opening</title>

</head>
<body>
	<div class="generic-container">
		<h1>Account opening form</h1>
		<p class="error">${noSuccessMsg}</p>
		<form:form
			action="${pageContext.request.contextPath}/employee/open-account/processing"
			modelAttribute="account">



			<!-- Choosing user // Ajax to find a customer by typing in a username -->

Customer: (Type in a username to look up for an existing customer) 
		<br>
			<span class="error" id="errmsg"></span>
			<span class="error" id="msg"></span>
			<br>
			<input type="text" id="userName" />
			<input type="button" value="look up" id="lookUpUser" />
			<br>


			<!-- Ajax response - filling in customers id -->


			<form:input path="userId" id="id" type="hidden" />
			<br>
			<!-- Saving or Current account -->

Account Type :
		<br>
		Current <form:radiobutton path="type" value="current" />
		Saving <form:radiobutton path="type" value="saving" />
			<br>
			<form:errors path="type" cssClass="error" />
			<br>
			<br>

Deposit:
<br>
			<form:input path="balance" />
			<form:errors path="balance" cssClass="error" />
			<br>
			<br>
			<input type="submit" value="Open"
				class="btn btn-success custom-width" />
		</form:form>
		<br> <br>
		<!-- Button to return to main page -->
		<a href="${pageContext.request.contextPath}/employee/main"
			class="btn btn-success" role="button">Back to main</a> <br> <br>
	</div>
</body>
</html>