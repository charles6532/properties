<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

    <%@include file="../main/head.jsp" %>
    
    <%@include file="../main/top.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
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
        $(location).attr('href', '${pageContext.request.contextPath}/inquiry/list/'+$("#userId").val()+pg);
    }
	
</script>


    <div class="theme-layout">
        
        <div class="inner-head overlap">
            <div style="background: url(${pageContext.request.contextPath}/img/parallax1.jpg) repeat scroll 50% 422.28px transparent;" class="parallax scrolly-invisible no-parallax"></div>
            <!-- PARALLAX BACKGROUND IMAGE -->	
            <div class="container">
                <div class="inner-content">
                    <span><i class="fa fa-bolt"></i></span>
                    <h2>문의내역</h2>
                    <ul>
                        <li><a href="../index.jsp" title="">HOME</a></li>
                        <li><a href="list.jsp" title="">문의내역</a></li>
                    </ul>
                </div>
            </div>
        </div><!-- inner Head -->

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
														<td align="center" style="font-weight:bold;">번호</th>
														<td align="center" style="font-weight:bold;">문의자</th>
														<td style="font-weight:bold;padding-left:2em;">문의제목</th>
														<td style="font-weight:bold;padding-left:2em;">문의등록날짜</th>
													</tr>
													
													<!-- 문의 내역이 없을떄  -->
													<c:if test="${totalCnt  == 0}">
														<tr>
															<td colspan="4" align="center">문의 내역이 없습니다</td>
														</tr>
													</c:if>
													<!-- 문의가있을 경우 -->
													<c:if test="${totalCnt != 0}">
													<c:set var="replynum" value="${0}"/>
														<c:forEach var="printlist" items="${printlist}">
															<c:if test="${printlist.id == printlist.thrId}">
																<c:set var="inqnum" value="1"/>
															</c:if>
															<c:if test="${printlist.id != printlist.thrId}">
																<c:set var="inqnum" value="0"/>
															</c:if>
															
															<tr class="active">
																<td align="center" width="20%">
																
																	<c:if test="${inqnum>0}">
																		${printlist.ridx}
																		<c:set var="rownum" value="${printlist.ridx}"/>
																		<c:set var="replynum" value="${0}"/>
																	</c:if>
																	
																	<c:if test="${inqnum == 0}">
																		
																		<c:if test="${printlist.id != printlist.thrId}">
																			
																			<c:if test="${printlist.pId == printlist.thrId}">
																				${rownum}${"에 대한 답글"} ${replynum=replynum+1} 
																				<c:set var="rereplynum" value="${0}"/>
																			</c:if>
																			<c:if test="${printlist.pId != printlist.thrId}">
																				${"답글 "}${replynum}${" 에 대한 재답글"} ${rereplynum=rereplynum+1}
																			</c:if>
																		</c:if>
																		
																	</c:if>
																</td>
																<td align="center" width="15%">${printlist.nickname}</td>
																<td style="padding-left:2em;"><a href="${pageContext.request.contextPath}/inquiry/detail/${sb:getIdStr(printlist.id)}"> ${printlist.subject} </a></td>
																<c:set var="string1" value="${printlist.addedAt}"/>
																<td style="padding-left:2em;">${fn:substringBefore(string1, ".")}</td>
															</tr>
															
														</c:forEach>
													</c:if>
												</table>
												
												<input type="hidden" id="userId" value="${sb:getIdStr(sbmemberdo.userId)}/">
												<c:if test="${not empty printlist}">
													<c:set var="totCnt" value="${totCnt}"/>
													<c:set var="rowCntPerPage" value="${rowCntPerPage}"/>
													<c:set var="currentPage" value="${currentPage}"/>
													${"  totCnt : "} ${totCnt}
													${sb:getPagination(totCnt, currentPage, rowCntPerPage, 5)}
												</c:if>
											</div>
										
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
    