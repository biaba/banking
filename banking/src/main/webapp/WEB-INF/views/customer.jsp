<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>

<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

<link href="<c:url value='/resources/css/bootstrap.css' />"
	rel="stylesheet"></link>

<link href="<c:url value='/resources/css/app.css' />" rel="stylesheet"></link>

<style>
.error {
	color: red
}
</style>
</head>
<body>
	<div class="generic-container">
		<div id="loginbox" style="margin-top: 50px;"
			class="mainbox col-md-3 col-md-offset-2 col-sm-6 col-sm-offset-2">

			<p>Customer: ${customerName }</p>

			<!-- Message after successful action (account opening, customer registration or transfer between accounts) -->
			<p class="error">${transactionMssg}</p>
			<br> ${successMssg}<br> ${actionResult}

			<!-- Button for seeing balances -->
			<a href="${pageContext.request.contextPath}/customer/balances"
				class="btn btn-primary" role="button">Balances</a> <br>
			<br>

			<!-- Button for searching transactions -->
			<a
				href="${pageContext.request.contextPath}/customer/transactions/search"
				class="btn btn-primary" role="button">Search Transactions</a> <br>
			<br>
			<br>
			<br>
			<!-- Button to Log out	 -->
			<a href="${pageContext.request.contextPath}/logout"
				class="btn btn-success" role="button">Logout</a>
		</div>
	</div>
</body>
</html>