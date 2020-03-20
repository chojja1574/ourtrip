<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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

<title>회원 탈퇴</title>

<style>
#agree-textarea {
	resize: none;
	width: 100%;
	height: 500px;
	margin: 20px 0px 20px 0px;
}
</style>

</head>
<body>
	<jsp:include page="../common/header.jsp" />
	<jsp:include page="../common/nav.jsp" />

	<div class="container my-5">
		<form action="secession" method="POST" onsubmit="return validate();">
			<h3 style="text-align: center;">회원탈퇴 안내</h3>
			<textarea class="form-control" id="agree-textarea" readonly>
회원 탈퇴 시 유의사항
    ① 탈퇴할 경우 가입할 때 입력하신 회원님의 개인정보는 모두 삭제되며, 삭제된 데이터는 복구되지 않습니다.
    ② 또한 OurTrip에서 제공하고 있는 마이플랫폼에서도 로그인할 수 없습니다.
    ③ 신중하게 고려하신 후 선택하시기 바랍니다.


회원 탈퇴 후 보존 안내사항
    ① OurTrip 사이트의 다양한 페이지에 있는 게시글, 댓글, 설문, 후기 등은 탈퇴 시 자동으로 삭제되지 않고 그대로 남아 있습니다.
    ② 삭제를 원하는 게시글이 있다면 반드시 탈퇴 전에 삭제하시기 바랍니다.
    ③ 탈퇴 후에는 회원정보가 삭제되어 본인 여부를 확인할 수 있는 방법이 없어, 게시글을 임의로 삭제해드릴 수 없습니다.
            </textarea>
			<div id="agree-wrapper">
				<input type="checkbox" id="termsAgree" name="termsAgree"> <label
					for="termsAgree">약관 동의</label>
			</div>
			<div class="row mt-3">
				<div class="col-md-2">
					<label for="pwd">비밀번호 입력</label>
				</div>
				<div class="col-md-4">
					<input type="password" class="form-control" name="memberPwd"
						id="pwd">
				</div>
			</div>
			<div id="btn-wrapper" class="mt-3 d-flex">
				<button class="btn del-btn ml-auto">회원 탈퇴</button>
			</div>
		</form>
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
        var pwdCheck =false;

        $(function(){
            $("#pwd").on("input", function(){
                if($("#pwd").val() != ""){
                    pwdCheck = true;
                }
            });
        });
        
        function validate(){
            if(!(pwdCheck && $("input:checkbox[id='termsAgree']").is(":checked"))){
                alert("일부 입력값이 잘못 입력되었습니다.");
                return false;
            }else{
            	if(!confirm("정말 탈퇴하시겠습니까?")){
            		return false;
            	}
            }
        }
    </script>
</body>
</html>