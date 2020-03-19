<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
</head>
<body>
<c:set var="contextPath" value="${pageContext.servletContext.contextPath }" scope="application"/>

<c:if test="${!empty msg}">
	<script>alert("${msg}")</script>
	<c:remove var="msg"/>
</c:if>

<!--start header area-->
<div class="container-fluid px-0" style="background-color: white;">
    <div class="container my-0 px-0">
        <div class="d-flex">
            <a href="${contextPath}">
                <img class="logo" id="mainlogo" src="${contextPath}/resources/images/ourtrip_logo2.png" alt="이미지"
                    style="width: 220px; height: auto;">
            </a>
            <div class="btn-wrapper ml-auto mt-3">
            	<c:if test="${!empty loginMember}">
            		<a href="${contextPath}/member/myPage" class="btn main-btn-rev" type="button">${loginMember.memberNickName}</a>
            		<a href="${contextPath}/member/logout" class="btn btn-outline-danger" type="button">로그아웃</a>
            	</c:if>
            	<c:if test="${empty loginMember}">
	                <a href="${contextPath}/member/loginForm" class="btn main-btn-rev" type="button">로그인</a>
	                <a href="${contextPath}/member/signUpForm" class="btn gray-btn-rev" type="button">회원가입</a>
            	</c:if>
            </div>
        </div>
    </div>
</div>
<!--end header area-->

</body>
</html>