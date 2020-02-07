<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>


<link href="<c:url value='/resources/css/bootstrap.css' />"
	rel="stylesheet"></link>

<link href="<c:url value='/resources/css/app.css' />" rel="stylesheet"></link>

</head>
<body>
	<div class="generic-container">
		${userName}
		<security:authorize access="hasRole('CUSTOMER')">
			<form:form action="${pageContext.request.contextPath}/customer/main"
				method="get">
				<input type="submit" value="customer" />
			</form:form>
		</security:authorize>

		<security:authorize access="hasRole('EMPLOYEE')">
			<form:form action="${pageContext.request.contextPath}/employee/main"
				method="get">
				<input type="submit" value="employee" />
			</form:form>
		</security:authorize>

		<!-- Log out option -->
		<form:form action="${pageContext.request.contextPath}/logout"
			method="POST">

			<input type="submit" value="logout"
				class="btn btn-success custom-width" />

		</form:form>
	</div>
</body>
</html>