package dao;
import commons.DBUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;
import vo.*;
public class ProductDao {
	
	public Product selectProductOne(int productId) throws Exception{
		Product product = null;
		
		  //데이터 베이스 연결
	      DBUtil dbUtil = new DBUtil();
	      Connection conn = dbUtil.getConnection();         
	      //sql 문
	      String sql ="select * from product where product_id=?";
	      //데이터 베이스 접속
	      PreparedStatement stmt = conn.prepareStatement(sql);
	      stmt.setInt(1, productId);
	      ResultSet rs = stmt.executeQuery();
	      if(rs.next()) {
	    	  product = new Product();
	    	  product.setProductId(rs.getInt("product_id"));
	    	  product.setCategoryId(rs.getInt("category_id"));
	          product.setProductName(rs.getString("product_name"));
	          product.setProductPrice(rs.getInt("product_price"));
	          product.setProductPic(rs.getString("product_pic"));
	          product.setProductContent(rs.getString("product_content"));
	          product.setProductSoldout(rs.getString("product_soldout"));
	      }
		
		return product;
	}
	
	
	public ArrayList<Product> selectProductList() throws Exception{
      //리스트 지정
      ArrayList<Product> list = new ArrayList<Product>();
      
      //데이터 베이스 연결
      DBUtil dbUtil = new DBUtil();
      Connection conn = dbUtil.getConnection();         
      //sql 문
      String sql ="select product_id, category_id, product_name, product_price, product_pic from product order by product_id limit 0,10";
      //데이터 베이스 접속
      PreparedStatement stmt = conn.prepareStatement(sql);
      //결과문 출력
      ResultSet rs = stmt.executeQuery();
      
      while(rs.next()) {
         Product p = new Product();
        // p.productId = rs.getInt("product_id");
        // p.categoryId = rs.getInt("category_id");
        // p.productName = rs.getString("product_name");
        // p.productPrice = rs.getInt("product_price");
        // p.productPic = rs.getString("product_pic");
         p.setProductId(rs.getInt("product_id"));
         p.setCategoryId(rs.getInt("category_id"));
         p.setProductName(rs.getString("product_name"));
         p.setProductPrice(rs.getInt("product_price"));
         p.setProductPic(rs.getString("product_pic"));
         list.add(p);
      }
      //데이터 베이스 닫기
      conn.close();
      
      return list;
   }
}