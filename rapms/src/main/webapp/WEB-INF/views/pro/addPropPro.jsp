<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="kr.starbocks.api.dao.SbUserDaoImpl"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="../common/top.jsp" %>
<c:if test="${result == 0 }">
	<script type="text/javascript">
		<!--
		alert( "가입실패" );
		-->
	</script>
	<meta http-equiv="refresh" content="0; url=${pageContext.request.contextPath}/properties/addProp">
</c:if>
<c:if test="${result != 0 }">
	<c:redirect url="/prop"/>
</c:if>
<%@ include file="../common/footer.jsp" %>
