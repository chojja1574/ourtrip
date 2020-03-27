<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
<link rel="stylesheet" href="css/common.css">
<title>회원목록</title>

<style>
@media screen and (max-width: 500px) {
	table {
		font-size: 10px;
	}
}
</style>
</head>

<body>

	<jsp:include page="/WEB-INF/views/common/header.jsp" />
	<jsp:include page="/WEB-INF/views/admin/nav.jsp" />

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


	<hr>
	<div class="container my-5">
		<hr>
		<form action="searchPlanner" method="GET" id="searchInfo"
			name="searchInfo">
			<!-- 검색창 -->
			<div>
				<!-- 가운데정렬시 class="text-center" -->
				<div class="text-center" id="searchForm"
					onsubmit="return checkValue();">
					<select name="searchKey" class="form-control"
						style="width: 100px; display: inline-block;">
						<option value="title">제목</option>
						<option value="nickName">닉네임</option>
					</select> <input type="text" name="searchValue" id="searchValue"
						class="form-control" style="width: 25%; display: inline-block;">
					<button class="form-control btn main-btn" type="submit"
						style="width: 100px; display: inline-block; margin-bottom: 5px;">검색</button>
				</div>
			</div>
			<!-- 첫번째 검색 조건 -->
			<div class="row mt-3">
				<div class="col-md-6">
					<label class="col-3">여행기간</label>&nbsp; <input name="startTrip"
						type="date"> ~ <input name="endTrip" type="date">
				</div>

				<div class="col-md-6 custom-control custom-checkbox">
					<div>
						<label style="float: left;">그룹카테고리 : </label>
					</div>
					<div class="form-check form-check-inline">
						<input type="checkbox" name="searchGroup" value="1"
							class="form-check-input custom-control-input" id="customCheck1">
						<label class="form-check-label custom-control-label"
							for="customCheck1">혼자</label>
					</div>
					<div class="form-check form-check-inline">
						<input type="checkbox" name="searchGroup" value="2"
							class="form-check-input custom-control-input" id="customCheck2">
						<label class="form-check-label custom-control-label"
							for="customCheck2">친구</label>
					</div>
					<div class="form-check form-check-inline">
						<input type="checkbox" name="searchGroup" value="3"
							class="form-check-input custom-control-input" id="customCheck3">
						<label class="form-check-label custom-control-label"
							for="customCheck3">커플</label>
					</div>
					<div class="form-check form-check-inline">
						<input type="checkbox" name="searchGroup" value="4"
							class="form-check-input custom-control-input" id="customCheck4">
						<label class="form-check-label custom-control-label"
							for="customCheck4">가족</label>
					</div>
				</div>

			</div>
			<!-- 두번째 검색조건 -->
			<div id="select-option" class="col-md-12 mb-3 mt-3">
				<div class="row">

					<div class="col-md-6" id="location-wrapper">
						<div class="row">
							<label class="col-2" style="margin-top: 7px;">지역</label>
							<div class="col-4">
								<select name="searchArea" id="wide-area" class="custom-select">
									<option value="0" selected>전체</option>
									<option value="01">서울특별시</option>
									<option value="02">경기도</option>
									<option value="03">강원도</option>
								</select>
							</div>
							<div class="col-4">
								<select name="searchLocal" id="local-area" class="custom-select">
									<option value="0" selected>전체</option>
								</select>
							</div>
						</div>
					</div>

					<div class="col-md-6" id="group-wrapper">
						<div class="row">
							<label class="col-3 ml-2" style="margin-top: 7px;">삭제여부</label>
							<div class="col-4">
								<select name="deleted" id="wide-area" class="custom-select">
									<option value="all" selected>전체</option>
									<option value="N">존제</option>
									<option value="Y">삭제</option>
								</select>
							</div>
						</div>
					</div>
				</div>
			</div>
		</form>
		<hr>

		<h2>플래너 목록</h2>
		<table id="plannerTable" class="table table-hover" style="text-align: center;">
			<thead class="thead-dark">
				<tr>
					<th>플래너번호</th>
					<th>제목</th>
					<th>여행기간</th>
					<th>지역</th>
					<th>그룹</th>
					<th>생성일</th>
				</tr>
			</thead>
			<tbody>
				<c:if test="${empty plannerList }">
					<tr>
						<td colspan="6" id="none-board">존재하는 플래너가 없습니다.</td>
					</tr>
				</c:if>
				<c:if test="${!empty plannerList }">
					<c:forEach var="planner" items="${plannerList}" varStatus="vs">
						<tr>
							<td>${planner.plannerNo}</td>
							<td>${planner.plannerTitle}</td>
							<td>${planner.plannerStartDT}~${planner.plannerEndDate}</td>

							<td><c:if test="${planner.areaNames.size()>1 }">
		                                	${planner.areaNames[0].largeAreaName}
		                                	${planner.areaNames[0].smallAreaName} ...
		                                </c:if> <c:if
									test="${planner.areaNames.size()==1 }">
		                                	${planner.areaNames[0].largeAreaName}
		                                	${planner.areaNames[0].smallAreaName} 
		                                </c:if></td>
							<td>${planner.groupName}</td>
							<td>${planner.plannerCreateDT}</td>
						</tr>
					</c:forEach>
				</c:if>
			</tbody>
		</table>

		<!-- 페이징바 -->
		<div class="pagination-wrapper mt-5" id="pagination-wrapper">
			<nav aria-label="Page navigation" id="pagination">
				<ul class="pagination justify-content-center">
					<c:if test="${pInfom.currentPage > 1}">
						<li>
							<!-- 맨 처음으로(<<) --> <!--c: url 태그에 var속성이 존재하지 않으면 변수처럼 사용되는 것이 아니라 작성된 자리에 바로 url형식으로 표기된다.  -->
							<a class="page-link text-success"
							href=" 
		                    	<c:url value="list"> 
		                    		<c:if test="${!empty param.searchKey }">
						        		<c:param name="searchKey" value="${param.searchKey}"/>
						        	</c:if>
						        	
						        	<c:if test="${!empty param.searchValue }">
						        		<c:param name="searchValue" value="${param.searchValue}"/>
						        	</c:if>
		                    		<c:param name="currentPage" value="1"/>
		                    	</c:url>
	                    	">
								&lt;&lt; </a>
						</li>

						<li>
							<!-- 이전으로(<) --> <a class="page-link text-success"
							href=" 
		                    	<c:url value="list">
		                    		<c:if test="${!empty param.searchKey }">
						        		<c:param name="searchKey" value="${param.searchKey}"/>
						        	</c:if>
						        	
						        	<c:if test="${!empty param.searchValue }">
						        		<c:param name="searchValue" value="${param.searchValue}"/>
						        	</c:if>
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
			                    	<c:url value="list">
			                    		<c:if test="${!empty param.searchKey }">
							        		<c:param name="searchKey" value="${param.searchKey}"/>
							        	</c:if>
							        	
							        	<c:if test="${!empty param.searchValue }">
							        		<c:param name="searchValue" value="${param.searchValue}"/>
							        	</c:if>
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
		                    	<c:url value="list">
		                    		<c:if test="${!empty param.searchKey }">
						        		<c:param name="searchKey" value="${param.searchKey}"/>
						        	</c:if>
						        	
						        	<c:if test="${!empty param.searchValue }">
						        		<c:param name="searchValue" value="${param.searchValue}"/>
						        	</c:if>
		                    		<c:param name="currentPage" value="${pInfom.currentPage+1}"/>
		                    	</c:url>
	                    	">
								&gt; </a></li>

						<!-- 맨 끝으로(>>) -->
						<li><a class="page-link text-success"
							href=" 
		                    	<c:url value="list">
		                    		<c:if test="${!empty param.searchKey }">
						        		<c:param name="searchKey" value="${param.searchKey}"/>
						        	</c:if>
						        	<c:if test="${!empty param.searchValue }">
						        		<c:param name="searchValue" value="${param.searchValue}"/>
						        	</c:if>
		                    		<c:param name="currentPage" value="${pInfom.maxPage}"/>
		                    	</c:url>
	                    	">
								&gt;&gt; </a></li>

					</c:if>
				</ul>
			</nav>
		</div>
	</div>
	<!-- 회원 목록 끝 -->

	<script>
		// 검색창 내용 유무 확인
		function checkValue() {
			if ($("#searchValue").val().trim().length == 0) {
				alert("검색내용을 입력해주세요");
				return false;
			} else {
				return true;
			}
		}

		$(function() {
			var searchKey = "${param.searchKey}";
			var searchValue = "${param.searchValue}";
			var searchGroup = "${param.searchGroup}";
			var searchArea = "${param.seachArea}";
			var searchLocal = "${param.seachLocal}";
			var deleted = "${param.deleted}";
			var startTrip = "${param.startTrip}";
			var endTrip = "${param.endTrip}";

			if (searchKey != "null" && searchValue != "null") {
				$.each($("select[name=searchKey] > option"), function(index,
						item) {
					if ($(item).val() == searchKey) {
						$(item).prop("selected", "true");
					}
				});

				$("input[name=searchValue]").val(searchValue);
			}

			if (searchGroup != "null") {
				$.each($("select[name=searchGroup] > option"), function(index,
						item) {
					if ($(item).val() == searchGroup) {
						$(item).attr("selected", "true")
					}
				});
			}
		});
		
		$(function() {
			$("#plannerTable td").click(function() {
					var plannerNo = $(this).parent().children().eq(0).text();
						location.href = "${contextPath}/admin/plannerDetail?no="
							+ plannerNo + "&currentPageNum=" + ${pInfom.currentPage};
							}).mouseenter(function() {
						$(this).parent().css("cursor", "pointer");
					});
		});
		
		
	</script>

	<jsp:include page="/WEB-INF/views/common/footer.jsp" />
</body>

</html>