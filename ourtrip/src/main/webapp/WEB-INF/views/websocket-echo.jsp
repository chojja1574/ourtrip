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
	});
	
	// 웹소켓을 지정한 url로 연결한다.
	let sock = new SockJS("<c:url value="/echo"/>");
	sock.onmessage = onMessage;
	sock.onclose = onClose;
	
	// 메시지 전송
	function sendMessage() {
		sock.send("m_" + $("#message").val());
	}
	
	function redbtn() {
		sock.send("b_red");
	}
	function bluebtn() {
		sock.send("b_blue");
	}
	function greenbtn() {
		sock.send("b_green");
	}
	
	function chageback(color){
		console.log(color);
		$("#testbox").css("background",color);
	}
	
	// 서버로부터 메시지를 받았을 때
	function onMessage(msg) {
		var data = msg.data;
		var dataFlag = data.substring(data.indexOf(':')+2);
		
		console.log(data + " / " + data[0]);
		console.log(dataFlag + " / " + dataFlag[0]);
		switch(dataFlag[0]){
		case 'm':
			console.log("m");
			$("#data").append(data + "<br/>");
			break;
		case 'b':
			switch(dataFlag[2]){
			case 'r' :
				chageback("red");
				break;
			case 'b' :
				chageback("blue");
				break;
			case 'g' :
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