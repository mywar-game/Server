package com.system.action;

import java.sql.Timestamp;

import net.sf.json.JSONObject;

import com.adminTool.bo.AdminChangeConstantLog;
import com.adminTool.service.AdminChangeConstantLogService;
import com.framework.client.http.HttpClientBridge;
import com.framework.common.ALDAdminActionSupport;
import com.framework.constant.SystemConstant;
import com.framework.servicemanager.ServiceCacheFactory;
import com.system.bo.GameWeb;
import com.system.service.GameWebService;

/**
 * 刷新服务器列表
 * 
 * @author yezp
 */
public class ReflashServerList extends ALDAdminActionSupport {

	private static final long serialVersionUID = -1689611980975043420L;

	private static final String REQ_URL = "/webApi/refreshServerList.do";

	private String gameWebId;

	public String execute() {
		GameWebService service = ServiceCacheFactory.getServiceCache()
				.getService(GameWebService.class);
		Integer id = 0;
		if (gameWebId != null && !gameWebId.equals("")) {
			id = Integer.parseInt(gameWebId);
		}

		GameWeb gameWeb = service.getGameWebById(id);
		String url = "http://" + gameWeb.getServerIp() + ":"
				+ gameWeb.getServerPort() + REQ_URL;
		String response = HttpClientBridge.sendToGameServer(url);

		if (response == null) {
			super.setErroCodeNum(SystemConstant.FAIL_CODE);
			super.setErroDescrip("刷新出现异常,请查看日志！");
		} else {
			JSONObject jsonObject = JSONObject.fromObject(response);
			if (jsonObject.containsKey(HttpClientBridge.RETURN_RC)
					&& jsonObject.getInt(HttpClientBridge.RETURN_RC) != HttpClientBridge.RETURN_CODE) {
				super.setErroCodeNum(Integer.parseInt(jsonObject.getString(
						HttpClientBridge.FAIL).toString()));
				super.setErroDescrip("查询失败！");
			}
		}

		// 记录日志
		AdminChangeConstantLog adminChangeConstantLog = new AdminChangeConstantLog();
		adminChangeConstantLog.setSysNum(0);
		adminChangeConstantLog
				.setAdminName(super.getAdminUser().getAdminName());
		adminChangeConstantLog.setChangeTime(new Timestamp(System
				.currentTimeMillis()));
		adminChangeConstantLog.setConstantName("ReflashServerList");
		adminChangeConstantLog.setChangeType(1);
		adminChangeConstantLog.setChangeDetail("刷新服务器列表 ");
		AdminChangeConstantLogService adminChangeConstantLogService = ServiceCacheFactory
				.getServiceCache().getService(
						AdminChangeConstantLogService.class);
		adminChangeConstantLogService.saveOne(adminChangeConstantLog);
		return SUCCESS;
	}

	public String getGameWebId() {
		return gameWebId;
	}

	public void setGameWebId(String gameWebId) {
		this.gameWebId = gameWebId;
	}

}
