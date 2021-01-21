<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="dao.*"%>
<%@ page import="vo.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>noticOne.jsp</title>

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
		int noticeId = Integer.parseInt(request.getParameter("noticeId"));
	System.out.println("noticeId: " + noticeId); // noticeId 디버깅

	Notice notice = new NoticeDao().selectNoticeOne(noticeId); // 하나의 메소드만 호출할 때 사용
	System.out.println(notice);
	%>

	<!-- 상단 메뉴 -->
	<div>
		<!-- Menu 항목을 include한다 -->
		<jsp:include page="/inc/menu.jsp"></jsp:include>
	</div>

	<div style="margin-top: 35px;"></div>

	<div class="container">
		<h3>
			<a style="color: black;"
				href="<%=request.getContextPath()%>/notice/noticeList.jsp">공지사항</a>
		</h3>

		<br>

		<table class="table table-hover" style="text-align: center;">
			<tr>
				<td>번호</td>
				<td><%=notice.getNoticeId()%></td>
			</tr>
			<tr>
				<td>게시일자</td>
				<td><%=notice.getNoticeDate()%></td>
			</tr>
			<tr>
				<td>제목</td>
				<td><%=notice.getNoticeTitle()%></td>
			</tr>
			<tr>
				<td>내용</td>
				<td><%=notice.getNoticeContent()%></td>
			</tr>
		</table>
	</div>

	<div style="margin-top: 60px;"></div>


</body>
</html>