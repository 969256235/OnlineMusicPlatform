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
 * 闊充箰鏁版嵁璁块棶灞傚疄鐜扮被
 */
public class MusicDaoImp implements MusicDao {

	@Override
	public ArrayList<MusicBusiModel> getMostPopMusic() throws AppException {
		Connection conn = null;// 澹版槑鏁版嵁搴撹繛鎺�
		PreparedStatement psmt = null;// 澹版槑棰勫鐞嗚鍙�
		ResultSet rs, rs1 = null;// 澹版槑缁撴灉闆嗗悎

		// 鏌ヨ鐐瑰嚮閲忓墠鍗佺殑姝屾洸骞朵笖杩斿洖MusicBusiModel鐨勬暟缁�
		ArrayList<MusicBusiModel> mbml = new ArrayList<MusicBusiModel>();// 澹版槑杩斿洖缁撴灉
		try {
			// 鑾峰緱鎵�鏈塼ag鍜屽搴旂殑缂栧彿
			// 浣跨敤hashmap瀛樻斁tag.tag_id -> tag.tagname
			conn = DBUtil.getConnection();// 鑾峰緱鏁版嵁搴撹繛鎺�
			String sql1 = "select * from tag;";// 鑾峰彇tag淇℃伅鐨剆ql璇彞
			psmt = conn.prepareStatement(sql1);// 棰勫鐞嗚幏鍙杢ag淇℃伅鐨剆ql璇彞
			rs1 = psmt.executeQuery(sql1);// 鎵ц鑾峰彇tag淇℃伅鐨勮鍙�
			Map map = new HashMap();// 鐢╤ashMap瀛樺偍
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
			System.out.println("鎵цsql璇彞");
			while (rs.next()) {
				MusicBusiModel mbmd = new MusicBusiModel();
				// 璁剧疆MusicBusiModel
				mbmd.setDown_count(rs.getInt(1));
				mbmd.setHit_count(rs.getInt(2));
				mbmd.setId(rs.getInt(3));
				mbmd.setName(rs.getString(4));
				mbmd.setRealname(rs.getString(5));
				mbmd.setLrc(rs.getString(6));
				mbmd.setZone(rs.getString(7));
				mbmd.setPublishdate(rs.getString(8));
				mbmd.setTag1(map.get(rs.getInt(9)).toString());
				if (rs.getInt(10) != 0)
					mbmd.setTag2(map.get(rs.getInt(10)).toString());
				else {
					mbmd.setTag2("");
				}
				if (rs.getInt(11) != 0)
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
			// 10.鍏抽棴鏁版嵁搴撹繛鎺�
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
		Connection conn = null;// 澹版槑鏁版嵁搴撹繛鎺�
		PreparedStatement psmt = null;// 澹版槑棰勫鐞嗚鍙�
		ResultSet rs, rs1 = null;// 澹版槑缁撴灉闆嗗悎

		// 鏌ヨ鐐瑰嚮閲忓墠鍗佺殑姝屾洸骞朵笖杩斿洖MusicBusiModel鐨勬暟缁�
		ArrayList<MusicBusiModel> mbml = new ArrayList<MusicBusiModel>();// 澹版槑杩斿洖缁撴灉
		try {
			// 鑾峰緱鎵�鏈塼ag鍜屽搴旂殑缂栧彿
			// 浣跨敤hashmap瀛樻斁tag.tag_id -> tag.tagname
			conn = DBUtil.getConnection();// 鑾峰緱鏁版嵁搴撹繛鎺�
			String sql1 = "select * from tag;";// 鑾峰彇tag淇℃伅鐨剆ql璇彞
			psmt = conn.prepareStatement(sql1);// 棰勫鐞嗚幏鍙杢ag淇℃伅鐨剆ql璇彞
			rs1 = psmt.executeQuery(sql1);// 鎵ц鑾峰彇tag淇℃伅鐨勮鍙�
			Map map = new HashMap();// 鐢╤ashMap瀛樺偍
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
			System.out.println("鎵цsql璇彞");
			while (rs.next()) {
				MusicBusiModel mbmd = new MusicBusiModel();
				// 璁剧疆MusicBusiModel
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
			// 10.鍏抽棴鏁版嵁搴撹繛鎺�
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
			// 10.鍏抽棴鏁版嵁搴撹繛鎺�
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

	// 鏃犺姝屾洸鏄惁瀛樺湪鍧囪繑鍥濵usicBusiModel瀵硅薄
	// 鏍规嵁id鏌ヨ姝屾洸骞惰繑鍥濵usicBusiModel
	// 鑻ヤ笉瀛樺湪MusicBusiModel瀵硅薄锛屼絾鍏跺唴瀹逛负null
	@Override
	public MusicBusiModel getMusicById(String Id) throws AppException {
		Connection conn = null;// 澹版槑鏁版嵁搴撹繛鎺�
		PreparedStatement psmt = null;// 澹版槑棰勫鐞嗚鍙�
		ResultSet rs, rs1 = null;// 澹版槑缁撴灉闆嗗悎
		MusicBusiModel mbmd = new MusicBusiModel();// 澹版槑杩斿洖鐨凪usicBusiModel瀵硅薄

		try {
			// 鑾峰緱鎵�鏈塼ag鍜屽搴旂殑缂栧彿
			// 浣跨敤hashmap瀛樻斁tag.tag_id -> tag.tagname
			conn = DBUtil.getConnection();// 鑾峰緱鏁版嵁搴撹繛鎺�
			String sql1 = "select * from tag;";// 鑾峰彇tag淇℃伅鐨剆ql璇彞
			psmt = conn.prepareStatement(sql1);// 棰勫鐞嗚幏鍙杢ag淇℃伅鐨剆ql璇彞
			rs1 = psmt.executeQuery(sql1);// 鎵ц鑾峰彇tag淇℃伅鐨勮鍙�
			Map map = new HashMap();// 鐢╤ashMap瀛樺偍
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
			System.out.println("鎵цsql璇彞");
			if (rs.next()) {
				// 璁剧疆MusicBusiModel
				mbmd.setDown_count(rs.getInt(15));
				mbmd.setHit_count(rs.getInt(16));
				mbmd.setId(rs.getInt(1));
				mbmd.setName(rs.getString(2));
				System.out.println("姝屾洸鍚�:" + rs.getString(2));
				mbmd.setRealname(rs.getString(3));
				mbmd.setLrc(rs.getString(4));
				mbmd.setZone(rs.getString(5));
				mbmd.setPublishdate(rs.getString(6));
				mbmd.setTag1(map.get(rs.getInt(7)).toString());
				// tag2銆乼ag3鍙负null
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
				// 鍏抽棴缁撴灉闆嗗悎锛岃鍙ュ拰杩炴帴
				DBUtil.closeResultSet(rs);
				DBUtil.closeResultSet(rs1);
				DBUtil.closeStatement(psmt);
				DBUtil.closeConnection(conn);
			} else {
				System.out.println("鏌ヨ鏃犵粨鏋�");
				// 鍏抽棴缁撴灉闆嗗悎锛岃鍙ュ拰杩炴帴
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

	// 鏃犺姝屾洸鏄惁瀛樺湪鍧囪繑鍥濵usicBusiModel瀵硅薄
	// 鏍规嵁姝屾洸鍚嶇О鏌ヨ姝屾洸骞惰繑鍥濵usicBusiModel
	// 鑻ヤ笉瀛樺湪MusicBusiModel瀵硅薄锛屼絾鍏跺唴瀹逛负null
	// 妯＄硦鎼滅储锛屽瓨鍦ㄧ洰鏍囧瓧娈电殑姝屾洸閮戒細琚悳绱�
	@Override
	public ArrayList<MusicBusiModel> getMusicByName(String name) throws AppException {
		Connection conn = null;// 澹版槑鏁版嵁搴撹繛鎺�
		PreparedStatement psmt = null;// 澹版槑棰勫鐞嗚鍙�
		ResultSet rs, rs1 = null;// 澹版槑缁撴灉闆嗗悎
		ArrayList<MusicBusiModel> mbml = new ArrayList<MusicBusiModel>();// 澹版槑杩斿洖缁撴灉

		try {
			// 鑾峰緱鎵�鏈塼ag鍜屽搴旂殑缂栧彿
			// 浣跨敤hashmap瀛樻斁tag.tag_id -> tag.tagname
			conn = DBUtil.getConnection();// 鑾峰緱鏁版嵁搴撹繛鎺�
			String sql1 = "select * from tag;";// 鑾峰彇tag淇℃伅鐨剆ql璇彞
			psmt = conn.prepareStatement(sql1);// 棰勫鐞嗚幏鍙杢ag淇℃伅鐨剆ql璇彞
			rs1 = psmt.executeQuery(sql1);// 鎵ц鑾峰彇tag淇℃伅鐨勮鍙�
			Map map = new HashMap();// 鐢╤ashMap瀛樺偍
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
			System.out.println("鎵цsql璇彞");
			while (rs.next()) {
				MusicBusiModel mbmd = new MusicBusiModel();// 澹版槑MusicBusiModel瀵硅薄
				// 璁剧疆MusicBusiModel
				mbmd.setDown_count(rs.getInt(15));
				mbmd.setHit_count(rs.getInt(16));
				mbmd.setId(rs.getInt(1));
				mbmd.setName(rs.getString(2));
				// System.out.println("姝屾洸鍚�:"+rs.getString(2));
				mbmd.setRealname(rs.getString(3));
				mbmd.setLrc(rs.getString(4));
				mbmd.setZone(rs.getString(5));
				mbmd.setPublishdate(rs.getString(6));
				mbmd.setTag1(map.get(rs.getInt(7)).toString());
				// tag2銆乼ag3鍙负null
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
			// 鍏抽棴缁撴灉闆嗗悎锛岃鍙ュ拰杩炴帴
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
	//妯＄硦鎼滅储
	//鏍规嵁姝屾墜鍚嶆煡璇㈡瓕鏇�
	public ArrayList<MusicBusiModel> getMusicByArtist(String art_name) throws AppException {
		Connection conn = null;//澹版槑鏁版嵁搴撹繛鎺�
		PreparedStatement psmt = null;//澹版槑棰勫鐞嗚鍙�
		ResultSet rs,rs1 = null;//澹版槑缁撴灉闆嗗悎		
		ArrayList<MusicBusiModel> mbml = new ArrayList<MusicBusiModel>();//澹版槑杩斿洖缁撴灉
		
		try{
			//鑾峰緱鎵�鏈塼ag鍜屽搴旂殑缂栧彿
			//浣跨敤hashmap瀛樻斁tag.tag_id -> tag.tagname
			conn = DBUtil.getConnection();//鑾峰緱鏁版嵁搴撹繛鎺�
			String sql1 = "select * from tag;";//鑾峰彇tag淇℃伅鐨剆ql璇彞
			psmt = conn.prepareStatement(sql1);//棰勫鐞嗚幏鍙杢ag淇℃伅鐨剆ql璇彞
			rs1 = psmt.executeQuery(sql1);//鎵ц鑾峰彇tag淇℃伅鐨勮鍙�
			Map map = new HashMap();//鐢╤ashMap瀛樺偍
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
			System.out.println("鎵цsql璇彞");
			while(rs.next()){
				MusicBusiModel mbmd = new MusicBusiModel();//澹版槑MusicBusiModel瀵硅薄
				//璁剧疆MusicBusiModel
				mbmd.setDown_count(rs.getInt(15));
				mbmd.setHit_count(rs.getInt(16));
				mbmd.setId(rs.getInt(3));
				mbmd.setName(rs.getString(4));
				//System.out.println("姝屾洸鍚�:"+rs.getString(2));
				mbmd.setRealname(rs.getString(5));
				mbmd.setLrc(rs.getString(6));
				mbmd.setZone(rs.getString(7));
				mbmd.setPublishdate(rs.getString(8));
				mbmd.setTag1(map.get(rs.getInt(9)).toString());
				//tag2銆乼ag3鍙负null
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
			//鍏抽棴缁撴灉闆嗗悎锛岃鍙ュ拰杩炴帴
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
	//妯＄硦鎼滅储
	//閫氳繃涓撹緫鍚嶆悳绱㈡瓕鏇�
	public ArrayList<MusicBusiModel> getMusicByAlbum(String alb_name) throws AppException {
		Connection conn = null;//澹版槑鏁版嵁搴撹繛鎺�
		PreparedStatement psmt = null;//澹版槑棰勫鐞嗚鍙�
		ResultSet rs,rs1 = null;//澹版槑缁撴灉闆嗗悎		
		ArrayList<MusicBusiModel> mbml = new ArrayList<MusicBusiModel>();//澹版槑杩斿洖缁撴灉
		
		try{
			//鑾峰緱鎵�鏈塼ag鍜屽搴旂殑缂栧彿
			//浣跨敤hashmap瀛樻斁tag.tag_id -> tag.tagname
			conn = DBUtil.getConnection();//鑾峰緱鏁版嵁搴撹繛鎺�
			String sql1 = "select * from tag;";//鑾峰彇tag淇℃伅鐨剆ql璇彞
			psmt = conn.prepareStatement(sql1);//棰勫鐞嗚幏鍙杢ag淇℃伅鐨剆ql璇彞
			rs1 = psmt.executeQuery(sql1);//鎵ц鑾峰彇tag淇℃伅鐨勮鍙�
			Map map = new HashMap();//鐢╤ashMap瀛樺偍
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
			System.out.println("鎵цsql璇彞");
			while(rs.next()){
				MusicBusiModel mbmd = new MusicBusiModel();//澹版槑MusicBusiModel瀵硅薄
				//璁剧疆MusicBusiModel
				mbmd.setDown_count(rs.getInt(15));
				mbmd.setHit_count(rs.getInt(16));
				mbmd.setId(rs.getInt(5));
				mbmd.setName(rs.getString(6));
				//System.out.println("姝屾洸鍚�:"+rs.getString(2));
				mbmd.setRealname(rs.getString(7));
				mbmd.setLrc(rs.getString(8));
				mbmd.setZone(rs.getString(9));
				mbmd.setPublishdate(rs.getString(10));
				mbmd.setTag1(map.get(rs.getInt(11)).toString());
				//tag2銆乼ag3鍙负null
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
			//鍏抽棴缁撴灉闆嗗悎锛岃鍙ュ拰杩炴帴
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
	//绮剧‘
	//閫氳繃姝屾墜id鎼滅储姝屾墜
	public Artist getArtistById(String Id) throws AppException {
		Connection conn = null;//澹版槑鏁版嵁搴撹繛鎺ュ璞�
		PreparedStatement psmt = null;//澹版槑棰勫鐞嗚鍙ュ璞�
		ResultSet rs = null;//澹版槑缁撴灉闆嗗璞�
		Artist artist = new Artist();//澹版槑杩斿洖鐨勫璞�
		
		try {
			conn = DBUtil.getConnection();//鑾峰緱鏁版嵁搴撹繛鎺�
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
	//妯＄硦鎼滅储
	//鏍规嵁妫�绱俊鎭鎵惧寘鍚绱俊鎭殑姝屾墜鍒楄〃
	public ArrayList<Artist> getArtistByName(String name) throws AppException {
		Connection conn = null;//澹版槑鏁版嵁搴撹繛鎺ュ璞�
		PreparedStatement psmt = null;//澹版槑棰勫鐞嗚鍙ュ璞�
		ResultSet rs = null;//澹版槑缁撴灉闆嗗璞�
		ArrayList<Artist> artList = new ArrayList<Artist>();
		
		try {
			conn = DBUtil.getConnection();//鑾峰緱鏁版嵁搴撹繛鎺�
			String sql = "select * from artist where name like '%" + name +"%'";
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();
			while(rs.next()){
				Artist artist = new Artist();//澹版槑杩斿洖鐨勫璞�
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
	//绮剧‘鎼滅储
	//鏍规嵁涓撹緫id鎼滅储涓撹緫
	public Album getAlbumById(String Id) throws AppException {
		Connection conn = null;//澹版槑鏁版嵁搴撹繛鎺ュ璞�
		PreparedStatement psmt = null;//澹版槑棰勫鐞嗚鍙�
		ResultSet rs = null;//澹版槑缁撴灉闆嗗悎瀵硅薄
		Album album = new Album();//澹版槑杩斿洖鐨勪笓杈戝璞�
		
		try {
			conn = DBUtil.getConnection();//杩炴帴鏁版嵁搴�
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
			//鍏抽棴鏁版嵁搴撶浉鍏冲璞�
			DBUtil.closeResultSet(rs);
			DBUtil.closeStatement(psmt);
			DBUtil.closeConnection(conn);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return album;
	}
	
	@Override
	//妯＄硦鎼滅储
	//鏍规嵁妫�绱俊鎭鎵惧寘鍚绱俊鎭殑涓撹緫鍒楄〃
	public ArrayList<Album> getAlbumByName(String name) throws AppException {
		Connection conn = null;//澹版槑鏁版嵁搴撹繛鎺ュ璞�
		PreparedStatement psmt = null;//澹版槑棰勫鐞嗚鍙�
		ResultSet rs = null;//澹版槑缁撴灉闆嗗悎瀵硅薄
		ArrayList<Album> albumList = new ArrayList<Album>();
		
		try {
			conn = DBUtil.getConnection();//杩炴帴鏁版嵁搴�
			String sql = "select * from album where name like '%" + name + "%'";
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();
			while(rs.next()){
				Album album = new Album();//澹版槑涓撹緫瀵硅薄
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
			//鍏抽棴鏁版嵁搴撶浉鍏冲璞�
			DBUtil.closeResultSet(rs);
			DBUtil.closeStatement(psmt);
			DBUtil.closeConnection(conn);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return albumList;
	}


	@Override
	//绮剧‘鎼滅储
	//鏍规嵁涓撹緫id鎼滅储鏍囩
	public Tag getTagById(String Id) throws AppException {
		Connection conn = null;//澹版槑鏁版嵁搴撹繛鎺ュ璞�
		PreparedStatement psmt = null;//澹版槑棰勫鐞嗚鍙�
		ResultSet rs = null;//澹版槑缁撴灉闆嗗悎瀵硅薄
		Tag tag = new Tag();//澹版槑杩斿洖鐨勪笓杈戝璞�
		
		try {
			conn = DBUtil.getConnection();//杩炴帴鏁版嵁搴�
			String sql = "select * from tag where tag_id = '" + Id + "'";
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();
			if(rs.next()){
				tag.setId(rs.getInt(1));
				tag.setTagname(rs.getString(2));
				tag.setDescription(rs.getString(3));
				tag.setDel(rs.getInt(4));
			}
			//鍏抽棴鏁版嵁搴撶浉鍏冲璞�
			DBUtil.closeResultSet(rs);
			DBUtil.closeStatement(psmt);
			DBUtil.closeConnection(conn);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return tag;
	}
	

	@Override
	//绮剧‘鎼滅储
	//鏍规嵁鏍囩鍚嶆煡璇㈡爣绛�
	public Tag getTagByName(String name) throws AppException {
		Connection conn = null;//澹版槑鏁版嵁搴撹繛鎺ュ璞�
		PreparedStatement psmt = null;//澹版槑棰勫鐞嗚鍙�
		ResultSet rs = null;//澹版槑缁撴灉闆嗗悎瀵硅薄
		Tag tag = new Tag();//澹版槑杩斿洖鐨勪笓杈戝璞�
		
		try {
			conn = DBUtil.getConnection();//杩炴帴鏁版嵁搴�
			String sql = "select * from tag where tagname = '" + name + "'";
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();
			if(rs.next()){
				tag.setId(rs.getInt(1));
				tag.setTagname(rs.getString(2));
				tag.setDescription(rs.getString(3));
				tag.setDel(rs.getInt(4));
			}
			//鍏抽棴鏁版嵁搴撶浉鍏冲璞�
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
		Connection conn = null;// 杩炴帴澹版槑
		PreparedStatement psmt = null;// 棰勫鐞嗚鍙ュ０鏄�
		ResultSet rs = null;// 杩斿洖缁撴灉缁撳悎澹版槑
		int res = 0;
		try {
			conn = DBUtil.getConnection();
			// System.out.println("UserDaoImp.save()鑾峰彇鏁版嵁搴撹繛鎺ユ垚鍔�");
			String sql = "insert into music(tag1,tag2,tag3,alb_id,art_id,name,realname,publishdate,lrc,zone,del,mus_url) values (?,?,?,?,?,?,?,?,?,?,0,?);";
			psmt = conn.prepareStatement(sql);
			System.out.println("sql璇彞棰勫鐞嗘垚鍔�");
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
			// 7.鎵ц鏂板鎿嶄綔
			psmt.executeUpdate();
		} catch (SQLException e) {
			// 9.寮傚父澶勭悊
			e.printStackTrace();
		} finally {
			// 10.鍏抽棴鏁版嵁搴撹繛鎺�
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
			// 8.鑾峰彇鎿嶄綔缁撴灉锛岃缃甪lag
		} catch (SQLException e) {
			// 9.寮傚父澶勭悊
			e.printStackTrace();
		} finally {
			// 10.鍏抽棴鏁版嵁搴撹繛鎺�
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
		Connection conn = null;// 杩炴帴澹版槑
		PreparedStatement psmt = null;// 棰勫鐞嗚鍙ュ０鏄�
		ResultSet rs = null;// 杩斿洖缁撴灉缁撳悎澹版槑
		int res = 0;
		try {
			conn = DBUtil.getConnection();
			// System.out.println("UserDaoImp.save()鑾峰彇鏁版嵁搴撹繛鎺ユ垚鍔�");
			String sql = "insert into artist(name,image1,image2,description,category,gender,del) values(?,?,?,?,?,?,0);";
			psmt = conn.prepareStatement(sql);
			System.out.println("sql璇彞棰勫鐞嗘垚鍔�");
			// 6.涓哄弬鏁拌缃��
			psmt.setString(1, artist.getName());
			psmt.setString(2, artist.getImage1());
			psmt.setString(3, artist.getImage2());
			psmt.setString(4, artist.getDescription());
			psmt.setString(5, artist.getCategory());
			psmt.setString(6, artist.getGender());
			// 7.鎵ц鏂板鎿嶄綔
			psmt.executeUpdate();
		} catch (SQLException e) {
			// 9.寮傚父澶勭悊
			e.printStackTrace();
		} finally {
			// 10.鍏抽棴鏁版嵁搴撹繛鎺�
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
			// System.out.println("UserDaoImp.save()鑾峰彇鏁版嵁搴撹繛鎺ユ垚鍔�");
			String sql = "select max(art_id) from artist;";
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();
			rs.next();
			res = rs.getInt(1);
			// 8.鑾峰彇鎿嶄綔缁撴灉锛岃缃甪lag
		} catch (SQLException e) {
			// 9.寮傚父澶勭悊
			e.printStackTrace();
		} finally {
			// 10.鍏抽棴鏁版嵁搴撹繛鎺�
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
		Connection conn = null;// 杩炴帴澹版槑
		PreparedStatement psmt = null;// 棰勫鐞嗚鍙ュ０鏄�
		ResultSet rs = null;// 杩斿洖缁撴灉缁撳悎澹版槑
		int res = 0;
		try {
			conn = DBUtil.getConnection();
			// System.out.println("UserDaoImp.save()鑾峰彇鏁版嵁搴撹繛鎺ユ垚鍔�");
			String sql = "insert into album (art_id,name,publishdate,image1,image2,company,description,del) values(?,?,?,?,?,?,?,0);";
			psmt = conn.prepareStatement(sql);
			System.out.println("sql璇彞棰勫鐞嗘垚鍔�");
			// 6.涓哄弬鏁拌缃��
			psmt.setLong(1, album.getArt_id());
			psmt.setString(2, album.getName());
			psmt.setString(3, album.getPublishdate());
			psmt.setString(4, album.getImage1());
			psmt.setString(5, album.getImage2());
			psmt.setString(6, album.getCompany());
			psmt.setString(7, album.getDescription());

			// 7.鎵ц鏂板鎿嶄綔
			int result = psmt.executeUpdate();
			System.out.println("鏈鎿嶄綔褰卞搷" + result + "琛�");
			sql = "select max(alb_id) from album;";
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();
			rs.next();
			res = rs.getInt(1);
			// 8.鑾峰彇鎿嶄綔缁撴灉锛岃缃甪lag
		} catch (SQLException e) {
			// 9.寮傚父澶勭悊
			e.printStackTrace();
			return 0;
		} finally {
			// 10.鍏抽棴鏁版嵁搴撹繛鎺�
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
		Connection conn = null;// 杩炴帴澹版槑
		PreparedStatement psmt = null;// 棰勫鐞嗚鍙ュ０鏄�
		ResultSet rs = null;// 杩斿洖缁撴灉缁撳悎澹版槑
		int res = 0;
		try {
			conn = DBUtil.getConnection();
			// System.out.println("UserDaoImp.save()鑾峰彇鏁版嵁搴撹繛鎺ユ垚鍔�");
			String sql = "insert into popularity (obj_id,down_count,hits_count,type,del) values(?,?,?,?,0);";
			psmt = conn.prepareStatement(sql);
			System.out.println("sql璇彞棰勫鐞嗘垚鍔�");
			// 6.涓哄弬鏁拌缃��
			psmt.setLong(1, pop.getObj_id());
			psmt.setLong(2, pop.getDown_count());
			psmt.setLong(3, pop.getHit_count());
			psmt.setLong(4, pop.getType());
			// 7.鎵ц鏂板鎿嶄綔
			int result = psmt.executeUpdate();
			System.out.println("鏈鎿嶄綔褰卞搷" + result + "琛�");
			sql = "select max(pop_id) from popularity;";
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();
			rs.next();
			res = rs.getInt(1);
			// 8.鑾峰彇鎿嶄綔缁撴灉锛岃缃甪lag
		} catch (SQLException e) {
			// 9.寮傚父澶勭悊
			e.printStackTrace();
			return 0;
		} finally {
			// 10.鍏抽棴鏁版嵁搴撹繛鎺�
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
		Connection conn = null;// 杩炴帴澹版槑
		PreparedStatement psmt = null;// 棰勫鐞嗚鍙ュ０鏄�
		try {
			conn = DBUtil.getConnection();
			// System.out.println("UserDaoImp.save()鑾峰彇鏁版嵁搴撹繛鎺ユ垚鍔�");
			String sql = "update music set del = 1 where mus_id = ?";
			psmt = conn.prepareStatement(sql);
			System.out.println("sql璇彞棰勫鐞嗘垚鍔�");
			// 6.涓哄弬鏁拌缃��
			psmt.setLong(1, Integer.parseInt(id));

			// 7.鎵ц鏂板鎿嶄綔
			psmt.executeUpdate();
			// 8.鑾峰彇鎿嶄綔缁撴灉锛岃缃甪lag
		} catch (SQLException e) {
			// 9.寮傚父澶勭悊
			e.printStackTrace();
			return false;
		} finally {
			// 10.鍏抽棴鏁版嵁搴撹繛鎺�
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
			System.out.println("sql璇彞棰勫鐞嗘垚鍔�");
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
		Connection conn = null;// 杩炴帴澹版槑
		PreparedStatement psmt = null;// 棰勫鐞嗚鍙ュ０鏄�
		ResultSet rs = null;// 杩斿洖缁撴灉缁撳悎澹版槑
		try {
			conn = DBUtil.getConnection();
			// System.out.println("UserDaoImp.save()鑾峰彇鏁版嵁搴撹繛鎺ユ垚鍔�");
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
			// 7.鎵ц鏂板鎿嶄綔
			int result = psmt.executeUpdate();
			System.out.println("鏈鎿嶄綔褰卞搷" + result + "琛�");
			// 8.鑾峰彇鎿嶄綔缁撴灉锛岃缃甪lag
		} catch (SQLException e) {
			// 9.寮傚父澶勭悊
			e.printStackTrace();
			return false;
		} finally {
			// 10.鍏抽棴鏁版嵁搴撹繛鎺�
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
		Connection conn = null;// 杩炴帴澹版槑
		PreparedStatement psmt = null;// 棰勫鐞嗚鍙ュ０鏄�
		ResultSet rs = null;// 杩斿洖缁撴灉缁撳悎澹版槑
		try {
			conn = DBUtil.getConnection();
			// System.out.println("UserDaoImp.save()鑾峰彇鏁版嵁搴撹繛鎺ユ垚鍔�");
			String sql = "update artist set name = ?,image1 = ?,image2 = ?,description = ?,category = ?,gender = ?where art_id = ?;";
			psmt = conn.prepareStatement(sql);
			System.out.println("sql璇彞棰勫鐞嗘垚鍔�");
			// 6.涓哄弬鏁拌缃��
			psmt.setString(1, artist.getName());
			psmt.setString(2, artist.getImage1());
			psmt.setString(3, artist.getImage2());
			psmt.setString(4, artist.getDescription());
			psmt.setString(5, artist.getCategory());
			psmt.setString(6, artist.getGender());
			psmt.setLong(7, artist.getArtId());

			// 7.鎵ц鏂板鎿嶄綔
			int result = psmt.executeUpdate();
			System.out.println("鏈鎿嶄綔褰卞搷" + result + "琛�");
			// 8.鑾峰彇鎿嶄綔缁撴灉锛岃缃甪lag
		} catch (SQLException e) {
			// 9.寮傚父澶勭悊
			e.printStackTrace();
		} finally {
			// 10.鍏抽棴鏁版嵁搴撹繛鎺�
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
		Connection conn = null;// 杩炴帴澹版槑
		PreparedStatement psmt = null;// 棰勫鐞嗚鍙ュ０鏄�
		ResultSet rs = null;// 杩斿洖缁撴灉缁撳悎澹版槑
		try {
			conn = DBUtil.getConnection();
			// System.out.println("UserDaoImp.save()鑾峰彇鏁版嵁搴撹繛鎺ユ垚鍔�");
			String sql = "update album set art_id = ?,name = ?,publishdate = ?,image1 = ?,image2 = ?,company = ?,description = ?where alb_id = ?;";
			psmt = conn.prepareStatement(sql);
			System.out.println("sql璇彞棰勫鐞嗘垚鍔�");
			// 6.涓哄弬鏁拌缃��
			psmt.setLong(1, album.getArt_id());
			psmt.setString(2, album.getName());
			psmt.setString(3, album.getPublishdate());
			psmt.setString(4, album.getImage1());
			psmt.setString(5, album.getImage2());
			psmt.setString(6, album.getCompany());
			psmt.setString(7, album.getDescription());
			psmt.setLong(8, album.getAlbId());

			// 7.鎵ц鏂板鎿嶄綔
			int result = psmt.executeUpdate();
			System.out.println("鏈鎿嶄綔褰卞搷" + result + "琛�");
			// 8.鑾峰彇鎿嶄綔缁撴灉锛岃缃甪lag
		} catch (SQLException e) {
			// 9.寮傚父澶勭悊
			e.printStackTrace();
		} finally {
			// 10.鍏抽棴鏁版嵁搴撹繛鎺�
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
	//绮剧‘
	public MusicBusiModel getMusicByRealname(String realname) throws AppException {

		Connection conn = null;//澹版槑鏁版嵁搴撹繛鎺�
		PreparedStatement psmt = null;//澹版槑棰勫鐞嗚鍙�
		ResultSet rs,rs1 = null;//澹版槑缁撴灉闆嗗悎		
		MusicBusiModel mbmd = new MusicBusiModel();//澹版槑MusicBusiModel瀵硅薄
		
		try{
			//鑾峰緱鎵�鏈塼ag鍜屽搴旂殑缂栧彿
			//浣跨敤hashmap瀛樻斁tag.tag_id -> tag.tagname
			conn = DBUtil.getConnection();//鑾峰緱鏁版嵁搴撹繛鎺�
			String sql1 = "select * from tag;";//鑾峰彇tag淇℃伅鐨剆ql璇彞
			psmt = conn.prepareStatement(sql1);//棰勫鐞嗚幏鍙杢ag淇℃伅鐨剆ql璇彞
			rs1 = psmt.executeQuery(sql1);//鎵ц鑾峰彇tag淇℃伅鐨勮鍙�
			Map map = new HashMap();//鐢╤ashMap瀛樺偍
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
			System.out.println("鎵цsql璇彞");
			while(rs.next()){
				//璁剧疆MusicBusiModel
				mbmd.setDown_count(rs.getInt(15));
				mbmd.setHit_count(rs.getInt(16));
				mbmd.setId(rs.getInt(1));
				mbmd.setName(rs.getString(2));
				//System.out.println("姝屾洸鍚�:"+rs.getString(2));
				mbmd.setRealname(rs.getString(3));
				mbmd.setLrc(rs.getString(4));
				mbmd.setZone(rs.getString(5));
				mbmd.setPublishdate(rs.getString(6));
				mbmd.setTag1(map.get(rs.getInt(7)).toString());
				//tag2銆乼ag3鍙负null
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
			//鍏抽棴缁撴灉闆嗗悎锛岃鍙ュ拰杩炴帴
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
