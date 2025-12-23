# Automation Selenium Practice

UI test automation framework built with **Java + Selenium + TestNG**, following Page Object Model and clean framework practices.

This project is meant for learning and portfolio purposes, structured the way real-world automation frameworks are built.

---

## Tech Stack

- Java 8
- Selenium WebDriver
- TestNG
- Maven
- Log4j2
- Git & GitHub

---

## Framework Structure

```text
src
├── main
│   └── java
│       ├── base
│       │   └── BaseTest.java
│       ├── pages
│       │   └── LoginPage.java
│       └── utilities
│           ├── ConfigReader.java
│           ├── DriverFactory.java
│           ├── ElementUtils.java
│           ├── LocatorsUtil.java
│           └── ScreenshotUtil.java
│
├── test
│   └── java
│       ├── listeners
│       │   └── TestListener.java
│       └── testcases
│           └── QaRegisterTest.java
│
└── resources
    ├── config.properties
    ├── locators.properties
    └── testng.xml


---

## Key Design Decisions

- **Page Object Model (POM)** for maintainability
- **By locators instead of WebElement** to avoid stale element issues
- **DriverFactory with ThreadLocal WebDriver** for scalability
- **Centralized waits & actions** via ElementUtils
- **Externalized configuration** using properties files
- **Listeners for screenshots & logging on failures**

---

## Configuration

### `config.properties`
```properties
browser=chrome
base.url=https://demoqa.com
login.url=/login

### `locators.properties` (sample)

```properties
loginBtn=//button[@id='login']
userName=//input[@id='userName']
password=//input[@id='password']
recaptchaFrame=//iframe[@title='reCAPTCHA']

