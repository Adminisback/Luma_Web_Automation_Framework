package Luma_Test.objectFunctionalities;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import Luma_ElementLocators.Luma_LoginPage_Locators;
import Luma_Test.abstractComponents.Luma_CommonComponents;

public class Luma_LoginPage extends Luma_CommonComponents implements Luma_LoginPage_Locators {

	WebDriver driver;
	String baseURL = "https://magento.softwaretestingboard.com/";

	public Luma_LoginPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = userNameI)
	WebElement userName;

	@FindBy(xpath = passwordI)
	WebElement password;

	@FindBy(xpath = signInButtonI)
	WebElement signInButton;

	@FindBy(linkText = clickOnSignInI)
	WebElement clickOnSignIn;

	@FindBy(id = moveOnMensDropDownI)
	WebElement moveOnMensDropDown;

	@FindBy(xpath = moveOnTopsI)
	WebElement moveOnTops;

	@FindBy(xpath = clickOnSelectedCategoriesI)
	WebElement clickOnSelectedCategories;

	@FindBy(xpath = errorMessageI)
	WebElement errorMessage;

	@FindBy(xpath = waitToShowHomePageI)
	WebElement waitToShowHomePage;

	@FindBy(xpath = waitToShowLogInPageI)
	WebElement waitToShowLogInPage;

	By waitForAppearingProducts = By.xpath(waitForAppearingProductsI);

	public void openURL() {
		driver.get(baseURL);
		driver.manage().window().maximize();
		clickOnSignIn.click();
		waitForElementsAppear(waitToShowLogInPage);
	}

	public void signInTo_Luma(String userN, String paswd) {
		userName.sendKeys(userN);
		password.sendKeys(paswd);
		signInButton.click();
		waitForElementsAppear(waitToShowHomePage);
	}

	public Luma_ProductsCataloguePage goToProductsListPage() {
		Actions act = new Actions(driver);
		act.moveToElement(moveOnMensDropDown).build().perform();
		act.moveToElement(moveOnTops).build().perform();
		clickOnSelectedCategories.click();
		waitForAllElementsAppear(waitForAppearingProducts);
		Luma_ProductsCataloguePage productPage = new Luma_ProductsCataloguePage(driver);
		return productPage;
	}

	public String getErrorMessage() {
		waitForElemetAppear(errorMessage);
		return errorMessage.getText();
	}

}
