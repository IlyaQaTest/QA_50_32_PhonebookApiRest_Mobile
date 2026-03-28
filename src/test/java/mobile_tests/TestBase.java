package mobile_tests;

import io.appium.java_client.AppiumDriver;
import manager.AppiumConfig;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import screens.ContactListScreen;
import screens.LoginRegistrationScreen;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

public class TestBase {
    protected static AppiumDriver driver;
    protected WebDriverWait wait;
    protected LoginRegistrationScreen loginRegistrationScreen;
    protected ContactListScreen contactListScreen;

    @BeforeMethod
    public void setup() {
        driver= AppiumConfig.createAppiumDriver("pixel.propeties");
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        loginRegistrationScreen = new LoginRegistrationScreen(driver);
        contactListScreen = new ContactListScreen(driver);
    }
    @Test
    public void start(){}
    @AfterMethod(enabled = false)
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
    public String getAlertTextAndClose() {
        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
        String text = alert.getText();
        alert.accept();
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
