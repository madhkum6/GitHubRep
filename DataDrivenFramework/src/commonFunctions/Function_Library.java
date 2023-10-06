package commonFunctions;

import org.openqa.selenium.By;
import org.testng.Reporter;

import config.AppUtil;

public class Function_Library extends AppUtil {
	
	
	public static boolean verify_Login(String user, String pass) {
		
		driver.get(p.getProperty("Url"));
		driver.findElement(By.xpath(p.getProperty("ObjUser"))).sendKeys(user);
		driver.findElement(By.xpath(p.getProperty("ObjPass"))).sendKeys(pass);
		driver.findElement(By.xpath(p.getProperty("ObjLogin"))).click();
		
		String expected = "dashboard";
		String actual = driver.getCurrentUrl();
		
		if(actual.contains(expected)) {
			
			Reporter.log("login successfull",true);
			return true;
		}
		else {
			Reporter.log("login unsucessfull", true);
			return false;
		}
		 
		
	}
	
	

}
