package basic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import utils.DBUtils;
//drop database if exists mydb2;
//create database mydb2;
//use mydb2;
//create table tb_batch(id int primary key auto_increment, name varchar(20));
//insert into tb_batch values(null,'a');
//insert into tb_batch values(null,'b');
//insert into tb_batch values(null,'c');
//insert into tb_batch values(null,'d');
public class StatmentBatch {
	static Connection con=null;
	static Statement stat=null;
	static PreparedStatement preStat=null;
	
	public static void main(String[] args) {
		con=DBUtils.getConnection();
		try {
			stat = con.createStatement();
			stat.addBatch("drop database if exists mydb2;");
			stat.addBatch("create database mydb2;");
			stat.addBatch("use mydb2;");
			stat.addBatch("create table tb_batch(id int primary key auto_increment, name varchar(20));");
			stat.addBatch("insert into tb_batch values(null,'a');");
			stat.addBatch("insert into tb_batch values(null,'b');");
			stat.addBatch("insert into tb_batch values(null,'c');");
			stat.addBatch("insert into tb_batch values(null,'d');");
			stat.executeBatch();
			System.out.println("Ö´ÐÐÍê³É");
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DBUtils.closeConnection(null, stat, con);
		}
	}
}
