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
		String nickname = "";// �������ص��ַ�������
		Connection conn = null;//�������ݿ����Ӷ���
		PreparedStatement psmt = null;//����Ԥ����������
		ResultSet rs = null;//�������ؼ��϶���
		
		try {
			conn = DBUtil.getConnection();//�������ݿ�
			String sql = "select * from admin where username = '" + username + "' and password = '" + password + "'";
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();
			if(rs.next()){
				nickname = rs.getString(2);
			}
			//�ر���ض���
			DBUtil.closeResultSet(rs);
			DBUtil.closeStatement(psmt);
			DBUtil.closeConnection(conn);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return nickname;
	}
}