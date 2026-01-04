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

Values can be overridden via Jenkins or Maven command line:
```bash
mvn test -Dbrowser=chrome -Durl=https://demoqa.com/books

### `locators.properties` (sample)

```properties
loginBtn=//button[@id='login']
userName=//input[@id='userName']
password=//input[@id='password']
recaptchaFrame=//iframe[@title='reCAPTCHA']

## CI Detection

The framework detects CI execution using environment variables.

When running in Jenkins, the environment variable `IS_CI` is used to control
CI-specific behavior such as handling CAPTCHA-based flows.

Example:
```java
Boolean.parseBoolean(System.getenv("IS_CI"))


## Known Limitations

- Some flows (e.g. CAPTCHA-protected actions) require human interaction.
- When executed in CI (detected via `IS_CI` environment variable), these flows
  are logged and handled with timeouts or skipped to avoid pipeline blockage.

## 🚀 Planned Enhancements & Learning Roadmap

This project is actively evolving as part of my test automation learning journey.  
Planned improvements include:

- **Advanced Reporting**
  - Integrate **ExtentReports** for rich HTML reports with screenshots and execution metadata
  - Explore **Allure Reports** for timeline, trend analysis, and CI-friendly reporting

- **API Testing Integration**
  - Add **API test coverage** using REST Assured
  - Validate backend APIs used by the UI flows
  - Combine UI + API tests in a single Maven project

- **CI/CD Enhancements**
  - Parameterize browser, environment, and execution settings via Jenkins
  - Improve reporting visibility inside Jenkins builds

- **Framework Improvements**
  - Improve wait and synchronization strategies
  - Enhance logging and error handling
  - Expand test coverage and scenarios

These enhancements will be added incrementally as skills and project maturity grow.

