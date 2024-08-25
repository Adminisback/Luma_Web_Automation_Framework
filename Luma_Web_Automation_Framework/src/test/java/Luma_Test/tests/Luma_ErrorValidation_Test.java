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

public class Luma_ErrorValidation_Test extends Luma_BaseClass implements JsonFilesPath {

	List<HashMap<String,String>> JsData;

	@Test(dataProvider = "get_LoginError_TestData", groups= {"ErrorValidationTest"})
	public void Luma_checkLoginError(HashMap<String,String> errorInputData) throws InterruptedException, IOException {
  // Changing below expected message, to fail test ....
		String expectedMessage = errorInputData.get("loginErrorMessage");
		landingPage.signInTo_Luma(errorInputData.get("userName"),errorInputData.get("password"));
		Assert.assertEquals(expectedMessage, landingPage.getErrorMessage());

	}

	
	@Test(dataProvider = "get_ValidateOrderedProducts_TestData", groups= {"ErrorValidationTest"})
	public void productValidation(HashMap<String,String> errorInputData) throws InterruptedException, IOException {

		landingPage.signInTo_Luma(errorInputData.get("userName"),errorInputData.get("password"));
		Luma_ProductsCataloguePage productPage = landingPage.goToProductsListPage();
		productPage.scrollDownPage();
		Luma_ProductConfigurationPage chooseProductConfig = productPage.getProductByNameAndAddToCart(errorInputData.get("productToSelect"));
		chooseProductConfig.waitForLoading();
		Luma_CartPage cart = chooseProductConfig.configureSelectedProduct(errorInputData.get("colorToSelect"),errorInputData.get("quantityToSelect"));
		cart.goToCart();
		Boolean orderedProducts = cart.checkOrderedProducts(errorInputData.get("productToSelect"));
		Assert.assertTrue(orderedProducts);

	}
	

	@DataProvider
	public Object[][] get_LoginError_TestData() throws IOException {
		
		 JsData = getData(JsonFilesPath.filePath_ErrorValidation_Json);
		
		return new Object [][] {{JsData.get(0)}};
		
	}
	
	@DataProvider
	public Object[][] get_ValidateOrderedProducts_TestData() throws IOException {
		
		return new Object [][] {{JsData.get(1)}};
		
	}

}
