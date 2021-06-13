package tests;

import com.google.common.collect.ImmutableMap;
import io.appium.java_client.MobileDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.*;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import static org.testng.Assert.assertEquals;

public class StartAppiumServer {
	private static WebDriver driver;
	public static final String ACCESS_KEY = "a05d4728-8242-4cdc-805f-d553e22d8720";
	public static final String USER_NAME = "Dave3818";
	public static final String URL = "https://"+USER_NAME+":"+ACCESS_KEY+"@ondemand.us-west-1.saucelabs.com/wd/hub";
	@BeforeTest
	public void setup() throws MalformedURLException {

		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability("platformName", "Android");
		capabilities.setCapability("deviceName", "Pixel_3a_API_30_x86");
		//capabilities.setCapability("udid", "639f8173");
		capabilities.setCapability("platformVersion", "11");
		//  capabilities.setCapability("app", "storage:filename=swag-labs.apk");
		capabilities.setCapability("browserName", "");
		// capabilities.setCapability("deviceOrientation", "portrait");
		//capabilities.setCapability("appiumVersion", "1.16.0");
		capabilities.setCapability("appPackage", "com.google.android.youtube");
		capabilities.setCapability("appActivity", "com.google.android.apps.youtube.app.WatchWhileActivity");
		AppiumServiceBuilder serviceBuilder = new AppiumServiceBuilder();
		serviceBuilder.usingPort(4723);
		//serviceBuilder.withAppiumJS(new File("C:\\Users\\dines\\AppData\\Roaming\\npm\\node_modules\\appium\\lib\\appium.js"));
		//serviceBuilder.usingDriverExecutable(new File("C:\\Program Files\\nodejs\\node.exe"));
		serviceBuilder.withIPAddress("127.0.0.1");
		//serviceBuilder.withLogFile(new File("C:\\codebase\\test-appium-android-sample\\appium_log.txt"));
		serviceBuilder.withArgument(GeneralServerFlag.SESSION_OVERRIDE);
		serviceBuilder.withArgument(GeneralServerFlag.LOG_LEVEL,"error");
		serviceBuilder.withCapabilities(capabilities);
		AppiumDriverLocalService.buildService(serviceBuilder).start();
        URL url = new URL("http://127.0.0.1:4723/wd/hub");
        driver = new AndroidDriver<WebElement>( url, capabilities);
	}
	
	@Test
	public void logintoYouTube() throws InterruptedException {
		Wait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("com.google.android.youtube:id/menu_item_0")));
		WebElement search = driver.findElement(By.id("com.google.android.youtube:id/menu_item_0"));
		search.click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("com.google.android.youtube:id/search_edit_text")));
		WebElement searchClick = driver.findElement(By.id("com.google.android.youtube:id/search_edit_text"));
		searchClick.click();
		searchClick.sendKeys("Billboard");
		((RemoteWebDriver) driver).executeScript("mobile: performEditorAction", ImmutableMap.of("action", "search"));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("android.view.ViewGroup")));
		//WebElement startPoint = driver.findElement(By.className("android.view.ViewGroup"));
		JavascriptExecutor js = (JavascriptExecutor) ((MobileDriver) driver);
		HashMap<String, String> scrollObject = new HashMap<String, String>();
		Boolean elementVisiable = false;
		
	
		while (!elementVisiable) {
			if (getItemWebView().size()==0)
				
			{
				ScrollDown();				
			
				/*scrollObject.put("direction", "down");
				js.executeScript("mobile: scroll", scrollObject);*/
				
			}
			else {
				
				driver.findElement(By.id("com.google.android.youtube:id/subscribe_button")).click();
				//driver.findElement(By.xpath("//android.view.ViewGroup[contains(text(),'Billboard Hot 100 Top Singles This Week')]")).click();
				elementVisiable = true;
			}
		}

	    //int startY = startPoint.getLocation().getY() + (startPoint.getSize().getHeight() / 2);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("com.google.android.youtube:id/image")));
		WebElement clickAccount = driver.findElement(By.id("com.google.android.youtube:id/image"));
		clickAccount.click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("com.google.android.youtube:id/button")));
		WebElement signIn = driver.findElement(By.id("com.google.android.youtube:id/button"));
		signIn.click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("com.google.android.youtube:id/name")));
		WebElement addAccount = driver.findElement(By.id("com.google.android.youtube:id/name"));
		addAccount.click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("android.view.View")));
		WebElement userName = driver.findElement(By.xpath("//android.view.View[@index=2]"));
		userName.click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//android.widget.EditText[@resource-id='identifierId']")));
		WebElement enterUserName = driver.findElement(By.xpath("//android.widget.EditText[@resource-id='identifierId']"));
		enterUserName.sendKeys("m.dineshrajarajan@gmail.com");
		WebElement nextUN = driver.findElement(By.xpath("//android.widget.Button[@text='Next']"));
		nextUN.click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//android.view.View[@resource-id='selectionc0']")));
		driver.findElement(By.xpath("//android.view.View[@resource-id='selectionc0']")).getText();
		assertEquals(driver.findElement(By.xpath("//android.view.View[@resource-id='selectionc0']")).getText(), "Show password");
         
	}
	
	@AfterTest
	public void tearDown() {
		driver.quit();
	}


	public List<WebElement> getItemWebView(){
		return ((AndroidDriver) driver).findElements(By.id("com.google.android.youtube:id/subscribe_button"));
		//return ((AndroidDriver) driver).findElements(By.xpath("//android.view.ViewGroup[contains(text(),'Billboard Hot 100 Top Singles This Week')]"));
	}
	
	public void ScrollDown() {
		Dimension dimension = driver.manage().window().getSize();
		Double scrollHeightValue = dimension.getHeight() * 0.5;
		int scrollHeightValueInt = scrollHeightValue.intValue();
		Double scrollHeightEndValue = dimension.getHeight() * 0.2;
		int scrollHeightEndValueInt = scrollHeightEndValue.intValue();
		new TouchAction((MobileDriver) driver).press(PointOption.point(0, scrollHeightValueInt)).waitAction(WaitOptions.waitOptions(Duration.ofMillis(500))).moveTo(PointOption.point(0, scrollHeightEndValueInt)).release().perform();
		
	}

}
		

