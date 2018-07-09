package com.ruanko.music.service;

import com.ruanko.music.dao.UserDao;
import com.ruanko.music.dao.impl.UserDaoImp;
import com.ruanko.music.model.User;
import com.ruanko.music.utils.AppException;

/**
 *用户业务逻辑
 */
public class UserService {

	static UserDao user_dao = new UserDaoImp();
	
	public UserService(){
		UserService.user_dao = new UserDaoImp();
	}
	
	/**
	 * 判断账号是否存在
	 * @param user
	 * @return flag:true-已存在；false-不存在
	 * @throws AppException
	 */
	public static boolean isExist(User user) throws AppException{
		boolean flag = false;
		try{
			flag = user_dao.isExist(user.getAccount());
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
	public static boolean regist(User user) throws AppException{
		boolean flag = false;
		try{
			if(!UserService.isExist(user)){
				//System.out.println("账户未被注册");
				flag = user_dao.save(user);
				//flag=true则操作成功
				//System.out.print("注册操作flag:"+flag);
			}
		}catch(AppException e){
			throw new AppException("com.ruanko.music.service.UserService.register");
		}		
		return flag;
	}
	
	/**
	 * 尝试登录
	 * @param user
	 * @return 用户昵称
	 * @throws AppException
	 */
	public static String login(User user) throws AppException{
		String nickname = "";
		try{
			nickname = user_dao.login(user.getAccount(), user.getPassword());
		}catch(AppException e){
			throw new AppException("com.ruanko.music.service.UserService.login");
		}
		return nickname;
	}
	
}
