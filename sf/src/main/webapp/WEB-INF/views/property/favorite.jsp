<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

    <%@include file="../main/head.jsp" %>

    <%@include file="../main/top.jsp" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib prefix="sb" uri="/WEB-INF/spring/tld/starbocks.tld" %>
    
    <script type='text/javascript'>
	    $(function() {
	    		    	
	    	$("a").on("click",function(event){
	            event.stopPropagation();
	            if($(this).attr("id") == 'paging') {
	                var toGo = $(this).attr("value");
	                alert(toGo);
	                goPage(toGo);       
	            }
	        });
	    	
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
				
	    function goPage(pg) {
	        $(location).attr('href', '${pageContext.request.contextPath}/property/favorite/'+$("#userId").val()+pg);
	    }
	    
	    function deleteCategoryIndex( idx, propuservalue ){
				var str = "";
				$.ajax(
					{
						type:"POST",
						url: '${pageContext.request.contextPath}/property/deletefromFavorite/'+propuservalue,
						dataType:"text",
						success:function(datum){
							if (datum == 1){
								alert("관심목록에서 삭제되었습니다");
								location.reload();
							} else{
								alert("관심목록에 삭제하는 데 실패하였습니다")
							}
						},
						error:function(e){
							alert("bad");
						}			
					}		
				);
		}
	</script>

    <div class="theme-layout">
        
		<div class="inner-head overlap">
            <div style="background: url(${pageContext.request.contextPath}/img/parallax1.jpg) repeat scroll 50% 422.28px transparent;" class="parallax scrolly-invisible no-parallax"></div><!-- PARALLAX BACKGROUND IMAGE -->	
            <div class="container">
                <div class="inner-content">
                    <span><i class="ti ti-home"></i></span>
                    <h2>관심매물목록 </h2>
                    <ul>
                        <li><a href="../index.jsp" title="">HOME</a></li>
                        <li><a href="favorite.jsp" title="">관심매물목록</a></li>
                    </ul>
                </div>
            </div>
        </div><!-- inner Head -->

        <section class="block">
            <div class="container">
                <div class="row">
                    <div class="col-md-12">	
                        <div class="row">
                            <div class="col-md-9 column">                               
                                <div class="properties-sec">
                                    <div class="properties-list">
                                        <div class="filter-wrapper">    
                                            <ol class="list-option-filter">
                                                <li>
                                                    <div class="option-filter-box">                                                  
                                                        <div class="label-select"> 
                                                            <select class="form-control" name="s_statu">
                                                                <option> All Status </option>
                                                                <option>Open house</option>
                                                                <option>Rent </option>
                                                                <option>Boy</option>
                                                                <option>used</option>  
                                                            </select>
                                                        </div>
                                                    </div>
                                                </li>
                                                <!-- select how many post you want to see perpage -->
                                                <li>
                                                    <div class="option-filter-box">            
                                                        <div class="label-select"> 
                                                            <select class="form-control">
                                                                <option value="">
                                                                    Property/page 
                                                                </option>
                                                                <option value="4">
                                                                    4 properties-/Page 
                                                                </option>
                                                                <option value="8">
                                                                    8 properties-/Page                    
                                                                </option>
                                                                <option value="12">
                                                                    12 properties-/Page                    
                                                                </option>
                                                            </select>    
                                                        </div>
                                                    </div>
                                                </li>
                                                <!--// select how many post you want to see perpage -->
                                                <li class="sort-rates-lastest">
                                                    <div class="option-filter-box">
                                                        <span class="title">SORT BY:</span>
                                                        <a href="" class="sort-icon orderby" data-order="" data-sort="rating_score">
                                                            Rating                </a> / 
                                                        <a href="" class="sort-icon orderby active" data-order="" data-sort="date">
                                                            Latest                </a>
                                                    </div>
                                                </li>
                                                <li class="icon-list-view">
                                                    <div class="option-filter-box">
                                                        <span class="icon-view grid-style active"><i class="fa fa-th"></i></span>
                                                        <span class="icon-view fullwidth-style"><i class="fa fa-th-list"></i></span>
                                                    </div>
                                                </li>
                                            </ol>
                                            <div class="clearfix"></div>
                                        </div>
                                        <div class="properties-content">                                            
                                            <div class="row">
                                            	<c:forEach var="printlist" items="${printlist}" varStatus="loop">
                                                <div class="col-md-4 col-sm-6  col-xs-12">
                                                
                                                    <div class="properties-box">
                                                        <div class="properties-thumb">
                                                            <img src="${pageContext.request.contextPath}/img/demo/property1.jpg" alt="">
                                                            <span class="spn-save"><a onclick="deleteCategoryIndex(${loop.index},'${sb:getIdStr(printlist.propId)}/${sb:getIdStr(sbmemberdo.userId)}')" href="#"><i class="ti ti-heart"></i></a></span>
                                                            
                                                            <ul class="property-info">
                                                                <li> 
                                                                    <i class="fa  fa-retweet"> </i> <span> </span>                                     
                                                                </li>
                                                                <li class="li-rl"></li>
                                                                <li> 
                                                                    <i class="fa  fa-bed"></i><span>  ${printlist.room}   </span>                                 
                                                                </li> 
                                                                <li class="li-rl"></li>
                                                                <li> 
                                                                    <i class="fa  fa-building"> </i> <span>${printlist.floor}   </span>                                
                                                                </li>
                                                            </ul> 
                                                            
                                                            <a class="proeprty-sh-more" href="${pageContext.request.contextPath}/property/detail/${sb:getIdStr(printlist.propId)}"><i class="ti ti-share"> </i></a>

                                                        </div>
                                                        <h3><a href="${pageContext.request.contextPath}/property/detail/${sb:getIdStr(printlist.propId)}" title="">${printlist.aptName}</a></h3>
                                                        <span class="price">&#x20a9;${printlist.price}</span>
                                                        <p>${printlist.propId}</p>
                                                    </div><!-- prop Box -->
                                                
                                                </div>
                                                </c:forEach>
                                                <input type="hidden" id="userId" value="${sb:getIdStr(sbmemberdo.userId)}/">
                                            </div>
                                            <c:if test="${not empty printlist}">
												<c:set var="totCnt" value="${totCnt}"/>
												<c:set var="rowCntPerPage" value="${rowCntPerPage}"/>
												<c:set var="currentPage" value="${currentPage}"/>
												${"totCnt"} ${totCnt}
												${sb:getPagination(totCnt, currentPage, rowCntPerPage, 5)}
											</c:if>
                                        </div>
                                    </div><!-- Blog Post -->
                                </div><!-- Blog POst Sec -->
                            </div>
                            <aside class="col-md-3 column">
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

 