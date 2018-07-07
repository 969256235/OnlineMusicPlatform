package com.ruanko.music.web;

import java.util.ArrayList;
import java.util.List;

import com.opensymphony.xwork2.ActionSupport;
import com.ruanko.music.model.MusicBusiModel;

public class GetRankList extends ActionSupport {
	
	private List<List<MusicBusiModel>> RankList;
	
	public String execute() throws Exception {
		
		RankList = new ArrayList();
		
		MusicBusiModel first = new MusicBusiModel();
		first.setAttributes(0, "First", "201807021545.mp3", "", "", "", "", "", null, 0, 0, "", "", "");
		
		MusicBusiModel second = new MusicBusiModel();
		second.setAttributes(0, "Second", "201807060919.mp3", "", "", "", "", "", null, 0, 0, "", "", "");
		
		MusicBusiModel third = new MusicBusiModel();
		third.setAttributes(0, "Third", "201807021545.mp3", "", "", "", "", "", null, 0, 0, "", "", "");
		
		List<MusicBusiModel> line = new ArrayList();
		line.add(first);
		line.add(second);
		line.add(third);
		
		RankList.add(line);
		
		return SUCCESS;
	}

	public List<List<MusicBusiModel>> getRankList() {
		return RankList;
	}

	public void setRankList(List<List<MusicBusiModel>> rankList) {
		RankList = rankList;
	}

}
