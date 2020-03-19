// 각 유효성 검사 결과를 저장할 객체
var signUpCheck = {
	"email" : false,
	"certifyCode" : false,
	"pwd1" : false,
	"pwd2" : false,
	"nickname" : false,
};

var defaultImg = "../resources/images/default-profile.png";

// 실시간 입력 형식 검사
// 정규표현식
$(document).ready(
		function() {

			// jQuery 변수 : 변수에 직접적으로 jQuery메소드를 사용할 수 있음.
			var $email = $("#email");
			var $pwd1 = $("#pwd1");
			var $pwd2 = $("#pwd2");
			var $nickname = $("#nickname");

			// 비밀번호 유효성 검사
			$pwd1.on("input", function() {
				// 영어 대,소문자 + 숫자, 총 6~12글자
				var regExp = /^[A-Za-z0-9]{6,12}$/;
				if (!regExp.test($pwd1.val())) {
					$("#checkPwd1").text("영어 대,소문자 + 숫자, 총 6~12글자를 입력해주세요")
							.css("color", "red");
					signUpCheck.pwd1 = false;
				} else {
					$("#checkPwd1").text("유효한 비밀번호 형식입니다.").css("color",
							"green");
					signUpCheck.pwd1 = true;
				}

			});

			// 비밀번호 일치 여부
			$pwd2.on("input", function() {
				if ($pwd1.val().trim() != $pwd2.val().trim()) {
					$("#checkPwd2").text("비밀번호 불일치").css("color", "red");
					signUpCheck.pwd2 = false;
				} else {
					$("#checkPwd2").text("비밀번호 일치").css("color", "green");
					signUpCheck.pwd2 = true;
				}
			});

			// 이름 유효성 검사
			$nickname.on("input", function() {
				var regExp = /^[가-힣a-zA-Z0-9]{4,12}$/; // 한글,영문,숫자 4~12글자

				if (!regExp.test($(this).val())) { // 이름이 정규식을 만족하지 않을경우
					$("#checkNick").text("한글,영문,숫자 4~12글자 입력").css("color",
							"red");
					signUpCheck.nickname = false;
				} else {
					$("#checkNick").text("정상입력").css("color", "green");
					signUpCheck.nickname = true;
				}
			});
			
			// 이메일 확인 및 전송
			$("#email-authentication-btn").on("click", function(){
				signUpCheck.email = false;
				
		    	if($(this).text() == "취소"){
		    		$email.prop("readonly", false).val("").focus();
					$("#email-authentication-btn").text("메일 인증").removeClass("del-btn")
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
		    				url: "emailCertify",
		    				type: "POST",
		    				data: {email: $email.val()},
		    				success: function(result){
		    					console.log(result);
		    					
		    					if(result == "0"){
		    						$("#checkEmail").html("이미 가입된 이메일입니다.")
		    						.css("color", "red");
		    					}else{
		    						$("#checkEmail").html("이메일로 전송된 코드를 확인해주세요.")
		    						.css("color", "green");
		    						
		    						$email.prop("readonly", true);
		    						$("#email-authentication-btn").text("취소").removeClass("main-btn")
		    						.addClass("del-btn");
		    						
		    						signUpCheck.email = true;
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

// submit 동작
function validate() {

	// 인증번호 검사
	if($("#certifyCode").val() != certifyCode){
		alert("인증번호가 일치하지않습니다.");
		$("#certifyCode").focus();
		signUpCheck.certifyCode = false;
		return false;
	}else{
		signUpCheck.certifyCode = true;
	}

	for (var key in signUpCheck) {
		if (!signUpCheck[key]) {
			alert("일부 입력값이 잘못되었습니다.");
			console.log(key);
			var id = "#" + key;
			$(id).focus();
			return false;
		}
	}
}

// 각각의 영역에 파일을 첨부 했을 경우 미리 보기가 가능하도록 하는 함수
function LoadImg(value, num) {
	// multiple 속성을 사용하면 사진이 배열의 형태로 올라옴
	// 파일 업로드 시 업로드 된 파일의 경로는 files라는 배열이 생성되면 저장됨
	// files라는 배열이 있고 value.files[0]에 값이 있는지 확인
	// : 파일이 선택된 경우를 말함
	if (value.files && value.files[0] && num != 2) {
		var reader = new FileReader();
		// 파일을 읽어들임

		reader.onload = function(e) {

			$("#ot_profile_image").prop("src", e.target.result);
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

$(function() {
	$("#email-authentication-div").hide();
	$("#email-authentication-btn").click(function(e) {
		$("#email-authentication-div").show();
	})
});
