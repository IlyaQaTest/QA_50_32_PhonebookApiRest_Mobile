package manager;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.options.UiAutomator2Options;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

import static utils.PropertiesReader.getProperty;

public class AppiumConfig {
    public static AppiumDriver createAppiumDriver(String fileName) {

        String appiumUrl = getProperty(fileName, "appiumUrl");
        if (appiumUrl == null) {
            throw new RuntimeException("ERROR: appiumUrl is missing in " + fileName);
        }

        System.out.println("LOG: Trying to connect to Appium at: " + appiumUrl);

        UiAutomator2Options options = new UiAutomator2Options()
                .setPlatformName(getRequired(fileName, "os"))
                .setAutomationName(getRequired(fileName, "automationName"))
                .setDeviceName(getRequired(fileName, "deviceName"))
                .setAppPackage(getRequired(fileName, "appPackage"))
                .setAppActivity(getRequired(fileName, "appActivity"))
                .setNewCommandTimeout(Duration.ofSeconds(120))
                .setAndroidInstallTimeout(Duration.ofSeconds(120))
                .setNoReset(false);

        try {
            return new AppiumDriver(new URL(appiumUrl), options);
        } catch (MalformedURLException e) {
            throw new RuntimeException("Bad Appium URL: " + appiumUrl, e);
        }
        }

    private static String getRequired(String fileName, String key) {
        String value = getProperty(fileName, key);
        if (value == null) {
            throw new RuntimeException("Missing required property: " + key + " in " + fileName);
        }
        return value;
    }
}