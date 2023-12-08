package pages;

import dto.UserDTO;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.support.FindBy;

public class AuthenticationPage extends BasePage {

    public AuthenticationPage(AppiumDriver<MobileElement> driver) {
        super(driver);
    }

    @FindBy(xpath = "//*[@resource-id='com.sheygam.contactapp:id/inputEmail']")
    MobileElement inputEmail;
    @FindBy(xpath = "//*[@resource-id='com.sheygam.contactapp:id/inputPassword']")
    MobileElement inputPassword;
    @FindBy(xpath = "//*[@resource-id='com.sheygam.contactapp:id/regBtn']")
    MobileElement buttonRegistration;
    @FindBy(xpath = "//*[@resource-id='com.sheygam.contactapp:id/loginBtn']")
    MobileElement buttonLogin;
    @FindBy(xpath = "//*[@resource-id='android:id/alertTitle']")
    MobileElement alertTitle;
    @FindBy(xpath = "//*[@resource-id='android:id/button1']")
    MobileElement alertAccept;

    public ContactListPage clickRegistrationPositive() {

        click(buttonRegistration);
        return new ContactListPage(driver);
    }

    public AuthenticationPage clickRegistrationNegative() {

        click(buttonRegistration);
        return this;
    }

    public ContactListPage clickLoginPositive() {

        click(buttonLogin);
        return new ContactListPage(driver);
    }

    public AuthenticationPage clickLoginNegative() {

        click(buttonLogin);
        return this;
    }

    public void acceptAlert() {

        click(alertAccept);
    }

    public AuthenticationPage fillEmail(String email) {

        typeText(inputEmail, email);
        return this; // this - object of this class
    }

    public AuthenticationPage fillPassword(String password) {

        typeText(inputPassword, password);
        return this;
    }

    public ContactListPage register(UserDTO user) {

        return fillEmail(user.getEmail()).fillPassword(user.getPassword()).clickRegistrationPositive();
    }

    public ContactListPage login(UserDTO user) {

        return fillEmail(user.getEmail()).fillPassword(user.getPassword()).clickLoginPositive();
    }

    public boolean validateAlertTitle() {

        return isTextEqual(alertTitle, "Error");
    }
}