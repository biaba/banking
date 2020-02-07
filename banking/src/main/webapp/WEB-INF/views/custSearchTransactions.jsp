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
	$(document)
			.ready(
					function() {

						$
								.ajax({
									url : 'findUserAccounts',
									dataType : "json",

									data : {
										userName : $("#userName").val()
									},

									success : function(result) {

										var s = '';
										for (var i = 0; i < result.length; i++) {
											s += '<option value="' + result[i].accountNumber + '">'
													+ result[i].accountNumber
													+ '</option>';
										}
										$('#selectBox').html(s);

									}
								});
					});
</script>
</head>
<body>

	<div class="generic-container">
		<h1>Search Transactions</h1>
		<p class="error">${transactionSuccessMsg}</p>


		<!-- Last year transactions -->

		<form
			action="${pageContext.request.contextPath}/customer/transactions/search1"
			method="get">



			<table>
				<tr>
					<td>Account</td>
					<td><select id="selectBox" name="string_account">
					</select></td>
				</tr>
			</table>

			<br> <input type="hidden" name="userName" value="${userName }"
				id="userName" /> <br>




			<!-- Transactions between two dates -->


			<label>From Date:</label> <i>dd/mm/yyy</i> <input type="text"
				name="from" pattern="[0-3][0-9][/][0-1][0-9][/][1-2][0-9][0-9][0-9]"
				title=" 19/01/1999"> <br> <label>To Date:</label> <i>dd/mm/yyy</i>
			<input type="text" name="to"
				pattern="[0-3][0-9][/][0-1][0-9][/][1-2][0-9][0-9][0-9]"
				title=" 19/01/1999"> <br> <input type="submit"
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
		<br>
		<br>
		<!-- Button to return to main page -->
		<a href="${pageContext.request.contextPath}/customer/main"
			class="btn btn-success" role="button">Back to main</a> <br>
		<br>

	</div>



</body>
</html>