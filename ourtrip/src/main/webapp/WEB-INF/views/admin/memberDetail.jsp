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
<link rel="stylesheet" href="css/common.css">
<!-- 폰트어썸 -->
<!-- <script src="https://kit.fontawesome.com/76b49c6d9b.js" crossorigin="anonymous"></script> -->
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
									<c:if test="${detailMember.signUpRoute == '3'}">구글</c:if>
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
				<h4>프로필 사진</h4>
				<img src="../4_jQuery/image/cacao1.png" alt="이미지테스트"
					style="width: 100%; height: auto;">
			</div>
		</div>
		<hr>
		<!-- 나의 플래너 -->
		<div class="py-3 planner-wrapper">
			<h2 class="display-5">회원 플래너 목록</h2>
			<!-- 플래너 리스트 -->
			<div class="planner-wrapper my-3">
				<c:if test="${empty plannerInfo }">
						존재하는 플래너가 없습니다.
						</c:if>
				<c:if test="${!empty plannerInfo }">
					<c:forEach var="plannerInfo" items="${plannerInfo}" varStatus="vs">
						<div class="planner">
							<div class="card">
								<img class="card-img-top" src="images/ourtrip_logo.png"
									alt="Card image">
								<div class="card-body">
									<div id="plannerNo" style="display: none;">${plannerInfo.plannerNo}</div>
									<h5 class="card-title">${plannerInfo.plannerTitle}</h5>
									<p class="card-text">
										<span>시작일 :${plannerInfo.plannerStartDT}</span><br> 
										<span>${plannerInfo.groupName} </span><br> 
										<span> <c:forEach var="plannerArea" items="${plannerArea}" varStatus="vs">
												<c:if test="${plannerInfo.plannerNo} == ${plannerArea.plannerNo}">
												${plannerArea.largeAreaName} - ${plannerArea.smallAreaName}
											</c:if>
												<c:if test="${fn:length(boardList)} > 1">
											${plannerArea.largeAreaName} - ${plannerArea.smallAreaName} 
											</c:if>
											</c:forEach>
										</span>
									</p>
									<div class="d-flex justify-content-between">
										<div class="btn-wrapper">
											<button type="button" class="btn btn-sm main-btn">바로가기</button>
											<button type="button" class="btn  btn-sm main-btn copy-btn">복사</button>
										</div>
										<div>
											<i class="fas fa-eye"></i>&nbsp;조회수
										</div>
									</div>
								</div>
							</div>
						</div>
					</c:forEach>
				</c:if>
			</div>
		</div>
		<!-- 페이징바 -->
		<div class="pagination-wrapper">
			<nav aria-label="Page navigation">
				<ul class="pagination justify-content-center">
					<c:if test="${pInfom.currentPage > 1}">
						<li>
							<!-- 맨 처음으로(<<) --> <!--c: url 태그에 var속성이 존재하지 않으면 변수처럼 사용되는 것이 아니라 작성된 자리에 바로 url형식으로 표기된다.  -->
							<a class="page-link text-success"
							href=" 
		                    	<c:url value="detail"> 
		                    		<c:param name="currentPage" value="1"/>
		                    	</c:url>
	                    	">
								&lt;&lt; </a>
						</li>
						<li>
							<!-- 이전으로(<) --> <a class="page-link text-success"
							href=" 
		                    	<c:url value="detail">
		                    		
		                    		<c:param name="currentPage" value="${pInfom.currentPage-1}"/>
		                    	</c:url>
	                    	">
								&lt; </a>
						</li>
					</c:if>
					<!-- 10개의 페이지 목록 -->
					<c:forEach var="p" begin="${pInfom.startPage}"
						end="${pInfom.endPage}">
						<c:if test="${p == pInfom.currentPage}">
							<li><a class="page-link">${p}</a></li>
						</c:if>

						<c:if test="${p != pInfom.currentPage}">
							<li><a class="page-link text-success"
								href=" 
			                    	<c:url value="detail">
			                    		
			                    		<c:param name="currentPage" value="${p}"/>
			                    	</c:url>
		                    	">
									${p} </a></li>
						</c:if>

					</c:forEach>
					<!-- 다음 페이지로(>) -->
					<c:if test="${pInfom.currentPage < pInfom.maxPage }">
						<li><a class="page-link text-success"
							href=" 
		                    	<c:url value="detail">
		                    		
		                    		<c:param name="currentPage" value="${pInfom.currentPage+1}"/>
		                    	</c:url>
	                    	">
								&gt; </a></li>
						<!-- 맨 끝으로(>>) -->
						<li><a class="page-link text-success"
							href=" 
		                    	<c:url value="detail">
		                    		
		                    		<c:param name="currentPage" value="${pInfom.maxPage}"/>
		                    	</c:url>
	                    	">
								&gt;&gt; </a></li>
					</c:if>
				</ul>
			</nav>
		</div>

		<!-- 하단버튼 -->
		<div class="d-flex">
			<a href="${contextPath}/admin/List" class="btn main-btn mr-auto">목록으로</a>
			<div class="ml-auto">
				<button type="button" class="btn del-btn" data-toggle="modal"
					data-target="#myModal">삭제</button>
				<a href="#" class="btn gray-btn" id="restore">복구</a>
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
						<form action="#" method="POST">
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
									id="delCheck" checked=""> <label
									class="custom-control-label" for="delCheck"
									style="margin-right: 20px; margin-bottom: 10px">확인</label>
							</div>
							<div class="modal-footer">
								<button type="submit" class="btn btn-primary">
									삭제
									<button type="button" class="btn btn-secondary"
										data-dismiss="modal">취소</button>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
		<!-- 복구질문 -->
		<script>
			$("#restore").on("click", function() {
				if (confirm("복구하시겠습니까?")) {
					location.href = "#";
				}
			})
		</script>
</body>

</html>