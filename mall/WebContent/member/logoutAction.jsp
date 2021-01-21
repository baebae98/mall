<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	session.invalidate();	// 세션 지우기
	response.sendRedirect(request.getContextPath() + "/index.jsp");	// 로그인 페이지로 리다이렉트
%>