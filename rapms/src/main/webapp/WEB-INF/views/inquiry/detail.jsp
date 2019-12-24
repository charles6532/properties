<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

	<%@include file="../common/head.jsp" %>
    <%@include file="../common/top.jsp" %>
    <%@ taglib prefix="sb" uri="/WEB-INF/spring/tld/starbocks.tld" %>
    
    <script type='text/javascript' src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.5.0/js/bootstrap-datepicker.min.js"></script><!--daypicker-->
    <script type='text/javascript'>
    $(function() {
        $('.col-md-4.date').datepicker({});
    });
</script>
    
    <div class="theme-layout">
		<div class="inner-head overlap">
            <div style="background: url(${pageContext.request.contextPath}/img/parallax1.jpg) repeat scroll 50% 422.28px transparent;" class="parallax scrolly-invisible no-parallax"></div><!-- PARALLAX BACKGROUND IMAGE -->	
            <div class="container">
                <div class="inner-content">
                    <span><i class="ti ti-home"></i></span>
                    <h2>문의 상세보기</h2>
                    <ul>
                        <li><a href="${pageContext.request.contextPath}/main" title="">HOME</a></li>
                        <li><a href="${pageContext.request.contextPath}/inquiry/detail" title="">문의 상세보기</a></li>
                    </ul>
                </div>
            </div>
        </div><!-- inner Head -->
        

         <section class="block ">
            <div class="container">
                <div class="row">
                    <div class="col-md-12">
                    		<div class="heading4" >
	                            <h2>문의 상세보기</h2> 
	                        </div>
                    	<div class="container"  style="width:800px;">
	                        <div class="container col-md-8" style="width:800px;">
	                            <form id="inquirydetail" name="inquirydetail" method="post" class="property-form" role="form" 
	                            		action="${pageContext.request.contextPath}/inquiry/reply/${sb:getIdStr(inquirydo.id)}/${sb:getIdStr(inquirydo.thrId)}">
                                <div class="control-group">
                                    <div class="group-title">Inquiry Description</div>
                                    <div class="group-container row">
                                        <div class="col-md-12">
                                            <div class="form-group s-prop-title">
                                                <label for="title">문의 제목&nbsp;&#42;</label>
                                                <input type="text" id="subject" class="form-control" value="${inquirydo.subject}" name="subject" required="" readonly>
                                            </div>
                                            
                                			<div class="form-group s-com-title">
                                                <label for="title">문의 내용&nbsp;&#42;</label><br>
                                                <textarea cols="80" rows="4" name="content" readonly>${inquirydo.content}</textarea>
                                            </div>        
                                        </div>
                                        <input type="hidden" id="id" name="id" value="${inquirydo.id}">
                                        <input type="hidden" id="userId" name="userId" value="${sbmemberdo.userId}">
		                                <div class="submit row" style="text-align:center; clear: both; margin-top: 25px;">
		                                    <div style="display:inline-block; width:400px;">
		                                        <input type="submit" class="btn btn-sm" id="property_submit" value="답변하기" style="margin:5px">
		                                        <input type="reset" class="btn btn-sm" id="property_cancel" value="취소" style="margin:5px">
												<button type="button" class="btn btn-sm" onclick="location.href='${pageContext.request.contextPath}/inquiry/list/${sb:getIdStr(sbmemberdo.userId)}/${sb:getIdStr(1)}'">리스트로 돌아가기</button>		                                    
		                                    </div>
		                                </div>
                                        
								
                                        
                                        
                                
                            </form>
		                        </div>
		            	</div>
                    </div>
                </div>
            </div>
        </section>

            

        <%@include file="../common/footer.jsp" %>
    