package pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.support.FindBy;

public class ContactInfoPage extends BasePage {

    public ContactInfoPage(AppiumDriver<MobileElement> driver) {
        super(driver);
    }


    @FindBy(xpath = "//*[@resource-id='com.sheygam.contactapp:id/nameTxt']")
    MobileElement name;
    @FindBy(xpath = "//*[@resource-id='com.sheygam.contactapp:id/lastNameTxt']")
    MobileElement lastName;
    @FindBy(xpath = "//*[@resource-id='com.sheygam.contactapp:id/emailTxt']")
    MobileElement email;
    @FindBy(xpath = "//*[@resource-id='com.sheygam.contactapp:id/phoneTxt']")
    MobileElement phone;
    @FindBy(xpath = "//*[@resource-id='com.sheygam.contactapp:id/addressTxt']")
    MobileElement address;
    @FindBy(xpath = "//*[@resource-id='com.sheygam.contactapp:id/descTxt']")
    MobileElement description;

    public boolean validateEmailUpdated(String omail) {

        return email.getText().equals(omail);
    }

    public boolean validatePhoneUpdated(String phene) {

        return phone.getText().equals(phene);
    }
}
