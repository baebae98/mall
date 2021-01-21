<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ page import="java.util.*"%>
<%@ page import="dao.*"%>
<%@ page import="vo.*"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>index.jsp</title>

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

	CategoryDao categoryDao = new CategoryDao(); // 카테고리 객체 생성
	NoticeDao noticeDao = new NoticeDao(); // 공지사항 객체 생성

	// 전체 카테고리 리스트
	ArrayList<Category> categoryList1 = categoryDao.selectCategoryListAll();

	// 추천 카테고리 리스트
	ArrayList<Category> categoryList2 = categoryDao.selectCategoryCkList();

	// Index 공지사항 리스트
	ArrayList<Notice> noticeList = noticeDao.selectNoticeIndexList();
	%>

	<!-- 상단 메뉴 -->
	<div>
		<!-- Menu 항목을 include한다 -->
		<jsp:include page="/inc/menu.jsp"></jsp:include>
	</div>

	<div class="container">

		<div style="margin-top: 5px;"></div>

		<!-- 전체 카테고리 / 이미지 배너 -->
		<div class="row">
			<!-- 전체 카테고리 -->
			<div class="col-sm-3">
				<a class="nav-link"
					href="<%=request.getContextPath()%>/product/productList.jsp">
					<button type="button" class="btn btn-primary btn-block">전체
						카테고리</button>
				</a>
				<%
					for (Category c : categoryList1) {
					int categoryId = c.getCategoryId();
					String categoryName = c.getCategoryName();
				%>
				<a class="nav-link"
					href="<%=request.getContextPath()%>/product/productList.jsp?thisCategoryId=<%=categoryId%>&thisCategoryName=<%=categoryName%>">
					<button type="button" class="btn btn-secondary btn-block"><%=categoryName%></button>
				</a>
				<%
					}
				%>
			</div>

			<!-- 이미지 배너 -->
			<div class="col-sm-9">
				<div style="margin-top: 8px;"></div>

				<img width="825" height="310"
					src="<%=request.getContextPath()%>/image/banner.png">
			</div>
		</div>

		<div style="margin-top: 30px;"></div>

		<!-- 추천 카테고리 이미지 링크 -->
		<div class="row" style="text-align: center;">
			<%
				for (Category c : categoryList2) {
				int categoryId = c.getCategoryId();
				String categoryPic = c.getCategoryPic();
				String categoryName = c.getCategoryName();
			%>
			<div class="col-sm-3">
				<table width="100%">
					<tr>
						<td><a
							href="<%=request.getContextPath()%>/product/productList.jsp?thisCategoryId=<%=categoryId%>"><img
								class="rounded-circle" width="200" height="200"
								src="/mall-admin/image/<%=categoryPic%>"></a></td>
					</tr>
					<tr>
						<td><a style="color: black;"
							href="<%=request.getContextPath()%>/product/productList.jsp?thisCategoryId=<%=categoryId%>"><%=categoryName%></a>
						</td>
					</tr>
				</table>
			</div>
			<%
				}
			%>
		</div>

		<div style="margin-top: 60px;"></div>

		<!-- 오늘의 추천상품 -->
		<%
			Calendar today = Calendar.getInstance(); // new Calender
		%>

		<div>
			<table>
				<tr>
					<td>
						<h3>오늘의 추천상품</h3>
					</td>
					<td>&nbsp;</td>
					<td><span class="badge badge-info"> <font size="2"><%=today.get(Calendar.YEAR)%>.<%=today.get(Calendar.MONTH) + 1%>.<%=today.get(Calendar.DAY_OF_MONTH)%></font>
					</span></td>
				</tr>
			</table>
		</div>


		<div class="row">
			<table width="100%">
				<tr>
					<td><a class="nav-link"
						href="<%=request.getContextPath()%>/product/productList.jsp">
							<button type="button" class="btn btn-primary btn-block">전체
								카테고리</button>
					</a></td>

					<%
						for (Category c : categoryList1) {
						int categoryId = c.getCategoryId();
						String categoryName = c.getCategoryName();
					%>
					<td><a class="nav-link"
						href="<%=request.getContextPath()%>/product/productList.jsp?thisCategoryId=<%=categoryId%>">
							<button type="button" class="btn btn-secondary btn-block"><%=categoryName%></button>
					</a></td>
					<%
						}
					%>
				</tr>
			</table>
		</div>

		<div style="margin-top: 10px;"></div>

		<%
			ProductDao productDao = new ProductDao();
		ArrayList<Product> productList = productDao.selectProductPicList();
		%>
		<!-- 상품목록 6개 -->
		<div>
			<table>
				<tr>
					<%
						int i = 0;
					for (Product p : productList) {
						int productId = p.getProductId();
						String productName = p.getProductName();
						int productPrice = p.getProductPrice();
						String productPic = p.getProductPic();

						if (i % 3 == 0 && i != 0) { // 줄바꿈
					%>
				</tr>
				<tr>
					<%
						}
					%>
					<td>
						<div class="card"
							style="width: 348px; margin-top: 10px; margin-bottom: 10px; margin-left: 10px; margin-right: 10px;">
							<a
								href="<%=request.getContextPath()%>/product/productOne.jsp?productId=<%=productId%>">
								<img width="300px" height="340px" class="card-img-top"
								src="/mall-admin/image/<%=productPic%>" alt="Card image">
							</a>
							<div class="card-body">
								<h4 class="card-title" style="text-align: right;">
									<a style="color: black;"
										href="<%=request.getContextPath()%>/product/productOne.jsp?productId=<%=productId%>">
										<font size="4"><%=productName%></font>
									</a>
								</h4>
								<p class="card-text">
									<font size="5"><%=productPrice%>원</font>
								</p>
							</div>
						</div>
					</td>
					<%
						i++;
					}
					%>
				</tr>
			</table>
		</div>

		<div style="margin-top: 60px;"></div>

		<!-- 최근 공지사항 -->
		<div class="row">
			<div class="col-sm-2">
				<h3>
					<a style="color: black;"
						href="<%=request.getContextPath()%>/notice/noticeList.jsp">공지사항</a>
				</h3>
			</div>

			<div class="col-sm-10">
				<table style="text-align: left">
					<tbody>
						<tr>
							<td>
								<ul>
									<%
										for (Notice n : noticeList) {
										int noticeId = n.getNoticeId();
										String noticeTitle = n.getNoticeTitle();
									%>

									<li><a style="color: black;"
										href="<%=request.getContextPath()%>/notice/noticeOne.jsp?noticeId=<%=noticeId%>">
											<%=noticeTitle%></a></li>
									<%
										}
									%>
								</ul>
							</td>
						</tr>
					</tbody>
				</table>
			</div>
		</div>
	</div>

	<div style="margin-top: 30px;"></div>

</body>
</html>