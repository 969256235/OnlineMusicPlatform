package com.ruanko.music.service;

import java.util.ArrayList;
import java.util.List;

import com.ruanko.music.dao.MusicDao;
import com.ruanko.music.dao.impl.MusicDaoImp;
import com.ruanko.music.model.MusicBusiModel;
import com.ruanko.music.utils.AppException;

/**
 * 音乐业务逻辑
 */
public class MusicService {

	private MusicDao music_dao;
	
	public MusicService(){
		this.music_dao = new MusicDaoImp(); 
	}
	
	/**
	 * 获取热度最高的十首歌
	 * @return 包含十个MusicBusiModel的List
	 * @throws AppException
	 */
	public ArrayList<MusicBusiModel> getMostPopMusic() throws AppException{
		ArrayList<MusicBusiModel> mpm = new ArrayList<MusicBusiModel>();
		try{
			mpm = music_dao.getMostPopMusic();
		}catch(AppException e){
			throw new AppException("com.ruanko.music.service.UserService.register");
		}		
		return mpm;
	}

}
