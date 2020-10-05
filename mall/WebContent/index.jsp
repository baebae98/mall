<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="dao.*"%>
<%@ page import="vo.*"%>
<%@ page import="java.util.*"%>
<%@ page import="java.text.SimpleDateFormat" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
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
</head>
<%
	//객체선언
	CategoryDao categoryDao = new CategoryDao();
	// 전체 카테고리 이름 리스트
	ArrayList<Category> categoryList1 = categoryDao.selectCategoryList();
	// 추천 카테고리 이미지 리스트(4개)
	ArrayList<Category> categoryList2 = categoryDao.selectCategoryCKList();
%>
<body>
	<div class="container">
	<br/>
	<br/>
		<div>
			<!-- 최상단 검색바 -->
			<div class="row">
				<div class="col-sm-3"><a href="index.jsp" class="text-dark"><h2>Goodee Shop</h2></a></div>
				<div class="col">
					<form>
					<table>
						<tr>
						<td width="400px">
									<input type="text" class="form-control col-sm-15" name="search">
								</td>
								<td width="100px">
									<button type="submit" class="btn btn-dark">검색</button>
						</tr>
					</table>
					</form>
				</div>
				<div class="col-sm-3">
					<a href="" class="text-dark"><i class='fas fa-user-alt' style='font-size: 36px'></i></a> <!-- 사람모양 아이콘 -->
					<i>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</i>
					<a href="/mall/orders/ordersList.jsp" class="text-dark"><i class='fas fa-shopping-cart' style='font-size: 36px'></i></a><!-- 쇼핑카트 아이콘 -->
				</div>
			</div>
		</div>
				<div style="margin-top: 20px;"></div>
		<div>
			<!-- 로그인/회원가입 메뉴바 -->
			<nav class="navbar navbar-expand-sm bg-dark navbar-dark">
			<ul class="navbar-nav mr-auto"></ul>
			<ul class="navbar-nav mr-right">
				<%
					//로그인 안했을떄 로그인/회원가입 보여짐
					if(session.getAttribute("loginMemberEmail")==null){
				%>
				<!-- 로그아웃인 상태-->
				
					<li class="nav-item"><a class="nav-link" href="<%=request.getContextPath()%>/member/login.jsp">로그인</a></li>
					<li class="nav-item"><a class="nav-link" href="<%=request.getContextPath()%>/member/signup.jsp">회원가입</a></li>
				<%
					}else{
				%>
				<!-- 로그인 상태-->
					<li class="nav-item"><a class="nav-link"><%=(session.getAttribute("loginMemberEmail"))%>님 반갑습니다.</a></li>
					<li class="nav-item"><a class="nav-link" href="<%=request.getContextPath()%>/member/logoutAction.jsp">로그아웃</a></li>
					<li class="nav-item"><a class="nav-link" href="<%=request.getContextPath()%>/member/">회원정보</a></li>
				<%
					}
				%>
				</ul>
			</nav>
		</div>
				<div style="margin-top: 5px;"></div>
			<!-- 전체카테고리3 / 이미지 배너9 -->
			<div class="row">
			<div class="row">
				<!-- 전체 카테고리 -->
				<div class="col-sm-3">
					<a class="nav-link" href="<%=request.getContextPath() %>/product/productList.jsp">
						<button type="button" class="btn btn-primary btn-block">전체 카테고리</button>
					</a>
					<%
						for (Category c : categoryList1) {
							%>
								<a class="nav-link" href="<%=request.getContextPath() %>/product/productList.jsp?thisCategoryId=<%=c.getCategoryId() %>">
									<button type="button" class="btn btn-secondary btn-block"><%=c.getCategoryName()%></button>
								</a>
							<%
						}
					%>
				</div>
				<!-- 이미지 배너 -->
				<div class="col-sm-9">
					<div style="margin-top: 8px;"></div>
				
					<img width="825" height="310" src="<%=request.getContextPath() %>/images/b1.PNG">
				</div>
			</div>
			
			<div style="margin-top: 30px;"></div>

		<!-- 추천 카테고리 이미지 링크 -->
		<div class="row" style="text-align:center;">
				<%
					for (Category c : categoryList2) {
						%>
							<div class="col-sm-3">
								<table width="100%">
									<tr>
										<td>
											<a href=""><img class="rounded-circle" width="200" height="200" src="<%=request.getContextPath() %>/images/<%=c.getCategoryPic() %>"></a>
										</td>
									</tr>
									<tr>
										<td>
											<a href="" class="text-dark"><%=c.getCategoryName() %></a>
										</td>
									</tr>
								</table>
							</div>
						<%
					}
				%>
			</div>

		<%
			Calendar today = Calendar.getInstance(); // new Calendar()
		%>
		<!-- 카테고리별 추천상품 -->
		<div>
			<h3>
				오늘의 추천 상품 <span class="badge badge-primary"> <%=today.get(Calendar.YEAR)%>.<%=today.get(Calendar.MONTH) + 1%>.<%=today.get(Calendar.DAY_OF_MONTH)%>
				</span>
			</h3>
			<div>
				<%
					for (Category c : categoryList1) {
				%>
				<a href="" class="btn"><%=c.getCategoryName()%></a>
				<%
					}
				%>
			</div>
		</div>

		<%
			ProductDao productDao = new ProductDao();
		ArrayList<Product> productList = productDao.selectProductList();
		%>
		<!-- 상품 목록 6개 -->
		<table>
			<tr>
				<%
					int i = 0;
					for (Product p : productList) {
						i=i+1;
				%>
						<td>
							<div class="card" style="width: 400px">
								<img class="card-img-top" src="<%=request.getContextPath()%>/images/<%=p.getProductPic()%>">
								<div class="card-body">
									<h4 class="card-title"><a href="<%=request.getContextPath()%>/product/productOne.jsp?productId=<%=p.getProductId()%>"><%=p.getProductName()%></a></h4>
									<p class="card-text"><%=p.getProductPrice()%></p>
								</div>
							</div>
						</td>
				<%
						if(i%3==0) {
				%>
							</tr><tr>
				<%			
						}
					}
				%>
			</tr>
		</table>
		
		<!-- 최근 공지 2개 -->
		<%
			NoticeDao noticeDao = new NoticeDao();
			ArrayList<Notice> noticeList = noticeDao.selectNoticeList();
		%>
		<div>
			<table class="table">
				<thead>
					<tr>
						<td>notice_id</td>
						<td>notice_title</td>
						<td></td>
					</tr>
				</thead>
				<tbody>
					<%
						for(Notice n : noticeList){
					%>
						<tr>
							<td><a href="<%=request.getContextPath()%>/notice/noticeOne.jsp"><%=n.getNoticeId() %></a></td>
							<td>
							<a href="<%=request.getContextPath()%>/notice/noticeOne.jsp"><%=n.getNoticeTitle()%></a></td>
						</tr>
					<%
						}
					%>
				</tbody>
			</table>
		</div>
	</div>
	</div>
</body>
</html>