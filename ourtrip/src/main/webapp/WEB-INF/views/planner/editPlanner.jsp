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
    
    <title>${plannerTitle}</title>
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
                                    <div class="col-md-6"><button type="button" class="updateBtn btnColor1" id="toggleMap">지도 초기화</button></div>
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

                        </div>
                        
                        <div class="row p-4" style=" height: 18%;">
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
    <jsp:include page="../common/footer.jsp" />
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

$(function() {
	var plannerInfo = '${plannerInfo}';
	//var plannerInfo = '{"plannerNo":"1","plannerTitle":"제목1","plannerPwd":"1234","plannerCost":"0","plannerCreateDT":"2020-03-20","plannerModifyDT":"null","plannerStartDT":"2020-03-20","plannerPublicYN":"Y","plannerDeleteYN":"N","plannerExpiry":"N","plannerCount":"1","plannerUrl":"1","groupCode":"1","days":[{"dateNo":"1","tripDate":"1","plannerNo":"1","schedules":[{"scheduleNo":"1","scheduleTitle":"제목1","scheduleCost":"0","scheduleTime":"0900","scheduleMemo":"가","scheduleLocationNM":"null","scheduleLat":"35.435358898687994","scheduleLng":"128.1578038205071","dateNo":"1"},{"scheduleNo":"2","scheduleTitle":"제목1","scheduleCost":"0","scheduleTime":"1000","scheduleMemo":"나","scheduleLocationNM":"null","scheduleLat":"36.435358898687994","scheduleLng":"127.4578038205071","dateNo":"1"},{"scheduleNo":"3","scheduleTitle":"제목1","scheduleCost":"0","scheduleTime":"1100","scheduleMemo":"다","scheduleLocationNM":"null","scheduleLat":"37.33535889868799","scheduleLng":"127.3578038205071","dateNo":"1"}]},{"dateNo":"2","tripDate":"4","plannerNo":"1","schedules":[{"scheduleNo":"4","scheduleTitle":"제목1","scheduleCost":"0","scheduleTime":"1200","scheduleMemo":"라","scheduleLocationNM":"null","scheduleLat":"36.73535889868799","scheduleLng":"127.1578038205071","dateNo":"2"},{"scheduleNo":"5","scheduleTitle":"제목1","scheduleCost":"0","scheduleTime":"1300","scheduleMemo":"마","scheduleLocationNM":"null","scheduleLat":"35.935358898687994","scheduleLng":"127.9578038205071","dateNo":"2"}]},{"dateNo":"4","tripDate":"3","plannerNo":"1","schedules":[{"scheduleNo":"9","scheduleTitle":"제목1","scheduleCost":"0","scheduleTime":"1700","scheduleMemo":"자","scheduleLocationNM":"null","scheduleLat":"36.935358898687994","scheduleLng":"128.0578038205071","dateNo":"4"},{"scheduleNo":"10","scheduleTitle":"제목1","scheduleCost":"0","scheduleTime":"1800","scheduleMemo":"차","scheduleLocationNM":"null","scheduleLat":"35.73535889868799","scheduleLng":"127.7578038205071","dateNo":"4"}]},{"dateNo":"6","tripDate":"2","plannerNo":"1","schedules":[{"scheduleNo":"13","scheduleTitle":"제목1","scheduleCost":"0","scheduleTime":"2100","scheduleMemo":"파","scheduleLocationNM":"null","scheduleLat":"35.935358898687994","scheduleLng":"127.6578038205071","dateNo":"6"},{"scheduleNo":"14","scheduleTitle":"제목1","scheduleCost":"0","scheduleTime":"2200","scheduleMemo":"하","scheduleLocationNM":"null","scheduleLat":"36.73535889868799","scheduleLng":"127.5578038205071","dateNo":"6"}]}]}';
	var plannerJson = JSON.parse(plannerInfo)
	/* var qwe = 538;
	console.log(plannerInfo[qwe-2],plannerInfo[qwe-1],plannerInfo[qwe],plannerInfo[qwe+1],plannerInfo[qwe+2]); */
	$("#join").click(function(){
		console.log('1');
		sock.send(JSON.stringify({chatRoomId: "${selectRoom}", type: 'JOIN', writer: "${userId}", content: ""}));
		initPlanner(plannerJson);
	})
	console.log('1');
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
	console.log(planner.no);
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
			var templocation = new kakao.maps.LatLng(36.435358898687994,127.5578038205071)
			scheduleMarker.push({"sno" : schedule.no, "LatLng" : scheduleLatLng, "unselect" : (schedule.lat+schedule.lng==0?true:false), "infoWindow" : null}); 
				
			loadingInfo += 1;
			
			//console.log("promise : " + getScheduleAddr(templocation,'나와라'));
			getScheduleAddr(scheduleLatLng,schedule.locationNM,schedule.no).then(function(args){
				for(var i in scheduleMarkers){
					for(var j in scheduleMarkers[i].scheduleMarker){
						if(scheduleMarkers[i].scheduleMarker[j].sno == args[0]){
							scheduleMarkers[i].scheduleMarker[j].infoWindow = args[1];
							loadingAddr += 1;
							if(loadingInfo == loadingAddr){
								for(var i = 0; i < days.length; i++){
									createDate(days[i].no);
								}
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
	sortDays(days);
	for(var i = 0; i < days.length; i++){
		sortSchedules(days[i].schedules);
	}
	console.log(days);
	console.log("scheduleMarkers");
	console.log(scheduleMarkers);
	
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
	for(var i = schedules.length-1; i > 0; i--){
        for(var j = 0; j < i; j++){
        	if(schedules[j+1].time < schedules[j].time){
                var temp = schedules[j];
                schedules[j] = schedules[j+1];
                schedules[j+1] = temp;
            }   
	    }
	}
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
    var locationArr = new Array();
    var infoWindowArr = new Array()
    displayAllPlaces(scheduleMarkers[1],scheduleMarkers[3]);
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
				    }
				});
				
			} catch(error){
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
				reject(error);
			}
		});
	}
} 

function extractDayMarker(dno){
	for(var i = 0; i < scheduleMarkers.length; i++){
		if(scheduleMarkers[i].dno == dno){
			return scheduleMarkers[i];
		}
	}
}

//=======================================================================================//
//====================================== Day 관련 함수 ======================================//
//=======================================================================================//

//일차 추가하는 함수
// 매개변수 dateNo = PLANNER_DATE 테이블의 DATE_NO값을 넣어야함
function createDate(dateNo){
    
    // 일차 만드는 HTML코드, dayInd는 테스트용이고 dateNo로 바꿔줘야함
    var dayForm = 
    '<div data-dateno="' + dateNo + '" id="days" class="daystyle" onclick="selectDay(' + dateNo + ');">' +
    '<span class="dayCount pl-2">1일차</span>' +
    '<button class="dayDeleteBtn btnColor3" onclick="deleteDay(' + dateNo + ');">-</button>' +
    '</div>';

    // 일차 목록에 추가함
    $("#sortable").append(dayForm)

    // 추가하고 정렬(몇일차인지 계산해서 텍스트 바꿔줌)
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
   				displayAllPlaces(extractDayMarker(no));
   				
    		}
    	}
    }
    infowindowAll.close();
}

//일차 제거하는 함수
function deleteDay(ind){
    
    $(".daystyle").each(function(i, box) {
        if($(box).data("dateno")==ind)
            $(box).remove();
    });
    reorder();
    selectDay($($(".daystyle").get(0)).data('dateno'));

}



// 일차 정렬하여 몇일차인지 텍스트 바꿔줌
function reorder() {
    $(".daystyle").each(function(i, box) {
        $(box).find(".dayCount").html(i + 1 + "일차");
    });
};

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

//일정 추가하는 함수
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

//createNo 변수는 테스트때만 사용하고 실제 DB사용할때는 지울것
var createNo=100;

function selectScheduleNo(no){
	var dayIdx = -1;
	var scheIdx = -1;
	var scheDiv = null;
	if(dayIdx == -1){
		for(var i in scheduleMarkers){
			for(var j in scheduleMarkers[i].scheduleMarker){
				if(scheduleMarkers[i].scheduleMarker[j].sno == no){
					console.log("i : " + i + ", dno : " + scheduleMarkers[i].dno);
					console.log("j : " + j + ", sno : " + scheduleMarkers[i].scheduleMarker[j].sno);
					dayIdx = i;
					scheIdx = j;
				}
			}
		}
	}
	
	$('.schedule').each(function(i, item){
		console.log("i : " + i);
		console.log("item : " + item);
		
		if($(item).data('scheduleno') == no){
			scheDiv = item; 
		}
	})
	
    return new Array(dayIdx, scheIdx, scheDiv);
}

function selectSchedule(no){
    var scheInfo = selectScheduleNo(no);
    
    $('#inputScheduleTitle').val($(scheInfo[2]).find(".scheduleTitle").html());
    console.log("scheInfoTitle" + $(scheInfo[2]).find(".scheduleTitle").html());
    console.log(scheInfo[2]);
    $('#inputScheduleTime').val($(scheInfo[2]).find(".scheduleTime").val());
    $('#inputScheduleCost').val($(scheInfo[2]).find(".scheduleCost").html());
    $('#inputScheduleMemo').val($(scheInfo[2]).find(".scheduleMemo").html());
    $('#inputScheduleLocationName').val($(scheInfo[2]).find(".scheduleLocation").html());
    $('#scheduleInfo').data('scheduleno',no);

    if(!scheduleMarkers[scheInfo[0]].scheduleMarker[scheInfo[1]].unselect){
    	console.log("panTo");
    	console.log(scheduleMarkers[scheInfo[0]].scheduleMarker[scheInfo[1]].LatLng);
    	console.log("dayIdx : " + scheInfo[0] + ", scheIdx : " + scheInfo[1]);
        allMap.panTo(scheduleMarkers[scheInfo[0]].scheduleMarker[scheInfo[1]].LatLng);
        map.panTo(scheduleMarkers[scheInfo[0]].scheduleMarker[scheInfo[1]].LatLng);
    }
    
    lat = scheduleMarkers[scheInfo[0]].scheduleMarker[scheInfo[1]].LatLng.getLat();
    lng = scheduleMarkers[scheInfo[0]].scheduleMarker[scheInfo[1]].LatLng.getLng();
    // marker.setPosition(mouseEvent.latLng);
    // marker.setMap(map);
    initMarker(scheduleMarkers[scheInfo[0]].scheduleMarker[scheInfo[1]].LatLng,scheduleMarkers[scheInfo[0]].scheduleMarker[scheInfo[1]].infoWindow);
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
        var scheInfo = selectScheduleNo($('#scheduleInfo').data('scheduleno'));
        $(scheInfo[2]).find(".scheduleTitle").html($('#inputScheduleTitle').val());
        $(scheInfo[2]).find(".scheduleTime").val($('#inputScheduleTime').val());
        $(scheInfo[2]).find(".scheduleLocation").html( $('#inputScheduleLocationName').val());
        $(scheInfo[2]).find(".scheduleCost").html($('#inputScheduleCost').val());
        $(scheInfo[2]).find(".scheduleMemo").html($('#inputScheduleMemo').val());

        var flag = false;
        var i;
        var dupIdx;

        scheduleLatLng = new kakao.maps.LatLng(lat, lng);
        scheduleMarkers[scheInfo[0]].scheduleMarker[scheInfo[1]].LatLng = scheduleLatLng;
        scheduleMarkers[scheInfo[0]].scheduleMarker[scheInfo[1]].unselect = lat + lng == 0 ?true:false;
        scheduleMarkers[scheInfo[0]].scheduleMarker[scheInfo[1]].infoWindow = iwContent;
        console.log("updateIwContent");
        console.log(iwContent);


        sortSchedule();

        // initMarker(scheduleMarkers[1][dupIdx], scheduleMarkers[3][dupIdx]);
        console.log(scheduleMarkers[scheInfo[0]].scheduleMarker[scheInfo[1]].infoWindow);
        initMarker(scheduleMarkers[scheInfo[0]].scheduleMarker[scheInfo[1]].LatLng, scheduleMarkers[scheInfo[0]].scheduleMarker[scheInfo[1]].infoWindow);
    }
});
$('#removeSchedule').click(function(){
    if($('#scheduleInfo').data('scheduleno')==0){
        alert('일정을 선택해주세요');
    } else {
        var selectedSchedule = selectScheduleNo($('#scheduleInfo').data('scheduleno'));

        $(selectedSchedule).remove();
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
console.log('2');

// 메시지 전송
function chageback(color){
	console.log(color);
	$("#testbox").css("background",color);
}

// 서버로부터 메시지를 받았을 때
function onMessage(msg) {
	var jsonData = msg.data;
	var data = JSON.parse(jsonData)
	console.log("receive : " + data);
	switch(data['type']){
	case 'msg':
		
		if(data['id'] == '${userId}'){
	     	// inputChat = 채팅 내역에 채팅창 올리는 함수
	        // mkMyChatMsg = 내가 보낸 메세지로 채팅창 만드는 함수
	        // mkMyChatMsg 매개변수 = (msgContent,msgTime)
	        inputChat(mkMyChatMsg(data['content'],data['time']));
		}else {
			// inputChat = 채팅 내역에 채팅창 올리는 함수
	        // mkChatMsg = 다른사람이 보낸 메세지로 채팅창 만드는 함수
	        // mkChatMsg 매개변수 = (profileImg,userId,msgContent,msgTime)
	        inputChat(mkChatMsg('',data['id'],data['content'],data['time']));
		}
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

//=======================================================================================//
//====================================== 채팅 관련 함수  ======================================//
//=======================================================================================//

// 다른사람이 보낸 채팅 메세지 만들어서 리턴하는 함수
function mkChatMsg(profileImg,userId,msgContent,msgTime){
    var chatMsg =
    '<div class="chatbox overhidden">' +
    '<div>' +
    '<img src = "' + profileImg + '" class="profileImg">' +
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
//======================================== 기타 함수 ========================================//
//=======================================================================================//

$(function () {
    
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
        var msg = document.getElementById("mymsg").value;
        msg = msg.replace(/(?:\r\n|\r|\n)/g, '<br/>');
        
        // 날짜 객체
        var d = new Date();
        var h = d.getHours();
        var m = d.getMinutes();
        if(m < 10)
        	m = '0' + d.getMinutes();
        if(h < 10)
        	h = '0' + d.getHours();
        	
        // 현재 시간
        var now = h + ':' + m;

        // 채팅 입력창 비어있지 않으면 실행
        if( msg != ''){
            
            console.log("send : " + JSON.stringify({chatRoomId: "${selectRoom}", type: 'msg', id: "${userId}", content: msg, time: now}));
            sock.send(JSON.stringify({chatRoomId: "${selectRoom}", type: 'msg', id: "${userId}", content: msg, time: now}));
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
    
    // 페이지 로딩될 때 지도영역 감추는거 그냥 함수로 하는부분
    //$("#inputScheduleLocationArea").toggle();

    // 지도추가 버튼 클릭 시 보이고 안보이고 토글, 버튼 텍스트 변경
    /* $("#toggleMap").click(function(){
        $("#inputScheduleLocationArea").toggle(['slow']);
        if($("#toggleMap").html() == '지도 추가')
            $("#toggleMap").html('지도 삭제');
        else
        $("#toggleMap").html('지도 추가');
        
    }); */
    $('textarea').keyup(function (evt) {
    	console.log("textarea keyup");
    	if (evt.keyCode == 13 && !evt.shiftKey) {
    		console.log('click');
    	    $('#send').click();
    	}else if(evt.keyCode == 13 && evt.shiftKey){
    		console.log('\r\n');
    		$('#send').val($('#send').val()+'<br>');
    	}
    });
});

// 뒤로가기 하는 함수(참여 모달창의 이전으로 버튼에 사용)
function goBack(){
    console.log("돌아가기");
    history.go(-1);
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