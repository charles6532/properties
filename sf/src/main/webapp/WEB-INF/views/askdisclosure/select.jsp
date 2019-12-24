<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="../main/head.jsp" %>
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
        $(location).attr('href', '${pageContext.request.contextPath}/askdisclosure/select/'+$("#propid").val()+pg);
    }
	
	function insdelcheck( idx, propvalue ){
		var str = "";
		if(document.getElementById('myCheck').checked){
			$.ajax(
				{
					type:"POST",
					url: '${pageContext.request.contextPath}/askdisclosure/addtoPropsId/'+propvalue,
					dataType:"text",
					success:function(datum){
						if (datum !== "abc"){
							alert("저장됨");
							selectform.propsid.value = datum;
								
						} else{
							alert("저장 실패");
							selectform.propsid.value = 0;
						}
					},
					error:function(e){
						alert("bad");
					}			
				}		
			);
		} else{
			$.ajax(
					{
						type:"POST",
						url: '${pageContext.request.contextPath}/askdisclosure/deleteFromPropsId/'+propvalue,
						dataType:"text",
						success:function(datum){
							if (datum !== "abc"){
								alert("삭제됨");
								selectform.propsid.value = datum;
							} else{
								alert("삭제 실패")
								selectform.propsid.value = 0;
							}
						},
						error:function(e){
							alert("bad");
						}			
					}		
			);
		}
	}
	function deleteselect(propvalue){
		$.ajax(
				{
					type:"POST",
					url: '${pageContext.request.contextPath}/askdisclosure/deleteFromPropsId/'+propvalue,
					dataType:"text",
					success:function(datum){
						if (datum === "abc"){
							alert("삭제됨");
							selectform.propsid.value = datum;
						} else{
							alert("완전 삭제 완료");
							selectform.propsid.value = 0;
						}
					},
					error:function(e){
						alert("bad");
					}			
				}		
		);	
	}
	
	function setpropsid(id){
		
		opener.document.askdisdetail.propId.value = id;
		
		self.close();
	}
	
</script>

	<div class="properties-content properties-grid">
		<div class="container ">
			
			<form id="selectform" name="selectform" method="post" class="property-form" role="form" 
	                            		onsubmit="setpropsid(selectform.propid.value)">
			<table class="table table-bordered table-striped table-hover">
				
				<tr>
					<td align="center" style="font-weight:bold;">  </td>
					<td align="center" style="font-weight:bold;">주소</td>
					<td style="font-weight:bold;padding-left:2em;">단지명</td>
					<td style="font-weight:bold;padding-left:2em;">예상가격</td>
				</tr>
				<c:forEach var="printlist" items="${printlist}" varStatus="loop">
				<tr>
					<td align="center" style="font-weight:bold;"><input type="checkbox" id="check" name="check"
					 onclick="insdelcheck(${loop.index},'${propsid}/${sb:getIdStr(printlist.propId)}')"> </td>
					<td align="center" style="font-weight:bold;">${printlist.state} ${printlist.city} ${printlist.legalDistrictNm}</td>
					<td style="font-weight:bold;padding-left:2em;">${printlist.aptName}</td>
					<td style="font-weight:bold;padding-left:2em;">${printlist.prediction}</td>
				</tr>
				</c:forEach>
			</table>
			<input type="hidden" id="propid" name="propid" value="">
			<input type="hidden" id="propsid" name="propsid" value="${propsid}">
			<c:if test="${not empty printlist}">
				<c:set var="totCnt" value="${totCnt}"/>
				<c:set var="rowCntPerPage" value="${rowCntPerPage}"/>
				<c:set var="currentPage" value="${currentPage}"/>
				${"  totCnt : "} ${totCnt}
				${sb:getPagination(totCnt, currentPage, rowCntPerPage, 5)}
			</c:if>
			
			<%-- <div class="submit row" style="text-align:center; clear: both; margin-top: 25px;">
                   <div style="display:inline-block; width:300px;">
                       <input type="submit" class="btn btn-lg flat-btn" id="property_submit" value="매물 선택하기" style="margin:5px">
                       <input type="reset" class="btn btn-lg flat-btn" id="property_cancel" value="취소" style="margin:5px" 
                       onclick="deleteselect('${propsid}/${sb:getIdStr(0)}')">
                   </div>
               </div> --%>
           </form>
		</div>
	</div>