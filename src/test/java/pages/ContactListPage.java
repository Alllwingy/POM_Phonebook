package pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.support.FindBy;

public class ContactListPage extends BasePage {

    public ContactListPage(AppiumDriver<MobileElement> driver) {
        super(driver);
    }

    @FindBy(xpath = "//*[@text='Contact list']")
    MobileElement textTitle;

    @FindBy(xpath = "//android.widget.ImageView[@content-desc='More options']")
    MobileElement dots;

    @FindBy(xpath = "//*[@resource-id='com.sheygam.contactapp:id/title']")
    MobileElement buttonLogout;

    public AuthenticationPage clickLogout() {

        clickBase(dots);
        clickBase(buttonLogout);
        return new AuthenticationPage(driver);
    }

    public boolean validateContactListOpened() {

        return isTextEqual(textTitle, "Contact list");
    }
}
