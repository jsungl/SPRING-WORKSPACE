<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>


<jsp:include page="/views/common/header.jsp">
	<jsp:param value="SpringBoot - 게시판" name="title"/>
</jsp:include>


<div class="img_area mt-5">
	<img src="${pageContext.request.contextPath}/resources/images/springboot-logo2.png" id="center-image" alt="springboot로고" class="d-block mx-auto mt-5"/>	
</div>


<jsp:include page="/views/common/footer.jsp"></jsp:include>	