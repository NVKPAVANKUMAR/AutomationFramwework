package opeartion;

import java.util.Properties;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class UIOperation {
	
	WebDriver driver;
	
	public UIOperation(){}
	public UIOperation(WebDriver driver){ this.driver = driver; }
	
	public void perform(Properties p, String operation, String objectName, String objectType, String value)
	{
		// this method is calling getObject method defined below
		// and will be passed to driver.findElement(By) method --> By will be given by getObject method.
		try
		{
			switch(operation)
			{
				case "click":
					driver.findElement(this.getObject(p, objectName, objectType)).click(); // calling method getObject defined below and likewise for all switch cases.
					break;
					
				case "open_browser": // can have more conditions if a specific browser has to be selected.
					String path = System.getProperty("user.dir");
					
					if(objectName.equalsIgnoreCase("chrome"))
					{
						System.setProperty("webdriver.chrome.driver", path + p.getProperty(objectName));
						driver = new ChromeDriver();
					}
					
					driver.manage().window().maximize();
					driver.get(p.getProperty(value));
					break;
					
				case "enter_text":
					driver.findElement(this.getObject(p, objectName, objectType)).sendKeys(value);
					break;
					
				case "select":
					WebElement element = driver.findElement(this.getObject(p, objectName, objectType));
					Select select = new Select(element);
					select.selectByVisibleText(value);
					break;
					
				case "close_browser":
					driver.quit();
					break;
			}
		}
		catch(Exception ex)
		{
			System.out.println("Error from perform method = " + ex.getMessage());
		}
	}
	
	
	public By getObject(Properties p, String objectName, String objectType)
	{
		By by = null;
		// p will read from ReadObject class and use the values in objects.properties file.
		try
		{
			if(objectType.equalsIgnoreCase("xpath"))
			{
				by = By.xpath(p.getProperty(objectName));
			}
			else if(objectType.equalsIgnoreCase("id"))
			{
				by = By.id(p.getProperty(objectName));
			}
			else if(objectType.equalsIgnoreCase("name"))
			{
				by = By.name(p.getProperty(objectName));
			}
			else if(objectType.equalsIgnoreCase("cssSelector"))
			{
				by = By.cssSelector(p.getProperty(objectName));
			}
			else if(objectType.equalsIgnoreCase("linkText"))
			{
				by = By.linkText(p.getProperty(objectName));
			}
			else if(objectType.equalsIgnoreCase("partialLinkText"))
			{
				by = By.partialLinkText(p.getProperty(objectName));
			}
			else if(objectType.equalsIgnoreCase("tagName"))
			{
				by = By.tagName(p.getProperty(objectName));
			}
		}
		catch(Exception ex)
		{
			System.out.println("Error from getObject method = " + ex.getMessage());
		}
		
		return by;
	}
}
