package automation.test;

import org.openqa.selenium.WebDriver;

public class LaunchApplication {
	//https://www.cleartrip.com
	public void launchApplication(String strURL){
		InitializeDriver initDriver = new InitializeDriver();
		initDriver.setDriver("Chrome");
		WebDriver driver = InitializeDriver.getDriver();
		driver.get(strURL);
		driver.manage().window().maximize();
		String strTitle = driver.getTitle();
		
		if(strTitle.contains("Cleartrip - Flights")){
			System.out.println("Application launched successfully");
		}else{
			System.out.println("Application failed to launch");
		}		
	}
}
