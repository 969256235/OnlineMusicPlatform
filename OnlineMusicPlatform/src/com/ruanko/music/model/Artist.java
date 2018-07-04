package com.ruanko.music.model;

/**
 * 艺人基础实体类
 */
public class Artist {
	
	private int id;  //主键id
	
	private String name;        //歌名
	private String image1;      //图片路径1
	private String image2;      //图片路径2
	private String description; //描述
	private String category;    //
	private String gender;      //性别
	
	private int del;  //删除标记(0-未删除;1-已删除)

	public Artist(){
		this.id = 0;
		this.name = "";
		this.image1 = "";
		this.image2 = "";
		this.description = "";
		this.category = "";
		this.gender = "";
		this.del = 0;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public int getDel() {
		return del;
	}

	public void setDel(int del) {
		this.del = del;
	}
	
}
