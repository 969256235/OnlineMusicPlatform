package com.ruanko.music.web;

import javax.servlet.ServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.ruanko.music.model.Admin;
import com.ruanko.music.model.User;
import com.ruanko.music.service.AdminService;
import com.ruanko.music.service.UserService;

public class Signin extends ActionSupport {

	private User user;
	private String message;//登录信息
	
	public String execute() throws Exception {

		String account = ServletActionContext.getRequest().getParameter("username");
		String password = ServletActionContext.getRequest().getParameter("password");

		
		String value = ServletActionContext.getRequest().getParameter("box");
		//����box��ֵ�ж��ǹ���Ա��¼�����û���¼
		if(value.equals("admin")){
			AdminService as = new AdminService();
			Admin adm = new Admin();
			adm.setUsername(account);
			adm.setPassword(password);
			adm.setUsername(as.login(adm));
			if(!adm.getUsername().equals("")){
				message = "admin signed in successfully";
				return "Adm";
			}
			message = "admin failed to signin";
			return ERROR;
		}
		else{
			UserService us = new UserService();
			user = new User(); //
			user.setAccount(account);
			user.setPassword(password);
			user.setNickname(us.login(user));
			if(!user.getNickname().equals("")){
				message = "登录成功";
				return SUCCESS;
			}
			else{
				message = "登录失败";
				return ERROR;
			}

		}
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}
