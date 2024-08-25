package Luma_Test.objectFunctionalities;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.github.dockerjava.core.dockerfile.DockerfileStatement.Add;

import Luma_ElementLocators.Luma_ProductCataloguePage_Locators;
import Luma_Test.abstractComponents.Luma_CommonComponents;

public class Luma_ProductsCataloguePage extends Luma_CommonComponents implements Luma_ProductCataloguePage_Locators {

	WebDriver driver;

	public Luma_ProductsCataloguePage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = listOfAllProductsI)
	List<WebElement> listOfAllProducts;


	 By waitForProductToAppear = By.xpath(moveToProduct1 + productToSelect + moveToProduct3);

	By loading = By.xpath(loadingI);

	public List<WebElement> getProducts() {
		return listOfAllProducts;
	}

	public Luma_ProductConfigurationPage getProductByNameAndAddToCart(String productToSelect)
			throws InterruptedException {

		List<WebElement> listofP = getProducts();
		Actions act = new Actions(driver);
		for (WebElement list : listofP) {
			String texts = list.getText();
			if (texts.equalsIgnoreCase(productToSelect)) {
				
				closeAdvertisementPopup();

				act.moveToElement(list.findElement(By.xpath(moveToProduct1 + productToSelect + moveToProduct2))).build()
						.perform();
				
				 waitForAllElementsAppear(waitForProductToAppear); 
				// The above method can't use due to dynamic xPath...Can be used with interface.
				
				//Thread.sleep(1000); // adding for multiple data sets (PurchaseProductsPage.xml)
				 
				WebElement add = driver.findElement(
						By.xpath("//li[@class='item product product-item']/div/div/strong/a[contains(text(),'"
								+ productToSelect + "')]/../../div[3]/div/div/form/button"));
				add.click();
				break;

			}

		}
		Luma_ProductConfigurationPage chooseProductConfig = new Luma_ProductConfigurationPage(driver);
		return chooseProductConfig;

	}

}
