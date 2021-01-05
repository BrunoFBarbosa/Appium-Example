package maventutorial.AppiumFramework;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;

public class Base {
	
	public static AppiumDriverLocalService service;
	public static AndroidDriver<AndroidElement> driver;
	
	@BeforeClass
	public void setupTest() throws IOException, InterruptedException {
		startAppiumServer();
		startEmulator();
	}
	
	@AfterClass
	public void teardownTest() throws IOException, InterruptedException {
		driver.closeApp();
		stopAppiumServer();
		stopEmulator();
		
	}

	public void startAppiumServer() throws IOException, InterruptedException {
		
		AppiumServiceBuilder builder = new AppiumServiceBuilder();
	    builder.withIPAddress("127.0.0.1");
	    builder.usingPort(4723);
	    builder.withArgument(GeneralServerFlag.SESSION_OVERRIDE);
	    builder.withArgument(GeneralServerFlag.LOG_LEVEL,"error");
	    service = AppiumDriverLocalService.buildService(builder);
	    System.out.println("Starting Appium Server...");
	    service.start();
	}

	public void stopAppiumServer() {
		
		System.out.println("Stopping Appium Server...");
		service.stop();
	}

	public static AndroidDriver<AndroidElement> Capabilities(String app) throws IOException, InterruptedException {
		
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + "\\src\\main\\java\\maventutorial\\AppiumFramework\\global.properties");
		Properties prop = new Properties();
		prop.load(fis);
		
		File f = new File("src");
		File fs = new File(f, (String) prop.get(app));
		DesiredCapabilities cap = new DesiredCapabilities();

		cap.setCapability(MobileCapabilityType.DEVICE_NAME, getDevice());
		cap.setCapability(MobileCapabilityType.AUTOMATION_NAME,"uiautomator2");
		cap.setCapability(MobileCapabilityType.APP, fs.getAbsolutePath());
		
		driver = new AndroidDriver<>(new URL("http://127.0.0.1:4723/wd/hub"), cap);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		return driver;
	}
	public static String getDevice() throws IOException {
		
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + "\\src\\main\\java\\maventutorial\\AppiumFramework\\global.properties");
		Properties prop = new Properties();
		prop.load(fis);
		String device = null;
		File f = new File("src");
		if(System.getProperty("deviceType") != null) {
			device = System.getProperty("deviceType");
		}
		else {
			device = (String) prop.get("device");
		}
		return device;
	}
	
	public static String getEmulatorId() throws IOException {
		
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + "\\src\\main\\java\\maventutorial\\AppiumFramework\\global.properties");
		Properties prop = new Properties();
		prop.load(fis);
		String emulatorId = null;
		File f = new File("src");
		if(System.getProperty("emulatorId") != null) {
			emulatorId = System.getProperty("emulatorId");
		}
		else {
			emulatorId = (String) prop.get("emulatorId");
		}
		return emulatorId;
	}
	
	public void startEmulator() throws IOException, InterruptedException {
		String d = getDevice();
		System.out.println("Starting emulator '"+d+"'");
		String[] command = new String[] {System.getProperty("user.dir") + "\\src\\main\\java\\resources\\startEmulator.bat", d};
		Runtime.getRuntime().exec(command);
		Thread.sleep(6000);
	}
	
	public void stopEmulator() throws IOException, InterruptedException {
		Thread.sleep(3000);
		String emulatorId = getEmulatorId();
		System.out.println("Closing emulator with ID '"+emulatorId+"'");
		String[] command = new String[] {System.getProperty("user.dir") + "\\src\\main\\java\\resources\\stopEmulator.bat", emulatorId};
		Runtime.getRuntime().exec(command);
	}
	
	public static void takeScreenshot(String testName) throws IOException {
		File scrShot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(scrShot, new File(System.getProperty("user.dir") + "\\screenshots\\"+testName+".png"));
	}

}
