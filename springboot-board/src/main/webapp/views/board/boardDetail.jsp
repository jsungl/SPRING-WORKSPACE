<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<jsp:include page="/views/common/header.jsp">
	<jsp:param value="게시판 상세보기" name="title"/>
</jsp:include>
<style>
div#board-container{width:600px;}
div#board-container ul#title {border-top:1px solid #ccc;border-bottom:1px solid #ccc;background:#fcfcfc;}
input, button, textarea {margin-bottom:15px;}
button { overflow: hidden; }
/* 부트스트랩 : 파일라벨명 정렬*/
div#board-container label.custom-file-label{text-align:left;}
</style>
			   
<div id="board-container" class="mx-auto text-center">
	<ul class="nav" id="title">
	  <li class="nav-item mr-auto mt-2 mb-2">
	  	<span class="h3">${board.title}</span>
	  </li>
	  <li class="nav-item ml-auto my-auto">
	  	<span><fmt:formatDate value="${board.regDate}" pattern="yyyy-MM-dd"/></span>
	  </li>
	</ul>
	
	
	<ul class="nav justify-content-end" style="border-bottom: 1px solid #eee;">
		<li class="nav-item mr-auto my-auto">
			<c:if test="${board.hasAttachment}">
				<c:forEach items="${board.attachList}" var="attach">
					<div onclick="location.href='${pageContext.request.contextPath}/board/fileDownload.do?no=${attach.no}';" style="cursor:pointer;">
						<img src="${pageContext.request.contextPath}/resources/images/file.png" width="16px"/>
						첨부파일 - ${attach.originalFileName}
					</div>
				<%-- <button type="button" 
						class="btn btn-outline-info"
						onclick="location.href='${pageContext.request.contextPath}/board/fileDownload.do?no=${attach.no}';"
						style="margin:0;">
					첨부파일 - ${attach.originalFileName}
				</button> --%>
				</c:forEach>
			</c:if>
		</li>
		<li class="nav-item my-auto" style="color:#888;">
			조회 수 ${board.readCount}
		</li>
	</ul>

	<br />
	
	<div id="board-content" class="text-left">
	    ${board.content}	
	</div>
    
    <br />
    <br />
    <br />
    <br />
    <br />		  
    <input type="button" value="수정" id="btn-update" class="btn btn-outline-success"/>
    <input type="button" value="삭제" id="btn-delete" class="btn btn-outline-danger"/>		      		  
	<input type="hidden" value="${board.no}" id="board-no"/>
	<form id="boardDeleteFrm" action="${pageContext.request.contextPath}/board/boardDelete.do?no=${board.no}" method="post">
		<input type="hidden" value="deleteForm" />
	</form>	   
</div>

<script>
$(() => {
	$("#btn-update").click(e => {
		console.log(e.target);
		var no = $("#board-no").val();
		location.href = "${pageContext.request.contextPath}/board/boardUpdate.do?no=" + no;	
	});

	$("#btn-delete").click(e => {
		console.log(e.target);
		if(confirm("정말 삭제하시겠습니까?")){
			var no = $("#board-no").val();
			console.log(no);
			 $("#boardDeleteFrm").submit();	 
			//location.href = "${pageContext.request.contextPath}/board/boardDelete.do?no=" + no;					
		}		
	});
	
});

</script>
<jsp:include page="/views/common/footer.jsp"></jsp:include>
