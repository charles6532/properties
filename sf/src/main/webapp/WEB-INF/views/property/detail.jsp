<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

    <%@include file="../main/head.jsp" %>
    
    <%@include file="../main/top.jsp" %>
    
    <%@ taglib prefix="sb" uri="/WEB-INF/spring/tld/starbocks.tld"%>
<style>
 /* Style the Image Used to Trigger the Modal */
#myImg {
  border-radius: 5px;
  cursor: pointer;
  transition: 0.3s;
}

#myImg:hover {opacity: 0.7;}

/* The Modal (background) */
.modal {
  display: none; /* Hidden by default */
  position: fixed; /* Stay in place */
  z-index: 1; /* Sit on top */
  padding-top: 100px; /* Location of the box */
  left: 0;
  top: 0;
  width: 100%; /* Full width */
  height: 100%; /* Full height */
  overflow: auto; /* Enable scroll if needed */
  background-color: rgb(0,0,0); /* Fallback color */
  background-color: rgba(0,0,0,0.9); /* Black w/ opacity */
}

/* Modal Content (Image) */
.modal-content {
  /* margin: auto; */
  display: block;
  width: 80%;
  max-width: 600px;
  margin-top : 11em;
  margin-left : 30em;
}

/* Caption of Modal Image (Image Text) - Same Width as the Image */
#caption {
  margin: auto;
  display: block;
  width: 80%;
  max-width: 700px;
  text-align: center;
  color: #ccc;
  padding: 10px 0;
  height: 150px;
}

/* Add Animation - Zoom in the Modal */
.modal-content, #caption {
  animation-name: zoom;
  animation-duration: 0.6s;
}

@keyframes zoom {
  from {transform:scale(0)}
  to {transform:scale(1)}
}

/* The Close Button */
.close {
  position: absolute;
  top: 15px;
  right: 35px;
  color: #f1f1f1;
  font-size: 40px;
  font-weight: bold;
  transition: 0.3s;
}

.close:hover,
.close:focus {
  color: #bbb;
  text-decoration: none;
  cursor: pointer;
}

/* 100% Image Width on Smaller Screens */
@media only screen and (max-width: 700px){
  .modal-content {
  	
    width: 100%;
  }
} 
</style>

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
	
	function modalopen(src){
		//Get the modal
		var modal = document.getElementById("myModal");
		// Get the image and insert it inside the modal - use its "alt" text as a caption
		var img = document.getElementById("myImg");
		var modalImg = document.getElementById("img01");
		var captionText = document.getElementById("caption");
		  modal.style.display = "block";
		  modalImg.src = src;
		  /* captionText.innerHTML = this.alt; */
		}
	function modalclose(){
		var modal = document.getElementById("myModal");
		// Get the <span> element that closes the modal
		var span = document.getElementsByClassName("close")[0];
		modal.style.display = "none";
	}
</script>
    <script type='text/javascript'>
	    $(function() {
	        
	    	$("select").on("change",function(event){
	        	let value = event.target.value;
	        	var url = '${pageContext.request.contextPath}/property/list/';
	        	if($(this).attr("id") =='state'){
	        		url += $(this).attr("id")+'/' + $("select[name=state] option:selected").val();
	        		$("select[name=city]").append("<option>Loading data ....</option>");
	        		$.ajax(
		                    {
		                        type: "POST",
		                        url: url,
		                        data: { city : value },
		                        dataType: "json",
		                        success: function(data){
		                        	$("select[name=city] option").remove();
		                            let gun = "";
		                            gun = "<option>시/군/구</option>";
		                            $("select[name=city]").append(gun);
		                            for(let i=0; i<data.length; i++){
		                                gun = "<option>";
		                                gun += data[i].city;
		                                gun += "</option>";
			                            $("select[name=city]").append(gun);
		                            }
		                        },
		                        error: function(){
		                            alert("bad");
		                        }
		                    }
		            );
	        	}

	        	if($(this).attr("id") =='city'){
	        		url += $(this).attr("id")+'/' + $("select[name=city] option:selected").val();
	        		$("select[name=legalDistrictNm]").append("<option>Loading data ....</option>");
	        		$.ajax(
		                    {
		                        type: "POST",
		                        url: url,
		                        data: { legalDistrictNm : value },
		                        dataType: "json",
		                        success: function(data){
		                        	$("select[name=legalDistrictNm] option").remove();
		                            let gun = "";
		                            gun = "<option>읍/면/동</option>";
		                            $("select[name=legalDistrictNm]").append(gun);
		                            for(let i=0; i<data.length; i++){
		                                gun = "<option>";
		                                gun += data[i].legalDistrictNm;
		                                gun += "</option>";
			                            $("select[name=legalDistrictNm]").append(gun);
		                            }
		                        },
		                        error: function(){
		                            alert("bad");
		                        }
		                    }
		            );
	        	}
	        	
	        	if($(this).attr("id") =='legalDistrictNm'){
	        		url += $(this).attr("id")+'/' + $("select[name=state] option:selected").val()+" " +$("select[name=city] option:selected").val()+" " + $("select[name=legalDistrictNm] option:selected").val();
	        		$("select[name=bldgNm]").append("<option>Loading data ....</option>");
	        		$.ajax(
		                    {
		                        type: "POST",
		                        url: url,
		                        data: { bldgNm : value },
		                        dataType: "json",
		                        success: function(data){
		                        	$("select[name=bldgNm] option").remove();
		                            let gun = "";
		                            gun = "<option>단지이름</option>";
		                            $("select[name=bldgNm]").append(gun);
		                            for(let i=0; i<data.length; i++){
		                                gun = "<option>";
		                                gun += data[i].bldgNm;
		                                gun += "</option>";
			                            $("select[name=bldgNm]").append(gun);
		                            }
		                        },
		                        error: function(){
		                            alert("bad");
		                        }
		                    }
		            );
	        	}
	        	
	        	
	        });
	    });	        
	    function putZero(){		//added by sf on October 12
	    	
	    	inquirywrite.propId.value = Number(inquirywrite.propId.value);
	    	
			if( ! $("#nickname").val()) {
				alert( "nickname" );
				inquirywrite.nickname.focus();
				return false;
			} else if( ! $("#email").val()) {
				alert( "email" );
				inquirywrite.email.focus();
				return false;
			} 
		}
				
			
	</script>
    <div class="theme-layout">
    
        <div class="inner-head overlap">
            <div style="background: url(${pageContext.request.contextPath}/img/parallax1.jpg) repeat scroll 50% 422.28px transparent;" class="parallax scrolly-invisible no-parallax"></div><!-- PARALLAX BACKGROUND IMAGE -->	
            <div class="container">
                <div class="inner-content">
                    <span><i class="ti ti-home"></i></span>
                    <h2>매물 상세 보기</h2>
                    <ul>
                        <li><a href="../index.jsp" title="">HOME</a></li>
                        <li><a href="detail.jsp" title="">매물 상세 보기</a></li>
                    </ul>
                </div>
            </div>
        </div><!-- inner Head -->
        
	<section class="block">
		<div class="container">
			<div class="row">
				<div class="col-md-12">
					<div class="heading4" >
						<h2>매물 상세보기</h2>
					</div>
							<div class="row">
						<div class="col-md-8 column">
							<div class="single-post-sec">
								<div class="blog-post property-post">
									
									<h1>매물사진</h1>
									<div class="img-list">
										<c:forEach var="photo" items="${internalPhotos}" varStatus="loop">
											
											<img id="myImg" src="${pageContext.request.contextPath}${sb:getThumbnailPhotoUrl( photo.photoId )}"
												onclick="modalopen('${pageContext.request.contextPath}${sb:getPhotoUrl( photo.photoId )}')">
											<!-- The Modal -->
											<div id="myModal" class="modal">
												<!-- The Close Button -->
												<span class="close">&times;</span>
												<!-- Modal Content (The Image) -->
												<img class="modal-content" id="img01" onclick="modalclose()">
												<!-- Modal Caption (Image Text) -->
												<div id="caption"></div>
											</div>
										</c:forEach>
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
                                        <div class="send-email-to-agent">
                                            <div class="comment-form">
                                                <div class="heading3">
                                                    <h3>담당 중개사에게 문의하기</h3> 
                                                </div>
                                                <form id="inquirywrite" name="inquirywrite" method="post" class="property-form" role="form" 
	                            					action="${pageContext.request.contextPath}/inquiry/writePro" onsubmit="return putZero()">
                                                    <div class="row">
                                                        <div class="col-md-12">
                                                            <label>
                                                                <i class="fa fa-user"></i>
                                                                <input type="text" placeholder="이름" id="nickname" name="nickname"/>
                                                            </label>
                                                        </div>
                                                        <c:if test="${empty sbmemberdo}">
                                                        <div class="col-md-12">
                                                            <label>
                                                                <i class="fa fa-at"></i>
                                                                <input type="text" placeholder="이메일" id="email" name="email"/>
                                                            </label>
                                                        </div>
                                                        </c:if>
                                                        <c:if test="${not empty sbmemberdo}">
                                                        	<input type="hidden" id="email" name="email" value="${sbmemberdo.email}">
                                                        	<input type="hidden" id="addedBy" name="addedBy" value="${sbmemberdo.userId}">
                                                        </c:if>
                                                        <div class="col-md-12">
                                                            <label>
                                                                <i class="fa fa-phone"></i>
                                                                <input type="text" placeholder="전화번호" name="tel" />
                                                            </label>
                                                        </div>
                                                        <div class="col-md-12">
                                                            <label>
                                                                <i class="fa fa-pencil"></i>
                                                                <input type="text" placeholder="문의제목" name="subject"></textarea>
                                                            </label>
                                                        </div>
                                                        <div class="col-md-12">
                                                            <label>
                                                                <i class="fa fa-pencil"></i>
                                                                <textarea placeholder="문의내용" name="content"></textarea>
                                                            </label>
                                                        </div>
                                                        <%-- <input type="hidden" id="propId" name="propId" value="${propertydo.propId}"> --%>
                                                        <input type="hidden" id="propId" name="propId" value="${propdetailvo.propId}">	 <!-- added by sf on October 12 -->
														<div class="submit row" style="text-align:center; clear: both; margin-top: 25px;">
						                                    <div style="display:inline-block; width:300px;">
						                                        <input type="submit" class="btn btn-lg flat-btn" id="property_submit" value="문의하기" style="margin:5px">
						                                        <input type="reset" class="btn btn-lg flat-btn" id="property_cancel" value="취소" style="margin:5px">
						                                    </div>
						                                </div>
                                                    </div>
                                                </form>
                                            </div>
                                        </div>
                                    </div><!-- Blog Post -->
                                </div><!-- Blog POst Sec -->
                            </div>
                            
                            <aside class="col-md-4 column">

                                <div class="search_widget widget">
                                    <div class="heading2">
                                        <h3>매물찾기</h3>
                                    </div>
									<div class="search-form"> 
                                        <form id="propertySearch" name="propertySearch" class="form-inline" method="post" 
                                        	action="${pageContext.request.contextPath}/property/searchPro/${sb:getIdStr(1)}">   
                                           <div class="search-form-content">
                                                <div class="search-form-field">  
                                                    <div class="form-group col-md-12">
                                                        <div class="label-select">
                                                            <select id="state" name="state" class="form-control">
		                                                        <option value="">지역선택</option>
		                                                        <option value="서울">서울</option>
		                                                        <option value="경기">경기</option>
		                                                    </select>
                                                        </div>
                                                    </div>
                                                    <div class="form-group col-md-12">
                                                        <div class="label-select">
                                                            <select name="city" id="city" class="form-control">
																	<c:forEach var="slido" items="${slido}" varStatus="i">
																	<option value="${slido.city}">
																	${slido.city}
																	</option> 
																	</c:forEach>
																</select>
                                                        </div>
                                                    </div>
                                                    <div class="form-group col-md-12">
                                                        <div class="label-select">
                                                            <select name="legalDistrictNm" id="legalDistrictNm" class="form-control">
																	<c:forEach var="slido" items="${slido}" varStatus="i">
																	<option value="${slido.legalDistrictNm}">
																	${slido.legalDistrictNm}
																	</option> 
																	</c:forEach>
																</select>
                                                        </div>
                                                    </div>
                                                    <div class="form-group col-md-12">
                                                        <div class="label-select">
                                                            <select name="bldgNm" id="bldgNm" class="form-control">
																	<c:forEach var="slido" items="${slido}" varStatus="i">
																	<option value="${slido.bldgNm}">
																	${slido.bldgNm}
																	</option> 
																	</c:forEach>
																</select>
                                                        </div>
                                                    </div>
                                                    
                                                    <div class="form-group col-md-12">
                                                        <div class="label-select">
                                                            <select class="form-control" name="roomCount">
                                                                <option value=0>방갯수</option>
                                                                <option value=1>1</option>
                                                                <option value=2>2</option>
                                                                <option value=3>3</option>
                                                                <option value=4>4</option>
                                                                <option value=5>5이상</option>
                                                            </select>
                                                        </div>
                                                    </div>
                                                    
                                                    <div class="form-group col-md-12">
                                                        <div class="label-select">
                                                            <select class="form-control" name="floorDesc">
                                                                <option value="">층수</option>
                                                                <option value="low">저층</option>
                                                                <option value="medium">중간층</option>
                                                                <option value="high">고층</option>
                                                            </select>
                                                        </div>
                                                    </div>
                                                    
                                                    <div class="form-group col-md-12">
                                                        <span class="gprice-label">Price</span>
                                                        <input type="text" class="span2" value="0,5000000000" data-slider-min="0" 
                                                               data-slider-max="5000000000" data-slider-step="2000" 
                                                               data-slider-value="[0,3000000000]" id="price-range" name="priceRange">
                                                    </div>
                                                                                               
                                                </div> 
                                            </div>
                                            <div class="search-form-submit">
                                				<button type="submit" class="btn-search">Search</button>
                                            </div>
                                        </form>
                                    </div><!-- Services Sec -->
                                </div><!-- Category Widget -->
                            </aside>
                        </div>

                        
                    </div>
                </div>
            </div>
        </section>

        <%@include file="../main/bottom.jsp" %>
