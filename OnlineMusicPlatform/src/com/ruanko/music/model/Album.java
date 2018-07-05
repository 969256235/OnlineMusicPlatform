package com.ruanko.music.model;

/**
 * 专辑基本实体类
 */
public class Album {
	
	private int id;            //主键id
	private int art_id;        //艺人id
	
	private String name;       //专辑名
	private String publishdate;  //发行日期
	private String image1;     //图片路径
	private String image2;     //图片路径
	private String company;    //发行商
	private String description;//描述
	
	private int del; //删除标记(0-未删除;1-已删除)

	public Album(){
		this.id = 0;
		this.art_id = 0;
		this.name = "";
		this.publishdate = "";
		this.image1 = "";
		this.image2 = "";
		this.company = "";
		this.description = "";
		this.del = 0;
	}
	
	public void setAttributes(int id_, int art_id_,String name_,String pd_,String image1_, String image2_,String com_,String dsp_){
		this.id = id_;
		this.art_id = art_id_;
		this.name = name_;
		this.publishdate = pd_;
		this.image1 = image1_;
		this.image2 = image2_;
		this.company = com_;
		this.description = dsp_;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPublishdate() {
		return publishdate;
	}

	public void setPublishdate(String publishdate) {
		this.publishdate = publishdate;
	}

	public String getImage1() {
		return image1;
	}

	public void setImage1(String image1) {
		this.image1 = image1;
	}

	public String getImage2() {
		return image2;
	}

	public void setImage2(String image2) {
		this.image2 = image2;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getDel() {
		return del;
	}

	public void setDel(int del) {
		this.del = del;
	}
	
}
