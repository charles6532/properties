<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../common/top.jsp" %>
<div class="complete-popup-sec">
    <div class="complete-popup-area">
        <div class="complete-popup">
            <div class="row">
                <div class="col-md-12">
                    <div class="account-user">
                        <div class="logo">
                            <a href="#" title="" style="padding:0px 0px 0px 0px ">
                            <img src="${pageContext.request.contextPath}/img/starbocks.png" width="50px" height="50px" align="left" style="margin-right:8px"><span>Starbocks</span><br>
                                <strong style="width:185px">PROPERTIES FOR SALE</strong>
                            </a>
                        </div>
                        <!-- LOGO -->
                        <form method="post" id="complete" name="complete" action="${pageContext.request.contextPath}/prop/complete/${sb:getIdStr(propdetailvo.propId)}" onsubmit="return confirmcheck()" >
                            <h4>매물 계약 완료 처리</h4>
                            <div class="field">
                                <label style="color:white;">비밀번호를 입력해주세요</label>
                                <input type="password" id="passwd" name="passwd"/>
                                <input type="hidden" id="email" name="email" value="${sbAgentDo.email}"/>    <!-- 1001 혜원추가 -->
                            </div>
                            <div class="field" style="padding:20px">
                                <input type="submit" value="CONFIRM" class="flat-btn" style="margin:5px"/>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
            <span class="close-popup"><i class="fa fa-close"></i></span>
        </div>
    </div>
</div>
<!-- Account Popup Sec -->
<script type="text/javascript">
//<!--
    function confirmcheck() {
        if (! document.complete.passwd.value) {
            alert("비밀번호를 입력하세요");
            return false;
        }
    }
//-->
</script>
