<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../common/top.jsp" %>
<%@ include file="../common/signupbutton.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sb" uri="/WEB-INF/spring/tld/starbocks.tld"%>

<div class="theme-layout">
	<div class="inner-head overlap">
		<div
			style="background: url(${pageContext.request.contextPath}/img/parallax1.jpg) repeat scroll 50% 422.28px transparent;"
			class="parallax scrolly-invisible no-parallax"></div>
		<!-- PARALLAX BACKGROUND IMAGE -->
		<div class="container">
			<div class="inner-content">
				<span><i class="ti ti-home"></i></span>
				<h2>매물 상세보기</h2>
				<ul>
					<li><a href="${pageContext.request.contextPath}/prop/list/1" title="">PROPERTIES</a></li>
					<li><a href="${pageContext.request.contextPath}/prop/propDetail" title="">매물 상세보기</a></li>
				</ul>
			</div>
		</div>
	</div>
	<!-- inner Head -->

	<section class="block">
		<div class="container">
			<div class="row">
				<div class="col-md-12">
					<div class="heading4" >
						<h2>매물 상세보기</h2>
					</div>
					<div class="row">
						<div class="col-md-12 column">
							<div class="single-post-sec">
								<div class="blog-post property-post">
									
									<h1>매물사진</h1>
									<div class=" row col-md-10">
										<div class="carousel-inner">
											<div class="carousel-item active">
												<c:forEach var="photo"  items="${internalPhotos }">
													<img src="${pageContext.request.contextPath}${sb:getThumbnailPhotoUrl( photo.photoId )}">
												</c:forEach>
											</div>
											<a class="carousel-control-prev" data-slide="prev">
												<span class="carousel-control-prev-icon"></span>
											</a>
											<a class="carousel-control-next" data-slide="next">
												<span class="carousel-control-next-icon"></span>
											</a>
										</div>
									</div>

									<h1>매물명 : ${sbPropertyDo.title}</h1>

									<div class="row">
										<div class="col-md-6">
											<div class="property-detail">

												<div class="detail-field row">

													<span class="col-xs-6 col-md-5 detail-field-label">단지명</span>
													<span class="col-xs-6 col-md-7 detail-field-value">${sbPropertyDo.aptName}</span> 

													<span class="col-xs-6 col-md-5 detail-field-label">매매가격</span>
													<span class="col-xs-6 col-md-7 detail-field-value"><fmt:formatNumber value="${sbPropertyDo.price}" pattern="#,###"/>원</span> 
													
													<span class="col-xs-6 col-md-5 detail-field-label">입주가능일</span>
													<span class="col-xs-6 col-md-7 detail-field-value">${sbPropertyDo.moveAt}</span>
													
													<span class="col-xs-6 col-md-5 detail-field-label">방 개수</span>
													<span class="col-xs-6 col-md-7 detail-field-value">${sbPropertyDo.room}</span>
													
													<span class="col-xs-6 col-md-5 detail-field-label">층수</span>
													<span class="col-xs-6 col-md-7 detail-field-value">${sbPropertyDo.floor}</span> 
													
													<span class="col-xs-6 col-md-5 detail-field-label">면적</span> 
													<span class="col-xs-6 col-md-7 detail-field-value">${sbPropertyDo.areaSpace}</span>

													<span class="col-xs-6 col-md-5 detail-field-label">준공년도</span> 
													<span class="col-xs-6 col-md-7 detail-field-value">${sbPropertyDo.builtYear}</span>
													
													<span class="col-xs-6 col-md-5 detail-field-label">세대수</span>
													<span class="col-xs-6 col-md-7 detail-field-value">${sbPropertyDo.numApartment}</span>
													
													<span class="col-xs-6 col-md-5 detail-field-label">현관구조</span>
													<span class="col-xs-6 col-md-7 detail-field-value">${sbPropertyDo.entrance}</span>
													
													<span class="col-xs-6 col-md-5 detail-field-label">Parking</span>
													<span class="col-xs-6 col-md-7 detail-field-value">${sbPropertyDo.builtYear}</span> -->
												</div>

											</div>
										</div>
										<div class="col-md-6">
											<!-- <span class="col-xs-6 col-md-5 detail-field-label" style="text-align:left" >매물특징</span>
											<hr style="margin-top:31px; border-top-width:1px; margin-bottom:8px">
											<p>매물특징 받아오기</p> -->
										</div>
									</div>

									<div class="property-feature">
										<div class="heading3">
											<h3>Features</h3>
										</div>
										<div class="property-feature-content clearfix">
											${sbPropertyDo.features}
										</div>
									</div>

									<div class="property-map">
										<div class="heading3" style="margin-bottom: 15px;">
											<h3>Find this property on the map</h3>
										</div>
										<div class="col-md-9">
											<div id="daumRoughmapContainer1562655863143" class="root_daum_roughmap root_daum_roughmap_landing"></div>
										</div>
									</div>


								</div>
								<!-- Blog Post -->
							</div>
							<!-- Blog POst Sec -->
						</div>
					</div>


				</div>
			</div>
		</div>
	</section>
</div>
<!--  DAUM MAP  -->
<script charset="UTF-8" class="daum_roughmap_loader_script" src="https://ssl.daumcdn.net/dmaps/map_js_init/roughmapLoader.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		new daum.roughmap.Lander({
			"timestamp" : "1562655863143",
			"key" : "uaf4",
			"mapWidth" : "800",
			"mapHeight" : "400"
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


<script>
	$(document).ready(function() {

		$('#image-gallery').lightSlider({
			gallery : true,
			item : 1,
			thumbItem : 9,
			slideMargin : 0,
			speed : 500,
			auto : true,
			loop : true,
			onSliderLoad : function() {
				$('#image-gallery').removeClass('cS-hidden');
			}
		});
	});
</script>

<script type="text/javascript">
	$(document).ready(function() {
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
	});
</script>


<%@ include file="../common/footer.jsp" %>