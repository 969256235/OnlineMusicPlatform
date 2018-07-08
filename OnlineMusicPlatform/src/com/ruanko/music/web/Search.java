package com.ruanko.music.web;

import java.util.ArrayList;
import java.util.List;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

public class Search extends ActionSupport {
	
	private List<String> SearchList;
	private int resultlength;
	
	public String execute() throws Exception {
		SearchList = new ArrayList<String>();
		String str = ServletActionContext.getRequest().getParameter("SearchText");
		SearchList.add(str);
		SearchList.add("Second");
		SearchList.add("Third");
		resultlength = 100;
		return SUCCESS;
		
	}

	public List<String> getSearchList() {
		return SearchList;
	}

	public int getResultlength() {
		return resultlength;
	}

	public void setResultlength(int resultlength) {
		this.resultlength = resultlength;
	}

	public void setSearchList(List<String> searchList) {
		SearchList = searchList;
	}

}
