package com.ruanko.music.dao.impl;

import java.util.ArrayList;
import com.ruanko.music.dao.MusicDao;
import com.ruanko.music.model.Album;
import com.ruanko.music.model.Artist;
import com.ruanko.music.model.Music;
import com.ruanko.music.model.MusicBusiModel;
import com.ruanko.music.model.Popularity;
import com.ruanko.music.model.Tag;
import com.ruanko.music.utils.AppException;

/**
 * 音乐数据访问层实现类
 */
public class MusicDaoImp implements MusicDao{

	@Override
	public ArrayList<MusicBusiModel> getMostPopMusic() throws AppException {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public ArrayList<MusicBusiModel> getNewestMusic() throws AppException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<MusicBusiModel> getRandomMusic() throws AppException {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public MusicBusiModel getMusicById(String Id) throws AppException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<MusicBusiModel> getMusicByName(String name) throws AppException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<MusicBusiModel> getMusicByArtist(String art_name) throws AppException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<MusicBusiModel> getMusicByAlbum(String alb_name) throws AppException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Artist getArtistById(String Id) throws AppException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Artist> getArtistByName(String name) throws AppException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Album getAlbumById(String Id) throws AppException {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public ArrayList<Album> getAlbumByName(String name) throws AppException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Tag getTagById(String Id) throws AppException {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public Tag getTagByName(String name) throws AppException {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public int addMusic(Music music) throws AppException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int addArtist(Artist artist) throws AppException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int addAlbum(Album album) throws AppException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int addPopularity(Popularity pop) throws AppException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean delMusic(String id) throws AppException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean resetMusic(Music music) throws AppException {
		// TODO Auto-generated method stub
		return false;
	}

}
