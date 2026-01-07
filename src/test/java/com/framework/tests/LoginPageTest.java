package com.framework.tests;
import com.framework.base.BaseTest;
import com.framework.pages.LoginPage;
import lombok.extern.slf4j.Slf4j;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

@Slf4j
public class LoginPageTest extends BaseTest {
    // --- POSITIVE DATA ---
    @DataProvider(name = "validLoginData")
    public Object[][] getValidData() {
        return new Object[][]{
                {"standard_user", "secret_sauce"}
        };
    }

    // --- NEGATIVE DATA ---
    @DataProvider(name = "invalidLoginData")
    public Object[][] getInvalidData() {
        return new Object[][]{
                {"locked_out_user", "secret_sauce", "locked out"},
                {"standard_user", "wrong_password", "do not match"},
                {"invalid_user", "secret_sauce", "do not match"}
        };
    }

    // Positive Tests

    @Test(dataProvider = "validLoginData", description = "Verify users can login successfully")
    public void testSuccessfulLogin(String user, String pass) {
        new LoginPage()
                .enterUsername(user)
                .enterPassword(pass)
                .clickLogin();
    }

    // Negative Tests

    @Test(dataProvider = "invalidLoginData", description = "Verify error messages for failed login attempts")
    public void testFailedLogins(String user, String pass, String expectedError) {
        String actualError = new LoginPage()
                .enterUsername(user)
                .enterPassword(pass)
                .clickLoginExpectingError()
                .getErrorMessage();

        Assert.assertTrue(actualError.contains(expectedError),
                "Error message mismatch! Expected to find: " + expectedError);
    }

    @Test(description = "Purposefully fail test")
    public void intentionalFailTest()
    {
        Assert.assertTrue(false);
    }


}