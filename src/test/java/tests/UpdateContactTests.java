package tests;

import com.github.javafaker.Faker;
import config.AppiumConfig;
import dto.ContactDTO;
import dto.UserDTO;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.ContactListPage;
import pages.EditContactPage;
import pages.SplashPage;

import java.util.Random;

public class UpdateContactTests extends AppiumConfig {

    Faker faker = new Faker();
    boolean flagView = false;

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

        if (flagView) {
            flagView = false;
            new EditContactPage(driver).clickBack();
        }
    }

    @AfterClass
    public void afterClass() {

        new ContactListPage(driver).logout();
    }

    @Test
    public void updateEmail() {

        flagView = true;

        String phone = faker.number().digits(10);
        String omail = String.format("email%s@mail.com", new Random().nextInt(1000) + 1000);

        ContactDTO contact = ContactDTO.builder()
                .name(faker.name().firstName())
                .lastName(faker.name().lastName())
                .email(omail)
                .phone(phone)
                .address(faker.address().fullAddress())
                .description(faker.demographic().race())
                .build();

        String email = faker.internet().emailAddress();

        new ContactListPage(driver).add()
                .addNewContactPositive(contact)
                .edit(phone)
                .updateContactEmail(email)
                .tap(phone)
                .validateEmailUpdated(email);
    }

    @Test
    public void updatePhone() {

        flagView = true;

        String phene = "053826" + new Random().nextInt(10000) + 10000;

        ContactDTO contact = ContactDTO.builder()
                .name(faker.name().firstName())
                .lastName(faker.name().lastName())
                .email(faker.internet().emailAddress())
                .phone(phene)
                .address(faker.address().fullAddress())
                .description(faker.demographic().race())
                .build();

        String phone = String.valueOf(faker.number().numberBetween(1000000000, 9999999999L));

        new ContactListPage(driver).add()
                .addNewContactPositive(contact)
                .edit(phene)
                .updateContactPhone(phone)
                .tap(phone)
                .validatePhoneUpdated(phone);
    }
}
