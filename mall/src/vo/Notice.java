package vo;

public class Notice {
	private int noticeId;			// 공지사항 번호
	private String noticeTitle;		// 공지사항 제목
	private String noticeContent;	// 공지사항 내용
	private String noticeDate;		// 공지사항 게시일시
	
	public int getNoticeId() {
		return noticeId;
	}
	
	public void setNoticeId(int noticeId) {
		this.noticeId = noticeId;
	}
	
	public String getNoticeTitle() {
		return noticeTitle;
	}
	
	public void setNoticeTitle(String noticeTitle) {
		this.noticeTitle = noticeTitle;
	}
	
	public String getNoticeContent() {
		return noticeContent;
	}
	
	public void setNoticeContent(String noticeContent) {
		this.noticeContent = noticeContent;
	}
	
	public String getNoticeDate() {
		return noticeDate;
	}
	
	public void setNoticeDate(String noticeDate) {
		this.noticeDate = noticeDate;
	}
	
}