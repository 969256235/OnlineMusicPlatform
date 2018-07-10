package com.ruanko.music.web;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.ruanko.music.dao.impl.MusicDaoImp;
import com.ruanko.music.model.MusicBusiModel;
import com.ruanko.music.service.MusicService;
import com.ruanko.music.utils.AppException;

public class ManageMusic extends ActionSupport {
	private String message;
	private MusicBusiModel mbml;
	//alter music information
	public String execute() throws Exception{
		
		String id = ServletActionContext.getRequest().getParameter("id");
		MusicService ms = new MusicService();
		try {
			mbml = ms.getMusicById(id);
			String name = ServletActionContext.getRequest().getParameter("name");
			String realname = ServletActionContext.getRequest().getParameter("realname");
			String artist = ServletActionContext.getRequest().getParameter("artist");
			String album = ServletActionContext.getRequest().getParameter("album"); 
			String zone = ServletActionContext.getRequest().getParameter("zone"); 
			String publishdate = ServletActionContext.getRequest().getParameter("publishdate"); 
			mbml.setName(name);
			mbml.setRealname(realname);
			mbml.setArtist(artist);
			mbml.setAlbum(album);
			mbml.setZone(zone);
			mbml.setPublishdate(publishdate);
			ms.resetMusic(mbml);
			
			message = "alter success";
			return SUCCESS;
		} catch (AppException e) {
			e.printStackTrace();
			return ERROR;
		}
	}
}
