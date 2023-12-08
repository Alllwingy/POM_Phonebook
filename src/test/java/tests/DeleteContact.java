package tests;

import com.github.javafaker.Faker;
import config.AppiumConfig;
import dto.ContactDTO;
import dto.UserDTO;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.ContactListPage;
import pages.SplashPage;

public class DeleteContact extends AppiumConfig {

    Faker faker = new Faker();

    @BeforeClass
    public void beforeClass() {

        new SplashPage(driver).goToAuthenticationPage()
                .login(UserDTO.builder()
                        .email("document@gmail.com")
                        .password("Task$12345")
                        .build());
    }

    @AfterClass
    public void afterClass() {

        new ContactListPage(driver).logout();
    }

    @Test
    public void deleteOneContactPositive() {

        String phone = faker.number().digits(10);

        Assert.assertFalse(new ContactListPage(driver).add()
                .addNewContactPositive(ContactDTO.builder()
                        .name(faker.name().firstName())
                        .lastName(faker.name().lastName())
                        .email(faker.internet().emailAddress())
                        .phone(phone)
                        .address(faker.address().fullAddress())
                        .description(faker.demographic().race())
                        .build())
                .removeSingle(phone)
                .isPhonePresent(phone));
    }

    @Test
    public void deleteAllContacts() {

        String phone = faker.number().digits(10);
//        String phone = new Random().nextInt(1000) + 1000;

        Assert.assertTrue(new ContactListPage(driver).add()
                .addNewContactPositive(ContactDTO.builder()
                        .name(faker.name().firstName())
                        .lastName(faker.name().lastName())
                        .email(faker.internet().emailAddress())
                        .phone(phone)
                        .address(faker.address().fullAddress())
                        .description(faker.demographic().race())
                        .build())
                .removeAll()
                .validateContactListEmpty());
    }
}
