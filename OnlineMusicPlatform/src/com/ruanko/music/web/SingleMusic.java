package com.ruanko.music.web;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

public class SingleMusic extends ActionSupport {
	
	private String music;

	public String execute() throws Exception {
		
		music = ServletActionContext.getRequest().getParameter("musicname");
		
		return SUCCESS;
	}
	
	public String getMusic() {
		return music;
	}

	public void setMusic(String music) {
		this.music = music;
	}
	

}
