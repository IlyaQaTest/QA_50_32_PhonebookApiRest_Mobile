package screens;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.WebElement;

public class UpdateContactScreen extends BaseScreen {
    public UpdateContactScreen(AppiumDriver driver) {
        super(driver);
    }
    @AndroidFindBy(id ="com.sheygam.contactapp:id/updateBtn")
    WebElement btnUpdate;
    @AndroidFindBy(id = "com.sheygam.contactapp:id/inputName")
    WebElement inputName;

    public void clearName() {
        inputName.clear();
    }
    public void typeName(String name) {
        inputName.sendKeys(name);
    }
    public void clickUpdateBtn() {
        btnUpdate.click();
    }
}
