package tests;

import org.testng.Assert;
import org.testng.annotations.*;
import driver.DriverFactory;
import pages.*;
import utils.ConfigReader;

public class CheckoutTest {

    private LoginPage loginPage;
    private InventoryPage inventoryPage;
    private CartPage cartPage;
    private CheckoutPage checkoutPage;

    @BeforeClass
    public void setup() {
        DriverFactory.initDriver();
        DriverFactory.getDriver().get(ConfigReader.get("baseUrl"));
        loginPage = new LoginPage();
        inventoryPage = new InventoryPage();
        cartPage = new CartPage();
        checkoutPage = new CheckoutPage();

        loginPage.login("standard_user", "secret_sauce");
        inventoryPage.addFirstProductToCart();
        inventoryPage.goToCart();
    }

    @Test
    public void completeCheckoutProcess() {
        cartPage.clickCheckout();
        checkoutPage.fillCheckoutInformation("John", "Doe", "12345");
        checkoutPage.clickContinue();
        checkoutPage.clickFinish();

        Assert.assertTrue(checkoutPage.isOrderSuccessMessageDisplayed(), "Order not completed!");
    }

    @AfterClass
    public void tearDown() {
        DriverFactory.quitDriver();
    }
}
