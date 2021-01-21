<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="dao.*" %>
<%@ page import="vo.*" %>
<%
	if (session.getAttribute("loginMemberEmail") != null) { // 세션이 유지되어 있는 경우
		response.sendRedirect(request.getContextPath() + "/index.jsp");
		
		return;
	} else { // 세션이 없는 경우
		request.setCharacterEncoding("utf-8");
		
		String memberEmail	= request.getParameter("memberEmail");
		String memberPw 	= request.getParameter("memberPw");
		String memberName	= request.getParameter("memberName");
		
		//System.out.println(memberEmail + " " + memberPw + " " + memberName);
		
		if (memberEmail.equals(null) || memberPw.equals(null) || memberName.equals(null)) {	// 회원가입 페이지를 거치지 않은 경우
			response.sendRedirect(request.getContextPath() + "/index.jsp");	
		}
		
		// 사용 가능한 이메일 검증
		MemberDao memberDao	= new MemberDao();
		Member member		= memberDao.selectMemberEmailCk(memberEmail);
		
		if (member != null) { // 중복된 계정이 있는 경우
			// 사용중인 계정입니다.
			response.sendRedirect(request.getContextPath() + "/member/login.jsp");	
		
			return;
		}
		
		Member paramMember = new Member();
		paramMember.setMemberEmail(memberEmail);
		paramMember.setMemberPw(memberPw);
		paramMember.setMemberName(memberName);
		
		memberDao.insertMember(paramMember);	// 회원가입 메소드 호출
		
		%>
			<script>
				alert("회원가입되었습니다.\n로그인을 해 주세요.");
				location.href="<%=request.getContextPath() %>/member/login.jsp";
			</script>
		<%
	}
%>