package com.ruanko.music.service;

import java.util.ArrayList;

import com.ruanko.music.dao.MusicDao;
import com.ruanko.music.dao.impl.MusicDaoImp;
import com.ruanko.music.model.Album;
import com.ruanko.music.model.Artist;
import com.ruanko.music.model.Music;
import com.ruanko.music.model.MusicBusiModel;
import com.ruanko.music.model.Popularity;
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
	 * 获取最新的十首歌
	 * @return 包含十个MusicBusiModel的List
	 * @throws AppException
	 */
	public ArrayList<MusicBusiModel> getNewestMusic() throws AppException{
		ArrayList<MusicBusiModel> mbml = new ArrayList<MusicBusiModel>();
		try{
			mbml = music_dao.getNewestMusic();
		}catch(AppException e){
			throw new AppException("com.ruanko.music.service.UserService.getMostPopMusic");
		}		
		return mbml;
	}
	
	/**
	 * 获取随机的十首歌
	 * @return 包含十个MusicBusiModel的List
	 * @throws AppException
	 */
	public ArrayList<MusicBusiModel> getRandomMusic() throws AppException{
		ArrayList<MusicBusiModel> mbml = new ArrayList<MusicBusiModel>();
		try{
			mbml = music_dao.getRandomMusic();
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
				return mbm;		
	}
	
	/**
	 * 搜索歌曲
	 * @param content 搜索字段
	 * @param type 0-按歌曲名搜索；1-按歌手名搜索；2-按专辑名搜索；3-歌名、歌手、专辑
	 * @return 符合条件的歌曲列表
	 * @throws AppException
	 */
	public ArrayList<MusicBusiModel> getMusicByContent(String content, int type) throws AppException{
		ArrayList<MusicBusiModel> mbml_temp,mbml;
		try{
			mbml = new ArrayList<MusicBusiModel>();
			if(type == 0 || type == 3){
				mbml_temp = new ArrayList<MusicBusiModel>();
				mbml_temp = music_dao.getMusicByName(content);
				if(mbml_temp != null){
					mbml.addAll(mbml_temp);
				}
			}
			if(type == 1 || type == 3){
				mbml_temp = new ArrayList<MusicBusiModel>();
				mbml_temp = music_dao.getMusicByArtist(content);
				if(mbml_temp != null){
					mbml.addAll(mbml_temp);
				}
			}
			if(type == 2 || type ==3){
				mbml_temp = new ArrayList<MusicBusiModel>();
				mbml_temp = music_dao.getMusicByAlbum(content);
				if(mbml_temp != null){
					mbml.addAll(mbml_temp);
				}
			}
		}catch(AppException e){
			throw new AppException("com.ruanko.music.service.UserService.getMusicByContent");
		}
		return mbml;
	}
	
	/**
	 * 添加音乐
	 * @param mbm MusicBusiModel
	 * @return 添加后的Music的Id
	 * @throws AppException
	 */
	public int addMusic(MusicBusiModel mbm) throws AppException{
		Music music = new Music();
		Artist artist = new Artist();
		Album album = new Album();
		Popularity pop;
		try{
			int[] tag_ = {music_dao.getTagByName(mbm.getTag1()).getTagId(),music_dao.getTagByName(mbm.getTag2()).getTagId(),music_dao.getTagByName(mbm.getTag3()).getTagId()};
			//根据MusicBusiModel为music赋值
			music.setAttributes(0, 0, 0, tag_[0],tag_[1],tag_[2], mbm.getName(), mbm.getRealname(), mbm.getLrc(), mbm.getZone(), mbm.getPublishdate(), mbm.getMusicurl());
			music.setDel(0);
			
			//查询该艺人是否存在
			ArrayList<Artist> art_= music_dao.getArtistByName(mbm.getArtist());
			if(art_.size() != 0){ //若存在则将艺人id设为查询到的id
				music.setArt_id(art_.get(0).getArtId());
			}
			else{ //否则创建新艺人
				artist.setAttributes(0, mbm.getArtist(), mbm.getArtist_photo(), "", "", "", "");
				artist.setArtId(music_dao.addArtist(artist));
				pop = new Popularity();
				pop.setAttributes(0, 2, artist.getArtId(), 0, 0);
				music_dao.addPopularity(pop);
				music.setArt_id(artist.getArtId());
			}
			
			//查询专辑是否存在
			ArrayList<Album> alb_ = music_dao.getAlbumByName(mbm.getAlbum());
			if(alb_.size() != 0){ //若存在则将专辑id设为查询到的id
				music.setAlb_id(alb_.get(0).getAlbId());
			}
			else{ //否则创建新专辑
				album.setAttributes(0, music.getArt_id(), mbm.getAlbum(), mbm.getPublishdate(), mbm.getCover(), "", "", "");
				album.setAlbId(music_dao.addAlbum(album));
				pop = new Popularity();
				pop.setAttributes(0, 1, album.getAlbId(), 0, 0);
				music_dao.addPopularity(pop);
				music.setAlb_id(album.getAlbId());
			}
			
			//添加音乐
			music.setId(music_dao.addMusic(music)); 
			pop = new Popularity();
			pop.setAttributes(0, 3, music.getMusId(), 0, 0);
			music_dao.addPopularity(pop);
			
		}catch(AppException e){
			throw new AppException("com.ruanko.music.service.UserService.addMusic");
		}
		return music.getMusId();	
	}
	
	/**
	 * 按歌曲id删除歌曲
	 * @param id
	 * @throws AppException
	 */
	public void deleteMusicById(String id) throws AppException{
		try{
			music_dao.delMusic(id);
		}catch(AppException e){
			throw new AppException("com.ruanko.music.service.UserService.deleteMusicById");
		}
	}
	
	/**
	 * 修改歌曲信息
	 * @param mbm MusicBusiMoedl
	 * @throws AppException
	 */
	public void resetMusic(MusicBusiModel mbm) throws AppException{
		Music music = new Music();
		Artist artist = new Artist();
		Album album = new Album();
		Popularity pop;
		try{
			int[] tag_ = {music_dao.getTagByName(mbm.getTag1()).getTagId(),music_dao.getTagByName(mbm.getTag2()).getTagId(),music_dao.getTagByName(mbm.getTag3()).getTagId()};
			music.setAttributes(mbm.getId(), 0, 0, tag_[0],tag_[1],tag_[2], mbm.getName(), mbm.getRealname(), mbm.getLrc(), mbm.getZone(), mbm.getPublishdate(), mbm.getMusicurl());
			music.setDel(0);
			
			//查询该艺人是否存在
			ArrayList<Artist> art_= music_dao.getArtistByName(mbm.getArtist());
			if(art_.size() == 0){ //若艺人不存在
				artist.setAttributes(0, mbm.getArtist(), mbm.getArtist_photo(), "", "", "", "");
				artist.setArtId(music_dao.addArtist(artist));
				pop = new Popularity();
				pop.setAttributes(0, 2, artist.getArtId(), 0, 0);
				music_dao.addPopularity(pop);
				music.setArt_id(artist.getArtId());
			}else{
				music.setArt_id(art_.get(0).getArtId());
			}
			
			//查询专辑是否存在
			ArrayList<Album> alb_ = music_dao.getAlbumByName(mbm.getAlbum());
			if(alb_.size() == 0){ //若专辑不存在
				album.setAttributes(0, music.getArt_id(), mbm.getAlbum(), mbm.getPublishdate(), mbm.getCover(), "", "", "");
				album.setAlbId(music_dao.addAlbum(album));
				pop = new Popularity();
				pop.setAttributes(0, 1, album.getAlbId(), 0, 0);
				music_dao.addPopularity(pop);
				music.setAlb_id(album.getAlbId());
			}else{
				music.setAlb_id(alb_.get(0).getAlbId());
			}
			
			music_dao.resetMusic(music);
		}catch(AppException e){
			throw new AppException("com.ruanko.music.service.UserService.resetMusic");
		}
	}
	
	/**
	 * 修改艺人信息
	 * @param artist
	 * @throws AppException
	 */
	public void resetArtist(Artist artist) throws AppException{
		try{
			music_dao.resetAritist(artist);
		}catch(AppException e){
			throw new AppException("com.ruanko.music.service.UserService.resetArtist");
		}
	}
	
	/**
	 * 修改专辑信息
	 * @param artist
	 * @throws AppException
	 */
	public void resetArtist(Album album) throws AppException{
		try{
			music_dao.resetAlbum(album);
		}catch(AppException e){
			throw new AppException("com.ruanko.music.service.UserService.resetAlbum");
		}
	}
	
}
