package Luma_Test.tests;

import org.testng.Assert;
import org.testng.annotations.*;

import Luma_Test.JsonData.JsonFilesPath;
import Luma_Test.objectFunctionalities.Luma_CartPage;
import Luma_Test.objectFunctionalities.Luma_LoginPage;
import Luma_Test.objectFunctionalities.Luma_ProductConfigurationPage;
import Luma_Test.objectFunctionalities.Luma_ProductsCataloguePage;
import Luma_Test.objectFunctionalities.Luma_ShippingPage;
import Luma_Test.objectFunctionalities.Luma_SuccessPage;
import Luma_Test.objectFunctionalities.Luma_ValidateOrderedProducts;
import Luma_Test.testComponents.Luma_BaseClass;
import io.github.bonigarcia.wdm.WebDriverManager;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.bouncycastle.oer.its.ieee1609dot2.basetypes.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Luma_PurchaseProducts_Test extends Luma_BaseClass implements JsonFilesPath  {
	public WebDriver driver;
	List<HashMap<String, String>> JsData;

	@Test(dataProvider = "getTestDataForTC1_PurchaseP", groups = { "PurchasePage" })
	public void luma_Purchase_Products(HashMap<String, String> inputData) throws InterruptedException, IOException {

		landingPage.signInTo_Luma(inputData.get("userName"), inputData.get("password"));
		Luma_ProductsCataloguePage productPage = landingPage.goToProductsListPage();
		productPage.scrollDownPage();
		Luma_ProductConfigurationPage chooseProductConfig = productPage
				.getProductByNameAndAddToCart(inputData.get("productToSelect"));
		chooseProductConfig.waitForLoading();
		Luma_CartPage cart = chooseProductConfig.configureSelectedProduct(inputData.get("colorToSelect"),
				inputData.get("quantityToSelect"));
		cart.goToCart();
		Boolean orderedProducts = cart.checkOrderedProducts(inputData.get("productToSelect"));
		Assert.assertTrue(orderedProducts);
		Luma_ShippingPage shipProduct = cart.proceedToCheckOut();
		shipProduct.confirmShipping();
		shipProduct.reviewAndPayments();
		Luma_SuccessPage successPage = shipProduct.proceedOrder();
		String con = successPage.checkConfirmationMessage();
		Assert.assertTrue(con.contentEquals(inputData.get("successMessage")));
		successPage.getOrderMessage();

	}

	@Test(dataProvider = "getTestDataForTC2_OrderedP", dependsOnMethods = { "luma_Purchase_Products" })
	public void CheckOrderProducts(HashMap<String, String> inputData) throws InterruptedException, IOException {

		landingPage.signInTo_Luma(inputData.get("userName"), inputData.get("password"));
		Luma_ProductsCataloguePage productPage = landingPage.goToProductsListPage();
		productPage.scrollDownPage();
		Luma_ProductConfigurationPage chooseProductConfig = productPage
				.getProductByNameAndAddToCart(inputData.get("productToSelect"));
		chooseProductConfig.waitForLoading();
		chooseProductConfig.configureSelectedProduct(inputData.get("colorToSelect"), inputData.get("quantityToSelect"));
		Luma_ValidateOrderedProducts op = landingPage.goToOrdersPage();
		Assert.assertTrue(op.verifyOrderedProducts(inputData.get("productToSelect")));

	}

	@DataProvider
	public Object[][] getTestDataForTC1_PurchaseP() throws IOException {

		JsData = getData(JsonFilesPath.filePath_PurchaseProducts_Json);

		return new Object[][] { { JsData.get(0) }, { JsData.get(1) } };

	}

	@DataProvider
	public Object[][] getTestDataForTC2_OrderedP() throws IOException {

		return new Object[][] { { JsData.get(2) } };

	}

//	@DataProvider
//	public Object[][] getTestData() {
//		
//		HashMap<Object,Object> map = new HashMap<Object,Object>();
//		map.put("userName","s123@gmail.com");
//		map.put("password", "King@1234567890");
//		map.put("productToSelect", "Grayson Crewneck Sweatshirt");
//		
//		HashMap<Object,Object> map1 = new HashMap<Object,Object>();
//		map1.put("userName","Kshirish123@gmail.com");
//		map1.put("password", "King@12345");
//		map1.put("productToSelect", "Ajax Full-Zip Sweatshirt");
//		
//		
//		return new Object [][] {{map},{map1}};
//	}

//	@DataProvider
//	public Object[][] getTestData() {
//		return new Object [][] {{"s123@gmail.com","King@12345},{map1}};
//	}

}
