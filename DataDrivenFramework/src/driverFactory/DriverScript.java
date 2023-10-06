package driverFactory;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.Reporter;
import org.testng.annotations.Test;

import commonFunctions.Function_Library;
import config.AppUtil;
import utilities.ExcelFilePath;

public class DriverScript extends AppUtil {
	
	String inputpath = "./FileInput/LoginData_lyst3815.xlsx";
	String Outputpath = "./FileOutput/DataDriven.xlsx";
	boolean res =false;
	
	@Test
	public void validate_login() throws Throwable {
	

		ExcelFilePath xl = new ExcelFilePath(inputpath, "Login");
		
		int rc = xl.getRowNum();
		Reporter.log("no of row:: "+rc,true);
		
		for(int i=1;i<=rc;i++) {
			
			String username = xl.getCellData(i, 0);
			String password = xl.getCellData(i, 1);
			
			res = Function_Library.verify_Login(username, password);
			
			if(res) {
				
				xl.setCellData(i, 2, "pass", Outputpath);
				xl.setCellData(i, 3, "login sucessfull", Outputpath);
			}
			
			else {
				
				File Screen = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
				FileUtils.copyFile(Screen, new File("./Screenshot/failed"+i+"/login.html"));
				xl.setCellData(i, 2, "fail", Outputpath);
				xl.setCellData(i, 3, "login failed", Outputpath);
			}
			
			
			
		}
		
	}
	
	
	

}
