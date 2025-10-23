package tests;

import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.*;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

import driver.DriverFactory;
import pages.LoginPage;
import reports.ExtentManager;
import utils.ConfigReader;
import utils.ScreenshotUtil;


public class LoginTest {

    private ExtentReports extent;
    private ExtentTest test;
    private LoginPage loginPage;

    
    @BeforeClass
    public void setup() {
        DriverFactory.initDriver();
        DriverFactory.getDriver().get(ConfigReader.get("baseUrl"));
        loginPage = new LoginPage();
    }

    @Test(priority = 1)
    public void validLoginTest() {
        loginPage.login("standard_user", "secret_sauce");
        String currentUrl = DriverFactory.getDriver().getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("inventory.html"), "Login failed!");
    }

    @Test(priority = 2)
    public void invalidLoginTest() {
        DriverFactory.getDriver().get(ConfigReader.get("baseUrl"));
        loginPage.login("wrong_user", "wrong_pass");
        Assert.assertTrue(loginPage.getErrorMessage().contains("Username and password"), "Error message not displayed!");
    }


    @AfterClass
    public void tearDown() {
        DriverFactory.quitDriver();
    }

   
}

