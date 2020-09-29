package dao;


import vo.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;
import commons.DBUtil;

public class NoticeDao {
	
	//�Խ��� ����� ����ϴ� �޼���
	public ArrayList<Notice> selectNoticeList() throws Exception{
		//����Ʈ����
		ArrayList<Notice> list = new ArrayList<Notice>();
		//DBUtil �޼��忡�� DB�� ���� ������ �ҷ���. �ߺ�(ª�� ��������)
		DBUtil dbUtil = new DBUtil();
		Connection conn = dbUtil.getConnection();
		//������
		String sql="SELECT notice_id,notice_title FROM notice ORDER BY notice_date DESC LIMIT 0,2";
		PreparedStatement stmt = conn.prepareStatement(sql);
		ResultSet rs = stmt.executeQuery();
		
		while(rs.next()) {
			Notice n = new Notice();
			//n.noticeId = rs.getInt("notice_id");
			n.setNoticeId(rs.getInt("notice_id"));//�����Ҷ��� set �ҷ��ö��� get
			//n.noticeTitle = rs.getString("notice_title");
			n.setNoticeTitle( rs.getString("notice_title"));
			list.add(n);
		}
		conn.close();//�����ͺ��̽� �ݱ�3
		return list;
		
	}
	//�Խ��� ����� �󼼺���� ����ϴ� �޼���
	public Notice selectNoticeOne(int noticeId) throws Exception {
		
		Notice notice = new Notice();
		
		//DBUtil �޼��忡�� DB�� ���� ������ �ҷ���.
		DBUtil dbUtil = new DBUtil();
		Connection conn = dbUtil.getConnection();
		//������
		String sql="select notice_id,notice_title,notice_content,notice_date from notice where notice_id=?";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1,noticeId);
		//��������
		ResultSet rs = stmt.executeQuery();
		while(rs.next()) {
			//notice.noticeId = rs.getInt("notice_id");
			//notice.noticeTitle = rs.getString("notice_title");
			//notice.noticeContent = rs.getString("notice_content");
			//notice.noticeDate = rs.getString("notice_date");
			notice.setNoticeId(rs.getInt("notice_id"));//�����Ҷ��� set �ҷ��ö��� get
			notice.setNoticeTitle( rs.getString("notice_title"));//private ĸ��ȭ
			notice.setNoticeContent(rs.getString("notice_content"));
			notice.setNoticeDate(rs.getString("notice_date"));
			
		}
		conn.close();
		return notice;
	}
}
