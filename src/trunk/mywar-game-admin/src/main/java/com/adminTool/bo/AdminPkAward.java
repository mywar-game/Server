package com.adminTool.bo;

public class AdminPkAward implements java.io.Serializable{

	private Integer id;
	private int score;
	private String tools;
	private String name;
	private String description;
	private String imgId;
	private int day_buy_num;
	private int total_buy_num;
	
	public AdminPkAward(){
		
	}
	
	public AdminPkAward(Integer id,int score, String tools, String name,
			String description, String imgId, int day_buy_num, int total_buy_num) {
		super();
		this.id = id;
		this.score = score;
		this.tools = tools;
		this.name = name;
		this.description = description;
		this.imgId = imgId;
		this.day_buy_num = day_buy_num;
		this.total_buy_num = total_buy_num;
	}
	public int getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	public String getTools() {
		return tools;
	}
	public void setTools(String tools) {
		this.tools = tools;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getImgId() {
		return imgId;
	}
	public void setImgId(String imgId) {
		this.imgId = imgId;
	}
	public int getDay_buy_num() {
		return day_buy_num;
	}
	public void setDay_buy_num(int day_buy_num) {
		this.day_buy_num = day_buy_num;
	}
	public int getTotal_buy_num() {
		return total_buy_num;
	}
	public void setTotal_buy_num(int total_buy_num) {
		this.total_buy_num = total_buy_num;
	}
	
	
	
	
}
