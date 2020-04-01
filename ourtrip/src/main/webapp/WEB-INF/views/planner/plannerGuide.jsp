<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
	integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh"
	crossorigin="anonymous">

<!-- style -->
<link rel="stylesheet" type="text/css" href="../resources/css/slick.css" />
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
<!-- 공용 css -->
<link rel="stylesheet" href="../resources/css/common.css">

<!-- 호환성 -->
<meta http-equiv="X-UA-Compatible" content="IE=edge">

<!-- 폰트 -->
<link
	href="https://fonts.googleapis.com/css?family=Nanum+Pen+Script|Ubuntu&display=swap"
	rel="stylesheet">

<title>사용설명서</title>
<style>
html, body {
	height: 100%;
	margin: 0;
}

body {
	font-family: 'Ubuntu', sans-serif;
	font-family: 'Nanum Pen Script', cursive;
	font-size: 30px;
}

div:focus {
	outline: none;
}

div {
	/* border:1px dotted black; */
	box-sizing: border-box;
}

.row {
	height: 860px;
}

.row>div {
	float: left;
	width: 100%;
	height: 100%;
}

/* 버튼설정 */
.slider .slick-dots {
	display: block;
	width: 100%;
	text-align: center;
	padding: 0;
}

.slider .slick-dots li {
	display: inline-block;
	width: 30px;
	height: 30px;
	margin: 5px;
	line-height: 2px;
	font-size: 26px;
}

.slider .slick-dots li button {
	width: 30px;
	height: 30px;
	border-radius: 50%;
	background-color: #18a8f1;
	border: 0px;
	color: white;
	/* line-height: 2px; */
}

/* 화살표 설정 */
.slider .slick-prev {
	position: absolute;
	left: 0;
	bottom: 20px;
	z-index: 1000;
	border: 0px;
	background-color: white;
	width: 30px;
	height: 30px;
	display: inline-block;
	/* 문장들여쓰기 */
	text-indent: -9999px;
	/* 30px/1 은 글자크기30px lineheight=1 */
	font: normal normal normal 30px/1 FontAwesome;
	outline: none;
}
/* 이미지태그를 사용할수없는 상태 */
.slider .slick-prev::before {
	content: "\f060";
	color: #18a8f1;
	text-indent: 0;
	position: absolute;
	left: 8px;
	bottom: 0px;
}

.slider .slick-next {
	position: absolute;
	right: 0;
	bottom: 20px;
	z-index: 1000;
	border: 0px;
	background-color: white;
	width: 30px;
	height: 30px;
	display: inline-block;
	text-indent: -9999px;
	font: normal normal normal 30px/1 FontAwesome;
	outline: none;
}

.slider .slick-next::before {
	content: "\f061";
	color: #18a8f1;
	text-indent: 0;
	position: absolute;
	right: 8px;
	bottom: 0px;
}

.checkbox-xl .custom-control-label::before, .checkbox-xl .custom-control-label::after
	{
	top: 1.2rem;
	width: 1.85rem;
	height: 1.85rem;
}

.checkbox-xl .custom-control-label {
	padding-top: 10px;
	padding-left: 10px;
	padding-bottom: 10px;
}
</style>

</head>
<body>
	<div class="container" id="tutorial-container">
		<div class="m-auto" id="tutorial-wrapper">
			<div style="text-align: center;">
				<h1>OurTrip 사용설명서</h1>
			</div>
			<div class="slider-wrapper">
				<div class="slider">
					<!-- 첫번째 페이지 -->
					<div class="row">
						<div class="col-md-6 d-flex">
							<img src="../resources/guideGifs/entered.gif" class="m-auto">
						</div>
						<div class="col-md-6 d-flex">
							<div class="m-auto">
								<h2 style="color: #18a8f1">Step 1</h2>
								<ul>
									<li>플래너의 비밀번호를 입력하고 'Join'버튼을 클릭해주세요!</li>
									<li>비밀번호를 모른다면 참여할 수 없습니다.</li>
									<li>비밀번호가 없는 플래너는 다음과 같은 화면이 나오지 않습니다.</li>
								</ul>
							</div>
						</div>
					</div>
					<!-- 첫페이지끝 -->
					<!-- 두번째페이지 -->
					<div class="row">
						<div class="col-md-4 d-flex">
							<img src="../resources/guideGifs/updateDate.gif" class="m-auto">
						</div>
						<div class="col-md-8 d-flex">
							<div class="m-auto">
								<h2 style="color: #18a8f1">Step 2</h2>
								<ul>
									<li>날짜를 수정한 뒤 '출발일 수정' 버튼을 클릭하면 출발일이 수정됩니다.</li>
									<li>일차의 '-' 버튼을 클릭하면 일차를 삭제할 수 있습니다.</li>
									<li>하단의 '일정 추가' 버튼을 클릭하면 일차를 추가할 수 있습니다.</li>
									<li>일차를 드래그하면 손쉽게 일차를 변경할 수 있습니다.</li>
									<li>일차를 클릭하면 해당 일차의 상세 일정들을 확인할 수 있습니다.</li>
								</ul>
							</div>
						</div>
					</div>
					<!-- 두번째끝 -->
					<!-- 세번째페이지 -->
					<div class="row">
						<div class="col-md-6 d-flex">
							<img src="../resources/guideGifs/addSchedule.gif" class="m-auto">
						</div>
						<div class="col-md-6 d-flex">
							<div class="m-auto">
								<h2 style="color: #18a8f1">Step 3</h2>
								<ul>
									<li>일정 제목, 경비, 시간, 메모, 장소를 입력한 뒤 '일정 추가' 버튼을 클릭하면 일정을 추가할
										수 있습니다.</li>
									<li>장소의 첫 번째 입력란에 지도에서 표시하고 싶은 장소명을 입력할 수 있습니다.</li>
									<li>장소의 검색 입력란에 찾고싶은 장소를 입력한 뒤 검색하면 지도에 나타납니다.</li>
									<li>장소를 검색한 뒤 '맛집', '관광지', '숙소' 버튼을 클릭하여 지도상에서 확인할 수 있습니다.
									</li>
									<li>'지도 초기화' 버튼을 클릭하면 지도상의 마커를 지울 수 있습니다.</li>
								</ul>
							</div>
						</div>
					</div>
					<!-- 세번째끝 -->
					<!-- 네번째페이지 -->
					<div class="row">
						<div class="col-md-12 d-flex">
							<img src="../resources/guideGifs/updateSchedule.gif" class="m-auto">
						</div>
					</div>
					<!-- 네번째끝 -->
					<!-- 5번째페이지 -->
					<div class="row">
						<div class="col-md-12 d-flex">
							<div class="m-auto">
								<h2 style="color: #18a8f1">Step 4</h2>
								<ul>
									<li>일차 안의 일정제목을 클릭하여 일정을 수정할 수 있습니다.</li>
									<li>일정수정이 끝난 뒤 '저장' 버튼을 클릭하면 수정한 내용이 저장됩니다.</li>
									<li>'삭제' 버튼을 클릭하여 일정을 삭제할 수 있습니다.</li>
									<li>일정의 '▼' 기호를 클릭하면 일정에 대한 상세정보를 확인할 수 있습니다.</li>
									<li>일정 지도의 마커를 클릭하면 장소명과 상세주소가 나타납니다.</li>
								</ul>
							</div>
						</div>
					</div>
					<!-- 5번째끝 -->
					<!-- 6번째페이지 -->
					<div class="row">
						<div class="col-md-6 d-flex">
							<img src="../resources/guideGifs/chatting.gif" class="m-auto">
						</div>
						<div class="col-md-6 d-flex">
							<div class="m-auto">
								<h2 style="color: #18a8f1">Step 5</h2>
								<ul>
									<li>플래너에 접속한 회원들과 채팅을 이용할 수 있습니다.</li>
								</ul>
							</div>
						</div>
					</div>
					<!-- 6번째끝 -->

				</div>
				<!-- 슬라이드끝 -->
			</div>
			<!-- 슬라이드 래퍼끝 -->
		</div>
	</div>
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

	<!-- javascript lib -->
	<script type="text/javascript" src="../resources/js/slick.min.js"></script>

	<!-- 슬라이드 설정 -->
	<script>
       // 이미지 슬라이더
       $(".slider").slick({
           // 아래 누르는 버튼
           dots: true,
           autoplay : false,
           autoplaySpeed: 3000,
           arrows: true,
           responsive : [
               {
                   breakpoint: 768,
                   settings : {
                       arrows: true,
                       dots : true
                   }
               }
           ]
       });
   </script>
</body>
</html>