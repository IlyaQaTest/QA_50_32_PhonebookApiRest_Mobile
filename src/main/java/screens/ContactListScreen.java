package screens;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.Direction;
import utils.SwipeUtils;

import java.time.Duration;
import java.util.List;

public class ContactListScreen extends BaseScreen implements SwipeUtils {

    public ContactListScreen(AppiumDriver driver) {
        super(driver);
    }
    @AndroidFindBy(xpath = "//android.widget.ImageView[@content-desc='More options']")
    WebElement moreOptions;

    @AndroidFindBy(xpath = "//*[@text='Contact list']")
    WebElement title;

    @AndroidFindBy(id = "com.sheygam.contactapp:id/add_contact_btn")
    WebElement addContactBtn;

    @AndroidFindBy(id = "com.sheygam.contactapp:id/rowContainer")
    java.util.List<WebElement> contactRows;

    @AndroidFindBy(xpath = "//android.widget.Toast[@text='Contact was added!']")
    WebElement messageContactWasAdded;

    @AndroidFindBy(xpath = "//android.widget.ImageButton[@content-desc='add']")
    WebElement btnPlus;

    @AndroidFindBy(id = "android:id/button1")
    WebElement btnYes;

    @AndroidFindBy(id = "com.sheygam.contactapp:id/emptyTxt")
    WebElement noContacts;

    @AndroidFindBy(xpath = "(//*[@resource-id='com.sheygam.contactapp:id/rowContainer'])")
    List<WebElement> contactListScreen;

    @AndroidFindBy(xpath = "//android.widget.Toast[@text='Contact was updated!']")
    WebElement messageContactWasUpdated;

    @AndroidFindBy(xpath = "//android.widget.TextView[@resource-id='com.sheygam.contactapp:id/title' and @text='Logout']")
    WebElement btnLogout;

    @AndroidFindBy(xpath = "//android.widget.TextView[@resource-id='com.sheygam.contactapp:id/title' and @text='Date picker']")
    WebElement datePicker;

    public void clickDatePicker() {
        datePicker.click();
    }

    public void clickBtnLogout() {
        btnLogout.click();
    }

    public void clickMoreOptions() {
        moreOptions.click();
    }

    public void clickBtnPlus() {
        addContactBtn.click();
    }

    public boolean isBtnPlusPresent() {
        try {
            new WebDriverWait(driver, Duration.ofSeconds(10))
                    .until(ExpectedConditions.visibilityOf(addContactBtn));
            return true;
        } catch (Exception e) {
            return false;
        }
    }



    public boolean isContactListDisplayed() {
        try {
            // Проверяем заголовок — это надежнее
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
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
    public void deleteContactMiddle(){
        new WebDriverWait(driver, Duration.ofSeconds(2))
                .until(ExpectedConditions.visibilityOf(btnPlus));
        swipeScreen(driver, Direction.RIGHT);
        btnYes.click();
    }
    public void deleteFirstContact(){
        new WebDriverWait(driver, Duration.ofSeconds(2))
                .until(ExpectedConditions.visibilityOf(btnPlus));

        swipeInsideElementDelete(driver, contactRows.get(0));
        btnYes.click();
    }

    public WebElement getContact(int index) {
        return contactRows.get(index);
    }

    public void deleteLastContact() {

        if (contactRows.isEmpty()) {
            throw new RuntimeException("No contacts found");
        }

        WebElement last = contactRows.get(contactRows.size() - 1);

        swipeInsideElementDelete(driver, last);
        btnYes.click();
    }
    public void waitForContactListNotEmpty() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(d -> !contactRows.isEmpty());
    }
    public void refresh() {
        // Свайп вниз от середины экрана — классический pull-to-refresh
        swipeScreen(driver, Direction.DOWN);

        // Даем UI время обновиться
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ignored) {}
    }

    public boolean isTextInMessageContactWasUpdatedPresent(String text, int time){
        return isTextInElementPresent(messageContactWasUpdated, text, time);
    }

    public void editFirstContact(){
        new WebDriverWait(driver, Duration.ofSeconds(2))
                .until(ExpectedConditions.visibilityOf(btnPlus));
        swipeInsideElement(driver, contactListScreen.get(0), Direction.LEFT);
    }

}