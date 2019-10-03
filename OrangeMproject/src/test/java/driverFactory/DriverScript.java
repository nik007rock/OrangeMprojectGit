package driverFactory;

import java.io.File;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import commonFunctionLibrary.FunctionLibrary;
import utilities.ExcelFileUtil;

public class DriverScript {
    WebDriver driver;
	
	ExtentHtmlReporter path;
	ExtentReports report;
	ExtentTest logger;

	public void startTest() throws Throwable
	{
		ExcelFileUtil excel=new ExcelFileUtil();
		//module sheet
		String ModuleStatus="";
		for(int i=1;i<=excel.rowCount("MasterTestCase");i++)
		{
			if(excel.getData("MasterTestCase",i, 2).equalsIgnoreCase("Y"))
			{
				String TCModule=excel.getData("MasterTestCase",i,1);
				int rowCount=excel.rowCount(TCModule);
				
				path=new ExtentHtmlReporter("./Reports/"+TCModule+".html"+"_"+FunctionLibrary.generateDate());
				report=new ExtentReports();
				report.attachReporter(path);
				logger=report.createTest(TCModule);
				
				//Corresponding sheet
				for(int j=1;j<=rowCount;j++)
				{
					String Description=excel.getData(TCModule, j, 0);
					String Object_Type=excel.getData(TCModule, j, 1);
					String locator_Type=excel.getData(TCModule, j, 2);
					String locator_Value=excel.getData(TCModule, j, 3);
					String Test_Data=excel.getData(TCModule, j, 4);
					
					try
					{
						if(Object_Type.equalsIgnoreCase("startBrowser"))
						{
							driver=FunctionLibrary.startBrowser(driver);
							logger.log(Status.INFO,Description);
						}
						if(Object_Type.equalsIgnoreCase("openApplication"))
						{
							FunctionLibrary.openApplication(driver);
							logger.log(Status.INFO,Description);
						}
						if(Object_Type.equalsIgnoreCase("waitForElement"))
						{
							FunctionLibrary.waitForElement(driver, locator_Type, locator_Value);
							logger.log(Status.INFO,Description);
						}
						if(Object_Type.equalsIgnoreCase("typeAction"))
						{
							FunctionLibrary.typeAction(driver, locator_Type, locator_Value,Test_Data);
							logger.log(Status.INFO,Description);
						}
						if(Object_Type.equalsIgnoreCase("clickAction"))
						{
							FunctionLibrary.clickAction(driver, locator_Type, locator_Value);
							logger.log(Status.INFO,Description);
						}
						
						if(Object_Type.equalsIgnoreCase("titleValidation"))
						{
							FunctionLibrary.titleValidation(driver, Test_Data);
							logger.log(Status.INFO,Description);
						}
						
						if(Object_Type.equalsIgnoreCase("closeAppilication"))
						{
							FunctionLibrary.closeAppilication(driver);
							logger.log(Status.INFO,Description);
						}
						
						if(Object_Type.equalsIgnoreCase("mouseAction"))
						{
							FunctionLibrary.mouseAction(driver, locator_Type, locator_Value);
							logger.log(Status.INFO, Description);
						}
						
						if(Object_Type.equalsIgnoreCase("captureData"))
						{
							FunctionLibrary.captureData(driver, locator_Type, locator_Value);
							logger.log(Status.INFO, Description);
						}
						
				/*		if(Object_Type.equalsIgnoreCase("photoUpload"))
						{
							FunctionLibrary.photoUpload(driver, locator_Type, locator_Value);
							logger.log(Status.INFO, Description);
						}
						
				*/		
						if(Object_Type.equalsIgnoreCase("dropDown"))
						{
							FunctionLibrary.dropDown(driver, locator_Type, locator_Value, Test_Data);
							logger.log(Status.INFO, Description);
						}
						
						if(Object_Type.equalsIgnoreCase("datePicker"))
						{
							FunctionLibrary.datePicker(driver, locator_Type, locator_Value, Test_Data);
							logger.log(Status.INFO, Description);
						}
						excel.setData(TCModule, j, 5, "Pass");
						ModuleStatus="true";
							
					}
					
					catch(Exception e)
						{
						excel.setData(TCModule, j, 5,"Fail");
						ModuleStatus="false";
						
						logger.log(Status.FAIL,Description + " Fail");
						File srcFile=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
						FileHandler.copy(srcFile, new File("./Screenshots/"+Description+"_"+FunctionLibrary.generateDate()+".jpg"));
						
						break;
						}
					catch(AssertionError e)
					{
						excel.setData(TCModule, j, i, "Fail");
						ModuleStatus="false";
						logger.log(Status.FAIL,Description+"Fail");
						File srcFile=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
						FileHandler.copy(srcFile, new File("./Screenshots/"+Description+"_"+FunctionLibrary.generateDate()+".jpg"));
						
						break;
					}
				}
				if(ModuleStatus.equalsIgnoreCase("true"))
				{
					excel.setData("MasterTestCase",i,3,"Pass");
				}
				
				else
					{
						excel.setData("MasterTestCase", i, 3,"Fail");
					}
				report.flush();
			}
			
			else 
			{
				excel.setData("MasterTestCase",i, 3,"Not Executed");
			}
			
		}
		
		
	}
}

	
      
		




