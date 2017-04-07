package basic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;

import utils.DBUtils;

public class PsBatch {
	static Connection con=null;
	static Statement stat=null;
	static PreparedStatement preStat=null;
	public static void main(String[] args) {
		con=DBUtils.getConnection();
		try {
//			���ر�ʱ��ʹ��ʱ��14��࣬�ر��Զ��ύ�����0.119��
			con.setAutoCommit(false);
			long begin =System.currentTimeMillis();
			preStat=con.prepareStatement("insert into tb_batch values(null,?);");
			for (int i = 0; i < 2000; i++) {
				preStat.setString(1, "test"+i);
				preStat.addBatch();
			}
			preStat.executeBatch();
//			�ر�֮����Ҫ�Լ��ֶ��ύ�����ݿ⣬���򲻻������ݿ���Ч
			con.commit();
			long end=System.currentTimeMillis();
			System.out.println("ʹ��ʱ��"+(end-begin)/1000.0+"��");
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			DBUtils.closeConnection(null, preStat, con);
		}
		
	}
}
