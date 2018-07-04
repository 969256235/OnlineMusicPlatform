package com.ruanko.music.model;

/**
 * 音乐业务实体类
 */
public class MusicBusiModel {
	
	private int id;            //歌曲在数据库中的Id
	private String name;       //歌曲名
	private String realname;   //真名
	private String artist;     //艺人名
	private String album;      //专辑名
	private String lrc;        //歌词
	private String zone;	   //地区
	private String publishdate;  //发行日期
	private String[] tag = new String[3]; //标签
	private int down_count;    //下载量
	private int hit_count;     //点击量
	
	private String musicurl;      //歌曲地址
	private String cover;        //封面
	private String artist_photo; //艺人照片
	
	//Constructors
	public MusicBusiModel(){
		this.id = 0;
		this.name = "";
		this.realname = "";
		this.artist = "V.A.";
		this.album = "No Imformation";
		this.lrc = "";
		this.zone = "";
		this.publishdate = null;
		this.tag[0] = "";
		this.tag[1] = "";
		this.tag[2] = "";
		this.down_count = 0;
		this.hit_count = 0;
		this.musicurl = "";
		this.cover = "";
		this.artist_photo = "";
	}
	
	public MusicBusiModel(Music music, Popularity popularity){
		this.id = music.getId();
		this.name = music.getName();
		this.realname = music.getRealname();
		this.artist = "V.A.";
		this.album = "No Imformation";
		this.lrc = music.getLrc();
		this.zone = music.getZone();
		this.publishdate = music.getPublishdate();
		this.tag[0] = "";
		this.tag[1] = "";
		this.tag[2] = "";
		this.down_count = popularity.getDown_count();
		this.hit_count = popularity.getHit_count();
		this.musicurl = music.getMusicurl();
		this.cover = "";
		this.artist_photo = "";
	}
	
	public MusicBusiModel(Music music, Popularity popularity, Artist artist){
		this.id = music.getId();
		this.name = music.getName();
		this.realname = music.getRealname();
		this.artist = artist.getName();
		this.album = "No Imformation";
		this.lrc = music.getLrc();
		this.zone = music.getZone();
		this.publishdate = music.getPublishdate();
		this.tag[0] = "";
		this.tag[1] = "";
		this.tag[2] = "";
		this.down_count = popularity.getDown_count();
		this.hit_count = popularity.getHit_count();
		this.musicurl = music.getMusicurl();
		this.cover = "";
		this.artist_photo = artist.getImage1();
	}
	
	public MusicBusiModel(Music music, Popularity popularity, Album album){
		this.id = music.getId();
		this.name = music.getName();
		this.realname = music.getRealname();
		this.artist = "V.A.";
		this.album = album.getName();
		this.lrc = music.getLrc();
		this.zone = music.getZone();
		this.publishdate = music.getPublishdate();
		this.tag[0] = "";
		this.tag[1] = "";
		this.tag[2] = "";
		this.down_count = popularity.getDown_count();
		this.hit_count = popularity.getHit_count();
		this.musicurl = music.getMusicurl();
		this.cover = album.getImage1();
		this.artist_photo = "";
	}
	
	public MusicBusiModel(Music music, Popularity popularity, Tag[] tag){
		this.id = music.getId();
		this.name = music.getName();
		this.realname = music.getRealname();
		this.artist = "V.A.";
		this.album = "No Imformation";
		this.lrc = music.getLrc();
		this.zone = music.getZone();
		this.publishdate = music.getPublishdate();
		this.tag[0] = tag[0].getTagname();
		this.tag[1] = tag[1].getTagname();
		this.tag[2] = tag[2].getTagname();
		this.down_count = popularity.getDown_count();
		this.hit_count = popularity.getHit_count();
		this.musicurl = music.getMusicurl();
		this.cover = "";
		this.artist_photo = "";
	}
	
	public MusicBusiModel(Music music, Popularity popularity, Artist artist, Album album){
		this.id = music.getId();
		this.name = music.getName();
		this.realname = music.getRealname();
		this.artist = artist.getName();
		this.album = album.getName();
		this.lrc = music.getLrc();
		this.zone = music.getZone();
		this.publishdate = music.getPublishdate();
		this.tag[0] = "";
		this.tag[1] = "";
		this.tag[2] = "";
		this.down_count = popularity.getDown_count();
		this.hit_count = popularity.getHit_count();
		this.musicurl = music.getMusicurl();
		this.cover = album.getImage1();
		this.artist_photo = artist.getImage1();
	}
	
	public MusicBusiModel(Music music, Popularity popularity, Artist artist, Tag[] tag){
		this.id = music.getId();
		this.name = music.getName();
		this.realname = music.getRealname();
		this.artist = artist.getName();
		this.album = "No Imformation";
		this.lrc = music.getLrc();
		this.zone = music.getZone();
		this.publishdate = music.getPublishdate();
		this.tag[0] = tag[0].getTagname();
		this.tag[1] = tag[1].getTagname();
		this.tag[2] = tag[2].getTagname();
		this.down_count = popularity.getDown_count();
		this.hit_count = popularity.getHit_count();
		this.musicurl = music.getMusicurl();
		this.cover = "";
		this.artist_photo = artist.getImage1();
	}
	
	public MusicBusiModel(Music music, Popularity popularity, Album album, Tag[] tag){
		this.id = music.getId();
		this.name = music.getName();
		this.realname = music.getRealname();
		this.artist = "V.A.";
		this.album = album.getName();
		this.lrc = music.getLrc();
		this.zone = music.getZone();
		this.publishdate = music.getPublishdate();
		this.tag[0] = tag[0].getTagname();
		this.tag[1] = tag[1].getTagname();
		this.tag[2] = tag[2].getTagname();
		this.down_count = popularity.getDown_count();
		this.hit_count = popularity.getHit_count();
		this.musicurl = music.getMusicurl();
		this.cover = album.getImage1();
		this.artist_photo = "";
	}
	
	public MusicBusiModel(Music music, Popularity popularity, Artist artist, Album album, Tag[] tag){
		this.id = music.getId();
		this.name = music.getName();
		this.realname = music.getRealname();
		this.artist = artist.getName();
		this.album = album.getName();
		this.lrc = music.getLrc();
		this.zone = music.getZone();
		this.publishdate = music.getPublishdate();
		this.tag[0] = tag[0].getTagname();
		this.tag[1] = tag[1].getTagname();
		this.tag[2] = tag[2].getTagname();
		this.down_count = popularity.getDown_count();
		this.hit_count = popularity.getHit_count();
		this.musicurl = music.getMusicurl();
		this.cover = album.getImage1();
		this.artist_photo = artist.getImage1();
	}
	
	//Tag special setters and getters
	public String getTag1(){
		return tag[0];
	}
	
	public void setTag1(String tag1){
		this.tag[0] = tag1;
	}
	
	public String getTag2(){
		return tag[1];
	}
	
	public void setTag2(String tag2){
		this.tag[1] = tag2;
	}
	
	public String getTag3(){
		return tag[2];
	}
	
	public void setTag3(String tag3){
		this.tag[2] = tag3;
	}
	
	//Generated setters and getters
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

	public String getRealname() {
		return realname;
	}

	public void setRealname(String realname) {
		this.realname = realname;
	}

	public String getArtist() {
		return artist;
	}

	public void setArtist(String artist) {
		this.artist = artist;
	}

	public String getAlbum() {
		return album;
	}

	public void setAlbum(String album) {
		this.album = album;
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

	public String[] getTag() {
		return tag;
	}

	public void setTag(String[] tag) {
		this.tag = tag;
	}

	public int getDown_count() {
		return down_count;
	}

	public void setDown_count(int down_count) {
		this.down_count = down_count;
	}

	public int getHit_count() {
		return hit_count;
	}

	public void setHit_count(int hit_count) {
		this.hit_count = hit_count;
	}

	public String getMusicurl() {
		return musicurl;
	}

	public void setMusicurl(String musicurl) {
		this.musicurl = musicurl;
	}

	public String getCover() {
		return cover;
	}

	public void setCover(String cover) {
		this.cover = cover;
	}

	public String getArtist_photo() {
		return artist_photo;
	}

	public void setArtist_photo(String artist_photo) {
		this.artist_photo = artist_photo;
	}
	
}
