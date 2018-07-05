package com.ruanko.music.web;

import java.util.ArrayList;
import java.util.List;

import com.opensymphony.xwork2.ActionSupport;

public class GetRankList extends ActionSupport {
	
	private List<String> RankList;
	
	public String execute() throws Exception {
		
		RankList = new ArrayList();
		RankList.add("First");
		RankList.add("Second");
		RankList.add("Thrid");
		return SUCCESS;
	}

	public List<String> getRankList() {
		return RankList;
	}

	public void setRankList(List<String> rankList) {
		RankList = rankList;
	}

}
