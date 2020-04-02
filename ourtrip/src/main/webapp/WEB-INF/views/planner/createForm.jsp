<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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

<link rel="stylesheet" href="../resources/css/common.css">
<style>
#createContent {
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
	height: 900px;
}

.selectArea {
	width: 30%;
	display: inline-block
}

.selectGroup {
	display: inline-block
}

.selectdate {
	display: inline-block;
	width: 30%;
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
					<form action="createPlanner" method="POST"
						onsubmit="return sendData()">
						<div
							style="width: 100%; height: 20%; position: relative; padding-left: 30px; padding-right: 30px; padding-top: 20px">
							<label for="planerTitle">
								<h2 class="inputtext">*플래너 제목입력</h2>
							</label> <input type="text" name="plannerTitle"
								class="form-control required" id="planerTitle"
								placeholder="제목을 입력해 주세요" />
						</div>

						<hr>

						<div class="triPlace"
							style="width: 100%; height: 15%; padding-left: 30px; padding-right: 30px;">
							<div class="selectArea">
								<label for="selectbox">
									<h2 class="inputtext">*여행장소 입력</h2>
								</label>
								<button type="button" class="btn btn-primary" id="locationAdd">+</button>
								<select name="largeArea" id="wide-area" class="custom-select"
									style="display: inline-block" required>
									<c:forEach var="largeArea" items="${largeNmList}"
										varStatus="vs">
										<option value="${largeArea.largeAreaCode}">${largeArea.largeAreaName}</option>
									</c:forEach>
								</select> <select name="smallArea" id="local-area" class="custom-select"
									style="display: inline-block" required>
									<option value="0" selected>전체</option>
								</select> <input name="locationList" id="lList" style="display: none;">
							</div>
							<div id="locationList" style="width: 100%;"></div>
						</div>
						<hr>
						<div class="triPlace"
							style="width: 100%; height: 15%; padding-left: 30px; padding-right: 30px; padding-top: 20px;">

							<div class="selectGroup" style="width: 35%;">
								<label for="selectbox">
									<h2 class="inputtext">*여행 그룹선택</h2>
								</label><br> <label><h3>여행그룹</h3></label> <select name="groupCode"
									id="tripgroup" class="required form-control">
									<option selected disabled hidden>그룹 선택</option>
									<option value="1">혼자 여행하기</option>
									<option value="2">연인이랑 여행하기</option>
									<option value="3">친구랑 여행하기</option>
									<option value="4">가족과 여행하기</option>
								</select>
							</div>
							<div class="selectdate" style="width: 35%;">
								<h2 class="inputtext">*여행 일자 선택</h2>
								<label><h3>출발일</h3></label> <input name="plannerStartDT"
									type="date" id="startPlanner" class="form-control">
							</div>

						</div>
						<hr>

						<div
							style="width: 100%; height: 20%; padding-left: 30px; padding-right: 30px;">
							<h2 class="inputtext">플래너 공개여부 설정</h2>
							<input name=plannerPublicYN type="checkbox" id="openplanner"
								checked="checked" /> <label for="openplanner"> (선택 시
								비공개) </label> <input id="pwd" name="plannerPwd" type="password"
								class="form-control" placeholder="비밀번호 입력 ">

						</div>
						<hr>
						<div style="width: 100%; height: 20%; padding: 30px 30px;">
							
								<button type="button" class="btn btn-primary" id="cancle"
									style="width: 40%; height: 50px; float: right; margin: 5px;">취소</button>
								<button type="submit" class="btn btn-primary" id="event"
									style="width: 40%; height: 50px; float: left; margin: 5px;">생성</button>
						</div>
					</form>
				</div>
			</div>
			<div class="col-md-3"></div>
		</div>
	</div>

	<script type="text/javascript">
	var areal = '';
	var locationList = new Array();
	
		
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
        	var todateCompare = new Date(yyyy , mm , dd);
        	if(startDateCompare.getDate() > endDateCompare.getDate()) {
        		 alert("시작날짜와 종료날짜를 확인해 주세요.");
                 return false;	
        	}
        	})
		
        		
		
		 $(function () {
	            $("#wide-area").on("change", function () {
	                var wideVal = Number($(this).val());
	                console.log(wideVal);
	                var html = "";
	                switch(wideVal){
					<c:forEach var="largeArea" items="${largeNmList}" varStatus="vs">
					case ${largeArea.largeAreaCode} : 
						<c:forEach var="smallArea" items="${smallNmList}" varStatus="vs">
							<c:if test="${smallArea.largeAreaCode eq largeArea.largeAreaCode}">
			        			html += "<option value='${smallArea.smallAreaCode}'>${smallArea.smallAreaName}</option>";
			        		</c:if>
			        	</c:forEach>break;
					</c:forEach>
	                }

	                $("#local-area").html(html);
	            });
	            
		  });

	   $('#locationAdd').click(function(){
		   
	      var locationDup = locationEqual($('#wide-area').val(),$('#local-area').val());
		   console.log(locationDup);
		   
	      if(locationDup == undefined){
	         var locationName = $('#wide-area option[value='+$('#wide-area').val()+']').html() + 
	            " - " + $('#local-area option[value='+$('#local-area').val()+']').html();
	         
	         var locationDiv =
	         '<div style="display: inline-block;">' +
	         '<div class="locationDiv" style="width:115px; display: inline-block; margin:3px">' +
	         '<span class="custom-select" style="background:white">' + locationName + '</span>' +
	         '</div>' +
	         '<button data-large="' + $('#wide-area').val() + '" data-small="' + $('#local-area').val() + '" type="button"' +
	         'class="btn btn-danger" onclick="removeLocation(this);">-</button>'+
	         '</div>';
	         
	         if(locationList.length < 5){
	            console.log("large : " + $('#wide-area').val() + ", small : " + $('#local-area').val());
	            locationList.push({large:$('#wide-area').val(),small:$('#local-area').val()});
	            console.log(locationList);
	            $('#locationList').append(locationDiv);
	            
	         }else{
	            alert('지역을 더이상 추가할 수 없습니다');
	         }
	      }else{
	         alert('같은 장소를 중복해서 선택할 수 없습니다');
	      }
	   });

	   function removeLocation(location){
	      console.log(location);
	      console.log(locationList);
	      locationList.splice(locationEqual($('#wide-area').val(),$('#local-area').val()),1);
	      console.log(locationList);
	      $(location).parent().remove();
	   }

	   function locationEqual(lc, sc){
	      for(var i in locationList){
	         if(locationList[i].large == lc && locationList[i].small == sc)
	            return i;
	      }
	   }

	   $('#updateLocation').click(function(){
	      var locationJson = JSON.stringify(locationList);
	      console.log(locationJson);
	   });
	   
	   
	   function sendData(){
		   
		   if($("#planerTitle").val() == ''){
			   alert('플래너 제목을 입력해 주세요');
			   $("#planerTitle").focus()
			   return false;
		   }else if(locationList.length < 1){
			   alert('여행지를 선택해 주세요');
			   return false;
		   }else if($("#tripgroup").val() == null){
			   alert('여행 그룹을 선택해 주세요');
			   return false
		   }else if($("#startPlanner").val == ''){
			   alert('출발일 선택해 주세요');
			   return false
		   }else if($("#endPlanner").val == ''){
			   alert('종료일 선택해 주세요');
			   return false
		   }else if($("#openplanner").is(":checked") == true && $("#pwd").val() == ''){
			   alert("비공개인경우 비밀번호를 입력해주세요")
			   return false
		   }
		   $("#lList").val(JSON.stringify(locationList));
		   return true;
	   };
	   
		   $("#cancle").on("click",function(){
			   if(confirm("취소 하시겠습니까?")){
				  	location.href="${contextPath}"
				   console.log("test")
			   }
			   
		   })
	  
			
	</script>



	<jsp:include page="../common/footer.jsp" />
</body>
</html>