<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
	integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh"
	crossorigin="anonymous">
<link
	href="https://fonts.googleapis.com/css?family=Stylish&display=swap"
	rel="stylesheet">
<script src="https://kit.fontawesome.com/76b49c6d9b.js"
	crossorigin="anonymous"></script>
<link rel="stylesheet" href="../resources/css/common.css">
<title>나의 플래너</title>

<style>
#local-area {
	margin-top: 24px;
}

#check-wrapper {
	margin-top: 20px;
}

#search-wrapper {
	min-width: 300px;
}

.planner-wrapper {
	width: 100%;
	position: relative;
}

.planner {
	font-family: 'Stylish';
	padding: 10px;
	width: 25%;
	float: left;
	height: 400px;
}

.pagination-wrapper {
	width: 100%;
	clear: both;
	margin-top: 20px;
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

@media screen and (max-width: 1199px) {
	.planner {
		width: 33%;
	}
}

@media screen and (max-width: 991px) {
	.planner {
		width: 50%;
	}
}

@media screen and (max-width: 506px) {
	.planner {
		width: 100%;
	}
}
</style>

<title>나의 플래너</title>
</head>
<body>
	<jsp:include page="../common/header.jsp" />
	<jsp:include page="../common/nav.jsp" />

	<!-- 나의 플래너 컨텐츠 시작 부분 -->
	<div class="container my-5">
		<h2 class="font-weight-bold">나의 플래너</h2>
		<hr>

		<div class="d-flex btn-wrapper">
			<a href="#" class="btn main-btn ml-auto" type="button">플래너 만들기</a>
		</div>

		<!-- 플래너 컨테이너 -->
		<div class="py-3">
			<h4>수정중인 플래너</h4>

			<!-- 수정중인 플래너 리스트 -->
			<div class="planner-wrapper my-3">

				<div class="planner">
					<div class="card">
						<img class="card-img-top" src="images/example1.jpg"
							alt="Card image">
						<div class="card-body">
							<h5 class="card-title">부천 맛집 투어</h5>
							<p class="card-text">
								<span>시작일 : 2020-02-01 7DAYS</span><br> <span>가족 여행</span><br>
								<span>경기도 부천시...</span>
							</p>
							<div class="d-flex justify-content-between">
								<div class="btn-wrapper">
									<button type="button" class="btn btn-sm main-btn">바로가기</button>
									<button type="button" class="btn  btn-sm del-btn">삭제</button>
								</div>
								<div>
									<i class="fas fa-eye"></i>&nbsp;15
								</div>
							</div>
						</div>
					</div>
				</div>
				<div class="planner">
					<div class="card">
						<img class="card-img-top" src="images/example2.jpg"
							alt="Card image">
						<div class="card-body">
							<h5 class="card-title">부천 맛집 투어</h5>
							<p class="card-text">
								<span>시작일 : 2020-02-01 7DAYS</span><br> <span>가족 여행</span><br>
								<span>경기도 부천시...</span>
							</p>
							<div class="d-flex justify-content-between">
								<div class="btn-wrapper">
									<button type="button" class="btn btn-sm main-btn">바로가기</button>
								</div>
								<div>
									<i class="fas fa-eye"></i>&nbsp;15
								</div>
							</div>
						</div>
					</div>
				</div>
				<div class="planner">
					<div class="card">
						<img class="card-img-top" src="images/example3.jpg"
							alt="Card image">
						<div class="card-body">
							<h5 class="card-title">부천 맛집 투어</h5>
							<p class="card-text">
								<span>시작일 : 2020-02-01 7DAYS</span><br> <span>가족 여행</span><br>
								<span>경기도 부천시...</span>
							</p>
							<div class="d-flex justify-content-between">
								<div class="btn-wrapper">
									<button type="button" class="btn btn-sm main-btn">바로가기</button>
								</div>
								<div>
									<i class="fas fa-eye"></i>&nbsp;15
								</div>
							</div>
						</div>
					</div>
				</div>
				<div class="planner">
					<div class="card">
						<img class="card-img-top" src="images/ourtrip_logo.png"
							alt="Card image">
						<div class="card-body">
							<h5 class="card-title">부천 맛집 투어</h5>
							<p class="card-text">
								<span>시작일 : 2020-02-01 7DAYS</span><br> <span>가족 여행</span><br>
								<span>경기도 부천시...</span>
							</p>
							<div class="d-flex justify-content-between">
								<div class="btn-wrapper">
									<button type="button" class="btn btn-sm main-btn">바로가기</button>
									<button type="button" class="btn  btn-sm del-btn">삭제</button>
								</div>
								<div>
									<i class="fas fa-eye"></i>&nbsp;15
								</div>
							</div>
						</div>
					</div>
				</div>

			</div>

			<!-- 페이징바 -->
			<div class="pagination-wrapper">
				<nav aria-label="Page navigation">
					<ul class="pagination justify-content-center">
						<li class="page-item disabled"><a class="page-link" href="#">&lt;&lt;</a>
						</li>
						<li class="page-item disabled"><a class="page-link" href="#">&lt;</a>
						</li>
						<li class="page-item"><a class="page-link" href="#">1</a></li>
						<li class="page-item"><a class="page-link" href="#">2</a></li>
						<li class="page-item"><a class="page-link" href="#">3</a></li>
						<li class="page-item"><a class="page-link" href="#">4</a></li>
						<li class="page-item"><a class="page-link" href="#">5</a></li>
						<li class="page-item"><a class="page-link" href="#">&gt;</a>
						</li>
						<li class="page-item"><a class="page-link" href="#">&gt;&gt;</a>
						</li>
					</ul>
				</nav>
			</div>
		</div>

		<!-- 플래너 컨테이너 -->
		<div class="py-3">
			<h4>완료된 플래너</h4>

			<!-- 완료된 플래너 리스트 -->
			<div class="planner-wrapper my-3">

				<div class="planner">
					<div class="card">
						<img class="card-img-top" src="images/example1.jpg"
							alt="Card image">
						<div class="card-body">
							<h5 class="card-title">부천 맛집 투어</h5>
							<p class="card-text">
								<span>시작일 : 2020-02-01 7DAYS</span><br> <span>가족 여행</span><br>
								<span>경기도 부천시...</span>
							</p>
							<div class="d-flex justify-content-between">
								<div class="btn-wrapper">
									<button type="button" class="btn btn-sm main-btn">바로가기</button>
									<button type="button" class="btn  btn-sm del-btn">삭제</button>
								</div>
								<div>
									<i class="fas fa-eye"></i>&nbsp;15
								</div>
							</div>
						</div>
					</div>
				</div>
				<div class="planner">
					<div class="card">
						<img class="card-img-top" src="images/example2.jpg"
							alt="Card image">
						<div class="card-body">
							<h5 class="card-title">부천 맛집 투어</h5>
							<p class="card-text">
								<span>시작일 : 2020-02-01 7DAYS</span><br> <span>가족 여행</span><br>
								<span>경기도 부천시...</span>
							</p>
							<div class="d-flex justify-content-between">
								<div class="btn-wrapper">
									<button type="button" class="btn btn-sm main-btn">바로가기</button>
								</div>
								<div>
									<i class="fas fa-eye"></i>&nbsp;15
								</div>
							</div>
						</div>
					</div>
				</div>
				<div class="planner">
					<div class="card">
						<img class="card-img-top" src="images/example3.jpg"
							alt="Card image">
						<div class="card-body">
							<h5 class="card-title">부천 맛집 투어</h5>
							<p class="card-text">
								<span>시작일 : 2020-02-01 7DAYS</span><br> <span>가족 여행</span><br>
								<span>경기도 부천시...</span>
							</p>
							<div class="d-flex justify-content-between">
								<div class="btn-wrapper">
									<button type="button" class="btn btn-sm main-btn">바로가기</button>
								</div>
								<div>
									<i class="fas fa-eye"></i>&nbsp;15
								</div>
							</div>
						</div>
					</div>
				</div>
				<div class="planner">
					<div class="card">
						<img class="card-img-top" src="images/ourtrip_logo.png"
							alt="Card image">
						<div class="card-body">
							<h5 class="card-title">부천 맛집 투어</h5>
							<p class="card-text">
								<span>시작일 : 2020-02-01 7DAYS</span><br> <span>가족 여행</span><br>
								<span>경기도 부천시...</span>
							</p>
							<div class="d-flex justify-content-between">
								<div class="btn-wrapper">
									<button type="button" class="btn btn-sm main-btn">바로가기</button>
									<button type="button" class="btn  btn-sm del-btn">삭제</button>
								</div>
								<div>
									<i class="fas fa-eye"></i>&nbsp;15
								</div>
							</div>
						</div>
					</div>
				</div>

			</div>

			<!-- 페이징바 -->
			<div class="pagination-wrapper">
				<nav aria-label="Page navigation">
					<ul class="pagination justify-content-center">
						<li class="page-item disabled"><a class="page-link" href="#">&lt;&lt;</a>
						</li>
						<li class="page-item disabled"><a class="page-link" href="#">&lt;</a>
						</li>
						<li class="page-item"><a class="page-link" href="#">1</a></li>
						<li class="page-item"><a class="page-link" href="#">2</a></li>
						<li class="page-item"><a class="page-link" href="#">3</a></li>
						<li class="page-item"><a class="page-link" href="#">4</a></li>
						<li class="page-item"><a class="page-link" href="#">5</a></li>
						<li class="page-item"><a class="page-link" href="#">&gt;</a>
						</li>
						<li class="page-item"><a class="page-link" href="#">&gt;&gt;</a>
						</li>
					</ul>
				</nav>
			</div>
		</div>
	</div>

	<jsp:include page="../common/footer.jsp" />

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
        $(function () {
            $(".planner-delete").on("click", function () {
                var plannerNo = $(this).parent().children("input").val();
                console.log(plannerNo);

                if (confirm("플래너를 삭제하시겠습니까?")) {
                    alert("삭제되었습니다.");
                }
            });
        });
    </script>
</body>
</html>