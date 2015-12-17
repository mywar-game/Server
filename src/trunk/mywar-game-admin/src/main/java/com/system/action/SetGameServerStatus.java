package com.system.action;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import com.framework.common.ALDAdminActionSupport;
import com.framework.servicemanager.ServiceCacheFactory;
import com.opensymphony.xwork2.ModelDriven;
import com.system.bo.GameServerStatus;
import com.system.constant.ServerConstant;
import com.system.service.GameServerStatusService;

/**
 * 设置服务器状态
 * 
 * @author yezp
 */
public class SetGameServerStatus extends ALDAdminActionSupport implements
		ModelDriven<GameServerStatus> {

	private static final long serialVersionUID = -5870981667971931973L;

	private GameServerStatus gameServerStatus = new GameServerStatus();
	private Integer gameWebId;
	private String isCommit = "F";

	public String execute() {
		GameServerStatusService statusService = ServiceCacheFactory
				.getServiceCache().getService(GameServerStatusService.class);
		String serverId = gameServerStatus.getServerId();
		if (isCommit.equals("F")) {
			gameServerStatus = statusService.getServerStatusByServerId(
					serverId, gameWebId);
			if (gameServerStatus == null) {
				gameServerStatus = new GameServerStatus();
				gameServerStatus.setServerId(serverId);
			}

			return INPUT;
		}

		// 开启服务器
		if (gameServerStatus.getStatus() == ServerConstant.SERVER_STATUS_CLOSE) {
			gameServerStatus = statusService.getServerStatusByServerId(
					serverId, gameWebId);
			gameServerStatus.setStatus(ServerConstant.SERVER_STATUS_OPEN);
		} else {
			gameServerStatus.setStatus(ServerConstant.SERVER_STATUS_CLOSE);
			try {
				gameServerStatus.setNotice(URLDecoder.decode(
						gameServerStatus.getNotice(), "UTF-8"));
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}

		statusService.setServerStatus(gameServerStatus, gameWebId);
		return SUCCESS;
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

	public GameServerStatus getGameServerStatus() {
		return gameServerStatus;
	}

	public void setGameServerStatus(GameServerStatus gameServerStatus) {
		this.gameServerStatus = gameServerStatus;
	}

	@Override
	public GameServerStatus getModel() {
		// TODO Auto-generated method stub
		return gameServerStatus;
	}

}
