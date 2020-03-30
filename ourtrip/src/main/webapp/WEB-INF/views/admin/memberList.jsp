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


	<div class="container my-5">
		<!-- 검색창 -->
		<div>
			<!-- 가운데정렬시 class="text-center" -->
			<form action="search" method="GET" class="text-center"
				id="searchForm" onsubmit="return checkValue();">
				<select name="searchKey" class="form-control"
					style="width: 100px; display: inline-block;">
					<option value="email">이메일</option>
					<option value="nickName">닉네임</option>
				</select> <input type="text" name="searchValue" id="searchValue"
					class="form-control" style="width: 25%; display: inline-block;">
				<button class="form-control btn main-btn"
					style="width: 100px; display: inline-block; margin-bottom: 5px;">검색</button>
			</form>
		</div>

		<h2>회원목록</h2>
		<table class="table table-hover" style="text-align: center;"
			id="memberTable">
			<thead class="thead-dark">
				<tr>
					<th>회원번호</th>
					<th>이메일</th>
					<th>닉네임</th>
					<th>가입일</th>
				</tr>
			</thead>
			<tbody>
				<c:if test="${empty memberList }">
					<tr>
						<td colspan="4" id="none-board">존재하는 회원이 없습니다.</td>
					</tr>
				</c:if>
				<c:if test="${!empty memberList }">
					<c:forEach var="memberList" items="${memberList}" varStatus="vs">
						<tr>
							<td>${memberList.memberNo}</td>
							<td>${memberList.memberEmail}</td>
							<td>${memberList.memberNickName}</td>
							<td>${memberList.memberEnrollDt}</td>
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
		                    	<c:url value="List"> 
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
		                    	<c:url value="List">
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
					<c:forEach var="p" begin="${pInfom.startPage}" end="${pInfom.endPage}">


						<c:if test="${p == pInfom.currentPage}">
							<li><a class="page-link">${p}</a></li>
						</c:if>

						<c:if test="${p != pInfom.currentPage}">
							<li><a class="page-link text-success"
								href=" 
			                    	<c:url value="List">
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
		                    	<c:url value="List">
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
		                    	<c:url value="List">
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
		<jsp:include page="/WEB-INF/views/common/footer.jsp" />
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
			$("#memberTable td")
					.click(
							function() {
								var memberNo = $(this).parent().children()
										.eq(0).text();
								location.href = "${contextPath}/admin/detail?no="
										+ memberNo ;
							}).mouseenter(function() {
						$(this).parent().css("cursor", "pointer");

					});

		});
	</script>

</body>
</html>