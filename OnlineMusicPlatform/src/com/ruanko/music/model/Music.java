package com.ruanko.music.model;

/**
 * 音乐基础实体类
 */
public class Music {

	private int mus_id;                  //主键id
	private int art_id;              //艺人id
	private int alb_id;              //专辑id
	private int tag1;  //标签id1
	private int tag2;  //标签id1
	private int tag3;  //标签id1
	
	private String name;        //歌名  
	private String realname;
	private String lrc;        //歌词
	private String zone;	   //地区
	private String publishdate;  //发行日期
	
	private String musicurl;   //歌曲地址
	
	private int del;  //删除标记(0-未删除;1-已删除)
	
	public Music(){
		int[] tag_ = {0,0,0};
		this.mus_id = 0;
		this.art_id = 0;
		this.alb_id = 0;
		this.tag1 = 0;
		this.tag2 = 0;
		this.tag3 = 0;
		this.name = "";
		this.realname = "";
		this.lrc = "";
		this.zone = "";
		this.publishdate = "";
		this.musicurl = "";
		this.del = 0;
	}

	public void setAttributes(int id_,int art_id_,int alb_id_,int tag1_,int tag2_,int tag3_,String name_,String rn_,String lrc_,String zone_, String pd_,String url_){
		this.mus_id = id_;
		this.art_id = art_id_;
		this.alb_id = alb_id_;
		this.tag1 = tag1_;
		this.tag2 = tag2_;
		this.tag3 = tag3_;
		this.name = name_;
		this.realname = rn_;
		this.lrc = lrc_;
		this.zone = zone_;
		this.publishdate = pd_;
		this.musicurl = url_;
	}
	
	public int getTag1(){
		return tag1;
	}
	
	public void setTag1(int tag1){
		this.tag1 = tag1;
	}
	
	public int getTag2(){
		return tag2;
	}
	
	public void setTag2(int tag2){
		this.tag2 = tag2;
	}
	
	public int getTag3(){
		return tag3;
	}
	
	public void setTag3(int tag3){
		this.tag3 = tag3;
	}
	
	//Generated setters and getters
	public int getMusId() {
		return mus_id;
	}

	public void setId(int mus_id) {
		this.mus_id = mus_id;
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
