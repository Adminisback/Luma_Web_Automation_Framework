package Luma_Test.testComponents;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.beust.jcommander.defaultprovider.PropertyFileDefaultProvider;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import Luma_Test.objectFunctionalities.Luma_LoginPage;
import Luma_Test.tests.Luma_PurchaseProducts_Test;
import io.github.bonigarcia.wdm.WebDriverManager;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class Luma_BaseClass {

	public WebDriver driver;
	public Luma_LoginPage landingPage;
	String browserFilePath = System.getProperty("user.dir") + "\\src\\main\\java\\Luma_Test\\resources\\Luma_BrowserProperties.properties";

	public WebDriver initialiazeDriverFirst() throws IOException {
		Properties prop = new Properties();
		
		
		
		FileInputStream file = new FileInputStream(browserFilePath);
		prop.load(file);
		String browserName = prop.getProperty("browser");

		if (browserName.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			
			//Close automated chrome browser sentence 
			ChromeOptions options = new ChromeOptions();
			options.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));
			
			driver = new ChromeDriver(options);
			
		} else if (browserName.equalsIgnoreCase("edge")) {
			// Code for edge driver;
		} else if (browserName.equalsIgnoreCase("firefox")) {
           // Code for Firefox driver;
		}
		return driver;

	}

	@BeforeMethod(alwaysRun = true)
	public Luma_LoginPage launchWebURLDriver() throws IOException {
		driver = initialiazeDriverFirst();
		landingPage = new Luma_LoginPage(driver);
		landingPage.openURL();
		return landingPage;
	}

	@AfterMethod(alwaysRun=true)
	public void closeDriver() throws IOException, InterruptedException {
	    // driver.close();
		
	}

	public String takeScreenShotForFailedTC(String takeScreenShot, WebDriver driver) throws IOException {
		TakesScreenshot takeScreenS = (TakesScreenshot) driver;
		File source = takeScreenS.getScreenshotAs(OutputType.FILE);
		File fl = new File(System.getProperty("user.dir") + "\\reports\\" + takeScreenShot + ".png");
		FileUtils.copyFile(source, fl);
		return System.getProperty("user.dir") + "\\reports\\" + takeScreenShot + ".png";
	}

	public List<HashMap<String, String>> getData(String filePath) throws IOException {

		String jsonContent = FileUtils.readFileToString(new File(filePath), StandardCharsets.UTF_8);

		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String, String>> data = mapper.readValue(jsonContent,
				new TypeReference<List<HashMap<String, String>>>() {

				});
		return data;
	}

	

}
