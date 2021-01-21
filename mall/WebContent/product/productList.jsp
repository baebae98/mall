<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="dao.*" %>
<%@ page import="vo.*" %>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>productList.jsp</title>
		
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
	<body>
		<%
			// 요청 인코딩 설정
			request.setCharacterEncoding("UTF-8");
		
			int currentPage = 1;	// 기본적으로 1페이지 표시하기 위함
			
			if (request.getParameter("currentPage") != null) { // null인지 아닌지 체크
				currentPage = Integer.parseInt(request.getParameter("currentPage"));	// currentPage를 파라미터 값으로 변경
			}
			
			int thisCategoryId = 0;	// 0인경우 전체목록, 0이 아닌경우 특정 카테고리별 상품목록 출력
			if (request.getParameter("thisCategoryId") != null) { // null인지 아닌지 체크
				thisCategoryId = Integer.parseInt(request.getParameter("thisCategoryId")); // thisCategoryId를 파라미터 값으로 변경
			}
			
			String thisCategoryName = "전체 카테고리";
			if (request.getParameter("thisCategoryName") != null) { // null인지 아닌지 체크
				thisCategoryName = request.getParameter("thisCategoryName"); // thisCategoryName을 파라미터 값으로 변경
			}
			
			int rowPerPage = 10; // 한 페이지당 데이터를 표시할 개수
			
			int limit1 = (currentPage - 1) * rowPerPage;
	        int limit2 = rowPerPage;
	        
	        // 프로덕트 객체 생성
	        ProductDao productDao = new ProductDao();
	        
	        // 프로덕트 리스트
	        ArrayList<Product> productList = null;
	        if (thisCategoryId == 0) {	// 카테고리 선택 안했을 때
	        	productList = productDao.selectProductList(limit1, limit2);
	        } else { // 특정 카테고리에 있는 물품 검색시
	        	productList = productDao.selectProductListById(thisCategoryId, limit1, limit2);
	        }
	        
	        CategoryDao categoryDao = new CategoryDao();
	        
	        // 카테고리 선택메뉴
	        ArrayList<Category> categoryList = categoryDao.selectCategoryListAll();
		%>
		<!-- 상단 메뉴 -->
	<div>
		<!-- Menu 항목을 include한다 -->
		<jsp:include page="/inc/menu.jsp"></jsp:include>
	</div>
		
		<div style="margin-top: 35px;"></div>
		
		<div class="container">
			<h3><%=thisCategoryName %></h3>
			
			<br>
			
			<div class="row">
				<table width="100%">
					<tr>
						<td>
							<a class="nav-link" href="<%=request.getContextPath() %>/product/productList.jsp">
								<button type="button" class="btn btn-primary btn-block">전체 카테고리</button>
							</a>
						</td>
						
						<%
							for (Category c : categoryList) {
								int categoryId = c.getCategoryId();
								String categoryName = c.getCategoryName();
								%>
									<td>
										<a class="nav-link" href="<%=request.getContextPath() %>/product/productList.jsp?thisCategoryId=<%=categoryId %>&thisCategoryName=<%=categoryName %>">
											<button type="button" class="btn btn-secondary btn-block"><%=categoryName %></button>
										</a>
									</td>
								<%
							}
						%>
					</tr>
				</table>
			</div>
			
			<br>
			
			<%
				if (productList.size() != 0) {	// 상품이 존재할 때
					%>
						<table class="table" style="text-align: center;">
							<%
								for (Product p : productList) {
									int productId = p.getProductId();
									String productPic = p.getProductPic();
									String productName = p.getProductName();
									int productPrice = p.getProductPrice();
									String productSoldout = p.getProductSoldout();
									%>
										<tr>
											<td rowspan="3" width="30%">
												<a href="<%=request.getContextPath() %>/product/productOne.jsp?productId=<%=productId %>">
													<img width="300px" height="300px"src="/mall-admin/image/<%=productPic %>">
												</a>
											</td>
											<td class="align-middle">
												<a style="color: black;" href="<%=request.getContextPath() %>/product/productOne.jsp?productId=<%=productId %>">
													<%=productName %>
												</a>
											</td>
										</tr>
										<tr>
											<td class="align-middle"><%=productPrice %>원</td>
										</tr>
										<tr>
											<td class="align-middle">
												<%
													if (productSoldout.equals("Y")) { // 품절시
														%><button type="button" class="btn btn-danger btn-sm">&nbsp;&nbsp;품절&nbsp;&nbsp;</button><%
													} else {	// 판매중
														%><button type="button" class="btn btn-success btn-sm">판매중</button><%
													}							
												%>
											</td>
										</tr>
									<%
								}
							%>
						</table>
						
						<br>
						
						<!-- 페이지 네비게이션 -->
						<ul class="pagination justify-content-center">
							<!-- 처음으로 버튼 -->
								<%
									if (currentPage > 1) { // currentPage가 1보다 클 때만 처음으로 갈 수 있음
										%>
											<li class="page-item">
											<a class="page-link" href="./productList.jsp?&thisCategoryId=<%=thisCategoryId %>&thisCategoryName=<%=thisCategoryName %>&currentPage=1">처음으로</a>
										<%
									} else { // 첫 페이지 일 때 처음으로 버튼 표시 안 함
										%>
											<li class="page-item disabled">
											<a class="page-link" href="#">처음으로</a>
										<%
									}
								%>
							</li>
							<!-- 이전 버튼 -->
							
								<%
									if (currentPage > 1) { // currentPage가 1보다 클 때만 이전으로 갈 수 있음
										%>
											<li class="page-item">
											<a class="page-link" href="./productList.jsp?&thisCategoryId=<%=thisCategoryId %>&thisCategoryName=<%=thisCategoryName %>&currentPage=<%=currentPage - 1 %>">이전</a>
										<%
									} else { // 1이거나 그 이하면 버튼 표시 안 함
										%>
											<li class="page-item disabled">
											<a class="page-link" href="#">이전</a>
										<%
									}
								%>
							</li>
							<!-- 현재 페이지 표시 -->
							<%
								int totalCount = 0;	// 전체 데이터 수
								
								if (thisCategoryId == 0) {
									totalCount = productDao.countAllData();	// 전체 데이터 개수
								} else {
									totalCount = productDao.countAllDataById(thisCategoryId);	// 특정 카테고리별 개수
								}
								
								int lastPage = totalCount / rowPerPage;
								if (totalCount % rowPerPage != 0) {	// 10 미만의 개수의 데이터가 있는 페이지를 표시
									lastPage += 1;
								}
								
								if (lastPage == 0) { // 전체 페이지가 0개이면 현재 페이지도 0으로 표시
									currentPage = 0;
								}
							
								int navPerPage = 10;	// 네비게이션에 표시할 페이지 수
								int navFirstPage = currentPage - (currentPage % navPerPage) + 1;	// 네비게이션 첫번째 페이지
								int navLastPage = navFirstPage + navPerPage - 1;	// 네비게이션 마지막 페이지
								
								if (currentPage % navPerPage == 0 && currentPage != 0) {	// 10으로 나누어 떨어지는 경우 처리하는 코드
									navFirstPage = navFirstPage - navPerPage;
									navLastPage = navLastPage - navPerPage;
								}
							
								for(int i = navFirstPage; i <= navLastPage; i++) {
									if (i <= lastPage) {
										if (i == currentPage) {	// 현재 페이지
											%>
												<li class="page-item disabled">
													<a class="page-link" href="#"><%=i %></a>
												</li>
											<%
										} else {	// 다음 페이지
											%>
												<li class="page-item">
													<a class="page-link" href="./productList.jsp?&thisCategoryId=<%=thisCategoryId %>&thisCategoryName=<%=thisCategoryName %>&currentPage=<%=i %>"><%=i %></a>
												</li>
											<%
										}
									}
								}
							%>
							<!-- 다음 버튼 -->
								<%
									if (currentPage < lastPage) { // currentPage가 lastPage보다 작을 때만 다음으로 갈 수 있음
										%>
											<li class="page-item">
											<a class="page-link" href="./productList.jsp?&thisCategoryId=<%=thisCategoryId %>&thisCategoryName=<%=thisCategoryName %>&currentPage=<%=currentPage + 1 %>">다음</a>
										<%
									} else { // 마지막 페이지 일 때 다음 버튼 표시 안 함
										%>
											<li class="page-item disabled">
											<a class="page-link" href="#">다음</a>
										<%
									}
								%>
							</li>
							<!-- 마지막으로 버튼 -->
								<%
									if (currentPage < lastPage) { // currentPage가 lastPage보다 작을 때만 마지막으로 갈 수 있음
										%>
											<li class="page-item">
											<a class="page-link" href="./productList.jsp?&thisCategoryId=<%=thisCategoryId %>&thisCategoryName=<%=thisCategoryName %>&currentPage=<%=lastPage %>">마지막으로</a>
										<%
									} else { // 마지막 페이지 일 때 마지막으로 버튼 표시 안 함
										%>
											<li class="page-item disabled">
											<a class="page-link" href="#">마지막으로</a>
										<%
									}
								%>
							</li>
						</ul>
						
						<!-- 총 페이지 수 출력 -->
						<table style="margin: auto;">
							<tr>
								<td>
									<button type="button" class="btn btn-outline-dark btn-sm">
										<%=currentPage %> / <%=lastPage %> 페이지
									</button>
								</td>
							</tr>
						</table>
					<%
				} else {	// 상품이 존재하지 않을 때
					%>상품 결과가 없습니다.<%
				}
			%>
		</div>
			
		<div style="margin-top: 60px;"></div>
		
	</body>
</html>