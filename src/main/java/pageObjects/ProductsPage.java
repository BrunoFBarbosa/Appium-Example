package pageObjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class ProductsPage {
	
	AndroidDriver<AndroidElement> driver;
	public ProductsPage(AndroidDriver<AndroidElement> driver)
	{
		this.driver = driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}
	
	@AndroidFindBy(id="com.androidsample.generalstore:id/appbar_btn_cart")
	public WebElement cartButton;
	
	public void scrollToProduct(String product) {
		driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().resourceId(\"com.androidsample.generalstore:id/rvProductList\")).scrollIntoView(new UiSelector().textMatches(\""+product+"\").instance(0))");
	}
	
	
	public void addProductToCart(String product) {
		this.scrollToProduct(product);
		driver.findElementByXPath("//android.widget.TextView[@text='"+product+"']/parent::android.widget.LinearLayout//android.widget.TextView[@text='ADD TO CART']").click();
	}

}
