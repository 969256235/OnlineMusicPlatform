package com.ruanko.music.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.ruanko.music.dao.UserDao;
import com.ruanko.music.model.User;
import com.ruanko.music.utils.AppException;
import com.ruanko.music.utils.DBUtil;

/**
 * 用户数据访问层实现类
 */
public class UserDaoImp implements UserDao{

	@Override
	public boolean isExist(String account) throws AppException {
		//1.声明flag
		boolean flag = false;
		//2.声明数据库访问的对象
		Connection conn = null;//连接声明
		PreparedStatement psmt = null;//预处理语句声明
		ResultSet rs = null;//返回结果结合声明
		
		//3.获取数据库连接
		conn = DBUtil.getConnection();
		//4.声明查询sql语句
		String sql = "select usr_id from user where account = ?";
		try {
			psmt = conn.prepareStatement(sql);//预编译sql
			psmt.setString(1, account);//占位符设置
			rs = psmt.executeQuery();//执行sql语句
			//flag=true表明账号已被注册
			if(rs.next()){
				flag = true;
				System.out.println("该账号已经被注册");
			}
			else
				System.out.println("该账号未被注册");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return flag;
	}

	@Override
	public boolean save(User user) throws AppException {
		//1.声明flag
		boolean flag = false;
		//2.声明数据库访问的对象
		Connection conn = null;
		PreparedStatement psmt = null;
		
		try{
			//3.获取数据库连接
			conn = DBUtil.getConnection();
			System.out.println("UserDaoImp.save()获取数据库连接成功");
			//4.声明sql语句
			String sql = "insert into user(account,password,nickname,gender) values(?,?,?,?)";
			//5.预处理sql
			psmt = conn.prepareStatement(sql);
			System.out.println("sql语句预处理成功");
			//6.为参数设置值
			psmt.setString(1, user.getAccount());
			psmt.setString(2, user.getPassword());
			psmt.setString(3, user.getNickname());
			psmt.setString(4, user.getGender());
			//7.执行新增操作
			int result = psmt.executeUpdate();
			System.out.println("本次操作影响"+result+"行");
			//8.获取操作结果，设置flag
			if (result > 0) {
				flag= true;
			}
		} catch(SQLException e){
			//9.异常处理
				e.printStackTrace();
		} finally {
			//10.关闭数据库连接
			try {
				DBUtil.closeStatement(psmt);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				DBUtil.closeConnection(conn);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		//11.返回操作标志
		return flag;
	}

	@Override
	public String login(String account, String password) throws AppException {
		// TODO Auto-generated method stub
		return null;
	}

}
