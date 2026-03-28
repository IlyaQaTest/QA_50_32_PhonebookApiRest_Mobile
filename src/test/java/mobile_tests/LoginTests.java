package mobile_tests;

import dto.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import screens.ContactListScreen;
import screens.ErrorScreen;
import screens.LoginRegistrationScreen;
import screens.SplashScreen;

import static utils.PropertiesReader.*;

public class LoginTests extends TestBase {
    private static final Logger logger = LoggerFactory.getLogger(RegistrationTests.class);
    @BeforeMethod
    public void openAuthScreen() {
        loginRegistrationScreen = new LoginRegistrationScreen(driver);
    }
    @Test
    public void loginPositiveBtnPlusTest(){
        User user  =  new User(getProperty("base.properties",
                "login"), getProperty("base.properties",
                "password"));
        loginRegistrationScreen.typeLoginRegistrationForm(user);
        loginRegistrationScreen.clickBtnLogin();
        Assert.assertTrue(new ContactListScreen(driver).isBtnPlusPresent());
    }

    @Test
    public void loginPositiveTest(){
        User user = new User(getProperty("base.properties","login"),
                getProperty("base.properties","password"));
        loginRegistrationScreen.typeLoginRegistrationForm(user);
        loginRegistrationScreen.clickBtnLogin();
        Assert.assertTrue(contactListScreen.isContactListDisplayed(),
                "Error: Contact List screen did not display after login!");
    }
    @Test
    public void loginSuccessTest() {
        User user = new User(getProperty("base.properties","login"),
                getProperty("base.properties","password"));
        Assert.assertTrue(contactListScreen.isContactListDisplayed(),
                "Ошибка: Экран Contact List не отобразился после логина!");

        logger.info("Starting login test for user: {}", user.getUsername());

        loginRegistrationScreen.typeLoginRegistrationForm(user);
        loginRegistrationScreen.clickBtnLogin();
        Assert.assertTrue(contactListScreen.isContactListDisplayed(),
                "Ошибка: Экран Contact List не отобразился после логина!");

        logger.info("Login successful, Contact List is displayed.");

    }

    @Test
    public void loginNegativeInvalidPasswordEmptyTest() {
        User user = new User(getProperty("base.properties", "login"),
                "");

        logger.debug("Testing empty password for user: {}", user.getUsername());
        loginRegistrationScreen.typeLoginRegistrationForm(user);
        loginRegistrationScreen.clickBtnLogin();

        String alertText = getAlertTextAndClose();
        logger.info("REAL ALERT TEXT: " + alertText);
        Assert.assertTrue(alertText.contains("Login or Password incorrect"));
    }
    @Test
    public void loginNegativeInvalidPasswordCleanCodeEmptyTest() {
        User user = new User(getProperty("base.properties", "login"), "");

        logger.debug("Testing login with empty password for: {}", user.getUsername());
        loginRegistrationScreen.typeLoginRegistrationForm(user);
        loginRegistrationScreen.clickBtnLogin();

        String alertText = getAlertTextAndClose();
        logger.info("Alert text received: {}", alertText);

        Assert.assertTrue(alertText.contains("Login or Password incorrect"),
                "The error text didn't match. We received: " + alertText);
    }
    @Test
    public void loginNegativeTest(){
        User user  =  new User(
                "", getProperty("base.properties",
                "password"));
        loginRegistrationScreen.typeLoginRegistrationForm(user);
        loginRegistrationScreen.clickBtnLogin();
        Assert.assertTrue(new ErrorScreen(driver)
                .validateTextInError("Login or Password incorrect", 5));
    }

    @Test
    public void loginNegative_EmptyFields_Test(){
        loginRegistrationScreen.clickBtnLogin();
        Assert.assertTrue(new ErrorScreen(driver)
                .validateTextInError("Login or Password incorrect", 5));
    }

    @Test
    public void loginNegative_WrongEmail_WithSpace_Test(){
        User user  =  new User(
                " ", getProperty("base.properties",
                "password"));
        loginRegistrationScreen.typeLoginRegistrationForm(user);
        loginRegistrationScreen.clickBtnLogin();
        Assert.assertTrue(new ErrorScreen(driver)
                .validateTextInError("Login or Password incorrect", 5));
    }
}

