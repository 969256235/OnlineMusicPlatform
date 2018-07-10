package com.ruanko.music.web;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.ruanko.music.dao.impl.MusicDaoImp;
import com.ruanko.music.model.Music;
import com.ruanko.music.model.MusicBusiModel;
import com.ruanko.music.service.MusicService;
import com.ruanko.music.utils.AppException;

public class ShowManage extends ActionSupport {
	
	private MusicBusiModel mbmd;
	private String message;
	
	public String execute() throws Exception{
		String name = ServletActionContext.getRequest().getParameter("name");//get music.name from name in jsp
		MusicService ms = new MusicService();
		try {
			//if successed to read info , u can use mbmd to get what u want
			MusicDaoImp mdi = new MusicDaoImp();
			if(ms.getMusicByContent(name, 0).size()>0){
				mbmd = ms.getMusicByContent(name, 0).get(0);				
				message = "successed to read music info";
				return SUCCESS;
			}
			else{
				message = "failed to read music info";
				return "admsearch";//return to admsearch page
			}
		} catch (AppException e) {
			e.printStackTrace();
			return ERROR;
		}
	}

	public MusicBusiModel getMbmd() {
		return mbmd;
	}

	public void setMbmd(MusicBusiModel mbmd) {
		this.mbmd = mbmd;
	}
	
}
