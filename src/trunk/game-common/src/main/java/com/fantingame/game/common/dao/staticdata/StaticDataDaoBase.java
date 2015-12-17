package com.fantingame.game.common.dao.staticdata;

import java.util.ArrayList;
import java.util.List;

import com.fandingame.game.framework.event.ModuleEventBase;
import com.fandingame.game.framework.event.ModuleEventHandler;
import com.fandingame.game.framework.log.LogSystem;
import com.fandingame.game.framework.plugin.IAppPlugin;


public abstract class StaticDataDaoBase implements ModuleEventHandler,IAppPlugin{
    public static final String RELOAD_ALL = "ALL";
    public static final String CLASS_NAME=  "className";
	@Override
	public void handler(String handlerType, ModuleEventBase baseModuleEvent) {
		String className = baseModuleEvent.getStringValue(CLASS_NAME, "");
		if(className.equals(RELOAD_ALL)||this.getClass().getSimpleName().equals(className)){
			LogSystem.info("reload data"+this.getClass().getSimpleName()+",className = "+className);
			this.reload();
		}else{
			LogSystem.info("skip reload data"+this.getClass().getSimpleName()+",className = "+className);
		}
	}
	@Override
	public List<String> getHandlerType() {
		List<String> eventList = new ArrayList<String>();
		eventList.add("reload_static_data_event");
		return eventList;
	}

	public abstract void reload();
	
	@Override
	public void shutdown() throws Exception {
	}
	@Override
	public int order() {
		return 0;
	}
	@Override
	public int cpOrder(){
		return -99000;
	}

}
