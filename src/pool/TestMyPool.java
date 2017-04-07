package pool;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.jdbc.ResultSetMetaData;

public class TestMyPool {
	static Connection conn=null;
	static Statement stat=null;
	static ResultSet rs=null;
	static MyDBConnectionPool pool=null;
	public static void main(String[] args) {
//		测试连接池
//		创建连接池对象
		pool=new MyDBConnectionPool();
		
		try {
			conn=pool.getConnection();
			stat=conn.createStatement();
			rs=stat.executeQuery("select * from employee");
			ResultSetMetaData rsm=(ResultSetMetaData) rs.getMetaData();
			int column=rsm.getColumnCount();
			while (rs.next()) {
				String str="";
				for (int i = 0; i < column; i++) {
					str+=rs.getString(i+1);
				}
				System.out.println(str);
			}
			System.out.println("=============");
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			cloaseConnection();
		}
	}
	private static void cloaseConnection() {
		if (rs !=null) {
			 try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}finally{
					rs =null;
				}
		}     
		if (stat !=null) {
			try {
				stat.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}finally{
				stat =null;
			}
		}
		if (conn !=null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}finally{
				conn =null;
			}
		}
	}
}
