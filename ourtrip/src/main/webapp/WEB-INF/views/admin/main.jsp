<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
<!-- 차트 -->
<script src="https://cdn.jsdelivr.net/npm/chart.js@2.8.0"></script>
<!-- 공용 css -->
<link rel="stylesheet" href="${contextPath}/resources/css/common.css">

<title>관리자 대시보드</title>
<style>
	.card{
		margin-left: 5px;
		display: inline-block;
		text-align: center;
	}
	
	body {
		padding: 20px 20px;
	}
</style>
</head>
<body>
	<jsp:include page="/WEB-INF/views/common/header.jsp"/>
	<jsp:include page="/WEB-INF/views/admin/nav.jsp"/>
	<div class="container">
		<div class="row justify-content-center my-3">
			<div class="card text-white bg-primary"
				style="width: 12rem;">
				<div class="card-header">오늘방문수</div>
				<div class="card-body">
					<h4 class="card-title">${dashBoardData.weekVisitCount[6]}명</h4>
				</div>
			</div>
			<div class="card text-white bg-secondary"
				style="width: 12rem;">
				<div class="card-header">이번달 방문수</div>
				<div class="card-body">
					<h4 class="card-title">${dashBoardData.vMonthCount}명</h4>
				</div>
			</div>
			<div class="card text-white bg-success"
				style="width: 12rem;">
				<div class="card-header">누적 방문수</div>
				<div class="card-body">
					<h4 class="card-title">${dashBoardData.vTotal}명</h4>
				</div>
			</div>
		</div>
		<!-- 2번쨰줄 -->
		<div class="row justify-content-center my-3">
			<div class="card text-white bg-danger"
				style="width: 12rem;">
				<div class="card-header">오늘 플래너</div>
				<div class="card-body">
					<h4 class="card-title">${dashBoardData.weekPlannerCount[6]}개</h4>
				</div>
			</div>
			<div class="card text-white bg-warning"
				style="width: 12rem;">
				<div class="card-header">이번달 플래너</div>
				<div class="card-body">
					<h4 class="card-title">${dashBoardData.pMonthCount}</h4>
				</div>
			</div>
			<div class="card text-white bg-info"
				style="width: 12rem;">
				<div class="card-header">누적 플래너</div>
				<div class="card-body">
					<h4 class="card-title">${dashBoardData.pTotal}</h4>
				</div>
			</div>
		</div>
		<!-- 목록끝 -->
		<div class="row mt-5">
			<div class="col-md-2"></div>
			<div class="col-md-8">
				<canvas id="myChart"></canvas>
			</div>
			<div class="col-md-2"></div>
		</div>


		<!-- 차트 -->
		<script>
			// 현재 날짜 구함
			// *가데이터 넣을때 ★달★만 -1 해서 적용해줘야됨
			// date객체 생성
			var now = new Date();
			
			// day(오늘날짜)에 현재날짜를 넣음
			var day = now.getDate();
			
			// 날짜별로 담기위한 배열생성
			var dateArr = [];
			for(var i=1; i<7; i++) {
				now.setDate(now.getDate()-1);
				dateArr.push(now.getDate());
				console.log(now.getDate());
			}
			// 차트
			var ctx = document.getElementById('myChart').getContext('2d');
			var chart = new Chart(ctx, {
				// 챠트 종류를 선택
				type : 'line',
				// 챠트를 그릴 데이타
				// ★빨간줄 생기면 이클립스 오류
				data : {
					labels : [dateArr[5]+"일", dateArr[4]+"일", dateArr[3]+"일", dateArr[2]+"일", dateArr[1]+"일", dateArr[0]+"일", day+"일"],
					datasets : [ 
						{label : '일일 방문 통계',
						backgroundColor : 'transparent',
						borderColor : '#18a8f1',
						data : [ ${dashBoardData.weekVisitCount[0]}, 
							${dashBoardData.weekVisitCount[1]}, 
							${dashBoardData.weekVisitCount[2]}, 
							${dashBoardData.weekVisitCount[3]}, 
							${dashBoardData.weekVisitCount[4]}, 
							${dashBoardData.weekVisitCount[5]}, 
							${dashBoardData.weekVisitCount[6]}]
						},
						{label : '일일 플래너 통계',
						backgroundColor : 'transparent',
						borderColor: '#f11818',
						data: [${dashBoardData.weekPlannerCount[0]},
							${dashBoardData.weekPlannerCount[1]},
							${dashBoardData.weekPlannerCount[2]},
							${dashBoardData.weekPlannerCount[3]},
							${dashBoardData.weekPlannerCount[4]},
							${dashBoardData.weekPlannerCount[5]},
							${dashBoardData.weekPlannerCount[6]}]
						}
					] // dataSet 끝
				} //data 끝
				// 옵션 options: {}
			});// 차트끝
			
		</script>

	</div>
		<jsp:include page="/WEB-INF/views/common/footer.jsp" />
</body>
</html>