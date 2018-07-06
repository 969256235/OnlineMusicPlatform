package com.ruanko.music.dao;

import com.ruanko.music.utils.AppException;

public interface AdminDao {

	/**
	 * 尝试登录
	 * @param account
	 * @param password
	 * @return 管理员用户名
	 * @throws AppException
	 */
	public String login(String username, String password) throws AppException;
	
}
