package com.ruanko.music.model;

/**
 * 标签基础实体类
 */
public class Tag {

	private int id;  //主键id
	
	private String tagname;      //标签名
	private String description;  //标签描述
	
	private int del;  //删除标记(0-未删除;1-已删除)
	
	public Tag(){
		this.id = 0;
		this.tagname = "";
		this.description = "";
		this.del = 0;
	}

	//Generated setters and getters
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTagname() {
		return tagname;
	}

	public void setTagname(String tagname) {
		this.tagname = tagname;
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
