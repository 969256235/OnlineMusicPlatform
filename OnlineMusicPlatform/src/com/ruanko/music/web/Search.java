package com.ruanko.music.web;

import java.util.ArrayList;
import java.util.List;

import com.opensymphony.xwork2.ActionSupport;

public class Search extends ActionSupport {
	
	private List<String> SearchList;
	
	public String execute() throws Exception {
		SearchList = new ArrayList<String>();
		SearchList.add("Fitst");
		SearchList.add("Second");
		SearchList.add("Third");
		return SUCCESS;
		
	}

	public List<String> getSearchList() {
		return SearchList;
	}

	public void setSearchList(List<String> searchList) {
		SearchList = searchList;
	}

}
