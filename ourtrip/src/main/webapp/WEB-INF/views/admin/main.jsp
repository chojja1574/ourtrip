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
<%-- <link rel="stylesheet" href="${contextPath}/resources/images/ourtrip_logo2.png"> --%>
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
	<jsp:include page="header.jsp"/>
	<div class="container">
		<div class="row justify-content-center my-3">
			<div class="card text-white bg-primary"
				style="width: 12rem;">
				<div class="card-header">오늘방문수</div>
				<div class="card-body">
					<h4 class="card-title">200명</h4>
				</div>
			</div>
			<div class="card text-white bg-secondary"
				style="width: 12rem;">
				<div class="card-header">이번달 방문수</div>
				<div class="card-body">
					<h4 class="card-title">1300명</h4>
				</div>
			</div>
			<div class="card text-white bg-success"
				style="width: 12rem;">
				<div class="card-header">누적 방문수</div>
				<div class="card-body">
					<h4 class="card-title">20000명</h4>
				</div>
			</div>
		</div>
		<!-- 2번쨰줄 -->
		<div class="row justify-content-center my-3">
			<div class="card text-white bg-danger"
				style="width: 12rem;">
				<div class="card-header">오늘 플래너</div>
				<div class="card-body">
					<h4 class="card-title">7개</h4>
				</div>
			</div>
			<div class="card text-white bg-warning"
				style="width: 12rem;">
				<div class="card-header">이번달 플래너</div>
				<div class="card-body">
					<h4 class="card-title">700개</h4>
				</div>
			</div>
			<div class="card text-white bg-info"
				style="width: 12rem;">
				<div class="card-header">누적 플래너</div>
				<div class="card-body">
					<h4 class="card-title">1400개</h4>
				</div>
			</div>
		</div>
		<!-- 목록끝 -->
		<div class="row">
			<div class="col-md-2"></div>
			<div class="col-md-8">
				<canvas id="myChart"></canvas>
			</div>
			<div class="col-md-2"></div>
		</div>


		<!-- 차트 -->
		<script>
		
			// 현재 날짜 구함
			var now = new Date();
			var day = now.getDate();
			
			// 차트
			var ctx = document.getElementById('myChart').getContext('2d');
			var chart = new Chart(ctx, {
				// 챠트 종류를 선택
				type : 'line',
				// 챠트를 그릴 데이타
				data : {
					labels : [day-6, day-5, day-4, day-3, day-2, day-1, day],
					datasets : [ {
						label : '일일 방문 통계',
						backgroundColor : 'transparent',
						borderColor : '#18a8f1',
						data : [ 0, 15, 5, 2, 20, 30, 45 ]
					} ]
				},
			// 옵션 options: {}
			});
		</script>

	</div>
</body>
</html>