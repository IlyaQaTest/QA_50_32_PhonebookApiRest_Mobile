package screens;

import dto.User;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.time.Duration;

public class LoginRegistrationScreen extends BaseScreen{
    private static final Logger logger = LoggerFactory.getLogger(LoginRegistrationScreen.class);

    @AndroidFindBy(id = "com.sheygam.contactapp:id/inputEmail")
    WebElement inputEmail;
    @AndroidFindBy(id = "com.sheygam.contactapp:id/inputPassword")
    WebElement inputPassword;
    @AndroidFindBy(id = "com.sheygam.contactapp:id/regBtn")
    WebElement btnRegistration;
    @AndroidFindBy(id = "com.sheygam.contactapp:id/loginBtn")
    WebElement btnLogin;
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Authentication']")
    WebElement auth;

    public boolean isTextAuthenticationDisplayed() {
        try {
            // Ждем короткое время (например, 2 секунды), чтобы проверить наличие элемента
            return auth.isDisplayed();
        } catch (Exception e) {
            // Если элемента нет или случился таймаут — мы ушли с этого экрана
            return false;
        }
    }

    public LoginRegistrationScreen(AppiumDriver driver) {
        super(driver);
    }

    public void typeLoginRegistrationForm(User user){
        logger.info("I begin entering user data: {}", user.getUsername());

        try {
            // Ожидание конкретно этого поля
            WebDriverWait localWait = new WebDriverWait(driver, Duration.ofSeconds(10));

            logger.info("Waiting for the Email field to appear...");
            localWait.until(ExpectedConditions.visibilityOf(inputEmail));

            inputEmail.click();
            inputEmail.clear();
            inputEmail.sendKeys(user.getUsername());
            logger.info("Email entered successfully");

            logger.info("Entering a password...");
            inputPassword.sendKeys(user.getPassword());
            logger.info("Password entered successfully");

        } catch (Exception e) {
            logger.error("ERROR while entering data: " + e.getMessage());
            throw e;
        }
    }

    public void clickBtnRegistration(){
        logger.info("Step: Pressing the button 'Login'");
        btnRegistration.click();
    }

    public boolean isLoginRegistrationFormDisplayed() {
        try {
            // Ждем короткое время (например, 2 секунды), чтобы проверить наличие элемента
            return inputEmail.isDisplayed();
        } catch (Exception e) {
            // Если элемента нет или случился таймаут — мы ушли с этого экрана
            return false;
        }
    }

    public void clickBtnLogin(){
        btnLogin.click();
    }
}