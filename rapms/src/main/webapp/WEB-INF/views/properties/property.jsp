<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../common/top.jsp" %>
<%@ include file="../common/signupbutton.jsp" %>
<%@ include file="../common/modifybutton.jsp" %>
<%@ include file="../properties/completebutton.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sb" uri="/WEB-INF/spring/tld/starbocks.tld"%>
<style>
<!--
p { text-align: center; }
.img-list {
width: 1280px;
margin: 50px auto 0;
overflow: hidden;
}
.img-list li {
float: left;
margin: 10px;
}
.myImg {
border-radius: 5px;
cursor: pointer;
transition: 0.3s;
}
.myImg:hover { opacity: 0.7; }
/* The Modal (background) */
.modal {
display: none;
/* Hidden by default */
position: fixed;
/* Stay in place */
z-index: 1;
/* Sit on top */
padding-top: 100px;
/* Location of the box */
left: 0;
top: 0;
width: 100%;
/* Full width */
height: 100%;
/* Full height */
overflow: auto;
/* Enable scroll if needed */
background-color: rgb(0, 0, 0);
/* Fallback color */
background-color: rgba(0, 0, 0, 0.9);
/* Black w/ opacity */
}
/* Modal Content (image) */
.modal-content {
margin: auto;
display: block;
width: 80%;
max-width: 700px;
padding-top: 0px;
margin-top: 49px;
}
-->
</style>

<div class="theme-layout">
	<div class="inner-head overlap">
		<div style="background: url(${pageContext.request.contextPath}/img/parallax1.jpg) repeat scroll 50% 422.28px transparent;"
			class="parallax scrolly-invisible no-parallax"></div>
		<!-- PARALLAX BACKGROUND IMAGE -->
		<div class="container">
			<div class="inner-content">
				<span><i class="ti ti-home"></i></span>
				<h2>매물 상세보기</h2>
				<ul>
					<li><a href="${pageContext.request.contextPath}/prop/list/1" title="">PROPERTIES</a></li>
					<li><a href="${pageContext.request.contextPath}/prop/list/${page}/${sb:getIdStr(sbPropertyDo.propId)}" title="">매물 상세보기</a></li>
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
									<div class="img-list">
										<c:forEach var="photo" items="${internalPhotos}" varStatus="loop">
											<a href="#myModal" onclick="modalopen('${pageContext.request.contextPath}${sb:getPhotoUrl( photo.photoId )}')">
												<img class="myImg" src="${pageContext.request.contextPath}${sb:getThumbnailPhotoUrl( photo.photoId )}" >
											</a>
										</c:forEach>
									</div>
									<!-- Modal -->
									<div id="myModal" class="modal">
										<span class="close-popup" style="top:70; top:150px;"><i class="fa fa-close"></i></span>
										<img class="modal-content" width="400" height="500">
									</div>

									<h1>매물명 : ${propdetailvo.title}</h1>

									<div class="row">
										<div class="col-md-6">
											<div class="property-detail">

												<div class="detail-field row">

													<span class="col-xs-6 col-md-5 detail-field-label">소재지</span>
													<span class="col-xs-6 col-md-7 detail-field-value">${propdetailvo.state} &nbsp; ${propdetailvo.city} &nbsp; ${propdetailvo.legalDistrictNm}</span> 

													<span class="col-xs-6 col-md-5 detail-field-label">단지명</span>
													<span class="col-xs-6 col-md-7 detail-field-value">${propdetailvo.bldgNm}</span> 

													<span class="col-xs-6 col-md-5 detail-field-label">층수</span>
													<span class="col-xs-6 col-md-7 detail-field-value">${propdetailvo.floor}</span> 

													<span class="col-xs-6 col-md-5 detail-field-label">매매가격</span>
													<span class="col-xs-6 col-md-7 detail-field-value"><fmt:formatNumber value="${propdetailvo.price}" pattern="#,###"/>원</span> 
													
													<span class="col-xs-6 col-md-5 detail-field-label">면적</span>
													<span class="col-xs-6 col-md-7 detail-field-value">${propdetailvo.areaSpace} ㎡</span>
													
													<span class="col-xs-6 col-md-5 detail-field-label">방 개수</span>
													<span class="col-xs-6 col-md-7 detail-field-value">${propdetailvo.room} 개</span>

													<span class="col-xs-6 col-md-5 detail-field-label">욕실 개수</span>
													<span class="col-xs-6 col-md-7 detail-field-value">${propdetailvo.bathroomNo} 개</span>
													
													<span class="col-xs-6 col-md-5 detail-field-label">입주가능일</span>
													<span class="col-xs-6 col-md-7 detail-field-value">
													<fmt:parseDate value="${propdetailvo.moveAt}" var="moveAt1" pattern="yyyy-MM-dd"/>
													<fmt:formatDate value="${moveAt1}" pattern="yyyy-MM-dd"/> 이후 </span>

													<span class="col-xs-6 col-md-5 detail-field-label">방향</span>
													<span class="col-xs-6 col-md-7 detail-field-value">${propdetailvo.aspect}향</span>
													
													<span class="col-xs-6 col-md-5 detail-field-label">현관구조</span>
													<span class="col-xs-6 col-md-7 detail-field-value">${propdetailvo.entrance}</span>

													<span class="col-xs-6 col-md-5 detail-field-label">준공년도</span> 
													<span class="col-xs-6 col-md-7 detail-field-value">${propdetailvo.builtYear} 년</span>
													
													<span class="col-xs-6 col-md-5 detail-field-label">주차대수</span>
													<span class="col-xs-6 col-md-7 detail-field-value">${propdetailvo.parking} 대</span>
													
													<span class="col-xs-6 col-md-5 detail-field-label">단지 세대수</span>
													<span class="col-xs-6 col-md-7 detail-field-value">${propdetailvo.numApartment}</span>

													<span class="col-xs-6 col-md-5 detail-field-label">수리 여부</span>
													<span class="col-xs-6 col-md-7 detail-field-value">${propdetailvo.repaired}</span>
													
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
											${propdetailvo.features}
										</div>
									</div>

									<div class="property-map">
										<div class="heading3" style="margin-bottom: 15px;">
											<h3>Find this property on the map</h3>
										</div>
										<div class="col-md-9">
											<div id="map_canvas" style="width:800; height:600;"></div>
										</div>
									</div>

								</div>
								<!-- Blog Post -->
							</div>
							<!-- Blog POst Sec -->
						</div>
					</div>
					sbAgentDo : ${sbAgentDo.userId}<br>
					propdetailvo : ${propdetailvo.userId}
				<c:if test="${sbAgentDo.userId == propdetailvo.userId}">
					<div class="submit row" style="text-align: center; clear: both; margin-top: 25px;">
						<div style="display: inline-block; width: 400px;">
							<a href="${pageContext.request.contextPath}/prop/modify/${sb:getIdStr(propdetailvo.propId)}">
							<input type="submit" class="btn btn-lg flat-btn" id="property_modify" name="property_modify" value="매물 수정" style="margin: 5px" ></a>
							<c:if test="${propdetailvo.statusCd != 'PR11'}">
							<div class="popup-complete" style="margin-left: 0px; padding-left:12px;">
								<input type="button" class="btn btn-lg flat-btn" id="complete" value="계약 완료" style="margin: 5px">
							</div>
							<div id="popup-complete" class="modal fade"><!-- modal -->
								<!-- 계약완료 비밀번호 확인 -->
							</div><!-- modal -->
							</c:if>
							
						</div>
					</div>
				</c:if>

				</div>
			</div>
		</div>
	</section>
</div>

 <!-- DAUM MAP -->  
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
	var modal = $("#myModal"); //팝업 div

	function modalopen(url) { //클릭 이벤트
		var _index = $(this).parent().index(); //해당 index 찾기
		var _indexTar = $(".img-list").eq(_index); //해당 인덱스
		$(".img-list").removeClass("open"); //"open" 클래스 삭제
		_indexTar.addClass("open"); //"open" 클래스 추가
		modal.find(".modal-content").attr("src", url ); //해당 팝업 안에 이미지 넣기
		modal.show(); //팝업 열기
	}
 
	$("span").on("click", 
		function () {
			modal.hide(); //팝업 닫기
		}
	)


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