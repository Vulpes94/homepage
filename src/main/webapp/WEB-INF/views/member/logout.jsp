<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="root" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<c:set var="m_num" scope="session" />
	<c:remove var="id" scope="session" />
	<c:remove var="premission" scope="session" />

	<script type="text/javascript">
		alert("로그아웃 되었습니다.");
		location.href = "${root}/member/login.do";
	</script>
</body>
</html>