package com.ruanko.music.service;

import com.ruanko.music.dao.UserDao;
import com.ruanko.music.dao.impl.UserDaoImp;
import com.ruanko.music.model.User;
import com.ruanko.music.utils.AppException;

/**
 *用户业务逻辑
 */
public class UserService {

	private UserDao user_dao = null;
	
	public UserService(){
		user_dao = new UserDaoImp();
	}
	
	/**
	 * 判断账号是否存在
	 * @param user
	 * @return flag:true-已存在；false-不存在
	 * @throws AppException
	 */
	public boolean isExist(User user) throws AppException{
		boolean flag = false;
		try{
			flag = this.user_dao.isExist(user.getAccount());
		}catch(AppException e){
			throw new AppException("com.ruanko.music.service.UserService.isExist");
		}
		return flag;
	}
	
	/**
	 * 注册业务逻辑
	 * @param 封装的User类实例
	 * @return flag:true-注册成功；false-注册失败
	 * @throws AppException
	 */
	public boolean register(User user) throws AppException{
		boolean flag = false;
		try{
			if(!this.isExist(user)){
				flag = user_dao.save(user);
			}
		}catch(AppException e){
			throw new AppException("com.ruanko.music.service.UserService.register");
		}		
		return flag;
	}
		
}
