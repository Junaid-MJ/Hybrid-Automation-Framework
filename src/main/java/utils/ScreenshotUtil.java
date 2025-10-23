package utils;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;

public class ScreenshotUtil {
	
	public static String CaptureScreenShot(WebDriver driver, String testName) {
		String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date()); 
		String dest = System.getProperty("user.dir")+"/reports/screenshots/"+testName+"_"+timeStamp+".png";
		
		File srcFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		
		try {
			FileUtils.copyFile(srcFile,new File(dest));
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		
		return dest;
		
	}

}
