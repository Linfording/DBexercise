package dbpoolc3p0;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import utils.DBUtils;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.mysql.jdbc.ResultSetMetaData;
/**
 * 开源数据库使用范例
 * @author linfon
 *
 */
public class TestC3P0 {
	static Connection conn=null;	
	
	public static void main(String[] args) {
		try {
//			初始化连接池
			ComboPooledDataSource cpds = new ComboPooledDataSource(); 
//			此时加载的是c3p0-config.cml/default-config
//			ComboPooledDataSource cps=new ComboPooledDataSource("config1");
//			此时加载的是c3p0-config.cml/named-config

//			配置数据库连接的基本信息
//			c3p0自带了提取配置方法，要求配置文件必须存放在类加载资源目录下(就是bin目录下，也就是SRC目录下)：
//			src,配置文件类型为XML,名字为:c3p0-config.xml;配置文件类型为properties,名字为:c3p0.properties

//			=============配置了xml则不需要下面这段了===========
//			try {
//				cpds.setDriverClass( properties.DB_DIRVER );
//			} catch (PropertyVetoException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			cpds.setJdbcUrl( properties.DB_URL );
//			cpds.setUser(properties.DB_USERNAME); 
//			cpds.setPassword(properties.DB_PASSWORD); 
			
			
//			通过连接池获取连接
			conn=cpds.getConnection();
			
			Statement stat=conn.createStatement();
			
//			ResultSet res=stat.executeQuery("select * from tb_batch");
			ResultSet res=stat.executeQuery("select * from employee");
			while (res.next()) {
				ResultSetMetaData rsm=(ResultSetMetaData) res.getMetaData();
				int row=rsm.getColumnCount();
				String str="";
				for (int i = 0; i < row; i++) {
					str+=res.getString(i+1);
				}
				System.out.println(str);
			}
			DBUtils.closeConnection(res, stat, conn);
		} catch (SQLException e) {
			e.printStackTrace();
		} 
	}


}
