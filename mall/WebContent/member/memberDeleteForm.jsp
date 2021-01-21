<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="dao.*"%>
<%@ page import="vo.*"%>

<%
	if (session.getAttribute("loginMemberEmail") == null) { // 세션이 유지되어 있는 경우
%>
<script>
	alert("로그인이 필요합니다.");
</script>
<%
	response.sendRedirect(request.getContextPath() + "/member/login.jsp");
return;
}
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>memberInfo.jsp</title>

<!-- Bootstrap Framework 사용 -->

<!-- Latest compiled and minified CSS -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">

<!-- jQuery library -->
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>

<!-- Popper JS -->
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>

<!-- Latest compiled JavaScript -->
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

<!-- Bootstrap 4 Icons -->
<link rel="stylesheet"
	href="https://use.fontawesome.com/releases/v5.7.0/css/all.css"
	integrity="sha384-lZN37f5QGtY3VHgisS14W3ExzMWZxybE1SJSEsQp9S+oqd12jhcu+A56Ebc1zFSJ"
	crossorigin="anonymous">
</head>
<body>
	<!-- 상단 메뉴 -->
	<div>
		<!-- Menu 항목을 include한다 -->
		<jsp:include page="/inc/menu.jsp"></jsp:include>
	</div>

	<div style="margin-top: 35px;"></div>

	<div class="container">
		<h3>회원탈퇴</h3>

		<br>

		<form method="post"
			action="<%=request.getContextPath()%>/member/memberDeleteAction.jsp">
			<table class="table table-hover" style="text-align: center">
				<tr>
					<td colspan="2">정말로 회원탈퇴를 진행하시겠습니까?<br>비밀번호를 다시 한 번
						입력해주세요.
					</td>
				</tr>
				<tr>
					<td>비밀번호</td>
					<td><input type="password" class="form-control"
						name="password"></td>
				</tr>
				<tr>
					<td colspan="2"><button type="submit"
							class="btn btn-danger btn-sm">회원탈퇴</button></td>
				</tr>
			</table>
		</form>
	</div>

	<div style="margin-top: 60px;"></div>
</body>
</html>