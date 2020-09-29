package dao;
import java.util.ArrayList;
import vo.Category;
import java.sql.*;
import commons.*;

public class CategoryDao {
   //����¡
   
   //������ ����
   public void insertCategory(Category category) throws Exception {
      //������ ���̽� ����
      DBUtil dbUtil = new DBUtil();
      Connection conn = dbUtil.getConnection();
      //sql��
      String sql ="insert into category(category_name) values(?)";
      //������ ���̽� ����
      PreparedStatement stmt = conn.prepareStatement(sql);
      //stmt.setString(1, category.categoryName);
      stmt.setString(1, category.getCategoryName());
      stmt.executeLargeUpdate();
   }
   //������ ����
   public void deleteCategory(Category category) throws Exception{
      //������ ���̽� ����
      DBUtil dbUtil = new DBUtil();
      Connection conn = dbUtil.getConnection();      
      //sql��
      String sql ="delete from category where category_id=?";
      //������ ���̽� ����
      PreparedStatement stmt = conn.prepareStatement(sql);
     // stmt.setInt(1,  category.categoryId);
      stmt.setInt(1, category.getCategoryId());
      //��� ����
      stmt.executeLargeUpdate();
   }
   //������ ����
   public void updateCategory(Category category) throws Exception{
      //db �ּ�,�̵�, ��й�ȣ ���� ����
      String driver ="org.mariadb.jdbc.Driver";
      String dbaddr ="jdbc:mariadb://localhost:3306/mall";
      String dbid ="root";
      String dbpw ="java1004";
      //sql��
      String sql ="update category set category_name = ? where category_id = ?";
      Class.forName(driver);
      //mariadb ����
      Connection conn = DriverManager.getConnection(dbaddr, dbid, dbpw);
      //�����ͺ��̽� ����
      PreparedStatement stmt = conn.prepareStatement(sql);
     // stmt.setString(1, category.categoryName);
     // stmt.setInt(2, category.categoryId);
      //ĸ��ȭ�� ���� �ڵ� ����
      stmt.setString(1, category.getCategoryName());
      stmt.setInt(2, category.getCategoryId());
      //��� ����
      stmt.executeLargeUpdate();
   }
   
   //����Ʈ��
   public ArrayList<Category> selectCategoryList(int currentPage) throws Exception{
      //����Ʈ ����
      ArrayList<Category> list = new ArrayList<Category>();
      
      //db �ּ�, ���̵� , ��й�ȣ ���� ����
      String driver ="org.mariadb.jdbc.Driver";
      String dbaddr = "jdbc:mariadb://localhost:3306/mall";
      String dbid = "root";
      String dbpw = "java1004";
      //sql ��
      String sql ="select category_id, category_name from category order by category_id desc limit ?,?";
      Class.forName(driver);
      //������ db ����
      Connection conn = DriverManager.getConnection(dbaddr, dbid,dbpw);
      //������ ���̽� ����
      PreparedStatement stmt = conn.prepareStatement(sql);
      stmt.setInt(1, (currentPage-1)*10);
      stmt.setInt(2, 10);
      //����� ���
      ResultSet rs = stmt.executeQuery();
      
      while(rs.next()) {
         Category category = new Category();
        // category.categoryId = rs.getInt("category_id");
        // category.categoryName = rs.getString("category_name");
         category.setCategoryId(rs.getInt("category_id"));
         category.setCategoryName(rs.getString("category_name"));
         list.add(category);
      }
      //������ ���̽� �ݱ�
      conn.close();
      
      return list;
   }
   //����Ʈ��
      public ArrayList<Category> selectCategoryList() throws Exception{
         //����Ʈ ����
         ArrayList<Category> list = new ArrayList<Category>();
         
         //������ ���̽� ����
         DBUtil dbUtil = new DBUtil();
         Connection conn = dbUtil.getConnection();         
         //sql ��
         String sql ="select category_id, category_name from category order by category_id desc ";
         //������ ���̽� ����
         PreparedStatement stmt = conn.prepareStatement(sql);
         //����� ���
         ResultSet rs = stmt.executeQuery();
         
         while(rs.next()) {
            Category category = new Category();
            //category.categoryId = rs.getInt("category_id");
            //category.categoryName = rs.getString("category_name");
            category.setCategoryId(rs.getInt("category_id"));
            category.setCategoryName(rs.getString("category_name"));
            list.add(category);
         }
         //������ ���̽� �ݱ�
         conn.close();
         
         return list;
      }
      
      //�ִ� ������ ���ϱ�
      public int getCategoryEndPage() throws Exception{
         //������ ���� ����
         int endPage = 1;
         //������ ���̽� ����
         DBUtil dbUtil = new DBUtil();
         Connection conn = dbUtil.getConnection();
         String sql = "select count(*) from category"; 
         //�����ͺ��̽� ����
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
   //��õ Category ���
      public ArrayList<Category> selectCategoryCKList() throws Exception{
         //����Ʈ ����
         ArrayList<Category> list = new ArrayList<Category>();
         
         //������ ���̽� ����
         DBUtil dbUtil = new DBUtil();
         Connection conn = dbUtil.getConnection();         
         //sql ��
         String sql ="select category_id, category_pic from category where category_ck = 'Y' limit 0,8";
         //������ ���̽� ����
         PreparedStatement stmt = conn.prepareStatement(sql);
         //����� ���
         ResultSet rs = stmt.executeQuery();
         
         while(rs.next()) {
            Category category = new Category();
           // category.categoryId = rs.getInt("category_id");
           // category.categoryPic = rs.getString("category_pic");
            category.setCategoryId(rs.getInt("category_id"));
            category.setCategoryPic(rs.getString("category_pic"));
            list.add(category);
         }
         //������ ���̽� �ݱ�
         conn.close();
         
         return list;
      }
   
}