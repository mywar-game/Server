package com.adminTool.action;

import net.sf.json.JSONObject;

import com.adminTool.bo.User;
import com.adminTool.service.UserService;
import com.framework.client.http.HttpClientBridge;
import com.framework.common.ALDAdminActionSupport;
import com.framework.constant.SystemConstant;
import com.framework.servicemanager.ServiceCacheFactory;

/**
 * 修改用户等级
 * 
 * @author yezp
 */
public class UpdateUserLevel extends ALDAdminActionSupport {

	private static final long serialVersionUID = 1034628698577416936L;

	private static final String REQ_URL = "updateUserLevel.do";
	private String isCommit = "F";
	private Integer lodoId;
	private Integer level;

	public String execute() {
		if (isCommit.equals("F")) {
			return INPUT;
		}

		if (lodoId == null || lodoId == 0 || level == null || level == 0) {
			return INPUT;
		}

		UserService userService = ServiceCacheFactory.getServiceCache()
				.getService(UserService.class);
		User user = userService.findUserByLodoId(lodoId);

		StringBuilder sb = new StringBuilder();
		sb.append("&userId=" + user.getUserId());
		sb.append("&targetLevel=" + level);
		String response = HttpClientBridge.sendToGameServer(REQ_URL,
				sb.toString(), user.getUserId() + level);

		if (response.equals("") || response == null) {
			super.setErroCodeNum(SystemConstant.FAIL_CODE);
			super.setErroDescrip("修改玩家等级失败,请查看日志！");
		} else {
			JSONObject jsonObject = JSONObject.fromObject(response);
			if (jsonObject.containsKey(HttpClientBridge.RETURN_RC)
					&& jsonObject.getInt(HttpClientBridge.RETURN_RC) != HttpClientBridge.RETURN_CODE) {
				super.setErroCodeNum(Integer.parseInt(jsonObject.get(
						 HttpClientBridge.FAIL).toString()));
				super.setErroDescrip("修改玩家等级失败,请查看日志！");
				return ERROR;
			}
		}

		return SUCCESS;
	}

	public String getIsCommit() {
		return isCommit;
	}

	public void setIsCommit(String isCommit) {
		this.isCommit = isCommit;
	}

	public Integer getLodoId() {
		return lodoId;
	}

	public void setLodoId(Integer lodoId) {
		this.lodoId = lodoId;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

}
