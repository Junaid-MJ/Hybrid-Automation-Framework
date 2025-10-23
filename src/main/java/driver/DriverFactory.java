package driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import utils.ConfigReader;

public class DriverFactory {
	private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();
	
	public static WebDriver getDriver() {
		return driver.get();
	}
	
	public static void initDriver() {
		String browser = ConfigReader.get("browser");
		String driverPath= ConfigReader.get("driverPath");
		if(driver.get()==null) {
		if(browser.equalsIgnoreCase("chrome")) {
			System.setProperty("webdriver.chrome.driver", driverPath);
			WebDriver webDriver = new ChromeDriver();
			driver.set(webDriver);
			driver.get().manage().window().maximize();
			
		}
		else {
			throw new IllegalArgumentException("Unsupported Brower: "+ browser);
		}
		}
		
	}
	
	 public static void quitDriver() {
	        if (driver.get() != null) {
	            driver.get().quit();
	            driver.remove();
	        }
	    }
	
	

}
