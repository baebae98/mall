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
		
	}