package pool;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Logger;

import javax.sql.DataSource;

public class MyDBConnectionPool implements DataSource{
	static String username="root";
	static String password="root";
	static String url="jdbc:mysql://localhost:3306/mytest";
	
	
//	保存Conn的list，LinkedList便于增删
	private static List<Connection> list=new LinkedList<Connection>();
	
	static{
//		往连接池中初始化一批连接
		try {
			Class.forName("com.mysql.jdbc.Driver");
			for (int i = 0; i < 5; i++) {
				Connection conn=DriverManager.getConnection(url,username,password);
				list.add(conn);
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	/**
	 * 	获取连接
	 */	
	@Override
	public Connection getConnection() throws SQLException {
		if (list.isEmpty()) {
			for (int i = 0; i < 3; i++) {
				Connection conn=DriverManager.getConnection(url,username,password);
				list.add(conn);
			}
		}
		Connection conn=list.remove(0);
		
		//对连接对象进行包装, 将包装后的对象返回
		Connection connDecorate = new ConnectionDecorator(conn, this);
		System.out.println("成功获取一个连接,剩余连接:"+list.size());
		return connDecorate;
	}
	
	/**
	 * 还连接
	 * @param conn
	 */
	public void returnConn(Connection conn){
		try {
			if (conn!=null && !conn.isClosed()) {
				list.add(conn);
				System.out.println("成功还回一个连接,剩余连接:"+list.size());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	@Override
	public PrintWriter getLogWriter() throws SQLException {
		return null;
	}

	@Override
	public void setLogWriter(PrintWriter out) throws SQLException {
		
	}

	@Override
	public void setLoginTimeout(int seconds) throws SQLException {
		
	}

	@Override
	public int getLoginTimeout() throws SQLException {
		return 0;
	}

	@Override
	public Logger getParentLogger() throws SQLFeatureNotSupportedException {
		return null;
	}

	@Override
	public <T> T unwrap(Class<T> iface) throws SQLException {
		return null;
	}

	@Override
	public boolean isWrapperFor(Class<?> iface) throws SQLException {
		return false;
	}


	@Override
	public Connection getConnection(String username, String password)
			throws SQLException {
		return null;
	}

}
