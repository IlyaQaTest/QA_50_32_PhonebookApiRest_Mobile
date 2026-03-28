package screens;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
public class ContactListScreen extends BaseScreen {


    public ContactListScreen(AppiumDriver driver) {
        super(driver);
    }
    @AndroidFindBy(xpath = "//*[@text='Contact list']")
    WebElement title;
    @AndroidFindBy(id = "com.sheygam.contactapp:id/add_contact_btn")
    WebElement addContactBtn;
    @AndroidFindBy(id = "com.sheygam.contactapp:id/rowContainer")
    java.util.List<WebElement> contactRows;
    @AndroidFindBy(id ="android:id/title_template")
    WebElement appStop;
    @AndroidFindBy(xpath = "//android.widget.Toast[@text='Contact was added!']")
    WebElement messageContactWasAdded;


    public boolean isBtnPlusPresent() {
        return appStop.isDisplayed();
    }
    public void clickBtnPlus() {
        addContactBtn.click();
    }

    public boolean isContactListDisplayed() {
        try {
            // Проверяем заголовок — это надежнее
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            return wait.until(ExpectedConditions.visibilityOf(title)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    // Метод для удаления всех контактов (полезно для очистки перед тестами)
    public boolean isContactListEmpty() {
        return contactRows.isEmpty();
    }

    public boolean validateTextInContactListScreenAfterRegistration(String expectedText, int timeout) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
            // Динамический поиск любого текста на экране
            WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//*[@text='" + expectedText + "']")
            ));
            return element.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
    public boolean isTextInMessageContactWasAddedPresent(String text, int time){
        return isTextInElementPresent(messageContactWasAdded, text, time);
    }
}