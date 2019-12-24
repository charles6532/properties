<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

	<%@include file="../main/head.jsp" %>
    <%@include file="../main/top.jsp" %>
    
    
    <div class="theme-layout">
		<div class="inner-head overlap">
            <div style="background: url(${pageContext.request.contextPath}/img/parallax1.jpg) repeat scroll 50% 422.28px transparent;" class="parallax scrolly-invisible no-parallax"></div><!-- PARALLAX BACKGROUND IMAGE -->	
            <div class="container">
                <div class="inner-content">
                    <span><i class="ti ti-home"></i></span>
                    <h2>문의하기</h2>
                    <ul>
                        <li><a href="${pageContext.request.contextPath}/main" title="">HOME</a></li>
                        <li><a href="${pageContext.request.contextPath}/inquiry/write" title="">문의하기</a></li>
                    </ul>
                </div>
            </div>
        </div><!-- inner Head -->
        

         <section class="block ">
            <div class="container">
                <div class="row">
                    <div class="col-md-12">
                    		<div class="heading4" >
	                            <h2>문의하기</h2> 
	                        </div>
                    	<div class="container"  style="width:800px;">
	                        <div class="container col-md-8" style="width:800px;">
	                            <form id="inquirywrite" name="inquirywrite" method="post" class="property-form" role="form" 
	                            		action="${pageContext.request.contextPath}/inquiry/writePro">
                                <div class="control-group">
                                    <div class="group-title">Inquiry Description</div>
                                    <div class="group-container row">
                                        <div class="col-md-12">
                                            <div class="form-group s-prop-title">
                                                <label for="title">문의 제목&nbsp;&#42;</label>
                                                <input type="text" id="subject" class="form-control" value="" name="subject" required="">
                                            </div>
                                            
                                			<div class="form-group s-com-title">
                                                <label for="title">문의 내용&nbsp;&#42;</label><br>
                                                <textarea cols="100" rows="8" name="content"></textarea>
                                            </div>        
                                        </div>
                                        
								<input type="hidden" id="addedBy" name="addedBy" value="${sbmemberdo.userId}">
								<div class="submit row" style="text-align:center; clear: both; margin-top: 25px;">
                                    <div style="display:inline-block; width:300px;">
                                        <input type="submit" class="btn btn-lg flat-btn" id="property_submit" value="문의하기" style="margin:5px">
                                        <input type="reset" class="btn btn-lg flat-btn" id="property_cancel" value="취소" style="margin:5px">
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
    