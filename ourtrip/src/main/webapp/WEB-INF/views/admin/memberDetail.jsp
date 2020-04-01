<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html lang="ko">

<head>
<meta charset="UTF-8">
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
<!-- 공용 css -->
<link rel="stylesheet" href="${contextPath}/resources/css/common.css">
<!-- 폰트어썸 -->
<script src="https://kit.fontawesome.com/76b49c6d9b.js"
	crossorigin="anonymous"></script>
<link
	href="https://fonts.googleapis.com/css?family=Stylish&display=swap"
	rel="stylesheet">
<title>회원 상세 조회</title>
<style>
body {
	padding: 20px 20px;
}

.text-color {
	color: #18a8f1;
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

.page-num{
	color: #18a8f1;
}

.page-num:hover{
	cursor: pointer;
}

.displayNone {
	display: none;
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
	<jsp:include page="/WEB-INF/views/admin/nav.jsp" />

	<div class="container">

		<h1>회원정보</h1>

		<hr>
		<div class="row">
			<!-- 회원정보 -->
			<div class="col-md-8">
				<table class="table">
					<thead>
						<tr>
							<th><h5>회원상태</h5></th>
							<c:if test="${detailMember.memberStatus == 'N' }">
								<th><h4 class="mb-0">
										<span class="badge badge-success">정상</span>
									</h4></th>
							</c:if>
							<c:if test="${detailMember.memberStatus == 'Y'}">
								<th><h4 class="mb-0">
										<span class="badge badge-success"
											style="background-color: red;">탈퇴</span>
									</h4></th>
							</c:if>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td><h5>회원번호</h5></td>
							<td><h5>${detailMember.memberNo}</h5></td>
						</tr>
						<tr>
							<td><h5>Email</h5></td>
							<td><h5>${detailMember.memberEmail}</h5></td>
						</tr>
						<tr>
							<td><h5>닉네임</h5></td>
							<td><h5>${detailMember.memberNickName}</h5></td>
						</tr>
						<tr>
							<td><h5>가입 경로</h5></td>
							<td><h5>
									<c:if test="${detailMember.signUpRoute == '1'}">일반가입</c:if>
									<c:if test="${detailMember.signUpRoute == '2'}">카카오</c:if>
									<c:if test="${detailMember.signUpRoute == '3'}">네이버</c:if>
								</h5></td>
						</tr>
						<tr>
							<td><h5>가입일</h5></td>
							<td><h5>${detailMember.memberEnrollDt}</h5></td>
						</tr>

					</tbody>
				</table>
			</div>
			<div class="col-md-4">
				<h4 style="height: 10%">프로필 사진</h4>
				<c:if test="${!empty pi}">
					<!-- 카카오에서 가져온 이미지 경로일 경우 -->
					<c:if test="${fn:contains(pi.imagePath, 'http')}">
						<c:set var="filePath" value="${pi.imagePath}" />
					</c:if>

					<!-- ourtrip서버에 있는 경로일 경우 -->
					<c:if test="${!fn:contains(pi.imagePath, 'http')}">
						<c:set var="fileName" value="${fn:split(pi.imagePath, '/')}" />
						<c:set var="filePath"
							value="${contextPath}/resources/profileImages/${fileName[fn:length(fileName) - 1]}" />
					</c:if>
				</c:if>

				<img id="ot_profile_image" alt="your image" class="rounded mx-auto"
					width="100%" height="85%"
					src=<c:if test="${!empty pi}">
								"${filePath}"
							</c:if>
					<c:if test="${empty pi}">
								"../resources/images/default-profile.png"
							</c:if>>
			</div>
		</div>
		<hr>
		<!-- 플래너 컨테이너 -->
		<div class="py-3">
			<h4>회원의 플래너 목록</h4>

			<!-- 수정중인 플래너 리스트 -->
			<div class="planner-wrapper my-3">
				<c:if test="${empty plannerList}">
					<div class="planner" style="height: 250px;">수정중인 플래너가 없습니다.</div>
				</c:if>
				<c:if test="${!empty plannerList}">
					<c:forEach var="planner" items="${plannerList}" varStatus="vs">
						<div
							class="planner<c:if test='${vs.count > 4}'> displayNone</c:if>"
							id="planner${vs.count}">
							<div class="card">
								<img class="card-img-top"
									src="${contextPath}/resources/areaImages/${planner.areaNames[0].largeAreaCode}.jpg"
									alt="Card image">
								<div class="card-body">
									<h5 class="card-title">${planner.plannerTitle}</h5>
									<p class="card-text">
										<span>시작일 : ${planner.plannerStartDT}
											${planner.tripDate}DAYS</span><br> <span>${planner.groupName}
											여행</span><br>
										<c:if test="${planner.areaNames.size()>1 }">
											<span>${planner.areaNames[0].largeAreaName}
												${planner.areaNames[0].smallAreaName} ...</span>
										</c:if>
										<c:if test="${planner.areaNames.size()==1 }">
											<span>${planner.areaNames[0].largeAreaName}
												${planner.areaNames[0].smallAreaName} </span>
										</c:if>
										<c:if test="${planner.areaNames.size()<1 }">
											<span>없음 </span>
										</c:if>
									</p>
									<div class="d-flex justify-content-between">
										<div class="btn-wrapper">
											<a href="plannerDetail?no=${planner.plannerNo}" type="button"
												class="btn btn-sm main-btn">플래너 확인</a>
										</div>
										<div>
											<i class="fas fa-eye"></i>&nbsp;${planner.plannerCount}
										</div>
									</div>
								</div>
							</div>
						</div>
					</c:forEach>
				</c:if>

			</div>

			<script>
				var limit = 4;
				var pagingBarSize = 5;
				
				var plannerSize = ${plannerList.size()};
				var currentPage = 1;
			</script>

			<!-- 페이징바 -->
			<div class="pagination-wrapper" id="plannerPaging">
				<nav aria-label="Page navigation">
					<ul class="pagination justify-content-center"></ul>
				</nav>
			</div>
		</div>
		<!-- 수정화면 -->

		<!-- 하단버튼 -->
		<div class="d-flex mb-3">
			<a href="${beforeUrl}" class="btn gray-btn mr-auto">목록으로</a>
			<div class="ml-auto">
				<c:if test="${detailMember.memberStatus eq 'N'}">
					<button type="button" class="btn del-btn" data-toggle="modal"
						data-target="#myModal">삭제</button>
				</c:if>
				<c:if test="${detailMember.memberStatus ne 'N'}">
					<button class="btn gray-btn" id="restore">복구</button>
				</c:if>
			</div>


			<!-- 모달창 -->
			<div class="modal" id="myModal">
				<div class="modal-dialog" role="document">
					<div class="modal-content">
						<div class="modal-header main-back">
							<h5 class="modal-title font-weight-bold">삭제 사유</h5>
							<button type="button" class="close" data-dismiss="modal"
								aria-label="Close">
								<span aria-hidden="true">&times;</span>
							</button>
						</div>
						<form action="${contextPath}/admin/memberDelete" method="POST" onsubmit="return validate();">
							<input type="hidden" name="memberNo" value="${detailMember.memberNo}"></input>
							<input type="hidden" name="email" value="${detailMember.memberEmail}"></input>
							<div class="modal-body text-center">
								<textarea id="delBecause" name="delBecause"
									style="width: 400px; height: 200px;" placeholder="삭제사유를 입력해주세요"></textarea>
								<p class="text-left">
									삭제 사유를 입력하지 않으면 회원을 삭제시킬 수 없습니다.<br> 입력한 사유는 해당 회원의 이메일로
									전송됩니다.<br> 삭제된 회원은 로그인을 할 수 없습니다.<br> 삭제된 회원은 같은 이메일,
									가입경로로 회원가입을 할 수 없습니다.<br> 삭제된 회원은 복구시킬 수 있습니다.
								</p>
							</div>
							<div class="custom-control custom-checkbox text-right">
								<input type="checkbox" class="custom-control-input"
									id="delCheck"> <label
									class="custom-control-label" for="delCheck"
									style="margin-right: 20px; margin-bottom: 10px">확인</label>
							</div>
							<div class="modal-footer">
								<button type="submit" class="btn del-btn">삭제</button>
								<button type="button" class="btn gray-btn" data-dismiss="modal">취소</button>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>

	</div>
	<jsp:include page="/WEB-INF/views/common/footer.jsp" />


	<!-- 복구질문 -->
	<script>
	
	function validate() {
		if($("#delCheck").prop("checked")) {
			return true;
		} else {
			alert("설명을 읽고 체크해주세요");
			return false;
		}
	}
	
	$(function(){
		
		
		var MemberNo = ${detailMember.memberNo};
		
		$("#restore").on("click",function() {
			if("${detailMember.memberStatus}" == "Y"){
			 	if(confirm("복구하시겠습니까?")){
					location.href = "${contextPath}/admin/memberRecovery?memberNo="+ MemberNo
			 	}
			}
		});

       	// 수정중인 플래너 페이징바 초기화
       	pagingHtmlFn();
       	disCurrent($("#plannerPaging").children().children().children());

        $(".planner-delete").on("click", function () {
            var plannerNo = $(this).parent().children("input").val();

            if (confirm("플래너를 삭제하시겠습니까?")) {
                location.href="delPlanner?plannerNo=" + plannerNo;
            }
        });
           
        $(".planner-out").on("click", function () {
            var plannerNo = $(this).parent().children("input").val();

            if (confirm("플래너를 나가시겠습니까?")) {
                location.href="outPlanner?plannerNo=" + plannerNo;
            }
        });
	});
		// 페이징 번호 작성 함수
        function pagingHtmlFn(){
			
			var maxPage = (plannerSize + (limit - (plannerSize % limit)) % limit) / limit;
            
            var totalPage = Math.ceil(plannerSize/limit);    // 총 페이지 수
            var pageGroup = Math.ceil(currentPage/pagingBarSize);    // 페이지 그룹
            
            var last = pageGroup * pagingBarSize;    // 화면에 보여질 마지막 페이지 번호
            
            if(last > totalPage){
                last = totalPage;
            }
            var first = ((pageGroup - 1) * pagingBarSize) + 1;    // 화면에 보여질 첫번째 페이지 번호
            /* var next = last+1;
            var prev = first-1; */
            
        	var $plannerChild = $("#plannerPaging").children().children();
			
        	var pagingHtml = "";
        	if(currentPage > 1){
        		pagingHtml += "<li class='page-item'><span class='page-link page-num paging'><<</span></li>";
        		pagingHtml += "<li class='page-item'><span class='page-link page-num paging'><</span></li>";
        	}
        	
        	for(var i=first; i<=last; i++){
        		pagingHtml += "<li class='page-item'><span class='page-link page-num paging'>" + i + "</span></li>";
        	}
        	
        	if(currentPage < maxPage){
        		pagingHtml += "<li class='page-item'><span class='page-link page-num paging'>></span></li>";
        		pagingHtml += "<li class='page-item'><span class='page-link page-num paging'>>></span></li>";
        	}
        	
        	// 플래너 display:none으로 초기화
			for(var i=1; i<=plannerSize; i++){
				$("#planner" + i).addClass("displayNone");
			}
			
			var startIndex = (currentPage - 1) * limit + 1;
    		var endIndex = startIndex + limit - 1;
    		if(endIndex > plannerSize){
    			endIndex = plannerSize;
    		}
			// 해당되는 플래너들만 보여줌
			for(var i=startIndex; i<=endIndex; i++){
				$("#planner" + i).removeClass("displayNone");
			}
        	console.log(pagingHtml);
        	$plannerChild.html(pagingHtml);
        	
        	$(".paging").on("click", function(){
        		var $click = $(this);
        		
        		if($click.text() == "<<"){
        			currentPage = 1;
        		}else if($click.text() == "<"){
        			currentPage = Number(currentPage) - 1;
        		}else if($click.text() == ">"){
        			currentPage = Number(currentPage) + 1;
        		}else if($click.text() == ">>"){
        			currentPage = totalPage;
        		}else if($click.text() != currentPage){
        			currentPage = $click.text();
        		}
        		
        		$click = $click.parent().parent();
       			pagingHtmlFn();
       			disCurrent($click.children());
        	});
        	
        }
        
        // 현재 페이지번호와 같을 시 disabled시키는 함수
        function disCurrent(list){
        	$(list).each(function(index, item){
        		if($(item).text() == currentPage){
        			$(item).addClass("disabled");
        		}else{
        			$(item).removeClass("disabled");
        		}
        	});
        }
    </script>
</body>
</html>