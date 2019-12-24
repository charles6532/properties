<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="sfjavascript.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sb" uri="/WEB-INF/spring/tld/starbocks.tld" %>


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
<div class="account-popup-sec">
            <div class="account-popup-area">
                <div class="account-popup">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="account-user">
                                <div class="logo" style="margin-left:-4em; margin-bottom:-0.7em;">
                                    <a href="#" title="">
                                        <img src="${pageContext.request.contextPath}/img/starbocks.png" width="60px" height="60px" style="margin-right:5px;float:left">
                                        <span>Starbocks</span>
                                        <strong>RENT PROPERTIES</strong>
                                    </a>
                                </div><!-- LOGO -->
                                <form name="loginform" method="POST" action="${pageContext.request.contextPath}/main/loginPro"  
										onsubmit="return logincheck()">
                                    <h4>Login</h4>
                                    <div class="field">
                                        <input autofocus name="email" type="text" placeholder="이메일을 입력하세요"/>
                                    </div>
                                    <div class="field">
                                        <input name="passwd" type="password" placeholder="패스워드를 입력하세요"/>
                                    </div>
                                    <div class="field">
                                        <input type="submit" value="SEND NOW" class="btn flat-btn btn-sm" style="margin-right:5px;"/>
                                        <input type="button" value="SIGN UP NOW" class="btn flat-btn btn-sm" style="margin-left:5px;" 
                                        		onclick="location.href='${pageContext.request.contextPath}/member/signup'"/>
                                    </div>
                                </form>
                                
                            </div>
                        </div>
                        
                    </div>
                    <span class="close-popup"><i class="fa fa-close"></i></span>
                </div>
            </div>
        </div><!-- Account Popup Sec -->
    
<header class="simple-header for-sticky white ">
			<div class="menu">
				<div class="container">
					<div class="logo">
						<a id="index" onclick="javascript:location.href='${pageContext.request.contextPath}/main'" title="">
							<img src="${pageContext.request.contextPath}/img/starbocks.png" width="60px" height="60px" style="margin-right:15px;float:left">
							<span>Starbocks</span>
							<strong>PROPERTIES FOR SALE</strong>
						</a>
					</div><!-- LOGO -->
					
					<c:set var="loginresult"  value="${loginresult}"/>
					<c:if test="${loginresult == 1}">
						<c:set var="sbmemberdo"  value="${sbmemberdo}" scope="session"/>
					</c:if>
					<c:if test="${loginresult == 0}">
					${"비밀번호가 틀렸습니다"}
					</c:if>
					
					
					<c:if test="${empty sbmemberdo}">
						<div class="popup-client">
							<span><i class="fa fa-user"></i> /  Sign In</span>
						</div>
					</c:if>
					<c:if test="${not empty sbmemberdo}">						
						<div class="popup-client-logout">
							<span><i class="fa fa-user"></i> /  Sign Out</span><br>
							
						</div>

					</c:if>
					<!-- modal -->
					<div id="popup-client" class="modal fade">
						<!-- 로그인 -->
						
						
					</div><!-- modal -->
					<span class="menu-toggle"><i class="fa fa-bars"></i></span>
					<c:if test="${sbmemberdo != null}">
					<nav>
						<ul>
							
							<li class="menu-item-has-children">
								<a href="#" title="">PROPERTIES</a>
								<ul>
									<li><a id="properties2" onclick="javascript:location.href='${pageContext.request.contextPath}/property/list/${sb:getIdStr(sbmemberdo.userId)}'" title="">매물목록</a></li>
									<li><a id="consultproperty" onclick="javascript:location.href='${pageContext.request.contextPath}/property/askfor'" title="">매물요청</a></li>
									<li><a id="myproperties" onclick="javascript:location.href='${pageContext.request.contextPath}/property/mine/${sb:getIdStr(sbmemberdo.userId)}/${sb:getIdStr(1)}'" title="">내 매물 보기</a></li>
								</ul>
							</li>
							<li><a id="property" onclick="javascript:location.href='${pageContext.request.contextPath}/property/favorite/${sb:getIdStr(sbmemberdo.userId)}/${sb:getIdStr(1)}'" title="">FAVORITE</a></li><!--관심매물 -->

							<li class="menu-item-has-children">
								<a href="#" title="">NOTIFICATION</a>
								<ul>
									<li><a id="noticelist" onclick="javascript:location.href='${pageContext.request.contextPath}/notice/list/${sb:getIdStr(sbmemberdo.userId)}/${sb:getIdStr(1)}'" title="">알림내역</a></li> 
									<li><a id="inquirylist" onclick="javascript:location.href='${pageContext.request.contextPath}/inquiry/list/${sb:getIdStr(sbmemberdo.userId)}/${sb:getIdStr(1)}'" title="">문의내역 </a></li> 
									<li><a id="askdisclosure-list" onclick="javascript:location.href='${pageContext.request.contextPath}/askdisclosure/list/${sb:getIdStr(sbmemberdo.userId)}/${sb:getIdStr(1)}'" title="">공개요청내역</a></li> 
								</ul>
							</li>
							<li><a id="property" onclick="javascript:location.href='${pageContext.request.contextPath}/member/modifyinfo'" title="">INFO</a></li><!--회원정보수정 -->
							<li><a id="contact" onclick="javascript:location.href='${pageContext.request.contextPath}/etc/contact'" title="">CONTACT</a></li>
						</ul>
					</nav>
					</c:if>
					<c:if test="${sbmemberdo == null}">
						<nav>
							<ul>
								<li class="menu-item-has-children">
									<a href="#" title="">PROPERTIES</a>
									<ul>
										<li><a id="properties2" onclick="javascript:location.href='${pageContext.request.contextPath}/property/list/none'" title="">매물목록</a></li>
									</ul>
								</li>
								<li><a id="contact" onclick="javascript:location.href='${pageContext.request.contextPath}/etc/contact'" title="">CONTACT</a></li>
							</ul>
						</nav>
					</c:if>
				</div>
			</div>
		</header>
		<input type="hidden" id="hiddenid" value="${sbmemberdo.userId}"/>
		
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
	/* var ws = new WebSocket('ws://localhost:8080/rapms/ws/noti'); */
	var ws = new WebSocket('ws://192.168.10.10:8080/rapms/ws/noti');
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