<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Admin Homepage</title>
</head>
<body>
	admin page
	<br>
	<c:if test="${sessionScope.acc != null}">
	Hello ${sessionScope.acc.getUsername()}
	<br>
		<a href="<c:url value='/logout' />">Thoát</a>
	</c:if>

</body>
</html>