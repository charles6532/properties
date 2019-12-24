<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

	<%@include file="../common/head.jsp" %>
    <%@include file="../common/top.jsp" %>
    <script type="text/javascript">
    function putZero(  ){
		
    	inquiryreply.pId.value = Number(inquiryreply.pId.value);
    	inquiryreply.thrId.value = Number(inquiryreply.thrId.value);
    	inquiryreply.refId.value = Number(inquiryreply.refId.value);
    	inquiryreply.addedBy.value = Number(inquiryreply.addedBy.value);
    	inquiryreply.propId.value = Number(inquiryreply.propId.value);
    		
				
		document.getElementById("inquiryreply").submit();
	}
    </script>
    
    
    <div class="theme-layout">
		<div class="inner-head overlap">
            <div style="background: url(${pageContext.request.contextPath}/img/parallax1.jpg) repeat scroll 50% 422.28px transparent;" class="parallax scrolly-invisible no-parallax"></div><!-- PARALLAX BACKGROUND IMAGE -->	
            <div class="container">
                <div class="inner-content">
                    <span><i class="ti ti-home"></i></span>
                    <h2>문의에 답하기</h2>
                    <ul>
                        <li><a href="${pageContext.request.contextPath}/main" title="">HOME</a></li>
                        <li><a href="${pageContext.request.contextPath}/inquiry/reply" title="">문의에 답하기</a></li>
                    </ul>
                </div>
            </div>
        </div><!-- inner Head -->
        

         <section class="block ">
            <div class="container">
                <div class="row">
                    <div class="col-md-12">
                    		<div class="heading4" >
	                            <h2>문의에 답변하기</h2> 
	                        </div>
                    	<div class="container"  style="width:800px;">
	                        <div class="container col-md-8" style="width:800px;">
	                            <form id="inquiryreply" name="inquiryreply" method="post" class="property-form" role="form" 
	                            		action="${pageContext.request.contextPath}/inquiry/replyPro">
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
                                <c:set var="inquirydo" value="${inquirydo}"/>
                                <input type="hidden" id="pId" name="pId" value="${inquirydo.pId}">
                                <input type="hidden" id="thrId" name="thrId" value="${inquirydo.thrId}">
                                <input type="hidden" id="refId" name="refId" value="${inquirydo.thrId}">
                                <input type="hidden" id="propId" name="propId" value="${inquirydo.propId}">
                                <input type="hidden" id="email" name="email" value="${sbmemberdo.email}">
                                <input type="hidden" id="tel" name="tel" value="${sbmemberdo.tel}">
                                <input type="hidden" id="nickname" name="nickname" value="${sbmemberdo.nickname}">
								<input type="hidden" id="addedBy" name="addedBy" value="${sbmemberdo.userId}">
								<div class="submit row" style="text-align:center; clear: both; margin-top: 25px;">
                                    <div style="display:inline-block; width:300px;">
                                        <input type="button" class="btn btn-lg flat-btn" id="property_submit" value="답변하기" style="margin:5px"
                                        	onclick="putZero()">
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

            

        <%@include file="../common/footer.jsp" %>
    