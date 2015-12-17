package com.adminTool.action;

import com.admin.util.Tools;
import com.adminTool.bo.User;
import com.adminTool.constant.AdminToolCMDCode;
import com.adminTool.msgbody.ReqModifyUserPassword;
import com.adminTool.service.UserService;
import com.framework.client.http.HttpServersBridge;
import com.framework.common.ALDAdminActionSupport;
import com.framework.common.CommomMsgBody;
import com.framework.constant.SystemConstant;
import com.framework.server.msg.Msg;
import com.framework.servicemanager.ServiceCacheFactory;

/** 修改玩家密码 **/
public class ModifyUserPassword extends ALDAdminActionSupport {

	/**  */
	private static final long serialVersionUID = 5909603531623808131L;
	
	private String name;
	
	private String newPassword;
	
	private String isCommit = "F";

	public String execute() {
		//没提交，返回修改密码页面
		if ("F".equals(isCommit)) {
			return SUCCESS;
		}
		UserService userService = ServiceCacheFactory.getServiceCache().getService(UserService.class);
		User user = userService.findUserByName(name.trim());
		if (user == null) {
			super.setErroDescrip("角色名为(" + name + ")的玩家不存在，修改密码失败！");
			return SUCCESS;
		}
		if (Tools.isEmpty(newPassword)) {
			super.setErroDescrip("新密码为空！");
			return SUCCESS;
		}
		ReqModifyUserPassword req = new ReqModifyUserPassword(user.getUserId()+"", newPassword);
		Msg msg = HttpServersBridge.connectServerToExecuteTask(AdminToolCMDCode.MODIFY_USER_PASSWORD, req, CommomMsgBody.class);
		if (msg.getMsgHead().getErrorCode() != SystemConstant.SUCCESS_CODE) {
			CommomMsgBody commomMsgBody = (CommomMsgBody) msg.getMsgBody();
			super.setErroCodeNum(commomMsgBody.getErrorCode());
			super.setErroDescrip(commomMsgBody.getErrorDescription()+" 修改密码失败！");
			return SUCCESS;
		}
		super.setErroDescrip("修改密码成功！");
		return SUCCESS;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setIsCommit(String isCommit) {
		this.isCommit = isCommit;
	}

	public String getIsCommit() {
		return isCommit;
	}
	
}
