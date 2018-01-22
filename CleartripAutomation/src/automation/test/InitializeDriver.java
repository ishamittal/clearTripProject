package automation.test;
//change2
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

public class InitializeDriver {
	private static WebDriver l_driver;
	
	public static WebDriver getDriver(){
		return l_driver;
	}
	
	public void setDriver(String strDriverType ){
		l_driver = initDriver(strDriverType);
	}
	
	public WebDriver initDriver(String strDriverType){
		switch(strDriverType.toUpperCase()) {		
			case "IE":
				System.setProperty("webdriver.ie.driver", "E:\\isha_selenium\\IEdriver.exe");
				l_driver = new InternetExplorerDriver();
				break;
				
			case "FIREFOX":
				l_driver = new FirefoxDriver();
				break;
				
			case "CHROME":
				System.setProperty("webdriver.chrome.driver", "E:\\isha_selenium\\chromedriver.exe");
				l_driver = new ChromeDriver();
				break;
				
			default:
				System.out.println("Invalid choice: " + strDriverType);
				l_driver = null;
				break;
		}
		l_driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		return l_driver;
	}
	
}
