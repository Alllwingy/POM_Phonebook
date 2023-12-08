package tests;

import config.AppiumConfig;
import dto.UserDTO;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import pages.AuthenticationPage;
import pages.ContactListPage;
import pages.SplashPage;

public class LoginTests extends AppiumConfig {

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
    public void positiveLogin() {

        Assert.assertTrue(new SplashPage(driver).goToAuthenticationPage()
                .login(UserDTO.builder()
                        .email("document@gmail.com")
                        .password("Task$12345")
                        .build())
                .validateContactListPageOpened());

        flagLogout = true;
    }

    @Test
    public void negativeLogin_EmptyEmail() {

        Assert.assertTrue(new SplashPage(driver).goToAuthenticationPage()
                .fillPassword("Task$12345")
                .clickLoginNegative()
                .validateAlertTitle());

        flagAlert = true;
    }
}
