<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

	<%@include file="../main/head.jsp" %>
    <%@include file="../main/top.jsp" %>
    <%@ taglib prefix="sb" uri="/WEB-INF/spring/tld/starbocks.tld" %>
    
    <script type='text/javascript' src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.5.0/js/bootstrap-datepicker.min.js"></script><!--daypicker-->
    <script type='text/javascript'>
	    $(function() {
	        $('.col-md-4.date').datepicker({});
	    });
	    
	    function selectProperties(argumentProps){
	    		argumentProps = argumentProps !== "" ? String(argumentProps) : String("0");
	    		askdisdetail.props.value = String(askdisdetail.props.value);				//bug is captured
		 		var url = "${pageContext.request.contextPath}/askdisclosure/select/"+askdisdetail.props.value.trim()+"/"+argumentProps.trim()+"/"+askdisdetail.pageone.value;
				open( url, "Select Properties","toolbar=no, menubar=no, scrollbar=no,status=no, width=700, height=670");
	    }
	</script>
    
    <div class="theme-layout">
		<div class="inner-head overlap">
            <div style="background: url(${pageContext.request.contextPath}/img/parallax1.jpg) repeat scroll 50% 422.28px transparent;" class="parallax scrolly-invisible no-parallax"></div><!-- PARALLAX BACKGROUND IMAGE -->	
            <div class="container">
                <div class="inner-content">
                    <span><i class="ti ti-home"></i></span>
                    <h2>공개요청 상세보기</h2>
                    <ul>
                        <li><a href="${pageContext.request.contextPath}/main" title="">HOME</a></li>
                        <li><a href="${pageContext.request.contextPath}/askdisclosure/detail" title="">공개요청 상세보기</a></li>
                    </ul>
                </div>
            </div>
        </div><!-- inner Head -->
        

         <section class="block ">
            <div class="container">
                <div class="row">
                    <div class="col-md-12">
                    		<div class="heading4" >
	                            <h2>공개요청 상세보기</h2> 
	                        </div>
                    	<div class="container"  style="width:800px;">
	                        <div class="container col-md-8" style="width:800px;">
	                            <form id="askdisdetail" name="askdisdetail" method="post" class="property-form" role="form" 
	                            		action="${pageContext.request.contextPath}/askdisclosure/reply/${sb:getIdStr(askdo.askId)}/${sb:getIdStr(askdo.thrAskId)}">
                                <div class="control-group">
                                    <div class="group-title">Ask Disclosure Description</div>
                                    <div class="group-container row">
                                        <div class="col-md-12">
                                            <div class="form-group s-prop-title">
                                                <label for="title">공개요청인&nbsp;&#42;</label>
                                                <input type="text" id="name" class="form-control" value="${askdo.name}" name="name" required="" readonly>
                                            </div>
                                        </div>
                                            
                                			<div class="col-md-10">
	                                            <div class="form-group s-prop-title">
	                                                <label for="title">매물 보기&nbsp;&#42;</label>
	                                                <input type="text" class="form-control" id="addresses" name="addresses" readonly >
	                                            </div>
                                            </div>
                                            <div class="col-md-2" style="margin-top: 2em;">
                                            	<input type="button" class="btn btn-primary btn-sm" value="매물 보기" 
                                            		onclick="selectProperties(askdisdetail.userId.value)">
                                            </div>
                                        <input type="hidden" id="pageone" name="pageone" value="${sb:getIdStr(1)}">
										<input type="hidden" id="props" name="props" value="${askdo.propId}">
										<input type="hidden" id="propId" name="propId">
										<input type="hidden" id="userId" name="userId" value="${sb:getIdStr(sbmemberdo.userId)}">
                                        <input type="hidden" id="addedBy" name="addedBy" value="${sbmemberdo.userId}">
		                                <div class="submit row" style="text-align:center; clear: both; margin-top: 25px;">
		                                    <div style="display:inline-block; width:250px;">
		                                        <!-- <input type="submit" class="btn btn-sm" id="property_submit" value="답변하기" style="margin:5px">
		                                        <input type="reset" class="btn btn-sm" id="property_cancel" value="취소" style="margin:5px"> -->
												<button type="button" class="btn btn-sm flat-btn" onclick="location.href='${pageContext.request.contextPath}/askdisclosure/list/${sb:getIdStr(sbmemberdo.userId)}/${sb:getIdStr(1)}'">리스트로 돌아가기</button>		                                    
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
    