package automation.test;

import java.io.IOException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SearchFlight {
	
	static WebDriver driver = null;
	static SearchFlight searchFlight = new SearchFlight();
	public void searchFlight() throws IOException{
		WebDriverUtils.clickElement("lnkFlights");
		WebDriverUtils.clickElement("optRoundTrip");
		WebDriverUtils.enterValue("txtFrom", "PNQ");
		WebDriverUtils.enterValue("txtTo", "DEL");
		WebDriverUtils.clickElement("txtDepartDate");
		searchFlight.selectDate("13-October-2017");
		WebDriverUtils.clickElement("txtReturnDate");
		searchFlight.selectDate("22-October-2017");		
		WebDriverUtils.selectByText("lstAdults", "2");
		WebDriverUtils.selectByText("lstChildren", "1");
		WebDriverUtils.selectByText("lstInfants", "1");
		WebDriverUtils.clickElement("btnSearch");
	}
	
	//"13-October-2017"
	public void selectDate(String strDate){
		//String compRes = searchFlight.compareDate(strDate, System.currentTimeMillis());
		Boolean strFlag = false;
		//if (compRes.equalsIgnoreCase("valid")){
		String[] arrDate = strDate.split("-");
		String strMonthYr = arrDate[1] + " " + arrDate[2];
		
		do {
			String strAppFirstMonthYr = WebDriverUtils.findElement("eleDateBoxFirstMonth").getText()
					+ " " + WebDriverUtils.findElement("eleDateBoxFirstYear").getText();
			String strAppSecondMonth = WebDriverUtils.findElement("eleDateBoxSecondMonth").getText()
					+ " " + WebDriverUtils.findElement("eleDateBoxSecondYear").getText();
			if (strMonthYr.equalsIgnoreCase(strAppFirstMonthYr)){				
				driver.findElement(By.xpath("//div[@class='monthBlock first']//a[text()='" + arrDate[0] + "']")).click();
				strFlag = true;
			}else if(strMonthYr.equalsIgnoreCase(strAppSecondMonth)) {
				driver.findElement(By.xpath("//div[@class='monthBlock last']//a[text()='" + arrDate[0] + "']")).click();
				strFlag = true;
			}else{
				WebDriverUtils.clickElement("btnNextMonth");
			}
		}while(strFlag==false);
		//}else{
		//	System.out.println("invalid date entered");
		//}		
	}
	
	public String compareDate(String strDate, Long lngCurrentDate){
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy");
		//String formattedDate = formatter.format(System.currentTimeMillis());
		//System.out.println(Date.(formattedDate)); 
		try {
			Date inputDate = (Date) formatter.parse(strDate);
			Date todayDate =(Date) formatter.parse(formatter.format(lngCurrentDate));
			
			//If input date is before than the today's date
			if (inputDate.before(todayDate)){			
				return "invalid";
			}else{
				return "valid";
			}
		} catch (ParseException e) {
			System.out.println("Invalid date input");
			return null;
		}		
	}
		
	public static void main(String[] args) throws IOException, ParseException {
		
		LaunchApplication la = new LaunchApplication();
		la.launchApplication("https://www.cleartrip.com");
		driver = InitializeDriver.getDriver();
		System.out.println(driver.getTitle());
		searchFlight.searchFlight();
		
		
	}
}
