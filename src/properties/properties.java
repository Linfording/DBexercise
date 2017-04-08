package properties;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import utils.DBUtils;

public class properties {
	public static String DB_DIRVER;
	public static String DB_URL;
	public static String DB_USERNAME;
	public static String DB_PASSWORD;
	private static Properties prop=new Properties();
	static {
		ClassLoader classLoader=DBUtils.class.getClassLoader();
		String path=classLoader.getResource("./config.properties").getPath();
		File file=new File(path);
		try {
//			读取配置文件中的参数
			prop.load(new FileInputStream(file));
			DB_DIRVER=prop.getProperty("driver");
			DB_USERNAME=prop.getProperty("username");
			DB_PASSWORD=prop.getProperty("password");
			DB_URL=prop.getProperty("url");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
