package com.ruanko.music.web;

import java.util.List;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.ruanko.music.model.MusicBusiModel;
import com.ruanko.music.service.MusicService;

public class AdmSearch extends ActionSupport {
	
	private List<MusicBusiModel> SearchList;
	private int resultlength;
	
	public String execute() throws Exception {
		
		String str = ServletActionContext.getRequest().getParameter("SearchText");
		
		MusicService ms = new MusicService();
		
		SearchList = ms.getMusicByContent(str, 3);
		
		resultlength = 100;
		return SUCCESS;
		
	}

	public List<MusicBusiModel> getSearchList() {
		return SearchList;
	}

	public void setSearchList(List<MusicBusiModel> searchList) {
		SearchList = searchList;
	}

	public int getResultlength() {
		return resultlength;
	}

	public void setResultlength(int resultlength) {
		this.resultlength = resultlength;
	}

	
}
