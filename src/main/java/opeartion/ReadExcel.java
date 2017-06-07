package opeartion;

import java.io.File;
import java.io.FileInputStream;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;

public class ReadExcel {
	// declaring static fields which will not change.
	String path = System.getProperty("user.dir") + "\\src\\main\\resources\\object\\Facebook_Keyword.xlsx";
	static XSSFWorkbook workbook = null;
	static XSSFSheet sheet = null;
	static XSSFRow row = null;
	static XSSFCell cell = null;
	static FileInputStream fis = null;
	
	// initializing workbook and loading the excel file from path.
	public ReadExcel()
	{
		try
		{
			File src = new File(path);
			fis = new FileInputStream(src);
			workbook = new XSSFWorkbook(fis);
			sheet = workbook.getSheetAt(0);
		}
		catch(Exception ex)
		{
			System.out.println("Error from ReadExcel constructor = " + ex.getMessage());
		}
	}
	
	@SuppressWarnings("deprecation")
	@DataProvider(name = "hybridData") // this will read the keywords and data from excel and serve as DataProvider.
	public static Object[][] read()
	{
		Object[][] data = null; // declaring 2-D Object array which will hold the values.
		try
		{
			row = sheet.getRow(1);
			int rows = sheet.getLastRowNum(); // total rows.
			int cells = row.getLastCellNum(); // total columns.
			data = new Object[rows - 1][cells - 1]; // defining size of array. As array index is 0 based, so -1.
			String cell_value = "";
			
			for(int i = 2; i <= rows; i ++) // i = 2 because of first value is in row 2 --> open_browser
			{
				for(int j = 1; j < cells; j ++) // j = 1 because first value is needed from column 'Keyword'
				{
					cell = sheet.getRow(i).getCell(j); 
					if(cell.getCellTypeEnum() == CellType.NUMERIC) // if value in the cell is numeric, converting it to string.
					{
						int val = (int) cell.getNumericCellValue();
						cell_value = String.valueOf(val);
					}
					else
					{
						cell_value = cell.getStringCellValue();
					}
					
					System.out.println(cell_value);
					data[i - 2][j - 1] = cell_value; // i - 2 and j - 1 because we have to start from data[0][0]
				}
				System.out.println();
			}
			
		}
		catch(Exception ex)
		{
			System.out.println("Error from ReadExcel class = " + ex.getMessage());
		}
		return data;
	}
}
