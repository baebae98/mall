<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import = "vo.*" %>
<%@ page import = "dao.*" %>
<%@ page import="java.util.*" %>
<%
	//로그인이 안되어 있으면 로그인 페이지로 이동
	if(session.getAttribute("loginMemberEmail") == null) {
	   response.sendRedirect("/mall/member/login.jsp");
	   return;
	}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<!-- 부트 스트랩 -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<!-- 아이콘 사용 -->
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.0/css/all.css">

</head>
<body>

<%
	request.setCharacterEncoding("utf-8"); //인코딩 utf-8로 통일
	MemberDao memberDao = new MemberDao();
	// 세션으로 이메일 받아와서 tostring으로 형 변환 
	System.out.println(session.getAttribute("loginMemberEmail").toString()+"<----------------------");
	Member member = memberDao.selectmyInfoEmail(session.getAttribute("loginMemberEmail").toString());
%>

	<div><!-- 상단 메뉴바 -->
	      <jsp:include page="/inc/menu.jsp"></jsp:include>
	</div>
<div class="container">
<h2>내 정보</h2>
<table class="table table-dark table-striped table-hover">
	<thead>
		<tr>
			<td>이메일</td>
			<td><%=member.getMemberEmail()%></td>
			<td>이름</td>
			<td><%=member.getMemberName() %></td>
			<td>가입날짜</td>
			<td><%=member.getMemberDate() %></td>
			<td><a href="" class="">수정</a></td>
		</tr>
	</thead>
</table>
</div>
</body>
</html>