<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">

<!-- Bootstrap CSS -->
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
	integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh"
	crossorigin="anonymous">

<!-- 폰트 -->
<link
	href="https://fonts.googleapis.com/css?family=Stylish&display=swap"
	rel="stylesheet">

<!-- 아이콘 -->
<script src="https://kit.fontawesome.com/76b49c6d9b.js"
	crossorigin="anonymous"></script>

<link rel="stylesheet" href="resources/css/common.css">
<link rel="stylesheet" href="resources/css/carousel.css">

<title>ourtrip</title>

<style>
h5 {
	margin-bottom: 0px;
}

.mainimg {
	width: 750px;
	height: 550px;
}

.carousel-caption {
	margin-bottom: 30px;
}

.carousel-item {
	background-repeat: no-repeat;
	background-size: cover;
}

@media screen and (max-width: 767px) {
	.hide-li {
		display: none;
	}
}

#local-area {
	margin-top: 24px;
}

#check-wrapper {
	margin-top: 20px;
}

#search-wrapper {
	min-width: 300px;
}


.planner {
	font-family: 'Stylish';
	padding: 10px;
	width: 100%;
	height: 400px;
}

.card {
	height: 100%;
}

.card-body {
	height: 50%;
}

.card-img-top {
	width: 100%;
	height: 50%;
}

</style>
</head>
<body>
	<jsp:include page="/WEB-INF/views/common/header.jsp" />
	<jsp:include page="/WEB-INF/views/common/nav.jsp" />

	<!-- main content -->
	<div class="container-fluid mx-0 mt-0 px-0">
		<div class="carousel slide mainspot" id="carousel-628660">
			<ol class="carousel-indicators">
				<li data-slide-to="0" data-target="#carousel-628660" class="active">
				</li>
				<li data-slide-to="1" data-target="#carousel-628660"></li>
				<li data-slide-to="2" data-target="#carousel-628660"></li>
			</ol>
			<div class="carousel-inner">
				<div class="carousel-item active"
					style="background-image: url(resources/images/intro-image1.jpg);">
					<div class="carousel-caption text-left">
						<h1>Our Trip을 활용해 여행 계획을 같이 만들어보세요!</h1>
						<p>Our Trip은 여러분의 여행 수정사항을 실시간으로 반영하여 즉각 배포해드립니다</p>
					</div>
				</div>
				<div class="carousel-item"
					style="background-image: url(resources/images/intro-image2.jpg);">
					<div class="carousel-caption text-left">
						<h1>Our Trip을 활용해 여행 계획을 같이 만들어보세요!</h1>
						<p>Our Trip은 여러분의 여행 수정사항을 실시간으로 반영하여 즉각 배포해드립니다</p>
					</div>
				</div>
				<div class="carousel-item"
					style="background-image: url(resources/images/intro-image3.jpg);">
					<div class="carousel-caption text-left">
						<h1>Our Trip을 활용해 여행 계획을 같이 만들어보세요!</h1>
						<p>Our Trip은 여러분의 여행 수정사항을 실시간으로 반영하여 즉각 배포해드립니다</p>
					</div>
				</div>
			</div>
			<a class="carousel-control-prev" href="#carousel-628660"
				data-slide="prev"> <span class="carousel-control-prev-icon"></span>
				<span class="sr-only">Previous</span>
			</a> <a class="carousel-control-next" href="#carousel-628660"
				data-slide="next"> <span class="carousel-control-next-icon"></span>
				<span class="sr-only">Next</span>
			</a>
		</div>
	</div>

	<div class="container my-5 px-0">
		<div class="row">
			<c:if test="${empty recommendPCList}">
				<div class="col-xl-6">
					존재하는 추천 리스트가 없습니다.
				</div>
			</c:if>
			<c:if test="${!empty recommendPCList}">
				<c:forEach var="recommendCard" items="${recommendPCList}" varStatus="vs" begin="0" end="1">
					<div class="col-xl-3">
						<div class="planner">
		                    <div class="card">
		                        <img class="card-img-top" src="${contextPath}/resources/areaImages/${recommendCard.areaNames[0].largeAreaCode}.jpg"
                           		alt="Card image">

		                        <div class="card-body">
		                            <h5 class="card-title">${recommendCard.plannerTitle}</h5>
		                            <p class="card-text">
		                                <span>시작일 : ${recommendCard.plannerStartDT} ${recommendCard.tripDate}DAYS</span><br>
		                                <span>${recommendCard.groupName }</span><br>
		                                <c:if test="${recommendCard.areaNames.size()>1 }">
		                                	<span>${recommendCard.areaNames[0].largeAreaName}
		                                		  ${recommendCard.areaNames[0].smallAreaName} ...</span>
		                                </c:if>
		                                <c:if test="${recommendCard.areaNames.size()==1 }">
		                                	<span>${recommendCard.areaNames[0].largeAreaName}
		                                		  ${recommendCard.areaNames[0].smallAreaName} </span>
		                                </c:if>
		                                <c:if test="${recommendCard.areaNames.size()<1 }">
		                                	<span>없음 </span>
		                                </c:if>
		                            </p>
		                            <div class="d-flex justify-content-between">
		                                <div class="btn-wrapper">
		                                    <a href="#" class="btn btn-sm main-btn">바로가기</a>
		                                    <a href="#" class="btn btn-sm gray-btn copy-btn">복사</a>
		                                </div>
		                                <div><i class="fas fa-eye"></i>&nbsp;${recommendCard.plannerCount}</div>
		                            </div>
		                        </div>
		                    </div>
		                </div>
					</div>
				</c:forEach>
			</c:if>
						
			<!-- 플래너생성하기 -->
			<div class="col-xl-6">
				<h2>플레너 생성하기</h2>
				<p>
					플래너가 처음이신가요? <br> ourTrip 플래너는 여러분의 여행을 보다 즐겁게 만들어드립니다.<br>
					지금 바로 여러분의 여행파트너와 플래너를 작성해 보세요 <br> 단 몇분이면 여러분만의 여행플래너가 완성됩니다.
				</p>
				<c:if test="${!empty loginMember }">
					<a href= '${contextPath}/planner/create'
						id="createButton" class="btn btn-lg btn-primary" type="button" style="color:white;">플래너
						생성하기</a>
				</c:if>
				<c:if test="${empty loginMember }">
					<a href= '${contextPath}/member/loginForm'
						id="nologincreate" class="btn btn-lg btn-primary" type="button" style="color:white;">로그인
						후 생성</a>
				</c:if>
				<a href="${contextPath}/planner/guide" class="btn btn-lg btn-success" type="button"> 플래너 생성
					가이드</a>
			</div>
		</div>	
	</div>

	<!-- main content end -->

	<jsp:include page="/WEB-INF/views/common/footer.jsp" />

	<script src="https://code.jquery.com/jquery-3.4.1.min.js"
		integrity="sha256-CSXorXvZcTkaix6Yvo6HppcZGetbYMGWSFlBw8HfCJo="
		crossorigin="anonymous"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
		integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo"
		crossorigin="anonymous"></script>
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"
		integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6"
		crossorigin="anonymous"></script>
		
	<script>
		$(function() {
			// 생성버튼 조건
			$("#nologincreate").on("click", function() {
				alert("로그인후 이용해 주세요");
				location.href = "${contextPath}/member/loginForm";
			});
			
			// 복사버튼 조건
			$(".copy-btn").on("click", function(){
				if(${empty loginMember}) {
					alert("로그인후 이용해 주세요");
					location.href = "${contextPath}/member/loginForm";
				}
			});
			
		});
		
		
	</script>
</body>
</html>