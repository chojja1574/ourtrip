<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>

<meta charset="UTF-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">

<!-- Bootstrap CSS -->
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
	integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh"
	crossorigin="anonymous">

<link rel="stylesheet" href="../resources/css/common.css">

<!-- kakao api -->
<script src="//developers.kakao.com/sdk/js/kakao.min.js"></script>

<title>login</title>

<style>
#form-wrapper{
	width: 100%;
	height: 80%;
	position: relative;
	z-index:1;
}
#form-wrapper:after{
	background-image: url(../resources/images/login-background.jpg);
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

.form-login {
  background-color: rgba(255,255,255,0.6);
  border-radius: 10px;
  width: 100%;
  max-width: 330px;
  padding: 15px;
  position: absolute;
  left: 50%;
  top: 50%;
  margin-left: -165px;
  margin-top: -200px;
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

.wrap-loading{
    position: fixed;
    left:0;
    right:0;
    top:0;
    bottom:0;
    /* not in ie */
    background:  rgba(0,0,0,0.2); 
    /* ie */
    filter: progid:DXImageTransform.Microsoft.Gradient(startColorstr='#20000000',endColorstr='#20000000');
}

.wrap-loading div{ /*로딩이미지*/
    position: fixed;
    top:50%;
    left:50%;
    margin-left: -21px;
    margin-top: -21px;
}

.display-none{
    display:none;
}

#find-pwd{
	color: black;
}

#find-pwd:hover{
	color: blue;
}
</style>

</head>
<body>
	<jsp:include page="../common/header.jsp" />
	<jsp:include page="../common/nav.jsp" />
	
	<div id="form-wrapper">
		<form class="form-login" action="login" method="POST">
			<h3 class="mb-3 font-weight-normal" style="text-align: center;">Login</h3>
			<label for="email">이메일</label>
			<input class="form-control mb-2" type="email" name="memberEmail" id="email" <c:if test="${!empty saveEmail}">value="${saveEmail}"</c:if>
				placeholder="ourtrip@example.com" required autofocus>
			<label for="email">비밀번호</label>
			<input class="form-control mb-2" type="password" name="memberPwd" id="pwd"
				placeholder="비밀번호를 입력해주세요" required autofocus>
			<div class="checkbox mb-3">
				<input type="checkbox" name="saveEmail" id="saveEmail" <c:if test="${!empty saveEmail}">checked</c:if> >
				<label for="saveEmail">&nbsp;아이디 저장</label>
				<a class="float-right" href="findPwdForm" id="find-pwd">비밀번호 찾기</a>
			</div>
			<button class="btn btn-lg main-btn btn-block" onclick="return validate();">로그인</button>
			<a id="custom-login-btn" class="btn btn-lg kakao-btn btn-block" href="javascript:loginWithKakao()">카카오 계정으로 로그인</a>
		</form>
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

	<script type='text/javascript'>
	    // 카카오 로그인
	    //<![CDATA[
	    // 사용할 앱의 JavaScript 키를 설정해 주세요.
	    Kakao.init('3265d67cbccb2a931046b989ef45ad5f');
	    
        var nickname;
        var profile_url;
        var email;
        var id;
	    
	    function loginWithKakao() {
	        // 로그인 창을 띄웁니다.
	        Kakao.Auth.login({
	            success: function (authObj) {

	                Kakao.API.request({
	                    // 로그인한 회원의 정보 요청
	                    url: '/v2/user/me',
	                    success: function (res) {
	                        console.log(res);
	                        nickname = res.kakao_account.profile.nickname;
	                        profile_url = res.kakao_account.profile.profile_image_url;
	                        email = res.kakao_account.email;
	                        id = res.id;
	                        
	                        $.ajax({
	                        	url : "kakaoLogin",
	                        	type : "POST",
	                        	data : {memberEmail : email,
	                        			memberPwd : id,
	                        			memberNickName : nickname,
	                        			imagePath : profile_url},
	                        	beforeSend : function(){
	                        		$("#loading").removeClass("display-none");
	                        	},
	                        	success : function(result){
	                        		if(result == "success"){
	                        			location.href = "${contextPath}";
	                        		}else{
	                        			location.href = "loginForm";
	                        		}
	                        	},
	                        	error : function(e){
	                        		alert("카카오 로그인에 실패하였습니다.\n" + e);
	                        	},
	                        	complete : function(){
	                        		$("#loading").addClass("display-none");
	                        	}
	                        });
	                        
	                    },
	                    fail: function (error) {
	                        alert("카카오 로그인에 실패하였습니다.");
	                    }
	                });
	            },
	            fail: function (err) {
	            	alert("카카오 로그인에 실패하였습니다.");
	            }
	        });
	    };
	    //]]>
	
/* 	    var idCheck = false;
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
	        if(!(idCheck&&pwdCheck)){
	            alert("일부 입력값이 잘못되었습니다.");
	            return false;
	        }
	    } */
	</script>
</body>
</html>