# Selenium Hybrid Automation Framework – DemoQA

## Project Overview

This repository contains a Selenium-based hybrid test automation framework built using Java and TestNG. The primary goal of this project is to demonstrate **real-world automation framework design**, clean architecture, and best practices rather than full application coverage.

The framework is implemented against the DemoQA Book Store application and focuses on structure, reusability, logging, reporting, and maintainability.

---

## Tech Stack

* Java (JDK 8+)
* Selenium WebDriver
* TestNG
* Maven
* Log4j2 (logging)
* ReportNG (test reporting)
* Apache Commons IO
* Page Object Model (POM)

---

## Framework Architecture

The framework follows a layered and modular design:

```
src/main/java
 ├── base        → WebDriver setup and teardown
 ├── pages       → Page Object classes (UI abstraction)
 ├── utilities   → Reusable helpers (waits, screenshots, locators, config)

src/test/java
 ├── testcases   → Test classes
 ├── listeners   → TestNG listeners (screenshots, reporting hooks)

src/test/resources
 ├── config.properties
 ├── locators.properties
 ├── log4j2.xml
```

### Key Components

* **BaseTest**

  * Manages WebDriver lifecycle using TestNG annotations
  * Provides driver access to listeners

* **Page Objects**

  * Encapsulate UI interactions
  * No test logic inside pages

* **Utilities**

  * Screenshot utility (captures screenshots on failure)
  * Element utilities (waits, actions)
  * Locator utility (property-based locators)

* **Listeners**

  * Capture screenshots on test failure
  * Integrate logging and reporting

* **Logging**

  * Centralized logging via Log4j2
  * Console and rolling file appenders

* **Reporting**

  * HTML execution reports generated using ReportNG

---

## Test Coverage

Automated scenarios include:

* User registration (UI-level)
* User login
* reCAPTCHA Verification (Requires **Human Intervention** to complete the challenge phase)

### Important Note on Application Limitations

The DemoQA Book Store application uses **API-driven operations** for actions such as adding or removing books. These actions require authentication tokens and backend API calls and are not suitable for Selenium-only UI automation.

This framework intentionally stops at login to reflect realistic automation boundaries.

---

## Screenshot Handling

* Screenshots are automatically captured on test failure
* Implemented using a dedicated utility class
* Screenshots are stored in the `/screenshots` directory

---

## Logging & Reporting

* **Log4j2** captures execution and framework-level logs
* **ReportNG** generates readable HTML reports
* Logs and reports are separated by responsibility (engineers vs stakeholders)

---

## How to Run the Tests

### Prerequisites

* Java installed and configured
* Maven installed
* Browser driver available (ChromeDriver, GeckoDriver, etc.)

### Run via Maven

```
mvn clean test
```

### Run using TestNG XML

```
mvn clean test -DsuiteXmlFile=testng.xml
```

---

## Known Limitations

* reCAPTCHA Handling (Manual Intervention Required): Due to Google's anti-bot security measures, the test execution must be manually paused at the reCAPTCHA challenge. The user must solve the visual or audio puzzle to allow the automated script to resume and continue the verification flow.
* Full end-to-end Book Store flow is not automated due to backend/API constraints
  

These limitations are documented by design and reflect real-world automation challenges.

---

## Future Enhancements

* TestNG groups and parallel execution
* Retry analyzer for flaky tests
* Data-driven testing
* CI integration using Jenkins
* Migration to ExtentReports or Allure
* Hybrid UI + API automation using REST Assured

---

## Purpose of This Project

This project is intended to showcase:

* Clean automation framework design
* Proper separation of concerns
* Industry-standard tooling
* Realistic handling of application constraints

It serves as a foundation for more advanced, job-level automation projects.
