<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ko">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
        integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">

    <script src="https://code.jquery.com/jquery-3.4.1.min.js"
        integrity="sha256-CSXorXvZcTkaix6Yvo6HppcZGetbYMGWSFlBw8HfCJo=" crossorigin="anonymous"></script>

    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
        integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo"
        crossorigin="anonymous"></script>

    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"
        integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6"
        crossorigin="anonymous"></script>

    <!-- style -->
    <link rel="stylesheet" type="text/css" href="${contextPath}/resources/slick/slick.css" />

    <!-- 호환성 -->
    <meta http-equiv="X-UA-Compatible" content="IE=edge">

    <!-- javascript lib -->
    <script type="text/javascript" src="${contextPath}/resources/slick/slick.min.js"></script>

    <!-- 폰트 -->
    <link href="https://fonts.googleapis.com/css?family=Nanum+Pen+Script|Ubuntu&display=swap" rel="stylesheet">

    <!-- 공용 css -->
    <link rel="stylesheet" href="${contextPath}/resources/css/common.css">
        
    <!-- Web socket CDN -->
	<script src="https://cdnjs.cloudflare.com/ajax/libs/sockjs-client/1.4.0/sockjs.js"></script>
	
	<!-- json2.js -->
	<script src="https://cdnjs.cloudflare.com/ajax/libs/json2/20160511/json2.js"></script>
	
    <title></title>

    <style>
        .tripinfo {
            width: 200px;
        }

        #plandays {
            border: 2px solid skyblue;
            height: 88%;

            position: relative;
            float: left;
            margin-top: 20px;
            overflow: auto;
        }

        .daystyle {
            border: 2px solid #18a8f1;
            background-color: #18a8f1;
            width: 90%;
            height: 70px;
            clear: both;
            margin: 4px;
            color: black;

        }

        .addbtn {
            width: 97%;
            left: 20px;
            bottom: 30px;
        }

        #inputplanner {
            border: 2px solid skyblue;
            height: 88%;

            position: relative;
            float: left;
            margin-top: 20px;
            overflow: auto;

        }

        .maplace {
            width: 300px;
            height: 300px;
            position: relative;
            margin-top: 30px;
            margin-left: 60px;
        }

        input {
            border: 1px;
        }

        .content-size{
            height: 950px;
        } 

        #chatpart {
            border: 2px solid skyblue;
            height: 88%;

            position: relative;
            float: left;
            margin-top: 20px;
        }

        #complaner {
            border: 2px solid skyblue;
            height: 88%;
            position: relative;
            float: left;
            margin-top: 20px;
        }

        .inputchat {
            border-top: 1px solid skyblue;
            width: 100%;
            height: 20%;
        }

        textarea {
            border: 0px;
        }


        /* 슬라이더 */


        div:focus {
            outline: none;
        }
        
        /* 슬라이더 크기 */
        .slick-slider {
            width: 100%;
            height: 100%;
        }
        /* 슬라이더 내용크기 */
        .slick-list{
            height: 100%;
        }
        /* 슬라이더내용+드래그크기 */
        .slick-track{
            height: 100%!important;
        }

        .slick-slide {
            border: 0px;
        }

        @media screen and (max-width: 414px) {

            .card-footer,
            .header {
                display: none !important;
            }
        }

        @media screen and (max-width: 1024px) {

            .content-size{
            height: 780px;
            } 
        }
        .p-cont {
            text-align: left;
            display: block;
        }

        body {
            font-family: 'Ubuntu', sans-serif;
            font-family: 'Nanum Pen Script', cursive;
            font-size: 24px;
        }

        

    </style>
</head>

<body>
	<jsp:include page="../common/header.jsp" />
	<jsp:include page="../common/nav.jsp" />
	<button type="button" id="join">join</button>
    <div class="container-fluid" style="border-top:1px solid">
        <form action="#">
            <div class="row">
                <div class="col-sm-1">
                </div>
                <div class="col-sm-4">
                    <div style="text-align: center;">
                        <span>총경비</span>
                        <span id="totalcost">0 원</span>
                    </div>
                </div>
                <div class="col-sm-4">
                    <div style="text-align: center;">
                        <input id="url" type="text" readonly placeholder="url" value="https//:ourtrip.com/1234567">
                        <a id="copy">복사</a>
                    </div>
                </div>
                <div class="col-sm-3">
                    <div style="text-align: center;">
                        <input id="addgrade" type="text" placeholder="권한부여">
                        <a id="gradebt">권한 부여</a>
                    </div>
                </div>
            </div>
            <div class="row content-size">
                <div class="col-md-2" id="plandays">
                    <div>
                        <input id="startrip" type="date">
                        <input id="endtrip" type="date">
                    </div>

                    <div id="days1" class="daystyle">
                        <span id="indexdays1" style="color: white;"></span>
                        <span style="color: white;">-Days</span>
                        <a id="removedays1" class="btn" style="color: red;">-</a>
                    </div>
                </div>
                <div class="col-md-3" id="inputplanner">
                    <div id="plannerWraper1">
                        <span id="indexday"></span>일
                        <div id="schedule1_1" style="margin-top: 20px; border-bottom: 2px solid black;">
                            <label for="">
                                <span>일정제목 </span>
                                <a id="removesc" style="float: right; margin-right: 5px; color: red;">-</a>
                            </label><br>
                            <input class="tripinfo trip_title" type="text" placeholder="일정제목을 입력해 주세요">
                            <br>
                            <label for="">
                                <span>경비</span>
                            </label><br>
                            <input class="tripinfo cost" type="number" placeholder="경비 입력"><br>

                            <label for="">
                                <span>시간</span>
                            </label><br>
                            <input class="tripinfo meetime" type="time" placeholder="시간 입력"><br>

                            <label for="">
                                <span>메모</span>
                            </label><br>
                            <textarea class="tripinfo memo" style="height:150px; width: 98%; resize: none;"
                                placeholder="메모 입력"></textarea> <br>

                            <label for=""></label>
                            <span>장소</span><br>
                            <input class="tripinfo" id="meetplace" type="text" placeholder="장소입력"><br>
                            <div class="maplace">
                            </div>

                        </div>
                    </div>
                </div>
                <div class="col-md-4" id="complaner">
                    <!-- <div class="slider-wrapper"> -->
                            <!-- 첫번쨰 day-1 -->
                            <div class="card">
                                <h2 class="card-header main-back" style="height:10%;">Day-1(경비:10만원)</h2>
                                <div class="card-body" style="overflow: auto; height: 90%;">
                                    <div class="btn p-cont" data-toggle="modal" data-target="#myModal">
                                        <h1 class="font-weight-bold">08:00</h1>
                                        <h2>서울역</h2>
                                        <h3>경비 : 10,000원 </h3>
                                    </div>
                                    <div class="btn p-cont" data-toggle="modal" data-target="#myModal">
                                        <h1 class="font-weight-bold">10:00</h1>
                                        <h2>동대문</h2>
                                        <h3>경비 : 10,000원 </h3>
                                    </div>
                                    <div class="btn p-cont" data-toggle="modal" data-target="#myModal">
                                        <h1 class="font-weight-bold">14:00</h1>
                                        <h2>을지로</h2>
                                        <h3>경비 : 10,000원 </h3>
                                    </div>
                                    <div class="btn p-cont" data-toggle="modal" data-target="#myModal">
                                        <h1 class="font-weight-bold">16:00</h1>
                                        <h2>서울역</h2>
                                        <h3>경비 : 10,000원 </h3>
                                    </div>

                                    <div class="btn p-cont" data-toggle="modal" data-target="#myModal">
                                        <h1 class="font-weight-bold">20:00</h1>
                                        <h2>술집</h2>
                                        <h3>경비 : 10,000원 </h3>
                                    </div>
                                    <div style="width:100%; height: 350px; background-color: yellow;"></div>
                                </div>
                            </div>
                            <!-- 첫번쨰 day 끝 -->
                    <!-- </div> -->
                    <!-- slider wrapper end -->
                </div>
                <!-- complaner end -->
            
                

                <div class="col-md-3" id="chatpart">
                    <div class="chatarea" style="width: 100%; height: 75%; overflow: auto;">
                        <div class="chatbox chatsample" style="margin-top: 10px; margin-bottom: 10px;">
                            <span class="userId">지연지연</span><br>
                            <textarea style="width: 98%; height: 100px;  resize: none;" readonly>아 죽겠다
                            </textarea>
                        </div>
                        <div class="chatbox" style="margin-top: 10px; margin-bottom: 10px;">
                            <span class="userId">덕수형님</span><br>
                            <textarea style="width: 98%; height: 100px;  resize: none;" readonly>집에 가고 싶다
                            </textarea>
                        </div>
                        <div class="chatbox" style="margin-top: 10px; margin-bottom: 10px;">
                            <span class="userId">조유상이</span><br>
                            <textarea style="width: 98%; height: 100px;  resize: none;" readonly>언제 가냐
                            </textarea>
                        </div>
                    </div>

                    <div class="inputchat" style="width: 100%; height: 20%;">
                        <textarea id="mymsg" style="width: 75%; height: 100%;  resize: none;"></textarea>
                        <a id="sand" style="position: relative; left: 20px; bottom: 70px;">전송</a>
                    </div>
                </div>
            </div>
            <!-- 2번쨰줄 끝 -->

            <div class="row">
                <div class="col-md-1 addbtn">
                    <div>
                        <br>
                        <a id="adddays1" style="color: royalblue;">일차 추가</a><br>
                        <a >이용 방법</a>
                    </div>
                </div>
                <div class="col-md-11">
                    <div >
                        
                        <a id="addsc" style="color: royalblue; ">일정 추가</a>
                        <a type="submit" >수정완료</a>
                        <a id="savebt">저장</a>
                    </div>
                </div>
            </div> 
        </form>

    </div>
	
		<!-- 소켓 스크립트 -->
<script type="text/javascript">
	$(document).ready(function() {
		
		
		
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
			sock.send(JSON.stringify({chatRoomId: "${selectRoom}", type: 'JOIN', writer: "${userId}", content: ""}));
		});
	});
	
	// 웹소켓을 지정한 url로 연결한다.
	var sockurl = "<c:url value="/echo"/>";
	let sock = new SockJS(sockurl);
	sock.onmessage = onMessage;
	sock.onclose = onClose;
	
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
	
    <script>

        $(function () {

            // var startday = $("#startrip").val();
            // var endday = $("#endtrip").val();
            // var index = endtrip - startrip;

            var index = 1;

            $("#indexdays1").text(index);
            $("#indexday").text(index);



            $("#adddays1").on("click", function () {
                index++;

                var days = $("#days1").clone(true).attr('id', 'days' + index);
                days.children("#indexdays1").attr('id', 'indexdays' + index).text(index);
                days.children("#adddays1").attr('id', 'adddays' + index);
                days.children("[id^=removedays]").attr('id', 'removedays' + index);

                var plan = $("#plannerWraper1").clone(true).attr('id', 'plannerWraper' + index).css("display", "none");
                plan.children("#indexday").attr('id', 'indexday' + index).text(index);


                $("#plandays").append(days);
                $("#inputplanner").append(plan);

            });

            $('[id^=days]').on("click", function () {
                var indexi = $(this).children('[id^=indexdays]').text();
                var planner = $("#plannerWraper" + indexi)

                if (planner.css("display") == "none") {
                    planner.css("display", "block")
                    planner.siblings('[id^=plannerWraper]').css("display", "none")
                }
            })


            $("[id^=removedays]").click(function () {
                index--;
                this.closest("div").remove();
            })



            var sc = 1;
            $("#addsc").on("click", function () {
                sc++
                var addsc = $("#schedule1_1").clone(true).attr('id', 'schedule' + index + "_" + sc);

                for (i = 1; i <= index; i++) {
                    if ($("#plannerWraper" + i).css("display") == "block") {
                        $("#plannerWraper" + i).append(addsc);
                    }
                }
            })

            $("[id^=removesc]").click(function () {
                sc--;
                this.closest("div").remove();
            })


            $("#copy").on("click", function () {

                var urlbox = document.getElementById('url');
                urlbox.select();
                document.execCommand('Copy');
                alert('URL 이 복사 되었습니다.');
            })

            
            $("#savebt").on("click", function () {
                var price = 0;
                for (i = 1; i <= index; i++) {
                    for (j = 1; j <= sc; j++) {
                        console.log("i : " + i + ",j : " + j + " = " + $("#schedule" + i + "_" + j).children(".cost").val());
                        var result = parseInt($("#schedule" + i + "_" + j).children(".cost").val());
                        price += result
                    }
                }
                $("#totalcost").text(price + "원");
            })

            $(".userId").on("click", function () {
                var id = $(this).text()
                $("#addgrade").val(id);

            });

            $("#sand").on("click", function () {
                var msgbox = $(".chatsample").clone();
                var msg = $("#mymsg").val();
                var myid = "후니훈";
                msgbox.children("span").text(myid);
                msgbox.children("textarea").val(msg);

                $(".chatarea").append(msgbox);


            })
        });



    </script>

</body>

</html>