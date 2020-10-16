<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>login</title>
<!-- 
	스크립트
	스타일시트
 -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>

<!-- 로그인 폼 페이지 -->
<%
	//로그인 
	if(session.getAttribute("loginMemberEmail") !=null){
		response.sendRedirect(request.getContextPath()+"/index.jsp");
		return;
	}
%>
<script>
	$(document).ready(function(){
		$("#btn").click(function(){
		if($("#memberEmail").val().length<12){
			alert("이메일확인");
			return;
		}else if($("#memberPw").val().length<4){
			alert("pw 확인");
			return;
		}
		$("#loginForm").submit();
		});
	});
</script>
	<h1>로그인</h1>
	<form method="post" action="<%=request.getContextPath()%>/member/loginAction.jsp" id="loginForm">
	<table class="table-dark table-striped table-hover ">
		<tr>
			<td>member_email</td>
			<td><input type="text" name="memberEmail" id="memberEmail"></td>
		</tr>
		<tr>
			<td>member_pw</td>
			<td><input type="password" name="memberPw" id="memberPw"></td>
		</tr>
	</table>
	<button type="button" class="btn btn-danger text-right" id="btn">로그인</button>
	</form>
</body>
</html>