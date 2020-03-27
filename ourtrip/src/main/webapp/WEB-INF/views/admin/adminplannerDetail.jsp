<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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

<link rel="stylesheet" href="css/common.css">
<!-- 폰트어썸 -->
<!-- <script src="https://kit.fontawesome.com/76b49c6d9b.js" crossorigin="anonymous"></script> -->
<title>플래너 상세 조회</title>
<style>
body {
	padding: 20px 20px;
}

.card-size {
	margin-left: 5px;
	display: inline-block;
	text-align: center;
}

.text-color {
	color: #18a8f1;
}
</style>
</head>

<body>

	<jsp:include page="/WEB-INF/views/common/header.jsp" />
	<jsp:include page="/WEB-INF/views/admin/nav.jsp" />
	<div class="container">

		<h1>플래너정보</h1>

		<hr>
		<div class="row">
			<!-- 회원정보 -->
			<div class="col-md-8">
				<table class="table">
					<thead>
						<tr>
							<th><h5>플래너상태</h5></th>
							<c:if test="${plannerinfo.plannerDeleteYN == 'N' }">
								<th><h4 class="mb-0">
										<span class="badge badge-success">정상</span>
									</h4></th>
							</c:if>
							<c:if test="${plannerinfo.plannerDeleteYN == 'Y'}">
								<th><h4 class="mb-0">
										<span class="badge badge-danger"
											style="background-color: red;">탈퇴</span>
									</h4></th>
							</c:if>
						</tr>
					</thead>



					<tbody>
						<tr>
							<td><h5>플래너번호</h5></td>
							<td><h5>${plannerinfo.plannerNo}</h5></td>
						</tr>
						<tr>
							<td><h5>제목</h5></td>
							<td><h5>${plannerinfo.plannerTitle }</h5></td>
						</tr>
						<tr>
							<td><h5>만든이</h5></td>
							<td><h5>${plannerinfo.memberEmail }</h5></td>
						</tr>
						<tr>
							<td><h5>만든이 닉네임</h5></td>
							<td><h5>${plannerinfo.memberNickName }</h5></td>
						</tr>
						<tr>
							<td><h5>여행 기간</h5></td>
							<td><h5>${plannerinfo.plannerStartDT }~
									${plannerinfo.plannerEndDate}</h5></td>
						</tr>
						<tr>
							<td><h5>지역 카테고리</h5></td>
							<td><h5>
									<c:forEach var="Area" items="${plannerArea}" varStatus="vs">
							${Area.largeAreaName}
		                    ${Area.smallAreaName} 
							</c:forEach>
								</h5></td>
						</tr>
						<tr>
							<td><h5>그룹 카테고리</h5></td>
							<td><h5>${plannerinfo.groupName}</h5></td>
						</tr>
						<tr>
							<td><h5>생성일</h5></td>
							<td><h5>${plannerinfo.plannerCreateDT }</h5></td>
						</tr>

					</tbody>
				</table>
			</div>
			<div class="col-md-4">
				<h4>플래너 사진</h4>
				<img src="../4_jQuery/image/cacao1.png" alt="이미지테스트"
					style="width: 100%; height: auto;">
			</div>
		</div>
		<!-- 하단버튼 -->
		<div class="d-flex">
			<a href="${contextPath}/admin/plannerList"
				class="btn main-btn mr-auto">목록으로</a>
			<div class="ml-auto">
				<button type="button" class="btn del-btn" data-toggle="modal"
					data-target="#myModal">삭제</button>
				<a class="btn gray-btn" id="restore">복구</a>
			</div>



			<!-- 모달창 -->
			<div class="modal" id="myModal">
				<div class="modal-dialog" role="document">
					<div class="modal-content">
						<div class="modal-header"
							style="background-color: #18a8f1; color: white">
							<h5 class="modal-title font-weight-bold">삭제 사유</h5>
							<button type="button" class="close" data-dismiss="modal"
								aria-label="Close">
								<span aria-hidden="true">&times;</span>
							</button>
						</div>
						<form action="plannerDelete" method="POST">
							<div class="modal-body text-center">
								<textarea id="delBecause" name="deleteReason"
									style="width: 400px; height: 200px;" placeholder="삭제사유를 입력해주세요"></textarea>
								<p class="text-left">
									삭제 사유를 입력하지 않으면 플래너를 삭제시킬 수 없습니다.<br> 입력한 사유는 해당 플래너의 만든이의
									이메일로 전송됩니다.<br> 삭제된 플래너는 일반회원은 볼 수 없습니다.<br> 삭제된 플래너는
									복구시킬 수 있습니다.
								</p>
								<input name="plannerNo" value="${plannerinfo.plannerNo}"
									style="display: none"> <input name="email"
									value="${plannerinfo.memberEmail}" style="display: none">
							</div>
							<div class="custom-control custom-checkbox text-right">
								<input type="checkbox" class="custom-control-input"
									id="delCheck" checked="checked"> <label
									class="custom-control-label" for="delCheck"
									style="margin-right: 20px; margin-bottom: 10px">확인</label>
							</div>
							<div class="modal-footer">
								<button type="submit" class="btn del-btn">삭제</button>
								<button type="button" class="btn btn-secondary"
									data-dismiss="modal">취소</button>
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
	
	$(function(){
		
		var plannerNo = ${plannerinfo.plannerNo};
		var N = "N";
		var Y = "Y";
		
		$("#restore").on("click",function() {
			if(${plannerinfo.plannerDeleteYN} == N){
				alert("삭제 되지 않은 플래너 입니다.")
			}
			if(${plannerinfo.plannerDeleteYN} == Y){
			 (confirm("복구하시겠습니까?")) 
				location.href = "${contextPath}/admin/plannerRecovery?plannerNo="+ plannerNo
			}
		});
		
	});
		
		
	</script>
</body>

</html>