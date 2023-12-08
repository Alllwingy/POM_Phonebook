package tests;

import com.github.javafaker.Faker;
import config.AppiumConfig;
import dto.UserDTO;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import pages.AuthenticationPage;
import pages.ContactListPage;
import pages.SplashPage;

public class SignUpTests extends AppiumConfig {

    Faker faker = new Faker();
    boolean flagAlert = false, flagLogout = false;

    @AfterMethod
    public void afterMethod() {

        if (flagAlert) {
            flagAlert = false;
            new AuthenticationPage(driver).acceptAlert();
        }
        if (flagLogout) {
            flagLogout = false;
            new ContactListPage(driver).logout();
        }
    }

    @Test
    public void positiveRegistration() {

        Assert.assertTrue(new SplashPage(driver).goToAuthenticationPage()
                .register(UserDTO.builder()
                        .email(faker.internet().emailAddress())
                        .password(faker.internet().password() + "A$")
                        .build())
                .validateContactListPageOpened());

        flagLogout = true;
    }

    @Test
    public void negativeRegistration_EmptyEmail() {

        Assert.assertTrue(new SplashPage(driver).goToAuthenticationPage()
                .fillPassword(faker.internet().password() + "A$")
                .clickRegistrationNegative()
                .validateAlertTitle());

        flagAlert = true;
    }
}
