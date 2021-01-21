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
	<%
		// 요청 인코딩 설정
	request.setCharacterEncoding("UTF-8");

	String memberEmail = session.getAttribute("loginMemberEmail").toString(); // 현재 세션에 저장되어있는 회원 정보

	Member member = new Member();
	member.setMemberEmail(memberEmail);

	MemberDao memberDao = new MemberDao();
	memberDao.selectMemberOne(member);
	%>

	<!-- 상단 메뉴 -->
	<div>
		<!-- Menu 항목을 include한다 -->
		<jsp:include page="/inc/menu.jsp"></jsp:include>
	</div>

	<div style="margin-top: 35px;"></div>

	<div class="container">
		<h3>개인정보 관리</h3>

		<br>

		<table class="table table-hover" style="text-align: center">
			<%
				String memberName = member.getMemberName();
			String memberDate = member.getMemberDate();
			String memberSubscription = member.getMemberSubscription();
			%>
			<tr>
				<td>이름</td>
				<td><%=memberName%></td>
			</tr>
			<tr>
				<td>이메일</td>
				<td><%=memberEmail%></td>
			</tr>
			<tr>
				<td>가입일자</td>
				<td><%=memberDate%></td>
			</tr>
			<tr>
				<td>가입/탈퇴 여부</td>
				<td>
					<%
						if (memberSubscription.equals("Y")) { // 가입되어있을 때
					%>정상가입<%
						} else { // 탈퇴한 회원일 때
					%>탈퇴한 사용자<%
						}
					%>
				</td>
			</tr>
			<tr>
				<td>탈퇴하기</td>
				<td>
					<%
						if (memberSubscription.equals("Y")) { // 가입되어있을 때
					%><button type="button" class="btn btn-danger btn-sm"
						onclick="location.href='<%=request.getContextPath()%>/member/memberDeleteForm.jsp'">회원탈퇴</button>
					<%
						} else { // 탈퇴한 회원일 때
					%>탈퇴완료<%
						}
					%>
				</td>
			</tr>
		</table>
	</div>

	<div style="margin-top: 60px;"></div>



</body>
</html>