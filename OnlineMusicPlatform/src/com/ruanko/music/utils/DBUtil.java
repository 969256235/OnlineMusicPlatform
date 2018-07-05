package com.ruanko.music.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * 管理数据库连接和关闭
 */
/**
 * @author JP
 *
 */
public class DBUtil {
	//数据库连接字符串
	//格式： 数据库协议://<数据库服务器地址>[<:端口号>]/<数据库名称>?字符设置
	static private String url = "jdbc:mysql://localhost:3306/music?useUnicode=true&amp;"+"characterEncoding=utf8&useSSL=false";
	//数据库用户名
	static private String user = "root";
	//数据库密码
	static private String password = "130513";
	
	//静态代码块，实现驱动加载，仅在该类呗加载时执行一次}
	static {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//创建连接
	public static Connection getConnection(){
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(url, user, password);
			System.out.print("connect successed!");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return conn;
	}
	
	//xxx关闭操作，判断均为xxx！=null且!xxx.isClosed()
	public static void closeConnection(Connection conn) throws SQLException{
		if( (conn != null) && (!conn.isClosed()) ){
			conn.close();
			conn = null;
		}
	}
	
	public static void closeStatement(Statement st) throws SQLException{
		if( (st != null) && (!st.isClosed()) ){
			st.close();
			st = null;
		}
	}
	
	public static void closeResultSet(ResultSet rs) throws SQLException{
		if( (rs != null) && (!rs.isClosed()) ){
			rs.close();
			rs = null;
		}
	}

}
