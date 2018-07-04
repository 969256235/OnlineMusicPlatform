package com.ruanko.music.dao.impl;

import java.util.ArrayList;
import com.ruanko.music.dao.MusicDao;
import com.ruanko.music.model.Album;
import com.ruanko.music.model.Artist;
import com.ruanko.music.model.Music;
import com.ruanko.music.model.MusicBusiModel;
import com.ruanko.music.model.Popularity;
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
	public ArrayList<Artist> getArtistByName(String name) throws AppException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Album> getAlbumByName(String name) throws AppException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean addMusic(Music music) throws AppException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean addArtist(Artist artist) throws AppException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean addAlbum(Album album) throws AppException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean addPopularity(Popularity pop) throws AppException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delMusic(String id) throws AppException {
		// TODO Auto-generated method stub
		return false;
	}

}
