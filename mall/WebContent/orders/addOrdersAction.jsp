<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="dao.*" %>
<%@ page import="vo.*" %>

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
	request.setCharacterEncoding("UTF-8");
	
	int productId = Integer.parseInt(request.getParameter("productId"));
	int ordersAmount = Integer.parseInt(request.getParameter("ordersAmount"));
	int ordersPrice = Integer.parseInt(request.getParameter("productPrice"));
	String memberEmail = session.getAttribute("loginMemberEmail").toString();
	String ordersAddr = request.getParameter("ordersAddr");
	
	Orders orders = new Orders();
	
	orders.setProductId(productId);
	orders.setOrdersAmount(ordersAmount);
	orders.setOrdersPrice(ordersPrice * ordersAmount);
	orders.setMemberEmail(memberEmail);
	orders.setOrdersAddr(ordersAddr);
	
	//System.out.println(orders);
	//System.out.println(productId + "/" + ordersAmount + "/" + ordersPrice * ordersAmount + "/" + memberEmail + "/" + ordersAddr);
	
	OrdersDao ordersDao = new OrdersDao();
	ordersDao.insertOrders(orders);
	response.sendRedirect(request.getContextPath() + "/orders/myOrdersList.jsp");
%>