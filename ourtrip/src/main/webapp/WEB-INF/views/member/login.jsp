<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<meta charset="UTF-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">

<!-- Bootstrap CSS -->
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
	integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh"
	crossorigin="anonymous">

<link rel="stylesheet" href="${contextPath}/resources/css/common.css">

<!-- google api -->
<meta name="google-signin-scope" content="profile email">
<meta name="google-signin-client_id"
	content="223910214928-m851p2ba376ai836vrqm7265aabj8jsi.apps.googleusercontent.com">
<script src="https://apis.google.com/js/platform.js?onload=init" async
	defer></script>
<script src="https://apis.google.com/js/api:client.js"></script>
<link href="https://fonts.googleapis.com/css?family=Roboto"
	rel="stylesheet" type="text/css">
<script>
    var googleUser = {};
    var startApp = function () {
        gapi.load('auth2', function () {
            // Retrieve the singleton for the GoogleAuth library and set up the client.
            auth2 = gapi.auth2.init({
                client_id: '223910214928-m851p2ba376ai836vrqm7265aabj8jsi.apps.googleusercontent.com',
                cookiepolicy: 'single_host_origin',
                // Request scopes in addition to 'profile' and 'email'
                //scope: 'additional_scope'
            });
            attachSignin(document.getElementById('customBtn'));
        });
    };

    function attachSignin(element) {
        console.log(element.id);
        auth2.attachClickHandler(element, {},
            function (googleUser) {

                // 콘솔에 로그인 된 프로필 찍어주는 부분
                console.log(googleUser.getBasicProfile());
            }, function (error) {
                alert(JSON.stringify(error, undefined, 2));
            }
        );
    }
</script>

<!-- kakao api -->
<script src="//developers.kakao.com/sdk/js/kakao.min.js"></script>

<title>login</title>

<style>
.ot-secessionTerms {
	resize: none;
	width: 100%;
	height: 600px;
	margin: 20px 0px 20px 0px;
}

.ot-termsAgree {
	width: 15px;
	height: 15px;
}

.ot-termsAgreeLabel {
	line-height: 12px;
	padding-left: 5px;
	vertical-align: middle;
}

.ot-inputTextSetting {
	font-weight: bold;
	font-size: 1.2em;
}

.ot-inputTextSetting h1 {
	text-align: center;
}

.g-signin2 {
	width: 100%;
}

#customBtn {
	display: inline-block;
	text-align: center;
	width: 100%;
	height: 40px;
	line-height: 40px;
	border: #4285F4 solid 2px;
	background-color: #ffffff;
	color: #4285F4;
	font-weight: 2em;
	border-radius: 3px;
	display: block;
	margin-bottom: 40px;
	text-decoration: none;
	cursor: pointer;
}

#customBtn:hover {
	text-align: center;
	width: 100%;
	height: 40px;
	line-height: 40px;
	border: #4285F4 solid 2px;
	background-color: #4285F4;
	color: #ffffff;
	font-weight: 2em;
	border-radius: 3px;
	display: block;
	margin-bottom: 40px;
	text-decoration: none;
	cursor: pointer;
}

span.label {
	font-family: serif;
	font-weight: normal;
}

span.icon {
	background: url('GoogleLogo.PNG') transparent 5px 50% no-repeat;
	display: inline-block;
	vertical-align: middle;
	width: 30px;
	height: 20px;
}

span.buttonText {
	display: inline-block;
	vertical-align: middle;
	padding-left: 5px;
	padding-right: 10px;
	font-size: 1em;
	font-weight: bold;
	/* Use the Roboto font that is loaded in the <head> */
	font-family: 'Roboto', sans-serif;
}

.form-login {
  width: 100%;
  max-width: 330px;
  padding: 15px;
  margin: auto;
}
.form-login .checkbox {
  font-weight: 400;
}
.form-login .form-control {
  position: relative;
  box-sizing: border-box;
  height: auto;
  padding: 10px;
  font-size: 16px;
}

/* 버튼 */
.kakao-btn {
    background-color: #f7e317;
    color: #3c1e1e;
}

.kakao-btn:hover {
    background-color: rgb(245, 235, 132);
    color: #3c1e1ee;
}
</style>

</head>
<body>
	<jsp:include page="../common/header.jsp" />
	<jsp:include page="../common/nav.jsp" />
	
	<div class="container my-5">
		<h1 class="h3 mb-3 font-weight-normal" style="text-align: center;">Login</h1>
		<form class="form-login" action="login" method="POST">
			<label for="email">이메일</label>
			<input class="form-control mb-2" type="email" name="memberEmail" id="email"
				placeholder="이메일을 입력해주세요" required autofocus>
			<label for="email">비밀번호</label>
			<input class="form-control mb-2" type="password" name="memberPwd" id="pwd"
				placeholder="비밀번호를 입력해주세요" required autofocus>
			<div class="checkbox mb-3">
				<label>
				<input type="checkbox" value="remember-me">
					아이디 저장
				</label>
			</div>
			<button class="btn btn-lg main-btn btn-block" onclick="return validate();">로그인</button>
			<div class="col-md-12" style="text-align: center;"
				id="google-btn-div">
				<div class="g-signin2" data-onsuccess="onSignIn" data-width="100" data-height="40" data-longtitle="true"></div>
				<div id="gSignInWrapper">
					<span class="label"></span>
					<div id="customBtn" class="customGPlusSignIn">
						<span class="icon"></span>
						<span class="buttonText">구글	로그인</span>
					</div>
				</div>
				<script>startApp();</script>
			</div>
			<a id="custom-login-btn" class="btn btn-lg kakao-btn btn-block" href="javascript:loginWithKakao()">카카오 계정으로 로그인</a>
		</form>
	</div>

	<!-- <div class="container-fluid my-3">
		<div class="row">
			<div class="col-md-12 ot-inputTextSetting">
				<div class="row">
					<div class="col-md-4"></div>
					<div class="col-md-4" style="margin-top: 40px;">
						<div class="row mb-5">
							<h1 style="text-align: center; width: 100%;">로그인</h1>
						</div>
						<hr>
						<form action="#" method="POST">
							<div class="row mt-5 mb-4">
								<div class="col-md-4">이메일</div>
								<div class="col-md-8">
									<input type="text" name="email" id="email" style="width: 100%;">
								</div>
							</div>
							<div class="row mt-4 mb-5">
								<div class="col-md-4">비밀번호</div>
								<div class="col-md-8">
									<input type="password" name="pwd" id="pwd" style="width: 100%;">
								</div>
							</div>
							<div class="row">
								<input type="checkbox" name="save" id="save" checked> <label
									for="save" style="line-height: 10px;">&nbsp;&nbsp;아이디
									저장</label>
							</div>
							<hr>
							<div class="row mt-5" style="height: 40px">
								<div class="col-md-12" style="text-align: center;">
									<button class="ot-btn1" onclick="return validate();">로그인</button>
								</div>
							</div>
						</form>
						<div class="row mt-3" style="height: 40px">
							<div class="col-md-12" style="text-align: center;"
								id="google-btn-div">
								<div class="g-signin2" data-onsuccess="onSignIn" data-width="100" data-height="40" data-longtitle="true"></div>
								<div id="gSignInWrapper">
									<span class="label"></span>
									<div id="customBtn" class="customGPlusSignIn">
										<span class="icon"></span> <span class="buttonText">구글
											로그인</span>
									</div>
								</div>
								<script>startApp();</script>
							</div>
						</div>
						<div class="row mt-3" style="height: 40px">
							<div class="col-md-12" style="text-align: center;">
								<a id="custom-login-btn" class="ot-btn-kakao"
									href="javascript:loginWithKakao()"> <img
									src="KakaoLogo.PNG">&nbsp;카카오 로그인
								</a>
							</div>
						</div>
						<div class="row mt-3" style="height: 40px">
							<div class="col-md-6" style="text-align: center;">
								<button type="button" class="ot-btn2" onclick="changePwd();">비밀번호
									찾기</button>
							</div>
							<div class="col-md-6" style="text-align: center;">
								<button type="button" class="ot-btn2" onclick="signUpForm();">회원
									가입</button>
							</div>
						</div>
					</div>
					<div class="col-md-4">
						<div id="my-signin2"></div>
					</div>
				</div>
			</div>
		</div>
	</div> -->

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

	<script type='text/javascript'>
	    // 카카오 로그인
	    //<![CDATA[
	    // 사용할 앱의 JavaScript 키를 설정해 주세요.
	    Kakao.init('9c1bfb8346b701102641547c85accb43');
	    
	    function loginWithKakao() {
	        // 로그인 창을 띄웁니다.
	        Kakao.Auth.login({
	            success: function (authObj) {
	                alert(JSON.stringify(authObj));
	            },
	            fail: function (err) {
	                alert(JSON.stringify(err));
	            }
	        });
	    };
	    //]]>
	
	    var idCheck = false;
	    var pwdCheck = false;
	    $(function(){
	        $("#pwd").on("input", function(){
	            if($("#pwd").val() != ""){
	                pwdCheck = true;
	            }
	        });
	        $("#email").on("input", function(){
	            if($("#email").val() != ""){
	                idCheck = true;
	            }
	        });
	    })
	
	    function validate(){
	        console.log(idCheck + "&&" + pwdCheck)
	        if(!(idCheck&&pwdCheck)){
	            alert("일부 입력값이 잘못되었습니다.");
	            return false;
	        }
	    }
	    
	    // 회원가입 페이지로 이동
	    function signUpForm(){ location.href="signUpForm.html"; }
	    // 비밀번호 찾기 페이지로 이동
	    function changePwd(){ location.href="changePwd.html"; }
	
	</script>
</body>
</html>