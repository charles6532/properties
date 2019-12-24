<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div class="modify-popup-sec">
	<div class="modify-popup-area">
		<div class="modify-popup">
			<div class="row">
				<div class="col-md-12">
					<div class="account-user">
						<div class="logo">
							<a href="#" title="" style="padding:0px 0px 0px 0px ">
							<img src="${pageContext.request.contextPath}/img/starbocks.png" width="50px" height="50px" align="left" style="margin-right:8px"><span>Starbocks</span><br>
								<strong style="width:185px">PROPERTIES FOR SALE</strong>
							</a>
						</div>
						<!-- LOGO -->
						<form method="post" id="modify" name="modify" action="${pageContext.request.contextPath}/modifypro" onsubmit="return modifycheck()" >
							<h4>비밀번호 확인</h4>
							<div class="field">
								<label style="color:white;">비밀번호를 입력하세요</label>
								<input type="password" id="passwd" name="passwd"/>
								<input type="hidden" id="email" name="email" value="${sbAgentDo.email}"/>
							</div>
							<div class="field" style="padding:20px">
								<input type="submit" value="CONFIRM" class="flat-btn" style="margin:5px"/>
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
	function modifycheck() {
		if (! document.modify.passwd.value) {
			alert("비밀번호를 입력하세요");
			return false;
		}
	}
//-->
</script>