<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>signup.jsp 회원가입</title>
</head>
<!--w3school.com 사이트에서 가져옴  -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<body>
<div class="container">
	<h1>회원가입</h1>
	<form method="post" action="<%=request.getContextPath()%>/member/signupAction.jsp">
	<table class="table">
	
			<tr>
				<td>member_email</td>
				<td><input type="text" name="memberEmail"></td>
			</tr>
			<tr>
				<td>member_pw</td>
				<td><input type="password" name="memberPw"></td>
			</tr>
			<tr>
				<td>member_name</td>
				<td><input type="text" name="memberName"></td>
			</tr>
			<tr>
				<td></td>
				<td></td>
			</tr>
	</table>
	<button type="submit">회원가입</button>
	</form>
</div>
</body>
</html>