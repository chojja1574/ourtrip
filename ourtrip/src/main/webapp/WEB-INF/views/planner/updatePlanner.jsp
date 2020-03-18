<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ko">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
    integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
    
    <!-- jQuery -->
    <script src="https://code.jquery.com/jquery-3.4.1.min.js"
    integrity="sha256-CSXorXvZcTkaix6Yvo6HppcZGetbYMGWSFlBw8HfCJo=" crossorigin="anonymous"></script>
    <!-- jQueryCSS -->
    <link href="https://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css" rel="stylesheet" type="text/css" />
    <!-- jQueryUI -->
    <script type="text/javascript" src="https://code.jquery.com/ui/1.12.1/jquery-ui.js" ></script>
    
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
    integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo"
    crossorigin="anonymous"></script>
    
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"
    integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6"
    crossorigin="anonymous"></script>
    
    <!-- Web socket CDN -->
	<script src="https://cdnjs.cloudflare.com/ajax/libs/sockjs-client/1.4.0/sockjs.js"></script>
	
	<!-- json2.js -->
	<script src="https://cdnjs.cloudflare.com/ajax/libs/json2/20160511/json2.js"></script>
    
    <!-- 호환성 -->
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
        
    <!-- 폰트 -->
    <link href="https://fonts.googleapis.com/css?family=Nanum+Pen+Script|Ubuntu&display=swap" rel="stylesheet">
    
    <!-- 공용 css -->
    <!-- <link rel="stylesheet" href="${contextPath}/resources/css/common.css"> -->
    <link rel="stylesheet" type="text/css" href="${contextPath}/resources/css/common.css"/>
    <link rel="stylesheet" type="text/css" href="${contextPath}/resources/css/planner.css"/>

    
    <!-- Web socket CDN -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/sockjs-client/1.4.0/sockjs.js"></script>
    
    <!-- json2.js -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/json2/20160511/json2.js"></script>

    <!-- kakao map sdk -->
    <script src="//developers.kakao.com/sdk/js/kakao.min.js"></script>
    <script type="text/javascript"
        src="//dapi.kakao.com/v2/maps/sdk.js?appkey=3265d67cbccb2a931046b989ef45ad5f&libraries=services,clusterer,drawing"></script>
    
    <title>플래너 수정</title>
</head>

<body>
    <jsp:include page="../common/header.jsp" />
    <jsp:include page="../common/nav.jsp" />
    <button type="button" data-toggle="modal" data-target="#myModal" id="modalBtn" class="hide">Open Modal</button>

    <!-- Modal -->
    <div class="modal noselect fade" id="myModal" role="dialog">
        <div class="modal-dialog">
            <!-- Modal content-->
            <div class="modal-content">
                <div class="modal-header">
					참여하기
                    <button type="button" class="close" data-dismiss="modal">×</button>
                    <h4 class="modal-title"></h4>
                </div>
                <div class="modal-body">
                    <p>참여하려면 Join 버튼을 누르세요</p>
                    <p>비밀번호를 입력하세요</p>
                    <input type="password" class="form-control" name="joinPwd" id=>
                </div>
                <div class="modal-footer">
                    <button type="button" class="ModalBtnCss2" data-dismiss="modal" onclick="goBack();">돌아가기</button>
                    <button type="button" class="ModalBtnCss1" data-dismiss="modal" id="join">Join</button>
                </div>
            </div>
        </div>
    </div>

    <div class="container-fluid pt-5" style="border-top:1px solid">
        <form action="#">
            <div class="row noselect">
                <div class="col-md-1">
                </div>
                <div class="col-md-4">
                    <div class="main-back plannerHeader row ml-0 mr-0" style="text-align: center;">
                        <div class="col-md-9 pl-2 pr-0">
                            <input type="text" id="url" class="pl-2" readonly value="https//:ourtrip.com/1234567">
                        </div>
                        <div class="col-md-3">
                            <button type="button" id="copy" class="plannerHeaderBtn btnColor1">복사</button>
                        </div>
                    </div>
                </div>
                <div class="col-md-3 pl-0">
                    <div class="main-back plannerHeader row ml-0 mr-0" style="text-align: center;">
                        <div class="col-md-2"></div>
                        <div class="col-md-3">
                            <span>총 경비</span>
                        </div>
                        <div class="col-md-5">
                            <span id="totalcost">0 원</span>
                        </div>
                    </div>
                </div>
                <div class="col-md-3 pl-0">
                    <div class="main-back plannerHeader row ml-0">
                        <div class="col-md-6 pl-2">
                            <select name="permissionUser" id="permissionUser">
                                <option value="1">신덕수</option>
                                <option value="2">이훈</option>
                                <option value="3">박지현</option>
                                <option value="4">조유상</option>
                            </select>
                        </div>
                        <div class="col-md-3 pl-0">
                            <button type="button" class="plannerHeaderBtn btnColor3" id="gradebt">권한 삭제</button>
                        </div>
                        <div class="col-md-3 pl-0">
                            <button type="button" class="plannerHeaderBtn btnColor1" id="gradebt">권한 부여</button>
                        </div>
                    </div>
                </div>
            </div>
            <div class="row content-size">
                <div class="col-md-1"></div>
                
                <!-- 일자 선택창 -->
                <div class="col-md-1" id="plandays">
                    <div class="divBorder noselect">

                        <!-- 출발일 설정 -->
                        <div class="card-header main-back plannerDivHeader dayHeader">
                            <span>출발일 입력</span>
                            <input class="plannerDay" id="startrip" type="date" max="9999-12-31">
                        </div>
                        <!-- 일자 카드 -->
                        <div id="sortable" class="divContent">
                            <div data-dateno="1" id="days" class="daystyle" onclick="selectDay(1);">
                                <span class="dayCount pl-2">1일차</span>
                                <button class="dayDeleteBtn btnColor3" onclick="deleteDay(1);">-</button>
                            </div>
                            <div data-dateno="2" id="days" class="daystyle" onclick="selectDay(2);">
                                <span class="dayCount pl-2">2일차</span>
                                <button class="dayDeleteBtn btnColor3" onclick="deleteDay(2);">-</button>
                            </div>
                            <div data-dateno="3" id="days" class="daystyle" onclick="selectDay(3);">
                                <span class="dayCount pl-2">3일차</span>
                                <button class="dayDeleteBtn btnColor3" onclick="deleteDay(3);">-</button>
                            </div>
                            <div data-dateno="4" id="days" class="daystyle" onclick="selectDay(4);">
                                <span class="dayCount pl-2">4일차</span>
                                <button class="dayDeleteBtn btnColor3" onclick="deleteDay(4);">-</button>
                            </div>
                            <div data-dateno="5" id="days" class="daystyle" onclick="selectDay(5);">
                                <span class="dayCount pl-2">5일차</span>
                                <button class="dayDeleteBtn btnColor3" onclick="deleteDay(5);">-</button>
                            </div>
                        </div>
                        
                        <div class="updateBtns p-3">
                            <div class="row mb-3">
                                <div class="col-md-12"><button type="button" id="addDayBtn" class="updateBtn btnColor1">일차 추가</button></div>
                            </div>
                            <div class="row">
                                <div class="col-md-12"><button type="button" class="updateBtn btnColor1">이용 방법</button></div>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="col-md-3 pl-0" id="inputplanner">
                    <div class="divBorder noselect" id="plannerWraper1">
                        <h2 class="card-header main-back plannerDivHeader">일정 편집</h2>
                        <div data-scheduleno="0" class="p-3 divContent" id="scheduleInfo">
                            <div>
                                <label for="inputScheduleTitle" class="mb-2">
                                    <h2>일정제목</h2>
                                </label>
                                <input id="inputScheduleTitle" class="form-control mb-4" type="text" 
                                placeholder="일정제목을 입력해 주세요" style="font-size: 1.4rem;">
                            </div>
                            <div>
                                <label for="inputScheduleCost">
                                    <h2>경비</h2>
                                </label><br>
                                <input id="inputScheduleCost" class="tripinfo inputScheduleCost form-control" 
                                type="number" placeholder="경비 입력" style="font-size: 1.4rem;"><br>
                            </div>
                            <div>
                                <label for="inputScheduleTime">
                                    <h2>시간</h2>
                                </label><br>
                                <input id="inputScheduleTime" class="tripinfo inputScheduleTime form-control" 
                                type="time" placeholder="시간 입력" style="font-size: 1.4rem;"><br>
                            </div>
                            <div>
                                <label for="inputScheduleMemo">
                                    <h2>메모</h2>
                                </label><br>
                                <textarea id="inputScheduleMemo" class="tripinfo inputScheduleMemo form-control" 
                                    style="height:150px; width: 98%; resize: none;" style="font-size: 1.4rem;"> 
                                </textarea><br>
                            </div>
                            <label for="inputScheduleLocationName">
                                <h2>장소</h2>
                            </label>
                            <input class="inputScheduleLocationName form-control" id="inputScheduleLocationName" 
                                    type="text" placeholder="장소 이름 입력" style="font-size: 1.4rem;">
                            <div id="inputScheduleLocationArea">
                                <div class="input-group my-3">
                                    <input class="inputScheduleLocation form-control" id="inputScheduleLocation" 
                                        type="text" placeholder="장소검색" style="font-size: 1.4rem;" onkeypress="enterSearch(this);">
                                    <div class="input-group-append">
                                        <button class="btn btn-success" type="button" id="search-btn" onclick="searchKeyword();">검색</button>
                                    </div>

                                </div>
                                <div class="maplace" id="map">
                                </div>

                                <div id="btn-wrapper" class="mt-3 d-flex">
                                    <div class="flex-fill">
                                        <button type="button" class="btn btn-primary btn-block reco-btn">맛집</button>
                                    </div>
                                    <div class="flex-fill mx-3  ">
                                        <button type="button" class="btn btn-success btn-block reco-btn">관광지</button>
                                    </div>
                                    <div class="flex-fill">
                                        <button type="button" class="btn btn-info btn-block reco-btn">숙소</button>
                                    </div>
                                </div>
                            </div>

                            <div class="updateBtns p-3">
                                <div class="row mb-3">
                                    <div class="col-md-6"><button type="button" class="updateBtn btnColor1" id="toggleMap">지도 추가</button></div>
                                    <div class="col-md-6"><button type="button" class="updateBtn btnColor1" id="addSchedule">일정 추가</button></div>
                                </div>
                                <div class="row">
                                    <div class="col-md-6"><button type="button" class="updateBtn btnColor1" id="scheduleUpdate">저장</button></div>
                                    <div class="col-md-6"><button type="button" class="updateBtn btnColor3" id="removeSchedule">삭제</button></div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col-md-3 pl-0" id="complaner">
                    <div class="card divBorder noselect">
                        <h2 class="card-header main-back plannerDivHeader" id="selectedDay">n일차</h2>
                        <div class="card-body divContent p-3">
                            <div class="plannerAccodian" id="scheduleList">
                                <div data-scheduleno="1" class="btn p-cont schedule" onclick="selectSchedule(1);">
                                    <div class="row font-weight-bold accodianElement mb-1">
                                        <div class="col-md-8">
                                            <h1 class="scheduleTitle">첫번째 일정</h1>
                                        </div>
                                        <div class="col-md-3">
                                            <h3><input type="time" value="08:00" class="disableInput scheduleTime" disabled></h3>
                                        </div>
                                        <div class="col-md-1 toggleArrow" onclick="toggleArrow(this)">▼</div>
                                    </div>
                                    <div style="display:none">
                                        <h4 class="mb-4 mt-4 scheduleLocation">서울역</h4>
                                        <h4 class="mb-4 ">경비 : <span class="scheduleCost">10000</span>원 </h4>
                                        <h4 class="mb-3 ">메모 내용</h4>
                                        <h5 class="pl-3 scheduleMemo">가나다라 마바사</h5>
                                    </div>
                                    <hr class="mt-4 mb-3">
                                </div>
                                <div data-scheduleno="2" class="btn p-cont schedule" onclick="selectSchedule(2);">
                                    <div class="row font-weight-bold accodianElement mb-1">
                                        <div class="col-md-8">
                                            <h1 class="scheduleTitle">두번째 일정</h1>
                                        </div>
                                        <div class="col-md-3">
                                            <h3><input type="time" value="09:00" class="disableInput scheduleTime" disabled></h3>
                                        </div>
                                        <div class="col-md-1 toggleArrow" onclick="toggleArrow(this)">▼</div>
                                    </div>
                                    <div style="display:none">
                                        <h4 class="mb-4 mt-4 scheduleLocation">종각역</h4>
                                        <h4 class="mb-4 ">경비 : <span class="scheduleCost">12000</span>원 </h4>
                                        <h4 class="mb-3 ">메모 내용</h4>
                                        <h5 class="pl-3 scheduleMemo">사바마 라다나가</h5>
                                    </div>
                                    <hr class="mt-4 mb-3">
                                </div>                                 
                                                                                  
                            </div>
                            <div id="allMapPlace" style="width:100%; height: 350px;"></div>
                        </div>
                    </div>
                </div>
                <!-- complaner end -->
                <div class="col-md-3" id="chatpart" style="padding: 0px 0px 0px 0px; overflow: hidden;">
                    <div class="divBorder chatBGC">
                        <h2 class="card-header main-back plannerDivHeader noselect">채팅</h2>
                        <div class="chatarea p-3">
                            <div class="chatbox overhidden">
                                <div>
                                    <img src = "" class="profileImg">
                                    <span class="userId">user02</span>
                                </div>
                                <div>
                                    <div class="message">
                                        <span>asdf지장문의 메세지장문의 메세지장문의 메세지장문의 메세지</span>
                                    </div>
                                    <span class="messageTime">11:00</span>
                                </div>
                            </div>
                            
                            <div class="myChatbox overhidden">
                                <div class="myMessage">
                                    <span>아 죽겠다아 죽겠다아 죽겠다아 죽겠다아 죽겠다아 죽겠다아 죽겠다아 죽겠다</span>
                                </div>
                                <div>
                                    <span class="myMessageTime">11:00</span>
                                </div>
                            </div>
                            <div class="chatbox overhidden">
                                <div>
                                    <img src = "" class="profileImg">
                                    <span class="userId">user02</span>
                                </div>
                                <div>
                                    <div class="message">
                                        <span>장문의 메세지장문복의 메세지장문의 메세지장문의 메세지장문의 메세지장문복의 메세지장문의 메세지장문의 메세지장문의 메세지장문의 메세지장문의 메세지</span>
                                    </div>
                                    <span class="messageTime">11:00</span>
                                </div>
                            </div>
                            
                            <div class="myChatbox overhidden">
                                <div class="myMessage">
                                    <span>아 죽겠다아 죽겠다아 죽겠다아 죽겠다아 죽겠다아 죽겠다아 죽겠다아 죽겠다</span>
                                </div>
                                <div>
                                    <span class="myMessageTime">11:00</span>
                                </div>
                            </div>
                            
                            <div class="arrow_box"></div>
                        </div>
                        
                        <div class="row p-4" style=" height: 18%;">
                            <div class="col-md-9">
                                <textarea id="mymsg" class="inputMsg"></textarea>
                            </div>
                            <div class="col-md-3">
                                <button type="button" id="send" class="sendBtn" style="height:50%">전송1</button>
                                <button type="button" id="send2" class="sendBtn" style="height:50%">전송2</button>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col-md-1"></div>
            </div>
        </form>
    </div>
    <jsp:include page="../common/footer.jsp" />
</body>

<script>
$(function() {
	$("#sendBtn").click(function() {
		sendMessage();
		$('#message').val('')
	});
	
	$("#redbtn").click(function() {
		console.log('r');
		redbtn();
	});
	
	$("#bluebtn").click(function() {
		console.log('b');
		bluebtn();
	});
	
	$("#greenbtn").click(function() {
		console.log('g');
		greenbtn();
	});
	
	$("#message").keydown(function(key) {
		if (key.keyCode == 13) {// 엔터
			sendMessage();
			$('#message').val('')
		}
	});
	$("#jsonTest").click(function() {
		sock.send(JSON.stringify({chatRoomId: "room1", type: 'TEST', writer: "${userId}", content: "null"}))
	});
	$("#join").click(function(){
		console.log('1');
		sock.send(JSON.stringify({chatRoomId: "${selectRoom}", type: 'JOIN', writer: "${userId}", content: ""}));
	})
	console.log('1');
    // 페이지 입장 시 참여버튼 모달 출력
    $("#modalBtn").click();
    
  	//웹소켓을 지정한 url로 연결한다.
	let sock = new SockJS("<c:url value="/echo"/>");
	sock.onmessage = onMessage;
	sock.onclose = onClose;
	console.log('2');
});

// 메시지 전송
function sendMessage() {
	sock.send(JSON.stringify({chatRoomId: "${selectRoom}", type: 'Message', writer: "${userId}", content: $("#message").val()}));
}
function redbtn() {
	sock.send(JSON.stringify({chatRoomId: "${selectRoom}", type: 'changeback', writer: "${userId}", content: "red"}));
}
function bluebtn() {
	sock.send(JSON.stringify({chatRoomId: "${selectRoom}", type: 'changeback', writer: "${userId}", content: "blue"}));
}
function greenbtn() {
	sock.send(JSON.stringify({chatRoomId: "${selectRoom}", type: 'changeback', writer: "${userId}", content: "green"}));
}

function chageback(color){
	console.log(color);
	$("#testbox").css("background",color);
}

// 서버로부터 메시지를 받았을 때
function onMessage(msg) {
	var data = msg.data;
	var dataFlag = JSON.parse(data)
	console.log(dataFlag);
	switch(dataFlag['type']){
	case 'Message':
		console.log("m");
		$("#data").append(data + "<br/>");
		break;
	case 'changeback':
		console.log(dataFlag['content']);
		switch(dataFlag['content']){
		case 'red' :
			chageback("red");
			break;
		case 'blue' :
			chageback("blue");
			break;
		case 'green' :
			chageback("green");
			break;
		}
	}
}

// 서버와 연결을 끊었을 때
function onClose(evt) {
	$("#data").append("연결 끊김");
}

</script>
<script type="text/javascript" src="${contextPath}/resources/js/map.js"></script>
<script type="text/javascript" src="${contextPath}/resources/js/planner.js"></script>
</html>