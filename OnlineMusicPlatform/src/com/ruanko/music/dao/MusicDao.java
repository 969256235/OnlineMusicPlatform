package com.ruanko.music.dao;

import java.util.ArrayList;

import com.ruanko.music.model.MusicBusiModel;
import com.ruanko.music.utils.AppException;

/**
 * 音乐数据访问层接口
 */
public interface MusicDao {

	/**
	 * 获取热度最高的十首歌
	 * @return 包含十个MusicBusiModel的List
	 * @throws AppException
	 */
	public ArrayList<MusicBusiModel> getMostPopMusic() throws AppException;
	
	/**
	 * 通过Id搜索歌曲
	 * @param 歌曲id
	 * @return MusicBusiModel
	 * @throws AppException
	 */
	public MusicBusiModel getMusicById(String Id) throws AppException;
	
	/**
	 * 通过歌曲名搜索获取歌曲列表
	 * @param name
	 * @return 符合条件的MusiBusiModel数组
	 * @throws AppException
	 */
	public ArrayList<MusicBusiModel> getMusicByName(String name) throws AppException;
	
	/**
	 * 通过歌曲名搜索获取歌曲列表
	 * @param art_name
	 * @return 符合条件的MusiBusiModel数组
	 * @throws AppException
	 */
	public ArrayList<MusicBusiModel> getMusicByArtist(String art_name) throws AppException;
	
	/**
	 * 通过歌曲名搜索获取歌曲列表
	 * @param alb_name
	 * @return 符合条件的MusiBusiModel数组
	 * @throws AppException
	 */
	public ArrayList<MusicBusiModel> getMusicByAlbum(String alb_name) throws AppException;
}
