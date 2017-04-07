package basic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.jdbc.ResultSetMetaData;

public class JDBCIntroducation {
	static String username="root";
	static String password="root";
	static String url="jdbc:mysql://localhost:3306/mytest";
	public static void main(String[] args) {
//		1.注册驱动有如下方式：
//      1.通过驱动管理器注册驱动，但会注册两次，并且会对类产生依赖。如果该类不存在那就报错了。
//      DriverManager.registerDriver(new com.mysql.jdbc.Driver());
//      2.与3类似
//      System.setProperty("jdbc.drivers","com.mysql.jdbc.Driver");
		Connection conn=null;
		 Statement stat=null;
		 ResultSet res=null;
		try {
			Class.forName("com.mysql.jdbc.Driver");// 推荐使用方式
//		2.获取数据库连接
			conn=DriverManager.getConnection(url,username,password);
//		3.获取传输器
			stat=conn.createStatement();
//		4.利用传输器发送sql语句到数据库执行, 返回执行的结果
			String sql="select * from employee";
			res= stat.executeQuery(sql);
//		5.遍历结果集
			while (res.next()) {
				ResultSetMetaData rsm=(ResultSetMetaData) res.getMetaData();
				int row=rsm.getColumnCount();
				String str="";
				for (int i = 0; i < row; i++) {
					str+=res.getString(i+1);
				}
				System.out.println(str);
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}finally{
//		6.释放资源
			if (res !=null) {
				 try {
						res.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}finally{
						res =null;
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

}
