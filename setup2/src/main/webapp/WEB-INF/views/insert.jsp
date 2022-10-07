<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
<title>Insert title here</title>
</head>
<body>
	<form name="frm" method="post" enctype="multipart/form-data"
		action="/api/user/insert">
<!-- 			action은 controller를 인식하지 @RestController는 인식못해서 400번 오류뜸.
 -->		<input type="text" name='uid' />
		<hr />
		<input  type="text" name='uname' />
		<hr />
		<input  type="text" name='upass' />
		<hr />
		<input name="file" type="file">
		<button>upload</button>

		<!-- <img src="/display?fileName=/upload/photo/img1.png"/>
	<img src="/display?fileName=/upload/photo/1665101598250img3.jpg"/> -->

	</form>
</body>
</html>
<script>
$(frm).on("submit",function(){
	e.preventDefault();
/* 	input type file의 첫번째 중에서도 첫번쨰를 고름. input file 하나에 여러개 파일을 담을 수 있고, 그런 input file이 여러개 있을 수 있기 때문에 아래와 같이 적음.
 */	const file=$(frm.file[0]).files[0];
	const uid=$(frm.uid).val();
	const uname=$(frm.uname).val();
	const upass=$(frm.upass).val();
	const formData=new FormData();
	formData.append("file",file);
	formData.append("uid",uid);
	formData.append("uname",uname);
	formData.append("upass",upass);
	
	$.ajax({
		type:'post',
		url:'/api/user/insert',
		data:formData,
	}).done(function(){
		alert("성공!")
		location.href='/'
	})

})
</script>
