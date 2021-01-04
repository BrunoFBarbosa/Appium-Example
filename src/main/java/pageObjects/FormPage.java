package pageObjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class FormPage {
	
	AndroidDriver<AndroidElement> driver;
	public FormPage(AndroidDriver<AndroidElement> driver)
	{
		this.driver = driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}
	
	@AndroidFindBy(id="com.androidsample.generalstore:id/nameField")
	public WebElement nameField;
	
	@AndroidFindBy(id="com.androidsample.generalstore:id/radioFemale")
	public WebElement femaleButton;
	
	@AndroidFindBy(id="com.androidsample.generalstore:id/spinnerCountry")
	public WebElement countryDropdown;
	
	@AndroidFindBy(id="com.androidsample.generalstore:id/btnLetsShop")
	public WebElement loginButton;
	
	public void scrollToCountry(String country) {
		driver.findElementsByAndroidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\""+country+"\"));");
	}
	
	
	public void selectCountryFromDropdown(String country) {
		this.scrollToCountry(country);
		driver.findElementByXPath("//android.widget.TextView[@text='"+country+"']").click();
	}

}
