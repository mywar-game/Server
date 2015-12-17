package com.framework.common;

import java.util.Date;

import com.adminTool.bo.User;
import com.adminTool.service.UserService;
import com.framework.servicemanager.ServiceCacheFactory;

public class ALDAdminLogPageActionSupport extends ALDAdminPageActionSupport {

	/**  */
	private static final long serialVersionUID = 1L;
	
	/** 搜索的玩家id **/
	private String userId;
	
	/** 搜索的玩家账号 **/
	private String userName;
	
	/** 搜索的玩家昵称 **/
	private String name;
	/**玩家登陆id**/
	private int lodoId;
	
	/** 开始时间 **/
	private Date startDate;
	
	/** 结束时间 **/
	private Date endDate;
	
	public String searchUser(){
		//如果查询了用户
		if ((userId != null && !userId.equals("")) || lodoId!=0) {
			UserService userService = ServiceCacheFactory.getServiceCache().getService(UserService.class);
			User user = userService.findUserByLodo(userId, lodoId);
			//存在条件用户
			if (user != null) {
				return user.getUserId();
			} else {
				//提示对应条件的玩家不存在
				String str = "";
				if (userId != null  && !userId.equals("")) {
					str = this.getText("log.userIdNotExist", new String[]{userId + ""});
				}
				if (lodoId!=0) {
					str = this.getText("log.lodoNotExist", new String[]{lodoId+""});
				}
				super.setErroDescrip(str);
				return null;
			}
		}
		return null;
	}

	/**
	 * @return the userId
	 */
	public String getUserId() {
		return userId;
	}

	/**
	 * @param userId the userId to set
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}

	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}

	public int getLodoId() {
		return lodoId;
	}

	public void setLodoId(int lodoId) {
		this.lodoId = lodoId;
	}

	/**
	 * @param userName the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the startDate
	 */
	public Date getStartDate() {
		return startDate;
	}

	/**
	 * @param startDate the startDate to set
	 */
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	/**
	 * @return the endDate
	 */
	public Date getEndDate() {
		return endDate;
	}

	/**
	 * @param endDate the endDate to set
	 */
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
}
