package pages;

import dto.ContactDTO;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.support.FindBy;

public class AddNewContactPage extends BasePage {

    public AddNewContactPage(AppiumDriver<MobileElement> driver) {
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
    @FindBy(xpath = "//*[@resource-id='com.sheygam.contactapp:id/createBtn']")
    MobileElement buttonCreate;
    @FindBy(xpath = "//*[@resource-id='android:id/alertTitle']")
    MobileElement alertTitle;
    @FindBy(xpath = "//*[@resource-id='android:id/button1']")
    MobileElement acceptAlert;

    public AddNewContactPage sendName(String name) {

        typeTextBase(inputName, name);
        return this;
    }

    public AddNewContactPage sendLastName(String lastName) {

        typeTextBase(inputLastName, lastName);
        return this;
    }

    public AddNewContactPage sendEmail(String email) {

        typeTextBase(inputEmail, email);
        return this;
    }

    public AddNewContactPage sendPhone(String phone) {

        typeTextBase(inputPhone, phone);
        return this;
    }

    public AddNewContactPage sendAddress(String address) {

        typeTextBase(inputAddress, address);
        return this;
    }

    public AddNewContactPage sendDescription(String description) {

        typeTextBase(inputDescription, description);
        return this;
    }

    public ContactListPage clickCreate() {

        clickBase(buttonCreate);
        return new ContactListPage(driver);
    }

    public AddNewContactPage clickCreateNegative() {

        clickBase(buttonCreate);
        return this;
    }

    public ContactListPage addNewContact(ContactDTO contact) {

        return sendName(contact.getName())
                .sendLastName(contact.getLastName()) // due to "this"
                .sendEmail(contact.getEmail())
                .sendPhone(contact.getPhone())
                .sendAddress(contact.getAddress())
                .sendDescription(contact.getDescription())
                .clickCreate();
    }

    public AddNewContactPage addNewContactNegative(ContactDTO contact) {

        return sendName(contact.getName())
                .sendLastName(contact.getLastName()) // due to "this"
                .sendEmail(contact.getEmail())
                .sendPhone(contact.getPhone())
                .sendAddress(contact.getAddress())
                .sendDescription(contact.getDescription())
                .clickCreateNegative();
    }

    public boolean validateErrorMessage() {

        return isTextEqual(alertTitle, "Error");
    }

    public AddNewContactPage acceptAlert() {

        clickBase(acceptAlert);
        return this;
    }
}
