<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<title>Trang chu</title>
</head>
<body>

	Home Page
	<br>
	<c:if test="${sessionScope.acc != null}">
Hello ${sessionScope.acc.getUsername()}
<br>
		<a href="<c:url value='/logout' />">Thoát</a>
		<br>
		<a href="<c:url value='/change-password' />">Đổi mật khẩu</a>
	</c:if>

</body>
</html>
