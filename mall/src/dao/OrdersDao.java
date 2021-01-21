package dao;

import java.util.ArrayList;
import java.sql.*;
import vo.*;
import commons.DBUtil;

public class OrdersDao {
	// 二쇰Ц�쓣 �엯�젰�븯�뒗 硫붿냼�뱶
	public void insertOrders(Orders orders) throws Exception {	// Orders ���엯�쓣 �엯�젰諛쏅뒗�떎.
		DBUtil dbUtil = new DBUtil();	// �뜲�씠�꽣踰좎씠�뒪 �젙蹂닿� �떞湲� 媛앹껜 �깮�꽦
		Connection conn = dbUtil.getConnection(); // �뜲�씠�꽣踰좎씠�뒪 �젒�냽
		
		// SQL 紐낅졊, 紐낅졊 以�鍮�
		String sql = "insert into orders(product_id, orders_amount, orders_price, member_email, orders_addr, orders_state, orders_date) values(?, ?, ?, ?, ?, '결제완료', now())";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, orders.getProductId());		// Orders ���엯�쓽 getProductId�쓣 �엯�젰諛쏅뒗�떎.
		stmt.setInt(2, orders.getOrdersAmount());	// Orders ���엯�쓽 getOrdersAmount瑜� �엯�젰諛쏅뒗�떎.
		stmt.setInt(3, orders.getOrdersPrice());	// Orders ���엯�쓽 getOrdersPrice瑜� �엯�젰諛쏅뒗�떎.
		stmt.setString(4, orders.getMemberEmail());	// Orders ���엯�쓽 getMemberEmail瑜� �엯�젰諛쏅뒗�떎.
		stmt.setString(5, orders.getOrdersAddr());	// Orders ���엯�쓽 getOrdersAddr瑜� �엯�젰諛쏅뒗�떎.
		
		stmt.executeUpdate();
		
		conn.close(); // �뜲�씠�꽣踰좎씠�뒪 �궗�슜�쓣 �떎 �뻽�쑝硫� �젒�냽�쓣 醫낅즺�븳�떎.
	}
	
	// 二쇰Ц �긽�깭瑜� �닔�젙�븯�뒗 硫붿냼�뱶
	public void updateOrdersState(Orders orders) throws Exception {	// Orders ���엯�쓣 �엯�젰諛쏅뒗�떎.
		DBUtil dbUtil = new DBUtil();	// �뜲�씠�꽣踰좎씠�뒪 �젙蹂닿� �떞湲� 媛앹껜 �깮�꽦
		Connection conn = dbUtil.getConnection(); // �뜲�씠�꽣踰좎씠�뒪 �젒�냽
		
		// SQL 紐낅졊, 紐낅졊 以�鍮�
		String sql = "update orders set orders_state = ? where orders_id = ?";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setString(1, orders.getOrdersState());	// Orders ���엯�쓽 ordersState�쓣 �엯�젰諛쏅뒗�떎.
		stmt.setInt(2, orders.getOrdersId());		// Orders ���엯�쓽 ordersId瑜� �엯�젰諛쏅뒗�떎.
		
		stmt.executeUpdate();
		
		conn.close(); // �뜲�씠�꽣踰좎씠�뒪 �궗�슜�쓣 �떎 �뻽�쑝硫� �젒�냽�쓣 醫낅즺�븳�떎.
	}
	
	// 二쇰Ц�긽�깭 �럹�씠吏��뿉 異쒕젰�븯湲� �쐞�븳 荑쇰━
	public ArrayList<Orders> selectOrdersOne(int ordersId) throws Exception {	// Orders ���엯�쓣 �엯�젰諛쏅뒗�떎.
		// ArrayList �깮�꽦
		ArrayList<Orders> list = new ArrayList<Orders>();
		
		DBUtil dbUtil = new DBUtil();	// �뜲�씠�꽣踰좎씠�뒪 �젙蹂닿� �떞湲� 媛앹껜 �깮�꽦
		Connection conn = dbUtil.getConnection(); // �뜲�씠�꽣踰좎씠�뒪 �젒�냽
		
		// SQL 紐낅졊, 紐낅졊 以�鍮�
		String sql = "select orders_id, orders_state from orders where orders_id = ?";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, ordersId);
		
		// SQL 紐낅졊 �떎�뻾
		ResultSet rs = stmt.executeQuery();
				
		// �뜲�씠�꽣踰좎씠�뒪 �궡�슜 遺덈윭�삤湲�
		while(rs.next()) {
			Orders orders = new Orders();	// 二쇰Ц 媛앹껜 �깮�꽦
			orders.setOrdersId(rs.getInt("orders_id"));
			orders.setOrdersState(rs.getString("orders_state"));
			list.add(orders);	// 由ъ뒪�듃�뿉 �뜲�씠�꽣 異붽�
		}

		conn.close(); // �뜲�씠�꽣踰좎씠�뒪 �궗�슜�쓣 �떎 �뻽�쑝硫� �젒�냽�쓣 醫낅즺�븳�떎.
		
		return null;
	}
	
	// �듅�젙 �궗�슜�옄�쓽 二쇰Ц 紐⑸줉�쓣 異쒕젰�븯�뒗 硫붿냼�뱶
	public ArrayList<OrdersAndProduct> selectOrdersMyList(String memberEmail, int limit1, int limit2) throws Exception {
		// ArrayList �깮�꽦
		ArrayList<OrdersAndProduct> list = new ArrayList<OrdersAndProduct>();
		
		DBUtil dbUtil = new DBUtil();	// �뜲�씠�꽣踰좎씠�뒪 �젙蹂닿� �떞湲� 媛앹껜 �깮�꽦
		Connection conn = dbUtil.getConnection(); // �뜲�씠�꽣踰좎씠�뒪 �젒�냽
		
		// SQL 紐낅졊, 紐낅졊 以�鍮�
		String sql = "select o.orders_id, o.product_id, p.product_name, o.orders_amount, o.orders_price, o.member_email, o.orders_addr, o.orders_state, o.orders_date from orders o inner join product p on p.product_id = o.product_id where member_email = ? order by orders_id desc limit ?, ?";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setString(1, memberEmail);
		stmt.setInt(2, limit1);
        stmt.setInt(3, limit2);     
		
		// SQL 紐낅졊 �떎�뻾
		ResultSet rs = stmt.executeQuery();
		
		// �뜲�씠�꽣踰좎씠�뒪 �궡�슜 遺덈윭�삤湲�
		while(rs.next()) {
			OrdersAndProduct oap = new OrdersAndProduct();
			oap.setOrders(new Orders());	// 二쇰Ц 媛앹껜 �깮�꽦
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
			list.add(oap);	// 由ъ뒪�듃�뿉 �뜲�씠�꽣 異붽�
		}
		
		conn.close(); // �뜲�씠�꽣踰좎씠�뒪 �궗�슜�쓣 �떎 �뻽�쑝硫� �젒�냽�쓣 醫낅즺�븳�떎.
		
		// 理쒖쥌 �뜲�씠�꽣 諛섑솚
		return list;
	}
	
	// 二쇰Ц�긽�깭 紐⑸줉 �쟾泥대�� 異쒕젰�븯�뒗 硫붿냼�뱶
	public ArrayList<Orders> selectOrdersStateListAll() throws Exception {
		// ArrayList �깮�꽦
		ArrayList<Orders> list = new ArrayList<Orders>();
		
		DBUtil dbUtil = new DBUtil();	// �뜲�씠�꽣踰좎씠�뒪 �젙蹂닿� �떞湲� 媛앹껜 �깮�꽦
		Connection conn = dbUtil.getConnection(); // �뜲�씠�꽣踰좎씠�뒪 �젒�냽
		
		// SQL 紐낅졊, 紐낅졊 以�鍮�
		String sql = "select distinct orders_state from orders";
		PreparedStatement stmt = conn.prepareStatement(sql);   
        
		// SQL 紐낅졊 �떎�뻾
		ResultSet rs = stmt.executeQuery();
		
		// �뜲�씠�꽣踰좎씠�뒪 �궡�슜 遺덈윭�삤湲�
		while(rs.next()) {
			Orders oList = new Orders();	// 移댄뀒怨좊━ 媛앹껜 �깮�꽦
			oList.setOrdersState(rs.getString("orders_state"));
			list.add(oList);	// 由ъ뒪�듃�뿉 �뜲�씠�꽣 異붽�
		}
		
		conn.close(); // �뜲�씠�꽣踰좎씠�뒪 �궗�슜�쓣 �떎 �뻽�쑝硫� �젒�냽�쓣 醫낅즺�븳�떎.
		
		// 理쒖쥌 �뜲�씠�꽣 諛섑솚
		return list;
	}
	
	// �쟾泥� 二쇰Ц�긽�깭�쓽 �쟾泥� �뜲�씠�꽣 媛쒖닔 援ы븯湲�
	public int countAllData(String memberEmail) throws Exception {
		int totalCount = 0;	// 湲곕낯媛믪� 0�쑝濡�
		
		DBUtil dbUtil = new DBUtil();	// �뜲�씠�꽣踰좎씠�뒪 �젙蹂닿� �떞湲� 媛앹껜 �깮�꽦
		Connection conn = dbUtil.getConnection(); // �뜲�씠�꽣踰좎씠�뒪 �젒�냽
		
		// SQL 紐낅졊, 紐낅졊 以�鍮�
		String sql = "select count(*) from orders where member_email = ?";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setString(1, memberEmail);
		
		// SQL 紐낅졊 �떎�뻾
		ResultSet rs = stmt.executeQuery();
		
		if (rs.next()) {
			totalCount = rs.getInt("count(*)");
		}
		
		conn.close(); // �뜲�씠�꽣踰좎씠�뒪 �궗�슜�쓣 �떎 �뻽�쑝硫� �젒�냽�쓣 醫낅즺�븳�떎.
				
		// 理쒖쥌 �뜲�씠�꽣 諛섑솚
		return totalCount;
	}
}