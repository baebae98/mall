package dao;
import java.util.ArrayList;
import vo.Category;
import java.sql.*;
import commons.*;

public class CategoryDao {
   //페이징
   
   //데이터 생성
   public void insertCategory(Category category) throws Exception {
      //데이터 베이스 연결
      DBUtil dbUtil = new DBUtil();
      Connection conn = dbUtil.getConnection();
      //sql문
      String sql ="insert into category(category_name) values(?)";
      //데이터 베이스 접속
      PreparedStatement stmt = conn.prepareStatement(sql);
      //stmt.setString(1, category.categoryName);
      stmt.setString(1, category.getCategoryName());
      stmt.executeLargeUpdate();
   }
   //데이터 삭제
   public void deleteCategory(Category category) throws Exception{
      //데이터 베이스 연결
      DBUtil dbUtil = new DBUtil();
      Connection conn = dbUtil.getConnection();      
      //sql문
      String sql ="delete from category where category_id=?";
      //데이터 베이스 접속
      PreparedStatement stmt = conn.prepareStatement(sql);
     // stmt.setInt(1,  category.categoryId);
      stmt.setInt(1, category.getCategoryId());
      //결과 삭제
      stmt.executeLargeUpdate();
   }
   //데이터 수정
   public void updateCategory(Category category) throws Exception{
      //db 주소,이디, 비밀번호 변수 지정
      String driver ="org.mariadb.jdbc.Driver";
      String dbaddr ="jdbc:mariadb://localhost:3306/mall";
      String dbid ="root";
      String dbpw ="java1004";
      //sql문
      String sql ="update category set category_name = ? where category_id = ?";
      Class.forName(driver);
      //mariadb 연결
      Connection conn = DriverManager.getConnection(dbaddr, dbid, dbpw);
      //데이터베이스 접속
      PreparedStatement stmt = conn.prepareStatement(sql);
     // stmt.setString(1, category.categoryName);
     // stmt.setInt(2, category.categoryId);
      //캡슐화로 인해 코드 수정
      stmt.setString(1, category.getCategoryName());
      stmt.setInt(2, category.getCategoryId());
      //결과 수정
      stmt.executeLargeUpdate();
   }
   
   //리스트문
   public ArrayList<Category> selectCategoryList(int currentPage) throws Exception{
      //리스트 지정
      ArrayList<Category> list = new ArrayList<Category>();
      
      //db 주소, 아이디 , 비밀번호 변수 지정
      String driver ="org.mariadb.jdbc.Driver";
      String dbaddr = "jdbc:mariadb://localhost:3306/mall";
      String dbid = "root";
      String dbpw = "java1004";
      //sql 문
      String sql ="select category_id, category_name from category order by category_id desc limit ?,?";
      Class.forName(driver);
      //마리아 db 연결
      Connection conn = DriverManager.getConnection(dbaddr, dbid,dbpw);
      //데이터 베이스 접속
      PreparedStatement stmt = conn.prepareStatement(sql);
      stmt.setInt(1, (currentPage-1)*10);
      stmt.setInt(2, 10);
      //결과문 출력
      ResultSet rs = stmt.executeQuery();
      
      while(rs.next()) {
         Category category = new Category();
        // category.categoryId = rs.getInt("category_id");
        // category.categoryName = rs.getString("category_name");
         category.setCategoryId(rs.getInt("category_id"));
         category.setCategoryName(rs.getString("category_name"));
         list.add(category);
      }
      //데이터 베이스 닫기
      conn.close();
      
      return list;
   }
   //리스트문
      public ArrayList<Category> selectCategoryList() throws Exception{
         //리스트 지정
         ArrayList<Category> list = new ArrayList<Category>();
         
         //데이터 베이스 연결
         DBUtil dbUtil = new DBUtil();
         Connection conn = dbUtil.getConnection();         
         //sql 문
         String sql ="select category_id, category_name from category order by category_id desc ";
         //데이터 베이스 접속
         PreparedStatement stmt = conn.prepareStatement(sql);
         //결과문 출력
         ResultSet rs = stmt.executeQuery();
         
         while(rs.next()) {
            Category category = new Category();
            //category.categoryId = rs.getInt("category_id");
            //category.categoryName = rs.getString("category_name");
            category.setCategoryId(rs.getInt("category_id"));
            category.setCategoryName(rs.getString("category_name"));
            list.add(category);
         }
         //데이터 베이스 닫기
         conn.close();
         
         return list;
      }
      
      //최대 페이지 구하기
      public int getCategoryEndPage() throws Exception{
         //페이지 변수 지정
         int endPage = 1;
         //데이터 베이스 연결
         DBUtil dbUtil = new DBUtil();
         Connection conn = dbUtil.getConnection();
         String sql = "select count(*) from category"; 
         //데이터베이스 접속
         PreparedStatement stmt = conn.prepareStatement(sql);
         ResultSet rs = stmt.executeQuery();
         
         if(rs.next()) {
            endPage = rs.getInt("count(*)");
            if(endPage%10 == 0) {
               endPage = (int)(endPage/10);
            }else {
               endPage = (int)(endPage/10) + 1;
            }
         }
      
      
      conn.close();
      return endPage;

   }
   //추천 Category 목록
      public ArrayList<Category> selectCategoryCKList() throws Exception{
         //리스트 지정
         ArrayList<Category> list = new ArrayList<Category>();
         
         //데이터 베이스 연결
         DBUtil dbUtil = new DBUtil();
         Connection conn = dbUtil.getConnection();         
         //sql 문
         String sql ="select category_id, category_pic from category where category_ck = 'Y' limit 0,8";
         //데이터 베이스 접속
         PreparedStatement stmt = conn.prepareStatement(sql);
         //결과문 출력
         ResultSet rs = stmt.executeQuery();
         
         while(rs.next()) {
            Category category = new Category();
           // category.categoryId = rs.getInt("category_id");
           // category.categoryPic = rs.getString("category_pic");
            category.setCategoryId(rs.getInt("category_id"));
            category.setCategoryPic(rs.getString("category_pic"));
            list.add(category);
         }
         //데이터 베이스 닫기
         conn.close();
         
         return list;
      }
   
}