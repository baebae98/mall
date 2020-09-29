<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="dao.*" %>
<%@ page import="vo.*" %>

<%
	//로그인중이여도 이 파일을 접근했을 때 강제로 index로 보냄
	if(session.getAttribute("loginMemberEmail") !=null){
		response.sendRedirect(request.getContextPath()+"/index.jsp");
		return; //밑에 파일이 다시 실행못하도록 여기서 리턴
	}
	//인코딩방법
	request.setCharacterEncoding("utf-8");	
	//...
	String memberEmail = request.getParameter("memberEmail");
	String memberPw = request.getParameter("memberPw");
	String memberName = request.getParameter("memberName");
	//String memberData = request.getParameter("memberData");
	
	//사용가능한 이메일인지 확인(검증)
	MemberDao memberDao = new MemberDao();
	Member member = memberDao.selectMemberEmailCk(memberEmail);//가입 이메일 체크 메서드 호출
	if(member !=null){
			System.out.println("이미 사용중인 이메일.");
		//out.print("script>alert('이미 사용중인 이메일입니다.')</script>");
		response.sendRedirect(request.getContextPath()+"/member/signup.jsp"); 
		return; //다시 리턴
	}
	
	Member paramMember = new Member();
	//paramMember.memberEmail = memberEmail;
	//paramMember.memberPw = memberPw;
	//paramMember.memberName = memberName;
	paramMember.setMemberEmail(memberEmail);
	paramMember.setMemberPw(memberPw);
	paramMember.setMemberName(memberName);
	memberDao.insertMember(paramMember);//회원가입 메서드 호출
	
	response.sendRedirect(request.getContextPath()+"/member/login.jsp"); //회원가입성공시 로그인페이지로 이동	
%>