package dbutils;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class DBtuilsCRUD {
	public static void main(String[] args) throws SQLException {
//		��
//		����QueryRunnerʵ��
		QueryRunner qr=new QueryRunner(new ComboPooledDataSource());
//		���ø��·���
//		int rows=qr.update("insert into user values(null,?,?)","����","119");
//		System.out.println("Ӱ����"+rows+"��");
//		��
//		int rows=qr.update("update user set password=? where name=?","123214","����");
//		System.out.println("Ӱ����"+rows+"��");
//		ɾ
//		int rows=qr.update("delete from user where name=?","����");
//		System.out.println("Ӱ����"+rows+"��");
//		��
		UserBean user=qr.query("select * from user where name=? and id=?",new ResultSetHandler<UserBean>(){
//			���𽫽����ת��Bean���󲢷��� 
			@Override
			public UserBean handle(ResultSet arg0) throws SQLException {
				UserBean user=null;
				if (arg0.next()) {
					user=new UserBean();
					user.setId(arg0.getInt(1)+"");
					user.setName(arg0.getString(2));
					user.setPassword(arg0.getString(3));
				}
				return user;
			}
		} ,"����","5");
		System.out.println(user);
	}
}
