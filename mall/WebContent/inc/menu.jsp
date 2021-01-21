<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div class="container">
	<div style="margin-top: 60px;"></div>

	<div class="row" style="text-align:center;">	<!-- 최상단 -->
		<div class="col-sm-4">
			<h1>
				<a style="text-decoration: none; color: black;" href="<%=request.getContextPath() %>/index.jsp">Goodee Shop</a>
			</h1>
		</div>
		<div class="col-sm-5">
			<form method="post" action="<%=request.getContextPath() %>/search.jsp">
				<table>
					<tr>
						<td width="400px">
							<input type="text" class="form-control col-sm-15" name="search">
						</td>
						<td width="100px">
							<button type="submit" class="btn btn-dark">검색</button>
						</td>
					</tr>
				</table>
			</form>
		</div>
		
		<div class="col-sm-3">
			<a style="text-decoration: none; color: black;" href="<%=request.getContextPath() %>/member/memberInfo.jsp"><i class='fas fa-user-alt' style='font-size:36px'></i></a> <!-- 사람모양 아이콘 -->
			<i>&nbsp;&nbsp;&nbsp;&nbsp;</i>
			<a style="text-decoration: none; color: black;" href="<%=request.getContextPath() %>/orders/myOrdersList.jsp"><i class='fas fa-shopping-cart' style='font-size:36px'></i></a> <!-- 쇼핑카트 아이콘 -->
		</div>
	</div>
	
	<div style="margin-top: 20px;"></div>
	
</div>

<div class="container-fluid bg-dark">
<!-- 로그인/회원가입 메뉴바 -->
	<nav class="navbar navbar-expand-sm bg-dark navbar-dark">
	
		<div class="container">
			<ul class="navbar-nav mr-auto"></ul>
			
			<ul class="navbar-nav mr-right">
				<%
					// 로그인 세션이 있을 경우 (회원 로그인 상태)
					if (session.getAttribute("loginMemberName") != null) {
						%>
							<li class="nav-item">
								<font color="white"><%=session.getAttribute("loginMemberName") %></font><font color="gray">님 환영합니다.</font>
							</li>
							
							<li>&nbsp;</li>
				
							<li class="nav-item">
								<button type="button" class="btn btn-danger btn-sm" onclick="location.href='<%=request.getContextPath() %>/member/logoutAction.jsp'">로그아웃</button>
							</li>
						<%
					// 로그인 세션이 없는 경우 (비회원 혹은 로그아웃 상태)
					} else {
						%>
							<li class="nav-item">
								<button type="button" class="btn btn-primary btn-sm" onclick="location.href='<%=request.getContextPath() %>/member/login.jsp'">로그인</button>
							</li>
							
							<li>&nbsp;</li>
							
							<li class="nav-item">
								<button type="button" class="btn btn-secondary btn-sm" onclick="location.href='<%=request.getContextPath() %>/member/signup.jsp'">회원가입</button>
							</li>
						<%
					}
				%>
			</ul>
		</div>
	</nav>
</div>