package com.system.action;

import java.util.List;
import java.util.Map;

import com.framework.common.ALDAdminPageActionSupport;
import com.framework.servicemanager.ServiceCacheFactory;
import com.system.bo.Command;
import com.system.bo.GameWeb;
import com.system.service.CommandService;
import com.system.service.GameWebService;

/**
 * 通知列表
 * 
 * @author yezp
 */
public class CommandDetails extends ALDAdminPageActionSupport {

	private static final long serialVersionUID = -6730327320364071512L;
	private String serverId;
	private List<Command> cmdList;
	private Map<Integer, GameWeb> map;

	public String execute() {
		GameWebService service = ServiceCacheFactory.getServiceCache()
				.getService(GameWebService.class);
		CommandService commandService = ServiceCacheFactory.getServiceCache()
				.getService(CommandService.class);
		map = service.findGameWebServerIdMap();
		cmdList = commandService.getCommandDetails(serverId);
		return SUCCESS;
	}

	public List<Command> getCmdList() {
		return cmdList;
	}

	public void setCmdList(List<Command> cmdList) {
		this.cmdList = cmdList;
	}

	public Map<Integer, GameWeb> getMap() {
		return map;
	}

	public void setMap(Map<Integer, GameWeb> map) {
		this.map = map;
	}

	public String getServerId() {
		return serverId;
	}

	public void setServerId(String serverId) {
		this.serverId = serverId;
	}


}
