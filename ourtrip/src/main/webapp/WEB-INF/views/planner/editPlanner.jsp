<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html lang="ko">

<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">

<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
	integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh"
	crossorigin="anonymous">

<!-- jQuery -->
<script src="https://code.jquery.com/jquery-3.4.1.min.js"
	integrity="sha256-CSXorXvZcTkaix6Yvo6HppcZGetbYMGWSFlBw8HfCJo="
	crossorigin="anonymous"></script>
<!-- jQueryCSS -->
<link href="https://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css"
	rel="stylesheet" type="text/css" />
<!-- jQueryUI -->
<script type="text/javascript"
	src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>

<script
	src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
	integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo"
	crossorigin="anonymous"></script>

<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"
	integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6"
	crossorigin="anonymous"></script>

<!-- Web socket CDN -->
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/sockjs-client/1.4.0/sockjs.js"></script>

<!-- json2.js -->
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/json2/20160511/json2.js"></script>

<!-- 아이콘 -->
<script src="https://kit.fontawesome.com/76b49c6d9b.js" crossorigin="anonymous"></script>
<!-- 호환성 -->
<meta http-equiv="X-UA-Compatible" content="IE=edge">

<!-- 폰트 -->
<link
	href="https://fonts.googleapis.com/css?family=Nanum+Pen+Script|Ubuntu&display=swap"
	rel="stylesheet">

<!-- 공용 css -->
<!-- <link rel="stylesheet" href="${contextPath}/resources/css/common.css"> -->
<link rel="stylesheet" type="text/css" href="${contextPath}/resources/css/common.css" />
<link rel="stylesheet" type="text/css" href="${contextPath}/resources/css/planner.css" />


<!-- Web socket CDN -->
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/sockjs-client/1.4.0/sockjs.js"></script>

<!-- json2.js -->
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/json2/20160511/json2.js"></script>

<!-- kakao map sdk -->
<script src="//developers.kakao.com/sdk/js/kakao.min.js"></script>
<script type="text/javascript"
	src="//dapi.kakao.com/v2/maps/sdk.js?appkey=3265d67cbccb2a931046b989ef45ad5f&libraries=services,clusterer,drawing"></script>

<title>${plannerTitle}</title>
</head>

<body>
	<jsp:include page="../common/header.jsp" />
	<jsp:include page="../common/nav.jsp" />
	<button type="button" data-toggle="modal" data-target="#myModal"
		id="modalBtn" class="hide">Open Modal</button>

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
					<button type="button" class="ModalBtnCss2" data-dismiss="modal"
						onclick="goBack();">돌아가기</button>
					<button type="button" class="ModalBtnCss1" data-dismiss="modal"
						id="join">Join</button>
				</div>
			</div>
		</div>
	</div>

	<div class="container-fluid pt-5" style="border-top: 1px solid">
		<form action="#">
			<div class="row noselect">
				<div class="col-md-1"></div>
				<div class="col-md-4">
					<div class="main-back plannerHeader row ml-0 mr-0"
						style="text-align: center;">
						<div class="col-md-9 pl-2 pr-0">
							<input type="text" id="url" class="pl-2" readonly
								value="">
						</div>
						<div class="col-md-3">
							<button type="button" id="copy"
								class="plannerHeaderBtn btnColor1">복사</button>
						</div>
					</div>
				</div>
				<div class="col-md-3 pl-0">
					<div class="main-back plannerHeader row ml-0 mr-0"
						style="text-align: center;">
						<div class="col-md-1"></div>
						<div class="col-md-3">
							<span>총 경비 : </span>
						</div>
						<div class="col-md-5 pl-0 pr-0">
							<span id="totalcost">0 원</span>
						</div>
						<div class="col-md-3">
							<button type="button" class="plannerHeaderBtn btnColor1" id="calc">계산</button>
						</div>
					</div>
				</div>
				<div class="col-md-3 pl-0">
					<div class="main-back plannerHeader row ml-0">
						<div class="col-md-6 pl-2">
							<select name="userPermission" id="userPermission">
							</select>
						</div>
						<div class="col-md-3 pl-0">
							<button type="button" class="plannerHeaderBtn btnColor3"
								id="stealBtn">권한 삭제</button>
						</div>
						<div class="col-md-3 pl-0">
							<button type="button" class="plannerHeaderBtn btnColor1"
								id="grantBtn">권한 부여</button>
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
							<input class="plannerDay" id="startrip"	type="date" max="9999-12-31">
							<button type="button" class="btnColor1" id="startDateUpdateBtn">출발일 수정</button> 
						</div>
						<!-- 일자 카드 -->
						<div id="sortable" class="divContent"></div>

						<div class="updateBtns p-3">
							<div class="row mb-3">
								<div class="col-md-12">
									<button type="button" id="addDayBtn"
										class="updateBtn btnColor1" onclick="addDate();">일차
										추가</button>
								</div>
							</div>
							<div class="row">
								<div class="col-md-12">
									<button type="button" class="updateBtn btnColor1">이용
										방법</button>
								</div>
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
								</label> <input id="inputScheduleTitle" class="form-control mb-4"
									type="text" placeholder="일정제목을 입력해 주세요"
									style="font-size: 1.4rem;" autocomplete="off">
							</div>
							<div>
								<label for="inputScheduleCost">
									<h2>경비</h2>
								</label><br> <input id="inputScheduleCost"
									class="tripinfo inputScheduleCost form-control" type="number"
									placeholder="경비 입력" style="font-size: 1.4rem;" autocomplete="off"><br>
							</div>
							<div>
								<label for="inputScheduleTime">
									<h2>시간</h2>
								</label><br> <input id="inputScheduleTime"
									class="tripinfo inputScheduleTime form-control" type="time"
									placeholder="시간 입력" style="font-size: 1.4rem;" autocomplete="off"><br>
							</div>
							<div>
								<label for="inputScheduleMemo">
									<h2>메모</h2>
								</label><br>
								<textarea id="inputScheduleMemo"
									class="tripinfo inputScheduleMemo form-control"
									style="height: 150px; width: 98%; resize: none;"
									style="font-size: 1.4rem;" autocomplete="off"></textarea>
								<br>
							</div>
							<label for="inputScheduleLocationName">
								<h2>장소</h2>
							</label> <input class="inputScheduleLocationName form-control"
								id="inputScheduleLocationName" type="text"
								placeholder="장소 이름 입력" style="font-size: 1.4rem;" autocomplete="off">
							<div id="inputScheduleLocationArea">
								<div class="input-group my-3">
									<input class="inputScheduleLocation form-control"
										id="inputScheduleLocation" type="text" placeholder="장소검색"
										style="font-size: 1.4rem;" onkeypress="enterSearch(this);">
									<div class="input-group-append">
										<button class="btn btn-success" type="button" id="search-btn"
											onclick="searchKeyword();">검색</button>
									</div>

								</div>
								<div class="maplace" id="map"></div>

								<div id="btn-wrapper" class="mt-3 d-flex">
									<div class="flex-fill">
										<button type="button"
											class="btn btn-primary btn-block reco-btn">맛집</button>
									</div>
									<div class="flex-fill mx-3  ">
										<button type="button"
											class="btn btn-success btn-block reco-btn">관광지</button>
									</div>
									<div class="flex-fill">
										<button type="button" class="btn btn-info btn-block reco-btn">숙소</button>
									</div>
								</div>
							</div>

							<div class="updateBtns p-3">
								<div class="row mb-3">
									<div class="col-md-6">
										<button type="button" class="updateBtn btnColor1"
											id="scheduleUpdate">저장</button>
									</div>
									<div class="col-md-6">
										<button type="button" class="updateBtn btnColor1"
											id="addSchedule">일정 추가</button>
									</div>
								</div>
								<div class="row">
									<div class="col-md-6">
										<button type="button" class="updateBtn btnColor1" id="initMap"
											onclick="initMapBtn();">지도 초기화</button>
									</div>
									<div class="col-md-6">
										<button type="button" class="updateBtn btnColor3"
											id="removeSchedule">삭제</button>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
				<div class="col-md-3 pl-0" id="complaner">
					<div class="card divBorder noselect">
						<h2 data-dateno="0" class="card-header main-back plannerDivHeader"
							id="selectedDay">n일차</h2>
						<div class="card-body divContent p-3">
							<div class="plannerAccodian" id="scheduleList"></div>
							<div id="allMapPlace" style="width: 100%; height: 350px;"></div>
						</div>
					</div>
				</div>
				<!-- complaner end -->
				<div class="col-md-3" id="chatpart"
					style="padding: 0px 0px 0px 0px; overflow: hidden;">
					<div class="divBorder chatBGC">
						<h2 class="card-header main-back plannerDivHeader noselect">채팅</h2>
						<div class="chatarea p-3"></div>

						<div class="row p-4" style="height: 18%;">
							<div class="col-md-9">
								<textarea id="mymsg" class="inputMsg"></textarea>
							</div>
							<div class="col-md-3 pl-0">
								<button type="button" id="send" class="sendBtn">보내기</button>
							</div>
						</div>
					</div>
				</div>
				<div class="col-md-1"></div>
			</div>
		</form>
	</div>
	<jsp:include page="../common/footer.jsp"/>
</body>

<script type="text/javascript" src="${contextPath}/resources/js/map.js"></script>
<script>

//페이지 로딩 시 일정 수에 맞게 위치정보 Array에 자리생성
// 0 = schedule 번호
// 1 = 카카오 위치 객체
// 2 = lat + lng
// 3 = 인포 윈도우

var scheduleMarkers = new Array();
var planner = new Object();
var days = new Array();
var loadingInfo = 0;
var loadingAddr = 0;
var dayIndex = -1;
var memberNo = null;
var memberNickName = null;
var permission = null;
var joinMember = new Array();

$(function() {
	memberNo = '${loginMember.memberNo}';
	if(memberNo == ''){
		memberNo = '-1';
	}
	memberNickName = '${loginMember.memberNickName}';
	if(memberNickName == ''){
		memberNickName = '비회원';
	}
	var plannerInfo = '${plannerInfo}';
	var chatList = '${chatList}';
	var joinUserArray = '${joinUserArray}';
	var joinUserJson = JSON.parse(joinUserArray);
	var plannerJson = JSON.parse(plannerInfo);
	var chatListJson = JSON.parse(chatList);
	var profilePath = '${profilePath}';

	
	$('#url').val(window.location.href);
	$('#startrip').val(plannerJson.plannerStartDT);
	$("#join").click(function(){
		initChatting(chatListJson);
		initPlanner(plannerJson);
		initJoinMember(joinUserJson);
		sock.send(JSON.stringify({pno:planner.no, type: 'JOIN', memberNo: memberNo, memberNickName: memberNickName}));
		for(var i in joinMember){
			if(joinMember[i].memberNo == memberNo){
				permission = joinMember[i].plannerPermission;
			}
		}
	})
	
    // 페이지 입장 시 참여버튼 모달 출력
    $("#modalBtn").click();
    
    
});
var iwContent = '';
function initPlanner(pj){
	planner.no = pj.plannerNo;
	planner.title = pj.plannerTitle;
	planner.pwd = pj.plannerPwd;
	planner.cost = pj.plannerCost;
	planner.createDT = pj.plannerCreateDT;
	planner.modifyDT = pj.plannerModifyDT;
	planner.startDT = pj.plannerStartDT;
	planner.publicYN = pj.plannerPublicYN;
	planner.deleteYN = pj.plannerDeleteYN;
	planner.expiry = pj.plannerExpiry;
	planner.count = pj.plannerCount;
	planner.url = pj.plannerUrl;
	planner.groupCode = pj.groupCode;
	for(var d in pj.days){
		var day = new Object();
		day.no = pj.days[d].dateNo;
		day.tripDate = pj.days[d].tripDate;
		day.plannerNo = planner.no;
		day.schedules = new Array();
		var scheduleMarker = new Array();
		for(var s in pj.days[d].schedules){
			var schedule = new Object();
			schedule.no = pj.days[d].schedules[s].scheduleNo;
			schedule.title = pj.days[d].schedules[s].scheduleTitle;
			schedule.cost = pj.days[d].schedules[s].scheduleCost;
			schedule.time = pj.days[d].schedules[s].scheduleTime;
			schedule.memo = pj.days[d].schedules[s].scheduleMemo;
			schedule.locationNM = pj.days[d].schedules[s].scheduleLocationNM;
			schedule.lat = pj.days[d].schedules[s].scheduleLat;
			schedule.lng = pj.days[d].schedules[s].scheduleLng;
			schedule.infoWindow = pj.days[d].schedules[s].infoWindow;
			schedule.dateNo = pj.days[d].dateNo;
			day.schedules.push(schedule);
			
			//페이지 로딩 시 일정 수에 맞게 위치정보 Array에 자리생성
			// 0 = schedule 번호
			// 1 = 카카오 위치 객체
			// 2 = lat + lng
			// 3 = 인포 윈도우
			scheduleLatLng = new kakao.maps.LatLng(schedule.lat,schedule.lng)
			scheduleMarker.push({"sno" : schedule.no, "LatLng" : scheduleLatLng, "unselect" : (scheduleLatLng.getLat()+scheduleLatLng.getLng()==0?true:false), "infoWindow" : null}); 

			loadingInfo += 1;
			
			getScheduleAddr(scheduleLatLng,schedule.locationNM,schedule.no).then(function(args){
				for(var i in scheduleMarkers){
					for(var j in scheduleMarkers[i].scheduleMarker){
						console.log(scheduleMarkers[i].scheduleMarker[j].sno + ' =sno= ' +  args[0]);
						if(scheduleMarkers[i].scheduleMarker[j].sno == args[0]){
							scheduleMarkers[i].scheduleMarker[j].infoWindow = args[1];
							loadingAddr += 1;
							console.log()
							console.log(loadingAddr + ' == ' + loadingInfo);
							if(loadingInfo == loadingAddr){
								for(var k = 0; k < days.length; k++){
									createDate(days[k].no,false);
								}
								reorder();
							}
						}
					}
				}
			});
		}
		scheduleMarkers.push({"dno" : day.no, "scheduleMarker" : scheduleMarker});
		days.push(day);
	}
	console.log("planner");
	console.log(planner);
	console.log("days");
	console.log(days);
	sortDaysUserTripdate(days);
	console.log(days);
	console.log("scheduleMarkers");
	console.log(scheduleMarkers);
	//setTimeout(function(){
	for(var i = 0; i < days.length; i++){
		sortSchedules(days[i].schedules);
	}
	//}, 1000);
}
function initChatting(chatList){
	for(var i in chatList){
		if(chatList[i].memberNo == '-1'){
			inputChat(mkChatMsg('null','비회원',chatList[i].chatContent,chatList[i].chatTime));
		}else if(chatList[i].memberNo == memberNo) {
	     	// inputChat = 채팅 내역에 채팅창 올리는 함수
	        // mkMyChatMsg = 내가 보낸 메세지로 채팅창 만드는 함수
	        // mkMyChatMsg 매개변수 = (msgContent,msgTime)
	        inputChat(mkMyChatMsg(chatList[i].chatContent,chatList[i].chatTime));
		}else {
			// inputChat = 채팅 내역에 채팅창 올리는 함수
	        // mkChatMsg = 다른사람이 보낸 메세지로 채팅창 만드는 함수
	        // mkChatMsg 매개변수 = (profileImg,memberNo,msgContent,msgTime)
	        inputChat(mkChatMsg(chatList[i].imagePath,chatList[i].memberNickName,chatList[i].chatContent,chatList[i].chatTime));
		}
	}
}
//=======================================================================================//
//====================================== 변환 관련 함수  ======================================//
//=======================================================================================//
function timeToTime(time){
	var str = time.replace(/(.{2})/g,"$1:").slice(0,-1);
	return str;
} 

//=======================================================================================//
//====================================== 정렬 관련 함수  ======================================//
//=======================================================================================//

function sortSchedules(schedules){
	var dno = schedules.dateNo;
	var dayIdx = -1;
	var i = null;
	var j = null;
	for(i in scheduleMarkers){
		if(scheduleMarkers[i].dno == dno)
			dayIdx = i;
	}
	try{
		for(i = schedules.length-1; i > 0; i--){
	        for(j = 0; j < i; j++){
	        	if(schedules[j+1].time < schedules[j].time){
	                var temp = schedules[j];
	                var mTemp = scheduleMarkers[dayIdx].scheduleMarker[j];
	                schedules[j] = schedules[j+1];
	                scheduleMarkers[dayIdx].scheduleMarker[j]=scheduleMarkers[dayIdx].scheduleMarker[j+1]
	                schedules[j+1] = temp;
	                scheduleMarkers[dayIdx].scheduleMarker[j+1] = mTemp;
	            }   
		    }
		}
	}catch(e){
		console.log('정렬오류 발생' + e.stack);
		console.log('error : i = ' + i + ', j = ' + j + ', idx : ' + dayIdx);
		console.log(scheduleMarkers[dayIdx]);
	}
}

function sortSchedule(){
    var scheduleArr = $(".schedule");
    
  	//scheduleMarkers에서 ($('#selectedDay').data('dateno'))에 해당하는 날짜의 데이터를  daySchedule에 저장
    var daySchedule = extractDayMarker($('#selectedDay').data('dateno'));
  
    for(var i = scheduleArr.length-1; i > 0; i--){
        for(var j = 0; j < i; j++){
            if($(scheduleArr[j+1]).find(".scheduleTime").val() 
            < $(scheduleArr[j]).find(".scheduleTime").val()){
                var temp = scheduleArr[j];
                var tempSchedule = daySchedule.scheduleMarker[j];
                scheduleArr[j] = scheduleArr[j+1];
                daySchedule.scheduleMarker[j] = daySchedule.scheduleMarker[j+1];
                scheduleArr[j+1] = temp;
                daySchedule.scheduleMarker[j+1] = tempSchedule;
            }   
        }
    } 
    
    $('#scheduleList').html('');
    $(scheduleArr).each(function(i, arr){
        $('#scheduleList').append(arr);
    });
    var locationArr = new Array();
    var infoWindowArr = new Array()
    displayAllPlaces(daySchedule,allMap,allMarkers);
}

function sortDays(days){
	for(var i = days.length-1; i > 0; i--){
        for(var j = 0; j < i; j++){
            if(days[j+1].tripDate < days[j].tripDate){
                var temp = days[j];
                days[j] = days[j+1];
                days[j+1] = temp;
            }   
        }
    }
}

function sortDaysUserTripdate(){
	var tempDays = new Array();
	for(var i in days){
		for(var j in days){
			if(parseInt(days[j].tripDate,10) == i){
				tempDays.push(days[j]);
			}
		}
	}
	days = tempDays;
}

function sortDaysUseDateNo(orderDate){
	console.log(orderDate);
	var tempDays = new Array();
	for(var i in orderDate){
		for(var j in orderDate){
			try{
				if(orderDate[i].dno == days[j].no){
					days[j].tripDate = orderDate[i].order;
					tempDays.push(days[j]);
				}
			}catch(e){
				console.log(e.stack);
				console.log('i : ' + i + ', j : ' + j);
			}
		}
	}
	days = tempDays;
}

//일차 정렬하여 몇일차인지 텍스트 바꿔줌
function reorder() {
	var dateInfo = new Array();
    $(".daystyle").each(function(i, box) {
    	var dno = $(box).data('dateno');
        $(box).find(".dayCount").html(i + 1 + "일차");
        var before = $(box).data('dateorder')
        $(box).data('dateorder',i);
        dateInfo.push({order:i,dno:$(box).data('dateno')});
    });
    //sock.send로 afterDayOrder 보내줌
	sock.send(JSON.stringify({pno:planner.no, type: 'orderDate', id: "${loginMember.getMemberEmail()}", dateInfo:dateInfo}));

}

//=======================================================================================//
//====================================== 지도 관련 함수  ======================================//
//=======================================================================================//

function getScheduleAddr(templocation,locationName,scheduleNo){
	if(templocation.getLng() + templocation.getLat() != 0){
		return new Promise(function(resolve, reject){
			try{
				var contentArr= new Array();
				contentArr.push(scheduleNo);
				searchDetailAddrFromCoords(templocation, function(result, status) {
				    if (status === kakao.maps.services.Status.OK) {
				        var detailAddr = !!result[0].road_address ? '<div style="font-size: 14px;">도로명주소 : ' + result[0].road_address.address_name + '</div>' : '';
				        detailAddr += '<div style="font-size: 14px;">지번 주소 : ' + result[0].address.address_name + '</div>';
				        
			        	content = 	'<div class="bAddr">' +
				                        '<span class="title">' + locationName + '</span>' + 
				                        detailAddr + 
			                        '</div>';
				      	contentArr.push(content);
				        resolve(contentArr);
				    }else if (status === kakao.maps.services.Status.ZERO_RESULT) {
				        // 검색결과가 없는경우 해야할 처리가 있다면 이곳에 작성해 주세요
					    content = 
					    	'<div class="bAddr">' +
		                        '<span class="title">' + locationName + '</span>' + 
		                    '</div>';
					    contentArr.push(content);
				        resolve(contentArr);
				    } else if (status === kakao.maps.services.Status.ERROR) {
				        // 에러로 인해 검색결과가 나오지 않은 경우 해야할 처리가 있다면 이곳에 작성해 주세요
				    	console.log("에러로 인해 검색결과가 나오지 않은 경우");
				    }
				});
				
			} catch(error){
				console.log(error.stack);
				reject(error)
			}
		});
	}else{
		return new Promise(function(resolve, reject){
			try{
				var contentArr= new Array();
				contentArr.push(scheduleNo);
				contentArr.push(null);
				resolve(contentArr);
			}catch(error){
				console.log(error.stack);
				reject(error);
			}
		});
	}
} 

//scheduleMarkers에서 dno에 해당하는 날짜의 데이터를 반환
function extractDayMarker(dno){
	for(var i = 0; i < scheduleMarkers.length; i++){
		if(scheduleMarkers[i].dno == dno){
			return scheduleMarkers[i];
		}
	}
}

//scheduleMarkers에서 dno에 해당하는 날짜의 sno에 해당하는 인덱스 반환
function extractScheduleMarker(dno,sno){
	var dayMarker = extractDayMarker(dno);
	var scheMarker = null;
	for(var i in dayMarker.scheduleMarker){
		if(dayMarker.scheduleMarker[i].sno == sno)
			scheMarker = i;
	}
	return scheMarker;
}

//=======================================================================================//
//====================================== Day 관련 함수 ======================================//
//=======================================================================================//

//일차 추가하는 함수
// 매개변수 dateNo = PLANNER_DATE 테이블의 DATE_NO값을 넣어야함
var tempDayno = 100;

function addDate(){
	// date_no는 DB 에서 가져옴
	if(permission > 1)
		sock.send(JSON.stringify({pno:planner.no, type: 'addDate', id: "${loginMember.getMemberEmail()}"}));
	else
		alert('권한이 없습니다.');
	// trip_date 값은  reorder에서 수정
	
	
}

function createDate(dateNo,reorderBoolean){

	var dayIndex = -1;
	
	for(var i in days){
		if (days[i].no == dateNo)
			dayIndex = days[i].tripDate;
	}
	console.log(dayIndex+"일차");
    // 일차 만드는 HTML코드, dayInd는 테스트용이고 dateNo로 바꿔줘야함
    var dayForm = 
    '<div data-dateorder="' + dayIndex + '" data-dateno="' + dateNo + '" id="days" class="daystyle" onclick="selectDay(' + dateNo + ');">' +
    '<span class="dayCount pl-2">' + (parseInt(dayIndex,10) + 1) + '일차</span>' +
    '<button type="button" class="dayDeleteBtn btnColor3" onclick="deleteDay(' + dateNo + ');">-</button>' +
    '</div>';

    // 일차 목록에 추가함
    $("#sortable").append(dayForm)
	
    // 추가하고 정렬(몇일차인지 계산해서 텍스트 바꿔줌)
    if(reorderBoolean)
   		reorder();
    
}

//일차 선택하는 함수
function selectDay(no){

    // 매개변수로 받은 인덱스로 div를 찾아 저장할 변수
    var selectedDay;

    // .daystyle 속성을 가진 요소들의 배열중
    // ind와 같은 번호의 요소를 selectedDay에 저장
    $('.daystyle').each(function(i, box) {
        if($(box).data('dateno') == no)
            selectedDay = box;
    });

    // 몇일차인지 찾아서 바꿔줌
    $('#selectedDay').html($(selectedDay).find('span').html());
    $('#selectedDay').data('dateno',no)

    // 여기서 data('dateno')=DATE_NO 조건을 만족하는 행을 가져와 일정 목록에 추가하면서
    // scheduleMarkers을 scheduleMarkers = new Array(new Array(), new Array());로 초기화
    // scheduleMarkers[0] 에 add()함수를 이용하여 SCHEDULE_NO를 추가
    // scheduleMarkers[1] 에 좌표 두개를 카카오맵 객체로 묶어서 add() 해야함
    $("#scheduleList").html('');
    
    var existMarker = false;
    var todayMarker = null;
    for(var i = 0; i < days.length; i++){
    	if(days[i].no == no){
    		for(var j = 0; j < days[i].schedules.length; j++){
    			var sno = days[i].schedules[j].no;
    			var stitle = days[i].schedules[j].title;
    			var stime = timeToTime(days[i].schedules[j].time);
    			var scost = days[i].schedules[j].cost;
    			var smemo = days[i].schedules[j].memo;
    			var slocationName = days[i].schedules[j].locationNM;
   				createSchedule(sno,stitle,stime,scost,smemo,slocationName);
   				todayMarker = extractDayMarker(no);
    		}
    	}
    }

    sortSchedule();
    for(var i in days){
    	if(days[i].no == no)
    		sortSchedules(days[i].schedules);
    }
    
	for(var i in todayMarker.scheduleMarker){
		if(!todayMarker.scheduleMarker[i].unselect)
			existMarker = true;
	}
	if(existMarker){
		displayAllPlaces(extractDayMarker(no),allMap,allMarkers);
	}else{
		initMapBtn();
		removeAllMarker();
	}
    infowindowAll.close();
}

//일차 제거하는 함수
function deleteDay(ind){
	if(permission > 1)
		sock.send(JSON.stringify({pno:planner.no, type: 'deleteDate', id: "${loginMember.getMemberEmail()}", dno:ind}));
	else
		alert('권한이 없습니다.');
}
function deleteDate(ind){
	dayIndex--;
    $(".daystyle").each(function(i, box) {
        if($(box).data("dateno")==ind)
            $(box).remove();
    });
    reorder();
    selectDay($($(".daystyle").get(0)).data('dateno'));
}

//=======================================================================================//
//==================================== Schedule 관련 함수 ===================================//
//=======================================================================================//

//일정 만드는 코드 반환하는 함수
//첫번째 매개변수는 SCHEDULE 테이블에서 SCHEDULE_NO값이 들어가야 하는데
//새로운 일정을 만드는 것이니 시퀀스 NEXTVAL 얻어와서 넣어야함
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

var tempScheduleNo = 100;
//일정 추가하는 함수

$('#addSchedule').click(function(){
	var dno = $('#selectedDay').data('dateno');
	var title = $('#inputScheduleTitle').val();
    var time = $('#inputScheduleTime').val();
    var cost = $('#inputScheduleCost').val();
    var memo = $('#inputScheduleMemo').val();
    var location = $('#inputScheduleLocationName').val();
    
    if(dno == 0){
		alert('일자를 선택해주세요');
	}else if(title == ''){
        alert('제목을 입력해주세요');
    }else if(lat + lng == 0){
        alert('위치를 선택해주세요');
    }else if(time == ''){
        alert('시간을 입력해주세요');
    }else if(location == ''){
        alert('장소 이름을 입력해주세요');
    }else{
    	if(permission > 1){
	    	sock.send(JSON.stringify({pno:planner.no, type: 'addSchedule', id: memberNo,
	    		dno: dno, title: title, time: time, location: location, cost: cost, memo: memo, lat: lat, lng: lng, iwContent: iwContent}));
    	}
   		else
   			alert('권한이 없습니다.');
    }
});

function addSchedule(dno,sno,title,time,location,cost,memo,llat,llng,liwContent,lmemberNo){

    // 새로운 일정을 만드는 것이니 시퀀스 NEXTVAL 얻어와서 넣어야함
    // 테스트때 임시로 createNo 변수 사용
    var dayIdx;
	for(var i in days){
		if(days[i].no == dno){
			days[i].schedules.push({no:sno, title:title, cost:cost, time:removeColonFromTime(time), memo:memo})
			dayIdx = i;
		}
	}
	// 마커 배열에 새로운 자리를 만듦
	var today = extractDayMarker(dno);

	var locationLatLng = new kakao.maps.LatLng(llat,llng)
	today.scheduleMarker.push({"sno" : sno, "LatLng" : locationLatLng, "unselect" : (llat+llng==0?true:false), "infoWindow" : liwContent});
	
	if($('#selectedDay').data('dateno') == dno){
		createSchedule(sno,title,time,cost,memo,location);
		if(memberNo == lmemberNo)
			selectSchedule(sno);
	}
}

function selectScheduleNofromDays(no){
	var dayIdx = -1;
	var scheIdx = -1;
	if(dayIdx == -1){
		for(var i in days){
			for(var j in days[i].schedules){
				if(days[i].schedules[j].no == no){
					dayIdx = i;
					scheIdx = j;
				}
			}
		}
	}
	
	$('.schedule').each(function(i, item){
		if($(item).data('scheduleno') == no){
			scheDiv = item; 
		}
	});
	
    return new Array(dayIdx, scheIdx);
}
function selectScheduleNo(no){
	var dayIdx = -1;
	var scheIdx = -1;
	var scheDiv = null;
	if(dayIdx == -1){
		for(var i in scheduleMarkers){
			for(var j in scheduleMarkers[i].scheduleMarker){
				if(scheduleMarkers[i].scheduleMarker[j].sno == no){
					dayIdx = i;
					scheIdx = j;
				}
			}
		}
	}
	
	$('.schedule').each(function(i, item){
		if($(item).data('scheduleno') == no){
			scheDiv = item; 
		}
	});
	
    return new Array(dayIdx, scheIdx, scheDiv);
}

function selectSchedule(no){
    
	var scheInfo = selectScheduleNo(no);
	
    $('#inputScheduleTitle').val($(scheInfo[2]).find(".scheduleTitle").html());
    $('#inputScheduleTime').val($(scheInfo[2]).find(".scheduleTime").val());
    $('#inputScheduleCost').val($(scheInfo[2]).find(".scheduleCost").html());
    $('#inputScheduleMemo').val($(scheInfo[2]).find(".scheduleMemo").html());
    $('#inputScheduleLocationName').val($(scheInfo[2]).find(".scheduleLocation").html());
    $('#scheduleInfo').data('scheduleno',no);

    if(!scheduleMarkers[scheInfo[0]].scheduleMarker[scheInfo[1]].unselect){
        allMap.panTo(scheduleMarkers[scheInfo[0]].scheduleMarker[scheInfo[1]].LatLng);
        map.panTo(scheduleMarkers[scheInfo[0]].scheduleMarker[scheInfo[1]].LatLng);
    }
    
    // marker.setPosition(mouseEvent.latLng);
    // marker.setMap(map);
    initMarker(scheduleMarkers[scheInfo[0]].scheduleMarker[scheInfo[1]].LatLng,scheduleMarkers[scheInfo[0]].scheduleMarker[scheInfo[1]].infoWindow);
    
    lat = scheduleMarkers[scheInfo[0]].scheduleMarker[scheInfo[1]].LatLng.getLat();
    lng = scheduleMarkers[scheInfo[0]].scheduleMarker[scheInfo[1]].LatLng.getLng();
    console.log('lat : ' + lat);
    console.log('lat : ' + lng);
};

function updateSchedule(sno,title,time,location,cost,memo,llat,llng,liwContent){
	
	var scheInfo = selectScheduleNo(sno);
	var scheduleIdx = selectScheduleNofromDays(sno);
	
	$(scheInfo[2]).find(".scheduleTitle").html(title);
	$(scheInfo[2]).find(".scheduleTime").val(time);
	$(scheInfo[2]).find(".scheduleLocation").html(location);
	$(scheInfo[2]).find(".scheduleCost").html(cost);
	$(scheInfo[2]).find(".scheduleMemo").html(memo);
	
	scheduleLatLng = new kakao.maps.LatLng(llat, llng);
	scheduleMarkers[scheInfo[0]].scheduleMarker[scheInfo[1]].LatLng = scheduleLatLng;
	scheduleMarkers[scheInfo[0]].scheduleMarker[scheInfo[1]].unselect = llat + llng == 0 ?true:false;
	scheduleMarkers[scheInfo[0]].scheduleMarker[scheInfo[1]].infoWindow = liwContent;
	
	days[scheduleIdx[0]].schedules[scheduleIdx[1]].title = title;
	days[scheduleIdx[0]].schedules[scheduleIdx[1]].time = time;
	days[scheduleIdx[0]].schedules[scheduleIdx[1]].location = location;
	days[scheduleIdx[0]].schedules[scheduleIdx[1]].cost = cost;
	days[scheduleIdx[0]].schedules[scheduleIdx[1]].memo = memo;
	days[scheduleIdx[0]].schedules[scheduleIdx[1]].lat = llat;
	days[scheduleIdx[0]].schedules[scheduleIdx[1]].lng = llng;
	
	sortSchedule();
	
	scheInfo = selectScheduleNo($('#scheduleInfo').data('scheduleno'));
	// initMarker(scheduleMarkers[1][dupIdx], scheduleMarkers[3][dupIdx]);
	initMarker(scheduleMarkers[scheInfo[0]].scheduleMarker[scheInfo[1]].LatLng, scheduleMarkers[scheInfo[0]].scheduleMarker[scheInfo[1]].infoWindow);
}



$('#scheduleUpdate').click(function(){
	
	var sno = $('#scheduleInfo').data('scheduleno');
   	var title = $('#inputScheduleTitle').val();
    var time = $('#inputScheduleTime').val();
    var location = $('#inputScheduleLocationName').val();
    var cost = $('#inputScheduleCost').val();
    var memo = $('#inputScheduleMemo').val();
	
	if(sno == 0){
        alert('일정을 선택해주세요');
    }else if(title == ''){
        alert('제목을 입력해주세요');
    }else if(lat + lng == 0){
        alert('위치를 선택해주세요');
    }else if(time == ''){
        alert('시간을 입력해주세요');
    }else if(location == ''){
    	alert('장소를 입력해주세요');
    }else{
    	if(permission > 1){
	    	sock.send(JSON.stringify({pno:planner.no, type: 'updateSchedule', id: memberNo,
	    		sno: sno, title: title, time: time, location: location, cost: cost, memo: memo, lat: lat, lng: lng, iwContent: iwContent}));
    	}
   		else
   			alert('권한이 없습니다.');
    }
});

function removeSchedule(dno,sno){

	for(var i in scheduleMarkers){
    	if(scheduleMarkers[i].dno == dno){
    		for(var j in scheduleMarkers[i].scheduleMarker){
    			if(scheduleMarkers[i].scheduleMarker[j].sno == sno){
    				scheduleMarkers[i].scheduleMarker.splice(j,1);
    			}
    		}
    	}
    	if(days[i].no == dno){
    		for(var j in days[i].schedules){
    			if(days[i].schedules[j].no == sno){
    				days[i].schedules.splice(j,1);
    			}
    		}
    	}
    }
    
	// #selectedDay의 data('dateno')값이 dno와 같으면 .schedule 중에 data('scheduleno')가 sno와 같은 div 삭제
	if($('#selectedDay').data('dateno') == dno){
		$('.schedule').each(function(i, item){
			if($(item).data('scheduleno') == sno){
				$(item).remove();
			}
		})
	}
    
	sortSchedule();
    for(var i in days){
    	if(days[i].no == dno)
    		sortSchedules(days[i].schedules);
    }
}

$('#removeSchedule').click(function(){
    if($('#scheduleInfo').data('scheduleno')==0){
        alert('일정을 선택해주세요');
    } else {
    	var sno = $('#scheduleInfo').data('scheduleno');
    	var dno = $('#selectedDay').data('dateno');
    	
    	if(permission > 1){
	    	sock.send(JSON.stringify({pno:planner.no, type: 'removeSchedule', id: memberNo,
	    		dno: dno, sno: sno}));
    	}
   		else
   			alert('권한이 없습니다.');
    	
    	$('#inputScheduleTitle').val('');
        $('#inputScheduleTime').val('');
        $('#inputScheduleCost').val('');
        $('#inputScheduleMemo').val('');
        $('#inputScheduleLocationName').val('');
        $('#scheduleInfo').data('scheduleno',0);
    }
});


//=======================================================================================//
//==================================== Websocket 관련 함수 ==================================//
//=======================================================================================//

//웹소켓을 지정한 url로 연결한다.
let sock = new SockJS("<c:url value="/echo"/>");
sock.onmessage = onMessage;
sock.onclose = onClose;

// 서버로부터 메시지를 받았을 때
function onMessage(msg) {
	var jsonData = msg.data;
	var data = JSON.parse(jsonData);
	switch(data['type']){
	case 'msg':
		if(data['memberNo'] == '-1'){
			inputChat(mkChatMsg('null','비회원',data['content'],data['time']));
		}else if(data['memberNo'] == memberNo){
	     	// inputChat = 채팅 내역에 채팅창 올리는 함수
	        // mkMyChatMsg = 내가 보낸 메세지로 채팅창 만드는 함수
	        // mkMyChatMsg 매개변수 = (msgContent,msgTime)
	        inputChat(mkMyChatMsg(data['content'],data['time']));
		}else {
			// inputChat = 채팅 내역에 채팅창 올리는 함수
	        // mkChatMsg = 다른사람이 보낸 메세지로 채팅창 만드는 함수
	        // mkChatMsg 매개변수 = (profileImg,memberNo,msgContent,msgTime)
	        inputChat(mkChatMsg(data['imagePath'],data['memberNickName'],data['content'],data['time']));
		}
		break;
	case 'addDate': 
		days.push({no:data['dno'],tripDate:-1,plannerNo:planner.no,schedules:new Array()});
		scheduleMarkers.push(
				{dno:data['dno'],scheduleMarker:new Array({sno:data['sno'],LatLng:new kakao.maps.LatLng(0,0),unselect:true,infoWindow:null})});
		createDate(data['dno'],true);
		addSchedule(data['dno'],data['sno'],'제목 없음','','미정',0,'',0,0,null,memberNo);
		break;
	case 'deleteDate': 
		deleteDate(data['dno']);
		break;
	case 'updateSchedule':
		updateSchedule(data['sno'],data['title'],data['time'],data['location'],data['cost'],data['memo'],data['lat'],data['lng'],data['iwContent'])
		break;
	case 'addSchedule': 
		addSchedule(data['dno'],data['sno'],data['title'],data['time'],data['location'],data['cost'],data['memo'],data['lat'],data['lng'],data['iwContent'],memberNo)
		sortSchedule(data['sno']);
	    for(var i in days){
	    	if(days[i].no == data['dno']){
	    		sortSchedules(days[i].schedules);
	    	}
	    }
		break;
	case 'removeSchedule': 
		removeSchedule(data['dno'],data['sno']);
		sortSchedule(data['sno']);
	    for(var i in days){
	    	if(days[i].no == data['dno']){
	    		sortSchedules(days[i].schedules);
	    	}
	    }
		break;
	case 'JOIN':
		if(data['newJoinUser'] != undefined)
			joinMember.push(data['newJoinUser']);
		try{
			var userOption = 
				'<option value="' + data['newJoinUser'].memberNo + '">' + data['newJoinUser'].memberNickName + '</option>'
			$('#userPermission').append(userOption);
		}catch(e){}
		break;
	case 'permission':
		grantPermission(data['grantMemberNo'],data['permission']);
		if(data['grantMemberNo'] == memberNo){
			permission = data['permission'];
		}
		break;
	case 'sumCost':
		$('#totalcost').html(data['content'] + '원');
		break;
	case 'orderDate':
		console.log(days);
		sortDaysUseDateNo(data['dateInfo']);
		console.log(days);
		$('#sortable').html('');
		for(var i in days){
			createDate(days[i].no,false);
		}
		for(var i in days){
			console.log(days[i].no + ", " + $('#selectedDay').data('dateno'));
			if(days[i].no == $('#selectedDay').data('dateno')){
				$('#selectedDay').html((parseInt(days[i].tripDate,10) + 1 )+'일차');
			}
		}
		break;
	case 'startDate':
		$('#startrip').val(data['content']);
		break;
	}
		
	

}

// 서버와 연결을 끊었을 때
function onClose(evt) {
	$("#data").append("연결 끊김");
}

//=======================================================================================//
//====================================== 채팅 관련 함수  ======================================//
//=======================================================================================//

// 다른사람이 보낸 채팅 메세지 만들어서 리턴하는 함수
function mkChatMsg(profileImg,userId,msgContent,msgTime){
	
	var imagePath = null;
	
	if(profileImg.includes('http')){
		imagePath = profileImg;
	}else if(profileImg == 'null'){
		imagePath = '${contextPath}/resources/images/default-profile.png'
	}else if(profileImg == ''){
		imagePath = '${contextPath}/resources/images/default-profile.png'
	}else {
		imagePath = '${contextPath}/resources' + profileImg.substring(profileImg.indexOf('/'));
	}
	
    var chatMsg =
    '<div class="chatbox overhidden">' +
    '<div>' +
    '<img src = "' + imagePath + '" class="profileImg">' +
    '<span class="userId">' + userId + '</span>' +
    '</div>' +
    '<div>' +
    '<div class="message">' +
    '<span>' + msgContent + '</span>' +
    '</div>' +
    '<span class="messageTime">' + msgTime + '</span>' +
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

//=======================================================================================//
//======================================== 권한 함수 ========================================//
//=======================================================================================//

function initJoinMember(joinUserArray){
	$('#userPermission').html('');
	var color = null;
	var icon = '';
	var i = 0;
	var j = 0;
	while(i < joinUserArray.length){
		if(joinUserArray[i].memberNo != -1){
			joinMember.push(joinUserArray[i]);
			j++;
		}
		i++;
	}
	for(var i = 0; i < joinMember.length; i++){
		icon = '';
		switch(joinMember[i].plannerPermission){
			case '1':	color = '#C8FFFF';	break;
			case '2':	color = '#32F1FF';	icon = '☆';	break;
			case '3':	color = '#0AC9FF';	icon = '★';	break;
		}		
		var userOption = 
			'<option style="background:' + color + '" value="' + joinMember[i].memberNo + '">' + joinMember[i].memberNickName + icon + '</option>'
		$('#userPermission').append(userOption);
	}
	// <option value="memberNo">닉네임 <option>
}

$('#grantBtn').click(function(){
	var grantMemberNo = $('#userPermission').val();
	if(permission > 2)
		sock.send(JSON.stringify({pno:planner.no, type: 'permission', memberNo: memberNo, permission: '2', grantMemberNo:grantMemberNo}));
	else
		alert('권한이 없습니다.');
});

$('#stealBtn').click(function(){
	var grantMemberNo = $('#userPermission').val();
	if(permission > 2)
		sock.send(JSON.stringify({pno:planner.no, type: 'permission', memberNo: memberNo, permission: '1', grantMemberNo:grantMemberNo}));
	else
		alert('권한이 없습니다.');
});

// 매개변수로 받은 멤버 넘버의 권한을 매개변수로 받은 permission으로 바꿈
function grantPermission(grantMemberNo,permission){
	for(var i in joinMember){
		if(joinMember[i].memberNo == grantMemberNo){
			joinMember[i].plannerPermission = permission;
			changePermissionColor(i);
		}
	}
}


function changePermissionColor(i){
	joinMember[i].memberNo;
	var color = '#C8FFFF';
	var icon = '';
	switch(joinMember[i].plannerPermission){
		case '1':	color = '#C8FFFF';	break;
		case '2':	color = '#32F1FF';	icon = '☆';	break;
		case '3':	color = '#0AC9FF';	icon = '★';	break;
	}		
	$('option').each(function(j,item){
		if(joinMember[i].memberNo == $(item).attr('value')){
			$(item).attr('style','background:' + color + ';');
			$(item).html(joinMember[i].memberNickName+icon);
		}
	});
}

//=======================================================================================//
//======================================== 기타 함수 ========================================//
//=======================================================================================//

$(function () {
        
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
        var msg = document.getElementById("mymsg").value;
        msg = msg.replace(/(?:\r\n|\r|\n)/g, '<br/>');
        
        // 채팅 입력창 비어있지 않으면 실행
        if(msg != '' && msg != '<br/>'){
            sock.send(JSON.stringify({pno:planner.no, type: 'msg', memberNo: memberNo, memberNickName: memberNickName, content: msg, imagePath:'${profilePath}'}));
        }

        // 메세지 전송 후 채팅 입력창 비워줌
        $('#mymsg').val('');
    })

    // 일차 마우스로 이동 가능하게 하고 정렬하는 함수
    $("#sortable").disableSelection();
    $("#sortable").sortable({
        placeholder:".daystyleHighlight",
        // 드래그 시작했을 때 작동
        start: function(event, ui) {
        	if(permission < 2)
        		alert('권한이 없습니다');
        	else
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
    
    $('#mymsg').keyup(function (evt) {
    	if (evt.keyCode == 13 && !evt.shiftKey) {
    	    $('#send').click();
    	}else if(evt.keyCode == 13 && evt.shiftKey){
    		$('#send').val($('#send').val()+'<br>');
    	}
    	if($('#mymsg').val().length > 100){
    		alert('100글자를 초과하여 입력할 수 없습니다');
    		$('#mymsg').val($('#mymsg').val().substring(0,100));
    	}
    });
    $('#calc').click(function(){
    	if(permission > 1)
    		sock.send(JSON.stringify({pno:planner.no, type: 'sumCost', memberNo: memberNo, content: calculator()}));
    	else
    		alert('권한이 없습니다');
    })
    
    $('#startDateUpdateBtn').click(function(){
    	if(permission > 1)
	    	sock.send(JSON.stringify({pno:planner.no, type: 'startDate', memberNo: memberNo, content: $('#startrip').val()}));
    	else
    		alert('권한이 없습니다');
    });
    
    $('#inputScheduleTitle').keyup(function(){
    	if($('#inputScheduleTitle').val().length > 20){
    		alert('20글자를 초과하여 입력할 수 없습니다');
    		$('#inputScheduleTitle').val($('#inputScheduleTitle').val().substring(0,20));
    	}
    });
    
    $('#inputScheduleCost').keyup(function(){
    	if($('#inputScheduleCost').val().length > 9){
    		alert('돈을 너무 막씁니다');
    		$('#inputScheduleCost').val($('#inputScheduleCost').val().substring(0,9));
    	}
    });
    
    $('#inputScheduleMemo').keyup(function(){
    	if($('#inputScheduleCost').val().length > 200){
    		alert('200글자를 초과하여 입력할 수 없습니다');
    		$('#inputScheduleCost').val($('#inputScheduleCost').val().substring(0,200));
    	}
    });
    
    $('#inputScheduleLocationName').keyup(function(){
    	if($('#inputScheduleLocationName').val().length > 20){
    		alert('20글자를 초과하여 입력할 수 없습니다');
    		$('#inputScheduleLocationName').val($('#inputScheduleLocationName').val().substring(0,20));
    	}
    });
});

// 경비 총액 계산 후 리턴
function calculator(){
	var totalCost = 0;
	var expensive = '';
	for(var i in days){
		for(var j in days[i].schedules){
			totalCost += parseInt(days[i].schedules[j].cost,10);
			if(parseInt(days[i].schedules[j].cost,10) > 100000000){
				expensive += '\n' + days[i].schedules[j].title;
			}
		}
	}
	if(totalCost > 2000000000){
		alert('과도한 지출은 통장건강에 해롭습니다\n\n통장건강에 해로운 일정 목록' + expensive);
		totalCost = 0;
	}
	return totalCost;
}

// 뒤로가기 하는 함수(참여 모달창의 이전으로 버튼에 사용)
function goBack(){
    history.go(-1);
}

function initMapBtn(){
	var sl = new kakao.maps.LatLng(0,0);
	initMarker(sl,null);
}

function removeColonFromTime(time){
	return time.replace(':','');
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

</script>
</html>