<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%
	if (session.getAttribute("loginMemberEmail") != null) {	// 로그인 세션 체크
		response.sendRedirect(request.getContextPath() + "/index.jsp");
		return;
	}
%>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>login.jsp</title>

		<!-- Bootstrap Framework 사용 -->
		
		<!-- Latest compiled and minified CSS -->
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
		
		<!-- jQuery library -->
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
		
		<!-- Popper JS -->
		<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
		
		<!-- Latest compiled JavaScript -->
		<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
		
		<!-- Bootstrap 4 Icons -->
		<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.0/css/all.css" integrity="sha384-lZN37f5QGtY3VHgisS14W3ExzMWZxybE1SJSEsQp9S+oqd12jhcu+A56Ebc1zFSJ" crossorigin="anonymous">
	
		<!-- jQuery Google CDN -->
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
		
		<!-- jQuery를 이용하여 로그인 E-mail/PW 검사 -->
		<script>
			$(document).ready(function() {	// 문서가 로드되면 이 스크립트를 제일 마지막에 실행해주세요
				$("#btn").click(function() {	// 버튼 클릭시 폼 내용의 유효성 검사를 수행
					if ($("#memberEmail").val().length < 1) {	// memberEmail이 1 이상이 아닌경우 수행
						alert("이메일을 입력해주세요");
						return;
					}
					else if ($("#memberPw").val() < 1) {		// memberPw가 1 이상이 아닌경우 수행
						alert("비밀번호를 입력해주세요");
						return;
					}
					$("#loginForm").submit();
				});	
			});
		</script>
	</head>
	<body>	
		<!-- 상단 메뉴 -->
		<div>
			<!-- Menu 항목을 include한다 -->
		<jsp:include page="/inc/menu.jsp"></jsp:include>
		</div>
		
		<div style="margin-top: 35px;"></div>
		
		<div class="container">
			
			<table style="margin: auto; text-align: center;">
				<tr>
					<td><h3 style="margin: auto;">로그인</h3></td>
				</tr>
			</table>
			
			<br>
			
			<form method="post" action="<%=request.getContextPath() %>/member/loginAction.jsp" id="loginForm">
				<table width="35%" style="margin: auto; text-align: center;">
					<tr>
						<td><input type="text" class="form-control" name="memberEmail" id="memberEmail" placeholder="E-mail 주소" value="user@user.com"></td>
					</tr>
					<tr>
						<td>
							<input type="password" class="form-control" name="memberPw" id="memberPw" placeholder="비밀번호" value="1234">
						</td>
					</tr>
					<tr>
						<td>
							<div style="margin-top: 15px;"></div>
							<button type="button" class="btn btn-primary btn-block" id="btn">로그인</button>
						</td>
					</tr>
				</table>
			</form>
		</div>
		
		<div style="margin-top: 60px;"></div>
		
	</body>
</html>