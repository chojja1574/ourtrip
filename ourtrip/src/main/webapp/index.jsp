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
				<div class="carousel-item active">
					<img class="d-block w-100 mainimg" alt="Carousel Bootstrap First"
						src="http://img.earthtory.com/img/city_default/6/10034.jpg" />
					<div class="carousel-caption text-left">
						<h1>Our Trip을 활용해 여행 계획을 같이 만들어보세요!</h1>
						<p>Our Trip은 여러분의 여행 수정사항을 실시간으로 반영하여 즉각 배포해드립니다</p>
					</div>
				</div>
				<div class="carousel-item">
					<img class="d-block w-100 mainimg" alt="Carousel Bootstrap Second"
						src="http://img.earthtory.com/img/city_images/243/singapore_1426840110.jpg" />
					<div class="carousel-caption text-left">
						<h1>Our Trip을 활용해 여행 계획을 같이 만들어보세요!</h1>
						<p>Our Trip은 여러분의 여행 수정사항을 실시간으로 반영하여 즉각 배포해드립니다</p>
					</div>
				</div>
				<div class="carousel-item">
					<img class="d-block w-100 mainimg" alt="Carousel Bootstrap Third"
						src="http://img.earthtory.com/img/city_images/309/london_1429516385.jpg" />
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
			<div class="col-md-6">
				<div class="row">
					<div class="col-md-4">
						<div class="card">
							<img class="card-img-top" alt="Bootstrap Thumbnail First"
								src="https://www.layoutit.com/img/people-q-c-600-200-1.jpg" />
							<div class="card-block">
								<h5 class="card-title">Card title</h5>
								<p class="card-text">Cras justo odio, dapibus ac facilisis
									in, egestas eget quam. Donec id elit non mi porta gravida at
									eget metus. Nullam id dolor id nibh ultricies vehicula ut id
									elit.</p>
								<p>
									<a class="btn btn-primary" href="#">Action</a> <a class="btn"
										href="#">Action</a>
								</p>
							</div>
						</div>
					</div>
					<div class="col-md-4">
						<div class="card">
							<img class="card-img-top" alt="Bootstrap Thumbnail Second"
								src="https://www.layoutit.com/img/city-q-c-600-200-1.jpg" />
							<div class="card-block">
								<h5 class="card-title">Card title</h5>
								<p class="card-text">Cras justo odio, dapibus ac facilisis
									in, egestas eget quam. Donec id elit non mi porta gravida at
									eget metus. Nullam id dolor id nibh ultricies vehicula ut id
									elit.</p>
								<p>
									<a class="btn btn-primary" href="#">Action</a> <a class="btn"
										href="#">Action</a>
								</p>
							</div>
						</div>
					</div>
					<div class="col-md-4">
						<div class="card">
							<img class="card-img-top" alt="Bootstrap Thumbnail Third"
								src="https://www.layoutit.com/img/sports-q-c-600-200-1.jpg" />
							<div class="card-block">
								<h5 class="card-title">Card title</h5>
								<p class="card-text">Cras justo odio, dapibus ac facilisis
									in, egestas eget quam. Donec id elit non mi porta gravida at
									eget metus. Nullam id dolor id nibh ultricies vehicula ut id
									elit.</p>
								<p>
									<a class="btn btn-primary" href="#">Action</a> <a class="btn"
										href="#">Action</a>
								</p>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="col-md-6">
				<h2>Heading</h2>
				<p>Donec id elit non mi porta gravida at eget metus. Fusce
					dapibus, tellus ac cursus commodo, tortor mauris condimentum nibh,
					ut fermentum massa justo sit amet risus. Etiam porta sem malesuada
					magna mollis euismod. Donec sed odio dui.</p>
				<p>
					<a class="btn" href="#">View details »</a>
				</p>
				<a href="#" class="btn btn-lg btn-primary" type="button">Button</a>
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