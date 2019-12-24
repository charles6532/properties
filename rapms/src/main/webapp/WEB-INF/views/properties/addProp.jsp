<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- top -->
<%@ include file="../common/top.jsp" %>
<%@ include file="../common/signupbutton.jsp" %>
<%@ include file="./addproperty.jsp" %>

<title>Add Property</title>

	<div class="theme-layout">
		<div class="inner-head overlap">
			<div style="background: url(${pageContext.request.contextPath}/img/parallax1.jpg) repeat scroll 50% 422.28px transparent;" class="parallax scrolly-invisible no-parallax"></div>
			<!-- PARALLAX BACKGROUND IMAGE -->
			<div class="container">
				<div class="inner-content">
					<span><i class="ti ti-home"></i></span>
					<h2>매물 등록</h2>
					<ul>
						<li><a href="${pageContext.request.contextPath}/prop" title="">PROPERTY</a></li>
						<li><a href="${pageContext.request.contextPath}/prop/addForm" title="">매물 등록</a></li>
					</ul>
				</div>
			</div>
		</div>

		<section class="block">
			<div class="container agnet-prop">
				<div class="row">
					<div class="col-md-12 column">
						<div class="heading4">
							<h2>매물 등록</h2>
						</div>

						<div class="submit-content">
						
							<form id="propertyform" name="propertyform" class="propertyform" enctype="multipart/form-data" method="post" role="form" action="${pageContext.request.contextPath}/prop/addPro">
								
								<div class="control-group">
									<div class="group-title">Property features &amp; Price</div>
									<div class="group-container row">
										<div class="col-md-12">
											<div class="form-group s-prop-title">
												<label for="title">매물명&nbsp;&#42;</label>
												<input type="text" id="title" class="form-control" value="" name="title">
											</div>
										</div>
									
									<c:set var="sbzip" value="${sbzip}" />
									<div class="col-md-3">
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
									<div class="col-md-3">
										<div class="form-group s-prop-sub_location">
											<label>&nbsp;</label>
											<div class="dropdown label-select">
												<select name="city" id="city" class="form-control">
													<c:forEach var="sbzip" items="${sbzip}" varStatus="i">
														<option value="${sbzip.city}">${sbzip.city}</option>
													</c:forEach>
												</select>
											</div>
										</div>
									</div>
									<div class="col-md-3">
										<div class="form-group s-prop-sub_location">
											<label>&nbsp;</label>
											<div class="dropdown label-select">
												<select name="legalDistrictNm" id="legalDistrictNm"
													class="form-control">
													<c:forEach var="sbzip" items="${sbzip}" varStatus="i">
														<option value="${sbzip.legalDistrictNm}">
															${sbzip.legalDistrictNm}</option>
													</c:forEach>
												</select>
											</div>
										</div>
									</div>
									<div class="col-md-3">
										<div class="form-group s-prop-sub_location">
											<label>&nbsp;</label>
											<div class="dropdown label-select">
												<select class="form-control" id="bldgNm" name="bldgNm">
													<c:forEach var="sbzip" items="${sbzip}" varStatus="i">
														<option value="${sbzip.bldgNm}">
															${sbzip.bldgNm}</option>
													</c:forEach>

												</select>
											</div>
										</div>
									</div>

									<div class="col-md-4">
											<div class="form-group s-prop-title">
												<label for="dong">아파트 동&nbsp;&#42;</label>
												<input type="number" min="0" id="dong" class="form-control" name="dong"  value="">
											</div>
										</div>
										<div class="col-md-4">
											<div class="form-group s-prop-title">
												<label for="hoNo">아파트 호수&nbsp;&#42;</label>
												<input type="number" min="0" id="hoNo" class="form-control" name="hoNo"  value="">
											</div>
										</div>
										<div class="col-md-4">
											<div class="form-group s-prop-sub_location">
												<label>층수&nbsp;</label>
												<input type="number" min="0" id="floor" class="form-control" name="floor"  value="">
											</div>
										</div>
										<div class="col-md-12">
											<div class="form-group s-prop-price row">
												<div class="col-md-4">
													<label for="areaSpace">면적</label>
													<input type="number" step="0.001" id="areaSpace" name="areaSpace" class="form-control" placeholder="예) 84.123" >
												</div>
												<div class="col-md-1" style="margin-left: 0px; padding-top: 15px; padding-left: 0px;">
													<label></label><br> <label>㎡</label>
												</div>
											</div>
 										</div>
										<div class="col-md-12">
											<div class="form-group s-prop-price row">
												<div class="col-md-4">
													<label for="price">매매가격</label>
													<input type="number" min="0" id="price" class="form-control"  value="" name="price" placeholder="예) 1000000000">
												</div>
												<div class="col-md-1" style="margin-left: 0px; padding-top: 15px; padding-left: 0px;">
													<label></label><br> <label>원</label>
												</div>
											</div>
										</div>

										<!--daypicker-->
										<div class="col-md-8">
											<div class="form-group">
												<label>입주가능일&nbsp;&#42;</label>
												<div class="col-md-6 input-group date">
													<input type="text" class="form-control"  readonly placeholder="Select Date" id="moveAt" name="moveAt">
													<span class="input-group-addon">날짜선택</span>
												</div>
											</div>
										</div>
										<!--daypicker end-->

										<div class="col-md-12">
											<div class="form-group s-prop-features">
												<label for="textarea">매물 특징&nbsp;&#42;</label>
												<textarea id="features" name="features" rows="10" style="width: 100%;"></textarea>
											</div>
										</div>
									</div>
								</div>
								
								<!-- 추가사항 -->
								<div class="control-group">
									<div class="group-title">Additional Info</div>
									<div class="group-container row">
										<div class="col-md-12">
										
										<div class="col-md-3">
											<div class="form-group s-prop-title">
												<label for="builtYear">준공년도</label>
												<input type="number" min="1950" max="2030" class="form-control" name="builtYear" id="builtYear" placeholder="예) 2019"  value="">
											</div>
										</div>
										<div class="col-md-3">
											<div class="form-group s-prop-title">
												<label for="parking">주차대수</label>
												<input type="number" min="0" class="form-control" name="parking"  id="parking" placeholder="예) 1234"  value="">
											</div>
										</div>
										<div class="col-md-3">
											<div class="form-group s-prop-title">
												<label for="numApartment">단지 세대수</label>
												<input type="number" min="0" class="form-control" name="numApartment"  id="numApartment" placeholder="예) 12345"  value="">
											</div>
										</div>
										<div class="col-md-3">
											<div class="form-group s-prop-sub_location">
												<label>방 개수&nbsp;</label>
												<div class="dropdown label-select">
													<select class="form-control" id="room" name="room">
														<option value="0">선택</option>
														<option value="1">1</option>
														<option value="2">2</option>
														<option value="3">3</option>
														<option value="4">4</option>
														<option value="5">5</option>
														<option value="6">6</option>
													</select>
												</div>
											</div>
										</div>
										<div class="col-md-3">
											<div class="form-group s-prop-sub_location">
												<label>욕실 개수&nbsp;</label>
												<div class="dropdown label-select">
													<select class="form-control" id="bathroomNo" name="bathroomNo">
														<option value="0">선택</option>
														<option value="1">1</option>
														<option value="2">2</option>
														<option value="3">3</option>
														<option value="4">4</option>
														<option value="5">5</option>
													</select>
												</div>
											</div>
										</div>
										<div class="col-md-3">
											<div class="form-group s-prop-sub_location">
												<label>방향&nbsp;</label>
												<div class="dropdown label-select">
													<select class="form-control" id="aspect" name="aspect">
														<option value="0">선택</option>
														<option value="동">동</option>
														<option value="서">서</option>
														<option value="남">남</option>
														<option value="북">북</option>
														<option value="남동">남동</option>
														<option value="남서">남서</option>
														<option value="북동">북동</option>
														<option value="북서">북서</option>
													</select>
												</div>
											</div>
										</div>
										<div class="col-md-3">
											<div class="form-group s-prop-sub_location">
												<label>현관구조&nbsp;</label>
												<div class="dropdown label-select">
													<select class="form-control" id="entrance" name="entrance">
														<option value="0">선택</option>
														<option value="계단식">계단식</option>
														<option value="복도식">복도식</option>
													</select>
												</div>
											</div>
										</div>
										
										<div class="group-container row">
											<div class="col-md-9" style="padding-left: 0px; padding-tio:0px; padding-right: 140px;">
												<div class="col-md-2" style="padding-left: 11px; padding-right:0px;">
													<label>수리 여부</label>
												</div>
												<div class="col-md-7">
													<input type="radio" id="repaired" name="repaired" value="yes">&nbsp;YES&nbsp;
													<input type="radio" id="repaired" name="repaired" value="no">&nbsp;NO
												</div>
											</div>
										</div>
										
										</div>
									</div>
								</div>
								<!-- 추가사항 -->
								
								<div class="control-group">
									<div class="group-title">매물 사진</div>
									<div class="group-container row">
										<div class="col-md-12">
											<div id="upload-container">
												<div id="aaiu-upload-container">
													<div class="moxie-shim moxie-shim-html5">
														<label for="photoNames" class="btn flat-btn btn-lg">사진선택</label>
														<input onchange="fileInfo(this)" id="photoNames" name="photoNames" type="file" multiple accept="image/jpeg,image/gif,image/png" >
													</div>
													<p>하나 이상의 사진을 선택하세요</p>
													<div><div id="thumbnail" style="width:1000px; margin-left:145px;"></div></div>
												</div>
											</div>
										</div>
									</div>
								</div>
								
								<div class="control-group">
									<div class="group-title">등기부등본</div>
									<div class="group-container row">
										<div class="col-md-12">
											<div id="upload-container">
												<div id="aaiu-upload-container">
													<div class="moxie-shim moxie-shim-html5">
														<label for="photoRightImages" class="btn flat-btn btn-lg">사진선택</label> 
														<input onchange="fileInfoD(this)" id="photoRightImages" name="photoRightImages" type="file" multiple accept="image/jpeg,image/gif,image/png">
													</div>
													<p>하나 이상의 사진을 선택하세요</p>
													<div><div id="Dthumbnail" style="width:1000px; margin-left:145px;"></div></div>
												</div>
											</div>
										</div>
									</div>
								</div>

<!-- 								<div class="control-group">
									<div class="group-title">매물 자동/수동 공개</div>
									<div class="group-container row">
										<div class="col-md-9">
											<div class="col-md-2">
												<label>자동/수동 설정</label>
											</div>
											<div class="col-md-7">
												<input type="radio" id="autopublic" name="auto" value="1">&nbsp;자동&nbsp;
												<input type="radio" id="autopublic" name="auto" value="2">&nbsp;수동
											</div>
										</div>
									</div>
								</div> -->
								
								<input type="hidden" id="userId" name="userId" value="${sbAgentDo.userId}">
								<div class="submit row" style="text-align: center; clear: both; margin-top: 25px;">
									<div style="display: inline-block; width: 400px;">
										<input type="button" class="btn btn-lg flat-btn" value="Add Property" style="margin: 5px" onclick="return putZero()">
										<input type="reset" class="btn btn-lg flat-btn" value="Cancel" style="margin: 5px">
									</div>
								</div>
							</form>
						</div>
					</div>
				</div>
			</div>
		</section>

	</div>
	
<%@ include file="../common/footer.jsp" %>