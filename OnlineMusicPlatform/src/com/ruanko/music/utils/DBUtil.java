package com.ruanko.music.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * 绠＄悊鏁版嵁搴撹繛鎺ュ拰鍏抽棴
 */
/**
 * @author JP
 *
 */
public class DBUtil {
	//鏁版嵁搴撹繛鎺ュ瓧绗︿覆
	//鏍煎紡锛� 鏁版嵁搴撳崗璁�://<鏁版嵁搴撴湇鍔″櫒鍦板潃>[<:绔彛鍙�>]/<鏁版嵁搴撳悕绉�>?瀛楃璁剧疆
	static private String url = "jdbc:mysql://localhost:3306/music?useUnicode=true&amp;"+"characterEncoding=utf8&useSSL=false";
	//鏁版嵁搴撶敤鎴峰悕
	static private String user = "root";
	//鏁版嵁搴撳瘑鐮�
	static private String password = "123456";
	
	//闈欐�佷唬鐮佸潡锛屽疄鐜伴┍鍔ㄥ姞杞斤紝浠呭湪璇ョ被鍛楀姞杞芥椂鎵ц涓�娆
	static {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//鍒涘缓杩炴帴
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
	
	//xxx鍏抽棴鎿嶄綔锛屽垽鏂潎涓簒xx锛�=null涓�!xxx.isClosed()
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
