package Luma_Test.objectFunctionalities;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Luma_ElementLocators.Luma_ShippingPage_Locators;
import Luma_Test.abstractComponents.Luma_CommonComponents;

public class Luma_ShippingPage extends Luma_CommonComponents implements Luma_ShippingPage_Locators {

	WebDriver driver;

	public Luma_ShippingPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}

	@FindBy(xpath = selectFlatRateRadioButtonI)
	WebElement selectFlatRateRadioButton;

	@FindBy(xpath = clickOnNextI)
	WebElement clickOnNext;

	@FindBy(xpath = placeOrderI )
	WebElement placeOrder;
	
	@FindBy(xpath = waitForPlaceOrderButtonI)
	WebElement waitForPlaceOrderButton;


	public void confirmShipping() throws InterruptedException {
		selectFlatRateRadioButton.click();
		Thread.sleep(1000);
		clickOnNext.click();
	}

	public void reviewAndPayments() {
		waitForLoading();
		waitForLoading();
		scrollDownPage();
		waitForElementsAppear(waitForPlaceOrderButton);

	}

	public Luma_SuccessPage proceedOrder() throws InterruptedException {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView(true);", placeOrder);
		scrollDownPage();
		Thread.sleep(1000);
		Actions act = new Actions(driver);
		act.click(placeOrder).build().perform();
		closeAdvertisementPopup();
		Luma_SuccessPage successPage = new Luma_SuccessPage(driver);
		return successPage;

	}

}
