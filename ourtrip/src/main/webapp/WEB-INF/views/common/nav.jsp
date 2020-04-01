<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<!-- 폰트 -->
<link
	href="https://fonts.googleapis.com/css?family=Sunflower:300&display=swap"
	rel="stylesheet">

<style>
	.navbar-nav {
		text-align: center;
		margin-left: auto;
		margin-right: auto;
	}
	
	.nav-itemPosion {
		padding-left: 50px;
		padding-right: 50px;
	}
	
	.nav-text {
		font-family: Sunflower;
		color: black;
	}
	
	.nav-item {
		text-align: center;
	}
</style>
</head>
<body>
<!-- start nav area -->
<nav class="navbar navbar-expand-sm nabigater" style="background-color: white;">
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
        <i class="fas fa-bars"></i>
    </button>
    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
        <div class="container my-0 px-0">
            <div class="col-sm-3 nav-item">
                <a href="${contextPath}" class="nav-text">
                    <h5>홈</h5>
                </a>
            </div>
            <div class="col-sm-3 nav-item">
                <a href="${contextPath}/planner/myPlanner" class="nav-text">
                    <h5>나의 플래너</h5>
                </a>
            </div>
            <div class="col-sm-3 nav-item">
                <a href="${contextPath}/planner/search" class="nav-text">
                    <h5>탐색</h5>
                </a>
            </div>
            <div class="col-sm-3 nav-item">
                <a href="${contextPath}/notice/list" class="nav-text">
                    <h5>공지사항</h5>
                </a>
            </div>
        </div>
    </div>
</nav>
<!--end nav area-->

</body>
</html>