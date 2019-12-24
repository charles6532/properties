<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sb" uri="/WEB-INF/spring/tld/starbocks.tld" %>
<%@ include file="head.jsp" %>
<%@ include file="../common/signupbutton.jsp" %>
<%@ include file="../common/modifybutton.jsp" %>
<style>
.pop-layer .pop-container {
  padding: 20px 25px;
}

.pop-layer p.ctxt {
  color: #666;
  line-height: 25px;
}

.pop-layer .btn-r {
  width: 100%;
  margin: 10px 0 20px;
  padding-top: 10px;
  border-top: 1px solid #DDD;
  text-align: right;
}

.pop-layer {
  display: none;
  position: absolute;
  top: 50%;
  left: 50%;
  width: 410px;
  height: auto;
  background-color: #fff;
  border: 5px solid #3571B5;
  z-index: 10;
}

.dim-layer {
  display: none;
  position: fixed;
  _position: absolute;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  z-index: 100;
}

.dim-layer .dimBg {
  position: absolute;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background: #000;
  opacity: .5;
  filter: alpha(opacity=50);
}

.dim-layer .pop-layer {
  display: block;
}

a.btn-layerClose {
  display: inline-block;
  height: 25px;
  padding: 0 14px 0;
  border: 1px solid #304a8a;
  background-color: #3f5a9d;
  font-size: 13px;
  color: #fff;
  line-height: 25px;
}

a.btn-layerClose:hover {
  border: 1px solid #091940;
  background-color: #1f326a;
  color: #fff;
}
</style>

</head>

	<div class="theme-layout"></div>
	<header class="simple-header for-sticky white ">
		<div class="menu">
			<div class="container">
				<div class="logo" style="padding: 25px 0px 0px">
					<a href="${pageContext.request.contextPath}/main" title="">
					<img src="${pageContext.request.contextPath}/img/starbocks.png" width="50px" height="50px" style="float: left; margin-right: 8px;">
						<span>Starbocks</span>
						<strong style="width: 200px">PROPERTIES FOR SALE</strong>
					</a>
				</div>
				<!-- 혜원 - 수정, 추가사항 -->
				<c:set var="loginresult" value="${loginresult}"/>
				<c:if test="${loginresult == 1}">
					<c:set var="sbAgentDo" value="${sbAgentDo}" scope="session"/>
				</c:if>
					<c:if test="${loginresult == 0}">
						<script type="text/javascript">
							<!--
							alert( "입력하신 아이디/비밀번호가 다릅니다. \nAgent회원만 로그인 할 수 있습니다." );
							location.href="${pageContext.request.contextPath}/main";
							//-->
						</script>
					</c:if>
				
				<c:if test="${empty sbAgentDo}">
					<div class="popup-client">
						<span><i class="fa fa-user"></i>/  Sign-in</span>
					</div>
				</c:if>
				
				<c:if test="${not empty sbAgentDo}">
					<div class="popup-client-logout">
							<span><i class="fa fa-user"></i> /  Sign Out</span><br>
						</div>
				</c:if>
				<!-- 혜원 - 수정, 추가사항 -->

				<div id="popup-client" class="modal fade"><!-- modal -->
					<!-- 로그인 -->
				</div><!-- modal -->
				<div id="popup-modify" class="modal fade"><!-- modal -->
					<!-- 회원정보 수정 비밀번호 확인 -->
				</div><!-- modal -->
				
				<span class="menu-toggle"><i class="fa fa-bars"></i></span>
				<c:if test="${sbAgentDo == null}">
					<nav>
						<ul>
							<li class="menu-item-has-children"><a href="#" title="">PROPERTIES</a>
								<ul>
									<li><a href="${pageContext.request.contextPath}/prop/list" title="">매물 목록</a></li>
									<li><a href="javascript:window.alert('로그인을 먼저 하세요');" title="">매물 등록</a></li>
									<li><a href="javascript:window.alert('로그인을 먼저 하세요');" title="">내 구역 매물 보기</a></li>
									<li><a href="javascript:window.alert('로그인을 먼저 하세요');" title="">회원 매물 요청 보기</a></li>
								</ul></li>
							<li class="menu-item-has-children"><a href="#" title="">SALES STATEMENT</a>
								<ul>
									<li><a href="javascript:window.alert('로그인을 먼저 하세요');" title="">스타 사용내역</a></li>
									<li><a href="javascript:window.alert('로그인을 먼저 하세요');" title="">스타 충전하기</a></li>
								</ul></li>
							<li class="menu-item-has-children"><a href="#" title="">NOTIFICATION</a>
								<ul>
									<li><a href="javascript:window.alert('로그인을 먼저 하세요');" title="">알림내역</a></li>
									<li><a href="javascript:window.alert('로그인을 먼저 하세요');" title="">문의내역 </a></li>
									<li><a href="javascript:window.alert('로그인을 먼저 하세요');" title="">공개요청내역</a></li>
								</ul>
							</li>
							<li class="menu-item-has-children"><a href="#" title="">CONTACT</a>
								<ul>
									<li><a href="${pageContext.request.contextPath}/contact" title="">CONTACT</a></li>
									<li><a href="javascript:window.alert('로그인을 먼저 하세요');" title="">회원정보 수정</a></li>
								</ul>
							</li>
						</ul>
					</nav>
				</c:if>
				
				<c:if test="${not empty sbAgentDo}">
				<nav>
					<ul>
						<li class="menu-item-has-children"><a href="#" title="">PROPERTIES</a>
							<ul>
								<li><a href="${pageContext.request.contextPath}/prop/list" title="">매물 목록</a></li>
								<li><a href="${pageContext.request.contextPath}/prop/addForm" title="">매물 등록</a></li>
								<li><a href="${pageContext.request.contextPath}/prop/myprop/${sb:getIdStr(sbAgentDo.userId)}/${sb:getIdStr(1)}" title="">내 매물 보기</a></li>
								<li><a href="${pageContext.request.contextPath}/prop/mine/${sb:getIdStr(sbAgentDo.zipcodeId)}/${sb:getIdStr(1)}" title="">회원 매물 요청 보기</a></li>
							</ul></li>
						<li class="menu-item-has-children"><a href="#" title="">SALES STATEMENT</a>
							<ul>
								<li><a href="${pageContext.request.contextPath}/sales/list/${sb:getIdStr(sbAgentDo.userId)}/${sb:getIdStr(1)}" title="">스타 사용내역</a></li>
								<li><a href="${pageContext.request.contextPath}/sales/pay/${sb:getIdStr(sbAgentDo.userId)}" title="">스타 충전하기</a></li>
							</ul></li>
						<li class="menu-item-has-children"><a href="#" title="">NOTIFICATION</a>
							<ul>
								<li><a href="${pageContext.request.contextPath}/noti/${sb:getIdStr(sbAgentDo.userId)}/${sb:getIdStr(1)}" title="">알림내역</a></li>
								<li><a href="${pageContext.request.contextPath}/inquiry/list/${sb:getIdStr(sbAgentDo.userId)}/${sb:getIdStr(1)}" title="">문의내역 </a></li>
								<li><a href="${pageContext.request.contextPath}/askdisclosure/list/${sb:getIdStr(sbAgentDo.userId)}/${sb:getIdStr(1)}" title="">공개요청내역</a></li>
							</ul>
						</li>
						<li class="menu-item-has-children"><a href="#" title="">CONTACT</a>
							<ul>
								<li><a href="${pageContext.request.contextPath}/contact" title="">CONTACT</a></li>
								<li><a class="popup-modify" id="a">회원정보 수정</a></li>
							</ul>
						</li>
					</ul>
				</nav>
				</c:if>
					
			</div>
		</div>
	</header>
	<input type="hidden" id="hiddenid" value="${sbAgentDo.userId}"/>
<body>
<script type="text/javascript">
$('.btn-example').click(function(){
    var $href = $(this).attr('href');
    layer_popup($href);
});

function layer_popup(el){

    var $el = $(el);        //레이어의 id를 $el 변수에 저장
    var isDim = $el.prev().hasClass('dimBg');   //dimmed 레이어를 감지하기 위한 boolean 변수

    isDim ? $('.dim-layer').fadeIn() : $el.fadeIn();

    var $elWidth = ~~($el.outerWidth()),
        $elHeight = ~~($el.outerHeight()),
        docWidth = $(document).width(),
        docHeight = $(document).height();

    // 화면의 중앙에 레이어를 띄운다.
    if ($elHeight < docHeight || $elWidth < docWidth) {
        $el.css({
            marginTop: -$elHeight /2,
            marginLeft: -$elWidth/2
        })
    } else {
        $el.css({top: 0, left: 0});
    }

    $el.find('a.btn-layerClose').click(function(){
        isDim ? $('.dim-layer').fadeOut() : $el.fadeOut(); // 닫기 버튼을 클릭하면 레이어가 닫힌다.
        return false;
    });

    $('.layer .dimBg').click(function(){
        $('.dim-layer').fadeOut();
        return false;
    });

}
$(document).ready(function(){
	
	var userId = "<%=request.getSession().getId()%>";
	/* var ws = new WebSocket('ws://localhost:8080/sf/ws/noti'); */
	var ws = new WebSocket('ws://192.168.10.10:8080/sf/ws/noti');
	if(ws) {
		ws.onopen = function() { 
			console.log('websocket opened');

			ws.send(userId);
		}; 
		ws.onmessage = function(message) { 
			
			console.log(message); 
			
			console.log('receive message : ' + message.data); 

			var data = JSON.parse(message.data);
			console.log('data : '+data);
			console.log('data.receiver : '+data.receiver);
			
			var re = JSON.parse(data.receiver);
			console.log('re : '+re);
			
			
			for (var i = 0; i < data.receiver.length; i++) {
			  if ( re[i] == document.getElementById("hiddenid").value ) {
				  console.log('in if');
				  $("#msg").html("제목 : " + data.title + "<br>" + "주소 : " + data.address);
				  layer_popup("#layer1");
			  }
			} 
		};
		ws.onclose = function(event) { 
			console.log(event); 
			console.log('websocket closed'); 
		};
	}
	
});
</script>
<div style="height: 10px;"></div>
<div id="layer1" class="pop-layer">
    <div class="pop-container">
        <div class="pop-conts">
            <!--content //-->
            <p id="msg" class="ctxt mb20">
            </p>

            <div class="btn-r">
                <a href="#" class="btn-layerClose">Close</a>
            </div>
            <!--// content-->
        </div>
    </div>
</div>