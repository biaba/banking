<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
	
<link href="<c:url value='/resources/css/bootstrap.css' />" rel="stylesheet"></link>

<link href="<c:url value='/resources/css/app.css' />" rel="stylesheet"></link>

</head>
<body>
<div class="generic-container">

<h2>Online banking</h2>
	
<form:form action="${pageContext.request.contextPath}/login" method="get" >	
	<input type="submit" class="btn btn-success" value="login"/>
</form:form>
<c:if test="${param.logout != null }">
<p>Your online banking session is finished
</c:if>


</div>
</body>
</html>
