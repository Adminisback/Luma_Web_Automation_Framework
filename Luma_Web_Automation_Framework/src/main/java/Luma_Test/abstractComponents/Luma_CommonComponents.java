package Luma_Test.abstractComponents;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import Luma_Test.objectFunctionalities.Luma_ValidateOrderedProducts;

public class Luma_CommonComponents {

	WebDriver driver;

	public Luma_CommonComponents(WebDriver driver) {
		this.driver = driver;
	}

	@FindBy(xpath = "//a[@class='action showcart']")
	WebElement clickOnCartIcon;

	@FindBy(xpath = "//span[text()='View and Edit Cart']")
	WebElement clickOnViewEdit;

	@FindBy(xpath = "//img[@alt='Loading...']")
	WebElement loading;

	@FindBy(id = "ui-id-1")
	WebElement waitOnClickOfCart;

	@FindBy(xpath = "//div[@class='ea-stickybox-hide']")
	WebElement closeAdvertisePopup;
	



	public void waitForElementsAppear(WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, java.time.Duration.ofSeconds(20));
		wait.until(ExpectedConditions.visibilityOf(element));
	}

	public void waitForAllElementsAppear(By findBy) {
		WebDriverWait wait = new WebDriverWait(driver, java.time.Duration.ofSeconds(20));
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(findBy));
	}

	public void waitForInvisibilityOfElementAppear(WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, java.time.Duration.ofSeconds(20));
		wait.until(ExpectedConditions.invisibilityOf(element));
	}

	public void scrollDownPage() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,600)");
	}

	public void waitForLoading() {
		waitForInvisibilityOfElementAppear(loading);
	}

	public void waitForElemetAppear(WebElement ele) {
		WebDriverWait wait = new WebDriverWait(driver, java.time.Duration.ofSeconds(20));
		wait.until(ExpectedConditions.visibilityOf(ele));
	}

//	public Actions actionMethod(Actions act) {
//		act = new Actions(driver);
//		return act;
//	}

	public void goToCart() throws InterruptedException {
		clickOnCartIcon.click();
		Thread.sleep(2000);
		waitForElementsAppear(waitOnClickOfCart);
		clickOnViewEdit.click();
		waitForLoading();
		closeAdvertisementPopup();
		scrollDownPage();

	}

	public Luma_ValidateOrderedProducts goToOrdersPage() throws InterruptedException {
		clickOnCartIcon.click();
		Thread.sleep(2000);
		waitForElementsAppear(waitOnClickOfCart);
		clickOnViewEdit.click();
		waitForLoading();
		scrollDownPage();
		Luma_ValidateOrderedProducts pro = new Luma_ValidateOrderedProducts(driver);
		return pro;

	}

	public Object closeAdvertisementPopup() {
		try {

			if (closeAdvertisePopup.isDisplayed()) {
				waitForElemetAppear(closeAdvertisePopup);
				closeAdvertisePopup.click();
			}
		} catch (Exception e) {
			System.out.println("No advertisement popup shown on page.Continue your Shopping.");
			return null;
		}
		return null;
	}

}
