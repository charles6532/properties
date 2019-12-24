<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../common/top.jsp" %>
<%@ include file="../common/signupbutton.jsp" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<script type="text/javascript">
		$(function() {
	    		    	
	    	$("a").on("click",function(event){
	            event.stopPropagation();
	            if($(this).attr("id") == 'paging') {
	                var toGo = $(this).attr("value");
	                alert(toGo);
	                goPage(toGo);       
	            }
	        });
 		});		
	    function goPage(pg) {
	        $(location).attr('href', '${pageContext.request.contextPath}/sales/list/'+$("#userId").val()+pg);
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
				<h2>스타 사용내역</h2>
				<ul>
					<li><a href="${pageContext.request.contextPath}/sales" title="">SALES STATEMENT</a></li>
					<li><a href="${pageContext.request.contextPath}/sales/list/1" title="">스타 사용내역</a></li>
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
						<h2>스타 사용내역</h2>
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
								<!-- for each 시작!  -->
									<div class="container ">
										<table class="table table-bordered table-striped table-hover">
											<tr class="danger">
												<td align="center" style="font-weight:bold;">날짜</td>
												<td align="center" style="font-weight:bold;">사용/충전</td>
												<td align="center" style="font-weight:bold;">금액</td>
											</tr>
									<c:forEach var="result" items="${searchlist}">
										<c:if test="${result.txCd=='ST00'}">	
											<tr class="info">
												<td align="center" width="20%">
													<fmt:parseDate value="${result.updatedAt}" var="updatedAt1" pattern="yyyy-MM-dd"/>
													<fmt:formatDate value="${updatedAt1}" pattern="yyyy-MM-dd"/>
												</td>
												<td align="center" width="20%">
												<c:if test="${result.txCd=='ST00'}">
													${"충전"}
												</c:if>
												<c:if test="${result.txCd=='ST11'}">
													${"사용"}
												</c:if>
												</td>
												<td align="center" width="20%"><fmt:formatNumber value="${result.amount}" groupingUsed="true"/>원</td>
											</tr>
										</c:if>
										<c:if test="${result.txCd=='ST11'}">	
											<tr class="info">
												<td align="center" width="20%">
													<fmt:parseDate value="${result.updatedAt}" var="updatedAt1" pattern="yyyy-MM-dd"/>
													<fmt:formatDate value="${updatedAt1}" pattern="yyyy-MM-dd"/>
												</td>
												<td align="center" width="20%">
												<c:if test="${result.txCd=='ST00'}">
												${"충전"}
												</c:if>
												<c:if test="${result.txCd=='ST11'}">
												${"사용"}
												</c:if>
												</td>
												<td align="center" width="20%"><fmt:formatNumber value="${result.amount}" groupingUsed="true"/>원</td>
											</tr>
										</c:if>
									</c:forEach>
										</table>
									</div>
									${"현재 잔액 : "} <fmt:formatNumber value="${balance}" groupingUsed="true"/>원 <br>
									<!-- for each 끝!! -->
									<input type="hidden" id="userId" value="${sb:getIdStr(sbAgentDo.userId)}/">
									<c:if test="${not empty printlist}">
										<c:set var="totCnt" value="${totCnt}" />
										<c:set var="rowCntPerPage" value="${rowCntPerPage}" />
										<c:set var="currentPage" value="${currentPage}" />
										${"  totCnt : "} ${totCnt}
										${sb:getPagination(totCnt, currentPage, rowCntPerPage, 5)}
									</c:if>

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