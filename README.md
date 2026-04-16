# 📱 Mobile UI Automation Framework for Phonebook Application

A modular, scalable, and production‑ready mobile automation framework built with **Appium**, **Java**, and **TestNG**.  
The project demonstrates clean architecture, maintainable Page Object design, reusable utilities, and a structured test execution flow aligned with industry best practices.

---

## 🚀 Technology Stack

- **Java 17**
- **Appium 2.x (UIAutomator2 driver)**
- **TestNG**
- **Gradle**
- **SLF4J / Logback**
- **Page Object Model (POM)**
- **Android ADB tools**
- **Properties‑based configuration**

---

## 🧱 Architecture Overview

The framework is designed around the following engineering principles:

- **Separation of concerns** — screens, tests, utilities, and configuration are isolated.
- **Reusability** — shared logic (waits, swipes, configuration) is centralized.
- **Maintainability** — Page Objects expose business‑level actions, not low‑level driver calls.
- **Scalability** — new screens and test suites can be added with minimal boilerplate.

```
src/test/java
├── mobile_tests        # Test suites (login, logout, contacts)
├── screens             # Page Objects for each application screen
├── utils               # Waits, swipes, configuration, helpers
└── dto                 # Data models (User)
```

## 🧪 Example Test Case

```java
@Test
public void loginPositiveTest() {
    User user = new User("test@test.com", "Test1234$");

    loginRegistrationScreen.typeLoginRegistrationForm(user);
    loginRegistrationScreen.clickBtnLogin();

    boolean isLoaded = new ContactListScreen(driver).isContactListDisplayed();
    Assert.assertTrue(isLoaded, "Contact List screen should be displayed after login");
}
```

## 📄 Configuration

Application and device settings are stored in base.properties:

```
platformName=Android
deviceName=Pixel_5_API_30
appPackage=com.sheygam.contactapp
appActivity=.SplashActivity
```

## ▶️ Running Tests
1. Install dependencies
```
gradle clean build
```
2. Start Appium Server
(Local Appium server or Appium Inspector)

3. Execute test suite
```
gradle test
```
## 🧩 Implemented Features
- Login (positive and negative flows)
- Logout
- Contact creation and deletion
- Toast validation
- Swipe gestures
- Element wait strategies
- Structured logging
- Page Object Model with reusable components

## 🛠 Roadmap
- Integrate Allure Reports
- Add WaitUtils with explicit wait wrappers
- Introduce DriverFactory and capability builder
- Add CI pipeline (GitHub Actions)
- Expand negative test coverage
- Add parameterized test execution

## 👤 Author
Ilya Qa Test  
Automation QA Engineer
GitHub: https://github.com/IlyaQaTest