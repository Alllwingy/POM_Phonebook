package pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {

    AppiumDriver<MobileElement> driver;

    public BasePage(AppiumDriver<MobileElement> driver) {

        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    public void click(MobileElement element) {

        element.click();
    }

    public void click(By by) {

        findMobileElementBy(by).click();
    }

    public ContactListPage clickBack() {

        driver.navigate().back();
        return new ContactListPage(driver);
    }

    public MobileElement findMobileElementBy(By by) {

        return driver.findElement(by);
    }

    public By findElementByStringFormat(String format, String item) {

        return By.xpath(String.format(format, item));
    }

    public void wait(By by, int seconds) {

        new WebDriverWait(driver, seconds).until(ExpectedConditions.presenceOfElementLocated(by)).isDisplayed();
    }

    public void typeText(MobileElement element, String text) {

        element.click();
        element.clear();
        element.sendKeys(text);
        driver.hideKeyboard();
    }

    public String getText(MobileElement element) {

        return element.getText().toUpperCase().trim();
    }

    public boolean isTextEqual(MobileElement element, String text) {

        if (getText(element).equals(text.toUpperCase())) {
            return true;
        } else {
            System.out.println("actual result: " + getText(element) + " expected result: " + text.toUpperCase());
            return false;
        }
    }

    public void pause(long seconds) {

        try {
            Thread.sleep(seconds * 1000L);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
