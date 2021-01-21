<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="vo.*" %>
<%@ page import="dao.*" %>
<%
	if (session.getAttribute("loginMemberEmail") != null) { // 세션이 유지되어 있는 경우
		response.sendRedirect(request.getContextPath() + "/index.jsp");
		return;
	}

	// 인코딩은 UTF-8로
	request.setCharacterEncoding("UTF-8");
	
	String memberEmail	= request.getParameter("memberEmail");
	String memberPw		= request.getParameter("memberPw");
	
	System.out.println(memberEmail + "<- 이메일");
	System.out.println(memberPw + "<- 패스워드");
	
	if (memberEmail.equals(null) || memberPw.equals(null)) {	// 로그인 ID, PW를 입력하지 않은 경우
		%>
		<script>
			alert("아이디 혹은 비밀번호를 입력해주세요.");
			history.back();	// 로그인 실패시 이전 페이지로 이동
		</script>
	<%
	}
	
	Member paramMember = new Member(); // 파라미터로 넘겨주는 객체
	paramMember.setMemberEmail(memberEmail);
	paramMember.setMemberPw(memberPw);
	
	MemberDao memberDao = new MemberDao();
	Member loginMember = memberDao.login(paramMember);
	
	//System.out.println(memberEmail + " " + memberPw);
	
	if (loginMember != null && loginMember.getMemberSubscription().equals("Y")) { // ID와 PW의 일치여부
		// 로그인 정보를 Session에 저장
		session.setAttribute("loginMemberEmail", loginMember.getMemberEmail());	// 세션에 로그인 성공한 memberEmail을 저장
		session.setAttribute("loginMemberName", loginMember.getMemberName());	// 세션에 로그인 성공한 memberName를 저장
		
		// 로그인 성공시 index.jsp로 이동
		response.sendRedirect(request.getContextPath() + "/index.jsp");
	}
	else {	// 로그인에 실패했을 경우
		if (loginMember.getMemberSubscription().equals("N")) {	// Y면 가입된 사용자, N이면 탈퇴한 사용자
			%>
				<script>
					alert("탈퇴된 사용자입니다.");
					history.back();	// 로그인 실패시 이전 페이지로 이동
				</script>
			<%
		} else {
			%>
				<script>
					alert("아이디 혹은 비밀번호가 틀렸습니다.\n다시 한 번 확인해주세요.");
					history.back();	// 로그인 실패시 이전 페이지로 이동
				</script>
			<%
		}
	}
%>