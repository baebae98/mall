package commons;
import java.sql.*;
public class DBUtil {
   public Connection getConnection() throws Exception{
      //db 변수 생성
      String driver = "org.mariadb.jdbc.Driver";
      String dbaddr = "jdbc:mariadb://localhost:3306/mall";
      String dbid = "root";
      String dbpw = "java1004";
                           
      //드라이버 가져오기
      Class.forName(driver);
               
      //db연결
      Connection conn = DriverManager.getConnection(dbaddr, dbid, dbpw);
      return conn;
   }
}