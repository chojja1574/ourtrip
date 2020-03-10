<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!-- jQuery CDN-->

<script src="https://code.jquery.com/jquery-1.9.0.js"
	integrity="sha256-TXsBwvYEO87oOjPQ9ifcb7wn3IrrW91dhj6EMEtRLvM="
	crossorigin="anonymous"></script>

<!-- Web socket CDN -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/sockjs-client/1.4.0/sockjs.js"></script>

<!-- json2.js -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/json2/20160511/json2.js"></script>

<style type="text/css">
	.testbox{
		width: 300px;
		height: 50px;
	}
</style>
</head>
<body>
	<input type="text" id="message" />
	<input type="button" id="sendBtn" value="전송" />
	<br><br>
	<div class="testbox" id="testbox" style="background:red"></div>
	<br>
	<button type="button" id="redbtn">red</button>
	<button type="button" id="bluebtn">blue</button>
	<button type="button" id="greenbtn">green</button>
	<span>${userId} + ${selectRoom}</span>
	<br>
	<button type="button" id="jsonTest">json test</button>
	<button type="button" id="join">join</button>
	<div id="data"></div>
</body>
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
		})
	});
	
	// 웹소켓을 지정한 url로 연결한다.
	let sock = new SockJS("<c:url value="/echo"/>");
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
</html>