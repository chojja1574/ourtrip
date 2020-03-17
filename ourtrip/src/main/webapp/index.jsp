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
</style>
</head>
<body>
	<jsp:include page="WEB-INF/views/common/header.jsp" />
	<jsp:include page="WEB-INF/views/common/nav.jsp" />

	<!-- main content -->
	<div class="container-fluid mx-0 mt-2 px-0">
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
			<div class="col-md-7">
				<div class="row">
					<div id="demo" class="carousel slide" data-ride="carousel">
						<div class="carousel-inner">
							<div class="carousel-item active">
								<div class="cardWrapper">
									<div class="planner orn">
										<a href="#"> <img class="card-img-top"
											src="${contextPath}/resources/images/ourtrip_logo2.png"
											alt="Card image">
										</a>
										<div class="card-body">
											<h5 class="card-title">부천 맛집 투어</h5>
											<p class="card-text">
												<span>시작일 : 2020-02-01 7DAYS</span><br> <span>신혼여행</span><br>
												<span>경기도 부천시...</span>
											</p>
											<div class="d-flex justify-content-between">
												<div class="btn-wrapper">
													<a type="button" href="#" class="btn btn-sm main-btn">바로가기</a>
												</div>
												<div>
													<i class="fas fa-eye"></i>&nbsp;15
												</div>
											</div>
										</div>
									</div>
									<div class="planner wen">
										<a href="#"> <img class="card-img-top"
											src="${contextPath}/resources/images/ourtrip_logo2.png"
											alt="Card image">
										</a>
										<div class="card-body">
											<h5 class="card-title">부천 맛집 투어</h5>
											<p class="card-text">
												<span>시작일 : 2020-02-01 7DAYS</span><br> <span>신혼여행</span><br>
												<span>경기도 부천시...</span>
											</p>
											<div class="d-flex justify-content-between">
												<div class="btn-wrapper">
													<a type="button" href="#" class="btn btn-sm main-btn">바로가기</a>
												</div>
												<div>
													<i class="fas fa-eye"></i>&nbsp;15
												</div>
											</div>
										</div>
									</div>
								</div>
							</div>
							<div class="carousel-item">
								<div class="planner orn">
									<a href="#"> <img class="card-img-top"
										src="${contextPath}/resources/images/ourtrip_logo2.png"
										alt="Card image">
									</a>
									<div class="card-body">
										<h5 class="card-title">부천 맛집 투어</h5>
										<p class="card-text">
											<span>시작일 : 2020-02-01 7DAYS</span><br> <span>신혼여행</span><br>
											<span>경기도 부천시...</span>
										</p>
										<div class="d-flex justify-content-between">
											<div class="btn-wrapper">
												<a type="button" href="#" class="btn btn-sm main-btn">바로가기</a>
											</div>
											<div>
												<i class="fas fa-eye"></i>&nbsp;15
											</div>
										</div>
									</div>
								</div>
								<div class="planner wen">
									<a href="#"> <img class="card-img-top"
										src="${contextPath}/resources/images/ourtrip_logo2.png"
										alt="Card image">
									</a>
									<div class="card-body">
										<h5 class="card-title">부천 맛집 투어</h5>
										<p class="card-text">
											<span>시작일 : 2020-02-01 7DAYS</span><br> <span>신혼여행</span><br>
											<span>경기도 부천시...</span>
										</p>
										<div class="d-flex justify-content-between">
											<div class="btn-wrapper">
												<a type="button" href="#" class="btn btn-sm main-btn">바로가기</a>
											</div>
											<div>
												<i class="fas fa-eye"></i>&nbsp;15
											</div>
										</div>
									</div>
								</div>
							</div>
							<div class="carousel-item">
								<div class="planner orn">
									<a href="#"> <img class="card-img-top"
										src="${contextPath}/resources/images/ourtrip_logo2.png"
										alt="Card image">
									</a>
									<div class="card-body">
										<h5 class="card-title">부천 맛집 투어</h5>
										<p class="card-text">
											<span>시작일 : 2020-02-01 7DAYS</span><br> <span>신혼여행</span><br>
											<span>경기도 부천시...</span>
										</p>
										<div class="d-flex justify-content-between">
											<div class="btn-wrapper">
												<a type="button" href="#" class="btn btn-sm main-btn">바로가기</a>
											</div>
											<div>
												<i class="fas fa-eye"></i>&nbsp;15
											</div>
										</div>
									</div>
								</div>
								<div class="planner wen">
									<a href="#"> <img class="card-img-top"
										src="${contextPath}/resources/images/ourtrip_logo2.png"
										alt="Card image">
									</a>
									<div class="card-body">
										<h5 class="card-title">부천 맛집 투어</h5>
										<p class="card-text">
											<span>시작일 : 2020-02-01 7DAYS</span><br> <span>신혼여행</span><br>
											<span>경기도 부천시...</span>
										</p>
										<div class="d-flex justify-content-between">
											<div class="btn-wrapper">
												<a type="button" href="#" class="btn btn-sm main-btn">바로가기</a>
											</div>
											<div>
												<i class="fas fa-eye"></i>&nbsp;15
											</div>
										</div>
									</div>
								</div>
							</div>

							<a class="carousel-control-prev" href="#demo" data-slide="prev">
								<span class="carousel-control-prev-icon" aria-hidden="true"></span>
								<span>Previous</span> -->
							</a> <a class="carousel-control-next" href="#demo" data-slide="next">
								<span class="carousel-control-next-icon" aria-hidden="true"></span>
								<span>Next</span> -->
							</a>

							<ul class="carousel-indicators">
								<li data-target="#demo" data-slide-to="0" class="active"></li>
								<li data-target="#demo" data-slide-to="1"></li>
								<li data-target="#demo" data-slide-to="2"></li>
							</ul>
						</div>

					</div>
				</div>
			</div>
			<div class="col-md-5">
				<h2>플레너 생성하기</h2>
				<p>플래너가 처음이신가요? <br>
				ourTrip 플래너는 여러분의 여행을 보다 즐겁게 만들어드립니다.<br>
				지금 바로 여러분의 여행파트너와 플래너를 작성해 보세요 <br>
				단 몇분이면 여러분만의 여행플래너가 완성됩니다.</p>
				<p>
					<a class="btn" href="#">자세히 보기.. »</a>
				</p>
				<a href="#" class="btn btn-lg btn-primary" type="button">플래너
					생성하기</a>
			</div>
		</div>
	</div>
	<!-- main content end -->

	<jsp:include page="WEB-INF/views/common/footer.jsp" />

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
</body>
</html>