<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>login</title>
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
<div class ="container">
	<h1>로그인</h1>
	<form method="post" action="<%=request.getContextPath()%>/member/loginAction.jsp">
	<table class="table">
		<tr>
			<td>member_email</td>
			<td><input type="text" name="memberEmail"></td>
		</tr>
		<tr>
			<td>member_pw</td>
			<td><input type="password" name="memberPw"></td>
		</tr>
	</table>
	<button type="submit">로그인</button>
	</form>
</div>
</body>
</html>