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

        <section class="block ">
            <div class="container">
                <div class="row">
                    <div class="col-md-12">
                    	<div class="heading4" >
	                    	<h2>회원정보수정</h2> 
	                    </div>
	                    <c:set var="memberdo"  value="${memberdo}"/>
                    	<div class="container"  style="width:800px;">
	                        <div class="container col-md-8" style="width:800px;">
		                            <form id="modifyview" name="modifyview" method="post" class="property-form" role="form" action="${pageContext.request.contextPath}/member/modifyviewPro">
			                                <div class="control-group">
			                                    <div class="group-title">Modify your Personal Info.</div>
			                                    <div class="group-container row">
			                                        <div class="col-md-12">
			                                            <div class="form-group s-prop-title">
			                                                <label for="title">별명&nbsp;&#42;</label>
			                                                <input type="text" id="nickname" class="form-control" value="${memberdo.nickname}" 
			                                                	name="nickname" placeholder="${memberdo.nickname}">
			                                            </div>
			                                        </div>
			                                        
			
			                                        <div class="col-md-9">
			                                            <div class="form-group s-com-title">
			                                                <label for="title">주소&nbsp;&#42;</label>
			                                                <input type="text" id="address" class="form-control" value="${memberdo.address}" name="address" 
			                                                	placeholder="${memberdo.address}" required="">
			                                            </div>
			                                        </div>
			                                        <div class="col-md-4">
			                                            <div class="form-group s-prop-dong">
			                                                <label for="title">전화번호&nbsp;</label>
			                                                <input type="text" id="tel" class="form-control" value="${memberdo.tel}" name="tel" 
			                                                	placeholder="${memberdo.tel}" required="">
			                                            </div>
			                                        </div>
			                                        <div class="col-md-4">
			                                            <div class="form-group s-prop-ho">
			                                                <label for="title">우편번호&nbsp;</label>
			                                                <input type="text" id="zipcode" class="form-control" value="${memberdo.zipcode}" name="zipcode" 
			                                                	placeholder="${memberdo.zipcode}" required="">
			                                            </div>
			                                        </div>
			                                    			                                        
													<div class="col-md-3">
			                                           <div class="form-group s-prop-land-size">
			                                               <label for="title">이름&nbsp;&#42;</label>
			                                               <input type="text" id="name" class="form-control" value="${memberdo.name}" name="name" 
			                                               		placeholder="${memberdo.name}" required="">
			                                           </div>
													</div>
													<div class="col-md-4">
			                                            <div class="form-group s-prop-sub_location">
			                                                <label>패스워드&nbsp;</label>
			                                                <input type="password" id="passwd" class="form-control" value="" name="passwd" 
			                                                	required="">
			                                                <input type="password" id="repasswd" class="form-control" value="" name="repasswd" 
			                                                	required="">
			                                            </div>
			                                        </div>
			                                        <input type="hidden" id="email" name="email" value="${memberdo.email}">
			                                        <input type="hidden" id="userId" name="userId" value="${memberdo.userId}">
			                                        <div class="submit row" style="text-align:center; clear: both; margin-top: 25px;">
					                                    <div style="display:inline-block; width:350px;">
					                                        <input type="submit" class="btn btn-lg flat-btn" id="property_submit" value="회원정보수정" style="margin:5px">
					                                        <input type="reset" class="btn btn-lg flat-btn" id="property_cancel" value="취소" style="margin:5px">
					                                    </div>
					                                </div>
		                                        </div>
											</div>
		                                </div>
                            		</form>
								</div>
			            	</div>
	                    </div>
	                </div>
	            </div>
	        </section>

        <%@include file="../main/bottom.jsp" %>
    