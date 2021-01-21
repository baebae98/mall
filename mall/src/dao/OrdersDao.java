package dao;

import java.util.ArrayList;
import java.sql.*;
import vo.*;
import commons.DBUtil;

public class OrdersDao {
	// 주문을 입력하는 메소드
	public void insertOrders(Orders orders) throws Exception {	// Orders 타입을 입력받는다.
		DBUtil dbUtil = new DBUtil();	// 데이터베이스 정보가 담긴 객체 생성
		Connection conn = dbUtil.getConnection(); // 데이터베이스 접속
		
		// SQL 명령, 명령 준비
		String sql = "insert into orders(product_id, orders_amount, orders_price, member_email, orders_addr, orders_state, orders_date) values(?, ?, ?, ?, ?, '결제완료', now())";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, orders.getProductId());		// Orders 타입의 getProductId을 입력받는다.
		stmt.setInt(2, orders.getOrdersAmount());	// Orders 타입의 getOrdersAmount를 입력받는다.
		stmt.setInt(3, orders.getOrdersPrice());	// Orders 타입의 getOrdersPrice를 입력받는다.
		stmt.setString(4, orders.getMemberEmail());	// Orders 타입의 getMemberEmail를 입력받는다.
		stmt.setString(5, orders.getOrdersAddr());	// Orders 타입의 getOrdersAddr를 입력받는다.
		
		stmt.executeUpdate();
		
		conn.close(); // 데이터베이스 사용을 다 했으면 접속을 종료한다.
	}
	
	// 주문 상태를 수정하는 메소드
	public void updateOrdersState(Orders orders) throws Exception {	// Orders 타입을 입력받는다.
		DBUtil dbUtil = new DBUtil();	// 데이터베이스 정보가 담긴 객체 생성
		Connection conn = dbUtil.getConnection(); // 데이터베이스 접속
		
		// SQL 명령, 명령 준비
		String sql = "update orders set orders_state = ? where orders_id = ?";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setString(1, orders.getOrdersState());	// Orders 타입의 ordersState을 입력받는다.
		stmt.setInt(2, orders.getOrdersId());		// Orders 타입의 ordersId를 입력받는다.
		
		stmt.executeUpdate();
		
		conn.close(); // 데이터베이스 사용을 다 했으면 접속을 종료한다.
	}
	
	// 주문상태 페이지에 출력하기 위한 쿼리
	public ArrayList<Orders> selectOrdersOne(int ordersId) throws Exception {	// Orders 타입을 입력받는다.
		// ArrayList 생성
		ArrayList<Orders> list = new ArrayList<Orders>();
		
		DBUtil dbUtil = new DBUtil();	// 데이터베이스 정보가 담긴 객체 생성
		Connection conn = dbUtil.getConnection(); // 데이터베이스 접속
		
		// SQL 명령, 명령 준비
		String sql = "select orders_id, orders_state from orders where orders_id = ?";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, ordersId);
		
		// SQL 명령 실행
		ResultSet rs = stmt.executeQuery();
				
		// 데이터베이스 내용 불러오기
		while(rs.next()) {
			Orders orders = new Orders();	// 주문 객체 생성
			orders.setOrdersId(rs.getInt("orders_id"));
			orders.setOrdersState(rs.getString("orders_state"));
			list.add(orders);	// 리스트에 데이터 추가
		}

		conn.close(); // 데이터베이스 사용을 다 했으면 접속을 종료한다.
		
		return null;
	}
	
	// 특정 사용자의 주문 목록을 출력하는 메소드
	public ArrayList<OrdersAndProduct> selectOrdersMyList(String memberEmail, int limit1, int limit2) throws Exception {
		// ArrayList 생성
		ArrayList<OrdersAndProduct> list = new ArrayList<OrdersAndProduct>();
		
		DBUtil dbUtil = new DBUtil();	// 데이터베이스 정보가 담긴 객체 생성
		Connection conn = dbUtil.getConnection(); // 데이터베이스 접속
		
		// SQL 명령, 명령 준비
		String sql = "select o.orders_id, o.product_id, p.product_name, o.orders_amount, o.orders_price, o.member_email, o.orders_addr, o.orders_state, o.orders_date from orders o inner join product p on p.product_id = o.product_id where member_email = ? order by orders_id desc limit ?, ?";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setString(1, memberEmail);
		stmt.setInt(2, limit1);
        stmt.setInt(3, limit2);     
		
		// SQL 명령 실행
		ResultSet rs = stmt.executeQuery();
		
		// 데이터베이스 내용 불러오기
		while(rs.next()) {
			OrdersAndProduct oap = new OrdersAndProduct();
			oap.setOrders(new Orders());	// 주문 객체 생성
			oap.setProduct(new Product());
			
			oap.getOrders().setOrdersId(rs.getInt("o.orders_id"));
			oap.getOrders().setProductId(rs.getInt("o.product_id"));
			oap.getProduct().setProductName(rs.getString("p.product_name"));
			oap.getOrders().setOrdersAmount(rs.getInt("o.orders_amount"));
			oap.getOrders().setOrdersPrice(rs.getInt("o.orders_price"));
			oap.getOrders().setMemberEmail(rs.getString("o.member_email"));
			oap.getOrders().setOrdersAddr(rs.getString("o.orders_addr"));
			oap.getOrders().setOrdersState(rs.getString("o.orders_state"));
			oap.getOrders().setOrdersDate(rs.getString("o.orders_date"));
			list.add(oap);	// 리스트에 데이터 추가
		}
		
		conn.close(); // 데이터베이스 사용을 다 했으면 접속을 종료한다.
		
		// 최종 데이터 반환
		return list;
	}
	
	// 주문상태 목록 전체를 출력하는 메소드
	public ArrayList<Orders> selectOrdersStateListAll() throws Exception {
		// ArrayList 생성
		ArrayList<Orders> list = new ArrayList<Orders>();
		
		DBUtil dbUtil = new DBUtil();	// 데이터베이스 정보가 담긴 객체 생성
		Connection conn = dbUtil.getConnection(); // 데이터베이스 접속
		
		// SQL 명령, 명령 준비
		String sql = "select distinct orders_state from orders";
		PreparedStatement stmt = conn.prepareStatement(sql);   
        
		// SQL 명령 실행
		ResultSet rs = stmt.executeQuery();
		
		// 데이터베이스 내용 불러오기
		while(rs.next()) {
			Orders oList = new Orders();	// 카테고리 객체 생성
			oList.setOrdersState(rs.getString("orders_state"));
			list.add(oList);	// 리스트에 데이터 추가
		}
		
		conn.close(); // 데이터베이스 사용을 다 했으면 접속을 종료한다.
		
		// 최종 데이터 반환
		return list;
	}
	
	// 전체 주문상태의 전체 데이터 개수 구하기
	public int countAllData(String memberEmail) throws Exception {
		int totalCount = 0;	// 기본값은 0으로
		
		DBUtil dbUtil = new DBUtil();	// 데이터베이스 정보가 담긴 객체 생성
		Connection conn = dbUtil.getConnection(); // 데이터베이스 접속
		
		// SQL 명령, 명령 준비
		String sql = "select count(*) from orders where member_email = ?";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setString(1, memberEmail);
		
		// SQL 명령 실행
		ResultSet rs = stmt.executeQuery();
		
		if (rs.next()) {
			totalCount = rs.getInt("count(*)");
		}
		
		conn.close(); // 데이터베이스 사용을 다 했으면 접속을 종료한다.
				
		// 최종 데이터 반환
		return totalCount;
	}
}