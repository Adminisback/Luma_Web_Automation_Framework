package Luma_ElementLocators;

public interface Luma_LoginPage_Locators {

	public static final String userNameI = "//input[@id='email']";

	public static final String passwordI = "//input[@id='pass']";

	public static final String signInButtonI = "//button[@class='action login primary']";

	public static final String clickOnSignInI = "Sign In";

	public static final String moveOnMensDropDownI = "ui-id-5";

	public static final String moveOnTopsI = "(//span[text()='Tops'])[2]";

	public static final String clickOnSelectedCategoriesI = "(//span[text()='Hoodies & Sweatshirts'])[2]";

	public static final String errorMessageI = "//div[@class='messages']/div/div";

	public static final String waitToShowHomePageI = "//main[@id='maincontent']";

	public static final String waitToShowLogInPageI = "//span[text()='Customer Login']";

	public static final String waitForAppearingProductsI = "//strong[@class='product name product-item-name']";

}
