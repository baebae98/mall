<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="dao.*" %>
<%@ page import="vo.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>productOne</title>
</head>
	<!-- Bootstrap Framework 사용 -->
		
		<!-- Latest compiled and minified CSS -->
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
		
		<!-- jQuery library -->
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
		
		<!-- Popper JS -->
		<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
		
		<!-- Latest compiled JavaScript -->
		<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
		
		<!-- Bootstrap 4 Icons -->
		<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.0/css/all.css" integrity="sha384-lZN37f5QGtY3VHgisS14W3ExzMWZxybE1SJSEsQp9S+oqd12jhcu+A56Ebc1zFSJ" crossorigin="anonymous">
<body>
<%
	int productId = Integer.parseInt(request.getParameter("productId"));
	ProductDao productDao = new ProductDao();
	Product product = productDao.selectProductOne(productId);
%>
<div class="container">
	<h1>상품 상세보기</h1>
	<form method="post" action="<%=request.getContextPath()%>/orders/addOrdersAction.jsp">
		<input type="hidden" value="<%=product.getProductId()%>" name="productId">
		<input type="hidden" value="<%=product.getProductPrice()%>" name="productPrice"> 
		<div>
			수량 : 
			<select name="ordersAmount">
				<%
					for(int i=1; i<11; i+=1) {
				%>
						<option value="<%=i%>"><%=i%></option>
				<%		
					}
				%>
			</select>개
		</div>
		<div>
			배송주소 :
			<input type="text" name="ordersAddr">
		</div>
		<div><button type="submit">주문</button></div>
	</form>
	<table class="table">
		<tr>
			<td>product_id</td>
			<td><%=product.getProductId()%></td>
		</tr>
		<tr>
			<td>product_pic</td>
			<td><img src="<%=request.getContextPath()%>/images/<%=product.getProductPic()%>"></td>
		</tr>
		<tr>
			<td>product_name</td>
			<td><%=product.getProductName() %></td>
		</tr>
		<tr>
			<td>product_content</td>
			<td><%=product.getProductContent() %></td>
		</tr>
		<tr>
			<td>product_price</td>
			<td><%=product.getProductPrice() %></td>
		</tr>
		<tr>
			<td>product_soldout</td>
			<td><%=product.getProductSoldout() %></td>
		</tr>
	</table>
</div>
</body>
</html>