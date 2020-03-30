<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html lang="ko">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
        integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
    <link rel="stylesheet" href="${contextPath}/resources/css/common.css">
    <title>공지사항</title>

    <style>
        @media screen and (max-width: 500px) {
            table {
                font-size: 10px;
            }
        }
    </style>
</head>

<body>
    <script src="https://code.jquery.com/jquery-3.4.1.min.js"
        integrity="sha256-CSXorXvZcTkaix6Yvo6HppcZGetbYMGWSFlBw8HfCJo=" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
        integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo"
        crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"
        integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6"
        crossorigin="anonymous"></script>

	<jsp:include page="../common/header.jsp" />
	<jsp:include page="../common/nav.jsp" />

    <!-- 공지사항 목록 시작 -->
    <div class="container my-5">
        <h2 class="font-weight-bold">공지사항</h2>
        <hr>
        <table class="table table-hover" style="text-align: center;">
            <thead class="thead-dark">
                <tr>
                    <th>글번호</th>
                    <th>제목</th>
                    <th>조회수</th>
                    <th>날짜</th>
                </tr>
            </thead>
            <tbody>
           		<c:if test="${empty list}">
	                <tr>
	                    <td colspan="4" id="none-board">존재하는 게시글이 없습니다</td>
	                </tr>
	            </c:if>
	            <c:if test="${!empty list}">
	                <c:forEach var='notice' items="${list}" varStatus="status">
		                <tr>
		                    <td>${notice.noticeNo}</td>
		                    <td><a href="./detail?no=${notice.noticeNo}" class="btn">${notice.noticeTitle}</a></td>
		                    <td>${notice.noticeCount}</td>
		                    <td>${notice.noticeModifyDT}</td>
		                    
		                </tr>
	                </c:forEach>
                </c:if>
            </tbody>
        </table>
        
        <!-- Spring project 페이징 바 -->
        <div class="row">
        	<div class="col-md-3"></div>
	        <div style="clear: both;" class="mx-auto">
	            <ul class="pagination mx-auto">
	            	<c:if test="${pInf.currentPage > 1}">
		                <li>
		                	<!-- 맨 처음으로(<<) -->
		                    <a class="page-link text-success" 
		                    	href=" 
		                    	<c:url value="list">
		                    		<c:if test="${!empty param.searchKey }">
						        		<c:param name="searchKey" value="${param.searchKey}"/>
						        		<c:param name="searchValue" value="${param.searchValue}"/>
						        	</c:if>
		                    		<c:param name="currentPage" value="1"/>
		                    	</c:url>
	                    	">
			                    &lt;&lt;
			                </a>
		                </li>
		                
		                <li>
		                	<!-- 이전으로(<) -->
	                   		<a class="page-link text-success" 
		                    	href=" 
		                    	<c:url value="list">
		                    		<c:if test="${!empty param.searchKey }">
						        		<c:param name="searchKey" value="${param.searchKey}"/>
						        		<c:param name="searchValue" value="${param.searchValue}"/>
						        	</c:if>
		                    		<c:param name="currentPage" value="${pInf.currentPage-1}"/>
		                    	</c:url>
	                    	">
			                    &lt;
			                </a>
		                </li>
	                </c:if>
	                
	                <!-- 10개의 페이지 목록 -->
	                <c:forEach var="p" begin="${pInf.startPage}" end="${pInf.endPage}">
	                
	                
	                	<c:if test="${p == pInf.currentPage}">
			                <li>
			                    <a class="page-link">${p}</a>
			                </li>
		                </c:if>
	                	
	                	<c:if test="${p != pInf.currentPage}">
	                		<li>
		                    	<a class="page-link text-success" 
			                    	href=" 
			                    	<c:url value="list">
			                    		<c:if test="${!empty param.searchKey }">
							        		<c:param name="searchKey" value="${param.searchKey}"/>
							        		<c:param name="searchValue" value="${param.searchValue}"/>
							        	</c:if>
			                    		<c:param name="currentPage" value="${p}"/>
			                    	</c:url>
		                    	">
				                    ${p}
				                </a>
		                	</li>
	                	</c:if>
	                	
                	</c:forEach>
		                
	                <!-- 다음 페이지로(>) -->
	                <c:if test="${pInf.currentPage < pInf.maxPage }">
		                <li>
							<a class="page-link text-success" 
		                    	href=" 
		                    	<c:url value="list">
		                    		<c:if test="${!empty param.searchKey }">
						        		<c:param name="searchKey" value="${param.searchKey}"/>
						        		<c:param name="searchValue" value="${param.searchValue}"/>
						        	</c:if>
		                    		<c:param name="currentPage" value="${pInf.currentPage+1}"/>
		                    	</c:url>
	                    	">
			                    &gt;
			                </a>
		                </li>
		                
		                <!-- 맨 끝으로(>>) -->
		                <li>
		                    <a class="page-link text-success" 
		                    	href=" 
		                    	<c:url value="list">
		                    		<c:if test="${!empty param.searchKey }">
						        		<c:param name="searchKey" value="${param.searchKey}"/>
						        		<c:param name="searchValue" value="${param.searchValue}"/>
						        	</c:if>
		                    		<c:param name="currentPage" value="${pInf.maxPage}"/>
		                    	</c:url>
	                    	">
			                    &gt;&gt;
			                </a>
		                </li>
	                </c:if>
	            </ul>
        	</div>
        	<div class="col-md-3">
        		<a href="./insertForm" class="form-control btn btn-success" style="width:150px">공지사항 작성</a>
        	</div>
       	</div>
       	<div class="row">
	        <div class="col-md-12 mx-auto">
	            <form action="list" method="GET" class="text-center" id="searchForm" style="margin-bottom:100px;">
	                <br>
	                <select name="searchKey" class="form-control" style="width:100px; display: inline-block;">
	                    <option value="title">글제목</option>
	                    <option value="content">내용</option>
	                    <option value="titcont">제목+내용</option>
	                </select>
	                <input type="text" name="searchValue" class="form-control" style="width:25%; display: inline-block;">
	                <button class="form-control btn btn-success" style="width:100px; display: inline-block;">검색</button>
	            </form>
	            
	           	<script>
					$(function(){
						var searchKey = "${param.searchKey}";
						var searchValue = "${param.searchValue}";
						
						if(searchKey != "null" && searchValue != "null"){
							$.each($("select[name=searchKey] > option"), function(index, item){
								if($(item).val() == searchKey){
									$(item).prop("selected","true");
								} 
							});
							$("input[name=searchValue]").val(searchValue);
						}
					});
					
					// script 태그 내에서도 JSTL 사용 가능
					// 서버 동작 시 코드를 읽어 나가는 우선 순서 
					// JAVA > EL/JSTL > HTML > Javascript 
					// -> EL/JSTL이 먼저 완성 되어있을 때 javascript와 혼용해서 사용해도 문제 없음.
					<c:forEach var="ct" items="${paramValues.searchCategory}" varStatus="vs">
						$.each($("input[name=searchCategory]"), function(index, item){
							console.log(1);
							if($(item).val() == "${ct}"){
								$(item).prop("checked","true");
							} 
						});
						
					</c:forEach>
				</script>
	            
	        </div>
    	</div>
    </div>
    <!-- 공지사항 목록 끝 -->
    
    <jsp:include page="../common/footer.jsp" />
    
</body>

</html>