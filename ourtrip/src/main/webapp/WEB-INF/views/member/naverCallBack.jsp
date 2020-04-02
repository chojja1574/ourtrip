<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<script type="text/javascript" src="https://static.nid.naver.com/js/naveridlogin_js_sdk_2.0.0.js" charset="utf-8"></script>
<meta charset="UTF-8">
<title>Naver Login Callback</title>
<style>
.wrap-loading{
    position: fixed;
    left:0;
    right:0;
    top:0;
    bottom:0;
    z-index: 10;
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
</style>
</head>
<body>
	<div class="wrap-loading" id="loading">
        <div><img src="${contextPath}/resources/images/loadingBar.gif"/></div>
    </div>

	<script src="https://code.jquery.com/jquery-3.4.1.min.js"
		integrity="sha256-CSXorXvZcTkaix6Yvo6HppcZGetbYMGWSFlBw8HfCJo="
		crossorigin="anonymous"></script>
	<script>
		console.log('192.168.10.59:8080');
		var naverLogin = new naver.LoginWithNaverId(
			{
				clientId: "cajbBEXn_EXigNoRN2Oc",

    			callbackUrl: "${contextPath}/member/naverCallBack",
    			/* callbackUrl: "http://115.90.212.22:9003/ourtrip/member/naverCallBack",
    			callbackUrl: "http://localhost:8080/ourtrip/member/naverCallBack", */

				isPopup: true,
				callbackHandle: true
				/* callback 페이지가 분리되었을 경우에 callback 페이지에서는 callback처리를 해줄수 있도록 설정합니다. */
			}
		);
		
		/* (3) 네아로 로그인 정보를 초기화하기 위하여 init을 호출 */
		naverLogin.init();
		
		/* (4) Callback의 처리. 정상적으로 Callback 처리가 완료될 경우 main page로 redirect(또는 Popup close) */
		window.addEventListener('load', function () {
			naverLogin.getLoginStatus(function (status) {
				if (status) {
					
					/* (5) 필수적으로 받아야하는 프로필 정보가 있다면 callback처리 시점에 체크 */
					var email = naverLogin.user.getEmail();
					if( email == undefined || email == null) {
						alert("이메일은 필수정보입니다. 정보제공을 동의해주세요.");
						/* (5-1) 사용자 정보 재동의를 위하여 다시 네아로 동의페이지로 이동함 */
						naverLogin.reprompt();
						return;
						
					}else{
						$.ajax({
	                    	url : "naverLogin",
	                    	type : "POST",
	                    	data : {memberEmail : naverLogin.user.email,
	                    			memberPwd : naverLogin.user.id,
	                    			memberNickName : naverLogin.user.nickname,
	                    			imagePath : naverLogin.user.profile_image},
	                    	success : function(result){
	                    		if(result == "success"){
	                    			location.href = "${detailUrl}";
	                    		}else{
	                    			alert("네이버 로그인에 실패하였습니다.\n" + e);
	                    			location.href = "${contextPath}/member/loginForm";
	                    		}
	                    	},
	                    	error : function(e){
	                    		alert("네이버 로그인에 실패하였습니다.\n" + e);
	                    		location.href = "${contextPath}/member/loginForm";
	                    	},
	                    });
					}
		
				} else {
					alert("callback 처리에 실패하였습니다.");
					location.href = "http://192.168.10.59:8080/ourtrip/member/loginForm";
				}
			});
		});
	</script>
</body>
</html>