var lat = 0; // 위도 Lat Y 작은값 Ha
var lng = 0; // 경도 Lng X 큰값 Ga

var lastKeyword = "";
var mapContainer = document.getElementById('map'); //지도를 담을 영역의 DOM 레퍼런스
var mapOption = { //지도를 생성할 때 필요한 기본 옵션
    center: new kakao.maps.LatLng(36.45163365699092, 127.91201232261682), //지도의 중심좌표. 필수 파라미터
    level: 13 //지도의 레벨(확대, 축소 정도)
};

var map = new kakao.maps.Map(mapContainer, mapOption); // 지도를 생성합니다
var geocoder = new kakao.maps.services.Geocoder(); // 주소-좌표 변환 객체를 생성합니다

// 클릭 시 마커 생성
var marker = new kakao.maps.Marker(); // 지도를 클릭한 위치에 표출할 마커입니다
var infowindow = new kakao.maps.InfoWindow({zIndex:1}); // 클릭한 위치에 대한 주소를 표시할 인포윈도우입니다
var iwContent;  // 인포윈도우 내용 저장용 변수

// 검색 시 마커 생성
var searchMarker = new kakao.maps.Marker();
var searchMarkers = []; // 마커들을 담을 배열 생성

// 전체 장소 포함하는 지도
var allMapContainer = document.getElementById('allMapPlace');
var allMap = new kakao.maps.Map(allMapContainer, mapOption);
var allMarkers = [];
var infowindowAll = new kakao.maps.InfoWindow({
    removable : true
});


// 지도에 클릭 이벤트를 등록합니다
// 지도를 클릭하면 마지막 파라미터로 넘어온 함수를 호출합니다
kakao.maps.event.addListener(map, 'click', function (mouseEvent) {
    searchDetailAddrFromCoords(mouseEvent.latLng, function(result, status) {
    	console.log(mouseEvent.latLng);
        if (status === kakao.maps.services.Status.OK) {
            var detailAddr = !!result[0].road_address ? '<div style="font-size: 14px;">도로명주소 : ' + result[0].road_address.address_name + '</div>' : '';
            detailAddr += '<div style="font-size: 14px;">지번 주소 : ' + result[0].address.address_name + '</div>';
            
            var content = '<div class="bAddr">' +
                            '<span class="title">' + $('#inputScheduleLocationName').val() + '</span>' + 
                            detailAddr + 
                        '</div>';

            // 마커를 클릭한 위치에 표시합니다 
            marker.setPosition(mouseEvent.latLng);
            marker.setMap(map);
            console.log(mouseEvent.latLng);
            // 인포윈도우에 클릭한 위치에 대한 법정동 상세 주소정보를 표시합니다
            infowindow.setContent(content);
            infowindow.open(map, marker);

            // 인포윈도우 내용 저장
            iwContent = content;
            console.log(iwContent + ", " + content);

            // 마지막 검색 키워드 저장
            lastKeyword = result[0].address.address_name;

            // 마지막 클릭 위치 저장
            lat = mouseEvent.latLng.getLat();
            lng = mouseEvent.latLng.getLng();
            console.log("위도(Lat) 경도(Lng) : " + lat + ", " + lng);
        }
    });
});

function searchDetailAddrFromCoords(coords, callback) {
    // 좌표로 법정동 상세 주소 정보를 요청합니다
    geocoder.coord2Address(coords.getLng(), coords.getLat(), callback);
}

// 검색어를 통한 지도 변경
// 장소 검색 객체를 생성합니다
var ps = new kakao.maps.services.Places();

// 키워드 검색 완료 시 호출되는 콜백함수 입니다
function placesSearchCB (data, status, pagination) {
    if (status === kakao.maps.services.Status.OK) {

        // 정상적으로 검색이 완료됐으면
        // 검색 목록과 마커를 표출합니다
        displayPlaces(data);

    } else if (status === kakao.maps.services.Status.ZERO_RESULT) {

        alert('검색 결과가 존재하지 않습니다.');
        return;

    } else if (status === kakao.maps.services.Status.ERROR) {

        alert('검색 결과 중 오류가 발생했습니다.');
        return;

    }
}

// 검색 결과 목록과 마커를 표출하는 함수입니다
function displayPlaces(places) {

    var bounds = new kakao.maps.LatLngBounds();

    // 지도에 표시되고 있는 마커를 제거합니다
    removeMarker();
    
    for ( var i=0; i<places.length; i++ ) {

        // 마커를 생성하고 지도에 표시합니다
        searchMarker = addMarker(places[i]);

        // 검색된 장소 위치를 기준으로 지도 범위를 재설정하기위해
        // LatLngBounds 객체에 좌표를 추가합니다
        bounds.extend(new kakao.maps.LatLng(places[i].y, places[i].x));
    }

    // 검색된 장소 위치를 기준으로 지도 범위를 재설정합니다
    map.setBounds(bounds);
}

// 지도 위에 표시되고 있는 마커를 모두 제거합니다
function removeMarker() {
    for ( var i = 0; i < searchMarkers.length; i++ ) {
        searchMarkers[i].setMap(null);
    }   
    searchMarkers = [];
}

// 지도 위에 표시되고 있는 마커를 모두 제거합니다
function removeAllMarker() {
    for ( var i = 0; i < allMarkers.length; i++ ) {
        allMarkers[i].setMap(null);
    }   
    allMarkers = [];
}

var imageSrc = "http://t1.daumcdn.net/localimg/localimages/07/mapapidoc/markerStar.png";// 마커 이미지의 이미지 주소입니다
var imageSize = new kakao.maps.Size(24, 35); // 마커 이미지의 이미지 크기 입니다
var markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize); // 마커 이미지를 생성합니다 (별 모양)

// 마커를 생성하고 지도 위에 마커를 표시하는 함수입니다
function addMarker(place) {
    var searchMarker = new kakao.maps.Marker({
        position: new kakao.maps.LatLng(place.y, place.x), // 마커의 위치
        image: markerImage 
    });

    // 마커에 클릭이벤트를 등록합니다
    kakao.maps.event.addListener(searchMarker, 'click', function() {
        geocoder.coord2Address(place.x, place.y, function(result, status){
            if (status === kakao.maps.services.Status.OK) {
                var detailAddr = !!result[0].road_address ? '<div style="font-size: 14px;">도로명주소 : ' + result[0].road_address.address_name + '</div>' : '';
                detailAddr += '<div style="font-size: 14px;">지번 주소 : ' + result[0].address.address_name + '</div>';
                
                var content = '<div class="bAddr">'
                            + "<span class='title'>" + place.place_name + "</span>"
                            + detailAddr
                            + '</div>';

                lastKeyword = result[0].address.address_name;
                

            }else{
                var content = '<div class="bAddr">'
                            + "<span class='title'>" + place.place_name + "</span>"
                            + '</div>';

                lastKeyword = place.place_name;

            }

            // 마커를 클릭하면 장소명이 인포윈도우에 표출됩니다
            infowindow.setContent(content);
            infowindow.open(map, searchMarker);
            
            // 클릭한 위도, 경도 저장
            lat = place.y;
            lng = place.x;

            console.log("위도(Lat) 경도(Lng) : " + lat + ", " + lng);
        });
    });

    searchMarker.setMap(map); // 지도 위에 마커를 표출합니다
    searchMarkers.push(searchMarker);  // 배열에 생성된 마커를 추가합니다

    return searchMarker;
}



function displayInfowindow(marker, i) {
    var content = '<div style="padding:5px;z-index:1;">' + i + '</div>';

    infowindowAll.setContent(content);
    infowindowAll.open(allMap, marker);
}

// 검색 결과 목록과 마커를 표출하는 함수입니다
function displayAllPlaces(points,locations) {
    // 버튼을 클릭하면 아래 배열의 좌표들이 모두 보이게 지도 범위를 재설정합니다

    var allBounds = new kakao.maps.LatLngBounds();

    // 지도에 표시되고 있는 마커를 제거합니다
    removeAllMarker();
    
    var i;
    for (i = 0; i < points.length; i++) {

        // 경도, 위도의 합이 0이면(위치지정 안한 값) 마커 찍는거 건너뜀
        console.log("points : " + points[i].getLat()+points[i].getLng());
        if(points[i].getLat()+points[i].getLng() == 0){
            console.log("continue : " + i);
            continue;
        }

        //   순서있는 마커 코드
       var numImageSrc = 'http://t1.daumcdn.net/localimg/localimages/07/mapapidoc/marker_number_blue.png', // 마커 이미지 url, 스프라이트 이미지를 씁니다
       numImageSize = new kakao.maps.Size(36, 37),  // 마커 이미지의 크기
       imgOptions =  {
           spriteSize : new kakao.maps.Size(36, 691), // 스프라이트 이미지의 크기
           spriteOrigin : new kakao.maps.Point(0, (i*46)+10), // 스프라이트 이미지 중 사용할 영역의 좌상단 좌표
           offset: new kakao.maps.Point(13, 37) // 마커 좌표에 일치시킬 이미지 내에서의 좌표
       },
       markerImage = new kakao.maps.MarkerImage(numImageSrc, numImageSize, imgOptions),
        allMarker = new kakao.maps.Marker({
           position: points[i], // 마커의 위치
           image: markerImage,
           clickable: true
       });
        // 마커에 클릭이벤트를 등록합니다
        (function(allMarker, i){
            kakao.maps.event.addListener(allMarker, 'click', function() {
                // 마커 위에 인포윈도우를 표시합니다
                displayInfowindow(allMarker, locations[i]);
            });
        })(allMarker, i);

       // 배열의 좌표들이 잘 보이게 마커를 지도에 추가합니다
       allMarker.setMap(allMap);
       allMarkers.push(allMarker);
       
       // LatLngBounds 객체에 좌표를 추가합니다
       allBounds.extend(points[i]);
   }

    // 검색된 장소 위치를 기준으로 지도 범위를 재설정합니다
    allMap.setBounds(allBounds);
}


// 카카오 외의 함수들
function enterSearch(){
    var keyCode = event.keyCode;

    if(keyCode == 13){
        $("#search-btn").click();
    }
}

function searchKeyword(){
    // 키워드로 장소를 검색합니다
    ps.keywordSearch($("#inputScheduleLocation").val(), placesSearchCB);
    lastKeyword = $("#inputScheduleLocation").val();
}

/* 
맛집, 관광지, 숙소를 버튼을 통하여 검색할때
사용자의 마지막 동작으로 얻은 검색값을 기준으로
검색이 이루어져야함.

keyword를 이용한 검색, 마커 클릭 했을 때
javascript변수의 값을 계속 갱신해주고
3가지 버튼을 눌렀을 때 변수의 값을 기준으로
검색 실행
*/
$(function(){
    $(".reco-btn").on("click", function(){
        if(lastKeyword == ""){
            alert("검색이나 지도를 클릭해주세요!");
        }
        var recoText = lastKeyword + " " + $(this).text();

        // 키워드로 장소를 검색합니다
        ps.keywordSearch(recoText, placesSearchCB);
    });

    // 처음 지도 설정
    var plannerPoint = new Array();
    displayAllPlaces();
});

function initMarker(scheduleLocation,locationContent){
    if(scheduleLocation.getLat()+scheduleLocation.getLng() == 0){
        marker.setMap(null);
        infowindow.setMap(null);
    }else{
        lat = 0;
        lng = 0;
        marker.setMap(map);
        
        console.log("locationContent : " + locationContent);
        // 마커를 클릭한 위치에 표시합니다 
        console.log(scheduleLocation);

        marker.setPosition(scheduleLocation);
        marker.setMap(map);

        // 인포윈도우에 클릭한 위치에 대한 법정동 상세 주소정보를 표시합니다
        infowindow.setContent(locationContent);
        infowindow.open(map, marker);
    }
}

