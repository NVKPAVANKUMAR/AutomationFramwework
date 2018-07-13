package opeartion;

import java.util.Properties;

import com.aventstack.extentreports.ExtentTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import static driver.BrowserInstance.initiateDriver;
import static driver.BrowserInstance.openUrl;
import static utilities.ScreenshotUtility.takeScreenshot;

public class UIOperation {

    private WebDriver driver;

    public UIOperation(WebDriver driver) {
        this.driver = driver;
    }

    public void perform(Properties p, String operation, String objectName, String objectType, String value, ExtentTest logger) {
        try {
            switch (operation) {
                case "click":
                    //Perform click
                    driver.findElement(this.getObject(p, objectName, objectType)).click();
                    logger.info(objectName + " clicked");
                    break;
                case "enter_text":
                    //Set text on control
                    driver.findElement(this.getObject(p, objectName, objectType)).sendKeys(value);
                    logger.info(objectName + " text entered");
                    break;
                case "open_browser": // can have more conditions if a specific browser has to be selected.
                    initiateDriver(objectName);
                    System.out.println(p.getProperty(value));
                    openUrl(p.getProperty(value));
                    logger.info(objectName + " url opened");
                    break;

                case "get_text":
                    //Get text of an element
                    driver.findElement(this.getObject(p, objectName, objectType)).getText();
                    logger.info(objectName + " text captured");
                    break;

                case "select":
                    WebElement element = driver.findElement(this.getObject(p, objectName, objectType));
                    Select select = new Select(element);
                    select.selectByVisibleText(value);
                    logger.info(objectName + " selected successfully");
                    break;

                case "close_browser":
                    takeScreenshot(driver, "SignUp");
                    driver.quit();
                    logger.info("Browser closed successfully");
                    break;

                default:
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private By getObject(Properties p, String objectName, String objectType) throws Exception {
        //Find by xpath
        if (objectType.equalsIgnoreCase("XPATH")) {
            return By.xpath(p.getProperty(objectName));
        }
        //find by class
        else if (objectType.equalsIgnoreCase("CLASSNAME")) {
            return By.className(p.getProperty(objectName));
        }
        //find by id
        else if (objectType.equalsIgnoreCase("ID")) {
            return By.id(p.getProperty(objectName));
        }
        //find by name
        else if (objectType.equalsIgnoreCase("NAME")) {
            return By.name(p.getProperty(objectName));
        }
        //Find by css
        else if (objectType.equalsIgnoreCase("CSS")) {
            return By.cssSelector(p.getProperty(objectName));
        }
        //find by link
        else if (objectType.equalsIgnoreCase("LINK")) {
            return By.linkText(p.getProperty(objectName));
        }
        //find by partial link
        else if (objectType.equalsIgnoreCase("PARTIALLINK")) {
            return By.partialLinkText(p.getProperty(objectName));
        } else {
            throw new Exception("Wrong object type");
        }
    }
}
