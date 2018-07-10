package com.ruanko.music.web;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.ruanko.music.model.User;
import com.ruanko.music.service.UserService;
import com.ruanko.music.utils.AppException;
//注册Action
public class Signup extends ActionSupport {

	private String message; //提示信息
	private User user;
	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String execute(){
		UserService us = new UserService();//声明用户服务对象
		//将用户信息保存到User中
		String account = ServletActionContext.getRequest().getParameter("username");//获取账号
		String password = ServletActionContext.getRequest().getParameter("password");//获取密码
		String nickname = ServletActionContext.getRequest().getParameter("nickname");//获取昵称
		String password2 = ServletActionContext.getRequest().getParameter("password2");//获取密码2与密码做比较
		
		user = new User();
		user.setAccount(account);
		user.setNickname(nickname);
		user.setPassword(password);
		
		try{
			if(password.equals(password2)){
				if(us.regist(user)){
					message = "注册成功";
					return SUCCESS;
				}
				else {
					message = "注册失败,账户已经存在";
					return "Signup";
				}
			}
			else {
				message = "两次输入的密码不同";
				return "Signup";
			}
		} catch(AppException e) {
			e.printStackTrace();
			message = "注册时系统异常";
			return ERROR;
		}
	}
	
}
