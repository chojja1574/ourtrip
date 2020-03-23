<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<title>CreatePlanner</title>
<!-- 폰트 -->
<link
	href="https://fonts.googleapis.com/css?family=Nanum+Pen+Script|Ubuntu&display=swap"
	rel="stylesheet">

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


<style>
body {
	font-family: 'Ubuntu', sans-serif;
	font-family: 'Nanum Pen Script', cursive;
	font-size: 28px;
}

.inputtext {
	font-size: 42px;
}

.planPlace {
	font-size: 25px;
	background-color: #18a8f1;
	color: white;
	border-radius: 4px;
	width: 20%;
	text-align: center;
	display: inline-block;
}

.planPlaces {
	border: 0px solid rgba(135, 206, 235, 0.3);
	border-radius: 5px;
	background-color: #18a8f1;
	color: white;
	display: none;
	text-align: center;
	width: 10%;
}

#createContent {
	background-color: rgba(135, 206, 235, 0.4);
	height: 1020px;
}

.selectArea {
	width: 50%;
	display: inline-block
}

.selectGroup {
	display: inline-block
}

.selectdate {
	display: inline-block;
	width: 44%;
}
</style>

</head>
<body>
	<jsp:include page="../common/header.jsp" />
	<jsp:include page="../common/nav.jsp" />
	<div class="container-fluid">
		<div class="row">
			<div class="col-md-3"></div>
			<div class="col-md-6">
				<div id="createContent">
					<form action="createPlanner" method="POST">
						<div
							style="width: 100%; height: 20%; position: relative; padding: 30px 30px;">
							<label for="planerTitle">
								<h2 class="inputtext">*플래너 제목입력</h2>
							</label> <input type="text" name="plannerTitle"
								class="form-control required" id="planerTitle"
								placeholder="제목을 입력해 주세요" />
						</div>

						<hr>

						<div class="triPlace"
							style="width: 100%; height: 20%; padding: 30px 30px;">
							<div class="selectArea">
								<label for="selectbox">
									<h2 class="inputtext">*여행장소 입력</h2>
								</label> <br> <select name="largeAreaCode" id="largeArea"
									class="required areaselect">
									<option selected disabled hidden>지역 선택</option>
									<option value="1">서울</option>
									<option value="2">경기</option>
									<option value="3">강원</option>
									<option value="4">충북</option>
									<option value="5">충남</option>
									<option value="6">전북</option>
									<option value="7">전남</option>
									<option value="8">경북</option>
									<option value="9">경남</option>
									<option value="0">제주</option>
								</select> 
								<select name="smallAreaCode" id="smallArea"
									class="required areaselect">
									<option selected disabled hidden>장소 선택</option>
									<option value="11">서울</option>
									<option value="12">경기</option>
									<option value="13">강원</option>
									<option value="14">충북</option>
									<option value="15">충남</option>
									<option value="16">전북</option>
									<option value="17">전남</option>
									<option value="18">경북</option>
									<option value="19">경남</option>
									<option value="00">제주</option>
								</select>
							</div>
							<div class="selectGroup" style="width: 49%;">
								<label for="selectbox">
									<h2 class="inputtext">*여행 그룹선택</h2>
								</label><br> <select name="groupCode" id="tripgroup"
									class="required">
									<option selected disabled hidden>그룹 선택</option>
									<option value="1">혼자 여행하기</option>
									<option value="2">친구랑 여행하기</option>
									<option value="3">연인이랑 여행하기</option>
									<option value="4">가족과 여행하기</option>
								</select>
							</div>
							<br> <input type="text" class="planPlaces" readonly>
						</div>

						<hr>

						<div style="width: 100%; height: 30%; padding: 30px 30px;">
							<div class="selectdate">
								<label for="datePlanner">
									<h2 class="inputtext">*여행 일자 선택</h2>
								</label> <br> <input name="plannerStartDT" type="date"
									id="startPlanner">
							</div>
							<div class="selectdate">

								<input name="plannerExpiry" type="date" id="endPlanner">
								<button type="button" id="event">이벤트</button>
							</div>
						</div>

						<hr>

						<div style="width: 100%; padding: 30px 30px;">
							<h2 class="inputtext">플래너 공개여부 설정</h2>
							<input name=plannerPublicYN type="checkbox" id="openplanner"
								checked="checked" /> <label for="openplanner"> (선택 시
								비공개) </label> <input name="plannerPwd" type="text"
								placeholder="비밀번호 입력 ">

							<hr>
						</div>
						<div style="width: 100%; height: 20%; padding: 30px 30px;">
							<button type="submit" class="btn btn-primary"
								style="width: 40%; height: 50px;">생성</button>
							<button type="reset" class="btn btn-primary"
								style="width: 40%; height: 50px;">취소</button>
						</div>
					</form>
				</div>
			</div>
			<div class="col-md-3"></div>
		</div>
	</div>

	<script type="text/javascript">
	
		$(function(){
			
        	$("#event").on("click",function(){
			var today = new Date(); 
			var dd = today.getDate();
			var mm = today.getMonth() +1;
			var yyyy = today.getFullYear();
		
			var startDate = $("#startPlanner").val(); 
       		var startDateArr = startDate.split('-');
         
        	var endDate = $("#endPlanner").val(); 
        	var endDateArr = endDate.split('-');
                 
        	var startDateCompare = new Date(startDateArr[0], parseInt(startDateArr[1])-1, startDateArr[2]);
        	var endDateCompare = new Date(endDateArr[0], parseInt(endDateArr[1])-1, endDateArr[2]);
         
        	if(startDateCompare.getDate() > endDateCompare.getDate()) {
        		 alert("시작날짜와 종료날짜를 확인해 주세요.");
                 
                 return;	
        	}
        	
        	
        		console.log(startDate);
        		console.log(today)
        	})
		
			
		})
	
	</script>




</body>
</html>