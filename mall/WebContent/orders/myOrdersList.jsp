<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="dao.*"%>
<%@ page import="vo.*"%>

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

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>myOrdersList.jsp</title>

<!-- Bootstrap Framework 사용 -->

<!-- Latest compiled and minified CSS -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">

<!-- jQuery library -->
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>

<!-- Popper JS -->
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>

<!-- Latest compiled JavaScript -->
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

<!-- Bootstrap 4 Icons -->
<link rel="stylesheet"
	href="https://use.fontawesome.com/releases/v5.7.0/css/all.css"
	integrity="sha384-lZN37f5QGtY3VHgisS14W3ExzMWZxybE1SJSEsQp9S+oqd12jhcu+A56Ebc1zFSJ"
	crossorigin="anonymous">
</head>
<body>
	<%
		// 요청 인코딩 설정
	request.setCharacterEncoding("UTF-8");

	int currentPage = 1; // 기본적으로 1페이지 표시하기 위함 (현재 페이지)

	if (request.getParameter("currentPage") != null) { // null인지 아닌지 체크
		currentPage = Integer.parseInt(request.getParameter("currentPage")); // currentPage를 파라미터 값으로 변경
	}

	int rowPerPage = 10; // 한 페이지당 데이터를 표시할 개수

	int limit1 = (currentPage - 1) * rowPerPage;
	int limit2 = rowPerPage;

	String thisOrdersState = ""; // 페이지에 출력할 주문 상태
	if (request.getParameter("thisOrdersState") != null) { // null인지 아닌지 체크
		thisOrdersState = request.getParameter("thisOrdersState"); // thisOrdersState를 파라미터 값으로 변경
	}

	OrdersDao ordersDao = new OrdersDao();
	ArrayList<OrdersAndProduct> ordersList;

	ordersList = ordersDao.selectOrdersMyList(session.getAttribute("loginMemberEmail").toString(), limit1, limit2);
	%>

	<!-- 상단 메뉴 -->
	<div>
		<!-- Menu 항목을 include한다 -->
		<jsp:include page="/inc/menu.jsp"></jsp:include>
	</div>

	<div style="margin-top: 35px;"></div>

	<div class="container">
		<h3>주문내역</h3>

		<br>
		<br>

		<table class="table table-hover" style="text-align: center">
			<thead>
				<tr>
					<th>주문 번호</th>
					<th>상품 번호</th>
					<th>상품명</th>
					<th>주문 수량</th>
					<th>주문 가격</th>
					<th>구매자 이메일</th>
					<th>배송지</th>
					<th>주문 상태</th>
					<th>주문 일시</th>
			</thead>
			<tbody>
				<%
					for (OrdersAndProduct oap : ordersList) {
					int ordersId = oap.getOrders().getOrdersId();
					int productId = oap.getOrders().getProductId();
					String productName = oap.getProduct().getProductName();
					int ordersAmount = oap.getOrders().getOrdersAmount();
					int ordersPrice = oap.getOrders().getOrdersPrice();
					String memberEmail = oap.getOrders().getMemberEmail();
					String ordersAddr = oap.getOrders().getOrdersAddr();
					String ordersState = oap.getOrders().getOrdersState();
					String ordersDate = oap.getOrders().getOrdersDate();
				%>
				<tr>
					<td><%=ordersId%></td>
					<td><%=productId%></td>
					<td><%=productName%></td>
					<td><%=ordersAmount%></td>
					<td><%=ordersPrice%></td>
					<td><%=memberEmail%></td>
					<td><%=ordersAddr%></td>
					<td><%=ordersState%></td>
					<td><%=ordersDate%></td>
				</tr>
				<%
					}
				%>
			</tbody>
		</table>

		<br>

		<!-- 페이지 네비게이션 -->
		<ul class="pagination justify-content-center">
			<!-- 처음으로 버튼 -->
			<%
				if (currentPage > 1) { // currentPage가 1보다 클 때만 처음으로 갈 수 있음
			%>
			<li class="page-item"><a class="page-link"
				href="./myOrdersList.jsp?currentPage=1">처음으로</a> <%
 	} else { // 첫 페이지 일 때 처음으로 버튼 표시 안 함
 %>
			<li class="page-item disabled"><a class="page-link" href="#">처음으로</a>
				<%
					}
				%></li>
			<!-- 이전 버튼 -->

			<%
				if (currentPage > 1) { // currentPage가 1보다 클 때만 이전으로 갈 수 있음
			%>
			<li class="page-item"><a class="page-link"
				href="./myOrdersList.jsp?currentPage=<%=currentPage - 1%>">이전</a> <%
 	} else { // 1이거나 그 이하면 버튼 표시 안 함
 %>
			<li class="page-item disabled"><a class="page-link" href="#">이전</a>
				<%
					}
				%></li>
			<!-- 현재 페이지 표시 -->
			<%
				int totalCount = 0; // 전체 데이터 수
			totalCount = ordersDao.countAllData(session.getAttribute("loginMemberEmail").toString());

			int lastPage = totalCount / rowPerPage;
			if (totalCount % rowPerPage != 0) { // 10 미만의 개수의 데이터가 있는 페이지를 표시
				lastPage += 1;
			}

			if (lastPage == 0) { // 전체 페이지가 0개이면 현재 페이지도 0으로 표시
				currentPage = 0;
			}

			int navPerPage = 10; // 네비게이션에 표시할 페이지 수
			int navFirstPage = currentPage - (currentPage % navPerPage) + 1; // 네비게이션 첫번째 페이지
			int navLastPage = navFirstPage + navPerPage - 1; // 네비게이션 마지막 페이지

			if (currentPage % navPerPage == 0 && currentPage != 0) { // 10으로 나누어 떨어지는 경우 처리하는 코드
				navFirstPage = navFirstPage - navPerPage;
				navLastPage = navLastPage - navPerPage;
			}

			for (int i = navFirstPage; i <= navLastPage; i++) {
				if (i <= lastPage) {
					if (i == currentPage) { // 현재 페이지
			%>
			<li class="page-item disabled"><a class="page-link" href="#"><%=i%></a>
			</li>
			<%
				} else { // 다음 페이지
			%>
			<li class="page-item"><a class="page-link"
				href="./myOrdersList.jsp?currentPage=<%=i%>"><%=i%></a></li>
			<%
				}
			}
			}
			%>
			<!-- 다음 버튼 -->
			<%
				if (currentPage < lastPage) { // currentPage가 lastPage보다 작을 때만 다음으로 갈 수 있음
			%>
			<li class="page-item"><a class="page-link"
				href="./myOrdersList.jsp?currentPage=<%=currentPage + 1%>">다음</a> <%
 	} else { // 마지막 페이지 일 때 다음 버튼 표시 안 함
 %>
			<li class="page-item disabled"><a class="page-link" href="#">다음</a>
				<%
					}
				%></li>
			<!-- 마지막으로 버튼 -->
			<%
				if (currentPage < lastPage) { // currentPage가 lastPage보다 작을 때만 마지막으로 갈 수 있음
			%>
			<li class="page-item"><a class="page-link"
				href="./myOrdersList.jsp?currentPage=<%=lastPage%>">마지막으로</a> <%
 	} else { // 마지막 페이지 일 때 마지막으로 버튼 표시 안 함
 %>
			<li class="page-item disabled"><a class="page-link" href="#">마지막으로</a>
				<%
					}
				%></li>
		</ul>

		<!-- 총 페이지 수 출력 -->
		<table style="margin: auto;">
			<tr>
				<td>
					<button type="button" class="btn btn-outline-dark btn-sm">
						<%=currentPage%>
						/
						<%=lastPage%>
						페이지
					</button>
				</td>
			</tr>
		</table>

		<br>
		<br>
	</div>

	<div style="margin-top: 60px;"></div>
</body>
</html>