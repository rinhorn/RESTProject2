<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1">
<style>
body {
	font-family: Arial, Helvetica, sans-serif;
}

* {
	box-sizing: border-box;
}

input[type=text], select, textarea {
	width: 100%;
	padding: 12px;
	border: 1px solid #ccc;
	border-radius: 4px;
	box-sizing: border-box;
	margin-top: 6px;
	margin-bottom: 16px;
	resize: vertical;
}

input[type=button] {
	background-color: #4CAF50;
	color: white;
	padding: 12px 20px;
	border: none;
	border-radius: 4px;
	cursor: pointer;
}

input[type=button]:hover {
	background-color: #45a049;
}

.container {
	border-radius: 5px;
	background-color: #f2f2f2;
	padding: 20px;
}
</style>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script>
	$(function() {
		$($("input[type='button']")[0]).click(function() {
			searchMountain();
		});
		$($("input[type='button']")[1]).click(function() {
			regist();
		});
	});
	//비동기 요청
	function searchMountain() {
		$.ajax({
			url : "/admin/mountain/list",
			type : "get",
			data : {
				name : $($("form").find("input[name='name']")).val()
			},
			success : function(result) {
				var json = JSON.parse(result);

				var obj;
				if (json.length < 1) {
					alert("검색 결과가 없습니다. " + json.length);
				} else if (json.length == 1) {
					obj = json[0];
					alert("결정된 산의 정보 : " + obj.detail);
					setData(obj);
				} else {
					var n = prompt(json.length
							+ " 건이 발견되었습니다.\n 원하시는 산의 번호로 선택하세요[0~"
							+ (json.length - 1) + "]");
					obj = json[n];
					alert("결정된 산의 정보 : " + obj.detail);
					setData(obj);
				}
			}
		});
	}
	//산의 주소와 상세정보 채워넣기
	function setData(obj) {
		$($("form").find("input[name='addr']")).val(obj.addr);
		$($("form").find("textarea[name='detail']")).text(obj.detail);
	}
	//산 정보 등록
	function regist() {
		$("form").attr({
			method : "post",
			action : "/admin/mountain/regist"
		});
		$("form").submit();
	}
</script>
</head>
<body>

	<h3>Contact Form</h3>

	<div class="container">
		<form enctype="multipart/form-data">
			<!-- 텍스트 뿐만아니라 모든 바이너리 데이터를 보낼 수 있음 -->

			<input type="text" name="name" placeholder="산 이름" style="width: 85%">

			<input type="button" value="공공 데이터 검색"> <input type="text"
				name="addr" placeholder="주소">

			<textarea name="detail" placeholder="상세정보" style="height: 200px"></textarea>

			<input type="file" name="myFile" />
			<!-- DTO에서 MultipartFile 의 변수명과 일치해야 등록됨 -->
			<input type="text" name="lati" placeholder="위도"> <input
				type="text" name="longi" placeholder="경도"> <select
				name="marker">
				<option value="">마커 선택</option>
				<option value="pin1.png">초록핀</option>
				<option value="pin2.png">빨간핀</option>
				<option value="pin3.png">깃발핀</option>
				<option value="pin4.png">빨간압정</option>
				<option value="pin5.png">메탈핀</option>
			</select> <input type="button" value="등록"> <input type="button"
				value="목록">
		</form>
	</div>

</body>
</html>
