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
//		1.ע�����������·�ʽ��
//      1.ͨ������������ע������������ע�����Σ����һ�������������������಻�����Ǿͱ����ˡ�
//      DriverManager.registerDriver(new com.mysql.jdbc.Driver());
//      2.��3����
//      System.setProperty("jdbc.drivers","com.mysql.jdbc.Driver");
		Connection conn=null;
		 Statement stat=null;
		 ResultSet res=null;
		try {
			Class.forName("com.mysql.jdbc.Driver");// �Ƽ�ʹ�÷�ʽ
//		2.��ȡ���ݿ�����
			conn=DriverManager.getConnection(url,username,password);
//		3.��ȡ������
			stat=conn.createStatement();
//		4.���ô���������sql��䵽���ݿ�ִ��, ����ִ�еĽ��
			String sql="select * from employee";
			res= stat.executeQuery(sql);
//		5.���������
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
//		6.�ͷ���Դ
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
