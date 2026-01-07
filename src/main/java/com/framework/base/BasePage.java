package com.framework.base;

import com.framework.driver.DriverFactory;
import com.framework.utils.PropertyReader;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

@Slf4j
public abstract class BasePage {
    protected WebDriver driver;
    protected WebDriverWait wait;

    public BasePage() {
        // Grab driver from Factory instead of passing it in
        this.driver = DriverFactory.getDriver();

        // Fetch timeout from src/main/resources via our static PropertyReader
        long baseTimeout = Long.parseLong(PropertyReader.get("timeout"));
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(baseTimeout));
    }

    // --- Basic Interactions ---

    protected void click(By locator, String elementName) {
        log.info("Clicking on: {}", elementName);
        waitForElementToBeClickable(locator).click();
    }

    protected void type(By locator, String text, String elementName) {
        log.info("Typing '{}' into: {}", text, elementName);
        WebElement element = waitForElementToBeVisible(locator);
        element.clear();
        element.sendKeys(text);
    }

    protected String getElementText(By locator) {
        String text = waitForElementToBeVisible(locator).getText();
        log.info("Retrieved text: {}", text);
        return text;
    }

    public String getPageTitle() {
        String title = driver.getTitle();
        log.info("Current Page Title is: {}", title);
        return title;
    }

    // --- Wait Strategies ---

    protected WebElement waitForElementToBeVisible(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    protected WebElement waitForElementToBeClickable(By locator) {
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    protected WebElement waitForElementToBePresent(By locator) {
        return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    /**
     * verifyPageLoaded ensures we are on the right screen.
     * If the locator isn't found, we throw a RuntimeException to stop the test immediately.
     */
    protected void verifyPageLoaded(By locator, String pageName) {
        try {
            log.info("Waiting for {} to load", pageName);
            waitForElementToBePresent(locator);
            log.info("{} successfully loaded", pageName);
        } catch (Exception e) {
            log.error("ERROR: {} failed to load within the timeout!", pageName);
            throw new RuntimeException(pageName + " did not load properly: " + e.getMessage());
        }
    }
}