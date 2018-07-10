package com.ruanko.music.web;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.ruanko.music.model.MusicBusiModel;
import com.ruanko.music.service.MusicService;
import com.ruanko.music.utils.AppException;

public class DeleteMusic extends ActionSupport {
	
	private MusicBusiModel mbmd;
	private String message;
	private String name;
	
	public MusicBusiModel getMbmd() {
		return mbmd;
	}

	public void setMbmd(MusicBusiModel mbmd) {
		this.mbmd = mbmd;
	}

	public String execute() throws Exception {
		mbmd = new MusicBusiModel();
		name = ServletActionContext.getRequest().getParameter("name");
		System.out.println(name);
		MusicService ms = new MusicService();
		try {
			
			if(ms.getMusicByContent(name, 0).size()>0){
				mbmd = ms.getMusicByContent(name, 0).get(0);
				ms.deleteMusicById(Integer.toString(mbmd.getId()));
				message = "delete success";
				return SUCCESS;
			}
			else{
				message = "failed to delete";
				return "admsearch";//return to admsearch page
			}
		} catch (AppException e) {
			message = "system error";
			e.printStackTrace();
			return ERROR;//return to error page
		}
		
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
