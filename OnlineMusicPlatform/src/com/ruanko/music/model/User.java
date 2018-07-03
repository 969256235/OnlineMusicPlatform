package com.ruanko.music.model;

import java.util.Date;

/**
 * 用户基础实体类
 */
public class User {

	private int id;  //主键id
	
	private String account;   //账号
	private String password;  //密码
	private String nickname;  //昵称
	private String gender;    //性别
	
	public User(){
		this.id = 0;
		this.account = "";
		this.password = "";
		this.nickname = "";
		this.gender = "";
	}

	//Generated setters and getters
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

}
