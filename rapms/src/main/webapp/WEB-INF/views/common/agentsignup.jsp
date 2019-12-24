<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- top -->
<%@ include file="../common/top.jsp" %>
<%@ include file="../common/signupbutton.jsp" %>
<!-- 우편번호 검색창 -->
<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
<script>
	function postcode() {
		new daum.Postcode({
			oncomplete : function(data) {
				var addr = '';
				var extraAddr = '';

				if (data.userSelectedType === 'R') {
					addr = data.roadAddress;
				} else {
					addr = data.jibunAddress;
				}

				if (data.userSelectedType === 'R') {
					if (data.bname !== '' && /[동|로|가]$/g.test(data.bname)) {
						extraAddr += data.bname;
					}
					if (data.buildingName !== '' && data.apartment === 'Y') {
						extraAddr += (extraAddr !== '' ? ', '
								+ data.buildingName : data.buildingName);
					}
					if (extraAddr !== '') {
						extraAddr = ' (' + extraAddr + ')';
					}
					document.getElementById("address1").value = extraAddr;
				} else {
					document.getElementById("address1").value = '';
				}
				document.getElementById('zipcode').value = data.zonecode;
				document.getElementById("address1").value = addr;
				document.getElementById("address2").focus();
			}
		}).open();
	}
	
</script><!-- 우편번호 검색창 -->
<script type="text/javascript">
//agent sign-up 비밀번호 체크
function checkPwd(){
	//var f1 = document.forms[0];
	var pw1 = $("#passwd").val();
	var pw2 = $("#repasswd").val();
	if( pw1 != pw2 ){
		document.getElementById('passwdck').innerHTML = "비밀번호 불일치"; 
	} else {
		document.getElementById('passwdck').innerHTML = "비밀번호 일치"; 
	}
}

//agent sign-up 회원가입
function signupcheck() {
	if( ! agent_signup.email1.value ) {
		alert( "Email" );
		agent_signup.email.focus();
		return false;
	} else if( ! agent_signup.passwd.value ) {
		alert( "Password" );
		agent_signup.passwd.focus();
		return false;
	} else if( agent_signup.passwd.value != agent_signup.repasswd.value ) {
		alert( "Make Password Same" );
		agent_signup.repasswd.focus();
		return false;
	} else if( ! agent_signup.officename.value ) {
		alert( "Office Name");
		agent_signup.officename.focus();
		return false;
	} else if( ! agent_signup.mobile.value ) {
		alert( "mobile" );
		agent_signup.mobile.focus();
		return false;
	} else if( ! agent_signup.agentnumber.value ) {
		alert( "agent number" );
		agent_signup.agentnumber.focus();
		return false;
	} else if( ! agent_signup.address2.value ) {
		alert( "address" );
		agent_signup.address2.focus();
		return false;
	}
	if( agent_signup.email1.value ) {
		if( agent_signup.email2.value =="0" ) {
			//직접입력
			if( agent_signup.email1.value.indexOf("@") == -1 ){
				alert( "Email @" );
				agent_signup.email1.focus();
				return false;
			}
		} else {
			//선택입력
			if( agent_signup.email1.value.index("@") != -1 ) {
				alert( "Email !" );
				agent_signup.email1.focus();
			}
		}
	}
} //function signupcheck
function signupok() {
	window.location.href = "pro/signupPro"
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
					<h2>AGENT SIGN-UP</h2>
					<ul>
						<li><a href="#" title="">HOME</a></li>
						<li><a href="#" title="">AGENT SIGN-UP</a></li>
					</ul>
				</div>
			</div>
		</div>
		<!-- inner Head -->


		<section class="block ">
			<div class="container agnet-prop">
				<div class="row">
					<div class="col-md-12 column">
						<div class="heading4">
							<h2>Agent Sign-up</h2>
						</div>

						<div class="container" style="max-width: 800px">
							<form id="agent_signup" name="agent_signup" method="post" class="property-form" action="${pageContext.request.contextPath}/pro/signupPro" role="form" onsubmit="return signupcheck()">
								<div class="control-group">
									<div class="group-title">이메일 / 비밀번호</div>
									<div class="group-container row">
										<div class="col-md-12">
											<label for="title">이메일&nbsp;&#42;</label>
											<div class="form-group s-prop-title">
												<input type="text" id="email1" style="width: 70%" class="col-md-6 form-control" value="" rows="1" placeholder="이메일주소 입력" name="email1" required="">
												<div class="label-select col-md-3">
													<select class="form-control" id="email2" name="email2">
														<option value="0">직접입력</option>
														<option value="naver.com">네이버</option>
														<option value="daum.net">다음</option>
														<option value="gmail.com">구글</option>
														<option value="nate.com">네이트</option>
													</select>
												</div>
											</div>
										</div>
										<div class="col-md-9">
											<label for="email" style="padding-top:10px">이메일 인증&nbsp;&#42;</label>
											<div class="form-group">
												<textarea id="confirmemail" style="width: 80%" class="col-md-9 form-control" name="confirmemail" rows="1" placeholder="인증번호 입력" required=""></textarea>
												&nbsp;&nbsp;&nbsp;
												<button class="btn btn-info">인증</button>
											</div>
										</div>
										<div class="col-md-12">
											<div class="form-group">
												<div class="col-md-8" style="padding-left:0px">
												<label for="passwd" style="margin: 10px 0px 0px; padding-left: 0px; padding-top: 5px;" >비밀번호&nbsp;&#42;</label>
												<input type="password" id="passwd" style="width: 60%" class="form-control" name="passwd" rows="1">
												</div>
											</div>
										</div>
										<div class="col-md-12">
											<div class="form-group">
												<div class="col-md-8"  style="padding-left:0px; padding-top:5px">
													<label for="repasswd" style="padding-top:10px">비밀번호 확인&nbsp;&#42;</label>
													<input type="password" id="repasswd" style="width: 60%" class="form-control" name="repasswd" rows="1" onkeyup="checkPwd()">
												</div>
												<div class="col-md-4" id="passwdck" style="margin-left: 0px; padding-top: 15px; padding-left: 0px;">
												</div>
											</div>
										</div>
									</div>

								</div>

								<div class="control-group">
									<div class="group-title">공인중개사 사무실</div>
									<!-- <div class="group-container row"> -->
									<div class="col-md-9">
										<div class="group-container row"">
											<label for="address" style="margin: 16px 0px 0px">중개사무소명&nbsp;&#42;</label>
											<textarea id="officename" class="form-control" name="officename" rows="1"></textarea>
										</div>
									</div>
									<div class="col-md-8">
										<div class="group-container row"">
											<label for="property_field_lot_area">사무실 전화번호</label>
											<input type="text" id="tel" name="tel" class="form-control" value="">
										</div>
									</div>
									<!-- </div> -->
									<div class="group-container row">
										<div class="col-md-8">
											<div class="form-group property_field_lot_area">
												<label for="property_field_lot_area">공인중개사 전화번호&nbsp;&#42;</label>
												<input type="text" id="mobile" name="mobile" class="form-control" value="">
											</div>
										</div>
									</div>
									<div class="group-container row">
										<div class="col-md-8">
											<div class="form-group property_field_lot_area">
												<label for="property_field_lot_area">사업자 등록번호&nbsp;&#42;</label>
												<input type="text" id="agentnumber" name="agentnumber" class="form-control" value="">
											</div>
										</div>
									</div>
								</div>

								<div class="control-group">
									<div class="group-title">사무실 주소</div>
									<div class="group-container row">
										<div class="col-md-9">
											<label for="zipcode">우편번호 검색&nbsp;&#42;</label>
											<div class="form-group s-prop-address">
												<textarea id="zipcode" style="width: 80%" class="col-md-9 form-control" name="zipcode" rows="1" placeholder="검색버튼을 누르세요" readonly></textarea>
												&nbsp;&nbsp;&nbsp;
												<button class="btn btn-info" onclick="return postcode()">검색</button>
											</div>
										</div>

										<div class="col-md-9">
											<div class="form-group s-prop-address">
												<label for="address">Address</label>
												<textarea id="address1" class="form-control" name="address1" rows="1" readonly></textarea>
												<label for="address" style="margin: 10px 0px 0px">상세주소&nbsp;&#42;</label>
												<textarea id="address2" class="form-control" name="address2" rows="1" required=""></textarea>
											</div>
										</div>
									</div>
								</div>
								<div class="submit row" style="text-align: center; clear: both; margin-top: 25px;">
									<div style="display: inline-block; width: 400px;">
										<input type="submit" class="btn btn-lg flat-btn" id="signup_submit" value="Add Property" style="margin: 5px" onclick="/main">
										<input type="reset" class="btn btn-lg flat-btn" id="btn_reset" value="Cancel" style="margin: 5px">
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