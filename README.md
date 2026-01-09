# MOJ Application Framework
This is a containerized automation suite built with Java, Selenium Webdriver, with TestNG as the supporting test framework.
The containerization utilises a Selenium Grid sidecar pattern allowing for a portable setup.

Reporting: ExtentReports
Build Tool: Maven

Tests: /src/test/java/
Framework: /src/main/java/

## Prerequisites:
(for local setup)
- Java 21
- Maven
(docker execution)
- Docker

## Running via Docker
1. Clone the repository:
```
git clone https://github.com/DimShellNomad/MOJ_APPLICATION.git
cd MOJ_APPLICATION
```

2. Build via docker
```
docker-compose up --build --exit-code-from test-runner
```

## Running locally

Follow step 1 of running via docker
and then either
a. Right-click the testng.xml file and select run to do a full run
b. go to the specific test in com.framework.tests.LoginPageTest#testSuccessfulLogin and run from there


## Reporting:
Once the execution finishes, the framework automatically syncs the results back to your host machine.

Location: target/ExtentReport.html

Features: The report provides a high-level dashboard, category-wise breakdowns, and detailed logs of the 5 core login functionality tests.



