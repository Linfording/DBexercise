package basic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.jdbc.ResultSetMetaData;

import utils.DBUtils;

//JDBC���h�Ĳ�
public class JDBCIntr2 {
	static String username = "root";
	static String password = "root";
	static String url = "jdbc:mysql://localhost:3306/mytest";
	static Connection conn=null;
	static Statement stat=null;
	static ResultSet rs=null;
	public static void main(String[] args) {
		create();
		String addsql="insert into employee" +
				"(id,name,gender,birthday,entry_date,job,salary,resume)" +
				"VALUES" +
				"(5,'lisi',1,'1999-1-1','2011-1-1','CEO',10000,'cowB');";
		selectall();
		add(addsql);
		selectall();
		String updatesql="update employee set name='ll' where name='lisi';";
		update(updatesql);
		selectall();
		deleteByID(5);
		selectall();
		DBUtils.closeConnection(rs,stat,conn);
	}
	
//ͨ��IDɾ������
	private static void deleteByID(int i) {
		try {
//			����sql����ȡ���
			int rows=stat.executeUpdate("delete from employee where id="+i);
			System.out.println(rows);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * ������������
	 */
	private static void selectall() {
		try {
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
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * �޸�����
	 * @param updatesql
	 */
	private static void update(String updatesql) {
		try {
//			����sql����ȡ���
			int rows=stat.executeUpdate(updatesql);
			System.out.println(rows);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * �������
	 * @param addsql
	 */
	public static void add(String addsql) {
		
		try {
//			����sql����ȡ���
			int rows=stat.executeUpdate(addsql);
			System.out.println(rows);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	private static void create() {
		conn=DBUtils.getConnection();
		try {
			stat=conn.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
