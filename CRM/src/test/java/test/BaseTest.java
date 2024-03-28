package test;


import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import constants.Constants;
import utility.ElementUtility;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;

public class BaseTest {
	WebDriver driver;

	@Parameters({"browser"})
	@BeforeMethod
	public void beforeMethod(@Optional ("chrome")String browser) {
		if(browser.equals("chrome")) {
			driver =new ChromeDriver();}
		else if(browser.equals("edge"))
		{
			driver=new EdgeDriver();
		}
		else
		{
			System.out.println("pass edge or chrome");
		}
		driver.manage().window().maximize();// maximize window
		driver.get(ElementUtility.getPropertyValue("url"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(200));
	}

	@AfterMethod
	public void afterMethod(ITestResult iTestResult) throws IOException  {
		if(iTestResult.getStatus()==ITestResult.FAILURE)
		{
			takesScreenshotOnFailure(iTestResult.getName());
		}
		driver.quit();
	}
	public void takesScreenshotOnFailure(String name) throws IOException  {
		String dateName= new SimpleDateFormat("yyyy-MM-dd-hh-mm").format(new Date());
		File source=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		String destination=Constants.screenShot_path + name + dateName + ".png";
		File finalDestination=new File(destination);
		FileUtils.copyFile(source, finalDestination);

	}

}
