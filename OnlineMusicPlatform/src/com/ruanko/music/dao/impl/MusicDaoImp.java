package com.ruanko.music.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import com.ruanko.music.dao.MusicDao;
import com.ruanko.music.model.Album;
import com.ruanko.music.model.Artist;
import com.ruanko.music.model.Music;
import com.ruanko.music.model.MusicBusiModel;
import com.ruanko.music.model.Popularity;
import com.ruanko.music.model.Tag;
import com.ruanko.music.utils.AppException;
import com.ruanko.music.utils.DBUtil;

/**
 * 音乐数据访问层实现类
 */
public class MusicDaoImp implements MusicDao{

	@Override
	public ArrayList<MusicBusiModel> getMostPopMusic() throws AppException {
		Connection conn = null;//声明数据库连接
		PreparedStatement psmt = null;//声明预处理语句
		ResultSet rs = null;//声明结果集合

		//根据
		ArrayList<MusicBusiModel> mbml = new ArrayList<MusicBusiModel>();//声明返回结果
		try{
			conn = DBUtil.getConnection();//获得数据库连接
			String sql = "select popularity.hits_count,popularity.obj_id,music.mus_id,music.musicurl,music.name ,artist.name "
					+"from popularity,music,artist"
					+"where popularity.type = 3 "
					+"and music.art_id = popularity.obj_id"
					+"and where artist.id = music.art_id"
					+"order by popularity.hits_count desc limit 10";
			rs = psmt.executeQuery(sql);
			System.out.println("执行sql语句");
			while(rs.next()){
				MusicBusiModel muisc = new MusicBusiModel();
				System.out.println(rs.getInt(0));
			}
			
		} catch(SQLException e){
			e.printStackTrace();
		}
		return mbml;
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

	@Override
	public void resetAritist(Artist artist) throws AppException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resetAlbum(Album album) throws AppException {
		// TODO Auto-generated method stub
		
	}


	@Override
	public MusicBusiModel getMusicByRealname(String realname) throws AppException {
		// TODO Auto-generated method stub
		return null;
	}

}
