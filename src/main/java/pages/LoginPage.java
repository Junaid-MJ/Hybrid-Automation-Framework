package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {
	
	@FindBy(id="user-name")
	private WebElement username;

    @FindBy(id = "password")
    private WebElement password;

    @FindBy(id = "login-button")
    private WebElement loginButton;
    
    @FindBy(xpath = "//h3[@data-test='error']")
    private WebElement errorMessage;
    
    public  void login(String user, String pass) {
    	type(username,user);
    	type(password,pass);
    	click(loginButton);
    	
    }
    
    public String getErrorMessage(){
    
    	try {
    		return getText(errorMessage);
    	}
    	catch(Exception e) {
    		e.printStackTrace();
    		return "";
    	}
    	
    }
    
    
    
    
    
	

}
