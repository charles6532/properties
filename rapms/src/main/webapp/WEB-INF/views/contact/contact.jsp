<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../common/top.jsp"%>
<%@ include file="../common/signupbutton.jsp"%>
<%@ include file="../common/modifybutton.jsp"%>
<!--  DAUM MAP  -->
<script charset="UTF-8" class="daum_roughmap_loader_script" src="https://ssl.daumcdn.net/dmaps/map_js_init/roughmapLoader.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		new daum.roughmap.Lander({
			"timestamp" : "1562655863143",
			"key" : "uaf4",
			"mapWidth" : "600",
			"mapHeight" : "250"
		}).render();

		"use strict";

		$(function() {
			var foo = $('.gallery-box');
			foo.poptrox({
				usePopupCaption : true
			});
		});
	});
</script>
<!--  DAUM MAP -->

<div class="theme-layout">
	<div class="inner-head overlap">
		<div
			style="background: url(${pageContext.request.contextPath}/img/parallax1.jpg) repeat scroll 50% 422.28px transparent;"
			class="parallax scrolly-invisible no-parallax"></div>
		<!-- PARALLAX BACKGROUND IMAGE -->
		<div class="container">
			<div class="inner-content">
				<span><i class="fa fa-bolt"></i></span>
				<h2>CONTACT US</h2>
				<ul>
					<li><a href="#" title="">HOME</a></li>
					<li><a href="#" title="">CONTACT US</a></li>
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
						<h2>CONTACT US</h2>
					</div>
					<div class="contact-page-sec">
						<div class="container-fluid">
							<div class="row">
								<div class="col-md-5">
									<div class="contact-details">
										<div class="contact-infos">
											<ul>
												<li><span><i class="fa fa-phone"></i> Address</span>
													<p>
														서울 서초구 <br>우면산길 1234
													</p></li>
												<li><span><i class="fa fa-fax"></i> Fax/Email</span>
													<p>
														674-1235, 234-8987 <br>starbocks@email.com
													</p></li>
											</ul>
										</div>
									</div>
								</div>
								<div class="col-md-7">
									<div id="daumRoughmapContainer1562655863143" class="root_daum_roughmap root_daum_roughmap_landing"></div>
								</div>
							</div>
						</div>

					</div>
				</div>
			</div>
		</div>
	</section>
</div>
<%@ include file="../common/footer.jsp"%>