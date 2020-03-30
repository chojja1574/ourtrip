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
        textarea {
            resize: none;
        }

        #boardContent{
            min-height: 350px;
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

    <!-- 공지사항 상세조회 시작 -->
    <div class="container my-5">
        <h2 class="font-weight-bold">공지사항</h2>
        <hr>
        <div class="container">

            <!-- 제목 -->
            <h4 class="my-3">제목 : ${notice.noticeTitle}</h4>
            <!-- 작성일 및 조회수 -->
            <div class="d-flex my-3">
                <div class="mr-auto">
                    <span>수정일 : ${notice.noticeModifyDT}</span>
                </div>
                <div class="ml-auto">
                    <span>조회수 : ${notice.noticeCount}</span>
                </div>
            </div>

            <hr>

            <!-- 내용 -->
            <div class="my-3" id="boardContent">
		    	${notice.noticeContent}
            </div>

            <hr>

            <!-- 버튼들 -->
            <div class="d-flex" id="btn-wrapper">
	            <a href="./updateForm?no=${notice.noticeNo}" class="btn gray-btn ml-auto">수정하기</a>
                <a href="#" class="btn gray-btn ml-auto" id="historyBack">이전페이지</a>
            </div>

        </div>
    </div>
    <!-- 공지사항 상세조회 끝 -->
	<jsp:include page="../common/footer.jsp" />
</body>
<script>
	$("#historyBack").click(function(){
		window.history.go(-1);
	})
</script>
</html>