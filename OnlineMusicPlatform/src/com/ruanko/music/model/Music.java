package com.ruanko.music.model;

import java.util.Date;

/**
 * 音乐实体类
 */
public class Music {

	private int id;
	private String name;
	private String singer;
	private String adress;
	private String photo;
	private String picture;
	private String lrc;
	private int palyTime;
	private int downloadTime;
	private String tag;
	private Date releaseDate;
	
	public Music(){
		
	}
	
	//setters and getters	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSinger() {
		return singer;
	}

	public void setSinger(String singer) {
		this.singer = singer;
	}

	public String getAdress() {
		return adress;
	}

	public void setAdress(String adress) {
		this.adress = adress;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public String getLrc() {
		return lrc;
	}

	public void setLrc(String lrc) {
		this.lrc = lrc;
	}

	public int getPalyTime() {
		return palyTime;
	}

	public void setPalyTime(int palyTime) {
		this.palyTime = palyTime;
	}

	public int getDownloadTime() {
		return downloadTime;
	}

	public void setDownloadTime(int downloadTime) {
		this.downloadTime = downloadTime;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public Date getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(Date releaseDate) {
		this.releaseDate = releaseDate;
	}

}
