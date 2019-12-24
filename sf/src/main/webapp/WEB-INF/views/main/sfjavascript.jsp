<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<script type="text/javascript">
	<!--
	
	function erroralert( msg ) {
		alert( msg );
		history.back();
	}

	
	function logincheck() {
		if( ! loginform.email.value ) {
			alert( "이메일을 입력하세요" );
			loginform.email.focus();
			return false;
		} else if( ! loginform.passwd.value ) {
			alert( "패스워드를 입력하세요" );
			loginform.passwd.focus();
			return false;
		}		
	}
	
	function setid( id ) {
		opener.document.inputform.confirm.value = "1";		// 중복확인 성공
		opener.document.inputform.id.value = id;
		self.close();
	}
	//-->
</script>