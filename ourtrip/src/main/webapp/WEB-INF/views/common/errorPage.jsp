<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isErrorPage="true"%>
<%
	String errorMsg = (String)request.getAttribute("errorMsg");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Error Page</title>
</head>
<body>
	<h1 align="center"> <%= errorMsg %> </h1>
	<div align="center">
		<button onclick="history.back();">이전 페이지</button>
		<button onclick="location.href='<%= request.getContextPath()%>'">홈으로 돌아가기</button>
	</div>
</body>
</html>