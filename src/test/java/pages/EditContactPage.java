package pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.support.FindBy;

public class EditContactPage extends BasePage {

    public EditContactPage(AppiumDriver<MobileElement> driver) {
        super(driver);
    }

    @FindBy(xpath = "//*[@resource-id='com.sheygam.contactapp:id/inputName']")
    MobileElement inputName;
    @FindBy(xpath = "//*[@resource-id='com.sheygam.contactapp:id/inputLastName']")
    MobileElement inputLastName;
    @FindBy(xpath = "//*[@resource-id='com.sheygam.contactapp:id/inputEmail']")
    MobileElement inputEmail;
    @FindBy(xpath = "//*[@resource-id='com.sheygam.contactapp:id/inputPhone']")
    MobileElement inputPhone;
    @FindBy(xpath = "//*[@resource-id='com.sheygam.contactapp:id/inputAddress']")
    MobileElement inputAddress;
    @FindBy(xpath = "//*[@resource-id='com.sheygam.contactapp:id/inputDesc']")
    MobileElement inputDescription;
    @FindBy(xpath = "//*[@resource-id='com.sheygam.contactapp:id/updateBtn']")
    MobileElement update;

    public EditContactPage sendName(String name) {

        typeText(inputName, name);
        return this;
    }

    public EditContactPage sendLastName(String lastName) {

        typeText(inputLastName, lastName);
        return this;
    }

    public EditContactPage sendEmail(String email) {

        typeText(inputEmail, email);
        return this;
    }

    public EditContactPage sendPhone(String phone) {

        typeText(inputPhone, phone);
        return this;
    }

    public EditContactPage sendAddress(String address) {

        typeText(inputAddress, address);
        return this;
    }

    public EditContactPage sendDescription(String description) {

        typeText(inputDescription, description);
        return this;
    }

    public ContactListPage update() {

        click(update);
        return new ContactListPage(driver);
    }

    public ContactListPage updateContactEmail(String email) {

        return sendEmail(email).update();
    }

    public ContactListPage updateContactPhone(String phone) {

        return sendPhone(phone).update();
    }
}
