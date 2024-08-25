package Luma_ElementLocators;

public interface Luma_ProductCataloguePage_Locators {

	public static final String loadingI = "//img[@alt='Loading...']";

	public static final String listOfAllProductsI = "//div[@class='product-item-info']/div/strong/a";

	String moveToProduct1 = "//li[@class='item product product-item']/div/div/strong/a[contains(text(),'";
	String moveToProduct2 = "')]";
	String moveToProduct3 = "')]/../../div[3]/div/div/form/button";
	String addToCart = "')]/../../div[3]/div/div/form/button";
	String productToSelect = "Grayson Crewneck Sweatshirt";

}
