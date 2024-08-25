package Luma_Test.objectFunctionalities;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import Luma_ElementLocators.Luma_CartPage_Locators;
import Luma_Test.abstractComponents.Luma_CommonComponents;

public class Luma_CartPage extends Luma_CommonComponents implements Luma_CartPage_Locators {

	WebDriver driver;
	Boolean checkOrderedProducts;

	public Luma_CartPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}

	@FindBy(xpath = orderedProductsListI)
	List<WebElement> orderedProductsList;

	@FindBy(xpath = checkOutProductI)
	WebElement checkOutProduct;

	public Boolean checkOrderedProducts(String productToSelect) {
		for (WebElement orderedP : orderedProductsList) {
			String getTextOfP = orderedP.getText();
			if (getTextOfP.equalsIgnoreCase(productToSelect)) {
				Boolean checkOrderedProducts = getTextOfP.equalsIgnoreCase(productToSelect);
				return checkOrderedProducts;
			}
		}
		return checkOrderedProducts;

	}

	public Luma_ShippingPage proceedToCheckOut() throws InterruptedException {
		checkOutProduct.click();
		waitForLoading();
		scrollDownPage();
		Thread.sleep(1000);
		closeAdvertisementPopup();
		Luma_ShippingPage shipProduct = new Luma_ShippingPage(driver);
		return shipProduct;
	}

}
