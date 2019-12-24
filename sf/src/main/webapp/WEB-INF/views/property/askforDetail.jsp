<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

	<%@include file="../main/head.jsp" %>
    <%@include file="../main/top.jsp" %>
    <%@ taglib prefix="sb" uri="/WEB-INF/spring/tld/starbocks.tld" %>
    
    <script type='text/javascript' src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.5.0/js/bootstrap-datepicker.min.js"></script><!--daypicker-->
    <script type="text/javascript">
	    function selectProperties(argumentProps){
	    	argumentProps = argumentProps !== "" ? String(argumentProps) : String("0");	//bug is captured
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
		    	askfordetail.propId.value = argumentProps;
		    	
	    	}
	    	askfordetail.props.value = String(askfordetail.props.value);				//bug is captured
   	 		var url = "${pageContext.request.contextPath}/property/select/"+askfordetail.props.value.trim()+"/"+argumentProps.trim()+"/"+askfordetail.pageone.value;
   			open( url, "Select Properties","toolbar=no, menubar=no, scrollbar=no,status=no, width=700, height=670");
	    }
	    
		
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
                        <li><a href="${pageContext.request.contextPath}/inquiry/askforDetail" title="">문의 상세보기</a></li>
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
	                            <form id="askfordetail" name="askfordetail" method="post" action="${pageContext.request.contextPath}/askdisclosure/write/${sb:getIdStr(conproperty.userId)}/${sb:getIdStr(sbmemberdo.userId)}">
								<c:if test="${conproperty.pCpId == 0}">
                                <div class="control-group">
			                                    <div class="group-title">Property Description &amp; Price</div>
			                                    <div class="group-container row">
			                                        <div class="col-md-12">
			                                            <div class="form-group s-prop-title">
			                                                <label for="title">매물명&nbsp;&#42;</label>
			                                                <input type="text" id="conTitle" class="form-control" value="${conproperty.conTitle}" name="conTitle" required="" readonly>
			                                            </div>
			                                        </div>
			                                        <c:set var="sbzip" value="${sbzip}"/>
			                                        <div class="col-md-4">
			                                            <div class="form-group s-prop-location">
			                                                <label>소재지</label>
			                                                <div class="dropdown label-select">
			                                                    <input type="text" id="state" name="state" class="form-control" value="${conproperty.state}" readonly>
			                                                </div>
			                                            </div>
			                                        </div>
			                                        <div class="col-md-4">
			                                            <div class="form-group s-prop-sub_location">
			                                                <label>&nbsp;</label>
			                                                <div class="dropdown label-select">			                                                    
			                                                    <input type="text" name="city" id="city" class="form-control" value="${conproperty.city}" readonly>
			                                                </div>
			                                            </div>
			                                        </div>
			                                        
			                                        <div class="col-md-4">
			                                            <div class="form-group s-prop-sub_location">
			                                                <label>층수&nbsp;</label>
			                                                <div class="dropdown label-select">
			                                                    <input type="text" name="floor" class="form-control" value="${conproperty.floor}" readonly>
			                                                </div>
			                                            </div>
			                                        </div>
			
			                                        <!--daypicker-->
			                                        <div class="col-md-12">
													    <div class="form-group">
													        <label>입주가능일&nbsp;&#42;</label>
													        <div class="col-md-4 input-group date">
													            <input type="text" class="form-control" id="moveAt" name="moveAt"  value="${conproperty.moveAt}" readonly>
													        </div>
													    </div>
													</div>
			                                        <!--daypicker end-->
			                                        <div class="col-md-4">
			                                            <label for="price">매매가격&nbsp;&#42;</label>
			                                            <input type="text" id="price" class="form-control" name="price" value="${conproperty.price}" readonly>
			                                        </div>
			                                        <div class="col-md-1" style="margin-left:-10px;padding-top: 15px;padding-left: 0px;margin-right: 0px;">
			                                            <label></label><br>
			                                            <label>만원</label>
			                                        </div>
													<div class="col-md-3">
			                                           <div class="form-group s-prop-land-size">
			                                               <label for="title">면적&nbsp;&#42;</label>
			                                               <input type="text" id="space" class="form-control" name="space" value="${conproperty.space}" readonly>
			                                               
			                                           </div>
			                                       </div>
			                                        <div class="col-md-1" style="margin-left:-25px;">
			                                            <div class="form-group s-prop-price">
			                                                <br><br>
			                                             	<label>m²</label>
			                                            </div>
			                                        </div>
			                                        </div>
			                                    </div>
			                                </div>
			                                
			
			                                <div class="control-group">
			                                    <div class="group-title">Additional Info</div>
			                                    <div class="group-container row">
			                                        <div class="col-md-4">
			                                        	<div class="form-group property_field_lot_area">
			                                        		<label for="property_field_lot_area">올수리</label>
			                                        		<input type="text" name="maintenance" class="form-control" value="${conproperty.maintenance}" readonly>
			                                            </div>
			                                        </div>
			                                        <div class="col-md-4">
			                                            <div class="form-group s-prop-property_field_year_built">
			                                                <label for="property_field_year_built">준공년도</label>
			                                                <input type="text" id="builtAt" name="builtAt" class="form-control" value="${conproperty.builtAt}" readonly>
			                                            </div>
			                                        </div>
			                                        <div class="col-md-4">
			                                            <div class="form-group s-prop-property_field_flooring">
			                                                <label for="property_field_flooring">방 갯수</label>
			                                                <input type="text" id="roomCount" name="roomCount" class="form-control" value="${conproperty.roomCount}" readonly>
			                                            </div>
			                                        </div>
			                                        
			                                        <div class="col-md-4">
			                                            <div class="form-group s-prop-property_field_parking">
			                                                <label for="property_field_parking">세대 당 주차대수</label>
			                                                <input type="text" id="avgParkingSpace" name="avgParkingSpace" class="form-control" value="${conproperty.avgParkingSpace}" readonly>
			                                            </div>
			                                        </div>
			                                        <div class="col-md-4">
			                                            <div class="form-group s-prop-property_field_style">
			                                                <label for="property_field_style">세대수</label>
			                                                <input type="text" id="housesCount" name="housesCount" class="form-control" value="${conproperty.housesCount}" readonly>
			                                            </div>
			                                        </div>
			                                    </div>
			                                </div>
											
											
											<div class="control-group">
			                                    <div class="group-title">Remark</div>
				                                    <div class="group-container row">
				                                        <div class="col-md-4">
				                                            <div class="form-group property_field_lot_area">
				                                                <label for="property_field_lot_area">기타 요청사항</label>
				                                                <textarea name="remark" cols="70" rows="10" readonly>${conproperty.remark}</textarea>
				                                            </div>
				                                        </div>
													</div>
												</div>
											</div>
									</c:if>
											<c:if test="${conproperty.pCpId != 0}">
											<div class="control-group">
			                                    <div class="group-title">Property Description &amp; Price</div>
			                                    <div class="group-container row">
			                                        <div class="col-md-12">
			                                            <div class="form-group s-prop-title">
			                                                <label for="title">매물명&nbsp;&#42;</label>
			                                                <input type="text" id="conTitle" class="form-control" value="${conproperty.conTitle}" name="conTitle" required="" readonly>
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
                                            		onclick="selectProperties(askfordetail.propId.value)">
                                            </div>
											<div class="control-group">
			                                    <div class="group-title">Remark</div>
				                                    <div class="group-container row">
				                                        <div class="col-md-4">
				                                            <div class="form-group property_field_lot_area">
				                                                <label for="property_field_lot_area">기타 요청사항</label>
				                                                <textarea name="remark" cols="70" rows="10" readonly>${conproperty.remark}</textarea>
				                                            </div>
				                                        </div>
													</div>
												</div>
											</div>
												
												<input type="hidden" id="pageone" name="pageone" value="${sb:getIdStr(1)}">
												<input type="hidden" id="props" name="props" value="${conproperty.propsId}">
												<input type="hidden" id="propId" name="propId" value="0">
												<div class="submit row"
													style="text-align: center; clear: both; margin-top: 25px;">
													<div style="display: inline-block; width:550px; padding-left: 2em;">
														<input type="submit" class="btn btn-lg flat-btn" id="property_submit" value="공개 요청하기" style="margin: 5px">
														<button type="button" class="btn btn-lg flat-btn" id="property_cancel" style="margin: 5px"
															onclick="location.href='${pageContext.request.contextPath}/property/mine/${sb:getIdStr(sbmemberdo.userId)}/${sb:getIdStr(1)}'">리스트로
															돌아가기</button>
													</div>
												</div>
											</c:if>
											
											
		                	</div>
		            	</div>
		            	</form>
                    </div>
                </div>
            </div>
        </section>
        

            

        <%@include file="../main/bottom.jsp" %>
    