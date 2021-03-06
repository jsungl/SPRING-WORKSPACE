<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<jsp:include page="/WEB-INF/views/common/header.jsp">
	<jsp:param value="Dev 목록" name="title"/>
</jsp:include>
<table class="table w-75 mx-auto">
    <tr>
      <th scope="col">번호</th>
      <th scope="col">이름</th>
      <th scope="col">경력</th>
      <th scope="col">이메일</th>
      <th scope="col">성별</th>
      <th scope="col">개발가능언어</th>
      <th scope="col">수정 | 삭제</th>
    </tr>
    
   <c:if test="${empty list}">
   	<td>등록된 dev가 존재하지않습니다.</td>
   </c:if> 
   
   <c:if test="${not empty list}">
	   <c:forEach items="${list}" var="dev">
	   	<tr>	
	   		<td scope="row">${dev.no}</td>
	   		<td>${dev.name}</td>
	   		<td>${dev.career}</td>
	   		<td>${dev.email}</td>
	   		<td>${dev.gender}</td>
	   		<td>
	   			<c:forEach items="${dev.lang}" var="lang" varStatus="vs">
	   				${lang}${vs.last ? "" : ","}
	   			</c:forEach>
	   		</td>
	   		<td>
	   			<button class="btn btn-outline-secondary" onclick="updateDev();" data-no="${dev.no}">수정</button>
	   			<button class="btn btn-outline-danger" onclick="deleteDev();" data-no="${dev.no}">삭제</button>
	   		</td>
	   	</tr>
	   </c:forEach>
   </c:if>   
</table>

<form id="deleteFrm">
	<input type="hidden" name="no" />
</form>
<form id="updateFrm">
	<input type="hidden" name="no" />
</form>

<form method="post">
		<input type="text" name="startPlace" id="startPlace" placeholder="출발지"/>
		<input type="text" name="endPlace" id="endPlace" placeholder="도착지"/>
		<input type="submit" class="searchBtn" value="검색"/>
</form>





<script>

	function updateDev() {
		//GET방식 /demo/updateDev?no=123 ---> devUpdateForm.jsp
		//POST  /demo/updateDev ---> redirect:/demo/devList.do
		var no = $(event.target).data("no");
		var $updateFrm = $("#updateFrm");
		$updateFrm.attr("action","${pageContext.request.contextPath}/demo/updateDev.do")
				  .attr("method","GET")
				  .find("[name=no]").val(no);
		$updateFrm.submit();

		//실습답안(따로 폼안만들고 처리방식)
		//location.href = `${pageContext.request.contextPath}/demo/updateDev.do?no=\${no}`;
		
	}

	function deleteDev() {
		//POST  /demo/deleteDev.do ---> redirect:/demo/devList.do
		if(confirm("정말 삭제하시겠습니까?")){
			var no = $(event.target).data("no");
			var $deleteFrm = $("#deleteFrm");
			$deleteFrm.attr("action","${pageContext.request.contextPath}/demo/deleteDev.do")
				   	  .attr("method","POST")
				   	  .find("[name=no]").val(no);
			$deleteFrm.submit();
		}
	}


</script>
<jsp:include page="/WEB-INF/views/common/footer.jsp"></jsp:include>
