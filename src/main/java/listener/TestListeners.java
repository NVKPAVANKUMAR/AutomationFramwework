package listener;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.sun.jna.platform.FileUtils;

import runner.ExecuteTestCases;

public class TestListeners implements ITestListener {

	private WebDriver driver;
	
	@Override
	public void onTestStart(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTestFailure(ITestResult result) {
		// TODO Auto-generated method stub
		try
		{
			this.driver = ((ExecuteTestCases)result.getInstance()).driver; // getting instance of WebDriver
			// screenshot code.
			File src = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);

			// for unique name of screenshot.
			String file_name = result.getMethod().getMethodName() + new SimpleDateFormat("MM-dd-yyyy_HH-ss").format(new GregorianCalendar().getTime());
			
			String path = System.getProperty("user.dir") + "\\screenshots" + file_name + ".PNG";
			
			File dest = new File(path);
			
			org.apache.commons.io.FileUtils.copyFile(src, dest);
		}
		catch(Exception ex)
		{
			System.out.println("Error from Test Listener onTestFailure screenshot method = " + ex.getMessage());
		}
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
		
	}

}
