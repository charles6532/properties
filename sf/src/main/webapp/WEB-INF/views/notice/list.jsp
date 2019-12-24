<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

    <%@include file="../main/head.jsp" %>
    
    <%@include file="../main/top.jsp" %>
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
	        $(location).attr('href', '${pageContext.request.contextPath}/notice/list/'+$("#userId").val()+pg);
	    }

</script>

    <div class="theme-layout">
        
        <div class="inner-head overlap">
            <div style="background: url(${pageContext.request.contextPath}/img/parallax1.jpg) repeat scroll 50% 422.28px transparent;" class="parallax scrolly-invisible no-parallax"></div>
            <!-- PARALLAX BACKGROUND IMAGE -->	
            <div class="container">
                <div class="inner-content">
                    <span><i class="fa fa-bolt"></i></span>
                    <h2>알림내역</h2>
                    <ul>
                        <li><a href="../main/index.jsp" title="">HOME</a></li>
                        <li><a href="list.jsp" title="">알림내역</a></li>
                    </ul>
                </div>
            </div>
        </div><!-- inner Head -->

        <section class="block">
		<div class="container">
			<div class="row">
				<div class="col-md-12">
					<div class="heading4">
						<h2>알림내역</h2>
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
											<tr class="success">
												<td align="center" style="font-weight:bold;">날짜</td>
												<!-- <td align="center" style="font-weight:bold;">제목</td> -->
												<td align="center" style="font-weight:bold;">내용</td>
											</tr>
									<c:forEach var="result" items="${printlist}">
											<tr class="warning">
												<td align="center" width="5%">
													<fmt:parseDate value="${result.updatedAt}" var="updatedAt1" pattern="yyyy-MM-dd"/>
													<fmt:formatDate value="${updatedAt1}" pattern="yyyy-MM-dd"/>
												</td>
												<%-- <td align="center" width="20%">
												${result.subject}
												</td> --%>
												<td align="center" width="35%">${result.content}</td>
											</tr>
									</c:forEach>
										</table>
									</div>
									<input type="hidden" id="userId" value="${sb:getIdStr(sbmemberdo.userId)}/">
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
			</div>
		</div>
	</section>

        <%@include file="../main/bottom.jsp" %>
    