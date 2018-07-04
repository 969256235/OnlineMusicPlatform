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
	
}
