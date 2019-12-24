<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../common/top.jsp" %>
<div class="payment-popup-sec">
	<div class="payment-popup-area">
		<div class="payment-popup">
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
						<form method="post" id="recharge" name="recharge" action="${pageContext.request.contextPath}/sales/payPro/${sb:getIdStr(sbAgentDo.userId)}" >
							<h4>ADD STA</h4>
							<div class="field">
								<label style="color:white;">충전할 금액을 확인하세요</label>
								<input type="text" readonly id="amount" name="amount"/>
							</div>
							<div class="field" style="padding:20px">
								<input type="submit" value="PAYMENT NOW" class="flat-btn" style="margin:5px"/>
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