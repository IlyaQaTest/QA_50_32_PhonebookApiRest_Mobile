package mobile_tests;

import dto.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import screens.ContactListScreen;
import screens.LoginRegistrationScreen;
import static utils.PropertiesReader.getProperty;

public class LogoutTests extends TestBase {
    private static final Logger logger = LoggerFactory.getLogger(LogoutTests.class);
    @BeforeMethod
    public void openAuthScreen() {
        loginRegistrationScreen = new LoginRegistrationScreen(driver);
    }

    @Test
    public void loginLoggerPositiveTest() {
        logger.info("---Start test---: loginPositiveTest");
        User user = new User(
                getProperty("base.properties", "login"),
                getProperty("base.properties", "password")
        );
        loginRegistrationScreen.typeLoginRegistrationForm(user);
        loginRegistrationScreen.clickBtnLogin();
        logger.info("Step: Testing the transition to the contact list screen");
        boolean isLoaded = new ContactListScreen(driver).isContactListDisplayed();
        if (isLoaded) {
            logger.info("RESULT: Authorization successful");
        } else {
            logger.error("RESULT: Contact list screen is NOT displayed!");
        }
        Assert.assertTrue(isLoaded, "Contact List screen should be displayed after login");
        ContactListScreen contactListScreen = new ContactListScreen(driver);
        contactListScreen.clickMoreOptions();
        contactListScreen.clickBtnLogout();
        Assert.assertTrue(loginRegistrationScreen.isTextAuthenticationDisplayed(),
                "Authentication Displayed is not displayed after login");
        logger.info("--- END OF TEST ---");
    }

}