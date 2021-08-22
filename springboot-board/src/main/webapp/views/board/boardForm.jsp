<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<jsp:include page="/views/common/header.jsp">
	<jsp:param value="게시글 작성" name="title"/>
</jsp:include>
<style>
div#board-container{width:400px; margin:0 auto; text-align:center;}
div#board-container input{margin-bottom:15px;}
div#board-container textarea{height:150px;}
/* 부트스트랩 : 파일라벨명 정렬*/
div#board-container label.custom-file-label{text-align:left;}

</style>

<div id="board-container">
	<form name="boardFrm" action="${pageContext.request.contextPath}/board/boardEnroll.do" method="post" onsubmit="return boardValidate();" enctype="multipart/form-data">
		<input type="text" class="form-control" placeholder="제목" name="title" id="title" required>
		<!-- input:file소스 : https://getbootstrap.com/docs/4.1/components/input-group/#custom-file-input -->
		<div class="input-group mb-3" style="padding:0px;">
		  <div class="input-group-prepend" style="padding:0px;">
		    <span class="input-group-text">첨부파일</span>
		  </div>
		  <div class="custom-file">
		    <input type="file" class="custom-file-input" name="upFile" id="upFile1" multiple>
		    <label class="custom-file-label" for="upFile1">파일을 선택하세요</label>
		  </div>
		</div>
		
	    <textarea class="form-control" name="content" placeholder="내용" required></textarea>
		<br />
		<input type="submit" class="btn btn-outline-success" value="저장">
	</form>
	
</div>
<script>
/* textarea에도 required속성을 적용가능하지만, 공백이 입력된 경우 대비 유효성검사를 실시함. */
function boardValidate(){
	var $content = $("[name=content]");
	if(/^(.|\n)+$/.test($content.val()) == false){
		alert("내용을 입력하세요");
		return false;
	}
	return true;
}

$(() => {
	$("[name=upFile]").change(e => {
		//파일명 가져오기
		var file = $(e.target).prop('files')[0]; //javascript객체 
		//var file = $(e.target).prop('files'); 
		console.log(file);
		//console.log(files[0]);
		var $label = $(e.target).next();
		
		//label 적용
		//$label.html(file.length > 1 ? file[0].name + " 외 " + (file.length - 1) + "개"  : "파일을 선택하세요.");		
		$label.html(file ? file.name : "파일을 선택하세요.");		
	});

});

</script>

<jsp:include page="/views/common/footer.jsp"></jsp:include>
