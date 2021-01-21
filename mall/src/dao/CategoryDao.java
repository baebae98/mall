package dao;

import java.util.ArrayList;
import java.sql.*;
import vo.Category;
import commons.DBUtil;

public class CategoryDao {
	// 카테고리 추가하는 메소드
	public void insertCategory(Category category) throws Exception {	// 카테고리 타입을 입력받는다.
		DBUtil dbUtil = new DBUtil();	// 데이터베이스 정보가 담긴 객체 생성
		Connection conn = dbUtil.getConnection(); // 데이터베이스 접속
		
		// SQL 명령, 명령 준비
		String sql = "insert into category(category_name) values(?)";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setString(1, category.getCategoryName());	// 카테고리 타입의 categoryName을 입력받는다.
		
		stmt.executeUpdate();
		
		conn.close(); // 데이터베이스 사용을 다 했으면 접속을 종료한다.
	}
	
	// 카테고리 삭제하는 메소드
	public void deleteCategory(Category category) throws Exception {
		DBUtil dbUtil = new DBUtil();	// 데이터베이스 정보가 담긴 객체 생성
		Connection conn = dbUtil.getConnection(); // 데이터베이스 접속
		
		// SQL 명령, 명령 준비
		String sql = "delete from category where category_id = ?";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, category.getCategoryId());	// 카테고리 타입의 categoryId를 입력받는다.
		
		stmt.executeUpdate();
		
		conn.close(); // 데이터베이스 사용을 다 했으면 접속을 종료한다.
	}
	
	// 카테고리  수정하는 메소드
	public void updateCategory(Category category) throws Exception {	// 카테고리 타입을 입력받는다.
		DBUtil dbUtil = new DBUtil();	// 데이터베이스 정보가 담긴 객체 생성
		Connection conn = dbUtil.getConnection(); // 데이터베이스 접속
		
		// SQL 명령, 명령 준비
		String sql = "update category set category_name = ? where category_id = ?";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setString(1, category.getCategoryName());	// 카테고리 타입의 categoryName을 입력받는다.
		stmt.setInt(2, category.getCategoryId());		// 카테고리 타입의 categoryId를 입력받는다.
		
		stmt.executeUpdate();
		
		conn.close(); // 데이터베이스 사용을 다 했으면 접속을 종료한다.
	}
	
	// 카테고리 목록을 출력하는 메소드
	public ArrayList<Category> selectCategoryList(int limit1, int limit2) throws Exception {
		// ArrayList 생성
		ArrayList<Category> list = new ArrayList<Category>();
		
		DBUtil dbUtil = new DBUtil();	// 데이터베이스 정보가 담긴 객체 생성
		Connection conn = dbUtil.getConnection(); // 데이터베이스 접속
		
		// SQL 명령, 명령 준비
		String sql = "select category_id, category_name from category order by category_id asc limit ?, ?";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, limit1);	// 첫 번째 인자 (시작 데이터)
        stmt.setInt(2, limit2);	// 두 번째 인자 (데이터 개수)
        
		// SQL 명령 실행
		ResultSet rs = stmt.executeQuery();
		
		// 데이터베이스 내용 불러오기
		while(rs.next()) {
			Category category = new Category();	// 카테고리 객체 생성
			category.setCategoryId(rs.getInt("category_id"));
			category.setCategoryName(rs.getString("category_name"));
			list.add(category);	// 리스트에 데이터 추가
		}
		
		conn.close(); // 데이터베이스 사용을 다 했으면 접속을 종료한다.
		
		// 최종 데이터 반환
		return list;
	}
	
	// 카테고리 목록 전체를 출력하는 메소드
	public ArrayList<Category> selectCategoryListAll() throws Exception {
		// ArrayList 생성
		ArrayList<Category> list = new ArrayList<Category>();
		
		DBUtil dbUtil = new DBUtil();	// 데이터베이스 정보가 담긴 객체 생성
		Connection conn = dbUtil.getConnection(); // 데이터베이스 접속
		
		// SQL 명령, 명령 준비
		String sql = "select category_id, category_name from category order by category_id asc";
		PreparedStatement stmt = conn.prepareStatement(sql);   
        
		// SQL 명령 실행
		ResultSet rs = stmt.executeQuery();
		
		// 데이터베이스 내용 불러오기
		while(rs.next()) {
			Category category = new Category();	// 카테고리 객체 생성
			category.setCategoryId(rs.getInt("category_id"));
			category.setCategoryName(rs.getString("category_name"));
			list.add(category);	// 리스트에 데이터 추가
		}
		
		conn.close(); // 데이터베이스 사용을 다 했으면 접속을 종료한다.
		
		// 최종 데이터 반환
		return list;
	}
	
	// 전체 카테고리의 전체 데이터 개수 구하기
	public int countAllData() throws Exception {
		int totalCount = 0;	// 기본값은 0으로
		
		DBUtil dbUtil = new DBUtil();	// 데이터베이스 정보가 담긴 객체 생성
		Connection conn = dbUtil.getConnection(); // 데이터베이스 접속
		
		// SQL 명령, 명령 준비
		String sql = "select count(*) from category";
		PreparedStatement stmt = conn.prepareStatement(sql);
		
		// SQL 명령 실행
		ResultSet rs = stmt.executeQuery();
		
		if (rs.next()) {
			totalCount = rs.getInt("count(*)");
		}
		
		conn.close(); // 데이터베이스 사용을 다 했으면 접속을 종료한다.
				
		// 최종 데이터 반환
		return totalCount;
	}
	
	// 카테고리 추천 리스트
	public ArrayList<Category> selectCategoryCkList() throws Exception {
		ArrayList<Category> list = new ArrayList<Category>();

		DBUtil dbUtil = new DBUtil();	// 데이터베이스 정보가 담긴 객체 생성
		Connection conn = dbUtil.getConnection(); // 데이터베이스 접속
		
		// SQL 명령, 명령 준비
		String sql = "select category_id, category_name, category_pic from category where category_ck = 'Y' limit 0, 4";
		PreparedStatement stmt = conn.prepareStatement(sql);
		
		// SQL 명령 실행
		ResultSet rs = stmt.executeQuery();
		
		while (rs.next()) {
			Category category = new Category();	// 카테고리 객체 생성
			category.setCategoryId(rs.getInt("category_id"));
			category.setCategoryName(rs.getString("category_name"));
			category.setCategoryPic(rs.getString("category_pic"));
			list.add(category);	// 리스트에 데이터 추가
		}
		
		conn.close(); // 데이터베이스 사용을 다 했으면 접속을 종료한다.
		
		return list;
	}
}