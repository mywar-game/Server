package com.adminTool.action; 

import com.admin.util.Tools;
import com.adminTool.msgbody.UserBuildingSomeInfo;
import com.adminTool.msgbody.UserSomeInfo;
import com.framework.common.ALDAdminActionSupport;
import com.framework.server.msg.model.UnSynList;

public class GetUserBuilding extends ALDAdminActionSupport {

	private static final long serialVersionUID = 8205451880896128078L;
	
	/**
	 * 搜索的玩家角色名
	 */
	private String searchName;
	
	/**
	 * 是否提交
	 */
	private String isCommit = "F";
	
	private UnSynList<UserBuildingSomeInfo> userBuildingSomeInfoList; 
	
	public String execute() {
		if ("F".equals(isCommit)) {
			return SUCCESS;
		}
		
		if (Tools.isEmpty(searchName)) {
			super.setErroDescrip("角色名为空，请填写！");
			return SUCCESS;
		}
		UserSomeInfo userSomeInfo = new UserSomeInfo();
		userSomeInfo.setRoleName(searchName);
		
//		Msg msg = HttpServersBridge.connectServerToExecuteTask(AdminToolCMDCode.GET_USER_BUILDING, userSomeInfo, ResGetUserBuildingSomeInfoTask.class);
//		if (msg.getMsgHead().getErrorCode() != SystemConstant.SUCCESS_CODE) {
//			CommomMsgBody commomMsgBody = (CommomMsgBody) msg.getMsgBody();
//			super.setErroCodeNum(commomMsgBody.getErrorCode());
//			super.setErroDescrip(commomMsgBody.getErrorDescription()+" 查看用户建筑失败！");
//			return SUCCESS;
//		}
//		userBuildingSomeInfoList = ((ResGetUserBuildingSomeInfoTask)msg.getMsgBody()).getUserBuildingSomeInfoList();
		
		return SUCCESS; 
	}

	/**
	 * 获取 搜索的玩家角色名 
	 */
	public String getSearchName() {
		return searchName;
	}

	/**
	 * 设置 搜索的玩家角色名 
	 */
	public void setSearchName(String searchName) {
		this.searchName = searchName;
	}

	/**
	 * 获取 是否提交 
	 */
	public String getIsCommit() {
		return isCommit;
	}

	/**
	 * 设置 是否提交 
	 */
	public void setIsCommit(String isCommit) {
		this.isCommit = isCommit;
	}

	/**
	 * 获取 userBuildingSomeInfoList 
	 */
	public UnSynList<UserBuildingSomeInfo> getUserBuildingSomeInfoList() {
		return userBuildingSomeInfoList;
	}

	/**
	 * 设置 userBuildingSomeInfoList 
	 */
	public void setUserBuildingSomeInfoList(
			UnSynList<UserBuildingSomeInfo> userBuildingSomeInfoList) {
		this.userBuildingSomeInfoList = userBuildingSomeInfoList;
	}

}