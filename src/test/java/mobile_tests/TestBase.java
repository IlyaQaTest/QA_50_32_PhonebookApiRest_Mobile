package mobile_tests;

import io.appium.java_client.AppiumDriver;
import manager.AppiumConfig;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import screens.ContactListScreen;
import screens.LoginRegistrationScreen;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

public class TestBase {
    // Правильная инициализация логгера для текущего класса
    protected static final Logger logger = LoggerFactory.getLogger(TestBase.class);

    protected static AppiumDriver driver;
    protected WebDriverWait wait;
    protected LoginRegistrationScreen loginRegistrationScreen;
    protected ContactListScreen contactListScreen;

    @BeforeMethod
    public void setup() {
        logger.info("Starting Appium driver...");
        driver = AppiumConfig.createAppiumDriver("pixel.properties");

        logger.info("The driver has been created. Wait 5 seconds for it to stabilize SplashActivity...");
        try { Thread.sleep(2000); } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        loginRegistrationScreen = new LoginRegistrationScreen(driver);
        contactListScreen = new ContactListScreen(driver);
    }

    @AfterMethod(enabled = false) // Включаем, чтобы не копились «мертвые» сессии
    public void tearDown() {
        if (driver != null) {
            logger.info("Ending the session and closing the driver.");
            driver.quit();
        }
    }

    public String getAlertTextAndClose() {
        logger.debug("Waiting for a system message to appear (Alert)...");
        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
        String text = alert.getText();
        alert.accept();
        logger.info("Alert closed. Text: {}", text);
        return text;
    }

    public void takeScreenshot(String name) {
        File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(scrFile, new File("screenshots/" + name + ".png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}