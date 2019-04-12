<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
.wrapper{
	width:100%;
	height:500px;
}
.regist-area{
	width:20%;
	height:500px;
	background:yellow;
}
.list-area{
	width:60%;
	height:500px;
	background:pink;
	overflow:scroll;
}
.detail-area{
	width:20%;
	height:500px;
	background:blue;
}
.regist-area, .list-area, .detail-area{
	float:left;
}
</style>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script>
$(function(){
	getList();
	
	$("form[name='regist-form']").find("button").click(function(){
		regist();
	});

	$($("button")[1]).click(function(){
		edit();
	});
	
	$($("button")[2]).click(function(){
		del();
	});
});

//비동기 요청
function regist(){
	$.ajax({
		url:"/admin/member/regist",
		type:"post",
		data:{
			id:$($("form[name='regist-form']").find("input[name='id']")).val(),
			pass:$($("form[name='regist-form']").find("input[name='pass']")).val(),
			name:$($("form[name='regist-form']").find("input[name='name']")).val()
		},
		success:function(result){
			//alert("파싱 전의 값: "+result);
			var json=JSON.parse(result);
			//목록 갱신
			if(json.result==1){
				getList();
			}else{
				alert("등록되지 않았습니다");
			}
		},
		error:function(result){
			alert(result);
		}
	});
}

//비동기 목록 요청
function getList(){
	$.ajax({
		url:"/admin/member/list",
		type:"get",
		success:function(result){
			renderList(JSON.parse(result));
		},
		error:function(result){
		}
	});
}

//리스트 화면 처리
function renderList(json){
	$(".list-area").html("");  //기존 데이터 지우기

	$(".list-area").append("<table width='100%' border='1'>");
	$(".list-area").append("<tr>");
	$(".list-area").append("<td width='10%'>Seq</td>");
	$(".list-area").append("<td width='30%'>아이디</td>");
	$(".list-area").append("<td width='30%'>비밀번호</td>");
	$(".list-area").append("<td width='30%'>이름</td>");
	$(".list-area").append("</tr>");

	for(var i=0;i<json.memberList.length;i++){
		var obj=json.memberList[i];
		$(".list-area").append("<tr>");
		$(".list-area").append("<td>"+obj.member_id+"</td>");
		$(".list-area").append("<td>"+obj.id+"</td>");
		$(".list-area").append("<td>"+obj.pass+"</td>");
		$(".list-area").append("<td><a href='javascript:getDetail("+JSON.stringify(obj)+");'>"+obj.name+"</a></td>");
		$(".list-area").append("</tr>");
	}
	
	$(".list-area").append("</table>");
}

function getDetail(member){
	$.ajax({
		url:"/admin/member/detail",
		type:"get",
		data:{
			member_id:member.member_id
		},
		success:function(result){
			var json=JSON.parse(result);
			$($("form[name='detail-form']").find("input[name='member_id']")).val(json.member_id);
			$($("form[name='detail-form']").find("input[name='id']")).val(json.id);
			$($("form[name='detail-form']").find("input[name='pass']")).val(json.pass);
			$($("form[name='detail-form']").find("input[name='name']")).val(json.name);
		}
	});
}

function del(){
	if(!confirm("삭제하실래요?")){
		return;
	}
	$.ajax({
		url:"/admin/member/delete",
		type:"get",
		data:{
			member_id:$($("form[name='detail-form']").find("input[name='member_id']")).val()
		},
		success:function(result){
			var json=JSON.parse(result);
			if(json.result==1){
				//alert("삭제완료");
				getList();
				//상세보기 초기화 (채워져 있지 않게)
				$("form[name='detail-form']").trigger("reset");
			}else{
				alert("삭제실패");
			}
		}
	});
}

function edit(){
	$.ajax({
		url:"/admin/member/edit",
		type:"post",
		data:{
			member_id:$($("form[name='detail-form']").find("input[name='member_id']")).val(),
			id:$($("form[name='detail-form']").find("input[name='id']")).val(),
			pass:$($("form[name='detail-form']").find("input[name='pass']")).val(),
			name:$($("form[name='detail-form']").find("input[name='name']")).val()
		},
		success:function(result){
			var json=JSON.parse(result);
			if(json.result==1){
				getList();
			}else{
				alert("수정실패")
			}
		}
	});
}
</script>
</head>
<body>
	<div class="wrapper">
		<div class="regist-area">
			<form name="regist-form">
				<input type="text" name="id" placeholder="아이디입력"/>
				<input type="text" name="pass" placeholder="비번입력"/>
				<input type="text" name="name" placeholder="이름입력"/>
				</p>
				<button type="button">등록</button>
			</form>
		</div>
		<div class="list-area"></div>
		<div class="detail-area">
			<form name="detail-form">
				<input type="hidden" name="member_id"/>
				<input type="text" name="id" placeholder="아이디입력"/>
				<input type="text" name="pass" placeholder="비번입력"/>
				<input type="text" name="name" placeholder="이름입력"/>
				</p>
				<button type="button">수정</button>
				<button type="button">삭제</button>
			</form>
		</div>
	</div>
</body>
</html>