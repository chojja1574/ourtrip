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
<title>플래너 탐색</title>

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

#footer {
	clear:both;
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

</head>
<body>
	<jsp:include page="/WEB-INF/views/common/header.jsp" />
	<jsp:include page="/WEB-INF/views/common/nav.jsp" />

	<!-- 탐색 컨텐츠 시작 부분 -->
	<div class="container my-5">

		<h2 class="font-weight-bold">플래너 검색</h2>
		<hr>

		<form id="search-form" method="GET" action="searchPlanner" onsubmit="false">

			<!-- 검색창 -->
			<div class="col-md-6 mx-auto" id="search-wrapper">
				<div class="input-group mb-3">
					<input type="text" id="searchTitle" name="searchTitle"
						class="form-control" placeholder="플래너 제목을 입력하세요">
					<div class="input-group-append">
						<button class="btn main-btn" type="submit">검색</button>
					</div>
				</div>
			</div>

			<!-- 검색 옵션 -->
			<div id="select-option" class="col-md-12 mb-3">
				<div class="row">

					<div class="col-md-6" id="location-wrapper">
						<div class="row">
							<div class="col-6">
								<label>지역</label> <select name="largeArea" id="wide-area"
									class="custom-select">
									<option value="전체" selected>전체</option>
									<option value="경기">경기</option>
									<option value="서울특별시">서울특별시</option>
									<option value="강원도">강원도</option>
								</select>
							</div>
							<div class="col-6">
								<select name="smallArea" id="local-area" class="custom-select">
									<option value="전체" selected>전체</option>
								</select>
							</div>
						</div>
					</div>

					<div class="col-md-6" id="group-wrapper">
						<div class="row">
							<div class="col-6">
								<label>그룹</label> <select name="groupName" id="groupName"
									class="custom-select">
									<option value="전체" selected>전체</option>
									<option value="혼자">혼자</option>
									<option value="커플">커플</option>
									<option value="친구">친구</option>
									<option value="가족">가족</option>
								</select>
							</div>
							<div class="col-6">
								<div class="custom-control custom-checkbox" id="check-wrapper">
									<input type="checkbox" class="custom-control-input" id="viaCheck"
										name="viaCheck"> <label class="custom-control-label"
										for="viaCheck">경유 여부</label>
								</div>
							</div>
						</div>
					</div>
				</div>

			</div>

		</form>

		<!-- 플래너 컨테이너 -->
		<div class="py-3">
			<h4>검색된 플래너</h4>

			<!-- 검색된 플래너 리스트 -->
			<div class="planner-wrapper my-3">
				<!-- 플래너 -->
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
									<button type="button" class="btn  btn-sm gray-btn copy-btn">복사</button>
								</div>
								<div>
									<i class="fas fa-eye"></i>&nbsp;15
								</div>
							</div>
						</div>
					</div>
				</div>
				<!-- 플래너끝 -->
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

		<!-- 추천 플래너 컨테이너 -->
		<div class="py-3">
			<h4>추천 플래너</h4>

			<!-- 추천 플래너 리스트 -->
			<div class="planner-wrapper my-3">
			<c:if test="${empty recommendPCList}">
				<div>
					존재하는 추천 리스트가 없습니다.
				</div>
			</c:if>
			<c:if test="${!empty recommendPCList}">
				<c:forEach var="recommendCard" items="${recommendPCList}" varStatus="vs">
					<div class="planner">
						<div class="card">
							<img class="card-img-top" src="images/ourtrip_logo.png"
								alt="Card image">
							<div class="card-body">
								<h5 class="card-title">${recommendCard.plannerTitle}</h5>
								<p class="card-text">
									<span>시작일 : ${recommendCard.plannerStartDT} ${recommendCard.tripDate}DAYS</span><br> <span>가족 여행</span><br>
									<span>${recommendCard.groupName}</span>
									<c:if test="${recommendCard.areaNames.size()>1}">
										<span>${recommendCard.areaNames[0].largeAreaName}
											${recommnedCard.areaNames[0].smallAreaName} ...</span>
									</c:if>
									<c:if test="${recommendCard.areaNames.size()==1}">
										<span>${recommendCard.areaNames[0].largeAreaName}
											${recommnedCard.areaNames[0].smallAreaName} </span>
									</c:if>
									<c:if test="${recommendCard.areaNames.size()<1}">
										<span> 없음</span>
									</c:if>
								</p>
								<div class="d-flex justify-content-between">
									<div class="btn-wrapper">
										<button type="button" class="btn btn-sm main-btn">바로가기</button>
										<button type="button" class="btn  btn-sm gray-btn copy-btn">복사</button>
									</div>
									<div>
										<i class="fas fa-eye"></i>&nbsp;15
									</div>
								</div>
							</div>
						</div>
					</div>
				</c:forEach>
			</c:if>
			</div>

			<!-- 페이징바 -->
			<!-- <div class="pagination-wrapper">
				
			</div> -->
		</div>
		<!-- 추천플래너 컨테이너 끝나는 부분 -->
	</div>
	<!-- 탐색 컨텐츠 끝나는 부분 -->

	<script>
        $(function () {
            $("#wide-area").on("change", function () {
                var wideVal = $(this).val();
                var html = "";
                html += "<option value='전체' selected>전체</option>";

                if (wideVal == "경기") {
                    html += "<option value='부천시'>부천시</option>";
                    html += "<option value='고양시'>고양시</option>";
                    html += "<option value='구리시'>구리시</option>";
                    html += "<option value='수원시'>수원시</option>";
                    html += "<option value='남양주시'>남양주시</option>";

                } else if (wideVal == "서울특별시") {
                    html += "<option value='강남구'>강남구</option>";
                    html += "<option value='종로구'>종로구</option>";
                    html += "<option value='강서구'>강서구</option>";
                    html += "<option value='이태원'>이태원</option>";
                    html += "<option value='여의도'>여의도</option>";

                } else if (wideVal == "강원도") {
                    html += "<option value='강릉시'>강릉시</option>";
                    html += "<option value='속초시'>속초시</option>";

                }

                $("#local-area").html(html);
            });
        });
    </script>

	<jsp:include page="/WEB-INF/views/common/footer.jsp" />
</body>
</html>