package com.fantingame.game.battle;

import com.fandingame.game.framework.context.SpringLoad;
import com.fandingame.game.framework.log.LogSystem;
import com.fandingame.game.framework.plugin.AppPluginFactory;


public class BattleRun {
	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		SpringLoad.getApplicationLoad();
		// 启动各个应用插件
		AppPluginFactory.startup();
		LogSystem.info("战斗服务器启动成功！");
	}

}
