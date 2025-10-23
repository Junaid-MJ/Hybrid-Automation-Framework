package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import java.util.List;

public class CartPage extends BasePage {

    @FindBy(className = "title")
    private WebElement cartTitle;

    @FindBy(className = "cart_item")
    private List<WebElement> cartItems;

    @FindBy(id = "checkout")
    private WebElement checkoutButton;

    public boolean isCartPageDisplayed() {
        return getText(cartTitle).equalsIgnoreCase("Your Cart");
    }

    public int getCartItemCount() {
        return cartItems.size();
    }

    public void clickCheckout() {
        click(checkoutButton);
    }
}
