package vo;

public class Member {
	private String memberEmail;
	private String memberPw;
	private String memberName;
	private String memberDate;
	private String memberSubscription;
	
	public String getMemberEmail() {
		return memberEmail;
	}
	
	public void setMemberEmail(String memberEmail) {
		this.memberEmail = memberEmail;
	}
	
	public String getMemberPw() {
		return memberPw;
	}
	
	public void setMemberPw(String memberPw) {
		this.memberPw = memberPw;
	}
	
	public String getMemberName() {
		return memberName;
	}
	
	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}
	
	public String getMemberDate() {
		return memberDate;
	}
	
	public void setMemberDate(String memberDate) {
		this.memberDate = memberDate;
	}

	public String getMemberSubscription() {
		return memberSubscription;
	}

	public void setMemberSubscription(String memberSubscription) {
		this.memberSubscription = memberSubscription;
	}
}