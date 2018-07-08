package com.ruanko.music.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
public class MusicDaoImp implements MusicDao {

	@Override
	public ArrayList<MusicBusiModel> getMostPopMusic() throws AppException {
		Connection conn = null;// 声明数据库连接
		PreparedStatement psmt = null;// 声明预处理语句
		ResultSet rs, rs1 = null;// 声明结果集合

		// 查询点击量前十的歌曲并且返回MusicBusiModel的数组
		ArrayList<MusicBusiModel> mbml = new ArrayList<MusicBusiModel>();// 声明返回结果
		try {
			// 获得所有tag和对应的编号
			// 使用hashmap存放tag.tag_id -> tag.tagname
			conn = DBUtil.getConnection();// 获得数据库连接
			String sql1 = "select * from tag;";// 获取tag信息的sql语句
			psmt = conn.prepareStatement(sql1);// 预处理获取tag信息的sql语句
			rs1 = psmt.executeQuery(sql1);// 执行获取tag信息的语句
			Map map = new HashMap();// 用hashMap存储
			while (rs1.next()) {
				map.put(rs1.getInt(1), rs1.getString(2));
				// System.out.println(rs1.getString(1)+rs1.getString(2)+rs1.getString(3)+rs1.getString(4));
			}
			String sql = "select " + "popularity.down_count,popularity.hits_count,"
			// 1.int 2.int
					+ "music.mus_id,music.name,music.realname,music.lrc,music.zone,music.publishdate,music.tag1,music.tag2,music.tag3,music.mus_url,"
					// 3.int 4.string 5.string 6.string 7.string 8.string 9.int
					// 10.int 11.int 12.string
					+ "artist.name,artist.image1, "
					// 13.string 14.string
					+ "album.name,album.image1 "
					// 15.string 16.string
					+ "from popularity,music,artist,album " + "where popularity.type = 3 "
					+ "and music.art_id = popularity.obj_id " + "and artist.art_id = music.art_id "
					+ "and album.art_id = artist.art_id " + "and album.alb_id = music.alb_id "
					+ "order by popularity.hits_count desc limit 10;";
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery(sql);
			System.out.println("执行sql语句");
			while (rs.next()) {
				MusicBusiModel mbmd = new MusicBusiModel();
				// 设置MusicBusiModel
				mbmd.setDown_count(rs.getInt(1));
				mbmd.setHit_count(rs.getInt(2));
				mbmd.setId(rs.getInt(3));
				mbmd.setName(rs.getString(4));
				mbmd.setRealname(rs.getString(5));
				mbmd.setLrc(rs.getString(6));
				mbmd.setZone(rs.getString(7));
				mbmd.setPublishdate(rs.getString(8));
				mbmd.setTag1(map.get(rs.getInt(9)).toString());
				if (map.get(rs.getInt(10)) != null)
					mbmd.setTag2(map.get(rs.getInt(10)).toString());
				else {
					mbmd.setTag2("");
				}
				if (map.get(rs.getInt(10)) != null)
					mbmd.setTag3(map.get(rs.getInt(11)).toString());
				else {
					mbmd.setTag3("");
				}
				mbmd.setMusicurl(rs.getString(12));
				mbmd.setArtist(rs.getString(13));
				mbmd.setArtist_photo(rs.getString(14));
				mbmd.setAlbum(rs.getString(15));
				mbmd.setArtist_photo(rs.getString(16));
				mbml.add(mbmd);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// 10.关闭数据库连接
			try {
				DBUtil.closeStatement(psmt);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				DBUtil.closeConnection(conn);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return mbml;
	}

	@Override
	public ArrayList<MusicBusiModel> getNewestMusic() throws AppException {
		Connection conn = null;// 声明数据库连接
		PreparedStatement psmt = null;// 声明预处理语句
		ResultSet rs, rs1 = null;// 声明结果集合

		// 查询点击量前十的歌曲并且返回MusicBusiModel的数组
		ArrayList<MusicBusiModel> mbml = new ArrayList<MusicBusiModel>();// 声明返回结果
		try {
			// 获得所有tag和对应的编号
			// 使用hashmap存放tag.tag_id -> tag.tagname
			conn = DBUtil.getConnection();// 获得数据库连接
			String sql1 = "select * from tag;";// 获取tag信息的sql语句
			psmt = conn.prepareStatement(sql1);// 预处理获取tag信息的sql语句
			rs1 = psmt.executeQuery(sql1);// 执行获取tag信息的语句
			Map map = new HashMap();// 用hashMap存储
			while (rs1.next()) {
				map.put(rs1.getInt(1), rs1.getString(2));
				// System.out.println(rs1.getString(1)+rs1.getString(2)+rs1.getString(3)+rs1.getString(4));
			}
			String sql = "select " + "popularity.down_count,popularity.hits_count,"
					+ "music.mus_id,music.name,music.realname,music.lrc,music.zone,music.publishdate,music.tag1,music.tag2,music.tag3,music.mus_url,"
					+ "artist.name,artist.image1, " + "album.name,album.image1 " + "from popularity,music,artist,album "
					+ "where artist.art_id = music.art_id " + "and popularity.type = 3 "
					+ "and music.art_id = popularity.obj_id " + "and album.art_id = artist.art_id "
					+ "and album.alb_id = music.alb_id " + "order by music.publishdate desc limit 10;";
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery(sql);
			System.out.println("执行sql语句");
			while (rs.next()) {
				MusicBusiModel mbmd = new MusicBusiModel();
				// 设置MusicBusiModel
				mbmd.setDown_count(rs.getInt(1));
				mbmd.setHit_count(rs.getInt(2));
				mbmd.setId(rs.getInt(3));
				mbmd.setName(rs.getString(4));
				System.out.println(rs.getString(4));
				mbmd.setRealname(rs.getString(5));
				mbmd.setLrc(rs.getString(6));
				mbmd.setZone(rs.getString(7));
				mbmd.setPublishdate(rs.getString(8));
				mbmd.setTag1(map.get(rs.getInt(9)).toString());
				if (map.get(rs.getInt(10)) != null)
					mbmd.setTag2(map.get(rs.getInt(10)).toString());
				else {
					mbmd.setTag2("");
				}
				if (map.get(rs.getInt(10)) != null)
					mbmd.setTag3(map.get(rs.getInt(11)).toString());
				else {
					mbmd.setTag3("");
				}
				mbmd.setMusicurl(rs.getString(12));
				mbmd.setArtist(rs.getString(13));
				mbmd.setArtist_photo(rs.getString(14));
				mbmd.setAlbum(rs.getString(15));
				mbmd.setArtist_photo(rs.getString(16));
				mbml.add(mbmd);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// 10.关闭数据库连接
			try {
				DBUtil.closeStatement(psmt);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				DBUtil.closeConnection(conn);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return mbml;
	}

	@Override
	public ArrayList<MusicBusiModel> getRandomMusic() throws AppException {
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		conn = DBUtil.getConnection();
		String sql1 = "select max(mus_id) from music;";
		int[] result = new int[10];
		int count = 0;
		try {
			psmt = conn.prepareStatement(sql1);
			rs = psmt.executeQuery();

			rs.next();
			int max = rs.getInt(1);
			while (count < 10) {
				int num = (int) (Math.random() * max) + 1;
				boolean flag = true;
				for (int j = 0; j < 10; j++) {
					if (num == result[j]) {
						flag = false;
						break;
					}
				}
				if (flag) {
					result[count] = num;
					System.out.println(num);
					count++;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// 10.关闭数据库连接
			try {
				DBUtil.closeStatement(psmt);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				DBUtil.closeConnection(conn);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		ArrayList<MusicBusiModel> list = new ArrayList(10);
		for (int i = 0; i < 10; i++) {
			MusicBusiModel m = getMusicById(Integer.toString(result[i]));
			list.add(m);
		}
		return list;
	}

	// 无论歌曲是否存在均返回MusicBusiModel对象
	// 根据id查询歌曲并返回MusicBusiModel
	// 若不存在MusicBusiModel对象，但其内容为null
	@Override
	public MusicBusiModel getMusicById(String Id) throws AppException {
		Connection conn = null;// 声明数据库连接
		PreparedStatement psmt = null;// 声明预处理语句
		ResultSet rs, rs1 = null;// 声明结果集合
		MusicBusiModel mbmd = new MusicBusiModel();// 声明返回的MusicBusiModel对象

		try {
			// 获得所有tag和对应的编号
			// 使用hashmap存放tag.tag_id -> tag.tagname
			conn = DBUtil.getConnection();// 获得数据库连接
			String sql1 = "select * from tag;";// 获取tag信息的sql语句
			psmt = conn.prepareStatement(sql1);// 预处理获取tag信息的sql语句
			rs1 = psmt.executeQuery(sql1);// 执行获取tag信息的语句
			Map map = new HashMap();// 用hashMap存储
			while (rs1.next()) {
				map.put(rs1.getInt(1), rs1.getString(2));
				// System.out.println(rs1.getString(1)+rs1.getString(2)+rs1.getString(3)+rs1.getString(4));
			}
			String sql = "select "
					+ "music.mus_id,music.name,music.realname,music.lrc,music.zone,music.publishdate,music.tag1,music.tag2,music.tag3,music.mus_url,"
					// 3.int 4.string 5.string 6.string 7.string 8.string 9.int
					// 10.int 11.int 12.string
					+ "artist.name,artist.image1, "
					// 13.string 14.string
					+ "album.name,album.image1,"
					// 15.string 16.string
					+ "popularity.down_count,popularity.hits_count "
					// 1.int 2.int
					+ "from music,artist,album,popularity " + "where music.mus_id = " + Id
					+ " and artist.art_id = music.art_id " + "and album.art_id = artist.art_id "
					+ "and album.alb_id = music.alb_id " + "and popularity.type = 3 "
					+ "and popularity.obj_id = music.mus_id;";
			System.out.println(sql);
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery(sql);
			System.out.println("执行sql语句");
			if (rs.next()) {
				// 设置MusicBusiModel
				mbmd.setDown_count(rs.getInt(15));
				mbmd.setHit_count(rs.getInt(16));
				mbmd.setId(rs.getInt(1));
				mbmd.setName(rs.getString(2));
				System.out.println("歌曲名:" + rs.getString(2));
				mbmd.setRealname(rs.getString(3));
				mbmd.setLrc(rs.getString(4));
				mbmd.setZone(rs.getString(5));
				mbmd.setPublishdate(rs.getString(6));
				mbmd.setTag1(map.get(rs.getInt(7)).toString());
				// tag2、tag3可为null
				if (rs.getInt(8) != 0) {
					mbmd.setTag2(map.get(rs.getInt(8)).toString());
				} else {
					mbmd.setTag2("");
				}
				if (rs.getInt(9) != 0) {
					mbmd.setTag3(map.get(rs.getInt(9)).toString());
				} else {
					mbmd.setTag3("");
				}
				mbmd.setMusicurl(rs.getString(10));
				mbmd.setArtist(rs.getString(11));
				mbmd.setArtist_photo(rs.getString(12));
				mbmd.setAlbum(rs.getString(13));
				mbmd.setArtist_photo(rs.getString(14));
				// 关闭结果集合，语句和连接
				DBUtil.closeResultSet(rs);
				DBUtil.closeResultSet(rs1);
				DBUtil.closeStatement(psmt);
				DBUtil.closeConnection(conn);
			} else {
				System.out.println("查询无结果");
				// 关闭结果集合，语句和连接
				DBUtil.closeResultSet(rs);
				DBUtil.closeResultSet(rs1);
				DBUtil.closeStatement(psmt);
				DBUtil.closeConnection(conn);
				mbmd = null;
				return mbmd;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return mbmd;
	}

	// 无论歌曲是否存在均返回MusicBusiModel对象
	// 根据歌曲名称查询歌曲并返回MusicBusiModel
	// 若不存在MusicBusiModel对象，但其内容为null
	// 模糊搜索，存在目标字段的歌曲都会被搜索
	@Override
	public ArrayList<MusicBusiModel> getMusicByName(String name) throws AppException {
		Connection conn = null;// 声明数据库连接
		PreparedStatement psmt = null;// 声明预处理语句
		ResultSet rs, rs1 = null;// 声明结果集合
		ArrayList<MusicBusiModel> mbml = new ArrayList<MusicBusiModel>();// 声明返回结果

		try {
			// 获得所有tag和对应的编号
			// 使用hashmap存放tag.tag_id -> tag.tagname
			conn = DBUtil.getConnection();// 获得数据库连接
			String sql1 = "select * from tag;";// 获取tag信息的sql语句
			psmt = conn.prepareStatement(sql1);// 预处理获取tag信息的sql语句
			rs1 = psmt.executeQuery(sql1);// 执行获取tag信息的语句
			Map map = new HashMap();// 用hashMap存储
			while (rs1.next()) {
				map.put(rs1.getInt(1), rs1.getString(2));
				// System.out.println(rs1.getString(1)+rs1.getString(2)+rs1.getString(3)+rs1.getString(4));
			}
			String sql = "select "
					+ "music.mus_id,music.name,music.realname,music.lrc,music.zone,music.publishdate,music.tag1,music.tag2,music.tag3,music.mus_url,"
					// 3.int 4.string 5.string 6.string 7.string 8.string 9.int
					// 10.int 11.int 12.string
					+ "artist.name,artist.image1, "
					// 13.string 14.string
					+ "album.name,album.image1,"
					// 15.string 16.string
					+ "popularity.down_count,popularity.hits_count "
					// 1.int 2.int
					+ "from music,artist,album,popularity " + "where music.name like " + " '%" + name + "%' "
					+ "and artist.art_id = music.art_id " + "and album.art_id = artist.art_id "
					+ "and album.alb_id = music.alb_id " + "and popularity.type = 3 "
					+ "and popularity.obj_id = music.mus_id;";
			// System.out.println(sql);
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery(sql);
			System.out.println("执行sql语句");
			while (rs.next()) {
				MusicBusiModel mbmd = new MusicBusiModel();// 声明MusicBusiModel对象
				// 设置MusicBusiModel
				mbmd.setDown_count(rs.getInt(15));
				mbmd.setHit_count(rs.getInt(16));
				mbmd.setId(rs.getInt(1));
				mbmd.setName(rs.getString(2));
				// System.out.println("歌曲名:"+rs.getString(2));
				mbmd.setRealname(rs.getString(3));
				mbmd.setLrc(rs.getString(4));
				mbmd.setZone(rs.getString(5));
				mbmd.setPublishdate(rs.getString(6));
				mbmd.setTag1(map.get(rs.getInt(7)).toString());
				// tag2、tag3可为null
				if (rs.getInt(8) != 0) {
					mbmd.setTag2(map.get(rs.getInt(8)).toString());
				} else {
					mbmd.setTag2("");
				}
				if (rs.getInt(9) != 0) {
					mbmd.setTag3(map.get(rs.getInt(9)).toString());
				} else {
					mbmd.setTag3("");
				}
				mbmd.setMusicurl(rs.getString(10));
				mbmd.setArtist(rs.getString(11));
				mbmd.setArtist_photo(rs.getString(12));
				mbmd.setAlbum(rs.getString(13));
				mbmd.setArtist_photo(rs.getString(14));
				mbml.add(mbmd);
			}
			// 关闭结果集合，语句和连接
			DBUtil.closeResultSet(rs);
			DBUtil.closeResultSet(rs1);
			DBUtil.closeStatement(psmt);
			DBUtil.closeConnection(conn);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return mbml;
	}

	@Override
	//模糊搜索
	//根据歌手名查询歌曲
	public ArrayList<MusicBusiModel> getMusicByArtist(String art_name) throws AppException {
		Connection conn = null;//声明数据库连接
		PreparedStatement psmt = null;//声明预处理语句
		ResultSet rs,rs1 = null;//声明结果集合		
		ArrayList<MusicBusiModel> mbml = new ArrayList<MusicBusiModel>();//声明返回结果
		
		try{
			//获得所有tag和对应的编号
			//使用hashmap存放tag.tag_id -> tag.tagname
			conn = DBUtil.getConnection();//获得数据库连接
			String sql1 = "select * from tag;";//获取tag信息的sql语句
			psmt = conn.prepareStatement(sql1);//预处理获取tag信息的sql语句
			rs1 = psmt.executeQuery(sql1);//执行获取tag信息的语句
			Map map = new HashMap();//用hashMap存储
			while(rs1.next()){
				map.put(rs1.getInt(1), rs1.getString(2));
				//System.out.println(rs1.getString(1)+rs1.getString(2)+rs1.getString(3)+rs1.getString(4));
			}
			String sql = "select "
					+ "artist.name,artist.image1, "
					//1.string     2.string
					+ "music.mus_id,music.name,music.realname,music.lrc,music.zone,music.publishdate,music.tag1,music.tag2,music.tag3,music.mus_url,"
					//3.int			4.string   5.string       6.string  7.string   8.string          9.int      10.int     11.int     12.string
					+ "album.name,album.image1,"
					//13.string    14.string
					+ "popularity.down_count,popularity.hits_count "
					//15.int				16.int
					+ "from artist,music,album,popularity "
					+ "where artist.name like "
					+ " '%"
					+ art_name
					+ "%' "
					+ "and music.art_id = artist.art_id "
					+ "and album.art_id = artist.art_id "
					+ "and album.alb_id = music.alb_id "
					+ "and popularity.type = 3 "
					+ "and popularity.obj_id = music.mus_id;";
			//System.out.println(sql);
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery(sql);
			System.out.println("执行sql语句");
			while(rs.next()){
				MusicBusiModel mbmd = new MusicBusiModel();//声明MusicBusiModel对象
				//设置MusicBusiModel
				mbmd.setDown_count(rs.getInt(15));
				mbmd.setHit_count(rs.getInt(16));
				mbmd.setId(rs.getInt(3));
				mbmd.setName(rs.getString(4));
				//System.out.println("歌曲名:"+rs.getString(2));
				mbmd.setRealname(rs.getString(5));
				mbmd.setLrc(rs.getString(6));
				mbmd.setZone(rs.getString(7));
				mbmd.setPublishdate(rs.getString(8));
				mbmd.setTag1(map.get(rs.getInt(9)).toString());
				//tag2、tag3可为null
				if(rs.getInt(10)!=0){
					mbmd.setTag2(map.get(rs.getInt(10)).toString());
				}
				else {
					mbmd.setTag2("");
				}
				if(rs.getInt(11)!=0){
					mbmd.setTag3(map.get(rs.getInt(11)).toString());
				}
				else {
					mbmd.setTag3("");
				}
				mbmd.setMusicurl(rs.getString(12));
				mbmd.setArtist(rs.getString(15));
				mbmd.setArtist_photo(rs.getString(16));
				mbmd.setAlbum(rs.getString(13));
				mbmd.setArtist_photo(rs.getString(14));
				mbml.add(mbmd);
			}
			//关闭结果集合，语句和连接
			DBUtil.closeResultSet(rs);
			DBUtil.closeResultSet(rs1);
			DBUtil.closeStatement(psmt);
			DBUtil.closeConnection(conn);
		} catch(SQLException e){
			e.printStackTrace();
		} 
		return mbml;
	}


	@Override
	//模糊搜索
	//通过专辑名搜索歌曲
	public ArrayList<MusicBusiModel> getMusicByAlbum(String alb_name) throws AppException {
		Connection conn = null;//声明数据库连接
		PreparedStatement psmt = null;//声明预处理语句
		ResultSet rs,rs1 = null;//声明结果集合		
		ArrayList<MusicBusiModel> mbml = new ArrayList<MusicBusiModel>();//声明返回结果
		
		try{
			//获得所有tag和对应的编号
			//使用hashmap存放tag.tag_id -> tag.tagname
			conn = DBUtil.getConnection();//获得数据库连接
			String sql1 = "select * from tag;";//获取tag信息的sql语句
			psmt = conn.prepareStatement(sql1);//预处理获取tag信息的sql语句
			rs1 = psmt.executeQuery(sql1);//执行获取tag信息的语句
			Map map = new HashMap();//用hashMap存储
			while(rs1.next()){
				map.put(rs1.getInt(1), rs1.getString(2));
				//System.out.println(rs1.getString(1)+rs1.getString(2)+rs1.getString(3)+rs1.getString(4));
			}
			String sql = "select "
					+ "album.name,album.image1,"
					//1.string    2.string
					+ "artist.name,artist.image1, "
					//3.string     4.string
					+ "music.mus_id,music.name,music.realname,music.lrc,music.zone,music.publishdate,music.tag1,music.tag2,music.tag3,music.mus_url,"
					//5.int			6.string   7.string       8.string  9.string   10.string         11.int      12.int     13.int     14.string
					+ "popularity.down_count,popularity.hits_count "
					//15.int				16.int
					+ "from album,artist,music,popularity "
					+ "where album.name like "
					+ " '%"
					+ alb_name
					+ "%' "
					+ "and artist.art_id = album.art_id "
					+ "and music.art_id = artist.art_id "
					+ "and popularity.type = 3 "
					+ "and popularity.obj_id = music.mus_id;";
			//System.out.println(sql);
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery(sql);
			System.out.println("执行sql语句");
			while(rs.next()){
				MusicBusiModel mbmd = new MusicBusiModel();//声明MusicBusiModel对象
				//设置MusicBusiModel
				mbmd.setDown_count(rs.getInt(15));
				mbmd.setHit_count(rs.getInt(16));
				mbmd.setId(rs.getInt(5));
				mbmd.setName(rs.getString(6));
				//System.out.println("歌曲名:"+rs.getString(2));
				mbmd.setRealname(rs.getString(7));
				mbmd.setLrc(rs.getString(8));
				mbmd.setZone(rs.getString(9));
				mbmd.setPublishdate(rs.getString(10));
				mbmd.setTag1(map.get(rs.getInt(11)).toString());
				//tag2、tag3可为null
				if(rs.getInt(12)!=0){
					mbmd.setTag2(map.get(rs.getInt(12)).toString());
				}
				else {
					mbmd.setTag2("");
				}
				if(rs.getInt(13)!=0){
					mbmd.setTag3(map.get(rs.getInt(13)).toString());
				}
				else {
					mbmd.setTag3("");
				}
				mbmd.setMusicurl(rs.getString(14));
				mbmd.setArtist(rs.getString(3));
				mbmd.setArtist_photo(rs.getString(4));
				mbmd.setAlbum(rs.getString(1));
				mbmd.setArtist_photo(rs.getString(2));
				mbml.add(mbmd);
			}
			//关闭结果集合，语句和连接
			DBUtil.closeResultSet(rs);
			DBUtil.closeResultSet(rs1);
			DBUtil.closeStatement(psmt);
			DBUtil.closeConnection(conn);
		} catch(SQLException e){
			e.printStackTrace();
		} 
		return mbml;
	}
	
	@Override
	//精确
	//通过歌手id搜索歌手
	public Artist getArtistById(String Id) throws AppException {
		Connection conn = null;//声明数据库连接对象
		PreparedStatement psmt = null;//声明预处理语句对象
		ResultSet rs = null;//声明结果集对象
		Artist artist = new Artist();//声明返回的对象
		
		try {
			conn = DBUtil.getConnection();//获得数据库连接
			String sql = "select * from artist where art_id = '" + Id +"'";
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();
			if(rs.next()){
				artist.setArtId(rs.getInt(1));
				artist.setName(rs.getString(2));
				artist.setImage1(rs.getString(3));
				artist.setImage2(rs.getString(4));
				artist.setDescription(rs.getString(5));
				artist.setCategory(rs.getString(6));
				artist.setGender(rs.getString(7));
				artist.setDel(rs.getInt(8));
			}
			else {
				artist = null;
			}
			DBUtil.closeResultSet(rs);
			DBUtil.closeStatement(psmt);
			DBUtil.closeConnection(conn);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return artist;
	}


	@Override
	//模糊搜索
	//根据检索信息寻找包含检索信息的歌手列表
	public ArrayList<Artist> getArtistByName(String name) throws AppException {
		Connection conn = null;//声明数据库连接对象
		PreparedStatement psmt = null;//声明预处理语句对象
		ResultSet rs = null;//声明结果集对象
		ArrayList<Artist> artList = new ArrayList<Artist>();
		
		try {
			conn = DBUtil.getConnection();//获得数据库连接
			String sql = "select * from artist where name like '%" + name +"%'";
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();
			while(rs.next()){
				Artist artist = new Artist();//声明返回的对象
				artist.setArtId(rs.getInt(1));
				artist.setName(rs.getString(2));
				artist.setImage1(rs.getString(3));
				artist.setImage2(rs.getString(4));
				artist.setDescription(rs.getString(5));
				artist.setCategory(rs.getString(6));
				artist.setGender(rs.getString(7));
				artist.setDel(rs.getInt(8));
				artList.add(artist);
			}
			DBUtil.closeResultSet(rs);
			DBUtil.closeStatement(psmt);
			DBUtil.closeConnection(conn);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return artList;
	}

	@Override
	//精确搜索
	//根据专辑id搜索专辑
	public Album getAlbumById(String Id) throws AppException {
		Connection conn = null;//声明数据库连接对象
		PreparedStatement psmt = null;//声明预处理语句
		ResultSet rs = null;//声明结果集合对象
		Album album = new Album();//声明返回的专辑对象
		
		try {
			conn = DBUtil.getConnection();//连接数据库
			String sql = "select * from album where alb_id = '" + Id + "'";
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();
			if(rs.next()){
				album.setAlbId(rs.getInt(1));
				album.setArt_id(rs.getInt(2));
				album.setName(rs.getString(3));
				album.setPublishdate(rs.getString(4));
				album.setImage1(rs.getString(5));
				album.setImage2(rs.getString(6));
				album.setCompany(rs.getString(7));
				album.setDescription(rs.getString(8));
				album.setDel(rs.getInt(9));
			}
			//关闭数据库相关对象
			DBUtil.closeResultSet(rs);
			DBUtil.closeStatement(psmt);
			DBUtil.closeConnection(conn);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return album;
	}
	
	@Override
	//模糊搜索
	//根据检索信息寻找包含检索信息的专辑列表
	public ArrayList<Album> getAlbumByName(String name) throws AppException {
		Connection conn = null;//声明数据库连接对象
		PreparedStatement psmt = null;//声明预处理语句
		ResultSet rs = null;//声明结果集合对象
		ArrayList<Album> albumList = new ArrayList<Album>();
		
		try {
			conn = DBUtil.getConnection();//连接数据库
			String sql = "select * from album where name like '%" + name + "%'";
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();
			while(rs.next()){
				Album album = new Album();//声明专辑对象
				album.setAlbId(rs.getInt(1));
				album.setArt_id(rs.getInt(2));
				album.setName(rs.getString(3));
				album.setPublishdate(rs.getString(4));
				album.setImage1(rs.getString(5));
				album.setImage2(rs.getString(6));
				album.setCompany(rs.getString(7));
				album.setDescription(rs.getString(8));
				album.setDel(rs.getInt(9));
				albumList.add(album);
			}
			//关闭数据库相关对象
			DBUtil.closeResultSet(rs);
			DBUtil.closeStatement(psmt);
			DBUtil.closeConnection(conn);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return albumList;
	}


	@Override
	//精确搜索
	//根据专辑id搜索标签
	public Tag getTagById(String Id) throws AppException {
		Connection conn = null;//声明数据库连接对象
		PreparedStatement psmt = null;//声明预处理语句
		ResultSet rs = null;//声明结果集合对象
		Tag tag = new Tag();//声明返回的专辑对象
		
		try {
			conn = DBUtil.getConnection();//连接数据库
			String sql = "select * from tag where tag_id = '" + Id + "'";
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();
			if(rs.next()){
				tag.setId(rs.getInt(1));
				tag.setTagname(rs.getString(2));
				tag.setDescription(rs.getString(3));
				tag.setDel(rs.getInt(4));
			}
			//关闭数据库相关对象
			DBUtil.closeResultSet(rs);
			DBUtil.closeStatement(psmt);
			DBUtil.closeConnection(conn);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return tag;
	}
	

	@Override
	//精确搜索
	//根据标签名查询标签
	public Tag getTagByName(String name) throws AppException {
		Connection conn = null;//声明数据库连接对象
		PreparedStatement psmt = null;//声明预处理语句
		ResultSet rs = null;//声明结果集合对象
		Tag tag = new Tag();//声明返回的专辑对象
		
		try {
			conn = DBUtil.getConnection();//连接数据库
			String sql = "select * from tag where tagname = '" + name + "'";
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();
			if(rs.next()){
				tag.setId(rs.getInt(1));
				tag.setTagname(rs.getString(2));
				tag.setDescription(rs.getString(3));
				tag.setDel(rs.getInt(4));
			}
			//关闭数据库相关对象
			DBUtil.closeResultSet(rs);
			DBUtil.closeStatement(psmt);
			DBUtil.closeConnection(conn);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return tag;
	}
	

	@Override
	public int addMusic(Music music) throws AppException {
		Connection conn = null;// 连接声明
		PreparedStatement psmt = null;// 预处理语句声明
		ResultSet rs = null;// 返回结果结合声明
		int res = 0;
		try {
			conn = DBUtil.getConnection();
			// System.out.println("UserDaoImp.save()获取数据库连接成功");
			String sql = "insert into music(tag1,tag2,tag3,alb_id,art_id,name,realname,publishdate,lrc,zone,del,mus_url) values (?,?,?,?,?,?,?,?,?,?,0,?);";
			psmt = conn.prepareStatement(sql);
			System.out.println("sql语句预处理成功");
			psmt.setLong(1, music.getTag1());
			System.out.println(music.getTag1());
			if(music.getTag2()!=0){
				psmt.setLong(2, music.getTag2());
			}
			else{
				psmt.setNull(2, Types.INTEGER);
			}
			if(music.getTag3()!=0){
				psmt.setLong(3, music.getTag3());
			}
			else{
				psmt.setNull(3, Types.INTEGER);
			}
			psmt.setLong(4, music.getAlb_id());
			psmt.setLong(5, music.getArt_id());
			psmt.setString(6, music.getName());
			psmt.setString(7, music.getRealname());
			psmt.setString(8, music.getPublishdate());
			psmt.setString(9, music.getLrc());
			psmt.setString(10, music.getZone());
			psmt.setString(11, music.getMusicurl());
			// 7.执行新增操作
			psmt.executeUpdate();
		} catch (SQLException e) {
			// 9.异常处理
			e.printStackTrace();
		} finally {
			// 10.关闭数据库连接
			try {
				DBUtil.closeStatement(psmt);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				DBUtil.closeConnection(conn);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		try {
			conn = DBUtil.getConnection();
			String sql = "select max(mus_id) from music;";
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();
			rs.next();
			res = rs.getInt(1);
			// 8.获取操作结果，设置flag
		} catch (SQLException e) {
			// 9.异常处理
			e.printStackTrace();
		} finally {
			// 10.关闭数据库连接
			try {
				DBUtil.closeStatement(psmt);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				DBUtil.closeConnection(conn);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return res;
	}

	@Override
	public int addArtist(Artist artist) throws AppException {
		Connection conn = null;// 连接声明
		PreparedStatement psmt = null;// 预处理语句声明
		ResultSet rs = null;// 返回结果结合声明
		int res = 0;
		try {
			conn = DBUtil.getConnection();
			// System.out.println("UserDaoImp.save()获取数据库连接成功");
			String sql = "insert into artist(name,image1,image2,description,category,gender,del) values(?,?,?,?,?,?,0);";
			psmt = conn.prepareStatement(sql);
			System.out.println("sql语句预处理成功");
			// 6.为参数设置值
			psmt.setString(1, artist.getName());
			psmt.setString(2, artist.getImage1());
			psmt.setString(3, artist.getImage2());
			psmt.setString(4, artist.getDescription());
			psmt.setString(5, artist.getCategory());
			psmt.setString(6, artist.getGender());
			// 7.执行新增操作
			psmt.executeUpdate();
		} catch (SQLException e) {
			// 9.异常处理
			e.printStackTrace();
		} finally {
			// 10.关闭数据库连接
			try {
				DBUtil.closeStatement(psmt);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				DBUtil.closeConnection(conn);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		try {
			conn = DBUtil.getConnection();
			// System.out.println("UserDaoImp.save()获取数据库连接成功");
			String sql = "select max(art_id) from artist;";
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();
			rs.next();
			res = rs.getInt(1);
			// 8.获取操作结果，设置flag
		} catch (SQLException e) {
			// 9.异常处理
			e.printStackTrace();
		} finally {
			// 10.关闭数据库连接
			try {
				DBUtil.closeStatement(psmt);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				DBUtil.closeConnection(conn);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return res;
	}

	@Override
	public int addAlbum(Album album) throws AppException {
		Connection conn = null;// 连接声明
		PreparedStatement psmt = null;// 预处理语句声明
		ResultSet rs = null;// 返回结果结合声明
		int res = 0;
		try {
			conn = DBUtil.getConnection();
			// System.out.println("UserDaoImp.save()获取数据库连接成功");
			String sql = "insert into album (art_id,name,publishdate,image1,image2,company,description,del) values(?,?,?,?,?,?,?,0);";
			psmt = conn.prepareStatement(sql);
			System.out.println("sql语句预处理成功");
			// 6.为参数设置值
			psmt.setLong(1, album.getArt_id());
			psmt.setString(2, album.getName());
			psmt.setString(3, album.getPublishdate());
			psmt.setString(4, album.getImage1());
			psmt.setString(5, album.getImage2());
			psmt.setString(6, album.getCompany());
			psmt.setString(7, album.getDescription());

			// 7.执行新增操作
			int result = psmt.executeUpdate();
			System.out.println("本次操作影响" + result + "行");
			sql = "select max(alb_id) from album;";
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();
			rs.next();
			res = rs.getInt(1);
			// 8.获取操作结果，设置flag
		} catch (SQLException e) {
			// 9.异常处理
			e.printStackTrace();
			return 0;
		} finally {
			// 10.关闭数据库连接
			try {
				DBUtil.closeStatement(psmt);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				DBUtil.closeConnection(conn);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return res;
	}

	@Override
	public int addPopularity(Popularity pop) throws AppException {
		Connection conn = null;// 连接声明
		PreparedStatement psmt = null;// 预处理语句声明
		ResultSet rs = null;// 返回结果结合声明
		int res = 0;
		try {
			conn = DBUtil.getConnection();
			// System.out.println("UserDaoImp.save()获取数据库连接成功");
			String sql = "insert into popularity (obj_id,down_count,hits_count,type,del) values(?,?,?,?,0);";
			psmt = conn.prepareStatement(sql);
			System.out.println("sql语句预处理成功");
			// 6.为参数设置值
			psmt.setLong(1, pop.getObj_id());
			psmt.setLong(2, pop.getDown_count());
			psmt.setLong(3, pop.getHit_count());
			psmt.setLong(4, pop.getType());
			// 7.执行新增操作
			int result = psmt.executeUpdate();
			System.out.println("本次操作影响" + result + "行");
			sql = "select max(pop_id) from popularity;";
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();
			rs.next();
			res = rs.getInt(1);
			// 8.获取操作结果，设置flag
		} catch (SQLException e) {
			// 9.异常处理
			e.printStackTrace();
			return 0;
		} finally {
			// 10.关闭数据库连接
			try {
				DBUtil.closeStatement(psmt);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return 0;
			}
			try {
				DBUtil.closeConnection(conn);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return 0;
			}
		}
		return res;
	}

	@Override
	public boolean delMusic(String id) throws AppException {
		Connection conn = null;// 连接声明
		PreparedStatement psmt = null;// 预处理语句声明
		try {
			conn = DBUtil.getConnection();
			// System.out.println("UserDaoImp.save()获取数据库连接成功");
			String sql = "update music set del = 1 where mus_id = ?";
			psmt = conn.prepareStatement(sql);
			System.out.println("sql语句预处理成功");
			// 6.为参数设置值
			psmt.setLong(1, Integer.parseInt(id));

			// 7.执行新增操作
			psmt.executeUpdate();
			// 8.获取操作结果，设置flag
		} catch (SQLException e) {
			// 9.异常处理
			e.printStackTrace();
			return false;
		} finally {
			// 10.关闭数据库连接
			try {
				DBUtil.closeStatement(psmt);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return false;
			}
			try {
				DBUtil.closeConnection(conn);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return false;
			}
		}
		try {
			conn = DBUtil.getConnection();
			String sql = "update popularity set del = 1 where obj_id = ? and type = 3";
			psmt = conn.prepareStatement(sql);
			System.out.println("sql语句预处理成功");
			psmt.setLong(1, Integer.parseInt(id));
			psmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			try {
				DBUtil.closeStatement(psmt);
			} catch (SQLException e) {
				e.printStackTrace();
				return false;
			}
			try {
				DBUtil.closeConnection(conn);
			} catch (SQLException e) {
				e.printStackTrace();
				return false;
			}
		}
		return true;
	}

	@Override
	public boolean resetMusic(Music music) throws AppException {
		Connection conn = null;// 连接声明
		PreparedStatement psmt = null;// 预处理语句声明
		ResultSet rs = null;// 返回结果结合声明
		try {
			conn = DBUtil.getConnection();
			// System.out.println("UserDaoImp.save()获取数据库连接成功");
			String sql = "update music set tag1 = ?,tag2 = ?,tag3 = ?,alb_id = ?,art_id = ?,name = ?,realname = ?,publishdate = ?,lrc = ?,zone = ?,mus_url = ?where mus_id = ?;";
			psmt = conn.prepareStatement(sql);
			psmt.setLong(1, music.getTag1());
			if(music.getTag2()!=0){
				psmt.setLong(2, music.getTag2());
			}
			else{
				psmt.setNull(2, Types.INTEGER);
			}
			if(music.getTag3()!=0){
				psmt.setLong(3, music.getTag3());
			}
			else{
				psmt.setNull(3, Types.INTEGER);
			}
			psmt.setLong(4, music.getAlb_id());
			psmt.setLong(5, music.getArt_id());
			psmt.setString(6, music.getName());
			psmt.setString(7, music.getRealname());
			psmt.setString(8, music.getPublishdate());
			psmt.setString(9, music.getLrc());
			psmt.setString(10, music.getZone());
			psmt.setString(11, music.getMusicurl());
			psmt.setLong(12, music.getMusId());
			// 7.执行新增操作
			int result = psmt.executeUpdate();
			System.out.println("本次操作影响" + result + "行");
			// 8.获取操作结果，设置flag
		} catch (SQLException e) {
			// 9.异常处理
			e.printStackTrace();
			return false;
		} finally {
			// 10.关闭数据库连接
			try {
				DBUtil.closeStatement(psmt);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return false;
			}
			try {
				DBUtil.closeConnection(conn);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return false;
			}
		}
		return true;
	}

	@Override
	public void resetAritist(Artist artist) throws AppException {
		Connection conn = null;// 连接声明
		PreparedStatement psmt = null;// 预处理语句声明
		ResultSet rs = null;// 返回结果结合声明
		try {
			conn = DBUtil.getConnection();
			// System.out.println("UserDaoImp.save()获取数据库连接成功");
			String sql = "update artist set name = ?,image1 = ?,image2 = ?,description = ?,category = ?,gender = ?where art_id = ?;";
			psmt = conn.prepareStatement(sql);
			System.out.println("sql语句预处理成功");
			// 6.为参数设置值
			psmt.setString(1, artist.getName());
			psmt.setString(2, artist.getImage1());
			psmt.setString(3, artist.getImage2());
			psmt.setString(4, artist.getDescription());
			psmt.setString(5, artist.getCategory());
			psmt.setString(6, artist.getGender());
			psmt.setLong(7, artist.getArtId());

			// 7.执行新增操作
			int result = psmt.executeUpdate();
			System.out.println("本次操作影响" + result + "行");
			// 8.获取操作结果，设置flag
		} catch (SQLException e) {
			// 9.异常处理
			e.printStackTrace();
		} finally {
			// 10.关闭数据库连接
			try {
				DBUtil.closeStatement(psmt);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				DBUtil.closeConnection(conn);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	@Override
	public void resetAlbum(Album album) throws AppException {
		Connection conn = null;// 连接声明
		PreparedStatement psmt = null;// 预处理语句声明
		ResultSet rs = null;// 返回结果结合声明
		try {
			conn = DBUtil.getConnection();
			// System.out.println("UserDaoImp.save()获取数据库连接成功");
			String sql = "update album set art_id = ?,name = ?,publishdate = ?,image1 = ?,image2 = ?,company = ?,description = ?where alb_id = ?;";
			psmt = conn.prepareStatement(sql);
			System.out.println("sql语句预处理成功");
			// 6.为参数设置值
			psmt.setLong(1, album.getArt_id());
			psmt.setString(2, album.getName());
			psmt.setString(3, album.getPublishdate());
			psmt.setString(4, album.getImage1());
			psmt.setString(5, album.getImage2());
			psmt.setString(6, album.getCompany());
			psmt.setString(7, album.getDescription());
			psmt.setLong(8, album.getAlbId());

			// 7.执行新增操作
			int result = psmt.executeUpdate();
			System.out.println("本次操作影响" + result + "行");
			// 8.获取操作结果，设置flag
		} catch (SQLException e) {
			// 9.异常处理
			e.printStackTrace();
		} finally {
			// 10.关闭数据库连接
			try {
				DBUtil.closeStatement(psmt);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				DBUtil.closeConnection(conn);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	@Override
	//精确
	public MusicBusiModel getMusicByRealname(String realname) throws AppException {

		Connection conn = null;//声明数据库连接
		PreparedStatement psmt = null;//声明预处理语句
		ResultSet rs,rs1 = null;//声明结果集合		
		MusicBusiModel mbmd = new MusicBusiModel();//声明MusicBusiModel对象
		
		try{
			//获得所有tag和对应的编号
			//使用hashmap存放tag.tag_id -> tag.tagname
			conn = DBUtil.getConnection();//获得数据库连接
			String sql1 = "select * from tag;";//获取tag信息的sql语句
			psmt = conn.prepareStatement(sql1);//预处理获取tag信息的sql语句
			rs1 = psmt.executeQuery(sql1);//执行获取tag信息的语句
			Map map = new HashMap();//用hashMap存储
			while(rs1.next()){
				map.put(rs1.getInt(1), rs1.getString(2));
				//System.out.println(rs1.getString(1)+rs1.getString(2)+rs1.getString(3)+rs1.getString(4));
			}
			String sql = "select "
					+ "music.mus_id,music.name,music.realname,music.lrc,music.zone,music.publishdate,music.tag1,music.tag2,music.tag3,music.mus_url,"
					//3.int			4.string   5.string       6.string  7.string   8.string          9.int      10.int     11.int     12.string
					+ "artist.name,artist.image1, "
					//13.string    14.string
					+ "album.name,album.image1,"
					//15.string    16.string
					+ "popularity.down_count,popularity.hits_count "
					//1.int					2.int
					+ "from music,artist,album,popularity "
					+ "where music.realname = "
					+ " '"
					+ realname
					+ "' "
					+ "and artist.art_id = music.art_id "
					+ "and album.art_id = artist.art_id "
					+ "and album.alb_id = music.alb_id "
					+ "and popularity.type = 3 "
					+ "and popularity.obj_id = music.mus_id;";
			//System.out.println(sql);
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery(sql);
			System.out.println("执行sql语句");
			while(rs.next()){
				//设置MusicBusiModel
				mbmd.setDown_count(rs.getInt(15));
				mbmd.setHit_count(rs.getInt(16));
				mbmd.setId(rs.getInt(1));
				mbmd.setName(rs.getString(2));
				//System.out.println("歌曲名:"+rs.getString(2));
				mbmd.setRealname(rs.getString(3));
				mbmd.setLrc(rs.getString(4));
				mbmd.setZone(rs.getString(5));
				mbmd.setPublishdate(rs.getString(6));
				mbmd.setTag1(map.get(rs.getInt(7)).toString());
				//tag2、tag3可为null
				if(rs.getInt(8)!=0){
					mbmd.setTag2(map.get(rs.getInt(8)).toString());
				}
				else {
					mbmd.setTag2("");
				}
				if(rs.getInt(9)!=0){
					mbmd.setTag3(map.get(rs.getInt(9)).toString());
				}
				else {
					mbmd.setTag3("");
				}
				mbmd.setMusicurl(rs.getString(10));
				mbmd.setArtist(rs.getString(11));
				mbmd.setArtist_photo(rs.getString(12));
				mbmd.setAlbum(rs.getString(13));
				mbmd.setArtist_photo(rs.getString(14));
			}
			//关闭结果集合，语句和连接
			DBUtil.closeResultSet(rs);
			DBUtil.closeResultSet(rs1);
			DBUtil.closeStatement(psmt);
			DBUtil.closeConnection(conn);
		} catch(SQLException e){
			e.printStackTrace();
		} 
		return mbmd;
	}
	

}
