package com.framework.pages;

import com.framework.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage {

    private final By usernameField = By.id("user-name");
    private final By passwordField = By.id("password");
    private final By loginButton = By.id("login-button");
    private final By errorMessage = By.cssSelector("h3[data-test='error']");

    public LoginPage() {
        super();
        verifyPageLoaded(loginButton, "Login page");
    }

    public LoginPage enterUsername(String username) {
        type(usernameField, username, "Username Field");
        return this;
    }

    public LoginPage enterPassword(String password) {
        type(passwordField, password, "Password Field");
        return this;
    }

    public ProductsPage clickLogin() {
        click(loginButton, "Login Button");
        return new ProductsPage();
    }

    public LoginPage clickLoginExpectingError() {
        click(loginButton, "Login Button");
        return this;
    }
    public String getErrorMessage() {
        return getElementText(errorMessage);
    }

}
