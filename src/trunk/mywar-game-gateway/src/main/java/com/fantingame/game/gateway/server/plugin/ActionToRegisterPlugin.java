package com.fantingame.game.gateway.server.plugin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.fandingame.game.framework.plugin.IAppPlugin;
import com.fantingame.game.gateway.server.service.ServerActionService;
import com.fantingame.game.server.actionmanager.ActionManager;
import com.fantingame.game.server.msg.ServerType;

public class ActionToRegisterPlugin implements IAppPlugin {
	@Autowired
	private ServerActionService serverActionService;
	
	@Override
	public void shutdown() throws Exception {

	}

	@Override
	public void startup() throws Exception {
		List<String> allActionNames = ActionManager.getAllActionName();
		for(String actionName:allActionNames){
			serverActionService.addActionName(actionName, ServerType.GATEWAY_SERVER);
		}
	}

	@Override
	public int cpOrder() {
		return 0;
	}

}
