<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
	
	<!-- Bootstrap CSS -->
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
	    integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
	<!-- Jquery -->
	<script src="https://code.jquery.com/jquery-3.4.1.min.js"
	    integrity="sha256-CSXorXvZcTkaix6Yvo6HppcZGetbYMGWSFlBw8HfCJo=" crossorigin="anonymous"></script>
	<!-- cdn -->
	<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
	    integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo"
	    crossorigin="anonymous"></script>
	<!-- BootStrap -->
	<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"
	    integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6"
	    crossorigin="anonymous"></script>
	
	<!-- 폰트 -->
	<link href="https://fonts.googleapis.com/css?family=Sunflower:300&display=swap" rel="stylesheet">
	
	<!-- 아이콘 -->
	<script src="https://kit.fontawesome.com/76b49c6d9b.js" crossorigin="anonymous"></script>    
	
	<!-- 공용 스타일시트 -->
	<link rel="stylesheet" href="../../css/common.css">
	
	<title>관리자 헤더</title>
	<style>

        .admin-btn-rev{
            background-color: white;
            color: red;
            border: solid 1px red;
        }

        .admin-btn-rev:hover{
            color: white;
            background-color: red;
        }

        a:hover {
            text-decoration: none;
        }

        h5 {
            margin-bottom: 0px;
        }

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

        .nav-item{
            text-align: center;
        }
    
        </style>

</head>
<body>
	<!--start header area-->
        <div class="container-fluid px-0" style="background-color: white;">
            <div class="container my-0 px-0">
                <div class="d-flex">
                    <a href="main.html"> 
                        <img class="logo" id="mainlogo" src="${contextPath}/resources/images/ourtrip_logo2.png" alt="이미지"
                            style="width: 220px; height: auto;">
                    </a>
                    <div class="btn-wrapper ml-auto mt-4">
                        <a href="#" class="btn admin-btn-rev" type="button">로그아웃</a>
                    </div>
                </div>
            </div>
        </div>
        <!--end header area-->
        <!-- start nav area -->
        <nav class="navbar navbar-expand-sm nabigater" style="background-color: white;">
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
                <i class="fas fa-bars"></i>
            </button>
            <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                <div class="container my-0 px-0">
                    <div class="col-sm-3 nav-item">
                        <a href="#" class="nav-text">
                            <h5>대시보드</h5>
                        </a>
                    </div>
                    <div class="col-sm-3 nav-item">
                        <a href="#" class="nav-text">
                            <h5>회원목록</h5>
                        </a>
                    </div>
                    <div class="col-sm-3 nav-item">
                        <a href="#" class="nav-text">
                            <h5>플래너목록</h5>
                        </a>
                    </div>
                    <div class="col-sm-3 nav-item">
                        <a href="#" class="nav-text">
                            <h5>공지</h5>
                        </a>
                    </div>
                </div>
            </div>
        </nav>
        <!--end nav area-->
</body>
</html>