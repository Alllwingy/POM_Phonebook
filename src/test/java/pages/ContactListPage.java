package pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.By;
import org.openqa.selenium.Rectangle;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class ContactListPage extends BasePage {

    public ContactListPage(AppiumDriver<MobileElement> driver) {
        super(driver);
    }

    @FindBy(xpath = "//android.widget.TextView[@text='Contact list']")
    MobileElement title;
    @FindBy(xpath = "//android.widget.ImageView[@content-desc='More options']")
    MobileElement dots;
    By add = By.xpath("//*[@resource-id='com.sheygam.contactapp:id/add_contact_btn']");
    @FindBy(xpath = "//*[@resource-id='com.sheygam.contactapp:id/title']")
    MobileElement logout;
    @FindBy(xpath = "//*[@resource-id='android:id/button1']")
    MobileElement remove;
    @FindBy(xpath = "//*[@resource-id='com.sheygam.contactapp:id/rowPhone']")
    List<MobileElement> allContacts;
    By contact = By.xpath("//*[@resource-id='com.sheygam.contactapp:id/rowPhone']");
    String phoneable = "//*[@text='%s']";

    public AddNewContactPage add() {

        wait(add, 5);
        click(add);
        return new AddNewContactPage(driver);
    }

    public void logout() {

        click(dots);
        click(logout);
    }

    public ContactListPage remove() {

        click(remove);
        return this;
    }

    public boolean isPhonePresent(String phone) {

        try {
            wait(contact, 2);
        } catch (Exception e) {
            return false;
        }

        String last = allContacts.get(allContacts.size() - 1).getText();

        while (!allContacts.isEmpty()) {

            if (search(phone)) return true;
            else scroll();

            if (allContacts.get(allContacts.size() - 1).getText().equals(last)) return false;
            last = allContacts.get(allContacts.size() - 1).getText();
        }

        return false;
    }

    private boolean search(String phone) {

        for (MobileElement p : allContacts)
            if (p.getText().equals(phone)) return true;

        return false;
    }

    public ContactListPage scroll() {

        try {
            wait(contact, 2);
        } catch (Exception e) {
            return this;
        }

        Rectangle d1t = allContacts.get(allContacts.size() - 1).getRect(), d2t = allContacts.get(0).getRect();

        ((TouchAction<?>) new TouchAction<>(driver))
                .longPress(PointOption.point(d1t.getX() + d1t.getWidth() / 2, d1t.getY() + d1t.getHeight() / 2))
                .moveTo(PointOption.point(d2t.getX() + d2t.getWidth() / 2, d2t.getY() + d2t.getHeight() / 2))
                .release()
                .perform();

        return this;
    }

    public ContactListPage removeSingle(String phone) {

        try {
            wait(contact, 2);
        } catch (Exception e) {
            return this;
        }

        String last = allContacts.get(allContacts.size() - 1).getText();

        while (!allContacts.isEmpty()) {

            if (search(phone)) gestureRemove(phone);
            else scroll();

            if (allContacts.get(allContacts.size() - 1).getText().equals(last)) break;
            last = allContacts.get(allContacts.size() - 1).getText();
        }

        return this;
    }

    public EditContactPage edit(String phone) {

        try {
            wait(contact, 2);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        while (!allContacts.isEmpty()) {

            if (search(phone)) gestureEdit(phone);
            else scroll();
        }

        return new EditContactPage(driver);
    }

    public ContactInfoPage tap(String phone) {

        try {
            wait(contact, 2);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        while (!allContacts.isEmpty()) {

            if (search(phone)) gestureTap(phone);
            else scroll();
        }

        return new ContactInfoPage(driver);
    }

    private void gestureEdit(String phone) {

        Rectangle dot = findMobileElementBy(findElementByStringFormat(phoneable, phone)).getRect();

        ((TouchAction<?>) new TouchAction<>(driver))
                .longPress(PointOption.point(dot.getX() + dot.getWidth() / 5 * 4, dot.getY() + dot.getHeight() / 2))
                .moveTo(PointOption.point(dot.getX() + dot.getWidth() / 5, dot.getY() + dot.getHeight() / 2))
                .release()
                .perform();
    }

    private void gestureTap(String phone) {

        Rectangle dot = findMobileElementBy(findElementByStringFormat(phoneable, phone)).getRect();

        ((TouchAction<?>) new TouchAction<>(driver))
                .tap(PointOption.point(dot.getX() + dot.getWidth() / 2, dot.getY() + dot.getHeight() / 2))
                .perform();
    }

    private void gestureRemove(String phone) {

        Rectangle dot = findMobileElementBy(findElementByStringFormat(phoneable, phone)).getRect();

        ((TouchAction<?>) new TouchAction<>(driver))
                .longPress(PointOption.point(dot.getX() + dot.getWidth() / 5, dot.getY() + dot.getHeight() / 2))
                .moveTo(PointOption.point(dot.getX() + dot.getWidth() / 5 * 4, dot.getY() + dot.getHeight() / 2))
                .release()
                .perform();
        remove();
    }

    public ContactListPage removeAll() {

        try {
            wait(contact, 2);
        } catch (Exception e) {
            return this;
        }

        while (!allContacts.isEmpty()) removeSingle(getText(findMobileElementBy(contact)));

        return this;
    }

    public boolean validateContactListEmpty() {

        return allContacts.isEmpty();
    }

    public boolean validateContactListPageOpened() {

        return isTextEqual(title, "Contact list");
    }

    public boolean validateCurrentContactCreated(String phone) {

        if (phone.isEmpty())
            return false;

        try {
            wait(contact, 5);
        } catch (Exception e) {
            return false;
        }

        MobileElement first;
        Rectangle dot;

        while (true) {

            first = findMobileElementBy(By.xpath("//*[@resource-id='com.sheygam.contactapp:id/rowPhone']"));

            try {

                if (findMobileElementBy(findElementByStringFormat(phoneable, phone)).getText().equals(phone)) {
                    return true;
                }

            } catch (Exception e) {

                dot = first.getRect();
                ((TouchAction<?>) new TouchAction<>(driver))
                        .longPress(PointOption.point(dot.getX() + dot.getWidth() / 2, dot.getY() + driver.manage().window().getSize().height / 10 * 8))
                        .moveTo(PointOption.point(dot.getX() + dot.getWidth() / 2, dot.getY() + dot.getHeight() / 2))
                        .release()
                        .perform();
            } finally {

                if (findMobileElementBy(By.xpath("//*[@resource-id='com.sheygam.contactapp:id/rowPhone']")).getText().equals(first.getText())) {
                    if (findMobileElementBy(findElementByStringFormat(phoneable, phone)).getText().equals(phone)) {
                        return true;
                    }
                    return false;
                }
            }
        }
    }
}