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
		ArrayList<MusicBusiModel> mbml = new ArrayList<MusicBusiModel>();
		try{
			mbml = music_dao.getMostPopMusic();
		}catch(AppException e){
			throw new AppException("com.ruanko.music.service.UserService.getMostPopMusic");
		}		
		return mbml;
	}

	/**
	 * 通过Id搜索歌曲
	 * @param Id
	 * @return MusicBusiModel
	 * @throws AppException
	 */
	public MusicBusiModel getMusicById(String id) throws AppException{
		MusicBusiModel mbm = new MusicBusiModel();
		try{
			mbm = music_dao.getMusicById(id);
		}catch(AppException e){
			throw new AppException("com.ruanko.music.service.UserService.getMusicById");
		}
				return null;		
	}
	
	/**
	 * 搜索歌曲
	 * @param content 搜索字段
	 * @param type 0-按歌曲名搜索；1-按歌手名搜索；2-按专辑名搜索
	 * @return 符合条件的歌曲列表
	 * @throws AppException
	 */
	public ArrayList<MusicBusiModel> getMusicByContent(String content, int type) throws AppException{
		ArrayList<MusicBusiModel> mbml = new ArrayList<MusicBusiModel>();
		try{
			switch(type){
			case 0:mbml = music_dao.getMusicByName(content);break;
			case 1:mbml = music_dao.getMusicByArtist(content);break;
			case 2:mbml = music_dao.getMusicByAlbum(content);break;
			default:mbml = null;break;
			}
		}catch(AppException e){
			throw new AppException("com.ruanko.music.service.UserService.getMusicByContent");
		}
		return mbml;		
	}
}
