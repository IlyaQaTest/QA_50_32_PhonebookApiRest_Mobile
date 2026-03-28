package mobile_tests;

import dto.Contact;
import dto.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import screens.AddNewContactScreen;
import screens.ContactListScreen;
import screens.ErrorScreen;
import screens.LoginRegistrationScreen;

import static utils.ContactFactory.positiveContact;
import static utils.PropertiesReader.*;
public class AddNewContactTests extends TestBase {
    private static final Logger logger = LoggerFactory.getLogger(RegistrationTests.class);
    LoginRegistrationScreen loginRegistrationScreen;
    ContactListScreen contactListScreen;
    AddNewContactScreen addNewContactScreen;

    @BeforeMethod
    public void login() {
        loginRegistrationScreen = new LoginRegistrationScreen(driver);
        User  user = new User(getProperty("base.properties","login"),
                getProperty("base.properties","password"));
        loginRegistrationScreen.typeLoginRegistrationForm(user);
        loginRegistrationScreen.clickBtnLogin();
        contactListScreen = new ContactListScreen(driver);
        contactListScreen.clickBtnPlus();
        addNewContactScreen = new AddNewContactScreen(driver);
    }
    @Test
    public void addNewContactTest() {
        Contact contact = positiveContact();
        addNewContactScreen.typeContactForm(contact);
        addNewContactScreen.clickBtnCreate();
        Assert.assertTrue(contactListScreen
                .isTextInMessageContactWasAddedPresent("Contact was added",5));
    }
    @Test
    public void addNewContactNegative_WrongLengthPhoneTest(){
        Contact contact = positiveContact();
        contact.setPhone("098765443");
        addNewContactScreen.typeContactForm(contact);
        addNewContactScreen.clickBtnCreate();
        Assert.assertTrue(new ErrorScreen(driver)
                .validateTextInError("min 10, max 15!", 5));
    }

    @Test
    public void addNewContactNegative_EmptyNameTest(){
        Contact contact = positiveContact();
        contact.setName("");
        addNewContactScreen.typeContactForm(contact);
        addNewContactScreen.clickBtnCreate();
        Assert.assertTrue(new ErrorScreen(driver)
                .validateTextInError("not be blank", 5));
    }

    @Test
    public void addNewContactNegative_EmptyLastNameTest(){
        Contact contact = positiveContact();
        contact.setLastName("");
        addNewContactScreen.typeContactForm(contact);
        addNewContactScreen.clickBtnCreate();
        Assert.assertTrue(new ErrorScreen(driver)
                .validateTextInError("not be blank", 5));
    }

    @Test
    public void addNewContactNegative_EmptyAddressTest(){
        Contact contact = positiveContact();
        contact.setAddress("");
        addNewContactScreen.typeContactForm(contact);
        addNewContactScreen.clickBtnCreate();
        Assert.assertTrue(new ErrorScreen(driver)
                .validateTextInError("not be blank", 5));
    }
}
