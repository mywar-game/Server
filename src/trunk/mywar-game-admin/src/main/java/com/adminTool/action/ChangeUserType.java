/**
 * 
 */
package com.adminTool.action;

import com.admin.util.Tools;
import com.adminTool.bean.ReqChangeUserType;
import com.adminTool.bo.User;
import com.adminTool.bo.UserType;
import com.adminTool.constant.AdminToolCMDCode;
import com.adminTool.service.UserService;
import com.adminTool.service.UserTypeService;
import com.framework.client.http.HttpServersBridge;
import com.framework.common.ALDAdminActionSupport;
import com.framework.common.CommomMsgBody;
import com.framework.constant.SystemConstant;
import com.framework.server.msg.Msg;
import com.framework.servicemanager.CustomerContextHolder;
import com.framework.servicemanager.ServiceCacheFactory;

/**
 * 改变玩家账号类型
 * @author hzy
 * 2012-7-28
 */
public class ChangeUserType extends ALDAdminActionSupport {

	/**  */
	private static final long serialVersionUID = 1L;
	
	/**  */
	private User user = new User();
	
	/** 账号类型 */
	private Integer type;
	
	/**  */
	private String isCommit = "F";
	
	@Override
	public String execute() {
		if ("F".equals(isCommit)) {
			return SUCCESS;
		}
		UserService userService = ServiceCacheFactory.getServiceCache().getService(UserService.class);
		//没有填写要改变的玩家
		if (user.getUserId() == null && Tools.isEmpty(user.getUserName()) && Tools.isEmpty(user.getName())) {
			super.setErroDescrip("没有填写要改变的玩家");
			return SUCCESS;
		}
		if (type == null) {
			super.setErroDescrip("账号类型为空");
			return SUCCESS;
		}
		user = userService.findUserByCondition(user.getUserId(), user.getUserName(), user.getName());
		if (user == null) {
			super.setErroDescrip("找不到玩家");
			return SUCCESS;
		}
		ReqChangeUserType req = new ReqChangeUserType();
		req.setUserID(user.getUserId() + "");
		req.setType(type);
		
		Msg msg = HttpServersBridge.connectServerToExecuteTask(AdminToolCMDCode.CHANGE_USER_TYPE, req, CommomMsgBody.class);
		if (msg.getMsgHead().getErrorCode() != SystemConstant.SUCCESS_CODE) {
			CommomMsgBody commomMsgBody = (CommomMsgBody) msg.getMsgBody();
			super.setErroCodeNum(commomMsgBody.getErrorCode());
			super.setErroDescrip(commomMsgBody.getErrorDescription()+" 改变玩家账号类型失败！");
			return ERROR;
		}
		UserType userType = new UserType();
		userType.setSysNum(CustomerContextHolder.getSystemNum());
		userType.setUserId(user.getUserId());
		userType.setType(type);
		UserTypeService userTypeService = ServiceCacheFactory.getServiceCache().getService(UserTypeService.class);
		userTypeService.saveOrUpdateUserType(userType);
		super.setErroDescrip("改变玩家账号类型成功！");
		return SUCCESS;
	}

	/**
	 * @return the user
	 */
	public User getUser() {
		return user;
	}

	/**
	 * @param user the user to set
	 */
	public void setUser(User user) {
		this.user = user;
	}

	/**
	 * @return the type
	 */
	public Integer getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(Integer type) {
		this.type = type;
	}

	/**
	 * @return the isCommit
	 */
	public String getIsCommit() {
		return isCommit;
	}

	/**
	 * @param isCommit the isCommit to set
	 */
	public void setIsCommit(String isCommit) {
		this.isCommit = isCommit;
	}
}
