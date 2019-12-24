<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../common/top.jsp" %>
<%@ include file="../common/modifybutton.jsp" %>
<%@ include file="./paymentbutton.jsp" %>

<script type="text/javascript">
function amoutalert(){
	if( $("#amountOfPayment").val() == "선택" ) {
		alert( "금액을 확인하세요" );
		return false;
	}
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
				<h2>스타 충전하기</h2>
				<ul>
					<li><a href="${pageContext.request.contextPath}/sales/list/1" title="">SALES STATEMENT</a></li>
					<li><a href="${pageContext.request.contextPath}/sales/pay" title="">스타 충전하기</a></li>
				</ul>
			</div>
		</div>
	</div>
	<!-- inner Head -->

	<section class="block">
		<div class="container agnet-prop">
			<div class="row">
				<div class="col-md-12 column">
					<div class="heading4">
						<h2>스타 충전하기</h2>
					</div>

					<div class="submit-content">
						<form id="recharge" name="recharge" method="post" class="property-form" role="form"
							action="${pageContext.request.contextPath}/sales/payPro/${sb:getIdStr(sbAgentDo.userId)}" onsubmit="amoutalert()">
							<div class="control-group">
								<div class="group-title">Agent 정보</div>
								<div class="group-container row">
									<div class="col-md-8">
									<div class="col-md-6">
										<div class="form-group s-prop-title">
											<label for="title">공인중개사 사무실명</label>
											<input type="text" id="userId" class="form-control" value="${sbAgentDo.userId}" name="userId" readonly>
										</div>
									</div>
									<div class="col-md-6">
										<div class="form-group s-prop-title">
											<label for="title">보유 스타잔액</label>
											<input type="text" id="currbalance" class="form-control" value="${balance}" name="currbalance" readonly>
										</div>
									</div>
									<div class="col-md-3">
										<div class="form-group s-prop-title">
											<label for="title">충전할 스타&nbsp;&#42;</label>
											<div class="dropdown label-select">
													<select class="form-control" id="amountOfPayment" name="amountOfPayment">
														<option value="0">선택</option>
														<option value="1000">1,000원</option>
														<option value="3000">3,000원</option>
														<option value="5000">5,000원</option>
														<option value="10000">10,000원</option>
														<option value="30000">30,000원</option>
													</select>
												</div>
										</div>
									</div>
								</div>
							</div>
							</div>

							<div class="row">
								<div class="col-md-12"></div>
							</div>
							<div class="submit row" style="text-align: center; clear: both; margin-top: 25px;">
								<div style="display: inline-block; width: 400px;">
									<div class="popup-payment" style="margin-left: 0px; padding-left:12px;">
										<input type="button" class="btn btn-lg flat-btn" id="addstar" value="Payment" style="margin: 5px; float:left;"> 
									</div>
									<div id="popup-payment" class="modal fade">
									</div>
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