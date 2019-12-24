<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- top -->
<%@ include file="../common/top.jsp"%>
<%@ include file="../common/signupbutton.jsp"%>
<%@ include file="../common/modifybutton.jsp"%>
<script type="text/javascript">

	$(function() {
		$("select")
				.on(
						"change",
						function(event) {
							let value = event.target.value;
							var url = '${pageContext.request.contextPath}/common/askfor/';
							if ($(this).attr("id") == 'state') {
								url += $(this).attr("id")
										+ '/'
										+ $(
												"select[name=state] option:selected")
												.val();
								$("select[name=city]").append(
										"<option>Loading data ....</option>");
								$.ajax({
									type : "POST",
									url : url,
									data : {
										city : value
									},
									dataType : "json",
									success : function(data) {
										$("select[name=city] option").remove();
										let gun = "";
										gun = "<option>시/군/구</option>";
										$("select[name=city]").append(gun);
										for (let i = 0; i < data.length; i++) {
											gun = "<option>";
											gun += data[i].city;
											gun += "</option>";
											$("select[name=city]").append(gun);
										}
									},
									error : function() {
										alert("bad");
									}
								});
							}

							if ($(this).attr("id") == 'city') {
								url += $(this).attr("id")
								+ '/'
								+  $("select[name=city] option:selected")
												.val();
								$("select[name=legalDistrictNm]").append(
										"<option>Loading data ....</option>");
								$
										.ajax({
											type : "POST",
											url : url,
											data : {
												legalDistrictNm : value
											},
											dataType : "json",
											success : function(data) {
												$(
														"select[name=legalDistrictNm] option")
														.remove();
												let gun = "";
												gun = "<option>단지</option>";
												$(
														"select[name=legalDistrictNm]")
														.append(gun);
												for (let i = 0; i < data.length; i++) {
													gun = "<option>";
													gun += data[i].legalDistrictNm;
													gun += "</option>";
													$(
															"select[name=legalDistrictNm]")
															.append(gun);
												}
											},
											error : function() {
												alert("bad");
											}
										});
							}

						});
	});
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
				<h2>회원정보 수정</h2>
				<ul>
					<li><a href="${pageContext.request.contextPath}/contact"
						title="">CONTACT</a></li>
					<li><a href="${pageContext.request.contextPath}/modify"
						title="">회원정보 수정</a></li>
				</ul>
			</div>
		</div>
	</div>

	<section class="block">
		<div class="container agnet-prop">
			<div class="row">
				<div class="col-md-12 column">
					<div class="heading4">
						<h2>회원정보 수정</h2>
					</div>

					<div class="submit-content">
						<form class="property-form" id="modifyform" name="modifyform"
							method="post" onsubmit="return modifycheck()"
							action="${pageContext.request.contextPath}/common/confirmMod">
							<div class="control-group">
								<div class="group-title">Agent 기본정보 수정</div>
								<div class="group-container row">



									<div class="col-md-12">
										<div class="col-md-6">
											<div class="form-group s-prop-title">
												<label for="address">사무실 소재지</label> <input type="text"
													id="address" name="address" class="form-control"
													value="${agentdo.address}" readonly>
											</div>
										</div>
									</div>

									<div class="col-md-12">
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
															<option value="${sbzip.city}">${sbzip.city}</option>
														</c:forEach>
													</select>
												</div>
											</div>
										</div>
										<div class="col-md-4">
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
									</div>
									<div class="col-md-12">
										<div class="col-md-4">
											<div class="form-group s-prop-title">
												<label for="nickname">이름&nbsp;&#42;</label> <input
													type="text" id="name" class="form-control"
													value="${agentdo.name}" name="name">
											</div>
										</div>
									</div>
									<div class="col-md-12">
										<div class="col-md-4">
											<div class="form-group s-prop-title">
												<label for="nickname">사무실명&nbsp;&#42;</label> <input
													type="text" id="nickname" class="form-control"
													value="${agentdo.nickname}" name="nickname">
											</div>
										</div>
									</div>

									<div class="col-md-12">
										<div class="col-md-4">
											<div class="form-group s-prop-title">
												<label for="tel">사무실 연락처</label> <input type="text" min="0"
													id="tel" class="form-control" value="${agentdo.tel}"
													name="tel">
											</div>
										</div>
									</div>

									<div class="col-md-12">
										<div class="col-md-4">
											<div class="form-group s-prop-title">
												<label for="deposit">Star Balance</label>
												<%-- <input type="text" id="deposit" name="deposit" class="form-control" value="${sbPaymentDo.amount}" readonly> --%>
											</div>
										</div>
									</div>
								</div>
							</div>
							<!-- 비번 변경 -->
							<div class="control-group">
								<div class="group-title">비밀번호 변경</div>
								<div class="group-container row">
									<div class="col-md-12">

										<div class="col-md-12">
											<div class="col-md-3">
												<div class="form-group s-prop-title">
													<label for="text">변경할 비밀번호</label> <input type="password"
														class="form-control" name="passwd" id="passwd">
												</div>
											</div>
										</div>

										<div class="col-md-12">
											<div class="col-md-3">
												<div class="form-group s-prop-title">
													<label for="text">변경할 비밀번호 확인</label> <input
														type="password" class="form-control" name="repasswd"
														id="repasswd">
												</div>
											</div>
										</div>
										<!-- <div class="col-md-12">
											<div class="col-md-3 alert alert-success" id="alert-success" style=" padding-top: 15px; padding-left: 8px; margin-left: 14px; width: 246px;">
												비밀번호가 일치합니다
											</div>
										</div>
										<div class="col-md-12">
											<div class="col-md-3 alert alert-danger" id="alert-danger" style=" padding-top: 15px; padding-left: 8px; margin-left: 14px; width: 246px;">
												비밀번호가 불일치합니다
											</div>
										</div> -->

										<!-- 										<div class="group-container row">
											<div class="col-md-9" style="padding-left: 0px; padding-tio:0px; padding-right: 140px;">
												<div class="col-md-2" style="padding-left: 11px; padding-right:0px;">
													<label>매물 자동공개 설정</label>
												</div>
												<div class="col-md-7">
													<input type="radio" id="autopublic" name="repaired" value="yes">&nbsp;YES&nbsp;
													<input type="radio" id="autopublic" name="repaired" value="no">&nbsp;NO
												</div>
											</div>
										</div> -->

									</div>
								</div>
							</div>
							<!-- 추가사항 -->

							<div class="row">
								<div class="col-md-12"></div>
							</div>
							<input type="hidden" id="email" name="email"
								value="${sbAgentDo.email}"> <input type="hidden"
								id="userId" name="userId" value="${sbAgentDo.userId}">
							<div class="submit row"
								style="text-align: center; clear: both; margin-top: 25px;">
								<div style="display: inline-block; width: 400px;">
									<input type="submit" class="btn btn-lg flat-btn" value="Update"
										style="margin: 5px"> <input type="reset"
										class="btn btn-lg flat-btn" value="Cancel" style="margin: 5px">
								</div>
							</div>

						</form>

					</div>
				</div>
			</div>
		</div>
	</section>
</div>

<script type="text/javascript">
	 $(function () {
		$("#alert-success").hide();
		$("#alert-danger").hide();
		$("input").keyup(function() {
			var passwd=$("#passwd").val();
			var repasswd=$("#repasswd").val();
			if( passwd != "" || repasswd != "" ) {
				if( passwd == repasswd ) {
					$("#alert-success").show();
					$("#alert-danger").hide();
				} else {
					$("#alert-danger").show();
					$("#alert-success").hide();
				}
			}
		});
		
	} ); 
	
	function modifycheck() {
		if( modifyform.passwd.value != modifyform.repasswd.value ) {
			alert( "Make Password Same" );
			modifyform.repasswd.focus();
			return false;
		} 
	} //function modifycheck
</script>

<%@ include file="../common/footer.jsp"%>