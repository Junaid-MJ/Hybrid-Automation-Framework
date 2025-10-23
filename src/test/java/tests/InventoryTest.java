
package tests;

import org.testng.Assert;
import org.testng.annotations.*;
import driver.DriverFactory;
import pages.InventoryPage;
import pages.LoginPage;
import utils.ConfigReader;

public class InventoryTest {

    private LoginPage loginPage;
    private InventoryPage inventoryPage;

    @BeforeClass
    public void setup() {
        DriverFactory.initDriver();
        DriverFactory.getDriver().get(ConfigReader.get("baseUrl"));
        loginPage = new LoginPage();
        inventoryPage = new InventoryPage();

        // login before verifying inventory
        loginPage.login("standard_user", "secret_sauce");
    }

    @Test(priority = 1)
    public void verifyProductListDisplayed() {
        Assert.assertTrue(inventoryPage.isInventoryPageDisplayed(), "Inventory page not loaded!");
        Assert.assertTrue(inventoryPage.getProductCount() > 0, "No products displayed!");
    }

    @Test(priority = 2)
    public void addProductToCartTest() {
        String productName = inventoryPage.addFirstProductToCart();
        Assert.assertTrue(inventoryPage.isCartBadgeVisible(), "Cart badge not visible after adding item!");
        Assert.assertEquals(inventoryPage.getCartBadgeCount(), "1");
    }

    @AfterClass
    public void tearDown() {
        DriverFactory.quitDriver();
    }
}
