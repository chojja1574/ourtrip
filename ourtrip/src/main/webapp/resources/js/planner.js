var scheduleMarkers = new Array(new Array(), new Array(), new Array(),new Array());
var date = -1;
var schedule= -1;
$(function () {
    
    // 페이지 로딩 시 일정 수에 맞게 위치정보 Array에 자리생성
    $('.schedule').each(function(i, el){
        scheduleMarkers[0].push($(el).data('scheduleno'));
        scheduleMarkers[1].push(new kakao.maps.LatLng(0, 0));
        scheduleMarkers[2].push('0');
        scheduleMarkers[3].push(null);
    });

    var index = 1;
    
    // 복사 버튼 클릭 시 페이지 url 클립보드에 복사
    $("#copy").on("click", function () {
        var urlbox = document.getElementById('url');
        urlbox.select();
        document.execCommand('Copy');
        alert('URL 이 복사 되었습니다.');
    })

    // 내가 쓴 채팅 보내는 함수
    $("#send").click(function(){
        // 채팅 입력창 내용 변수에 저장
        var msg = $('#mymsg').val();

        // 날짜 객체
        var d = new Date();
        // 현재 시간
        var now = d.getHours() + ':' + d.getMinutes();

        // 채팅 입력창 비어있지 않으면 실행
        if( msg != ''){
            // inputChat = 채팅 내역에 채팅창 올리는 함수
            // mkMyChatMsg = 내가 보낸 메세지로 채팅창 만드는 함수
            // mkMyChatMsg 매개변수 = (msgContent,msgTime)
            inputChat(mkMyChatMsg(msg,now));
        }

        // 메세지 전송 후 채팅 입력창 비워줌
        $('#mymsg').val('');
    })

    // #send2 버튼은 테스트하려고 만듦            
    $("#send2").click(function(){

        // inputChat = 채팅 내역에 채팅창 올리는 함수
        // mkChatMsg = 다른사람이 보낸 메세지로 채팅창 만드는 함수
        // mkChatMsg 매개변수 = (profileImg,userId,msgContent,msgTime)
        inputChat(mkChatMsg(1,2,3,4));
    })

    // 일차 마우스로 이동 가능하게 하고 정렬하는 함수
    $("#sortable").disableSelection();
    $("#sortable").sortable({
        placeholder:".daystyleHighlight",
        // 드래그 시작했을 때 작동
        start: function(event, ui) {
            ui.item.data('start_pos', ui.item.index());
        },
        // 드래그 끝났을 때 작동
        stop: function(event, ui) {
            var spos = ui.item.data('start_pos');
            var epos = ui.item.index();
            // 일차 정렬하는 함수
            reorder();
        }
    });
    var dayInd=6;

    // 일차 추가하는 함수
    // 매개변수 dateNo = PLANNER_DATE 테이블의 DATE_NO값을 넣어야함
    $("#addDayBtn").click(function(dateNo){
        
        // 일차 만드는 HTML코드, dayInd는 테스트용이고 dateNo로 바꿔줘야함
        var dayForm = 
        '<div data-dateno="' + dayInd + '" id="days" class="daystyle" onclick="selectDay(' + dayInd + ');">' +
        '<span class="dayCount pl-2">1일차</span>' +
        '<button class="dayDeleteBtn btnColor3" onclick="deleteDay(' + dayInd + ');">-</button>' +
        '</div>';

        // 일차 목록에 추가함
        $("#sortable").append(dayForm)

        // dayInd 변수는 테스트때만 사용하고 실제 DB사용할때는 지울것
        dayInd = dayInd+1;

        // 추가하고 정렬(몇일차인지 계산해서 텍스트 바꿔줌)
        reorder();
    });

    // 페이지 로딩될 때 지도영역 감추는거 그냥 함수로 하는부분
    $("#inputScheduleLocationArea").toggle();

    // 지도추가 버튼 클릭 시 보이고 안보이고 토글, 버튼 텍스트 변경
    $("#toggleMap").click(function(){
        $("#inputScheduleLocationArea").toggle(['slow']);
        if($("#toggleMap").html() == '지도 추가')
            $("#toggleMap").html('지도 삭제');
        else
        $("#toggleMap").html('지도 추가');
        
    });
});

// 뒤로가기 하는 함수(참여 모달창의 이전으로 버튼에 사용)
function goBack(){
    console.log("돌아가기");
    history.go(-1);
}

// 다른사람이 보낸 채팅 메세지 만들어서 리턴하는 함수
function mkChatMsg(profileImg,userId,msgContent,msgTime){
    var chatMsg =
    '<div class="chatbox overhidden">' +
    '<div>' +
    '<img src = "images/flower1.PNG" class="profileImg">' +
    '<span class="userId">user02</span>' +
    '</div>' +
    '<div>' +
    '<div class="message">' +
    '<span>장문의 메세지장문복의 메세지장문의 메세지장문의 메세지장문의 메세지장문복의 메세지장문의 메세지장문의 메세지장문의 메세지장문의 메세지장문의 메세지</span>' +
    '</div>' +
    '<span class="messageTime">11:00</span>' +
    '</div>' +
    '</div>';

    return chatMsg;
}

// 내가 보낸 채팅 메세지 만들어서 리턴하는 함수
function mkMyChatMsg(msgContent,msgTime){
    var myChatMsg =
    // '<div class="myChatbox">' +
    // '<span class="messageTime">' + msgTime + '</span>' +
    // '<span class="myMessage">' + msgContent + '</span>' +
    // '</div>';
    '<div class="myChatbox overhidden">' +
    '<div class="myMessage">' +
    '<span>' + msgContent + '</span>' +
    '</div>' +
    '<div>' +
    '<span class="myMessageTime">' + msgTime + '</span>' +
    '</div>' +
    '</div>';
        
    return myChatMsg;
}

// 채팅내역에 채팅창 추가하는 함수( 매개변수 msg = 채팅창)
function inputChat(msg){
    var $chatArea = $(".chatarea")
    $chatArea.append(msg);

    // 스크롤 자동으로 아래로 내려주는 함수
    $chatArea.scrollTop($chatArea.prop("scrollHeight"));
}

// createNo 변수는 테스트때만 사용하고 실제 DB사용할때는 지울것
var createNo=5;

// 일정 추가하는 함수
$('#addSchedule').click(function(){
    
    var title = $('#inputScheduleTitle').val();
    var time = $('#inputScheduleTime').val();
    var cost = $('#inputScheduleCost').val();
    var memo = $('#inputScheduleMemo').val();
    var locationName = $('#inputScheduleLocationName').val();

    if(title == ''){
        alert('제목을 입력해주세요');
    }else if(lat + lng == 0){
        alert('위치를 선택해주세요');
    }else if(time == ''){
        alert('시간을 입력해주세요');
    }else if(locationName.val()){
        alert('장소 이름을 입력해주세요');
    }else{

        

        // 첫번째 매개변수는 SCHEDULE 테이블에서 SCHEDULE_NO값이 들어가야 하는데
        // 새로운 일정을 만드는 것이니 시퀀스 NEXTVAL 얻어와서 넣어야함
        // 테스트때 임시로 createNo 변수 사용
        createSchedule(createNo,title,time,cost,memo,locationName);

        // 마커 배열에 새로운 자리를 만듦
        scheduleMarkers[0].push(createNo);
        scheduleMarkers[1].push(new kakao.maps.LatLng(lat, lng));
        scheduleMarkers[2].push(lat+lng);

        console.log('createschedule');
        for(var i = 0; i < scheduleMarkers[0].length; i++ ){
            console.log(i+"[0 : "+ scheduleMarkers[0][i] + ", 1 : "+ scheduleMarkers[1][i]+"]");
        }

        var dupIdx = scheduleMarkers[1].length-1;
        console.log("==============createNo=============" + dupIdx);
        console.log(scheduleMarkers[1][dupIdx]);
        console.log(scheduleMarkers[3][dupIdx]);
        sortSchedule();

        // 생성한 일정을 선택상태로 만들어줌
        selectSchedule(createNo);
        
                
        // createNo 변수는 테스트때만 사용하고 실제 DB사용할때는 지울것
        createNo = createNo + 1;
    }
});

// 일정 만드는 코드 반환하는 함수
// 첫번째 매개변수는 SCHEDULE 테이블에서 SCHEDULE_NO값이 들어가야 하는데
// 새로운 일정을 만드는 것이니 시퀀스 NEXTVAL 얻어와서 넣어야함
function createSchedule(no,title,time,cost,memo,locationName){
    var schedule = 
    '<div data-scheduleno="' + no + '" class="btn p-cont schedule" onclick="selectSchedule(' + no + ')">' +
    '<div class="row font-weight-bold accodianElement mb-1">' +
    '<div class="col-md-8">' +
    '<h1 class="scheduleTitle">' + title + '</h1>' +
    '</div>' +
    '<div class="col-md-3">' +
    '<h3><input type="time" value="' + time + '" class="disableInput scheduleTime" disabled></h3>' +
    '</div>' +
    '<div class="col-md-1 toggleArrow" onclick="toggleArrow(this)">▼</div>' +
    '</div>' +
    '<div style="display:none">' +
    '<h4 class="mb-4 mt-4 scheduleLocation">' + locationName + '</h4>' +
    '<h4 class="mb-4 ">경비 : <span class="scheduleCost">' + cost + '</span>원</h4>' +
    '<h4 class="mb-3 ">메모 내용</h4>' +
    '<h5 class="pl-3 scheduleMemo">' + memo + '</h5>' +
    '</div>' +
    '<hr class="mt-4 mb-3">' +
    '</div>';
    $("#scheduleList").append(schedule);
}

function selectScheduleNo(no){
    var selectedSchedule;
    var order;
    $(".schedule").each(function(index, item){
        if($(item).data("scheduleno") == no){
            selectedSchedule = item;
            order = index;
        }
    });
    console.log(order);
    return new Array(selectedSchedule, order);
}

function selectSchedule(no){
    var selectedSchedule = selectScheduleNo(no);
    
    $('#inputScheduleTitle').val($(selectedSchedule[0]).find(".scheduleTitle").html());
    $('#inputScheduleTime').val($(selectedSchedule[0]).find(".scheduleTime").val());
    $('#inputScheduleCost').val($(selectedSchedule[0]).find(".scheduleCost").html());
    $('#inputScheduleMemo').val($(selectedSchedule[0]).find(".scheduleMemo").html());
    $('#inputScheduleLocationName').val($(selectedSchedule[0]).find(".scheduleLocation").html());
    $('#scheduleInfo').data('scheduleno',no);

    console.log('selectSchedule()');
    for(var i = 0; i < scheduleMarkers[0].length; i++ ){
        console.log(i+"[0 : "+ scheduleMarkers[0][i] + ", 1 : " + scheduleMarkers[1][i] + ", 2 : " + scheduleMarkers[2][i] + "]");
    }

    console.log("selectedSchedule[1]" + selectedSchedule[1]);
    console.log("scheduleMarkers[2]" + scheduleMarkers[2][selectedSchedule[1]]);
    console.log("scheduleMarkers[2]" + scheduleMarkers[1][selectedSchedule[1]]);
    if(scheduleMarkers[2][selectedSchedule[1]]!=0){
        allMap.panTo(scheduleMarkers[1][selectedSchedule[1]]);
        map.panTo(scheduleMarkers[1][selectedSchedule[1]]);
    }
    
    lat = scheduleMarkers[1][selectedSchedule[1]].getLat();
    lng = scheduleMarkers[1][selectedSchedule[1]].getLng();
    // marker.setPosition(mouseEvent.latLng);
    // marker.setMap(map);
    initMarker(scheduleMarkers[1][selectedSchedule[1]],scheduleMarkers[3][selectedSchedule[1]]);
};
$('#scheduleUpdate').click(function(){
    if($('#scheduleInfo').data('scheduleno')==0){
        alert('일정을 선택해주세요');
    }else if($('#inputScheduleTitle').val() == ''){
        alert('제목을 입력해주세요');
    }else if(lat + lng == 0){
        alert('위치를 선택해주세요');
    }else if($('#inputScheduleTime').val() == ''){
        alert('시간을 입력해주세요');
    }else {
        var selectedSchedule = selectScheduleNo($('#scheduleInfo').data('scheduleno'));
        $(selectedSchedule[0]).find(".scheduleTitle").html($('#inputScheduleTitle').val());
        $(selectedSchedule[0]).find(".scheduleTime").val($('#inputScheduleTime').val());
        $(selectedSchedule[0]).find(".scheduleLocation").html( $('#inputScheduleLocationName').val());
        $(selectedSchedule[0]).find(".scheduleCost").html($('#inputScheduleCost').val());
        $(selectedSchedule[0]).find(".scheduleMemo").html($('#inputScheduleMemo').val());

        var flag = false;
        var i;
        var dupIdx;
        
        for(i = 0; i < scheduleMarkers[0].length; i++){
            if($('#scheduleInfo').data('scheduleno') == scheduleMarkers[0][i]){
                dupIdx = i;
            }
        }

        scheduleLatLng = new kakao.maps.LatLng(lat, lng);
        console.log(scheduleLatLng + ", order = " + dupIdx);
        scheduleMarkers[1][dupIdx] = scheduleLatLng;
        scheduleMarkers[2][dupIdx] = lat + lng;
        scheduleMarkers[3][dupIdx] = iwContent;
        console.log("updateIwContent");
        console.log(iwContent);
        console.log("getLat : " + scheduleMarkers[1][dupIdx].getLat());
        console.log("getLng : " + scheduleMarkers[1][dupIdx].getLng());

        
        
        
        for(var i = 0; i < scheduleMarkers[0].length; i++ ){
            console.log(i+"[0 : "+ scheduleMarkers[0][i] + ", 1 : " + scheduleMarkers[1][i] + ", 2 : " + scheduleMarkers[2][i] + "]");
        }
        
        sortSchedule();

        initMarker(scheduleMarkers[1][dupIdx], scheduleMarkers[3][dupIdx]);
    }
});
$('#removeSchedule').click(function(){
    if($('#scheduleInfo').data('scheduleno')==0){
        alert('일정을 선택해주세요');
    } else {
        var selectedSchedule = selectScheduleNo($('#scheduleInfo').data('scheduleno'));

        $(selectedSchedule[0]).remove();
        $('#inputScheduleTitle').val('');
        $('#inputScheduleTime').val('');
        $('#inputScheduleCost').val('');
        $('#inputScheduleMemo').val('');
        $('#inputScheduleLocationName').val('');
        $('#scheduleInfo').data('scheduleno',0);
    }

});



function sortSchedule(){
    var scheduleArr = $(".schedule");
    for(var i = scheduleArr.length-1; i > 0; i--){
        for(var j = 0; j < i; j++){
            if($(scheduleArr[j+1]).find(".scheduleTime").val() 
            < $(scheduleArr[j]).find(".scheduleTime").val()){
                var temp = scheduleArr[j];
                var temp1 = scheduleMarkers[0][j];
                var temp2 = scheduleMarkers[1][j];
                var temp3 = scheduleMarkers[2][j];
                var temp4 = scheduleMarkers[3][j];
                scheduleArr[j] = scheduleArr[j+1];
                scheduleMarkers[0][j] = scheduleMarkers[0][j+1];
                scheduleMarkers[1][j] = scheduleMarkers[1][j+1];
                scheduleMarkers[2][j] = scheduleMarkers[2][j+1];
                scheduleMarkers[3][j] = scheduleMarkers[3][j+1];
                scheduleArr[j+1] = temp;
                scheduleMarkers[0][j+1] = temp1;
                scheduleMarkers[1][j+1] = temp2;
                scheduleMarkers[2][j+1] = temp3;
                scheduleMarkers[3][j+1] = temp4;
            }   
        }
    }
    $('#scheduleList').html('');
    $(scheduleArr).each(function(i, arr){
        $('#scheduleList').append(arr);
    });
    
    displayAllPlaces(scheduleMarkers[1],scheduleMarkers[3]);
    for(var i = 0; i < scheduleMarkers[0].length; i++ ){
        console.log(i+"[0 : "+ scheduleMarkers[0][i] + ", 1 : " + scheduleMarkers[1][i] + ", 2 : " + scheduleMarkers[2][i] + "]");
    }
}
// 일정목록의 삼각형 클릭하면 일정 자세히 보여주면서 삼각형 모양 바꿔주는 함수
function toggleArrow(e){
    if(!$(e).parent().next().is(":visible"))
    {
        $(e).parent().next().slideDown();
        $(e).html("▲");
    }else{
        $(e).parent().next().slideUp();
        $(e).html("▼");
    }
};

// 일차 제거하는 함수
function deleteDay(ind){
    
    $(".daystyle").each(function(i, box) {
        if($(box).data("dateno")==ind)
            $(box).remove();
    });
    reorder();
    selectDay($($(".daystyle").get(0)).data('dateno'));

}

// 일차 선택하는 함수
function selectDay(ind){

    // 매개변수로 받은 인덱스로 div를 찾아 저장할 변수
    var selectedDay;

    // .daystyle 속성을 가진 요소들의 배열중
    // ind와 같은 번호의 요소를 selectedDay에 저장
    $('.daystyle').each(function(i, box) {
        if($(box).data('dateno')==ind)
            selectedDay = box;
    });

    // 몇일차인지 찾아서 바꿔줌
    $('#selectedDay').html($(selectedDay).find('span').html());
    $('#selectedDay').data('dateno',$(selectedDay).data('dateno'))

    // 여기서 data('dateno')=DATE_NO 조건을 만족하는 행을 가져와 일정 목록에 추가하면서
    // scheduleMarkers을 scheduleMarkers = new Array(new Array(), new Array());로 초기화
    // scheduleMarkers[0] 에 add()함수를 이용하여 SCHEDULE_NO를 추가
    // scheduleMarkers[1] 에 좌표 두개를 카카오맵 객체로 묶어서 add() 해야함

    // 아래는 테스트용 코드
    scheduleMarkers = new Array(new Array(), new Array(), new Array(), new Array());
    $('.schedule').each(function(i, el){
        scheduleMarkers[0].push($(el).data('scheduleno'));
        scheduleMarkers[1].push(new kakao.maps.LatLng(0, 0));
        scheduleMarkers[2].push('0');
        scheduleMarkers[3].push(null);
    }); 
    console.log(scheduleMarkers);
}

// 일차 정렬하여 몇일차인지 텍스트 바꿔줌
function reorder() {
    $(".daystyle").each(function(i, box) {
        $(box).find(".dayCount").html(i + 1 + "일차");
    });
};
