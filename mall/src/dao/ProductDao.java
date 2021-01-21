package dao;

import java.util.ArrayList;
import java.sql.*;
import vo.*;
import commons.DBUtil;

public class ProductDao {
	// 프로덕트 품절여부 갱신
	public void updateProductSoldout(Product product) throws Exception {
		DBUtil dbUtil = new DBUtil();	// 데이터베이스 정보가 담긴 객체 생성
		Connection conn = dbUtil.getConnection(); // 데이터베이스 접속
		
		// SQL 명령, 명령 준비
		String sql = "update product set product_soldout = ? where product_id = ?";
		PreparedStatement stmt = conn.prepareStatement(sql);
		
		if (product.getProductSoldout().equals("Y")) {
			stmt.setString(1, "N");
		} else {
			stmt.setString(1, "Y");
		}
		stmt.setInt(2, product.getProductId());			// productId를 입력받는다.
		
		int row = stmt.executeUpdate();
		System.out.println(row + "행에 SQL Update 처리가 되었습니다.");	// 디버깅
		
		conn.close(); // 데이터베이스 사용을 다 했으면 접속을 종료한다.
	}
	
	// 프로덕트 상세페이지 검색용
	public Product selectProductOne(int productId) throws Exception {
		Product product = null;
		
		DBUtil dbUtil = new DBUtil();	// 데이터베이스 정보가 담긴 객체 생성
		Connection conn = dbUtil.getConnection(); // 데이터베이스 접속
		
		// SQL 명령, 명령 준비
		String sql = ("select product_id, category_id, product_name, product_price, product_content, product_soldout, product_pic from product where product_id = ?");
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, productId);			// productId을 입력받는다.
		
		ResultSet rs = stmt.executeQuery();
		
		if (rs.next()) {
			product = new Product();
			product.setProductId(rs.getInt("product_id"));
			product.setCategoryId(rs.getInt("category_id"));
			product.setProductName(rs.getString("product_name"));
			product.setProductPrice(rs.getInt("product_price"));
			product.setProductContent(rs.getString("product_content"));
			product.setProductSoldout(rs.getString("product_soldout"));
			product.setProductPic(rs.getString("product_pic"));
		}
		
		conn.close(); // 데이터베이스 사용을 다 했으면 접속을 종료한다.
		
		return product;
	}
	
	// 프로덕트 추가하는 메소드
	public void insertProduct(Product product) throws Exception {	// 프로덕트 타입을 입력받는다.
		DBUtil dbUtil = new DBUtil();	// 데이터베이스 정보가 담긴 객체 생성
		Connection conn = dbUtil.getConnection(); // 데이터베이스 접속
		
		// SQL 명령, 명령 준비
		String sql = "insert into product(category_id, product_name, product_price, product_content, product_soldout) values(?, ?, ?, ?, ?)";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, product.getCategoryId());			// 프로덕트 타입의 categoryId을 입력받는다.
		stmt.setString(2, product.getProductName());		// 프로덕트 타입의 productName을 입력받는다.
		stmt.setInt(3, product.getProductPrice());		// 프로덕트 타입의 productPrice을 입력받는다.
		stmt.setString(4, product.getProductContent());	// 프로덕트 타입의 productContent을 입력받는다.
		stmt.setString(5, product.getProductSoldout());	// 프로덕트 타입의 productSoldout을 입력받는다.
		
		stmt.executeUpdate();
		
		conn.close(); // 데이터베이스 사용을 다 했으면 접속을 종료한다.
	}
	
	// 프로덕트 삭제하는 메소드
	public void deleteProduct(Product product) throws Exception {
		DBUtil dbUtil = new DBUtil();	// 데이터베이스 정보가 담긴 객체 생성
		Connection conn = dbUtil.getConnection(); // 데이터베이스 접속
		
		// SQL 명령, 명령 준비
		String sql = "delete from product where product_id = ?";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, product.getProductId());	// 프로덕트 타입의 productId를 입력받는다.
		
		stmt.executeUpdate();
		
		conn.close(); // 데이터베이스 사용을 다 했으면 접속을 종료한다.
	}
	
	// 프로덕트  수정하는 메소드
	public void updateProduct(Product product) throws Exception {	// 프로덕트 타입을 입력받는다.
		DBUtil dbUtil = new DBUtil();	// 데이터베이스 정보가 담긴 객체 생성
		Connection conn = dbUtil.getConnection(); // 데이터베이스 접속
		
		// SQL 명령, 명령 준비
		String sql = "update product set product_name = ? where product_id = ?";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setString(1, product.getProductName());	// 프로덕트 타입의 productName을 입력받는다.
		stmt.setInt(2, product.getProductId());		// 프로덕트 타입의 productId를 입력받는다.
		
		stmt.executeUpdate();
		
		conn.close(); // 데이터베이스 사용을 다 했으면 접속을 종료한다.
	}
	
	// 프로덕트 목록을 출력하는 메소드
	public ArrayList<Product> selectProductList(int limit1, int limit2) throws Exception {
		// ArrayList 생성
		ArrayList<Product> list = new ArrayList<Product>();
		
		DBUtil dbUtil = new DBUtil();	// 데이터베이스 정보가 담긴 객체 생성
		Connection conn = dbUtil.getConnection(); // 데이터베이스 접속
		
		// SQL 명령, 명령 준비
		String sql = "select product_id, category_id, product_name, product_price, product_content, product_soldout, product_pic from product order by product_id asc limit ?, ?";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, limit1);	// 첫 번째 인자 (시작 데이터)
        stmt.setInt(2, limit2);	// 두 번째 인자 (데이터 개수)       
		
		// SQL 명령 실행
		ResultSet rs = stmt.executeQuery();
		
		// 데이터베이스 내용 불러오기
		while(rs.next()) {
			Product product = new Product();	// 프로덕트 객체 생성
			product.setProductId(rs.getInt("product_id"));
			product.setCategoryId(rs.getInt("category_id"));
			product.setProductName(rs.getString("product_name"));
			product.setProductPrice(rs.getInt("product_price"));
			//product.productContent = rs.getString("product_content");
			product.setProductSoldout(rs.getString("product_soldout"));
			product.setProductPic(rs.getString("product_pic"));
			list.add(product);	// 리스트에 데이터 추가
		}
		
		conn.close(); // 데이터베이스 사용을 다 했으면 접속을 종료한다.
		
		// 최종 데이터 반환
		return list;
	}
	
	// 프로덕트 검색 목록을 출력하는 메소드
	public ArrayList<Product> selectSearchList(String search, int limit1, int limit2) throws Exception {
		// ArrayList 생성
		ArrayList<Product> list = new ArrayList<Product>();
		
		DBUtil dbUtil = new DBUtil();	// 데이터베이스 정보가 담긴 객체 생성
		Connection conn = dbUtil.getConnection(); // 데이터베이스 접속
		
		// SQL 명령, 명령 준비
		String sql = "select product_id, category_id, product_name, product_price, product_content, product_soldout, product_pic from product where product_name like ? order by product_id asc limit ?, ?";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setString(1, "%"+search+"%");	// 첫 번째 인자 (검색어)
		stmt.setInt(2, limit1);		// 두 번째 인자 (시작 데이터)
        stmt.setInt(3, limit2);		// 세 번째 인자 (데이터 개수)       
		
		// SQL 명령 실행
		ResultSet rs = stmt.executeQuery();
		
		// 데이터베이스 내용 불러오기
		
		if (!search.equals(null) && !search.equals("")) {	// 검색어가 없는경우는 건너뜀, 검색어가 있는경우에만 list에 결과를 추가
			while(rs.next()) {
				Product product = new Product();	// 프로덕트 객체 생성
				product.setProductId(rs.getInt("product_id"));
				product.setCategoryId(rs.getInt("category_id"));
				product.setProductName(rs.getString("product_name"));
				product.setProductPrice(rs.getInt("product_price"));
				//product.productContent = rs.getString("product_content");
				product.setProductSoldout(rs.getString("product_soldout"));
				product.setProductPic(rs.getString("product_pic"));
				list.add(product);	// 리스트에 데이터 추가
			}
		}
		
		conn.close(); // 데이터베이스 사용을 다 했으면 접속을 종료한다.
		
		// 최종 데이터 반환
		return list;
	}
	
	// 프로덕트 검색 목록을 출력하는 메소드
	public ArrayList<Product> selectSearchCategoryList(String search, int categoryId, int limit1, int limit2) throws Exception {
		// ArrayList 생성
		ArrayList<Product> list = new ArrayList<Product>();
		
		DBUtil dbUtil = new DBUtil();	// 데이터베이스 정보가 담긴 객체 생성
		Connection conn = dbUtil.getConnection(); // 데이터베이스 접속
		
		// SQL 명령, 명령 준비
		String sql = "select product_id, category_id, product_name, product_price, product_content, product_soldout, product_pic from product where product_name like ? and category_id = ? order by product_id asc limit ?, ?";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setString(1, "%"+search+"%");	// 첫 번째 인자 (검색어)
		stmt.setInt(2, categoryId);		// 두 번째 인자 (카테고리 번호)
		stmt.setInt(3, limit1);		// 세 번째 인자 (시작 데이터)
        stmt.setInt(4, limit2);		// 네 번째 인자 (데이터 개수)       
		
		// SQL 명령 실행
		ResultSet rs = stmt.executeQuery();
		
		// 데이터베이스 내용 불러오기
		
		if (!search.equals(null) && !search.equals("")) {	// 검색어가 없는경우는 건너뜀, 검색어가 있는경우에만 list에 결과를 추가
			while(rs.next()) {
				Product product = new Product();	// 프로덕트 객체 생성
				product.setProductId(rs.getInt("product_id"));
				product.setCategoryId(rs.getInt("category_id"));
				product.setProductName(rs.getString("product_name"));
				product.setProductPrice(rs.getInt("product_price"));
				//product.productContent = rs.getString("product_content");
				product.setProductSoldout(rs.getString("product_soldout"));
				product.setProductPic(rs.getString("product_pic"));
				list.add(product);	// 리스트에 데이터 추가
			}
		}
		
		conn.close(); // 데이터베이스 사용을 다 했으면 접속을 종료한다.
		
		// 최종 데이터 반환
		return list;
	}
	
	// 프로덕트 목록 중 6개만 추천 프로덕트를 출력하는 메소드
	public ArrayList<Product> selectProductPicList() throws Exception {
		// ArrayList 생성
		ArrayList<Product> list = new ArrayList<Product>();
		
		DBUtil dbUtil = new DBUtil();	// 데이터베이스 정보가 담긴 객체 생성
		Connection conn = dbUtil.getConnection(); // 데이터베이스 접속
		
		// SQL 명령, 명령 준비
		String sql = "select product_id, category_id, product_name, product_price, product_content, product_soldout, product_pic from product order by product_id asc limit 0, 6";
		PreparedStatement stmt = conn.prepareStatement(sql);
		
		// SQL 명령 실행
		ResultSet rs = stmt.executeQuery();
		
		// 데이터베이스 내용 불러오기
		while(rs.next()) {
			Product product = new Product();	// 프로덕트 객체 생성
			product.setProductId(rs.getInt("product_id"));
			product.setCategoryId(rs.getInt("category_id"));
			product.setProductName(rs.getString("product_name"));
			product.setProductPrice(rs.getInt("product_price"));
			//product.productContent = rs.getString("product_content");
			product.setProductSoldout(rs.getString("product_soldout"));
			product.setProductPic(rs.getString("product_pic"));
			list.add(product);	// 리스트에 데이터 추가
		}
		
		conn.close(); // 데이터베이스 사용을 다 했으면 접속을 종료한다.
		
		// 최종 데이터 반환
		return list;
	}

	// 특정한 카테고리의 프로덕트 목록을 출력하는 메소드
	public ArrayList<Product> selectProductListById(int categoryId, int limit1, int limit2) throws Exception {
		// ArrayList 생성
		ArrayList<Product> list = new ArrayList<Product>();
		
		DBUtil dbUtil = new DBUtil();	// 데이터베이스 정보가 담긴 객체 생성
		Connection conn = dbUtil.getConnection(); // 데이터베이스 접속
		
		// SQL 명령, 명령 준비
		String sql = "select product_id, category_id, product_name, product_price, product_content, product_soldout, product_pic from product where category_id = ? order by product_id asc limit ?, ?";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, categoryId);	// 첫 번째 인자 (출력할 카테고리)
		stmt.setInt(2, limit1);		// 두 번째 인자 (시작 데이터)
        stmt.setInt(3, limit2);		// 세 번째 인자 (데이터 개수)       
		
		// SQL 명령 실행
		ResultSet rs = stmt.executeQuery();
		
		// 데이터베이스 내용 불러오기
		while(rs.next()) {
			Product product = new Product();	// 프로덕트 객체 생성
			product.setProductId(rs.getInt("product_id"));
			product.setCategoryId(rs.getInt("category_id"));
			product.setProductName(rs.getString("product_name"));
			product.setProductPrice(rs.getInt("product_price"));
			//product.productContent = rs.getString("product_content");
			product.setProductSoldout(rs.getString("product_soldout"));
			product.setProductPic(rs.getString("product_pic"));
			list.add(product);	// 리스트에 데이터 추가
		}
		
		conn.close(); // 데이터베이스 사용을 다 했으면 접속을 종료한다.
		
		// 최종 데이터 반환
		return list;
	}
	
	// 전체 프로덕트의 전체 데이터 개수 구하기
	public int countAllData() throws Exception {
		int totalCount = 0;	// 기본값은 0으로
		
		DBUtil dbUtil = new DBUtil();	// 데이터베이스 정보가 담긴 객체 생성
		Connection conn = dbUtil.getConnection(); // 데이터베이스 접속
		
		// SQL 명령, 명령 준비
		String sql = "select count(*) from product";
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
	
	// 특정한 카테고리의 전체 데이터 개수 구하기
	public int countAllDataById(int thisCategoryId) throws Exception {
		int totalCount = 0;	// 기본값은 0으로
		
		DBUtil dbUtil = new DBUtil();	// 데이터베이스 정보가 담긴 객체 생성
		Connection conn = dbUtil.getConnection(); // 데이터베이스 접속
		
		// SQL 명령, 명령 준비
		String sql = "select count(*) from product where category_id = ?";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, thisCategoryId);		// 첫 번째 인자 (시작 데이터)
		
		// SQL 명령 실행
		ResultSet rs = stmt.executeQuery();
		
		conn.close(); // 데이터베이스 사용을 다 했으면 접속을 종료한다.
		
		if (rs.next()) {
			totalCount = rs.getInt("count(*)");
		}
		
		// 최종 데이터 반환
		return totalCount;
	}
	
	// 프로덕트의 검색된 전체 데이터 개수 구하기
	public int countSearchData(String search) throws Exception {
		int totalCount = 0;	// 기본값은 0으로
		
		DBUtil dbUtil = new DBUtil();	// 데이터베이스 정보가 담긴 객체 생성
		Connection conn = dbUtil.getConnection(); // 데이터베이스 접속
		
		// SQL 명령, 명령 준비
		String sql = "select count(*) from product where product_name like ?";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setString(1, "%" + search + "%");
		
		// SQL 명령 실행
		ResultSet rs = stmt.executeQuery();
		
		if (rs.next()) {
			totalCount = rs.getInt("count(*)");
		}
		
		conn.close(); // 데이터베이스 사용을 다 했으면 접속을 종료한다.
				
		// 최종 데이터 반환
		return totalCount;
	}
	
	// 프로덕트의 검색된 카테고리별 데이터 개수 구하기
	public int countSearchCategoryData(String search, int categoryId) throws Exception {
		int totalCount = 0;	// 기본값은 0으로
		
		DBUtil dbUtil = new DBUtil();	// 데이터베이스 정보가 담긴 객체 생성
		Connection conn = dbUtil.getConnection(); // 데이터베이스 접속
		
		// SQL 명령, 명령 준비
		String sql = "select count(*) from product where product_name like ? and category_id = ?";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setString(1, "%" + search + "%");
		stmt.setInt(2, categoryId);
		
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