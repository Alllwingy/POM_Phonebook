package config;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import java.net.MalformedURLException;
import java.net.URL;

public class AppiumConfig {

    @BeforeSuite
    public void setUp() {

        DesiredCapabilities capabilities = new DesiredCapabilities();

        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("deviceName", "Nex6");
        capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "8.0");
        capabilities.setCapability("appPackage", "com.sheygam.contactapp");
        capabilities.setCapability("appActivity", ".SplashActivity");

//        capabilities.setCapability("deviceName", "samsung SM-S901E");
//        capabilities.setCapability("udid", "R5CT343DPLP");
//        capabilities.setCapability("platformVersion", "12.0");
//        capabilities.setCapability("platformName", "Android");
//        capabilities.setCapability("appActivity", ".SplashActivity");
//        capabilities.setCapability("automationName", "UiAutomator2");
//        capabilities.setCapability("appPackage", "com.sheygam.contactapp");

        capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "Appium");
        capabilities.setCapability(MobileCapabilityType.APP, "C://Tools/APK/contacts-android.apk");

        try {
            driver = new AppiumDriver<MobileElement>(new URL("http://localhost:4723/wd/hub"), capabilities);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

    public static AppiumDriver<MobileElement> driver;

    @AfterSuite
    public void tearDown() {

        driver.quit();
    }
}
