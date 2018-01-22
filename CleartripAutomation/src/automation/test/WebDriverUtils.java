package automation.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class WebDriverUtils {
	static WebDriver driver = InitializeDriver.getDriver();
	
	//Find the element
	public static WebElement findElement(String strElement){
		try {
			return driver.findElement(getElement(strElement));
		}catch (Exception e) {
			e.printStackTrace();
			System.out.println("Element: " + strElement + " not found");
			return null;
		}
	}
	
	//Get element By Locator
	private static By getElement(String strElement) {
		InputStream objRep = null;
		Properties prop = null;
		try {
			objRep = new FileInputStream(new File("ObjectRepository.properties"));		
			prop = new Properties();
			prop.load(objRep);
		} catch (Exception e) {
			e.printStackTrace();
		}
		String strLocator = prop.getProperty(strElement);
		String strLocatorType = strLocator.split(";")[0];
		String strLocatorValue = strLocator.split(";")[1];
		
		switch (strLocatorType.toUpperCase()){
			case "LINKTEXT":
				return By.linkText(strLocatorValue);
				
			case "XPATH":
				return By.xpath(strLocatorValue);
				
			case "ID":
				return By.id(strLocatorValue);
				
			case "PARTIALLINKTEXT":
				return By.partialLinkText(strLocatorValue);
				
			case "NAME":
				return By.name(strLocatorValue);
		}
		return null;
	}
	
	//Click Element
	public static void clickElement(String strElement){
		findElement(strElement).click();
		System.out.println(strElement + " clicked successfully");
	}
	
	//Enter value in element
	public static void enterValue(String strElement, String strValue){		
		WebElement element = findElement(strElement);		
		element.clear();
		element.sendKeys(strValue);
		System.out.println("Value: " + strValue + " entered successfully");
	}
	
	//Select value from element
	public static void selectValue(String strElement, String strValue){		
		WebElement element = findElement(strElement);
		Select select = new Select(element);
		select.selectByValue(strValue);		
		System.out.println("Value: " + strValue + " selected successfully");
	}
	
	//Select visible text from element
	public static void selectByText(String strElement, String strText){		
		WebElement element = findElement(strElement);
		Select select = new Select(element);
		select.selectByValue(strText);		
		System.out.println("Text: " + strText + " selected successfully");
	}
	
	//Select index from element
	public static void selectValue(String strElement, int index){		
		WebElement element = findElement(strElement);
		Select select = new Select(element);
		select.selectByIndex(index);		
		System.out.println("Value at index: " + index + " selected successfully");
	}
	
	
	
}
