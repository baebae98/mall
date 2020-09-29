package dao;


import vo.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;
import commons.DBUtil;

public class NoticeDao {
	
	//게시판 목록을 출력하는 메서드
	public ArrayList<Notice> selectNoticeList() throws Exception{
		//리스트지정
		ArrayList<Notice> list = new ArrayList<Notice>();
		//DBUtil 메서드에서 DB에 관한 정보를 불러옴. 중복(짧게 쓰기위해)
		DBUtil dbUtil = new DBUtil();
		Connection conn = dbUtil.getConnection();
		//쿼리문
		String sql="SELECT notice_id,notice_title FROM notice ORDER BY notice_date DESC LIMIT 0,2";
		PreparedStatement stmt = conn.prepareStatement(sql);
		ResultSet rs = stmt.executeQuery();
		
		while(rs.next()) {
			Notice n = new Notice();
			//n.noticeId = rs.getInt("notice_id");
			n.setNoticeId(rs.getInt("notice_id"));//수정할때는 set 불러올때는 get
			//n.noticeTitle = rs.getString("notice_title");
			n.setNoticeTitle( rs.getString("notice_title"));
			list.add(n);
		}
		conn.close();//데이터베이스 닫기3
		return list;
		
	}
	//게시판 목록을 상세보기로 출력하는 메서드
	public Notice selectNoticeOne(int noticeId) throws Exception {
		
		Notice notice = new Notice();
		
		//DBUtil 메서드에서 DB에 관한 정보를 불러옴.
		DBUtil dbUtil = new DBUtil();
		Connection conn = dbUtil.getConnection();
		//쿼리문
		String sql="select notice_id,notice_title,notice_content,notice_date from notice where notice_id=?";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1,noticeId);
		//쿼리실행
		ResultSet rs = stmt.executeQuery();
		while(rs.next()) {
			//notice.noticeId = rs.getInt("notice_id");
			//notice.noticeTitle = rs.getString("notice_title");
			//notice.noticeContent = rs.getString("notice_content");
			//notice.noticeDate = rs.getString("notice_date");
			notice.setNoticeId(rs.getInt("notice_id"));//수정할때는 set 불러올때는 get
			notice.setNoticeTitle( rs.getString("notice_title"));//private 캡슐화
			notice.setNoticeContent(rs.getString("notice_content"));
			notice.setNoticeDate(rs.getString("notice_date"));
			
		}
		conn.close();
		return notice;
	}
}
