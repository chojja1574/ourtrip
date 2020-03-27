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

<title>비밀번호 변경</title>

<style>
#changePwd-wrapper{
	width: 100%;
	height: 80%;
	position: relative;
	z-index:1;
}

#changePwd-container{
	height: 100%;
}

#changePwd-wrapper:after{
	background-image: url(../resources/images/changepwd-background.jpg);
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

#changePwd-form{
	background-color: rgba(255,255,255,0.6);
 	border-radius: 10px;
 	padding: 15px;
}
</style>

</head>
<body>
	<jsp:include page="../common/header.jsp" />
	<jsp:include page="../common/nav.jsp" />
	
	<div id="changePwd-wrapper">
		<div class="container py-5 d-flex" id="changePwd-container">
			<div class="col-md-10 m-auto">
				<form method="POST" action="changePwd" onsubmit="return validate();"
						id="changePwd-form">
					<h3 style="text-align: center;">비밀번호 수정</h3>
					<hr>
					<div class="mt-4 d-flex">
						<div class="mx-auto col-md-10">
							<div class="row mt-3">
								<div class="col-md-3">
									<label for="currPwd">현재 비밀번호</label>
								</div>
								<div class="col-md-9">
									<input class="form-control" type="password" name="memberPwd" id="currPwd">
								</div>
							</div>
							<div class="row mt-3">
								<div class="col-md-3">
									<label>새 비밀번호</label>
								</div>
								<div class="col-md-9">
									<input class="form-control" type="password" name="changePwd" id="pwd1">
									<span id="checkPwd1"></span>
								</div>
							</div>
							<div class="row mt-3">
								<div class="col-md-3">
									<label for="nickName">새 비밀번호 확인</label>
								</div>
								<div class="col-md-9">
									<input class="form-control" type="password" id="pwd2">
		                               <span id="checkPwd2"></span>
								</div>
							</div>
						</div>
					</div>
					<div class="row mt-5 mb-3">
						<div class="col-md-6">
							<a href="updateForm" class="btn gray-btn btn-block">이전으로</a>
						</div>
						<div class="col-md-6">
							<button class="btn main-btn btn-block">수정 완료</button>
						</div>
					</div>
				</form>
			</div>
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
    // 각 유효성 검사 결과를 저장할 객체
    var signUpCheck = { 
	    "pwd1":false,
	    "pwd2":false,
    };
    
    // 실시간 입력 형식 검사
    // 정규표현식
    $(document).ready(function(){
        
        // jQuery 변수 : 변수에 직접적으로 jQuery메소드를 사용할 수 있음.
        var $pwd1 = $("#pwd1");
        var $pwd2 = $("#pwd2");
        
        // 비밀번호  유효성 검사
        $pwd1.on("input", function(){
            //영어 대,소문자 + 숫자, 총 6~12글자
            var regExp = /^[A-Za-z0-9]{6,12}$/;
            if(!regExp.test($pwd1.val())){ 
                $("#checkPwd1").text("영어 대,소문자 + 숫자, 총 6~12글자를 입력해주세요").css("color","red");
                signUpCheck.pwd1 = false;
            }else{
                $("#checkPwd1").text("유효한 비밀번호 형식입니다.").css("color","green");
                signUpCheck.pwd1 = true;
            }
        });
        
        
        // 비밀번호 일치 여부
        $pwd2.on("input", function(){
            if($pwd1.val().trim() != $pwd2.val().trim()){
                $("#checkPwd2").text("비밀번호 불일치").css("color","red");
                signUpCheck.pwd2 = false;
            }else{
                $("#checkPwd2").text("비밀번호 일치").css("color","green");
                signUpCheck.pwd2 = true;
              
            }
        });
    });
    
    // submit 동작
    function validate(){
        for(var key in signUpCheck){
            if(!signUpCheck[key]){
                alert("일부 입력값이 잘못되었습니다.");
                console.log(key);
                var id = "#"+key;
                $(id).focus();
                return false;
            }
        }
    }
    </script>

</body>
</html>