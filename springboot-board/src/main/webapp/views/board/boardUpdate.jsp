<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<jsp:include page="/views/common/header.jsp">
	<jsp:param value="게시판 상세보기" name="title"/>
</jsp:include>
<style>
div#board-container{width:400px;}
/* input, button {margin-bottom:15px;} */
textarea {height:150px;margin-bottom:15px;}
button { overflow: hidden; }
/* 부트스트랩 : 파일라벨명 정렬*/
div#board-container label.custom-file-label{text-align:left;}
</style>
<div id="board-container" class="mx-auto text-center">    
    <form name="boardFrm" action="${pageContext.request.contextPath}/board/boardUpdate.do?no=${board.no}" method="post" onsubmit="return boardValidate();" enctype="multipart/form-data">
		<input type="text" class="form-control" placeholder="제목" name="title" id="title" value="${board.title}" required>
		<!-- input:file소스 : https://getbootstrap.com/docs/4.1/components/input-group/#custom-file-input -->
		  <c:choose>
		  	<c:when test="${board.hasAttachment}">
			  	<c:forEach items="${board.attachList}" var="attach">
				  <%-- <div class="custom-file mb-3 mt-3">
				    <input type="file" class="custom-file-input" name="upFile" id="upFile1" multiple>
				    <label class="custom-file-label" for="upFile1">${attach.originalFileName}</label>
				  </div>
				  <button type="button" class="btn btn-danger">삭제</button> --%>
				  <ul class="nav" id="ulTitle">
					  <li class="nav-item my-auto ml-1">
					  	첨부파일 - ${attach.originalFileName}
					  </li>
					  <li class="nav-item ml-auto">
					  	<button type="button" class="btn btn-danger" id="deleteAttachment">삭제</button>
					  </li>
				  </ul>
				</c:forEach>
				<input type="hidden" class="chkAttachment" name="hasAttachment" value="${board.hasAttachment}" />
				<input type="hidden" class="delAttachmentYN" name="delAttachmentYN" value="false" />
		  	</c:when>
		  	<c:otherwise>
		  	<div class="input-group mb-3 mt-3" style="padding:0px;">	
		  		<div class="input-group-prepend" style="padding:0px;">
			    	<span class="input-group-text">첨부파일</span>
			  	</div>
		  		<div class="custom-file">
				   <input type="file" class="custom-file-input" name="upFile" id="upFile1" multiple>
				   <label class="custom-file-label" for="upFile1">파일을 선택하세요</label>
				</div>
			</div>
		  	</c:otherwise>		  
		  </c:choose>		  
		
		
		<div class="input-group mb-3 mt-3" id="selectAttachment" style="padding:0px; display:none;">	
	  		<div class="input-group-prepend" style="padding:0px;">
		    	<span class="input-group-text">첨부파일</span>
		  	</div>
	  		<div class="custom-file">
			   <input type="file" class="custom-file-input" name="upFile" id="upFile1" multiple>
			   <label class="custom-file-label" for="upFile1">파일을 선택하세요</label>
			</div>
		</div>
		
	    <textarea class="form-control" name="content" placeholder="내용" required>${board.content}</textarea>
		<br />
		<input type="submit" class="btn btn-outline-success" value="저장">
		<input type="button" value="취소" id="btn-cancel" class="btn btn-outline-danger"/>
	</form>		  
</div>

<script>
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

	$("#btn-cancel").click(e => {
		location.href = '${referer}';
	});

	$("#deleteAttachment").click(e => {
		$("#ulTitle").hide();
		$("#selectAttachment").show();
		$(".chkAttachment").val(false);
		$(".delAttachmentYN").val(true);
	});

});

</script>
<jsp:include page="/views/common/footer.jsp"></jsp:include>
