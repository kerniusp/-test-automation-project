# Test Automation Project  

Introduction

This repository contains a UI test automation project built with Selenium WebDriver, JUnit, and Java.
The goal of this project is to demonstrate automated UI testing, structured test design, and automated reporting.

Objectives

The objectives of automation testing for the Swag Labs are as follows:
- Demonstrate automated UI testing using Selenium WebDriver
- Apply test design techniques such as equivalence partitioning and boundary value analysis
- Implement a Page Object Model (POM) structure for maintainable and scalable test automation  
- Generate automated HTML reports for test execution results
- Capture screenshots automatically on test failures to aid debugging
- Validate both positive and negative user scenarios
- Maintain manual test documentation alongside automated tests

##  Technologies Used  
- Java – Test implementation  
- Selenium WebDriver – Browser automation 
- JUnit – Test framework  
- Maven – Dependency management and build tool
- ExtentReports – Automated HTML test reports

The test scenarios and cases are stored in **CSV files** called "Test scenario and cases"


Test Reports
After running the tests, an ExtentReports HTML report is generated containing:
- Test execution results
- Pass / Fail status
- Detailed logs
- Screenshots for failed tests

Reports can be found in:
- test-output/ExtentReport.html
Screenshots for failed tests are stored in:
- test-output/screenshots/
<img width="1913" height="896" alt="report" src="https://github.com/user-attachments/assets/e51eea10-2992-42b2-acec-f894ff6e483a" />
<img width="1919" height="760" alt="report2" src="https://github.com/user-attachments/assets/d7cc28cf-38ed-4e0a-aace-a8096f23c442" />

How to Run Tests
- Clone the repository
- Install dependencies using Maven
- Run the tests
- mvn test

After execution, open the generated ExtentReport.html to view the test results.

