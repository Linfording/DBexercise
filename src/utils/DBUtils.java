package utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;



public class DBUtils {
	static String username ;
	static String password ;
	static String url ;
	static String driver;
	private static Connection conn = null;
	private static DBUtils db = null;

	/*// 通过静态代码块注册数据库驱动，保证注册只执行一次
	static {
		try {
			// 注册驱动有如下方式：
			// 1.通过驱动管理器注册驱动，但会注册两次，程序会和具体的数据库驱动绑死在一起，会对类产生依赖。如果该类不存在那就报错了。
			// DriverManager.registerDriver(new com.mysql.jdbc.Driver());
			// 2.与3类似
			// System.setProperty("jdbc.drivers","com.mysql.jdbc.Driver");
			// 3.驱动只会注册一次;程序没有和具体的数据库驱动绑死，绑死的仅仅是驱动的全路径名字符串，后期可提取到配置文件
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	private DBUtils() {
		try {
			conn = DriverManager.getConnection(url, username, password);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	 public static DBUtils getInitJDBCUtil() {
	        if (db == null) {
	            // 给类加锁 防止线程并发
	            synchronized (DBUtils.class) {
	                if (db == null) {
	                	db = new DBUtils();
	                }
	            }
	        }
	        return db;
	    }
	 // 获得连接
	    public Connection getConnection() {
	        try {
	            conn = DriverManager.getConnection(url,username,password);
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return conn;
	 
	    }*/
	 
	private static Properties prop=new Properties();
	private DBUtils() {}
	static {
//		类加载器
		ClassLoader classLoader=DBUtils.class.getClassLoader();
//		String path="./config.properties";
		String path=classLoader.getResource("./config.properties").getPath();
		File file=new File(path);
		try {
//			读取配置文件中的参数
			prop.load(new FileInputStream(file));
			driver=prop.getProperty("driver");
			username=prop.getProperty("username");
			password=prop.getProperty("password");
			url=prop.getProperty("url");
//			注册驱动
			Class.forName(driver);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
//		细节：
//		1.由于读取配置文件和注册驱动都只需要做一次，所有都放在静态代码块中
//		2.使用类加载器获取配置文件的路径，原因是发布项目时目录位置会有改动
		
	}
//	获取连接
	public static Connection getConnection(){
		 try {
			 if (conn==null) {
				 conn = DriverManager.getConnection(url,username,password);
			}
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return conn;
	}
	
	// 关闭连接
	    public static void closeConnection(ResultSet rs, Statement statement, Connection con) {
	        try {
	            if (rs != null) {
	                rs.close();
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        } finally {
	            try {
	                if (statement != null) {
	                    statement.close();
	                }
	            } catch (Exception e) {
	                e.printStackTrace();
	            } finally {
	                try {
	                    if (con != null) {
	                        con.close();
	                    }
	                } catch (SQLException e) {
	                    e.printStackTrace();
	                }
	            }
	        }
	    }

}
