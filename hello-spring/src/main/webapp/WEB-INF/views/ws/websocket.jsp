﻿<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<jsp:include page="/WEB-INF/views/common/header.jsp">
	<jsp:param value="Websocket" name="title"/>
</jsp:include>

	<div class="input-group mb-3">
	    <input type="text" id="message" class="form-control" placeholder="Message">
	    <div class="input-group-append" style="padding: 0px;">
	        <button id="sendBtn" class="btn btn-outline-secondary" type="button">Send</button>
	    </div>
    </div>
    
    <div>
        <ul class="list-group list-group-flush" id="data"></ul>
    </div>

<script>
//페이지 접속시 서버로 websocket 연결을 시도
const ws = new WebSocket(`ws://\${location.host}/spring/websoocket`);
const $data = $("#data"); //메세지를 여기에 쌓기
ws.onopen = e => {
	console.log("onopen : ",e);
	
};

ws.onmessage = e => {
	console.log("onmessage : ",e);
	const {data} = e; //구조분해할당
	$data.append("<li class='list-group-item'>" + data + "</li>");
	
};

ws.onclose = e => {
	console.log("onclose : ",e);
};


//함수등록
//websocket에 메시지 등록
const sendMessage = () => {
	const $message = $("#message");
	if($message.val()){
		ws.send($message.val());
		$message.val("").focus();
	}
};
//함수선언
$("#sendBtn").click(sendMessage);
$("#message").keyup(e => e.keyCode == 13 && sendMessage()); //엔터치면 메세지전송
</script>

<jsp:include page="/WEB-INF/views/common/footer.jsp"></jsp:include>	
