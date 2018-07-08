package com.ruanko.music.web;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.ruanko.music.model.MusicBusiModel;
import com.ruanko.music.service.MusicService;

public class SingleMusic extends ActionSupport {
	
	private MusicBusiModel music;

	public String execute() throws Exception {
		
		String str = ServletActionContext.getRequest().getParameter("musicname");
		
		MusicService ms = new MusicService();
		
		music = ms.getMusicByContent(str, 0).get(0);
		
		return SUCCESS;
	}

	public MusicBusiModel getMusic() {
		return music;
	}

	public void setMusic(MusicBusiModel music) {
		this.music = music;
	}
	
}
