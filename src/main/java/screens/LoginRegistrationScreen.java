package screens;

import dto.User;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.WebElement;

public class LoginRegistrationScreen extends BaseScreen{
    public LoginRegistrationScreen(AppiumDriver driver) {
        super(driver);
    }
    @AndroidFindBy(id = "com.sheygam.contactapp:id/inputEmail")
    WebElement inputEmail;
    @AndroidFindBy(id = "com.sheygam.contactapp:id/inputPassword")
    WebElement inputPassword;
    @AndroidFindBy(id = "com.sheygam.contactapp:id/regBtn")
    WebElement btnRegistration;
    @AndroidFindBy(id = "com.sheygam.contactapp:id/loginBtn")
    WebElement btnLogin;

    public void typeLoginRegistrationForm(User user){
        // Очистка полей перед вводом — залог стабильности
        inputEmail.clear();
        inputEmail.sendKeys(user.getUsername());
        inputPassword.sendKeys(user.getPassword());
    }

    public void clickBtnRegistration(){
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
