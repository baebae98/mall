package vo;

public class Orders {
	private int ordersId;		// 주문번호
	private int productId;		// 상품번호
	private int ordersAmount;	// 상품수량
	private int ordersPrice;	// 주문시 상품가격
	private String memberEmail;	// 구매자 이메일
	private String ordersAddr ;	// 구매자 주소
	private String ordersState;	// 주문상태
	private String ordersDate;	// 구매일시
	
	public int getOrdersId() {
		return ordersId;
	}
	
	public void setOrdersId(int ordersId) {
		this.ordersId = ordersId;
	}
	
	public int getProductId() {
		return productId;
	}
	
	public void setProductId(int productId) {
		this.productId = productId;
	}
	
	public int getOrdersAmount() {
		return ordersAmount;
	}
	
	public void setOrdersAmount(int ordersAmount) {
		this.ordersAmount = ordersAmount;
	}
	
	public int getOrdersPrice() {
		return ordersPrice;
	}
	
	public void setOrdersPrice(int ordersPrice) {
		this.ordersPrice = ordersPrice;
	}
	
	public String getMemberEmail() {
		return memberEmail;
	}
	
	public void setMemberEmail(String memberEmail) {
		this.memberEmail = memberEmail;
	}
	
	public String getOrdersAddr() {
		return ordersAddr;
	}
	
	public void setOrdersAddr(String ordersAddr) {
		this.ordersAddr = ordersAddr;
	}
	
	public String getOrdersState() {
		return ordersState;
	}
	
	public void setOrdersState(String ordersState) {
		this.ordersState = ordersState;
	}
	
	public String getOrdersDate() {
		return ordersDate;
	}
	
	public void setOrdersDate(String ordersDate) {
		this.ordersDate = ordersDate;
	}
}