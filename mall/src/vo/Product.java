package vo;

public class Product {
	//private로 캡슐화
	private int productId;
	private int categoryId;
	private String productName;
	private int productPrice;
	private String productContent;
	private String productSoldout;
	private String productPic;
	
	//오른쪽마우스 -> Source - > generate get/set 클릭
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
