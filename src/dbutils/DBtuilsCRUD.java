package dbutils;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class DBtuilsCRUD {
	public static void main(String[] args) throws SQLException {
//		增
//		创建QueryRunner实例
		QueryRunner qr=new QueryRunner(new ComboPooledDataSource());
//		调用更新方法
//		int rows=qr.update("insert into user values(null,?,?)","王五","119");
//		System.out.println("影响了"+rows+"行");
//		改
//		int rows=qr.update("update user set password=? where name=?","123214","王五");
//		System.out.println("影响了"+rows+"行");
//		删
//		int rows=qr.update("delete from user where name=?","王五");
//		System.out.println("影响了"+rows+"行");
//		查
		UserBean user=qr.query("select * from user where name=? and id=?",new ResultSetHandler<UserBean>(){
//			负责将结果集转成Bean对象并返回 
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
		} ,"张三","5");
		System.out.println(user);
	}
}
