package basic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;



import utils.DBUtils;

public class registerUser {
	static Connection con=null;
	static Statement stat=null;
	static PreparedStatement preStat=null;
	
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		System.out.println("�������û���");
		String user=sc.nextLine();
		System.out.println("����������");
		String pas=sc.nextLine();
//		login(user,pas);
		loginByPreparedStatement(user,pas);
	}

	private static void loginByPreparedStatement(String user, String pas) {
		con=DBUtils.getConnection();
		try {
			String sql ="select * from user where name=? and password=?";
			preStat=con.prepareStatement(sql);
			preStat.setString(1, user);
			preStat.setString(2, pas);
			ResultSet rs=preStat.executeQuery();
			if (rs.next()) {
				if (rs.getString("password").equals(pas)) {
					System.out.println("ȷ�ϵ�¼");
				}else {
					System.out.println("�������");
				}
			}else {
				System.out.println("û�и��û�");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private static void login(String user, String pas) {
		con=DBUtils.getConnection();
		try {
			stat=con.createStatement();
			String sql="select * from user where name='"+user+"'";
			System.out.println(sql);
			ResultSet rs=stat.executeQuery(sql);
			if (rs.next()) {
				if (rs.getString("password").equals(pas)) {
					System.out.println("ȷ�ϵ�¼");
				}else {
					System.out.println("�������");
				}
			}else {
				System.out.println("û�и��û�");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
