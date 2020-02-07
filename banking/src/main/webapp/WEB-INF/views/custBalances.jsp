<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" trimDirectiveWhitespaces="true"
	isELIgnored="false"%>
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


<link href="<c:url value='/resources/css/bootstrap.css' />"
	rel="stylesheet"></link>

<link href="<c:url value='/resources/css/app.css' />" rel="stylesheet"></link>

<style>
.error {
	color: red
}
</style>
<title>Balances</title>

</head>
<body>
	<div class="generic-container">
		<h1>Balances</h1>


		
		<table class="table table-hover">
			<thead>
				<tr>
					<th>Account number</th>
					<th>Account type</th>
					<th>Balance</th>
				</tr>
			</thead>

			<tbody>
				<c:forEach var="acc" items="${accounts}">
					<tr>
						<td>${acc.accountNumber}</td>
						<td>${acc.type}</td>
						<td>${acc.balance}</td>
					</tr>
				</c:forEach>

			</tbody>
		</table>
		<br><br>
		<!-- Button to return to main page -->
		<a href="${pageContext.request.contextPath}/customer/main"
			class="btn btn-success" role="button">Back to main</a>
		<br><br>
	</div>
</body>
</html>