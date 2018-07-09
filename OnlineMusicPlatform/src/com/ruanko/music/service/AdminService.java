package com.ruanko.music.service;

import com.ruanko.music.dao.AdminDao;
import com.ruanko.music.dao.impl.AdminDaoImp;
import com.ruanko.music.model.Admin;
import com.ruanko.music.utils.AppException;

public class AdminService {

	static AdminDao admin_dao = new AdminDaoImp();
	
	public AdminService(){
		AdminService.admin_dao = new AdminDaoImp();
	}
	
	public static String login(Admin admin) throws AppException{
		String username = "";
		try{
			username = admin_dao.login(admin.getUsername(), admin.getPassword());
		}catch(AppException e){
			throw new AppException("com.ruanko.music.service.AdminService.login");
		}
		return username;		
	}
}
