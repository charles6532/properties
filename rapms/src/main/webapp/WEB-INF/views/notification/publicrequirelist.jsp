<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../common/top.jsp" %>
<%@ include file="../common/signupbutton.jsp" %>
<div class="theme-layout">
	<div class="inner-head overlap">
		<div
			style="background: url(${pageContext.request.contextPath}/img/parallax1.jpg) repeat scroll 50% 422.28px transparent;"
			class="parallax scrolly-invisible no-parallax"></div>
		<!-- PARALLAX BACKGROUND IMAGE -->
		<div class="container">
			<div class="inner-content">
				<span><i class="ti ti-home"></i></span>
				<h2>공개요청내역</h2>
				<ul>
					<li><a href="${pageContext.request.contextPath}/noti" title="">NOTIFICATION</a></li>
					<li><a href="${pageContext.request.contextPath}/noti/pub" title="">공개요청내역</a></li>
				</ul>
			</div>
		</div>
	</div>
	<!-- inner Head -->

	<section class="block">
		<div class="container">
			<div class="row">
				<div class="col-md-12">
					<div class="heading4">
						<h2>공개요청내역</h2>
					</div>
					<div class="row">
						<div class="properties-sec">
							<div class="properties-list">
								<div class="filter-wrapper">
									<ol class="list-option-filter">

										<!-- select how many post you want to see perpage -->
										<li>
											<div class="option-filter-box">
												<div class="label-select">
													<select class="form-control">
														<option value="">매물/page</option>
														<option value="4">4 매물/page</option>
														<option value="8">8 매물/page</option>
														<option value="12">12 매물/page</option>
													</select>
												</div>
											</div>
										</li>
										<!--// select how many post you want to see perpage -->
										<li class="sort-rates-lastest">
											<div class="option-filter-box">
												<span class="title">SORT BY:</span> <a href=""
													class="sort-icon orderby" data-order=""
													data-sort="rating_score"> Rating </a> / <a href=""
													class="sort-icon orderby active" data-order=""
													data-sort="date"> Latest </a>
											</div>
										</li>
										<li class="icon-list-view">
											<div class="option-filter-box">
												<span class="icon-view grid-style active"><i
													class="fa fa-th"></i></span> <span
													class="icon-view fullwidth-style"><i
													class="fa fa-th-list"></i></span>
											</div>
										</li>
									</ol>
									<div class="clearfix"></div>
								</div>

								<div class="properties-content properties-grid">
									<div class="container ">
										<table class="table table-bordered table-striped table-hover">
											<tr>
												<th>Product ID</th>
												<th>Product Name</th>
												<th>Product Quality</th>
												<th>Product Quantity</th>
											</tr>
											<tr class="info">
												<td>1</td>
												<td>Wheat</td>
												<td>Good</td>
												<td>200 Bags</td>
											</tr>
											<tr class="danger">
												<td>2</td>
												<td>Rice</td>
												<td>Good</td>
												<td>250 Bags</td>
											<tr class="warning">
												<td>3</td>
												<td>Sugar</td>
												<td>Good</td>
												<td>200 Bags</td>
											</tr>
											<tr class="active">
												<td>3</td>
												<td>Sugar</td>
												<td>Good</td>
												<td>200 Bags</td>
											</tr>
											<tr class="success">
												<td>3</td>
												<td>Sugar</td>
												<td>Good</td>
												<td>200 Bags</td>
											</tr>
										</table>
									</div>
									<ul class="pagination">
										<li class="disabled"><a href="#" title=""><span>NEXT</span></a></li>
										<li><a href="#" title="">1</a></li>
										<li class="active"><a href="#" title="">2</a></li>
										<li><a href="#" title="">3</a></li>
										<li><a href="#" title=""><span>PREV</span></a></li>
									</ul>

								</div>
							</div>
						</div>
					</div>
				</div>
				<!-- Services Sec -->
				<!-- Category Widget -->
			</div>

		</div>
	</section>
</div>
<%@ include file="../common/footer.jsp" %>