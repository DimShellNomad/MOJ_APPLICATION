package com.framework.base;

import com.framework.utils.PropertyReader;
import lombok.extern.slf4j.Slf4j;
import com.framework.driver.DriverFactory;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.util.Properties;

@Slf4j
public class BaseTest {

    protected Properties config;

    @BeforeMethod(alwaysRun = true)
    public void setUp() {
        String browser = PropertyReader.get("browser");
        String url = PropertyReader.get("url");

        log.info("Starting test execution");
        DriverFactory.initDriver(browser);
        log.info("Browser launched and maximised");
        DriverFactory.getDriver().get(url);
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        WebDriver driver = DriverFactory.getDriver();

        log.info("Closing Driver and Cleaning Up.");
        DriverFactory.quitDriver();
    }
}