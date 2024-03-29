﻿<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<jsp:include page="/WEB-INF/views/common/header.jsp">
	<jsp:param value="게시판" name="title"/>
</jsp:include>

<!-- https://jqueryui.com/autocomplete/ -->
<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>

<style>
/*글쓰기버튼*/
input#btn-add{float:right; margin: 0 0 15px;}
/* data-no속성이 있는 tr */
tr[data-no]{
	cursor:pointer;
}
</style>
<script>
function goBoardForm(){
	location.href = "${pageContext.request.contextPath}/board/boardForm.do";
}

$(() => {
	$("tr[data-no]").click(e => {
		//화살표함수안에서는 this는 e.target이 아니다.
		console.log(e.target); //td태그클릭 -> 부모tr로 이벤트 전파(bubbling)
		var $tr = $(e.target).parent(); //tr태그
		var no = $tr.data("no");
		location.href = "${pageContext.request.contextPath}/board/boardDetail.do?no=" + no;
	});

	
    $("#searchTitle").autocomplete({
    	source: function(request, response){
     		  console.log(request); //{term:"안녕"}
     		  //console.log(response);
     		  //response([{label:'a', value:'a'}, {label:'b', value:'b'}]);
     		  
     		  //사용자입력값전달 ajax요청 -> success함수안에서 response호출 
     		  $.ajax({
         		  url: "${pageContext.request.contextPath}/board/boardSearch.do",
         		  data: {
             		  searchTitle: request.term
                  },
                  success(data){
                      console.log(data); //{list: Array(60), searchTitle: "안녕"}
					  const {list} = data;
					  //배열을 새로만듬(순수자바스크립트)
					  /* list.map((board) => {
						  console.log(board);
						  return {
							  label: board.title,
							  value: board.title
						  }
					  }); */
					  console.log(list); //객체배열 [{no: 1, title: "안녕하세요, 게시판입니다 - 1", memberId: "abcde", content: "반갑습니다", regDate: 1518188400000},{},......{}]
					  const arr = 
							list.map(({no,title}) => ({
								label: title,//노출텍스트
								value: title,//내부적으로 처리될값
								no		
							}));
						console.log(arr); //객체배열 [{label: "안녕하세요, 게시판입니다 - 1", value: "안녕하세요, 게시판입니다 - 1", no: 1},{},.....{}]
						response(arr); //콜백함수 호출
					  	
                  },
                  error(xhr,status,err){
        			  console.log(xhr,status,err);
        		  }
   	          });
     		  
  		},
  		select: function(event, selected){
  		 	// 클릭했을때, 해당게시글 상세페이지로 이동
  			console.log("select : ", selected); //객체 {item: {label: "안녕하세요, 게시판입니다 - 3", value: "안녕하세요, 게시판입니다 - 3", no: 3}}
  			const {item: {no}} = selected; //no = 3
			location.href = "${pageContext.request.contextPath}/board/boardDetail.do?no=" + no; 
  	  	},
  		focus: function(event, focused){
  		 return false;
  		},
  		autoFocus: true, 
		minLength: 2 //2글자이상 입력해야 자동완성글자가 나온다
  		
    });
});
</script>
<section id="board-container" class="container">
	<input type="search" placeholder="제목 검색..." id="searchTitle" class="form-control col-sm-3 d-inline"/>
	<input type="button" value="글쓰기" id="btn-add" class="btn btn-outline-success" onclick="goBoardForm();"/>
	<table id="tbl-board" class="table table-striped table-hover">
		<tr>
			<th>번호</th>
			<th>제목</th>
			<th>작성자</th>
			<th>작성일</th>
			<th>첨부파일</th> <!-- 첨부파일 있을 경우, /resources/images/file.png 표시 width: 16px-->
			<th>조회수</th>
		</tr>
		<c:forEach items="${list}" var="board">
		<tr data-no="${board.no}">
			<td>${board.no}</td>
			<td>${board.title}</td>
			<td>${board.memberId}</td>
			<td><fmt:formatDate value="${board.regDate}" pattern="yy/MM/dd"/></td>
			<td>
				<%-- <c:if test="${board.attachCount > 0}">
					<img src="${pageContext.request.contextPath}/resources/images/file.png" width="16px" />
				</c:if> --%>
				<c:if test="${board.hasAttachment}">
					<img src="${pageContext.request.contextPath}/resources/images/file.png" width="16px" />
				</c:if>
				
			</td>
			<td>${board.readCount}</td>
		</tr>
		</c:forEach>
	</table>
	<nav aria-label="Page navigation example">
	  <ul class="pagination justify-content-center">
	    ${pageBar}
	    <%-- <li class="page-item">
	      <a class="page-link" href="#" aria-label="Previous">
	        <span aria-hidden="true">&laquo;</span>
	        <span class="sr-only">Previous</span>
	      </a>
	    </li>
	    <li class="page-item disabled"><a class="page-link" href="#">1</a></li>
	    <li class="page-item"><a class="page-link" href="/spring/board/boardList.do?cpage=2">2</a></li>
	    <li class="page-item"><a class="page-link" href="${pageContext.request.contextPath}/board/boardList.do?cpage=3">3</a></li>
	    <li class="page-item"><a class="page-link" href="${pageContext.request.contextPath}/board/boardList.do?cpage=4">4</a></li>
	    <li class="page-item"><a class="page-link" href="${pageContext.request.contextPath}/board/boardList.do?cpage=5">5</a></li>
	    <li class="page-item">
	      <a class="page-link" href="#" aria-label="Next">
	        <span aria-hidden="true">&raquo;</span>
	        <span class="sr-only">Next</span>
	      </a>
	    </li> --%>
	  </ul>
	</nav>
</section> 

<jsp:include page="/WEB-INF/views/common/footer.jsp"></jsp:include>
