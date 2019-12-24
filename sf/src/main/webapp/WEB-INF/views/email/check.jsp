<?xml version="1.0" encoding="UTF-8"?>
<%@ page language="java" contentType="text/xml; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>

<sql:setDataSource var = "snapshot" driver = "oracle.jdbc.driver.OracleDriver"
         url = "jdbc:oracle:thin:@localhost:1521:xe"
         user = "encore"  password = "encore"/>

<sql:query var="rs" dataSource="${snapshot}">
	select count(*) from sb_user where email=?
	<sql:param value="${param.email}"/>
</sql:query>

<c:forEach var="row" items="${rs.rowsByIndex}">
	<c:if test="${row[0] == 1 }">
		<result><![CDATA[
			{
				code:"success",
				message:"사용할 수 없는 이메일입니다"
			}
		]]></result>
	</c:if>
	<c:if test="${row[0] == 0 }">
		<result><![CDATA[
			{
				code:"success",
				message:"사용할 수 있는 이메일입니다"
			}
		]]></result>
	</c:if>
</c:forEach>