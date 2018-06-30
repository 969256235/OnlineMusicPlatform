package com.ruanko.music.model;

/**
 * 用户实体类
 */
public class User {

	private int id;
	private String username;
	private boolean isAdministrator;
	
	public User()
	{
		isAdministrator = false;
	}

	//setters and getters
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

	public boolean isAdministrator() {
		return isAdministrator;
	}

	public void setAdministrator(boolean isAdministrator) {
		this.isAdministrator = isAdministrator;
	}
	
	
}
