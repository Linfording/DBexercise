package dbutils;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ArrayListHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class DbUtilsTest {
	public static void main(String[] args) throws SQLException {
		QueryRunner qr=new QueryRunner(new ComboPooledDataSource());
//		beanhandler(class<T>)通过class对象来知道User里有什么参数
//		UserBean user=qr.query("select * from user where  id=?", new BeanHandler<UserBean>(UserBean.class),"5");
//		System.out.println(user);
		
		List<UserBean> users=qr.query("select * from user", new BeanListHandler<UserBean>(UserBean.class));
		System.out.println(users);
	}
}
