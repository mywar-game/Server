package com.adminTool.action;

import java.text.MessageFormat;
import java.util.Date;

import com.admin.util.Tools;
import com.adminTool.bean.UserMail;
import com.adminTool.bo.User;
import com.adminTool.constant.AdminToolCMDCode;
import com.adminTool.msgbody.ReqSendSystemMail;
import com.adminTool.service.UserService;
import com.framework.client.http.HttpServersBridge;
import com.framework.common.ALDAdminActionSupport;
import com.framework.common.CommomMsgBody;
import com.framework.constant.SystemConstant;
import com.framework.server.msg.Msg;
import com.framework.servicemanager.CustomerContextHolder;
import com.framework.servicemanager.ServiceCacheFactory;
import com.system.manager.DataSourceManager;

/**
 * @author hzy
 * 2012-7-20
 */
public class AddSystemMail extends ALDAdminActionSupport {

	private static final long serialVersionUID = -8316976295339506985L;
	
	/**邮件标题**/
	private String theme;
	
	/**邮件内容**/
	private String content;
	
	/** 角色名字符串 **/
	private String nameStr;
	
	private String userId;
	
	private int allUser = 0;
	
	private String isCommit = "F";
	
	/** 选中的要生成激活码的服务器id **/
	private String serverIds;
	
	@Override
	public String execute() {
		UserService userService = ServiceCacheFactory.getServiceCache().getService(UserService.class);
		if ("F".equals(isCommit)) {
			super.setErroDescrip("");
			if (userId != null) {
				User user = userService.findUserByUserId(userId);
				nameStr = user.getName();
			}
			return INPUT;
		}
		
		ReqSendSystemMail req = new ReqSendSystemMail();
		//发全服
		if (allUser == 1) {
			UserMail userMail = new UserMail();
			userMail.setSenderUserId(-1);
			userMail.setSenderName("系统");
			userMail.setUserId(-1L);
			userMail.setTheme(theme);
			userMail.setContent(content);
			userMail.setSendTime(new Date());
			userMail.setMailAttach("");
			userMail.setGetAttach(false);
			req.addUserMailList(userMail);
		} else {
			nameStr = nameStr.trim();
			String[] names = nameStr.split(",");
			for (String name : names) {
				UserMail userMail = new UserMail();
				User user = userService.findUserByName(name.trim());
				if (user == null) {
					super.setErroDescrip("角色名为 " + name + " 的玩家不存在日志库中，邮件发送失败！");
					return ERROR;
				}
				userMail.setSenderUserId(-1);
				userMail.setSenderName("系统");
				userMail.setUserId(Long.valueOf(user.getUserId()));
				userMail.setTheme(MessageFormat.format(theme, name));
				userMail.setContent(MessageFormat.format(content, name));
				userMail.setSendTime(new Date());
				userMail.setMailAttach("");
				userMail.setGetAttach(false);
				req.addUserMailList(userMail);
			}
			//没发全服，设置serverIds为当前服
			serverIds = CustomerContextHolder.getSystemNum()+"";
		}
		req.setMailAttach("");
		
		String[] errorDescArr = {"",""};
		//循环发送邮件
		for (String serverId : serverIds.split(",")) {
			CustomerContextHolder.setSystemNum(Integer.valueOf(serverId.trim()));
			String serverName = DataSourceManager.getInstatnce().getGameServerMap().get(Integer.valueOf(serverId.trim())).getServerAlias();
			Msg msg = HttpServersBridge.connectServerToExecuteTask(AdminToolCMDCode.SEND_SYSTEM_MAIL, req,  CommomMsgBody.class);
			if (msg.getMsgHead().getErrorCode() != SystemConstant.SUCCESS_CODE) {
				CommomMsgBody commomMsgBody = (CommomMsgBody) msg.getMsgBody();
				if (Tools.isEmpty(errorDescArr[1])) {
					errorDescArr[1] = serverName +" 邮件发送失败： " + commomMsgBody.getErrorDescription();
				} else {
					errorDescArr[1] += "\\n" +serverName +" 邮件发送失败： " + commomMsgBody.getErrorDescription();
				}
			} else {
				if (Tools.isEmpty(errorDescArr[0])) {
					errorDescArr[0] = serverName;
				} else {
					errorDescArr[0] += " , " + serverName;
				}
			}
		}
		if (!Tools.isEmpty(errorDescArr[0])) {
			errorDescArr[0] += " 邮件发送成功！\\n";
		}
		super.setErroDescrip(errorDescArr[0] + errorDescArr[1]);
		return SUCCESS;
	}

	public String getTheme() {
		return theme;
	}

	public void setTheme(String theme) {
		this.theme = theme;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getNameStr() {
		return nameStr;
	}

	public void setNameStr(String nameStr) {
		this.nameStr = nameStr;
	}

	public void setIsCommit(String isCommit) {
		this.isCommit = isCommit;
	}

	public String getIsCommit() {
		return isCommit;
	}

	public void setAllUser(int allUser) {
		this.allUser = allUser;
	}

	public int getAllUser() {
		return allUser;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	/**
	 * 获取 选中的要生成激活码的服务器id 
	 */
	public String getServerIds() {
		return serverIds;
	}

	/**
	 * 设置 选中的要生成激活码的服务器id 
	 */
	public void setServerIds(String serverIds) {
		this.serverIds = serverIds;
	}
}
