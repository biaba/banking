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

	
<link href="<c:url value='/resources/css/bootstrap.css' />" rel="stylesheet"></link>

<link href="<c:url value='/resources/css/app.css' />" rel="stylesheet"></link>

<style>
.error {
	color: red
}
</style>
<title>Transferring Funds</title>
<script>
	$(document).ready(function() {
		
		
$("#lookUpAccount2").click(function() {
			
			$.ajax({
				url : 'transfer_funds/findAccount',

				data : {
					userName : $("#userName2").val()
				},

				success : function(result) {
					if(result == "no account found"){
						$("#string_credit_account").val('');
						$("#errmsg2").text('No account found.');
						$("#msg2").text('');
					} 
					if(result != "no account found"){
						$("#string_credit_account").val(result);
						$("#msg2").text('Account found');
						$("#errmsg2").text('');
					}
				}

			});
	});
		
		
		
		
		$("#lookUpAccount").click(function() {
			
			$.ajax({
				url : 'transfer_funds/findAccount',

				data : {
					userName : $("#userName").val()
				},

				success : function(result) {
					if(result == "no account found"){
						$("#string_debit_account").val('');
						$("#errmsg").text('No account found.');
						$("#msg").text('');
					} 
					if(result != "no account found"){
						$("#string_debit_account").val(result);
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
	<h1>Transferring funds between two Accounts</h1>
	<p class="error">${transactionSuccessMsg}</p>
	<form:form action="${pageContext.request.contextPath}/employee/transfer-funds/processing" modelAttribute="transaction">



		<!-- Debiting Account -->
<b>From account :</b>
		<!-- Ajax call - looking up for Account by username -->
		<br>
Type in a username to look up for Account: 
		<br>
		<span class="error" id="errmsg"></span>
		<span class="error" id="msg"></span>
		<br>
		<input type="text" id="userName" />
		<input type="button" value="look up" id="lookUpAccount" />
		<br>

		<br>
		<form:input path="string_debit_account"/>
		<br>
			<form:errors path="string_debit_account" cssClass="error" />
		<br>
		<br>
	<!-- Crediting Account -->

<!-- Debiting Account -->
<b>To account :</b>
		<!-- Ajax call - looking up for Account by username -->
		<br>
Type in a username to look up for Account: 
		<br>
		<span class="error" id="errmsg2"></span>
		<span class="error" id="msg2"></span>
		<br>
		<input type="text" id="userName2" />
		<input type="button" value="look up" id="lookUpAccount2" />
		<br>
		<br>
		<form:input path="string_credit_account"/>
		<br>
			<form:errors path="string_credit_account" cssClass="error" />
		<br>
		<br>

Amount:
		<br>
		<form:input path="amount"/>
		<br>
			<form:errors path="amount" cssClass="error" />
		<br>
		<br>
		<input type="submit" value="Transfer" class="btn btn-success custom-width" />
	</form:form>
	<br>
	<br>
	<!-- Button to return to main page -->
	<a href="${pageContext.request.contextPath}/employee/main"
		class="btn btn-success" role="button">Back to main</a>
	<br>
	<br>	
</div>
</body>
</html>