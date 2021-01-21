<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="dao.*" %>
<%@ page import="vo.*" %>

<html>
	<body>
		<%
			if (session.getAttribute("loginMemberEmail") == null) { // 세션이 없는 경우
				%>
					<script>
						alert("로그인이 필요합니다.");
					</script>
				<%
				
				response.sendRedirect(request.getContextPath() + "/member/login.jsp");
				return;
			}
		%>
		
		<%
			//요청 인코딩 설정
			request.setCharacterEncoding("UTF-8");
			
			String loginEmail = session.getAttribute("loginMemberEmail").toString(); // 현재 로그인된 계정
			String inputPassword = request.getParameter("password");	// 입력받은 패스워드
			
			Member member = new Member();	// Member 데이터 타입을 생성
			member.setMemberEmail(loginEmail);
			member.setMemberPw(inputPassword);
			
			MemberDao passwordDao = new MemberDao();
			Member checkMember = passwordDao.passwordCheck(member);	// 패스워드 일치여부 확인 메소드
			
			System.out.println("나의 비밀번호: "+ checkMember.getMemberPw());
			System.out.println("확인해야될 비밀번호: "+ inputPassword);
			
			if (checkMember.getMemberPw().equals(inputPassword)) {	// 비밀번호가 일치할 때
				MemberDao memberDao = new MemberDao();
				memberDao.updateMemberSubscription(member);
				
				session.invalidate();	// 세션 지우기
				
				%>
					<script>
						alert("탈퇴되었습니다");
						location.href="<%=request.getContextPath() %>/index.jsp";
					</script>
				<%
			} else {	// 비밀번호가 일치하지 않을 때
				%>
					<script>
						alert("비밀번호를 확인하세요.");
						history.back();
					</script>
				<%
			}
		%>
	</body>
</html>