package com.ruanko.music.utils;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 学长提供的封装数据库工具
 * JDBC�����ࣨ�����࣬����JDBC�Ĺ���������
 * @author Administrator
 *
 */
public class DBHelper {
	//���峣���ַ���������ݿ����ӵ���Ϣ
//	private static final String DRIVER = "oracle.jdbc.driver.OracleDriver"; //�������·��
//	private static final String URL = "jdbc:oracle:thin:@localhost:1521:orcl";
//	private static final String USER = "scott";
//	private static final String PWD = "tiger";
//	private static Connection conn = null;
	
	private static final String DRIVER = "com.mysql.jdbc.Driver"; //�������·��
	private static final String URL = "jdbc:mysql://127.0.0.1:3306/sifangcai";
	private static final String USER = "root";
	private static final String PWD = "*****";
	private static Connection conn = null;
	
	
	/**
	 * �������
	 * @return
	 */
	public static void openConn(){
		try {
			//��������
			Class.forName(DRIVER);
			//�������������Ļ����ϻ������
			conn = DriverManager.getConnection(URL, USER, PWD);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * �ر�����
	 * @param conn
	 */
	public static void closeConn(){
		if(conn!=null){
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}
	
	/**
	 * ����SQL����в�����ֵ
	 * @param ps
	 * @param values
	 */
	public static void setParam(PreparedStatement ps,Object...values){
		//�ж�SQL������Ƿ��в�
		if(values.length>0){
			//ѭ�����ò�����ֵ
			for (int i = 0; i < values.length; i++) {
				try {
					ps.setObject(i+1, values[i]);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	
	/**
	 * ִ���� ɾ ��
	 * @param conn
	 * @param sql
	 * @param values
	 * @return
	 */
	public static int executeUpdate(String sql,Object...values){
		int count=0;
		try {
			//��дSQL���
			PreparedStatement ps = conn.prepareStatement(sql);
			//���ò�����ֵ
			setParam(ps,values);
			//ִ��SQL���
			count = ps.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return count;
	}
	
	/**
	 * ִ�в�ѯ
	 * @param conn
	 * @param sql
	 * @param values
	 * @return
	 */
	public static ResultSet executeQuery(String sql,Object...values){
		ResultSet rs = null;
		try {
			//��дSQL���
			PreparedStatement ps = conn.prepareStatement(sql);
			//���ò�����ֵ
			setParam(ps,values);
			//ִ��SQL���
			rs  = ps.executeQuery();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rs;
	}
	
}
