// 각 유효성 검사 결과를 저장할 객체
var signUpCheck = {
	"email" : false,
	"emailDup" : false,
	"pwd1" : false,
	"pwd2" : false,
	"nickname" : false,
};

// 실시간 입력 형식 검사
// 정규표현식
$(document).ready(
		function() {

			// jQuery 변수 : 변수에 직접적으로 jQuery메소드를 사용할 수 있음.
			var $email = $("#email");
			var $pwd1 = $("#pwd1");
			var $pwd2 = $("#pwd2");
			var $nickname = $("#nickname");

			// 아이디 유효성 검사
			$email.on("input", function() {
				// 첫글자는 영어 소문자, 나머지 글자는 영어 대,소문자 + 숫자, 총 6~12글자
				var regExp = /^[\w]{4,}@[\w]+(\.[\w]+){1,3}$/;
				if (!regExp.test($id.val())) {
					$("#checkEmail").text("이메일 형식이 유효하지 않습니다.").css({
						"color" : "red",
						"font-weight" : "bold"
					});
					signUpCheck.email = false;
				} else {
					signUpCheck.email = true;
					$("#checkEmail").text("사용 가능한 아이디 입니다.").css({
						"color" : "green",
						"font-weight" : "bold"
					});
					signUpCheck.emailDup = true;
					/*
					 * 수업자료 회원가입부분 Ajax 긁어온 부분
					 * 
					 * $.ajax({ url : "idDupCheck", data : {memberId: $id.val() },
					 * type : "post", success : function(result){
					 * 
					 * if(result == "true"){ $("#checkId").text("사용 가능한 아이디
					 * 입니다.").css({"color":"green","font-weight":"bold"});
					 * signUpCheck.idDup = true; }else{ $("#checkId").text("사용할
					 * 수 없는 아이디
					 * 입니다.").css({"color":"red","font-weight":"bold"});
					 * signUpCheck.idDup = false; } },
					 * 
					 * error : function(e){ console.log("ajax 통신 실패");
					 * console.log(e); } });
					 */
				}
			});

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
				var regExp = /^[가-힣a-z0-9]{4,12}$/; // 한글,영문,숫자 4~12글자

				if (!regExp.test($(this).val())) { // 이름이 정규식을 만족하지 않을경우
					$("#checkNick").text("한글,영문,숫자 4~12글자 입력").css("color",
							"red");
					signUpCheck.nickname = false;
				} else {
					$("#checkNick").text("정상입력").css("color", "green");
					signUpCheck.nickname = true;
				}
			});
		});

// submit 동작
function validate() {

	// 아이디 중복 검사 결과
	// if( $("#idDup").val() == "true") signUpCheck.idDup = true;
	// else signUpCheck.idDup = false;

	for ( var key in signUpCheck) {
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
}

$(function() {
	console.log("start : " + $("#ot-input-profileImg").val);
	$('#btn-upload').click(function(e) {

		e.preventDefault();
		console.log("c")
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

// 비밀번호 찾기 페이지로 이동
function changePwd() {
	location.href = "changePwd.html";
}
