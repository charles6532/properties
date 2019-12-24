<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@include file="../common/head.jsp"%>
<%@include file="../common/top.jsp"%>
<%@ taglib prefix="sb" uri="/WEB-INF/spring/tld/starbocks.tld"%>

<script type='text/javascript'
	src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.5.0/js/bootstrap-datepicker.min.js"></script>
<!--daypicker-->
<script type="text/javascript">
function putZero(  ){
	
	askfordetail.userId.value = Number(askfordetail.userId.value);
	/* askfordetail.zipcode.value = Number(askfordetail.zipcode.value); */
	askfordetail.roomCount.value = Number(askfordetail.roomCount.value);
	askfordetail.price.value = Number(askfordetail.price.value);
	askfordetail.avgParkingSpace.value = Number(askfordetail.avgParkingSpace.value);
	askfordetail.housesCount.value = Number(askfordetail.housesCount.value);
	
			
	document.getElementById("askfordetail").submit();
}
</script>

<div class="theme-layout">
	<div class="inner-head overlap">
		<div
			style="background: url(${pageContext.request.contextPath}/img/parallax1.jpg) repeat scroll 50% 422.28px transparent;"
			class="parallax scrolly-invisible no-parallax"></div>
		<!-- PARALLAX BACKGROUND IMAGE -->
		<div class="container">
			<div class="inner-content">
				<span><i class="ti ti-home"></i></span>
				<h2>회원 매물요청 상세보기</h2>
				<ul>
					<li><a href="${pageContext.request.contextPath}/main" title="">HOME</a></li>
					<li><a
						href="${pageContext.request.contextPath}/property/askforDetail/${sb:getIdStr(printlist.cpId)}"
						title="">회원 매물요청 상세보기</a></li>
				</ul>
			</div>
		</div>
	</div>
	<!-- inner Head -->


	<section class="block ">
		<div class="container">
			<div class="row">
				<div class="col-md-12">
					<div class="heading4">
						<h2>회원 매물요청 상세보기</h2>
					</div>
					<div class="container" style="width: 800px;">
						<div class="container col-md-8" style="width: 800px;">
							<form id="askfordetail" name="askfordetail" method="post"
								class="property-form" role="form"
								action="${pageContext.request.contextPath}/prop/reply">
								<div class="control-group">
									<div class="group-title">Property Description &amp; Price</div>
									<div class="group-container row">
										<div class="col-md-12">
											<div class="form-group s-prop-title">
												<label for="title">매물명&nbsp;&#42;</label> <input type="text"
													id="conTitle" class="form-control"
													value="${conproperty.conTitle}" name="conTitle" required=""
													readonly>
											</div>
										</div>
										<c:set var="sbzip" value="${sbzip}" />
										<div class="col-md-4">
											<div class="form-group s-prop-location">
												<label>소재지</label>
												<div class="dropdown label-select">
													<input type="text" id="state" name="state"
														class="form-control" value="${conproperty.state}" readonly>
												</div>
											</div>
										</div>
										<div class="col-md-4">
											<div class="form-group s-prop-sub_location">
												<label>&nbsp;</label>
												<div class="dropdown label-select">
													<input type="text" name="city" id="city"
														class="form-control" value="${conproperty.city}" readonly>
												</div>
											</div>
										</div>

										<div class="col-md-4">
											<div class="form-group s-prop-sub_location">
												<label>층수&nbsp;</label>
												<div class="dropdown label-select">
													<input type="text" name="floor" class="form-control"
														value="${conproperty.floor}" readonly>
												</div>
											</div>
										</div>

										<!--daypicker-->
										<div class="col-md-12">
											<div class="form-group">
												<label>입주가능일&nbsp;&#42;</label>
												<div class="col-md-4 input-group date">
													<c:set var="string1" value="${conproperty.moveAt}" />
													<input type="text" class="form-control" id="moveAt"
														name="moveAt" value="${fn:substringBefore(string1,' ')}"
														readonly>
												</div>
											</div>
										</div>
										<!--daypicker end-->
										<div class="col-md-4">
											<label for="price">매매가격&nbsp;&#42;</label> <input type="text"
												id="price" class="form-control" name="price"
												value="${conproperty.price}" readonly>
										</div>
										<div class="col-md-1"
											style="margin-left: -10px; padding-top: 15px; padding-left: 0px; margin-right: 0px;">
											<label></label><br> <label>만원</label>
										</div>
										<div class="col-md-3">
											<div class="form-group s-prop-land-size">
												<label for="title">면적&nbsp;&#42;</label> <input type="text"
													id="space" class="form-control" name="space"
													value="${conproperty.space}" readonly>

											</div>
										</div>
										<div class="col-md-1" style="margin-left: -25px;">
											<div class="form-group s-prop-price">
												<br>
												<br> <label>m²</label>
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
										<label for="property_field_lot_area">올수리</label> <input
											type="text" name="maintenance" class="form-control"
											value="${conproperty.maintenance}" readonly>
									</div>
								</div>
								<div class="col-md-4">
									<div class="form-group s-prop-property_field_year_built">
										<label for="property_field_year_built">준공년도</label> <input
											type="text" id="builtAt" name="builtAt" class="form-control"
											value="${conproperty.builtAt}" readonly>
									</div>
								</div>
								<div class="col-md-4">
									<div class="form-group s-prop-property_field_flooring">
										<label for="property_field_flooring">방 갯수</label> <input
											type="text" id="roomCount" name="roomCount"
											class="form-control" value="${conproperty.roomCount}"
											readonly>
									</div>
								</div>

								<div class="col-md-4">
									<div class="form-group s-prop-property_field_parking">
										<label for="property_field_parking">세대 당 주차대수</label> <input
											type="text" id="avgParkingSpace" name="avgParkingSpace"
											class="form-control" value="${conproperty.avgParkingSpace}"
											readonly>
									</div>
								</div>
								<div class="col-md-4">
									<div class="form-group s-prop-property_field_style">
										<label for="property_field_style">세대수</label> <input
											type="text" id="housesCount" name="housesCount"
											class="form-control" value="${conproperty.housesCount}"
											readonly>
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
					
					<input type="hidden" id="cpId" name="cpId"
						value="${conproperty.cpId}">
					<input type="hidden" id="userId" name="userId"
						value="${sbAgentDo.userId}">
					<div class="submit row"
						style="text-align: center; clear: both; margin-top: 25px;">
						<div style="display: inline-block; width: 400px;">
							<input type="button" class="btn btn-sm" 
								value="답변하기" style="margin: 5px" onclick="putZero()">
							<button type="button" class="btn btn-sm"
								onclick="location.href='${pageContext.request.contextPath}/prop/mine/${sb:getIdStr(sbAgentDo.zipcodeId)}/${sb:getIdStr(1)}'">리스트로
								돌아가기</button>
						</div>
					</div>
				</div>
				</form>
			</div>
		</div>
</div>
</div>
</section>



<%@include file="../common/footer.jsp"%>
