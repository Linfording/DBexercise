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
//			不关闭时，使用时间14秒多，关闭自动提交事务后0.119秒
			con.setAutoCommit(false);
			long begin =System.currentTimeMillis();
			preStat=con.prepareStatement("insert into tb_batch values(null,?);");
			for (int i = 0; i < 2000; i++) {
				preStat.setString(1, "test"+i);
				preStat.addBatch();
			}
			preStat.executeBatch();
//			关闭之后需要自己手动提交到数据库，否则不会在数据库生效
			con.commit();
			long end=System.currentTimeMillis();
			System.out.println("使用时间"+(end-begin)/1000.0+"秒");
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			DBUtils.closeConnection(null, preStat, con);
		}
		
	}
}
