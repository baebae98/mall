package vo;

public class Product {
	private int productId;			// 상품 고유번호
	private int categoryId;			// 상품이 속한 카테고리 고유번호
	private String productName;		// 상품 이름
	private int productPrice;		// 상품 가격
	private String productContent;	// 상품 설명
	private String productSoldout;	// 상품 품절여부 (Y이면 품절, N이면 판매중)
	private String productPic;		// 상품 이미지 (default.jsp)
	
	public int getProductId() {
		return productId;
	}
	
	public void setProductId(int productId) {
		this.productId = productId;
	}
	
	public int getCategoryId() {
		return categoryId;
	}
	
	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}
	
	public String getProductName() {
		return productName;
	}
	
	public void setProductName(String productName) {
		this.productName = productName;
	}
	
	public int getProductPrice() {
		return productPrice;
	}
	
	public void setProductPrice(int productPrice) {
		this.productPrice = productPrice;
	}
	
	public String getProductContent() {
		return productContent;
	}
	
	public void setProductContent(String productContent) {
		this.productContent = productContent;
	}
	
	public String getProductSoldout() {
		return productSoldout;
	}
	
	public void setProductSoldout(String productSoldout) {
		this.productSoldout = productSoldout;
	}
	
	public String getProductPic() {
		return productPic;
	}
	
	public void setProductPic(String productPic) {
		this.productPic = productPic;
	}
}