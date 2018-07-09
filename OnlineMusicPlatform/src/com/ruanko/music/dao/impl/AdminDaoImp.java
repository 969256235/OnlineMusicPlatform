package com.ruanko.music.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.ruanko.music.dao.AdminDao;
import com.ruanko.music.utils.AppException;
import com.ruanko.music.utils.DBUtil;

public class AdminDaoImp implements AdminDao{

	@Override
	public String login(String username, String password) throws AppException {
		String nickname = "";// 声明返回的字符串对象
		Connection conn = null;//声明数据库连接对象
		PreparedStatement psmt = null;//声明预处理语句对象
		ResultSet rs = null;//声明返回集合对象
		
		try {
			conn = DBUtil.getConnection();//连接数据库
			String sql = "select * from admin where username = '" + username + "' and password = '" + password + "'";
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();
			if(rs.next()){
				nickname = rs.getString(2);
			}
			//关闭相关对象
			DBUtil.closeResultSet(rs);
			DBUtil.closeStatement(psmt);
			DBUtil.closeConnection(conn);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return nickname;
	}
}