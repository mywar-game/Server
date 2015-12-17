package com.adminTool.action;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.sql.Timestamp;

import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;

import com.adminTool.bo.AdminIssueDiamondLog;
import com.adminTool.bo.User;
import com.adminTool.constant.AdminToolConstant;
import com.adminTool.service.AdminIssueDiamondLogService;
import com.adminTool.service.UserService;
import com.framework.client.http.HttpClientBridge;
import com.framework.common.ALDAdminActionSupport;
import com.framework.common.SystemCode;
import com.framework.constant.SystemConstant;
import com.framework.log.LogSystem;
import com.framework.servicemanager.ServiceCacheFactory;

public class SendUserGolden extends ALDAdminActionSupport {

	private static final long serialVersionUID = -1786864753393320957L;
	private static final String REQ_URL = "addUserMoney.do";
	private Integer allUser;

	@Override
	public String execute() {
		StringBuffer receiveUser = new StringBuffer();
		// 接受玩家名称
		String lodoIds = null;
		String issueReason = null;
		try {
			lodoIds = ServletActionContext.getRequest().getParameter("name") == null ? ""
					: URLDecoder.decode(
							ServletActionContext.getRequest()
									.getParameter("name").toString(), "UTF-8")
							.trim();
			issueReason = ServletActionContext.getRequest().getParameter(
					"issueReason") == null ? "" : URLDecoder.decode(
					ServletActionContext.getRequest()
							.getParameter("issueReason").toString(), "UTF-8")
					.trim();
		} catch (UnsupportedEncodingException e) {
			LogSystem.error(e, "");
			super.setErroCodeNum(SystemCode.SYS_FAIL);
			super.setErroDescrip("解析接受玩家名或发放理由出错，发放钻石失败！");
			return ERROR;
		}

		if (allUser == AdminToolConstant.USER && (lodoIds == null
				|| "".equals(lodoIds))) {
			super.setErroCodeNum(SystemCode.SYS_FAIL);
			super.setErroDescrip("接受玩家名为空，发放钻石失败！");
			return ERROR;
		}

		// 金币数量
		int golden = ServletActionContext.getRequest().getParameter("golden") == null ? 0
				: Integer.valueOf(ServletActionContext.getRequest()
						.getParameter("golden").toString());
		if (golden <= 0 || golden > 50000) {
			super.setErroCodeNum(SystemCode.SYS_FAIL);
			super.setErroDescrip("钻石数[" + golden + "]不在允许范围内，发放钻石失败！");
			return ERROR;
		}

		UserService userService = ServiceCacheFactory.getServiceCache()
				.getService(UserService.class);
		StringBuilder builder = new StringBuilder();
		// 非全服发放
		if (allUser == AdminToolConstant.USER && lodoIds != "") {
			String[] str = lodoIds.split(",");
			for (int i = 0; i < str.length; i++) {
				User user = userService.findUserByLodoId(Integer
						.parseInt(str[i]));
				if (user == null) {
					super.setErroCodeNum(SystemCode.SYS_FAIL);
					super.setErroDescrip("用户(" + str[i] + ")不存在，发放钻石失败！");
					return ERROR;
				}
				builder.append(user.getUserId());
				if (i != str.length - 1) {
					builder.append(",");
				}
				if (receiveUser.length() == 0) {
					receiveUser.append(user.getUserId() + "_"
							+ user.getUserName());
				} else {
					receiveUser.append(",").append(
							user.getUserId() + "_" + user.getUserName());
				}
			}
		// 全服发放
		} else if (allUser == AdminToolConstant.SERVER) {
			builder.append(AdminToolConstant.SERVER);
			receiveUser.append("全服");
		}

		if (builder == null || "".equals(builder)) {
			super.setErroCodeNum(SystemCode.SYS_FAIL);
			super.setErroDescrip("接受玩家的id为空，发放钻石失败！");
			return ERROR;
		}

		StringBuilder param = new StringBuilder();
		StringBuilder md5Str = new StringBuilder();
		String userIds = builder.toString();
		param.append("&allUser=").append(allUser);
		param.append("&userIdStr=").append(userIds);
		param.append("&gold=").append(golden);
		md5Str.append(allUser).append(userIds).append(golden);

		String response = HttpClientBridge.sendToGameServer(REQ_URL,
				param.toString(), md5Str.toString());
		String failStr = "";
		if (response == null || response.equals("")) {
			super.setErroCodeNum(SystemConstant.FAIL_CODE);
			super.setErroDescrip("发送出现异常,请查看日志！");
			return ERROR;
		} else {
			JSONObject jsonObject = JSONObject.fromObject(response);
			if (!jsonObject.containsKey(HttpClientBridge.RETURN_RC)) {
				super.setErroDescrip("发送失败！");
				return ERROR;
			}
			
			int rc = jsonObject.getInt(HttpClientBridge.RETURN_RC);
			if (rc == HttpClientBridge.RETURN_SUCCESS_CODE) {
				super.setErroDescrip("发放成功！");
			} else {
				String result = jsonObject.get(HttpClientBridge.SUCCESS)
						.toString();
				if (result.equals("")) {// 全部发送成功
					super.setErroDescrip("发放成功！");
				} else {
					failStr = "用户编号为:" + result + "的玩家没有发送成功!";
					super.setErroDescrip(failStr);
				}
			}
		}

		// super.setErroCodeNum(SystemCode.SYS_SUCESS);
		AdminIssueDiamondLogService adminIssueDiamondLogService = ServiceCacheFactory
				.getServiceCache()
				.getService(AdminIssueDiamondLogService.class);
		AdminIssueDiamondLog adminIssueDiamondLog = new AdminIssueDiamondLog();
		adminIssueDiamondLog.setSysNum(Integer.parseInt(this.getAdminUser()
				.getExp1()));
		adminIssueDiamondLog.setAdminName(this.getAdminUser().getAdminName());
		adminIssueDiamondLog.setIssueTime(new Timestamp(System
				.currentTimeMillis()));
		adminIssueDiamondLog.setReceiveUser(receiveUser.toString());
		adminIssueDiamondLog.setFailUser(failStr);
		adminIssueDiamondLog.setNum(golden);
		adminIssueDiamondLog.setIssueReason(issueReason);
		adminIssueDiamondLogService
				.saveAdminIssueDiamondLog(adminIssueDiamondLog);
		return SUCCESS;

	}

	public Integer getAllUser() {
		return allUser;
	}

	public void setAllUser(Integer allUser) {
		this.allUser = allUser;
	}

}
