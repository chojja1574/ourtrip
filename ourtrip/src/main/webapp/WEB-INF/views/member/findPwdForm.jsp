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
<title>비밀번호 찾기</title>

<style>
#findPwd-wrapper{
	width: 100%;
	height: 80%;
	position: relative;
	z-index:1;
}

#findPwd-wrapper:after{
	background-image: url(../resources/images/findpwd-background.jpg);
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

#findPwd-container{
	height: 100%;
}

#findPwd-form{
	background-color: rgba(255,255,255,0.6);
 	border-radius: 10px;
 	padding: 15px;
}

.display-none{
    display:none;
}

@media screen and (max-width: 1199px) {
    #email-authentication-btn{
    	font-size: 14px;
    }
}

@media screen and (max-width: 767px) {
    #email-authentication-btn{
    	font-size: 16px;
    }
}
</style>

</head>
<body>
	<jsp:include page="../common/header.jsp" />
	<jsp:include page="../common/nav.jsp" />
	
	<div id="findPwd-wrapper">
		<div class="container py-5 d-flex" id="findPwd-container">
			<div class="col-md-8 m-auto">
				<form action="findPwd" method="POST" onsubmit="return validate();" id="findPwd-form">
					<h3 style="text-align: center;">비밀번호 찾기</h3>
					<div class="row mt-5">
						<div class="col-md-3">
							<label for="email">이메일</label>
						</div>
						<div class="col-md-6">
							<input type="email" class="form-control" name="memberEmail" id="email" placeholder="outrip@example.com">
							<span id="checkEmail"></span>
						</div>
						<div class="col-md-3">
							<button class="btn main-btn btn-block" id="email-authentication-btn" type="button">이메일 확인</button>
						</div>
					</div>
					<div class="row mt-3" id="email-authentication-div">
						<div class="col-md-3">
							<label for="certifyCode">인증번호 입력</label>
						</div>
						<div class="col-md-6">
							<input type="text" class="form-control" id="certifyCode">
						</div>
					</div>
					<div class="row mt-5 mb-3">
						<div class="col-sm-6">
							<a href="${detailUrl}" class="btn gray-btn btn-block">이전 페이지</a>
						</div>
						<div class="col-sm-6">
							<button class="btn main-btn btn-block">비밀번호 찾기</button>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>
	
	<div class="wrap-loading display-none" id="loading">
        <div><img src="${contextPath}/resources/images/loadingBar.gif"/></div>
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
		var email = false;
		var certifyCode;
		$(function(){
			$email = $("#email");
			
			// 이메일 확인 및 전송
			$("#email-authentication-btn").on("click", function(){
				email = false;
				
		    	if($(this).text() == "취소"){
		    		$email.prop("readonly", false).val("").focus();
					$("#email-authentication-btn").text("이메일 확인").removeClass("del-btn")
						.addClass("main-btn");
					
					$("#checkEmail").html("이메일을 다시 입력해주세요.")
						.css("color", "red");
		    		
		    	}else{
		    		var regExp = /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i;
		    		if(!regExp.test($email.val())){
		    			$("#checkEmail").html("이메일 형식이 유효하지 않습니다.")
		    			.css("color", "red");
		    		}else{
		    			$("#checkEmail").html("<span class='spinner-border spinner-border-sm'></span> 이메일을 확인하고 있습니다.")
		    			.css("color", "gray");
		    			
		    			$.ajax({
		    				url: "signUpedEmail",
		    				type: "POST",
		    				data: {email: $email.val()},
		    				success: function(result){
		    					
		    					if(result == "0"){
		    						$("#checkEmail").html("가입되지 않은 이메일입니다.")
		    						.css("color", "red");
		    					}else{
		    						$("#checkEmail").html("이메일로 전송된 코드를 확인해주세요.")
		    						.css("color", "green");
		    						
		    						$email.prop("readonly", true);
		    						$("#email-authentication-btn").text("취소").removeClass("main-btn")
		    						.addClass("del-btn");
		    						
		    						email = true;
		    						
		    						console.log(result);
		    						certifyCode = result;
		    					}
		    				},
		    				error: function(e){
		    					console.log("email 인증 ajax 실패");
		    					console.log(e);
		    				}
		    			});
		    		}
		    	}
		    });
		});
		
		function validate(){
			if(certifyCode != $("#certifyCode").val()){
				alert("인증번호가 일치하지않습니다.");
				return false;
			}
			$("#loading").removeClass("display-none");
		}
	</script>
</body>
</html>