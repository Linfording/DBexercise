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

	/*// ͨ����̬�����ע�����ݿ���������֤ע��ִֻ��һ��
	static {
		try {
			// ע�����������·�ʽ��
			// 1.ͨ������������ע������������ע�����Σ������;�������ݿ�����������һ�𣬻�������������������಻�����Ǿͱ����ˡ�
			// DriverManager.registerDriver(new com.mysql.jdbc.Driver());
			// 2.��3����
			// System.setProperty("jdbc.drivers","com.mysql.jdbc.Driver");
			// 3.����ֻ��ע��һ��;����û�к;�������ݿ����������������Ľ�����������ȫ·�����ַ��������ڿ���ȡ�������ļ�
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
	            // ������� ��ֹ�̲߳���
	            synchronized (DBUtils.class) {
	                if (db == null) {
	                	db = new DBUtils();
	                }
	            }
	        }
	        return db;
	    }
	 // �������
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
//		�������
		ClassLoader classLoader=DBUtils.class.getClassLoader();
//		String path="./config.properties";
		String path=classLoader.getResource("./config.properties").getPath();
		File file=new File(path);
		try {
//			��ȡ�����ļ��еĲ���
			prop.load(new FileInputStream(file));
			driver=prop.getProperty("driver");
			username=prop.getProperty("username");
			password=prop.getProperty("password");
			url=prop.getProperty("url");
//			ע������
			Class.forName(driver);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
//		ϸ�ڣ�
//		1.���ڶ�ȡ�����ļ���ע��������ֻ��Ҫ��һ�Σ����ж����ھ�̬�������
//		2.ʹ�����������ȡ�����ļ���·����ԭ���Ƿ�����ĿʱĿ¼λ�û��иĶ�
		
	}
//	��ȡ����
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
	
	// �ر�����
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
