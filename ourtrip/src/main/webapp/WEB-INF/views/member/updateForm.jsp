<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
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

<link rel="stylesheet" href="../resources/css/common.css">

<title>회원정보 수정</title>

<style>
	input[type="file"] {
		/* 파일 필드 숨기기 */
		position: absolute;
		width: 1px;
		height: 1px;
		padding: 0;
		margin: -1px;
		overflow: hidden;
		clip: rect(0, 0, 0, 0);
		border: 0;
	}
	
	#update-wrapper{
		position: relative;
		z-index:1;
	}
	
 	#update-wrapper:after{
		background-image: url(../resources/images/update-background.jpg);
		background-repeat: no-repeat;
		background-size: cover;
		opacity: 0.6!important;
		top:0;
		left:0;
		position:absolute;
		z-index:-1;
		content:'';
		width:100%;
		height:100%;
	}
	
	#update-container{
		background-color: rgba(255,255,255,0.6);
  		border-radius: 10px;
	}

</style>
</head>
<body>
	<jsp:include page="../common/header.jsp" />
	<jsp:include page="../common/nav.jsp" />

	<div class="py-5" id="update-wrapper">
		<div class="container py-3" id="update-container">
			<h1 style="text-align: center;">회원정보 수정</h1>
			<hr>
			<form method="POST" action="update" enctype="multipart/form-data"
				role="form" onsubmit="return validate();">
				<h4 class="mt-5">프로필 사진 수정</h4>
				<div class="d-flex" id="profileImgArea">
					<!-- 회원의 프로필이 있으면 보여주고 없으면 디폴트 이미지를 보여줌 -->
					<c:if test="${!empty profileImage}">
					
						<!-- 카카오에서 가져온 이미지 경로일 경우 -->
						<c:if test="${fn:contains(profileImage.imagePath, 'http://')}">
							<c:set var="filePath" value="${profileImage.imagePath}"/>
						</c:if>
					
						<!-- ourtrip서버에 있는 경로일 경우 -->
						<c:if test="${!fn:contains(profileImage.imagePath, 'http://')}">
							<c:set var="fileName" value="${fn:split(profileImage.imagePath, '/')}"/>
							<c:set var="filePath" value="${contextPath}/resources/profileImages/${fileName[fn:length(fileName) - 1]}"/>
						</c:if>
					</c:if>
					
					<img id="ot_profile_image" alt="your image" class="rounded mx-auto"
						width="200" height="200"
						src=<c:if test="${!empty profileImage}">
								"${filePath}"
							</c:if>
								<c:if test="${empty profileImage}">
								"../resources/images/default-profile.png"
							</c:if>>
				</div>
				<div class="mt-3 d-flex">
					<div class="mx-auto">
						<button type="button" id="btn-upload" class="btn main-btn">사진
							등록</button>
						<input type="file" id="ot-input-profileImg" name="profileImage"
							onchange="LoadImg(this,1)" accept="image/png, image/jpeg">
						<button type="button" class="btn gray-btn" onclick="DefaultImg();">등록
							취소</button>
					</div>
				</div>
				<hr>
				<h4 class="mt-5">회원 정보 수정</h4>
				<div class="mt-4 d-flex">
					<div class="mx-auto col-md-6">
						<div class="row mt-3">
							<div class="col-md-3">
								<label>Email</label>
							</div>
							<div class="col-md-9">
								${loginMember.memberEmail}
							</div>
						</div>
						<div class="row mt-3">
							<div class="col-md-3">
								<label>비밀번호</label>
							</div>
							<div class="col-md-9">
								<a href="changePwdForm" class="btn gray-btn btn-block">비밀번호 변경하기</a>
							</div>
						</div>
						<div class="row mt-3">
							<div class="col-md-3">
								<label for="nickName">닉네임</label>
							</div>
							<div class="col-md-9">
								<input type="text" class="form-control" id="nickName" name="memberNickName"
									value="${loginMember.memberNickName }">
							</div>
						</div>
					</div>
				</div>
				<div class="row mt-5 mb-3">
					<div class="col-md-4">
						<a href="secessionForm" class="btn del-btn btn-block">회원 탈퇴</a>
					</div>
					<div class="col-md-4">
						<a href="#" class="btn gray-btn btn-block">이전으로</a>
					</div>
					<div class="col-md-4">
						<button class="btn main-btn btn-block">수정 완료</button>
					</div>
				</div>
				<input type="hidden" id="isDefault" name="isDefault" value="false">
			</form>
		</div>
	</div>

	<jsp:include page="../common/footer.jsp" />

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

	<script>
		var defaultImg = "../resources/images/default-profile.png";
		// 각각의 영역에 파일을 첨부 했을 경우 미리 보기가 가능하도록 하는 함수
		function LoadImg(value, num) {
			console.log("defaultImg : " + defaultImg);
			// multiple 속성을 사용하면 사진이 배열의 형태로 올라옴
			// 파일 업로드 시 업로드 된 파일의 경로는 files라는 배열이 생성되면 저장됨 
			// files라는 배열이 있고 value.files[0]에 값이 있는지 확인
			// : 파일이 선택된 경우를 말함
			if (value.files && value.files[0] && num != 2) {
				var reader = new FileReader();
				// 파일을 읽어들임

				reader.onload = function(e) {

					$("#ot_profile_image").prop("src", e.target.result);
					console.log(e.target.result);
					// e.target.result -> reader.onload한 이벤트 즉 파일 경로가 추가됨

				}
				// file에서 내용(content)을 읽어옴
				// +base64 인코딩된 경로를 반환
				reader.readAsDataURL(value.files[0]);
			} else {
				$("#ot_profile_image").prop("src", defaultImg);
			}
		}

		function DefaultImg() {
			LoadImg($("#ot-input-profileImg"), 2)
			$("#isDefault").val("true");
		}

		$(function() {
			$('#btn-upload').click(function(e) {

				e.preventDefault();
				$('#ot-input-profileImg').click();
			});
		});

		function changeValue(obj) {
			alert(obj.value);
		}

		// 닉네임 유효성 검사 할 변수
		// input태그에 회원의 닉네임으로 값을 초기화시키므로
		// 초기값을 true로 설정
		var nickCheck = true;
		
		$(function() {
			$("#email-authentication-div").hide();
			$("#email-authentication-btn").click(function(e) {
				$("#email-authentication-div").show();
			})

			// 닉네임 유효성 검사
			$("#nickName").on("input", function() {
				var regExp = /^[가-힣a-zA-Z0-9]{4,12}$/; // 한글,영문,숫자 4~12글자

				if (!regExp.test($(this).val())) { // 이름이 정규식을 만족하지 않을경우

					nickCheck = false;
				} else {

					nickCheck = true;
				}
			});
		});
		
		function validate(){
			if(!nickCheck){
				alert("올바르지 않은 닉네임입니다.\n한글, 영문, 숫자 4~12글자로 입력해주세요.");
				return false;
			}
		}
	</script>

</body>
</html>