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
    MobileElement alertTextTitle;
    
    @FindBy(xpath = "//*[@resource-id='android:id/button1']")
    MobileElement alertButton;

    public ContactListPage clickRegistrationButton() {

        clickBase(buttonRegistration);
        return new ContactListPage(driver);
    }

    public ContactListPage clickLoginButton() {

        clickBase(buttonLogin);
        return new ContactListPage(driver);
    }

    public AuthenticationPage clickLoginButtonNegative() {

        clickBase(buttonLogin);
        return this;
    }

    public AuthenticationPage clickRegistrationButtonNegative() {

        clickBase(buttonRegistration);
        return this;
    }

    public AuthenticationPage clickAlertButton() {

        clickBase(alertButton);
        return this;
    }

    public AuthenticationPage fillEmail(String email) {

        typeTextBase(inputEmail, email);
        return this; // this - object of this class
    }

    public AuthenticationPage fillPassword(String password) {

        typeTextBase(inputPassword, password);
        return this;
    }

    public ContactListPage login(UserDTO user) {

        return fillEmail(user.getEmail()).fillPassword(user.getPassword()).clickLoginButton();
    }

    public ContactListPage register(UserDTO user) {

        return fillEmail(user.getEmail()).fillPassword(user.getPassword()).clickRegistrationButton();
    }

    public boolean validateErrorTitleAlertCorrect() {

        return isTextEqual(alertTextTitle, "Error");
    }
}
