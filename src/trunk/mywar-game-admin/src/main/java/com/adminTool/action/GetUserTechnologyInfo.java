package com.adminTool.action;

import com.adminTool.bean.UserTechnologyInfo;
import com.adminTool.bo.User;
import com.adminTool.constant.AdminToolCMDCode;
import com.adminTool.msgbody.ReqUserInfo;
import com.adminTool.msgbody.ResGetUserTechnologyInfo;
import com.adminTool.service.UserService;
import com.framework.client.http.HttpServersBridge;
import com.framework.common.ALDAdminActionSupport;
import com.framework.common.CommomMsgBody;
import com.framework.constant.SystemConstant;
import com.framework.server.msg.Msg;
import com.framework.server.msg.model.UnSynList;
import com.framework.servicemanager.ServiceCacheFactory;
import com.opensymphony.xwork2.ModelDriven;

public class GetUserTechnologyInfo extends ALDAdminActionSupport implements ModelDriven<User> {

	/** * */
	private static final long serialVersionUID = 1L;
	
	private User user = new User(); 

	private UnSynList<UserTechnologyInfo> userTechnologyInfoList; 

	public String execute() {
		if (user.getUserId() == null && user.getName() == null && user.getUserName() == null) {
			return SUCCESS; 
		}
		UserService userService = ServiceCacheFactory.getServiceCache().getService(UserService.class);
		User resultUser = userService.findUserByCondition(user.getUserId(), user.getUserName(), user.getName());
		//存在条件用户
		if (resultUser == null) {
			//提示对应条件的玩家不存在
			String str = "";
			if (user.getUserId() != null) {
				str = this.getText("log.userIdNotExist", new String[]{user.getUserId() + ""});
			}
			if (user.getUserName() != null && !"".equals(user.getUserName())) {
				str = this.getText("log.userNameNotExist", new String[]{user.getUserName()});
			}
			if (user.getName() != null && !"".equals(user.getName())) {
				str = this.getText("log.nameNotExist", new String[]{user.getName()});
			}
			super.setErroDescrip(str);
			return SUCCESS;
		} else {
			user = resultUser;
		}
		
		ReqUserInfo reqUserInfo = new ReqUserInfo(); 
		reqUserInfo.setUserId(user.getUserId() + ""); 

		Msg msg = HttpServersBridge.connectServerToExecuteTask(AdminToolCMDCode.GET_USER_TECHNOLOGY_INFO, reqUserInfo, ResGetUserTechnologyInfo.class);
		if (msg.getMsgHead().getErrorCode() != SystemConstant.SUCCESS_CODE) {
			CommomMsgBody commomMsgBody = (CommomMsgBody) msg.getMsgBody();
			super.setErroCodeNum(commomMsgBody.getErrorCode());
			super.setErroDescrip(commomMsgBody.getErrorDescription()+" 查看玩家科技信息失败！");
			return SUCCESS;
		}
		userTechnologyInfoList = ((ResGetUserTechnologyInfo)msg.getMsgBody()).getUserTechnologyInfoList();
		 
		return SUCCESS; 
	}

	@Override
	public User getModel() {
		return user; 
	}

	public User getUser() {
		return user; 
	}

	public void setUser(User user) {
		this.user = user; 
	}

	public void setUserTechnologyInfoList(UnSynList<UserTechnologyInfo> userTechnologyInfoList) {
		this.userTechnologyInfoList = userTechnologyInfoList;
	}

	public UnSynList<UserTechnologyInfo> getUserTechnologyInfoList() {
		return userTechnologyInfoList;
	}
}
