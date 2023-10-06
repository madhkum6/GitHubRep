package config;

import java.io.FileInputStream;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Reporter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

public class AppUtil {

	public static WebDriver driver;
	public static Properties p;

	@BeforeTest
	public static void setUp() throws Throwable {

		p = new Properties();
		p.load(new FileInputStream("./PropertyFile/Environment.properties"));

		if(p.getProperty("Browser").equalsIgnoreCase("chrome")) {

			driver = new ChromeDriver();
			driver.manage().window().maximize();
		}

		if(p.getProperty("Browser").equalsIgnoreCase("firefox")) {

			driver = new FirefoxDriver();
			driver.manage().window().maximize();
		}
		
		else {
			
			Reporter.log("brower is not available",true);
		}


	}
	
	@AfterTest
	public static void tearDown() {
		driver.quit();
		
	}


}
