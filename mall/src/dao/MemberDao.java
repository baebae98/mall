package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import commons.DBUtil;
import vo.Member;

public class MemberDao {
	// Member 탈퇴하는 메소드 (강제탈퇴)
	public void updateMemberSubscription(Member member) throws Exception {
		DBUtil dbUtil = new DBUtil();	// 데이터베이스 정보가 담긴 객체 생성
		Connection conn = dbUtil.getConnection(); // 데이터베이스 접속
		
		// SQL 명령, 명령 준비
		String sql = "update member set member_subscription = 'N' where member_email = ?";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setString(1, member.getMemberEmail());	// Member 타입의 memberEmail를 입력받는다.
		
		stmt.executeUpdate();
		
		conn.close(); // 데이터베이스 사용을 다 했으면 접속을 종료한다.
	}
	
	// 회원가입 시 사용자 이메일 주소 중복여부 확인 메소드
	public Member selectMemberEmailCk(String memberEmail) throws Exception {
		Member member = null;	// 멤버 객체 생성
		
		DBUtil dbUtil = new DBUtil();	// 데이터베이스 정보가 담긴 객체 생성
		Connection conn = dbUtil.getConnection(); // 데이터베이스 접속
		
		// SQL 명령, 명령 준비
		// member_email과 admin_id가 모두 중복되지 않은 경우를 파악하기 위한 SQL Query
		String sql = "select id from (select member_email id from member union select admin_id id from admin) t where id = ?";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setString(1, memberEmail);	// 첫 번째 인자 (시작 데이터)
		
		// SQL 명령 실행
		ResultSet rs = stmt.executeQuery();
		
		if (rs.next()) { // 중복된 아이디가 있는 경우
			member = new Member();
			member.setMemberEmail(rs.getString("id"));
		}
		
		conn.close(); // 데이터베이스 사용을 다 했으면 접속을 종료한다.
				
		// 최종 데이터 반환
		return member;
	}
	
	// 회원가입 시 회원가입 정보를 등록하는 메소드
	public void insertMember(Member member) throws Exception {
		DBUtil dbUtil = new DBUtil();	// 데이터베이스 정보가 담긴 객체 생성
		Connection conn = dbUtil.getConnection(); // 데이터베이스 접속
		
		// SQL 명령, 명령 준비
		// member_email과 admin_id가 모두 중복되지 않은 경우를 파악하기 위한 SQL Query
		String sql = "insert into member(member_email, member_pw, member_name, member_date, member_subscription) values(?, ?, ?, now(), 'Y')";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setString(1, member.getMemberEmail());
		stmt.setString(2, member.getMemberPw());
		stmt.setString(3, member.getMemberName());
		
		stmt.executeUpdate();
		
		conn.close(); // 데이터베이스 사용을 다 했으면 접속을 종료한다.
		
		return;
	}
	
	// 로그인 메소드
	public Member login(Member member) throws Exception {
		// 반환할 객체 생성 (기본값 null)
		Member returnMember = null;
		
		DBUtil dbUtil = new DBUtil();	// 데이터베이스 정보가 담긴 객체 생성
		Connection conn = dbUtil.getConnection(); // 데이터베이스 접속
		
		// SQL 명령, 명령 준비
		String sql = "select member_email, member_pw, member_name, member_subscription from member where member_email = ? and member_pw = ?";
		PreparedStatement stmt = conn.prepareStatement(sql);   
        
		stmt.setString(1, member.getMemberEmail());
		stmt.setString(2, member.getMemberPw());
		
		// SQL 명령 실행
		ResultSet rs = stmt.executeQuery();
		
		// 데이터베이스 내용 불러오기
		while(rs.next()) {
			returnMember = new Member(); // 멤버 객체 생성
			returnMember.setMemberEmail(rs.getString("member_email"));	// 이메일 반환
			returnMember.setMemberName(rs.getString("member_name"));	// 이름 반환
			returnMember.setMemberSubscription(rs.getString("member_subscription"));	// 가입탈퇴여부 반환
		}
		
		conn.close(); // 데이터베이스 사용을 다 했으면 접속을 종료한다.
		
		// 최종 데이터 반환
		return returnMember;	// null이면 로그인 실패, null이 아니면 로그인 성공 및 member_email 반환
	}
	
	// 회원탈퇴시 비밀번호 일치여부 확인
	public Member passwordCheck(Member member) throws Exception {
		// 반환할 객체 생성 (기본값 null)
		Member returnMember = null;
		
		DBUtil dbUtil = new DBUtil();	// 데이터베이스 정보가 담긴 객체 생성
		Connection conn = dbUtil.getConnection(); // 데이터베이스 접속
		
		// SQL 명령, 명령 준비
		String sql = "select member_email, member_pw from member where member_email = ?";
		PreparedStatement stmt = conn.prepareStatement(sql);   
        
		stmt.setString(1, member.getMemberEmail());
		
		// SQL 명령 실행
		ResultSet rs = stmt.executeQuery();
		
		// 데이터베이스 내용 불러오기
		while(rs.next()) {
			returnMember = new Member(); // 멤버 객체 생성
			returnMember.setMemberEmail(rs.getString("member_email"));	// 이메일 반환
			returnMember.setMemberPw(rs.getString("member_pw"));	// 패스워드 반환
		}
		
		conn.close(); // 데이터베이스 사용을 다 했으면 접속을 종료한다.
		
		// 최종 데이터 반환
		return returnMember;
	}
	
	// Member 상세페이지 검색용
	public Member selectMemberOne(Member member) throws Exception {		
		DBUtil dbUtil = new DBUtil();	// 데이터베이스 정보가 담긴 객체 생성
		Connection conn = dbUtil.getConnection(); // 데이터베이스 접속
		
		// SQL 명령, 명령 준비
		String sql = ("select member_email, member_name, member_date, member_subscription from member where member_email = ?");
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setString(1, member.getMemberEmail());			// memberEmail을 입력받는다.
		
		ResultSet rs = stmt.executeQuery();
		
		if (rs.next()) {
			member.setMemberEmail(rs.getString("member_email"));
			member.setMemberName(rs.getString("member_name"));
			member.setMemberDate(rs.getString("member_date"));
			member.setMemberSubscription(rs.getString("member_subscription"));
		}
		
		conn.close(); // 데이터베이스 사용을 다 했으면 접속을 종료한다.
		
		return member;
	}
}