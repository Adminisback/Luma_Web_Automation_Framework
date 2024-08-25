package Luma_Test.objectFunctionalities;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import Luma_ElementLocators.Luma_ProductConfigurationPage_Locators;
import Luma_Test.abstractComponents.Luma_CommonComponents;

public class Luma_ProductConfigurationPage extends Luma_CommonComponents implements Luma_ProductConfigurationPage_Locators{

	WebDriver driver;


	public Luma_ProductConfigurationPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	
	@FindBy(xpath=priceOfTheSelectedPrductI)
	WebElement priceOfTheSelectedPrduct;

	@FindBy(xpath = selectColorI)
	WebElement selectColor;

	@FindBy(id = selectQuantityI)
	WebElement selectQuantity;

	@FindBy(xpath = addToCartI)
	WebElement addToCart;

	public Luma_CartPage configureSelectedProduct(String colorToSelect, String quantity) throws InterruptedException {
		closeAdvertisementPopup();
		System.out.println(priceI + priceOfTheSelectedPrduct.getText());
		scrollDownPage();
		selectColor.click();
		waitForLoading();
		driver.findElement(By.xpath("//*[@aria-label='" + colorToSelect + "']")).click();
		waitForLoading();
		Thread.sleep(1000);
		selectQuantity.clear();
		selectQuantity.sendKeys(quantity);
		addToCart.click();
		Thread.sleep(3000);
		waitForLoading();
		scrollDownPage();
		Luma_CartPage cart = new Luma_CartPage(driver);
		return cart;
	}
}
