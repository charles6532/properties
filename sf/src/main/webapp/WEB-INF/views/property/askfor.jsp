<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

	<%@include file="../main/head.jsp" %>
    <%@include file="../main/top.jsp" %>
    <script type='text/javascript' src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.5.0/js/bootstrap-datepicker.min.js"></script><!--daypicker-->
    <%@ taglib prefix="sb" uri="/WEB-INF/spring/tld/starbocks.tld" %>
    <script type='text/javascript'>
	    $(function() {
	    	
	    	/* document.getElementById("roomCount").defaultValue = 0;
			document.getElementById("price").defaultValue = 0;
			document.getElementById("avgParkingSpace").defaultValue = 0;
			document.getElementById("housesCount").defaultValue = 0; */
			
	        $('.col-md-4.date').datepicker({});
	        $("select").on("change",function(event){
	        	let value = event.target.value;
	        	var url = '${pageContext.request.contextPath}/property/askfor/';
	        	if($(this).attr("id") =='state'){
	        		url += $(this).attr("id")+'/' + $("select[name=state] option:selected").val();
	        		$("select[name=city]").append("<option>Loading data ....</option>");
	        		$.ajax(
		                    {
		                        type: "POST",
		                        url: url,
		                        data: { city : value },
		                        dataType: "json",
		                        success: function(data){
		                        	$("select[name=city] option").remove();
		                            let gun = "";
		                            gun = "<option>시/군/구</option>";
		                            $("select[name=city]").append(gun);
		                            for(let i=0; i<data.length; i++){
		                                gun = "<option>";
		                                gun += data[i].city;
		                                gun += "</option>";
			                            $("select[name=city]").append(gun);
		                            }
		                        },
		                        error: function(){
		                            alert("bad");
		                        }
		                    }
		            );
	        	}

	        	if($(this).attr("id") =='city'){
	        		url += $(this).attr("id")+'/' + $("select[name=city] option:selected").val();
	        		$("select[name=legalDistrictNm]").append("<option>Loading data ....</option>");
	        		$.ajax(
		                    {
		                        type: "POST",
		                        url: url,
		                        data: { legalDistrictNm : value },
		                        dataType: "json",
		                        success: function(data){
		                        	$("select[name=legalDistrictNm] option").remove();
		                            let gun = "";
		                            gun = "<option>단지</option>";
		                            $("select[name=legalDistrictNm]").append(gun);
		                            for(let i=0; i<data.length; i++){
		                                gun = "<option>";
		                                gun += data[i].legalDistrictNm;
		                                gun += "</option>";
			                            $("select[name=legalDistrictNm]").append(gun);
		                            }
		                        },
		                        error: function(){
		                            alert("bad");
		                        }
		                    }
		            );
	        	}
	        	
	        	
	        });
	        
	    });
				
		function putZero(  ){
			
			askforproperty.roomCount.value = Number(document.getElementById("roomCount").value);
			askforproperty.price.value = Number(document.getElementById("price").value);
			askforproperty.avgParkingSpace.value = Number(document.getElementById("avgParkingSpace").value);
			askforproperty.housesCount.value = Number(document.getElementById("housesCount").value);
			askforproperty.space.value = Number(document.getElementById("space").value);
			
			document.getElementById("askforproperty").submit();
		}  
	</script>
    
    <div class="theme-layout">
		<div class="inner-head overlap">
            <div style="background: url(${pageContext.request.contextPath}/img/parallax1.jpg) repeat scroll 50% 422.28px transparent;" class="parallax scrolly-invisible no-parallax"></div><!-- PARALLAX BACKGROUND IMAGE -->	
            <div class="container">
                <div class="inner-content">
                    <span><i class="ti ti-home"></i></span>
                    <h2>매물요청</h2>
                    <ul>
                        <li><a href="${pageContext.request.contextPath}/main" title="">HOME</a></li>
                        <li><a href="${pageContext.request.contextPath}/property/askfor" title="">매물요청</a></li>
                    </ul>
                </div>
            </div>
        </div><!-- inner Head -->
        

         <section class="block">
            <div class="container">
                <div class="row">
                    <div class="col-md-12">
                    	<div class="heading4" >
	                    	<h2>매물 요청</h2> 
	                    </div>
                    	<div class="container"  style="width:800px;">
	                        <div class="container col-md-8" style="width:800px;">
		                            <form id="askforproperty" name="askforproperty" method="post" action="${pageContext.request.contextPath}/property/askforPro">
			                                <div class="control-group">
			                                    <div class="group-title">Property Description &amp; Price</div>
			                                    <div class="group-container row">
			                                        <div class="col-md-12">
			                                            <div class="form-group s-prop-title">
			                                                <label for="title">매물명&nbsp;&#42;</label>
			                                                <input type="text" id="conTitle" class="form-control" value="" name="conTitle" required="">
			                                            </div>
			                                        </div>
			                                        <c:set var="sbzip" value="${sbzip}"/>
			                                        <div class="col-md-4">
			                                            <div class="form-group s-prop-location">
			                                                <label>소재지</label>
			                                                <div class="dropdown label-select">
			                                                    <select id="state" name="state" class="form-control">
			                                                        <option value="">지역선택</option>
			                                                        <option value="서울">서울</option>
			                                                        <option value="경기">경기</option>
			                                                    </select>
			                                                </div>
			                                            </div>
			                                        </div>
			                                        <div class="col-md-4">
			                                            <div class="form-group s-prop-sub_location">
			                                                <label>&nbsp;</label>
			                                                <div class="dropdown label-select">			                                                    
			                                                    <select name="city" id="city" class="form-control">
																	<c:forEach var="sbzip" items="${sbzip}" varStatus="i">
																	<option value="${sbzip.city}">
																	${sbzip.city}
																	</option> 
																	</c:forEach>
																</select>
			                                                </div>
			                                            </div>
			                                        </div>
			                                        <div class="col-md-4">
			                                            <div class="form-group s-prop-sub_location">
			                                                <label>&nbsp;</label>
			                                                <div class="dropdown label-select">
			                                                    <select name="legalDistrictNm" id="legalDistrictNm" class="form-control">
																	<c:forEach var="sbzip" items="${sbzip}" varStatus="i">
																	<option value="${sbzip.legalDistrictNm}">
																	${sbzip.legalDistrictNm}
																	</option> 
																	</c:forEach>
																</select>
			                                                </div>
			                                            </div>
			                                        </div>
															
			                                        
			                                        <div class="col-md-4">
			                                            <div class="form-group s-prop-sub_location">
			                                                <label>층수&nbsp;</label>
			                                                <div class="dropdown label-select">
			                                                    <select name="floorDesc" class="form-control">
			                                                        <option value="">고/중/저층</option>
			                                                        <option value="high">고층</option>
			                                                        <option value="middle">중층</option>
			                                                        <option value="low">저층</option>
			                                                    </select>
			                                                </div>
			                                            </div>
			                                        </div>
			
			                                        <!--daypicker-->
			                                        <div class="col-md-12">
													    <div class="form-group">
													        <label>입주가능일&nbsp;&#42;</label>
													        <div class="col-md-4 input-group date">
													            <input type="text" class="form-control" placeholder="Select Date" id="moveAt" name="moveAt">
													            <span class="input-group-addon">날짜선택</span>
													        </div>
													    </div>
													</div>
			                                        <!--daypicker end-->
			                                        <div class="col-md-4">
			                                            <label for="price">매매가격&nbsp;&#42;</label>
			                                            <input type="text" id="price" class="form-control" value="" name="price" required="">
			                                        </div>
			                                        <div class="col-md-1" style="margin-left:-10px;padding-top: 15px;padding-left: 0px;margin-right: 0px;">
			                                            <label></label><br>
			                                            <label>만원</label>
			                                        </div>
													<div class="col-md-3">
			                                           <div class="form-group s-prop-land-size">
			                                               <label for="title">면적&nbsp;&#42;</label>
			                                               <input type="text" id="space" class="form-control" value="" name="space" required="">
			                                               
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
			                                        		<select name="maintenance" class="form-control">
			                                                        <option value="">올수리여부</option>
			                                                        <option value="y">올수리하였음</option>
			                                                        <option value="n">올수리하지않았음</option>
			                                                        
			                                                </select>
			                                            </div>
			                                        </div>
			                                        <div class="col-md-4">
			                                            <div class="form-group s-prop-property_field_year_built">
			                                                <label for="property_field_year_built">준공년도</label>
			                                                <input type="text" id="builtAt" name="builtAt" class="form-control" value="" placeholder="예)1995(네 자리 숫자)">
			                                            </div>
			                                        </div>
			                                        <div class="col-md-4">
			                                            <div class="form-group s-prop-property_field_flooring">
			                                                <label for="property_field_flooring">방 갯수</label>
			                                                <input type="text" id="roomCount" name="roomCount" class="form-control" value="">
			                                            </div>
			                                        </div>
			                                        
			                                        <div class="col-md-4">
			                                            <div class="form-group s-prop-property_field_parking">
			                                                <label for="property_field_parking">세대 당 주차대수</label>
			                                                <input type="text" id="avgParkingSpace" name="avgParkingSpace" class="form-control" value="">
			                                            </div>
			                                        </div>
			                                        <div class="col-md-4">
			                                            <div class="form-group s-prop-property_field_style">
			                                                <label for="property_field_style">세대수</label>
			                                                <input type="text" id="housesCount" name="housesCount" class="form-control" value="">
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
				                                                <textarea name="remark" cols="70" rows="10"></textarea>
				                                            </div>
				                                        </div>
													</div>
												</div>
											</div>
			                                <input type="hidden" id="userId" name="userId" value="${sbmemberdo.userId}">
			                                <input type="hidden" id="pcpId" name="pcpId" value="0">
			                                <div class="submit row" style="text-align:center; clear: both; margin-top: 25px;">
			                                    <div style="display:inline-block; width:400px;">
			                                        <input type="button" class="btn btn-lg flat-btn" id="property_submit" value="매물 요청" style="margin:5px" 
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

            

        <%@include file="../main/bottom.jsp" %>
    