<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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

<title>회원가입</title>

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
	
	#signUp-wrapper{
		position: relative;
		z-index:1;
	}
	
 	#signUp-wrapper:after{
		background-image: url(../resources/images/signup-background.jpg);
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
	
	#signUp-container{
		background-color: rgba(255,255,255,0.6);
  		border-radius: 10px;
	}
	
</style>
</head>
<body>
	<jsp:include page="../common/header.jsp" />
	<jsp:include page="../common/nav.jsp" />
	
	<div class="py-5" id="signUp-wrapper">
		<div class="container py-3" id="signUp-container">
			<h1 style="text-align: center;">회원가입</h1>
			<hr>
			<form action="signUp" method="POST" onsubmit="return validate();">
				<h4 class="mt-5">회원 정보 입력(필수사항)</h4>
				<div class="row mt-5">
					<div class="col-md-3">
						<label for="email">이메일</label>
					</div>
					<div class="col-md-6">
						<input type="email" class="form-control" name="memberEmail" id="email" placeholder="outrip@example.com">
						<span id="checkEmail"></span>
					</div>
					<div class="col-md-2">
						<button class="btn main-btn btn-block" id="email-authentication-btn" type="button">메일 인증</button>
					</div>
				</div>
				<div class="row mt-3" id="email-authentication-div">
					<div class="col-md-3">
						<label for="memberEmail">인증번호 입력</label>
					</div>
					<div class="col-md-6">
						<input type="text" class="form-control" id="certifyCode">
					</div>
				</div>
				<div class="row mt-3">
					<div class="col-md-3">
						<label for="pwd1">비밀번호</label>
					</div>
					<div class="col-md-6">
						<input type="password" class="form-control" name="memberPwd" id="pwd1" placeholder="password">
						<span id="checkPwd1"></span>
					</div>
				</div>
				<div class="row mt-3">
					<div class="col-md-3">
						<label for="pwd2">비밀번호 확인</label>
					</div>
					<div class="col-md-6">
						<input type="password" class="form-control" id="pwd2" placeholder="password check">
						<span id="checkPwd2"></span>
					</div>
				</div>
				<div class="row my-3">
					<div class="col-md-3">
						<label for="nickname">닉네임</label>
					</div>
					<div class="col-md-6">
						<input type="text" class="form-control" name="memberNickName" id="nickname" placeholder="nickname">
						<span id="checkNick"></span>
					</div>
				</div>
				<hr>
				
				<h4 class="mt-5">프로필 사진 (선택사항)</h4>
				<div class="d-flex" id="profileImgArea">
					<img id="ot_profile_image" alt="your image" class="rounded mx-auto" width="220"
						height="220" src="../resources/images/default-profile.png">
				</div>
				<div class="mt-3 d-flex">
					<div class="mx-auto">
						<button type="button" id="btn-upload"
						class="btn main-btn">사진 등록</button>
						<input type="file" id="ot-input-profileImg" name="profileImage"
							onchange="LoadImg(this,1)" accept="image/png, image/jpeg" alt=""
							src="profile.png">
						<button class="btn gray-btn" onclick="DefaultImg();">등록 취소</button>
					</div>
				</div>
				<hr>
				<div class="row my-3">
					<div class="col-md-6">
						<a href="#" class="btn btn-lg gray-btn btn-block">비밀번호 찾기</a>
					</div>
					<div class="col-md-6">
						<button class="btn btn-lg main-btn btn-block">회원 가입</button>
					</div>
				</div>
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
		
	<script type="text/javascript" src="../resources/js/signUp.js"></script>
</body>
</html>