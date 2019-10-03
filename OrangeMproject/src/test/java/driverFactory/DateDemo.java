package driverFactory;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class DateDemo {

	public static void main(String[] args) {
		
		WebDriver driver=new FirefoxDriver();
		driver.get("https://jqueryui.com/datepicker/");
		((JavascriptExecutor)driver).executeScript("document.getElementById('datepicker').value='02/12/2003'");
		//FunctionLibrary.datePicker(driver, "", "datepicker", "02/05/1999");

 }
}