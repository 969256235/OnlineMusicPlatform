package com.ruanko.music.model;

/**
 * 音乐基础实体类
 */
public class Music {

	private int id;                  //主键id
	private int art_id;              //艺人id
	private int alb_id;              //专辑id
	private int[] tag = new int[3];  //标签id
	
	private String name;        //歌名  
	private String realname;
	private String lrc;        //歌词
	private String zone;	   //地区
	private String publishdate;  //发行日期
	
	private String musicurl;   //歌曲地址
	
	private int del;  //删除标记(0-未删除;1-已删除)
	
	public Music(){
		int[] tag_ = {0,0,0};
		this.id = 0;
		this.art_id = 0;
		this.alb_id = 0;
		this.tag = tag_;
		this.name = "";
		this.realname = "";
		this.lrc = "";
		this.zone = "";
		this.publishdate = null;
		this.musicurl = "";
		this.del = 0;
	}

	public int getTag1(){
		return tag[0];
	}
	
	public void setTag1(int tag1){
		this.tag[0] = tag1;
	}
	
	public int getTag2(){
		return tag[1];
	}
	
	public void setTag2(int tag2){
		this.tag[1] = tag2;
	}
	
	public int getTag3(){
		return tag[2];
	}
	
	public void setTag3(int tag3){
		this.tag[2] = tag3;
	}
	
	//Generated setters and getters
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getArt_id() {
		return art_id;
	}

	public void setArt_id(int art_id) {
		this.art_id = art_id;
	}

	public int getAlb_id() {
		return alb_id;
	}

	public void setAlb_id(int alb_id) {
		this.alb_id = alb_id;
	}

	public int[] getTag() {
		return tag;
	}

	public void setTag(int[] tag) {
		this.tag = tag;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRealname() {
		return realname;
	}

	public void setRealname(String realname) {
		this.realname = realname;
	}

	public String getLrc() {
		return lrc;
	}

	public void setLrc(String lrc) {
		this.lrc = lrc;
	}

	public String getZone() {
		return zone;
	}

	public void setZone(String zone) {
		this.zone = zone;
	}

	public String getPublishdate() {
		return publishdate;
	}

	public void setPublishdate(String publishdate) {
		this.publishdate = publishdate;
	}

	public String getMusicurl() {
		return musicurl;
	}

	public void setMusicurl(String musicurl) {
		this.musicurl = musicurl;
	}

	public int getDel() {
		return del;
	}

	public void setDel(int del) {
		this.del = del;
	}
		
}
