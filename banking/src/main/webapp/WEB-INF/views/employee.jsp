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

<style>
.error {
	color: red
}
</style>
	
<link href="<c:url value='/resources/css/bootstrap.css' />" rel="stylesheet"></link>

<link href="<c:url value='/resources/css/app.css' />" rel="stylesheet"></link>

</head>
<body>
<div class="generic-container">
	<div id="loginbox" style="margin-top: 50px;"
		class="mainbox col-md-3 col-md-offset-2 col-sm-6 col-sm-offset-2">

		<p>Employee: ${employeeName }</p>

		<!-- Message after successful action (account opening, customer registration or transfer between accounts) -->
		<p class="error">${transactionMssg}</p><br>
		${successMssg}<br>
		${actionResult}

		<!-- Button for new Customer registration -->
		<a href="${pageContext.request.contextPath}/employee/register"
			class="btn btn-primary" role="button">New Customer Registration</a>
<br><br>
		<!-- Button for new Account opening -->
		<a href="${pageContext.request.contextPath}/employee/open-account"
			class="btn btn-primary" role="button">New Account Opening</a>
			
<br><br>
		<!-- Button for Transferring funds -->
		<a href="${pageContext.request.contextPath}/employee/transfer-funds"
			class="btn btn-primary" role="button">Transfer funds</a>
<br><br>
		<!-- Button to Deposit money -->
		<a href="${pageContext.request.contextPath}/employee/deposit"
			class="btn btn-primary" role="button">Deposit money</a>
<br><br>
		<!-- Button to withdraw money -->
		<a href="${pageContext.request.contextPath}/employee/withdraw"
			class="btn btn-primary" role="button">Withdraw money</a>
			
<br><br>
		<!-- Button for Searching Transactions -->
		<a href="${pageContext.request.contextPath}/employee/search-transactions"
			class="btn btn-primary" role="button">Search Transactions</a>
<br><br>
		<!-- Button to Log out	 -->
		<a href="${pageContext.request.contextPath}/logout"
			class="btn btn-success" role="button">Logout</a>
	</div>
	</div>
</body>
</html>