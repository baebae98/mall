package dao;

import java.util.*;
import commons.DBUtil;
import vo.*;
import java.sql.*;

public class MemberDao {
	//
	public String login(Member member) throws Exception{
		//Member returnMember = null; //�ɹ��� ���� �����ϵǸ� ����
		String memberEmail = null;
		DBUtil dbUtil = new DBUtil();
		Connection conn = dbUtil.getConnection();
		String sql="select member_email from member where member_email=? and member_pw=?";
		PreparedStatement stmt = conn.prepareStatement(sql);
		//stmt.setString(1, member.memberEmail);
		//stmt.setString(2, member.memberPw);
		stmt.setString(1, member.getMemberEmail());//ĸ��ȭ
		stmt.setString(2, member.getMemberPw());//ĸ��ȭ
		ResultSet rs = stmt.executeQuery();
		if(rs.next()) {//�α��μ���
			memberEmail = rs.getString("member_email");
		}
		conn.close();
		return memberEmail;//���� �ƴѰ��� ���ϵǸ� �α��� ����
	}
	//
	public void insertMember(Member member) throws Exception {
		String sql="insert into member(member_email,member_pw,member_name,member_date) values(?,?,?,now())";
		//Member member = null;//null�� ���ϵǸ� ��밡�� null�̸��ϵǸ� ��� x 
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
	
	//ȸ�������Ҷ� �̸��� ��������� ����� �ƴ��� Ȯ���ϴ� �޼���
	public Member selectMemberEmailCk(String memberEmail) throws Exception {
		Member member = null;//null�� ���ϵǸ� ��밡�� null�̸��ϵǸ� ��� x 
		DBUtil dbUtil = new DBUtil();
		Connection conn = dbUtil.getConnection();
		String sql="select id from (select member_email id from member union select admin_id id from admin) t where id=?";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setString (1,memberEmail);
		ResultSet rs = stmt.executeQuery();
		if(rs.next()) {
			//���� �Է��� �̸����� �̹� �������̶� ��� �Ұ���
			member = new Member();
			//member.memberEmail = rs.getString("id");
			member.setMemberEmail(rs.getString("id"));
		}
		conn.close();
		return member;
	}
}
