package opeartion;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class ReadObject {
	
	Properties properties = new Properties();
	
	public Properties getObjectRepository()
	{
		try
		{
			String path = System.getProperty("user.dir") + "\\src\\main\\resources\\object\\objects.properties";
			File src = new File(path);
			FileInputStream fis = new FileInputStream(src);
			properties.load(fis);
		}
		catch(Exception ex)
		{
			System.out.println("Error from ReadObject class = " + ex.getMessage());
		}
		return properties;
	}

}
