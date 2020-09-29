<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	session.invalidate();
	response.sendRedirect(request.getContextPath()+"/index.jsp");//로그아웃 성공시 메인으로 돌아감
%>
