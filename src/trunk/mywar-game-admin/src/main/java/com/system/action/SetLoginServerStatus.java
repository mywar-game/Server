package com.system.action;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import com.framework.common.ALDAdminActionSupport;
import com.framework.servicemanager.ServiceCacheFactory;
import com.opensymphony.xwork2.ModelDriven;
import com.system.bo.LoginServerStatus;
import com.system.constant.ServerConstant;
import com.system.service.LoginServerStatusService;

/**
 * 设置区服务器开启状态
 * 
 * @author yezp
 */
public class SetLoginServerStatus extends ALDAdminActionSupport implements
		ModelDriven<LoginServerStatus> {

	private static final long serialVersionUID = -8519503872379994275L;

	private LoginServerStatus loginServerStatus = new LoginServerStatus();
	private String serverName;
	private Integer gameWebId;
	private String isCommit = "F";

	public String execute() {
		LoginServerStatusService statusService = ServiceCacheFactory
				.getServiceCache().getService(LoginServerStatusService.class);

		if (isCommit.equals("F")) {
			try {
				serverName = URLDecoder.decode(serverName, "UTF-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}

			loginServerStatus = statusService.getLoginServerStatus(gameWebId);
			if (loginServerStatus == null) {
				loginServerStatus = new LoginServerStatus();
			}
			return INPUT;
		}

		// 开启
		if (loginServerStatus.getStatus() == ServerConstant.SERVER_STATUS_CLOSE) {
			loginServerStatus = statusService.getLoginServerStatus(gameWebId);
			loginServerStatus.setStatus(ServerConstant.SERVER_STATUS_OPEN);
		} else {
			loginServerStatus.setStatus(ServerConstant.SERVER_STATUS_CLOSE);
			try {
				loginServerStatus.setNotice(URLDecoder.decode(
						loginServerStatus.getNotice(), "UTF-8"));
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}

		statusService.setLoginServerStatus(loginServerStatus, gameWebId);
		return SUCCESS;
	}

	public String getServerName() {
		return serverName;
	}

	public void setServerName(String serverName) {
		this.serverName = serverName;
	}

	public Integer getGameWebId() {
		return gameWebId;
	}

	public void setGameWebId(Integer gameWebId) {
		this.gameWebId = gameWebId;
	}

	public String getIsCommit() {
		return isCommit;
	}

	public void setIsCommit(String isCommit) {
		this.isCommit = isCommit;
	}

	public LoginServerStatus getLoginServerStatus() {
		return loginServerStatus;
	}

	public void setLoginServerStatus(LoginServerStatus loginServerStatus) {
		this.loginServerStatus = loginServerStatus;
	}

	@Override
	public LoginServerStatus getModel() {
		// TODO Auto-generated method stub
		return loginServerStatus;
	}

}
