<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>signup.jsp 회원가입</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script>
	$(document).ready(function(){
		$("#btn").click(function(){
			//유효성 검사 코드 구현
			if($("#memberEmail").val()== ""){
				alert("memberEmail 입력");
				return;
			}else if($("#memberPw").val() =""){
				alert("memberPw 입력");
				return;
			}else if($("#memberName").val() =""){
				alert("memberName입력");
				return;
			}
			// submit to action
			$("#signupForm").submit();
		});
	});
</script>
</head>
<!--w3school.com 사이트에서 가져옴  -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<body>
<div class="container">
	<h1>회원가입</h1>
	  <form method="post" action="<%=request.getContextPath()%>/member/signupAction.jsp" id="signupForm">
	<table class="table table-dark table-striped table-hover">
	
		  	<tr>
				<td>member_email</td>
				<td><input type="text" name="memberEmail" id="memberEmail"></td>
			</tr>
			<tr>
				<td>member_pw</td>
				<td><input type="password" name="memberPw" id="memberPw"></td>
			</tr>
			<tr>
				<td>member_name</td>
				<td><input type="text" name="memberName" id="memberName"></td>
			</tr>
			</table>
			<div>
				<button type="button" id="btn" class="btn btn-primary">회원가입</button>
				<a href="<%=request.getContextPath()%>/member/login.jsp" class="btn btn-secondary">뒤로가기</a>
			</div>
	</form>
</div>
</body>
</html>