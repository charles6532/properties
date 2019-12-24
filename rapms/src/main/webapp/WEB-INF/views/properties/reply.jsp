<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

	<%@include file="../common/head.jsp" %>
    <%@include file="../common/top.jsp" %>
    
    <script type="text/javascript">
	    function selectProperties(argumentProps){
	    	argumentProps = argumentProps !== "" ? String(argumentProps) : String("0");
	    	var tmparray = argumentProps.split(" ");
	    	if( argumentProps!=="0"){
		    	for (i = 0;i<tmparray.length;i++) {
		    		for( j=i+1;j<tmparray.length;j++){
		    			if(tmparray[i]===tmparray[j]){
		    				tmparray[i] = "";
		    			}
		    		}
		    	}
		    	argumentProps = String("");
		    	for (i = 0;i<tmparray.length;i++) {
		    		if(tmparray[i]!=="") argumentProps += tmparray[i]+" ";
		    	}
		    	inquiryreply.propsId.value = argumentProps;
		    	
	    	}
	    	inquiryreply.pCpIdstr.value = String(inquiryreply.pCpIdstr.value);
   	 		var url = "${pageContext.request.contextPath}/prop/select/"+inquiryreply.pCpIdstr.value+"/"+argumentProps.trim()+"/"+inquiryreply.pageone.value;
   			open( url, "Select Properties","toolbar=no, menubar=no, scrollbar=no,status=no, width=700, height=670");
	    	
	    	
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
                        <li><a href="${pageContext.request.contextPath}/prop/reply" title="">문의에 답변하기</a></li>
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
	                            		action="${pageContext.request.contextPath}/prop/replyPro/${sb:getIdStr(sbAgentDo.userId)}">
                                <div class="control-group">
                                    <div class="group-title">문의에 답변하기</div>
                                    <div class="group-container row">
                                        <div class="col-md-12">
                                        	<div class="col-md-10">
	                                            <div class="form-group s-prop-title">
	                                                <label for="title">문의 제목&nbsp;&#42;</label>
	                                                <input type="text" id="conTitle" class="form-control" value="" name="conTitle" required="">
	                                            </div>
                                            </div>
                                            <div class="col-md-10">
	                                            <div class="form-group s-prop-title">
	                                                <label for="title">매물 선택&nbsp;&#42;</label>
	                                                <input type="text" class="form-control" id="addresses" name="addresses" readonly >
	                                            </div>
                                            </div>
                                            <div class="col-md-2" style="margin-top: 2em;">
                                            	<input type="button" class="btn btn-primary btn-sm" value="매물 선택" 
                                            		onclick="selectProperties(inquiryreply.propsId.value)">
                                            </div>
                                            
                                            <div class="col-md-8">
	                                			<div class="form-group s-com-title">
	                                                <label for="title">문의 내용&nbsp;&#42;</label><br>
	                                                <textarea cols="80" rows="8" name="remark"></textarea>
	                                            </div>        
                                            </div>
                                        </div>
                                <c:set var="conDo" value="${conDo}"/>
                                <input type="hidden" id="propsId" name="propsId" value="0">
								<input type="hidden" id="pageone" name="pageone" value="${sb:getIdStr(1)}">
								<input type="hidden" id="pCpIdstr" name="pCpIdstr" value="${sb:getIdStr(conDo.cpId)}">
								<input type="hidden" id="pCpId" name="pCpId" value="${conDo.cpId}">                                                                
								<input type="hidden" id="zipcode" name="zipcode" value="${sbAgentDo.zipcodeId}">
								
								<div class="submit row" style="text-align:center; clear: both; margin-top: 25px;">
                                    <div style="display:inline-block; width:300px;">
                                        <input type="submit" class="btn btn-lg flat-btn" id="property_submit" value="답변하기" style="margin:5px">
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
    