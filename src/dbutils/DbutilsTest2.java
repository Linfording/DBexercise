package dbutils;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class DbutilsTest2 {
	public static void main(String[] args) throws SQLException {
		ComboPooledDataSource dataSource=new ComboPooledDataSource();
//	ScalarHandler()
		QueryRunner qr=new QueryRunner(dataSource);
		Object obj=qr.query("select count(*) from user", new ScalarHandler());
		System.out.println(obj);
		
//		BeanHandler
		UserBean user=qr.query("select * from user where  id=?", new BeanHandler<UserBean>(UserBean.class),"5");
		System.out.println(user);
		
//		BeanListHandler
		List<UserBean> users=qr.query("select * from user", new BeanListHandler<UserBean>(UserBean.class));
		System.out.println(users);
		
	}
}
