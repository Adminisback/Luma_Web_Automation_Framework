package Luma_Test.objectFunctionalities;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Luma_ElementLocators.Luma_SuccessPage_Locators;
import Luma_Test.abstractComponents.Luma_CommonComponents;

public class Luma_ValidateOrderedProducts extends  Luma_CommonComponents implements Luma_SuccessPage_Locators{

	WebDriver driver; 
	Boolean checkOrderedProducts;
	
	public Luma_ValidateOrderedProducts(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = orderedProductsListI)
	List<WebElement> orderedProductsList;
	
	public Boolean verifyOrderedProducts(String productToSelect) {
		for (WebElement orderedP : orderedProductsList) {
			String getTextOfP = orderedP.getText();
			if (getTextOfP.equalsIgnoreCase(productToSelect)) {
				Boolean checkOrderedProducts = getTextOfP.equalsIgnoreCase(productToSelect);
				return checkOrderedProducts;
			}
		}
		return checkOrderedProducts;

	}
}
