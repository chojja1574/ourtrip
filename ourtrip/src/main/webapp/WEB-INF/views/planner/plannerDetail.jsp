<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html lang = "ko">
    <html lang = "ko">
    <head>
    <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" 
        integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
        <script src="https://code.jquery.com/jquery-3.4.1.min.js"  
        integrity="sha256-CSXorXvZcTkaix6Yvo6HppcZGetbYMGWSFlBw8HfCJo="  crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" 
        integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" 
        integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
        <!-- style -->
        <link rel="stylesheet" type="text/css" href="${contextPath}/resources/css/slick.css"/>
        <link rel="stylesheet" type="text/css" href="${contextPath}/resources/css/planner.css"/>
        
        <!-- 호환성 -->
        <meta http-equiv="X-UA-Compatible" content="IE=edge">

        <!-- javascript lib -->
        <script type="text/javascript" src="${contextPath}/resources/js/slick.min.js"></script>

        <!-- 폰트 -->
        <link href="https://fonts.googleapis.com/css?family=Nanum+Pen+Script|Ubuntu&display=swap" rel="stylesheet">

        <!-- 공용 css -->
        <link rel="stylesheet" href="${contextPath}/resources/css/common.css" />

		<!-- kakao map sdk -->
		<script src="//developers.kakao.com/sdk/js/kakao.min.js"></script>
		<script type="text/javascript"
			src="//dapi.kakao.com/v2/maps/sdk.js?appkey=3265d67cbccb2a931046b989ef45ad5f&libraries=services,clusterer,drawing"></script>
		
        <title> ${plannerInfo.plannerTitle} </title>
        <style>
            /* div{
                border:1px dotted black;
            } */
            
            .dayMap{
            	width:100%; 
            	height: 350px; 
            }
            
            .card-header{
                border-top-right-radius: 5px;
                border-top-left-radius: 5px;
            }
            
            .scheduleInfo{
            	border: 2px solid lightgray;
                border-top: 2px solid #18a8f1;
            	border-left: 0px;
            }
            
            .card-body{
                border: 2px solid lightgray;
                border-top: 2px solid #18a8f1;

            }
            .card-body::-webkit-scrollbar {
			    width: 5px;
			    background-color: none;
			}
			.card-body::-webkit-scrollbar-thumb {
			    width: 5px;
			    border-radius: 15px;
			    background-color: rgba(0, 0, 0, 0.15);
			}
			.card-body::-webkit-scrollbar-track {
			    width: 5px;
			    background-color: none;
			}
            .scheduleInfo::-webkit-scrollbar {
			    width: 5px;
			    background-color: none;
			}
			.scheduleInfo::-webkit-scrollbar-thumb {
			    width: 5px;
			    border-radius: 15px;
			    background-color: rgba(0, 0, 0, 0.15);
			}
			.scheduleInfo::-webkit-scrollbar-track {
			    width: 5px;
			    background-color: none;
			}
            
            .card-footer{
                border: 2px solid lightgray;
                border-top:none;
            }

            div:focus {
                outline: none;
            }

            /* 슬라이더 크기 */
            /* 높이설정 */
            .slick-slider{
                height:800px;
            }
            /* 넓이 설정 */
            /* .slider-wrapper{
                width:100%
            } */

            /* 슬라이더 내용크기 */
            .slick-list{
                height:100%;
            }

            /* 내용크기 + 드래그범위 */
            .slick-track{
                height:100%!important;
            }
            

            .slick-slide{
                border:0px;
            }
            
            @media screen and (max-width: 414px) {
                /* .body div {
                    border:none;
                } */

                .container{
                    padding:0px;
                }

                .slick-slider{
                    width:100%;
                    height:100%;
                }

                .slider-wrapper{
                    width:100%;
                    height:100%;
                }


                .header{
                    display: none;
                }
                /* 할거 아래 푸터지우기 */
                /* .card-footer{
                    display: none!important;
                } */
            } 

            .p-cont{
            text-align: left;
            display: block;
            }

            .applyFont{
                font-family: 'Ubuntu', sans-serif;
                font-family: 'Nanum Pen Script', cursive;
                font-size: 24px;
            }

        </style>
    </head>
    <body>
    	<div class="header">
	       	<jsp:include page="../common/header.jsp" />
			<jsp:include page="../common/nav.jsp" />
		</div>
        <div class="container-fluid mb-5 mt-5 applyFont">
			<div class="row">
				<div class="col-md-2"></div>
		        <h2 class="card-header main-back col-md-8" style="height:70px;">${plannerInfo.plannerTitle}</h2>
	        </div>
	        <div class="row">
	        	<div class="col-md-2"></div>
	            <!-- 이미지 슬라이드 -->
	            <div class="col-md-3">
	            	<div class="row">
			            <div class="slider-wrapper col-md-12 pl-0 pr-0">
			                <div class="slider">
			                <c:forEach items="${plannerInfo.days}" var="thisDay" varStatus="vs">
			                    <!-- 1-day 시작 -->
			                    <div class="card">
			                        <div class="card-body" style="overflow: auto; height:800px;">
			                            <h1 style="color:#18a8f1;">Day-${thisDay.tripDate+1}</h1>
			                            <hr>
			                            <c:forEach items="${thisDay.schedules}" var="thisSchedule" varStatus="vs2">
				                            <div data-sno="${thisSchedule.scheduleNo}" class="btn p-cont schedule" data-toggle="modal" data-target="#myModal">
				                                <h1 class="font-weight-bold">${thisSchedule.scheduleTitle}</h1>
				                                <c:set var="time" value="${thisSchedule.scheduleTime}"/>
				                                <h3>시간 : ${fn:substring(time,0,2)}:${fn:substring(time,2,4)}</h3>
				                                <h2>장소 : ${thisSchedule.scheduleLocationNM}</h2>
				                            </div>
			                            	<hr>
			                            </c:forEach>
			                           
						            	<div class="dayMap" id="${thisDay.dateNo}">
						            	</div>
			                        </div>
			                    </div>
			                    <!-- 1-day 끝 -->
			                </c:forEach>
			                </div>
			            </div>
		            </div>
	            </div>
	            <div class="col-md-3 p-4 scheduleInfo PCOnly" style="overflow:auto; height:800px">
                    <h1 class="mb-4 selectTitle">제목:</h1>
                    <h2 class="mb-3 selectTime">시간 :</h2>
                    <h3 class="mb-3 selectLocation">장소 :</h3>
                    <h3 class="mb-3 selectCost">경비 :</h3>
                    <h3 class="mb-3">메모</h3>
                    <textarea class="selectMemo" readonly style="height:200px; width: 400px; resize: none; padding: 20px;" >

                    </textarea>
                    <h3>위치</h3>
                    
                    <div class="dayMap" id="PCMap">
                    </div>
                </div>
                <div class="col-md-2 p-5 scheduleInfo PCOnly">
	            </div>
	        </div> 
	        <!-- slider wrapper 끝 -->
	        <div class="row">
	        	<div class="col-md-2"></div>
	        	<div class="card-footer text-muted d-flex hideCon col-md-8" style="height:70px;">
	        	<c:if test="${loginMember != null }">
		            <button type="button" class="btn main-btn mr-auto PCOnly" id="copyBtn">복사</button>
		        </c:if>
		            <button type="button" class="btn gray-btn ml-auto PCOnly" id="beforeBtn">이전으로</button>
			    </div>
	        </div>
		</div>
        <!-- 모달창 -->
        <div class="modal mobileOnly applyFont" id="myModal">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header main-back">
                        <h1 class="modal-title font-weight-bold selectTitle">DAY - 1</h1>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
	                    <h2 class="mb-3 selectTime">시간 :</h2>
	                    <h3 class="mb-3 selectLocation">장소 :</h3>
	                    <h3 class="mb-3 selectCost">경비 :</h3>
	                    <h3 class="mb-3">메모</h3>
	                    <textarea class="selectMemo" readonly style="height:200px; width: 400px; resize: none; padding: 20px;" >
	
	                    </textarea>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-primary" 
                        data-dismiss="modal">되돌아가기</button>
                    </div>
                </div>
            </div>
        </div>
        <div class="header">
	    	<jsp:include page="../common/footer.jsp" />
	    </div>
    </body>
   
	<script type="text/javascript" src="${contextPath}/resources/js/map.js"></script>
    <script>
    var planner = new Object();
    var days = new Array();
    var mapId = null;
    var scheduleMarkers = new Array();
    var locationJson = null;
    var locationArray = new Array();
    var scheduleMarkers = new Array();
    var mapArray = new Array();
    var selectMap = null;
    var marker = new kakao.maps.Marker();
	var infowindow = new kakao.maps.InfoWindow({zIndex:1});
    
    $(function(){
    	var plannerInfo = '${plannerInfoJson}';
    	var plannerJson = JSON.parse(plannerInfo);
    	locationJson = ${locationArray};
    	initPlanner(plannerJson);
    	initMap();
    });
    
    $('#copyBtn').click(function(){
    	location.href="${contextPath}/planner/plannerCopy?no="+planner.no;
    });
    
    $('#beforeBtn').click(function(){
    	window.history.go(-1);
    });
    
    function initMap(){
    	
    	var loadingInfo = 0;
    	var loadingAddr = 0;
    	
    	for(var i in locationJson){
    		var scheduleMarker = new Array();
    		var dayMarker = new Object();
    		dayMarker.dno = locationJson[i].dno;
    		dayMarker.markers = [];
    		for(var j in locationJson[i].schedules){
    			var sm = new Object();
    			sm.sno = locationJson[i].schedules[j].sno;
    			sm.LatLng = new kakao.maps.LatLng(locationJson[i].schedules[j].lat, locationJson[i].schedules[j].lng);
    			sm.unselect = (locationJson[i].schedules[j].lat + locationJson[i].schedules[j].lng) == 0 ? true:false;
    			sm.location = locationJson[i].schedules[j].location;
    			sm.infoWindow = null;
    			scheduleMarker.push(sm);
    			loadingInfo++;
    		}
    		dayMarker.scheduleMarker = scheduleMarker;
    		scheduleMarkers.push(dayMarker);
    	}
    	
    	for(var d in scheduleMarkers){
    		for(var s in scheduleMarkers[d].scheduleMarker){
		    	getScheduleAddr(scheduleMarkers[d].scheduleMarker[s].LatLng,scheduleMarkers[d].scheduleMarker[s].location,scheduleMarkers[d].scheduleMarker[s].sno).then(function(args){
					for(var i in scheduleMarkers){
						for(var j in scheduleMarkers[i].scheduleMarker){
							console.log(scheduleMarkers[i].scheduleMarker[j].sno + ' =sno= ' +  args[0]);
							if(scheduleMarkers[i].scheduleMarker[j].sno == args[0]){
								scheduleMarkers[i].scheduleMarker[j].infoWindow = args[1];
								loadingAddr += 1;
								console.log(args[1]);
								console.log(loadingAddr + ' == ' + loadingInfo);
								if(loadingInfo == loadingAddr){
									for(var i in scheduleMarkers){
										var mapDiv = document.getElementById(locationJson[i].dno+''); 
										scheduleMarkers[i].map = new kakao.maps.Map(mapDiv, mapOption);
									}
									for(var i in scheduleMarkers){
							    		displayAllPlaces(scheduleMarkers[i],scheduleMarkers[i].map,scheduleMarkers[i].markers);
							    	}
								}
							}
						}
					}
				});
    		}
    	}
    }
    
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
    		}
    		days.push(day);
    	}
    	console.log("planner");
    	console.log(planner);
    	console.log("days");
    	console.log(days);
    	console.log(days);
    	console.log("scheduleMarkers");
    	console.log(scheduleMarkers);
    	
    	selectMapContainer = document.getElementById("PCMap");
    	selectMap = new kakao.maps.Map(selectMapContainer, mapOption);
    }
    
    function getScheduleAddr(templocation,locationName,scheduleNo){
    	if(templocation.getLng() + templocation.getLat() != 0){
    		return new Promise(function(resolve, reject){
    			try{
    				var contentArr= new Array();
    				contentArr.push(scheduleNo);
    				console.log(templocation);
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
    
	// 슬라이더
	$(".slider").slick({
	    // 아래 누르는 버튼
	    dots: false,
	    autoplay : false,
	    autoplaySpeed: 3000,
	    arrows: false,
	    responsive : [
	        {
	            breakpoint: 768,
	            settings : {
	                arrows: false,
	                dots : false
	            }
	        }
	    ]
	});
	
	function isMobile() {
	    return /Android|webOS|iPhone|iPad|iPod|BlackBerry|IEMobile|Opera Mini/i.test(navigator.userAgent);
	}
	
	if (isMobile()) {
	    // 모바일이면 실행될 코드 들어가는 곳
	    $('.PCOnly').css('display','none');
	} else {
	    // 모바일이 아니면 실행될 코드 들어가는 곳
	    $('#myModal').attr('id','ModalNone');
	    $('.mobileOnly').css('display','none');
	}
	
	$('.schedule').click(function(){
		console.log($(this).data('sno'));
		for(var i in days){
			for(var j in days[i].schedules){
				if(days[i].schedules[j].no == $(this).data('sno')){
					for(var k = 0; k < $('.selectTitle').length; k++){
						console.log($($('.selectTitle')[k]));
						$($('.selectTitle')[k]).html('제목 : ' + days[i].schedules[j].title);
						$($('.selectTime')[k]).html('시간 : ' + timeToTime(days[i].schedules[j].time));
						$($('.selectLocation')[k]).html('장소 : ' + days[i].schedules[j].locationNM);
						$($('.selectCost')[k]).html('비용 : ' + days[i].schedules[j].cost);
						$($('.selectMemo')[k]).html(days[i].schedules[j].memo);
					}
				}
				if(scheduleMarkers[i].scheduleMarker[j].sno == $(this).data('sno')){
					console.log('map');
					var scheduleLocation = scheduleMarkers[i].scheduleMarker[j].LatLng;
					var locationContent = scheduleMarkers[i].scheduleMarker[j].infoWindow;
					if(scheduleLocation.getLat()+scheduleLocation.getLng() == 0){
				        marker.setMap(null);
				        infowindow.setMap(null);
				    }else{
				    	console.log(selectMap);
				    	console.log(marker);
				    	
				        marker.setMap(selectMap);
				        
				        console.log("locationContent : " + locationContent);
				        // 마커를 클릭한 위치에 표시합니다 
				        console.log(scheduleLocation);

				        marker.setPosition(scheduleLocation);
				        marker.setMap(selectMap);
				        var allBounds = new kakao.maps.LatLngBounds();
				        allBounds.extend(scheduleLocation);
				     	
				        // 인포윈도우에 클릭한 위치에 대한 법정동 상세 주소정보를 표시합니다
				        infowindow.setContent(locationContent);
				        infowindow.open(selectMap, marker);
				        // selectMap.setBounds(allBounds);
				        selectMap.setCenter(scheduleLocation);
				    }
				}
			}
		}
	});
	function timeToTime(time){
		var str = time.replace(/(.{2})/g,"$1:").slice(0,-1);
		return str;
	} 
    </script>
    
</html>