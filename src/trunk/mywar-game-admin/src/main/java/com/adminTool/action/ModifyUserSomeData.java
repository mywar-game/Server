package com.adminTool.action;

import com.adminTool.msgbody.UserSomeInfo;
import com.framework.common.ALDAdminActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class ModifyUserSomeData extends ALDAdminActionSupport implements ModelDriven<UserSomeInfo> {

	/** * */
	private static final long serialVersionUID = 1L;
	
	private UserSomeInfo userSomeInfo = new UserSomeInfo();
	
	private String isCommit = "F";
	
	public String execute() {
		if ("F".equals(isCommit)) {
			return SUCCESS;
		}
//		Msg msg = HttpServersBridge.connectServerToExecuteTask(AdminToolCMDCode.MODIFY_USER_SOME_DATA, userSomeInfo, CommomMsgBody.class);
//		CommomMsgBody commomMsgBody = (CommomMsgBody) msg.getMsgBody();
//		if (msg.getMsgHead().getErrorCode() != SystemConstant.SUCCESS_CODE) {
//			super.setErroCodeNum(commomMsgBody.getErrorCode());
//			if (Tools.isEmpty(commomMsgBody.getErrorDescription())) {
//				super.setErroDescrip("修改失败！错误码：" + commomMsgBody.getErrorCode());
//			} else {
//				super.setErroDescrip(commomMsgBody.getErrorDescription() + " 修改失败！");
//			}
//		} else {
//			super.setErroDescrip(commomMsgBody.getErrorDescription() + " 修改成功！");
//		}
		return SUCCESS;
	}

	@Override
	public UserSomeInfo getModel() {
		return userSomeInfo;
	}

	public void setUserSomeInfo(UserSomeInfo userSomeInfo) {
		this.userSomeInfo = userSomeInfo;
	}

	public UserSomeInfo getUserSomeInfo() {
		return userSomeInfo;
	}

	public void setIsCommit(String isCommit) {
		this.isCommit = isCommit;
	}

	public String getIsCommit() {
		return isCommit;
	}

}
