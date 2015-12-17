package com.framework.manager;

public class UserInfo {
	//普通用户
	public static final int COMMON_USER = 0;
	//匿名用户
	public static final int ANONY_USER = 1;
	//机器人
	public static final int ROBOT = 2;
	//用户序列号
   private  String userSequece;
   //用户ID
   private String userId;
	public UserInfo() {
	super();
	// TODO Auto-generated constructor stub
}
	// 用户类型（0普通用户，1匿名拥护，2机器人)
	private int userType;
	public String getUserSequece() {
		return userSequece;
	}
	public void setUserSequece(String userSequece) {
		this.userSequece = userSequece;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public int getUserType() {
		return userType;
	}
	public void setUserType(int userType) {
		this.userType = userType;
	}
	public UserInfo(String userSequece, String userId,
			int userType) {
		super();
		this.userSequece = userSequece;
		this.userId = userId;
		this.userType = userType;
	}
	
}
