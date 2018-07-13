package runner;

import java.io.IOException;
import java.util.Properties;

import driver.BrowserInstance;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import opeartion.ReadExcel;
import opeartion.ReadObject;
import opeartion.UIOperation;

import static utilities.ReportGenerator.*;

public class ExecuteTestCases extends BrowserInstance {

    ReadObject read = new ReadObject(); // will return Properties object to read the objects.property file.
    Properties property;
    ReadExcel read_excel = new ReadExcel(); // Because excel workbook is initialized in the constructor.
    UIOperation ui_operation;

    @BeforeSuite
    public void setUp() throws IOException {
        startReport();
    }

    @Test(dataProvider = "hybridData", dataProviderClass = ReadExcel.class)
    public void execute(String operation, String objectName, String objectType, String value) {
        startTest(new Object() {
        }.getClass().getEnclosingMethod().getName());
        property = read.getObjectRepository(); // getting property to read from objects.property
        ui_operation = new UIOperation(driver);
        try {
            ui_operation.perform(property, operation, objectName, objectType, value, logger);
            logger.pass("SignUp Success");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @AfterClass
    public void tearDown() {
        endReport();
    }


}
