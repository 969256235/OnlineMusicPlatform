package com.ruanko.music.dao;

import java.util.ArrayList;
import com.ruanko.music.model.Album;
import com.ruanko.music.model.Artist;
import com.ruanko.music.model.Music;
import com.ruanko.music.model.MusicBusiModel;
import com.ruanko.music.model.Popularity;
import com.ruanko.music.model.Tag;
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
	 * 获取最新十首歌
	 * @return 包含十个MusicBusiModel的List
	 * @throws AppException
	 */
	public ArrayList<MusicBusiModel> getNewestMusic() throws AppException;
	
	/**
	 * 获取随机十首歌
	 * @return 包含十个MusicBusiModel的List
	 * @throws AppException
	 */
	public ArrayList<MusicBusiModel> getRandomMusic() throws AppException;
	
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
	
	/**
	 * 按照Id查询歌手
	 * @param Id
	 * @return Artist
	 * @throws AppException
	 */
	public Artist getArtistById(String Id) throws AppException;
	
	/**
	 * 按名字检索歌手
	 * @param name
	 * @return 符合条件的歌手列表
	 * @throws AppException
	 */
	public ArrayList<Artist> getArtistByName(String name) throws AppException;
	
	/**
	 * 按照Id查询专辑
	 * @param Id
	 * @return Album
	 * @throws AppException
	 */
	public Album getAlbumById(String Id) throws AppException;
		
	/**
	 * 按名字检索专辑
	 * @param name
	 * @return 符合条件的专辑列表
	 * @throws AppException
	 */
	public ArrayList<Album> getAlbumByName(String name) throws AppException;
	
	/**
	 * 按id搜索标签
	 * @param Id
	 * @return 返回符合条件的标签
	 * @throws AppException
	 */
	public Tag getTagById(String Id) throws AppException;
	
	/**
	 * 按名字搜索标签
	 * @param name
	 * @return 返回符合条件的标签，输入参数name为空时返回new Tag();
	 * @throws AppException
	 */
	public Tag getTagByName(String name) throws AppException;
	
	/**
	 * 添加音乐
	 * @param music 音乐基础模型
	 * @return 返回添加后的Music在数据库表中的id，失败返回0
	 * @throws AppException
	 */
	public int addMusic(Music music) throws AppException;
	
	/**
	 * 添加艺人
	 * @param artist
	 * @return 返回添加后的Artist在数据库表中的id，失败返回0
	 * @throws AppException
	 */
	public int addArtist(Artist artist) throws AppException;
	
	/**
	 * 添加专辑
	 * @param album
	 * @return 返回添加后的Album在数据库表中的id，失败返回0
	 * @throws AppException
	 */
	public int addAlbum(Album album) throws AppException;
	
	/**
	 * 添加热度
	 * @param pop
	 * @return 返回添加后的Popularity在数据库表中的id，失败返回0
	 * @throws AppException
	 */
	public int addPopularity(Popularity pop) throws AppException;
	
	/**
	 * 删除音乐
	 * @param id 欲删除的音乐的id
	 * @throws AppException
	 */
	public boolean delMusic(String id) throws AppException;
	
	/**
	 * 修改歌曲信息
	 * @param music
	 * @throws AppException
	 */
	public boolean resetMusic(Music music) throws AppException;
	
	/**
	 * 修改艺人信息
	 * @param artist
	 * @throws AppException
	 */
	public void resetAritist(Artist artist) throws AppException;
	
	/**
	 * 修改专辑信息
	 * @param album
	 * @throws AppException
	 */
	public void resetAlbum(Album album) throws AppException;
}
