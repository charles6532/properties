<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- top -->
<%@ include file="../common/top.jsp" %>
<%@ include file="../common/signupbutton.jsp" %>

<title>Add Property</title>

	<div class="theme-layout">
		<div class="inner-head overlap">
			<div style="background: url(${pageContext.request.contextPath}/img/parallax1.jpg) repeat scroll 50% 422.28px transparent;" class="parallax scrolly-invisible no-parallax"></div>
			<!-- PARALLAX BACKGROUND IMAGE -->
			<div class="container">
				<div class="inner-content">
					<span><i class="ti ti-home"></i></span>
					<h2>내매물 수정</h2>
					<ul>
						<li><a href="${pageContext.request.contextPath}/prop" title="">PROPERTY</a></li>
						<li><a href="${pageContext.request.contextPath}/prop/list/${page}/${sbPropertyDo.propId}" title="">내매물 수정</a></li>
					</ul>
				</div>
			</div>
		</div>

		<section class="block">
			<div class="container agnet-prop">
				<div class="row">
					<div class="col-md-12 column">
						<div class="heading4">
							<h2>내매물 수정</h2>
						</div>

						<div class="submit-content">
						
							<form class="property-form" id="modifyproperty" name="modifyproperty" enctype="multipart/form-data" method="post" role="form" action="${pageContext.request.contextPath}/prop/modifyPro/${sb:getIdStr(sbPropertyDo.propId)}" >
								<input type="hidden"  name="confirm" value="" >
								<input type="hidden" id="address" name="address" value="">
								<input type="hidden" id="photoName" name="photoName" value="photoN">
								<input type="hidden" id="photoRight" name="photoRight" value="photoR">
								<div class="control-group">
									<div class="group-title">Property features &amp; Price</div>
									<div class="group-container row">
										<div class="col-md-12">
											<div class="form-group s-prop-title">
												<label for="title">매물명&nbsp;&#42;</label>
												<input type="text" id="title" class="form-control" value="${sbPropertyDo.title}" name="title">
											</div>
										</div>
										<div class="col-md-3">
											<div class="form-group s-prop-location">
												<label>소재지</label>
													<div class="label-select">
														<select id="state" name="state" class="form-control">
		                                                        <option value="${sbPropertyDo.state}">${sbPropertyDo.state}</option>
		                                                        <option value="서울">서울</option>
		                                                        <option value="경기">경기</option>
		                                                    </select>
														</select>
													</div>
											</div>
										</div>
										<div class="col-md-3">
											<div class="form-group s-prop-sub_location">
												<label>&nbsp;</label>
												<div class="label-select">
													<select name="city" id="city" class="form-control">
														<option value="${sbPropertyDo.city}">${sbPropertyDo.city}</option>
														<c:forEach var="slido" items="${slido}" varStatus="i">
															<option value="${slido.city}">${slido.city}</option>
														</c:forEach>
													</select>
												</div>
										</div>
										</div>
										<div class="col-md-3">
											<div class="form-group s-prop-sub_location">
												<label>&nbsp;</label>
												<div class="label-select">
													<select name="legalDistrictNm" id="legalDistrictNm"
														class="form-control">
														<option value="${sbPropertyDo.legalDistrictNm}">${sbPropertyDo.legalDistrictNm}</option>
														<c:forEach var="slido" items="${slido}" varStatus="i">
															<option value="${slido.legalDistrictNm}">
																${slido.legalDistrictNm}</option>
														</c:forEach>
													</select>
												</div>
										</div>
										</div>
										<div class="col-md-3">
											<div class="form-group s-prop-sub_location">
												<label>&nbsp;</label>
												<div class="label-select">
													<select name="bldgNm" id="bldgNm" class="form-control">
														<option value="${sbPropertyDo.aptName}">${sbPropertyDo.aptName}</option>
														<c:forEach var="slido" items="${slido}" varStatus="i">
															<option value="${slido.bldgNm}">${slido.bldgNm}</option>
														</c:forEach>
													</select>
												</div>
											</div>
										</div>
										<input type="hidden" id="stateTwo" value="${sbPropertyDo.state}">
										<input type="hidden" id="cityTwo" value="${sbPropertyDo.city}">
										<input type="hidden" id="legalDistrictNmTwo" value="${sbPropertyDo.legalDistrictNm}">
										<input type="hidden" id="bldgNmTwo" value="${sbPropertyDo.aptName}">
										<div class="col-md-4">
											<div class="form-group s-prop-title">
												<label for="dong">아파트 동&nbsp;&#42;</label>
												<input type="number" min="0" id="dong" class="form-control" value="${sbPropertyDo.dong}" name="dong">
											</div>
										</div>
										
										<div class="col-md-4">
											<div class="form-group s-prop-title">
												<label for="hoNo">아파트 호수&nbsp;&#42;</label>
												<input type="number" min="0" id="hoNo" class="form-control" value="${sbPropertyDo.hoNo}" name="hoNo">
											</div>
										</div>
										<div class="col-md-4">
											<div class="form-group s-prop-sub_location">
												<label>층수&nbsp;</label>
												<div class="dropdown label-select">
													<select class="form-control" id="floorDesc" name="floorDesc">
														<option value="${sbPropertyDo.floor}">${sbPropertyDo.floor}</option>
														<option value="high">고층</option>
														<option value="medium">중층</option>
														<option value="low">저층</option>
													</select>
												</div>
											</div>
										</div>
										<div class="col-md-12">
											<div class="form-group s-prop-price row">
												<div class="col-md-4">
													<label for="areaSpace">면적</label>
													<input type="number" min="0" step="0.001" id="areaSpace" class="form-control" value="${sbPropertyDo.areaSpace}" name="areaSpace" placeholder="예) 84.123">
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
													<input type="number" min="0" id="price" class="form-control" value="${sbPropertyDo.price}" name="price" placeholder="예) 1000000000">
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
													<input type="text" class="form-control"  readonly value="${sbPropertyDo.moveAt}" id="moveAt" name="moveAt">
													<span class="input-group-addon">날짜선택</span>
												</div>
											</div>
										</div>
										<!--daypicker end-->

										<div class="col-md-12">
											<div class="form-group s-prop-features">
												<label for="textarea">매물 특징&nbsp;&#42;</label>
												<textarea id="features" name="features" rows="10" style="width: 100%;">${sbPropertyDo.features}</textarea>
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
												<input type="number" min="1950" max="2030" value="${sbPropertyDo.builtYear}" class="form-control" name="builtYear" id="builtYear" placeholder="예) 2019">
											</div>
										</div>
										<div class="col-md-3">
											<div class="form-group s-prop-title">
												<label for="parking">주차대수</label>
												<input type="number" min="0" class="form-control" value="${sbPropertyDo.parking}" name="parking"  id="parking" placeholder="예) 1234">
											</div>
										</div>
										<div class="col-md-3">
											<div class="form-group s-prop-title">
												<label for="numApartment">단지 세대수</label>
												<input type="number" min="0" class="form-control" value="${sbPropertyDo.numApartment}" name="numApartment"  id="numApartment" placeholder="예) 12345">
											</div>
										</div>
										<div class="col-md-3">
											<div class="form-group s-prop-sub_location">
												<label>방 개수&nbsp;</label>
												<div class="dropdown label-select">
													<select class="form-control" id="room" name="room">
														<option value="${sbPropertyDo.room}">${sbPropertyDo.room}</option>
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
														<option value="${sbPropertyDo.bathroomNo}">${sbPropertyDo.bathroomNo}</option>
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
														<option value="${sbPropertyDo.aspect}">${sbPropertyDo.aspect}</option>
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
														<option value="${sbPropertyDo.entrance}">${sbPropertyDo.entrance}</option>
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
													<input type="radio" id="repaired" name="repaired" value="yes"<c:if test="${sbPropertyDo.repaired eq 'yes'}">checked="checked"</c:if>>&nbsp;YES&nbsp;
													<input type="radio" id="repaired" name="repaired" value="no"<c:if test="${sbPropertyDo.repaired eq 'no'}">checked="checked"</c:if>>&nbsp;NO
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
														<input onchange="fileInfo(this)" id="photoNames" type="file" multiple accept="image/jpeg,image/gif,image/png" name="photoNames">
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
								
								<div class="submit row"
									style="text-align: center; clear: both; margin-top: 25px;">
									<div style="display: inline-block; width: 400px;">
										<input type="submit" class="btn btn-lg flat-btn" id="modify_submit" name="modify_submit" value="매물 수정" style="margin: 5px">
										<input type="reset" class="btn btn-lg flat-btn" id="modify_reset" name="modify_reset" value="수정 취소" style="margin: 5px">
										${sbPropertyDo.address}
									</div>
								</div>
							</form>
						</div>
					</div>
				</div>
			</div>
		</section>
<%@ include file="addproperty.jsp" %>
	</div>
	<script type="text/javascript">
	var isInit = true;
	var state = '${sbLocationDo.state}';
	var city = '${sbLocationDo.city}';
	var legalDistrictNm = '${sbLocationDo.legalDistrictNm}';
	var aptName = '${sbLocationDo.bldgNm}';
	var floor = '${sbPropertyDo.floor}';
	var room = '${sbPropertyDo.room}';
	var aspect = '${sbPropertyDo.aspect}';
	var entrance = '${sbPropertyDo.entrance}';
	var bathroomNo = '${sbPropertyDo.bathroomNo}';
		$(document).ready( function() {
			
		
		var str = $('#features').val();
		str = str.replace(/(?:\r\n|\r|\n)/g, '<br/>');
		str = str.split('<br/>').join("\r\n");
		$('#features').val(str);
		
		"use strict";
		$(".related-properties-items").owlCarousel({
			autoplay : true,
			autoplayTimeout : 3000,
			smartSpeed : 2000,
			loop : true,
			dots : true,
			nav : false,
			marging : 30,
			items : 4,
			responsiveClass : true,
			responsive : {
				0 : {
					items : 1,
					nav : false
				},
				600 : {
					items : 2,
					nav : false
				},
				1000 : {
					items : 3,
					nav : true,
					loop : false
				}
			}
		});
		
		$('.col-md-4.date').datepicker({});
		
		$('.col-md-6.date').datepicker({
			dateFormat: 'yyyy-mm-dd',
			minDate: 0
		});
		});
	</script>
<%@ include file="../common/footer.jsp" %>