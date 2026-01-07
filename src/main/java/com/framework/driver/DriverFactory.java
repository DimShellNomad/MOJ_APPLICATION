package com.framework.driver;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver; // Needed for Grid

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

@Slf4j
public final class DriverFactory {

    private static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    private DriverFactory() {}

    public static WebDriver getDriver() {
        return driver.get();
    }

    public static void initDriver(String browser) {
        WebDriver webDriver;

        String remoteUrl = System.getenv("SE_REMOTE_URL");

        if (browser.equalsIgnoreCase("chrome")) {
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--remote-allow-origins=*");
            options.addArguments("--headless=new");
            options.addArguments("--no-sandbox");
            options.addArguments("--disable-dev-shm-usage");

            if (remoteUrl != null) {
                try {
                    log.info("Running on Selenium Grid at: {}", remoteUrl);
                    webDriver = new RemoteWebDriver(new URL(remoteUrl), options);
                } catch (MalformedURLException e) {
                    throw new RuntimeException("Invalid Remote URL: " + remoteUrl, e);
                }
            } else {
                log.info("Running Chrome locally");
                webDriver = new ChromeDriver(options);
            }

        } else if (browser.equalsIgnoreCase("firefox")) {
            webDriver = new FirefoxDriver();
        } else if (browser.equalsIgnoreCase("edge")) {
            webDriver = new EdgeDriver();
        } else {
            throw new RuntimeException("Unsupported browser: " + browser);
        }

        webDriver.manage().window().maximize();
        webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.set(webDriver);
    }

    public static void quitDriver() {
        if (getDriver() != null) {
            getDriver().quit();
            driver.remove();
        }
    }
}