package vo;

public class Category {
	private int categoryId;
	private String categoryName;
	
	private String categoryPic;
	private String categoryCk;
	
	public int getCategoryId() {
		return categoryId;
	}
	
	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}
	
	public String getCategoryName() {
		return categoryName;
	}
	
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	
	public String getCategoryPic() {
		return categoryPic;
	}
	
	public void setCategoryPic(String categoryPic) {
		this.categoryPic = categoryPic;
	}
	
	public String getCategoryCk() {
		return categoryCk;
	}
	
	public void setCategoryCk(String categoryCk) {
		this.categoryCk = categoryCk;
	}
}