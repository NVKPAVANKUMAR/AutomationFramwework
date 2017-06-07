package runner;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;
import opeartion.ReadExcel;
import opeartion.ReadObject;
import opeartion.UIOperation;

public class ExecuteTestCases {
	
	ReadObject read =  new ReadObject(); // will return Properties object to read the objects.property file.
	Properties property = new Properties();
	ReadExcel read_excel = new ReadExcel(); // Because excel workbook is initialized in the constructor.
	UIOperation ui_operation = null;
	public WebDriver driver;
	
	public ExecuteTestCases()
	{
		property = read.getObjectRepository(); // getting property to read from objects.property
		ui_operation = new UIOperation(driver);
	}
	
	// as data provider is present in another class, it is static.
	// also we have to mention the class name in which it is present.
	@Test(dataProvider = "hybridData", dataProviderClass = ReadExcel.class)
	public void execute(String operation, String objectName, String objectType, String value)
	{
		// calling the perform method to run the test cases.
		ui_operation.perform(property, operation, objectName, objectType, value);
	}

}
