<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>  
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>Post Info</h1>
	<form name="frm" action="/posts/update" method="post">
		<input name="title" value="${vo.title}" />
		<!-- vo.id가 없으면 update할 때 보낼 id가 없어서 안됨. hidden으로 숨겨서 보내주기 -->
		<input name="id" value="${vo.id}" type="hidden" />
		<hr />
		<textarea rows="10" cols="50" name="body">${vo.body}</textarea>
		<hr />
		<button type="submit">Modify</button>
		<button type="reset">Cancle</button>
		<button id="btnDelete" type="button">Delete</button> <!-- a태그로 주면 a태그가 이동하는 방식이 get이라서 Post나 delete로 해야하는 삭제와 상충됨. -->
	</form>

</body>
</html>
<script>
	$("#btnDelete").on("click", function() {
		if (!confirm('삭제하시겠습니까?'))
			reutrn;
		frm.action = "/posts/delete";
		frm.method = "post";
		frm.submit();
	})
</script>