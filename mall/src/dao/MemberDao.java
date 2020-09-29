package dao;

import java.util.*;
import commons.DBUtil;
import vo.*;
import java.sql.*;

public class MemberDao {
	//
	public String login(Member member) throws Exception{
		//Member returnMember = null; //맴버가 눌로 리리턴되면 실패
		String memberEmail = null;
		DBUtil dbUtil = new DBUtil();
		Connection conn = dbUtil.getConnection();
		String sql="select member_email from member where member_email=? and member_pw=?";
		PreparedStatement stmt = conn.prepareStatement(sql);
		//stmt.setString(1, member.memberEmail);
		//stmt.setString(2, member.memberPw);
		stmt.setString(1, member.getMemberEmail());//캡슐화
		stmt.setString(2, member.getMemberPw());//캡슐화
		ResultSet rs = stmt.executeQuery();
		if(rs.next()) {//로그인성공
			memberEmail = rs.getString("member_email");
		}
		conn.close();
		return memberEmail;//눌이 아닌값이 리턴되면 로그인 성공
	}
	//
	public void insertMember(Member member) throws Exception {
		String sql="insert into member(member_email,member_pw,member_name,member_date) values(?,?,?,now())";
		//Member member = null;//null이 리턴되면 사용가능 null이리턴되면 사용 x 
		DBUtil dbUtil = new DBUtil();
		Connection conn = dbUtil.getConnection();
		PreparedStatement stmt = conn.prepareStatement(sql);
		//stmt.setString(1,member.memberEmail);
		//stmt.setString(2,member.memberPw);
		//stmt.setString(3,member.memberName);
		stmt.setString(1, member.getMemberEmail());
		stmt.setString(2, member.getMemberPw());
		stmt.setString(3, member.getMemberName());
		stmt.executeUpdate();
		
		conn.close();
	}
	
	//회원가입할때 이메일 사용중인지 사용중 아닌지 확인하는 메서드
	public Member selectMemberEmailCk(String memberEmail) throws Exception {
		Member member = null;//null이 리턴되면 사용가능 null이리턴되면 사용 x 
		DBUtil dbUtil = new DBUtil();
		Connection conn = dbUtil.getConnection();
		String sql="select id from (select member_email id from member union select admin_id id from admin) t where id=?";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setString (1,memberEmail);
		ResultSet rs = stmt.executeQuery();
		if(rs.next()) {
			//지금 입력한 이메일은 이미 가입중이라 사용 불가능
			member = new Member();
			//member.memberEmail = rs.getString("id");
			member.setMemberEmail(rs.getString("id"));
		}
		conn.close();
		return member;
	}
}
