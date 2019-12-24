<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div class="account-popup-sec">
	<div class="account-popup-area">
		<div class="account-popup">
			<div class="row">
				<div class="col-md-12">
					<div class="account-user">
						<div class="logo">
							<a href="#" title="" style="padding:0px 0px 0px 0px ">
							<img src="${pageContext.request.contextPath}/img/starbocks.png" width="50px" height="50px" align="left" style="margin-right:8px">
								<span>Starbocks</span><br>
								<strong style="width:185px">PROPERTIES FOR SALE</strong>
							</a>
						</div>
						<!-- LOGO -->
						<form id="signin" name="signin" method="post" action="${pageContext.request.contextPath}/signin" onsubmit="return logincheck()">
							<h4>SIGN-IN</h4>
							<div class="field">
								<input name="email" type="text" placeholder="이메일을 입력하세요" />
							</div>
							<div class="field">
								<input name="passwd" type="password" placeholder="패스워드를 입력하세요" />
							</div>
							<div class="field" style="padding:20px">
								<input type="submit" value="SEND NOW" class="flat-btn" style="margin:5px"/>
								
								<a href="${pageContext.request.contextPath}/signup">
									<input type="button" value="SIGN UP" class="flat-btn" style="margin:5px"/>
								</a>
							</div>
						</form>
					</div>
				</div>

			</div>
			<span class="close-popup"><i class="fa fa-close"></i></span>
		</div>
	</div>
</div>
<!-- Account Popup Sec -->

<script type="text/javascript">
//<!--
	function logincheck() {
		if (! document.signin.email.value) {
			alert("이메일를 입력하세요");
			return false;
		} else if (! document.signin.passwd.value) {
			alert("비밀번호를 입력하세요");
			return false;
		}

	}
//-->
</script>