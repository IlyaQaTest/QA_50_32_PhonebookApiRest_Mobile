package mobile_tests;

import dto.Contact;
import dto.ContactsDto;
import dto.TokenDto;
import dto.User;
import io.restassured.response.Response;
import manager.AuthenticationController;
import manager.ContactController;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import screens.AddNewContactScreen;
import screens.ContactListScreen;
import screens.LoginRegistrationScreen;
import screens.UpdateContactScreen;
import utils.BaseApi;
import static utils.ContactFactory.positiveContact;
import static utils.PropertiesReader.getProperty;

public class UpDateContactTests extends TestBase {
    LoginRegistrationScreen loginRegistrationScreen;
    ContactListScreen contactListScreen;
    TokenDto tokenDto;
    ContactsDto contactsDtoBeforeDelete, contactsDtoAfterDelete;
    AddNewContactScreen addNewContactScreen;
    UpdateContactScreen updateContactScreen;

    @BeforeMethod
    public void login() {
        User user = new User(getProperty("base.properties", "login"),
                getProperty("base.properties", "password"));
        tokenDto = AuthenticationController.requestRegLogin(user, BaseApi.LOGIN_URL)
                .as(TokenDto.class);
        Response response = ContactController.requestGetAllUserContacts(tokenDto.getToken());
        System.out.println(response.getStatusLine());
        if(response.getStatusCode() == 200)
            contactsDtoBeforeDelete = response.as(ContactsDto.class);
        loginRegistrationScreen = new LoginRegistrationScreen(driver);
        loginRegistrationScreen.typeLoginRegistrationForm(user);
        loginRegistrationScreen.clickBtnLogin();
        contactListScreen = new ContactListScreen(driver);
        addNewContactScreen = new AddNewContactScreen(driver);
    }
    @Test
    public void updateContactPositiveTest() {
        // 1. Если нет контактов — создаём один
        if (ContactController.requestGetAllUserContacts(tokenDto.getToken())
                .as(ContactsDto.class).getContacts().isEmpty()) {

            contactListScreen.clickBtnPlus();
            Contact contact = positiveContact();
            addNewContactScreen.typeContactForm(contact);
            addNewContactScreen.clickBtnCreate();
            contactListScreen.waitForContactListNotEmpty();
        }

        ContactsDto before = ContactController
                .requestGetAllUserContacts(tokenDto.getToken())
                .as(ContactsDto.class);

        String oldName = before.getContacts().get(0).getName();

        contactListScreen.swipeInsideElementUpdate(driver, contactListScreen.getContact(0));
        updateContactScreen = new UpdateContactScreen(driver);

        String newName = "UpdatedName_" + System.currentTimeMillis();
        updateContactScreen.clearName();
        updateContactScreen.typeName(newName);
        updateContactScreen.clickUpdateBtn();

        ContactsDto after = ContactController
                .requestGetAllUserContacts(tokenDto.getToken())
                .as(ContactsDto.class);

        Assert.assertEquals(after.getContacts().get(0).getName(), newName,
                "Имя контакта не обновилось!");

    }
}