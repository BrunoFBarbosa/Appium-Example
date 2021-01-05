package maventutorial.AppiumFramework;
import java.io.IOException;


import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import pageObjects.CheckoutPage;
import pageObjects.FormPage;
import pageObjects.ProductsPage;

import org.testng.Assert;
import org.testng.annotations.Test;

public class ecommerce_tc_4 extends Base {
	
	
	@Test
	public static void totalValidation() throws IOException, InterruptedException {
		AndroidDriver<AndroidElement> driver = Capabilities("GeneralStoreApp");
		
		FormPage formPage = new FormPage(driver);
		formPage.nameField.sendKeys("hello");
		driver.hideKeyboard();

		formPage.femaleButton.click();
		formPage.countryDropdown.click();
		formPage.selectCountryFromDropdown("Brazil");
		formPage.loginButton.click();
		  
		  
		  
		ProductsPage products = new ProductsPage(driver);
		CheckoutPage checkout = new CheckoutPage(driver);
		
		products.addProductToCart("Air Jordan 4 Retro");
		products.addProductToCart("Jordan 6 Rings");
		products.cartButton.click();
		
		checkout.cartTitle.isDisplayed();
		
		  
		String amount1 = checkout.productList.get(0).getText().substring(1);
		String amount2 = checkout.productList.get(1).getText().substring(1);
		String totalValue = checkout.totalAmount.getText().substring(1);
		  
		Double totalExpected = Double.parseDouble(totalValue);
		Double totalActual = Double.parseDouble(amount1) + Double.parseDouble(amount2);
		Assert.assertEquals(totalActual, totalExpected);

	}
	
}