package com.adminTool.action;

import java.util.ArrayList;
import java.util.List;

import com.adminTool.bo.User;
import com.adminTool.service.UserService;
import com.framework.common.ALDAdminActionSupport;
import com.framework.servicemanager.ServiceCacheFactory;
import com.log.bo.UserRegLog;
import com.log.service.UserRegLogService;

/**
 * 查询用户注册信息
 * @author Administrator
 *
 */
public class SearchUserReg extends ALDAdminActionSupport {

	private static final long serialVersionUID = 8098316577015637801L;

	private String isCommit = "F";
	
	private Integer lodoId;
	
	private String userName;
	
	private UserRegLog userRegLog;
	
	private List<UserRegLog> userRegLogList = new ArrayList<UserRegLog>();

	public List<UserRegLog> getUserRegLogList() {
		return userRegLogList;
	}

	public void setUserRegLogList(List<UserRegLog> userRegLogList) {
		this.userRegLogList = userRegLogList;
	}

	@Override
	public String execute() {
		
		if (isCommit == "T" || isCommit.equalsIgnoreCase("T")) {
			UserService userService = ServiceCacheFactory.getServiceCache().getService(UserService.class);
			UserRegLogService userRegLogService = ServiceCacheFactory.getServiceCache().getService(UserRegLogService.class); 
			if (lodoId == null) {
				lodoId = 0;
			}
			List<com.adminTool.bo.User> list = userService.findUserByLodoIdAndUserName(lodoId, userName);
			for (User user : list) {
				String userId = user.getUserId();
				userRegLog = userRegLogService.findUserRegLogByUserId(userId);
				userRegLogList.add(userRegLog);
			}
		}
		return SUCCESS;
	}
	
	public Integer getLodoId() {
		return lodoId;
	}

	public void setLodoId(Integer lodoId) {
		this.lodoId = lodoId;
	}

	public String getIsCommit() {
		return isCommit;
	}

	public void setIsCommit(String isCommit) {
		this.isCommit = isCommit;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public UserRegLog getUserRegLog() {
		return userRegLog;
	}

	public void setUserRegLog(UserRegLog userRegLog) {
		this.userRegLog = userRegLog;
	}


}
