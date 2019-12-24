<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

    <%@include file="../main/head.jsp" %>
    
    <%@include file="../main/top.jsp" %>
    
    <%@ taglib prefix="sb" uri="/WEB-INF/spring/tld/starbocks.tld" %>
    
    <script type='text/javascript'>
	$(document).ready(
		function(){
			$("a").on("click", function(event){
				event.stopPropagation();
				if($(this).attr("id") == 'paging'){
					var toGo = $(this).attr("value");
					goPage(toGo);
				}
			});
			
		}		
	);
	function goPage(pg) {
        $(location).attr('href', '${pageContext.request.contextPath}/property/mine/'+$("#userId").val()+'/'+pg);
    }
	
</script>
    <div class="theme-layout">
            
        <div class="inner-head overlap">
            <div style="background: url(${pageContext.request.contextPath}/img/parallax1.jpg) repeat scroll 50% 422.28px transparent;" class="parallax scrolly-invisible no-parallax"></div><!-- PARALLAX BACKGROUND IMAGE -->	
            <div class="container">
                <div class="inner-content">
                    <span><i class="ti ti-home"></i></span>
                    <h2>내 매물 보기</h2>
                    <ul>
                        <li><a href="#" title="">HOME</a></li>
                        <li><a href="#" title="">내 매물 보기</a></li>
                    </ul>
                </div>
            </div>
        </div>

       <section class="block">
				<div class="container">
					<div class="row">
						<div class="col-md-12">
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
													<%-- <div style="text-align:right">
														<a href="${pageContext.request.contextPath}/inquiry/write">${"문의하기"}</a>
													</div> --%>
													<tr>
														<td align="center" style="font-weight: bold;">번호</td>
														<td align="center" style="font-weight: bold;">매물요청제목</td>
														<td align="center" style="font-weight: bold;">주소</td>
														<td style="font-weight: bold; padding-left: 2em;">가격</td>
														<td style="font-weight: bold; padding-left: 2em;">문의등록날짜</td>
													</tr>
													<!-- 문의가있을 경우 -->
													<c:set var="replynum" value="${0}" />
													<c:forEach var="printlist" items="${printlist}">
															
													<tr class="active">
														<td align="center">
															<c:if test="${printlist.cpId == printlist.thrCpId}">
																<c:set var="rownum" value="${printlist.ridx}" />
																<c:set var="replynum" value="${0}" /> 
																${rownum}
															</c:if> 
															<c:if test="${printlist.cpId != printlist.thrCpId}">
																${rownum}${"에 대한 답글"} ${replynum=replynum+1}
															</c:if>
														</td>
														<td align="center">
															<a href="${pageContext.request.contextPath}/property/askforDetail/${sb:getIdStr(printlist.cpId)}"> ${printlist.conTitle} </a>
														</td>
														<td align="center">
															${printlist.address}
														</td>
														<td style="padding-left: 2em;">
															${printlist.price}
														</td>
														<c:set var="string1" value="${printlist.addedAt}" />
														<td style="padding-left: 2em;">${fn:substringBefore(string1, ".")}</td>
													</tr>
													</c:forEach>		
														
												</table>
												<c:if test="${not empty printlist}">
													<c:set var="totCnt" value="${totCnt}"/>
													<c:set var="rowCntPerPage" value="${rowCntPerPage}"/>
													<c:set var="currentPage" value="${currentPage}"/>
													${"totCnt"} ${totCnt}
													${sb:getPagination(totCnt, currentPage, rowCntPerPage, 5)}
												</c:if>
											</div>
										<input type="hidden" id="userId" value="${sb:getIdStr(sbmemberdo.userId)}" />
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

        <%@include file="../main/bottom.jsp" %>
