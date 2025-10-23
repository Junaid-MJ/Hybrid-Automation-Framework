package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import java.util.List;

public class InventoryPage extends BasePage {

    @FindBy(id = "inventory_container")
    private WebElement inventoryContainer;

    @FindBy(className = "inventory_item")
    private List<WebElement> productList;

    @FindBy(css = "button.btn_inventory")
    private List<WebElement> addToCartButtons;

    @FindBy(className = "shopping_cart_badge")
    private WebElement cartBadge;

    @FindBy(className = "shopping_cart_link")
    private WebElement cartIcon;

    public boolean isInventoryPageDisplayed() {
        return isDisplayed(inventoryContainer);
    }

    public int getProductCount() {
        return productList.size();
    }

    public String addFirstProductToCart() {
        if (productList.isEmpty()) return null;
        WebElement firstProduct = productList.get(0);
        String productName = firstProduct.findElement(org.openqa.selenium.By.className("inventory_item_name")).getText();
        click(addToCartButtons.get(0));
        return productName;
    }

    public boolean isCartBadgeVisible() {
        return isDisplayed(cartBadge);
    }

    public String getCartBadgeCount() {
        return getText(cartBadge);
    }

    public void goToCart() {
        click(cartIcon);
    }
}
