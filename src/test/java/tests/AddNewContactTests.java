package tests;

import com.github.javafaker.Faker;
import config.AppiumConfig;
import dto.ContactDTO;
import dto.UserDTO;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.AddNewContactPage;
import pages.ContactListPage;
import pages.SplashPage;

public class AddNewContactTests extends AppiumConfig {

    Faker faker = new Faker();
    boolean flagAlert = false;

    @BeforeClass
    public void beforeClass() {

        new SplashPage(driver).goToAuthenticationPage()
                .login(UserDTO.builder()
                        .email("document@gmail.com")
                        .password("Task$12345")
                        .build());
    }

    @AfterMethod
    public void afterMethod() {

        if (flagAlert) {
            flagAlert = false;
            new AddNewContactPage(driver).acceptAlert().clickBackButton();
        }
    }

//    @AfterClass
//    public void afterClass() {
//
//        new ContactListPage(driver).clickLogout();
//    }

    @Test
    public void positiveAddNewContact() {

        String phone = faker.number().digits(10);
//        String phone = new Random().nextInt(1000) + 1000;

        Assert.assertTrue(new ContactListPage(driver).clickButtonAddNewContact()
                .addNewContact(ContactDTO.builder()
                        .name(faker.name().firstName())
                        .lastName(faker.name().lastName())
                        .email(faker.internet().emailAddress())
                        .phone(phone)
                        .address(faker.address().fullAddress())
                        .description(faker.demographic().race())
                        .build())
                .validateCurrentContactCreated(phone));
    }

    @Test
    public void negativeTestEmptyPhone() {

        flagAlert = true;

        Assert.assertTrue(new ContactListPage(driver).clickButtonAddNewContact()
                .addNewContactNegative(ContactDTO.builder()
                        .name(faker.name().firstName())
                        .lastName(faker.name().lastName())
                        .email(faker.internet().emailAddress())
                        .phone("")
                        .address(faker.address().fullAddress())
                        .description(faker.demographic().race())
                        .build())
                .validateErrorMessage());
    }
}
