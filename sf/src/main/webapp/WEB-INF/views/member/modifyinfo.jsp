<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>


    <%@include file="../main/head.jsp" %>
    
    <%@include file="../main/top.jsp" %>

    <div class="theme-layout">
        
        <div class="inner-head overlap">
            <div style="background: url(${pageContext.request.contextPath}/img/parallax1.jpg) repeat scroll 50% 422.28px transparent;" class="parallax scrolly-invisible no-parallax"></div>
            <!-- PARALLAX BACKGROUND IMAGE -->	
            <div class="container">
                <div class="inner-content">
                    <span><i class="fa fa-bolt"></i></span>
                    <h2>회원정보 수정</h2>
                    <ul>
                        <li><a href="${pageContext.request.contextPath}/main" title="">HOME</a></li>
                        <li><a href="${pageContext.request.contextPath}/member/modifyview" title="">회원정보 수정</a></li>
                    </ul>
                </div>
            </div>
        </div><!-- inner Head -->

        <section class="block">
            <div class="container">
                <div class="row">
                    <div class="col-md-12">
                        <div class="login-sec"> 
                            <div class="container"  style="width:600px;">
                                <div class="container col-md-4" style="width:600px;">
                                    <div class="account-user">
                                        <div class="logo" style="margin-left:-4em; margin-bottom:-0.7em;">
		                                    <a href="#" title="">
		                                        <img src="${pageContext.request.contextPath}/img/starbocks.png" width="60px" height="60px" style="margin-right:5px;float:left">
		                                        <span>Starbocks</span>
		                                        <strong>RENT PROPERTIES</strong>
		                                    </a>
		                                </div><!-- LOGO -->
                                       
                                        <form id="modifyform" name="modifyform" method="post" action="${pageContext.request.contextPath}/member/modifyinfoPro">
                                            <h4>회원정보 수정</h4>
                                            <div class="field">
                                                <input type="password" placeholder="비밀번호를 입력하세요" id="passwd" name="passwd"/>
                                            </div>
                                            <div class="field">
                                                <input type="password" placeholder="비밀번호를 다시 입력하세요" id="repasswd" name="repasswd"/>
                                            </div>
                                            <div class="field">
                                                <input type="submit" value="SEND NOW" class="flat-btn" />
                                            </div>
                                            <%-- ${sbuserdo.userId} --%>
                                            <input type="hidden" id="userId" name="userId" value="${sbmemberdo.userId}">
                                            <input type="hidden" id="email" name="email" value="${sbmemberdo.email}">
                                        </form>
                                        <c:set var="modifyresult"  value="${modifyresult}"/>
                                            <c:if test="${modifyresult == 0}">
                                            	${"비밀번호가 틀렸습니다"}
                                            </c:if>
                                    </div>
                                </div>
                            </div>  
                        </div>
                    </div>
                </div>
            </div>
        </section>

        <%@include file="../main/bottom.jsp" %>
    