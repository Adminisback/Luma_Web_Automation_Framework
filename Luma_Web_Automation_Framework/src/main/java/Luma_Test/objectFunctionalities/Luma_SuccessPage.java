package Luma_Test.objectFunctionalities;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Luma_ElementLocators.Luma_SuccessPage_Locators;
import Luma_Test.abstractComponents.Luma_CommonComponents;

public class Luma_SuccessPage extends Luma_CommonComponents implements Luma_SuccessPage_Locators{

	WebDriver driver;

	public Luma_SuccessPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}

	@FindBy(xpath = confirmationMessageI)
	WebElement confirmationMessage;

	@FindBy(xpath = orderMessageI)
	WebElement orderMessage;

	@FindBy(xpath = continueShoppingI)
	WebElement continueShopping;
	
	@FindBy(xpath = waitForConfirmMessageI)
	WebElement waitForConfirmMessage;


	public String checkConfirmationMessage() {
		waitForLoading();
		waitForElementsAppear(waitForConfirmMessage);
		 return confirmationMessage.getText();
       
	}

	public void getOrderMessage() {
		String orderM = orderMessage.getText();
		String[] ID = orderM.split(": ");
		String i = ID[0].trim();
		String ID1 = ID[1].trim();
		System.out.println(i + " " + ID1);
		continueShopping.click();

	}
}
