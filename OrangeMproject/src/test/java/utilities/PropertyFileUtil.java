package utilities;
import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class PropertyFileUtil 
{
	public static String getValueForKey(String key) throws Throwable, Throwable
	{
		Properties configpro=new Properties();
		configpro.load(new FileInputStream(new File("./ConfigFile/Environment.properties")));
		return configpro.getProperty(key);
	
	}
}
	