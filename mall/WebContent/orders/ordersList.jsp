<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="dao.*"%>
<%@ page import="vo.*" %>
<%@ page import="java.util.*" %>
<%
   //로그인이 안되어 있으면 로그인 페이지로 이동
   if(session.getAttribute("loginMemberEmail") == null) {
      response.sendRedirect("/mall/member/login.jsp");
      return;
   }
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<!-- 부트 스트랩 -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<!-- 아이콘 사용 -->
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.0/css/all.css">
</head>
<body>
<div class="container">
<div><!-- 상단 메뉴바 -->
      <jsp:include page="/inc/menu.jsp"></jsp:include>
</div>
<%
   request.setCharacterEncoding("utf-8"); // 인코딩
   OrdersDao ordersDao = new OrdersDao();
   // 세션으로 이메일 받아와서 tostring으로 형 변환 
   ArrayList<OrdersAndProduct> orderslist = ordersDao.selectOrdersLsitByEmail(session.getAttribute("loginMemberEmail").toString());
   

%>   
   <h2>내 주문 내역</h2>
   <table class = "table">
      <thead>
         <tr>
            <td>주문 번호</td>
            <td>제품 번호</td>
            <td>제품 이름</td>
            <td>주문 개수</td>
            <td>주문 금액</td>
            <td>주문 주소</td>
            <td>주문 상태</td>
            <td>주문 날짜</td>
         </tr>
      </thead>
      <tbody>
         <%
            for(OrdersAndProduct oap : orderslist){
         %>
            <tr>
               <td><%=oap.getOrders().getOrdersId() %></td>
               <td><%=oap.getOrders().getProductId() %></td>
               <td><%=oap.getProduct().getProductName() %></td>
               <td><%=oap.getOrders().getOrdersAmount() %></td>
               <td><%=oap.getOrders().getOrdersPrice() %></td>
               <td><%=oap.getOrders().getOrdersAddr() %></td>
               <td><%=oap.getOrders().getOrdersState() %></td>
               <td><%=oap.getOrders().getOrdersDate() %></td>
            </tr>
         <%
            }
         %>
      </tbody>      
   </table>
</div>   
</body>
</html>