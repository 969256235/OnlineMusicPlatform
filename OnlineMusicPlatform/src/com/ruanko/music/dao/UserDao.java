package com.ruanko.music.dao;

import com.ruanko.music.model.User;
import com.ruanko.music.utils.AppException;

/**
 * 用户数据访问层接口
 */
public interface UserDao {

	/**
	 * 查询账号是否存在
	 * @param account
	 * @return true-已存在；false-不存在
	 * @throws AppException
	 */
	public boolean isExist(String account) throws AppException;
	
	/**
	 * 保存用户信息
	 * @param user
	 * @return true-保存成功；false-保存失败
	 * @throws AppException
	 */
	public boolean save(User user) throws AppException;
	
}
