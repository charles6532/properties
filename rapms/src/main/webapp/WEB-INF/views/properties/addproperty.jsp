<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<script type="text/javascript">
	$(document).ready(function() {
	
		
		var str = $('#features').val();
		str = str.replace(/(?:\r\n|\r|\n)/g, '<br/>');
		$('#features').val(str);
		
		"use strict";
		$(".related-properties-items").owlCarousel({
			autoplay : true,
			autoplayTimeout : 3000,
			smartSpeed : 2000,
			loop : true,
			dots : true,
			nav : false,
			marging : 30,
			items : 4,
			responsiveClass : true,
			responsive : {
				0 : {
					items : 1,
					nav : false
				},
				600 : {
					items : 2,
					nav : false
				},
				1000 : {
					items : 3,
					nav : true,
					loop : false
				}
			}
		});
		
		$('.col-md-4.date').datepicker({});
		$("select").on("change",function(event){
        	let value = event.target.value;
        	var url = '${pageContext.request.contextPath}/prop/askfor/';
        	if($(this).attr("id") =='state'){
        		url += $(this).attr("id")+'/' + $("select[name=state] option:selected").val();
        		$("select[name=city]").append("<option>Loading data ....</option>");
        		$.ajax(
	                    {
	                        type: "POST",
	                        url: url,
	                        data: { city : value },
	                        dataType: "json",
	                        success: function(data){
	                        	$("select[name=city] option").remove();
	                            let gun = "";
	                            gun = "<option>시/군/구</option>";
	                            $("select[name=city]").append(gun);
	                            for(let i=0; i<data.length; i++){
	                                gun = "<option>";
	                                gun += data[i].city;
	                                gun += "</option>";
		                            $("select[name=city]").append(gun);
	                            }
	                        },
	                        error: function(){
	                            alert("bad");
	                        }
	                    }
	            );
        	}

        	if($(this).attr("id") =='city'){
        		url += $(this).attr("id")+'/' + $("select[name=city] option:selected").val();
        		$("select[name=legalDistrictNm]").append("<option>Loading data ....</option>");
        		$.ajax(
	                    {
	                        type: "POST",
	                        url: url,
	                        data: { legalDistrictNm : value },
	                        dataType: "json",
	                        success: function(data){
	                        	$("select[name=legalDistrictNm] option").remove();
	                            let gun = "";
	                            gun = "<option>읍/면/동</option>";
	                            $("select[name=legalDistrictNm]").append(gun);
	                            for(let i=0; i<data.length; i++){
	                                gun = "<option>";
	                                gun += data[i].legalDistrictNm;
	                                gun += "</option>";
		                            $("select[name=legalDistrictNm]").append(gun);
	                            }
	                        },
	                        error: function(){
	                            alert("bad");
	                        }
	                    }
	            );
        	}
        	
        	if($(this).attr("id") =='legalDistrictNm'){
        		url += $(this).attr("id")+'/' + $("select[name=state] option:selected").val()+" " +$("select[name=city] option:selected").val()+" " + $("select[name=legalDistrictNm] option:selected").val();
        		$("select[name=bldgNm]").append("<option>Loading data ....</option>");
        		$.ajax(
	                    {
	                        type: "POST",
	                        url: url,
	                        data: { bldgNm : value },
	                        dataType: "json",
	                        success: function(data){
	                        	$("select[name=bldgNm] option").remove();
	                            let gun = "";
	                            gun = "<option>단지이름</option>";
	                            $("select[name=bldgNm]").append(gun);
	                            for(let i=0; i<data.length; i++){
	                                gun = "<option>";
	                                gun += data[i].bldgNm;
	                                gun += "</option>";
		                            $("select[name=bldgNm]").append(gun);
	                            }
	                        },
	                        error: function(){
	                            alert("bad");
	                        }
	                    }
	            );
        	}
        	
        	
        });
		 	
			$('.col-md-6.date').datepicker({
				dateFormat: 'yyyy-mm-dd',
				minDate: 0
			});
	});
       
	
	function putZero(  ){
				
 		propertyform.price.value = Number(propertyform.price.value);
		propertyform.dong.value = Number(propertyform.dong.value);
		propertyform.hoNo.value = Number(propertyform.hoNo.value);
		propertyform.floor.value = Number(propertyform.floor.value);
		propertyform.builtYear.value = Number(propertyform.builtYear.value);
		propertyform.parking.value = Number(propertyform.parking.value);
		propertyform.areaSpace.value = Number(propertyform.areaSpace.value).toFixed(2);
		propertyform.bathroomNo.value = Number(propertyform.bathroomNo.value);
		propertyform.numApartment.value = Number(propertyform.numApartment.value); 
		
		if( ! $("#title").val()) {
			alert( "title" );
			addproperty.title.focus();
			return false;
		} else if( ! $("#dong").val() ) {
			alert( "dong" );
			return false;
		} else if( ! $("#hoNo").val() ) {
			alert( "ho" );
			return false;
		} else if( ! $("#features").val() ) {
			alert( "features" );
			return false;
		} else if( !  $("#floor").val() ) {
			alert( "floor" );
			return false;
		} else if(  $("#moveAt").val()== "날짜선택"  ) {
			alert( "move" );
			return false;
		} else if( !  $("#price").val() ) {
			alert( "price" );
			return false;
		} else if(  $("#floor").val() == "0" ) {
			alert( "floor" );
			return false;
		} else if( $("#room").val() == "0" ) {
			alert( "room" );
			return false;
		} else if( ! $("#areaSpace").val() ) {
			alert( "areaSpace");
			return false;
		} else if( $("#state").val() == "시/도" ) {
			alert( "state");
			return false;
		} else if( $("#city").val() == "시/군/구" ) {
			alert( "시/군/구" );
			return false;
		} else if( $("#legalDistrictNm").val() == "읍/면/동" ) {
			alert("읍/면/동");
			return false;
		} else if( $("#aptName").val() == "단지명" ) {
			alert("aptName");
			return false;
		} 
		
		document.getElementById("propertyform").submit();
	}
</script>

<!-- 이미지 미리보기 -->
<script type="text/javascript">
	function fileInfo( f ) {
		if( $('#thumbnail') != null ) {
			$('#thumbnail *').remove();
		}
		var file = f.files;
		for( var i=0; i<file.length; i++) {
			var reader = new FileReader();
			reader.onload = function( rst ) {
				$( '#thumbnail' ).append('<img src="' + rst.target.result + '" width=190 height=200>');
			}
			reader.readAsDataURL( file[i] );
		}
	}
	function fileInfoD( f ) {
		if( $('#Dthumbnail') != null ) {
			$('#Dthumbnail *').remove();
		}
		var file = f.files;
		for( var i=0; i<file.length; i++) {
			var reader = new FileReader();
			reader.onload = function( rst ) {
				$( '#Dthumbnail' ).append('<img src="' + rst.target.result + '" width=190 height=200>');
			}
			reader.readAsDataURL( file[i] );
		}
	}
</script>
<!-- 이미지 미리보기 -->

