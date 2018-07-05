package com.ruanko.music.model;

/**
 * 热度基础实体类
 */
public class Popularity {

	private int id;        //主键id
	private int type;      //类型  1-album；2-artist；3-music
	private int obj_id;    //根据类型判定
	
	private int down_count;  //下载量
	private int hit_count;   //点击量
	
	private int del;  //删除标记(0-未删除;1-已删除)
	
	public Popularity(){
		this.id = 0;
		this.type = 0;
		this.obj_id = 0;
		this.down_count = 0;
		this.hit_count = 0;
		this.del = 0;
	}
	
	public void setAttributes(int id_,int type_,int obj_id_,int dc_,int hc_){
		this.id = id_;
		this.type = type_;
		this.obj_id =  obj_id_;
		this.down_count = dc_;
		this.hit_count = hc_;
	}

	//Generated setters and getters
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getObj_id() {
		return obj_id;
	}

	public void setObj_id(int obj_id) {
		this.obj_id = obj_id;
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

	public int getDel() {
		return del;
	}

	public void setDel(int del) {
		this.del = del;
	}
	
}
