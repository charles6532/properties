<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

 <%@include file="head.jsp" %>

 
 
<%@include file="top.jsp" %>
<style>
    .map_wrap {position:relative;width:100%;height:1000px;}
    .title {font-weight:bold;display:block;}
    .hAddr {position:absolute;left:10px;top:10px;border-radius: 2px;background:#fff;background:rgba(255,255,255,0.8);z-index:1;padding:5px;}
    #centerAddr {display:block;margin-top:2px;font-weight: normal;}
    .bAddr {padding:5px;text-overflow: ellipsis;overflow: hidden;white-space: nowrap;}
</style>

<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=bd3258469854be805863f8777a9756d9&libraries=services,clusterer,drawing"></script>
<script type="text/javascript">
        $(document).ready(function () {
        	
        	
        	
        	var mapContainer = document.getElementById('map_canvas'), //지도를 담을 영역의 DOM 레퍼런스
    		   mapOptions = { //지도를 생성할 때 필요한 기본 옵션
    			center : new kakao.maps.LatLng(37.49060091496244, 127.0105499394268), //지도의 중심좌표.
    			level : 4
    		//지도의 레벨(확대, 축소 정도)
    		};
    		var map = new kakao.maps.Map(mapContainer, mapOptions); //지도 생성 및 객체 리턴            
			
    		// 주소-좌표 변환 객체를 생성합니다
    		var geocoder = new kakao.maps.services.Geocoder();
    		
    		var marker = new kakao.maps.Marker();
    		// 장소 검색 객체를 생성합니다
    		var ps = new kakao.maps.services.Places(); 
    		
    		// 마커를 클릭하면 장소명을 표출할 인포윈도우 입니다
        	var infowindow = new kakao.maps.InfoWindow({zIndex:1});

    		// 키워드로 장소를 검색합니다
    		ps.keywordSearch('서울시 강남구 도곡2차 I PARK', placesSearchCB); 

    		// 키워드 검색 완료 시 호출되는 콜백함수 입니다
    		function placesSearchCB (data, status, pagination) {
    		    if (status === kakao.maps.services.Status.OK) {

    		        // 검색된 장소 위치를 기준으로 지도 범위를 재설정하기위해
    		        // LatLngBounds 객체에 좌표를 추가합니다
    		        var bounds = new kakao.maps.LatLngBounds();

    		        for (var i=0; i<data.length; i++) {
    		            displayMarker(data[i]);    
    		            bounds.extend(new kakao.maps.LatLng(data[i].y, data[i].x));
    		        }       

    		        // 검색된 장소 위치를 기준으로 지도 범위를 재설정합니다
    		        map.setBounds(bounds);
    		    } 
    		}

    		// 지도에 마커를 표시하는 함수입니다
    		function displayMarker(place) {
    		    
    		    // 마커를 생성하고 지도에 표시합니다
    		    var marker = new kakao.maps.Marker({
    		        map: map,
    		        position: new kakao.maps.LatLng(place.y, place.x) 
    		    });

    		    // 마커에 클릭이벤트를 등록합니다
    		    kakao.maps.event.addListener(marker, 'click', function() {
    		        // 마커를 클릭하면 장소명이 인포윈도우에 표출됩니다
    		        infowindow.setContent('<div style="padding:5px;font-size:12px;">' + place.place_name + '</div>');
    		        infowindow.open(map, marker);
    		    });
    		}
    		
    		
    		// 일반 지도와 스카이뷰로 지도 타입을 전환할 수 있는 지도타입 컨트롤을 생성합니다
    		var mapTypeControl = new kakao.maps.MapTypeControl();

    		// 지도에 컨트롤을 추가해야 지도위에 표시됩니다
    		// kakao.maps.ControlPosition은 컨트롤이 표시될 위치를 정의하는데 TOPRIGHT는 오른쪽 위를 의미합니다
    		map.addControl(mapTypeControl, kakao.maps.ControlPosition.TOPRIGHT);

    		// 지도 확대 축소를 제어할 수 있는  줌 컨트롤을 생성합니다
    		var zoomControl = new kakao.maps.ZoomControl();
    		map.addControl(zoomControl, kakao.maps.ControlPosition.RIGHT);
    		    		
    		// 현재 지도 중심좌표로 주소를 검색해서 지도 좌측 상단에 표시합니다
    		searchAddrFromCoords(map.getCenter(), displayCenterInfo);

    		// 지도를 클릭했을 때 클릭 위치 좌표에 대한 주소정보를 표시하도록 이벤트를 등록합니다
    		kakao.maps.event.addListener(map, 'click', function(mouseEvent) {
    		    searchDetailAddrFromCoords(mouseEvent.latLng, function(result, status) {
    		        if (status === kakao.maps.services.Status.OK) {
    		            var detailAddr = !!result[0].road_address ? '<div>도로명주소 : ' + result[0].road_address.address_name + '</div>' : '';
    		            detailAddr += '<div>지번 주소 : ' + result[0].address.address_name + '</div>';
    		            
    		            var content = '<div class="bAddr">' +
    		                            '<span class="title">법정동 주소정보</span>' + 
    		                            detailAddr + 
    		                        '</div>';
    		                        
                     	// 지도의 현재 중심좌표를 얻어옵니다 
                        var center = map.getCenter(); 
                        
                        // 지도의 현재 레벨을 얻어옵니다
                        var level = map.getLevel();
                        
                        // 지도타입을 얻어옵니다
                        var mapTypeId = map.getMapTypeId(); 
                        
                        // 지도의 현재 영역을 얻어옵니다 
                        var bounds = map.getBounds();
                        
                        // 영역의 남서쪽 좌표를 얻어옵니다 
                        var swLatLng = bounds.getSouthWest(); 
                        
                        // 영역의 북동쪽 좌표를 얻어옵니다 
                        var neLatLng = bounds.getNorthEast(); 
                        
                        // 영역정보를 문자열로 얻어옵니다. ((남,서), (북,동)) 형식입니다
                        var boundsStr = bounds.toString();

    		            // 마커를 클릭한 위치에 표시합니다 
    		            marker.setPosition(mouseEvent.latLng);
    		            marker.setMap(map);

    		            // 인포윈도우에 클릭한 위치에 대한 법정동 상세 주소정보를 표시합니다
    		            infowindow.setContent(content);
    		            infowindow.open(map, marker);
    		            
    		            message = '지도 중심좌표는 위도 ' + center.getLat() + ', <br>';
    	                message += '경도 ' + center.getLng() + ' 이고 <br>';
    	                message += '지도 레벨은 ' + level + ' 입니다 <br> <br>';
    	                message += '지도 타입은 ' + mapTypeId + ' 이고 <br> ';
    	                message += '지도의 남서쪽 좌표는 ' + swLatLng.getLat() + ', ' + swLatLng.getLng() + ' 이고 <br>';
    	                message += '북동쪽 좌표는 ' + neLatLng.getLat() + ', ' + neLatLng.getLng() + ' 입니다';
    	                
    	                // 개발자도구를 통해 직접 message 내용을 확인해 보세요.
    	                var resultDiv = document.getElementById('clickLatlng'); 
    	                resultDiv.innerHTML = message;
    	                
    		        }   
    		    });
    		});

    		// 중심 좌표나 확대 수준이 변경됐을 때 지도 중심 좌표에 대한 주소 정보를 표시하도록 이벤트를 등록합니다
    		kakao.maps.event.addListener(map, 'idle', function() {
    		    searchAddrFromCoords(map.getCenter(), displayCenterInfo);
    		});

    		function searchAddrFromCoords(coords, callback) {
    		    // 좌표로 행정동 주소 정보를 요청합니다
    		    geocoder.coord2RegionCode(coords.getLng(), coords.getLat(), callback);         
    		}

    		function searchDetailAddrFromCoords(coords, callback) {
    		    // 좌표로 법정동 상세 주소 정보를 요청합니다
    		    geocoder.coord2Address(coords.getLng(), coords.getLat(), callback);
    		}

    		// 지도 좌측상단에 지도 중심좌표에 대한 주소정보를 표출하는 함수입니다
    		function displayCenterInfo(result, status) {
    		    if (status === kakao.maps.services.Status.OK) {
    		        var infoDiv = document.getElementById('centerAddr');

    		        for(var i = 0; i < result.length; i++) {
    		            // 행정동의 region_type 값은 'H' 이므로
    		            if (result[i].region_type === 'H') {
    		                infoDiv.innerHTML = result[i].address_name;
    		                break;
    		            }
    		        }
    		    }    
    		}
    		
        	"use strict";

            $(".carousel-prop").owlCarousel({
                autoplay: true,
                autoplayTimeout: 3000,
                smartSpeed: 2000,
                loop: true,
                dots: true,
                nav: false,
                items: 4,
                itemsCustom: false,
                itemsDesktop: [1199, 3],
                itemsDesktopSmall: [980, 2],
                itemsTablet: [768, 2],
                itemsTabletSmall: false,
                itemsMobile: [479, 1],
                itemsScaleUp: false

            });


            $(".carousel").owlCarousel({
                autoplay: true,
                autoplayTimeout: 3000,
                smartSpeed: 2000,
                loop: false,
                dots: false,
                nav: true,
                margin: 0,
                items: 3
            });

            $(".carousel-client").owlCarousel({
                autoplay: true,
                autoplayTimeout: 3000,
                smartSpeed: 2000,
                loop: false,
                dots: false,
                items: 5,
                itemsCustom: false,
                itemsDesktop: [1199, 5],
                itemsDesktopSmall: [980, 3],
                itemsTablet: [768, 2],
                itemsTabletSmall: false,
                itemsMobile: [479, 1],
                itemsScaleUp: false
            });

        });
        
        
    </script>
    
<section>
<div class="map_wrap">
    <div id="map_canvas" style="width:80%;margin:auto;"></div>
    <div class="hAddr">
        <span class="title">지도중심기준 행정동 주소정보</span>
        <span id="centerAddr"></span>
    </div>
    
    <div id="clickLatlng" style="padding:5px;font-size:12px;"></div>
</div>

</section>














<%@include file="bottom.jsp" %>

    