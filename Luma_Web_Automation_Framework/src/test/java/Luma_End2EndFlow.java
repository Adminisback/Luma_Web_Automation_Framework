
import org.testng.Assert;
import org.testng.annotations.*;

import Luma_Test.objectFunctionalities.Luma_CartPage;
import Luma_Test.objectFunctionalities.Luma_LoginPage;
import Luma_Test.objectFunctionalities.Luma_ProductConfigurationPage;
import Luma_Test.objectFunctionalities.Luma_ProductsCataloguePage;
import Luma_Test.objectFunctionalities.Luma_ShippingPage;
import Luma_Test.objectFunctionalities.Luma_SuccessPage;
import io.github.bonigarcia.wdm.WebDriverManager;

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

public class Luma_End2EndFlow {
	public WebDriver driver;
	String productToSelect = "Hero Hoodie";
	// String productToSelect1 = "Atlas Fitness Tank";
	String colorToSelect = "Green";
	// String colorToSelect1 = "Blue";

	@Test
	public void endToEndFlow() throws InterruptedException {

		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		Actions act = new Actions(driver);

		driver.get("https://magento.softwaretestingboard.com/");
		driver.manage().window().maximize();
		driver.findElement(By.linkText("Sign In")).click();
		WebDriverWait wait = new WebDriverWait(driver, java.time.Duration.ofSeconds(20));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='Customer Login']")));

		String Login = driver.findElement(By.xpath("//span[text()='Customer Login']")).getText();
		System.out.println("YOU ARE ON - " + Login);
		driver.findElement(By.xpath("//input[@id='email']")).sendKeys("s123@gmail.com");
		driver.findElement(By.xpath("//input[@id='pass']")).sendKeys("King@1234567890");
		driver.findElement(By.xpath("//button[@class='action login primary']")).click();

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//main[@id='maincontent']")));

		act.moveToElement(driver.findElement(By.id("ui-id-5"))).build().perform();
		act.moveToElement(driver.findElement(By.xpath("(//span[text()='Tops'])[2]"))).build().perform();
		driver.findElement(By.xpath("(//span[text()='Hoodies & Sweatshirts'])[2]")).click();

		wait.until(ExpectedConditions
				.visibilityOfAllElementsLocatedBy(By.xpath("//strong[@class='product name product-item-name']")));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,600)");
		List<WebElement> listofP = driver.findElements(By.xpath("//div[@class='product-item-info']/div/strong/a"));

		for (WebElement list : listofP) {
			String texts = list.getText();
			if (texts.equalsIgnoreCase(productToSelect)) {
				driver.findElement(By.xpath("//div[@class='ea-stickybox-hide']")).click();
				act.moveToElement(list.findElement(
						By.xpath("//li[@class='item product product-item']/div/div/strong/a[contains(text(),'"
								+ productToSelect + "')]")))
						.build().perform();
				wait.until(ExpectedConditions.visibilityOfElementLocated(
						By.xpath("//li[@class='item product product-item']/div/div/strong/a[contains(text(),'"
								+ productToSelect + "')]/../../div[3]/div/div/form/button")));
				WebElement add = driver.findElement(
						By.xpath("//li[@class='item product product-item']/div/div/strong/a[contains(text(),'"
								+ productToSelect + "')]/../../div[3]/div/div/form/button"));

				add.click();
				break;
			}
		}
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//img[@alt='Loading...']")));
		// Product configuration page
		WebElement price = driver.findElement(By.xpath("(//div[@class='price-box price-final_price'])[1]"));
		System.out.println("PRICE OF THE PRODUCT TO BE ORDER - " + price.getText());
		js.executeScript("window.scrollBy(0,600)");
		driver.findElement(By.xpath("//div[text()='M']")).click();
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//img[@alt='Loading...']")));
		driver.findElement(By.xpath("//*[@aria-label='" + colorToSelect + "']")).click();
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//img[@alt='Loading...']")));
		Thread.sleep(1000);
		driver.findElement(By.id("qty")).clear();
		driver.findElement(By.id("qty")).sendKeys("2");
		driver.findElement(By.xpath("//button[@title='Add to Cart']")).click();
		Thread.sleep(3000);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//img[@alt='Loading...']")));
		js.executeScript("window.scrollTo(600,0)");

		driver.findElement(By.xpath("//a[@class='action showcart']")).click();
		Thread.sleep(2000);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("ui-id-1")));
		driver.findElement(By.xpath("//span[text()='View and Edit Cart']")).click();
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//img[@alt='Loading...']")));
		js.executeScript("window.scrollBy(0,600)");

		List<WebElement> orderedProducts = driver.findElements(By.xpath("//tbody/tr/td/div/strong/a"));
		for (WebElement orderedP : orderedProducts) {
			String getTextOfP = orderedP.getText();
			if (getTextOfP.equalsIgnoreCase(productToSelect)) {
				Boolean checkOrderedProducts = getTextOfP.equalsIgnoreCase(productToSelect);
				Assert.assertTrue(checkOrderedProducts);
			}
		}
		driver.findElement(By.xpath("//button[@title='Proceed to Checkout']/span")).click();
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//img[@alt='Loading...']")));
		js.executeScript("window.scrollBy(0,600)");
		Thread.sleep(1000);

		driver.findElement(By.xpath("//input[@value='flatrate_flatrate']")).click();
		driver.findElement(By.xpath("//*[text()='Next']")).click();
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//img[@alt='Loading...']")));
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//img[@alt='Loading...']")));
		js.executeScript("window.scrollBy(0,600)");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
				"//div[div[div[div[div[div[div[button[span[text()='Place Order']]]]]]]]]/div/div/div/div/div[4]/div/button/span")));
		WebElement inToView = driver.findElement(By.xpath(
				"//div[div[div[div[div[div[div[button[span[text()='Place Order']]]]]]]]]/div/div/div/div/div[4]/div/button/span"));
		js.executeScript("arguments[0].scrollIntoView(true);", inToView);
		js.executeScript("window.scrollBy(0,600)");
		Thread.sleep(1000);

		act.click(inToView).build().perform();
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//img[@alt='Loading...']")));
		wait.until(
				ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()='Thank you for your purchase!']")));
		WebElement orderSuccess = driver.findElement(By.xpath("//*[text()='Thank you for your purchase!']"));
		System.out.println(orderSuccess.getText());
		String successMessage = driver.findElement(By.xpath("//*[text()='Your order number is: ']")).getText();
		String[] ID = successMessage.split(": ");
		String i = ID[0].trim();
		String ID1 = ID[1].trim();
		String orderID = driver.findElement(By.xpath("//*[text()='Your order number is: ']/a/strong")).getText();
		System.out.println(i + " " + ID1);
		driver.findElement(By.xpath("//*[text()='Continue Shopping']")).click();

	}

}
