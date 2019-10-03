package commonFunctionLibrary;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import utilities.PropertyFileUtil;

public class FunctionLibrary 
{
    public static WebDriver startBrowser(WebDriver driver) throws Throwable
    {
    	if(PropertyFileUtil.getValueForKey("Browser").equalsIgnoreCase("Firefox"))
    	{
    		System.setProperty("webdriver.gecko.driver", ".\\CommonJarFiles\\geckodriver.exe");
    		driver = new FirefoxDriver();
    		driver.manage().deleteAllCookies();
    		
    	}
    	if(PropertyFileUtil.getValueForKey("Browser").equalsIgnoreCase("Chrome"))
    	{
    		System.setProperty("webdriver.chrome.driver", ".\\CommonJarFiles\\chromedriver.exe");
    		driver = new ChromeDriver();
    		driver.manage().deleteAllCookies();
    	}
    	
    	return driver;
    }
    public static void openApplication(WebDriver driver) throws Throwable
    {
      driver.manage().window().maximize();
      driver.get(PropertyFileUtil.getValueForKey("URL"));
    }
    
    public static void typeAction(WebDriver driver,String locatorType,String locatorValue,String TestData) throws Throwable
	{
		if(locatorType.equalsIgnoreCase("id"))
		{
			driver.findElement(By.id(locatorValue)).sendKeys(TestData);
		}
		if(locatorType.equalsIgnoreCase("name"))
		{
			driver.findElement(By.name(locatorValue)).sendKeys(TestData);
		}
		if(locatorType.equalsIgnoreCase("xpath"))
		{
			driver.findElement(By.xpath(locatorValue)).sendKeys(TestData);
		}
	

	 }
    public static void clickAction(WebDriver driver,String locatorType,String locatorValue) 
	 {
		 if(locatorType.equalsIgnoreCase("Id"))
		 {
			 driver.findElement(By.id(locatorValue)).click();
		 }
		 else if(locatorType.equalsIgnoreCase("name"))
		 {
			 driver.findElement(By.name(locatorValue)).click();
		 }
		 else if(locatorType.equalsIgnoreCase("xpath"))
		 {
			 driver.findElement(By.xpath(locatorValue)).click();
		 }
	 }
	 public static void waitForElement(WebDriver driver,String locatorType,String locatorValue)
	 {
		 WebDriverWait wait = new WebDriverWait(driver,30);
		 
		 if(locatorType.equalsIgnoreCase("id"))
		 {
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(locatorValue)));
		 }
		 if(locatorType.equalsIgnoreCase("name"))
		 {
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.name(locatorValue)));
		 }
		 if(locatorType.equalsIgnoreCase("xpath"))
		 {
			 wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locatorValue)));
			 
		 }
		 
	 }
	  public static void titleValidation(WebDriver driver,String exp_Title)
	  {
		  String act_Title=driver.getTitle();
		  Assert.assertEquals(act_Title, exp_Title);
		  
	  }
	  
	  public static void closeAppilication(WebDriver driver)
	  {
		  driver.close();
	  }
	  
	  public static void mouseAction(WebDriver driver,String locatorType,String locatorValue)
	  {
		 Actions action=new Actions(driver); 
		 if(locatorType.equalsIgnoreCase("id"))
		 {
			 action.moveToElement(driver.findElement(By.id(locatorValue))).build().perform();
		 }
		 else
		 {
			 action.moveToElement(driver.findElement(By.xpath(locatorValue))).build().perform();
		 }
		 
	  }
	  
	  public static void captureData(WebDriver driver,String locatorType,String locatorValue) throws Throwable
	  {
		  String data="";
		  Thread.sleep(3000);
		  if(locatorType.equalsIgnoreCase("id"))
		  {
			 data = driver.findElement(By.id(locatorValue)).getAttribute("value");
		  }
		  else 
		    if(locatorType.equalsIgnoreCase("name"))
		    {
		    	data=driver.findElement(By.name(locatorValue)).getAttribute("value");
		    }
		    else 
		    	if(locatorType.equalsIgnoreCase("xpath"))
		    	{
		    		data=driver.findElement(By.xpath(locatorValue)).getAttribute("value");
		    	}
		  FileWriter fw=new FileWriter("./CapturedData/Data.txt");
		  BufferedWriter bw=new BufferedWriter(fw);
		  bw.write(data);
		  bw.flush();
		  bw.close();
		  
	  }
/*	  public static void photoUpload(WebDriver driver,String locatorType,String locatorValue) throws Throwable
	  {
		  if(locatorType.equalsIgnoreCase("id"))
		  {
			  driver.findElement(By.id(locatorValue)).click();
		  }
		  else
			  if(locatorType.equalsIgnoreCase("name"))
			  {
				  driver.findElement(By.name(locatorValue)).click();
			  }
			  else 
				  if(locatorType.equalsIgnoreCase("xpath"))
				  {
					  driver.findElement(By.xpath(locatorValue)).click();
				  }
		  Runtime.getRuntime().exec("./Photos/wipro.png"); 
		  
	  }
	  */
	  
	  public static void dropDown(WebDriver driver,String locatorType,String locatorValue,String text)
	  {
		  WebElement obj1=null;
		  if(locatorType.equalsIgnoreCase("id"))
		  {
			 obj1= driver.findElement(By.id(locatorValue));
		  }
		  if(locatorType.equalsIgnoreCase("name"))
		  {
			  obj1= driver.findElement(By.name(locatorValue));
		  }
		  if(locatorType.equalsIgnoreCase("xpath"))
		  {
			  obj1= driver.findElement(By.xpath(locatorValue));
		  }
		  
		  Select list=new Select(obj1);
		  list.selectByVisibleText(text);
	  }
	  
	  public static void datePicker(WebDriver driver,String locatorType,String locatorValue,String date)
	  {
		  ((JavascriptExecutor)driver).executeScript("(document.getElementById('"+locatorValue+"').value='"+date+"')");
	  }
	 public static String generateDate()
	 {
		 Date d=new Date();
		 SimpleDateFormat sdf=new SimpleDateFormat("YYYY_MM_dd");
		 return sdf.format(d);
	 }
	
}


