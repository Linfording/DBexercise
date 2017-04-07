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
		System.out.println("请输入用户名");
		String user=sc.nextLine();
		System.out.println("请输入密码");
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
					System.out.println("确认登录");
				}else {
					System.out.println("密码错误");
				}
			}else {
				System.out.println("没有该用户");
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
					System.out.println("确认登录");
				}else {
					System.out.println("密码错误");
				}
			}else {
				System.out.println("没有该用户");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
