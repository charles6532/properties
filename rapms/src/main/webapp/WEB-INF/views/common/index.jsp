<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="top.jsp" %>
<%@ include file="signupbutton.jsp" %>
<%@ include file="modifybutton.jsp" %>

<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=bd3258469854be805863f8777a9756d9"></script>
<section>
	<div class="container">
		<div id="map_canvas"></div>
	</div>
	<script type="text/javascript">
		var container = document.getElementById('map_canvas'); //지도를 담을 영역의 DOM 레퍼런스
		var options = { //지도를 생성할 때 필요한 기본 옵션
			center : new kakao.maps.LatLng(37.49060091496244, 127.0105499394268), //지도의 중심좌표.
			level : 4
		//지도의 레벨(확대, 축소 정도)
		};
		var map = new kakao.maps.Map(container, options); //지도 생성 및 객체 리턴            

		var imageSrc = 'http://t1.daumcdn.net/localimg/localimages/07/mapapidoc/markerStar.png', imageSize = new kakao.maps.Size(24, 35), // 마커이미지의 크기입니다
		imageOption = {
			offset : new kakao.maps.Point(27, 69)
		}; // 마커이미지의 옵션입니다. 마커의 좌표와 일치시킬 이미지 안에서의 좌표를 설정합니다.

		var markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize,
			imageOption), markerPosition = new kakao.maps.LatLng(
			37.49060091496244, 127.0105499394268);

		// 마커를 생성합니다
		var marker = new kakao.maps.Marker({
			position : markerPosition,
			image : markerImage
		// 마커이미지 설정
		});

		// 마커가 지도 위에 표시되도록 설정합니다
		marker.setMap(map);
	//-->
	</script>
</section>



<%@include file="footer.jsp"%>
