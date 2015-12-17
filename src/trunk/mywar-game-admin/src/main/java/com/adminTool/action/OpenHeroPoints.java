package com.adminTool.action;

import net.sf.json.JSONObject;

import com.adminTool.bo.User;
import com.adminTool.service.UserService;
import com.framework.client.http.HttpClientBridge;
import com.framework.common.ALDAdminActionSupport;
import com.framework.constant.SystemConstant;
import com.framework.servicemanager.ServiceCacheFactory;

/**
 * 开启英雄关卡
 * 
 * @author yezp
 */
public class OpenHeroPoints extends ALDAdminActionSupport {

	private static final long serialVersionUID = -5113205628893784211L;

	private static final String REQ_URL = "updateUserForces.do";
	private String isCommit = "F";
	private Integer lodoId;
	private Integer pointsId;

	public String execute() {
		if (isCommit.equals("F")) {
			return INPUT;
		}

		if (lodoId == null || lodoId == 0) {
			return INPUT;
		}

		if (lodoId == null || pointsId == 0) {
			pointsId = -1;
		}

		UserService userService = ServiceCacheFactory.getServiceCache()
				.getService(UserService.class);
		User user = userService.findUserByLodoId(lodoId);

		StringBuilder sb = new StringBuilder();
		sb.append("&userId=" + user.getUserId());
		sb.append("&targetforcesId=" + pointsId);

		String response = HttpClientBridge.sendToGameServer(REQ_URL,
				sb.toString(), user.getUserId() + pointsId);

		if (response == null) {
			super.setErroCodeNum(SystemConstant.FAIL_CODE);
			super.setErroDescrip("开启关卡失败,请查看日志！");
		} else {
			JSONObject jsonObject = JSONObject.fromObject(response);
			if (jsonObject.containsKey(HttpClientBridge.RETURN_RT)
					&& jsonObject.getInt(HttpClientBridge.RETURN_RT) != HttpClientBridge.RETURN_CODE) {
//				super.setErroCodeNum(Integer.parseInt(jsonObject.get(
//						HttpClientBridge.FAIL).toString()));
				super.setErroDescrip("开启关卡失败,请查看日志！");
			}
		}

		super.setErroCodeNum(0);
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

	public Integer getPointsId() {
		return pointsId;
	}

	public void setPointsId(Integer pointsId) {
		this.pointsId = pointsId;
	}

}
