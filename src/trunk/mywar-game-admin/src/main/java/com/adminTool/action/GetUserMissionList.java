package com.adminTool.action;

import java.util.Map;

import com.adminTool.bo.User;
import com.adminTool.constant.AdminToolCMDCode;
import com.adminTool.msgbody.ReqReplaceUserCurrentMission;
import com.adminTool.service.UserService;
import com.dataconfig.service.MMissionConstantService;
import com.framework.client.http.HttpServersBridge;
import com.framework.common.ALDAdminPageActionSupport;
import com.framework.common.CommomMsgBody;
import com.framework.common.SystemCode;
import com.framework.constant.SystemConstant;
import com.framework.server.msg.Msg;
import com.framework.servicemanager.ServiceCacheFactory;

public class GetUserMissionList extends ALDAdminPageActionSupport {

	/**	 */
	private static final long serialVersionUID = -5227989897206335890L;
	
	/**  */
	private Map<Integer, String> missionIdNameMap;
	
	/** 玩家角色名 */
	private String name;
	
	/** 要修改的任务id */
	private Integer missionID;
	
	private String isCommit = "F";
	
	public String execute() {
		
		MMissionConstantService mMissionConstantService = ServiceCacheFactory.getServiceCache().getService(MMissionConstantService.class);
		missionIdNameMap = mMissionConstantService.findMissionIdNameMap();
		
		if ("F".equals(isCommit)) {
			return SUCCESS;
		}

		UserService userService = ServiceCacheFactory.getServiceCache().getService(UserService.class);
		User user = null;
		if (name != null && !"".equals(name)) {
			user = userService.findUserByName(name); 
		}
		if(user == null){
			super.setErroDescrip("找不到角色名为：\"" + name + "\"的玩家");
			return SUCCESS;
		}
		
		ReqReplaceUserCurrentMission reqReplaceUserCurrentMission = new ReqReplaceUserCurrentMission(); 
		reqReplaceUserCurrentMission.setMissionId(missionID);
		reqReplaceUserCurrentMission.setUserId(user.getUserId()+"");

		Msg msg = HttpServersBridge.connectServerToExecuteTask(AdminToolCMDCode.UPDATE_USER_MISSION, reqReplaceUserCurrentMission, CommomMsgBody.class);
		if (msg.getMsgHead().getErrorCode() != SystemConstant.SUCCESS_CODE) {
			CommomMsgBody commomMsgBody = (CommomMsgBody) msg.getMsgBody();
			super.setErroCodeNum(commomMsgBody.getErrorCode());
			super.setErroDescrip(commomMsgBody.getErrorDescription()+" 修改玩家任务失失败！");
			return SUCCESS;
		}
		super.setErroCodeNum(SystemCode.SYS_SUCESS);
		super.setErroDescrip("修改玩家任务成功！");
		return SUCCESS;
	}

	public Map<Integer, String> getMissionIdNameMap() {
		return missionIdNameMap;
	}

	public void setMissionIdNameMap(Map<Integer, String> missionIdNameMap) {
		this.missionIdNameMap = missionIdNameMap;
	}

	public Integer getMissionID() {
		return missionID;
	}

	public void setMissionID(Integer missionID) {
		this.missionID = missionID;
	}

	/**
	 * 获取 isCommit 
	 */
	public String getIsCommit() {
		return isCommit;
	}

	/**
	 * 设置 isCommit 
	 */
	public void setIsCommit(String isCommit) {
		this.isCommit = isCommit;
	}

	/**
	 * 获取 玩家角色名 
	 */
	public String getName() {
		return name;
	}

	/**
	 * 设置 玩家角色名 
	 */
	public void setName(String name) {
		this.name = name;
	}

}
