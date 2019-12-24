<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="../common/head.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<%@ taglib prefix="sb" uri="/WEB-INF/spring/tld/starbocks.tld" %>
<script type='text/javascript'>
	$(document).ready(function(){
			$("a").on("click", function(event){
				event.stopPropagation();
				if($(this).attr("id") == 'paging'){
					var toGo = $(this).attr("value");
					goPage(toGo);
				}
			});

			var k;
			var dupcheck = 0;
			for (k=0; k<rapmsselectform.myCheck.length; k++){
				if (rapmsselectform.myCheck[k].checked != false ) dupcheck += 0;
			}
			console.log("dupcheck : "+ dupcheck);
			if((!rapmsselectform.selectedProps.value && dupcheck > 0)||(!!rapmsselectform.selectedProps.value && dupcheck === 0)) {
				rapmsselectform.selectedProps.value = null; 
				for (i=0; i<rapmsselectform.myCheck.length; i++){
					rapmsselectform.myCheck[i].checked = false;
				}
				opener.document.inquiryreply.propsId.value = "";
			}
			
			var checkboxes = document.getElementsByName('myCheck');
			var tempstring = String("");
			for (var i=0; i<checkboxes.length;i++){
				if (checkboxes[i].checked){
					 tempstring += checkboxes[i].id.split("_")[1]+" ";
				}
			}
			
			rapmsselectform.selectedProps.value = tempstring.trim();
			
			
	});
	function goPage(pg) {
        $(location).attr('href', '${pageContext.request.contextPath}/prop/select/'+$("#consultidstr").val()+'/'+$("#selectedProps").val()+'/'+pg);
    }
	
	function indexdelcheck( idx, propvalue ){
		var checkboxes = document.getElementsByName('myCheck');
		var str = "";
		var selectedtemp = propvalue.trim().split("/")[1];
		console.log("selectedtemp : "+selectedtemp);
		var selectedprops = selectedtemp.split(" ");
		console.log("selectedprops : "+selectedprops);
		var i;
		var dup = 0;
		for (i = 0;i<selectedprops.length;i++) {
			var j;
			for (j=i;j<selectedprops.length-1;j++ ){
				if( selectedprops[i] === selectedprops[j+1]){
					dup += 1;					
				}
			}
			
		}
		console.log("dup : "+ dup);
		if (checkboxes[idx].checked){
			if(dup === 0){
				$.ajax(
					{
						type:"POST",
						url: '${pageContext.request.contextPath}/prop/addtoPropsId/'+propvalue,
						dataType:"text",
						success:function(datum){
							if (datum !== "abc"){
								alert("저장됨");
								rapmsselectform.selectedProps.value = datum;
									
							} else{
								alert("저장 실패");
								rapmsselectform.selectedProps.value = null;
							}
						},
						error:function(e){
							alert("bad");
						}			
					}		
				);
			}
		} else{
			$.ajax(
					{
						type:"POST",
						url: '${pageContext.request.contextPath}/prop/deleteFromPropsId/'+propvalue,
						dataType:"text",
						success:function(datum){
							if (datum !== "abc"){
								alert("삭제됨");
								rapmsselectform.selectedProps.value = datum;
							} else{
								alert("삭제 실패")
								rapmsselectform.selectedProps.value = null;
								
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
					url: '${pageContext.request.contextPath}/prop/deleteFromPropsId/'+propvalue,
					dataType:"text",
					success:function(datum){
						if (datum === "abc"){
							alert("삭제됨");
							rapmsselectform.selectedProps.value = datum;
						} else{
							alert("완전 삭제 완료");
							rapmsselectform.selectedProps.value = null;
							for (i=0; i<rapmsselectform.myCheck.length; i++){
								rapmsselectform.myCheck[i].checked = false;
							}
						}
					},
					error:function(e){
						alert("bad");
					}			
				}		
		);	
	}
	
	function setpropsid(id){
		var i;
		var count;
		var address;
		
		if ( !!rapmsselectform["print"].value ){
			address = String(rapmsselectform["print"].value);
		} else {
			for (i=0; i<rapmsselectform.myCheck.length; i++){
				if(rapmsselectform.myCheck[i].checked == true){
					address = String(rapmsselectform["print"][i].value);
					break;
				}
			}
		}
		
		var tmparray = id.split(" ");
		
		for (i = 0;i<tmparray.length;i++) {
    		for( j=i+1;j<tmparray.length;j++){
    			if(tmparray[i]===tmparray[j]){
    				tmparray[i] = "";
    			}
    		}
    	}
    	argumentProps = String("");
    	for (i = 0;i<tmparray.length;i++) {
    		if(tmparray[i]!=="") argumentProps += tmparray[i]+" ";
    	}
    	rapmsselectform.selectedProps.value = argumentProps.trim();
				
		count = String(Number(id.trim().split(" ").length)-1);
		opener.document.inquiryreply.propsId.value = id;
		
		if (count !== "0")
			opener.document.inquiryreply.addresses.value = address + " 외 " + count + "건";		// added by sf October 10
		else if( !id )
			opener.document.inquiryreply.addresses.value = "";
		else
			opener.document.inquiryreply.addresses.value = address;		// added by sf October 10
		
		
		self.close();
	}
	
</script>

	<div class="properties-content properties-grid">
		<div class="container ">
			
			<form id="rapmsselectform" name="rapmsselectform" method="post" class="property-form" role="form" 
	                            		onsubmit="setpropsid(rapmsselectform.selectedProps.value)">
			<table class="table table-bordered table-striped table-hover">
				
				<tr>
					<td align="center" style="font-weight:bold;">  </td>
					<td align="center" style="font-weight:bold;">주소</td>
					<td style="font-weight:bold;padding-left:2em;">단지명</td>
					<td style="font-weight:bold;padding-left:2em;">매물등록날짜</td>
				</tr>
				
				<c:forEach var="printlist" items="${printlist}" varStatus="loop">
				
				<tr>
					<td align="center" style="font-weight:bold;">
						<c:if test="${printlist.checkBox == false}">
						<input type="checkbox" id="myCheck" name="myCheck" onclick="indexdelcheck('${loop.index}','${consultpid}/${sb:getIdStr(printlist.propId)}')">
						</c:if>
						<c:if test="${printlist.checkBox == true}">
						<input type="checkbox" id="myCheck_${sb:getIdStr(printlist.propId)}" name="myCheck" onclick="indexdelcheck('${loop.index}','${consultpid}/${sb:getIdStr(printlist.propId)}')" checked>
						</c:if>
					</td>
					<td align="center" style="font-weight:bold;">${printlist.state} ${printlist.city} ${printlist.legalDistrictNm}</td>
					<td style="font-weight:bold;padding-left:2em;">${printlist.aptName}</td>
					<td style="font-weight:bold;padding-left:2em;">${printlist.price}</td>
				</tr>
				<input type="hidden" id="print" name="print" value="${printlist.state} ${printlist.city} ${printlist.legalDistrictNm} ${printlist.aptName}"/>
				</c:forEach>
			</table>
			
			<input type="hidden" id="consultpid" value="${consultpid}">
			<input type="hidden" id="selectedProps" name="selectedProps" value="${selectedProps}">
			<c:if test="${not empty printlist}">
				<c:set var="totCnt" value="${totCnt}"/>
				<c:set var="rowCntPerPage" value="${rowCntPerPage}"/>
				<c:set var="currentPage" value="${currentPage}"/>
				${"  totCnt : "} ${totCnt}
				${sb:getPagination(totCnt, currentPage, rowCntPerPage, 5)}
			</c:if>
			<div class="submit row" style="text-align:center; clear: both; margin-top: 25px;">
                   <div style="display:inline-block; width:500px; padding-left:7em;">
                       <input type="submit" class="btn btn-lg flat-btn" id="property_submit" value="매물 선택하기" style="margin:5px">
                       <input type="reset" class="btn btn-lg flat-btn" id="property_cancel" value="취소" style="margin:5px" 
                       onclick="deleteselect('${consultpid}/${sb:getIdStr(0)}')">
                   </div>
               </div>
           </form>
		</div>
	</div>