<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="ko">
    <head>
    <meta charset="UTF-8">
        <title>채팅방 선택</title>
    </head>
    <body>
        <form method="GET" action="/ourtrip/planner1/update">
            ID : <input type="text" name="userId" id="userId">
            ChatRoomId : <input type="text" name="selectRoom" id="selectRoom">

            <button>입장</button>
        </form>
    </body>
</html>