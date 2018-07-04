package com.ruanko.music.dao.impl;

import java.util.ArrayList;

import com.ruanko.music.dao.MusicDao;
import com.ruanko.music.model.MusicBusiModel;
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

}
