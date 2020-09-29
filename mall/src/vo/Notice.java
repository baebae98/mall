package vo;
/*
 * private 로 캡슐화
 */
public class Notice {
	private int noticeId; //게시판 번호(자동으로 부여)
	private String noticeTitle;//게시판 제목
	private String noticeContent;//게시판 내용
	private String noticeDate; // 원래는 date해야하지만 마리아 디비의 date를 자바에서 date로 못바꿈
	
	// 필드별(멤버변수) get/set 생성
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
