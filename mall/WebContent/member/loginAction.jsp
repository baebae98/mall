<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import ="dao.*" %>
<%@ page import ="vo.*" %>
<%
	//로그인중이여도 이 파일을 접근했을 때 강제로 index로 보냄
	if(session.getAttribute("loginMemberEmail") !=null){
		response.sendRedirect(request.getContextPath()+"/index.jsp");
		return; //밑에 파일이 다시 실행못하도록 여기서 리턴
	}

	request.setCharacterEncoding("utf-8");
	String memberEmail = request.getParameter("memberEmail");
	String memberPw = request.getParameter("memberPw");
	Member paramMember = new Member();
	//paramMember.memberEmail = memberEmail;
	//paramMember.memberPw = memberPw;
		paramMember.setMemberEmail(memberEmail);//캡슐화코드추가
		paramMember.setMemberPw(memberPw);

	MemberDao memberDao = new MemberDao();
	String loginmemberEmail = memberDao.login(paramMember);
	if(loginmemberEmail ==null){
		response.sendRedirect(request.getContextPath()+"/member/login.jsp");
	}else{
		session.setAttribute("loginMemberEmail", loginmemberEmail);
		response.sendRedirect(request.getContextPath()+"/index.jsp");
	}
%>
