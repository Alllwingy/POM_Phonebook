package pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindAll;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AndroidFindByChainSet;
import io.appium.java_client.pagefactory.AndroidFindBySet;
import org.openqa.selenium.By;
import org.openqa.selenium.Point;
import org.openqa.selenium.Rectangle;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.management.openmbean.ArrayType;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

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

    @FindBy(xpath = "//*[@class='android.widget.ImageButton']")
    MobileElement buttonAdd;

    @FindBy(xpath = "//*[@resource-id='com.sheygam.contactapp:id/rowPhone']")
    List<MobileElement> phones;

    public AuthenticationPage clickLogout() {

        clickBase(dots);
        clickBase(buttonLogout);
        return new AuthenticationPage(driver);
    }

    public AddNewContactPage clickButtonAddNewContact() {

        pause(2);
        clickBase(buttonAdd);
        return new AddNewContactPage(driver);
    }

    public boolean validateContactListOpened() {

        return isTextEqual(textTitle, "Contact list");
    }

    public boolean validateCurrentContactCreated(String phone) {
        pause(5);
        Actions actions = new Actions(driver);
        String last = phones.get(phones.size() - 1).getText();

        while (!phones.isEmpty()) {

            for (MobileElement p : phones)
                if (p.getText().equals(phone))
                    return true;

            actions.moveToElement(phones.get(phones.size() - 1)).clickAndHold().moveByOffset(0, -(phones.get(phones.size() - 1).getRect().getY() - phones.get(0).getRect().getY())).release().perform();

            if (phones.get(phones.size() - 1).getText().equals(last))
                return false;

            last = phones.get(phones.size() - 1).getText();
        }

        return false;
    }

//    public boolean validateCurrentContactCreated(String phone) {
//        pause(5);
//        Actions actions = new Actions(driver);
//        MobileElement first, last;
//        int index = 1;
//
//        while (true) {
//
//            first = driver.findElement(By.xpath("//*[@resource-id='com.sheygam.contactapp:id/rowPhone']"));
//
//            while (true) {
//
//                index++;
//
//                try {
//                    if (driver.findElement(By.xpath("//android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout[2]/android.view.ViewGroup/androidx.recyclerview.widget.RecyclerView/android.widget.LinearLayout" + "[" + index + "]" + "/android.widget.TextView[2]")).getText().equals(phone))
//                        return true;
//
//                } catch (Exception e) {
//                    break;
//                }
//            }
//
//            if (index == 0)
//                index++;
//
//            System.out.println(index);
//            last = driver.findElement(By.xpath("//android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout[2]/android.view.ViewGroup/androidx.recyclerview.widget.RecyclerView/android.widget.LinearLayout" + "[" + (index - 1) + "]" + "/android.widget.TextView[2]"));
//            index = 1;
//
//            actions.moveToElement(last).clickAndHold().moveByOffset(0, -(last.getRect().getY() - first.getRect().getY())).release().perform();
//        }
//    }
}
