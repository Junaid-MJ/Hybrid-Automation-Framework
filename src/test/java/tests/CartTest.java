package tests;

import org.testng.Assert;
import org.testng.annotations.*;
import driver.DriverFactory;
import pages.CartPage;
import pages.InventoryPage;
import pages.LoginPage;
import utils.ConfigReader;

public class CartTest {

    private LoginPage loginPage;
    private InventoryPage inventoryPage;
    private CartPage cartPage;

    @BeforeClass
    public void setup() {
        DriverFactory.initDriver();
        DriverFactory.getDriver().get(ConfigReader.get("baseUrl"));
        loginPage = new LoginPage();
        inventoryPage = new InventoryPage();
        cartPage = new CartPage();

        loginPage.login("standard_user", "secret_sauce");
        inventoryPage.addFirstProductToCart();
        inventoryPage.goToCart();
    }

    @Test
    public void verifyCartItems() {
        Assert.assertTrue(cartPage.isCartPageDisplayed(), "Cart page not displayed!");
        Assert.assertTrue(cartPage.getCartItemCount() > 0, "Cart is empty!");
    }

    @AfterClass
    public void tearDown() {
        DriverFactory.quitDriver();
    }
}
