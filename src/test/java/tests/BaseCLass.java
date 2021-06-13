package tests;

import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import io.appium.java_client.remote.MobileCapabilityType;

public class BaseCLass {

	@BeforeTest
	public void setUp() {
		
		DesiredCapabilities dcaps = new DesiredCapabilities();
		dcaps.setCapability(MobileCapabilityType.PLATFORM_NAME, "ANDROID");
		dcaps.setCapability(MobileCapabilityType.PLATFORM_VERSION, "");
		dcaps.setCapability(MobileCapabilityType.DEVICE_NAME, "");
		dcaps.setCapability(MobileCapabilityType.UDID, "");
		dcaps.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT,60);
		dcaps.setCapability(MobileCapabilityType.BROWSER_NAME,"Chrome");
		
	}
	
	@AfterTest
	public void tearDown() {
		
	}
	
	
}
