package dbpoolc3p0;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import utils.DBUtils;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.mysql.jdbc.ResultSetMetaData;
/**
 * ��Դ���ݿ�ʹ�÷���
 * @author linfon
 *
 */
public class TestC3P0 {
	static Connection conn=null;	
	
	public static void main(String[] args) {
		try {
//			��ʼ�����ӳ�
			ComboPooledDataSource cpds = new ComboPooledDataSource(); 
//			��ʱ���ص���c3p0-config.cml/default-config
//			ComboPooledDataSource cps=new ComboPooledDataSource("config1");
//			��ʱ���ص���c3p0-config.cml/named-config

//			�������ݿ����ӵĻ�����Ϣ
//			c3p0�Դ�����ȡ���÷�����Ҫ�������ļ����������������ԴĿ¼��(����binĿ¼�£�Ҳ����SRCĿ¼��)��
//			src,�����ļ�����ΪXML,����Ϊ:c3p0-config.xml;�����ļ�����Ϊproperties,����Ϊ:c3p0.properties

//			=============������xml����Ҫ���������===========
//			try {
//				cpds.setDriverClass( properties.DB_DIRVER );
//			} catch (PropertyVetoException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			cpds.setJdbcUrl( properties.DB_URL );
//			cpds.setUser(properties.DB_USERNAME); 
//			cpds.setPassword(properties.DB_PASSWORD); 
			
			
//			ͨ�����ӳػ�ȡ����
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
