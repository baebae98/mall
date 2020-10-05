package dao;
import java.util.*;
import commons.DBUtil;
import vo.*;
import java.sql.*;

public class OrdersDao {
		public ArrayList<Orders> selectOrdersListByEmail(String memberEmail) throws Exception{
			return null;
		}
		
		public void insertOrders(Orders orders) throws Exception {
			DBUtil dbUtil = new DBUtil();
			Connection conn = dbUtil.getConnection();
			String sql="insert into orders(product_id,orders_amount,orders_price,member_email,orders_addr,orders_state,orders_date) values(?,?,?,?,?,'결제완료',now())";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, orders.getProductId());
			stmt.setInt(2, orders.getOrdersAmount());
			stmt.setInt(3, orders.getOrdersPrice());
			stmt.setString(4, orders.getMemberEmail());
			stmt.setString(5, orders.getOrdersAddr());
			stmt.executeUpdate();
			conn.close();
		}
		//전체 리스트 출력
	      public ArrayList<OrdersAndProduct> selectOrdersLsitByEmail(String memberEmail) throws Exception{
	          ArrayList<OrdersAndProduct> list = new ArrayList<OrdersAndProduct>();
	            
	         //데이터 베이스 연결
	         DBUtil dbUtil = new DBUtil();
	         Connection conn = dbUtil.getConnection();
	           
	            //sql 문 생성
	            String sql = "select o.orders_id, o.product_id, o.orders_amount, o.orders_price, o.member_email, o.orders_addr, o.orders_state, o.orders_date, p.product_name from orders o inner join product p on o.product_id = p.product_id where member_email = ?  order by orders_date desc";
	            PreparedStatement stmt = conn.prepareStatement(sql);
	            stmt.setString(1, memberEmail);
	            ResultSet rs = stmt.executeQuery();
	            
	            while(rs.next()) {
	               OrdersAndProduct oap = new OrdersAndProduct();
	              /* oap.orders = new Orders();
	               oap.product = new Product();
	               oap.orders.ordersId = rs.getInt("o.orders_id");
	               oap.orders.productId = rs.getInt("o.product_id");
	               oap.product.productName = rs.getString("p.product_name");
	               oap.orders.ordersAmount = rs.getInt("o.orders_amount");
	               oap.orders.ordersPrice = rs.getInt("o.orders_price");
	               oap.orders.membersEmail = rs.getString("o.members_email");
	               oap.orders.ordersAddr = rs.getString("o.orders_addr");
	               oap.orders.ordersState = rs.getString("o.orders_state");
	               oap.orders.ordersDate = rs.getString("o.orders_date"); */
	               oap.setOrders(new Orders());
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
	               
	               
	               //리스트 출력
	               list.add(oap);
	            }
	            //데이터베이스 닫기
	            conn.close();
	            return list;   
	      }
	}