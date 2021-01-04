package maventutorial.AppiumFramework;

import org.testng.annotations.DataProvider;

public class TestData {

	@DataProvider(name="InputData")
	public Object[][] getDataForInputField()
	{
	 Object[][] obj = new Object[][] {
		 {"hello"},
	 };
			 return obj;
	}
}
