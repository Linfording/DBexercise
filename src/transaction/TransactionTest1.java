package transaction;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Savepoint;
import java.sql.Statement;

import utils.DBUtils;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class TransactionTest1 {
	public static void main(String[] args) {
		ComboPooledDataSource pool=new ComboPooledDataSource();
		Connection conn=null;
		PreparedStatement ps=null;
		Savepoint sp=null;
		try {
			conn = pool.getConnection();
			conn.setAutoCommit(false);
			String sqla="update user_tran set money=money-? where name=?";
			String sqlb="update user_tran set money=money+? where name=?";
			String sqlb_1="update user_tran set money=money-? where name='?'";
			String sqla_1="update user_tran set money=money+? where name='?'";
			
			ps = conn.prepareStatement(sqla);
			ps.setDouble(1, 520);
			ps.setString(2, "a");
			ps.executeUpdate();
			
			ps=conn.prepareStatement(sqlb);
			ps.setDouble(1, 520);
			ps.setString(2, "b");
			ps.executeUpdate();
			
			sp=conn.setSavepoint();
			ps=conn.prepareStatement(sqlb_1);
			ps.setDouble(1, 1040);
			ps.setString(2, "b");
			ps.executeUpdate();
			
			ps=conn.prepareStatement(sqla_1);
			ps.setDouble(1, 1100);
			ps.setString(2, "a");
			ps.executeUpdate();
			
			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			if (conn!=null) {
				try {
					if (sp!=null) {
//						提交部分转账
						conn.rollback(sp);
						conn.commit();
					}else{
//						全部回滚
						conn.rollback();
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		}finally{
			DBUtils.closeConnection(null, ps, conn);
		}
	}
}
