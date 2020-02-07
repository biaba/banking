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
<title>Search Transactions</title>
<script>
	$(document).ready(function() {

		$("#lookUpAccount").click(function() {

			$.ajax({
				url : 'findAccount',

				data : {
					userName : $("#userName").val()
				},

				success : function(result) {
					if (result == "no account found") {
						$("#string_account").val('');
						$("#string_account2").val('');
						$("#errmsg").text('No account found.');
						$("#msg").text('');
					}
					if (result != "no account found") {
						$("#string_account").val(result);
						$("#string_account2").val(result);
						$("#msg").text('Account found');
						$("#errmsg").text('');
					}
				}

			});

		});

	});
</script>
</head>
<body>
	<div class="generic-container">
		<h1>Search Transactions for user</h1>
		<p class="error">${transactionSuccessMsg}</p>


		<!-- Json response for last year transactions -->

		<form
			action="${pageContext.request.contextPath}/employee/search-transactions/account"
			method="get">


			Type in a username to look up for Account: <br> <span
				class="error" id="errmsg"></span> <span class="error" id="msg"></span>
			<br> <input type="text" id="userName" /> <input type="button"
				value="look up" id="lookUpAccount" /> <br> <br /> <label>Account:
			</label> <input type="text" name="string_account" id="string_account">
			<br> <b>Within last year - returned .json</b> <input
				type="submit" value="search" class="btn btn-success custom-width" />
		</form>

		<!-- html response - transactions between two dates -->

		<form
			action="${pageContext.request.contextPath}/employee/search-transactions/account-by-dates"
			method="get">

			<br> <input type="hidden" name="string_account"
				id="string_account2"> <label>From Date:</label> <i>dd/mm/yyy</i>
			<input type="text" name="from"> <br> <label>To
				Date:</label> <i>dd/mm/yyy</i> <input type="text" name="to"> <br>
			<b>Between dates - displayed on page</b> <input type="submit"
				value="search" class="btn btn-success custom-width" />
		</form>

		<!-- Search results displayed -->

		<br>






		<div class="panel-heading">
			<span class="lead">Transactions </span>
		</div>
		<table class="table table-hover">
			<thead>
				<tr>
					<th>Amount</th>
					<th>Date</th>
					<th>Status</th>
					<th>Credit</th>
					<th>Debit</th>
				</tr>
			</thead>
			<tbody>


				<c:forEach var="tx" items="${txList}">
					<tr>
						<td>${tx.amount}</td>
						<td>${tx.date}</td>
						<td>${tx.status}</td>
						<td>${tx.credit_account.accountNumber}</td>
						<td>${tx.debit_account.accountNumber}</td>



					</tr>
				</c:forEach>

			</tbody>
		</table>

		<br> <br>
		<!-- Button to return to main page -->
		<a href="${pageContext.request.contextPath}/employee/main"
			class="btn btn-success" role="button">Back to main</a> <br> <br>
	</div>



</body>
</html>