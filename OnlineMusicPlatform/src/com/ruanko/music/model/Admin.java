package com.ruanko.music.model;

/**
 * 管理员基础实体类
 */
public class Admin {

	private int id; //主键id
	
	private String username;  //用户名
	private String password;  //密码
	
	public Admin(){
		this.id = 0;
		this.username = "";
		this.password = "";
	}

	//Generated setters and getters
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
}
