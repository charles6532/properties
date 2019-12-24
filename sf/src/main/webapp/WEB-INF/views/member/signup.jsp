<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

    <%@include file="../main/head.jsp" %>
    <%@ taglib prefix="sb" uri="/WEB-INF/spring/tld/starbocks.tld" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <script type="text/javascript" src="https://cdn.jsdelivr.net/jquery.validation/1.16.0/jquery.validate.js"></script>
    
    <!-- <script type="text/javascript"> //<![CDATA[ $(function() { $("#signupForm").validate(); }); //]]> </script> -->

    <script type="text/javascript">
	//<!--
	var authstr = "";
	$(document).ready(
		
		function(){
									
			$("#authEmail").on(
				"click"
				, function( event ){
					var str = "";
					$.ajax(
						{
							type:"POST",
							url: '${pageContext.request.contextPath}/email/auth/'+signupForm.email.value,
							dataType:"text",
							success:function(datum){
								
								alert("datum : "+datum);
								signupForm.authStr.value = datum;
								$("#emailauthresult").append("authresult : " + datum +"<br>");
								
							},
							error:function(e){
								alert("bad");
							}			
						}		
					);
				}
			);
			

			$("input[name=email]").on(
				"keyup",
				function( event ){
					$("#emailresult").text("");
					$.ajax(
						{
							type:"POST",
							url: "${pageContext.request.contextPath}/email/check",
							data:{email:signupForm.email.value},
							dataType:"xml",
							success:function(data){
								var result = eval("("+$(data).find("result").text()+")");
								if( result.code.trim() == "success"){
									$("#emailresult").html(result.message);
								} else{
									$("#emailresult").html(result.message);
								}
								
							},
							error:function(e){
								$("#console").html(e);
							}			
						}		
					);
				}
				
				
			);
			
			// 비밀번호
			$("#signupForm #passwd2").on(
				"keyup",
				function( event ){
					if(!/^(?=.*[a-zA-Z])(?=.*[!@#$%^*+=-])(?=.*[0-9]).{8,25}$/.test($("#signupForm #passwd2").val())){            
						$("#passwdresult").text("숫자+영문자+특수문자 조합으로 8자리 이상 사용해야 합니다.");
				    } 
				    var checkNumber = $("#signupForm #passwd2").val().search(/[0-9]/g);
				    var checkEnglish = $("#signupForm #passwd2").val().search(/[a-z]/ig);
				    if(checkNumber <0 || checkEnglish <0){
				    	$("#passwdresult").text("숫자와 영문자를 혼용하여야 합니다.");
				    } 
				    if(/(\w)\1\1\1/.test($("input[name=passwd]").val())){
				    	$("#passwdresult").text("같은 문자를 4번 이상 사용하실 수 없습니다.");
				    }
				    if(/^(?=.*[a-zA-Z])(?=.*[!@#$%^*+=-])(?=.*[0-9]).{8,25}$/.test($("#signupForm #passwd2").val())
				    		&& !(checkNumber <0 || checkEnglish <0)
				    		&& !(/(\w)\1\1\1/.test($("#signupForm #passwd2").val()))){            
						$("#passwdresult").text("비밀번호가 유효합니다");
				    } 
				    
				}
			);
			
			// 비밀번호 일치여부
			$("input[name=repasswd2]").on(
				"keyup",
				function( event ){
					if($("#signupForm #passwd2").val() == $("input[name=repasswd2]").val()){
						$("#repasswdresult").text("비밀번호가 일치합니다");
					} else{
						$("#repasswdresult").text("비밀번호가 다릅니다");
					}
				}
			);
			// 이름
			// 전화번호	
		}
	);
	function authCheck(){
		if( ! $("#randomStr").val()) {
			alert( "randomStr" );
			signupForm.randomStr.focus();
			return false;
		} 
	}
	//-->
</script>
<br><br>
    
    <%@include file="../main/top.jsp" %>
    
    <div class="theme-layout">
        <div class="inner-head overlap">
            <div style="background: url(${pageContext.request.contextPath}/img/parallax1.jpg)" class="parallax scrolly-invisible no-parallax"></div>
            <!-- PARALLAX BACKGROUND IMAGE -->	
            <div class="container">
                <div class="inner-content">
                    <span><i class="fa fa-bolt"></i></span>
                    <h2>Sign Up PAGE</h2>
                    <ul>
                        <li><a href="../index.jsp" title="">HOME</a></li>
                        <li><a href="signup.jsp" title="">Sign Up PAGE</a></li>
                    </ul>
                </div>
            </div>
        </div><!-- inner Head -->

        <section class="block">
            <div class="container">
                <div class="row">
                    <div class="col-md-12">
                        <div class="login-sec"> 
                            <div class="row">
                              <div class="container" style="width:600px;">  
                                <div class="container col-md-6" style="width:600px;">
                                    <div class="registration-sec">
                                        <h3>SIGNUP Form</h3>
										<c:set var="auresult" value="${auresult}"/>
                                        <form class="cmxform" name="signupForm" id="signupForm" method="POST" 
                                        	action="${pageContext.request.contextPath}/email/verify" onsubmit="return authCheck()">
                                        <div id="id01">
                                          	<input type="hidden" id="authStr" name="authStr">
	                                        <div class="field">
	                                        <input id="cname" name="name" minlength="2" type="text" placeholder="이름 (필수, 2 자 이상)" required> 
	                                        </div>
	                                        <div class="field">
	                                        <input id="cemail" type="email" name="email" placeholder="이메일 (필수)" required>
	                                        </div>
	                                        <div id="emailresult"></div>
	                                        <div class="field">
	                                            <input id="passwd2" name="passwd" type="password" placeholder="패스워드를 입력하세요" />
	                                        </div>
                                        	<div id="passwdresult"></div>
	                                        <div class="field">
	                                            <input id="repasswd2" name="repasswd2" type="password" placeholder="패스워드를 다시 입력하세요" />
	                                        </div>
                                        	<div id="repasswdresult"></div>
                                            <div class="field">
                                                <input id="randomStr" name="randomStr" type="text" placeholder="이메일 인증코드를 입력하세요"  style="width:382px;display:inline;"/>
                                                <br>
                                                <input type="button" id="authEmail" name="authEmail" value="이메일 인증" class="bt btn-primary btn-sm" 
                                                 	style="vertical-align:middle;display:inline;margin-left:9px;" />
                                            </div>
                                            <div id="emailauthresult"></div>
											<c:if test="${randomresult eq 0}">
												${"이메일 인증 실패로 인한 "}
											</c:if>
											${signupresult}
											
                                            <div class="field">
	                                        	<input type="submit" value="Sign up Now" class="btn flat-btn btn-sm"> 
	                                        </div>
	                                      	                                 
                                        </div>
                                        </form>
											 		
											
													
                                    </div><!-- Registration sec -->
                                	</div>
                                </div>
                            </div>  
                            
                        </div>
                        <div id="console"></div>
                    </div>
                </div>
            </div>
        </section>

        <%@include file="../main/bottom.jsp" %>
